/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class EntityFishSchool
/*     */   extends EntityFish
/*     */ {
/*     */   private EntityFishSchool b;
/*  18 */   private int c = 1;
/*     */   
/*     */   public EntityFishSchool(EntityTypes<? extends EntityFishSchool> var0, World var1) {
/*  21 */     super((EntityTypes)var0, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  26 */     super.initPathfinder();
/*     */     
/*  28 */     this.goalSelector.a(5, new PathfinderGoalFishSchool(this));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxSpawnGroup() {
/*  33 */     return eN();
/*     */   }
/*     */   
/*     */   public int eN() {
/*  37 */     return super.getMaxSpawnGroup();
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean eL() {
/*  42 */     return !eO();
/*     */   }
/*     */   
/*     */   public boolean eO() {
/*  46 */     return (this.b != null && this.b.isAlive());
/*     */   }
/*     */   
/*     */   public EntityFishSchool a(EntityFishSchool var0) {
/*  50 */     this.b = var0;
/*  51 */     var0.eU();
/*     */     
/*  53 */     return var0;
/*     */   }
/*     */   
/*     */   public void eP() {
/*  57 */     this.b.eV();
/*  58 */     this.b = null;
/*     */   }
/*     */   
/*     */   private void eU() {
/*  62 */     this.c++;
/*     */   }
/*     */   
/*     */   private void eV() {
/*  66 */     this.c--;
/*     */   }
/*     */   
/*     */   public boolean eQ() {
/*  70 */     return (eR() && this.c < eN());
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  75 */     super.tick();
/*     */ 
/*     */     
/*  78 */     if (eR() && this.world.random.nextInt(200) == 1) {
/*  79 */       List<EntityFish> var0 = (List)this.world.a(getClass(), getBoundingBox().grow(8.0D, 8.0D, 8.0D));
/*  80 */       if (var0.size() <= 1) {
/*  81 */         this.c = 1;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean eR() {
/*  87 */     return (this.c > 1);
/*     */   }
/*     */   
/*     */   public boolean eS() {
/*  91 */     return (h(this.b) <= 121.0D);
/*     */   }
/*     */   
/*     */   public void eT() {
/*  95 */     if (eO()) {
/*  96 */       getNavigation().a(this.b, 1.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   public void a(Stream<EntityFishSchool> var0) {
/* 101 */     var0.limit((eN() - this.c)).filter(var0 -> (var0 != this)).forEach(var0 -> var0.a(this));
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(WorldAccess var0, DifficultyDamageScaler var1, EnumMobSpawn var2, @Nullable GroupDataEntity var3, @Nullable NBTTagCompound var4) {
/* 107 */     super.prepare(var0, var1, var2, var3, var4);
/*     */     
/* 109 */     if (var3 == null) {
/* 110 */       var3 = new a(this);
/*     */     } else {
/* 112 */       a(((a)var3).a);
/*     */     } 
/*     */     
/* 115 */     return var3;
/*     */   }
/*     */   
/*     */   public static class a implements GroupDataEntity {
/*     */     public final EntityFishSchool a;
/*     */     
/*     */     public a(EntityFishSchool var0) {
/* 122 */       this.a = var0;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityFishSchool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */