/*     */ package org.bukkit.event.player;
/*     */ 
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayerBedEnterEvent
/*     */   extends PlayerEvent
/*     */   implements Cancellable
/*     */ {
/*     */   public enum BedEnterResult
/*     */   {
/*  21 */     OK,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  26 */     NOT_POSSIBLE_HERE,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  35 */     NOT_POSSIBLE_NOW,
/*     */ 
/*     */ 
/*     */     
/*  39 */     TOO_FAR_AWAY,
/*     */ 
/*     */ 
/*     */     
/*  43 */     NOT_SAFE,
/*     */ 
/*     */ 
/*     */     
/*  47 */     OTHER_PROBLEM;
/*     */   }
/*     */   
/*  50 */   private static final HandlerList handlers = new HandlerList();
/*     */   private final Block bed;
/*     */   private final BedEnterResult bedEnterResult;
/*  53 */   private Event.Result useBed = Event.Result.DEFAULT;
/*     */   
/*     */   public PlayerBedEnterEvent(@NotNull Player who, @NotNull Block bed, @NotNull BedEnterResult bedEnterResult) {
/*  56 */     super(who);
/*  57 */     this.bed = bed;
/*  58 */     this.bedEnterResult = bedEnterResult;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public PlayerBedEnterEvent(@NotNull Player who, @NotNull Block bed) {
/*  63 */     this(who, bed, BedEnterResult.OK);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public BedEnterResult getBedEnterResult() {
/*  73 */     return this.bedEnterResult;
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
/*     */   @NotNull
/*     */   public Event.Result useBed() {
/*  87 */     return this.useBed;
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
/*     */ 
/*     */   
/*     */   public void setUseBed(@NotNull Event.Result useBed) {
/* 106 */     this.useBed = useBed;
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
/*     */   
/*     */   public boolean isCancelled() {
/* 124 */     return (this.useBed == Event.Result.DENY || (this.useBed == Event.Result.DEFAULT && this.bedEnterResult != BedEnterResult.OK));
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
/*     */   public void setCancelled(boolean cancel) {
/* 137 */     setUseBed(cancel ? Event.Result.DENY : ((useBed() == Event.Result.DENY) ? Event.Result.DEFAULT : useBed()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Block getBed() {
/* 147 */     return this.bed;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/* 153 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 158 */     return handlers;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerBedEnterEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */