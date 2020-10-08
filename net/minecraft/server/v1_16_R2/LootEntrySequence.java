/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.function.Consumer;
/*    */ 
/*    */ 
/*    */ public class LootEntrySequence
/*    */   extends LootEntryChildrenAbstract
/*    */ {
/*    */   LootEntrySequence(LootEntryAbstract[] var0, LootItemCondition[] var1) {
/* 10 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public LootEntryType a() {
/* 15 */     return LootEntries.g;
/*    */   }
/*    */ 
/*    */   
/*    */   protected LootEntryChildren a(LootEntryChildren[] var0) {
/* 20 */     switch (var0.length) {
/*    */       case 0:
/* 22 */         return b;
/*    */       case 1:
/* 24 */         return var0[0];
/*    */       case 2:
/* 26 */         return var0[0].a(var0[1]);
/*    */     } 
/* 28 */     return (var1, var2) -> {
/*    */         for (LootEntryChildren var6 : var0) {
/*    */           if (!var6.expand(var1, var2))
/*    */             return false; 
/*    */         } 
/*    */         return true;
/*    */       };
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootEntrySequence.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */