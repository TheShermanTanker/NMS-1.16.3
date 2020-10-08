/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CriterionTriggerThrownItemPickedUpByEntity
/*    */   extends CriterionTriggerAbstract<CriterionTriggerThrownItemPickedUpByEntity.a>
/*    */ {
/* 11 */   private static final MinecraftKey a = new MinecraftKey("thrown_item_picked_up_by_entity");
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 15 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   protected a b(JsonObject var0, CriterionConditionEntity.b var1, LootDeserializationContext var2) {
/* 20 */     CriterionConditionItem var3 = CriterionConditionItem.a(var0.get("item"));
/* 21 */     CriterionConditionEntity.b var4 = CriterionConditionEntity.b.a(var0, "entity", var2);
/* 22 */     return new a(var1, var3, var4);
/*    */   }
/*    */   
/*    */   public void a(EntityPlayer var0, ItemStack var1, Entity var2) {
/* 26 */     LootTableInfo var3 = CriterionConditionEntity.b(var0, var2);
/* 27 */     a(var0, var3 -> var3.a(var0, var1, var2));
/*    */   }
/*    */   
/*    */   public static class a extends CriterionInstanceAbstract {
/*    */     private final CriterionConditionItem a;
/*    */     private final CriterionConditionEntity.b b;
/*    */     
/*    */     public a(CriterionConditionEntity.b var0, CriterionConditionItem var1, CriterionConditionEntity.b var2) {
/* 35 */       super(CriterionTriggerThrownItemPickedUpByEntity.b(), var0);
/* 36 */       this.a = var1;
/* 37 */       this.b = var2;
/*    */     }
/*    */     
/*    */     public static a a(CriterionConditionEntity.b var0, CriterionConditionItem.a var1, CriterionConditionEntity.b var2) {
/* 41 */       return new a(var0, var1.b(), var2);
/*    */     }
/*    */     
/*    */     public boolean a(EntityPlayer var0, ItemStack var1, LootTableInfo var2) {
/* 45 */       if (!this.a.a(var1)) {
/* 46 */         return false;
/*    */       }
/*    */       
/* 49 */       if (!this.b.a(var2)) {
/* 50 */         return false;
/*    */       }
/*    */       
/* 53 */       return true;
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject a(LootSerializationContext var0) {
/* 58 */       JsonObject var1 = super.a(var0);
/* 59 */       var1.add("item", this.a.a());
/* 60 */       var1.add("entity", this.b.a(var0));
/* 61 */       return var1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerThrownItemPickedUpByEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */