/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.serialization.Codec;
/*     */ import java.util.List;
/*     */ import java.util.function.Function;
/*     */ import java.util.stream.Collectors;
/*     */ import java.util.stream.Stream;
/*     */ import java.util.stream.StreamSupport;
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
/*     */ public class ChunkProviderDebug
/*     */   extends ChunkGenerator
/*     */ {
/*  32 */   public static final Codec<ChunkProviderDebug> d = RegistryLookupCodec.<BiomeBase>a(IRegistry.ay).xmap(ChunkProviderDebug::new, ChunkProviderDebug::g).stable().codec(); private static final List<IBlockData> g;
/*     */   
/*     */   static {
/*  35 */     g = (List<IBlockData>)StreamSupport.stream(IRegistry.BLOCK.spliterator(), false).flatMap(var0 -> var0.getStates().a().stream()).collect(Collectors.toList());
/*  36 */   } private static final int h = MathHelper.f(MathHelper.c(g.size()));
/*  37 */   private static final int i = MathHelper.f(g.size() / h);
/*     */   
/*  39 */   protected static final IBlockData e = Blocks.AIR.getBlockData();
/*  40 */   protected static final IBlockData f = Blocks.BARRIER.getBlockData();
/*     */   
/*     */   private final IRegistry<BiomeBase> j;
/*     */   
/*     */   public ChunkProviderDebug(IRegistry<BiomeBase> var0) {
/*  45 */     super(new WorldChunkManagerHell(var0.d(Biomes.PLAINS)), new StructureSettings(false));
/*  46 */     this.j = var0;
/*     */   }
/*     */   
/*     */   public IRegistry<BiomeBase> g() {
/*  50 */     return this.j;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Codec<? extends ChunkGenerator> a() {
/*  55 */     return (Codec)d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildBase(RegionLimitedWorldAccess var0, IChunkAccess var1) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doCarving(long var0, BiomeManager var2, IChunkAccess var3, WorldGenStage.Features var4) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addDecorations(RegionLimitedWorldAccess var0, StructureManager var1) {
/*  73 */     BlockPosition.MutableBlockPosition var2 = new BlockPosition.MutableBlockPosition();
/*     */     
/*  75 */     int var3 = var0.a();
/*  76 */     int var4 = var0.b();
/*     */     
/*  78 */     for (int var5 = 0; var5 < 16; var5++) {
/*  79 */       for (int var6 = 0; var6 < 16; var6++) {
/*  80 */         int var7 = (var3 << 4) + var5;
/*  81 */         int var8 = (var4 << 4) + var6;
/*  82 */         var0.setTypeAndData(var2.d(var7, 60, var8), f, 2);
/*  83 */         IBlockData var9 = b(var7, var8);
/*  84 */         if (var9 != null) {
/*  85 */           var0.setTypeAndData(var2.d(var7, 70, var8), var9, 2);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildNoise(GeneratorAccess var0, StructureManager var1, IChunkAccess var2) {}
/*     */ 
/*     */   
/*     */   public int getBaseHeight(int var0, int var1, HeightMap.Type var2) {
/*  97 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockAccess a(int var0, int var1) {
/* 102 */     return new BlockColumn(new IBlockData[0]);
/*     */   }
/*     */   
/*     */   public static IBlockData b(int var0, int var1) {
/* 106 */     IBlockData var2 = e;
/*     */     
/* 108 */     if (var0 > 0 && var1 > 0 && var0 % 2 != 0 && var1 % 2 != 0) {
/* 109 */       var0 /= 2;
/* 110 */       var1 /= 2;
/*     */       
/* 112 */       if (var0 <= h && var1 <= i) {
/* 113 */         int var3 = MathHelper.a(var0 * h + var1);
/* 114 */         if (var3 < g.size()) {
/* 115 */           var2 = g.get(var3);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 120 */     return var2;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ChunkProviderDebug.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */