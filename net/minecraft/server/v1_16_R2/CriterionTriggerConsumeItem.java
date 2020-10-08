/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CriterionTriggerConsumeItem
/*    */   extends CriterionTriggerAbstract<CriterionTriggerConsumeItem.a>
/*    */ {
/* 10 */   private static final MinecraftKey a = new MinecraftKey("consume_item");
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 14 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public a b(JsonObject var0, CriterionConditionEntity.b var1, LootDeserializationContext var2) {
/* 19 */     return new a(var1, CriterionConditionItem.a(var0.get("item")));
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
/* 30 */       super(CriterionTriggerConsumeItem.b(), var0);
/* 31 */       this.a = var1;
/*    */     }
/*    */     
/*    */     public static a c() {
/* 35 */       return new a(CriterionConditionEntity.b.a, CriterionConditionItem.a);
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public static a a(IMaterial var0) {
/* 43 */       return new a(CriterionConditionEntity.b.a, new CriterionConditionItem(null, var0.getItem(), CriterionConditionValue.IntegerRange.e, CriterionConditionValue.IntegerRange.e, CriterionConditionEnchantments.b, CriterionConditionEnchantments.b, null, CriterionConditionNBT.a));
/*    */     }
/*    */     
/*    */     public boolean a(ItemStack var0) {
/* 47 */       return this.a.a(var0);
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject a(LootSerializationContext var0) {
/* 52 */       JsonObject var1 = super.a(var0);
/*    */       
/* 54 */       var1.add("item", this.a.a());
/*    */       
/* 56 */       return var1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerConsumeItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */