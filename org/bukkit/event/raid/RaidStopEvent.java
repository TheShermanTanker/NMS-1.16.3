/*    */ package org.bukkit.event.raid;
/*    */ 
/*    */ import org.bukkit.Raid;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RaidStopEvent
/*    */   extends RaidEvent
/*    */ {
/* 13 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private final Reason reason;
/*    */   
/*    */   public RaidStopEvent(@NotNull Raid raid, @NotNull World world, @NotNull Reason reason) {
/* 18 */     super(raid, world);
/* 19 */     this.reason = reason;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Reason getReason() {
/* 29 */     return this.reason;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 35 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 40 */     return handlers;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public enum Reason
/*    */   {
/* 48 */     PEACE,
/*    */ 
/*    */ 
/*    */     
/* 52 */     TIMEOUT,
/*    */ 
/*    */ 
/*    */     
/* 56 */     FINISHED,
/*    */ 
/*    */ 
/*    */     
/* 60 */     UNSPAWNABLE,
/*    */ 
/*    */ 
/*    */     
/* 64 */     NOT_IN_VILLAGE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\raid\RaidStopEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */