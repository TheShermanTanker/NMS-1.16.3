/*    */ package com.destroystokyo.paper.event.entity;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PreSpawnerSpawnEvent
/*    */   extends PreCreatureSpawnEvent
/*    */ {
/*    */   @NotNull
/*    */   private final Location spawnerLocation;
/*    */   
/*    */   public PreSpawnerSpawnEvent(@NotNull Location location, @NotNull EntityType type, @NotNull Location spawnerLocation) {
/* 21 */     super(location, type, CreatureSpawnEvent.SpawnReason.SPAWNER);
/* 22 */     this.spawnerLocation = (Location)Preconditions.checkNotNull(spawnerLocation, "Spawner location may not be null");
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public Location getSpawnerLocation() {
/* 27 */     return this.spawnerLocation;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\entity\PreSpawnerSpawnEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */