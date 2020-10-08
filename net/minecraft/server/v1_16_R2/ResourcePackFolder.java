/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.base.CharMatcher;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.io.File;
/*     */ import java.io.FileFilter;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Set;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.io.filefilter.DirectoryFileFilter;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ResourcePackFolder
/*     */   extends ResourcePackAbstract
/*     */ {
/*  26 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*  28 */   private static final boolean c = (SystemUtils.i() == SystemUtils.OS.WINDOWS);
/*  29 */   private static final CharMatcher d = CharMatcher.is('\\');
/*     */   
/*     */   public ResourcePackFolder(File var0) {
/*  32 */     super(var0);
/*     */   }
/*     */   
/*     */   public static boolean a(File var0, String var1) throws IOException {
/*  36 */     String var2 = var0.getCanonicalPath();
/*     */ 
/*     */     
/*  39 */     if (c) {
/*  40 */       var2 = d.replaceFrom(var2, '/');
/*     */     }
/*  42 */     return var2.endsWith(var1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected InputStream a(String var0) throws IOException {
/*  47 */     File var1 = e(var0);
/*  48 */     if (var1 == null) {
/*  49 */       throw new ResourceNotFoundException(this.a, var0);
/*     */     }
/*     */     
/*  52 */     return new FileInputStream(var1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean c(String var0) {
/*  57 */     return (e(var0) != null);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private File e(String var0) {
/*     */     try {
/*  63 */       File var1 = new File(this.a, var0);
/*  64 */       if (var1.isFile() && a(var1, var0)) {
/*  65 */         return var1;
/*     */       }
/*  67 */     } catch (IOException iOException) {}
/*     */     
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<String> a(EnumResourcePackType var0) {
/*  74 */     Set<String> var1 = Sets.newHashSet();
/*  75 */     File var2 = new File(this.a, var0.a());
/*     */     
/*  77 */     File[] var3 = var2.listFiles((FileFilter)DirectoryFileFilter.DIRECTORY);
/*  78 */     if (var3 != null) {
/*  79 */       for (File var7 : var3) {
/*  80 */         String var8 = a(var2, var7);
/*     */         
/*  82 */         if (var8.equals(var8.toLowerCase(Locale.ROOT))) {
/*  83 */           var1.add(var8.substring(0, var8.length() - 1));
/*     */         } else {
/*  85 */           d(var8);
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*  90 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {}
/*     */ 
/*     */   
/*     */   public Collection<MinecraftKey> a(EnumResourcePackType var0, String var1, String var2, int var3, Predicate<String> var4) {
/*  99 */     File var5 = new File(this.a, var0.a());
/* 100 */     List<MinecraftKey> var6 = Lists.newArrayList();
/* 101 */     a(new File(new File(var5, var1), var2), var3, var1, var6, var2 + "/", var4);
/* 102 */     return var6;
/*     */   }
/*     */   
/*     */   private void a(File var0, int var1, String var2, List<MinecraftKey> var3, String var4, Predicate<String> var5) {
/* 106 */     File[] var6 = var0.listFiles();
/* 107 */     if (var6 != null)
/* 108 */       for (File var10 : var6) {
/* 109 */         if (var10.isDirectory()) {
/* 110 */           if (var1 > 0) {
/* 111 */             a(var10, var1 - 1, var2, var3, var4 + var10.getName() + "/", var5);
/*     */           }
/*     */         }
/* 114 */         else if (!var10.getName().endsWith(".mcmeta") && var5.test(var10.getName())) {
/*     */           try {
/* 116 */             var3.add(new MinecraftKey(var2, var4 + var10.getName()));
/* 117 */           } catch (ResourceKeyInvalidException var11) {
/* 118 */             LOGGER.error(var11.getMessage());
/*     */           } 
/*     */         } 
/*     */       }  
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ResourcePackFolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */