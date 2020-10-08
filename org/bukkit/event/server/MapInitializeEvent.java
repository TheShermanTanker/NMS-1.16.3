/*    */ package org.bukkit.event.server;
/*    */ 
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.map.MapView;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class MapInitializeEvent
/*    */   extends ServerEvent
/*    */ {
/* 11 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final MapView mapView;
/*    */   
/*    */   public MapInitializeEvent(@NotNull MapView mapView) {
/* 15 */     this.mapView = mapView;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public MapView getMap() {
/* 25 */     return this.mapView;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 31 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 36 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\server\MapInitializeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */