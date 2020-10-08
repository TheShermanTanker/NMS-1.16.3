/*    */ package org.bukkit.event.block;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class BlockExplodeEvent
/*    */   extends BlockEvent
/*    */   implements Cancellable
/*    */ {
/* 13 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancel;
/*    */   private final List<Block> blocks;
/*    */   private float yield;
/*    */   
/*    */   public BlockExplodeEvent(@NotNull Block what, @NotNull List<Block> blocks, float yield) {
/* 19 */     super(what);
/* 20 */     this.blocks = blocks;
/* 21 */     this.yield = yield;
/* 22 */     this.cancel = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 27 */     return this.cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 32 */     this.cancel = cancel;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public List<Block> blockList() {
/* 43 */     return this.blocks;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getYield() {
/* 52 */     return this.yield;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setYield(float yield) {
/* 61 */     this.yield = yield;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 67 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 72 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\block\BlockExplodeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */