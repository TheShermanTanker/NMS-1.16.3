/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.AbstractVillager;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.inventory.MerchantRecipe;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VillagerReplenishTradeEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable
/*    */ {
/* 17 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private boolean cancelled;
/*    */   private MerchantRecipe recipe;
/*    */   private int bonus;
/*    */   
/*    */   public VillagerReplenishTradeEvent(@NotNull AbstractVillager what, @NotNull MerchantRecipe recipe, int bonus) {
/* 24 */     super((Entity)what);
/* 25 */     this.recipe = recipe;
/* 26 */     this.bonus = bonus;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public MerchantRecipe getRecipe() {
/* 36 */     return this.recipe;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRecipe(@NotNull MerchantRecipe recipe) {
/* 45 */     this.recipe = recipe;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getBonus() {
/* 55 */     return this.bonus;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setBonus(int bonus) {
/* 65 */     this.bonus = bonus;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 70 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 75 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public AbstractVillager getEntity() {
/* 81 */     return (AbstractVillager)super.getEntity();
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 87 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 92 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\VillagerReplenishTradeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */