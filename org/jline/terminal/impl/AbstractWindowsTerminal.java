/*     */ package org.jline.terminal.impl;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.Writer;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.jline.terminal.Attributes;
/*     */ import org.jline.terminal.Size;
/*     */ import org.jline.terminal.Terminal;
/*     */ import org.jline.utils.Curses;
/*     */ import org.jline.utils.InfoCmp;
/*     */ import org.jline.utils.Log;
/*     */ import org.jline.utils.NonBlocking;
/*     */ import org.jline.utils.NonBlockingInputStream;
/*     */ import org.jline.utils.NonBlockingPumpReader;
/*     */ import org.jline.utils.NonBlockingReader;
/*     */ import org.jline.utils.ShutdownHooks;
/*     */ import org.jline.utils.Signals;
/*     */ import org.jline.utils.WriterOutputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractWindowsTerminal
/*     */   extends AbstractTerminal
/*     */ {
/*     */   public static final String TYPE_WINDOWS = "windows";
/*     */   public static final String TYPE_WINDOWS_256_COLOR = "windows-256color";
/*     */   public static final String TYPE_WINDOWS_CONEMU = "windows-conemu";
/*     */   public static final String TYPE_WINDOWS_VTP = "windows-vtp";
/*     */   public static final int ENABLE_VIRTUAL_TERMINAL_PROCESSING = 4;
/*     */   private static final int UTF8_CODE_PAGE = 65001;
/*     */   protected static final int ENABLE_PROCESSED_INPUT = 1;
/*     */   protected static final int ENABLE_LINE_INPUT = 2;
/*     */   protected static final int ENABLE_ECHO_INPUT = 4;
/*     */   protected static final int ENABLE_WINDOW_INPUT = 8;
/*     */   protected static final int ENABLE_MOUSE_INPUT = 16;
/*     */   protected static final int ENABLE_INSERT_MODE = 32;
/*     */   protected static final int ENABLE_QUICK_EDIT_MODE = 64;
/*     */   protected final Writer slaveInputPipe;
/*     */   protected final NonBlockingInputStream input;
/*     */   protected final OutputStream output;
/*     */   protected final NonBlockingReader reader;
/*     */   protected final PrintWriter writer;
/*  70 */   protected final Map<Terminal.Signal, Object> nativeHandlers = new HashMap<>();
/*     */   protected final ShutdownHooks.Task closer;
/*  72 */   protected final Attributes attributes = new Attributes();
/*     */   
/*     */   protected final int originalConsoleMode;
/*  75 */   protected final Object lock = new Object();
/*     */   
/*     */   protected boolean paused = true;
/*     */   protected Thread pump;
/*  79 */   protected Terminal.MouseTracking tracking = Terminal.MouseTracking.Off; protected boolean focusTracking = false; private volatile boolean closing; static final int SHIFT_FLAG = 1; static final int ALT_FLAG = 2;
/*     */   static final int CTRL_FLAG = 4;
/*     */   static final int RIGHT_ALT_PRESSED = 1;
/*     */   
/*     */   public AbstractWindowsTerminal(Writer writer, String name, String type, Charset encoding, int codepage, boolean nativeSignals, Terminal.SignalHandler signalHandler) throws IOException {
/*  84 */     super(name, type, selectCharset(encoding, codepage), signalHandler);
/*  85 */     NonBlockingPumpReader reader = NonBlocking.nonBlockingPumpReader();
/*  86 */     this.slaveInputPipe = reader.getWriter();
/*  87 */     this.reader = (NonBlockingReader)reader;
/*  88 */     this.input = NonBlocking.nonBlockingStream((NonBlockingReader)reader, encoding());
/*  89 */     this.writer = new PrintWriter(writer);
/*  90 */     this.output = (OutputStream)new WriterOutputStream(writer, encoding());
/*  91 */     parseInfoCmp();
/*     */     
/*  93 */     this.originalConsoleMode = getConsoleMode();
/*  94 */     this.attributes.setLocalFlag(Attributes.LocalFlag.ISIG, true);
/*  95 */     this.attributes.setControlChar(Attributes.ControlChar.VINTR, ctrl('C'));
/*  96 */     this.attributes.setControlChar(Attributes.ControlChar.VEOF, ctrl('D'));
/*  97 */     this.attributes.setControlChar(Attributes.ControlChar.VSUSP, ctrl('Z'));
/*     */     
/*  99 */     if (nativeSignals) {
/* 100 */       for (Terminal.Signal signal : Terminal.Signal.values()) {
/* 101 */         if (signalHandler == Terminal.SignalHandler.SIG_DFL) {
/* 102 */           this.nativeHandlers.put(signal, Signals.registerDefault(signal.name()));
/*     */         } else {
/* 104 */           this.nativeHandlers.put(signal, Signals.register(signal.name(), () -> raise(signal)));
/*     */         } 
/*     */       } 
/*     */     }
/* 108 */     this.closer = this::close;
/* 109 */     ShutdownHooks.add(this.closer);
/*     */     
/* 111 */     if ("windows-conemu".equals(getType()) && 
/* 112 */       !Boolean.getBoolean("org.jline.terminal.conemu.disable-activate")) {
/* 113 */       writer.write("\033[9999E");
/* 114 */       writer.flush();
/*     */     } 
/*     */   }
/*     */   static final int LEFT_ALT_PRESSED = 2; static final int RIGHT_CTRL_PRESSED = 4; static final int LEFT_CTRL_PRESSED = 8; static final int SHIFT_PRESSED = 16; static final int NUMLOCK_ON = 32; static final int SCROLLLOCK_ON = 64; static final int CAPSLOCK_ON = 128;
/*     */   private static Charset selectCharset(Charset encoding, int codepage) {
/* 119 */     if (encoding != null) {
/* 120 */       return encoding;
/*     */     }
/*     */     
/* 123 */     if (codepage >= 0) {
/* 124 */       return getCodepageCharset(codepage);
/*     */     }
/*     */ 
/*     */     
/* 128 */     return StandardCharsets.UTF_8;
/*     */   }
/*     */ 
/*     */   
/*     */   private static Charset getCodepageCharset(int codepage) {
/* 133 */     if (codepage == 65001) {
/* 134 */       return StandardCharsets.UTF_8;
/*     */     }
/* 136 */     String charsetMS = "ms" + codepage;
/* 137 */     if (Charset.isSupported(charsetMS)) {
/* 138 */       return Charset.forName(charsetMS);
/*     */     }
/* 140 */     String charsetCP = "cp" + codepage;
/* 141 */     if (Charset.isSupported(charsetCP)) {
/* 142 */       return Charset.forName(charsetCP);
/*     */     }
/* 144 */     return Charset.defaultCharset();
/*     */   }
/*     */ 
/*     */   
/*     */   public Terminal.SignalHandler handle(Terminal.Signal signal, Terminal.SignalHandler handler) {
/* 149 */     Terminal.SignalHandler prev = super.handle(signal, handler);
/* 150 */     if (prev != handler) {
/* 151 */       if (handler == Terminal.SignalHandler.SIG_DFL) {
/* 152 */         Signals.registerDefault(signal.name());
/*     */       } else {
/* 154 */         Signals.register(signal.name(), () -> raise(signal));
/*     */       } 
/*     */     }
/* 157 */     return prev;
/*     */   }
/*     */   
/*     */   public NonBlockingReader reader() {
/* 161 */     return this.reader;
/*     */   }
/*     */   
/*     */   public PrintWriter writer() {
/* 165 */     return this.writer;
/*     */   }
/*     */ 
/*     */   
/*     */   public InputStream input() {
/* 170 */     return (InputStream)this.input;
/*     */   }
/*     */ 
/*     */   
/*     */   public OutputStream output() {
/* 175 */     return this.output;
/*     */   }
/*     */   
/*     */   public Attributes getAttributes() {
/* 179 */     int mode = getConsoleMode();
/* 180 */     if ((mode & 0x4) != 0) {
/* 181 */       this.attributes.setLocalFlag(Attributes.LocalFlag.ECHO, true);
/*     */     }
/* 183 */     if ((mode & 0x2) != 0) {
/* 184 */       this.attributes.setLocalFlag(Attributes.LocalFlag.ICANON, true);
/*     */     }
/* 186 */     return new Attributes(this.attributes);
/*     */   }
/*     */   
/*     */   public void setAttributes(Attributes attr) {
/* 190 */     this.attributes.copy(attr);
/* 191 */     updateConsoleMode();
/*     */   }
/*     */   
/*     */   protected void updateConsoleMode() {
/* 195 */     int mode = 8;
/* 196 */     if (this.attributes.getLocalFlag(Attributes.LocalFlag.ECHO)) {
/* 197 */       mode |= 0x4;
/*     */     }
/* 199 */     if (this.attributes.getLocalFlag(Attributes.LocalFlag.ICANON)) {
/* 200 */       mode |= 0x2;
/*     */     }
/* 202 */     if (this.tracking != Terminal.MouseTracking.Off) {
/* 203 */       mode |= 0x10;
/*     */     }
/* 205 */     setConsoleMode(mode);
/*     */   }
/*     */   
/*     */   protected int ctrl(char key) {
/* 209 */     return Character.toUpperCase(key) & 0x1F;
/*     */   }
/*     */   
/*     */   public void setSize(Size size) {
/* 213 */     throw new UnsupportedOperationException("Can not resize windows terminal");
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 217 */     super.close();
/* 218 */     this.closing = true;
/* 219 */     this.pump.interrupt();
/* 220 */     ShutdownHooks.remove(this.closer);
/* 221 */     for (Map.Entry<Terminal.Signal, Object> entry : this.nativeHandlers.entrySet()) {
/* 222 */       Signals.unregister(((Terminal.Signal)entry.getKey()).name(), entry.getValue());
/*     */     }
/* 224 */     this.reader.close();
/* 225 */     this.writer.close();
/* 226 */     setConsoleMode(this.originalConsoleMode);
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
/*     */   protected void processKeyEvent(boolean isKeyDown, short virtualKeyCode, char ch, int controlKeyState) throws IOException {
/* 243 */     boolean isCtrl = ((controlKeyState & 0xC) > 0);
/* 244 */     boolean isAlt = ((controlKeyState & 0x3) > 0);
/* 245 */     boolean isShift = ((controlKeyState & 0x10) > 0);
/*     */     
/* 247 */     if (isKeyDown && ch != '\003') {
/*     */ 
/*     */       
/* 250 */       if (ch != '\000' && (controlKeyState & 0x1F) == 9) {
/*     */ 
/*     */         
/* 253 */         processInputChar(ch);
/*     */       } else {
/* 255 */         String keySeq = getEscapeSequence(virtualKeyCode, (isCtrl ? 4 : 0) + (isAlt ? 2 : 0) + (isShift ? 1 : 0));
/* 256 */         if (keySeq != null) {
/* 257 */           for (char c : keySeq.toCharArray()) {
/* 258 */             processInputChar(c);
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           return;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 270 */         if (ch > '\000') {
/* 271 */           if (isAlt) {
/* 272 */             processInputChar('\033');
/*     */           }
/* 274 */           if (isCtrl && ch != ' ' && ch != '\n' && ch != '') {
/* 275 */             processInputChar((char)((ch == '?') ? '' : (Character.toUpperCase(ch) & 0x1F)));
/*     */           } else {
/* 277 */             processInputChar(ch);
/*     */           } 
/* 279 */         } else if (isCtrl) {
/* 280 */           if (virtualKeyCode >= 65 && virtualKeyCode <= 90) {
/* 281 */             ch = (char)(virtualKeyCode - 64);
/* 282 */           } else if (virtualKeyCode == 191) {
/* 283 */             ch = '';
/*     */           } 
/* 285 */           if (ch > '\000') {
/* 286 */             if (isAlt) {
/* 287 */               processInputChar('\033');
/*     */             }
/* 289 */             processInputChar(ch);
/*     */           } 
/*     */         } 
/*     */       } 
/* 293 */     } else if (isKeyDown && ch == '\003') {
/* 294 */       processInputChar('\003');
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 299 */     else if (virtualKeyCode == 18 && ch > '\000') {
/* 300 */       processInputChar(ch);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getEscapeSequence(short keyCode, int keyState) {
/* 308 */     String escapeSequence = null;
/* 309 */     switch (keyCode) {
/*     */       case 8:
/* 311 */         escapeSequence = ((keyState & 0x2) > 0) ? "\\E^H" : getRawSequence(InfoCmp.Capability.key_backspace);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 387 */         return Curses.tputs(escapeSequence, new Object[] { Integer.valueOf(keyState + 1) });case 9: escapeSequence = ((keyState & 0x1) > 0) ? getRawSequence(InfoCmp.Capability.key_btab) : null; return Curses.tputs(escapeSequence, new Object[] { Integer.valueOf(keyState + 1) });case 33: escapeSequence = getRawSequence(InfoCmp.Capability.key_ppage); return Curses.tputs(escapeSequence, new Object[] { Integer.valueOf(keyState + 1) });case 34: escapeSequence = getRawSequence(InfoCmp.Capability.key_npage); return Curses.tputs(escapeSequence, new Object[] { Integer.valueOf(keyState + 1) });case 35: escapeSequence = (keyState > 0) ? "\\E[1;%p1%dF" : getRawSequence(InfoCmp.Capability.key_end); return Curses.tputs(escapeSequence, new Object[] { Integer.valueOf(keyState + 1) });case 36: escapeSequence = (keyState > 0) ? "\\E[1;%p1%dH" : getRawSequence(InfoCmp.Capability.key_home); return Curses.tputs(escapeSequence, new Object[] { Integer.valueOf(keyState + 1) });case 37: escapeSequence = (keyState > 0) ? "\\E[1;%p1%dD" : getRawSequence(InfoCmp.Capability.key_left); return Curses.tputs(escapeSequence, new Object[] { Integer.valueOf(keyState + 1) });case 38: escapeSequence = (keyState > 0) ? "\\E[1;%p1%dA" : getRawSequence(InfoCmp.Capability.key_up); return Curses.tputs(escapeSequence, new Object[] { Integer.valueOf(keyState + 1) });case 39: escapeSequence = (keyState > 0) ? "\\E[1;%p1%dC" : getRawSequence(InfoCmp.Capability.key_right); return Curses.tputs(escapeSequence, new Object[] { Integer.valueOf(keyState + 1) });case 40: escapeSequence = (keyState > 0) ? "\\E[1;%p1%dB" : getRawSequence(InfoCmp.Capability.key_down); return Curses.tputs(escapeSequence, new Object[] { Integer.valueOf(keyState + 1) });case 45: escapeSequence = getRawSequence(InfoCmp.Capability.key_ic); return Curses.tputs(escapeSequence, new Object[] { Integer.valueOf(keyState + 1) });case 46: escapeSequence = getRawSequence(InfoCmp.Capability.key_dc); return Curses.tputs(escapeSequence, new Object[] { Integer.valueOf(keyState + 1) });case 112: escapeSequence = (keyState > 0) ? "\\E[1;%p1%dP" : getRawSequence(InfoCmp.Capability.key_f1); return Curses.tputs(escapeSequence, new Object[] { Integer.valueOf(keyState + 1) });case 113: escapeSequence = (keyState > 0) ? "\\E[1;%p1%dQ" : getRawSequence(InfoCmp.Capability.key_f2); return Curses.tputs(escapeSequence, new Object[] { Integer.valueOf(keyState + 1) });case 114: escapeSequence = (keyState > 0) ? "\\E[1;%p1%dR" : getRawSequence(InfoCmp.Capability.key_f3); return Curses.tputs(escapeSequence, new Object[] { Integer.valueOf(keyState + 1) });case 115: escapeSequence = (keyState > 0) ? "\\E[1;%p1%dS" : getRawSequence(InfoCmp.Capability.key_f4); return Curses.tputs(escapeSequence, new Object[] { Integer.valueOf(keyState + 1) });case 116: escapeSequence = (keyState > 0) ? "\\E[15;%p1%d~" : getRawSequence(InfoCmp.Capability.key_f5); return Curses.tputs(escapeSequence, new Object[] { Integer.valueOf(keyState + 1) });case 117: escapeSequence = (keyState > 0) ? "\\E[17;%p1%d~" : getRawSequence(InfoCmp.Capability.key_f6); return Curses.tputs(escapeSequence, new Object[] { Integer.valueOf(keyState + 1) });case 118: escapeSequence = (keyState > 0) ? "\\E[18;%p1%d~" : getRawSequence(InfoCmp.Capability.key_f7); return Curses.tputs(escapeSequence, new Object[] { Integer.valueOf(keyState + 1) });case 119: escapeSequence = (keyState > 0) ? "\\E[19;%p1%d~" : getRawSequence(InfoCmp.Capability.key_f8); return Curses.tputs(escapeSequence, new Object[] { Integer.valueOf(keyState + 1) });case 120: escapeSequence = (keyState > 0) ? "\\E[20;%p1%d~" : getRawSequence(InfoCmp.Capability.key_f9); return Curses.tputs(escapeSequence, new Object[] { Integer.valueOf(keyState + 1) });case 121: escapeSequence = (keyState > 0) ? "\\E[21;%p1%d~" : getRawSequence(InfoCmp.Capability.key_f10); return Curses.tputs(escapeSequence, new Object[] { Integer.valueOf(keyState + 1) });case 122: escapeSequence = (keyState > 0) ? "\\E[23;%p1%d~" : getRawSequence(InfoCmp.Capability.key_f11); return Curses.tputs(escapeSequence, new Object[] { Integer.valueOf(keyState + 1) });case 123: escapeSequence = (keyState > 0) ? "\\E[24;%p1%d~" : getRawSequence(InfoCmp.Capability.key_f12); return Curses.tputs(escapeSequence, new Object[] { Integer.valueOf(keyState + 1) });
/*     */     } 
/*     */     return null;
/*     */   } protected String getRawSequence(InfoCmp.Capability cap) {
/* 391 */     return this.strings.get(cap);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasFocusSupport() {
/* 396 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean trackFocus(boolean tracking) {
/* 401 */     this.focusTracking = tracking;
/* 402 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPauseResume() {
/* 407 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void pause() {
/* 412 */     synchronized (this.lock) {
/* 413 */       this.paused = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void pause(boolean wait) throws InterruptedException {
/*     */     Thread p;
/* 420 */     synchronized (this.lock) {
/* 421 */       this.paused = true;
/* 422 */       p = this.pump;
/*     */     } 
/* 424 */     if (p != null) {
/* 425 */       p.interrupt();
/* 426 */       p.join();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void resume() {
/* 432 */     synchronized (this.lock) {
/* 433 */       this.paused = false;
/* 434 */       if (this.pump == null) {
/* 435 */         this.pump = new Thread(this::pump, "WindowsStreamPump");
/* 436 */         this.pump.setDaemon(true);
/* 437 */         this.pump.start();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean paused() {
/* 444 */     synchronized (this.lock) {
/* 445 */       return this.paused;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void pump() {
/*     */     try {
/* 451 */       while (!this.closing) {
/* 452 */         synchronized (this.lock) {
/* 453 */           if (this.paused) {
/* 454 */             this.pump = null;
/*     */             break;
/*     */           } 
/*     */         } 
/* 458 */         if (processConsoleInput()) {
/* 459 */           this.slaveInputPipe.flush();
/*     */         }
/*     */       } 
/* 462 */     } catch (IOException e) {
/* 463 */       if (!this.closing) {
/* 464 */         Log.warn(new Object[] { "Error in WindowsStreamPump", e });
/*     */         try {
/* 466 */           close();
/* 467 */         } catch (IOException e1) {
/* 468 */           Log.warn(new Object[] { "Error closing terminal", e });
/*     */         } 
/*     */       } 
/*     */     } finally {
/* 472 */       synchronized (this.lock) {
/* 473 */         this.pump = null;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void processInputChar(char c) throws IOException {
/* 479 */     if (this.attributes.getLocalFlag(Attributes.LocalFlag.ISIG)) {
/* 480 */       if (c == this.attributes.getControlChar(Attributes.ControlChar.VINTR)) {
/* 481 */         raise(Terminal.Signal.INT); return;
/*     */       } 
/* 483 */       if (c == this.attributes.getControlChar(Attributes.ControlChar.VQUIT)) {
/* 484 */         raise(Terminal.Signal.QUIT); return;
/*     */       } 
/* 486 */       if (c == this.attributes.getControlChar(Attributes.ControlChar.VSUSP)) {
/* 487 */         raise(Terminal.Signal.TSTP); return;
/*     */       } 
/* 489 */       if (c == this.attributes.getControlChar(Attributes.ControlChar.VSTATUS)) {
/* 490 */         raise(Terminal.Signal.INFO);
/*     */       }
/*     */     } 
/* 493 */     if (c == '\r') {
/* 494 */       if (this.attributes.getInputFlag(Attributes.InputFlag.IGNCR)) {
/*     */         return;
/*     */       }
/* 497 */       if (this.attributes.getInputFlag(Attributes.InputFlag.ICRNL)) {
/* 498 */         c = '\n';
/*     */       }
/* 500 */     } else if (c == '\n' && this.attributes.getInputFlag(Attributes.InputFlag.INLCR)) {
/* 501 */       c = '\r';
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 507 */     this.slaveInputPipe.write(c);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean trackMouse(Terminal.MouseTracking tracking) {
/* 512 */     this.tracking = tracking;
/* 513 */     updateConsoleMode();
/* 514 */     return true;
/*     */   }
/*     */   
/*     */   protected abstract int getConsoleOutputCP();
/*     */   
/*     */   protected abstract int getConsoleMode();
/*     */   
/*     */   protected abstract void setConsoleMode(int paramInt);
/*     */   
/*     */   protected abstract boolean processConsoleInput() throws IOException;
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\terminal\impl\AbstractWindowsTerminal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */