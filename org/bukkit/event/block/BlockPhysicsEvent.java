/*    */ package org.bukkit.event.block;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.data.BlockData;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockPhysicsEvent
/*    */   extends BlockEvent
/*    */   implements Cancellable
/*    */ {
/* 30 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private final BlockData changed;
/*    */   private final Block sourceBlock;
/*    */   private boolean cancel = false;
/*    */   
/*    */   @Deprecated
/*    */   public BlockPhysicsEvent(Block block, BlockData changed, int sourceX, int sourceY, int sourceZ) {
/* 38 */     this(block, changed, block.getWorld().getBlockAt(sourceX, sourceY, sourceZ));
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPhysicsEvent(@NotNull Block block, @NotNull BlockData changed) {
/* 43 */     this(block, changed, block);
/*    */   }
/*    */   
/*    */   public BlockPhysicsEvent(@NotNull Block block, @NotNull BlockData changed, @NotNull Block sourceBlock) {
/* 47 */     super(block);
/* 48 */     this.changed = changed;
/* 49 */     this.sourceBlock = sourceBlock;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Block getSourceBlock() {
/* 61 */     return this.sourceBlock;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Material getChangedType() {
/* 71 */     return this.changed.getMaterial();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 76 */     return this.cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 81 */     this.cancel = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 87 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 92 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\block\BlockPhysicsEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */