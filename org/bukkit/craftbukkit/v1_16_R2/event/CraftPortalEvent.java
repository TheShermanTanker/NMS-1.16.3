/*    */ package org.bukkit.craftbukkit.v1_16_R2.event;
/*    */ 
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.event.entity.EntityPortalEvent;
/*    */ import org.bukkit.event.player.PlayerPortalEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CraftPortalEvent
/*    */ {
/*    */   private final Location to;
/*    */   private final int searchRadius;
/*    */   private final int creationRadius;
/*    */   private final boolean canCreatePortal;
/*    */   private final boolean cancelled;
/*    */   
/*    */   public CraftPortalEvent(EntityPortalEvent portalEvent) {
/* 19 */     this.to = portalEvent.getTo();
/* 20 */     this.searchRadius = portalEvent.getSearchRadius();
/* 21 */     this.cancelled = portalEvent.isCancelled();
/* 22 */     this.creationRadius = 0;
/* 23 */     this.canCreatePortal = false;
/*    */   }
/*    */   
/*    */   public CraftPortalEvent(PlayerPortalEvent portalEvent) {
/* 27 */     this.to = portalEvent.getTo();
/* 28 */     this.searchRadius = portalEvent.getSearchRadius();
/* 29 */     this.creationRadius = portalEvent.getCreationRadius();
/* 30 */     this.canCreatePortal = portalEvent.getCanCreatePortal();
/* 31 */     this.cancelled = portalEvent.isCancelled();
/*    */   }
/*    */   
/*    */   public Location getTo() {
/* 35 */     return this.to;
/*    */   }
/*    */   
/*    */   public int getSearchRadius() {
/* 39 */     return this.searchRadius;
/*    */   }
/*    */   
/*    */   public int getCreationRadius() {
/* 43 */     return this.creationRadius;
/*    */   }
/*    */   
/*    */   public boolean getCanCreatePortal() {
/* 47 */     return this.canCreatePortal;
/*    */   }
/*    */   
/*    */   public boolean isCancelled() {
/* 51 */     return this.cancelled;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\event\CraftPortalEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */