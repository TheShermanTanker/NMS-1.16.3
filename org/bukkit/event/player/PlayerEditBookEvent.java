/*     */ package org.bukkit.event.player;
/*     */ 
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.bukkit.inventory.meta.BookMeta;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class PlayerEditBookEvent
/*     */   extends PlayerEvent
/*     */   implements Cancellable
/*     */ {
/*  16 */   private static final HandlerList handlers = new HandlerList();
/*     */   
/*     */   private final BookMeta previousBookMeta;
/*     */   private final int slot;
/*     */   private BookMeta newBookMeta;
/*     */   private boolean isSigning;
/*     */   private boolean cancel;
/*     */   
/*     */   public PlayerEditBookEvent(@NotNull Player who, int slot, @NotNull BookMeta previousBookMeta, @NotNull BookMeta newBookMeta, boolean isSigning) {
/*  25 */     super(who);
/*     */     
/*  27 */     Validate.isTrue((slot >= -1 && slot <= 8), "Slot must be in range (-1)-8 inclusive");
/*  28 */     Validate.notNull(previousBookMeta, "Previous book meta must not be null");
/*  29 */     Validate.notNull(newBookMeta, "New book meta must not be null");
/*     */     
/*  31 */     Bukkit.getItemFactory().equals((ItemMeta)previousBookMeta, (ItemMeta)newBookMeta);
/*     */     
/*  33 */     this.previousBookMeta = previousBookMeta;
/*  34 */     this.newBookMeta = newBookMeta;
/*  35 */     this.slot = slot;
/*  36 */     this.isSigning = isSigning;
/*  37 */     this.cancel = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public BookMeta getPreviousBookMeta() {
/*  50 */     return this.previousBookMeta.clone();
/*     */   }
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
/*     */   @NotNull
/*     */   public BookMeta getNewBookMeta() {
/*  64 */     return this.newBookMeta.clone();
/*     */   }
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
/*     */   @Deprecated
/*     */   public int getSlot() {
/*  79 */     return this.slot;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNewBookMeta(@NotNull BookMeta newBookMeta) throws IllegalArgumentException {
/*  89 */     Validate.notNull(newBookMeta, "New book meta must not be null");
/*  90 */     Bukkit.getItemFactory().equals((ItemMeta)newBookMeta, null);
/*  91 */     this.newBookMeta = newBookMeta.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSigning() {
/* 101 */     return this.isSigning;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSigning(boolean signing) {
/* 111 */     this.isSigning = signing;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/* 117 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 122 */     return handlers;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/* 127 */     return this.cancel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCancelled(boolean cancel) {
/* 132 */     this.cancel = cancel;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerEditBookEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */