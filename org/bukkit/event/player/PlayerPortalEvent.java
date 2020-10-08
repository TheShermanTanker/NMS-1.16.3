/*     */ package org.bukkit.event.player;
/*     */ 
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayerPortalEvent
/*     */   extends PlayerTeleportEvent
/*     */ {
/*  16 */   private static final HandlerList handlers = new HandlerList();
/*  17 */   private int getSearchRadius = 128;
/*     */   private boolean canCreatePortal = true;
/*  19 */   private int creationRadius = 16;
/*     */   
/*     */   public PlayerPortalEvent(@NotNull Player player, @NotNull Location from, @Nullable Location to) {
/*  22 */     super(player, from, to);
/*     */   }
/*     */   
/*     */   public PlayerPortalEvent(@NotNull Player player, @NotNull Location from, @Nullable Location to, @NotNull PlayerTeleportEvent.TeleportCause cause) {
/*  26 */     super(player, from, to, cause);
/*     */   }
/*     */   
/*     */   public PlayerPortalEvent(@NotNull Player player, @NotNull Location from, @Nullable Location to, @NotNull PlayerTeleportEvent.TeleportCause cause, int getSearchRadius, boolean canCreatePortal, int creationRadius) {
/*  30 */     super(player, from, to, cause);
/*  31 */     this.getSearchRadius = getSearchRadius;
/*  32 */     this.canCreatePortal = canCreatePortal;
/*  33 */     this.creationRadius = creationRadius;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSearchRadius(int searchRadius) {
/*  43 */     this.getSearchRadius = searchRadius;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSearchRadius() {
/*  52 */     return this.getSearchRadius;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCanCreatePortal() {
/*  62 */     return this.canCreatePortal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCanCreatePortal(boolean canCreatePortal) {
/*  73 */     this.canCreatePortal = canCreatePortal;
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
/*     */   public void setCreationRadius(int creationRadius) {
/*  90 */     this.creationRadius = creationRadius;
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
/*     */   public int getCreationRadius() {
/* 106 */     return this.creationRadius;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/* 112 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 117 */     return handlers;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerPortalEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */