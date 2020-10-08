/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import org.bukkit.event.entity.EntityPotionEffectEvent;
/*    */ 
/*    */ public class EntitySpectralArrow extends EntityArrow {
/*  5 */   public int duration = 200;
/*    */   
/*    */   public EntitySpectralArrow(EntityTypes<? extends EntitySpectralArrow> entitytypes, World world) {
/*  8 */     super((EntityTypes)entitytypes, world);
/*    */   }
/*    */   
/*    */   public EntitySpectralArrow(World world, EntityLiving entityliving) {
/* 12 */     super((EntityTypes)EntityTypes.SPECTRAL_ARROW, entityliving, world);
/*    */   }
/*    */   
/*    */   public EntitySpectralArrow(World world, double d0, double d1, double d2) {
/* 16 */     super((EntityTypes)EntityTypes.SPECTRAL_ARROW, d0, d1, d2, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick() {
/* 21 */     super.tick();
/* 22 */     if (this.world.isClientSide && !this.inGround) {
/* 23 */       this.world.addParticle(Particles.INSTANT_EFFECT, locX(), locY(), locZ(), 0.0D, 0.0D, 0.0D);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected ItemStack getItemStack() {
/* 30 */     return new ItemStack(Items.SPECTRAL_ARROW);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(EntityLiving entityliving) {
/* 35 */     super.a(entityliving);
/* 36 */     MobEffect mobeffect = new MobEffect(MobEffects.GLOWING, this.duration, 0);
/*    */     
/* 38 */     entityliving.addEffect(mobeffect, EntityPotionEffectEvent.Cause.ARROW);
/*    */   }
/*    */ 
/*    */   
/*    */   public void loadData(NBTTagCompound nbttagcompound) {
/* 43 */     super.loadData(nbttagcompound);
/* 44 */     if (nbttagcompound.hasKey("Duration")) {
/* 45 */       this.duration = nbttagcompound.getInt("Duration");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void saveData(NBTTagCompound nbttagcompound) {
/* 52 */     super.saveData(nbttagcompound);
/* 53 */     nbttagcompound.setInt("Duration", this.duration);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntitySpectralArrow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */