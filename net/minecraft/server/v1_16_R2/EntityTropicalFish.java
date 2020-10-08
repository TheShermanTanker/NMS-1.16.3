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
/*     */ 
/*     */ 
/*     */ public class EntityTropicalFish
/*     */   extends EntityFishSchool
/*     */ {
/*  28 */   private static final DataWatcherObject<Integer> c = DataWatcher.a((Class)EntityTropicalFish.class, DataWatcherRegistry.b);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  34 */   private static final MinecraftKey[] d = new MinecraftKey[] { new MinecraftKey("textures/entity/fish/tropical_a.png"), new MinecraftKey("textures/entity/fish/tropical_b.png") };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   private static final MinecraftKey[] bo = new MinecraftKey[] { new MinecraftKey("textures/entity/fish/tropical_a_pattern_1.png"), new MinecraftKey("textures/entity/fish/tropical_a_pattern_2.png"), new MinecraftKey("textures/entity/fish/tropical_a_pattern_3.png"), new MinecraftKey("textures/entity/fish/tropical_a_pattern_4.png"), new MinecraftKey("textures/entity/fish/tropical_a_pattern_5.png"), new MinecraftKey("textures/entity/fish/tropical_a_pattern_6.png") };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   private static final MinecraftKey[] bp = new MinecraftKey[] { new MinecraftKey("textures/entity/fish/tropical_b_pattern_1.png"), new MinecraftKey("textures/entity/fish/tropical_b_pattern_2.png"), new MinecraftKey("textures/entity/fish/tropical_b_pattern_3.png"), new MinecraftKey("textures/entity/fish/tropical_b_pattern_4.png"), new MinecraftKey("textures/entity/fish/tropical_b_pattern_5.png"), new MinecraftKey("textures/entity/fish/tropical_b_pattern_6.png") };
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
/*  60 */   public static final int[] b = new int[] { 
/*  61 */       a(Variant.STRIPEY, EnumColor.ORANGE, EnumColor.GRAY), 
/*  62 */       a(Variant.FLOPPER, EnumColor.GRAY, EnumColor.GRAY), 
/*  63 */       a(Variant.FLOPPER, EnumColor.GRAY, EnumColor.BLUE), 
/*  64 */       a(Variant.CLAYFISH, EnumColor.WHITE, EnumColor.GRAY), 
/*  65 */       a(Variant.SUNSTREAK, EnumColor.BLUE, EnumColor.GRAY), 
/*  66 */       a(Variant.KOB, EnumColor.ORANGE, EnumColor.WHITE), 
/*  67 */       a(Variant.SPOTTY, EnumColor.PINK, EnumColor.LIGHT_BLUE), 
/*  68 */       a(Variant.BLOCKFISH, EnumColor.PURPLE, EnumColor.YELLOW), 
/*  69 */       a(Variant.CLAYFISH, EnumColor.WHITE, EnumColor.RED), 
/*  70 */       a(Variant.SPOTTY, EnumColor.WHITE, EnumColor.YELLOW), 
/*  71 */       a(Variant.GLITTER, EnumColor.WHITE, EnumColor.GRAY), 
/*  72 */       a(Variant.CLAYFISH, EnumColor.WHITE, EnumColor.ORANGE), 
/*  73 */       a(Variant.DASHER, EnumColor.CYAN, EnumColor.PINK), 
/*  74 */       a(Variant.BRINELY, EnumColor.LIME, EnumColor.LIGHT_BLUE), 
/*  75 */       a(Variant.BETTY, EnumColor.RED, EnumColor.WHITE), 
/*  76 */       a(Variant.SNOOPER, EnumColor.GRAY, EnumColor.RED), 
/*  77 */       a(Variant.BLOCKFISH, EnumColor.RED, EnumColor.WHITE), 
/*  78 */       a(Variant.FLOPPER, EnumColor.WHITE, EnumColor.YELLOW), 
/*  79 */       a(Variant.KOB, EnumColor.RED, EnumColor.WHITE), 
/*  80 */       a(Variant.SUNSTREAK, EnumColor.GRAY, EnumColor.WHITE), 
/*  81 */       a(Variant.DASHER, EnumColor.CYAN, EnumColor.YELLOW), 
/*  82 */       a(Variant.FLOPPER, EnumColor.YELLOW, EnumColor.YELLOW) };
/*     */   
/*     */   enum Variant
/*     */   {
/*  86 */     KOB(0, 0),
/*  87 */     SUNSTREAK(0, 1),
/*  88 */     SNOOPER(0, 2),
/*  89 */     DASHER(0, 3),
/*  90 */     BRINELY(0, 4),
/*  91 */     SPOTTY(0, 5),
/*  92 */     FLOPPER(1, 0),
/*  93 */     STRIPEY(1, 1),
/*  94 */     GLITTER(1, 2),
/*  95 */     BLOCKFISH(1, 3),
/*  96 */     BETTY(1, 4),
/*  97 */     CLAYFISH(1, 5);
/*     */     
/*     */     private final int m;
/*     */     private final int n;
/* 101 */     private static final Variant[] o = values();
/*     */     
/*     */     Variant(int var2, int var3) {
/* 104 */       this.m = var2;
/* 105 */       this.n = var3;
/*     */     } static {
/*     */     
/*     */     } public int a() {
/* 109 */       return this.m;
/*     */     }
/*     */     
/*     */     public int b() {
/* 113 */       return this.n;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int a(Variant var0, EnumColor var1, EnumColor var2) {
/* 126 */     return var0.a() & 0xFF | (var0.b() & 0xFF) << 8 | (var1.getColorIndex() & 0xFF) << 16 | (var2.getColorIndex() & 0xFF) << 24;
/*     */   }
/*     */   
/*     */   private boolean bq = true;
/*     */   
/*     */   public EntityTropicalFish(EntityTypes<? extends EntityTropicalFish> var0, World var1) {
/* 132 */     super((EntityTypes)var0, var1);
/*     */   }
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
/*     */   protected void initDatawatcher() {
/* 155 */     super.initDatawatcher();
/*     */     
/* 157 */     this.datawatcher.register(c, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound var0) {
/* 162 */     super.saveData(var0);
/*     */     
/* 164 */     var0.setInt("Variant", getVariant());
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound var0) {
/* 169 */     super.loadData(var0);
/*     */     
/* 171 */     setVariant(var0.getInt("Variant"));
/*     */   }
/*     */   
/*     */   public void setVariant(int var0) {
/* 175 */     this.datawatcher.set(c, Integer.valueOf(var0));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(int var0) {
/* 180 */     return !this.bq;
/*     */   }
/*     */   
/*     */   public int getVariant() {
/* 184 */     return ((Integer)this.datawatcher.<Integer>get(c)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void k(ItemStack var0) {
/* 189 */     super.k(var0);
/*     */     
/* 191 */     NBTTagCompound var1 = var0.getOrCreateTag();
/* 192 */     var1.setInt("BucketVariantTag", getVariant());
/*     */   }
/*     */ 
/*     */   
/*     */   protected ItemStack eK() {
/* 197 */     return new ItemStack(Items.TROPICAL_FISH_BUCKET);
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/* 202 */     return SoundEffects.ENTITY_TROPICAL_FISH_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/* 207 */     return SoundEffects.ENTITY_TROPICAL_FISH_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource var0) {
/* 212 */     return SoundEffects.ENTITY_TROPICAL_FISH_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundFlop() {
/* 217 */     return SoundEffects.ENTITY_TROPICAL_FISH_FLOP;
/*     */   }
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
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(WorldAccess var0, DifficultyDamageScaler var1, EnumMobSpawn var2, @Nullable GroupDataEntity var3, @Nullable NBTTagCompound var4) {
/*     */     int var5, var6, var7, var8;
/* 263 */     var3 = super.prepare(var0, var1, var2, var3, var4);
/*     */     
/* 265 */     if (var4 != null && var4.hasKeyOfType("BucketVariantTag", 3)) {
/* 266 */       setVariant(var4.getInt("BucketVariantTag"));
/* 267 */       return var3;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 276 */     if (var3 instanceof b) {
/* 277 */       b var9 = (b)var3;
/* 278 */       var5 = b.a(var9);
/* 279 */       var6 = b.b(var9);
/* 280 */       var7 = b.c(var9);
/* 281 */       var8 = b.d(var9);
/* 282 */     } else if (this.random.nextFloat() < 0.9D) {
/*     */       
/* 284 */       int var9 = SystemUtils.a(b, this.random);
/* 285 */       var5 = var9 & 0xFF;
/* 286 */       var6 = (var9 & 0xFF00) >> 8;
/* 287 */       var7 = (var9 & 0xFF0000) >> 16;
/* 288 */       var8 = (var9 & 0xFF000000) >> 24;
/* 289 */       var3 = new b(this, var5, var6, var7, var8);
/*     */     } else {
/* 291 */       this.bq = false;
/* 292 */       var5 = this.random.nextInt(2);
/* 293 */       var6 = this.random.nextInt(6);
/* 294 */       var7 = this.random.nextInt(15);
/* 295 */       var8 = this.random.nextInt(15);
/*     */     } 
/*     */     
/* 298 */     setVariant(var5 | var6 << 8 | var7 << 16 | var8 << 24);
/*     */     
/* 300 */     return var3;
/*     */   }
/*     */   
/*     */   static class b extends EntityFishSchool.a {
/*     */     private final int b;
/*     */     private final int c;
/*     */     private final int d;
/*     */     private final int e;
/*     */     
/*     */     private b(EntityTropicalFish var0, int var1, int var2, int var3, int var4) {
/* 310 */       super(var0);
/* 311 */       this.b = var1;
/* 312 */       this.c = var2;
/* 313 */       this.d = var3;
/* 314 */       this.e = var4;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityTropicalFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */