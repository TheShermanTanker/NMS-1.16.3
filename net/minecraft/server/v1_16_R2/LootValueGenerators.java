/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootValueGenerators
/*    */ {
/* 15 */   private static final Map<MinecraftKey, Class<? extends LootValue>> a = Maps.newHashMap();
/*    */   
/*    */   static {
/* 18 */     a.put(LootValue.b, LootValueBounds.class);
/* 19 */     a.put(LootValue.c, LootValueBinomial.class);
/* 20 */     a.put(LootValue.a, LootValueConstant.class);
/*    */   }
/*    */   
/*    */   public static LootValue a(JsonElement var0, JsonDeserializationContext var1) throws JsonParseException {
/* 24 */     if (var0.isJsonPrimitive()) {
/* 25 */       return (LootValue)var1.deserialize(var0, LootValueConstant.class);
/*    */     }
/*    */     
/* 28 */     JsonObject var2 = var0.getAsJsonObject();
/* 29 */     String var3 = ChatDeserializer.a(var2, "type", LootValue.b.toString());
/*    */     
/* 31 */     Class<? extends LootValue> var4 = a.get(new MinecraftKey(var3));
/* 32 */     if (var4 == null) {
/* 33 */       throw new JsonParseException("Unknown generator: " + var3);
/*    */     }
/*    */     
/* 36 */     return (LootValue)var1.deserialize((JsonElement)var2, var4);
/*    */   }
/*    */   
/*    */   public static JsonElement a(LootValue var0, JsonSerializationContext var1) {
/* 40 */     JsonElement var2 = var1.serialize(var0);
/* 41 */     if (var2.isJsonObject()) {
/* 42 */       var2.getAsJsonObject().addProperty("type", var0.a().toString());
/*    */     }
/*    */     
/* 45 */     return var2;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootValueGenerators.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */