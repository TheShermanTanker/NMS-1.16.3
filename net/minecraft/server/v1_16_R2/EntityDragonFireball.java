/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.bukkit.entity.AreaEffectCloud;
/*    */ 
/*    */ public class EntityDragonFireball extends EntityFireball {
/*    */   public EntityDragonFireball(EntityTypes<? extends EntityDragonFireball> entitytypes, World world) {
/*  9 */     super((EntityTypes)entitytypes, world);
/*    */   }
/*    */   
/*    */   public EntityDragonFireball(World world, EntityLiving entityliving, double d0, double d1, double d2) {
/* 13 */     super((EntityTypes)EntityTypes.DRAGON_FIREBALL, entityliving, d0, d1, d2, world);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(MovingObjectPosition movingobjectposition) {
/* 18 */     super.a(movingobjectposition);
/* 19 */     Entity entity = getShooter();
/*    */     
/* 21 */     if ((movingobjectposition.getType() != MovingObjectPosition.EnumMovingObjectType.ENTITY || !((MovingObjectPositionEntity)movingobjectposition).getEntity().s(entity)) && 
/* 22 */       !this.world.isClientSide) {
/* 23 */       List<EntityLiving> list = this.world.a(EntityLiving.class, getBoundingBox().grow(4.0D, 2.0D, 4.0D));
/* 24 */       EntityAreaEffectCloud entityareaeffectcloud = new EntityAreaEffectCloud(this.world, locX(), locY(), locZ());
/*    */       
/* 26 */       if (entity instanceof EntityLiving) {
/* 27 */         entityareaeffectcloud.setSource((EntityLiving)entity);
/*    */       }
/*    */       
/* 30 */       entityareaeffectcloud.setParticle(Particles.DRAGON_BREATH);
/* 31 */       entityareaeffectcloud.setRadius(3.0F);
/* 32 */       entityareaeffectcloud.setDuration(600);
/* 33 */       entityareaeffectcloud.setRadiusPerTick((7.0F - entityareaeffectcloud.getRadius()) / entityareaeffectcloud.getDuration());
/* 34 */       entityareaeffectcloud.addEffect(new MobEffect(MobEffects.HARM, 1, 1));
/* 35 */       if (!list.isEmpty()) {
/* 36 */         Iterator<EntityLiving> iterator = list.iterator();
/*    */         
/* 38 */         while (iterator.hasNext()) {
/* 39 */           EntityLiving entityliving = iterator.next();
/* 40 */           double d0 = h(entityliving);
/*    */           
/* 42 */           if (d0 < 16.0D) {
/* 43 */             entityareaeffectcloud.setPosition(entityliving.locX(), entityliving.locY(), entityliving.locZ());
/*    */             
/*    */             break;
/*    */           } 
/*    */         } 
/*    */       } 
/* 49 */       if ((new EnderDragonFireballHitEvent((DragonFireball)getBukkitEntity(), (Collection)list.stream().map(EntityLiving::getBukkitLivingEntity).collect(Collectors.toList()), (AreaEffectCloud)entityareaeffectcloud.getBukkitEntity())).callEvent())
/* 50 */       { this.world.triggerEffect(2006, getChunkCoordinates(), isSilent() ? -1 : 1);
/* 51 */         this.world.addEntity(entityareaeffectcloud); }
/* 52 */       else { entityareaeffectcloud.die(); }
/* 53 */        die();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isInteractable() {
/* 61 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 66 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   protected ParticleParam h() {
/* 71 */     return Particles.DRAGON_BREATH;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean W_() {
/* 76 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityDragonFireball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */