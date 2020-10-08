/*     */ package org.jline.terminal.impl.jansi.win;
/*     */ 
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.IOError;
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.function.IntConsumer;
/*     */ import org.fusesource.jansi.internal.Kernel32;
/*     */ import org.fusesource.jansi.internal.WindowsSupport;
/*     */ import org.jline.terminal.Cursor;
/*     */ import org.jline.terminal.Size;
/*     */ import org.jline.terminal.Terminal;
/*     */ import org.jline.terminal.impl.AbstractWindowsTerminal;
/*     */ import org.jline.terminal.impl.jansi.JansiSupportImpl;
/*     */ import org.jline.utils.InfoCmp;
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
/*     */ public class JansiWinSysTerminal
/*     */   extends AbstractWindowsTerminal
/*     */ {
/*     */   private char[] focus;
/*     */   private char[] mouse;
/*     */   
/*     */   public static JansiWinSysTerminal createTerminal(String name, String type, boolean ansiPassThrough, Charset encoding, int codepage, boolean nativeSignals, Terminal.SignalHandler signalHandler, boolean paused) throws IOException {
/*     */     WindowsAnsiWriter windowsAnsiWriter;
/*  39 */     if (ansiPassThrough) {
/*  40 */       if (type == null) {
/*  41 */         type = OSUtils.IS_CONEMU ? "windows-conemu" : "windows";
/*     */       }
/*  43 */       JansiWinConsoleWriter jansiWinConsoleWriter = new JansiWinConsoleWriter();
/*     */     } else {
/*  45 */       long console = Kernel32.GetStdHandle(Kernel32.STD_OUTPUT_HANDLE);
/*  46 */       int[] mode = new int[1];
/*  47 */       if (Kernel32.GetConsoleMode(console, mode) == 0) {
/*  48 */         throw new IOException("Failed to get console mode: " + WindowsSupport.getLastErrorMessage());
/*     */       }
/*  50 */       if (Kernel32.SetConsoleMode(console, mode[0] | 0x4) != 0) {
/*  51 */         if (type == null) {
/*  52 */           type = "windows-vtp";
/*     */         }
/*  54 */         JansiWinConsoleWriter jansiWinConsoleWriter = new JansiWinConsoleWriter();
/*  55 */       } else if (OSUtils.IS_CONEMU) {
/*  56 */         if (type == null) {
/*  57 */           type = "windows-conemu";
/*     */         }
/*  59 */         JansiWinConsoleWriter jansiWinConsoleWriter = new JansiWinConsoleWriter();
/*     */       } else {
/*  61 */         if (type == null) {
/*  62 */           type = "windows";
/*     */         }
/*  64 */         windowsAnsiWriter = new WindowsAnsiWriter(new BufferedWriter((Writer)new JansiWinConsoleWriter()));
/*     */       } 
/*     */     } 
/*  67 */     JansiWinSysTerminal terminal = new JansiWinSysTerminal((Writer)windowsAnsiWriter, name, type, encoding, codepage, nativeSignals, signalHandler);
/*     */     
/*  69 */     if (!paused) {
/*  70 */       terminal.resume();
/*     */     }
/*  72 */     return terminal;
/*     */   }
/*     */   
/*     */   JansiWinSysTerminal(Writer writer, String name, String type, Charset encoding, int codepage, boolean nativeSignals, Terminal.SignalHandler signalHandler) throws IOException {
/*  76 */     super(writer, name, type, encoding, codepage, nativeSignals, signalHandler);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 139 */     this.focus = new char[] { '\033', '[', ' ' };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 148 */     this.mouse = new char[] { '\033', '[', 'M', ' ', ' ', ' ' };
/*     */   } protected int getConsoleOutputCP() { return Kernel32.GetConsoleOutputCP(); } protected int getConsoleMode() { return WindowsSupport.getConsoleMode(); }
/*     */   protected void setConsoleMode(int mode) { WindowsSupport.setConsoleMode(mode); }
/* 151 */   private void processMouseEvent(Kernel32.MOUSE_EVENT_RECORD mouseEvent) throws IOException { int dwEventFlags = mouseEvent.eventFlags;
/* 152 */     int dwButtonState = mouseEvent.buttonState;
/* 153 */     if (this.tracking == Terminal.MouseTracking.Off || (this.tracking == Terminal.MouseTracking.Normal && dwEventFlags == Kernel32.MOUSE_EVENT_RECORD.MOUSE_MOVED) || (this.tracking == Terminal.MouseTracking.Button && dwEventFlags == Kernel32.MOUSE_EVENT_RECORD.MOUSE_MOVED && dwButtonState == 0)) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 158 */     int cb = 0;
/* 159 */     dwEventFlags &= Kernel32.MOUSE_EVENT_RECORD.DOUBLE_CLICK ^ 0xFFFFFFFF;
/* 160 */     if (dwEventFlags == Kernel32.MOUSE_EVENT_RECORD.MOUSE_WHEELED) {
/* 161 */       cb |= 0x40;
/* 162 */       if (dwButtonState >> 16 < 0)
/* 163 */         cb |= 0x1; 
/*     */     } else {
/* 165 */       if (dwEventFlags == Kernel32.MOUSE_EVENT_RECORD.MOUSE_HWHEELED)
/*     */         return; 
/* 167 */       if ((dwButtonState & Kernel32.MOUSE_EVENT_RECORD.FROM_LEFT_1ST_BUTTON_PRESSED) != 0) {
/* 168 */         cb |= 0x0;
/* 169 */       } else if ((dwButtonState & Kernel32.MOUSE_EVENT_RECORD.RIGHTMOST_BUTTON_PRESSED) != 0) {
/* 170 */         cb |= 0x1;
/* 171 */       } else if ((dwButtonState & Kernel32.MOUSE_EVENT_RECORD.FROM_LEFT_2ND_BUTTON_PRESSED) != 0) {
/* 172 */         cb |= 0x2;
/*     */       } else {
/* 174 */         cb |= 0x3;
/*     */       } 
/* 176 */     }  int cx = mouseEvent.mousePosition.x;
/* 177 */     int cy = mouseEvent.mousePosition.y;
/* 178 */     this.mouse[3] = (char)(32 + cb);
/* 179 */     this.mouse[4] = (char)(32 + cx + 1);
/* 180 */     this.mouse[5] = (char)(32 + cy + 1);
/* 181 */     this.slaveInputPipe.write(this.mouse); }
/*     */   public Size getSize() { long outputHandle = Kernel32.GetStdHandle(Kernel32.STD_OUTPUT_HANDLE); Kernel32.CONSOLE_SCREEN_BUFFER_INFO info = new Kernel32.CONSOLE_SCREEN_BUFFER_INFO(); Kernel32.GetConsoleScreenBufferInfo(outputHandle, info); return new Size(info.windowWidth(), info.windowHeight()); }
/*     */   public Size getBufferSize() { long outputHandle = Kernel32.GetStdHandle(Kernel32.STD_OUTPUT_HANDLE);
/*     */     Kernel32.CONSOLE_SCREEN_BUFFER_INFO info = new Kernel32.CONSOLE_SCREEN_BUFFER_INFO();
/*     */     Kernel32.GetConsoleScreenBufferInfo(outputHandle, info);
/* 186 */     return new Size(info.size.x, info.size.y); } public Cursor getCursorPosition(IntConsumer discarded) { Kernel32.CONSOLE_SCREEN_BUFFER_INFO info = new Kernel32.CONSOLE_SCREEN_BUFFER_INFO();
/* 187 */     long console = Kernel32.GetStdHandle(Kernel32.STD_OUTPUT_HANDLE);
/* 188 */     if (Kernel32.GetConsoleScreenBufferInfo(console, info) == 0) {
/* 189 */       throw new IOError(new IOException("Could not get the cursor position: " + WindowsSupport.getLastErrorMessage()));
/*     */     }
/* 191 */     return new Cursor(info.cursorPosition.x, info.cursorPosition.y); }
/*     */   protected boolean processConsoleInput() throws IOException { Kernel32.INPUT_RECORD[] events; if (JansiSupportImpl.isAtLeast(1, 17)) { events = WindowsSupport.readConsoleInput(1, 100); } else { events = WindowsSupport.readConsoleInput(1); }  if (events == null)
/*     */       return false;  boolean flush = false; for (Kernel32.INPUT_RECORD event : events) { if (event.eventType == Kernel32.INPUT_RECORD.KEY_EVENT) { Kernel32.KEY_EVENT_RECORD keyEvent = event.keyEvent; processKeyEvent(keyEvent.keyDown, keyEvent.keyCode, keyEvent.uchar, keyEvent.controlKeyState); flush = true; } else if (event.eventType == Kernel32.INPUT_RECORD.WINDOW_BUFFER_SIZE_EVENT) { raise(Terminal.Signal.WINCH); } else if (event.eventType == Kernel32.INPUT_RECORD.MOUSE_EVENT) { processMouseEvent(event.mouseEvent); flush = true; } else if (event.eventType == Kernel32.INPUT_RECORD.FOCUS_EVENT) { processFocusEvent(event.focusEvent.setFocus); }  }  return flush; }
/*     */   private void processFocusEvent(boolean hasFocus) throws IOException { if (this.focusTracking) { this.focus[2] = hasFocus ? 'I' : 'O'; this.slaveInputPipe.write(this.focus); }
/* 195 */      } public void disableScrolling() { this.strings.remove(InfoCmp.Capability.insert_line);
/* 196 */     this.strings.remove(InfoCmp.Capability.parm_insert_line);
/* 197 */     this.strings.remove(InfoCmp.Capability.delete_line);
/* 198 */     this.strings.remove(InfoCmp.Capability.parm_delete_line); }
/*     */ 
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\terminal\impl\jansi\win\JansiWinSysTerminal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */