/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import org.bukkit.event.entity.EntityPotionEffectEvent;
/*    */ 
/*    */ public class EntityCaveSpider extends EntitySpider {
/*    */   public EntityCaveSpider(EntityTypes<? extends EntityCaveSpider> entitytypes, World world) {
/*  8 */     super((EntityTypes)entitytypes, world);
/*    */   }
/*    */   
/*    */   public static AttributeProvider.Builder m() {
/* 12 */     return EntitySpider.eK().a(GenericAttributes.MAX_HEALTH, 12.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean attackEntity(Entity entity) {
/* 17 */     if (super.attackEntity(entity)) {
/* 18 */       if (entity instanceof EntityLiving) {
/* 19 */         byte b0 = 0;
/*    */         
/* 21 */         if (this.world.getDifficulty() == EnumDifficulty.NORMAL) {
/* 22 */           b0 = 7;
/* 23 */         } else if (this.world.getDifficulty() == EnumDifficulty.HARD) {
/* 24 */           b0 = 15;
/*    */         } 
/*    */         
/* 27 */         if (b0 > 0) {
/* 28 */           ((EntityLiving)entity).addEffect(new MobEffect(MobEffects.POISON, b0 * 20, 0), EntityPotionEffectEvent.Cause.ATTACK);
/*    */         }
/*    */       } 
/*    */       
/* 32 */       return true;
/*    */     } 
/* 34 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public GroupDataEntity prepare(WorldAccess worldaccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
/* 41 */     return groupdataentity;
/*    */   }
/*    */ 
/*    */   
/*    */   protected float b(EntityPose entitypose, EntitySize entitysize) {
/* 46 */     return 0.45F;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityCaveSpider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */