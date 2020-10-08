/*    */ package org.bukkit.event.block;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public abstract class BlockPistonEvent
/*    */   extends BlockEvent
/*    */   implements Cancellable
/*    */ {
/*    */   private boolean cancelled;
/*    */   private final BlockFace direction;
/*    */   
/*    */   public BlockPistonEvent(@NotNull Block block, @NotNull BlockFace direction) {
/* 17 */     super(block);
/* 18 */     this.direction = direction;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 23 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancelled) {
/* 28 */     this.cancelled = cancelled;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isSticky() {
/* 37 */     return (this.block.getType() == Material.STICKY_PISTON || this.block.getType() == Material.MOVING_PISTON);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public BlockFace getDirection() {
/* 50 */     return this.direction;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\block\BlockPistonEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */