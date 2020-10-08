/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CriterionTriggerPlayerHurtEntity
/*    */   extends CriterionTriggerAbstract<CriterionTriggerPlayerHurtEntity.a>
/*    */ {
/* 11 */   private static final MinecraftKey a = new MinecraftKey("player_hurt_entity");
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 15 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public a b(JsonObject var0, CriterionConditionEntity.b var1, LootDeserializationContext var2) {
/* 20 */     CriterionConditionDamage var3 = CriterionConditionDamage.a(var0.get("damage"));
/* 21 */     CriterionConditionEntity.b var4 = CriterionConditionEntity.b.a(var0, "entity", var2);
/* 22 */     return new a(var1, var3, var4);
/*    */   }
/*    */   
/*    */   public void a(EntityPlayer var0, Entity var1, DamageSource var2, float var3, float var4, boolean var5) {
/* 26 */     LootTableInfo var6 = CriterionConditionEntity.b(var0, var1);
/* 27 */     a(var0, var6 -> var6.a(var0, var1, var2, var3, var4, var5));
/*    */   }
/*    */   
/*    */   public static class a extends CriterionInstanceAbstract {
/*    */     private final CriterionConditionDamage a;
/*    */     private final CriterionConditionEntity.b b;
/*    */     
/*    */     public a(CriterionConditionEntity.b var0, CriterionConditionDamage var1, CriterionConditionEntity.b var2) {
/* 35 */       super(CriterionTriggerPlayerHurtEntity.b(), var0);
/* 36 */       this.a = var1;
/* 37 */       this.b = var2;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public static a a(CriterionConditionDamage.a var0) {
/* 49 */       return new a(CriterionConditionEntity.b.a, var0.b(), CriterionConditionEntity.b.a);
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public boolean a(EntityPlayer var0, LootTableInfo var1, DamageSource var2, float var3, float var4, boolean var5) {
/* 65 */       if (!this.a.a(var0, var2, var3, var4, var5)) {
/* 66 */         return false;
/*    */       }
/* 68 */       if (!this.b.a(var1)) {
/* 69 */         return false;
/*    */       }
/* 71 */       return true;
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject a(LootSerializationContext var0) {
/* 76 */       JsonObject var1 = super.a(var0);
/*    */       
/* 78 */       var1.add("damage", this.a.a());
/* 79 */       var1.add("entity", this.b.a(var0));
/*    */       
/* 81 */       return var1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerPlayerHurtEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */