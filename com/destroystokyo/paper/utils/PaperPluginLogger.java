/*    */ package com.destroystokyo.paper.utils;
/*    */ 
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.LogManager;
/*    */ import java.util.logging.Logger;
/*    */ import org.bukkit.plugin.PluginDescriptionFile;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PaperPluginLogger
/*    */   extends Logger
/*    */ {
/*    */   @NotNull
/*    */   public static Logger getLogger(@NotNull PluginDescriptionFile description) {
/* 17 */     Logger logger = new PaperPluginLogger(description);
/* 18 */     if (!LogManager.getLogManager().addLogger(logger))
/*    */     {
/*    */       
/* 21 */       logger = LogManager.getLogManager().getLogger((description.getPrefix() != null) ? description.getPrefix() : description.getName());
/*    */     }
/*    */     
/* 24 */     return logger;
/*    */   }
/*    */   
/*    */   private PaperPluginLogger(@NotNull PluginDescriptionFile description) {
/* 28 */     super((description.getPrefix() != null) ? description.getPrefix() : description.getName(), null);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setParent(@NotNull Logger parent) {
/* 33 */     if (getParent() != null) {
/* 34 */       warning("Ignoring attempt to change parent of plugin logger");
/*    */     } else {
/* 36 */       log(Level.FINE, "Setting plugin logger parent to {0}", parent);
/* 37 */       super.setParent(parent);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\pape\\utils\PaperPluginLogger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */