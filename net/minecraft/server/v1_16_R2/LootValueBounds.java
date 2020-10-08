/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonDeserializer;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.google.gson.JsonPrimitive;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import com.google.gson.JsonSerializer;
/*    */ import java.lang.reflect.Type;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootValueBounds
/*    */   implements LootValue
/*    */ {
/*    */   private final float d;
/*    */   private final float e;
/*    */   
/*    */   public LootValueBounds(float var0, float var1) {
/* 23 */     this.d = var0;
/* 24 */     this.e = var1;
/*    */   }
/*    */   
/*    */   public LootValueBounds(float var0) {
/* 28 */     this.d = var0;
/* 29 */     this.e = var0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static LootValueBounds a(float var0, float var1) {
/* 37 */     return new LootValueBounds(var0, var1);
/*    */   }
/*    */   
/*    */   public float b() {
/* 41 */     return this.d;
/*    */   }
/*    */   
/*    */   public float c() {
/* 45 */     return this.e;
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(Random var0) {
/* 50 */     return MathHelper.nextInt(var0, MathHelper.d(this.d), MathHelper.d(this.e));
/*    */   }
/*    */   
/*    */   public float b(Random var0) {
/* 54 */     return MathHelper.a(var0, this.d, this.e);
/*    */   }
/*    */   
/*    */   public boolean a(int var0) {
/* 58 */     return (var0 <= this.e && var0 >= this.d);
/*    */   }
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 63 */     return b;
/*    */   }
/*    */   
/*    */   public static class a
/*    */     implements JsonDeserializer<LootValueBounds>, JsonSerializer<LootValueBounds> {
/*    */     public LootValueBounds deserialize(JsonElement var0, Type var1, JsonDeserializationContext var2) throws JsonParseException {
/* 69 */       if (ChatDeserializer.b(var0)) {
/* 70 */         return new LootValueBounds(ChatDeserializer.e(var0, "value"));
/*    */       }
/* 72 */       JsonObject var3 = ChatDeserializer.m(var0, "value");
/* 73 */       float var4 = ChatDeserializer.l(var3, "min");
/* 74 */       float var5 = ChatDeserializer.l(var3, "max");
/* 75 */       return new LootValueBounds(var4, var5);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public JsonElement serialize(LootValueBounds var0, Type var1, JsonSerializationContext var2) {
/* 81 */       if (LootValueBounds.a(var0) == LootValueBounds.b(var0)) {
/* 82 */         return (JsonElement)new JsonPrimitive(Float.valueOf(LootValueBounds.a(var0)));
/*    */       }
/* 84 */       JsonObject var3 = new JsonObject();
/* 85 */       var3.addProperty("min", Float.valueOf(LootValueBounds.a(var0)));
/* 86 */       var3.addProperty("max", Float.valueOf(LootValueBounds.b(var0)));
/* 87 */       return (JsonElement)var3;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootValueBounds.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */