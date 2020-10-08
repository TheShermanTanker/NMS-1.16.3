/*    */ package org.bukkit.event.block;
/*    */ 
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockBreakEvent
/*    */   extends BlockExpEvent
/*    */   implements Cancellable
/*    */ {
/*    */   private final Player player;
/*    */   private boolean dropItems;
/*    */   private boolean cancel;
/*    */   
/*    */   public BlockBreakEvent(@NotNull Block theBlock, @NotNull Player player) {
/* 35 */     super(theBlock, 0);
/*    */     
/* 37 */     this.player = player;
/* 38 */     this.dropItems = true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Player getPlayer() {
/* 48 */     return this.player;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setDropItems(boolean dropItems) {
/* 57 */     this.dropItems = dropItems;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isDropItems() {
/* 66 */     return this.dropItems;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 71 */     return this.cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 76 */     this.cancel = cancel;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\block\BlockBreakEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */