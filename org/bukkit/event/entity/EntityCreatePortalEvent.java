/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.bukkit.PortalType;
/*    */ import org.bukkit.block.BlockState;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class EntityCreatePortalEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable
/*    */ {
/* 19 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final List<BlockState> blocks;
/*    */   private boolean cancelled = false;
/* 22 */   private PortalType type = PortalType.CUSTOM;
/*    */   
/*    */   public EntityCreatePortalEvent(@NotNull LivingEntity what, @NotNull List<BlockState> blocks, @NotNull PortalType type) {
/* 25 */     super((Entity)what);
/*    */     
/* 27 */     this.blocks = blocks;
/* 28 */     this.type = type;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public LivingEntity getEntity() {
/* 34 */     return (LivingEntity)this.entity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public List<BlockState> getBlocks() {
/* 44 */     return this.blocks;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 49 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 54 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public PortalType getPortalType() {
/* 64 */     return this.type;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 70 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 75 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\EntityCreatePortalEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */