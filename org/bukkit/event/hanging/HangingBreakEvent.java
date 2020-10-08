/*    */ package org.bukkit.event.hanging;
/*    */ 
/*    */ import org.bukkit.entity.Hanging;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class HangingBreakEvent
/*    */   extends HangingEvent
/*    */   implements Cancellable
/*    */ {
/* 12 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancelled;
/*    */   private final RemoveCause cause;
/*    */   
/*    */   public HangingBreakEvent(@NotNull Hanging hanging, @NotNull RemoveCause cause) {
/* 17 */     super(hanging);
/* 18 */     this.cause = cause;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public RemoveCause getCause() {
/* 28 */     return this.cause;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 33 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 38 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public enum RemoveCause
/*    */   {
/* 48 */     ENTITY,
/*    */ 
/*    */ 
/*    */     
/* 52 */     EXPLOSION,
/*    */ 
/*    */ 
/*    */     
/* 56 */     OBSTRUCTION,
/*    */ 
/*    */ 
/*    */     
/* 60 */     PHYSICS,
/*    */ 
/*    */ 
/*    */     
/* 64 */     DEFAULT;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 70 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 75 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\hanging\HangingBreakEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */