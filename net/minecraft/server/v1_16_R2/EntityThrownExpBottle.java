/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import org.bukkit.event.entity.ExpBottleEvent;
/*    */ 
/*    */ public class EntityThrownExpBottle extends EntityProjectileThrowable {
/*    */   public EntityThrownExpBottle(EntityTypes<? extends EntityThrownExpBottle> entitytypes, World world) {
/*  6 */     super((EntityTypes)entitytypes, world);
/*    */   }
/*    */   
/*    */   public EntityThrownExpBottle(World world, EntityLiving entityliving) {
/* 10 */     super((EntityTypes)EntityTypes.EXPERIENCE_BOTTLE, entityliving, world);
/*    */   }
/*    */   
/*    */   public EntityThrownExpBottle(World world, double d0, double d1, double d2) {
/* 14 */     super((EntityTypes)EntityTypes.EXPERIENCE_BOTTLE, d0, d1, d2, world);
/*    */   }
/*    */ 
/*    */   
/*    */   protected Item getDefaultItem() {
/* 19 */     return Items.EXPERIENCE_BOTTLE;
/*    */   }
/*    */ 
/*    */   
/*    */   protected float k() {
/* 24 */     return 0.07F;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(MovingObjectPosition movingobjectposition) {
/* 29 */     super.a(movingobjectposition);
/* 30 */     if (!this.world.isClientSide) {
/*    */ 
/*    */       
/* 33 */       int i = 3 + this.world.random.nextInt(5) + this.world.random.nextInt(5);
/*    */ 
/*    */       
/* 36 */       ExpBottleEvent event = CraftEventFactory.callExpBottleEvent(this, i);
/* 37 */       i = event.getExperience();
/* 38 */       if (event.getShowEffect()) {
/* 39 */         this.world.triggerEffect(2002, getChunkCoordinates(), PotionUtil.a(Potions.WATER));
/*    */       }
/*    */ 
/*    */       
/* 43 */       while (i > 0) {
/* 44 */         int j = EntityExperienceOrb.getOrbValue(i);
/*    */         
/* 46 */         i -= j;
/* 47 */         this.world.addEntity(new EntityExperienceOrb(this.world, locX(), locY(), locZ(), j, ExperienceOrb.SpawnReason.EXP_BOTTLE, getShooter(), this));
/*    */       } 
/*    */       
/* 50 */       die();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityThrownExpBottle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */