/*     */ package org.bukkit.event.player;
/*     */ 
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.inventory.EquipmentSlot;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public abstract class PlayerBucketEvent
/*     */   extends PlayerEvent
/*     */   implements Cancellable
/*     */ {
/*     */   private ItemStack itemStack;
/*     */   private boolean cancelled = false;
/*     */   private final Block block;
/*     */   private final Block blockClicked;
/*     */   private final BlockFace blockFace;
/*     */   private final Material bucket;
/*     */   private final EquipmentSlot hand;
/*     */   
/*     */   @Deprecated
/*     */   public PlayerBucketEvent(@NotNull Player who, @NotNull Block blockClicked, @NotNull BlockFace blockFace, @NotNull Material bucket, @NotNull ItemStack itemInHand) {
/*  27 */     this(who, (Block)null, blockClicked.getRelative(blockFace), blockFace, bucket, itemInHand);
/*     */   }
/*     */ 
/*     */   
/*     */   public PlayerBucketEvent(@NotNull Player who, @NotNull Block block, @NotNull Block blockClicked, @NotNull BlockFace blockFace, @NotNull Material bucket, @NotNull ItemStack itemInHand) {
/*  32 */     this(who, block, blockClicked, blockFace, bucket, itemInHand, null);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public PlayerBucketEvent(@NotNull Player who, @NotNull Block blockClicked, @NotNull BlockFace blockFace, @NotNull Material bucket, @NotNull ItemStack itemInHand, @Nullable EquipmentSlot hand) {
/*  37 */     this(who, null, blockClicked, blockFace, bucket, itemInHand, hand);
/*     */   }
/*     */ 
/*     */   
/*     */   public PlayerBucketEvent(@NotNull Player who, @NotNull Block block, @NotNull Block blockClicked, @NotNull BlockFace blockFace, @NotNull Material bucket, @NotNull ItemStack itemInHand, @Nullable EquipmentSlot hand) {
/*  42 */     super(who);
/*  43 */     this.block = block;
/*  44 */     this.blockClicked = blockClicked;
/*  45 */     this.blockFace = blockFace;
/*  46 */     this.itemStack = itemInHand;
/*  47 */     this.bucket = bucket;
/*  48 */     this.hand = (hand == null) ? (this.player.getInventory().getItemInMainHand().equals(itemInHand) ? EquipmentSlot.HAND : EquipmentSlot.OFF_HAND) : hand;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Material getBucket() {
/*  58 */     return this.bucket;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ItemStack getItemStack() {
/*  68 */     return this.itemStack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setItemStack(@Nullable ItemStack itemStack) {
/*  77 */     this.itemStack = itemStack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public final Block getBlock() {
/*  87 */     return this.block;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Block getBlockClicked() {
/*  97 */     return this.blockClicked;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public BlockFace getBlockFace() {
/* 107 */     return this.blockFace;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public EquipmentSlot getHand() {
/* 118 */     return this.hand;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/* 124 */     return this.cancelled;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCancelled(boolean cancel) {
/* 129 */     this.cancelled = cancel;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerBucketEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */