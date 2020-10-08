/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.base.MoreObjects;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.mojang.datafixers.kinds.App;
/*     */ import com.mojang.datafixers.kinds.Applicative;
/*     */ import com.mojang.datafixers.util.Function5;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.DataResult;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import com.mojang.serialization.DynamicOps;
/*     */ import com.mojang.serialization.JsonOps;
/*     */ import com.mojang.serialization.Lifecycle;
/*     */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.Properties;
/*     */ import java.util.Random;
/*     */ import java.util.function.Function;
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
/*     */ public class GeneratorSettings
/*     */ {
/*     */   public static final Codec<GeneratorSettings> a;
/*     */   
/*     */   static {
/*  47 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)Codec.LONG.fieldOf("seed").stable().forGetter(GeneratorSettings::getSeed), (App)Codec.BOOL.fieldOf("generate_features").orElse(Boolean.valueOf(true)).stable().forGetter(GeneratorSettings::shouldGenerateMapFeatures), (App)Codec.BOOL.fieldOf("bonus_chest").orElse(Boolean.valueOf(false)).stable().forGetter(GeneratorSettings::c), (App)RegistryMaterials.<T>b((ResourceKey)IRegistry.M, Lifecycle.stable(), (Codec)WorldDimension.a).xmap(WorldDimension::a, Function.identity()).fieldOf("dimensions").forGetter(GeneratorSettings::d), (App)Codec.STRING.optionalFieldOf("legacy_custom_options").stable().forGetter(())).apply((Applicative)var0, var0.stable(GeneratorSettings::new))).comapFlatMap(GeneratorSettings::m, Function.identity());
/*     */   }
/*  49 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   private final long seed;
/*     */   
/*     */   private final boolean d;
/*     */   
/*     */   private final boolean e;
/*     */   private final RegistryMaterials<WorldDimension> f;
/*     */   private final Optional<String> g;
/*     */   
/*     */   private DataResult<GeneratorSettings> m() {
/*  60 */     WorldDimension var0 = this.f.a(WorldDimension.OVERWORLD);
/*  61 */     if (var0 == null) {
/*  62 */       return DataResult.error("Overworld settings missing");
/*     */     }
/*  64 */     if (n()) {
/*  65 */       return DataResult.success(this, Lifecycle.stable());
/*     */     }
/*  67 */     return DataResult.success(this);
/*     */   }
/*     */   
/*     */   private boolean n() {
/*  71 */     return WorldDimension.a(this.seed, this.f);
/*     */   }
/*     */   
/*     */   public GeneratorSettings(long var0, boolean var2, boolean var3, RegistryMaterials<WorldDimension> var4) {
/*  75 */     this(var0, var2, var3, var4, Optional.empty());
/*     */     
/*  77 */     WorldDimension var5 = var4.a(WorldDimension.OVERWORLD);
/*  78 */     if (var5 == null) {
/*  79 */       throw new IllegalStateException("Overworld settings missing");
/*     */     }
/*     */   }
/*     */   
/*     */   private GeneratorSettings(long var0, boolean var2, boolean var3, RegistryMaterials<WorldDimension> var4, Optional<String> var5) {
/*  84 */     this.seed = var0;
/*  85 */     this.d = var2;
/*  86 */     this.e = var3;
/*  87 */     this.f = var4;
/*  88 */     this.g = var5;
/*     */   }
/*     */   
/*     */   public static GeneratorSettings a(IRegistryCustom var0) {
/*  92 */     IRegistry<BiomeBase> var1 = var0.b(IRegistry.ay);
/*  93 */     int var2 = "North Carolina".hashCode();
/*  94 */     IRegistry<DimensionManager> var3 = var0.b(IRegistry.K);
/*  95 */     IRegistry<GeneratorSettingBase> var4 = var0.b(IRegistry.ar);
/*  96 */     return new GeneratorSettings(var2, true, true, a(var3, DimensionManager.a(var3, var1, var4, var2), a(var1, var4, var2)));
/*     */   }
/*     */   
/*     */   public static GeneratorSettings a(IRegistry<DimensionManager> var0, IRegistry<BiomeBase> var1, IRegistry<GeneratorSettingBase> var2) {
/* 100 */     long var3 = (new Random()).nextLong();
/* 101 */     return new GeneratorSettings(var3, true, false, a(var0, DimensionManager.a(var0, var1, var2, var3), a(var1, var2, var3)));
/*     */   }
/*     */   
/*     */   public static ChunkGeneratorAbstract a(IRegistry<BiomeBase> var0, IRegistry<GeneratorSettingBase> var1, long var2) {
/* 105 */     return new ChunkGeneratorAbstract(new WorldChunkManagerOverworld(var2, false, false, var0), var2, () -> (GeneratorSettingBase)var0.d(GeneratorSettingBase.c));
/*     */   }
/*     */   
/*     */   public long getSeed() {
/* 109 */     return this.seed;
/*     */   }
/*     */   
/*     */   public boolean shouldGenerateMapFeatures() {
/* 113 */     return this.d;
/*     */   }
/*     */   
/*     */   public boolean c() {
/* 117 */     return this.e;
/*     */   }
/*     */   
/*     */   public static RegistryMaterials<WorldDimension> a(IRegistry<DimensionManager> var0, RegistryMaterials<WorldDimension> var1, ChunkGenerator var2) {
/* 121 */     WorldDimension var3 = var1.a(WorldDimension.OVERWORLD);
/* 122 */     Supplier<DimensionManager> var4 = () -> (var0 == null) ? var1.d(DimensionManager.OVERWORLD) : var0.b();
/*     */     
/* 124 */     return a(var1, var4, var2);
/*     */   }
/*     */   
/*     */   public static RegistryMaterials<WorldDimension> a(RegistryMaterials<WorldDimension> var0, Supplier<DimensionManager> var1, ChunkGenerator var2) {
/* 128 */     RegistryMaterials<WorldDimension> var3 = new RegistryMaterials<>(IRegistry.M, Lifecycle.experimental());
/*     */     
/* 130 */     var3.a(WorldDimension.OVERWORLD, new WorldDimension(var1, var2), Lifecycle.stable());
/* 131 */     for (Map.Entry<ResourceKey<WorldDimension>, WorldDimension> var5 : var0.d()) {
/* 132 */       ResourceKey<WorldDimension> var6 = var5.getKey();
/* 133 */       if (var6 == WorldDimension.OVERWORLD) {
/*     */         continue;
/*     */       }
/* 136 */       var3.a(var6, var5.getValue(), var0.d(var5.getValue()));
/*     */     } 
/* 138 */     return var3;
/*     */   }
/*     */   
/*     */   public RegistryMaterials<WorldDimension> d() {
/* 142 */     return this.f;
/*     */   }
/*     */   
/*     */   public ChunkGenerator getChunkGenerator() {
/* 146 */     WorldDimension var0 = this.f.a(WorldDimension.OVERWORLD);
/* 147 */     if (var0 == null) {
/* 148 */       throw new IllegalStateException("Overworld settings missing");
/*     */     }
/* 150 */     return var0.c();
/*     */   }
/*     */   
/*     */   public ImmutableSet<ResourceKey<World>> f() {
/* 154 */     return (ImmutableSet<ResourceKey<World>>)d().d().stream().map(var0 -> ResourceKey.a(IRegistry.L, ((ResourceKey)var0.getKey()).a())).collect(ImmutableSet.toImmutableSet());
/*     */   }
/*     */   
/*     */   public boolean isDebugWorld() {
/* 158 */     return getChunkGenerator() instanceof ChunkProviderDebug;
/*     */   }
/*     */   
/*     */   public boolean isFlatWorld() {
/* 162 */     return getChunkGenerator() instanceof ChunkProviderFlat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GeneratorSettings j() {
/* 170 */     return new GeneratorSettings(this.seed, this.d, true, this.f, this.g);
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
/*     */   public static GeneratorSettings a(IRegistryCustom var0, Properties var1) {
/*     */     JsonObject var16;
/*     */     Dynamic<JsonElement> var17;
/* 184 */     String var2 = (String)MoreObjects.firstNonNull(var1.get("generator-settings"), "");
/* 185 */     var1.put("generator-settings", var2);
/*     */     
/* 187 */     String var3 = (String)MoreObjects.firstNonNull(var1.get("level-seed"), "");
/* 188 */     var1.put("level-seed", var3);
/*     */     
/* 190 */     String var4 = (String)var1.get("generate-structures");
/* 191 */     boolean var5 = (var4 == null || Boolean.parseBoolean(var4));
/* 192 */     var1.put("generate-structures", Objects.toString(Boolean.valueOf(var5)));
/*     */     
/* 194 */     String var6 = (String)var1.get("level-type");
/* 195 */     String var7 = Optional.<String>ofNullable(var6).map(var0 -> var0.toLowerCase(Locale.ROOT)).orElse("default");
/* 196 */     var1.put("level-type", var7);
/*     */     
/* 198 */     long var8 = (new Random()).nextLong();
/* 199 */     if (!var3.isEmpty()) {
/*     */       try {
/* 201 */         long l = Long.parseLong(var3);
/* 202 */         if (l != 0L) {
/* 203 */           var8 = l;
/*     */         }
/* 205 */       } catch (NumberFormatException numberFormatException) {
/* 206 */         var8 = var3.hashCode();
/*     */       } 
/*     */     }
/*     */     
/* 210 */     IRegistry<DimensionManager> var10 = var0.b(IRegistry.K);
/* 211 */     IRegistry<BiomeBase> var11 = var0.b(IRegistry.ay);
/* 212 */     IRegistry<GeneratorSettingBase> var12 = var0.b(IRegistry.ar);
/*     */     
/* 214 */     RegistryMaterials<WorldDimension> var13 = DimensionManager.a(var10, var11, var12, var8);
/* 215 */     switch (var7) {
/*     */       case "flat":
/* 217 */         var16 = !var2.isEmpty() ? ChatDeserializer.a(var2) : new JsonObject();
/* 218 */         var17 = new Dynamic((DynamicOps)JsonOps.INSTANCE, var16);
/* 219 */         return new GeneratorSettings(var8, var5, false, a(var10, var13, new ChunkProviderFlat(GeneratorSettingsFlat.a.parse(var17).resultOrPartial(LOGGER::error).orElseGet(() -> GeneratorSettingsFlat.a(var0)))));
/*     */       case "debug_all_block_states":
/* 221 */         return new GeneratorSettings(var8, var5, false, a(var10, var13, new ChunkProviderDebug(var11)));
/*     */       case "amplified":
/* 223 */         return new GeneratorSettings(var8, var5, false, a(var10, var13, new ChunkGeneratorAbstract(new WorldChunkManagerOverworld(var8, false, false, var11), var8, () -> (GeneratorSettingBase)var0.d(GeneratorSettingBase.d))));
/*     */       case "largebiomes":
/* 225 */         return new GeneratorSettings(var8, var5, false, a(var10, var13, new ChunkGeneratorAbstract(new WorldChunkManagerOverworld(var8, false, true, var11), var8, () -> (GeneratorSettingBase)var0.d(GeneratorSettingBase.c))));
/*     */     } 
/* 227 */     return new GeneratorSettings(var8, var5, false, a(var10, var13, a(var11, var12, var8)));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GeneratorSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */