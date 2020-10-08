/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.mojang.serialization.Codec;
/*     */ import java.util.BitSet;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.function.Function;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.mutable.MutableBoolean;
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
/*     */ public abstract class WorldGenCarverAbstract<C extends WorldGenCarverConfiguration>
/*     */ {
/*  28 */   public static final WorldGenCarverAbstract<WorldGenFeatureConfigurationChance> a = a("cave", new WorldGenCaves(WorldGenFeatureConfigurationChance.b, 256));
/*  29 */   public static final WorldGenCarverAbstract<WorldGenFeatureConfigurationChance> b = a("nether_cave", new WorldGenCavesHell(WorldGenFeatureConfigurationChance.b));
/*  30 */   public static final WorldGenCarverAbstract<WorldGenFeatureConfigurationChance> c = a("canyon", new WorldGenCanyon(WorldGenFeatureConfigurationChance.b));
/*  31 */   public static final WorldGenCarverAbstract<WorldGenFeatureConfigurationChance> d = a("underwater_canyon", new WorldGenCanyonOcean(WorldGenFeatureConfigurationChance.b));
/*  32 */   public static final WorldGenCarverAbstract<WorldGenFeatureConfigurationChance> e = a("underwater_cave", new WorldGenCavesOcean(WorldGenFeatureConfigurationChance.b));
/*     */   
/*  34 */   protected static final IBlockData f = Blocks.AIR.getBlockData();
/*  35 */   protected static final IBlockData g = Blocks.CAVE_AIR.getBlockData();
/*  36 */   protected static final Fluid h = FluidTypes.WATER.h();
/*  37 */   protected static final Fluid i = FluidTypes.LAVA.h();
/*     */   
/*     */   private static <C extends WorldGenCarverConfiguration, F extends WorldGenCarverAbstract<C>> F a(String var0, F var1) {
/*  40 */     return (F)IRegistry.<WorldGenCarverAbstract>a((IRegistry)IRegistry.CARVER, var0, (WorldGenCarverAbstract)var1);
/*     */   }
/*     */   
/*  43 */   protected Set<Block> j = (Set<Block>)ImmutableSet.of(Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE, Blocks.DIRT, Blocks.COARSE_DIRT, (Object[])new Block[] { Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.TERRACOTTA, Blocks.WHITE_TERRACOTTA, Blocks.ORANGE_TERRACOTTA, Blocks.MAGENTA_TERRACOTTA, Blocks.LIGHT_BLUE_TERRACOTTA, Blocks.YELLOW_TERRACOTTA, Blocks.LIME_TERRACOTTA, Blocks.PINK_TERRACOTTA, Blocks.GRAY_TERRACOTTA, Blocks.LIGHT_GRAY_TERRACOTTA, Blocks.CYAN_TERRACOTTA, Blocks.PURPLE_TERRACOTTA, Blocks.BLUE_TERRACOTTA, Blocks.BROWN_TERRACOTTA, Blocks.GREEN_TERRACOTTA, Blocks.RED_TERRACOTTA, Blocks.BLACK_TERRACOTTA, Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.MYCELIUM, Blocks.SNOW, Blocks.PACKED_ICE });
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
/*  76 */   protected Set<FluidType> k = (Set<FluidType>)ImmutableSet.of(FluidTypes.WATER);
/*     */   
/*     */   private final Codec<WorldGenCarverWrapper<C>> m;
/*     */   
/*     */   protected final int l;
/*     */ 
/*     */   
/*     */   public WorldGenCarverAbstract(Codec<C> var0, int var1) {
/*  84 */     this.l = var1;
/*  85 */     this.m = var0.fieldOf("config").xmap(this::a, WorldGenCarverWrapper::a).codec();
/*     */   }
/*     */   
/*     */   public WorldGenCarverWrapper<C> a(C var0) {
/*  89 */     return new WorldGenCarverWrapper<>(this, var0);
/*     */   }
/*     */   
/*     */   public Codec<WorldGenCarverWrapper<C>> c() {
/*  93 */     return this.m;
/*     */   }
/*     */   
/*     */   public int d() {
/*  97 */     return 4;
/*     */   }
/*     */   
/*     */   protected boolean a(IChunkAccess var0, Function<BlockPosition, BiomeBase> var1, long var2, int var4, int var5, int var6, double var7, double var9, double var11, double var13, double var15, BitSet var17) {
/* 101 */     Random var18 = new Random(var2 + var5 + var6);
/*     */     
/* 103 */     double var19 = (var5 * 16 + 8);
/* 104 */     double var21 = (var6 * 16 + 8);
/*     */     
/* 106 */     if (var7 < var19 - 16.0D - var13 * 2.0D || var11 < var21 - 16.0D - var13 * 2.0D || var7 > var19 + 16.0D + var13 * 2.0D || var11 > var21 + 16.0D + var13 * 2.0D) {
/* 107 */       return false;
/*     */     }
/*     */     
/* 110 */     int var23 = Math.max(MathHelper.floor(var7 - var13) - var5 * 16 - 1, 0);
/* 111 */     int var24 = Math.min(MathHelper.floor(var7 + var13) - var5 * 16 + 1, 16);
/*     */     
/* 113 */     int var25 = Math.max(MathHelper.floor(var9 - var15) - 1, 1);
/* 114 */     int var26 = Math.min(MathHelper.floor(var9 + var15) + 1, this.l - 8);
/*     */     
/* 116 */     int var27 = Math.max(MathHelper.floor(var11 - var13) - var6 * 16 - 1, 0);
/* 117 */     int var28 = Math.min(MathHelper.floor(var11 + var13) - var6 * 16 + 1, 16);
/*     */     
/* 119 */     if (a(var0, var5, var6, var23, var24, var25, var26, var27, var28)) {
/* 120 */       return false;
/*     */     }
/*     */     
/* 123 */     boolean var29 = false;
/* 124 */     BlockPosition.MutableBlockPosition var30 = new BlockPosition.MutableBlockPosition();
/* 125 */     BlockPosition.MutableBlockPosition var31 = new BlockPosition.MutableBlockPosition();
/* 126 */     BlockPosition.MutableBlockPosition var32 = new BlockPosition.MutableBlockPosition();
/*     */     
/* 128 */     for (int var33 = var23; var33 < var24; var33++) {
/* 129 */       int var34 = var33 + var5 * 16;
/* 130 */       double var35 = (var34 + 0.5D - var7) / var13;
/* 131 */       for (int var37 = var27; var37 < var28; var37++) {
/* 132 */         int var38 = var37 + var6 * 16;
/* 133 */         double var39 = (var38 + 0.5D - var11) / var13;
/* 134 */         if (var35 * var35 + var39 * var39 < 1.0D) {
/*     */ 
/*     */ 
/*     */           
/* 138 */           MutableBoolean var41 = new MutableBoolean(false);
/*     */           
/* 140 */           for (int var42 = var26; var42 > var25; var42--) {
/* 141 */             double var43 = (var42 - 0.5D - var9) / var15;
/* 142 */             if (!a(var35, var43, var39, var42))
/*     */             {
/*     */ 
/*     */               
/* 146 */               var29 |= a(var0, var1, var17, var18, var30, var31, var32, var4, var5, var6, var34, var38, var33, var42, var37, var41); } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 151 */     return var29;
/*     */   }
/*     */   
/*     */   protected boolean a(IChunkAccess var0, Function<BlockPosition, BiomeBase> var1, BitSet var2, Random var3, BlockPosition.MutableBlockPosition var4, BlockPosition.MutableBlockPosition var5, BlockPosition.MutableBlockPosition var6, int var7, int var8, int var9, int var10, int var11, int var12, int var13, int var14, MutableBoolean var15) {
/* 155 */     int var16 = var12 | var14 << 4 | var13 << 8;
/* 156 */     if (var2.get(var16)) {
/* 157 */       return false;
/*     */     }
/* 159 */     var2.set(var16);
/*     */     
/* 161 */     var4.d(var10, var13, var11);
/*     */     
/* 163 */     IBlockData var17 = var0.getType(var4);
/*     */     
/* 165 */     IBlockData var18 = var0.getType(var5.a(var4, EnumDirection.UP));
/* 166 */     if (var17.a(Blocks.GRASS_BLOCK) || var17.a(Blocks.MYCELIUM)) {
/* 167 */       var15.setTrue();
/*     */     }
/* 169 */     if (!a(var17, var18)) {
/* 170 */       return false;
/*     */     }
/*     */     
/* 173 */     if (var13 < 11) {
/* 174 */       var0.setType(var4, i.getBlockData(), false);
/*     */     } else {
/* 176 */       var0.setType(var4, g, false);
/*     */ 
/*     */       
/* 179 */       if (var15.isTrue()) {
/* 180 */         var6.a(var4, EnumDirection.DOWN);
/* 181 */         if (var0.getType(var6).a(Blocks.DIRT)) {
/* 182 */           var0.setType(var6, ((BiomeBase)var1.apply(var4)).e().e().a(), false);
/*     */         }
/*     */       } 
/*     */     } 
/* 186 */     return true;
/*     */   }
/*     */   
/*     */   public abstract boolean a(IChunkAccess paramIChunkAccess, Function<BlockPosition, BiomeBase> paramFunction, Random paramRandom, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, BitSet paramBitSet, C paramC);
/*     */   
/*     */   public abstract boolean a(Random paramRandom, int paramInt1, int paramInt2, C paramC);
/*     */   
/*     */   protected boolean a(IBlockData var0) {
/* 194 */     return this.j.contains(var0.getBlock());
/*     */   }
/*     */   
/*     */   protected boolean a(IBlockData var0, IBlockData var1) {
/* 198 */     return (a(var0) || ((var0.a(Blocks.SAND) || var0.a(Blocks.GRAVEL)) && !var1.getFluid().a(TagsFluid.WATER)));
/*     */   }
/*     */   
/*     */   protected boolean a(IChunkAccess var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
/* 202 */     BlockPosition.MutableBlockPosition var9 = new BlockPosition.MutableBlockPosition();
/*     */     
/* 204 */     for (int var10 = var3; var10 < var4; var10++) {
/* 205 */       for (int var11 = var7; var11 < var8; var11++) {
/* 206 */         for (int var12 = var5 - 1; var12 <= var6 + 1; var12++) {
/* 207 */           if (this.k.contains(var0.getFluid(var9.d(var10 + var1 * 16, var12, var11 + var2 * 16)).getType())) {
/* 208 */             return true;
/*     */           }
/*     */ 
/*     */           
/* 212 */           if (var12 != var6 + 1 && !a(var3, var4, var7, var8, var10, var11)) {
/* 213 */             var12 = var6;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 218 */     return false;
/*     */   }
/*     */   
/*     */   private boolean a(int var0, int var1, int var2, int var3, int var4, int var5) {
/* 222 */     return (var4 == var0 || var4 == var1 - 1 || var5 == var2 || var5 == var3 - 1);
/*     */   }
/*     */   
/*     */   protected boolean a(int var0, int var1, double var2, double var4, int var6, int var7, float var8) {
/* 226 */     double var9 = (var0 * 16 + 8);
/* 227 */     double var11 = (var1 * 16 + 8);
/*     */     
/* 229 */     double var13 = var2 - var9;
/* 230 */     double var15 = var4 - var11;
/* 231 */     double var17 = (var7 - var6);
/* 232 */     double var19 = (var8 + 2.0F + 16.0F);
/*     */     
/* 234 */     return (var13 * var13 + var15 * var15 - var17 * var17 <= var19 * var19);
/*     */   }
/*     */   
/*     */   protected abstract boolean a(double paramDouble1, double paramDouble2, double paramDouble3, int paramInt);
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenCarverAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */