/*     */ package com.destroystokyo.paper.event.entity;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PreCreatureSpawnEvent
/*     */   extends Event
/*     */   implements Cancellable
/*     */ {
/*     */   @NotNull
/*     */   private final Location location;
/*     */   @NotNull
/*     */   private final EntityType type;
/*     */   @NotNull
/*     */   private final CreatureSpawnEvent.SpawnReason reason;
/*     */   private boolean shouldAbortSpawn;
/*     */   
/*     */   @NotNull
/*     */   public Location getSpawnLocation() {
/*     */     return this.location;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public EntityType getType() {
/*     */     return this.type;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public CreatureSpawnEvent.SpawnReason getReason() {
/*     */     return this.reason;
/*     */   }
/*     */   
/*     */   public boolean shouldAbortSpawn() {
/*     */     return this.shouldAbortSpawn;
/*     */   }
/*     */   
/*     */   public PreCreatureSpawnEvent(@NotNull Location location, @NotNull EntityType type, @NotNull CreatureSpawnEvent.SpawnReason reason) {
/*  86 */     this.cancelled = false;
/*     */     this.location = ((Location)Preconditions.checkNotNull(location, "Location may not be null")).clone();
/*     */     this.type = (EntityType)Preconditions.checkNotNull(type, "Type may not be null");
/*     */     this.reason = (CreatureSpawnEvent.SpawnReason)Preconditions.checkNotNull(reason, "Reason may not be null");
/*     */   }
/*     */   
/*     */   public boolean isCancelled() {
/*  93 */     return this.cancelled;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setShouldAbortSpawn(boolean shouldAbortSpawn) {
/*     */     this.shouldAbortSpawn = shouldAbortSpawn;
/*     */   }
/*     */   
/*     */   public void setCancelled(boolean cancel) {
/* 102 */     this.cancelled = cancel;
/*     */   }
/*     */   
/*     */   private static final HandlerList handlers = new HandlerList();
/*     */   private boolean cancelled;
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/*     */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/*     */     return handlers;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\entity\PreCreatureSpawnEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */