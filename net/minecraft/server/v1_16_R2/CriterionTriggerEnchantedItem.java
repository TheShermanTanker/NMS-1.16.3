/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ 
/*    */ 
/*    */ public class CriterionTriggerEnchantedItem
/*    */   extends CriterionTriggerAbstract<CriterionTriggerEnchantedItem.a>
/*    */ {
/*  9 */   private static final MinecraftKey a = new MinecraftKey("enchanted_item");
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 13 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public a b(JsonObject var0, CriterionConditionEntity.b var1, LootDeserializationContext var2) {
/* 18 */     CriterionConditionItem var3 = CriterionConditionItem.a(var0.get("item"));
/* 19 */     CriterionConditionValue.IntegerRange var4 = CriterionConditionValue.IntegerRange.a(var0.get("levels"));
/* 20 */     return new a(var1, var3, var4);
/*    */   }
/*    */   
/*    */   public void a(EntityPlayer var0, ItemStack var1, int var2) {
/* 24 */     a(var0, var2 -> var2.a(var0, var1));
/*    */   }
/*    */   
/*    */   public static class a extends CriterionInstanceAbstract {
/*    */     private final CriterionConditionItem a;
/*    */     private final CriterionConditionValue.IntegerRange b;
/*    */     
/*    */     public a(CriterionConditionEntity.b var0, CriterionConditionItem var1, CriterionConditionValue.IntegerRange var2) {
/* 32 */       super(CriterionTriggerEnchantedItem.b(), var0);
/* 33 */       this.a = var1;
/* 34 */       this.b = var2;
/*    */     }
/*    */     
/*    */     public static a c() {
/* 38 */       return new a(CriterionConditionEntity.b.a, CriterionConditionItem.a, CriterionConditionValue.IntegerRange.e);
/*    */     }
/*    */     
/*    */     public boolean a(ItemStack var0, int var1) {
/* 42 */       if (!this.a.a(var0)) {
/* 43 */         return false;
/*    */       }
/* 45 */       if (!this.b.d(var1)) {
/* 46 */         return false;
/*    */       }
/* 48 */       return true;
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject a(LootSerializationContext var0) {
/* 53 */       JsonObject var1 = super.a(var0);
/*    */       
/* 55 */       var1.add("item", this.a.a());
/* 56 */       var1.add("levels", this.b.d());
/*    */       
/* 58 */       return var1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerEnchantedItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */