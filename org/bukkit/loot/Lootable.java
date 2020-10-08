/*    */ package org.bukkit.loot;
/*    */ 
/*    */ import org.jetbrains.annotations.Nullable;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface Lootable
/*    */ {
/*    */   void setLootTable(@Nullable LootTable paramLootTable);
/*    */   
/*    */   @Nullable
/*    */   LootTable getLootTable();
/*    */   
/*    */   default void setLootTable(@Nullable LootTable table, long seed) {
/* 47 */     setLootTable(table);
/* 48 */     setSeed(seed);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   default boolean hasLootTable() {
/* 56 */     return (getLootTable() != null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   default void clearLootTable() {
/* 63 */     setLootTable(null);
/*    */   }
/*    */   
/*    */   void setSeed(long paramLong);
/*    */   
/*    */   long getSeed();
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\loot\Lootable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */