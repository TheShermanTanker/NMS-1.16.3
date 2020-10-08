/*    */ package org.bukkit.event.world;
/*    */ 
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class WorldUnloadEvent
/*    */   extends WorldEvent
/*    */   implements Cancellable
/*    */ {
/* 12 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean isCancelled;
/*    */   
/*    */   public WorldUnloadEvent(@NotNull World world) {
/* 16 */     super(world);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 21 */     return this.isCancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 26 */     this.isCancelled = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 32 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 37 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\world\WorldUnloadEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */