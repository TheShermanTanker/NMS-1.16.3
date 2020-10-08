/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.datafixers.DataFixUtils;
/*     */ import com.mojang.datafixers.kinds.App;
/*     */ import com.mojang.datafixers.kinds.Applicative;
/*     */ import com.mojang.datafixers.util.Function4;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.MapCodec;
/*     */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*     */ import java.util.Collection;
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
/*     */ public class BiomeSettingsGeneration
/*     */ {
/*  33 */   public static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*  35 */   public static final BiomeSettingsGeneration b = new BiomeSettingsGeneration(() -> WorldGenSurfaceComposites.p, 
/*     */       
/*  37 */       (Map<WorldGenStage.Features, List<Supplier<WorldGenCarverWrapper<?>>>>)ImmutableMap.of(), 
/*  38 */       (List<List<Supplier<WorldGenFeatureConfigured<?, ?>>>>)ImmutableList.of(), 
/*  39 */       (List<Supplier<StructureFeature<?, ?>>>)ImmutableList.of());
/*     */   
/*     */   static {
/*  42 */     c = RecordCodecBuilder.mapCodec(var0 -> var0.group((App)WorldGenSurfaceComposite.b.fieldOf("surface_builder").forGetter(()), (App)Codec.simpleMap(WorldGenStage.Features.c, WorldGenCarverWrapper.c.promotePartial(SystemUtils.a("Carver: ", LOGGER::error)), INamable.a((INamable[])WorldGenStage.Features.values())).fieldOf("carvers").forGetter(()), (App)WorldGenFeatureConfigured.c.promotePartial(SystemUtils.a("Feature: ", LOGGER::error)).listOf().fieldOf("features").forGetter(()), (App)StructureFeature.c.promotePartial(SystemUtils.a("Structure start: ", LOGGER::error)).fieldOf("starts").forGetter(())).apply((Applicative)var0, BiomeSettingsGeneration::new));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static final MapCodec<BiomeSettingsGeneration> c;
/*     */   
/*     */   private final Supplier<WorldGenSurfaceComposite<?>> d;
/*     */   
/*     */   private final Map<WorldGenStage.Features, List<Supplier<WorldGenCarverWrapper<?>>>> e;
/*     */   
/*     */   private final List<List<Supplier<WorldGenFeatureConfigured<?, ?>>>> f;
/*     */   
/*     */   private final List<Supplier<StructureFeature<?, ?>>> g;
/*     */   
/*     */   private final List<WorldGenFeatureConfigured<?, ?>> h;
/*     */ 
/*     */   
/*     */   private BiomeSettingsGeneration(Supplier<WorldGenSurfaceComposite<?>> var0, Map<WorldGenStage.Features, List<Supplier<WorldGenCarverWrapper<?>>>> var1, List<List<Supplier<WorldGenFeatureConfigured<?, ?>>>> var2, List<Supplier<StructureFeature<?, ?>>> var3) {
/*  61 */     this.d = var0;
/*  62 */     this.e = var1;
/*  63 */     this.f = var2;
/*  64 */     this.g = var3;
/*     */     
/*  66 */     this.h = (List<WorldGenFeatureConfigured<?, ?>>)var2.stream().flatMap(Collection::stream).map(Supplier::get).flatMap(WorldGenFeatureConfigured::d).filter(var0 -> (var0.e == WorldGenerator.FLOWER)).collect(ImmutableList.toImmutableList());
/*     */   }
/*     */   
/*     */   public List<Supplier<WorldGenCarverWrapper<?>>> a(WorldGenStage.Features var0) {
/*  70 */     return (List<Supplier<WorldGenCarverWrapper<?>>>)this.e.getOrDefault(var0, ImmutableList.of());
/*     */   }
/*     */   
/*     */   public boolean a(StructureGenerator<?> var0) {
/*  74 */     return this.g.stream().anyMatch(var1 -> (((StructureFeature)var1.get()).d == var0));
/*     */   }
/*     */   
/*     */   public Collection<Supplier<StructureFeature<?, ?>>> a() {
/*  78 */     return this.g;
/*     */   }
/*     */   
/*     */   public StructureFeature<?, ?> a(StructureFeature<?, ?> var0) {
/*  82 */     return (StructureFeature<?, ?>)DataFixUtils.orElse(this.g.stream().map(Supplier::get).filter(var1 -> (var1.d == var0.d)).findAny(), var0);
/*     */   }
/*     */   
/*     */   public List<WorldGenFeatureConfigured<?, ?>> b() {
/*  86 */     return this.h;
/*     */   }
/*     */   
/*     */   public List<List<Supplier<WorldGenFeatureConfigured<?, ?>>>> c() {
/*  90 */     return this.f;
/*     */   }
/*     */   
/*     */   public Supplier<WorldGenSurfaceComposite<?>> d() {
/*  94 */     return this.d;
/*     */   }
/*     */   
/*     */   public WorldGenSurfaceConfiguration e() {
/*  98 */     return ((WorldGenSurfaceComposite<WorldGenSurfaceConfiguration>)this.d.get()).a();
/*     */   }
/*     */   
/*     */   public static class a {
/* 102 */     private Optional<Supplier<WorldGenSurfaceComposite<?>>> a = Optional.empty();
/* 103 */     private final Map<WorldGenStage.Features, List<Supplier<WorldGenCarverWrapper<?>>>> b = Maps.newLinkedHashMap();
/* 104 */     private final List<List<Supplier<WorldGenFeatureConfigured<?, ?>>>> c = Lists.newArrayList();
/* 105 */     private final List<Supplier<StructureFeature<?, ?>>> d = Lists.newArrayList();
/*     */     
/*     */     public a a(WorldGenSurfaceComposite<?> var0) {
/* 108 */       return a(() -> var0);
/*     */     }
/*     */     
/*     */     public a a(Supplier<WorldGenSurfaceComposite<?>> var0) {
/* 112 */       this.a = Optional.of(var0);
/* 113 */       return this;
/*     */     }
/*     */     
/*     */     public a a(WorldGenStage.Decoration var0, WorldGenFeatureConfigured<?, ?> var1) {
/* 117 */       return a(var0.ordinal(), () -> var0);
/*     */     }
/*     */     
/*     */     public a a(int var0, Supplier<WorldGenFeatureConfigured<?, ?>> var1) {
/* 121 */       a(var0);
/* 122 */       ((List<Supplier<WorldGenFeatureConfigured<?, ?>>>)this.c.get(var0)).add(var1);
/* 123 */       return this;
/*     */     }
/*     */     
/*     */     public <C extends WorldGenCarverConfiguration> a a(WorldGenStage.Features var0, WorldGenCarverWrapper<C> var1) {
/* 127 */       ((List<Supplier>)this.b.computeIfAbsent(var0, var0 -> Lists.newArrayList())).add(() -> var0);
/* 128 */       return this;
/*     */     }
/*     */     
/*     */     public a a(StructureFeature<?, ?> var0) {
/* 132 */       this.d.add(() -> var0);
/* 133 */       return this;
/*     */     }
/*     */     
/*     */     private void a(int var0) {
/* 137 */       while (this.c.size() <= var0) {
/* 138 */         this.c.add(Lists.newArrayList());
/*     */       }
/*     */     }
/*     */     
/*     */     public BiomeSettingsGeneration a() {
/* 143 */       return new BiomeSettingsGeneration(this.a
/* 144 */           .<Throwable>orElseThrow(() -> new IllegalStateException("Missing surface builder")), (Map)this.b
/* 145 */           .entrySet().stream().collect(ImmutableMap.toImmutableMap(Map.Entry::getKey, var0 -> ImmutableList.copyOf((Collection)var0.getValue()))), (List)this.c
/* 146 */           .stream().map(ImmutableList::copyOf).collect(ImmutableList.toImmutableList()), 
/* 147 */           (List)ImmutableList.copyOf(this.d));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BiomeSettingsGeneration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */