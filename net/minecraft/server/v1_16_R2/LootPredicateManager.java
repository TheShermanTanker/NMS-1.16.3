/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.JsonElement;
/*    */ import java.util.Collections;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.function.Predicate;
/*    */ import javax.annotation.Nullable;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootPredicateManager
/*    */   extends ResourceDataJson
/*    */ {
/* 24 */   private static final Logger LOGGER = LogManager.getLogger(); private Map<MinecraftKey, LootItemCondition> c;
/* 25 */   private static final Gson b = LootSerialization.a().create();
/*    */   
/*    */   public LootPredicateManager() {
/* 28 */     super(b, "predicates");
/*    */ 
/*    */     
/* 31 */     this.c = (Map<MinecraftKey, LootItemCondition>)ImmutableMap.of();
/*    */   }
/*    */   @Nullable
/*    */   public LootItemCondition a(MinecraftKey var0) {
/* 35 */     return this.c.get(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(Map<MinecraftKey, JsonElement> var0, IResourceManager var1, GameProfilerFiller var2) {
/* 40 */     ImmutableMap.Builder<MinecraftKey, LootItemCondition> var3 = ImmutableMap.builder();
/* 41 */     var0.forEach((var1, var2) -> {
/*    */           try {
/*    */             if (var2.isJsonArray()) {
/*    */               LootItemCondition[] var3 = (LootItemCondition[])b.fromJson(var2, LootItemCondition[].class);
/*    */               var0.put(var1, new a(var3));
/*    */             } else {
/*    */               LootItemCondition var3 = (LootItemCondition)b.fromJson(var2, LootItemCondition.class);
/*    */               var0.put(var1, var3);
/*    */             } 
/* 50 */           } catch (Exception var3) {
/*    */             LOGGER.error("Couldn't parse loot table {}", var1, var3);
/*    */           } 
/*    */         });
/*    */     
/* 55 */     ImmutableMap immutableMap = var3.build();
/* 56 */     LootCollector var5 = new LootCollector(LootContextParameterSets.GENERIC, immutableMap::get, var0 -> null);
/* 57 */     immutableMap.forEach((var1, var2) -> var2.a(var0.b("{" + var1 + "}", var1)));
/* 58 */     var5.a().forEach((var0, var1) -> LOGGER.warn("Found validation problem in " + var0 + ": " + var1));
/*    */     
/* 60 */     this.c = (Map<MinecraftKey, LootItemCondition>)immutableMap;
/*    */   }
/*    */   
/*    */   public Set<MinecraftKey> a() {
/* 64 */     return Collections.unmodifiableSet(this.c.keySet());
/*    */   }
/*    */   
/*    */   static class a implements LootItemCondition {
/*    */     private final LootItemCondition[] a;
/*    */     private final Predicate<LootTableInfo> b;
/*    */     
/*    */     private a(LootItemCondition[] var0) {
/* 72 */       this.a = var0;
/* 73 */       this.b = LootItemConditions.a((Predicate<LootTableInfo>[])var0);
/*    */     }
/*    */ 
/*    */     
/*    */     public final boolean test(LootTableInfo var0) {
/* 78 */       return this.b.test(var0);
/*    */     }
/*    */ 
/*    */     
/*    */     public void a(LootCollector var0) {
/* 83 */       super.a(var0);
/*    */       
/* 85 */       for (int var1 = 0; var1 < this.a.length; var1++) {
/* 86 */         this.a[var1].a(var0.b(".term[" + var1 + "]"));
/*    */       }
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemConditionType b() {
/* 92 */       throw new UnsupportedOperationException();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootPredicateManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */