/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CriterionTriggerPlayerInteractedWithEntity
/*    */   extends CriterionTriggerAbstract<CriterionTriggerPlayerInteractedWithEntity.a>
/*    */ {
/* 11 */   private static final MinecraftKey a = new MinecraftKey("player_interacted_with_entity");
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
/* 27 */     a(var0, var2 -> var2.a(var0, var1));
/*    */   }
/*    */   
/*    */   public static class a extends CriterionInstanceAbstract {
/*    */     private final CriterionConditionItem a;
/*    */     private final CriterionConditionEntity.b b;
/*    */     
/*    */     public a(CriterionConditionEntity.b var0, CriterionConditionItem var1, CriterionConditionEntity.b var2) {
/* 35 */       super(CriterionTriggerPlayerInteractedWithEntity.b(), var0);
/* 36 */       this.a = var1;
/* 37 */       this.b = var2;
/*    */     }
/*    */     
/*    */     public static a a(CriterionConditionEntity.b var0, CriterionConditionItem.a var1, CriterionConditionEntity.b var2) {
/* 41 */       return new a(var0, var1.b(), var2);
/*    */     }
/*    */     
/*    */     public boolean a(ItemStack var0, LootTableInfo var1) {
/* 45 */       if (!this.a.a(var0)) {
/* 46 */         return false;
/*    */       }
/*    */       
/* 49 */       return this.b.a(var1);
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject a(LootSerializationContext var0) {
/* 54 */       JsonObject var1 = super.a(var0);
/* 55 */       var1.add("item", this.a.a());
/* 56 */       var1.add("entity", this.b.a(var0));
/* 57 */       return var1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerPlayerInteractedWithEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */