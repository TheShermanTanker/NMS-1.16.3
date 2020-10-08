/*    */ package org.bukkit.event.block;
/*    */ 
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockBurnEvent
/*    */   extends BlockEvent
/*    */   implements Cancellable
/*    */ {
/* 16 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancelled;
/*    */   private final Block ignitingBlock;
/*    */   
/*    */   @Deprecated
/*    */   public BlockBurnEvent(@NotNull Block block) {
/* 22 */     this(block, null);
/*    */   }
/*    */   
/*    */   public BlockBurnEvent(@NotNull Block block, @Nullable Block ignitingBlock) {
/* 26 */     super(block);
/* 27 */     this.ignitingBlock = ignitingBlock;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Block getIgnitingBlock() {
/* 38 */     return this.ignitingBlock;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 43 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 48 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 54 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 59 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\block\BlockBurnEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */