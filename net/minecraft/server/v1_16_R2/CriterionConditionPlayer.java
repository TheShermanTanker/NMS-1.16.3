/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonNull;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonPrimitive;
/*     */ import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
/*     */ import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import java.util.Map;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
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
/*     */ public class CriterionConditionPlayer
/*     */ {
/*     */   private final CriterionConditionValue.IntegerRange b;
/*     */   private final EnumGamemode c;
/*     */   private final Map<Statistic<?>, CriterionConditionValue.IntegerRange> d;
/*  33 */   public static final CriterionConditionPlayer a = (new d()).b();
/*     */   private final Object2BooleanMap<MinecraftKey> e;
/*     */   private final Map<MinecraftKey, c> f;
/*     */   
/*     */   static interface c extends Predicate<AdvancementProgress> {
/*     */     JsonElement a();
/*     */   }
/*     */   
/*     */   static class b implements c {
/*     */     public b(boolean var0) {
/*  43 */       this.a = var0;
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonElement a() {
/*  48 */       return (JsonElement)new JsonPrimitive(Boolean.valueOf(this.a));
/*     */     }
/*     */     private final boolean a;
/*     */     
/*     */     public boolean test(AdvancementProgress var0) {
/*  53 */       return (var0.isDone() == this.a);
/*     */     }
/*     */   }
/*     */   
/*     */   static class a implements c {
/*     */     private final Object2BooleanMap<String> a;
/*     */     
/*     */     public a(Object2BooleanMap<String> var0) {
/*  61 */       this.a = var0;
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonElement a() {
/*  66 */       JsonObject var0 = new JsonObject();
/*  67 */       this.a.forEach(var0::addProperty);
/*  68 */       return (JsonElement)var0;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean test(AdvancementProgress var0) {
/*  73 */       for (ObjectIterator<Object2BooleanMap.Entry<String>> objectIterator = this.a.object2BooleanEntrySet().iterator(); objectIterator.hasNext(); ) { Object2BooleanMap.Entry<String> var2 = objectIterator.next();
/*  74 */         CriterionProgress var3 = var0.getCriterionProgress((String)var2.getKey());
/*  75 */         if (var3 == null || var3.a() != var2.getBooleanValue()) {
/*  76 */           return false;
/*     */         } }
/*     */       
/*  79 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   private static c b(JsonElement var0) {
/*  84 */     if (var0.isJsonPrimitive()) {
/*  85 */       boolean var1 = var0.getAsBoolean();
/*  86 */       return new b(var1);
/*     */     } 
/*     */     
/*  89 */     Object2BooleanOpenHashMap object2BooleanOpenHashMap = new Object2BooleanOpenHashMap();
/*  90 */     JsonObject var2 = ChatDeserializer.m(var0, "criterion data");
/*  91 */     var2.entrySet().forEach(var1 -> {
/*     */           boolean var2 = ChatDeserializer.c((JsonElement)var1.getValue(), "criterion test");
/*     */           var0.put(var1.getKey(), var2);
/*     */         });
/*  95 */     return new a((Object2BooleanMap<String>)object2BooleanOpenHashMap);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private CriterionConditionPlayer(CriterionConditionValue.IntegerRange var0, EnumGamemode var1, Map<Statistic<?>, CriterionConditionValue.IntegerRange> var2, Object2BooleanMap<MinecraftKey> var3, Map<MinecraftKey, c> var4) {
/* 105 */     this.b = var0;
/* 106 */     this.c = var1;
/* 107 */     this.d = var2;
/* 108 */     this.e = var3;
/* 109 */     this.f = var4;
/*     */   }
/*     */   
/*     */   public boolean a(Entity var0) {
/* 113 */     if (this == a) {
/* 114 */       return true;
/*     */     }
/*     */     
/* 117 */     if (!(var0 instanceof EntityPlayer)) {
/* 118 */       return false;
/*     */     }
/*     */     
/* 121 */     EntityPlayer var1 = (EntityPlayer)var0;
/*     */     
/* 123 */     if (!this.b.d(var1.expLevel)) {
/* 124 */       return false;
/*     */     }
/*     */     
/* 127 */     if (this.c != EnumGamemode.NOT_SET && this.c != var1.playerInteractManager.getGameMode()) {
/* 128 */       return false;
/*     */     }
/*     */     
/* 131 */     StatisticManager var2 = var1.getStatisticManager();
/* 132 */     for (Map.Entry<Statistic<?>, CriterionConditionValue.IntegerRange> var4 : this.d.entrySet()) {
/* 133 */       int var5 = var2.getStatisticValue(var4.getKey());
/* 134 */       if (!((CriterionConditionValue.IntegerRange)var4.getValue()).d(var5)) {
/* 135 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 139 */     RecipeBook var3 = var1.getRecipeBook();
/* 140 */     for (ObjectIterator<Object2BooleanMap.Entry<MinecraftKey>> objectIterator = this.e.object2BooleanEntrySet().iterator(); objectIterator.hasNext(); ) { Object2BooleanMap.Entry<MinecraftKey> var5 = objectIterator.next();
/* 141 */       if (var3.hasDiscoveredRecipe((MinecraftKey)var5.getKey()) != var5.getBooleanValue()) {
/* 142 */         return false;
/*     */       } }
/*     */ 
/*     */     
/* 146 */     if (!this.f.isEmpty()) {
/* 147 */       AdvancementDataPlayer var4 = var1.getAdvancementData();
/* 148 */       AdvancementDataWorld var5 = var1.getMinecraftServer().getAdvancementData();
/*     */       
/* 150 */       for (Map.Entry<MinecraftKey, c> var7 : this.f.entrySet()) {
/* 151 */         Advancement var8 = var5.a(var7.getKey());
/* 152 */         if (var8 == null || !((c)var7.getValue()).test(var4.getProgress(var8))) {
/* 153 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 158 */     return true;
/*     */   }
/*     */   
/*     */   public static CriterionConditionPlayer a(@Nullable JsonElement var0) {
/* 162 */     if (var0 == null || var0.isJsonNull()) {
/* 163 */       return a;
/*     */     }
/*     */     
/* 166 */     JsonObject var1 = ChatDeserializer.m(var0, "player");
/* 167 */     CriterionConditionValue.IntegerRange var2 = CriterionConditionValue.IntegerRange.a(var1.get("level"));
/*     */     
/* 169 */     String var3 = ChatDeserializer.a(var1, "gamemode", "");
/* 170 */     EnumGamemode var4 = EnumGamemode.a(var3, EnumGamemode.NOT_SET);
/*     */     
/* 172 */     Map<Statistic<?>, CriterionConditionValue.IntegerRange> var5 = Maps.newHashMap();
/* 173 */     JsonArray var6 = ChatDeserializer.a(var1, "stats", (JsonArray)null);
/* 174 */     if (var6 != null) {
/* 175 */       for (JsonElement jsonElement : var6) {
/* 176 */         JsonObject jsonObject = ChatDeserializer.m(jsonElement, "stats entry");
/* 177 */         MinecraftKey minecraftKey1 = new MinecraftKey(ChatDeserializer.h(jsonObject, "type"));
/*     */         
/* 179 */         StatisticWrapper<?> var11 = IRegistry.STATS.get(minecraftKey1);
/* 180 */         if (var11 == null) {
/* 181 */           throw new JsonParseException("Invalid stat type: " + minecraftKey1);
/*     */         }
/*     */         
/* 184 */         MinecraftKey var12 = new MinecraftKey(ChatDeserializer.h(jsonObject, "stat"));
/* 185 */         Statistic<?> var13 = a(var11, var12);
/*     */         
/* 187 */         CriterionConditionValue.IntegerRange var14 = CriterionConditionValue.IntegerRange.a(jsonObject.get("value"));
/* 188 */         var5.put(var13, var14);
/*     */       } 
/*     */     }
/*     */     
/* 192 */     Object2BooleanOpenHashMap object2BooleanOpenHashMap = new Object2BooleanOpenHashMap();
/* 193 */     JsonObject var8 = ChatDeserializer.a(var1, "recipes", new JsonObject());
/* 194 */     for (Map.Entry<String, JsonElement> entry : (Iterable<Map.Entry<String, JsonElement>>)var8.entrySet()) {
/* 195 */       MinecraftKey var11 = new MinecraftKey(entry.getKey());
/* 196 */       boolean var12 = ChatDeserializer.c(entry.getValue(), "recipe present");
/* 197 */       object2BooleanOpenHashMap.put(var11, var12);
/*     */     } 
/*     */     
/* 200 */     Map<MinecraftKey, c> var9 = Maps.newHashMap();
/* 201 */     JsonObject var10 = ChatDeserializer.a(var1, "advancements", new JsonObject());
/* 202 */     for (Map.Entry<String, JsonElement> var12 : (Iterable<Map.Entry<String, JsonElement>>)var10.entrySet()) {
/* 203 */       MinecraftKey var13 = new MinecraftKey(var12.getKey());
/* 204 */       c var14 = b(var12.getValue());
/* 205 */       var9.put(var13, var14);
/*     */     } 
/*     */     
/* 208 */     return new CriterionConditionPlayer(var2, var4, var5, (Object2BooleanMap<MinecraftKey>)object2BooleanOpenHashMap, var9);
/*     */   }
/*     */   
/*     */   private static <T> Statistic<T> a(StatisticWrapper<T> var0, MinecraftKey var1) {
/* 212 */     IRegistry<T> var2 = var0.getRegistry();
/* 213 */     T var3 = var2.get(var1);
/* 214 */     if (var3 == null) {
/* 215 */       throw new JsonParseException("Unknown object " + var1 + " for stat type " + IRegistry.STATS.getKey(var0));
/*     */     }
/*     */     
/* 218 */     return var0.b(var3);
/*     */   }
/*     */   
/*     */   private static <T> MinecraftKey a(Statistic<T> var0) {
/* 222 */     return var0.getWrapper().getRegistry().getKey(var0.b());
/*     */   }
/*     */   
/*     */   public JsonElement a() {
/* 226 */     if (this == a) {
/* 227 */       return (JsonElement)JsonNull.INSTANCE;
/*     */     }
/*     */     
/* 230 */     JsonObject var0 = new JsonObject();
/* 231 */     var0.add("level", this.b.d());
/*     */     
/* 233 */     if (this.c != EnumGamemode.NOT_SET) {
/* 234 */       var0.addProperty("gamemode", this.c.b());
/*     */     }
/*     */     
/* 237 */     if (!this.d.isEmpty()) {
/* 238 */       JsonArray var1 = new JsonArray();
/* 239 */       this.d.forEach((var1, var2) -> {
/*     */             JsonObject var3 = new JsonObject();
/*     */             var3.addProperty("type", IRegistry.STATS.getKey(var1.getWrapper()).toString());
/*     */             var3.addProperty("stat", a(var1).toString());
/*     */             var3.add("value", var2.d());
/*     */             var0.add((JsonElement)var3);
/*     */           });
/* 246 */       var0.add("stats", (JsonElement)var1);
/*     */     } 
/*     */     
/* 249 */     if (!this.e.isEmpty()) {
/* 250 */       JsonObject var1 = new JsonObject();
/* 251 */       this.e.forEach((var1, var2) -> var0.addProperty(var1.toString(), var2));
/* 252 */       var0.add("recipes", (JsonElement)var1);
/*     */     } 
/*     */     
/* 255 */     if (!this.f.isEmpty()) {
/* 256 */       JsonObject var1 = new JsonObject();
/* 257 */       this.f.forEach((var1, var2) -> var0.add(var1.toString(), var2.a()));
/* 258 */       var0.add("advancements", (JsonElement)var1);
/*     */     } 
/*     */     
/* 261 */     return (JsonElement)var0;
/*     */   }
/*     */   
/*     */   public static class d {
/* 265 */     private CriterionConditionValue.IntegerRange a = CriterionConditionValue.IntegerRange.e;
/* 266 */     private EnumGamemode b = EnumGamemode.NOT_SET;
/* 267 */     private final Map<Statistic<?>, CriterionConditionValue.IntegerRange> c = Maps.newHashMap();
/* 268 */     private final Object2BooleanMap<MinecraftKey> d = (Object2BooleanMap<MinecraftKey>)new Object2BooleanOpenHashMap();
/* 269 */     private final Map<MinecraftKey, CriterionConditionPlayer.c> e = Maps.newHashMap();
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
/*     */     public CriterionConditionPlayer b() {
/* 306 */       return new CriterionConditionPlayer(this.a, this.b, this.c, this.d, this.e);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionConditionPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */