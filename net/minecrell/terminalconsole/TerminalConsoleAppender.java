/*     */ package net.minecrell.terminalconsole;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.Serializable;
/*     */ import org.apache.logging.log4j.core.Filter;
/*     */ import org.apache.logging.log4j.core.Layout;
/*     */ import org.apache.logging.log4j.core.LogEvent;
/*     */ import org.apache.logging.log4j.core.appender.AbstractAppender;
/*     */ import org.apache.logging.log4j.core.config.plugins.Plugin;
/*     */ import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
/*     */ import org.apache.logging.log4j.core.config.plugins.PluginElement;
/*     */ import org.apache.logging.log4j.core.config.plugins.PluginFactory;
/*     */ import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
/*     */ import org.apache.logging.log4j.core.layout.PatternLayout;
/*     */ import org.apache.logging.log4j.util.PropertiesUtil;
/*     */ import org.jline.reader.LineReader;
/*     */ import org.jline.terminal.Terminal;
/*     */ import org.jline.terminal.TerminalBuilder;
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
/*     */ @Plugin(name = "TerminalConsole", category = "Core", elementType = "appender", printObject = true)
/*     */ public final class TerminalConsoleAppender
/*     */   extends AbstractAppender
/*     */ {
/*     */   public static final String PLUGIN_NAME = "TerminalConsole";
/*     */   static final String PROPERTY_PREFIX = "terminal";
/*     */   public static final String JLINE_OVERRIDE_PROPERTY = "terminal.jline";
/*     */   public static final String ANSI_OVERRIDE_PROPERTY = "terminal.ansi";
/* 124 */   private static final Boolean ANSI_OVERRIDE = getOptionalBooleanProperty("terminal.ansi");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 131 */   private static final PrintStream stdout = System.out;
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean initialized;
/*     */ 
/*     */ 
/*     */   
/*     */   private static Terminal terminal;
/*     */ 
/*     */   
/*     */   private static LineReader reader;
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized Terminal getTerminal() {
/* 147 */     return terminal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized LineReader getReader() {
/* 158 */     return reader;
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
/*     */   public static synchronized void setReader(LineReader newReader) {
/* 172 */     if (newReader != null && newReader.getTerminal() != terminal) {
/* 173 */       throw new IllegalArgumentException("Reader was not created with TerminalConsoleAppender.getTerminal()");
/*     */     }
/*     */     
/* 176 */     reader = newReader;
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
/*     */   public static boolean isAnsiSupported() {
/* 190 */     return (ANSI_OVERRIDE != null) ? ANSI_OVERRIDE.booleanValue() : ((terminal != null));
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
/*     */   protected TerminalConsoleAppender(String name, Filter filter, Layout<? extends Serializable> layout, boolean ignoreExceptions) {
/* 204 */     super(name, filter, layout, ignoreExceptions);
/* 205 */     initializeTerminal();
/*     */   }
/*     */   
/*     */   private static synchronized void initializeTerminal() {
/* 209 */     if (!initialized) {
/* 210 */       initialized = true;
/*     */ 
/*     */       
/* 213 */       Boolean jlineOverride = getOptionalBooleanProperty("terminal.jline");
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
/* 227 */       boolean dumb = (jlineOverride == Boolean.TRUE || System.getProperty("java.class.path").contains("idea_rt.jar"));
/*     */       
/* 229 */       if (jlineOverride != Boolean.FALSE) {
/*     */         try {
/* 231 */           terminal = TerminalBuilder.builder().dumb(dumb).build();
/* 232 */         } catch (IllegalStateException e) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 238 */           if (LOGGER.isDebugEnabled()) {
/*     */             
/* 240 */             LOGGER.warn("Advanced terminal features are not available in this environment", e);
/*     */           } else {
/* 242 */             LOGGER.warn("Advanced terminal features are not available in this environment");
/*     */           } 
/* 244 */         } catch (IOException e) {
/* 245 */           LOGGER.error("Failed to initialize terminal. Falling back to standard console", e);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void append(LogEvent event) {
/* 253 */     print(getLayout().toSerializable(event).toString());
/*     */   }
/*     */   
/*     */   private static synchronized void print(String text) {
/* 257 */     if (terminal != null) {
/* 258 */       if (reader != null) {
/*     */         
/* 260 */         reader.printAbove(text);
/*     */       } else {
/* 262 */         terminal.writer().print(text);
/* 263 */         terminal.writer().flush();
/*     */       } 
/*     */     } else {
/* 266 */       stdout.print(text);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized void close() throws IOException {
/* 277 */     if (initialized) {
/* 278 */       initialized = false;
/* 279 */       reader = null;
/* 280 */       if (terminal != null) {
/*     */         try {
/* 282 */           terminal.close();
/*     */         } finally {
/* 284 */           terminal = null;
/*     */         } 
/*     */       }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PluginFactory
/*     */   public static TerminalConsoleAppender createAppender(@Required(message = "No name provided for TerminalConsoleAppender") @PluginAttribute("name") String name, @PluginElement("Filter") Filter filter, @PluginElement("Layout") Layout<? extends Serializable> layout, @PluginAttribute(value = "ignoreExceptions", defaultBoolean = true) boolean ignoreExceptions) {
/*     */     PatternLayout patternLayout;
/* 308 */     if (layout == null) {
/* 309 */       patternLayout = PatternLayout.createDefaultLayout();
/*     */     }
/*     */     
/* 312 */     return new TerminalConsoleAppender(name, filter, (Layout<? extends Serializable>)patternLayout, ignoreExceptions);
/*     */   }
/*     */   
/*     */   private static Boolean getOptionalBooleanProperty(String name) {
/* 316 */     String value = PropertiesUtil.getProperties().getStringProperty(name);
/* 317 */     if (value == null) {
/* 318 */       return null;
/*     */     }
/*     */     
/* 321 */     if (value.equalsIgnoreCase("true"))
/* 322 */       return Boolean.TRUE; 
/* 323 */     if (value.equalsIgnoreCase("false")) {
/* 324 */       return Boolean.FALSE;
/*     */     }
/* 326 */     LOGGER.warn("Invalid value for boolean input property '{}': {}", name, value);
/* 327 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecrell\terminalconsole\TerminalConsoleAppender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */