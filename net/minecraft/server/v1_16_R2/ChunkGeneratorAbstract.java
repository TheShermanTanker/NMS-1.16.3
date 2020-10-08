/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.mojang.datafixers.kinds.App;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectListIterator;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.IntStream;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public final class ChunkGeneratorAbstract extends ChunkGenerator {
/*     */   public static final Codec<ChunkGeneratorAbstract> d;
/*     */   
/*     */   static {
/*  18 */     d = RecordCodecBuilder.create(instance -> instance.group((App)WorldChunkManager.a.fieldOf("biome_source").forGetter(()), (App)Codec.LONG.fieldOf("seed").stable().forGetter(()), (App)GeneratorSettingBase.b.fieldOf("settings").forGetter(())).apply((Applicative)instance, instance.stable(ChunkGeneratorAbstract::new)));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  27 */     i = SystemUtils.<float[]>a(new float[13824], afloat -> {
/*     */           for (int i = 0; i < 24; i++) {
/*     */             for (int j = 0; j < 24; j++) {
/*     */               for (int k = 0; k < 24; k++) {
/*     */                 afloat[i * 24 * 24 + j * 24 + k] = (float)b(j - 12, k - 12, i - 12);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         });
/*     */     
/*  37 */     j = SystemUtils.<float[]>a(new float[25], afloat -> {
/*     */           for (int i = -2; i <= 2; i++) {
/*     */             for (int j = -2; j <= 2; j++) {
/*     */               float f = 10.0F / MathHelper.c((i * i + j * j) + 0.2F);
/*     */               afloat[i + 2 + (j + 2) * 5] = f;
/*     */             } 
/*     */           } 
/*     */         });
/*     */   }
/*     */   private static final float[] i; private static final float[] j;
/*  47 */   private static final IBlockData k = Blocks.AIR.getBlockData();
/*     */   private final int l;
/*     */   private final int m;
/*     */   private final int n;
/*     */   private final int o;
/*     */   private final int p;
/*     */   protected final SeededRandom e;
/*     */   private final NoiseGeneratorOctaves q;
/*     */   private final NoiseGeneratorOctaves r;
/*     */   private final NoiseGeneratorOctaves s;
/*     */   private final NoiseGenerator t;
/*     */   private final NoiseGeneratorOctaves u;
/*     */   @Nullable
/*     */   private final NoiseGenerator3Handler v;
/*     */   protected final IBlockData f;
/*     */   protected final IBlockData g;
/*     */   private final long w;
/*     */   protected final Supplier<GeneratorSettingBase> h;
/*     */   private final int x;
/*     */   
/*     */   public ChunkGeneratorAbstract(WorldChunkManager worldchunkmanager, long i, Supplier<GeneratorSettingBase> supplier) {
/*  68 */     this(worldchunkmanager, worldchunkmanager, i, supplier);
/*     */   }
/*     */   
/*     */   private ChunkGeneratorAbstract(WorldChunkManager worldchunkmanager, WorldChunkManager worldchunkmanager1, long i, Supplier<GeneratorSettingBase> supplier) {
/*  72 */     super(worldchunkmanager, worldchunkmanager1, ((GeneratorSettingBase)supplier.get()).a(), i);
/*  73 */     this.w = i;
/*  74 */     GeneratorSettingBase generatorsettingbase = supplier.get();
/*     */     
/*  76 */     this.h = supplier;
/*  77 */     NoiseSettings noisesettings = generatorsettingbase.b();
/*     */     
/*  79 */     this.x = noisesettings.a();
/*  80 */     this.l = noisesettings.f() * 4;
/*  81 */     this.m = noisesettings.e() * 4;
/*  82 */     this.f = generatorsettingbase.c();
/*  83 */     this.g = generatorsettingbase.d();
/*  84 */     this.n = 16 / this.m;
/*  85 */     this.o = noisesettings.a() / this.l;
/*  86 */     this.p = 16 / this.m;
/*  87 */     this.e = new SeededRandom(i);
/*  88 */     this.q = new NoiseGeneratorOctaves(this.e, IntStream.rangeClosed(-15, 0));
/*  89 */     this.r = new NoiseGeneratorOctaves(this.e, IntStream.rangeClosed(-15, 0));
/*  90 */     this.s = new NoiseGeneratorOctaves(this.e, IntStream.rangeClosed(-7, 0));
/*  91 */     this.t = noisesettings.i() ? new NoiseGenerator3(this.e, IntStream.rangeClosed(-3, 0)) : new NoiseGeneratorOctaves(this.e, IntStream.rangeClosed(-3, 0));
/*  92 */     this.e.a(2620);
/*  93 */     this.u = new NoiseGeneratorOctaves(this.e, IntStream.rangeClosed(-15, 0));
/*  94 */     if (noisesettings.k()) {
/*  95 */       SeededRandom seededrandom = new SeededRandom(i);
/*     */       
/*  97 */       seededrandom.a(17292);
/*  98 */       this.v = new NoiseGenerator3Handler(seededrandom);
/*     */     } else {
/* 100 */       this.v = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Codec<? extends ChunkGenerator> a() {
/* 107 */     return (Codec)d;
/*     */   }
/*     */   
/*     */   public boolean a(long i, ResourceKey<GeneratorSettingBase> resourcekey) {
/* 111 */     return (this.w == i && ((GeneratorSettingBase)this.h.get()).a(resourcekey));
/*     */   }
/*     */   
/*     */   private double a(int i, int j, int k, double d0, double d1, double d2, double d3) {
/* 115 */     double d4 = 0.0D;
/* 116 */     double d5 = 0.0D;
/* 117 */     double d6 = 0.0D;
/* 118 */     boolean flag = true;
/* 119 */     double d7 = 1.0D;
/*     */     
/* 121 */     for (int l = 0; l < 16; l++) {
/* 122 */       double d8 = NoiseGeneratorOctaves.a(i * d0 * d7);
/* 123 */       double d9 = NoiseGeneratorOctaves.a(j * d1 * d7);
/* 124 */       double d10 = NoiseGeneratorOctaves.a(k * d0 * d7);
/* 125 */       double d11 = d1 * d7;
/* 126 */       NoiseGeneratorPerlin noisegeneratorperlin = this.q.a(l);
/*     */       
/* 128 */       if (noisegeneratorperlin != null) {
/* 129 */         d4 += noisegeneratorperlin.a(d8, d9, d10, d11, j * d11) / d7;
/*     */       }
/*     */       
/* 132 */       NoiseGeneratorPerlin noisegeneratorperlin1 = this.r.a(l);
/*     */       
/* 134 */       if (noisegeneratorperlin1 != null) {
/* 135 */         d5 += noisegeneratorperlin1.a(d8, d9, d10, d11, j * d11) / d7;
/*     */       }
/*     */       
/* 138 */       if (l < 8) {
/* 139 */         NoiseGeneratorPerlin noisegeneratorperlin2 = this.s.a(l);
/*     */         
/* 141 */         if (noisegeneratorperlin2 != null) {
/* 142 */           d6 += noisegeneratorperlin2.a(NoiseGeneratorOctaves.a(i * d2 * d7), NoiseGeneratorOctaves.a(j * d3 * d7), NoiseGeneratorOctaves.a(k * d2 * d7), d3 * d7, j * d3 * d7) / d7;
/*     */         }
/*     */       } 
/*     */       
/* 146 */       d7 /= 2.0D;
/*     */     } 
/*     */     
/* 149 */     return MathHelper.b(d4 / 512.0D, d5 / 512.0D, (d6 / 10.0D + 1.0D) / 2.0D);
/*     */   }
/*     */   
/*     */   private double[] b(int i, int j) {
/* 153 */     double[] adouble = new double[this.o + 1];
/*     */     
/* 155 */     a(adouble, i, j);
/* 156 */     return adouble;
/*     */   }
/*     */   private void a(double[] adouble, int i, int j) {
/*     */     double d0, d1;
/* 160 */     NoiseSettings noisesettings = ((GeneratorSettingBase)this.h.get()).b();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 166 */     if (this.v != null) {
/* 167 */       d0 = (WorldChunkManagerTheEnd.a(this.v, i, j) - 8.0F);
/* 168 */       if (d0 > 0.0D) {
/* 169 */         d1 = 0.25D;
/*     */       } else {
/* 171 */         d1 = 1.0D;
/*     */       } 
/*     */     } else {
/* 174 */       float f = 0.0F;
/* 175 */       float f1 = 0.0F;
/* 176 */       float f2 = 0.0F;
/* 177 */       boolean flag = true;
/* 178 */       int k = getSeaLevel();
/* 179 */       float f3 = this.b.getBiome(i, k, j).h();
/*     */       
/* 181 */       for (int l = -2; l <= 2; l++) {
/* 182 */         for (int i1 = -2; i1 <= 2; i1++) {
/* 183 */           float f6, f7; BiomeBase biomebase = this.b.getBiome(i + l, k, j + i1);
/* 184 */           float f4 = biomebase.h();
/* 185 */           float f5 = biomebase.j();
/*     */ 
/*     */ 
/*     */           
/* 189 */           if (noisesettings.l() && f4 > 0.0F) {
/* 190 */             f6 = 1.0F + f4 * 2.0F;
/* 191 */             f7 = 1.0F + f5 * 4.0F;
/*     */           } else {
/* 193 */             f6 = f4;
/* 194 */             f7 = f5;
/*     */           } 
/*     */           
/* 197 */           if (f6 < -1.8F) {
/* 198 */             f6 = -1.8F;
/*     */           }
/*     */ 
/*     */           
/* 202 */           float f8 = (f4 > f3) ? 0.5F : 1.0F;
/* 203 */           float f9 = f8 * ChunkGeneratorAbstract.j[l + 2 + (i1 + 2) * 5] / (f6 + 2.0F);
/*     */           
/* 205 */           f += f7 * f9;
/* 206 */           f1 += f6 * f9;
/* 207 */           f2 += f9;
/*     */         } 
/*     */       } 
/*     */       
/* 211 */       float f10 = f1 / f2;
/* 212 */       float f11 = f / f2;
/*     */       
/* 214 */       double d15 = (f10 * 0.5F - 0.125F);
/* 215 */       double d16 = (f11 * 0.9F + 0.1F);
/* 216 */       d0 = d15 * 0.265625D;
/* 217 */       d1 = 96.0D / d16;
/*     */     } 
/*     */     
/* 220 */     double d4 = 684.412D * noisesettings.b().a();
/* 221 */     double d5 = 684.412D * noisesettings.b().b();
/* 222 */     double d6 = d4 / noisesettings.b().c();
/* 223 */     double d7 = d5 / noisesettings.b().d();
/*     */     
/* 225 */     double d2 = noisesettings.c().a();
/* 226 */     double d3 = noisesettings.c().b();
/* 227 */     double d8 = noisesettings.c().c();
/* 228 */     double d9 = noisesettings.d().a();
/* 229 */     double d10 = noisesettings.d().b();
/* 230 */     double d11 = noisesettings.d().c();
/* 231 */     double d12 = noisesettings.j() ? c(i, j) : 0.0D;
/* 232 */     double d13 = noisesettings.g();
/* 233 */     double d14 = noisesettings.h();
/*     */     
/* 235 */     for (int j1 = 0; j1 <= this.o; j1++) {
/* 236 */       double d15 = a(i, j1, j, d4, d5, d6, d7);
/* 237 */       double d16 = 1.0D - j1 * 2.0D / this.o + d12;
/* 238 */       double d17 = d16 * d13 + d14;
/* 239 */       double d18 = (d17 + d0) * d1;
/*     */       
/* 241 */       if (d18 > 0.0D) {
/* 242 */         d15 += d18 * 4.0D;
/*     */       } else {
/* 244 */         d15 += d18;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 249 */       if (d3 > 0.0D) {
/* 250 */         double d19 = ((this.o - j1) - d8) / d3;
/* 251 */         d15 = MathHelper.b(d2, d15, d19);
/*     */       } 
/*     */       
/* 254 */       if (d10 > 0.0D) {
/* 255 */         double d19 = (j1 - d11) / d10;
/* 256 */         d15 = MathHelper.b(d9, d15, d19);
/*     */       } 
/*     */       
/* 259 */       adouble[j1] = d15;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private double c(int i, int j) {
/* 265 */     double d1, d0 = this.u.a((i * 200), 10.0D, (j * 200), 1.0D, 0.0D, true);
/*     */ 
/*     */     
/* 268 */     if (d0 < 0.0D) {
/* 269 */       d1 = -d0 * 0.3D;
/*     */     } else {
/* 271 */       d1 = d0;
/*     */     } 
/*     */     
/* 274 */     double d2 = d1 * 24.575625D - 2.0D;
/*     */     
/* 276 */     return (d2 < 0.0D) ? (d2 * 0.009486607142857142D) : (Math.min(d2, 1.0D) * 0.006640625D);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBaseHeight(int i, int j, HeightMap.Type heightmap_type) {
/* 281 */     return a(i, j, (IBlockData[])null, heightmap_type.e());
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockAccess a(int i, int j) {
/* 286 */     IBlockData[] aiblockdata = new IBlockData[this.o * this.l];
/*     */     
/* 288 */     a(i, j, aiblockdata, (Predicate<IBlockData>)null);
/* 289 */     return new BlockColumn(aiblockdata);
/*     */   }
/*     */   
/*     */   private int a(int i, int j, @Nullable IBlockData[] aiblockdata, @Nullable Predicate<IBlockData> predicate) {
/* 293 */     int k = Math.floorDiv(i, this.m);
/* 294 */     int l = Math.floorDiv(j, this.m);
/* 295 */     int i1 = Math.floorMod(i, this.m);
/* 296 */     int j1 = Math.floorMod(j, this.m);
/* 297 */     double d0 = i1 / this.m;
/* 298 */     double d1 = j1 / this.m;
/* 299 */     double[][] adouble = { b(k, l), b(k, l + 1), b(k + 1, l), b(k + 1, l + 1) };
/*     */     
/* 301 */     for (int k1 = this.o - 1; k1 >= 0; k1--) {
/* 302 */       double d2 = adouble[0][k1];
/* 303 */       double d3 = adouble[1][k1];
/* 304 */       double d4 = adouble[2][k1];
/* 305 */       double d5 = adouble[3][k1];
/* 306 */       double d6 = adouble[0][k1 + 1];
/* 307 */       double d7 = adouble[1][k1 + 1];
/* 308 */       double d8 = adouble[2][k1 + 1];
/* 309 */       double d9 = adouble[3][k1 + 1];
/*     */       
/* 311 */       for (int l1 = this.l - 1; l1 >= 0; l1--) {
/* 312 */         double d10 = l1 / this.l;
/* 313 */         double d11 = MathHelper.a(d10, d0, d1, d2, d6, d4, d8, d3, d7, d5, d9);
/* 314 */         int i2 = k1 * this.l + l1;
/* 315 */         IBlockData iblockdata = a(d11, i2);
/*     */         
/* 317 */         if (aiblockdata != null) {
/* 318 */           aiblockdata[i2] = iblockdata;
/*     */         }
/*     */         
/* 321 */         if (predicate != null && predicate.test(iblockdata)) {
/* 322 */           return i2 + 1;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 327 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected IBlockData a(double d0, int i) {
/*     */     IBlockData iblockdata;
/* 333 */     if (d0 > 0.0D) {
/* 334 */       iblockdata = this.f;
/* 335 */     } else if (i < getSeaLevel()) {
/* 336 */       iblockdata = this.g;
/*     */     } else {
/* 338 */       iblockdata = k;
/*     */     } 
/*     */     
/* 341 */     return iblockdata;
/*     */   }
/*     */ 
/*     */   
/*     */   public void buildBase(RegionLimitedWorldAccess regionlimitedworldaccess, IChunkAccess ichunkaccess) {
/* 346 */     ChunkCoordIntPair chunkcoordintpair = ichunkaccess.getPos();
/* 347 */     int i = chunkcoordintpair.x;
/* 348 */     int j = chunkcoordintpair.z;
/* 349 */     SeededRandom seededrandom = new SeededRandom();
/*     */     
/* 351 */     seededrandom.a(i, j);
/* 352 */     ChunkCoordIntPair chunkcoordintpair1 = ichunkaccess.getPos();
/* 353 */     int k = chunkcoordintpair1.d();
/* 354 */     int l = chunkcoordintpair1.e();
/* 355 */     double d0 = 0.0625D;
/* 356 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/*     */     
/* 358 */     for (int i1 = 0; i1 < 16; i1++) {
/* 359 */       for (int j1 = 0; j1 < 16; j1++) {
/* 360 */         int k1 = k + i1;
/* 361 */         int l1 = l + j1;
/* 362 */         int i2 = ichunkaccess.getHighestBlock(HeightMap.Type.WORLD_SURFACE_WG, i1, j1) + 1;
/* 363 */         double d1 = this.t.a(k1 * 0.0625D, l1 * 0.0625D, 0.0625D, i1 * 0.0625D) * 15.0D;
/*     */         
/* 365 */         regionlimitedworldaccess.getBiome(blockposition_mutableblockposition.d(k + i1, i2, l + j1)).a(seededrandom, ichunkaccess, k1, l1, i2, d1, this.f, this.g, getSeaLevel(), regionlimitedworldaccess.getSeed());
/*     */       } 
/*     */     } 
/*     */     
/* 369 */     a(ichunkaccess, seededrandom);
/*     */   }
/*     */   
/*     */   private void a(IChunkAccess ichunkaccess, Random random) {
/* 373 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/* 374 */     int i = ichunkaccess.getPos().d();
/* 375 */     int j = ichunkaccess.getPos().e();
/* 376 */     GeneratorSettingBase generatorsettingbase = this.h.get();
/* 377 */     int k = generatorsettingbase.f(), floorHeight = k;
/* 378 */     int l = this.x - 1 - generatorsettingbase.e(), roofHeight = l;
/* 379 */     boolean flag = true;
/* 380 */     boolean flag1 = (l + 4 >= 0 && l < this.x);
/* 381 */     boolean flag2 = (k + 4 >= 0 && k < this.x);
/*     */     
/* 383 */     if (flag1 || flag2) {
/* 384 */       Iterator<BlockPosition> iterator = BlockPosition.b(i, 0, j, i + 15, 0, j + 15).iterator();
/*     */       
/* 386 */       while (iterator.hasNext()) {
/* 387 */         BlockPosition blockposition = iterator.next();
/*     */ 
/*     */         
/* 390 */         if (flag1) {
/* 391 */           for (int i1 = 0; i1 < 5; i1++) {
/* 392 */             if (i1 <= (ichunkaccess.generateFlatBedrock() ? roofHeight : random.nextInt(5))) {
/* 393 */               ichunkaccess.setType(blockposition_mutableblockposition.d(blockposition.getX(), l - i1, blockposition.getZ()), Blocks.BEDROCK.getBlockData(), false);
/*     */             }
/*     */           } 
/*     */         }
/*     */         
/* 398 */         if (flag2) {
/* 399 */           for (int i1 = 4; i1 >= 0; i1--) {
/* 400 */             if (i1 <= (ichunkaccess.generateFlatBedrock() ? floorHeight : random.nextInt(5))) {
/* 401 */               ichunkaccess.setType(blockposition_mutableblockposition.d(blockposition.getX(), k + i1, blockposition.getZ()), Blocks.BEDROCK.getBlockData(), false);
/*     */             }
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildNoise(GeneratorAccess generatoraccess, StructureManager structuremanager, IChunkAccess ichunkaccess) {
/* 412 */     ObjectArrayList objectArrayList1 = new ObjectArrayList(10);
/* 413 */     ObjectArrayList objectArrayList2 = new ObjectArrayList(32);
/* 414 */     ChunkCoordIntPair chunkcoordintpair = ichunkaccess.getPos();
/* 415 */     int i = chunkcoordintpair.x;
/* 416 */     int j = chunkcoordintpair.z;
/* 417 */     int k = i << 4;
/* 418 */     int l = j << 4;
/* 419 */     Iterator<StructureGenerator<?>> iterator = StructureGenerator.t.iterator();
/*     */     
/* 421 */     while (iterator.hasNext()) {
/* 422 */       StructureGenerator<?> structuregenerator = iterator.next();
/*     */       
/* 424 */       for (StructureStart<?> structurestart : structuremanager.getFeatureStarts(SectionPosition.a(chunkcoordintpair, 0), structuregenerator)) {
/* 425 */         Iterator<StructurePiece> iterator1 = structurestart.d().iterator();
/*     */         
/* 427 */         while (iterator1.hasNext()) {
/* 428 */           StructurePiece structurepiece = iterator1.next();
/*     */           
/* 430 */           if (structurepiece.a(chunkcoordintpair, 12)) {
/* 431 */             if (structurepiece instanceof WorldGenFeaturePillagerOutpostPoolPiece) {
/* 432 */               WorldGenFeaturePillagerOutpostPoolPiece worldgenfeaturepillageroutpostpoolpiece = (WorldGenFeaturePillagerOutpostPoolPiece)structurepiece;
/* 433 */               WorldGenFeatureDefinedStructurePoolTemplate.Matching worldgenfeaturedefinedstructurepooltemplate_matching = worldgenfeaturepillageroutpostpoolpiece.b().e();
/*     */               
/* 435 */               if (worldgenfeaturedefinedstructurepooltemplate_matching == WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID) {
/* 436 */                 objectArrayList1.add(worldgenfeaturepillageroutpostpoolpiece);
/*     */               }
/*     */               
/* 439 */               Iterator<WorldGenFeatureDefinedStructureJigsawJunction> iterator2 = worldgenfeaturepillageroutpostpoolpiece.e().iterator();
/*     */               
/* 441 */               while (iterator2.hasNext()) {
/* 442 */                 WorldGenFeatureDefinedStructureJigsawJunction worldgenfeaturedefinedstructurejigsawjunction = iterator2.next();
/* 443 */                 int m = worldgenfeaturedefinedstructurejigsawjunction.a();
/* 444 */                 int n = worldgenfeaturedefinedstructurejigsawjunction.c();
/*     */                 
/* 446 */                 if (m > k - 12 && n > l - 12 && m < k + 15 + 12 && n < l + 15 + 12)
/* 447 */                   objectArrayList2.add(worldgenfeaturedefinedstructurejigsawjunction); 
/*     */               } 
/*     */               continue;
/*     */             } 
/* 451 */             objectArrayList1.add(structurepiece);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 459 */     double[][][] adouble = new double[2][this.p + 1][this.o + 1];
/*     */     
/* 461 */     for (int i1 = 0; i1 < this.p + 1; i1++) {
/* 462 */       adouble[0][i1] = new double[this.o + 1];
/* 463 */       a(adouble[0][i1], i * this.n, j * this.p + i1);
/* 464 */       adouble[1][i1] = new double[this.o + 1];
/*     */     } 
/*     */     
/* 467 */     ProtoChunk protochunk = (ProtoChunk)ichunkaccess;
/* 468 */     HeightMap heightmap = protochunk.a(HeightMap.Type.OCEAN_FLOOR_WG);
/* 469 */     HeightMap heightmap1 = protochunk.a(HeightMap.Type.WORLD_SURFACE_WG);
/* 470 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/* 471 */     ObjectListIterator<StructurePiece> objectlistiterator = objectArrayList1.iterator();
/* 472 */     ObjectListIterator<WorldGenFeatureDefinedStructureJigsawJunction> objectlistiterator1 = objectArrayList2.iterator();
/*     */     
/* 474 */     for (int j1 = 0; j1 < this.n; j1++) {
/*     */       int k1;
/*     */       
/* 477 */       for (k1 = 0; k1 < this.p + 1; k1++) {
/* 478 */         a(adouble[1][k1], i * this.n + j1 + 1, j * this.p + k1);
/*     */       }
/*     */       
/* 481 */       for (k1 = 0; k1 < this.p; k1++) {
/* 482 */         ChunkSection chunksection = protochunk.a(15);
/*     */         
/* 484 */         chunksection.a();
/*     */         
/* 486 */         for (int l1 = this.o - 1; l1 >= 0; l1--) {
/* 487 */           double d0 = adouble[0][k1][l1];
/* 488 */           double d1 = adouble[0][k1 + 1][l1];
/* 489 */           double d2 = adouble[1][k1][l1];
/* 490 */           double d3 = adouble[1][k1 + 1][l1];
/* 491 */           double d4 = adouble[0][k1][l1 + 1];
/* 492 */           double d5 = adouble[0][k1 + 1][l1 + 1];
/* 493 */           double d6 = adouble[1][k1][l1 + 1];
/* 494 */           double d7 = adouble[1][k1 + 1][l1 + 1];
/*     */           
/* 496 */           for (int i2 = this.l - 1; i2 >= 0; i2--) {
/* 497 */             int j2 = l1 * this.l + i2;
/* 498 */             int k2 = j2 & 0xF;
/* 499 */             int l2 = j2 >> 4;
/*     */             
/* 501 */             if (chunksection.getYPosition() >> 4 != l2) {
/* 502 */               chunksection.b();
/* 503 */               chunksection = protochunk.a(l2);
/* 504 */               chunksection.a();
/*     */             } 
/*     */             
/* 507 */             double d8 = i2 / this.l;
/* 508 */             double d9 = MathHelper.d(d8, d0, d4);
/* 509 */             double d10 = MathHelper.d(d8, d2, d6);
/* 510 */             double d11 = MathHelper.d(d8, d1, d5);
/* 511 */             double d12 = MathHelper.d(d8, d3, d7);
/*     */             
/* 513 */             for (int i3 = 0; i3 < this.m; i3++) {
/* 514 */               int j3 = k + j1 * this.m + i3;
/* 515 */               int k3 = j3 & 0xF;
/* 516 */               double d13 = i3 / this.m;
/* 517 */               double d14 = MathHelper.d(d13, d9, d10);
/* 518 */               double d15 = MathHelper.d(d13, d11, d12);
/*     */               
/* 520 */               for (int l3 = 0; l3 < this.m; l3++) {
/* 521 */                 int i4 = l + k1 * this.m + l3;
/* 522 */                 int j4 = i4 & 0xF;
/* 523 */                 double d16 = l3 / this.m;
/* 524 */                 double d17 = MathHelper.d(d16, d14, d15);
/* 525 */                 double d18 = MathHelper.a(d17 / 200.0D, -1.0D, 1.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 531 */                 for (d18 = d18 / 2.0D - d18 * d18 * d18 / 24.0D; objectlistiterator.hasNext(); d18 += a(k4, l4, i5) * 0.8D) {
/* 532 */                   StructurePiece structurepiece = (StructurePiece)objectlistiterator.next();
/* 533 */                   StructureBoundingBox structureboundingbox = structurepiece.g();
/*     */                   
/* 535 */                   int k4 = Math.max(0, Math.max(structureboundingbox.a - j3, j3 - structureboundingbox.d));
/* 536 */                   int l4 = j2 - structureboundingbox.b + ((structurepiece instanceof WorldGenFeaturePillagerOutpostPoolPiece) ? ((WorldGenFeaturePillagerOutpostPoolPiece)structurepiece).d() : 0);
/* 537 */                   int i5 = Math.max(0, Math.max(structureboundingbox.c - i4, i4 - structureboundingbox.f));
/*     */                 } 
/*     */                 
/* 540 */                 objectlistiterator.back(objectArrayList1.size());
/*     */                 
/* 542 */                 while (objectlistiterator1.hasNext()) {
/* 543 */                   WorldGenFeatureDefinedStructureJigsawJunction worldgenfeaturedefinedstructurejigsawjunction = (WorldGenFeatureDefinedStructureJigsawJunction)objectlistiterator1.next();
/* 544 */                   int j5 = j3 - worldgenfeaturedefinedstructurejigsawjunction.a();
/*     */                   
/* 546 */                   int k4 = j2 - worldgenfeaturedefinedstructurejigsawjunction.b();
/* 547 */                   int l4 = i4 - worldgenfeaturedefinedstructurejigsawjunction.c();
/* 548 */                   d18 += a(j5, k4, l4) * 0.4D;
/*     */                 } 
/*     */                 
/* 551 */                 objectlistiterator1.back(objectArrayList2.size());
/* 552 */                 IBlockData iblockdata = a(d18, j2);
/*     */                 
/* 554 */                 if (iblockdata != ChunkGeneratorAbstract.k) {
/* 555 */                   if (iblockdata.f() != 0) {
/* 556 */                     blockposition_mutableblockposition.d(j3, j2, i4);
/* 557 */                     protochunk.k(blockposition_mutableblockposition);
/*     */                   } 
/*     */                   
/* 560 */                   chunksection.setType(k3, k2, j4, iblockdata, false);
/* 561 */                   heightmap.a(k3, j2, j4, iblockdata);
/* 562 */                   heightmap1.a(k3, j2, j4, iblockdata);
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 569 */         chunksection.b();
/*     */       } 
/*     */       
/* 572 */       double[][] adouble1 = adouble[0];
/*     */       
/* 574 */       adouble[0] = adouble[1];
/* 575 */       adouble[1] = adouble1;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static double a(int i, int j, int k) {
/* 581 */     int l = i + 12;
/* 582 */     int i1 = j + 12;
/* 583 */     int j1 = k + 12;
/*     */     
/* 585 */     return (l >= 0 && l < 24) ? ((i1 >= 0 && i1 < 24) ? ((j1 >= 0 && j1 < 24) ? ChunkGeneratorAbstract.i[j1 * 24 * 24 + l * 24 + i1] : 0.0D) : 0.0D) : 0.0D;
/*     */   }
/*     */   
/*     */   private static double b(int i, int j, int k) {
/* 589 */     double d0 = (i * i + k * k);
/* 590 */     double d1 = j + 0.5D;
/* 591 */     double d2 = d1 * d1;
/* 592 */     double d3 = Math.pow(Math.E, -(d2 / 16.0D + d0 / 16.0D));
/* 593 */     double d4 = -d1 * MathHelper.i(d2 / 2.0D + d0 / 2.0D) / 2.0D;
/*     */     
/* 595 */     return d4 * d3;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getGenerationDepth() {
/* 600 */     return this.x;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSeaLevel() {
/* 605 */     return ((GeneratorSettingBase)this.h.get()).g();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<BiomeSettingsMobs.c> getMobsFor(BiomeBase biomebase, StructureManager structuremanager, EnumCreatureType enumcreaturetype, BlockPosition blockposition) {
/* 610 */     if (structuremanager.a(blockposition, true, StructureGenerator.SWAMP_HUT).e()) {
/* 611 */       if (enumcreaturetype == EnumCreatureType.MONSTER) {
/* 612 */         return StructureGenerator.SWAMP_HUT.c();
/*     */       }
/*     */       
/* 615 */       if (enumcreaturetype == EnumCreatureType.CREATURE) {
/* 616 */         return StructureGenerator.SWAMP_HUT.j();
/*     */       }
/*     */     } 
/*     */     
/* 620 */     if (enumcreaturetype == EnumCreatureType.MONSTER) {
/* 621 */       if (structuremanager.a(blockposition, false, StructureGenerator.PILLAGER_OUTPOST).e()) {
/* 622 */         return StructureGenerator.PILLAGER_OUTPOST.c();
/*     */       }
/*     */       
/* 625 */       if (structuremanager.a(blockposition, false, StructureGenerator.MONUMENT).e()) {
/* 626 */         return StructureGenerator.MONUMENT.c();
/*     */       }
/*     */       
/* 629 */       if (structuremanager.a(blockposition, true, StructureGenerator.FORTRESS).e()) {
/* 630 */         return StructureGenerator.FORTRESS.c();
/*     */       }
/*     */     } 
/*     */     
/* 634 */     return super.getMobsFor(biomebase, structuremanager, enumcreaturetype, blockposition);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addMobs(RegionLimitedWorldAccess regionlimitedworldaccess) {
/* 639 */     if (!((GeneratorSettingBase)this.h.get()).h()) {
/* 640 */       int i = regionlimitedworldaccess.a();
/* 641 */       int j = regionlimitedworldaccess.b();
/* 642 */       BiomeBase biomebase = regionlimitedworldaccess.getBiome((new ChunkCoordIntPair(i, j)).l());
/* 643 */       SeededRandom seededrandom = new SeededRandom();
/*     */       
/* 645 */       seededrandom.a(regionlimitedworldaccess.getSeed(), i << 4, j << 4);
/* 646 */       SpawnerCreature.a(regionlimitedworldaccess, biomebase, i, j, seededrandom);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ChunkGeneratorAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */