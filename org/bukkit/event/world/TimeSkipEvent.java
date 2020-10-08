/*    */ package org.bukkit.event.world;
/*    */ 
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TimeSkipEvent
/*    */   extends WorldEvent
/*    */   implements Cancellable
/*    */ {
/* 15 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private boolean cancelled;
/*    */   private final SkipReason skipReason;
/*    */   private long skipAmount;
/*    */   
/*    */   public TimeSkipEvent(@NotNull World world, @NotNull SkipReason skipReason, @NotNull long skipAmount) {
/* 22 */     super(world);
/* 23 */     this.skipReason = skipReason;
/* 24 */     this.skipAmount = skipAmount;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public SkipReason getSkipReason() {
/* 34 */     return this.skipReason;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public long getSkipAmount() {
/* 43 */     return this.skipAmount;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSkipAmount(long skipAmount) {
/* 52 */     this.skipAmount = skipAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 57 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 62 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 68 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 73 */     return handlers;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public enum SkipReason
/*    */   {
/* 84 */     COMMAND,
/*    */ 
/*    */ 
/*    */     
/* 88 */     CUSTOM,
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 93 */     NIGHT_SKIP;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\world\TimeSkipEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */