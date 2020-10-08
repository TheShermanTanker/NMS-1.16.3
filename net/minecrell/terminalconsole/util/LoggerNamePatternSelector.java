/*     */ package net.minecrell.terminalconsole.util;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.logging.log4j.core.LogEvent;
/*     */ import org.apache.logging.log4j.core.config.Configuration;
/*     */ import org.apache.logging.log4j.core.config.plugins.Plugin;
/*     */ import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
/*     */ import org.apache.logging.log4j.core.config.plugins.PluginConfiguration;
/*     */ import org.apache.logging.log4j.core.config.plugins.PluginElement;
/*     */ import org.apache.logging.log4j.core.config.plugins.PluginFactory;
/*     */ import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
/*     */ import org.apache.logging.log4j.core.layout.PatternLayout;
/*     */ import org.apache.logging.log4j.core.layout.PatternMatch;
/*     */ import org.apache.logging.log4j.core.layout.PatternSelector;
/*     */ import org.apache.logging.log4j.core.pattern.PatternFormatter;
/*     */ import org.apache.logging.log4j.core.pattern.PatternParser;
/*     */ import org.apache.logging.log4j.util.PerformanceSensitive;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Plugin(name = "LoggerNamePatternSelector", category = "Core", elementType = "patternSelector")
/*     */ @PerformanceSensitive({"allocation"})
/*     */ public final class LoggerNamePatternSelector
/*     */   implements PatternSelector
/*     */ {
/*     */   private final PatternFormatter[] defaultFormatters;
/*     */   
/*     */   private static class LoggerNameSelector
/*     */   {
/*     */     private final String name;
/*     */     private final boolean isPackage;
/*     */     private final PatternFormatter[] formatters;
/*     */     
/*     */     LoggerNameSelector(String name, PatternFormatter[] formatters) {
/*  77 */       this.name = name;
/*  78 */       this.isPackage = name.endsWith(".");
/*  79 */       this.formatters = formatters;
/*     */     }
/*     */     
/*     */     PatternFormatter[] get() {
/*  83 */       return this.formatters;
/*     */     }
/*     */     
/*     */     boolean test(String s) {
/*  87 */       return this.isPackage ? s.startsWith(this.name) : s.equals(this.name);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  93 */   private final List<LoggerNameSelector> formatters = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected LoggerNamePatternSelector(String defaultPattern, PatternMatch[] properties, boolean alwaysWriteExceptions, boolean disableAnsi, boolean noConsoleNoAnsi, Configuration config) {
/* 109 */     PatternParser parser = PatternLayout.createPatternParser(config);
/* 110 */     PatternFormatter[] emptyFormatters = new PatternFormatter[0];
/* 111 */     this
/* 112 */       .defaultFormatters = (PatternFormatter[])parser.parse(defaultPattern, alwaysWriteExceptions, disableAnsi, noConsoleNoAnsi).toArray((Object[])emptyFormatters);
/* 113 */     for (PatternMatch property : properties) {
/*     */       
/* 115 */       PatternFormatter[] formatters = (PatternFormatter[])parser.parse(property.getPattern(), alwaysWriteExceptions, disableAnsi, noConsoleNoAnsi).toArray((Object[])emptyFormatters);
/* 116 */       for (String name : property.getKey().split(",")) {
/* 117 */         this.formatters.add(new LoggerNameSelector(name, formatters));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public PatternFormatter[] getFormatters(LogEvent event) {
/* 124 */     String loggerName = event.getLoggerName();
/* 125 */     if (loggerName != null)
/*     */     {
/* 127 */       for (int i = 0; i < this.formatters.size(); i++) {
/* 128 */         LoggerNameSelector selector = this.formatters.get(i);
/* 129 */         if (selector.test(loggerName)) {
/* 130 */           return selector.get();
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 135 */     return this.defaultFormatters;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PluginFactory
/*     */   public static LoggerNamePatternSelector createSelector(@Required(message = "Default pattern is required") @PluginAttribute("defaultPattern") String defaultPattern, @PluginElement("PatternMatch") PatternMatch[] properties, @PluginAttribute(value = "alwaysWriteExceptions", defaultBoolean = true) boolean alwaysWriteExceptions, @PluginAttribute("disableAnsi") boolean disableAnsi, @PluginAttribute("noConsoleNoAnsi") boolean noConsoleNoAnsi, @PluginConfiguration Configuration config) {
/* 159 */     return new LoggerNamePatternSelector(defaultPattern, properties, alwaysWriteExceptions, disableAnsi, noConsoleNoAnsi, config);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecrell\terminalconsol\\util\LoggerNamePatternSelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */