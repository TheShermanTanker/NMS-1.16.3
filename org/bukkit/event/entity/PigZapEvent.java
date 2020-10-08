/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import com.destroystokyo.paper.event.entity.EntityZapEvent;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LightningStrike;
/*    */ import org.bukkit.entity.Pig;
/*    */ import org.bukkit.entity.PigZombie;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PigZapEvent
/*    */   extends EntityZapEvent
/*    */   implements Cancellable
/*    */ {
/*    */   private boolean canceled;
/*    */   private final PigZombie pigzombie;
/*    */   private final LightningStrike bolt;
/*    */   
/*    */   public PigZapEvent(@NotNull Pig pig, @NotNull LightningStrike bolt, @NotNull PigZombie pigzombie) {
/* 23 */     super((Entity)pig, bolt, (Entity)pigzombie);
/* 24 */     this.bolt = bolt;
/* 25 */     this.pigzombie = pigzombie;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 30 */     return this.canceled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 35 */     this.canceled = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Pig getEntity() {
/* 41 */     return (Pig)this.entity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public LightningStrike getLightning() {
/* 51 */     return this.bolt;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   @NotNull
/*    */   public PigZombie getPigZombie() {
/* 64 */     return this.pigzombie;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\PigZapEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */