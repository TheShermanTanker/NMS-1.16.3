/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.attribute.FileAttribute;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.io.FileUtils;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class jp
/*     */   implements DebugReportProvider
/*     */ {
/*     */   @Nullable
/*  32 */   private static final Path b = null;
/*     */   
/*  34 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   private final DebugReportGenerator d;
/*     */   
/*  38 */   private final List<a> e = Lists.newArrayList();
/*     */   
/*     */   public jp(DebugReportGenerator var0) {
/*  41 */     this.d = var0;
/*     */   }
/*     */   
/*     */   public jp a(a var0) {
/*  45 */     this.e.add(var0);
/*  46 */     return this;
/*     */   }
/*     */   
/*     */   private NBTTagCompound a(String var0, NBTTagCompound var1) {
/*  50 */     NBTTagCompound var2 = var1;
/*  51 */     for (a var4 : this.e) {
/*  52 */       var2 = var4.a(var0, var2);
/*     */     }
/*  54 */     return var2;
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface a {
/*     */     NBTTagCompound a(String param1String, NBTTagCompound param1NBTTagCompound); }
/*     */   
/*     */   static class b { private final String a;
/*     */     private final byte[] b;
/*     */     
/*     */     public b(String var0, byte[] var1, @Nullable String var2, String var3) {
/*  65 */       this.a = var0;
/*  66 */       this.b = var1;
/*  67 */       this.c = var2;
/*  68 */       this.d = var3;
/*     */     }
/*     */     @Nullable
/*     */     private final String c; private final String d; }
/*     */   
/*     */   public void a(HashCache var0) throws IOException {
/*  74 */     Path var1 = this.d.b();
/*     */     
/*  76 */     List<CompletableFuture<b>> var2 = Lists.newArrayList();
/*     */     
/*  78 */     for (Path var4 : this.d.a()) {
/*  79 */       Files.walk(var4, new java.nio.file.FileVisitOption[0]).filter(var0 -> var0.toString().endsWith(".snbt")).forEach(var2 -> var0.add(CompletableFuture.supplyAsync((), SystemUtils.f())));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  84 */     ((List)SystemUtils.<b>b(var2).join()).stream().filter(Objects::nonNull).forEach(var2 -> a(var0, var2, var1));
/*     */   }
/*     */ 
/*     */   
/*     */   public String a() {
/*  89 */     return "SNBT -> NBT";
/*     */   }
/*     */   
/*     */   private String a(Path var0, Path var1) {
/*  93 */     String var2 = var0.relativize(var1).toString().replaceAll("\\\\", "/");
/*  94 */     return var2.substring(0, var2.length() - ".snbt".length());
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private b a(Path var0, String var1) {
/*  99 */     try (BufferedReader var2 = Files.newBufferedReader(var0)) {
/* 100 */       String var9, var4 = IOUtils.toString(var2);
/* 101 */       NBTTagCompound var5 = a(var1, MojangsonParser.parse(var4));
/* 102 */       ByteArrayOutputStream var6 = new ByteArrayOutputStream();
/* 103 */       NBTCompressedStreamTools.a(var5, var6);
/* 104 */       byte[] var7 = var6.toByteArray();
/* 105 */       String var8 = a.hashBytes(var7).toString();
/*     */       
/* 107 */       if (b != null) {
/* 108 */         var9 = var5.a("    ", 0).getString() + "\n";
/*     */       } else {
/* 110 */         var9 = null;
/*     */       } 
/* 112 */       return new b(var1, var7, var9, var8);
/* 113 */     } catch (CommandSyntaxException var2) {
/* 114 */       LOGGER.error("Couldn't convert {} from SNBT to NBT at {} as it's invalid SNBT", var1, var0, var2);
/* 115 */     } catch (IOException var2) {
/* 116 */       LOGGER.error("Couldn't convert {} from SNBT to NBT at {}", var1, var0, var2);
/*     */     } 
/* 118 */     return null;
/*     */   }
/*     */   
/*     */   private void a(HashCache var0, b var1, Path var2) {
/* 122 */     if (b.a(var1) != null) {
/* 123 */       Path path = b.resolve(b.b(var1) + ".snbt");
/*     */       try {
/* 125 */         FileUtils.write(path.toFile(), b.a(var1), StandardCharsets.UTF_8);
/* 126 */       } catch (IOException var4) {
/* 127 */         LOGGER.error("Couldn't write structure SNBT {} at {}", b.b(var1), path, var4);
/*     */       } 
/*     */     } 
/*     */     
/* 131 */     Path var3 = var2.resolve(b.b(var1) + ".nbt");
/*     */     try {
/* 133 */       if (!Objects.equals(var0.a(var3), b.c(var1)) || !Files.exists(var3, new java.nio.file.LinkOption[0])) {
/* 134 */         Files.createDirectories(var3.getParent(), (FileAttribute<?>[])new FileAttribute[0]);
/* 135 */         try (OutputStream var4 = Files.newOutputStream(var3, new java.nio.file.OpenOption[0])) {
/* 136 */           var4.write(b.d(var1));
/*     */         } 
/*     */       } 
/* 139 */       var0.a(var3, b.c(var1));
/* 140 */     } catch (IOException var4) {
/* 141 */       LOGGER.error("Couldn't write structure {} at {}", b.b(var1), var3, var4);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\jp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */