/*     */ package net.minecraft.server.v1_16_R2;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityMinecartFurnace
/*     */   extends EntityMinecartAbstract
/*     */ {
/*  29 */   private static final DataWatcherObject<Boolean> d = DataWatcher.a((Class)EntityMinecartFurnace.class, DataWatcherRegistry.i);
/*     */   
/*     */   private int e;
/*     */   public double b;
/*     */   public double c;
/*  34 */   private static final RecipeItemStack f = RecipeItemStack.a(new IMaterial[] { Items.COAL, Items.CHARCOAL });
/*     */   
/*     */   public EntityMinecartFurnace(EntityTypes<? extends EntityMinecartFurnace> var0, World var1) {
/*  37 */     super(var0, var1);
/*     */   }
/*     */   
/*     */   public EntityMinecartFurnace(World var0, double var1, double var3, double var5) {
/*  41 */     super(EntityTypes.FURNACE_MINECART, var0, var1, var3, var5);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityMinecartAbstract.EnumMinecartType getMinecartType() {
/*  46 */     return EntityMinecartAbstract.EnumMinecartType.FURNACE;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  51 */     super.initDatawatcher();
/*  52 */     this.datawatcher.register(d, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  57 */     super.tick();
/*     */     
/*  59 */     if (!this.world.s_()) {
/*  60 */       if (this.e > 0) {
/*  61 */         this.e--;
/*     */       }
/*  63 */       if (this.e <= 0) {
/*  64 */         this.b = 0.0D;
/*  65 */         this.c = 0.0D;
/*     */       } 
/*  67 */       o((this.e > 0));
/*     */     } 
/*     */     
/*  70 */     if (u() && this.random.nextInt(4) == 0) {
/*  71 */       this.world.addParticle(Particles.LARGE_SMOKE, locX(), locY() + 0.8D, locZ(), 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected double getMaxSpeed() {
/*  77 */     return 0.2D;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(DamageSource var0) {
/*  82 */     super.a(var0);
/*     */     
/*  84 */     if (!var0.isExplosion() && this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
/*  85 */       a(Blocks.FURNACE);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void c(BlockPosition var0, IBlockData var1) {
/*  92 */     double var2 = 1.0E-4D;
/*  93 */     double var4 = 0.001D;
/*     */     
/*  95 */     super.c(var0, var1);
/*     */     
/*  97 */     Vec3D var6 = getMot();
/*     */     
/*  99 */     double var7 = c(var6);
/* 100 */     double var9 = this.b * this.b + this.c * this.c;
/* 101 */     if (var9 > 1.0E-4D && var7 > 0.001D) {
/* 102 */       double var11 = MathHelper.sqrt(var7);
/* 103 */       double var13 = MathHelper.sqrt(var9);
/*     */ 
/*     */       
/* 106 */       this.b = var6.x / var11 * var13;
/* 107 */       this.c = var6.z / var11 * var13;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void decelerate() {
/* 113 */     double var0 = this.b * this.b + this.c * this.c;
/*     */     
/* 115 */     if (var0 > 1.0E-7D) {
/* 116 */       var0 = MathHelper.sqrt(var0);
/* 117 */       this.b /= var0;
/* 118 */       this.c /= var0;
/* 119 */       setMot(getMot().d(0.8D, 0.0D, 0.8D).add(this.b, 0.0D, this.c));
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 125 */       setMot(getMot().d(0.98D, 0.0D, 0.98D));
/*     */     } 
/*     */     
/* 128 */     super.decelerate();
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult a(EntityHuman var0, EnumHand var1) {
/* 133 */     ItemStack var2 = var0.b(var1);
/* 134 */     if (f.test(var2) && this.e + 3600 <= 32000) {
/* 135 */       if (!var0.abilities.canInstantlyBuild) {
/* 136 */         var2.subtract(1);
/*     */       }
/* 138 */       this.e += 3600;
/*     */     } 
/*     */     
/* 141 */     if (this.e > 0) {
/* 142 */       this.b = locX() - var0.locX();
/* 143 */       this.c = locZ() - var0.locZ();
/*     */     } 
/*     */     
/* 146 */     return EnumInteractionResult.a(this.world.isClientSide);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void saveData(NBTTagCompound var0) {
/* 151 */     super.saveData(var0);
/* 152 */     var0.setDouble("PushX", this.b);
/* 153 */     var0.setDouble("PushZ", this.c);
/* 154 */     var0.setShort("Fuel", (short)this.e);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void loadData(NBTTagCompound var0) {
/* 159 */     super.loadData(var0);
/* 160 */     this.b = var0.getDouble("PushX");
/* 161 */     this.c = var0.getDouble("PushZ");
/* 162 */     this.e = var0.getShort("Fuel");
/*     */   }
/*     */   
/*     */   protected boolean u() {
/* 166 */     return ((Boolean)this.datawatcher.<Boolean>get(d)).booleanValue();
/*     */   }
/*     */   
/*     */   protected void o(boolean var0) {
/* 170 */     this.datawatcher.set(d, Boolean.valueOf(var0));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData q() {
/* 175 */     return Blocks.FURNACE.getBlockData().set(BlockFurnaceFurace.FACING, EnumDirection.NORTH).set(BlockFurnaceFurace.LIT, Boolean.valueOf(u()));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityMinecartFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */