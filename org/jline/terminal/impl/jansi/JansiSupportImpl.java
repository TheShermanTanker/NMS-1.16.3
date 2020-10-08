/*     */ package org.jline.terminal.impl.jansi;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.Properties;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.fusesource.jansi.Ansi;
/*     */ import org.jline.terminal.Attributes;
/*     */ import org.jline.terminal.Size;
/*     */ import org.jline.terminal.Terminal;
/*     */ import org.jline.terminal.impl.jansi.freebsd.FreeBsdNativePty;
/*     */ import org.jline.terminal.impl.jansi.linux.LinuxNativePty;
/*     */ import org.jline.terminal.impl.jansi.osx.OsXNativePty;
/*     */ import org.jline.terminal.impl.jansi.win.JansiWinSysTerminal;
/*     */ import org.jline.terminal.spi.JansiSupport;
/*     */ import org.jline.terminal.spi.Pty;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JansiSupportImpl
/*     */   implements JansiSupport
/*     */ {
/*     */   static final int JANSI_MAJOR_VERSION;
/*     */   static final int JANSI_MINOR_VERSION;
/*     */   
/*     */   static {
/*  34 */     int major = 0, minor = 0;
/*     */     try {
/*  36 */       String v = null;
/*  37 */       try (InputStream is = Ansi.class.getResourceAsStream("jansi.properties")) {
/*  38 */         if (is != null) {
/*  39 */           Properties props = new Properties();
/*  40 */           props.load(is);
/*  41 */           v = props.getProperty("version");
/*     */         } 
/*  43 */       } catch (IOException iOException) {}
/*     */ 
/*     */       
/*  46 */       if (v == null) {
/*  47 */         v = Ansi.class.getPackage().getImplementationVersion();
/*     */       }
/*  49 */       if (v != null) {
/*  50 */         Matcher m = Pattern.compile("([0-9]+)\\.([0-9]+)([\\.-]\\S+)?").matcher(v);
/*  51 */         if (m.matches()) {
/*  52 */           major = Integer.parseInt(m.group(1));
/*  53 */           minor = Integer.parseInt(m.group(2));
/*     */         } 
/*     */       } 
/*  56 */     } catch (Throwable throwable) {}
/*     */ 
/*     */     
/*  59 */     JANSI_MAJOR_VERSION = major;
/*  60 */     JANSI_MINOR_VERSION = minor;
/*     */   }
/*     */   
/*     */   public static int getJansiMajorVersion() {
/*  64 */     return JANSI_MAJOR_VERSION;
/*     */   }
/*     */   
/*     */   public static int getJansiMinorVersion() {
/*  68 */     return JANSI_MINOR_VERSION;
/*     */   }
/*     */   
/*     */   public static boolean isAtLeast(int major, int minor) {
/*  72 */     return (JANSI_MAJOR_VERSION > major || (JANSI_MAJOR_VERSION == major && JANSI_MINOR_VERSION >= minor));
/*     */   }
/*     */ 
/*     */   
/*     */   public Pty current() throws IOException {
/*  77 */     String osName = System.getProperty("os.name");
/*  78 */     if (osName.startsWith("Linux")) {
/*  79 */       if (isAtLeast(1, 16)) {
/*  80 */         return (Pty)LinuxNativePty.current();
/*     */       }
/*     */     }
/*  83 */     else if (osName.startsWith("Mac") || osName.startsWith("Darwin")) {
/*  84 */       if (isAtLeast(1, 12)) {
/*  85 */         return (Pty)OsXNativePty.current();
/*     */       }
/*     */     }
/*  88 */     else if (!osName.startsWith("Solaris") && !osName.startsWith("SunOS")) {
/*     */ 
/*     */ 
/*     */       
/*  92 */       if (osName.startsWith("FreeBSD") && 
/*  93 */         isAtLeast(1, 16)) {
/*  94 */         return (Pty)FreeBsdNativePty.current();
/*     */       }
/*     */     } 
/*  97 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public Pty open(Attributes attributes, Size size) throws IOException {
/* 102 */     if (isAtLeast(1, 16)) {
/* 103 */       String osName = System.getProperty("os.name");
/* 104 */       if (osName.startsWith("Linux")) {
/* 105 */         return (Pty)LinuxNativePty.open(attributes, size);
/*     */       }
/* 107 */       if (osName.startsWith("Mac") || osName.startsWith("Darwin")) {
/* 108 */         return (Pty)OsXNativePty.open(attributes, size);
/*     */       }
/* 110 */       if (!osName.startsWith("Solaris") && !osName.startsWith("SunOS"))
/*     */       {
/*     */ 
/*     */         
/* 114 */         if (osName.startsWith("FreeBSD"))
/* 115 */           return (Pty)FreeBsdNativePty.open(attributes, size); 
/*     */       }
/*     */     } 
/* 118 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public Terminal winSysTerminal(String name, String type, boolean ansiPassThrough, Charset encoding, int codepage, boolean nativeSignals, Terminal.SignalHandler signalHandler) throws IOException {
/* 123 */     return winSysTerminal(name, type, ansiPassThrough, encoding, codepage, nativeSignals, signalHandler, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public Terminal winSysTerminal(String name, String type, boolean ansiPassThrough, Charset encoding, int codepage, boolean nativeSignals, Terminal.SignalHandler signalHandler, boolean paused) throws IOException {
/* 128 */     if (isAtLeast(1, 12)) {
/* 129 */       JansiWinSysTerminal terminal = JansiWinSysTerminal.createTerminal(name, type, ansiPassThrough, encoding, codepage, nativeSignals, signalHandler, paused);
/* 130 */       if (!isAtLeast(1, 16)) {
/* 131 */         terminal.disableScrolling();
/*     */       }
/* 133 */       return (Terminal)terminal;
/*     */     } 
/* 135 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\terminal\impl\jansi\JansiSupportImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */