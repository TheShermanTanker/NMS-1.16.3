/*    */ package com.destroystokyo.paper.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.ExperienceOrb;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.entity.EntityEvent;
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
/*    */ public class ExperienceOrbMergeEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable
/*    */ {
/*    */   @NotNull
/*    */   private final ExperienceOrb mergeTarget;
/*    */   @NotNull
/*    */   private final ExperienceOrb mergeSource;
/*    */   
/*    */   @NotNull
/*    */   public ExperienceOrb getMergeTarget() {
/*    */     return this.mergeTarget;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public ExperienceOrb getMergeSource() {
/*    */     return this.mergeSource;
/*    */   }
/*    */   
/*    */   public ExperienceOrbMergeEvent(@NotNull ExperienceOrb mergeTarget, @NotNull ExperienceOrb mergeSource) {
/* 40 */     super((Entity)mergeTarget);
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
/* 73 */     this.cancelled = false;
/*    */     this.mergeTarget = mergeTarget;
/*    */     this.mergeSource = mergeSource;
/*    */   } public boolean isCancelled() {
/* 77 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancelled;
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 85 */     this.cancelled = cancel;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/*    */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/*    */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\entity\ExperienceOrbMergeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */