/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class EntityExplodeEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable
/*    */ {
/* 15 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancel;
/*    */   private final Location location;
/*    */   private final List<Block> blocks;
/*    */   private float yield;
/*    */   
/*    */   public EntityExplodeEvent(@NotNull Entity what, @NotNull Location location, @NotNull List<Block> blocks, float yield) {
/* 22 */     super(what);
/* 23 */     this.location = location;
/* 24 */     this.blocks = blocks;
/* 25 */     this.yield = yield;
/* 26 */     this.cancel = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 31 */     return this.cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 36 */     this.cancel = cancel;
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
/* 47 */     return this.blocks;
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
/*    */   public Location getLocation() {
/* 60 */     return this.location;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getYield() {
/* 69 */     return this.yield;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setYield(float yield) {
/* 78 */     this.yield = yield;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 84 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 89 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\EntityExplodeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */