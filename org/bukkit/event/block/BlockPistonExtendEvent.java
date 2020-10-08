/*    */ package org.bukkit.event.block;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class BlockPistonExtendEvent
/*    */   extends BlockPistonEvent
/*    */ {
/* 15 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final int length;
/*    */   private List<Block> blocks;
/*    */   
/*    */   @Deprecated
/*    */   public BlockPistonExtendEvent(@NotNull Block block, int length, @NotNull BlockFace direction) {
/* 21 */     super(block, direction);
/*    */     
/* 23 */     this.length = length;
/*    */   }
/*    */   
/*    */   public BlockPistonExtendEvent(@NotNull Block block, @NotNull List<Block> blocks, @NotNull BlockFace direction) {
/* 27 */     super(block, direction);
/*    */     
/* 29 */     this.length = blocks.size();
/* 30 */     this.blocks = blocks;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public int getLength() {
/* 42 */     return this.length;
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
/* 53 */     if (this.blocks == null) {
/* 54 */       ArrayList<Block> tmp = new ArrayList<>();
/* 55 */       for (int i = 0; i < getLength(); i++) {
/* 56 */         tmp.add(this.block.getRelative(getDirection(), i + 1));
/*    */       }
/* 58 */       this.blocks = Collections.unmodifiableList(tmp);
/*    */     } 
/* 60 */     return this.blocks;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 66 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 71 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\block\BlockPistonExtendEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */