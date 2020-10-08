/*    */ package org.bukkit.event.server;
/*    */ 
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public abstract class PluginEvent
/*    */   extends ServerEvent
/*    */ {
/*    */   private final Plugin plugin;
/*    */   
/*    */   public PluginEvent(@NotNull Plugin plugin) {
/* 13 */     this.plugin = plugin;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Plugin getPlugin() {
/* 23 */     return this.plugin;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\server\PluginEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */