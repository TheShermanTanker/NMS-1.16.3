/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityCombustByEntityEvent;
/*    */ 
/*    */ public class EntitySmallFireball extends EntityFireballFireball {
/*    */   public EntitySmallFireball(EntityTypes<? extends EntitySmallFireball> entitytypes, World world) {
/*  8 */     super((EntityTypes)entitytypes, world);
/*    */   }
/*    */   
/*    */   public EntitySmallFireball(World world, EntityLiving entityliving, double d0, double d1, double d2) {
/* 12 */     super((EntityTypes)EntityTypes.SMALL_FIREBALL, entityliving, d0, d1, d2, world);
/*    */     
/* 14 */     if (getShooter() != null && getShooter() instanceof EntityInsentient) {
/* 15 */       this.isIncendiary = this.world.getGameRules().getBoolean(GameRules.MOB_GRIEFING);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public EntitySmallFireball(World world, double d0, double d1, double d2, double d3, double d4, double d5) {
/* 21 */     super((EntityTypes)EntityTypes.SMALL_FIREBALL, d0, d1, d2, d3, d4, d5, world);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(MovingObjectPositionEntity movingobjectpositionentity) {
/* 26 */     super.a(movingobjectpositionentity);
/* 27 */     if (!this.world.isClientSide) {
/* 28 */       Entity entity = movingobjectpositionentity.getEntity();
/*    */       
/* 30 */       if (!entity.isFireProof()) {
/* 31 */         Entity entity1 = getShooter();
/* 32 */         int i = entity.getFireTicks();
/*    */ 
/*    */         
/* 35 */         if (this.isIncendiary) {
/* 36 */           EntityCombustByEntityEvent event = new EntityCombustByEntityEvent((Entity)getBukkitEntity(), (Entity)entity.getBukkitEntity(), 5);
/* 37 */           entity.world.getServer().getPluginManager().callEvent((Event)event);
/*    */           
/* 39 */           if (!event.isCancelled()) {
/* 40 */             entity.setOnFire(event.getDuration(), false);
/*    */           }
/*    */         } 
/*    */         
/* 44 */         boolean flag = entity.damageEntity(DamageSource.fireball(this, entity1), 5.0F);
/*    */         
/* 46 */         if (!flag) {
/* 47 */           entity.setFireTicks(i);
/* 48 */         } else if (entity1 instanceof EntityLiving) {
/* 49 */           a((EntityLiving)entity1, entity);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void a(MovingObjectPositionBlock movingobjectpositionblock) {
/* 58 */     super.a(movingobjectpositionblock);
/* 59 */     if (!this.world.isClientSide) {
/* 60 */       Entity entity = getShooter();
/*    */       
/* 62 */       if (this.isIncendiary) {
/* 63 */         BlockPosition blockposition = movingobjectpositionblock.getBlockPosition().shift(movingobjectpositionblock.getDirection());
/*    */         
/* 65 */         if (this.world.isEmpty(blockposition) && !CraftEventFactory.callBlockIgniteEvent(this.world, blockposition, this).isCancelled()) {
/* 66 */           this.world.setTypeUpdate(blockposition, BlockFireAbstract.a(this.world, blockposition));
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void a(MovingObjectPosition movingobjectposition) {
/* 75 */     super.a(movingobjectposition);
/* 76 */     if (!this.world.isClientSide) {
/* 77 */       die();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isInteractable() {
/* 84 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 89 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntitySmallFireball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */