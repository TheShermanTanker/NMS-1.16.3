/*     */ package net.minecrell.terminalconsole;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.apache.logging.log4j.Level;
/*     */ import org.apache.logging.log4j.core.LogEvent;
/*     */ import org.apache.logging.log4j.core.config.Configuration;
/*     */ import org.apache.logging.log4j.core.config.plugins.Plugin;
/*     */ import org.apache.logging.log4j.core.layout.PatternLayout;
/*     */ import org.apache.logging.log4j.core.pattern.ConverterKeys;
/*     */ import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Plugin(name = "highlightError", category = "Converter")
/*     */ @ConverterKeys({"highlightError"})
/*     */ @PerformanceSensitive({"allocation"})
/*     */ public final class HighlightErrorConverter
/*     */   extends LogEventPatternConverter
/*     */ {
/*     */   private static final String ANSI_RESET = "\033[m";
/*     */   private static final String ANSI_ERROR = "\033[31;1m";
/*     */   private static final String ANSI_WARN = "\033[33;1m";
/*     */   private final List<PatternFormatter> formatters;
/*     */   
/*     */   protected HighlightErrorConverter(List<PatternFormatter> formatters) {
/*  74 */     super("highlightError", null);
/*  75 */     this.formatters = formatters;
/*     */   }
/*     */ 
/*     */   
/*     */   public void format(LogEvent event, StringBuilder toAppendTo) {
/*  80 */     if (TerminalConsoleAppender.isAnsiSupported()) {
/*  81 */       Level level = event.getLevel();
/*  82 */       if (level.isMoreSpecificThan(Level.ERROR)) {
/*  83 */         format("\033[31;1m", event, toAppendTo); return;
/*     */       } 
/*  85 */       if (level.isMoreSpecificThan(Level.WARN)) {
/*  86 */         format("\033[33;1m", event, toAppendTo);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     
/*  92 */     for (int i = 0, size = this.formatters.size(); i < size; i++) {
/*  93 */       ((PatternFormatter)this.formatters.get(i)).format(event, toAppendTo);
/*     */     }
/*     */   }
/*     */   
/*     */   private void format(String style, LogEvent event, StringBuilder toAppendTo) {
/*  98 */     int start = toAppendTo.length();
/*  99 */     toAppendTo.append(style);
/* 100 */     int end = toAppendTo.length();
/*     */ 
/*     */     
/* 103 */     for (int i = 0, size = this.formatters.size(); i < size; i++) {
/* 104 */       ((PatternFormatter)this.formatters.get(i)).format(event, toAppendTo);
/*     */     }
/*     */     
/* 107 */     if (toAppendTo.length() == end) {
/*     */       
/* 109 */       toAppendTo.setLength(start);
/*     */     } else {
/*     */       
/* 112 */       toAppendTo.append("\033[m");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean handlesThrowable() {
/* 118 */     for (PatternFormatter formatter : this.formatters) {
/* 119 */       if (formatter.handlesThrowable()) {
/* 120 */         return true;
/*     */       }
/*     */     } 
/* 123 */     return false;
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
/*     */   public static HighlightErrorConverter newInstance(Configuration config, String[] options) {
/* 135 */     if (options.length != 1) {
/* 136 */       LOGGER.error("Incorrect number of options on highlightError. Expected 1 received " + options.length);
/* 137 */       return null;
/*     */     } 
/* 139 */     if (options[0] == null) {
/* 140 */       LOGGER.error("No pattern supplied on highlightError");
/* 141 */       return null;
/*     */     } 
/*     */     
/* 144 */     PatternParser parser = PatternLayout.createPatternParser(config);
/* 145 */     List<PatternFormatter> formatters = parser.parse(options[0]);
/* 146 */     return new HighlightErrorConverter(formatters);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecrell\terminalconsole\HighlightErrorConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */