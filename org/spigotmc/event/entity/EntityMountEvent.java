/*    */ package org.spigotmc.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.entity.EntityEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class EntityMountEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable
/*    */ {
/* 14 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancelled;
/*    */   private final Entity mount;
/*    */   
/*    */   public EntityMountEvent(@NotNull Entity what, @NotNull Entity mount) {
/* 19 */     super(what);
/* 20 */     this.mount = mount;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public Entity getMount() {
/* 25 */     return this.mount;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 30 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 35 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 41 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 46 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\spigotmc\event\entity\EntityMountEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */