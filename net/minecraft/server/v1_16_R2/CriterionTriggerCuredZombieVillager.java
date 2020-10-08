/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CriterionTriggerCuredZombieVillager
/*    */   extends CriterionTriggerAbstract<CriterionTriggerCuredZombieVillager.a>
/*    */ {
/* 11 */   private static final MinecraftKey a = new MinecraftKey("cured_zombie_villager");
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 15 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public a b(JsonObject var0, CriterionConditionEntity.b var1, LootDeserializationContext var2) {
/* 20 */     CriterionConditionEntity.b var3 = CriterionConditionEntity.b.a(var0, "zombie", var2);
/* 21 */     CriterionConditionEntity.b var4 = CriterionConditionEntity.b.a(var0, "villager", var2);
/* 22 */     return new a(var1, var3, var4);
/*    */   }
/*    */   
/*    */   public void a(EntityPlayer var0, EntityZombie var1, EntityVillager var2) {
/* 26 */     LootTableInfo var3 = CriterionConditionEntity.b(var0, var1);
/* 27 */     LootTableInfo var4 = CriterionConditionEntity.b(var0, var2);
/*    */     
/* 29 */     a(var0, var2 -> var2.a(var0, var1));
/*    */   }
/*    */   
/*    */   public static class a extends CriterionInstanceAbstract {
/*    */     private final CriterionConditionEntity.b a;
/*    */     private final CriterionConditionEntity.b b;
/*    */     
/*    */     public a(CriterionConditionEntity.b var0, CriterionConditionEntity.b var1, CriterionConditionEntity.b var2) {
/* 37 */       super(CriterionTriggerCuredZombieVillager.b(), var0);
/* 38 */       this.a = var1;
/* 39 */       this.b = var2;
/*    */     }
/*    */     
/*    */     public static a c() {
/* 43 */       return new a(CriterionConditionEntity.b.a, CriterionConditionEntity.b.a, CriterionConditionEntity.b.a);
/*    */     }
/*    */     
/*    */     public boolean a(LootTableInfo var0, LootTableInfo var1) {
/* 47 */       if (!this.a.a(var0)) {
/* 48 */         return false;
/*    */       }
/* 50 */       if (!this.b.a(var1)) {
/* 51 */         return false;
/*    */       }
/* 53 */       return true;
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject a(LootSerializationContext var0) {
/* 58 */       JsonObject var1 = super.a(var0);
/*    */       
/* 60 */       var1.add("zombie", this.a.a(var0));
/* 61 */       var1.add("villager", this.b.a(var0));
/*    */       
/* 63 */       return var1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerCuredZombieVillager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */