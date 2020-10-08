/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.mojang.datafixers.kinds.App;
/*     */ import com.mojang.datafixers.kinds.Applicative;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.Lifecycle;
/*     */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.function.BiFunction;
/*     */ import java.util.function.Supplier;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class WorldDimension
/*     */ {
/*     */   public static final Codec<WorldDimension> a;
/*     */   
/*     */   static {
/*  25 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)DimensionManager.n.fieldOf("type").forGetter(WorldDimension::a), (App)ChunkGenerator.a.fieldOf("generator").forGetter(WorldDimension::c)).apply((Applicative)var0, var0.stable(WorldDimension::new)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  30 */   public static final ResourceKey<WorldDimension> OVERWORLD = ResourceKey.a(IRegistry.M, new MinecraftKey("overworld"));
/*  31 */   public static final ResourceKey<WorldDimension> THE_NETHER = ResourceKey.a(IRegistry.M, new MinecraftKey("the_nether"));
/*  32 */   public static final ResourceKey<WorldDimension> THE_END = ResourceKey.a(IRegistry.M, new MinecraftKey("the_end"));
/*     */   
/*  34 */   private static final LinkedHashSet<ResourceKey<WorldDimension>> e = Sets.newLinkedHashSet((Iterable)ImmutableList.of(OVERWORLD, THE_NETHER, THE_END));
/*     */ 
/*     */   
/*     */   private final Supplier<DimensionManager> f;
/*     */ 
/*     */   
/*     */   private final ChunkGenerator g;
/*     */ 
/*     */   
/*     */   public WorldDimension(Supplier<DimensionManager> var0, ChunkGenerator var1) {
/*  44 */     this.f = var0;
/*  45 */     this.g = var1;
/*     */   }
/*     */   
/*     */   public Supplier<DimensionManager> a() {
/*  49 */     return this.f;
/*     */   }
/*     */   
/*     */   public DimensionManager b() {
/*  53 */     return this.f.get();
/*     */   }
/*     */   
/*     */   public ChunkGenerator c() {
/*  57 */     return this.g;
/*     */   }
/*     */   
/*     */   public static RegistryMaterials<WorldDimension> a(RegistryMaterials<WorldDimension> var0) {
/*  61 */     RegistryMaterials<WorldDimension> var1 = new RegistryMaterials<>(IRegistry.M, Lifecycle.experimental());
/*  62 */     for (ResourceKey<WorldDimension> var3 : e) {
/*  63 */       WorldDimension var4 = var0.a(var3);
/*  64 */       if (var4 != null) {
/*  65 */         var1.a(var3, var4, var0.d(var4));
/*     */       }
/*     */     } 
/*  68 */     for (Map.Entry<ResourceKey<WorldDimension>, WorldDimension> var3 : var0.d()) {
/*  69 */       ResourceKey<WorldDimension> var4 = var3.getKey();
/*  70 */       if (e.contains(var4)) {
/*     */         continue;
/*     */       }
/*  73 */       var1.a(var4, var3.getValue(), var0.d(var3.getValue()));
/*     */     } 
/*  75 */     return var1;
/*     */   }
/*     */   
/*     */   public static boolean a(long var0, RegistryMaterials<WorldDimension> var2) {
/*  79 */     List<Map.Entry<ResourceKey<WorldDimension>, WorldDimension>> var3 = Lists.newArrayList(var2.d());
/*     */     
/*  81 */     if (var3.size() != e.size()) {
/*  82 */       return false;
/*     */     }
/*     */     
/*  85 */     Map.Entry<ResourceKey<WorldDimension>, WorldDimension> var4 = var3.get(0);
/*  86 */     Map.Entry<ResourceKey<WorldDimension>, WorldDimension> var5 = var3.get(1);
/*  87 */     Map.Entry<ResourceKey<WorldDimension>, WorldDimension> var6 = var3.get(2);
/*     */     
/*  89 */     if (var4.getKey() != OVERWORLD || var5.getKey() != THE_NETHER || var6.getKey() != THE_END) {
/*  90 */       return false;
/*     */     }
/*     */     
/*  93 */     if (!((WorldDimension)var4.getValue()).b().a(DimensionManager.OVERWORLD_IMPL) && ((WorldDimension)var4.getValue()).b() != DimensionManager.m) {
/*  94 */       return false;
/*     */     }
/*     */     
/*  97 */     if (!((WorldDimension)var5.getValue()).b().a(DimensionManager.THE_NETHER_IMPL)) {
/*  98 */       return false;
/*     */     }
/*     */     
/* 101 */     if (!((WorldDimension)var6.getValue()).b().a(DimensionManager.THE_END_IMPL)) {
/* 102 */       return false;
/*     */     }
/*     */     
/* 105 */     if (!(((WorldDimension)var5.getValue()).c() instanceof ChunkGeneratorAbstract) || !(((WorldDimension)var6.getValue()).c() instanceof ChunkGeneratorAbstract)) {
/* 106 */       return false;
/*     */     }
/*     */     
/* 109 */     ChunkGeneratorAbstract var7 = (ChunkGeneratorAbstract)((WorldDimension)var5.getValue()).c();
/* 110 */     ChunkGeneratorAbstract var8 = (ChunkGeneratorAbstract)((WorldDimension)var6.getValue()).c();
/*     */     
/* 112 */     if (!var7.a(var0, GeneratorSettingBase.e)) {
/* 113 */       return false;
/*     */     }
/*     */     
/* 116 */     if (!var8.a(var0, GeneratorSettingBase.f)) {
/* 117 */       return false;
/*     */     }
/*     */     
/* 120 */     if (!(var7.getWorldChunkManager() instanceof WorldChunkManagerMultiNoise)) {
/* 121 */       return false;
/*     */     }
/*     */     
/* 124 */     WorldChunkManagerMultiNoise var9 = (WorldChunkManagerMultiNoise)var7.getWorldChunkManager();
/* 125 */     if (!var9.b(var0)) {
/* 126 */       return false;
/*     */     }
/*     */     
/* 129 */     if (!(var8.getWorldChunkManager() instanceof WorldChunkManagerTheEnd)) {
/* 130 */       return false;
/*     */     }
/*     */     
/* 133 */     WorldChunkManagerTheEnd var10 = (WorldChunkManagerTheEnd)var8.getWorldChunkManager();
/* 134 */     if (!var10.b(var0)) {
/* 135 */       return false;
/*     */     }
/*     */     
/* 138 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldDimension.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */