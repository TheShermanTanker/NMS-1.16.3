/*     */ package org.jline.terminal.impl;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InterruptedIOException;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.EnumSet;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import java.util.function.IntConsumer;
/*     */ import java.util.function.IntSupplier;
/*     */ import org.jline.terminal.Attributes;
/*     */ import org.jline.terminal.Cursor;
/*     */ import org.jline.terminal.MouseEvent;
/*     */ import org.jline.terminal.Terminal;
/*     */ import org.jline.utils.Curses;
/*     */ import org.jline.utils.InfoCmp;
/*     */ import org.jline.utils.Log;
/*     */ import org.jline.utils.Status;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractTerminal
/*     */   implements Terminal
/*     */ {
/*     */   protected final String name;
/*     */   protected final String type;
/*     */   protected final Charset encoding;
/*  41 */   protected final Map<Terminal.Signal, Terminal.SignalHandler> handlers = new HashMap<>();
/*  42 */   protected final Set<InfoCmp.Capability> bools = new HashSet<>();
/*  43 */   protected final Map<InfoCmp.Capability, Integer> ints = new HashMap<>();
/*  44 */   protected final Map<InfoCmp.Capability, String> strings = new HashMap<>(); protected Status status;
/*     */   private MouseEvent lastMouseEvent;
/*     */   
/*     */   public AbstractTerminal(String name, String type) throws IOException {
/*  48 */     this(name, type, (Charset)null, Terminal.SignalHandler.SIG_DFL);
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
/*     */   public Status getStatus() {
/*  61 */     return getStatus(true);
/*     */   }
/*     */   
/*     */   public Status getStatus(boolean create) {
/*  65 */     if (this.status == null && create) {
/*  66 */       this.status = new Status(this);
/*     */     }
/*  68 */     return this.status;
/*     */   }
/*     */   
/*     */   public Terminal.SignalHandler handle(Terminal.Signal signal, Terminal.SignalHandler handler) {
/*  72 */     Objects.requireNonNull(signal);
/*  73 */     Objects.requireNonNull(handler);
/*  74 */     return this.handlers.put(signal, handler);
/*     */   }
/*     */   
/*     */   public void raise(Terminal.Signal signal) {
/*  78 */     Objects.requireNonNull(signal);
/*  79 */     Terminal.SignalHandler handler = this.handlers.get(signal);
/*  80 */     if (handler != Terminal.SignalHandler.SIG_DFL && handler != Terminal.SignalHandler.SIG_IGN) {
/*  81 */       handler.handle(signal);
/*     */     }
/*  83 */     if (this.status != null && signal == Terminal.Signal.WINCH) {
/*  84 */       this.status.resize();
/*     */     }
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/*  89 */     if (this.status != null) {
/*  90 */       this.status.update(null);
/*  91 */       flush();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void echoSignal(Terminal.Signal signal) {
/*  96 */     Attributes.ControlChar cc = null;
/*  97 */     switch (signal) {
/*     */       case INT:
/*  99 */         cc = Attributes.ControlChar.VINTR;
/*     */         break;
/*     */       case QUIT:
/* 102 */         cc = Attributes.ControlChar.VQUIT;
/*     */         break;
/*     */       case TSTP:
/* 105 */         cc = Attributes.ControlChar.VSUSP;
/*     */         break;
/*     */     } 
/* 108 */     if (cc != null) {
/* 109 */       int vcc = getAttributes().getControlChar(cc);
/* 110 */       if (vcc > 0 && vcc < 32) {
/* 111 */         writer().write(new char[] { '^', (char)(vcc + 64) }, 0, 2);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public Attributes enterRawMode() {
/* 117 */     Attributes prvAttr = getAttributes();
/* 118 */     Attributes newAttr = new Attributes(prvAttr);
/* 119 */     newAttr.setLocalFlags(EnumSet.of(Attributes.LocalFlag.ICANON, Attributes.LocalFlag.ECHO, Attributes.LocalFlag.IEXTEN), false);
/* 120 */     newAttr.setInputFlags(EnumSet.of(Attributes.InputFlag.IXON, Attributes.InputFlag.ICRNL, Attributes.InputFlag.INLCR), false);
/* 121 */     newAttr.setControlChar(Attributes.ControlChar.VMIN, 0);
/* 122 */     newAttr.setControlChar(Attributes.ControlChar.VTIME, 1);
/* 123 */     setAttributes(newAttr);
/* 124 */     return prvAttr;
/*     */   }
/*     */   
/*     */   public boolean echo() {
/* 128 */     return getAttributes().getLocalFlag(Attributes.LocalFlag.ECHO);
/*     */   }
/*     */   
/*     */   public boolean echo(boolean echo) {
/* 132 */     Attributes attr = getAttributes();
/* 133 */     boolean prev = attr.getLocalFlag(Attributes.LocalFlag.ECHO);
/* 134 */     if (prev != echo) {
/* 135 */       attr.setLocalFlag(Attributes.LocalFlag.ECHO, echo);
/* 136 */       setAttributes(attr);
/*     */     } 
/* 138 */     return prev;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 142 */     return this.name;
/*     */   }
/*     */   
/*     */   public String getType() {
/* 146 */     return this.type;
/*     */   }
/*     */   
/*     */   public String getKind() {
/* 150 */     return getClass().getSimpleName();
/*     */   }
/*     */ 
/*     */   
/*     */   public Charset encoding() {
/* 155 */     return this.encoding;
/*     */   }
/*     */   
/*     */   public void flush() {
/* 159 */     writer().flush();
/*     */   }
/*     */   
/*     */   public boolean puts(InfoCmp.Capability capability, Object... params) {
/* 163 */     String str = getStringCapability(capability);
/* 164 */     if (str == null) {
/* 165 */       return false;
/*     */     }
/* 167 */     Curses.tputs(writer(), str, params);
/* 168 */     return true;
/*     */   }
/*     */   
/*     */   public boolean getBooleanCapability(InfoCmp.Capability capability) {
/* 172 */     return this.bools.contains(capability);
/*     */   }
/*     */   
/*     */   public Integer getNumericCapability(InfoCmp.Capability capability) {
/* 176 */     return this.ints.get(capability);
/*     */   }
/*     */   
/*     */   public String getStringCapability(InfoCmp.Capability capability) {
/* 180 */     return this.strings.get(capability);
/*     */   }
/*     */   
/*     */   protected void parseInfoCmp() {
/* 184 */     String capabilities = null;
/* 185 */     if (this.type != null) {
/*     */       try {
/* 187 */         capabilities = InfoCmp.getInfoCmp(this.type);
/* 188 */       } catch (Exception e) {
/* 189 */         Log.warn(new Object[] { "Unable to retrieve infocmp for type " + this.type, e });
/*     */       } 
/*     */     }
/* 192 */     if (capabilities == null) {
/* 193 */       capabilities = InfoCmp.getLoadedInfoCmp("ansi");
/*     */     }
/* 195 */     InfoCmp.parseInfoCmp(capabilities, this.bools, this.ints, this.strings);
/*     */   }
/*     */ 
/*     */   
/*     */   public Cursor getCursorPosition(IntConsumer discarded) {
/* 200 */     return null;
/*     */   }
/*     */   
/* 203 */   public AbstractTerminal(String name, String type, Charset encoding, Terminal.SignalHandler signalHandler) throws IOException { this
/*     */       
/* 205 */       .lastMouseEvent = new MouseEvent(MouseEvent.Type.Moved, MouseEvent.Button.NoButton, EnumSet.noneOf(MouseEvent.Modifier.class), 0, 0); this.name = name;
/*     */     this.type = type;
/*     */     this.encoding = (encoding != null) ? encoding : Charset.defaultCharset();
/*     */     for (Terminal.Signal signal : Terminal.Signal.values())
/* 209 */       this.handlers.put(signal, signalHandler);  } public boolean hasMouseSupport() { return MouseSupport.hasMouseSupport(this); }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean trackMouse(Terminal.MouseTracking tracking) {
/* 214 */     return MouseSupport.trackMouse(this, tracking);
/*     */   }
/*     */ 
/*     */   
/*     */   public MouseEvent readMouseEvent() {
/* 219 */     return this.lastMouseEvent = MouseSupport.readMouse(this, this.lastMouseEvent);
/*     */   }
/*     */ 
/*     */   
/*     */   public MouseEvent readMouseEvent(IntSupplier reader) {
/* 224 */     return this.lastMouseEvent = MouseSupport.readMouse(reader, this.lastMouseEvent);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasFocusSupport() {
/* 229 */     return (this.type != null && this.type.startsWith("xterm"));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean trackFocus(boolean tracking) {
/* 234 */     if (hasFocusSupport()) {
/* 235 */       writer().write(tracking ? "\033[?1004h" : "\033[?1004l");
/* 236 */       writer().flush();
/* 237 */       return true;
/*     */     } 
/* 239 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void checkInterrupted() throws InterruptedIOException {
/* 244 */     if (Thread.interrupted()) {
/* 245 */       throw new InterruptedIOException();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPauseResume() {
/* 251 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void pause() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void pause(boolean wait) throws InterruptedException {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void resume() {}
/*     */ 
/*     */   
/*     */   public boolean paused() {
/* 268 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\terminal\impl\AbstractTerminal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */