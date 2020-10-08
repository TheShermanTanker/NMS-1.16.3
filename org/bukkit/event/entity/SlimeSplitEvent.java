/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Slime;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class SlimeSplitEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable {
/* 12 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancel = false;
/*    */   private int count;
/*    */   
/*    */   public SlimeSplitEvent(@NotNull Slime slime, int count) {
/* 17 */     super((Entity)slime);
/* 18 */     this.count = count;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 23 */     return this.cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 28 */     this.cancel = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Slime getEntity() {
/* 34 */     return (Slime)this.entity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCount() {
/* 43 */     return this.count;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setCount(int count) {
/* 52 */     this.count = count;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 58 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 63 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\SlimeSplitEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */