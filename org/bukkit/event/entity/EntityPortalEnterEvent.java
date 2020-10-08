/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class EntityPortalEnterEvent
/*    */   extends EntityEvent
/*    */ {
/* 12 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final Location location;
/*    */   
/*    */   public EntityPortalEnterEvent(@NotNull Entity entity, @NotNull Location location) {
/* 16 */     super(entity);
/* 17 */     this.location = location;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Location getLocation() {
/* 27 */     return this.location;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 33 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 38 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\EntityPortalEnterEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */