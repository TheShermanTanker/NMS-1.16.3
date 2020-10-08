/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ public class CircularTimer
/*    */ {
/*  6 */   private final long[] a = new long[240];
/*    */   
/*    */   private int b;
/*    */   
/*    */   private int c;
/*    */   
/*    */   private int d;
/*    */   
/*    */   public void a(long var0) {
/* 15 */     this.a[this.d] = var0;
/*    */     
/* 17 */     this.d++;
/* 18 */     if (this.d == 240) {
/* 19 */       this.d = 0;
/*    */     }
/*    */     
/* 22 */     if (this.c < 240) {
/* 23 */       this.b = 0;
/* 24 */       this.c++;
/*    */     } else {
/* 26 */       this.b = b(this.d + 1);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int b(int var0) {
/* 60 */     return var0 % 240;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CircularTimer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */