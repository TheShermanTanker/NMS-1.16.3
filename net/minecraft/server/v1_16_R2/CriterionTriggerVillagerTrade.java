/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CriterionTriggerVillagerTrade
/*    */   extends CriterionTriggerAbstract<CriterionTriggerVillagerTrade.a>
/*    */ {
/* 11 */   private static final MinecraftKey a = new MinecraftKey("villager_trade");
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 15 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public a b(JsonObject var0, CriterionConditionEntity.b var1, LootDeserializationContext var2) {
/* 20 */     CriterionConditionEntity.b var3 = CriterionConditionEntity.b.a(var0, "villager", var2);
/* 21 */     CriterionConditionItem var4 = CriterionConditionItem.a(var0.get("item"));
/* 22 */     return new a(var1, var3, var4);
/*    */   }
/*    */   
/*    */   public void a(EntityPlayer var0, EntityVillagerAbstract var1, ItemStack var2) {
/* 26 */     LootTableInfo var3 = CriterionConditionEntity.b(var0, var1);
/* 27 */     a(var0, var2 -> var2.a(var0, var1));
/*    */   }
/*    */   
/*    */   public static class a extends CriterionInstanceAbstract {
/*    */     private final CriterionConditionEntity.b a;
/*    */     private final CriterionConditionItem b;
/*    */     
/*    */     public a(CriterionConditionEntity.b var0, CriterionConditionEntity.b var1, CriterionConditionItem var2) {
/* 35 */       super(CriterionTriggerVillagerTrade.b(), var0);
/* 36 */       this.a = var1;
/* 37 */       this.b = var2;
/*    */     }
/*    */     
/*    */     public static a c() {
/* 41 */       return new a(CriterionConditionEntity.b.a, CriterionConditionEntity.b.a, CriterionConditionItem.a);
/*    */     }
/*    */     
/*    */     public boolean a(LootTableInfo var0, ItemStack var1) {
/* 45 */       if (!this.a.a(var0)) {
/* 46 */         return false;
/*    */       }
/* 48 */       if (!this.b.a(var1)) {
/* 49 */         return false;
/*    */       }
/* 51 */       return true;
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject a(LootSerializationContext var0) {
/* 56 */       JsonObject var1 = super.a(var0);
/*    */       
/* 58 */       var1.add("item", this.b.a());
/* 59 */       var1.add("villager", this.a.a(var0));
/*    */       
/* 61 */       return var1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerVillagerTrade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */