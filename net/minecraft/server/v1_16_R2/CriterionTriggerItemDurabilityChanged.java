/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ 
/*    */ 
/*    */ public class CriterionTriggerItemDurabilityChanged
/*    */   extends CriterionTriggerAbstract<CriterionTriggerItemDurabilityChanged.a>
/*    */ {
/*  9 */   private static final MinecraftKey a = new MinecraftKey("item_durability_changed");
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 13 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public a b(JsonObject var0, CriterionConditionEntity.b var1, LootDeserializationContext var2) {
/* 18 */     CriterionConditionItem var3 = CriterionConditionItem.a(var0.get("item"));
/* 19 */     CriterionConditionValue.IntegerRange var4 = CriterionConditionValue.IntegerRange.a(var0.get("durability"));
/* 20 */     CriterionConditionValue.IntegerRange var5 = CriterionConditionValue.IntegerRange.a(var0.get("delta"));
/* 21 */     return new a(var1, var3, var4, var5);
/*    */   }
/*    */   
/*    */   public void a(EntityPlayer var0, ItemStack var1, int var2) {
/* 25 */     a(var0, var2 -> var2.a(var0, var1));
/*    */   }
/*    */   
/*    */   public static class a extends CriterionInstanceAbstract {
/*    */     private final CriterionConditionItem a;
/*    */     private final CriterionConditionValue.IntegerRange b;
/*    */     private final CriterionConditionValue.IntegerRange c;
/*    */     
/*    */     public a(CriterionConditionEntity.b var0, CriterionConditionItem var1, CriterionConditionValue.IntegerRange var2, CriterionConditionValue.IntegerRange var3) {
/* 34 */       super(CriterionTriggerItemDurabilityChanged.b(), var0);
/* 35 */       this.a = var1;
/* 36 */       this.b = var2;
/* 37 */       this.c = var3;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public static a a(CriterionConditionEntity.b var0, CriterionConditionItem var1, CriterionConditionValue.IntegerRange var2) {
/* 45 */       return new a(var0, var1, var2, CriterionConditionValue.IntegerRange.e);
/*    */     }
/*    */     
/*    */     public boolean a(ItemStack var0, int var1) {
/* 49 */       if (!this.a.a(var0)) {
/* 50 */         return false;
/*    */       }
/* 52 */       if (!this.b.d(var0.h() - var1)) {
/* 53 */         return false;
/*    */       }
/* 55 */       if (!this.c.d(var0.getDamage() - var1)) {
/* 56 */         return false;
/*    */       }
/* 58 */       return true;
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject a(LootSerializationContext var0) {
/* 63 */       JsonObject var1 = super.a(var0);
/*    */       
/* 65 */       var1.add("item", this.a.a());
/* 66 */       var1.add("durability", this.b.d());
/* 67 */       var1.add("delta", this.c.d());
/*    */       
/* 69 */       return var1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerItemDurabilityChanged.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */