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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class EntityPiglinAbstract
/*     */   extends EntityMonster
/*     */ {
/*  26 */   protected static final DataWatcherObject<Boolean> b = DataWatcher.a((Class)EntityPiglinAbstract.class, DataWatcherRegistry.i);
/*     */   
/*  28 */   public int conversionTicks = 0;
/*     */   
/*     */   public EntityPiglinAbstract(EntityTypes<? extends EntityPiglinAbstract> var0, World var1) {
/*  31 */     super((EntityTypes)var0, var1);
/*  32 */     setCanPickupLoot(true);
/*  33 */     eS();
/*  34 */     a(PathType.DANGER_FIRE, 16.0F);
/*  35 */     a(PathType.DAMAGE_FIRE, -1.0F);
/*     */   }
/*     */   
/*     */   private void eS() {
/*  39 */     if (PathfinderGoalUtil.a(this)) {
/*  40 */       ((Navigation)getNavigation()).a(true);
/*     */     }
/*     */   }
/*     */   
/*     */   protected abstract boolean m();
/*     */   
/*     */   public void setImmuneToZombification(boolean var0) {
/*  47 */     getDataWatcher().set(b, Boolean.valueOf(var0));
/*     */   }
/*     */   
/*     */   public boolean isImmuneToZombification() {
/*  51 */     return ((Boolean)getDataWatcher().<Boolean>get(b)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  56 */     super.initDatawatcher();
/*  57 */     this.datawatcher.register(b, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound var0) {
/*  62 */     super.saveData(var0);
/*     */     
/*  64 */     if (isImmuneToZombification()) {
/*  65 */       var0.setBoolean("IsImmuneToZombification", true);
/*     */     }
/*  67 */     var0.setInt("TimeInOverworld", this.conversionTicks);
/*     */   }
/*     */ 
/*     */   
/*     */   public double ba() {
/*  72 */     return isBaby() ? -0.05D : -0.45D;
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound var0) {
/*  77 */     super.loadData(var0);
/*     */     
/*  79 */     setImmuneToZombification(var0.getBoolean("IsImmuneToZombification"));
/*  80 */     this.conversionTicks = var0.getInt("TimeInOverworld");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void mobTick() {
/*  85 */     super.mobTick();
/*     */     
/*  87 */     if (isConverting()) {
/*  88 */       this.conversionTicks++;
/*     */     } else {
/*  90 */       this.conversionTicks = 0;
/*     */     } 
/*  92 */     if (this.conversionTicks > 300) {
/*  93 */       eP();
/*  94 */       c((WorldServer)this.world);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isConverting() {
/*  99 */     return (!this.world.getDimensionManager().isPiglinSafe() && !isImmuneToZombification() && !isNoAI());
/*     */   }
/*     */   
/*     */   protected void c(WorldServer var0) {
/* 103 */     EntityPigZombie var1 = a(EntityTypes.ZOMBIFIED_PIGLIN, true);
/* 104 */     if (var1 != null) {
/* 105 */       var1.addEffect(new MobEffect(MobEffects.CONFUSION, 200, 0));
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean eM() {
/* 110 */     return !isBaby();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public EntityLiving getGoalTarget() {
/* 119 */     return this.bg.<EntityLiving>getMemory(MemoryModuleType.ATTACK_TARGET).orElse(null);
/*     */   }
/*     */   
/*     */   protected boolean eO() {
/* 123 */     return getItemInMainHand().getItem() instanceof ItemToolMaterial;
/*     */   }
/*     */ 
/*     */   
/*     */   public void F() {
/* 128 */     if (PiglinAI.d(this)) {
/* 129 */       super.F();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void M() {
/* 135 */     super.M();
/* 136 */     PacketDebug.a(this);
/*     */   }
/*     */   
/*     */   protected abstract void eP();
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityPiglinAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */