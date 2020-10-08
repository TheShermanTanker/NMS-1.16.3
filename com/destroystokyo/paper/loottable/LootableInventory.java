/*    */ package com.destroystokyo.paper.loottable;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.loot.Lootable;
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ public interface LootableInventory
/*    */   extends Lootable
/*    */ {
/*    */   boolean isRefillEnabled();
/*    */   
/*    */   boolean hasBeenFilled();
/*    */   
/*    */   default boolean hasPlayerLooted(@NotNull Player player) {
/* 41 */     return hasPlayerLooted(player.getUniqueId());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   boolean hasPlayerLooted(@NotNull UUID paramUUID);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   default Long getLastLooted(@NotNull Player player) {
/* 59 */     return getLastLooted(player.getUniqueId());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   Long getLastLooted(@NotNull UUID paramUUID);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   default boolean setHasPlayerLooted(@NotNull Player player, boolean looted) {
/* 78 */     return setHasPlayerLooted(player.getUniqueId(), looted);
/*    */   }
/*    */   
/*    */   boolean setHasPlayerLooted(@NotNull UUID paramUUID, boolean paramBoolean);
/*    */   
/*    */   boolean hasPendingRefill();
/*    */   
/*    */   long getLastFilled();
/*    */   
/*    */   long getNextRefill();
/*    */   
/*    */   long setNextRefill(long paramLong);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\loottable\LootableInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */