/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityPortalEvent
/*    */   extends EntityTeleportEvent
/*    */ {
/* 16 */   private static final HandlerList handlers = new HandlerList();
/* 17 */   private int searchRadius = 128;
/*    */   
/*    */   public EntityPortalEvent(@NotNull Entity entity, @NotNull Location from, @Nullable Location to) {
/* 20 */     super(entity, from, to);
/*    */   }
/*    */   
/*    */   public EntityPortalEvent(@NotNull Entity entity, @NotNull Location from, @Nullable Location to, int searchRadius) {
/* 24 */     super(entity, from, to);
/* 25 */     this.searchRadius = searchRadius;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSearchRadius(int searchRadius) {
/* 35 */     this.searchRadius = searchRadius;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getSearchRadius() {
/* 44 */     return this.searchRadius;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 50 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 55 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\EntityPortalEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */