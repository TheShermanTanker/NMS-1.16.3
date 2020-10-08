/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import java.util.function.Consumer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @FunctionalInterface
/*    */ interface LootEntryChildren
/*    */ {
/*    */   public static final LootEntryChildren a = (var0, var1) -> false;
/*    */   public static final LootEntryChildren b = (var0, var1) -> true;
/*    */   
/*    */   default LootEntryChildren a(LootEntryChildren var0) {
/* 16 */     Objects.requireNonNull(var0);
/* 17 */     return (var1, var2) -> (expand(var1, var2) && var0.expand(var1, var2));
/*    */   }
/*    */   
/*    */   default LootEntryChildren b(LootEntryChildren var0) {
/* 21 */     Objects.requireNonNull(var0);
/* 22 */     return (var1, var2) -> (expand(var1, var2) || var0.expand(var1, var2));
/*    */   }
/*    */   
/*    */   boolean expand(LootTableInfo paramLootTableInfo, Consumer<LootEntry> paramConsumer);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootEntryChildren.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */