/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CriterionTriggerRecipeUnlocked
/*    */   extends CriterionTriggerAbstract<CriterionTriggerRecipeUnlocked.a>
/*    */ {
/* 10 */   private static final MinecraftKey a = new MinecraftKey("recipe_unlocked");
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 14 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public a b(JsonObject var0, CriterionConditionEntity.b var1, LootDeserializationContext var2) {
/* 19 */     MinecraftKey var3 = new MinecraftKey(ChatDeserializer.h(var0, "recipe"));
/* 20 */     return new a(var1, var3);
/*    */   }
/*    */   
/*    */   public void a(EntityPlayer var0, IRecipe<?> var1) {
/* 24 */     a(var0, var1 -> var1.a(var0));
/*    */   }
/*    */   
/*    */   public static a a(MinecraftKey var0) {
/* 28 */     return new a(CriterionConditionEntity.b.a, var0);
/*    */   }
/*    */   
/*    */   public static class a extends CriterionInstanceAbstract {
/*    */     private final MinecraftKey a;
/*    */     
/*    */     public a(CriterionConditionEntity.b var0, MinecraftKey var1) {
/* 35 */       super(CriterionTriggerRecipeUnlocked.b(), var0);
/* 36 */       this.a = var1;
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject a(LootSerializationContext var0) {
/* 41 */       JsonObject var1 = super.a(var0);
/* 42 */       var1.addProperty("recipe", this.a.toString());
/* 43 */       return var1;
/*    */     }
/*    */     
/*    */     public boolean a(IRecipe<?> var0) {
/* 47 */       return this.a.equals(var0.getKey());
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerRecipeUnlocked.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */