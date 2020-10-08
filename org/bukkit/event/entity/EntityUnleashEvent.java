/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class EntityUnleashEvent
/*    */   extends EntityEvent
/*    */ {
/* 11 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final UnleashReason reason;
/*    */   
/*    */   public EntityUnleashEvent(@NotNull Entity entity, @NotNull UnleashReason reason) {
/* 15 */     super(entity);
/* 16 */     this.reason = reason;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public UnleashReason getReason() {
/* 26 */     return this.reason;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 32 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 37 */     return handlers;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public enum UnleashReason
/*    */   {
/* 45 */     HOLDER_GONE,
/*    */ 
/*    */ 
/*    */     
/* 49 */     PLAYER_UNLEASH,
/*    */ 
/*    */ 
/*    */     
/* 53 */     DISTANCE,
/* 54 */     UNKNOWN;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\EntityUnleashEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */