/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ 
/*    */ 
/*    */ public class CriterionTriggerEntityHurtPlayer
/*    */   extends CriterionTriggerAbstract<CriterionTriggerEntityHurtPlayer.a>
/*    */ {
/*  9 */   private static final MinecraftKey a = new MinecraftKey("entity_hurt_player");
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 13 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public a b(JsonObject var0, CriterionConditionEntity.b var1, LootDeserializationContext var2) {
/* 18 */     CriterionConditionDamage var3 = CriterionConditionDamage.a(var0.get("damage"));
/* 19 */     return new a(var1, var3);
/*    */   }
/*    */   
/*    */   public void a(EntityPlayer var0, DamageSource var1, float var2, float var3, boolean var4) {
/* 23 */     a(var0, var5 -> var5.a(var0, var1, var2, var3, var4));
/*    */   }
/*    */   
/*    */   public static class a extends CriterionInstanceAbstract {
/*    */     private final CriterionConditionDamage a;
/*    */     
/*    */     public a(CriterionConditionEntity.b var0, CriterionConditionDamage var1) {
/* 30 */       super(CriterionTriggerEntityHurtPlayer.b(), var0);
/* 31 */       this.a = var1;
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
/* 43 */       return new a(CriterionConditionEntity.b.a, var0.b());
/*    */     }
/*    */     
/*    */     public boolean a(EntityPlayer var0, DamageSource var1, float var2, float var3, boolean var4) {
/* 47 */       if (!this.a.a(var0, var1, var2, var3, var4)) {
/* 48 */         return false;
/*    */       }
/* 50 */       return true;
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject a(LootSerializationContext var0) {
/* 55 */       JsonObject var1 = super.a(var0);
/*    */       
/* 57 */       var1.add("damage", this.a.a());
/*    */       
/* 59 */       return var1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerEntityHurtPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */