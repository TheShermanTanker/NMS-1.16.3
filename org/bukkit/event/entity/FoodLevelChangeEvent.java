/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.HumanEntity;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class FoodLevelChangeEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable {
/* 14 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancel = false;
/*    */   private int level;
/*    */   private final ItemStack item;
/*    */   
/*    */   public FoodLevelChangeEvent(@NotNull HumanEntity what, int level) {
/* 20 */     this(what, level, null);
/*    */   }
/*    */   
/*    */   public FoodLevelChangeEvent(@NotNull HumanEntity what, int level, @Nullable ItemStack item) {
/* 24 */     super((Entity)what);
/* 25 */     this.level = level;
/* 26 */     this.item = item;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HumanEntity getEntity() {
/* 32 */     return (HumanEntity)this.entity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public ItemStack getItem() {
/* 42 */     return (this.item == null) ? null : this.item.clone();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getFoodLevel() {
/* 54 */     return this.level;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setFoodLevel(int level) {
/* 65 */     if (level < 0) level = 0;
/*    */     
/* 67 */     this.level = level;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 72 */     return this.cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 77 */     this.cancel = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 83 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 88 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\FoodLevelChangeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */