/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonNull;
/*    */ import com.google.gson.JsonObject;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CriterionConditionLight
/*    */ {
/* 13 */   public static final CriterionConditionLight a = new CriterionConditionLight(CriterionConditionValue.IntegerRange.e);
/*    */   
/*    */   private final CriterionConditionValue.IntegerRange b;
/*    */   
/*    */   private CriterionConditionLight(CriterionConditionValue.IntegerRange var0) {
/* 18 */     this.b = var0;
/*    */   }
/*    */   
/*    */   public boolean a(WorldServer var0, BlockPosition var1) {
/* 22 */     if (this == a) {
/* 23 */       return true;
/*    */     }
/* 25 */     if (!var0.p(var1)) {
/* 26 */       return false;
/*    */     }
/* 28 */     if (!this.b.d(var0.getLightLevel(var1))) {
/* 29 */       return false;
/*    */     }
/* 31 */     return true;
/*    */   }
/*    */   
/*    */   public JsonElement a() {
/* 35 */     if (this == a) {
/* 36 */       return (JsonElement)JsonNull.INSTANCE;
/*    */     }
/*    */     
/* 39 */     JsonObject var0 = new JsonObject();
/* 40 */     var0.add("light", this.b.d());
/* 41 */     return (JsonElement)var0;
/*    */   }
/*    */   
/*    */   public static CriterionConditionLight a(@Nullable JsonElement var0) {
/* 45 */     if (var0 == null || var0.isJsonNull()) {
/* 46 */       return a;
/*    */     }
/* 48 */     JsonObject var1 = ChatDeserializer.m(var0, "light");
/* 49 */     CriterionConditionValue.IntegerRange var2 = CriterionConditionValue.IntegerRange.a(var1.get("light"));
/* 50 */     return new CriterionConditionLight(var2);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionConditionLight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */