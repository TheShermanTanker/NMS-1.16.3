/*     */ package org.bukkit.event.block;
/*     */ 
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockState;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.bukkit.inventory.EquipmentSlot;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockPlaceEvent
/*     */   extends BlockEvent
/*     */   implements Cancellable
/*     */ {
/*  18 */   private static final HandlerList handlers = new HandlerList();
/*     */   protected boolean cancel;
/*     */   protected boolean canBuild;
/*     */   protected Block placedAgainst;
/*     */   protected BlockState replacedBlockState;
/*     */   protected ItemStack itemInHand;
/*     */   protected Player player;
/*     */   protected EquipmentSlot hand;
/*     */   
/*     */   @Deprecated
/*     */   public BlockPlaceEvent(@NotNull Block placedBlock, @NotNull BlockState replacedBlockState, @NotNull Block placedAgainst, @NotNull ItemStack itemInHand, @NotNull Player thePlayer, boolean canBuild) {
/*  29 */     this(placedBlock, replacedBlockState, placedAgainst, itemInHand, thePlayer, canBuild, EquipmentSlot.HAND);
/*     */   }
/*     */   
/*     */   public BlockPlaceEvent(@NotNull Block placedBlock, @NotNull BlockState replacedBlockState, @NotNull Block placedAgainst, @NotNull ItemStack itemInHand, @NotNull Player thePlayer, boolean canBuild, @NotNull EquipmentSlot hand) {
/*  33 */     super(placedBlock);
/*  34 */     this.placedAgainst = placedAgainst;
/*  35 */     this.itemInHand = itemInHand;
/*  36 */     this.player = thePlayer;
/*  37 */     this.replacedBlockState = replacedBlockState;
/*  38 */     this.canBuild = canBuild;
/*  39 */     this.hand = hand;
/*  40 */     this.cancel = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/*  45 */     return this.cancel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCancelled(boolean cancel) {
/*  50 */     this.cancel = cancel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Player getPlayer() {
/*  60 */     return this.player;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Block getBlockPlaced() {
/*  71 */     return getBlock();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public BlockState getBlockReplacedState() {
/*  82 */     return this.replacedBlockState;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Block getBlockAgainst() {
/*  92 */     return this.placedAgainst;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack getItemInHand() {
/* 103 */     return this.itemInHand;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public EquipmentSlot getHand() {
/* 112 */     return this.hand;
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
/*     */   public boolean canBuild() {
/* 125 */     return this.canBuild;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBuild(boolean canBuild) {
/* 135 */     this.canBuild = canBuild;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/* 141 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 146 */     return handlers;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\block\BlockPlaceEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */