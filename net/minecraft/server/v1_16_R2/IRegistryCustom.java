/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.DataResult;
/*     */ import com.mojang.serialization.DynamicOps;
/*     */ import com.mojang.serialization.Encoder;
/*     */ import com.mojang.serialization.JsonOps;
/*     */ import com.mojang.serialization.Lifecycle;
/*     */ import com.mojang.serialization.codecs.UnboundedMapCodec;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.function.Function;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
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
/*     */ public abstract class IRegistryCustom
/*     */ {
/*  33 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   private static final Map<ResourceKey<? extends IRegistry<?>>, a<?>> b;
/*     */   private static final Dimension c;
/*     */   
/*     */   public <E> IRegistryWritable<E> b(ResourceKey<? extends IRegistry<E>> var0) {
/*  38 */     return (IRegistryWritable<E>)a(var0).orElseThrow(() -> new IllegalStateException("Missing registry: " + var0));
/*     */   }
/*     */   
/*     */   public IRegistry<DimensionManager> a() {
/*  42 */     return b(IRegistry.K);
/*     */   }
/*     */   
/*     */   static final class a<E> {
/*     */     private final ResourceKey<? extends IRegistry<E>> a;
/*     */     private final Codec<E> b;
/*     */     @Nullable
/*     */     private final Codec<E> c;
/*     */     
/*     */     public a(ResourceKey<? extends IRegistry<E>> var0, Codec<E> var1, @Nullable Codec<E> var2) {
/*  52 */       this.a = var0;
/*  53 */       this.b = var1;
/*  54 */       this.c = var2;
/*     */     }
/*     */     
/*     */     public ResourceKey<? extends IRegistry<E>> a() {
/*  58 */       return this.a;
/*     */     }
/*     */     
/*     */     public Codec<E> b() {
/*  62 */       return this.b;
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public Codec<E> c() {
/*  67 */       return this.c;
/*     */     }
/*     */     
/*     */     public boolean d() {
/*  71 */       return (this.c != null);
/*     */     }
/*     */   }
/*     */   
/*     */   static {
/*  76 */     b = SystemUtils.<Map<ResourceKey<? extends IRegistry<?>>, a<?>>>a(() -> {
/*     */           ImmutableMap.Builder<ResourceKey<? extends IRegistry<?>>, a<?>> var0 = ImmutableMap.builder();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           a(var0, IRegistry.K, DimensionManager.d, DimensionManager.d);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           a(var0, IRegistry.ay, BiomeBase.b, BiomeBase.c);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           a(var0, IRegistry.as, WorldGenSurfaceComposite.a);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           a(var0, IRegistry.at, WorldGenCarverWrapper.a);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           a(var0, IRegistry.au, WorldGenFeatureConfigured.a);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           a(var0, IRegistry.av, StructureFeature.a);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           a(var0, IRegistry.aw, DefinedStructureStructureProcessorType.l);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           a(var0, IRegistry.ax, WorldGenFeatureDefinedStructurePoolTemplate.a);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           a(var0, IRegistry.ar, GeneratorSettingBase.a);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           return var0.build();
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 150 */     c = SystemUtils.<Dimension>a(() -> {
/*     */           Dimension var0 = new Dimension();
/*     */           DimensionManager.a(var0);
/*     */           b.keySet().stream().filter(()).forEach(());
/*     */           return var0;
/*     */         });
/*     */   } private static <E> void a(ImmutableMap.Builder<ResourceKey<? extends IRegistry<?>>, a<?>> var0, ResourceKey<? extends IRegistry<E>> var1, Codec<E> var2) {
/*     */     var0.put(var1, new a<>(var1, var2, null));
/* 158 */   } public static Dimension b() { Dimension var0 = new Dimension();
/* 159 */     RegistryReadOps.b.a var1 = new RegistryReadOps.b.a();
/*     */     
/* 161 */     for (a<?> var3 : b.values()) {
/* 162 */       a(var0, var1, var3);
/*     */     }
/*     */     
/* 165 */     RegistryReadOps.a((DynamicOps<?>)JsonOps.INSTANCE, var1, var0);
/* 166 */     return var0; }
/*     */   private static <E> void a(ImmutableMap.Builder<ResourceKey<? extends IRegistry<?>>, a<?>> var0, ResourceKey<? extends IRegistry<E>> var1, Codec<E> var2, Codec<E> var3) { var0.put(var1, new a<>(var1, var2, var3)); }
/*     */   public static final class Dimension extends IRegistryCustom {
/*     */     public static final Codec<Dimension> a = d();
/* 170 */     private final Map<? extends ResourceKey<? extends IRegistry<?>>, ? extends RegistryMaterials<?>> b; private static <E> Codec<Dimension> d() { Codec<ResourceKey<? extends IRegistry<E>>> var0 = MinecraftKey.a.xmap(ResourceKey::a, ResourceKey::a); Codec<RegistryMaterials<E>> var1 = var0.partialDispatch("type", var0 -> DataResult.success(var0.f()), var0 -> c(var0).map(())); UnboundedMapCodec<? extends ResourceKey<? extends IRegistry<?>>, ? extends RegistryMaterials<?>> var2 = Codec.unboundedMap(var0, var1); return a(var2); } private static <K extends ResourceKey<? extends IRegistry<?>>, V extends RegistryMaterials<?>> Codec<Dimension> a(UnboundedMapCodec<K, V> var0) { return var0.xmap(Dimension::new, var0 -> (Map)var0.b.entrySet().stream().filter(()).collect(ImmutableMap.toImmutableMap(Map.Entry::getKey, Map.Entry::getValue))); } private static <E> DataResult<? extends Codec<E>> c(ResourceKey<? extends IRegistry<E>> var0) { return Optional.ofNullable(IRegistryCustom.c().get(var0)).map(var0 -> var0.c()).map(DataResult::success).orElseGet(() -> DataResult.error("Unknown or not serializable registry: " + var0)); } public Dimension() { this((Map<? extends ResourceKey<? extends IRegistry<?>>, ? extends RegistryMaterials<?>>)IRegistryCustom.c().keySet().stream().collect(Collectors.toMap(Function.identity(), Dimension::d))); } private Dimension(Map<? extends ResourceKey<? extends IRegistry<?>>, ? extends RegistryMaterials<?>> var0) { this.b = var0; } private static <E> RegistryMaterials<?> d(ResourceKey<? extends IRegistry<?>> var0) { return new RegistryMaterials(var0, Lifecycle.stable()); } public <E> Optional<IRegistryWritable<E>> a(ResourceKey<? extends IRegistry<E>> var0) { return Optional.ofNullable(this.b.get(var0)).map(var0 -> var0); } } private static <E> void a(Dimension var0, RegistryReadOps.b.a var1, a<E> var2) { ResourceKey<? extends IRegistry<E>> var3 = var2.a();
/*     */     
/* 172 */     boolean var4 = (!var3.equals(IRegistry.ar) && !var3.equals(IRegistry.K));
/* 173 */     IRegistry<E> var5 = c.b(var3);
/* 174 */     IRegistryWritable<E> var6 = var0.b(var3);
/* 175 */     for (Map.Entry<ResourceKey<E>, E> var8 : var5.d()) {
/* 176 */       E var9 = var8.getValue();
/* 177 */       if (var4) {
/* 178 */         var1.a(c, var8.getKey(), (Encoder<E>)var2.b(), var5.a(var9), var9, var5.d(var9)); continue;
/*     */       } 
/* 180 */       var6.a(var5.a(var9), var8.getKey(), var9, var5.d(var9));
/*     */     }  }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static <R extends IRegistry<?>> void a(Dimension var0, ResourceKey<R> var1) {
/* 187 */     IRegistry<? extends IRegistry<?>> iRegistry = RegistryGeneration.b;
/* 188 */     IRegistry<?> var3 = iRegistry.a(var1);
/* 189 */     if (var3 == null) {
/* 190 */       throw new IllegalStateException("Missing builtin registry: " + var1);
/*     */     }
/* 192 */     a(var0, var3);
/*     */   }
/*     */   
/*     */   private static <E> void a(Dimension var0, IRegistry<E> var1) {
/* 196 */     IRegistryWritable<E> var2 = (IRegistryWritable<E>)var0.a(var1.f()).orElseThrow(() -> new IllegalStateException("Missing registry: " + var0.f()));
/* 197 */     for (Map.Entry<ResourceKey<E>, E> var4 : var1.d()) {
/* 198 */       E var5 = var4.getValue();
/* 199 */       var2.a(var1.a(var5), var4.getKey(), var5, var1.d(var5));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void a(Dimension var0, RegistryReadOps<?> var1) {
/* 204 */     for (a<?> var3 : b.values()) {
/* 205 */       a(var1, var0, var3);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static <E> void a(RegistryReadOps<?> var0, Dimension var1, a<E> var2) {
/* 211 */     ResourceKey<? extends IRegistry<E>> var3 = var2.a();
/* 212 */     RegistryMaterials<E> var4 = (RegistryMaterials<E>)Optional.ofNullable(Dimension.a(var1).get(var3)).map(var0 -> var0).orElseThrow(() -> new IllegalStateException("Missing registry: " + var0));
/* 213 */     DataResult<RegistryMaterials<E>> var5 = var0.a(var4, var2.a(), var2.b());
/* 214 */     var5.error().ifPresent(var0 -> LOGGER.error("Error loading registry data: {}", var0.message()));
/*     */   }
/*     */   
/*     */   public abstract <E> Optional<IRegistryWritable<E>> a(ResourceKey<? extends IRegistry<E>> paramResourceKey);
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IRegistryCustom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */