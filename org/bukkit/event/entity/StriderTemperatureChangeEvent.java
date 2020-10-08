/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Strider;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StriderTemperatureChangeEvent
/*    */   extends EntityEvent
/*    */ {
/* 13 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final boolean shivering;
/*    */   
/*    */   public StriderTemperatureChangeEvent(@NotNull Strider what, boolean shivering) {
/* 17 */     super((Entity)what);
/* 18 */     this.shivering = shivering;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Strider getEntity() {
/* 24 */     return (Strider)this.entity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isShivering() {
/* 33 */     return this.shivering;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 39 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 44 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\StriderTemperatureChangeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */