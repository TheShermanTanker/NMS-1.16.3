/*    */ package com.destroystokyo.paper.event.block;
/*    */ 
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.data.BlockData;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.block.BlockEvent;
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
/*    */ public class BlockDestroyEvent
/*    */   extends BlockEvent
/*    */   implements Cancellable
/*    */ {
/* 23 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   @NotNull
/*    */   private final BlockData newState;
/*    */   private final boolean willDrop;
/*    */   private boolean playEffect = true;
/*    */   private boolean cancelled = false;
/*    */   
/*    */   public BlockDestroyEvent(@NotNull Block block, @NotNull BlockData newState, boolean willDrop) {
/* 32 */     super(block);
/* 33 */     this.newState = newState;
/* 34 */     this.willDrop = willDrop;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public BlockData getNewState() {
/* 42 */     return this.newState;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean willDrop() {
/* 49 */     return this.willDrop;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean playEffect() {
/* 56 */     return this.playEffect;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setPlayEffect(boolean playEffect) {
/* 63 */     this.playEffect = playEffect;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 71 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 80 */     this.cancelled = cancel;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 85 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 90 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\block\BlockDestroyEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */