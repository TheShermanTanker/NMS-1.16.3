/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.google.common.base.Charsets;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.attribute.FileAttribute;
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import java.util.stream.Collectors;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;
/*     */ 
/*     */ public class HashCache {
/*  22 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   private final Path b;
/*     */   
/*     */   private final Path c;
/*     */   
/*     */   private int d;
/*  29 */   private final Map<Path, String> e = Maps.newHashMap();
/*  30 */   private final Map<Path, String> f = Maps.newHashMap();
/*  31 */   private final Set<Path> g = Sets.newHashSet();
/*     */   
/*     */   public HashCache(Path var0, String var1) throws IOException {
/*  34 */     this.b = var0;
/*     */     
/*  36 */     Path var2 = var0.resolve(".cache");
/*  37 */     Files.createDirectories(var2, (FileAttribute<?>[])new FileAttribute[0]);
/*  38 */     this.c = var2.resolve(var1);
/*     */     
/*  40 */     c().forEach(var0 -> (String)this.e.put(var0, ""));
/*     */     
/*  42 */     if (Files.isReadable(this.c))
/*  43 */       IOUtils.readLines(Files.newInputStream(this.c, new java.nio.file.OpenOption[0]), Charsets.UTF_8).forEach(var1 -> {
/*     */             int var2 = var1.indexOf(' ');
/*     */             this.e.put(var0.resolve(var1.substring(var2 + 1)), var1.substring(0, var2));
/*     */           }); 
/*     */   }
/*     */   
/*     */   public void a() throws IOException {
/*     */     Writer var0;
/*  51 */     b();
/*     */ 
/*     */     
/*     */     try {
/*  55 */       var0 = Files.newBufferedWriter(this.c, new java.nio.file.OpenOption[0]);
/*  56 */     } catch (IOException var1) {
/*  57 */       LOGGER.warn("Unable write cachefile {}: {}", this.c, var1.toString());
/*     */       
/*     */       return;
/*     */     } 
/*  61 */     IOUtils.writeLines((Collection)this.f
/*  62 */         .entrySet().stream().map(var0 -> (String)var0.getValue() + ' ' + this.b.relativize((Path)var0.getKey())).collect(Collectors.toList()), 
/*  63 */         System.lineSeparator(), var0);
/*     */ 
/*     */ 
/*     */     
/*  67 */     var0.close();
/*     */     
/*  69 */     LOGGER.debug("Caching: cache hits: {}, created: {} removed: {}", 
/*  70 */         Integer.valueOf(this.d), 
/*  71 */         Integer.valueOf(this.f.size() - this.d), 
/*  72 */         Integer.valueOf(this.e.size()));
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public String a(Path var0) {
/*  78 */     return this.e.get(var0);
/*     */   }
/*     */   
/*     */   public void a(Path var0, String var1) {
/*  82 */     this.f.put(var0, var1);
/*  83 */     if (Objects.equals(this.e.remove(var0), var1)) {
/*  84 */       this.d++;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean b(Path var0) {
/*  89 */     return this.e.containsKey(var0);
/*     */   }
/*     */   
/*     */   public void c(Path var0) {
/*  93 */     this.g.add(var0);
/*     */   }
/*     */   
/*     */   private void b() throws IOException {
/*  97 */     c().forEach(var0 -> {
/*     */           if (b(var0) && !this.g.contains(var0)) {
/*     */             try {
/*     */               Files.delete(var0);
/* 101 */             } catch (IOException var1) {
/*     */               LOGGER.debug("Unable to delete: {} ({})", var0, var1.toString());
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   private Stream<Path> c() throws IOException {
/* 109 */     return Files.walk(this.b, new java.nio.file.FileVisitOption[0]).filter(var0 -> (!Objects.equals(this.c, var0) && !Files.isDirectory(var0, new java.nio.file.LinkOption[0])));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\HashCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */