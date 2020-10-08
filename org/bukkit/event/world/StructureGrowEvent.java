/*     */ package org.bukkit.event.world;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.TreeType;
/*     */ import org.bukkit.block.BlockState;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ public class StructureGrowEvent
/*     */   extends WorldEvent
/*     */   implements Cancellable
/*     */ {
/*  18 */   private static final HandlerList handlers = new HandlerList();
/*     */   private boolean cancelled = false;
/*     */   private final Location location;
/*     */   private final TreeType species;
/*     */   private final boolean bonemeal;
/*     */   private final Player player;
/*     */   private final List<BlockState> blocks;
/*     */   
/*     */   public StructureGrowEvent(@NotNull Location location, @NotNull TreeType species, boolean bonemeal, @Nullable Player player, @NotNull List<BlockState> blocks) {
/*  27 */     super(location.getWorld());
/*  28 */     this.location = location;
/*  29 */     this.species = species;
/*  30 */     this.bonemeal = bonemeal;
/*  31 */     this.player = player;
/*  32 */     this.blocks = blocks;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Location getLocation() {
/*  42 */     return this.location;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public TreeType getSpecies() {
/*  53 */     return this.species;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFromBonemeal() {
/*  62 */     return this.bonemeal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Player getPlayer() {
/*  73 */     return this.player;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<BlockState> getBlocks() {
/*  83 */     return this.blocks;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/*  88 */     return this.cancelled;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCancelled(boolean cancel) {
/*  93 */     this.cancelled = cancel;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/*  99 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 104 */     return handlers;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\world\StructureGrowEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */