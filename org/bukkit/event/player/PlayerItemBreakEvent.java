/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerItemBreakEvent
/*    */   extends PlayerEvent
/*    */ {
/* 15 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final ItemStack brokenItem;
/*    */   
/*    */   public PlayerItemBreakEvent(@NotNull Player player, @NotNull ItemStack brokenItem) {
/* 19 */     super(player);
/* 20 */     this.brokenItem = brokenItem;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ItemStack getBrokenItem() {
/* 30 */     return this.brokenItem;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 36 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 41 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerItemBreakEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */