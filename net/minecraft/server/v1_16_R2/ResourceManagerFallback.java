/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FilterInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.function.Predicate;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ResourceManagerFallback
/*     */   implements IResourceManager
/*     */ {
/*  25 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*  27 */   protected final List<IResourcePack> a = Lists.newArrayList();
/*     */   private final EnumResourcePackType c;
/*     */   private final String d;
/*     */   
/*     */   public ResourceManagerFallback(EnumResourcePackType var0, String var1) {
/*  32 */     this.c = var0;
/*  33 */     this.d = var1;
/*     */   }
/*     */   
/*     */   public void a(IResourcePack var0) {
/*  37 */     this.a.add(var0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IResource a(MinecraftKey var0) throws IOException {
/*  47 */     e(var0);
/*     */     
/*  49 */     IResourcePack var1 = null;
/*  50 */     MinecraftKey var2 = d(var0);
/*     */     
/*  52 */     for (int var3 = this.a.size() - 1; var3 >= 0; var3--) {
/*  53 */       IResourcePack var4 = this.a.get(var3);
/*  54 */       if (var1 == null && var4.b(this.c, var2)) {
/*  55 */         var1 = var4;
/*     */       }
/*     */       
/*  58 */       if (var4.b(this.c, var0)) {
/*  59 */         InputStream var5 = null;
/*  60 */         if (var1 != null) {
/*  61 */           var5 = a(var2, var1);
/*     */         }
/*  63 */         return new Resource(var4.a(), var0, a(var0, var4), var5);
/*     */       } 
/*     */     } 
/*     */     
/*  67 */     throw new FileNotFoundException(var0.toString());
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
/*     */   protected InputStream a(MinecraftKey var0, IResourcePack var1) throws IOException {
/*  88 */     InputStream var2 = var1.a(this.c, var0);
/*  89 */     return LOGGER.isDebugEnabled() ? new a(var2, var0, var1.a()) : var2;
/*     */   }
/*     */   
/*     */   private void e(MinecraftKey var0) throws IOException {
/*  93 */     if (!f(var0)) {
/*  94 */       throw new IOException("Invalid relative path to resource: " + var0);
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean f(MinecraftKey var0) {
/*  99 */     return !var0.getKey().contains("..");
/*     */   }
/*     */   
/*     */   static class a
/*     */     extends FilterInputStream {
/*     */     private final String a;
/*     */     private boolean b;
/*     */     
/*     */     public a(InputStream var0, MinecraftKey var1, String var2) {
/* 108 */       super(var0);
/* 109 */       ByteArrayOutputStream var3 = new ByteArrayOutputStream();
/* 110 */       (new Exception()).printStackTrace(new PrintStream(var3));
/* 111 */       this.a = "Leaked resource: '" + var1 + "' loaded from pack: '" + var2 + "'\n" + var3;
/*     */     }
/*     */ 
/*     */     
/*     */     public void close() throws IOException {
/* 116 */       super.close();
/* 117 */       this.b = true;
/*     */     }
/*     */ 
/*     */     
/*     */     protected void finalize() throws Throwable {
/* 122 */       if (!this.b) {
/* 123 */         ResourceManagerFallback.c().warn(this.a);
/*     */       }
/*     */       
/* 126 */       super.finalize();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public List<IResource> c(MinecraftKey var0) throws IOException {
/* 132 */     e(var0);
/*     */     
/* 134 */     List<IResource> var1 = Lists.newArrayList();
/* 135 */     MinecraftKey var2 = d(var0);
/*     */     
/* 137 */     for (IResourcePack var4 : this.a) {
/* 138 */       if (var4.b(this.c, var0)) {
/* 139 */         InputStream var5 = var4.b(this.c, var2) ? a(var2, var4) : null;
/* 140 */         var1.add(new Resource(var4.a(), var0, a(var0, var4), var5));
/*     */       } 
/*     */     } 
/*     */     
/* 144 */     if (var1.isEmpty()) {
/* 145 */       throw new FileNotFoundException(var0.toString());
/*     */     }
/*     */     
/* 148 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<MinecraftKey> a(String var0, Predicate<String> var1) {
/* 153 */     List<MinecraftKey> var2 = Lists.newArrayList();
/*     */     
/* 155 */     for (IResourcePack var4 : this.a) {
/* 156 */       var2.addAll(var4.a(this.c, this.d, var0, 2147483647, var1));
/*     */     }
/*     */     
/* 159 */     Collections.sort(var2);
/*     */     
/* 161 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static MinecraftKey d(MinecraftKey var0) {
/* 170 */     return new MinecraftKey(var0.getNamespace(), var0.getKey() + ".mcmeta");
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ResourceManagerFallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */