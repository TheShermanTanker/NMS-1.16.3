/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.bukkit.event.entity.EntityPotionEffectEvent;
/*    */ 
/*    */ public class EntityGuardianElder extends EntityGuardian {
/*  8 */   public static final float b = EntityTypes.ELDER_GUARDIAN.j() / EntityTypes.GUARDIAN.j();
/*    */   
/*    */   public EntityGuardianElder(EntityTypes<? extends EntityGuardianElder> entitytypes, World world) {
/* 11 */     super((EntityTypes)entitytypes, world);
/* 12 */     setPersistent();
/* 13 */     if (this.goalRandomStroll != null) {
/* 14 */       this.goalRandomStroll.setTimeBetweenMovement(400);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public static AttributeProvider.Builder m() {
/* 20 */     return EntityGuardian.eM().a(GenericAttributes.MOVEMENT_SPEED, 0.30000001192092896D).a(GenericAttributes.ATTACK_DAMAGE, 8.0D).a(GenericAttributes.MAX_HEALTH, 80.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public int eK() {
/* 25 */     return 60;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect getSoundAmbient() {
/* 30 */     return aG() ? SoundEffects.ENTITY_ELDER_GUARDIAN_AMBIENT : SoundEffects.ENTITY_ELDER_GUARDIAN_AMBIENT_LAND;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/* 35 */     return aG() ? SoundEffects.ENTITY_ELDER_GUARDIAN_HURT : SoundEffects.ENTITY_ELDER_GUARDIAN_HURT_LAND;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect getSoundDeath() {
/* 40 */     return aG() ? SoundEffects.ENTITY_ELDER_GUARDIAN_DEATH : SoundEffects.ENTITY_ELDER_GUARDIAN_DEATH_LAND;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect getSoundFlop() {
/* 45 */     return SoundEffects.ENTITY_ELDER_GUARDIAN_FLOP;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void mobTick() {
/* 50 */     super.mobTick();
/* 51 */     boolean flag = true;
/*    */     
/* 53 */     if ((this.ticksLived + getId()) % 1200 == 0) {
/* 54 */       MobEffectList mobeffectlist = MobEffects.SLOWER_DIG;
/* 55 */       List<EntityPlayer> list = ((WorldServer)this.world).a(entityplayer -> 
/* 56 */           (h(entityplayer) < 2500.0D && entityplayer.playerInteractManager.d()));
/*    */       
/* 58 */       boolean flag1 = true;
/* 59 */       boolean flag2 = true;
/* 60 */       boolean flag3 = true;
/* 61 */       Iterator<EntityPlayer> iterator = list.iterator();
/*    */       
/* 63 */       while (iterator.hasNext()) {
/* 64 */         EntityPlayer entityplayer = iterator.next();
/*    */         
/* 66 */         if (!entityplayer.hasEffect(mobeffectlist) || entityplayer.getEffect(mobeffectlist).getAmplifier() < 2 || entityplayer.getEffect(mobeffectlist).getDuration() < 1200) {
/* 67 */           entityplayer.playerConnection.sendPacket(new PacketPlayOutGameStateChange(PacketPlayOutGameStateChange.k, isSilent() ? 0.0F : 1.0F));
/* 68 */           entityplayer.addEffect(new MobEffect(mobeffectlist, 6000, 2), EntityPotionEffectEvent.Cause.ATTACK);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 73 */     if (!ez())
/* 74 */       a(getChunkCoordinates(), 16); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityGuardianElder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */