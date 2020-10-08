/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.DataResult;
/*     */ import com.mojang.serialization.DynamicOps;
/*     */ import com.mojang.serialization.Keyable;
/*     */ import com.mojang.serialization.Lifecycle;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Stream;
/*     */ import java.util.stream.StreamSupport;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.Validate;
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
/*     */ public abstract class IRegistry<T>
/*     */   implements Codec<T>, Keyable, Registry<T>
/*     */ {
/*  97 */   protected static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*  99 */   private static final Map<MinecraftKey, Supplier<?>> a = Maps.newLinkedHashMap();
/*     */   
/* 101 */   public static final MinecraftKey d = new MinecraftKey("root");
/* 102 */   protected static final IRegistryWritable<IRegistryWritable<?>> e = new RegistryMaterials<>((ResourceKey)a("root"), Lifecycle.experimental());
/* 103 */   public static final IRegistry<? extends IRegistry<?>> f = (IRegistry)e;
/*     */   
/* 105 */   public static final ResourceKey<IRegistry<SoundEffect>> g = a("sound_event");
/* 106 */   public static final ResourceKey<IRegistry<FluidType>> h = a("fluid");
/* 107 */   public static final ResourceKey<IRegistry<MobEffectList>> i = a("mob_effect");
/* 108 */   public static final ResourceKey<IRegistry<Block>> j = a("block");
/* 109 */   public static final ResourceKey<IRegistry<Enchantment>> k = a("enchantment");
/* 110 */   public static final ResourceKey<IRegistry<EntityTypes<?>>> l = a("entity_type");
/* 111 */   public static final ResourceKey<IRegistry<Item>> m = a("item");
/* 112 */   public static final ResourceKey<IRegistry<PotionRegistry>> n = a("potion");
/* 113 */   public static final ResourceKey<IRegistry<Particle<?>>> o = a("particle_type");
/* 114 */   public static final ResourceKey<IRegistry<TileEntityTypes<?>>> p = a("block_entity_type");
/* 115 */   public static final ResourceKey<IRegistry<Paintings>> q = a("motive");
/* 116 */   public static final ResourceKey<IRegistry<MinecraftKey>> r = a("custom_stat");
/* 117 */   public static final ResourceKey<IRegistry<ChunkStatus>> s = a("chunk_status");
/* 118 */   public static final ResourceKey<IRegistry<DefinedStructureRuleTestType<?>>> t = a("rule_test");
/* 119 */   public static final ResourceKey<IRegistry<PosRuleTestType<?>>> u = a("pos_rule_test");
/* 120 */   public static final ResourceKey<IRegistry<Containers<?>>> v = a("menu");
/* 121 */   public static final ResourceKey<IRegistry<Recipes<?>>> w = a("recipe_type");
/* 122 */   public static final ResourceKey<IRegistry<RecipeSerializer<?>>> x = a("recipe_serializer");
/* 123 */   public static final ResourceKey<IRegistry<AttributeBase>> y = a("attribute");
/*     */   
/* 125 */   public static final ResourceKey<IRegistry<StatisticWrapper<?>>> z = a("stat_type");
/*     */   
/* 127 */   public static final ResourceKey<IRegistry<VillagerType>> A = a("villager_type");
/* 128 */   public static final ResourceKey<IRegistry<VillagerProfession>> B = a("villager_profession");
/* 129 */   public static final ResourceKey<IRegistry<VillagePlaceType>> C = a("point_of_interest_type");
/* 130 */   public static final ResourceKey<IRegistry<MemoryModuleType<?>>> D = a("memory_module_type");
/* 131 */   public static final ResourceKey<IRegistry<SensorType<?>>> E = a("sensor_type");
/*     */   
/* 133 */   public static final ResourceKey<IRegistry<Schedule>> F = a("schedule");
/* 134 */   public static final ResourceKey<IRegistry<Activity>> G = a("activity");
/*     */   
/* 136 */   public static final ResourceKey<IRegistry<LootEntryType>> H = a("loot_pool_entry_type");
/* 137 */   public static final ResourceKey<IRegistry<LootItemFunctionType>> I = a("loot_function_type");
/* 138 */   public static final ResourceKey<IRegistry<LootItemConditionType>> J = a("loot_condition_type");
/*     */   
/* 140 */   public static final ResourceKey<IRegistry<DimensionManager>> K = a("dimension_type");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 145 */   public static final ResourceKey<IRegistry<World>> L = a("dimension");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 150 */   public static final ResourceKey<IRegistry<WorldDimension>> M = a("dimension");
/*     */   
/* 152 */   public static final IRegistry<SoundEffect> SOUND_EVENT = a(g, () -> SoundEffects.ENTITY_ITEM_PICKUP);
/* 153 */   public static final RegistryBlocks<FluidType> FLUID = a(h, "empty", () -> FluidTypes.EMPTY);
/* 154 */   public static final IRegistry<MobEffectList> MOB_EFFECT = a(i, () -> MobEffects.LUCK);
/* 155 */   public static final RegistryBlocks<Block> BLOCK = a(j, "air", () -> Blocks.AIR);
/* 156 */   public static final IRegistry<Enchantment> ENCHANTMENT = a(k, () -> Enchantments.LOOT_BONUS_BLOCKS);
/* 157 */   public static final RegistryBlocks<EntityTypes<?>> ENTITY_TYPE = a(l, "pig", () -> EntityTypes.PIG);
/* 158 */   public static final RegistryBlocks<Item> ITEM = a(m, "air", () -> Items.AIR);
/* 159 */   public static final RegistryBlocks<PotionRegistry> POTION = a(n, "empty", () -> Potions.EMPTY);
/* 160 */   public static final IRegistry<Particle<?>> PARTICLE_TYPE = a(o, () -> Particles.BLOCK);
/* 161 */   public static final IRegistry<TileEntityTypes<?>> BLOCK_ENTITY_TYPE = a(p, () -> TileEntityTypes.FURNACE);
/* 162 */   public static final RegistryBlocks<Paintings> MOTIVE = a(q, "kebab", () -> Paintings.a);
/* 163 */   public static final IRegistry<MinecraftKey> CUSTOM_STAT = a(r, () -> StatisticList.JUMP);
/* 164 */   public static final RegistryBlocks<ChunkStatus> CHUNK_STATUS = a(s, "empty", () -> ChunkStatus.EMPTY);
/* 165 */   public static final IRegistry<DefinedStructureRuleTestType<?>> RULE_TEST = a(t, () -> DefinedStructureRuleTestType.a);
/* 166 */   public static final IRegistry<PosRuleTestType<?>> POS_RULE_TEST = a(u, () -> PosRuleTestType.a);
/* 167 */   public static final IRegistry<Containers<?>> MENU = a(v, () -> Containers.ANVIL);
/* 168 */   public static final IRegistry<Recipes<?>> RECIPE_TYPE = a(w, () -> Recipes.CRAFTING);
/* 169 */   public static final IRegistry<RecipeSerializer<?>> RECIPE_SERIALIZER = a(x, () -> RecipeSerializer.b);
/* 170 */   public static final IRegistry<AttributeBase> ATTRIBUTE = a(y, () -> GenericAttributes.LUCK);
/*     */   
/* 172 */   public static final IRegistry<StatisticWrapper<?>> STATS = a(z, () -> StatisticList.ITEM_USED);
/*     */   
/* 174 */   public static final RegistryBlocks<VillagerType> VILLAGER_TYPE = a(A, "plains", () -> VillagerType.PLAINS);
/* 175 */   public static final RegistryBlocks<VillagerProfession> VILLAGER_PROFESSION = a(B, "none", () -> VillagerProfession.NONE);
/* 176 */   public static final RegistryBlocks<VillagePlaceType> POINT_OF_INTEREST_TYPE = a(C, "unemployed", () -> VillagePlaceType.c);
/* 177 */   public static final RegistryBlocks<MemoryModuleType<?>> MEMORY_MODULE_TYPE = a(D, "dummy", () -> MemoryModuleType.DUMMY);
/* 178 */   public static final RegistryBlocks<SensorType<?>> SENSOR_TYPE = a(E, "dummy", () -> SensorType.a);
/*     */   
/* 180 */   public static final IRegistry<Schedule> SCHEDULE = a(F, () -> Schedule.EMPTY);
/* 181 */   public static final IRegistry<Activity> ACTIVITY = a(G, () -> Activity.IDLE);
/*     */   
/* 183 */   public static final IRegistry<LootEntryType> ao = a(H, () -> LootEntries.a);
/* 184 */   public static final IRegistry<LootItemFunctionType> ap = a(I, () -> LootItemFunctions.b);
/* 185 */   public static final IRegistry<LootItemConditionType> aq = a(J, () -> LootItemConditions.a);
/*     */   
/* 187 */   public static final ResourceKey<IRegistry<GeneratorSettingBase>> ar = a("worldgen/noise_settings");
/*     */   
/* 189 */   public static final ResourceKey<IRegistry<WorldGenSurfaceComposite<?>>> as = a("worldgen/configured_surface_builder");
/* 190 */   public static final ResourceKey<IRegistry<WorldGenCarverWrapper<?>>> at = a("worldgen/configured_carver");
/* 191 */   public static final ResourceKey<IRegistry<WorldGenFeatureConfigured<?, ?>>> au = a("worldgen/configured_feature");
/* 192 */   public static final ResourceKey<IRegistry<StructureFeature<?, ?>>> av = a("worldgen/configured_structure_feature");
/* 193 */   public static final ResourceKey<IRegistry<ProcessorList>> aw = a("worldgen/processor_list");
/* 194 */   public static final ResourceKey<IRegistry<WorldGenFeatureDefinedStructurePoolTemplate>> ax = a("worldgen/template_pool");
/* 195 */   public static final ResourceKey<IRegistry<BiomeBase>> ay = a("worldgen/biome");
/*     */   
/* 197 */   public static final ResourceKey<IRegistry<WorldGenSurface<?>>> az = a("worldgen/surface_builder");
/* 198 */   public static final IRegistry<WorldGenSurface<?>> SURFACE_BUILDER = a(az, () -> WorldGenSurface.v);
/*     */   
/* 200 */   public static final ResourceKey<IRegistry<WorldGenCarverAbstract<?>>> aB = a("worldgen/carver");
/* 201 */   public static final IRegistry<WorldGenCarverAbstract<?>> CARVER = a(aB, () -> WorldGenCarverAbstract.a);
/*     */   
/* 203 */   public static final ResourceKey<IRegistry<WorldGenerator<?>>> aD = a("worldgen/feature");
/* 204 */   public static final IRegistry<WorldGenerator<?>> FEATURE = a(aD, () -> WorldGenerator.ORE);
/*     */   
/* 206 */   public static final ResourceKey<IRegistry<StructureGenerator<?>>> aF = a("worldgen/structure_feature");
/* 207 */   public static final IRegistry<StructureGenerator<?>> STRUCTURE_FEATURE = a(aF, () -> StructureGenerator.MINESHAFT);
/*     */   
/* 209 */   public static final ResourceKey<IRegistry<WorldGenFeatureStructurePieceType>> aH = a("worldgen/structure_piece");
/* 210 */   public static final IRegistry<WorldGenFeatureStructurePieceType> STRUCTURE_PIECE = a(aH, () -> WorldGenFeatureStructurePieceType.c);
/* 211 */   public static final ResourceKey<IRegistry<WorldGenDecorator<?>>> aJ = a("worldgen/decorator");
/* 212 */   public static final IRegistry<WorldGenDecorator<?>> DECORATOR = a(aJ, () -> WorldGenDecorator.a);
/*     */   
/* 214 */   public static final ResourceKey<IRegistry<WorldGenFeatureStateProviders<?>>> aL = a("worldgen/block_state_provider_type");
/* 215 */   public static final ResourceKey<IRegistry<WorldGenBlockPlacers<?>>> aM = a("worldgen/block_placer_type");
/* 216 */   public static final ResourceKey<IRegistry<WorldGenFoilagePlacers<?>>> aN = a("worldgen/foliage_placer_type");
/* 217 */   public static final ResourceKey<IRegistry<TrunkPlacers<?>>> aO = a("worldgen/trunk_placer_type");
/* 218 */   public static final ResourceKey<IRegistry<WorldGenFeatureTrees<?>>> aP = a("worldgen/tree_decorator_type");
/* 219 */   public static final ResourceKey<IRegistry<FeatureSizeType<?>>> aQ = a("worldgen/feature_size_type");
/* 220 */   public static final ResourceKey<IRegistry<Codec<? extends WorldChunkManager>>> aR = a("worldgen/biome_source");
/* 221 */   public static final ResourceKey<IRegistry<Codec<? extends ChunkGenerator>>> aS = a("worldgen/chunk_generator");
/* 222 */   public static final ResourceKey<IRegistry<DefinedStructureStructureProcessorType<?>>> aT = a("worldgen/structure_processor");
/* 223 */   public static final ResourceKey<IRegistry<WorldGenFeatureDefinedStructurePools<?>>> aU = a("worldgen/structure_pool_element");
/*     */   
/* 225 */   public static final IRegistry<WorldGenFeatureStateProviders<?>> BLOCK_STATE_PROVIDER_TYPE = a(aL, () -> WorldGenFeatureStateProviders.a);
/* 226 */   public static final IRegistry<WorldGenBlockPlacers<?>> BLOCK_PLACER_TYPE = a(aM, () -> WorldGenBlockPlacers.a);
/* 227 */   public static final IRegistry<WorldGenFoilagePlacers<?>> FOLIAGE_PLACER_TYPE = a(aN, () -> WorldGenFoilagePlacers.a);
/* 228 */   public static final IRegistry<TrunkPlacers<?>> TRUNK_PLACER_TYPE = a(aO, () -> TrunkPlacers.a);
/* 229 */   public static final IRegistry<WorldGenFeatureTrees<?>> TREE_DECORATOR_TYPE = a(aP, () -> WorldGenFeatureTrees.b);
/* 230 */   public static final IRegistry<FeatureSizeType<?>> FEATURE_SIZE_TYPE = a(aQ, () -> FeatureSizeType.a);
/* 231 */   public static final IRegistry<Codec<? extends WorldChunkManager>> BIOME_SOURCE = a(aR, Lifecycle.stable(), () -> WorldChunkManager.a);
/* 232 */   public static final IRegistry<Codec<? extends ChunkGenerator>> CHUNK_GENERATOR = a(aS, Lifecycle.stable(), () -> ChunkGenerator.a);
/* 233 */   public static final IRegistry<DefinedStructureStructureProcessorType<?>> STRUCTURE_PROCESSOR = a(aT, () -> DefinedStructureStructureProcessorType.a);
/* 234 */   public static final IRegistry<WorldGenFeatureDefinedStructurePools<?>> STRUCTURE_POOL_ELEMENT = a(aU, () -> WorldGenFeatureDefinedStructurePools.d); private final ResourceKey<? extends IRegistry<T>> b;
/*     */   
/*     */   static {
/* 237 */     RegistryGeneration.a();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 245 */     a.forEach((var0, var1) -> {
/*     */           if (var1.get() == null) {
/*     */             LOGGER.error("Unable to bootstrap registry '{}'", var0);
/*     */           }
/*     */         });
/*     */     
/* 251 */     a(e);
/*     */   }
/*     */   private final Lifecycle bf;
/*     */   public static <T extends IRegistryWritable<?>> void a(IRegistryWritable<T> var0) {
/* 255 */     var0.forEach(var1 -> {
/*     */           if (var1.keySet().isEmpty()) {
/*     */             LOGGER.error("Registry '{}' was empty after loading", var0.getKey(var1));
/*     */             if (SharedConstants.d)
/*     */               throw new IllegalStateException("Registry: '" + var0.getKey(var1) + "' is empty, not allowed, fix me!"); 
/*     */           } 
/*     */           if (var1 instanceof RegistryBlocks) {
/*     */             MinecraftKey var2 = ((RegistryBlocks)var1).a();
/*     */             Validate.notNull(var1.get(var2), "Missing default of DefaultedMappedRegistry: " + var2, new Object[0]);
/*     */           } 
/*     */         });
/*     */   }
/*     */   private static <T> ResourceKey<IRegistry<T>> a(String var0) {
/*     */     return ResourceKey.a(new MinecraftKey(var0));
/*     */   }
/*     */   private static <T> IRegistry<T> a(ResourceKey<? extends IRegistry<T>> var0, Supplier<T> var1) {
/* 271 */     return a(var0, Lifecycle.experimental(), var1);
/*     */   }
/*     */   
/*     */   private static <T> RegistryBlocks<T> a(ResourceKey<? extends IRegistry<T>> var0, String var1, Supplier<T> var2) {
/* 275 */     return a(var0, var1, Lifecycle.experimental(), var2);
/*     */   }
/*     */   
/*     */   private static <T> IRegistry<T> a(ResourceKey<? extends IRegistry<T>> var0, Lifecycle var1, Supplier<T> var2) {
/* 279 */     return a(var0, new RegistryMaterials<>(var0, var1), var2, var1);
/*     */   }
/*     */   
/*     */   private static <T> RegistryBlocks<T> a(ResourceKey<? extends IRegistry<T>> var0, String var1, Lifecycle var2, Supplier<T> var3) {
/* 283 */     return a(var0, new RegistryBlocks<>(var1, var0, var2), var3, var2);
/*     */   }
/*     */ 
/*     */   
/*     */   private static <T, R extends IRegistryWritable<T>> R a(ResourceKey<? extends IRegistry<T>> var0, R var1, Supplier<T> var2, Lifecycle var3) {
/* 288 */     MinecraftKey var4 = var0.a();
/* 289 */     a.put(var4, var2);
/*     */     
/* 291 */     IRegistryWritable<IRegistryWritable<?>> iRegistryWritable = e;
/* 292 */     return (R)iRegistryWritable.<IRegistryWritable>a(var0, (IRegistryWritable)var1, var3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected IRegistry(ResourceKey<? extends IRegistry<T>> var0, Lifecycle var1) {
/* 299 */     this.b = var0;
/* 300 */     this.bf = var1;
/*     */   }
/*     */   
/*     */   public ResourceKey<? extends IRegistry<T>> f() {
/* 304 */     return this.b;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 309 */     return "Registry[" + this.b + " (" + this.bf + ")]";
/*     */   }
/*     */ 
/*     */   
/*     */   public <U> DataResult<Pair<T, U>> decode(DynamicOps<U> var0, U var1) {
/* 314 */     if (var0.compressMaps()) {
/* 315 */       return var0.getNumberValue(var1).flatMap(var0 -> {
/*     */             T var1 = fromId(var0.intValue());
/*     */ 
/*     */ 
/*     */             
/*     */             return (var1 == null) ? DataResult.error("Unknown registry id: " + var0) : DataResult.success(var1, d(var1));
/* 321 */           }).map(var1 -> Pair.of(var1, var0.empty()));
/*     */     }
/* 323 */     return MinecraftKey.a.decode(var0, var1).flatMap(var0 -> {
/*     */           T var1 = get((MinecraftKey)var0.getFirst());
/*     */           return (var1 == null) ? DataResult.error("Unknown registry key: " + var0.getFirst()) : DataResult.success(Pair.of(var1, var0.getSecond()), d(var1));
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <U> DataResult<U> encode(T var0, DynamicOps<U> var1, U var2) {
/* 334 */     MinecraftKey var3 = getKey(var0);
/* 335 */     if (var3 == null) {
/* 336 */       return DataResult.error("Unknown registry element " + var0);
/*     */     }
/* 338 */     if (var1.compressMaps()) {
/* 339 */       return var1.mergeToPrimitive(var2, var1.createInt(a(var0))).setLifecycle(this.bf);
/*     */     }
/* 341 */     return var1.mergeToPrimitive(var2, var1.createString(var3.toString())).setLifecycle(this.bf);
/*     */   }
/*     */ 
/*     */   
/*     */   public <U> Stream<U> keys(DynamicOps<U> var0) {
/* 346 */     return keySet().stream().map(var1 -> var0.createString(var1.toString()));
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
/*     */   public Optional<T> getOptional(@Nullable MinecraftKey var0) {
/* 368 */     return Optional.ofNullable(get(var0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public T d(ResourceKey<T> var0) {
/* 376 */     T var1 = a(var0);
/* 377 */     if (var1 == null) {
/* 378 */       throw new IllegalStateException("Missing: " + var0);
/*     */     }
/* 380 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Stream<T> g() {
/* 391 */     return StreamSupport.stream(spliterator(), false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T> T a(IRegistry<? super T> var0, String var1, T var2) {
/* 399 */     return a(var0, new MinecraftKey(var1), var2);
/*     */   }
/*     */   
/*     */   public static <V, T extends V> T a(IRegistry<V> var0, MinecraftKey var1, T var2) {
/* 403 */     return (T)((IRegistryWritable)var0).a(ResourceKey.a(var0.b, var1), var2, Lifecycle.stable());
/*     */   }
/*     */   
/*     */   public static <V, T extends V> T a(IRegistry<V> var0, int var1, String var2, T var3) {
/* 407 */     return (T)((IRegistryWritable)var0).a(var1, ResourceKey.a(var0.b, new MinecraftKey(var2)), var3, Lifecycle.stable());
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public abstract MinecraftKey getKey(T paramT);
/*     */   
/*     */   public abstract Optional<ResourceKey<T>> c(T paramT);
/*     */   
/*     */   public abstract int a(@Nullable T paramT);
/*     */   
/*     */   @Nullable
/*     */   public abstract T a(@Nullable ResourceKey<T> paramResourceKey);
/*     */   
/*     */   @Nullable
/*     */   public abstract T get(@Nullable MinecraftKey paramMinecraftKey);
/*     */   
/*     */   protected abstract Lifecycle d(T paramT);
/*     */   
/*     */   public abstract Lifecycle b();
/*     */   
/*     */   public abstract Set<MinecraftKey> keySet();
/*     */   
/*     */   public abstract Set<Map.Entry<ResourceKey<T>, T>> d();
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */