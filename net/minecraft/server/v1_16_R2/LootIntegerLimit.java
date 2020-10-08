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
/*    */ import java.util.function.IntUnaryOperator;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootIntegerLimit
/*    */   implements IntUnaryOperator
/*    */ {
/*    */   private final Integer a;
/*    */   private final Integer b;
/*    */   private final IntUnaryOperator c;
/*    */   
/*    */   private LootIntegerLimit(@Nullable Integer var0, @Nullable Integer var1) {
/* 24 */     this.a = var0;
/* 25 */     this.b = var1;
/*    */     
/* 27 */     if (var0 == null) {
/* 28 */       if (var1 == null) {
/* 29 */         this.c = (var0 -> var0);
/*    */       } else {
/* 31 */         int var2 = var1.intValue();
/* 32 */         this.c = (var1 -> Math.min(var0, var1));
/*    */       } 
/*    */     } else {
/* 35 */       int var2 = var0.intValue();
/* 36 */       if (var1 == null) {
/* 37 */         this.c = (var1 -> Math.max(var0, var1));
/*    */       } else {
/* 39 */         int var3 = var1.intValue();
/* 40 */         this.c = (var2 -> MathHelper.clamp(var2, var0, var1));
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public static LootIntegerLimit a(int var0, int var1) {
/* 46 */     return new LootIntegerLimit(Integer.valueOf(var0), Integer.valueOf(var1));
/*    */   }
/*    */   
/*    */   public static LootIntegerLimit a(int var0) {
/* 50 */     return new LootIntegerLimit(Integer.valueOf(var0), null);
/*    */   }
/*    */   
/*    */   public static LootIntegerLimit b(int var0) {
/* 54 */     return new LootIntegerLimit(null, Integer.valueOf(var0));
/*    */   }
/*    */ 
/*    */   
/*    */   public int applyAsInt(int var0) {
/* 59 */     return this.c.applyAsInt(var0);
/*    */   }
/*    */   
/*    */   public static class a
/*    */     implements JsonDeserializer<LootIntegerLimit>, JsonSerializer<LootIntegerLimit> {
/*    */     public LootIntegerLimit deserialize(JsonElement var0, Type var1, JsonDeserializationContext var2) throws JsonParseException {
/* 65 */       JsonObject var3 = ChatDeserializer.m(var0, "value");
/* 66 */       Integer var4 = var3.has("min") ? Integer.valueOf(ChatDeserializer.n(var3, "min")) : null;
/* 67 */       Integer var5 = var3.has("max") ? Integer.valueOf(ChatDeserializer.n(var3, "max")) : null;
/* 68 */       return new LootIntegerLimit(var4, var5);
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonElement serialize(LootIntegerLimit var0, Type var1, JsonSerializationContext var2) {
/* 73 */       JsonObject var3 = new JsonObject();
/* 74 */       if (LootIntegerLimit.a(var0) != null) {
/* 75 */         var3.addProperty("max", LootIntegerLimit.a(var0));
/*    */       }
/*    */       
/* 78 */       if (LootIntegerLimit.b(var0) != null) {
/* 79 */         var3.addProperty("min", LootIntegerLimit.b(var0));
/*    */       }
/*    */       
/* 82 */       return (JsonElement)var3;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootIntegerLimit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */