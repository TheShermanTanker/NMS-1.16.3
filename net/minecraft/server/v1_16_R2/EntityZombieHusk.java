/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ import org.bukkit.event.entity.EntityPotionEffectEvent;
/*    */ 
/*    */ public class EntityZombieHusk extends EntityZombie {
/*    */   public EntityZombieHusk(EntityTypes<? extends EntityZombieHusk> entitytypes, World world) {
/*  8 */     super((EntityTypes)entitytypes, world);
/*    */   }
/*    */   
/*    */   public static boolean a(EntityTypes<EntityZombieHusk> entitytypes, WorldAccess worldaccess, EnumMobSpawn enummobspawn, BlockPosition blockposition, Random random) {
/* 12 */     return (b((EntityTypes)entitytypes, worldaccess, enummobspawn, blockposition, random) && (enummobspawn == EnumMobSpawn.SPAWNER || worldaccess.e(blockposition)));
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean T_() {
/* 17 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect getSoundAmbient() {
/* 22 */     return SoundEffects.ENTITY_HUSK_AMBIENT;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/* 27 */     return SoundEffects.ENTITY_HUSK_HURT;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect getSoundDeath() {
/* 32 */     return SoundEffects.ENTITY_HUSK_DEATH;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect getSoundStep() {
/* 37 */     return SoundEffects.ENTITY_HUSK_STEP;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean attackEntity(Entity entity) {
/* 42 */     boolean flag = super.attackEntity(entity);
/*    */     
/* 44 */     if (flag && getItemInMainHand().isEmpty() && entity instanceof EntityLiving) {
/* 45 */       float f = this.world.getDamageScaler(getChunkCoordinates()).b();
/*    */       
/* 47 */       ((EntityLiving)entity).addEffect(new MobEffect(MobEffects.HUNGER, 140 * (int)f), EntityPotionEffectEvent.Cause.ATTACK);
/*    */     } 
/*    */     
/* 50 */     return flag;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean eN() {
/* 55 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void eP() {
/* 60 */     b(EntityTypes.ZOMBIE);
/* 61 */     if (!isSilent()) {
/* 62 */       this.world.a((EntityHuman)null, 1041, getChunkCoordinates(), 0);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected ItemStack eM() {
/* 69 */     return ItemStack.b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityZombieHusk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */