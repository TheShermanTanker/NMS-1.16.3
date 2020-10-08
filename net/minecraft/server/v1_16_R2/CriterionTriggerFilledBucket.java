/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ 
/*    */ 
/*    */ public class CriterionTriggerFilledBucket
/*    */   extends CriterionTriggerAbstract<CriterionTriggerFilledBucket.a>
/*    */ {
/*  9 */   private static final MinecraftKey a = new MinecraftKey("filled_bucket");
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 13 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public a b(JsonObject var0, CriterionConditionEntity.b var1, LootDeserializationContext var2) {
/* 18 */     CriterionConditionItem var3 = CriterionConditionItem.a(var0.get("item"));
/* 19 */     return new a(var1, var3);
/*    */   }
/*    */   
/*    */   public void a(EntityPlayer var0, ItemStack var1) {
/* 23 */     a(var0, var1 -> var1.a(var0));
/*    */   }
/*    */   
/*    */   public static class a extends CriterionInstanceAbstract {
/*    */     private final CriterionConditionItem a;
/*    */     
/*    */     public a(CriterionConditionEntity.b var0, CriterionConditionItem var1) {
/* 30 */       super(CriterionTriggerFilledBucket.b(), var0);
/* 31 */       this.a = var1;
/*    */     }
/*    */     
/*    */     public static a a(CriterionConditionItem var0) {
/* 35 */       return new a(CriterionConditionEntity.b.a, var0);
/*    */     }
/*    */     
/*    */     public boolean a(ItemStack var0) {
/* 39 */       if (!this.a.a(var0)) {
/* 40 */         return false;
/*    */       }
/* 42 */       return true;
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject a(LootSerializationContext var0) {
/* 47 */       JsonObject var1 = super.a(var0);
/*    */       
/* 49 */       var1.add("item", this.a.a());
/*    */       
/* 51 */       return var1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerFilledBucket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */