/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.datafixers.kinds.App;
/*     */ import com.mojang.datafixers.kinds.Applicative;
/*     */ import com.mojang.datafixers.util.Function14;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.DataResult;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import com.mojang.serialization.Lifecycle;
/*     */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*     */ import java.io.File;
/*     */ import java.util.Optional;
/*     */ import java.util.OptionalLong;
/*     */ import java.util.function.Supplier;
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
/*     */ public class DimensionManager
/*     */ {
/*  36 */   public static final MinecraftKey OVERWORLD_KEY = new MinecraftKey("overworld");
/*  37 */   public static final MinecraftKey THE_NETHER_KEY = new MinecraftKey("the_nether");
/*  38 */   public static final MinecraftKey THE_END_KEY = new MinecraftKey("the_end");
/*     */   static {
/*  40 */     d = RecordCodecBuilder.create(var0 -> var0.group((App)Codec.LONG.optionalFieldOf("fixed_time").xmap((), ()).forGetter(()), (App)Codec.BOOL.fieldOf("has_skylight").forGetter(DimensionManager::hasSkyLight), (App)Codec.BOOL.fieldOf("has_ceiling").forGetter(DimensionManager::hasCeiling), (App)Codec.BOOL.fieldOf("ultrawarm").forGetter(DimensionManager::isNether), (App)Codec.BOOL.fieldOf("natural").forGetter(DimensionManager::isNatural), (App)Codec.doubleRange(9.999999747378752E-6D, 3.0E7D).fieldOf("coordinate_scale").forGetter(DimensionManager::getCoordinateScale), (App)Codec.BOOL.fieldOf("piglin_safe").forGetter(DimensionManager::isPiglinSafe), (App)Codec.BOOL.fieldOf("bed_works").forGetter(DimensionManager::isBedWorks), (App)Codec.BOOL.fieldOf("respawn_anchor_works").forGetter(DimensionManager::isRespawnAnchorWorks), (App)Codec.BOOL.fieldOf("has_raids").forGetter(DimensionManager::hasRaids), (App)Codec.intRange(0, 256).fieldOf("logical_height").forGetter(DimensionManager::getLogicalHeight), (App)MinecraftKey.a.fieldOf("infiniburn").forGetter(()), (App)MinecraftKey.a.fieldOf("effects").orElse(OVERWORLD_KEY).forGetter(()), (App)Codec.FLOAT.fieldOf("ambient_light").forGetter(())).apply((Applicative)var0, DimensionManager::new));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final Codec<DimensionManager> d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   public static final float[] e = new float[] { 1.0F, 0.75F, 0.5F, 0.25F, 0.0F, 0.25F, 0.5F, 0.75F };
/*     */ 
/*     */ 
/*     */   
/*  62 */   public static final ResourceKey<DimensionManager> OVERWORLD = ResourceKey.a(IRegistry.K, new MinecraftKey("overworld"));
/*  63 */   public static final ResourceKey<DimensionManager> THE_NETHER = ResourceKey.a(IRegistry.K, new MinecraftKey("the_nether"));
/*  64 */   public static final ResourceKey<DimensionManager> THE_END = ResourceKey.a(IRegistry.K, new MinecraftKey("the_end"));
/*     */ 
/*     */   
/*  67 */   protected static final DimensionManager OVERWORLD_IMPL = new DimensionManager(OptionalLong.empty(), true, false, false, true, 1.0D, false, false, true, false, true, 256, GenLayerZoomVoronoiFixed.INSTANCE, TagsBlock.aE.a(), OVERWORLD_KEY, 0.0F);
/*  68 */   protected static final DimensionManager THE_NETHER_IMPL = new DimensionManager(OptionalLong.of(18000L), false, true, true, false, 8.0D, false, true, false, true, false, 128, GenLayerZoomVoronoi.INSTANCE, TagsBlock.aF.a(), THE_NETHER_KEY, 0.1F);
/*  69 */   protected static final DimensionManager THE_END_IMPL = new DimensionManager(OptionalLong.of(6000L), false, false, false, false, 1.0D, true, false, false, false, true, 256, GenLayerZoomVoronoi.INSTANCE, TagsBlock.aG.a(), THE_END_KEY, 0.0F);
/*     */   
/*  71 */   public static final ResourceKey<DimensionManager> l = ResourceKey.a(IRegistry.K, new MinecraftKey("overworld_caves"));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   protected static final DimensionManager m = new DimensionManager(OptionalLong.empty(), true, true, false, true, 1.0D, false, false, true, false, true, 256, GenLayerZoomVoronoiFixed.INSTANCE, TagsBlock.aE.a(), OVERWORLD_KEY, 0.0F);
/*     */   
/*  78 */   public static final Codec<Supplier<DimensionManager>> n = RegistryFileCodec.a(IRegistry.K, d);
/*     */   
/*     */   private final OptionalLong fixedTime;
/*     */   
/*     */   private final boolean hasSkylight;
/*     */   
/*     */   private final boolean hasCeiling;
/*     */   private final boolean ultraWarm;
/*     */   private final boolean natural;
/*     */   private final double coordinateScale;
/*     */   private final boolean createDragonBattle;
/*     */   private final boolean piglinSafe;
/*     */   private final boolean bedWorks;
/*     */   private final boolean respawnAnchorWorks;
/*     */   private final boolean hasRaids;
/*     */   private final int logicalHeight;
/*     */   private final GenLayerZoomer genLayerZoomer;
/*     */   private final MinecraftKey infiniburn;
/*     */   private final MinecraftKey effects;
/*     */   private final float ambientLight;
/*     */   private final transient float[] E;
/*     */   
/*     */   protected DimensionManager(OptionalLong var0, boolean var1, boolean var2, boolean var3, boolean var4, double var5, boolean var7, boolean var8, boolean var9, boolean var10, int var11, MinecraftKey var12, MinecraftKey var13, float var14) {
/* 101 */     this(var0, var1, var2, var3, var4, var5, false, var7, var8, var9, var10, var11, GenLayerZoomVoronoi.INSTANCE, var12, var13, var14);
/*     */   }
/*     */   
/*     */   protected DimensionManager(OptionalLong var0, boolean var1, boolean var2, boolean var3, boolean var4, double var5, boolean var7, boolean var8, boolean var9, boolean var10, boolean var11, int var12, GenLayerZoomer var13, MinecraftKey var14, MinecraftKey var15, float var16) {
/* 105 */     this.fixedTime = var0;
/* 106 */     this.hasSkylight = var1;
/* 107 */     this.hasCeiling = var2;
/* 108 */     this.ultraWarm = var3;
/* 109 */     this.natural = var4;
/* 110 */     this.coordinateScale = var5;
/* 111 */     this.createDragonBattle = var7;
/* 112 */     this.piglinSafe = var8;
/* 113 */     this.bedWorks = var9;
/* 114 */     this.respawnAnchorWorks = var10;
/* 115 */     this.hasRaids = var11;
/* 116 */     this.logicalHeight = var12;
/* 117 */     this.genLayerZoomer = var13;
/* 118 */     this.infiniburn = var14;
/* 119 */     this.effects = var15;
/* 120 */     this.ambientLight = var16;
/* 121 */     this.E = a(var16);
/*     */   }
/*     */   
/*     */   private static float[] a(float var0) {
/* 125 */     float[] var1 = new float[16];
/* 126 */     for (int var2 = 0; var2 <= 15; var2++) {
/*     */       
/* 128 */       float var3 = var2 / 15.0F;
/*     */       
/* 130 */       float var4 = var3 / (4.0F - 3.0F * var3);
/* 131 */       var1[var2] = MathHelper.g(var0, var4, 1.0F);
/*     */     } 
/* 133 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static DataResult<ResourceKey<World>> a(Dynamic<?> var0) {
/* 139 */     Optional<Number> var1 = var0.asNumber().result();
/* 140 */     if (var1.isPresent()) {
/* 141 */       int var2 = ((Number)var1.get()).intValue();
/* 142 */       if (var2 == -1)
/* 143 */         return DataResult.success(World.THE_NETHER); 
/* 144 */       if (var2 == 0)
/* 145 */         return DataResult.success(World.OVERWORLD); 
/* 146 */       if (var2 == 1) {
/* 147 */         return DataResult.success(World.THE_END);
/*     */       }
/*     */     } 
/*     */     
/* 151 */     return World.f.parse(var0);
/*     */   }
/*     */   
/*     */   public static IRegistryCustom.Dimension a(IRegistryCustom.Dimension var0) {
/* 155 */     IRegistryWritable<DimensionManager> var1 = var0.b(IRegistry.K);
/* 156 */     var1.a(OVERWORLD, OVERWORLD_IMPL, Lifecycle.stable());
/* 157 */     var1.a(l, m, Lifecycle.stable());
/* 158 */     var1.a(THE_NETHER, THE_NETHER_IMPL, Lifecycle.stable());
/* 159 */     var1.a(THE_END, THE_END_IMPL, Lifecycle.stable());
/* 160 */     return var0;
/*     */   }
/*     */   
/*     */   private static ChunkGenerator a(IRegistry<BiomeBase> var0, IRegistry<GeneratorSettingBase> var1, long var2) {
/* 164 */     return new ChunkGeneratorAbstract(new WorldChunkManagerTheEnd(var0, var2), var2, () -> (GeneratorSettingBase)var0.d(GeneratorSettingBase.f));
/*     */   }
/*     */   
/*     */   private static ChunkGenerator b(IRegistry<BiomeBase> var0, IRegistry<GeneratorSettingBase> var1, long var2) {
/* 168 */     return new ChunkGeneratorAbstract(WorldChunkManagerMultiNoise.b.a.a(var0, var2), var2, () -> (GeneratorSettingBase)var0.d(GeneratorSettingBase.e));
/*     */   }
/*     */   
/*     */   public static RegistryMaterials<WorldDimension> a(IRegistry<DimensionManager> var0, IRegistry<BiomeBase> var1, IRegistry<GeneratorSettingBase> var2, long var3) {
/* 172 */     RegistryMaterials<WorldDimension> var5 = new RegistryMaterials<>(IRegistry.M, Lifecycle.experimental());
/*     */     
/* 174 */     var5.a(WorldDimension.THE_NETHER, new WorldDimension(() -> (DimensionManager)var0.d(THE_NETHER), b(var1, var2, var3)), Lifecycle.stable());
/* 175 */     var5.a(WorldDimension.THE_END, new WorldDimension(() -> (DimensionManager)var0.d(THE_END), a(var1, var2, var3)), Lifecycle.stable());
/* 176 */     return var5;
/*     */   }
/*     */   
/*     */   public static double a(DimensionManager var0, DimensionManager var1) {
/* 180 */     double var2 = var0.getCoordinateScale();
/* 181 */     double var4 = var1.getCoordinateScale();
/*     */     
/* 183 */     return var2 / var4;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public String getSuffix() {
/* 188 */     if (a(THE_END_IMPL)) {
/* 189 */       return "_end";
/*     */     }
/* 191 */     return "";
/*     */   }
/*     */   
/*     */   public static File a(ResourceKey<World> var0, File var1) {
/* 195 */     if (var0 == World.OVERWORLD) {
/* 196 */       return var1;
/*     */     }
/* 198 */     if (var0 == World.THE_END) {
/* 199 */       return new File(var1, "DIM1");
/*     */     }
/* 201 */     if (var0 == World.THE_NETHER) {
/* 202 */       return new File(var1, "DIM-1");
/*     */     }
/* 204 */     return new File(var1, "dimensions/" + var0.a().getNamespace() + "/" + var0.a().getKey());
/*     */   }
/*     */   
/*     */   public boolean hasSkyLight() {
/* 208 */     return this.hasSkylight;
/*     */   }
/*     */   
/*     */   public boolean hasCeiling() {
/* 212 */     return this.hasCeiling;
/*     */   }
/*     */   
/*     */   public boolean isNether() {
/* 216 */     return this.ultraWarm;
/*     */   }
/*     */   
/*     */   public boolean isNatural() {
/* 220 */     return this.natural;
/*     */   }
/*     */   
/*     */   public double getCoordinateScale() {
/* 224 */     return this.coordinateScale;
/*     */   }
/*     */   
/*     */   public boolean isPiglinSafe() {
/* 228 */     return this.piglinSafe;
/*     */   }
/*     */   
/*     */   public boolean isBedWorks() {
/* 232 */     return this.bedWorks;
/*     */   }
/*     */   
/*     */   public boolean isRespawnAnchorWorks() {
/* 236 */     return this.respawnAnchorWorks;
/*     */   }
/*     */   
/*     */   public boolean hasRaids() {
/* 240 */     return this.hasRaids;
/*     */   }
/*     */   
/*     */   public int getLogicalHeight() {
/* 244 */     return this.logicalHeight;
/*     */   }
/*     */   
/*     */   public boolean isCreateDragonBattle() {
/* 248 */     return this.createDragonBattle;
/*     */   }
/*     */   
/*     */   public GenLayerZoomer getGenLayerZoomer() {
/* 252 */     return this.genLayerZoomer;
/*     */   }
/*     */   
/*     */   public boolean isFixedTime() {
/* 256 */     return this.fixedTime.isPresent();
/*     */   }
/*     */ 
/*     */   
/*     */   public float a(long var0) {
/* 261 */     double var2 = MathHelper.h(this.fixedTime.orElse(var0) / 24000.0D - 0.25D);
/*     */ 
/*     */     
/* 264 */     double var4 = 0.5D - Math.cos(var2 * Math.PI) / 2.0D;
/*     */     
/* 266 */     return (float)(var2 * 2.0D + var4) / 3.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public int b(long var0) {
/* 271 */     return (int)(var0 / 24000L % 8L + 8L) % 8;
/*     */   }
/*     */ 
/*     */   
/*     */   public float a(int var0) {
/* 276 */     return this.E[var0];
/*     */   }
/*     */   
/*     */   public Tag<Block> o() {
/* 280 */     Tag<Block> var0 = TagsBlock.a().a(this.infiniburn);
/* 281 */     return (var0 != null) ? var0 : TagsBlock.aE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(DimensionManager var0) {
/* 289 */     if (this == var0) {
/* 290 */       return true;
/*     */     }
/* 292 */     return (this.hasSkylight == var0.hasSkylight && this.hasCeiling == var0.hasCeiling && this.ultraWarm == var0.ultraWarm && this.natural == var0.natural && this.coordinateScale == var0.coordinateScale && this.createDragonBattle == var0.createDragonBattle && this.piglinSafe == var0.piglinSafe && this.bedWorks == var0.bedWorks && this.respawnAnchorWorks == var0.respawnAnchorWorks && this.hasRaids == var0.hasRaids && this.logicalHeight == var0.logicalHeight && 
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
/* 303 */       Float.compare(var0.ambientLight, this.ambientLight) == 0 && this.fixedTime
/* 304 */       .equals(var0.fixedTime) && this.genLayerZoomer
/* 305 */       .equals(var0.genLayerZoomer) && this.infiniburn
/* 306 */       .equals(var0.infiniburn) && this.effects
/* 307 */       .equals(var0.effects));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DimensionManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */