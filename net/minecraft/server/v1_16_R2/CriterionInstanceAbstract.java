/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ 
/*    */ public abstract class CriterionInstanceAbstract
/*    */   implements CriterionInstance
/*    */ {
/*    */   private final MinecraftKey a;
/*    */   private final CriterionConditionEntity.b b;
/*    */   
/*    */   public CriterionInstanceAbstract(MinecraftKey var0, CriterionConditionEntity.b var1) {
/* 12 */     this.a = var0;
/* 13 */     this.b = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 18 */     return this.a;
/*    */   }
/*    */   
/*    */   protected CriterionConditionEntity.b b() {
/* 22 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonObject a(LootSerializationContext var0) {
/* 27 */     JsonObject var1 = new JsonObject();
/* 28 */     var1.add("player", this.b.a(var0));
/* 29 */     return var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 34 */     return "AbstractCriterionInstance{criterion=" + this.a + '}';
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionInstanceAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */