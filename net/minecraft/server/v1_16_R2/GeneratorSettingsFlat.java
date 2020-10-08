/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.datafixers.kinds.App;
/*     */ import com.mojang.datafixers.kinds.Applicative;
/*     */ import com.mojang.datafixers.util.Function6;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.function.Supplier;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
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
/*     */ public class GeneratorSettingsFlat
/*     */ {
/*  39 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   public static final Codec<GeneratorSettingsFlat> a;
/*     */   
/*     */   private static final Map<StructureGenerator<?>, StructureFeature<?, ?>> c;
/*     */   private final IRegistry<BiomeBase> d;
/*     */   private final StructureSettings e;
/*     */   
/*     */   static {
/*  48 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)RegistryLookupCodec.<BiomeBase>a(IRegistry.ay).forGetter(()), (App)StructureSettings.a.fieldOf("structures").forGetter(GeneratorSettingsFlat::d), (App)WorldGenFlatLayerInfo.a.listOf().fieldOf("layers").forGetter(GeneratorSettingsFlat::f), (App)Codec.BOOL.fieldOf("lakes").orElse(Boolean.valueOf(false)).forGetter(()), (App)Codec.BOOL.fieldOf("features").orElse(Boolean.valueOf(false)).forGetter(()), (App)BiomeBase.d.optionalFieldOf("biome").orElseGet(Optional::empty).forGetter(())).apply((Applicative)var0, GeneratorSettingsFlat::new)).stable();
/*     */     
/*  50 */     c = SystemUtils.<Map<StructureGenerator<?>, StructureFeature<?, ?>>>a(Maps.newHashMap(), var0 -> {
/*     */           var0.put(StructureGenerator.MINESHAFT, StructureFeatures.b);
/*     */           var0.put(StructureGenerator.VILLAGE, StructureFeatures.t);
/*     */           var0.put(StructureGenerator.STRONGHOLD, StructureFeatures.k);
/*     */           var0.put(StructureGenerator.SWAMP_HUT, StructureFeatures.j);
/*     */           var0.put(StructureGenerator.DESERT_PYRAMID, StructureFeatures.f);
/*     */           var0.put(StructureGenerator.JUNGLE_PYRAMID, StructureFeatures.e);
/*     */           var0.put(StructureGenerator.IGLOO, StructureFeatures.g);
/*     */           var0.put(StructureGenerator.OCEAN_RUIN, StructureFeatures.m);
/*     */           var0.put(StructureGenerator.SHIPWRECK, StructureFeatures.h);
/*     */           var0.put(StructureGenerator.MONUMENT, StructureFeatures.l);
/*     */           var0.put(StructureGenerator.ENDCITY, StructureFeatures.q);
/*     */           var0.put(StructureGenerator.MANSION, StructureFeatures.d);
/*     */           var0.put(StructureGenerator.FORTRESS, StructureFeatures.o);
/*     */           var0.put(StructureGenerator.PILLAGER_OUTPOST, StructureFeatures.a);
/*     */           var0.put(StructureGenerator.RUINED_PORTAL, StructureFeatures.y);
/*     */           var0.put(StructureGenerator.BASTION_REMNANT, StructureFeatures.s);
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*  71 */   private final List<WorldGenFlatLayerInfo> f = Lists.newArrayList();
/*     */   private Supplier<BiomeBase> g;
/*  73 */   private final IBlockData[] h = new IBlockData[256];
/*     */   private boolean i;
/*     */   private boolean j = false;
/*     */   private boolean k = false;
/*     */   
/*     */   public GeneratorSettingsFlat(IRegistry<BiomeBase> var0, StructureSettings var1, List<WorldGenFlatLayerInfo> var2, boolean var3, boolean var4, Optional<Supplier<BiomeBase>> var5) {
/*  79 */     this(var1, var0);
/*  80 */     if (var3) {
/*  81 */       b();
/*     */     }
/*  83 */     if (var4) {
/*  84 */       a();
/*     */     }
/*  86 */     this.f.addAll(var2);
/*  87 */     h();
/*  88 */     if (!var5.isPresent()) {
/*  89 */       LOGGER.error("Unknown biome, defaulting to plains");
/*  90 */       this.g = (() -> (BiomeBase)var0.d(Biomes.PLAINS));
/*     */     } else {
/*  92 */       this.g = var5.get();
/*     */     } 
/*     */   }
/*     */   
/*     */   public GeneratorSettingsFlat(StructureSettings var0, IRegistry<BiomeBase> var1) {
/*  97 */     this.d = var1;
/*  98 */     this.e = var0;
/*  99 */     this.g = (() -> (BiomeBase)var0.d(Biomes.PLAINS));
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
/*     */   public void a() {
/* 123 */     this.j = true;
/*     */   }
/*     */   
/*     */   public void b() {
/* 127 */     this.k = true;
/*     */   }
/*     */   
/*     */   public BiomeBase c() {
/* 131 */     BiomeBase var0 = e();
/* 132 */     BiomeSettingsGeneration var1 = var0.e();
/*     */ 
/*     */     
/* 135 */     BiomeSettingsGeneration.a var2 = (new BiomeSettingsGeneration.a()).a(var1.d());
/*     */     
/* 137 */     if (this.k) {
/* 138 */       var2.a(WorldGenStage.Decoration.LAKES, BiomeDecoratorGroups.LAKE_WATER);
/* 139 */       var2.a(WorldGenStage.Decoration.LAKES, BiomeDecoratorGroups.LAKE_LAVA);
/*     */     } 
/*     */     
/* 142 */     for (Map.Entry<StructureGenerator<?>, StructureSettingsFeature> entry : this.e.a().entrySet()) {
/* 143 */       var2.a(var1.a(c.get(entry.getKey())));
/*     */     }
/*     */     
/* 146 */     boolean var3 = ((!this.i || this.d.c(var0).equals(Optional.of(Biomes.THE_VOID))) && this.j);
/*     */     
/* 148 */     if (var3) {
/* 149 */       List<List<Supplier<WorldGenFeatureConfigured<?, ?>>>> list = var1.c();
/* 150 */       for (int i = 0; i < list.size(); i++) {
/* 151 */         if (i != WorldGenStage.Decoration.UNDERGROUND_STRUCTURES.ordinal() && i != WorldGenStage.Decoration.SURFACE_STRUCTURES.ordinal()) {
/*     */ 
/*     */           
/* 154 */           List<Supplier<WorldGenFeatureConfigured<?, ?>>> var6 = list.get(i);
/* 155 */           for (Supplier<WorldGenFeatureConfigured<?, ?>> var8 : var6) {
/* 156 */             var2.a(i, var8);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 161 */     IBlockData[] var4 = g();
/* 162 */     for (int var5 = 0; var5 < var4.length; var5++) {
/* 163 */       IBlockData var6 = var4[var5];
/* 164 */       if (var6 != null && !HeightMap.Type.MOTION_BLOCKING.e().test(var6)) {
/* 165 */         this.h[var5] = null;
/* 166 */         var2.a(WorldGenStage.Decoration.TOP_LAYER_MODIFICATION, WorldGenerator.FILL_LAYER.b(new WorldGenFeatureFillConfiguration(var5, var6)));
/*     */       } 
/*     */     } 
/*     */     
/* 170 */     return (new BiomeBase.a())
/* 171 */       .a(var0.c())
/* 172 */       .a(var0.t())
/* 173 */       .a(var0.h())
/* 174 */       .b(var0.j())
/* 175 */       .c(var0.k())
/* 176 */       .d(var0.getHumidity())
/* 177 */       .a(var0.l())
/* 178 */       .a(var2.a())
/* 179 */       .a(var0.b())
/* 180 */       .a();
/*     */   }
/*     */   
/*     */   public StructureSettings d() {
/* 184 */     return this.e;
/*     */   }
/*     */   
/*     */   public BiomeBase e() {
/* 188 */     return this.g.get();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<WorldGenFlatLayerInfo> f() {
/* 196 */     return this.f;
/*     */   }
/*     */   
/*     */   public IBlockData[] g() {
/* 200 */     return this.h;
/*     */   }
/*     */   
/*     */   public void h() {
/* 204 */     Arrays.fill((Object[])this.h, 0, this.h.length, (Object)null);
/*     */     
/* 206 */     int var0 = 0;
/*     */     
/* 208 */     for (WorldGenFlatLayerInfo var2 : this.f) {
/* 209 */       var2.a(var0);
/* 210 */       var0 += var2.a();
/*     */     } 
/*     */ 
/*     */     
/* 214 */     this.i = true;
/* 215 */     for (WorldGenFlatLayerInfo var1 : this.f) {
/* 216 */       for (int var2 = var1.c(); var2 < var1.c() + var1.a(); var2++) {
/* 217 */         IBlockData var3 = var1.b();
/* 218 */         if (!var3.a(Blocks.AIR)) {
/*     */ 
/*     */           
/* 221 */           this.i = false;
/* 222 */           this.h[var2] = var3;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static GeneratorSettingsFlat a(IRegistry<BiomeBase> var0) {
/* 230 */     StructureSettings var1 = new StructureSettings(Optional.of(StructureSettings.c), Maps.newHashMap((Map)ImmutableMap.of(StructureGenerator.VILLAGE, StructureSettings.b.get(StructureGenerator.VILLAGE))));
/*     */ 
/*     */     
/* 233 */     GeneratorSettingsFlat var2 = new GeneratorSettingsFlat(var1, var0);
/*     */     
/* 235 */     var2.g = (() -> (BiomeBase)var0.d(Biomes.PLAINS));
/* 236 */     var2.f().add(new WorldGenFlatLayerInfo(1, Blocks.BEDROCK));
/* 237 */     var2.f().add(new WorldGenFlatLayerInfo(2, Blocks.DIRT));
/* 238 */     var2.f().add(new WorldGenFlatLayerInfo(1, Blocks.GRASS_BLOCK));
/* 239 */     var2.h();
/*     */     
/* 241 */     return var2;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GeneratorSettingsFlat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */