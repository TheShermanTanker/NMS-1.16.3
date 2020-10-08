/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import javax.annotation.Nullable;
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
/*     */ public class Resource
/*     */   implements IResource
/*     */ {
/*     */   private final String a;
/*     */   private final MinecraftKey b;
/*     */   private final InputStream c;
/*     */   private final InputStream d;
/*     */   
/*     */   public Resource(String var0, MinecraftKey var1, InputStream var2, @Nullable InputStream var3) {
/*  25 */     this.a = var0;
/*  26 */     this.b = var1;
/*  27 */     this.c = var2;
/*  28 */     this.d = var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream b() {
/*  38 */     return this.c;
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
/*     */   public String d() {
/*  77 */     return this.a;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object var0) {
/*  82 */     if (this == var0) {
/*  83 */       return true;
/*     */     }
/*  85 */     if (!(var0 instanceof Resource)) {
/*  86 */       return false;
/*     */     }
/*     */     
/*  89 */     Resource var1 = (Resource)var0;
/*     */     
/*  91 */     if ((this.b != null) ? !this.b.equals(var1.b) : (var1.b != null)) {
/*  92 */       return false;
/*     */     }
/*  94 */     if ((this.a != null) ? !this.a.equals(var1.a) : (var1.a != null)) {
/*  95 */       return false;
/*     */     }
/*     */     
/*  98 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 103 */     int var0 = (this.a != null) ? this.a.hashCode() : 0;
/* 104 */     var0 = 31 * var0 + ((this.b != null) ? this.b.hashCode() : 0);
/* 105 */     return var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 110 */     this.c.close();
/* 111 */     if (this.d != null)
/* 112 */       this.d.close(); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Resource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */