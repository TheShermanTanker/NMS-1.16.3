/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.inventory.EquipmentSlot;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class PlayerBucketEmptyEvent
/*    */   extends PlayerBucketEvent
/*    */ {
/* 16 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   @Deprecated
/*    */   public PlayerBucketEmptyEvent(@NotNull Player who, @NotNull Block blockClicked, @NotNull BlockFace blockFace, @NotNull Material bucket, @NotNull ItemStack itemInHand) {
/* 20 */     super(who, blockClicked, blockFace, bucket, itemInHand);
/*    */   }
/*    */   
/*    */   public PlayerBucketEmptyEvent(@NotNull Player who, @NotNull Block block, @NotNull Block blockClicked, @NotNull BlockFace blockFace, @NotNull Material bucket, @NotNull ItemStack itemInHand) {
/* 24 */     super(who, block, blockClicked, blockFace, bucket, itemInHand);
/*    */   }
/*    */   
/*    */   @Deprecated
/*    */   public PlayerBucketEmptyEvent(@NotNull Player who, @NotNull Block blockClicked, @NotNull BlockFace blockFace, @NotNull Material bucket, @NotNull ItemStack itemInHand, @Nullable EquipmentSlot hand) {
/* 29 */     super(who, blockClicked, blockFace, bucket, itemInHand, hand);
/*    */   }
/*    */   
/*    */   public PlayerBucketEmptyEvent(@NotNull Player who, @NotNull Block block, @NotNull Block blockClicked, @NotNull BlockFace blockFace, @NotNull Material bucket, @NotNull ItemStack itemInHand, @Nullable EquipmentSlot hand) {
/* 33 */     super(who, block, blockClicked, blockFace, bucket, itemInHand, hand);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 40 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 45 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerBucketEmptyEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */