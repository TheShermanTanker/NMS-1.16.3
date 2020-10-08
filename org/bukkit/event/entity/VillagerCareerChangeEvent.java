/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Villager;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class VillagerCareerChangeEvent
/*    */   extends EntityEvent implements Cancellable {
/* 11 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancelled;
/*    */   private Villager.Profession profession;
/*    */   private final ChangeReason reason;
/*    */   
/*    */   public VillagerCareerChangeEvent(@NotNull Villager what, @NotNull Villager.Profession profession, @NotNull ChangeReason reason) {
/* 17 */     super((Entity)what);
/* 18 */     this.profession = profession;
/* 19 */     this.reason = reason;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Villager getEntity() {
/* 25 */     return (Villager)super.getEntity();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Villager.Profession getProfession() {
/* 35 */     return this.profession;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setProfession(@NotNull Villager.Profession profession) {
/* 44 */     this.profession = profession;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ChangeReason getReason() {
/* 54 */     return this.reason;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 59 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(@NotNull boolean cancel) {
/* 64 */     this.cancelled = cancel;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public enum ChangeReason
/*    */   {
/* 86 */     LOSING_JOB,
/*    */ 
/*    */ 
/*    */     
/* 90 */     EMPLOYED;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\VillagerCareerChangeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */