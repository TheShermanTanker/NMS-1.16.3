/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CursorPosition
/*    */ {
/*    */   private int a;
/*    */   private int b;
/*    */   private int c;
/*    */   private int d;
/*    */   private int e;
/*    */   private int f;
/*    */   private int g;
/*    */   private int h;
/*    */   private int i;
/*    */   private int j;
/*    */   private int k;
/*    */   
/*    */   public CursorPosition(int var0, int var1, int var2, int var3, int var4, int var5) {
/* 24 */     this.a = var0;
/* 25 */     this.b = var1;
/* 26 */     this.c = var2;
/*    */     
/* 28 */     this.d = var3 - var0 + 1;
/* 29 */     this.e = var4 - var1 + 1;
/* 30 */     this.f = var5 - var2 + 1;
/* 31 */     this.g = this.d * this.e * this.f;
/*    */   }
/*    */   
/*    */   public boolean a() {
/* 35 */     if (this.h == this.g) {
/* 36 */       return false;
/*    */     }
/*    */     
/* 39 */     this.i = this.h % this.d;
/* 40 */     int var0 = this.h / this.d;
/* 41 */     this.j = var0 % this.e;
/* 42 */     this.k = var0 / this.e;
/*    */     
/* 44 */     this.h++;
/* 45 */     return true;
/*    */   }
/*    */   
/*    */   public int b() {
/* 49 */     return this.a + this.i;
/*    */   }
/*    */   
/*    */   public int c() {
/* 53 */     return this.b + this.j;
/*    */   }
/*    */   
/*    */   public int d() {
/* 57 */     return this.c + this.k;
/*    */   }
/*    */   
/*    */   public int e() {
/* 61 */     int var0 = 0;
/* 62 */     if (this.i == 0 || this.i == this.d - 1) {
/* 63 */       var0++;
/*    */     }
/* 65 */     if (this.j == 0 || this.j == this.e - 1) {
/* 66 */       var0++;
/*    */     }
/* 68 */     if (this.k == 0 || this.k == this.f - 1) {
/* 69 */       var0++;
/*    */     }
/* 71 */     return var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CursorPosition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */