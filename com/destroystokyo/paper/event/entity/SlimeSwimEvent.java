/*    */ package com.destroystokyo.paper.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Slime;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SlimeSwimEvent
/*    */   extends SlimeWanderEvent
/*    */   implements Cancellable
/*    */ {
/*    */   public SlimeSwimEvent(@NotNull Slime slime) {
/* 15 */     super(slime);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\entity\SlimeSwimEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */