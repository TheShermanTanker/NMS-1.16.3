/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class EntityHorseSkeleton extends EntityHorseAbstract {
/*     */   private boolean bx;
/*     */   private int by;
/*   7 */   private final PathfinderGoalHorseTrap bw = new PathfinderGoalHorseTrap(this);
/*     */   public int getTrapTime() {
/*   9 */     return this.by;
/*     */   }
/*     */   public EntityHorseSkeleton(EntityTypes<? extends EntityHorseSkeleton> entitytypes, World world) {
/*  12 */     super((EntityTypes)entitytypes, world);
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder eL() {
/*  16 */     return fi().a(GenericAttributes.MAX_HEALTH, 15.0D).a(GenericAttributes.MOVEMENT_SPEED, 0.20000000298023224D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void eK() {
/*  21 */     getAttributeInstance(GenericAttributes.JUMP_STRENGTH).setValue(fq());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void eV() {}
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/*  29 */     super.getSoundAmbient();
/*  30 */     return a(TagsFluid.WATER) ? SoundEffects.ENTITY_SKELETON_HORSE_AMBIENT_WATER : SoundEffects.ENTITY_SKELETON_HORSE_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/*  35 */     super.getSoundDeath();
/*  36 */     return SoundEffects.ENTITY_SKELETON_HORSE_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/*  41 */     super.getSoundHurt(damagesource);
/*  42 */     return SoundEffects.ENTITY_SKELETON_HORSE_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundSwim() {
/*  47 */     if (this.onGround) {
/*  48 */       if (!isVehicle()) {
/*  49 */         return SoundEffects.ENTITY_SKELETON_HORSE_STEP_WATER;
/*     */       }
/*     */       
/*  52 */       this.bv++;
/*  53 */       if (this.bv > 5 && this.bv % 3 == 0) {
/*  54 */         return SoundEffects.ENTITY_SKELETON_HORSE_GALLOP_WATER;
/*     */       }
/*     */       
/*  57 */       if (this.bv <= 5) {
/*  58 */         return SoundEffects.ENTITY_SKELETON_HORSE_STEP_WATER;
/*     */       }
/*     */     } 
/*     */     
/*  62 */     return SoundEffects.ENTITY_SKELETON_HORSE_SWIM;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void d(float f) {
/*  67 */     if (this.onGround) {
/*  68 */       super.d(0.3F);
/*     */     } else {
/*  70 */       super.d(Math.min(0.1F, f * 25.0F));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fn() {
/*  77 */     if (isInWater()) {
/*  78 */       playSound(SoundEffects.ENTITY_SKELETON_HORSE_JUMP_WATER, 0.4F, 1.0F);
/*     */     } else {
/*  80 */       super.fn();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumMonsterType getMonsterType() {
/*  87 */     return EnumMonsterType.UNDEAD;
/*     */   }
/*     */ 
/*     */   
/*     */   public double bb() {
/*  92 */     return super.bb() - 0.1875D;
/*     */   }
/*     */ 
/*     */   
/*     */   public void movementTick() {
/*  97 */     super.movementTick();
/*  98 */     if (eM() && this.by++ >= 18000) {
/*  99 */       die();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/* 106 */     super.saveData(nbttagcompound);
/* 107 */     nbttagcompound.setBoolean("SkeletonTrap", eM());
/* 108 */     nbttagcompound.setInt("SkeletonTrapTime", this.by);
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/* 113 */     super.loadData(nbttagcompound);
/* 114 */     t(nbttagcompound.getBoolean("SkeletonTrap"));
/* 115 */     this.by = nbttagcompound.getInt("SkeletonTrapTime");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean bs() {
/* 120 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float dL() {
/* 125 */     return 0.96F;
/*     */   }
/*     */   public boolean isTrap() {
/* 128 */     return eM();
/*     */   } public boolean eM() {
/* 130 */     return this.bx;
/*     */   }
/*     */   public void setTrap(boolean trap) {
/* 133 */     t(trap);
/*     */   } public void t(boolean flag) {
/* 135 */     if (flag != this.bx) {
/* 136 */       this.bx = flag;
/* 137 */       if (flag) {
/* 138 */         this.goalSelector.a(1, this.bw);
/*     */       } else {
/* 140 */         this.goalSelector.a(this.bw);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public EntityAgeable createChild(WorldServer worldserver, EntityAgeable entityageable) {
/* 149 */     return EntityTypes.SKELETON_HORSE.a(worldserver);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult b(EntityHuman entityhuman, EnumHand enumhand) {
/* 154 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/* 156 */     if (!isTamed())
/* 157 */       return EnumInteractionResult.PASS; 
/* 158 */     if (isBaby())
/* 159 */       return super.b(entityhuman, enumhand); 
/* 160 */     if (entityhuman.ep()) {
/* 161 */       f(entityhuman);
/* 162 */       return EnumInteractionResult.a(this.world.isClientSide);
/* 163 */     }  if (isVehicle()) {
/* 164 */       return super.b(entityhuman, enumhand);
/*     */     }
/* 166 */     if (!itemstack.isEmpty()) {
/* 167 */       if (itemstack.getItem() == Items.SADDLE && !hasSaddle()) {
/* 168 */         f(entityhuman);
/* 169 */         return EnumInteractionResult.a(this.world.isClientSide);
/*     */       } 
/*     */       
/* 172 */       EnumInteractionResult enuminteractionresult = itemstack.a(entityhuman, this, enumhand);
/*     */       
/* 174 */       if (enuminteractionresult.a()) {
/* 175 */         return enuminteractionresult;
/*     */       }
/*     */     } 
/*     */     
/* 179 */     h(entityhuman);
/* 180 */     return EnumInteractionResult.a(this.world.isClientSide);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityHorseSkeleton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */