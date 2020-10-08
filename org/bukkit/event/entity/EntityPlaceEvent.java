/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.Warning;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ @Warning(false)
/*    */ public class EntityPlaceEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable
/*    */ {
/* 26 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancelled;
/*    */   private final Player player;
/*    */   private final Block block;
/*    */   private final BlockFace blockFace;
/*    */   
/*    */   public EntityPlaceEvent(@NotNull Entity entity, @Nullable Player player, @NotNull Block block, @NotNull BlockFace blockFace) {
/* 33 */     super(entity);
/* 34 */     this.player = player;
/* 35 */     this.block = block;
/* 36 */     this.blockFace = blockFace;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Player getPlayer() {
/* 46 */     return this.player;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Block getBlock() {
/* 56 */     return this.block;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public BlockFace getBlockFace() {
/* 66 */     return this.blockFace;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 71 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 76 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 82 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 87 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\EntityPlaceEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */