/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.util.Vector;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityPortalExitEvent
/*    */   extends EntityTeleportEvent
/*    */ {
/* 16 */   private static final HandlerList handlers = new HandlerList();
/*    */   private Vector before;
/*    */   private Vector after;
/*    */   
/*    */   public EntityPortalExitEvent(@NotNull Entity entity, @NotNull Location from, @NotNull Location to, @NotNull Vector before, @NotNull Vector after) {
/* 21 */     super(entity, from, to);
/* 22 */     this.before = before;
/* 23 */     this.after = after;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Vector getBefore() {
/* 34 */     return this.before.clone();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Vector getAfter() {
/* 45 */     return this.after.clone();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setAfter(@NotNull Vector after) {
/* 54 */     this.after = after.clone();
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 60 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 65 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\EntityPortalExitEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */