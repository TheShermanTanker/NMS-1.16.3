/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public abstract class MovingObjectPosition {
/*    */   protected final Vec3D pos;
/*    */   
/*    */   public enum EnumMovingObjectType {
/*  7 */     MISS, BLOCK, ENTITY;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected MovingObjectPosition(Vec3D var0) {
/* 13 */     this.pos = var0;
/*    */   }
/*    */   
/*    */   public double a(Entity var0) {
/* 17 */     double var1 = this.pos.x - var0.locX();
/* 18 */     double var3 = this.pos.y - var0.locY();
/* 19 */     double var5 = this.pos.z - var0.locZ();
/* 20 */     return var1 * var1 + var3 * var3 + var5 * var5;
/*    */   }
/*    */   
/*    */   public abstract EnumMovingObjectType getType();
/*    */   
/*    */   public Vec3D getPos() {
/* 26 */     return this.pos;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MovingObjectPosition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */