/*    */ package org.bukkit.event.block;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockState;
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
/*    */ public class BlockFertilizeEvent
/*    */   extends BlockEvent
/*    */   implements Cancellable
/*    */ {
/* 20 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private boolean cancelled;
/*    */   private final Player player;
/*    */   private final List<BlockState> blocks;
/*    */   
/*    */   public BlockFertilizeEvent(@NotNull Block theBlock, @Nullable Player player, @NotNull List<BlockState> blocks) {
/* 27 */     super(theBlock);
/* 28 */     this.player = player;
/* 29 */     this.blocks = blocks;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Player getPlayer() {
/* 39 */     return this.player;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public List<BlockState> getBlocks() {
/* 49 */     return this.blocks;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 54 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancelled) {
/* 59 */     this.cancelled = cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 65 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 70 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\block\BlockFertilizeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */