/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.serialization.Codec;
/*     */ import java.util.Arrays;
/*     */ import java.util.function.Function;
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
/*     */ public class ChunkProviderFlat
/*     */   extends ChunkGenerator
/*     */ {
/*  21 */   public static final Codec<ChunkProviderFlat> d = GeneratorSettingsFlat.a.fieldOf("settings").xmap(ChunkProviderFlat::new, ChunkProviderFlat::g).codec();
/*     */   
/*     */   private final GeneratorSettingsFlat e;
/*     */   
/*     */   public ChunkProviderFlat(GeneratorSettingsFlat var0) {
/*  26 */     super(new WorldChunkManagerHell(var0.c()), new WorldChunkManagerHell(var0.e()), var0.d(), 0L);
/*  27 */     this.e = var0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Codec<? extends ChunkGenerator> a() {
/*  32 */     return (Codec)d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GeneratorSettingsFlat g() {
/*  41 */     return this.e;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildBase(RegionLimitedWorldAccess var0, IChunkAccess var1) {}
/*     */ 
/*     */   
/*     */   public int getSpawnHeight() {
/*  50 */     IBlockData[] var0 = this.e.g();
/*  51 */     for (int var1 = 0; var1 < var0.length; var1++) {
/*  52 */       IBlockData var2 = (var0[var1] == null) ? Blocks.AIR.getBlockData() : var0[var1];
/*  53 */       if (!HeightMap.Type.MOTION_BLOCKING.e().test(var2)) {
/*  54 */         return var1 - 1;
/*     */       }
/*     */     } 
/*  57 */     return var0.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildNoise(GeneratorAccess var0, StructureManager var1, IChunkAccess var2) {
/*  64 */     IBlockData[] var3 = this.e.g();
/*     */     
/*  66 */     BlockPosition.MutableBlockPosition var4 = new BlockPosition.MutableBlockPosition();
/*  67 */     HeightMap var5 = var2.a(HeightMap.Type.OCEAN_FLOOR_WG);
/*  68 */     HeightMap var6 = var2.a(HeightMap.Type.WORLD_SURFACE_WG);
/*     */     
/*  70 */     for (int var7 = 0; var7 < var3.length; var7++) {
/*  71 */       IBlockData var8 = var3[var7];
/*  72 */       if (var8 != null)
/*     */       {
/*     */ 
/*     */         
/*  76 */         for (int var9 = 0; var9 < 16; var9++) {
/*  77 */           for (int var10 = 0; var10 < 16; var10++) {
/*  78 */             var2.setType(var4.d(var9, var7, var10), var8, false);
/*  79 */             var5.a(var9, var7, var10, var8);
/*  80 */             var6.a(var9, var7, var10, var8);
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getBaseHeight(int var0, int var1, HeightMap.Type var2) {
/*  88 */     IBlockData[] var3 = this.e.g();
/*  89 */     for (int var4 = var3.length - 1; var4 >= 0; var4--) {
/*  90 */       IBlockData var5 = var3[var4];
/*  91 */       if (var5 != null)
/*     */       {
/*     */         
/*  94 */         if (var2.e().test(var5))
/*  95 */           return var4 + 1; 
/*     */       }
/*     */     } 
/*  98 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockAccess a(int var0, int var1) {
/* 103 */     return new BlockColumn((IBlockData[])Arrays.<IBlockData>stream(this.e.g()).map(var0 -> (var0 == null) ? Blocks.AIR.getBlockData() : var0).toArray(var0 -> new IBlockData[var0]));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ChunkProviderFlat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */