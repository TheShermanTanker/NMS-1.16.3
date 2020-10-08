/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CriterionTriggerBredAnimals
/*    */   extends CriterionTriggerAbstract<CriterionTriggerBredAnimals.a>
/*    */ {
/* 13 */   private static final MinecraftKey a = new MinecraftKey("bred_animals");
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 17 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public a b(JsonObject var0, CriterionConditionEntity.b var1, LootDeserializationContext var2) {
/* 22 */     CriterionConditionEntity.b var3 = CriterionConditionEntity.b.a(var0, "parent", var2);
/* 23 */     CriterionConditionEntity.b var4 = CriterionConditionEntity.b.a(var0, "partner", var2);
/* 24 */     CriterionConditionEntity.b var5 = CriterionConditionEntity.b.a(var0, "child", var2);
/* 25 */     return new a(var1, var3, var4, var5);
/*    */   }
/*    */   
/*    */   public void a(EntityPlayer var0, EntityAnimal var1, EntityAnimal var2, @Nullable EntityAgeable var3) {
/* 29 */     LootTableInfo var4 = CriterionConditionEntity.b(var0, var1);
/* 30 */     LootTableInfo var5 = CriterionConditionEntity.b(var0, var2);
/* 31 */     LootTableInfo var6 = (var3 != null) ? CriterionConditionEntity.b(var0, var3) : null;
/*    */     
/* 33 */     a(var0, var3 -> var3.a(var0, var1, var2));
/*    */   }
/*    */   
/*    */   public static class a extends CriterionInstanceAbstract {
/*    */     private final CriterionConditionEntity.b a;
/*    */     private final CriterionConditionEntity.b b;
/*    */     private final CriterionConditionEntity.b c;
/*    */     
/*    */     public a(CriterionConditionEntity.b var0, CriterionConditionEntity.b var1, CriterionConditionEntity.b var2, CriterionConditionEntity.b var3) {
/* 42 */       super(CriterionTriggerBredAnimals.b(), var0);
/* 43 */       this.a = var1;
/* 44 */       this.b = var2;
/* 45 */       this.c = var3;
/*    */     }
/*    */     
/*    */     public static a c() {
/* 49 */       return new a(CriterionConditionEntity.b.a, CriterionConditionEntity.b.a, CriterionConditionEntity.b.a, CriterionConditionEntity.b.a);
/*    */     }
/*    */     
/*    */     public static a a(CriterionConditionEntity.a var0) {
/* 53 */       return new a(CriterionConditionEntity.b.a, CriterionConditionEntity.b.a, CriterionConditionEntity.b.a, CriterionConditionEntity.b.a(var0.b()));
/*    */     }
/*    */     
/*    */     public static a a(CriterionConditionEntity var0, CriterionConditionEntity var1, CriterionConditionEntity var2) {
/* 57 */       return new a(CriterionConditionEntity.b.a, CriterionConditionEntity.b.a(var0), CriterionConditionEntity.b.a(var1), CriterionConditionEntity.b.a(var2));
/*    */     }
/*    */     
/*    */     public boolean a(LootTableInfo var0, LootTableInfo var1, @Nullable LootTableInfo var2) {
/* 61 */       if (this.c != CriterionConditionEntity.b.a && (var2 == null || !this.c.a(var2))) {
/* 62 */         return false;
/*    */       }
/*    */       
/* 65 */       return ((this.a.a(var0) && this.b.a(var1)) || (this.a.a(var1) && this.b.a(var0)));
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject a(LootSerializationContext var0) {
/* 70 */       JsonObject var1 = super.a(var0);
/*    */       
/* 72 */       var1.add("parent", this.a.a(var0));
/* 73 */       var1.add("partner", this.b.a(var0));
/* 74 */       var1.add("child", this.c.a(var0));
/*    */       
/* 76 */       return var1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerBredAnimals.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */