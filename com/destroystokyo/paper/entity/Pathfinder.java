/*     */ package com.destroystokyo.paper.entity;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Mob;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
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
/*     */ public interface Pathfinder
/*     */ {
/*     */   @NotNull
/*     */   Mob getEntity();
/*     */   
/*     */   void stopPathfinding();
/*     */   
/*     */   boolean hasPath();
/*     */   
/*     */   @Nullable
/*     */   PathResult getCurrentPath();
/*     */   
/*     */   @Nullable
/*     */   PathResult findPath(@NotNull Location paramLocation);
/*     */   
/*     */   @Nullable
/*     */   PathResult findPath(@NotNull LivingEntity paramLivingEntity);
/*     */   
/*     */   default boolean moveTo(@NotNull Location loc) {
/*  70 */     return moveTo(loc, 1.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default boolean moveTo(@NotNull Location loc, double speed) {
/*  81 */     PathResult path = findPath(loc);
/*  82 */     return (path != null && moveTo(path, speed));
/*     */   }
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
/*     */   default boolean moveTo(@NotNull LivingEntity target) {
/*  98 */     return moveTo(target, 1.0D);
/*     */   }
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
/*     */   default boolean moveTo(@NotNull LivingEntity target, double speed) {
/* 115 */     PathResult path = findPath(target);
/* 116 */     return (path != null && moveTo(path, speed));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default boolean moveTo(@NotNull PathResult path) {
/* 127 */     return moveTo(path, 1.0D);
/*     */   }
/*     */   
/*     */   boolean moveTo(@NotNull PathResult paramPathResult, double paramDouble);
/*     */   
/*     */   boolean canOpenDoors();
/*     */   
/*     */   void setCanOpenDoors(boolean paramBoolean);
/*     */   
/*     */   boolean canPassDoors();
/*     */   
/*     */   void setCanPassDoors(boolean paramBoolean);
/*     */   
/*     */   boolean canFloat();
/*     */   
/*     */   void setCanFloat(boolean paramBoolean);
/*     */   
/*     */   public static interface PathResult {
/*     */     @NotNull
/*     */     List<Location> getPoints();
/*     */     
/*     */     int getNextPointIndex();
/*     */     
/*     */     @Nullable
/*     */     Location getNextPoint();
/*     */     
/*     */     @Nullable
/*     */     Location getFinalPoint();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\entity\Pathfinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */