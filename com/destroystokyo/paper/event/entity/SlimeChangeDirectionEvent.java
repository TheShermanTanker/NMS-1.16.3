/*    */ package com.destroystokyo.paper.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Slime;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SlimeChangeDirectionEvent
/*    */   extends SlimePathfindEvent
/*    */   implements Cancellable
/*    */ {
/*    */   private float yaw;
/*    */   
/*    */   public SlimeChangeDirectionEvent(@NotNull Slime slime, float yaw) {
/* 17 */     super(slime);
/* 18 */     this.yaw = yaw;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getNewYaw() {
/* 27 */     return this.yaw;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setNewYaw(float yaw) {
/* 36 */     this.yaw = yaw;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\entity\SlimeChangeDirectionEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */