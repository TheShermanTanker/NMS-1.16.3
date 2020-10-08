/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.EnumSet;
/*     */ import java.util.List;
/*     */ import java.util.Random;
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
/*     */ public abstract class EntityMonsterPatrolling
/*     */   extends EntityMonster
/*     */ {
/*     */   private BlockPosition patrolTarget;
/*     */   private boolean patrolLeader;
/*     */   private boolean patrolling;
/*     */   
/*     */   protected EntityMonsterPatrolling(EntityTypes<? extends EntityMonsterPatrolling> var0, World var1) {
/*  33 */     super((EntityTypes)var0, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  38 */     super.initPathfinder();
/*  39 */     this.goalSelector.a(4, new a<>(this, 0.7D, 0.595D));
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound var0) {
/*  44 */     super.saveData(var0);
/*     */     
/*  46 */     if (this.patrolTarget != null) {
/*  47 */       var0.set("PatrolTarget", GameProfileSerializer.a(this.patrolTarget));
/*     */     }
/*     */     
/*  50 */     var0.setBoolean("PatrolLeader", this.patrolLeader);
/*  51 */     var0.setBoolean("Patrolling", this.patrolling);
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound var0) {
/*  56 */     super.loadData(var0);
/*     */     
/*  58 */     if (var0.hasKey("PatrolTarget")) {
/*  59 */       this.patrolTarget = GameProfileSerializer.b(var0.getCompound("PatrolTarget"));
/*     */     }
/*     */     
/*  62 */     this.patrolLeader = var0.getBoolean("PatrolLeader");
/*  63 */     this.patrolling = var0.getBoolean("Patrolling");
/*     */   }
/*     */ 
/*     */   
/*     */   public double ba() {
/*  68 */     return -0.45D;
/*     */   }
/*     */   
/*     */   public boolean eN() {
/*  72 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(WorldAccess var0, DifficultyDamageScaler var1, EnumMobSpawn var2, @Nullable GroupDataEntity var3, @Nullable NBTTagCompound var4) {
/*  80 */     if (var2 != EnumMobSpawn.PATROL && var2 != EnumMobSpawn.EVENT && var2 != EnumMobSpawn.STRUCTURE && 
/*  81 */       this.random.nextFloat() < 0.06F && eN()) {
/*  82 */       this.patrolLeader = true;
/*     */     }
/*     */ 
/*     */     
/*  86 */     if (isPatrolLeader()) {
/*  87 */       setSlot(EnumItemSlot.HEAD, Raid.s());
/*  88 */       a(EnumItemSlot.HEAD, 2.0F);
/*     */     } 
/*     */     
/*  91 */     if (var2 == EnumMobSpawn.PATROL) {
/*  92 */       this.patrolling = true;
/*     */     }
/*     */     
/*  95 */     return super.prepare(var0, var1, var2, var3, var4);
/*     */   }
/*     */   
/*     */   public static boolean b(EntityTypes<? extends EntityMonsterPatrolling> var0, GeneratorAccess var1, EnumMobSpawn var2, BlockPosition var3, Random var4) {
/*  99 */     if (var1.getBrightness(EnumSkyBlock.BLOCK, var3) > 8) {
/* 100 */       return false;
/*     */     }
/*     */     
/* 103 */     return c((EntityTypes)var0, var1, var2, var3, var4);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTypeNotPersistent(double var0) {
/* 108 */     return (!this.patrolling || var0 > 16384.0D);
/*     */   }
/*     */   
/*     */   public void setPatrolTarget(BlockPosition var0) {
/* 112 */     this.patrolTarget = var0;
/* 113 */     this.patrolling = true;
/*     */   }
/*     */   
/*     */   public BlockPosition getPatrolTarget() {
/* 117 */     return this.patrolTarget;
/*     */   }
/*     */   
/*     */   public boolean eP() {
/* 121 */     return (this.patrolTarget != null);
/*     */   }
/*     */   
/*     */   public void setPatrolLeader(boolean var0) {
/* 125 */     this.patrolLeader = var0;
/* 126 */     this.patrolling = true;
/*     */   }
/*     */   
/*     */   public boolean isPatrolLeader() {
/* 130 */     return this.patrolLeader;
/*     */   }
/*     */   
/*     */   public boolean eT() {
/* 134 */     return true;
/*     */   }
/*     */   
/*     */   public void eU() {
/* 138 */     this.patrolTarget = getChunkCoordinates().b(-500 + this.random.nextInt(1000), 0, -500 + this.random.nextInt(1000));
/* 139 */     this.patrolling = true;
/*     */   }
/*     */   
/*     */   protected boolean isPatrolling() {
/* 143 */     return this.patrolling;
/*     */   }
/*     */   
/*     */   protected void u(boolean var0) {
/* 147 */     this.patrolling = var0;
/*     */   }
/*     */   
/*     */   public static class a<T extends EntityMonsterPatrolling>
/*     */     extends PathfinderGoal
/*     */   {
/*     */     private final T a;
/*     */     private final double b;
/*     */     private final double c;
/*     */     private long d;
/*     */     
/*     */     public a(T var0, double var1, double var3) {
/* 159 */       this.a = var0;
/* 160 */       this.b = var1;
/* 161 */       this.c = var3;
/* 162 */       this.d = -1L;
/* 163 */       a(EnumSet.of(PathfinderGoal.Type.MOVE));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 168 */       boolean var0 = (((EntityMonsterPatrolling)this.a).world.getTime() < this.d);
/* 169 */       return (this.a.isPatrolling() && this.a.getGoalTarget() == null && !this.a.isVehicle() && this.a.eP() && !var0);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void c() {}
/*     */ 
/*     */ 
/*     */     
/*     */     public void d() {}
/*     */ 
/*     */     
/*     */     public void e() {
/* 182 */       boolean var0 = this.a.isPatrolLeader();
/* 183 */       NavigationAbstract var1 = this.a.getNavigation();
/* 184 */       if (var1.m()) {
/* 185 */         List<EntityMonsterPatrolling> var2 = g();
/* 186 */         if (this.a.isPatrolling() && var2.isEmpty()) {
/* 187 */           this.a.u(false);
/* 188 */         } else if (!var0 || !this.a.getPatrolTarget().a(this.a.getPositionVector(), 10.0D)) {
/* 189 */           Vec3D var3 = Vec3D.c(this.a.getPatrolTarget());
/*     */ 
/*     */           
/* 192 */           Vec3D var4 = this.a.getPositionVector();
/* 193 */           Vec3D var5 = var4.d(var3);
/*     */           
/* 195 */           var3 = var5.b(90.0F).a(0.4D).e(var3);
/*     */           
/* 197 */           Vec3D var6 = var3.d(var4).d().a(10.0D).e(var4);
/* 198 */           BlockPosition var7 = new BlockPosition(var6);
/* 199 */           var7 = ((EntityMonsterPatrolling)this.a).world.getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, var7);
/*     */           
/* 201 */           if (!var1.a(var7.getX(), var7.getY(), var7.getZ(), var0 ? this.c : this.b)) {
/*     */             
/* 203 */             h();
/* 204 */             this.d = ((EntityMonsterPatrolling)this.a).world.getTime() + 200L;
/* 205 */           } else if (var0) {
/* 206 */             for (EntityMonsterPatrolling var9 : var2) {
/* 207 */               var9.setPatrolTarget(var7);
/*     */             }
/*     */           } 
/*     */         } else {
/* 211 */           this.a.eU();
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     private List<EntityMonsterPatrolling> g() {
/* 217 */       return ((EntityMonsterPatrolling)this.a).world.a(EntityMonsterPatrolling.class, this.a.getBoundingBox().g(16.0D), var0 -> (var0.eT() && !var0.s((Entity)this.a)));
/*     */     }
/*     */     
/*     */     private boolean h() {
/* 221 */       Random var0 = this.a.getRandom();
/* 222 */       BlockPosition var1 = ((EntityMonsterPatrolling)this.a).world.getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, this.a.getChunkCoordinates().b(-8 + var0.nextInt(16), 0, -8 + var0.nextInt(16)));
/* 223 */       return this.a.getNavigation().a(var1.getX(), var1.getY(), var1.getZ(), this.b);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityMonsterPatrolling.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */