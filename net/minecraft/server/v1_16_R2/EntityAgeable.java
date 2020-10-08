/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public abstract class EntityAgeable
/*     */   extends EntityCreature {
/*   7 */   private static final DataWatcherObject<Boolean> bo = DataWatcher.a((Class)EntityAgeable.class, DataWatcherRegistry.i);
/*     */   protected int b;
/*     */   protected int c;
/*     */   protected int d;
/*     */   public boolean ageLocked;
/*     */   
/*     */   protected EntityAgeable(EntityTypes<? extends EntityAgeable> entitytypes, World world) {
/*  14 */     super((EntityTypes)entitytypes, world);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void inactiveTick() {
/*  21 */     super.inactiveTick();
/*  22 */     if (this.world.isClientSide || this.ageLocked) {
/*     */       
/*  24 */       updateSize();
/*     */     } else {
/*     */       
/*  27 */       int i = getAge();
/*     */       
/*  29 */       if (i < 0) {
/*     */         
/*  31 */         i++;
/*  32 */         setAgeRaw(i);
/*  33 */       } else if (i > 0) {
/*     */         
/*  35 */         i--;
/*  36 */         setAgeRaw(i);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public GroupDataEntity prepare(WorldAccess worldaccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
/*  44 */     if (groupdataentity == null) {
/*  45 */       groupdataentity = new a(true);
/*     */     }
/*     */     
/*  48 */     a entityageable_a = (a)groupdataentity;
/*     */     
/*  50 */     if (entityageable_a.c() && entityageable_a.a() > 0 && this.random.nextFloat() <= entityageable_a.d()) {
/*  51 */       setAgeRaw(-24000);
/*     */     }
/*     */     
/*  54 */     entityageable_a.b();
/*  55 */     return super.prepare(worldaccess, difficultydamagescaler, enummobspawn, groupdataentity, nbttagcompound);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public abstract EntityAgeable createChild(WorldServer paramWorldServer, EntityAgeable paramEntityAgeable);
/*     */   
/*     */   protected void initDatawatcher() {
/*  63 */     super.initDatawatcher();
/*  64 */     this.datawatcher.register(bo, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public boolean canBreed() {
/*  68 */     return false;
/*     */   }
/*     */   
/*     */   public int getAge() {
/*  72 */     return this.world.isClientSide ? (((Boolean)this.datawatcher.<Boolean>get(bo)).booleanValue() ? -1 : 1) : this.b;
/*     */   }
/*     */   
/*     */   public void setAge(int i, boolean flag) {
/*  76 */     if (this.ageLocked)
/*  77 */       return;  int j = getAge();
/*  78 */     int k = j;
/*     */     
/*  80 */     j += i * 20;
/*  81 */     if (j > 0) {
/*  82 */       j = 0;
/*     */     }
/*     */     
/*  85 */     int l = j - k;
/*     */     
/*  87 */     setAgeRaw(j);
/*  88 */     if (flag) {
/*  89 */       this.c += l;
/*  90 */       if (this.d == 0) {
/*  91 */         this.d = 40;
/*     */       }
/*     */     } 
/*     */     
/*  95 */     if (getAge() == 0) {
/*  96 */       setAgeRaw(this.c);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAge(int i) {
/* 102 */     setAge(i, false);
/*     */   }
/*     */   
/*     */   public void setAgeRaw(int i) {
/* 106 */     int j = this.b;
/*     */     
/* 108 */     this.b = i;
/* 109 */     if ((j < 0 && i >= 0) || (j >= 0 && i < 0)) {
/* 110 */       this.datawatcher.set(bo, Boolean.valueOf((i < 0)));
/* 111 */       m();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/* 118 */     super.saveData(nbttagcompound);
/* 119 */     nbttagcompound.setInt("Age", getAge());
/* 120 */     nbttagcompound.setInt("ForcedAge", this.c);
/* 121 */     nbttagcompound.setBoolean("AgeLocked", this.ageLocked);
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/* 126 */     super.loadData(nbttagcompound);
/* 127 */     setAgeRaw(nbttagcompound.getInt("Age"));
/* 128 */     this.c = nbttagcompound.getInt("ForcedAge");
/* 129 */     this.ageLocked = nbttagcompound.getBoolean("AgeLocked");
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(DataWatcherObject<?> datawatcherobject) {
/* 134 */     if (bo.equals(datawatcherobject)) {
/* 135 */       updateSize();
/*     */     }
/*     */     
/* 138 */     super.a(datawatcherobject);
/*     */   }
/*     */ 
/*     */   
/*     */   public void movementTick() {
/* 143 */     super.movementTick();
/* 144 */     if (this.world.isClientSide || this.ageLocked) {
/* 145 */       if (this.d > 0) {
/* 146 */         if (this.d % 4 == 0) {
/* 147 */           this.world.addParticle(Particles.HAPPY_VILLAGER, d(1.0D), cE() + 0.5D, g(1.0D), 0.0D, 0.0D, 0.0D);
/*     */         }
/*     */         
/* 150 */         this.d--;
/*     */       } 
/* 152 */     } else if (isAlive()) {
/* 153 */       int i = getAge();
/*     */       
/* 155 */       if (i < 0) {
/* 156 */         i++;
/* 157 */         setAgeRaw(i);
/* 158 */       } else if (i > 0) {
/* 159 */         i--;
/* 160 */         setAgeRaw(i);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void m() {}
/*     */ 
/*     */   
/*     */   public boolean isBaby() {
/* 170 */     return (getAge() < 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBaby(boolean flag) {
/* 175 */     setAgeRaw(flag ? -24000 : 0);
/*     */   }
/*     */   
/*     */   public static class a
/*     */     implements GroupDataEntity {
/*     */     private int a;
/*     */     private final boolean b;
/*     */     private final float c;
/*     */     
/*     */     private a(boolean flag, float f) {
/* 185 */       this.b = flag;
/* 186 */       this.c = f;
/*     */     }
/*     */     
/*     */     public a(boolean flag) {
/* 190 */       this(flag, 0.05F);
/*     */     }
/*     */     
/*     */     public a(float f) {
/* 194 */       this(true, f);
/*     */     }
/*     */     
/*     */     public int a() {
/* 198 */       return this.a;
/*     */     }
/*     */     
/*     */     public void b() {
/* 202 */       this.a++;
/*     */     }
/*     */     
/*     */     public boolean c() {
/* 206 */       return this.b;
/*     */     }
/*     */     
/*     */     public float d() {
/* 210 */       return this.c;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityAgeable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */