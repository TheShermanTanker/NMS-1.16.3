/*     */ package org.jline.terminal.impl;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.PrintWriter;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.Objects;
/*     */ import org.jline.terminal.Attributes;
/*     */ import org.jline.terminal.Size;
/*     */ import org.jline.terminal.Terminal;
/*     */ import org.jline.utils.NonBlocking;
/*     */ import org.jline.utils.NonBlockingPumpInputStream;
/*     */ import org.jline.utils.NonBlockingReader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LineDisciplineTerminal
/*     */   extends AbstractTerminal
/*     */ {
/*     */   private static final String DEFAULT_TERMINAL_ATTRIBUTES = "speed 9600 baud; 24 rows; 80 columns;\nlflags: icanon isig iexten echo echoe -echok echoke -echonl echoctl\n\t-echoprt -altwerase -noflsh -tostop -flusho pendin -nokerninfo\n\t-extproc\niflags: -istrip icrnl -inlcr -igncr ixon -ixoff ixany imaxbel iutf8\n\t-ignbrk brkint -inpck -ignpar -parmrk\noflags: opost onlcr -oxtabs -onocr -onlret\ncflags: cread cs8 -parenb -parodd hupcl -clocal -cstopb -crtscts -dsrflow\n\t-dtrflow -mdmbuf\ncchars: discard = ^O; dsusp = ^Y; eof = ^D; eol = <undef>;\n\teol2 = <undef>; erase = ^?; intr = ^C; kill = ^U; lnext = ^V;\n\tmin = 1; quit = ^\\; reprint = ^R; start = ^Q; status = ^T;\n\tstop = ^S; susp = ^Z; time = 0; werase = ^W;\n";
/*     */   private static final int PIPE_SIZE = 1024;
/*     */   protected final OutputStream masterOutput;
/*     */   protected final OutputStream slaveInputPipe;
/*     */   protected final NonBlockingPumpInputStream slaveInput;
/*     */   protected final NonBlockingReader slaveReader;
/*     */   protected final PrintWriter slaveWriter;
/*     */   protected final OutputStream slaveOutput;
/*     */   protected final Attributes attributes;
/*     */   protected final Size size;
/*     */   
/*     */   public LineDisciplineTerminal(String name, String type, OutputStream masterOutput, Charset encoding) throws IOException {
/*  93 */     this(name, type, masterOutput, encoding, Terminal.SignalHandler.SIG_DFL);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LineDisciplineTerminal(String name, String type, OutputStream masterOutput, Charset encoding, Terminal.SignalHandler signalHandler) throws IOException {
/* 101 */     super(name, type, encoding, signalHandler);
/* 102 */     NonBlockingPumpInputStream input = NonBlocking.nonBlockingPumpInputStream(1024);
/* 103 */     this.slaveInputPipe = input.getOutputStream();
/* 104 */     this.slaveInput = input;
/* 105 */     this.slaveReader = NonBlocking.nonBlocking(getName(), (InputStream)this.slaveInput, encoding());
/* 106 */     this.slaveOutput = new FilteringOutputStream();
/* 107 */     this.slaveWriter = new PrintWriter(new OutputStreamWriter(this.slaveOutput, encoding()));
/* 108 */     this.masterOutput = masterOutput;
/* 109 */     this.attributes = ExecPty.doGetAttr("speed 9600 baud; 24 rows; 80 columns;\nlflags: icanon isig iexten echo echoe -echok echoke -echonl echoctl\n\t-echoprt -altwerase -noflsh -tostop -flusho pendin -nokerninfo\n\t-extproc\niflags: -istrip icrnl -inlcr -igncr ixon -ixoff ixany imaxbel iutf8\n\t-ignbrk brkint -inpck -ignpar -parmrk\noflags: opost onlcr -oxtabs -onocr -onlret\ncflags: cread cs8 -parenb -parodd hupcl -clocal -cstopb -crtscts -dsrflow\n\t-dtrflow -mdmbuf\ncchars: discard = ^O; dsusp = ^Y; eof = ^D; eol = <undef>;\n\teol2 = <undef>; erase = ^?; intr = ^C; kill = ^U; lnext = ^V;\n\tmin = 1; quit = ^\\; reprint = ^R; start = ^Q; status = ^T;\n\tstop = ^S; susp = ^Z; time = 0; werase = ^W;\n");
/* 110 */     this.size = new Size(160, 50);
/* 111 */     parseInfoCmp();
/*     */   }
/*     */   
/*     */   public NonBlockingReader reader() {
/* 115 */     return this.slaveReader;
/*     */   }
/*     */   
/*     */   public PrintWriter writer() {
/* 119 */     return this.slaveWriter;
/*     */   }
/*     */ 
/*     */   
/*     */   public InputStream input() {
/* 124 */     return (InputStream)this.slaveInput;
/*     */   }
/*     */ 
/*     */   
/*     */   public OutputStream output() {
/* 129 */     return this.slaveOutput;
/*     */   }
/*     */   
/*     */   public Attributes getAttributes() {
/* 133 */     Attributes attr = new Attributes();
/* 134 */     attr.copy(this.attributes);
/* 135 */     return attr;
/*     */   }
/*     */   
/*     */   public void setAttributes(Attributes attr) {
/* 139 */     this.attributes.copy(attr);
/*     */   }
/*     */   
/*     */   public Size getSize() {
/* 143 */     Size sz = new Size();
/* 144 */     sz.copy(this.size);
/* 145 */     return sz;
/*     */   }
/*     */   
/*     */   public void setSize(Size sz) {
/* 149 */     this.size.copy(sz);
/*     */   }
/*     */ 
/*     */   
/*     */   public void raise(Terminal.Signal signal) {
/* 154 */     Objects.requireNonNull(signal);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 167 */     echoSignal(signal);
/* 168 */     super.raise(signal);
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
/*     */   public void processInputByte(int c) throws IOException {
/* 180 */     boolean flushOut = doProcessInputByte(c);
/* 181 */     this.slaveInputPipe.flush();
/* 182 */     if (flushOut) {
/* 183 */       this.masterOutput.flush();
/*     */     }
/*     */   }
/*     */   
/*     */   public void processInputBytes(byte[] input) throws IOException {
/* 188 */     processInputBytes(input, 0, input.length);
/*     */   }
/*     */   
/*     */   public void processInputBytes(byte[] input, int offset, int length) throws IOException {
/* 192 */     boolean flushOut = false;
/* 193 */     for (int i = 0; i < length; i++) {
/* 194 */       flushOut |= doProcessInputByte(input[offset + i]);
/*     */     }
/* 196 */     this.slaveInputPipe.flush();
/* 197 */     if (flushOut) {
/* 198 */       this.masterOutput.flush();
/*     */     }
/*     */   }
/*     */   
/*     */   protected boolean doProcessInputByte(int c) throws IOException {
/* 203 */     if (this.attributes.getLocalFlag(Attributes.LocalFlag.ISIG)) {
/* 204 */       if (c == this.attributes.getControlChar(Attributes.ControlChar.VINTR)) {
/* 205 */         raise(Terminal.Signal.INT);
/* 206 */         return false;
/* 207 */       }  if (c == this.attributes.getControlChar(Attributes.ControlChar.VQUIT)) {
/* 208 */         raise(Terminal.Signal.QUIT);
/* 209 */         return false;
/* 210 */       }  if (c == this.attributes.getControlChar(Attributes.ControlChar.VSUSP)) {
/* 211 */         raise(Terminal.Signal.TSTP);
/* 212 */         return false;
/* 213 */       }  if (c == this.attributes.getControlChar(Attributes.ControlChar.VSTATUS)) {
/* 214 */         raise(Terminal.Signal.INFO);
/*     */       }
/*     */     } 
/* 217 */     if (c == 13) {
/* 218 */       if (this.attributes.getInputFlag(Attributes.InputFlag.IGNCR)) {
/* 219 */         return false;
/*     */       }
/* 221 */       if (this.attributes.getInputFlag(Attributes.InputFlag.ICRNL)) {
/* 222 */         c = 10;
/*     */       }
/* 224 */     } else if (c == 10 && this.attributes.getInputFlag(Attributes.InputFlag.INLCR)) {
/* 225 */       c = 13;
/*     */     } 
/* 227 */     boolean flushOut = false;
/* 228 */     if (this.attributes.getLocalFlag(Attributes.LocalFlag.ECHO)) {
/* 229 */       processOutputByte(c);
/* 230 */       flushOut = true;
/*     */     } 
/* 232 */     this.slaveInputPipe.write(c);
/* 233 */     return flushOut;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void processOutputByte(int c) throws IOException {
/* 244 */     if (this.attributes.getOutputFlag(Attributes.OutputFlag.OPOST) && 
/* 245 */       c == 10 && 
/* 246 */       this.attributes.getOutputFlag(Attributes.OutputFlag.ONLCR)) {
/* 247 */       this.masterOutput.write(13);
/* 248 */       this.masterOutput.write(10);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 253 */     this.masterOutput.write(c);
/*     */   }
/*     */   
/*     */   protected void processIOException(IOException ioException) {
/* 257 */     this.slaveInput.setIoException(ioException);
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 261 */     super.close();
/*     */     try {
/* 263 */       this.slaveReader.close();
/*     */     } finally {
/*     */       try {
/* 266 */         this.slaveInputPipe.close();
/*     */       }
/*     */       finally {
/*     */         
/* 270 */         this.slaveWriter.close();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private class FilteringOutputStream extends OutputStream {
/*     */     private FilteringOutputStream() {}
/*     */     
/*     */     public void write(int b) throws IOException {
/* 279 */       LineDisciplineTerminal.this.processOutputByte(b);
/* 280 */       flush();
/*     */     }
/*     */ 
/*     */     
/*     */     public void write(byte[] b, int off, int len) throws IOException {
/* 285 */       if (b == null)
/* 286 */         throw new NullPointerException(); 
/* 287 */       if (off < 0 || off > b.length || len < 0 || off + len > b.length || off + len < 0)
/*     */       {
/* 289 */         throw new IndexOutOfBoundsException(); } 
/* 290 */       if (len == 0) {
/*     */         return;
/*     */       }
/* 293 */       for (int i = 0; i < len; i++) {
/* 294 */         LineDisciplineTerminal.this.processOutputByte(b[off + i]);
/*     */       }
/* 296 */       flush();
/*     */     }
/*     */ 
/*     */     
/*     */     public void flush() throws IOException {
/* 301 */       LineDisciplineTerminal.this.masterOutput.flush();
/*     */     }
/*     */ 
/*     */     
/*     */     public void close() throws IOException {
/* 306 */       LineDisciplineTerminal.this.masterOutput.close();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\terminal\impl\LineDisciplineTerminal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */