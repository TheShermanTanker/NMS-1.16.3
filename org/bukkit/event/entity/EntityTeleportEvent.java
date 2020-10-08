/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityTeleportEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable
/*    */ {
/* 17 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancel;
/*    */   private Location from;
/*    */   private Location to;
/*    */   
/*    */   public EntityTeleportEvent(@NotNull Entity what, @NotNull Location from, @Nullable Location to) {
/* 23 */     super(what);
/* 24 */     this.from = from;
/* 25 */     this.to = to;
/* 26 */     this.cancel = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 31 */     return this.cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 36 */     this.cancel = cancel;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Location getFrom() {
/* 46 */     return this.from;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setFrom(@NotNull Location from) {
/* 55 */     this.from = from;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Location getTo() {
/* 65 */     return this.to;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setTo(@Nullable Location to) {
/* 74 */     this.to = to;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 80 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 85 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\EntityTeleportEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */