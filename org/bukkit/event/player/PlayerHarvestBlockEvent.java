/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.inventory.ItemStack;
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
/*    */ public class PlayerHarvestBlockEvent
/*    */   extends PlayerEvent
/*    */   implements Cancellable
/*    */ {
/* 23 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancel = false;
/*    */   private final Block harvestedBlock;
/*    */   private final List<ItemStack> itemsHarvested;
/*    */   
/*    */   public PlayerHarvestBlockEvent(@NotNull Player player, @NotNull Block harvestedBlock, @NotNull List<ItemStack> itemsHarvested) {
/* 29 */     super(player);
/* 30 */     this.harvestedBlock = harvestedBlock;
/* 31 */     this.itemsHarvested = itemsHarvested;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Block getHarvestedBlock() {
/* 41 */     return this.harvestedBlock;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public List<ItemStack> getItemsHarvested() {
/* 51 */     return this.itemsHarvested;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 56 */     return this.cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 61 */     this.cancel = cancel;
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerHarvestBlockEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */