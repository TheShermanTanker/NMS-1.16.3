/*    */ package org.bukkit.event.block;
/*    */ 
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class BlockExpEvent
/*    */   extends BlockEvent
/*    */ {
/* 11 */   private static final HandlerList handlers = new HandlerList();
/*    */   private int exp;
/*    */   
/*    */   public BlockExpEvent(@NotNull Block block, int exp) {
/* 15 */     super(block);
/*    */     
/* 17 */     this.exp = exp;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getExpToDrop() {
/* 26 */     return this.exp;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setExpToDrop(int exp) {
/* 36 */     this.exp = exp;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 42 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 47 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\block\BlockExpEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */