/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class MovingObjectPositionEntity
/*    */   extends MovingObjectPosition
/*    */ {
/*    */   private final Entity b;
/*    */   
/*    */   public MovingObjectPositionEntity(Entity var0) {
/*  9 */     this(var0, var0.getPositionVector());
/*    */   }
/*    */   
/*    */   public MovingObjectPositionEntity(Entity var0, Vec3D var1) {
/* 13 */     super(var1);
/*    */     
/* 15 */     this.b = var0;
/*    */   }
/*    */   
/*    */   public Entity getEntity() {
/* 19 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public MovingObjectPosition.EnumMovingObjectType getType() {
/* 24 */     return MovingObjectPosition.EnumMovingObjectType.ENTITY;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MovingObjectPositionEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */