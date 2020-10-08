/*    */ package org.bukkit.plugin;
/*    */ 
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.LogRecord;
/*    */ import java.util.logging.Logger;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PluginLogger
/*    */   extends Logger
/*    */ {
/*    */   private String pluginName;
/*    */   
/*    */   public PluginLogger(@NotNull Plugin context) {
/* 24 */     super(context.getClass().getCanonicalName(), null);
/* 25 */     String prefix = context.getDescription().getPrefix();
/* 26 */     this.pluginName = (prefix != null) ? ("[" + prefix + "] ") : ("[" + context.getDescription().getName() + "] ");
/* 27 */     setParent(context.getServer().getLogger());
/* 28 */     setLevel(Level.ALL);
/*    */   }
/*    */ 
/*    */   
/*    */   public void log(@NotNull LogRecord logRecord) {
/* 33 */     logRecord.setMessage(this.pluginName + logRecord.getMessage());
/* 34 */     super.log(logRecord);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\plugin\PluginLogger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */