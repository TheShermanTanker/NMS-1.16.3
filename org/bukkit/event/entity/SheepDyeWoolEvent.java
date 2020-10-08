/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.DyeColor;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Sheep;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class SheepDyeWoolEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable {
/* 13 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancel;
/*    */   private DyeColor color;
/*    */   
/*    */   public SheepDyeWoolEvent(@NotNull Sheep sheep, @NotNull DyeColor color) {
/* 18 */     super((Entity)sheep);
/* 19 */     this.cancel = false;
/* 20 */     this.color = color;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 25 */     return this.cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 30 */     this.cancel = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Sheep getEntity() {
/* 36 */     return (Sheep)this.entity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public DyeColor getColor() {
/* 46 */     return this.color;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setColor(@NotNull DyeColor color) {
/* 55 */     this.color = color;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 61 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 66 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\SheepDyeWoolEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */