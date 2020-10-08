/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonDeserializer;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import com.google.gson.JsonSerializer;
/*    */ import java.lang.reflect.Type;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class LootValueBinomial
/*    */   implements LootValue
/*    */ {
/*    */   private final int d;
/*    */   private final float e;
/*    */   
/*    */   public LootValueBinomial(int var0, float var1) {
/* 22 */     this.d = var0;
/* 23 */     this.e = var1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int a(Random var0) {
/* 30 */     int var1 = 0;
/* 31 */     for (int var2 = 0; var2 < this.d; var2++) {
/* 32 */       if (var0.nextFloat() < this.e) {
/* 33 */         var1++;
/*    */       }
/*    */     } 
/*    */     
/* 37 */     return var1;
/*    */   }
/*    */   
/*    */   public static LootValueBinomial a(int var0, float var1) {
/* 41 */     return new LootValueBinomial(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 46 */     return c;
/*    */   }
/*    */   
/*    */   public static class a
/*    */     implements JsonDeserializer<LootValueBinomial>, JsonSerializer<LootValueBinomial> {
/*    */     public LootValueBinomial deserialize(JsonElement var0, Type var1, JsonDeserializationContext var2) throws JsonParseException {
/* 52 */       JsonObject var3 = ChatDeserializer.m(var0, "value");
/* 53 */       int var4 = ChatDeserializer.n(var3, "n");
/* 54 */       float var5 = ChatDeserializer.l(var3, "p");
/* 55 */       return new LootValueBinomial(var4, var5);
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonElement serialize(LootValueBinomial var0, Type var1, JsonSerializationContext var2) {
/* 60 */       JsonObject var3 = new JsonObject();
/* 61 */       var3.addProperty("n", Integer.valueOf(LootValueBinomial.a(var0)));
/* 62 */       var3.addProperty("p", Float.valueOf(LootValueBinomial.b(var0)));
/* 63 */       return (JsonElement)var3;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootValueBinomial.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */