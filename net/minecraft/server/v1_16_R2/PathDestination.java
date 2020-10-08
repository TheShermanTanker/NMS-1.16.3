/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class PathDestination
/*    */   extends PathPoint
/*    */ {
/*  6 */   private float m = Float.MAX_VALUE;
/*    */   private PathPoint n;
/*    */   private boolean o;
/*    */   
/*    */   public PathDestination(PathPoint var0) {
/* 11 */     super(var0.a, var0.b, var0.c);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(float var0, PathPoint var1) {
/* 19 */     if (var0 < this.m) {
/* 20 */       this.m = var0;
/* 21 */       this.n = var1;
/*    */     } 
/*    */   }
/*    */   
/*    */   public PathPoint d() {
/* 26 */     return this.n;
/*    */   }
/*    */   
/*    */   public void e() {
/* 30 */     this.o = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathDestination.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */