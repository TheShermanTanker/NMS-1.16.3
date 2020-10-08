/*    */ package org.bukkit.event.hanging;
/*    */ 
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.entity.Hanging;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class HangingPlaceEvent
/*    */   extends HangingEvent
/*    */   implements Cancellable
/*    */ {
/* 16 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancelled;
/*    */   private final Player player;
/*    */   private final Block block;
/*    */   private final BlockFace blockFace;
/*    */   
/*    */   public HangingPlaceEvent(@NotNull Hanging hanging, @Nullable Player player, @NotNull Block block, @NotNull BlockFace blockFace) {
/* 23 */     super(hanging);
/* 24 */     this.player = player;
/* 25 */     this.block = block;
/* 26 */     this.blockFace = blockFace;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Player getPlayer() {
/* 36 */     return this.player;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Block getBlock() {
/* 46 */     return this.block;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public BlockFace getBlockFace() {
/* 56 */     return this.blockFace;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 61 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 66 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 72 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 77 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\hanging\HangingPlaceEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */