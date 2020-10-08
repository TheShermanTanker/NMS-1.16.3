/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonNull;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonPrimitive;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CriterionConditionInOpenWater
/*    */ {
/* 14 */   public static final CriterionConditionInOpenWater a = new CriterionConditionInOpenWater(false);
/*    */   
/*    */   private boolean b;
/*    */ 
/*    */   
/*    */   private CriterionConditionInOpenWater(boolean var0) {
/* 20 */     this.b = var0;
/*    */   }
/*    */   
/*    */   public static CriterionConditionInOpenWater a(boolean var0) {
/* 24 */     return new CriterionConditionInOpenWater(var0);
/*    */   }
/*    */   
/*    */   public static CriterionConditionInOpenWater a(@Nullable JsonElement var0) {
/* 28 */     if (var0 == null || var0.isJsonNull()) {
/* 29 */       return a;
/*    */     }
/*    */     
/* 32 */     JsonObject var1 = ChatDeserializer.m(var0, "fishing_hook");
/* 33 */     JsonElement var2 = var1.get("in_open_water");
/* 34 */     if (var2 != null) {
/* 35 */       return new CriterionConditionInOpenWater(ChatDeserializer.c(var2, "in_open_water"));
/*    */     }
/* 37 */     return a;
/*    */   }
/*    */   
/*    */   public JsonElement a() {
/* 41 */     if (this == a) {
/* 42 */       return (JsonElement)JsonNull.INSTANCE;
/*    */     }
/*    */     
/* 45 */     JsonObject var0 = new JsonObject();
/* 46 */     var0.add("in_open_water", (JsonElement)new JsonPrimitive(Boolean.valueOf(this.b)));
/* 47 */     return (JsonElement)var0;
/*    */   }
/*    */   
/*    */   public boolean a(Entity var0) {
/* 51 */     if (this == a) {
/* 52 */       return true;
/*    */     }
/*    */     
/* 55 */     if (!(var0 instanceof EntityFishingHook)) {
/* 56 */       return false;
/*    */     }
/*    */     
/* 59 */     EntityFishingHook var1 = (EntityFishingHook)var0;
/* 60 */     return (this.b == var1.g());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionConditionInOpenWater.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */