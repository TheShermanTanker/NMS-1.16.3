/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonDeserializer;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.google.gson.JsonPrimitive;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import com.google.gson.JsonSerializer;
/*    */ import java.lang.reflect.Type;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ public final class LootValueConstant
/*    */   implements LootValue
/*    */ {
/*    */   private final int d;
/*    */   
/*    */   public LootValueConstant(int var0) {
/* 20 */     this.d = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(Random var0) {
/* 25 */     return this.d;
/*    */   }
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 30 */     return a;
/*    */   }
/*    */   
/*    */   public static LootValueConstant a(int var0) {
/* 34 */     return new LootValueConstant(var0);
/*    */   }
/*    */   
/*    */   public static class a
/*    */     implements JsonDeserializer<LootValueConstant>, JsonSerializer<LootValueConstant> {
/*    */     public LootValueConstant deserialize(JsonElement var0, Type var1, JsonDeserializationContext var2) throws JsonParseException {
/* 40 */       return new LootValueConstant(ChatDeserializer.g(var0, "value"));
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonElement serialize(LootValueConstant var0, Type var1, JsonSerializationContext var2) {
/* 45 */       return (JsonElement)new JsonPrimitive(Integer.valueOf(LootValueConstant.a(var0)));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootValueConstant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */