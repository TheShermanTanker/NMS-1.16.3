/*    */ package org.bukkit.event.block;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockState;
/*    */ import org.bukkit.entity.Item;
/*    */ import org.bukkit.entity.Player;
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
/*    */ public class BlockDropItemEvent
/*    */   extends BlockEvent
/*    */   implements Cancellable
/*    */ {
/* 29 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final Player player;
/*    */   private boolean cancel;
/*    */   private final BlockState blockState;
/*    */   private final List<Item> items;
/*    */   
/*    */   public BlockDropItemEvent(@NotNull Block block, @NotNull BlockState blockState, @NotNull Player player, @NotNull List<Item> items) {
/* 36 */     super(block);
/* 37 */     this.blockState = blockState;
/* 38 */     this.player = player;
/* 39 */     this.items = items;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Player getPlayer() {
/* 49 */     return this.player;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public BlockState getBlockState() {
/* 60 */     return this.blockState;
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
/*    */   public List<Item> getItems() {
/* 73 */     return this.items;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 78 */     return this.cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 83 */     this.cancel = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 89 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 94 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\block\BlockDropItemEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */