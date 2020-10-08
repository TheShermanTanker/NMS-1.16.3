/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ 
/*    */ public class CriterionTriggerEffectsChanged
/*    */   extends CriterionTriggerAbstract<CriterionTriggerEffectsChanged.a>
/*    */ {
/*  8 */   private static final MinecraftKey a = new MinecraftKey("effects_changed");
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 12 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public a b(JsonObject var0, CriterionConditionEntity.b var1, LootDeserializationContext var2) {
/* 17 */     CriterionConditionMobEffect var3 = CriterionConditionMobEffect.a(var0.get("effects"));
/* 18 */     return new a(var1, var3);
/*    */   }
/*    */   
/*    */   public void a(EntityPlayer var0) {
/* 22 */     a(var0, var1 -> var1.a(var0));
/*    */   }
/*    */   
/*    */   public static class a extends CriterionInstanceAbstract {
/*    */     private final CriterionConditionMobEffect a;
/*    */     
/*    */     public a(CriterionConditionEntity.b var0, CriterionConditionMobEffect var1) {
/* 29 */       super(CriterionTriggerEffectsChanged.b(), var0);
/* 30 */       this.a = var1;
/*    */     }
/*    */     
/*    */     public static a a(CriterionConditionMobEffect var0) {
/* 34 */       return new a(CriterionConditionEntity.b.a, var0);
/*    */     }
/*    */     
/*    */     public boolean a(EntityPlayer var0) {
/* 38 */       return this.a.a(var0);
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject a(LootSerializationContext var0) {
/* 43 */       JsonObject var1 = super.a(var0);
/*    */       
/* 45 */       var1.add("effects", this.a.b());
/*    */       
/* 47 */       return var1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerEffectsChanged.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */