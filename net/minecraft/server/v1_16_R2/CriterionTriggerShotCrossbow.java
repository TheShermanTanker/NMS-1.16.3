/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CriterionTriggerShotCrossbow
/*    */   extends CriterionTriggerAbstract<CriterionTriggerShotCrossbow.a>
/*    */ {
/* 12 */   private static final MinecraftKey a = new MinecraftKey("shot_crossbow");
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 16 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public a b(JsonObject var0, CriterionConditionEntity.b var1, LootDeserializationContext var2) {
/* 21 */     CriterionConditionItem var3 = CriterionConditionItem.a(var0.get("item"));
/* 22 */     return new a(var1, var3);
/*    */   }
/*    */   
/*    */   public void a(EntityPlayer var0, ItemStack var1) {
/* 26 */     a(var0, var1 -> var1.a(var0));
/*    */   }
/*    */   
/*    */   public static class a extends CriterionInstanceAbstract {
/*    */     private final CriterionConditionItem a;
/*    */     
/*    */     public a(CriterionConditionEntity.b var0, CriterionConditionItem var1) {
/* 33 */       super(CriterionTriggerShotCrossbow.b(), var0);
/* 34 */       this.a = var1;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public static a a(IMaterial var0) {
/* 42 */       return new a(CriterionConditionEntity.b.a, CriterionConditionItem.a.a().a(var0).b());
/*    */     }
/*    */     
/*    */     public boolean a(ItemStack var0) {
/* 46 */       return this.a.a(var0);
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject a(LootSerializationContext var0) {
/* 51 */       JsonObject var1 = super.a(var0);
/*    */       
/* 53 */       var1.add("item", this.a.a());
/*    */       
/* 55 */       return var1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerShotCrossbow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */