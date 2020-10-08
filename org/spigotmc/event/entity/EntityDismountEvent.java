/*    */ package org.spigotmc.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.entity.EntityEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class EntityDismountEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable
/*    */ {
/* 14 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private boolean cancelled;
/*    */   private final Entity dismounted;
/*    */   private final boolean isCancellable;
/*    */   
/*    */   public EntityDismountEvent(@NotNull Entity what, @NotNull Entity dismounted) {
/* 21 */     this(what, dismounted, true);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public EntityDismountEvent(@NotNull Entity what, @NotNull Entity dismounted, boolean isCancellable) {
/* 27 */     super(what);
/* 28 */     this.dismounted = dismounted;
/* 29 */     this.isCancellable = isCancellable;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public Entity getDismounted() {
/* 34 */     return this.dismounted;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 39 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 45 */     if (cancel && !this.isCancellable) {
/*    */       return;
/*    */     }
/* 48 */     this.cancelled = cancel;
/*    */   }
/*    */   
/*    */   public boolean isCancellable() {
/* 52 */     return this.isCancellable;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 59 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 64 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\spigotmc\event\entity\EntityDismountEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */