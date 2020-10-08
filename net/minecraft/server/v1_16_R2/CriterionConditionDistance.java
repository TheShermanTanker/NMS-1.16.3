/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonNull;
/*    */ import com.google.gson.JsonObject;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CriterionConditionDistance
/*    */ {
/* 12 */   public static final CriterionConditionDistance a = new CriterionConditionDistance(CriterionConditionValue.FloatRange.e, CriterionConditionValue.FloatRange.e, CriterionConditionValue.FloatRange.e, CriterionConditionValue.FloatRange.e, CriterionConditionValue.FloatRange.e);
/*    */   
/*    */   private final CriterionConditionValue.FloatRange b;
/*    */   private final CriterionConditionValue.FloatRange c;
/*    */   private final CriterionConditionValue.FloatRange d;
/*    */   private final CriterionConditionValue.FloatRange e;
/*    */   private final CriterionConditionValue.FloatRange f;
/*    */   
/*    */   public CriterionConditionDistance(CriterionConditionValue.FloatRange var0, CriterionConditionValue.FloatRange var1, CriterionConditionValue.FloatRange var2, CriterionConditionValue.FloatRange var3, CriterionConditionValue.FloatRange var4) {
/* 21 */     this.b = var0;
/* 22 */     this.c = var1;
/* 23 */     this.d = var2;
/* 24 */     this.e = var3;
/* 25 */     this.f = var4;
/*    */   }
/*    */   
/*    */   public static CriterionConditionDistance a(CriterionConditionValue.FloatRange var0) {
/* 29 */     return new CriterionConditionDistance(CriterionConditionValue.FloatRange.e, CriterionConditionValue.FloatRange.e, CriterionConditionValue.FloatRange.e, var0, CriterionConditionValue.FloatRange.e);
/*    */   }
/*    */   
/*    */   public static CriterionConditionDistance b(CriterionConditionValue.FloatRange var0) {
/* 33 */     return new CriterionConditionDistance(CriterionConditionValue.FloatRange.e, var0, CriterionConditionValue.FloatRange.e, CriterionConditionValue.FloatRange.e, CriterionConditionValue.FloatRange.e);
/*    */   }
/*    */   
/*    */   public boolean a(double var0, double var2, double var4, double var6, double var8, double var10) {
/* 37 */     float var12 = (float)(var0 - var6);
/* 38 */     float var13 = (float)(var2 - var8);
/* 39 */     float var14 = (float)(var4 - var10);
/* 40 */     if (!this.b.d(MathHelper.e(var12)) || !this.c.d(MathHelper.e(var13)) || !this.d.d(MathHelper.e(var14))) {
/* 41 */       return false;
/*    */     }
/* 43 */     if (!this.e.a((var12 * var12 + var14 * var14))) {
/* 44 */       return false;
/*    */     }
/* 46 */     if (!this.f.a((var12 * var12 + var13 * var13 + var14 * var14))) {
/* 47 */       return false;
/*    */     }
/* 49 */     return true;
/*    */   }
/*    */   
/*    */   public static CriterionConditionDistance a(@Nullable JsonElement var0) {
/* 53 */     if (var0 == null || var0.isJsonNull()) {
/* 54 */       return a;
/*    */     }
/* 56 */     JsonObject var1 = ChatDeserializer.m(var0, "distance");
/* 57 */     CriterionConditionValue.FloatRange var2 = CriterionConditionValue.FloatRange.a(var1.get("x"));
/* 58 */     CriterionConditionValue.FloatRange var3 = CriterionConditionValue.FloatRange.a(var1.get("y"));
/* 59 */     CriterionConditionValue.FloatRange var4 = CriterionConditionValue.FloatRange.a(var1.get("z"));
/* 60 */     CriterionConditionValue.FloatRange var5 = CriterionConditionValue.FloatRange.a(var1.get("horizontal"));
/* 61 */     CriterionConditionValue.FloatRange var6 = CriterionConditionValue.FloatRange.a(var1.get("absolute"));
/* 62 */     return new CriterionConditionDistance(var2, var3, var4, var5, var6);
/*    */   }
/*    */   
/*    */   public JsonElement a() {
/* 66 */     if (this == a) {
/* 67 */       return (JsonElement)JsonNull.INSTANCE;
/*    */     }
/*    */     
/* 70 */     JsonObject var0 = new JsonObject();
/*    */     
/* 72 */     var0.add("x", this.b.d());
/* 73 */     var0.add("y", this.c.d());
/* 74 */     var0.add("z", this.d.d());
/* 75 */     var0.add("horizontal", this.e.d());
/* 76 */     var0.add("absolute", this.f.d());
/*    */     
/* 78 */     return (JsonElement)var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionConditionDistance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */