/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.function.Consumer;
/*    */ 
/*    */ 
/*    */ public class LootEntryGroup
/*    */   extends LootEntryChildrenAbstract
/*    */ {
/*    */   LootEntryGroup(LootEntryAbstract[] var0, LootItemCondition[] var1) {
/* 10 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public LootEntryType a() {
/* 15 */     return LootEntries.h;
/*    */   }
/*    */   protected LootEntryChildren a(LootEntryChildren[] var0) {
/*    */     LootEntryChildren var1;
/*    */     LootEntryChildren var2;
/* 20 */     switch (var0.length) {
/*    */       case 0:
/* 22 */         return b;
/*    */       case 1:
/* 24 */         return var0[0];
/*    */       case 2:
/* 26 */         var1 = var0[0];
/* 27 */         var2 = var0[1];
/* 28 */         return (var2, var3) -> {
/*    */             var0.expand(var2, var3);
/*    */             var1.expand(var2, var3);
/*    */             return true;
/*    */           };
/*    */     } 
/*    */     
/* 35 */     return (var1, var2) -> {
/*    */         for (LootEntryChildren var6 : var0)
/*    */           var6.expand(var1, var2); 
/*    */         return true;
/*    */       };
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootEntryGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */