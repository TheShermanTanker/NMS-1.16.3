/*    */ package com.destroystokyo.paper.event.entity;
/*    */ 
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ public class PhantomPreSpawnEvent
/*    */   extends PreCreatureSpawnEvent
/*    */ {
/*    */   @NotNull
/*    */   private final Entity entity;
/*    */   
/*    */   public PhantomPreSpawnEvent(@NotNull Location location, @NotNull Entity entity, @NotNull CreatureSpawnEvent.SpawnReason reason) {
/* 18 */     super(location, EntityType.PHANTOM, reason);
/* 19 */     this.entity = entity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Entity getSpawningEntity() {
/* 29 */     return this.entity;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\entity\PhantomPreSpawnEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */