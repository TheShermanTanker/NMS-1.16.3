/*    */ package org.bukkit.event.server;
/*    */ 
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.plugin.RegisteredServiceProvider;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ServiceUnregisterEvent
/*    */   extends ServiceEvent
/*    */ {
/* 14 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   public ServiceUnregisterEvent(@NotNull RegisteredServiceProvider<?> serviceProvider) {
/* 17 */     super(serviceProvider);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 23 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 28 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\server\ServiceUnregisterEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */