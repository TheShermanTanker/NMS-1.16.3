/*     */ package org.bukkit.event.block;
/*     */ 
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.data.BlockData;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.HandlerList;
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
/*     */ public class BlockCanBuildEvent
/*     */   extends BlockEvent
/*     */ {
/*  23 */   private static final HandlerList handlers = new HandlerList();
/*     */   
/*     */   protected boolean buildable;
/*     */   protected BlockData blockData;
/*     */   private final Player player;
/*     */   
/*     */   @Deprecated
/*     */   public BlockCanBuildEvent(@NotNull Block block, @NotNull BlockData type, boolean canBuild) {
/*  31 */     this(block, null, type, canBuild);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockCanBuildEvent(@NotNull Block block, @Nullable Player player, @NotNull BlockData type, boolean canBuild) {
/*  41 */     super(block);
/*  42 */     this.player = player;
/*  43 */     this.buildable = canBuild;
/*  44 */     this.blockData = type;
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
/*     */   public boolean isBuildable() {
/*  56 */     return this.buildable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBuildable(boolean cancel) {
/*  66 */     this.buildable = cancel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Material getMaterial() {
/*  76 */     return this.blockData.getMaterial();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public BlockData getBlockData() {
/*  86 */     return this.blockData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Player getPlayer() {
/*  98 */     return this.player;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/* 104 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 109 */     return handlers;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\block\BlockCanBuildEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */