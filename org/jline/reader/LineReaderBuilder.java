/*     */ package org.jline.reader;
/*     */ 
/*     */ import java.io.IOError;
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import org.jline.reader.impl.LineReaderImpl;
/*     */ import org.jline.reader.impl.history.DefaultHistory;
/*     */ import org.jline.terminal.Terminal;
/*     */ import org.jline.terminal.TerminalBuilder;
/*     */ import org.jline.utils.Log;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class LineReaderBuilder
/*     */ {
/*     */   Terminal terminal;
/*     */   String appName;
/*     */   
/*     */   public static LineReaderBuilder builder() {
/*  26 */     return new LineReaderBuilder();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  31 */   Map<String, Object> variables = new HashMap<>();
/*  32 */   Map<LineReader.Option, Boolean> options = new HashMap<>();
/*     */   
/*     */   History history;
/*     */   
/*     */   Completer completer;
/*     */   
/*     */   History memoryHistory;
/*     */   Highlighter highlighter;
/*     */   Parser parser;
/*     */   Expander expander;
/*     */   
/*     */   public LineReaderBuilder terminal(Terminal terminal) {
/*  44 */     this.terminal = terminal;
/*  45 */     return this;
/*     */   }
/*     */   
/*     */   public LineReaderBuilder appName(String appName) {
/*  49 */     this.appName = appName;
/*  50 */     return this;
/*     */   }
/*     */   
/*     */   public LineReaderBuilder variables(Map<String, Object> variables) {
/*  54 */     Map<String, Object> old = this.variables;
/*  55 */     this.variables = Objects.<Map<String, Object>>requireNonNull(variables);
/*  56 */     this.variables.putAll(old);
/*  57 */     return this;
/*     */   }
/*     */   
/*     */   public LineReaderBuilder variable(String name, Object value) {
/*  61 */     this.variables.put(name, value);
/*  62 */     return this;
/*     */   }
/*     */   
/*     */   public LineReaderBuilder option(LineReader.Option option, boolean value) {
/*  66 */     this.options.put(option, Boolean.valueOf(value));
/*  67 */     return this;
/*     */   }
/*     */   
/*     */   public LineReaderBuilder history(History history) {
/*  71 */     this.history = history;
/*  72 */     return this;
/*     */   }
/*     */   
/*     */   public LineReaderBuilder completer(Completer completer) {
/*  76 */     this.completer = completer;
/*  77 */     return this;
/*     */   }
/*     */   
/*     */   public LineReaderBuilder highlighter(Highlighter highlighter) {
/*  81 */     this.highlighter = highlighter;
/*  82 */     return this;
/*     */   }
/*     */   
/*     */   public LineReaderBuilder parser(Parser parser) {
/*  86 */     if (parser != null) {
/*     */       try {
/*  88 */         if (!Boolean.getBoolean("org.jline.reader.support.parsedline") && 
/*  89 */           !(parser.parse("", 0) instanceof CompletingParsedLine)) {
/*  90 */           Log.warn(new Object[] { "The Parser of class " + parser.getClass().getName() + " does not support the CompletingParsedLine interface. Completion with escaped or quoted words won't work correctly." });
/*     */         }
/*     */       }
/*  93 */       catch (Throwable throwable) {}
/*     */     }
/*     */ 
/*     */     
/*  97 */     this.parser = parser;
/*  98 */     return this;
/*     */   }
/*     */   
/*     */   public LineReaderBuilder expander(Expander expander) {
/* 102 */     this.expander = expander;
/* 103 */     return this;
/*     */   }
/*     */   
/*     */   public LineReader build() {
/* 107 */     Terminal terminal = this.terminal;
/* 108 */     if (terminal == null) {
/*     */       try {
/* 110 */         terminal = TerminalBuilder.terminal();
/* 111 */       } catch (IOException e) {
/* 112 */         throw new IOError(e);
/*     */       } 
/*     */     }
/* 115 */     LineReaderImpl reader = new LineReaderImpl(terminal, this.appName, this.variables);
/* 116 */     if (this.history != null) {
/* 117 */       reader.setHistory(this.history);
/*     */     } else {
/* 119 */       if (this.memoryHistory == null) {
/* 120 */         this.memoryHistory = (History)new DefaultHistory();
/*     */       }
/* 122 */       reader.setHistory(this.memoryHistory);
/*     */     } 
/* 124 */     if (this.completer != null) {
/* 125 */       reader.setCompleter(this.completer);
/*     */     }
/* 127 */     if (this.highlighter != null) {
/* 128 */       reader.setHighlighter(this.highlighter);
/*     */     }
/* 130 */     if (this.parser != null) {
/* 131 */       reader.setParser(this.parser);
/*     */     }
/* 133 */     if (this.expander != null) {
/* 134 */       reader.setExpander(this.expander);
/*     */     }
/* 136 */     for (Map.Entry<LineReader.Option, Boolean> e : this.options.entrySet()) {
/* 137 */       reader.option(e.getKey(), ((Boolean)e.getValue()).booleanValue());
/*     */     }
/* 139 */     return (LineReader)reader;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\reader\LineReaderBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */