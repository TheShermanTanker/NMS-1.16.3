/*    */ package com.destroystokyo.paper.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Witch;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.entity.EntityEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class WitchConsumePotionEvent
/*    */   extends EntityEvent implements Cancellable {
/*    */   @Nullable
/*    */   private ItemStack potion;
/*    */   
/*    */   public WitchConsumePotionEvent(@NotNull Witch witch, @Nullable ItemStack potion) {
/* 18 */     super((Entity)witch);
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
/*    */ 
/*    */     
/* 56 */     this.cancelled = false;
/*    */     this.potion = potion; }
/*    */   @NotNull
/*    */   public Witch getEntity() { return (Witch)super.getEntity(); }
/*    */   @Nullable
/*    */   public ItemStack getPotion() { return this.potion; }
/*    */   public void setPotion(@Nullable ItemStack potion) { this.potion = (potion != null) ? potion.clone() : null; } public boolean isCancelled() {
/* 63 */     return (this.cancelled || this.potion == null);
/*    */   }
/*    */   private static final HandlerList handlers = new HandlerList(); private boolean cancelled;
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 68 */     this.cancelled = cancel;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/*    */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/*    */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\entity\WitchConsumePotionEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */