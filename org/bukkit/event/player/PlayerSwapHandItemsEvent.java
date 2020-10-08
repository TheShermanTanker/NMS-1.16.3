/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerSwapHandItemsEvent
/*    */   extends PlayerEvent
/*    */   implements Cancellable
/*    */ {
/* 16 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private ItemStack mainHandItem;
/*    */   private ItemStack offHandItem;
/*    */   private boolean cancelled;
/*    */   
/*    */   public PlayerSwapHandItemsEvent(@NotNull Player player, @NotNull ItemStack mainHandItem, @NotNull ItemStack offHandItem) {
/* 23 */     super(player);
/*    */     
/* 25 */     this.mainHandItem = mainHandItem;
/* 26 */     this.offHandItem = offHandItem;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public ItemStack getMainHandItem() {
/* 36 */     return this.mainHandItem;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setMainHandItem(@Nullable ItemStack mainHandItem) {
/* 45 */     this.mainHandItem = mainHandItem;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public ItemStack getOffHandItem() {
/* 55 */     return this.offHandItem;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setOffHandItem(@Nullable ItemStack offHandItem) {
/* 64 */     this.offHandItem = offHandItem;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 69 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 74 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 80 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 85 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerSwapHandItemsEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */