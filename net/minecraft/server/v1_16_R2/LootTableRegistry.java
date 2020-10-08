/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.JsonElement;
/*    */ import java.util.Map;
/*    */ import java.util.Objects;
/*    */ import java.util.Set;
/*    */ import java.util.function.Function;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class LootTableRegistry
/*    */   extends ResourceDataJson {
/* 15 */   private static final Logger LOGGER = LogManager.getLogger();
/* 16 */   private static final Gson b = LootSerialization.c().create();
/* 17 */   private Map<MinecraftKey, LootTable> c = (Map<MinecraftKey, LootTable>)ImmutableMap.of();
/* 18 */   public Map<LootTable, MinecraftKey> lootTableToKey = (Map<LootTable, MinecraftKey>)ImmutableMap.of();
/*    */   private final LootPredicateManager d;
/*    */   
/*    */   public LootTableRegistry(LootPredicateManager lootpredicatemanager) {
/* 22 */     super(b, "loot_tables");
/* 23 */     this.d = lootpredicatemanager;
/*    */   }
/*    */   
/*    */   public LootTable getLootTable(MinecraftKey minecraftkey) {
/* 27 */     return this.c.getOrDefault(minecraftkey, LootTable.EMPTY);
/*    */   }
/*    */   
/*    */   protected void a(Map<MinecraftKey, JsonElement> map, IResourceManager iresourcemanager, GameProfilerFiller gameprofilerfiller) {
/* 31 */     ImmutableMap.Builder<MinecraftKey, LootTable> builder = ImmutableMap.builder();
/* 32 */     JsonElement jsonelement = map.remove(LootTables.a);
/*    */     
/* 34 */     if (jsonelement != null) {
/* 35 */       LOGGER.warn("Datapack tried to redefine {} loot table, ignoring", LootTables.a);
/*    */     }
/*    */     
/* 38 */     map.forEach((minecraftkey, jsonelement1) -> {
/*    */           try {
/*    */             LootTable loottable = (LootTable)b.fromJson(jsonelement1, LootTable.class);
/*    */             
/*    */             builder.put(minecraftkey, loottable);
/* 43 */           } catch (Exception exception) {
/*    */             LOGGER.error("Couldn't parse loot table {}", minecraftkey, exception);
/*    */           } 
/*    */         });
/*    */     
/* 48 */     builder.put(LootTables.a, LootTable.EMPTY);
/* 49 */     ImmutableMap<MinecraftKey, LootTable> immutablemap = builder.build();
/* 50 */     LootContextParameterSet lootcontextparameterset = LootContextParameterSets.GENERIC;
/* 51 */     LootPredicateManager lootpredicatemanager = this.d;
/*    */     
/* 53 */     this.d.getClass();
/* 54 */     Objects.requireNonNull(lootpredicatemanager); Function<MinecraftKey, LootItemCondition> function = lootpredicatemanager::a;
/*    */     
/* 56 */     immutablemap.getClass();
/* 57 */     Objects.requireNonNull(immutablemap); LootCollector lootcollector = new LootCollector(lootcontextparameterset, function, immutablemap::get);
/*    */     
/* 59 */     immutablemap.forEach((minecraftkey, loottable) -> a(lootcollector, minecraftkey, loottable));
/*    */ 
/*    */     
/* 62 */     lootcollector.a().forEach((s, s1) -> LOGGER.warn("Found validation problem in " + s + ": " + s1));
/*    */ 
/*    */     
/* 65 */     this.c = (Map<MinecraftKey, LootTable>)immutablemap;
/*    */     
/* 67 */     ImmutableMap.Builder<LootTable, MinecraftKey> lootTableToKeyBuilder = ImmutableMap.builder();
/* 68 */     this.c.forEach((lootTable, key) -> lootTableToKeyBuilder.put(key, lootTable));
/* 69 */     this.lootTableToKey = (Map<LootTable, MinecraftKey>)lootTableToKeyBuilder.build();
/*    */   }
/*    */ 
/*    */   
/*    */   public static void a(LootCollector lootcollector, MinecraftKey minecraftkey, LootTable loottable) {
/* 74 */     loottable.a(lootcollector.a(loottable.getLootContextParameterSet()).a("{" + minecraftkey + "}", minecraftkey));
/*    */   }
/*    */   
/*    */   public static JsonElement a(LootTable loottable) {
/* 78 */     return b.toJsonTree(loottable);
/*    */   }
/*    */   
/*    */   public Set<MinecraftKey> a() {
/* 82 */     return this.c.keySet();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootTableRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */