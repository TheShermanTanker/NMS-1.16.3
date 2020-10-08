/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.mojang.serialization.Lifecycle;
/*    */ import java.util.Map;
/*    */ import java.util.function.Supplier;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class RegistryGeneration
/*    */ {
/* 12 */   protected static final Logger LOGGER = LogManager.getLogger();
/* 13 */   private static final Map<MinecraftKey, Supplier<?>> k = Maps.newLinkedHashMap();
/* 14 */   private static final IRegistryWritable<IRegistryWritable<?>> l = new RegistryMaterials<>((ResourceKey)ResourceKey.a(new MinecraftKey("root")), Lifecycle.experimental());
/* 15 */   public static final IRegistry<? extends IRegistry<?>> b = (IRegistry)l;
/* 16 */   public static final IRegistry<WorldGenSurfaceComposite<?>> c = a(IRegistry.as, () -> WorldGenSurfaceComposites.p);
/*    */ 
/*    */   
/* 19 */   public static final IRegistry<WorldGenCarverWrapper<?>> d = a(IRegistry.at, () -> WorldGenCarvers.a);
/*    */ 
/*    */   
/* 22 */   public static final IRegistry<WorldGenFeatureConfigured<?, ?>> e = a(IRegistry.au, () -> BiomeDecoratorGroups.OAK);
/*    */ 
/*    */   
/* 25 */   public static final IRegistry<StructureFeature<?, ?>> f = a(IRegistry.av, () -> StructureFeatures.b);
/*    */ 
/*    */   
/* 28 */   public static final IRegistry<ProcessorList> g = a(IRegistry.aw, () -> ProcessorLists.b);
/*    */ 
/*    */   
/* 31 */   public static final IRegistry<WorldGenFeatureDefinedStructurePoolTemplate> h = a(IRegistry.ax, () -> WorldGenFeaturePieces.a());
/* 32 */   public static final IRegistry<BiomeBase> WORLDGEN_BIOME = a(IRegistry.ay, () -> BiomeRegistry.a);
/*    */ 
/*    */   
/* 35 */   public static final IRegistry<GeneratorSettingBase> j = a(IRegistry.ar, () -> GeneratorSettingBase.i());
/*    */   
/*    */   private static <T> IRegistry<T> a(ResourceKey<? extends IRegistry<T>> resourcekey, Supplier<T> supplier) {
/* 38 */     return a(resourcekey, Lifecycle.stable(), supplier);
/*    */   }
/*    */   
/*    */   private static <T> IRegistry<T> a(ResourceKey<? extends IRegistry<T>> resourcekey, Lifecycle lifecycle, Supplier<T> supplier) {
/* 42 */     return a(resourcekey, new RegistryMaterials<>(resourcekey, lifecycle), supplier, lifecycle);
/*    */   }
/*    */   
/*    */   private static <T, R extends IRegistryWritable<T>> R a(ResourceKey<? extends IRegistry<T>> resourcekey, R r0, Supplier<T> supplier, Lifecycle lifecycle) {
/* 46 */     MinecraftKey minecraftkey = resourcekey.a();
/*    */     
/* 48 */     k.put(minecraftkey, supplier);
/* 49 */     IRegistryWritable<IRegistryWritable<?>> iRegistryWritable = l;
/*    */     
/* 51 */     return (R)iRegistryWritable.<IRegistryWritable>a(resourcekey, (IRegistryWritable)r0, lifecycle);
/*    */   }
/*    */   
/*    */   public static <T> T a(IRegistry<? super T> iregistry, String s, T t0) {
/* 55 */     return a(iregistry, new MinecraftKey(s), t0);
/*    */   }
/*    */   
/*    */   public static <V, T extends V> T a(IRegistry<V> iregistry, MinecraftKey minecraftkey, T t0) {
/* 59 */     return (T)((IRegistryWritable)iregistry).a(ResourceKey.a(iregistry.f(), minecraftkey), t0, Lifecycle.stable());
/*    */   }
/*    */   
/*    */   public static <V, T extends V> T a(IRegistry<V> iregistry, int i, ResourceKey<V> resourcekey, T t0) {
/* 63 */     return (T)((IRegistryWritable)iregistry).a(i, resourcekey, t0, Lifecycle.stable());
/*    */   }
/*    */   
/*    */   public static void a() {}
/*    */   
/*    */   static {
/* 69 */     k.forEach((minecraftkey, supplier) -> {
/*    */           if (supplier.get() == null) {
/*    */             LOGGER.error("Unable to bootstrap registry '{}'", minecraftkey);
/*    */           }
/*    */         });
/*    */     
/* 75 */     IRegistry.a(l);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RegistryGeneration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */