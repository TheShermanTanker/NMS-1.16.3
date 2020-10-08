/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LootItemConditionEntityScore
/*     */   implements LootItemCondition
/*     */ {
/*     */   private final Map<String, LootValueBounds> a;
/*     */   private final LootTableInfo.EntityTarget b;
/*     */   
/*     */   private LootItemConditionEntityScore(Map<String, LootValueBounds> var0, LootTableInfo.EntityTarget var1) {
/*  26 */     this.a = (Map<String, LootValueBounds>)ImmutableMap.copyOf(var0);
/*  27 */     this.b = var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public LootItemConditionType b() {
/*  32 */     return LootItemConditions.g;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<LootContextParameter<?>> a() {
/*  37 */     return (Set<LootContextParameter<?>>)ImmutableSet.of(this.b.a());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean test(LootTableInfo var0) {
/*  42 */     Entity var1 = var0.<Entity>getContextParameter((LootContextParameter)this.b.a());
/*     */     
/*  44 */     if (var1 == null) {
/*  45 */       return false;
/*     */     }
/*     */     
/*  48 */     Scoreboard var2 = var1.world.getScoreboard();
/*  49 */     for (Map.Entry<String, LootValueBounds> var4 : this.a.entrySet()) {
/*  50 */       if (!a(var1, var2, var4.getKey(), var4.getValue())) {
/*  51 */         return false;
/*     */       }
/*     */     } 
/*  54 */     return true;
/*     */   }
/*     */   
/*     */   protected boolean a(Entity var0, Scoreboard var1, String var2, LootValueBounds var3) {
/*  58 */     ScoreboardObjective var4 = var1.getObjective(var2);
/*  59 */     if (var4 == null) {
/*  60 */       return false;
/*     */     }
/*  62 */     String var5 = var0.getName();
/*  63 */     if (!var1.b(var5, var4)) {
/*  64 */       return false;
/*     */     }
/*  66 */     return var3.a(var1.getPlayerScoreForObjective(var5, var4).getScore());
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
/*     */ 
/*     */   
/*     */   public static class b
/*     */     implements LootSerializer<LootItemConditionEntityScore>
/*     */   {
/*     */     public void a(JsonObject var0, LootItemConditionEntityScore var1, JsonSerializationContext var2) {
/*  95 */       JsonObject var3 = new JsonObject();
/*  96 */       for (Map.Entry<String, LootValueBounds> var5 : (Iterable<Map.Entry<String, LootValueBounds>>)LootItemConditionEntityScore.a(var1).entrySet()) {
/*  97 */         var3.add(var5.getKey(), var2.serialize(var5.getValue()));
/*     */       }
/*  99 */       var0.add("scores", (JsonElement)var3);
/* 100 */       var0.add("entity", var2.serialize(LootItemConditionEntityScore.b(var1)));
/*     */     }
/*     */ 
/*     */     
/*     */     public LootItemConditionEntityScore a(JsonObject var0, JsonDeserializationContext var1) {
/* 105 */       Set<Map.Entry<String, JsonElement>> var2 = ChatDeserializer.t(var0, "scores").entrySet();
/* 106 */       Map<String, LootValueBounds> var3 = Maps.newLinkedHashMap();
/* 107 */       for (Map.Entry<String, JsonElement> var5 : var2) {
/* 108 */         var3.put(var5.getKey(), ChatDeserializer.a(var5.getValue(), "score", var1, LootValueBounds.class));
/*     */       }
/* 110 */       return new LootItemConditionEntityScore(var3, ChatDeserializer.<LootTableInfo.EntityTarget>a(var0, "entity", var1, LootTableInfo.EntityTarget.class));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemConditionEntityScore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */