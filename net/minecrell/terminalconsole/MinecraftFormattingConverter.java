/*     */ package net.minecrell.terminalconsole;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.apache.logging.log4j.core.LogEvent;
/*     */ import org.apache.logging.log4j.core.config.Configuration;
/*     */ import org.apache.logging.log4j.core.config.plugins.Plugin;
/*     */ import org.apache.logging.log4j.core.layout.PatternLayout;
/*     */ import org.apache.logging.log4j.core.pattern.ConverterKeys;
/*     */ import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;
/*     */ import org.apache.logging.log4j.core.pattern.PatternFormatter;
/*     */ import org.apache.logging.log4j.core.pattern.PatternParser;
/*     */ import org.apache.logging.log4j.util.PerformanceSensitive;
/*     */ import org.apache.logging.log4j.util.PropertiesUtil;
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
/*     */ @Plugin(name = "minecraftFormatting", category = "Converter")
/*     */ @ConverterKeys({"minecraftFormatting"})
/*     */ @PerformanceSensitive({"allocation"})
/*     */ public final class MinecraftFormattingConverter
/*     */   extends LogEventPatternConverter
/*     */ {
/*     */   public static final String KEEP_FORMATTING_PROPERTY = "terminal.keepMinecraftFormatting";
/*  81 */   private static final boolean KEEP_FORMATTING = PropertiesUtil.getProperties().getBooleanProperty("terminal.keepMinecraftFormatting");
/*     */   
/*     */   static final String ANSI_RESET = "\033[m";
/*     */   
/*     */   private static final char COLOR_CHAR = '§';
/*     */   
/*     */   private static final String LOOKUP = "0123456789abcdefklmnor";
/*  88 */   private static final String[] ansiCodes = new String[] { "\033[0;30m", "\033[0;34m", "\033[0;32m", "\033[0;36m", "\033[0;31m", "\033[0;35m", "\033[0;33m", "\033[0;37m", "\033[0;30;1m", "\033[0;34;1m", "\033[0;32;1m", "\033[0;36;1m", "\033[0;31;1m", "\033[0;35;1m", "\033[0;33;1m", "\033[0;37;1m", "\033[5m", "\033[21m", "\033[9m", "\033[4m", "\033[3m", "\033[m" };
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
/*     */   private final boolean ansi;
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
/*     */   private final List<PatternFormatter> formatters;
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
/*     */   protected MinecraftFormattingConverter(List<PatternFormatter> formatters, boolean strip) {
/* 123 */     super("minecraftFormatting", null);
/* 124 */     this.formatters = formatters;
/* 125 */     this.ansi = !strip;
/*     */   }
/*     */ 
/*     */   
/*     */   public void format(LogEvent event, StringBuilder toAppendTo) {
/* 130 */     int start = toAppendTo.length();
/*     */     
/* 132 */     for (int i = 0, size = this.formatters.size(); i < size; i++) {
/* 133 */       ((PatternFormatter)this.formatters.get(i)).format(event, toAppendTo);
/*     */     }
/*     */     
/* 136 */     if (KEEP_FORMATTING || toAppendTo.length() == start) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 141 */     String content = toAppendTo.substring(start);
/* 142 */     format(content, toAppendTo, start, (this.ansi && TerminalConsoleAppender.isAnsiSupported()));
/*     */   }
/*     */   
/*     */   static void format(String s, StringBuilder result, int start, boolean ansi) {
/* 146 */     int next = s.indexOf('§');
/* 147 */     int last = s.length() - 1;
/* 148 */     if (next == -1 || next == last) {
/*     */       return;
/*     */     }
/*     */     
/* 152 */     result.setLength(start + next);
/*     */     
/* 154 */     int pos = next;
/*     */     do {
/* 156 */       int format = "0123456789abcdefklmnor".indexOf(Character.toLowerCase(s.charAt(next + 1)));
/* 157 */       if (format != -1) {
/* 158 */         if (pos != next) {
/* 159 */           result.append(s, pos, next);
/*     */         }
/* 161 */         if (ansi) {
/* 162 */           result.append(ansiCodes[format]);
/*     */         }
/* 164 */         pos = next += 2;
/*     */       } else {
/* 166 */         next++;
/*     */       } 
/*     */       
/* 169 */       next = s.indexOf('§', next);
/* 170 */     } while (next != -1 && next < last);
/*     */     
/* 172 */     result.append(s, pos, s.length());
/* 173 */     if (ansi) {
/* 174 */       result.append("\033[m");
/*     */     }
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
/*     */   public static MinecraftFormattingConverter newInstance(Configuration config, String[] options) {
/* 189 */     if (options.length < 1 || options.length > 2) {
/* 190 */       LOGGER.error("Incorrect number of options on minecraftFormatting. Expected at least 1, max 2 received " + options.length);
/* 191 */       return null;
/*     */     } 
/* 193 */     if (options[0] == null) {
/* 194 */       LOGGER.error("No pattern supplied on minecraftFormatting");
/* 195 */       return null;
/*     */     } 
/*     */     
/* 198 */     PatternParser parser = PatternLayout.createPatternParser(config);
/* 199 */     List<PatternFormatter> formatters = parser.parse(options[0]);
/* 200 */     boolean strip = (options.length > 1 && "strip".equals(options[1]));
/* 201 */     return new MinecraftFormattingConverter(formatters, strip);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecrell\terminalconsole\MinecraftFormattingConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */