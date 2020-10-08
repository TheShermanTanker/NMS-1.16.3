/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.AbstractVillager;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.inventory.MerchantRecipe;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class VillagerAcquireTradeEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable
/*    */ {
/* 14 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private boolean cancelled;
/*    */   private MerchantRecipe recipe;
/*    */   
/*    */   public VillagerAcquireTradeEvent(@NotNull AbstractVillager what, @NotNull MerchantRecipe recipe) {
/* 20 */     super((Entity)what);
/* 21 */     this.recipe = recipe;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public MerchantRecipe getRecipe() {
/* 31 */     return this.recipe;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRecipe(@NotNull MerchantRecipe recipe) {
/* 40 */     this.recipe = recipe;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 45 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 50 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public AbstractVillager getEntity() {
/* 56 */     return (AbstractVillager)super.getEntity();
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 62 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 67 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\VillagerAcquireTradeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */