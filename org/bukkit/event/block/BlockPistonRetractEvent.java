/*    */ package org.bukkit.event.block;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class BlockPistonRetractEvent
/*    */   extends BlockPistonEvent
/*    */ {
/* 14 */   private static final HandlerList handlers = new HandlerList();
/*    */   private List<Block> blocks;
/*    */   
/*    */   public BlockPistonRetractEvent(@NotNull Block block, @NotNull List<Block> blocks, @NotNull BlockFace direction) {
/* 18 */     super(block, direction);
/*    */     
/* 20 */     this.blocks = blocks;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   @NotNull
/*    */   public Location getRetractLocation() {
/* 32 */     return getBlock().getRelative(getDirection(), 2).getLocation();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public List<Block> getBlocks() {
/* 43 */     return this.blocks;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 49 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 54 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\block\BlockPistonRetractEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */