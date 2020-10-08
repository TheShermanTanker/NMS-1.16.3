/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityHorseZombie
/*     */   extends EntityHorseAbstract
/*     */ {
/*     */   public EntityHorseZombie(EntityTypes<? extends EntityHorseZombie> var0, World var1) {
/*  23 */     super((EntityTypes)var0, var1);
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder eL() {
/*  27 */     return fi()
/*  28 */       .a(GenericAttributes.MAX_HEALTH, 15.0D)
/*  29 */       .a(GenericAttributes.MOVEMENT_SPEED, 0.20000000298023224D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void eK() {
/*  34 */     getAttributeInstance(GenericAttributes.JUMP_STRENGTH).setValue(fq());
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumMonsterType getMonsterType() {
/*  39 */     return EnumMonsterType.UNDEAD;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/*  44 */     super.getSoundAmbient();
/*  45 */     return SoundEffects.ENTITY_ZOMBIE_HORSE_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/*  50 */     super.getSoundDeath();
/*  51 */     return SoundEffects.ENTITY_ZOMBIE_HORSE_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource var0) {
/*  56 */     super.getSoundHurt(var0);
/*  57 */     return SoundEffects.ENTITY_ZOMBIE_HORSE_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public EntityAgeable createChild(WorldServer var0, EntityAgeable var1) {
/*  63 */     return EntityTypes.ZOMBIE_HORSE.a(var0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumInteractionResult b(EntityHuman var0, EnumHand var1) {
/*  69 */     ItemStack var2 = var0.b(var1);
/*     */     
/*  71 */     if (!isTamed()) {
/*  72 */       return EnumInteractionResult.PASS;
/*     */     }
/*     */     
/*  75 */     if (isBaby()) {
/*  76 */       return super.b(var0, var1);
/*     */     }
/*     */     
/*  79 */     if (var0.ep()) {
/*  80 */       f(var0);
/*  81 */       return EnumInteractionResult.a(this.world.isClientSide);
/*     */     } 
/*     */     
/*  84 */     if (isVehicle()) {
/*  85 */       return super.b(var0, var1);
/*     */     }
/*     */     
/*  88 */     if (!var2.isEmpty()) {
/*  89 */       if (var2.getItem() == Items.SADDLE && !hasSaddle()) {
/*  90 */         f(var0);
/*  91 */         return EnumInteractionResult.a(this.world.isClientSide);
/*     */       } 
/*     */ 
/*     */       
/*  95 */       EnumInteractionResult var3 = var2.a(var0, this, var1);
/*  96 */       if (var3.a()) {
/*  97 */         return var3;
/*     */       }
/*     */     } 
/*     */     
/* 101 */     h(var0);
/* 102 */     return EnumInteractionResult.a(this.world.isClientSide);
/*     */   }
/*     */   
/*     */   protected void eV() {}
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityHorseZombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */