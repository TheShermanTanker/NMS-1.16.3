/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Random;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityMagmaCube
/*     */   extends EntitySlime
/*     */ {
/*     */   public EntityMagmaCube(EntityTypes<? extends EntityMagmaCube> var0, World var1) {
/*  28 */     super((EntityTypes)var0, var1);
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder m() {
/*  32 */     return EntityMonster.eR()
/*  33 */       .a(GenericAttributes.MOVEMENT_SPEED, 0.20000000298023224D);
/*     */   }
/*     */   
/*     */   public static boolean b(EntityTypes<EntityMagmaCube> var0, GeneratorAccess var1, EnumMobSpawn var2, BlockPosition var3, Random var4) {
/*  37 */     return (var1.getDifficulty() != EnumDifficulty.PEACEFUL);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IWorldReader var0) {
/*  42 */     return (var0.j(this) && !var0.containsLiquid(getBoundingBox()));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(int var0, boolean var1) {
/*  47 */     super.setSize(var0, var1);
/*  48 */     getAttributeInstance(GenericAttributes.ARMOR).setValue((var0 * 3));
/*     */   }
/*     */ 
/*     */   
/*     */   public float aQ() {
/*  53 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected ParticleParam eI() {
/*  58 */     return Particles.FLAME;
/*     */   }
/*     */ 
/*     */   
/*     */   protected MinecraftKey getDefaultLootTable() {
/*  63 */     return eQ() ? LootTables.a : getEntityType().i();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBurning() {
/*  68 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int eJ() {
/*  73 */     return super.eJ() * 4;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void eK() {
/*  78 */     this.b *= 0.9F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void jump() {
/*  83 */     Vec3D var0 = getMot();
/*  84 */     setMot(var0.x, (dI() + getSize() * 0.1F), var0.z);
/*  85 */     this.impulse = true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void c(Tag<FluidType> var0) {
/*  90 */     if (var0 == TagsFluid.LAVA) {
/*  91 */       Vec3D var1 = getMot();
/*  92 */       setMot(var1.x, (0.22F + getSize() * 0.05F), var1.z);
/*  93 */       this.impulse = true;
/*     */     } else {
/*  95 */       super.c(var0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(float var0, float var1) {
/* 101 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean eL() {
/* 106 */     return doAITick();
/*     */   }
/*     */ 
/*     */   
/*     */   protected float eM() {
/* 111 */     return super.eM() + 2.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource var0) {
/* 116 */     if (eQ()) {
/* 117 */       return SoundEffects.ENTITY_MAGMA_CUBE_HURT_SMALL;
/*     */     }
/* 119 */     return SoundEffects.ENTITY_MAGMA_CUBE_HURT;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/* 125 */     if (eQ()) {
/* 126 */       return SoundEffects.ENTITY_MAGMA_CUBE_DEATH_SMALL;
/*     */     }
/* 128 */     return SoundEffects.ENTITY_MAGMA_CUBE_DEATH;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundSquish() {
/* 134 */     if (eQ()) {
/* 135 */       return SoundEffects.ENTITY_MAGMA_CUBE_SQUISH_SMALL;
/*     */     }
/* 137 */     return SoundEffects.ENTITY_MAGMA_CUBE_SQUISH;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundJump() {
/* 143 */     return SoundEffects.ENTITY_MAGMA_CUBE_JUMP;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityMagmaCube.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */