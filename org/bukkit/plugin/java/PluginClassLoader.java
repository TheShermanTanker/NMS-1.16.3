/*     */ package org.bukkit.plugin.java;
/*     */ import com.destroystokyo.paper.utils.PaperPluginLogger;
/*     */ import com.google.common.io.ByteStreams;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.net.URLClassLoader;
/*     */ import java.security.CodeSigner;
/*     */ import java.security.CodeSource;
/*     */ import java.util.Collections;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.jar.JarEntry;
/*     */ import java.util.jar.JarFile;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.plugin.InvalidPluginException;
/*     */ import org.bukkit.plugin.PluginDescriptionFile;
/*     */ import org.bukkit.plugin.SimplePluginManager;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public final class PluginClassLoader extends URLClassLoader {
/*     */   private final JavaPluginLoader loader;
/*     */   
/*     */   public JavaPlugin getPlugin() {
/*  32 */     return this.plugin;
/*     */   }
/*  34 */   private final Map<String, Class<?>> classes = new ConcurrentHashMap<>();
/*     */   private final PluginDescriptionFile description;
/*     */   private final File dataFolder;
/*     */   private final File file;
/*     */   private final JarFile jar;
/*     */   private final Manifest manifest;
/*     */   private final URL url;
/*     */   final JavaPlugin plugin;
/*     */   private JavaPlugin pluginInit;
/*     */   private IllegalStateException pluginState;
/*  44 */   private final Set<String> seenIllegalAccess = Collections.newSetFromMap(new ConcurrentHashMap<>());
/*     */   private Logger logger;
/*     */   
/*     */   static {
/*  48 */     ClassLoader.registerAsParallelCapable();
/*     */   }
/*     */   
/*     */   PluginClassLoader(@NotNull JavaPluginLoader loader, @Nullable ClassLoader parent, @NotNull PluginDescriptionFile description, @NotNull File dataFolder, @NotNull File file) throws IOException, InvalidPluginException, MalformedURLException {
/*  52 */     super(new URL[] { file.toURI().toURL() }, parent);
/*  53 */     Validate.notNull(loader, "Loader cannot be null");
/*     */     
/*  55 */     this.loader = loader;
/*  56 */     this.description = description;
/*  57 */     this.dataFolder = dataFolder;
/*  58 */     this.file = file;
/*  59 */     this.jar = new JarFile(file);
/*  60 */     this.manifest = this.jar.getManifest();
/*  61 */     this.url = file.toURI().toURL();
/*     */     
/*  63 */     this.logger = PaperPluginLogger.getLogger(description);
/*     */     try {
/*     */       Class<?> jarClass;
/*     */       Class<? extends JavaPlugin> pluginClass;
/*     */       try {
/*  68 */         jarClass = Class.forName(description.getMain(), true, this);
/*  69 */       } catch (ClassNotFoundException ex) {
/*  70 */         throw new InvalidPluginException("Cannot find main class `" + description.getMain() + "'", ex);
/*     */       } 
/*     */ 
/*     */       
/*     */       try {
/*  75 */         pluginClass = jarClass.asSubclass(JavaPlugin.class);
/*  76 */       } catch (ClassCastException ex) {
/*  77 */         throw new InvalidPluginException("main class `" + description.getMain() + "' does not extend JavaPlugin", ex);
/*     */       } 
/*     */       
/*  80 */       this.plugin = pluginClass.newInstance();
/*  81 */     } catch (IllegalAccessException ex) {
/*  82 */       Class<?> jarClass; throw new InvalidPluginException("No public constructor", jarClass);
/*  83 */     } catch (InstantiationException ex) {
/*  84 */       throw new InvalidPluginException("Abnormal plugin type", ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public URL getResource(String name) {
/*  90 */     return findResource(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public Enumeration<URL> getResources(String name) throws IOException {
/*  95 */     return findResources(name);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Class<?> findClass(String name) throws ClassNotFoundException {
/* 100 */     return findClass(name, true);
/*     */   }
/*     */   
/*     */   Class<?> findClass(@NotNull String name, boolean checkGlobal) throws ClassNotFoundException {
/* 104 */     if (name.startsWith("org.bukkit.") || name.startsWith("net.minecraft.")) {
/* 105 */       throw new ClassNotFoundException(name);
/*     */     }
/* 107 */     Class<?> result = this.classes.get(name);
/*     */     
/* 109 */     if (result == null) {
/* 110 */       if (checkGlobal) {
/* 111 */         result = this.loader.getClassByName(name, this);
/*     */         
/* 113 */         if (result != null) {
/* 114 */           PluginDescriptionFile provider = ((PluginClassLoader)result.getClassLoader()).description;
/*     */           
/* 116 */           if (provider != this.description && 
/* 117 */             !this.seenIllegalAccess.contains(provider.getName()) && 
/* 118 */             !((SimplePluginManager)this.loader.server.getPluginManager()).isTransitiveDepend(this.description, provider)) {
/*     */             
/* 120 */             this.seenIllegalAccess.add(provider.getName());
/* 121 */             if (this.plugin != null) {
/* 122 */               this.plugin.getLogger().log(Level.WARNING, "Loaded class {0} from {1} which is not a depend, softdepend or loadbefore of this plugin.", new Object[] { name, provider.getFullName() });
/*     */             } else {
/*     */               
/* 125 */               this.loader.server.getLogger().log(Level.WARNING, "[{0}] Loaded class {1} from {2} which is not a depend, softdepend or loadbefore of this plugin.", new Object[] { this.description.getName(), name, provider.getFullName() });
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 131 */       if (result == null) {
/* 132 */         String path = name.replace('.', '/').concat(".class");
/* 133 */         JarEntry entry = this.jar.getJarEntry(path);
/*     */         
/* 135 */         if (entry != null) {
/*     */ 
/*     */           
/* 138 */           try { InputStream is = this.jar.getInputStream(entry); 
/* 139 */             try { classBytes = ByteStreams.toByteArray(is);
/* 140 */               if (is != null) is.close();  } catch (Throwable throwable) { if (is != null) try { is.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException ex)
/* 141 */           { throw new ClassNotFoundException(name, ex); }
/*     */ 
/*     */           
/* 144 */           byte[] classBytes = this.loader.server.getUnsafe().processClass(this.description, path, classBytes);
/*     */           
/* 146 */           int dot = name.lastIndexOf('.');
/* 147 */           if (dot != -1) {
/* 148 */             String pkgName = name.substring(0, dot);
/* 149 */             if (getPackage(pkgName) == null) {
/*     */               try {
/* 151 */                 if (this.manifest != null) {
/* 152 */                   definePackage(pkgName, this.manifest, this.url);
/*     */                 } else {
/* 154 */                   definePackage(pkgName, null, null, null, null, null, null, null);
/*     */                 } 
/* 156 */               } catch (IllegalArgumentException ex) {
/* 157 */                 if (getPackage(pkgName) == null) {
/* 158 */                   throw new IllegalStateException("Cannot find package " + pkgName);
/*     */                 }
/*     */               } 
/*     */             }
/*     */           } 
/*     */           
/* 164 */           CodeSigner[] signers = entry.getCodeSigners();
/* 165 */           CodeSource source = new CodeSource(this.url, signers);
/*     */           
/* 167 */           result = defineClass(name, classBytes, 0, classBytes.length, source);
/*     */         } 
/*     */         
/* 170 */         if (result == null) {
/* 171 */           result = super.findClass(name);
/*     */         }
/*     */         
/* 174 */         if (result != null) {
/* 175 */           this.loader.setClass(name, result);
/*     */         }
/*     */         
/* 178 */         this.classes.put(name, result);
/*     */       } 
/*     */     } 
/*     */     
/* 182 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/*     */     try {
/* 188 */       super.close();
/*     */     } finally {
/* 190 */       this.jar.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   Set<String> getClasses() {
/* 196 */     return this.classes.keySet();
/*     */   }
/*     */   
/*     */   synchronized void initialize(@NotNull JavaPlugin javaPlugin) {
/* 200 */     Validate.notNull(javaPlugin, "Initializing plugin cannot be null");
/* 201 */     Validate.isTrue((javaPlugin.getClass().getClassLoader() == this), "Cannot initialize plugin outside of this class loader");
/* 202 */     if (this.plugin != null || this.pluginInit != null) {
/* 203 */       throw new IllegalArgumentException("Plugin already initialized!", this.pluginState);
/*     */     }
/*     */     
/* 206 */     this.pluginState = new IllegalStateException("Initial initialization");
/* 207 */     this.pluginInit = javaPlugin;
/*     */     
/* 209 */     javaPlugin.logger = this.logger;
/* 210 */     javaPlugin.init(this.loader, this.loader.server, this.description, this.dataFolder, this.file, this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 216 */     JavaPlugin currPlugin = (this.plugin != null) ? this.plugin : this.pluginInit;
/* 217 */     return "PluginClassLoader{plugin=" + currPlugin + ", pluginEnabled=" + (
/*     */       
/* 219 */       (currPlugin == null) ? "uninitialized" : (String)Boolean.valueOf(currPlugin.isEnabled())) + ", url=" + this.file + '}';
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\plugin\java\PluginClassLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */