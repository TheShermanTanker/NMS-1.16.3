/*    */ package net.minecraft.server.v1_16_R2;
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
/*    */ public class EntitySnowball
/*    */   extends EntityProjectileThrowable
/*    */ {
/*    */   public EntitySnowball(EntityTypes<? extends EntitySnowball> var0, World var1) {
/* 21 */     super((EntityTypes)var0, var1);
/*    */   }
/*    */   
/*    */   public EntitySnowball(World var0, EntityLiving var1) {
/* 25 */     super((EntityTypes)EntityTypes.SNOWBALL, var1, var0);
/*    */   }
/*    */   
/*    */   public EntitySnowball(World var0, double var1, double var3, double var5) {
/* 29 */     super((EntityTypes)EntityTypes.SNOWBALL, var1, var3, var5, var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected Item getDefaultItem() {
/* 34 */     return Items.SNOWBALL;
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
/*    */   protected void a(MovingObjectPositionEntity var0) {
/* 54 */     super.a(var0);
/* 55 */     Entity var1 = var0.getEntity();
/* 56 */     int var2 = (var1 instanceof EntityBlaze) ? 3 : 0;
/*    */     
/* 58 */     var1.damageEntity(DamageSource.projectile(this, getShooter()), var2);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(MovingObjectPosition var0) {
/* 63 */     super.a(var0);
/*    */     
/* 65 */     if (!this.world.isClientSide) {
/* 66 */       this.world.broadcastEntityEffect(this, (byte)3);
/* 67 */       die();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntitySnowball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */