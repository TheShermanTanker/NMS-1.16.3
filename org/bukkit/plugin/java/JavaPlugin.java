/*     */ package org.bukkit.plugin.java;
/*     */ 
/*     */ import com.destroystokyo.paper.utils.PaperPluginLogger;
/*     */ import com.google.common.base.Charsets;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Reader;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.command.PluginCommand;
/*     */ import org.bukkit.configuration.Configuration;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.generator.ChunkGenerator;
/*     */ import org.bukkit.plugin.PluginBase;
/*     */ import org.bukkit.plugin.PluginDescriptionFile;
/*     */ import org.bukkit.plugin.PluginLoader;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public abstract class JavaPlugin
/*     */   extends PluginBase {
/*     */   private boolean isEnabled = false;
/*  36 */   private PluginLoader loader = null;
/*  37 */   private Server server = null;
/*  38 */   private File file = null;
/*  39 */   private PluginDescriptionFile description = null;
/*  40 */   private File dataFolder = null;
/*  41 */   private ClassLoader classLoader = null;
/*     */   private boolean naggable = true;
/*  43 */   private FileConfiguration newConfig = null;
/*  44 */   private File configFile = null;
/*  45 */   Logger logger = null;
/*     */   
/*     */   public JavaPlugin() {
/*  48 */     ClassLoader classLoader = getClass().getClassLoader();
/*  49 */     if (!(classLoader instanceof PluginClassLoader)) {
/*  50 */       throw new IllegalStateException("JavaPlugin requires " + PluginClassLoader.class.getName());
/*     */     }
/*  52 */     ((PluginClassLoader)classLoader).initialize(this);
/*     */   }
/*     */   
/*     */   protected JavaPlugin(@NotNull JavaPluginLoader loader, @NotNull PluginDescriptionFile description, @NotNull File dataFolder, @NotNull File file) {
/*  56 */     ClassLoader classLoader = getClass().getClassLoader();
/*  57 */     if (classLoader instanceof PluginClassLoader) {
/*  58 */       throw new IllegalStateException("Cannot use initialization constructor at runtime");
/*     */     }
/*  60 */     init(loader, loader.server, description, dataFolder, file, classLoader);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public final File getDataFolder() {
/*  72 */     return this.dataFolder;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public final PluginLoader getPluginLoader() {
/*  83 */     return this.loader;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public final Server getServer() {
/*  94 */     return this.server;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean isEnabled() {
/* 105 */     return this.isEnabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   protected File getFile() {
/* 115 */     return this.file;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public final PluginDescriptionFile getDescription() {
/* 126 */     return this.description;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public FileConfiguration getConfig() {
/* 132 */     if (this.newConfig == null) {
/* 133 */       reloadConfig();
/*     */     }
/* 135 */     return this.newConfig;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected final Reader getTextResource(@NotNull String file) {
/* 150 */     InputStream in = getResource(file);
/*     */     
/* 152 */     return (in == null) ? null : new InputStreamReader(in, Charsets.UTF_8);
/*     */   }
/*     */ 
/*     */   
/*     */   public void reloadConfig() {
/* 157 */     this.newConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(this.configFile);
/*     */     
/* 159 */     InputStream defConfigStream = getResource("config.yml");
/* 160 */     if (defConfigStream == null) {
/*     */       return;
/*     */     }
/*     */     
/* 164 */     this.newConfig.setDefaults((Configuration)YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream, Charsets.UTF_8)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveConfig() {
/*     */     try {
/* 170 */       getConfig().save(this.configFile);
/* 171 */     } catch (IOException ex) {
/* 172 */       this.logger.log(Level.SEVERE, "Could not save config to " + this.configFile, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveDefaultConfig() {
/* 178 */     if (!this.configFile.exists()) {
/* 179 */       saveResource("config.yml", false);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveResource(@NotNull String resourcePath, boolean replace) {
/* 185 */     if (resourcePath == null || resourcePath.equals("")) {
/* 186 */       throw new IllegalArgumentException("ResourcePath cannot be null or empty");
/*     */     }
/*     */     
/* 189 */     resourcePath = resourcePath.replace('\\', '/');
/* 190 */     InputStream in = getResource(resourcePath);
/* 191 */     if (in == null) {
/* 192 */       throw new IllegalArgumentException("The embedded resource '" + resourcePath + "' cannot be found in " + this.file);
/*     */     }
/*     */     
/* 195 */     File outFile = new File(this.dataFolder, resourcePath);
/* 196 */     int lastIndex = resourcePath.lastIndexOf('/');
/* 197 */     File outDir = new File(this.dataFolder, resourcePath.substring(0, (lastIndex >= 0) ? lastIndex : 0));
/*     */     
/* 199 */     if (!outDir.exists()) {
/* 200 */       outDir.mkdirs();
/*     */     }
/*     */     
/*     */     try {
/* 204 */       if (!outFile.exists() || replace) {
/* 205 */         OutputStream out = new FileOutputStream(outFile);
/* 206 */         byte[] buf = new byte[1024];
/*     */         int len;
/* 208 */         while ((len = in.read(buf)) > 0) {
/* 209 */           out.write(buf, 0, len);
/*     */         }
/* 211 */         out.close();
/* 212 */         in.close();
/*     */       } else {
/* 214 */         this.logger.log(Level.WARNING, "Could not save " + outFile.getName() + " to " + outFile + " because " + outFile.getName() + " already exists.");
/*     */       } 
/* 216 */     } catch (IOException ex) {
/* 217 */       this.logger.log(Level.SEVERE, "Could not save " + outFile.getName() + " to " + outFile, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public InputStream getResource(@NotNull String filename) {
/* 224 */     if (filename == null) {
/* 225 */       throw new IllegalArgumentException("Filename cannot be null");
/*     */     }
/*     */     
/*     */     try {
/* 229 */       URL url = getClassLoader().getResource(filename);
/*     */       
/* 231 */       if (url == null) {
/* 232 */         return null;
/*     */       }
/*     */       
/* 235 */       URLConnection connection = url.openConnection();
/* 236 */       connection.setUseCaches(false);
/* 237 */       return connection.getInputStream();
/* 238 */     } catch (IOException ex) {
/* 239 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   protected final ClassLoader getClassLoader() {
/* 250 */     return this.classLoader;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void setEnabled(boolean enabled) {
/* 259 */     if (this.isEnabled != enabled) {
/* 260 */       this.isEnabled = enabled;
/*     */       
/* 262 */       if (this.isEnabled) {
/* 263 */         onEnable();
/*     */       } else {
/* 265 */         onDisable();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   final void init(@NotNull PluginLoader loader, @NotNull Server server, @NotNull PluginDescriptionFile description, @NotNull File dataFolder, @NotNull File file, @NotNull ClassLoader classLoader) {
/* 272 */     this.loader = loader;
/* 273 */     this.server = server;
/* 274 */     this.file = file;
/* 275 */     this.description = description;
/* 276 */     this.dataFolder = dataFolder;
/* 277 */     this.classLoader = classLoader;
/* 278 */     this.configFile = new File(dataFolder, "config.yml");
/*     */     
/* 280 */     if (this.logger == null) {
/* 281 */       this.logger = PaperPluginLogger.getLogger(this.description);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
/* 291 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
/* 300 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public PluginCommand getCommand(@NotNull String name) {
/* 313 */     String alias = name.toLowerCase(Locale.ENGLISH);
/* 314 */     PluginCommand command = getServer().getPluginCommand(alias);
/*     */     
/* 316 */     if (command == null || command.getPlugin() != this) {
/* 317 */       command = getServer().getPluginCommand(this.description.getName().toLowerCase(Locale.ENGLISH) + ":" + alias);
/*     */     }
/*     */     
/* 320 */     if (command != null && command.getPlugin() == this) {
/* 321 */       return command;
/*     */     }
/* 323 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLoad() {}
/*     */ 
/*     */   
/*     */   public void onDisable() {}
/*     */ 
/*     */   
/*     */   public void onEnable() {}
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ChunkGenerator getDefaultWorldGenerator(@NotNull String worldName, @Nullable String id) {
/* 339 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean isNaggable() {
/* 344 */     return this.naggable;
/*     */   }
/*     */ 
/*     */   
/*     */   public final void setNaggable(boolean canNag) {
/* 349 */     this.naggable = canNag;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Logger getLogger() {
/* 355 */     return this.logger;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String toString() {
/* 361 */     return this.description.getFullName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static <T extends JavaPlugin> T getPlugin(@NotNull Class<T> clazz) {
/* 389 */     Validate.notNull(clazz, "Null class cannot have a plugin");
/* 390 */     if (!JavaPlugin.class.isAssignableFrom(clazz)) {
/* 391 */       throw new IllegalArgumentException(clazz + " does not extend " + JavaPlugin.class);
/*     */     }
/* 393 */     ClassLoader cl = clazz.getClassLoader();
/* 394 */     if (!(cl instanceof PluginClassLoader)) {
/* 395 */       throw new IllegalArgumentException(clazz + " is not initialized by " + PluginClassLoader.class);
/*     */     }
/* 397 */     JavaPlugin plugin = ((PluginClassLoader)cl).plugin;
/* 398 */     if (plugin == null) {
/* 399 */       throw new IllegalStateException("Cannot get plugin for " + clazz + " from a static initializer");
/*     */     }
/* 401 */     return clazz.cast(plugin);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static JavaPlugin getProvidingPlugin(@NotNull Class<?> clazz) {
/* 418 */     Validate.notNull(clazz, "Null class cannot have a plugin");
/* 419 */     ClassLoader cl = clazz.getClassLoader();
/* 420 */     if (!(cl instanceof PluginClassLoader)) {
/* 421 */       throw new IllegalArgumentException(clazz + " is not provided by " + PluginClassLoader.class);
/*     */     }
/* 423 */     JavaPlugin plugin = ((PluginClassLoader)cl).plugin;
/* 424 */     if (plugin == null) {
/* 425 */       throw new IllegalStateException("Cannot get plugin for " + clazz + " from a static initializer");
/*     */     }
/* 427 */     return plugin;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\plugin\java\JavaPlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */