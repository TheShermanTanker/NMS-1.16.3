/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.time.LocalDate;
/*     */ import java.time.temporal.ChronoField;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ public class EntityBat
/*     */   extends EntityAmbient {
/*  11 */   private static final DataWatcherObject<Byte> b = DataWatcher.a((Class)EntityBat.class, DataWatcherRegistry.a);
/*  12 */   private static final PathfinderTargetCondition c = (new PathfinderTargetCondition()).a(4.0D).b();
/*     */   private BlockPosition d;
/*     */   
/*     */   public EntityBat(EntityTypes<? extends EntityBat> entitytypes, World world) {
/*  16 */     super((EntityTypes)entitytypes, world);
/*  17 */     setAsleep(true);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  22 */     super.initDatawatcher();
/*  23 */     this.datawatcher.register(b, Byte.valueOf((byte)0));
/*     */   }
/*     */ 
/*     */   
/*     */   protected float getSoundVolume() {
/*  28 */     return 0.1F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float dG() {
/*  33 */     return super.dG() * 0.95F;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public SoundEffect getSoundAmbient() {
/*  39 */     return (isAsleep() && this.random.nextInt(4) != 0) ? null : SoundEffects.ENTITY_BAT_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/*  44 */     return SoundEffects.ENTITY_BAT_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/*  49 */     return SoundEffects.ENTITY_BAT_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCollidable() {
/*  54 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void C(Entity entity) {}
/*     */ 
/*     */   
/*     */   protected void collideNearby() {}
/*     */   
/*     */   public static AttributeProvider.Builder m() {
/*  64 */     return EntityInsentient.p().a(GenericAttributes.MAX_HEALTH, 6.0D);
/*     */   }
/*     */   
/*     */   public boolean isAsleep() {
/*  68 */     return ((((Byte)this.datawatcher.<Byte>get(b)).byteValue() & 0x1) != 0);
/*     */   }
/*     */   
/*     */   public void setAsleep(boolean flag) {
/*  72 */     byte b0 = ((Byte)this.datawatcher.<Byte>get(b)).byteValue();
/*     */     
/*  74 */     if (flag) {
/*  75 */       this.datawatcher.set(b, Byte.valueOf((byte)(b0 | 0x1)));
/*     */     } else {
/*  77 */       this.datawatcher.set(b, Byte.valueOf((byte)(b0 & 0xFFFFFFFE)));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  84 */     super.tick();
/*  85 */     if (isAsleep()) {
/*  86 */       setMot(Vec3D.ORIGIN);
/*  87 */       setPositionRaw(locX(), MathHelper.floor(locY()) + 1.0D - getHeight(), locZ());
/*     */     } else {
/*  89 */       setMot(getMot().d(1.0D, 0.6D, 1.0D));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mobTick() {
/*  96 */     super.mobTick();
/*  97 */     BlockPosition blockposition = getChunkCoordinates();
/*  98 */     BlockPosition blockposition1 = blockposition.up();
/*     */     
/* 100 */     if (isAsleep()) {
/* 101 */       boolean flag = isSilent();
/*     */       
/* 103 */       if (this.world.getType(blockposition1).isOccluding(this.world, blockposition)) {
/* 104 */         if (this.random.nextInt(200) == 0) {
/* 105 */           this.aC = this.random.nextInt(360);
/*     */         }
/*     */         
/* 108 */         if (this.world.a(c, this) != null)
/*     */         {
/* 110 */           if (CraftEventFactory.handleBatToggleSleepEvent(this, true)) {
/* 111 */             setAsleep(false);
/* 112 */             if (!flag) {
/* 113 */               this.world.a((EntityHuman)null, 1025, blockposition, 0);
/*     */             
/*     */             }
/*     */           }
/*     */         
/*     */         }
/*     */       }
/* 120 */       else if (CraftEventFactory.handleBatToggleSleepEvent(this, true)) {
/* 121 */         setAsleep(false);
/* 122 */         if (!flag) {
/* 123 */           this.world.a((EntityHuman)null, 1025, blockposition, 0);
/*     */         }
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 129 */       if (this.d != null && (!this.world.isEmpty(this.d) || this.d.getY() < 1)) {
/* 130 */         this.d = null;
/*     */       }
/*     */       
/* 133 */       if (this.d == null || this.random.nextInt(30) == 0 || this.d.a(getPositionVector(), 2.0D)) {
/* 134 */         this.d = new BlockPosition(locX() + this.random.nextInt(7) - this.random.nextInt(7), locY() + this.random.nextInt(6) - 2.0D, locZ() + this.random.nextInt(7) - this.random.nextInt(7));
/*     */       }
/*     */       
/* 137 */       double d0 = this.d.getX() + 0.5D - locX();
/* 138 */       double d1 = this.d.getY() + 0.1D - locY();
/* 139 */       double d2 = this.d.getZ() + 0.5D - locZ();
/* 140 */       Vec3D vec3d = getMot();
/* 141 */       Vec3D vec3d1 = vec3d.add((Math.signum(d0) * 0.5D - vec3d.x) * 0.10000000149011612D, (Math.signum(d1) * 0.699999988079071D - vec3d.y) * 0.10000000149011612D, (Math.signum(d2) * 0.5D - vec3d.z) * 0.10000000149011612D);
/*     */       
/* 143 */       setMot(vec3d1);
/* 144 */       float f = (float)(MathHelper.d(vec3d1.z, vec3d1.x) * 57.2957763671875D) - 90.0F;
/* 145 */       float f1 = MathHelper.g(f - this.yaw);
/*     */       
/* 147 */       this.aT = 0.5F;
/* 148 */       this.yaw += f1;
/* 149 */       if (this.random.nextInt(100) == 0 && this.world.getType(blockposition1).isOccluding(this.world, blockposition1))
/*     */       {
/* 151 */         if (CraftEventFactory.handleBatToggleSleepEvent(this, false)) {
/* 152 */           setAsleep(true);
/*     */         }
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean playStepSound() {
/* 162 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(float f, float f1) {
/* 167 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(double d0, boolean flag, IBlockData iblockdata, BlockPosition blockposition) {}
/*     */ 
/*     */   
/*     */   public boolean isIgnoreBlockTrigger() {
/* 175 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 180 */     if (isInvulnerable(damagesource)) {
/* 181 */       return false;
/*     */     }
/* 183 */     if (!this.world.isClientSide && isAsleep())
/*     */     {
/* 185 */       if (CraftEventFactory.handleBatToggleSleepEvent(this, true)) {
/* 186 */         setAsleep(false);
/*     */       }
/*     */     }
/*     */ 
/*     */     
/* 191 */     return super.damageEntity(damagesource, f);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/* 197 */     super.loadData(nbttagcompound);
/* 198 */     this.datawatcher.set(b, Byte.valueOf(nbttagcompound.getByte("BatFlags")));
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/* 203 */     super.saveData(nbttagcompound);
/* 204 */     nbttagcompound.setByte("BatFlags", ((Byte)this.datawatcher.<Byte>get(b)).byteValue());
/*     */   }
/*     */   
/*     */   public static boolean b(EntityTypes<EntityBat> entitytypes, GeneratorAccess generatoraccess, EnumMobSpawn enummobspawn, BlockPosition blockposition, Random random) {
/* 208 */     if (blockposition.getY() >= generatoraccess.getSeaLevel()) {
/* 209 */       return false;
/*     */     }
/* 211 */     int i = generatoraccess.getLightLevel(blockposition);
/* 212 */     byte b0 = 4;
/*     */     
/* 214 */     if (eJ()) {
/* 215 */       b0 = 7;
/* 216 */     } else if (random.nextBoolean()) {
/* 217 */       return false;
/*     */     } 
/*     */     
/* 220 */     return (i > random.nextInt(b0)) ? false : a((EntityTypes)entitytypes, generatoraccess, enummobspawn, blockposition, random);
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean eJ() {
/* 225 */     LocalDate localdate = LocalDate.now();
/* 226 */     int i = localdate.get(ChronoField.DAY_OF_MONTH);
/* 227 */     int j = localdate.get(ChronoField.MONTH_OF_YEAR);
/*     */     
/* 229 */     return ((j == 10 && i >= 20) || (j == 11 && i <= 3));
/*     */   }
/*     */ 
/*     */   
/*     */   protected float b(EntityPose entitypose, EntitySize entitysize) {
/* 234 */     return entitysize.height / 2.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityBat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */