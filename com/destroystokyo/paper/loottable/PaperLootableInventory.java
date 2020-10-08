/*    */ package com.destroystokyo.paper.loottable;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import net.minecraft.server.v1_16_R2.World;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.loot.Lootable;
/*    */ 
/*    */ public interface PaperLootableInventory extends LootableInventory, Lootable {
/*    */   PaperLootableInventoryData getLootableData();
/*    */   
/*    */   LootableInventory getAPILootableInventory();
/*    */   
/*    */   World getNMSWorld();
/*    */   
/*    */   default World getBukkitWorld() {
/* 16 */     return (World)getNMSWorld().getWorld();
/*    */   }
/*    */ 
/*    */   
/*    */   default boolean isRefillEnabled() {
/* 21 */     return (getNMSWorld()).paperConfig.autoReplenishLootables;
/*    */   }
/*    */ 
/*    */   
/*    */   default boolean hasBeenFilled() {
/* 26 */     return (getLastFilled() != -1L);
/*    */   }
/*    */ 
/*    */   
/*    */   default boolean hasPlayerLooted(UUID player) {
/* 31 */     return getLootableData().hasPlayerLooted(player);
/*    */   }
/*    */ 
/*    */   
/*    */   default Long getLastLooted(UUID player) {
/* 36 */     return getLootableData().getLastLooted(player);
/*    */   }
/*    */ 
/*    */   
/*    */   default boolean setHasPlayerLooted(UUID player, boolean looted) {
/* 41 */     boolean hasLooted = hasPlayerLooted(player);
/* 42 */     if (hasLooted != looted) {
/* 43 */       getLootableData().setPlayerLootedState(player, looted);
/*    */     }
/* 45 */     return hasLooted;
/*    */   }
/*    */ 
/*    */   
/*    */   default boolean hasPendingRefill() {
/* 50 */     long nextRefill = getLootableData().getNextRefill();
/* 51 */     return (nextRefill != -1L && nextRefill > getLootableData().getLastFill());
/*    */   }
/*    */ 
/*    */   
/*    */   default long getLastFilled() {
/* 56 */     return getLootableData().getLastFill();
/*    */   }
/*    */ 
/*    */   
/*    */   default long getNextRefill() {
/* 61 */     return getLootableData().getNextRefill();
/*    */   }
/*    */ 
/*    */   
/*    */   default long setNextRefill(long refillAt) {
/* 66 */     if (refillAt < -1L) {
/* 67 */       refillAt = -1L;
/*    */     }
/* 69 */     return getLootableData().setNextRefill(refillAt);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\loottable\PaperLootableInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */