/*    */ package org.bukkit.event.server;
/*    */ 
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class PluginEnableEvent
/*    */   extends PluginEvent
/*    */ {
/* 11 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   public PluginEnableEvent(@NotNull Plugin plugin) {
/* 14 */     super(plugin);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 20 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 25 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\server\PluginEnableEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */