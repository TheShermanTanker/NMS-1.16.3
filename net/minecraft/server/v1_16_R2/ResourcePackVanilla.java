/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.net.URL;
/*     */ import java.nio.file.FileSystem;
/*     */ import java.nio.file.FileSystemNotFoundException;
/*     */ import java.nio.file.FileSystems;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.Paths;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ResourcePackVanilla
/*     */   implements IResourcePack
/*     */ {
/*     */   public static Path a;
/*  38 */   private static final Logger LOGGER = LogManager.getLogger(); public static Class<?> b;
/*     */   
/*     */   static {
/*  41 */     e = SystemUtils.<Map<EnumResourcePackType, FileSystem>>a(Maps.newHashMap(), var0 -> {
/*     */           synchronized (ResourcePackVanilla.class) {
/*     */             for (EnumResourcePackType var5 : EnumResourcePackType.values()) {
/*     */               URL var6 = ResourcePackVanilla.class.getResource("/" + var5.a() + "/.mcassetsroot");
/*     */               try {
/*     */                 URI var7 = var6.toURI();
/*     */                 if ("jar".equals(var7.getScheme())) {
/*     */                   FileSystem var8;
/*     */                   try {
/*     */                     var8 = FileSystems.getFileSystem(var7);
/*  51 */                   } catch (FileSystemNotFoundException var9) {
/*     */                     var8 = FileSystems.newFileSystem(var7, Collections.emptyMap());
/*     */                   } 
/*     */                   var0.put(var5, var8);
/*     */                 } 
/*  56 */               } catch (URISyntaxException|IOException var7) {
/*     */                 LOGGER.error("Couldn't get a list of all vanilla resources", var7);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         });
/*     */   }
/*     */   private static final Map<EnumResourcePackType, FileSystem> e; public final Set<String> c;
/*     */   
/*     */   public ResourcePackVanilla(String... var0) {
/*  66 */     this.c = (Set<String>)ImmutableSet.copyOf((Object[])var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public InputStream b(String var0) throws IOException {
/*  71 */     if (var0.contains("/") || var0.contains("\\")) {
/*  72 */       throw new IllegalArgumentException("Root resources can only be filenames, not paths (no / allowed!)");
/*     */     }
/*  74 */     if (a != null) {
/*  75 */       Path var1 = a.resolve(var0);
/*  76 */       if (Files.exists(var1, new java.nio.file.LinkOption[0])) {
/*  77 */         return Files.newInputStream(var1, new java.nio.file.OpenOption[0]);
/*     */       }
/*     */     } 
/*  80 */     return a(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public InputStream a(EnumResourcePackType var0, MinecraftKey var1) throws IOException {
/*  85 */     InputStream var2 = c(var0, var1);
/*  86 */     if (var2 != null) {
/*  87 */       return var2;
/*     */     }
/*  89 */     throw new FileNotFoundException(var1.getKey());
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<MinecraftKey> a(EnumResourcePackType var0, String var1, String var2, int var3, Predicate<String> var4) {
/*  94 */     Set<MinecraftKey> var5 = Sets.newHashSet();
/*     */     
/*  96 */     if (a != null) {
/*     */       try {
/*  98 */         a(var5, var3, var1, a.resolve(var0.a()), var2, var4);
/*  99 */       } catch (IOException iOException) {}
/*     */ 
/*     */ 
/*     */       
/* 103 */       if (var0 == EnumResourcePackType.CLIENT_RESOURCES) {
/* 104 */         Enumeration<URL> var6 = null;
/*     */         try {
/* 106 */           var6 = b.getClassLoader().getResources(var0.a() + "/");
/* 107 */         } catch (IOException iOException) {}
/*     */         
/* 109 */         while (var6 != null && var6.hasMoreElements()) {
/*     */           try {
/* 111 */             URI var7 = ((URL)var6.nextElement()).toURI();
/* 112 */             if ("file".equals(var7.getScheme())) {
/* 113 */               a(var5, var3, var1, Paths.get(var7), var2, var4);
/*     */             }
/* 115 */           } catch (URISyntaxException|IOException uRISyntaxException) {}
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 122 */     try { URL var6 = ResourcePackVanilla.class.getResource("/" + var0.a() + "/.mcassetsroot");
/* 123 */       if (var6 == null) {
/* 124 */         LOGGER.error("Couldn't find .mcassetsroot, cannot load vanilla resources");
/* 125 */         return var5;
/*     */       } 
/* 127 */       URI var7 = var6.toURI();
/* 128 */       if ("file".equals(var7.getScheme())) {
/* 129 */         URL var8 = new URL(var6.toString().substring(0, var6.toString().length() - ".mcassetsroot".length()));
/* 130 */         Path var9 = Paths.get(var8.toURI());
/* 131 */         a(var5, var3, var1, var9, var2, var4);
/* 132 */       } else if ("jar".equals(var7.getScheme())) {
/* 133 */         Path var8 = ((FileSystem)e.get(var0)).getPath("/" + var0.a(), new String[0]);
/* 134 */         a(var5, var3, "minecraft", var8, var2, var4);
/*     */       } else {
/* 136 */         LOGGER.error("Unsupported scheme {} trying to list vanilla resources (NYI?)", var7);
/*     */       }  }
/* 138 */     catch (FileNotFoundException|java.nio.file.NoSuchFileException fileNotFoundException) {  }
/* 139 */     catch (URISyntaxException|IOException var6)
/* 140 */     { LOGGER.error("Couldn't get a list of all vanilla resources", var6); }
/*     */ 
/*     */     
/* 143 */     return var5;
/*     */   }
/*     */   
/*     */   private static void a(Collection<MinecraftKey> var0, int var1, String var2, Path var3, String var4, Predicate<String> var5) throws IOException {
/* 147 */     Path var6 = var3.resolve(var2);
/* 148 */     try (Stream<Path> var7 = Files.walk(var6.resolve(var4), var1, new java.nio.file.FileVisitOption[0])) {
/* 149 */       var7
/* 150 */         .filter(var1 -> (!var1.endsWith(".mcmeta") && Files.isRegularFile(var1, new java.nio.file.LinkOption[0]) && var0.test(var1.getFileName().toString())))
/* 151 */         .map(var2 -> new MinecraftKey(var0, var1.relativize(var2).toString().replaceAll("\\\\", "/")))
/* 152 */         .forEach(var0::add);
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected InputStream c(EnumResourcePackType var0, MinecraftKey var1) {
/* 158 */     String var2 = d(var0, var1);
/*     */     
/* 160 */     if (a != null) {
/* 161 */       Path var3 = a.resolve(var0.a() + "/" + var1.getNamespace() + "/" + var1.getKey());
/* 162 */       if (Files.exists(var3, new java.nio.file.LinkOption[0])) {
/*     */         try {
/* 164 */           return Files.newInputStream(var3, new java.nio.file.OpenOption[0]);
/* 165 */         } catch (IOException iOException) {}
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*     */     try {
/* 171 */       URL var3 = ResourcePackVanilla.class.getResource(var2);
/* 172 */       if (a(var2, var3)) {
/* 173 */         return var3.openStream();
/*     */       }
/* 175 */     } catch (IOException var3) {
/* 176 */       return ResourcePackVanilla.class.getResourceAsStream(var2);
/*     */     } 
/*     */     
/* 179 */     return null;
/*     */   }
/*     */   
/*     */   private static String d(EnumResourcePackType var0, MinecraftKey var1) {
/* 183 */     return "/" + var0.a() + "/" + var1.getNamespace() + "/" + var1.getKey();
/*     */   }
/*     */   
/*     */   private static boolean a(String var0, @Nullable URL var1) throws IOException {
/* 187 */     return (var1 != null && (var1.getProtocol().equals("jar") || ResourcePackFolder.a(new File(var1.getFile()), var0)));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected InputStream a(String var0) {
/* 192 */     return ResourcePackVanilla.class.getResourceAsStream("/" + var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(EnumResourcePackType var0, MinecraftKey var1) {
/* 197 */     String var2 = d(var0, var1);
/*     */     
/* 199 */     if (a != null) {
/* 200 */       Path var3 = a.resolve(var0.a() + "/" + var1.getNamespace() + "/" + var1.getKey());
/* 201 */       if (Files.exists(var3, new java.nio.file.LinkOption[0])) {
/* 202 */         return true;
/*     */       }
/*     */     } 
/*     */     
/*     */     try {
/* 207 */       URL var3 = ResourcePackVanilla.class.getResource(var2);
/* 208 */       return a(var2, var3);
/* 209 */     } catch (IOException iOException) {
/*     */ 
/*     */       
/* 212 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Set<String> a(EnumResourcePackType var0) {
/* 217 */     return this.c;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public <T> T a(ResourcePackMetaParser<T> var0) throws IOException {
/* 223 */     try (InputStream var1 = b("pack.mcmeta")) {
/* 224 */       return (T)ResourcePackAbstract.a((ResourcePackMetaParser)var0, var1);
/* 225 */     } catch (RuntimeException|FileNotFoundException var1) {
/* 226 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String a() {
/* 232 */     return "Default";
/*     */   }
/*     */   
/*     */   public void close() {}
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ResourcePackVanilla.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */