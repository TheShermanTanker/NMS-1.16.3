/*    */ package org.bukkit.event.server;
/*    */ 
/*    */ import org.bukkit.plugin.RegisteredServiceProvider;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ServiceEvent
/*    */   extends ServerEvent
/*    */ {
/*    */   private final RegisteredServiceProvider<?> provider;
/*    */   
/*    */   public ServiceEvent(@NotNull RegisteredServiceProvider<?> provider) {
/* 14 */     this.provider = provider;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public RegisteredServiceProvider<?> getProvider() {
/* 19 */     return this.provider;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\server\ServiceEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */