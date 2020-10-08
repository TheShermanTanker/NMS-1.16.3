/*    */ package org.bukkit.event.block;
/*    */ 
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockFromToEvent
/*    */   extends BlockEvent
/*    */   implements Cancellable
/*    */ {
/* 17 */   private static final HandlerList handlers = new HandlerList();
/*    */   protected Block to;
/*    */   protected BlockFace face;
/*    */   protected boolean cancel;
/*    */   
/*    */   public BlockFromToEvent(@NotNull Block block, @NotNull BlockFace face) {
/* 23 */     super(block);
/* 24 */     this.face = face;
/* 25 */     this.cancel = false;
/*    */   }
/*    */   
/*    */   public BlockFromToEvent(@NotNull Block block, @NotNull Block toBlock) {
/* 29 */     super(block);
/* 30 */     this.to = toBlock;
/* 31 */     this.face = BlockFace.SELF;
/* 32 */     this.cancel = false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public BlockFace getFace() {
/* 42 */     return this.face;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Block getToBlock() {
/* 52 */     if (this.to == null) {
/* 53 */       this.to = this.block.getRelative(this.face);
/*    */     }
/* 55 */     return this.to;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 60 */     return this.cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 65 */     this.cancel = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 71 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 76 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\block\BlockFromToEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */