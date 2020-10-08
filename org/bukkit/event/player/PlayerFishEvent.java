/*     */ package org.bukkit.event.player;
/*     */ 
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.FishHook;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class PlayerFishEvent
/*     */   extends PlayerEvent
/*     */   implements Cancellable
/*     */ {
/*  15 */   private static final HandlerList handlers = new HandlerList();
/*     */   private final Entity entity;
/*     */   private boolean cancel = false;
/*     */   private int exp;
/*     */   private final State state;
/*     */   private final FishHook hookEntity;
/*     */   
/*     */   public PlayerFishEvent(@NotNull Player player, @Nullable Entity entity, @NotNull FishHook hookEntity, @NotNull State state) {
/*  23 */     super(player);
/*  24 */     this.entity = entity;
/*  25 */     this.hookEntity = hookEntity;
/*  26 */     this.state = state;
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
/*     */   @Nullable
/*     */   public Entity getCaught() {
/*  40 */     return this.entity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public FishHook getHook() {
/*  50 */     return this.hookEntity;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/*  55 */     return this.cancel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCancelled(boolean cancel) {
/*  60 */     this.cancel = cancel;
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
/*     */   public int getExpToDrop() {
/*  72 */     return this.exp;
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
/*     */   public void setExpToDrop(int amount) {
/*  84 */     this.exp = amount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public State getState() {
/*  94 */     return this.state;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/* 100 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 105 */     return handlers;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum State
/*     */   {
/* 116 */     FISHING,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 122 */     CAUGHT_FISH,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 128 */     CAUGHT_ENTITY,
/*     */ 
/*     */ 
/*     */     
/* 132 */     IN_GROUND,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 137 */     FAILED_ATTEMPT,
/*     */ 
/*     */ 
/*     */     
/* 141 */     REEL_IN,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 146 */     BITE;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerFishEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */