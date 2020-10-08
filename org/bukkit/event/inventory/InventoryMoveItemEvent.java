/*     */ package org.bukkit.event.inventory;
/*     */ 
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InventoryMoveItemEvent
/*     */   extends Event
/*     */   implements Cancellable
/*     */ {
/*  28 */   private static final HandlerList handlers = new HandlerList();
/*     */   private boolean cancelled;
/*     */   private final Inventory sourceInventory;
/*     */   private final Inventory destinationInventory;
/*     */   private ItemStack itemStack;
/*     */   private final boolean didSourceInitiate;
/*     */   public boolean calledGetItem;
/*     */   public boolean calledSetItem;
/*     */   
/*     */   public InventoryMoveItemEvent(@NotNull Inventory sourceInventory, @NotNull ItemStack itemStack, @NotNull Inventory destinationInventory, boolean didSourceInitiate) {
/*  38 */     Validate.notNull(itemStack, "ItemStack cannot be null");
/*  39 */     this.sourceInventory = sourceInventory;
/*  40 */     this.itemStack = itemStack;
/*  41 */     this.destinationInventory = destinationInventory;
/*  42 */     this.didSourceInitiate = didSourceInitiate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Inventory getSource() {
/*  52 */     return this.sourceInventory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack getItem() {
/*  63 */     this.calledGetItem = true;
/*  64 */     return this.itemStack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setItem(@NotNull ItemStack itemStack) {
/*  75 */     Validate.notNull(itemStack, "ItemStack cannot be null.  Cancel the event if you want nothing to be transferred.");
/*  76 */     this.calledSetItem = true;
/*  77 */     this.itemStack = itemStack.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Inventory getDestination() {
/*  87 */     return this.destinationInventory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Inventory getInitiator() {
/*  98 */     return this.didSourceInitiate ? this.sourceInventory : this.destinationInventory;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/* 103 */     return this.cancelled;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCancelled(boolean cancel) {
/* 108 */     this.cancelled = cancel;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/* 114 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 119 */     return handlers;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\inventory\InventoryMoveItemEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */