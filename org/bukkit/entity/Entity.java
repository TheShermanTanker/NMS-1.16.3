/*     */ package org.bukkit.entity;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import org.bukkit.Chunk;
/*     */ import org.bukkit.EntityEffect;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Nameable;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.block.PistonMoveReaction;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */ import org.bukkit.event.player.PlayerTeleportEvent;
/*     */ import org.bukkit.metadata.Metadatable;
/*     */ import org.bukkit.persistence.PersistentDataHolder;
/*     */ import org.bukkit.util.BoundingBox;
/*     */ import org.bukkit.util.Vector;
/*     */ import org.jetbrains.annotations.Contract;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface Entity
/*     */   extends Metadatable, CommandSender, Nameable, PersistentDataHolder
/*     */ {
/*     */   @NotNull
/*     */   default CompletableFuture<Boolean> teleportAsync(@NotNull Location loc) {
/* 167 */     return teleportAsync(loc, PlayerTeleportEvent.TeleportCause.PLUGIN);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   default CompletableFuture<Boolean> teleportAsync(@NotNull Location loc, @NotNull PlayerTeleportEvent.TeleportCause cause) {
/* 177 */     CompletableFuture<Boolean> future = new CompletableFuture<>();
/* 178 */     loc.getWorld().getChunkAtAsyncUrgently(loc).thenAccept(chunk -> future.complete(Boolean.valueOf(teleport(loc, cause)))).exceptionally(ex -> {
/*     */           future.completeExceptionally(ex);
/*     */           return null;
/*     */         });
/* 182 */     return future;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   Location getLocation();
/*     */   
/*     */   @Contract("null -> null; !null -> !null")
/*     */   @Nullable
/*     */   Location getLocation(@Nullable Location paramLocation);
/*     */   
/*     */   void setVelocity(@NotNull Vector paramVector);
/*     */   
/*     */   @NotNull
/*     */   Vector getVelocity();
/*     */   
/*     */   double getHeight();
/*     */   
/*     */   double getWidth();
/*     */   
/*     */   @NotNull
/*     */   BoundingBox getBoundingBox();
/*     */   
/*     */   boolean isOnGround();
/*     */   
/*     */   @NotNull
/*     */   World getWorld();
/*     */   
/*     */   void setRotation(float paramFloat1, float paramFloat2);
/*     */   
/*     */   boolean teleport(@NotNull Location paramLocation);
/*     */   
/*     */   boolean teleport(@NotNull Location paramLocation, @NotNull PlayerTeleportEvent.TeleportCause paramTeleportCause);
/*     */   
/*     */   boolean teleport(@NotNull Entity paramEntity);
/*     */   
/*     */   boolean teleport(@NotNull Entity paramEntity, @NotNull PlayerTeleportEvent.TeleportCause paramTeleportCause);
/*     */   
/*     */   @NotNull
/*     */   List<Entity> getNearbyEntities(double paramDouble1, double paramDouble2, double paramDouble3);
/*     */   
/*     */   int getEntityId();
/*     */   
/*     */   int getFireTicks();
/*     */   
/*     */   int getMaxFireTicks();
/*     */   
/*     */   void setFireTicks(int paramInt);
/*     */   
/*     */   void remove();
/*     */   
/*     */   boolean isDead();
/*     */   
/*     */   boolean isValid();
/*     */   
/*     */   @NotNull
/*     */   Server getServer();
/*     */   
/*     */   boolean isPersistent();
/*     */   
/*     */   void setPersistent(boolean paramBoolean);
/*     */   
/*     */   @Deprecated
/*     */   @Nullable
/*     */   Entity getPassenger();
/*     */   
/*     */   @Deprecated
/*     */   boolean setPassenger(@NotNull Entity paramEntity);
/*     */   
/*     */   @NotNull
/*     */   List<Entity> getPassengers();
/*     */   
/*     */   boolean addPassenger(@NotNull Entity paramEntity);
/*     */   
/*     */   boolean removePassenger(@NotNull Entity paramEntity);
/*     */   
/*     */   boolean isEmpty();
/*     */   
/*     */   boolean eject();
/*     */   
/*     */   float getFallDistance();
/*     */   
/*     */   void setFallDistance(float paramFloat);
/*     */   
/*     */   void setLastDamageCause(@Nullable EntityDamageEvent paramEntityDamageEvent);
/*     */   
/*     */   @Nullable
/*     */   EntityDamageEvent getLastDamageCause();
/*     */   
/*     */   @NotNull
/*     */   UUID getUniqueId();
/*     */   
/*     */   int getTicksLived();
/*     */   
/*     */   void setTicksLived(int paramInt);
/*     */   
/*     */   void playEffect(@NotNull EntityEffect paramEntityEffect);
/*     */   
/*     */   @NotNull
/*     */   EntityType getType();
/*     */   
/*     */   boolean isInsideVehicle();
/*     */   
/*     */   boolean leaveVehicle();
/*     */   
/*     */   @Nullable
/*     */   Entity getVehicle();
/*     */   
/*     */   void setCustomNameVisible(boolean paramBoolean);
/*     */   
/*     */   boolean isCustomNameVisible();
/*     */   
/*     */   void setGlowing(boolean paramBoolean);
/*     */   
/*     */   boolean isGlowing();
/*     */   
/*     */   void setInvulnerable(boolean paramBoolean);
/*     */   
/*     */   boolean isInvulnerable();
/*     */   
/*     */   boolean isSilent();
/*     */   
/*     */   void setSilent(boolean paramBoolean);
/*     */   
/*     */   boolean hasGravity();
/*     */   
/*     */   void setGravity(boolean paramBoolean);
/*     */   
/*     */   int getPortalCooldown();
/*     */   
/*     */   void setPortalCooldown(int paramInt);
/*     */   
/*     */   @NotNull
/*     */   Set<String> getScoreboardTags();
/*     */   
/*     */   boolean addScoreboardTag(@NotNull String paramString);
/*     */   
/*     */   boolean removeScoreboardTag(@NotNull String paramString);
/*     */   
/*     */   @NotNull
/*     */   PistonMoveReaction getPistonMoveReaction();
/*     */   
/*     */   @NotNull
/*     */   BlockFace getFacing();
/*     */   
/*     */   @NotNull
/*     */   Pose getPose();
/*     */   
/*     */   @NotNull
/*     */   Spigot spigot();
/*     */   
/*     */   @Nullable
/*     */   Location getOrigin();
/*     */   
/*     */   boolean fromMobSpawner();
/*     */   
/*     */   @NotNull
/*     */   Chunk getChunk();
/*     */   
/*     */   @NotNull
/*     */   CreatureSpawnEvent.SpawnReason getEntitySpawnReason();
/*     */   
/*     */   boolean isInWater();
/*     */   
/*     */   boolean isInRain();
/*     */   
/*     */   boolean isInBubbleColumn();
/*     */   
/*     */   boolean isInWaterOrRain();
/*     */   
/*     */   boolean isInWaterOrBubbleColumn();
/*     */   
/*     */   boolean isInWaterOrRainOrBubbleColumn();
/*     */   
/*     */   boolean isInLava();
/*     */   
/*     */   public static class Spigot extends CommandSender.Spigot {}
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Entity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */