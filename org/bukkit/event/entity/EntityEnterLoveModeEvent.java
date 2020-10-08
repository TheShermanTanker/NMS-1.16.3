/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Animals;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.HumanEntity;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityEnterLoveModeEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable
/*    */ {
/* 18 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancel;
/*    */   private final HumanEntity humanEntity;
/*    */   private int ticksInLove;
/*    */   
/*    */   public EntityEnterLoveModeEvent(@NotNull Animals animalInLove, @Nullable HumanEntity humanEntity, int ticksInLove) {
/* 24 */     super((Entity)animalInLove);
/* 25 */     this.humanEntity = humanEntity;
/* 26 */     this.ticksInLove = ticksInLove;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Animals getEntity() {
/* 37 */     return (Animals)this.entity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public HumanEntity getHumanEntity() {
/* 48 */     return this.humanEntity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getTicksInLove() {
/* 57 */     return this.ticksInLove;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setTicksInLove(int ticksInLove) {
/* 67 */     this.ticksInLove = ticksInLove;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 72 */     return this.cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 77 */     this.cancel = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 83 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 88 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\EntityEnterLoveModeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */