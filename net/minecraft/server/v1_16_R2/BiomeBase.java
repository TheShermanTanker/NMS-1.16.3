/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.mojang.datafixers.kinds.App;
/*     */ import com.mojang.datafixers.kinds.Applicative;
/*     */ import com.mojang.datafixers.util.Function4;
/*     */ import com.mojang.datafixers.util.Function5;
/*     */ import com.mojang.datafixers.util.Function7;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*     */ import it.unimi.dsi.fastutil.longs.Long2FloatLinkedOpenHashMap;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public final class BiomeBase {
/*  22 */   public static final Logger LOGGER = LogManager.getLogger(); public static final Codec<BiomeBase> b;
/*     */   public static final Codec<BiomeBase> c;
/*     */   
/*     */   private static class dProxy extends d { private dProxy(BiomeBase.Precipitation biomebase_precipitation, float f, BiomeBase.TemperatureModifier biomebase_temperaturemodifier, float f1) {
/*  26 */       super(biomebase_precipitation, f, biomebase_temperaturemodifier, f1);
/*     */     } }
/*     */   
/*     */   static {
/*  30 */     b = RecordCodecBuilder.create(instance -> instance.group((App)dProxy.a.forGetter(()), (App)Geography.r.fieldOf("category").forGetter(()), (App)Codec.FLOAT.fieldOf("depth").forGetter(()), (App)Codec.FLOAT.fieldOf("scale").forGetter(()), (App)BiomeFog.a.fieldOf("effects").forGetter(()), (App)BiomeSettingsGeneration.c.forGetter(()), (App)BiomeSettingsMobs.c.forGetter(())).apply((Applicative)instance, BiomeBase::new));
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
/*  47 */     c = RecordCodecBuilder.create(instance -> instance.group((App)dProxy.a.forGetter(()), (App)Geography.r.fieldOf("category").forGetter(()), (App)Codec.FLOAT.fieldOf("depth").forGetter(()), (App)Codec.FLOAT.fieldOf("scale").forGetter(()), (App)BiomeFog.a.fieldOf("effects").forGetter(())).apply((Applicative)instance, ()));
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
/*  62 */   public static final Codec<Supplier<BiomeBase>> d = RegistryFileCodec.a(IRegistry.ay, b);
/*  63 */   public static final Codec<List<Supplier<BiomeBase>>> e = RegistryFileCodec.b(IRegistry.ay, b);
/*     */   private final Map<Integer, List<StructureGenerator<?>>> g;
/*  65 */   private static final NoiseGenerator3 h = new NoiseGenerator3(new SeededRandom(1234L), (List<Integer>)ImmutableList.of(Integer.valueOf(0)));
/*  66 */   private static final NoiseGenerator3 i = new NoiseGenerator3(new SeededRandom(3456L), (List<Integer>)ImmutableList.of(Integer.valueOf(-2), Integer.valueOf(-1), Integer.valueOf(0)));
/*  67 */   public static final NoiseGenerator3 f = new NoiseGenerator3(new SeededRandom(2345L), (List<Integer>)ImmutableList.of(Integer.valueOf(0)));
/*     */   private final d j;
/*     */   private final BiomeSettingsGeneration k;
/*     */   private final BiomeSettingsMobs l;
/*     */   private final float m;
/*     */   private final float n;
/*     */   private final Geography o;
/*     */   private final BiomeFog p;
/*     */   private final ThreadLocal<Long2FloatLinkedOpenHashMap> q;
/*     */   
/*     */   private BiomeBase(d biomebase_d, Geography biomebase_geography, float f, float f1, BiomeFog biomefog, BiomeSettingsGeneration biomesettingsgeneration, BiomeSettingsMobs biomesettingsmobs) {
/*  78 */     this.g = (Map<Integer, List<StructureGenerator<?>>>)IRegistry.STRUCTURE_FEATURE.g().collect(Collectors.groupingBy(structuregenerator -> Integer.valueOf(structuregenerator.f().ordinal())));
/*     */ 
/*     */     
/*  81 */     this.q = ThreadLocal.withInitial(() -> (Long2FloatLinkedOpenHashMap)SystemUtils.<Long2FloatLinkedOpenHashMap>a(()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  91 */     this.j = biomebase_d;
/*  92 */     this.k = biomesettingsgeneration;
/*  93 */     this.l = biomesettingsmobs;
/*  94 */     this.o = biomebase_geography;
/*  95 */     this.m = f;
/*  96 */     this.n = f1;
/*  97 */     this.p = biomefog;
/*     */   }
/*     */   
/*     */   public BiomeSettingsMobs b() {
/* 101 */     return this.l;
/*     */   }
/*     */   
/*     */   public Precipitation c() {
/* 105 */     return this.j.b;
/*     */   }
/*     */   
/*     */   public boolean d() {
/* 109 */     return (getHumidity() > 0.85F);
/*     */   }
/*     */   
/*     */   private float b(BlockPosition blockposition) {
/* 113 */     float f = this.j.d.a(blockposition, k());
/*     */     
/* 115 */     if (blockposition.getY() > 64) {
/* 116 */       float f1 = (float)(h.a((blockposition.getX() / 8.0F), (blockposition.getZ() / 8.0F), false) * 4.0D);
/*     */       
/* 118 */       return f - (f1 + blockposition.getY() - 64.0F) * 0.05F / 30.0F;
/*     */     } 
/* 120 */     return f;
/*     */   }
/*     */ 
/*     */   
/*     */   public final float getAdjustedTemperature(BlockPosition blockposition) {
/* 125 */     long i = blockposition.asLong();
/* 126 */     Long2FloatLinkedOpenHashMap long2floatlinkedopenhashmap = this.q.get();
/* 127 */     float f = long2floatlinkedopenhashmap.get(i);
/*     */     
/* 129 */     if (!Float.isNaN(f)) {
/* 130 */       return f;
/*     */     }
/* 132 */     float f1 = b(blockposition);
/*     */     
/* 134 */     if (long2floatlinkedopenhashmap.size() == 1024) {
/* 135 */       long2floatlinkedopenhashmap.removeFirstFloat();
/*     */     }
/*     */     
/* 138 */     long2floatlinkedopenhashmap.put(i, f1);
/* 139 */     return f1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IWorldReader iworldreader, BlockPosition blockposition) {
/* 144 */     return a(iworldreader, blockposition, true);
/*     */   }
/*     */   
/*     */   public boolean a(IWorldReader iworldreader, BlockPosition blockposition, boolean flag) {
/* 148 */     if (getAdjustedTemperature(blockposition) >= 0.15F) {
/* 149 */       return false;
/*     */     }
/* 151 */     if (blockposition.getY() >= 0 && blockposition.getY() < 256 && iworldreader.getBrightness(EnumSkyBlock.BLOCK, blockposition) < 10) {
/* 152 */       IBlockData iblockdata = iworldreader.getType(blockposition);
/* 153 */       Fluid fluid = iworldreader.getFluid(blockposition);
/*     */       
/* 155 */       if (fluid.getType() == FluidTypes.WATER && iblockdata.getBlock() instanceof BlockFluids) {
/* 156 */         if (!flag) {
/* 157 */           return true;
/*     */         }
/*     */         
/* 160 */         boolean flag1 = (iworldreader.A(blockposition.west()) && iworldreader.A(blockposition.east()) && iworldreader.A(blockposition.north()) && iworldreader.A(blockposition.south()));
/*     */         
/* 162 */         if (!flag1) {
/* 163 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 168 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(IWorldReader iworldreader, BlockPosition blockposition) {
/* 173 */     if (getAdjustedTemperature(blockposition) >= 0.15F) {
/* 174 */       return false;
/*     */     }
/* 176 */     if (blockposition.getY() >= 0 && blockposition.getY() < 256 && iworldreader.getBrightness(EnumSkyBlock.BLOCK, blockposition) < 10) {
/* 177 */       IBlockData iblockdata = iworldreader.getType(blockposition);
/*     */       
/* 179 */       if (iblockdata.isAir() && Blocks.SNOW.getBlockData().canPlace(iworldreader, blockposition)) {
/* 180 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 184 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public BiomeSettingsGeneration e() {
/* 189 */     return this.k;
/*     */   }
/*     */   
/*     */   public void a(StructureManager structuremanager, ChunkGenerator chunkgenerator, RegionLimitedWorldAccess regionlimitedworldaccess, long i, SeededRandom seededrandom, BlockPosition blockposition) {
/* 193 */     List<List<Supplier<WorldGenFeatureConfigured<?, ?>>>> list = this.k.c();
/* 194 */     int j = (WorldGenStage.Decoration.values()).length;
/*     */     
/* 196 */     for (int k = 0; k < j; k++) {
/* 197 */       int l = 0;
/*     */       
/* 199 */       if (structuremanager.a()) {
/* 200 */         List<StructureGenerator<?>> list1 = this.g.getOrDefault(Integer.valueOf(k), Collections.emptyList());
/*     */         
/* 202 */         for (Iterator<StructureGenerator<?>> iterator = list1.iterator(); iterator.hasNext(); l++) {
/* 203 */           StructureGenerator<?> structuregenerator = iterator.next();
/*     */           
/* 205 */           seededrandom.b(i, l, k);
/* 206 */           int i1 = blockposition.getX() >> 4;
/* 207 */           int j1 = blockposition.getZ() >> 4;
/* 208 */           int k1 = i1 << 4;
/* 209 */           int l1 = j1 << 4;
/*     */ 
/*     */           
/*     */           try {
/* 213 */             for (StructureStart<?> structureStart : structuremanager.getFeatureStarts(SectionPosition.a(blockposition), structuregenerator)) {
/* 214 */               structureStart.a(regionlimitedworldaccess, structuremanager, chunkgenerator, seededrandom, new StructureBoundingBox(k1, l1, k1 + 15, l1 + 15), new ChunkCoordIntPair(i1, j1));
/*     */             }
/*     */           }
/* 217 */           catch (Exception exception) {
/* 218 */             CrashReport crashreport = CrashReport.a(exception, "Feature placement");
/*     */             
/* 220 */             crashreport.a("Feature").a("Id", IRegistry.STRUCTURE_FEATURE.getKey(structuregenerator)).a("Description", () -> structuregenerator.toString());
/*     */ 
/*     */             
/* 223 */             throw new ReportedException(crashreport);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 228 */       if (list.size() > k) {
/* 229 */         for (Iterator<Supplier<WorldGenFeatureConfigured<?, ?>>> iterator1 = ((List)list.get(k)).iterator(); iterator1.hasNext(); l++) {
/* 230 */           Supplier<WorldGenFeatureConfigured<?, ?>> supplier = iterator1.next();
/* 231 */           WorldGenFeatureConfigured<?, ?> worldgenfeatureconfigured = supplier.get();
/*     */           
/* 233 */           seededrandom.b(i, l, k);
/*     */           
/*     */           try {
/* 236 */             worldgenfeatureconfigured.a(regionlimitedworldaccess, chunkgenerator, seededrandom, blockposition);
/* 237 */           } catch (Exception exception1) {
/* 238 */             CrashReport crashreport1 = CrashReport.a(exception1, "Feature placement");
/*     */             
/* 240 */             crashreport1.a("Feature").a("Id", IRegistry.FEATURE.getKey((WorldGenerator<?>)worldgenfeatureconfigured.e)).a("Config", worldgenfeatureconfigured.f).a("Description", () -> worldgenfeatureconfigured.e.toString());
/*     */ 
/*     */             
/* 243 */             throw new ReportedException(crashreport1);
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(Random random, IChunkAccess ichunkaccess, int i, int j, int k, double d0, IBlockData iblockdata, IBlockData iblockdata1, int l, long i1) {
/* 252 */     WorldGenSurfaceComposite<?> worldgensurfacecomposite = this.k.d().get();
/*     */     
/* 254 */     worldgensurfacecomposite.a(i1);
/* 255 */     worldgensurfacecomposite.a(random, ichunkaccess, this, i, j, k, d0, iblockdata, iblockdata1, l, i1);
/*     */   }
/*     */   
/*     */   public final float h() {
/* 259 */     return this.m;
/*     */   }
/*     */   
/*     */   public final float getHumidity() {
/* 263 */     return this.j.e;
/*     */   }
/*     */   
/*     */   public final float j() {
/* 267 */     return this.n;
/*     */   }
/*     */   
/*     */   public final float k() {
/* 271 */     return this.j.c;
/*     */   }
/*     */   
/*     */   public BiomeFog l() {
/* 275 */     return this.p;
/*     */   }
/*     */   
/*     */   public final Geography t() {
/* 279 */     return this.o;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 283 */     MinecraftKey minecraftkey = RegistryGeneration.WORLDGEN_BIOME.getKey(this);
/*     */     
/* 285 */     return (minecraftkey == null) ? super.toString() : minecraftkey.toString();
/*     */   }
/*     */   static class d { public static final MapCodec<d> a; private final BiomeBase.Precipitation b;
/*     */     
/*     */     static {
/* 290 */       a = RecordCodecBuilder.mapCodec(instance -> instance.group((App)BiomeBase.Precipitation.d.fieldOf("precipitation").forGetter(()), (App)Codec.FLOAT.fieldOf("temperature").forGetter(()), (App)BiomeBase.TemperatureModifier.c.optionalFieldOf("temperature_modifier", BiomeBase.TemperatureModifier.NONE).forGetter(()), (App)Codec.FLOAT.fieldOf("downfall").forGetter(())).apply((Applicative)instance, d::new));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final float c;
/*     */ 
/*     */     
/*     */     private final BiomeBase.TemperatureModifier d;
/*     */ 
/*     */     
/*     */     private final float e;
/*     */ 
/*     */ 
/*     */     
/*     */     private d(BiomeBase.Precipitation biomebase_precipitation, float f, BiomeBase.TemperatureModifier biomebase_temperaturemodifier, float f1) {
/* 307 */       this.b = biomebase_precipitation;
/* 308 */       this.c = f;
/* 309 */       this.d = biomebase_temperaturemodifier;
/* 310 */       this.e = f1;
/*     */     } }
/*     */   public static class c { public static final Codec<c> a; private final float b;
/*     */     private final float c;
/*     */     
/*     */     static {
/* 316 */       a = RecordCodecBuilder.create(instance -> instance.group((App)Codec.floatRange(-2.0F, 2.0F).fieldOf("temperature").forGetter(()), (App)Codec.floatRange(-2.0F, 2.0F).fieldOf("humidity").forGetter(()), (App)Codec.floatRange(-2.0F, 2.0F).fieldOf("altitude").forGetter(()), (App)Codec.floatRange(-2.0F, 2.0F).fieldOf("weirdness").forGetter(()), (App)Codec.floatRange(0.0F, 1.0F).fieldOf("offset").forGetter(())).apply((Applicative)instance, c::new));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final float d;
/*     */ 
/*     */ 
/*     */     
/*     */     private final float e;
/*     */ 
/*     */ 
/*     */     
/*     */     private final float f;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public c(float f, float f1, float f2, float f3, float f4) {
/* 336 */       this.b = f;
/* 337 */       this.c = f1;
/* 338 */       this.d = f2;
/* 339 */       this.e = f3;
/* 340 */       this.f = f4;
/*     */     }
/*     */     
/*     */     public boolean equals(Object object) {
/* 344 */       if (this == object)
/* 345 */         return true; 
/* 346 */       if (object != null && getClass() == object.getClass()) {
/* 347 */         c biomebase_c = (c)object;
/*     */         
/* 349 */         return (Float.compare(biomebase_c.b, this.b) != 0) ? false : ((Float.compare(biomebase_c.c, this.c) != 0) ? false : ((Float.compare(biomebase_c.d, this.d) != 0) ? false : ((Float.compare(biomebase_c.e, this.e) == 0))));
/*     */       } 
/* 351 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 356 */       int i = (this.b != 0.0F) ? Float.floatToIntBits(this.b) : 0;
/*     */       
/* 358 */       i = 31 * i + ((this.c != 0.0F) ? Float.floatToIntBits(this.c) : 0);
/* 359 */       i = 31 * i + ((this.d != 0.0F) ? Float.floatToIntBits(this.d) : 0);
/* 360 */       i = 31 * i + ((this.e != 0.0F) ? Float.floatToIntBits(this.e) : 0);
/* 361 */       return i;
/*     */     }
/*     */     
/*     */     public float a(c biomebase_c) {
/* 365 */       return (this.b - biomebase_c.b) * (this.b - biomebase_c.b) + (this.c - biomebase_c.c) * (this.c - biomebase_c.c) + (this.d - biomebase_c.d) * (this.d - biomebase_c.d) + (this.e - biomebase_c.e) * (this.e - biomebase_c.e) + (this.f - biomebase_c.f) * (this.f - biomebase_c.f);
/*     */     } }
/*     */ 
/*     */   
/*     */   public static class a
/*     */   {
/*     */     @Nullable
/*     */     private BiomeBase.Precipitation a;
/*     */     @Nullable
/*     */     private BiomeBase.Geography b;
/*     */     @Nullable
/*     */     private Float c;
/*     */     @Nullable
/*     */     private Float d;
/*     */     @Nullable
/*     */     private Float e;
/*     */     private BiomeBase.TemperatureModifier f;
/*     */     @Nullable
/*     */     private Float g;
/*     */     @Nullable
/*     */     private BiomeFog h;
/*     */     @Nullable
/*     */     private BiomeSettingsMobs i;
/*     */     @Nullable
/*     */     private BiomeSettingsGeneration j;
/*     */     
/*     */     public a() {
/* 392 */       this.f = BiomeBase.TemperatureModifier.NONE;
/*     */     }
/*     */     
/*     */     public a a(BiomeBase.Precipitation biomebase_precipitation) {
/* 396 */       this.a = biomebase_precipitation;
/* 397 */       return this;
/*     */     }
/*     */     
/*     */     public a a(BiomeBase.Geography biomebase_geography) {
/* 401 */       this.b = biomebase_geography;
/* 402 */       return this;
/*     */     }
/*     */     
/*     */     public a a(float f) {
/* 406 */       this.c = Float.valueOf(f);
/* 407 */       return this;
/*     */     }
/*     */     
/*     */     public a b(float f) {
/* 411 */       this.d = Float.valueOf(f);
/* 412 */       return this;
/*     */     }
/*     */     
/*     */     public a c(float f) {
/* 416 */       this.e = Float.valueOf(f);
/* 417 */       return this;
/*     */     }
/*     */     
/*     */     public a d(float f) {
/* 421 */       this.g = Float.valueOf(f);
/* 422 */       return this;
/*     */     }
/*     */     
/*     */     public a a(BiomeFog biomefog) {
/* 426 */       this.h = biomefog;
/* 427 */       return this;
/*     */     }
/*     */     
/*     */     public a a(BiomeSettingsMobs biomesettingsmobs) {
/* 431 */       this.i = biomesettingsmobs;
/* 432 */       return this;
/*     */     }
/*     */     
/*     */     public a a(BiomeSettingsGeneration biomesettingsgeneration) {
/* 436 */       this.j = biomesettingsgeneration;
/* 437 */       return this;
/*     */     }
/*     */     
/*     */     public a a(BiomeBase.TemperatureModifier biomebase_temperaturemodifier) {
/* 441 */       this.f = biomebase_temperaturemodifier;
/* 442 */       return this;
/*     */     }
/*     */     
/*     */     public BiomeBase a() {
/* 446 */       if (this.a != null && this.b != null && this.c != null && this.d != null && this.e != null && this.g != null && this.h != null && this.i != null && this.j != null) {
/* 447 */         return new BiomeBase(new BiomeBase.d(this.a, this.e.floatValue(), this.f, this.g.floatValue()), this.b, this.c.floatValue(), this.d.floatValue(), this.h, this.j, this.i);
/*     */       }
/* 449 */       throw new IllegalStateException("You are missing parameters to build a proper biome\n" + this);
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 454 */       return "BiomeBuilder{\nprecipitation=" + this.a + ",\nbiomeCategory=" + this.b + ",\ndepth=" + this.c + ",\nscale=" + this.d + ",\ntemperature=" + this.e + ",\ntemperatureModifier=" + this.f + ",\ndownfall=" + this.g + ",\nspecialEffects=" + this.h + ",\nmobSpawnSettings=" + this.i + ",\ngenerationSettings=" + this.j + ",\n" + '}';
/*     */     }
/*     */   }
/*     */   
/*     */   public enum TemperatureModifier
/*     */     implements INamable {
/* 460 */     NONE("none")
/*     */     {
/*     */       public float a(BlockPosition blockposition, float f) {
/* 463 */         return f;
/*     */       }
/*     */     },
/* 466 */     FROZEN("frozen")
/*     */     {
/*     */       public float a(BlockPosition blockposition, float f) {
/* 469 */         double d0 = BiomeBase.i.a(blockposition.getX() * 0.05D, blockposition.getZ() * 0.05D, false) * 7.0D;
/* 470 */         double d1 = BiomeBase.f.a(blockposition.getX() * 0.2D, blockposition.getZ() * 0.2D, false);
/* 471 */         double d2 = d0 + d1;
/*     */         
/* 473 */         if (d2 < 0.3D) {
/* 474 */           double d3 = BiomeBase.f.a(blockposition.getX() * 0.09D, blockposition.getZ() * 0.09D, false);
/*     */           
/* 476 */           if (d3 < 0.8D) {
/* 477 */             return 0.2F;
/*     */           }
/*     */         } 
/*     */         
/* 481 */         return f;
/*     */       }
/*     */     };
/*     */     
/*     */     private final String d;
/* 486 */     public static final Codec<TemperatureModifier> c = INamable.a(TemperatureModifier::values, TemperatureModifier::a); private static final Map<String, TemperatureModifier> e; static {
/* 487 */       e = (Map<String, TemperatureModifier>)Arrays.<TemperatureModifier>stream(values()).collect(Collectors.toMap(TemperatureModifier::b, biomebase_temperaturemodifier -> biomebase_temperaturemodifier));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     TemperatureModifier(String s) {
/* 494 */       this.d = s;
/*     */     }
/*     */     
/*     */     public String b() {
/* 498 */       return this.d;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 503 */       return this.d;
/*     */     }
/*     */     
/*     */     public static TemperatureModifier a(String s) {
/* 507 */       return e.get(s);
/*     */     }
/*     */     
/*     */     public abstract float a(BlockPosition param1BlockPosition, float param1Float); }
/*     */   
/*     */   public enum Precipitation implements INamable {
/* 513 */     NONE("none"), RAIN("rain"), SNOW("snow"); private final String f;
/*     */     private static final Map<String, Precipitation> e;
/* 515 */     public static final Codec<Precipitation> d = INamable.a(Precipitation::values, Precipitation::a); static {
/* 516 */       e = (Map<String, Precipitation>)Arrays.<Precipitation>stream(values()).collect(Collectors.toMap(Precipitation::b, biomebase_precipitation -> biomebase_precipitation));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     Precipitation(String s) {
/* 522 */       this.f = s;
/*     */     }
/*     */     
/*     */     public String b() {
/* 526 */       return this.f;
/*     */     }
/*     */     
/*     */     public static Precipitation a(String s) {
/* 530 */       return e.get(s);
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 535 */       return this.f;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum Geography
/*     */     implements INamable {
/* 541 */     NONE("none"), TAIGA("taiga"), EXTREME_HILLS("extreme_hills"), JUNGLE("jungle"), MESA("mesa"), PLAINS("plains"), SAVANNA("savanna"), ICY("icy"), THEEND("the_end"), BEACH("beach"), FOREST("forest"), OCEAN("ocean"), DESERT("desert"), RIVER("river"), SWAMP("swamp"), MUSHROOM("mushroom"), NETHER("nether"); private final String t;
/*     */     private static final Map<String, Geography> s;
/* 543 */     public static final Codec<Geography> r = INamable.a(Geography::values, Geography::a); static {
/* 544 */       s = (Map<String, Geography>)Arrays.<Geography>stream(values()).collect(Collectors.toMap(Geography::b, biomebase_geography -> biomebase_geography));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     Geography(String s) {
/* 550 */       this.t = s;
/*     */     }
/*     */     
/*     */     public String b() {
/* 554 */       return this.t;
/*     */     }
/*     */     
/*     */     public static Geography a(String s) {
/* 558 */       return Geography.s.get(s);
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 563 */       return this.t;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BiomeBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */