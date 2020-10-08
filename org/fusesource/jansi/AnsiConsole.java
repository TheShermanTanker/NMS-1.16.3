/*     */ package org.fusesource.jansi;
/*     */ 
/*     */ import java.io.FilterOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Locale;
/*     */ import org.fusesource.jansi.internal.CLibrary;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AnsiConsole
/*     */ {
/*  43 */   public static final PrintStream system_out = System.out;
/*     */   
/*     */   public static final PrintStream out;
/*  46 */   public static final PrintStream system_err = System.err;
/*     */   
/*     */   public static final PrintStream err;
/*  49 */   static final boolean IS_WINDOWS = System.getProperty("os.name").toLowerCase(Locale.ENGLISH).contains("win");
/*     */   
/*  51 */   static final boolean IS_CYGWIN = (IS_WINDOWS && 
/*  52 */     System.getenv("PWD") != null && 
/*  53 */     System.getenv("PWD").startsWith("/") && 
/*  54 */     !"cygwin".equals(System.getenv("TERM")));
/*     */   
/*  56 */   static final boolean IS_MINGW_XTERM = (IS_WINDOWS && 
/*  57 */     System.getenv("MSYSTEM") != null && 
/*  58 */     System.getenv("MSYSTEM").startsWith("MINGW") && "xterm"
/*  59 */     .equals(System.getenv("TERM")));
/*     */   
/*     */   private static JansiOutputType jansiOutputType;
/*     */   static final JansiOutputType JANSI_STDOUT_TYPE;
/*     */   
/*     */   static {
/*  65 */     out = wrapSystemOut(system_out);
/*  66 */     JANSI_STDOUT_TYPE = jansiOutputType;
/*  67 */     err = wrapSystemErr(system_err);
/*  68 */     JANSI_STDERR_TYPE = jansiOutputType;
/*     */   }
/*     */ 
/*     */   
/*     */   static final JansiOutputType JANSI_STDERR_TYPE;
/*     */   
/*     */   private static int installed;
/*     */   
/*     */   @Deprecated
/*     */   public static OutputStream wrapOutputStream(OutputStream stream) {
/*     */     try {
/*  79 */       return wrapOutputStream(stream, CLibrary.STDOUT_FILENO);
/*  80 */     } catch (Throwable ignore) {
/*  81 */       return wrapOutputStream(stream, 1);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static PrintStream wrapSystemOut(PrintStream ps) {
/*     */     try {
/*  87 */       return wrapPrintStream(ps, CLibrary.STDOUT_FILENO);
/*  88 */     } catch (Throwable ignore) {
/*  89 */       return wrapPrintStream(ps, 1);
/*     */     } 
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static OutputStream wrapErrorOutputStream(OutputStream stream) {
/*     */     try {
/*  96 */       return wrapOutputStream(stream, CLibrary.STDERR_FILENO);
/*  97 */     } catch (Throwable ignore) {
/*  98 */       return wrapOutputStream(stream, 2);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static PrintStream wrapSystemErr(PrintStream ps) {
/*     */     try {
/* 104 */       return wrapPrintStream(ps, CLibrary.STDERR_FILENO);
/* 105 */     } catch (Throwable ignore) {
/* 106 */       return wrapPrintStream(ps, 2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static OutputStream wrapOutputStream(OutputStream stream, int fileno) {
/* 115 */     if (Boolean.getBoolean("jansi.passthrough")) {
/* 116 */       jansiOutputType = JansiOutputType.PASSTHROUGH;
/* 117 */       return stream;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 122 */     if (Boolean.getBoolean("jansi.strip")) {
/* 123 */       jansiOutputType = JansiOutputType.STRIP_ANSI;
/* 124 */       return new AnsiOutputStream(stream);
/*     */     } 
/*     */     
/* 127 */     if (IS_WINDOWS && !IS_CYGWIN && !IS_MINGW_XTERM) {
/*     */       
/*     */       try {
/*     */         
/* 131 */         jansiOutputType = JansiOutputType.WINDOWS;
/* 132 */         return new WindowsAnsiOutputStream(stream, (fileno == CLibrary.STDOUT_FILENO));
/* 133 */       } catch (Throwable throwable) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 139 */         jansiOutputType = JansiOutputType.STRIP_ANSI;
/* 140 */         return new AnsiOutputStream(stream);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 147 */       boolean forceColored = Boolean.getBoolean("jansi.force");
/*     */ 
/*     */       
/* 150 */       if (!forceColored && CLibrary.isatty(fileno) == 0) {
/* 151 */         jansiOutputType = JansiOutputType.STRIP_ANSI;
/* 152 */         return new AnsiOutputStream(stream);
/*     */       } 
/* 154 */     } catch (Throwable throwable) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 162 */     jansiOutputType = JansiOutputType.RESET_ANSI_AT_CLOSE;
/* 163 */     return new FilterOutputStream(stream)
/*     */       {
/*     */         public void close() throws IOException {
/* 166 */           write(AnsiOutputStream.RESET_CODE);
/* 167 */           flush();
/* 168 */           super.close();
/*     */         }
/*     */       };
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static PrintStream wrapPrintStream(PrintStream ps, int fileno) {
/* 193 */     if (Boolean.getBoolean("jansi.passthrough")) {
/* 194 */       jansiOutputType = JansiOutputType.PASSTHROUGH;
/* 195 */       return ps;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 200 */     if (Boolean.getBoolean("jansi.strip")) {
/* 201 */       jansiOutputType = JansiOutputType.STRIP_ANSI;
/* 202 */       return new AnsiPrintStream(ps);
/*     */     } 
/*     */     
/* 205 */     if (IS_WINDOWS && !IS_CYGWIN && !IS_MINGW_XTERM) {
/*     */       
/*     */       try {
/*     */         
/* 209 */         jansiOutputType = JansiOutputType.WINDOWS;
/* 210 */         return new WindowsAnsiPrintStream(ps, (fileno == CLibrary.STDOUT_FILENO));
/* 211 */       } catch (Throwable throwable) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 217 */         jansiOutputType = JansiOutputType.STRIP_ANSI;
/* 218 */         return new AnsiPrintStream(ps);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 225 */       boolean forceColored = Boolean.getBoolean("jansi.force");
/*     */ 
/*     */       
/* 228 */       if (!forceColored && CLibrary.isatty(fileno) == 0) {
/* 229 */         jansiOutputType = JansiOutputType.STRIP_ANSI;
/* 230 */         return new AnsiPrintStream(ps);
/*     */       } 
/* 232 */     } catch (Throwable throwable) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 240 */     jansiOutputType = JansiOutputType.RESET_ANSI_AT_CLOSE;
/* 241 */     return new FilterPrintStream(ps)
/*     */       {
/*     */         public void close() {
/* 244 */           this.ps.print("\033[0m");
/* 245 */           this.ps.flush();
/* 246 */           super.close();
/*     */         }
/*     */       };
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
/*     */   public static PrintStream out() {
/* 261 */     return out;
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
/*     */   public static PrintStream err() {
/* 274 */     return err;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized void systemInstall() {
/* 283 */     installed++;
/* 284 */     if (installed == 1) {
/* 285 */       System.setOut(out);
/* 286 */       System.setErr(err);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized void systemUninstall() {
/* 296 */     installed--;
/* 297 */     if (installed == 0) {
/* 298 */       System.setOut(system_out);
/* 299 */       System.setErr(system_err);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   enum JansiOutputType
/*     */   {
/* 307 */     PASSTHROUGH("just pass through, ANSI escape codes are supposed to be supported by terminal"),
/* 308 */     RESET_ANSI_AT_CLOSE("like pass through but reset ANSI attributes when closing the stream"),
/* 309 */     STRIP_ANSI("strip ANSI escape codes, for example when output is not a terminal"),
/* 310 */     WINDOWS("detect ANSI escape codes and transform Jansi-supported ones into a Windows API to get desired effect (since ANSI escape codes are not natively supported by Windows terminals like cmd.exe or PowerShell)");
/*     */     
/*     */     private final String description;
/*     */ 
/*     */     
/*     */     JansiOutputType(String description) {
/* 316 */       this.description = description;
/*     */     }
/*     */     
/*     */     String getDescription() {
/* 320 */       return this.description;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\fusesource\jansi\AnsiConsole.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */