/*    */ package com.destroystokyo.paper.block;
/*    */ 
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TargetBlockInfo
/*    */ {
/*    */   private final Block block;
/*    */   private final BlockFace blockFace;
/*    */   
/*    */   public TargetBlockInfo(@NotNull Block block, @NotNull BlockFace blockFace) {
/* 15 */     this.block = block;
/* 16 */     this.blockFace = blockFace;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Block getBlock() {
/* 26 */     return this.block;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public BlockFace getBlockFace() {
/* 36 */     return this.blockFace;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Block getRelativeBlock() {
/* 46 */     return this.block.getRelative(this.blockFace);
/*    */   }
/*    */   
/*    */   public enum FluidMode {
/* 50 */     NEVER,
/* 51 */     SOURCE_ONLY,
/* 52 */     ALWAYS;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\block\TargetBlockInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */