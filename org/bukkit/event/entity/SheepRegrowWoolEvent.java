/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Sheep;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class SheepRegrowWoolEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable {
/* 12 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancel;
/*    */   
/*    */   public SheepRegrowWoolEvent(@NotNull Sheep sheep) {
/* 16 */     super((Entity)sheep);
/* 17 */     this.cancel = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 22 */     return this.cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 27 */     this.cancel = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Sheep getEntity() {
/* 33 */     return (Sheep)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 39 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 44 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\SheepRegrowWoolEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */