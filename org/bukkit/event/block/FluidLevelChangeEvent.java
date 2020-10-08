/*    */ package org.bukkit.event.block;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.data.BlockData;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FluidLevelChangeEvent
/*    */   extends BlockEvent
/*    */   implements Cancellable
/*    */ {
/* 16 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private boolean cancelled;
/*    */   private BlockData newData;
/*    */   
/*    */   public FluidLevelChangeEvent(@NotNull Block theBlock, @NotNull BlockData newData) {
/* 22 */     super(theBlock);
/* 23 */     this.newData = newData;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public BlockData getNewData() {
/* 33 */     return this.newData;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setNewData(@NotNull BlockData newData) {
/* 43 */     Preconditions.checkArgument((newData != null), "newData null");
/* 44 */     Preconditions.checkArgument(this.newData.getMaterial().equals(newData.getMaterial()), "Cannot change fluid type");
/*    */     
/* 46 */     this.newData = newData;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 51 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancelled) {
/* 56 */     this.cancelled = cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 62 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 67 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\block\FluidLevelChangeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */