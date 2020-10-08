/*    */ package org.bukkit.event.block;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockState;
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
/*    */ public class SpongeAbsorbEvent
/*    */   extends BlockEvent
/*    */   implements Cancellable
/*    */ {
/* 22 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancelled;
/*    */   private final List<BlockState> blocks;
/*    */   
/*    */   public SpongeAbsorbEvent(@NotNull Block block, @NotNull List<BlockState> waterblocks) {
/* 27 */     super(block);
/* 28 */     this.blocks = waterblocks;
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
/*    */   public List<BlockState> getBlocks() {
/* 41 */     return this.blocks;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 46 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 51 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 57 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 62 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\block\SpongeAbsorbEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */