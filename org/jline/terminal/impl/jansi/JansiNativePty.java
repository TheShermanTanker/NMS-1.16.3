/*     */ package org.jline.terminal.impl.jansi;
/*     */ 
/*     */ import java.io.FileDescriptor;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.lang.reflect.Constructor;
/*     */ import org.fusesource.jansi.internal.CLibrary;
/*     */ import org.jline.terminal.Attributes;
/*     */ import org.jline.terminal.Size;
/*     */ import org.jline.terminal.impl.AbstractPty;
/*     */ import org.jline.terminal.spi.Pty;
/*     */ import org.jline.utils.ExecHelper;
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
/*     */ public abstract class JansiNativePty
/*     */   extends AbstractPty
/*     */   implements Pty
/*     */ {
/*     */   private final int master;
/*     */   private final int slave;
/*     */   private final int slaveOut;
/*     */   private final String name;
/*     */   private final FileDescriptor masterFD;
/*     */   private final FileDescriptor slaveFD;
/*     */   private final FileDescriptor slaveOutFD;
/*     */   
/*     */   public JansiNativePty(int master, FileDescriptor masterFD, int slave, FileDescriptor slaveFD, String name) {
/*  42 */     this(master, masterFD, slave, slaveFD, slave, slaveFD, name);
/*     */   }
/*     */   
/*     */   public JansiNativePty(int master, FileDescriptor masterFD, int slave, FileDescriptor slaveFD, int slaveOut, FileDescriptor slaveOutFD, String name) {
/*  46 */     this.master = master;
/*  47 */     this.slave = slave;
/*  48 */     this.slaveOut = slaveOut;
/*  49 */     this.name = name;
/*  50 */     this.masterFD = masterFD;
/*  51 */     this.slaveFD = slaveFD;
/*  52 */     this.slaveOutFD = slaveOutFD;
/*     */   }
/*     */   
/*     */   protected static String ttyname() throws IOException {
/*     */     String name;
/*  57 */     if (JansiSupportImpl.JANSI_MAJOR_VERSION > 1 || (JansiSupportImpl.JANSI_MAJOR_VERSION == 1 && JansiSupportImpl.JANSI_MINOR_VERSION >= 16)) {
/*  58 */       name = CLibrary.ttyname(0);
/*     */     } else {
/*     */       try {
/*  61 */         name = ExecHelper.exec(true, new String[] { OSUtils.TTY_COMMAND });
/*  62 */       } catch (IOException e) {
/*  63 */         throw new IOException("Not a tty", e);
/*     */       } 
/*     */     } 
/*  66 */     if (name != null) {
/*  67 */       name = name.trim();
/*     */     }
/*  69 */     if (name == null || name.isEmpty()) {
/*  70 */       throw new IOException("Not a tty");
/*     */     }
/*  72 */     return name;
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/*  77 */     if (this.master > 0) {
/*  78 */       getMasterInput().close();
/*     */     }
/*  80 */     if (this.slave > 0) {
/*  81 */       getSlaveInput().close();
/*     */     }
/*     */   }
/*     */   
/*     */   public int getMaster() {
/*  86 */     return this.master;
/*     */   }
/*     */   
/*     */   public int getSlave() {
/*  90 */     return this.slave;
/*     */   }
/*     */   
/*     */   public int getSlaveOut() {
/*  94 */     return this.slaveOut;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  98 */     return this.name;
/*     */   }
/*     */   
/*     */   public FileDescriptor getMasterFD() {
/* 102 */     return this.masterFD;
/*     */   }
/*     */   
/*     */   public FileDescriptor getSlaveFD() {
/* 106 */     return this.slaveFD;
/*     */   }
/*     */   
/*     */   public FileDescriptor getSlaveOutFD() {
/* 110 */     return this.slaveOutFD;
/*     */   }
/*     */   
/*     */   public InputStream getMasterInput() {
/* 114 */     return new FileInputStream(getMasterFD());
/*     */   }
/*     */   
/*     */   public OutputStream getMasterOutput() {
/* 118 */     return new FileOutputStream(getMasterFD());
/*     */   }
/*     */   
/*     */   protected InputStream doGetSlaveInput() {
/* 122 */     return new FileInputStream(getSlaveFD());
/*     */   }
/*     */   
/*     */   public OutputStream getSlaveOutput() {
/* 126 */     return new FileOutputStream(getSlaveOutFD());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Attributes getAttr() throws IOException {
/* 132 */     CLibrary.Termios tios = new CLibrary.Termios();
/* 133 */     CLibrary.tcgetattr(this.slave, tios);
/* 134 */     return toAttributes(tios);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void doSetAttr(Attributes attr) throws IOException {
/* 139 */     CLibrary.Termios tios = toTermios(attr);
/* 140 */     CLibrary.tcsetattr(this.slave, CLibrary.TCSANOW, tios);
/*     */   }
/*     */ 
/*     */   
/*     */   public Size getSize() throws IOException {
/* 145 */     CLibrary.WinSize sz = new CLibrary.WinSize();
/* 146 */     CLibrary.ioctl(this.slave, CLibrary.TIOCGWINSZ, sz);
/* 147 */     return new Size(sz.ws_col, sz.ws_row);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(Size size) throws IOException {
/* 152 */     CLibrary.WinSize sz = new CLibrary.WinSize((short)size.getRows(), (short)size.getColumns());
/* 153 */     CLibrary.ioctl(this.slave, CLibrary.TIOCSWINSZ, sz);
/*     */   }
/*     */ 
/*     */   
/*     */   protected abstract CLibrary.Termios toTermios(Attributes paramAttributes);
/*     */   
/*     */   protected abstract Attributes toAttributes(CLibrary.Termios paramTermios);
/*     */   
/*     */   public String toString() {
/* 162 */     return "JansiNativePty[" + getName() + "]";
/*     */   }
/*     */   
/*     */   protected static FileDescriptor newDescriptor(int fd) {
/*     */     try {
/* 167 */       Constructor<FileDescriptor> cns = FileDescriptor.class.getDeclaredConstructor(new Class[] { int.class });
/* 168 */       cns.setAccessible(true);
/* 169 */       return cns.newInstance(new Object[] { Integer.valueOf(fd) });
/* 170 */     } catch (Throwable e) {
/* 171 */       throw new RuntimeException("Unable to create FileDescriptor", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\terminal\impl\jansi\JansiNativePty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */