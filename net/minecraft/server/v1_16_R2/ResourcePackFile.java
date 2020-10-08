/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.base.Splitter;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Enumeration;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Set;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipFile;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;
/*     */ 
/*     */ public class ResourcePackFile
/*     */   extends ResourcePackAbstract
/*     */ {
/*  23 */   public static final Splitter b = Splitter.on('/').omitEmptyStrings().limit(3);
/*     */   private ZipFile c;
/*     */   
/*     */   public ResourcePackFile(File var0) {
/*  27 */     super(var0);
/*     */   }
/*     */   
/*     */   private ZipFile b() throws IOException {
/*  31 */     if (this.c == null) {
/*  32 */       this.c = new ZipFile(this.a);
/*     */     }
/*     */     
/*  35 */     return this.c;
/*     */   }
/*     */ 
/*     */   
/*     */   protected InputStream a(String var0) throws IOException {
/*  40 */     ZipFile var1 = b();
/*  41 */     ZipEntry var2 = var1.getEntry(var0);
/*     */     
/*  43 */     if (var2 == null) {
/*  44 */       throw new ResourceNotFoundException(this.a, var0);
/*     */     }
/*     */     
/*  47 */     return var1.getInputStream(var2);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(String var0) {
/*     */     try {
/*  53 */       return (b().getEntry(var0) != null);
/*  54 */     } catch (IOException var1) {
/*  55 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<String> a(EnumResourcePackType var0) {
/*     */     ZipFile var1;
/*     */     try {
/*  63 */       var1 = b();
/*  64 */     } catch (IOException iOException) {
/*  65 */       return Collections.emptySet();
/*     */     } 
/*     */     
/*  68 */     Enumeration<? extends ZipEntry> var2 = var1.entries();
/*     */     
/*  70 */     Set<String> var3 = Sets.newHashSet();
/*     */     
/*  72 */     while (var2.hasMoreElements()) {
/*  73 */       ZipEntry var4 = var2.nextElement();
/*     */       
/*  75 */       String var5 = var4.getName();
/*  76 */       if (var5.startsWith(var0.a() + "/")) {
/*  77 */         List<String> var6 = Lists.newArrayList(b.split(var5));
/*  78 */         if (var6.size() > 1) {
/*  79 */           String var7 = var6.get(1);
/*  80 */           if (var7.equals(var7.toLowerCase(Locale.ROOT))) {
/*  81 */             var3.add(var7); continue;
/*     */           } 
/*  83 */           d(var7);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  89 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void finalize() throws Throwable {
/*  95 */     close();
/*  96 */     super.finalize();
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() {
/* 101 */     if (this.c != null) {
/* 102 */       IOUtils.closeQuietly(this.c);
/* 103 */       this.c = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<MinecraftKey> a(EnumResourcePackType var0, String var1, String var2, int var3, Predicate<String> var4) {
/*     */     ZipFile var5;
/*     */     try {
/* 111 */       var5 = b();
/* 112 */     } catch (IOException iOException) {
/* 113 */       return Collections.emptySet();
/*     */     } 
/* 115 */     Enumeration<? extends ZipEntry> var6 = var5.entries();
/* 116 */     List<MinecraftKey> var7 = Lists.newArrayList();
/* 117 */     String var8 = var0.a() + "/" + var1 + "/";
/* 118 */     String var9 = var8 + var2 + "/";
/*     */     
/* 120 */     while (var6.hasMoreElements()) {
/* 121 */       ZipEntry var10 = var6.nextElement();
/* 122 */       if (var10.isDirectory()) {
/*     */         continue;
/*     */       }
/*     */       
/* 126 */       String var11 = var10.getName();
/* 127 */       if (var11.endsWith(".mcmeta") || !var11.startsWith(var9)) {
/*     */         continue;
/*     */       }
/*     */       
/* 131 */       String var12 = var11.substring(var8.length());
/* 132 */       String[] var13 = var12.split("/");
/* 133 */       if (var13.length >= var3 + 1 && var4.test(var13[var13.length - 1])) {
/* 134 */         var7.add(new MinecraftKey(var1, var12));
/*     */       }
/*     */     } 
/*     */     
/* 138 */     return var7;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ResourcePackFile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */