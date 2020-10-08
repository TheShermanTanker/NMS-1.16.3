/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.datafixers.DSL;
/*     */ import com.mojang.datafixers.Typed;
/*     */ import com.mojang.datafixers.kinds.App;
/*     */ import com.mojang.datafixers.kinds.Applicative;
/*     */ import com.mojang.datafixers.schemas.Schema;
/*     */ import com.mojang.datafixers.util.Function3;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import com.mojang.serialization.DynamicLike;
/*     */ import com.mojang.serialization.DynamicOps;
/*     */ import com.mojang.serialization.OptionalDynamic;
/*     */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.stream.Collectors;
/*     */ import java.util.stream.Stream;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.math.NumberUtils;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.mutable.MutableBoolean;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.mutable.MutableInt;
/*     */ 
/*     */ public class DataConverterWorldGenSettingsBuilding extends DataFix {
/*     */   public DataConverterWorldGenSettingsBuilding(Schema var0) {
/*  28 */     super(var0, true);
/*     */   }
/*     */ 
/*     */   
/*     */   protected TypeRewriteRule makeRule() {
/*  33 */     return fixTypeEverywhereTyped("WorldGenSettings building", getInputSchema().getType(DataConverterTypes.WORLD_GEN_SETTINGS), var0 -> var0.update(DSL.remainderFinder(), DataConverterWorldGenSettingsBuilding::a));
/*     */   }
/*     */   
/*     */   private static <T> Dynamic<T> a(long var0, DynamicLike<T> var2, Dynamic<T> var3, Dynamic<T> var4) {
/*  37 */     return var2.createMap((Map)ImmutableMap.of(var2
/*  38 */           .createString("type"), var2.createString("minecraft:noise"), var2
/*  39 */           .createString("biome_source"), var4, var2
/*  40 */           .createString("seed"), var2.createLong(var0), var2
/*  41 */           .createString("settings"), var3));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static <T> Dynamic<T> a(Dynamic<T> var0, long var1, boolean var3, boolean var4) {
/*  49 */     ImmutableMap.Builder<Dynamic<T>, Dynamic<T>> var5 = ImmutableMap.builder().put(var0.createString("type"), var0.createString("minecraft:vanilla_layered")).put(var0.createString("seed"), var0.createLong(var1)).put(var0.createString("large_biomes"), var0.createBoolean(var4));
/*     */     
/*  51 */     if (var3) {
/*  52 */       var5.put(var0.createString("legacy_biome_init_layer"), var0.createBoolean(var3));
/*     */     }
/*     */     
/*  55 */     return var0.createMap((Map)var5.build());
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
/*  68 */   private static final ImmutableMap<String, a> a = ImmutableMap.builder()
/*  69 */     .put("minecraft:village", new a(32, 8, 10387312))
/*  70 */     .put("minecraft:desert_pyramid", new a(32, 8, 14357617))
/*  71 */     .put("minecraft:igloo", new a(32, 8, 14357618))
/*  72 */     .put("minecraft:jungle_pyramid", new a(32, 8, 14357619))
/*  73 */     .put("minecraft:swamp_hut", new a(32, 8, 14357620))
/*  74 */     .put("minecraft:pillager_outpost", new a(32, 8, 165745296))
/*  75 */     .put("minecraft:monument", new a(32, 5, 10387313))
/*  76 */     .put("minecraft:endcity", new a(20, 11, 10387313))
/*  77 */     .put("minecraft:mansion", new a(80, 20, 10387319))
/*  78 */     .build();
/*     */   static final class a { public static final Codec<a> a; private final int b; private final int c; private final int d;
/*     */     static {
/*  81 */       a = RecordCodecBuilder.create(var0 -> var0.group((App)Codec.INT.fieldOf("spacing").forGetter(()), (App)Codec.INT.fieldOf("separation").forGetter(()), (App)Codec.INT.fieldOf("salt").forGetter(())).apply((Applicative)var0, a::new));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public a(int var0, int var1, int var2) {
/*  92 */       this.b = var0;
/*  93 */       this.c = var1;
/*  94 */       this.d = var2;
/*     */     }
/*     */     
/*     */     public <T> Dynamic<T> a(DynamicOps<T> var0) {
/*  98 */       return new Dynamic(var0, a.encodeStart(var0, this).result().orElse(var0.emptyMap()));
/*     */     } }
/*     */   
/*     */   private static <T> Dynamic<T> a(Dynamic<T> var0) {
/*     */     Dynamic<T> var4;
/* 103 */     DynamicOps<T> var1 = var0.getOps();
/*     */     
/* 105 */     long var2 = var0.get("RandomSeed").asLong(0L);
/*     */     
/* 107 */     Optional<String> var5 = var0.get("generatorName").asString().map(var0 -> var0.toLowerCase(Locale.ROOT)).result();
/*     */     
/* 109 */     Optional<String> var6 = var0.get("legacy_custom_options").asString().result().map(Optional::of).orElseGet(() -> var0.equals(Optional.of("customized")) ? var1.get("generatorOptions").asString().result() : Optional.empty());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 116 */     boolean var7 = false;
/* 117 */     if (var5.equals(Optional.of("customized")))
/* 118 */     { var4 = a(var0, var2); }
/* 119 */     else if (!var5.isPresent())
/* 120 */     { var4 = a(var0, var2); }
/*     */     else
/* 122 */     { String str; boolean var8; byte b; boolean var9; OptionalDynamic<T> optionalDynamic1; ImmutableMap.Builder<T, T> var10; Map<Dynamic<T>, Dynamic<T>> var11; OptionalDynamic<T> var12; OptionalDynamic<?> var13; Optional<String> var14; Dynamic<T> var15, var16, var17; switch ((String)var5.get())
/*     */       { case "flat":
/* 124 */           optionalDynamic1 = var0.get("generatorOptions");
/* 125 */           var11 = a(var1, optionalDynamic1);
/*     */           
/* 127 */           var4 = var0.createMap((Map)ImmutableMap.of(var0
/* 128 */                 .createString("type"), var0.createString("minecraft:flat"), var0
/* 129 */                 .createString("settings"), var0.createMap((Map)ImmutableMap.of(var0
/* 130 */                     .createString("structures"), var0.createMap(var11), var0
/* 131 */                     .createString("layers"), optionalDynamic1.get("layers").result().orElseGet(() -> var0.createList(Stream.of(new Dynamic[] { var0.createMap((Map)ImmutableMap.of(var0.createString("height"), var0.createInt(1), var0.createString("block"), var0.createString("minecraft:bedrock"))), var0.createMap((Map)ImmutableMap.of(var0.createString("height"), var0.createInt(2), var0.createString("block"), var0.createString("minecraft:dirt"))), var0.createMap((Map)ImmutableMap.of(var0.createString("height"), var0.createInt(1), var0.createString("block"), var0.createString("minecraft:grass_block"))) }))), var0
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
/* 145 */                     .createString("biome"), var0.createString(optionalDynamic1.get("biome").asString("minecraft:plains"))))));
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
/* 201 */           var8 = var0.get("MapFeatures").asBoolean(true);
/* 202 */           var9 = var0.get("BonusChest").asBoolean(false);
/*     */           
/* 204 */           var10 = ImmutableMap.builder();
/* 205 */           var10.put(var1.createString("seed"), var1.createLong(var2));
/* 206 */           var10.put(var1.createString("generate_features"), var1.createBoolean(var8));
/* 207 */           var10.put(var1.createString("bonus_chest"), var1.createBoolean(var9));
/* 208 */           var10.put(var1.createString("dimensions"), a(var0, var2, var4, var7));
/* 209 */           var6.ifPresent(var2 -> var0.put(var1.createString("legacy_custom_options"), var1.createString(var2)));
/*     */           
/* 211 */           return new Dynamic(var1, var1.createMap((Map)var10.build()));case "debug_all_block_states": var4 = var0.createMap((Map)ImmutableMap.of(var0.createString("type"), var0.createString("minecraft:debug"))); var8 = var0.get("MapFeatures").asBoolean(true); var9 = var0.get("BonusChest").asBoolean(false); var10 = ImmutableMap.builder(); var10.put(var1.createString("seed"), var1.createLong(var2)); var10.put(var1.createString("generate_features"), var1.createBoolean(var8)); var10.put(var1.createString("bonus_chest"), var1.createBoolean(var9)); var10.put(var1.createString("dimensions"), a(var0, var2, var4, var7)); var6.ifPresent(var2 -> var0.put(var1.createString("legacy_custom_options"), var1.createString(var2))); return new Dynamic(var1, var1.createMap((Map)var10.build()));case "buffet": var12 = var0.get("generatorOptions"); var13 = var12.get("chunk_generator"); var14 = var13.get("type").asString().result(); if (Objects.equals(var14, Optional.of("minecraft:caves"))) { var15 = var0.createString("minecraft:caves"); var7 = true; } else if (Objects.equals(var14, Optional.of("minecraft:floating_islands"))) { var15 = var0.createString("minecraft:floating_islands"); } else { var15 = var0.createString("minecraft:overworld"); }  var16 = var12.get("biome_source").result().orElseGet(() -> var0.createMap((Map)ImmutableMap.of(var0.createString("type"), var0.createString("minecraft:fixed")))); if (var16.get("type").asString().result().equals(Optional.of("minecraft:fixed"))) { String str1 = var16.get("options").get("biomes").asStream().findFirst().flatMap(var0 -> var0.asString().result()).orElse("minecraft:ocean"); var17 = var16.remove("options").set("biome", var0.createString(str1)); } else { var17 = var16; }  var4 = a(var2, (DynamicLike<T>)var0, var15, var17); var8 = var0.get("MapFeatures").asBoolean(true); var9 = var0.get("BonusChest").asBoolean(false); var10 = ImmutableMap.builder(); var10.put(var1.createString("seed"), var1.createLong(var2)); var10.put(var1.createString("generate_features"), var1.createBoolean(var8)); var10.put(var1.createString("bonus_chest"), var1.createBoolean(var9)); var10.put(var1.createString("dimensions"), a(var0, var2, var4, var7)); var6.ifPresent(var2 -> var0.put(var1.createString("legacy_custom_options"), var1.createString(var2))); return new Dynamic(var1, var1.createMap((Map)var10.build())); }  boolean var18 = ((String)var5.get()).equals("default"); boolean var19 = (((String)var5.get()).equals("default_1_1") || (var18 && var0.get("generatorVersion").asInt(0) == 0)); boolean var20 = ((String)var5.get()).equals("amplified"); boolean var21 = ((String)var5.get()).equals("largebiomes"); var4 = a(var2, (DynamicLike<T>)var0, var0.createString(var20 ? "minecraft:amplified" : "minecraft:overworld"), a(var0, var2, var19, var21)); }  boolean bool1 = var0.get("MapFeatures").asBoolean(true); boolean bool2 = var0.get("BonusChest").asBoolean(false); ImmutableMap.Builder builder = ImmutableMap.builder(); builder.put(var1.createString("seed"), var1.createLong(var2)); builder.put(var1.createString("generate_features"), var1.createBoolean(bool1)); builder.put(var1.createString("bonus_chest"), var1.createBoolean(bool2)); builder.put(var1.createString("dimensions"), a(var0, var2, var4, var7)); var6.ifPresent(var2 -> var0.put(var1.createString("legacy_custom_options"), var1.createString(var2))); return new Dynamic(var1, var1.createMap((Map)builder.build()));
/*     */   }
/*     */   
/*     */   protected static <T> Dynamic<T> a(Dynamic<T> var0, long var1) {
/* 215 */     return a(var1, (DynamicLike<T>)var0, var0.createString("minecraft:overworld"), a(var0, var1, false, false));
/*     */   }
/*     */   
/*     */   protected static <T> T a(Dynamic<T> var0, long var1, Dynamic<T> var3, boolean var4) {
/* 219 */     DynamicOps<T> var5 = var0.getOps();
/* 220 */     return (T)var5.createMap((Map)ImmutableMap.of(var5
/* 221 */           .createString("minecraft:overworld"), var5.createMap((Map)ImmutableMap.of(var5
/* 222 */               .createString("type"), var5.createString("minecraft:overworld" + (var4 ? "_caves" : "")), var5
/* 223 */               .createString("generator"), var3.getValue())), var5
/*     */           
/* 225 */           .createString("minecraft:the_nether"), var5.createMap((Map)ImmutableMap.of(var5
/* 226 */               .createString("type"), var5.createString("minecraft:the_nether"), var5
/* 227 */               .createString("generator"), a(var1, (DynamicLike<T>)var0, var0.createString("minecraft:nether"), var0.createMap((Map)ImmutableMap.of(var0
/* 228 */                     .createString("type"), var0.createString("minecraft:multi_noise"), var0
/* 229 */                     .createString("seed"), var0.createLong(var1), var0
/* 230 */                     .createString("preset"), var0.createString("minecraft:nether"))))
/* 231 */               .getValue())), var5
/*     */           
/* 233 */           .createString("minecraft:the_end"), var5.createMap((Map)ImmutableMap.of(var5
/* 234 */               .createString("type"), var5.createString("minecraft:the_end"), var5
/* 235 */               .createString("generator"), a(var1, (DynamicLike<T>)var0, var0.createString("minecraft:end"), var0.createMap((Map)ImmutableMap.of(var0
/* 236 */                     .createString("type"), var0.createString("minecraft:the_end"), var0
/* 237 */                     .createString("seed"), var0.createLong(var1))))
/* 238 */               .getValue()))));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static <T> Map<Dynamic<T>, Dynamic<T>> a(DynamicOps<T> var0, OptionalDynamic<T> var1) {
/* 244 */     MutableInt var2 = new MutableInt(32);
/* 245 */     MutableInt var3 = new MutableInt(3);
/* 246 */     MutableInt var4 = new MutableInt(128);
/* 247 */     MutableBoolean var5 = new MutableBoolean(false);
/* 248 */     Map<String, a> var6 = Maps.newHashMap();
/*     */     
/* 250 */     if (!var1.result().isPresent()) {
/* 251 */       var5.setTrue();
/* 252 */       var6.put("minecraft:village", a.get("minecraft:village"));
/*     */     } 
/*     */     
/* 255 */     var1.get("structures").flatMap(Dynamic::getMapValues).result().ifPresent(var5 -> var5.forEach(()));
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
/* 318 */     ImmutableMap.Builder<Dynamic<T>, Dynamic<T>> var7 = ImmutableMap.builder();
/* 319 */     var7.put(var1.createString("structures"), var1.createMap((Map)var6.entrySet().stream().collect(Collectors.toMap(var1 -> var0.createString((String)var1.getKey()), var1 -> ((a)var1.getValue()).a(var0)))));
/*     */ 
/*     */ 
/*     */     
/* 323 */     if (var5.isTrue()) {
/* 324 */       var7.put(var1.createString("stronghold"), var1.createMap((Map)ImmutableMap.of(var1
/* 325 */               .createString("distance"), var1.createInt(var2.getValue().intValue()), var1
/* 326 */               .createString("spread"), var1.createInt(var3.getValue().intValue()), var1
/* 327 */               .createString("count"), var1.createInt(var4.getValue().intValue()))));
/*     */     }
/*     */     
/* 330 */     return (Map<Dynamic<T>, Dynamic<T>>)var7.build();
/*     */   }
/*     */   
/*     */   private static int a(String var0, int var1) {
/* 334 */     return NumberUtils.toInt(var0, var1);
/*     */   }
/*     */   
/*     */   private static int a(String var0, int var1, int var2) {
/* 338 */     return Math.max(var2, a(var0, var1));
/*     */   }
/*     */   
/*     */   private static void a(Map<String, a> var0, String var1, String var2, int var3) {
/* 342 */     a var4 = (a)var0.getOrDefault(var1, a.get(var1));
/* 343 */     int var5 = a(var2, a.a(var4), var3);
/* 344 */     var0.put(var1, new a(var5, a.b(var4), a.c(var4)));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterWorldGenSettingsBuilding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */