/*     */ package org.jline.terminal;
/*     */ 
/*     */ import java.io.FileDescriptor;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.lang.reflect.Method;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.UnsupportedCharsetException;
/*     */ import java.util.Optional;
/*     */ import java.util.ServiceLoader;
/*     */ import org.jline.terminal.impl.AbstractPosixTerminal;
/*     */ import org.jline.terminal.impl.DumbTerminal;
/*     */ import org.jline.terminal.impl.ExecPty;
/*     */ import org.jline.terminal.impl.ExternalTerminal;
/*     */ import org.jline.terminal.impl.PosixPtyTerminal;
/*     */ import org.jline.terminal.impl.PosixSysTerminal;
/*     */ import org.jline.terminal.spi.JansiSupport;
/*     */ import org.jline.terminal.spi.JnaSupport;
/*     */ import org.jline.terminal.spi.Pty;
/*     */ import org.jline.utils.Log;
/*     */ import org.jline.utils.OSUtils;
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
/*     */ public final class TerminalBuilder
/*     */ {
/*     */   public static final String PROP_ENCODING = "org.jline.terminal.encoding";
/*     */   public static final String PROP_CODEPAGE = "org.jline.terminal.codepage";
/*     */   public static final String PROP_TYPE = "org.jline.terminal.type";
/*     */   public static final String PROP_JNA = "org.jline.terminal.jna";
/*     */   public static final String PROP_JANSI = "org.jline.terminal.jansi";
/*     */   public static final String PROP_EXEC = "org.jline.terminal.exec";
/*     */   public static final String PROP_DUMB = "org.jline.terminal.dumb";
/*     */   public static final String PROP_DUMB_COLOR = "org.jline.terminal.dumb.color";
/*     */   public static final String PROP_NON_BLOCKING_READS = "org.jline.terminal.pty.nonBlockingReads";
/*     */   public static final String PROP_COLOR_DISTANCE = "org.jline.utils.colorDistance";
/*     */   public static final String PROP_DISABLE_ALTERNATE_CHARSET = "org.jline.utils.disableAlternateCharset";
/*     */   private String name;
/*     */   private InputStream in;
/*     */   private OutputStream out;
/*     */   private String type;
/*     */   private Charset encoding;
/*     */   private int codepage;
/*     */   private Boolean system;
/*     */   private Boolean jna;
/*     */   private Boolean jansi;
/*     */   private Boolean exec;
/*     */   private Boolean dumb;
/*     */   private Attributes attributes;
/*     */   private Size size;
/*     */   
/*     */   public static Terminal terminal() throws IOException {
/*  75 */     return builder().build();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static TerminalBuilder builder() {
/*  84 */     return new TerminalBuilder();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean nativeSignals = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 101 */   private Terminal.SignalHandler signalHandler = Terminal.SignalHandler.SIG_DFL;
/*     */ 
/*     */   
/*     */   private boolean paused = false;
/*     */ 
/*     */   
/*     */   public TerminalBuilder name(String name) {
/* 108 */     this.name = name;
/* 109 */     return this;
/*     */   }
/*     */   
/*     */   public TerminalBuilder streams(InputStream in, OutputStream out) {
/* 113 */     this.in = in;
/* 114 */     this.out = out;
/* 115 */     return this;
/*     */   }
/*     */   
/*     */   public TerminalBuilder system(boolean system) {
/* 119 */     this.system = Boolean.valueOf(system);
/* 120 */     return this;
/*     */   }
/*     */   
/*     */   public TerminalBuilder jna(boolean jna) {
/* 124 */     this.jna = Boolean.valueOf(jna);
/* 125 */     return this;
/*     */   }
/*     */   
/*     */   public TerminalBuilder jansi(boolean jansi) {
/* 129 */     this.jansi = Boolean.valueOf(jansi);
/* 130 */     return this;
/*     */   }
/*     */   
/*     */   public TerminalBuilder exec(boolean exec) {
/* 134 */     this.exec = Boolean.valueOf(exec);
/* 135 */     return this;
/*     */   }
/*     */   
/*     */   public TerminalBuilder dumb(boolean dumb) {
/* 139 */     this.dumb = Boolean.valueOf(dumb);
/* 140 */     return this;
/*     */   }
/*     */   
/*     */   public TerminalBuilder type(String type) {
/* 144 */     this.type = type;
/* 145 */     return this;
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
/*     */   public TerminalBuilder encoding(String encoding) throws UnsupportedCharsetException {
/* 164 */     return encoding((encoding != null) ? Charset.forName(encoding) : null);
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
/*     */   public TerminalBuilder encoding(Charset encoding) {
/* 182 */     this.encoding = encoding;
/* 183 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public TerminalBuilder codepage(int codepage) {
/* 195 */     this.codepage = codepage;
/* 196 */     return this;
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
/*     */   public TerminalBuilder attributes(Attributes attributes) {
/* 212 */     this.attributes = attributes;
/* 213 */     return this;
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
/*     */   public TerminalBuilder size(Size size) {
/* 229 */     this.size = size;
/* 230 */     return this;
/*     */   }
/*     */   
/*     */   public TerminalBuilder nativeSignals(boolean nativeSignals) {
/* 234 */     this.nativeSignals = nativeSignals;
/* 235 */     return this;
/*     */   }
/*     */   
/*     */   public TerminalBuilder signalHandler(Terminal.SignalHandler signalHandler) {
/* 239 */     this.signalHandler = signalHandler;
/* 240 */     return this;
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
/*     */   public TerminalBuilder paused(boolean paused) {
/* 254 */     this.paused = paused;
/* 255 */     return this;
/*     */   }
/*     */   
/*     */   public Terminal build() throws IOException {
/* 259 */     Terminal terminal = doBuild();
/* 260 */     Log.debug(() -> "Using terminal " + terminal.getClass().getSimpleName());
/* 261 */     if (terminal instanceof AbstractPosixTerminal) {
/* 262 */       Log.debug(() -> "Using pty " + ((AbstractPosixTerminal)terminal).getPty().getClass().getSimpleName());
/*     */     }
/* 264 */     return terminal;
/*     */   }
/*     */   
/*     */   private Terminal doBuild() throws IOException {
/* 268 */     String name = this.name;
/* 269 */     if (name == null) {
/* 270 */       name = "JLine terminal";
/*     */     }
/* 272 */     Charset encoding = this.encoding;
/* 273 */     if (encoding == null) {
/* 274 */       String charsetName = System.getProperty("org.jline.terminal.encoding");
/* 275 */       if (charsetName != null && Charset.isSupported(charsetName)) {
/* 276 */         encoding = Charset.forName(charsetName);
/*     */       }
/*     */     } 
/* 279 */     int codepage = this.codepage;
/* 280 */     if (codepage <= 0) {
/* 281 */       String str = System.getProperty("org.jline.terminal.codepage");
/* 282 */       if (str != null) {
/* 283 */         codepage = Integer.parseInt(str);
/*     */       }
/*     */     } 
/* 286 */     String type = this.type;
/* 287 */     if (type == null) {
/* 288 */       type = System.getProperty("org.jline.terminal.type");
/*     */     }
/* 290 */     if (type == null) {
/* 291 */       type = System.getenv("TERM");
/*     */     }
/* 293 */     Boolean jna = this.jna;
/* 294 */     if (jna == null) {
/* 295 */       jna = getBoolean("org.jline.terminal.jna", Boolean.valueOf(true));
/*     */     }
/* 297 */     Boolean jansi = this.jansi;
/* 298 */     if (jansi == null) {
/* 299 */       jansi = getBoolean("org.jline.terminal.jansi", Boolean.valueOf(true));
/*     */     }
/* 301 */     Boolean exec = this.exec;
/* 302 */     if (exec == null) {
/* 303 */       exec = getBoolean("org.jline.terminal.exec", Boolean.valueOf(true));
/*     */     }
/* 305 */     Boolean dumb = this.dumb;
/* 306 */     if (dumb == null) {
/* 307 */       dumb = getBoolean("org.jline.terminal.dumb", null);
/*     */     }
/* 309 */     if ((this.system != null && this.system.booleanValue()) || (this.system == null && this.in == null && this.out == null)) {
/* 310 */       if (this.attributes != null || this.size != null) {
/* 311 */         Log.warn(new Object[] { "Attributes and size fields are ignored when creating a system terminal" });
/*     */       }
/* 313 */       IllegalStateException exception = new IllegalStateException("Unable to create a system terminal");
/* 314 */       if (OSUtils.IS_WINDOWS) {
/* 315 */         boolean cygwinTerm = "cygwin".equals(System.getenv("TERM"));
/* 316 */         boolean ansiPassThrough = OSUtils.IS_CONEMU;
/*     */ 
/*     */ 
/*     */         
/* 320 */         if ((OSUtils.IS_CYGWIN || OSUtils.IS_MSYSTEM) && exec.booleanValue() && !cygwinTerm) {
/*     */           try {
/* 322 */             Pty pty = ExecPty.current();
/*     */ 
/*     */             
/* 325 */             if ("xterm".equals(type) && this.type == null && System.getProperty("org.jline.terminal.type") == null) {
/* 326 */               type = "xterm-256color";
/*     */             }
/* 328 */             return (Terminal)new PosixSysTerminal(name, type, pty, encoding, this.nativeSignals, this.signalHandler);
/* 329 */           } catch (IOException e) {
/*     */             
/* 331 */             Log.debug(new Object[] { "Error creating EXEC based terminal: ", e.getMessage(), e });
/* 332 */             exception.addSuppressed(e);
/*     */           } 
/*     */         }
/* 335 */         if (jna.booleanValue()) {
/*     */           try {
/* 337 */             return ((JnaSupport)load(JnaSupport.class)).winSysTerminal(name, type, ansiPassThrough, encoding, codepage, this.nativeSignals, this.signalHandler, this.paused);
/* 338 */           } catch (Throwable t) {
/* 339 */             Log.debug(new Object[] { "Error creating JNA based terminal: ", t.getMessage(), t });
/* 340 */             exception.addSuppressed(t);
/*     */           } 
/*     */         }
/* 343 */         if (jansi.booleanValue()) {
/*     */           try {
/* 345 */             return ((JansiSupport)load(JansiSupport.class)).winSysTerminal(name, type, ansiPassThrough, encoding, codepage, this.nativeSignals, this.signalHandler, this.paused);
/* 346 */           } catch (Throwable t) {
/* 347 */             Log.debug(new Object[] { "Error creating JANSI based terminal: ", t.getMessage(), t });
/* 348 */             exception.addSuppressed(t);
/*     */           } 
/*     */         }
/*     */       } else {
/* 352 */         if (jna.booleanValue()) {
/*     */           try {
/* 354 */             Pty pty = ((JnaSupport)load(JnaSupport.class)).current();
/* 355 */             return (Terminal)new PosixSysTerminal(name, type, pty, encoding, this.nativeSignals, this.signalHandler);
/* 356 */           } catch (Throwable t) {
/*     */             
/* 358 */             Log.debug(new Object[] { "Error creating JNA based terminal: ", t.getMessage(), t });
/* 359 */             exception.addSuppressed(t);
/*     */           } 
/*     */         }
/* 362 */         if (jansi.booleanValue()) {
/*     */           try {
/* 364 */             Pty pty = ((JansiSupport)load(JansiSupport.class)).current();
/* 365 */             return (Terminal)new PosixSysTerminal(name, type, pty, encoding, this.nativeSignals, this.signalHandler);
/* 366 */           } catch (Throwable t) {
/* 367 */             Log.debug(new Object[] { "Error creating JANSI based terminal: ", t.getMessage(), t });
/* 368 */             exception.addSuppressed(t);
/*     */           } 
/*     */         }
/* 371 */         if (exec.booleanValue()) {
/*     */           try {
/* 373 */             Pty pty = ExecPty.current();
/* 374 */             return (Terminal)new PosixSysTerminal(name, type, pty, encoding, this.nativeSignals, this.signalHandler);
/* 375 */           } catch (Throwable t) {
/*     */             
/* 377 */             Log.debug(new Object[] { "Error creating EXEC based terminal: ", t.getMessage(), t });
/* 378 */             exception.addSuppressed(t);
/*     */           } 
/*     */         }
/*     */       } 
/* 382 */       if (dumb == null || dumb.booleanValue()) {
/*     */         
/* 384 */         boolean color = getBoolean("org.jline.terminal.dumb.color", Boolean.valueOf(false)).booleanValue();
/*     */         
/* 386 */         if (!color) {
/* 387 */           color = (System.getenv("INSIDE_EMACS") != null);
/*     */         }
/*     */         
/* 390 */         if (!color) {
/* 391 */           String command = getParentProcessCommand();
/* 392 */           color = (command != null && command.contains("idea"));
/*     */         } 
/* 394 */         if (!color && dumb == null) {
/* 395 */           if (Log.isDebugEnabled()) {
/* 396 */             Log.warn(new Object[] { "Creating a dumb terminal", exception });
/*     */           } else {
/* 398 */             Log.warn(new Object[] { "Unable to create a system terminal, creating a dumb terminal (enable debug logging for more information)" });
/*     */           } 
/*     */         }
/* 401 */         return (Terminal)new DumbTerminal(name, color ? "dumb-color" : "dumb", new FileInputStream(FileDescriptor.in), new FileOutputStream(FileDescriptor.out), encoding, this.signalHandler);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 406 */       throw exception;
/*     */     } 
/*     */     
/* 409 */     if (jna.booleanValue()) {
/*     */       try {
/* 411 */         Pty pty = ((JnaSupport)load(JnaSupport.class)).open(this.attributes, this.size);
/* 412 */         return (Terminal)new PosixPtyTerminal(name, type, pty, this.in, this.out, encoding, this.signalHandler, this.paused);
/* 413 */       } catch (Throwable t) {
/* 414 */         Log.debug(new Object[] { "Error creating JNA based terminal: ", t.getMessage(), t });
/*     */       } 
/*     */     }
/* 417 */     if (jansi.booleanValue()) {
/*     */       try {
/* 419 */         Pty pty = ((JansiSupport)load(JansiSupport.class)).open(this.attributes, this.size);
/* 420 */         return (Terminal)new PosixPtyTerminal(name, type, pty, this.in, this.out, encoding, this.signalHandler, this.paused);
/* 421 */       } catch (Throwable t) {
/* 422 */         Log.debug(new Object[] { "Error creating JANSI based terminal: ", t.getMessage(), t });
/*     */       } 
/*     */     }
/* 425 */     ExternalTerminal externalTerminal = new ExternalTerminal(name, type, this.in, this.out, encoding, this.signalHandler, this.paused);
/* 426 */     if (this.attributes != null) {
/* 427 */       externalTerminal.setAttributes(this.attributes);
/*     */     }
/* 429 */     if (this.size != null) {
/* 430 */       externalTerminal.setSize(this.size);
/*     */     }
/* 432 */     return (Terminal)externalTerminal;
/*     */   }
/*     */ 
/*     */   
/*     */   private static String getParentProcessCommand() {
/*     */     try {
/* 438 */       Class<?> phClass = Class.forName("java.lang.ProcessHandle");
/* 439 */       Object current = phClass.getMethod("current", new Class[0]).invoke(null, new Object[0]);
/* 440 */       Object parent = ((Optional)phClass.getMethod("parent", new Class[0]).invoke(current, new Object[0])).orElse(null);
/* 441 */       Method infoMethod = phClass.getMethod("info", new Class[0]);
/* 442 */       Object info = infoMethod.invoke(parent, new Object[0]);
/* 443 */       Object command = ((Optional)infoMethod.getReturnType().getMethod("command", new Class[0]).invoke(info, new Object[0])).orElse(null);
/* 444 */       return (String)command;
/* 445 */     } catch (Throwable t) {
/* 446 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static Boolean getBoolean(String name, Boolean def) {
/*     */     try {
/* 452 */       String str = System.getProperty(name);
/* 453 */       if (str != null) {
/* 454 */         return Boolean.valueOf(Boolean.parseBoolean(str));
/*     */       }
/* 456 */     } catch (IllegalArgumentException|NullPointerException illegalArgumentException) {}
/*     */     
/* 458 */     return def;
/*     */   }
/*     */   
/*     */   private <S> S load(Class<S> clazz) {
/* 462 */     return ServiceLoader.<S>load(clazz, clazz.getClassLoader()).iterator().next();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\terminal\TerminalBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */