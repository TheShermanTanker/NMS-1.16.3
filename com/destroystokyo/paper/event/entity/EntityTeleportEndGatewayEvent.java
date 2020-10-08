/*    */ package com.destroystokyo.paper.event.entity;
/*    */ 
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.block.EndGateway;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.entity.EntityTeleportEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class EntityTeleportEndGatewayEvent
/*    */   extends EntityTeleportEvent
/*    */ {
/*    */   @NotNull
/*    */   private final EndGateway gateway;
/*    */   
/*    */   public EntityTeleportEndGatewayEvent(@NotNull Entity what, @NotNull Location from, @NotNull Location to, @NotNull EndGateway gateway) {
/* 17 */     super(what, from, to);
/* 18 */     this.gateway = gateway;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public EndGateway getGateway() {
/* 28 */     return this.gateway;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\entity\EntityTeleportEndGatewayEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */