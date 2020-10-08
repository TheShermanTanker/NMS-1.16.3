/*    */ package com.destroystokyo.paper.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Slime;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SlimeTargetLivingEntityEvent
/*    */   extends SlimePathfindEvent
/*    */   implements Cancellable
/*    */ {
/*    */   @NotNull
/*    */   private final LivingEntity target;
/*    */   
/*    */   public SlimeTargetLivingEntityEvent(@NotNull Slime slime, @NotNull LivingEntity target) {
/* 18 */     super(slime);
/* 19 */     this.target = target;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public LivingEntity getTarget() {
/* 29 */     return this.target;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\entity\SlimeTargetLivingEntityEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */