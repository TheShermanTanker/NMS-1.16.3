/*    */ package org.apache.logging.log4j.core.pattern;
/*    */ 
/*    */ import org.apache.logging.log4j.core.LogEvent;
/*    */ import org.apache.logging.log4j.core.config.plugins.Plugin;
/*    */ import org.apache.logging.log4j.util.PerformanceSensitive;
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
/*    */ @Plugin(name = "NdcPatternConverter", category = "Converter")
/*    */ @ConverterKeys({"x", "NDC"})
/*    */ public final class NdcPatternConverter
/*    */   extends LogEventPatternConverter
/*    */ {
/* 33 */   private static final NdcPatternConverter INSTANCE = new NdcPatternConverter();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private NdcPatternConverter() {
/* 40 */     super("NDC", "ndc");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static NdcPatternConverter newInstance(String[] options) {
/* 50 */     return INSTANCE;
/*    */   }
/*    */ 
/*    */   
/*    */   @PerformanceSensitive({"allocation"})
/*    */   public void format(LogEvent event, StringBuilder toAppendTo) {
/* 56 */     toAppendTo.append(event.getContextStack());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\log4j\core\pattern\NdcPatternConverter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */