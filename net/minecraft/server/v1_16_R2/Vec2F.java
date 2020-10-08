/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Vec2F
/*    */ {
/*  7 */   public static final Vec2F a = new Vec2F(0.0F, 0.0F);
/*  8 */   public static final Vec2F b = new Vec2F(1.0F, 1.0F);
/*  9 */   public static final Vec2F c = new Vec2F(1.0F, 0.0F);
/* 10 */   public static final Vec2F d = new Vec2F(-1.0F, 0.0F);
/* 11 */   public static final Vec2F e = new Vec2F(0.0F, 1.0F);
/* 12 */   public static final Vec2F f = new Vec2F(0.0F, -1.0F);
/* 13 */   public static final Vec2F g = new Vec2F(Float.MAX_VALUE, Float.MAX_VALUE);
/* 14 */   public static final Vec2F h = new Vec2F(Float.MIN_VALUE, Float.MIN_VALUE);
/*    */   
/*    */   public final float i;
/*    */   public final float j;
/*    */   
/*    */   public Vec2F(float var0, float var1) {
/* 20 */     this.i = var0;
/* 21 */     this.j = var1;
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
/*    */   public boolean c(Vec2F var0) {
/* 41 */     return (this.i == var0.i && this.j == var0.j);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Vec2F.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */