/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ 
/*    */ 
/*    */ public interface LootItemCondition
/*    */   extends LootItemUser, Predicate<LootTableInfo>
/*    */ {
/*    */   LootItemConditionType b();
/*    */   
/*    */   @FunctionalInterface
/*    */   public static interface a
/*    */   {
/*    */     LootItemCondition build();
/*    */     
/*    */     default a a() {
/* 17 */       return LootItemConditionInverted.a(this);
/*    */     }
/*    */     
/*    */     default LootItemConditionAlternative.a a(a var0) {
/* 21 */       return LootItemConditionAlternative.a(new a[] { this, var0 });
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */