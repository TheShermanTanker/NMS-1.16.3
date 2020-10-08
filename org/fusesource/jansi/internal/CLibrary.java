/*     */ package org.fusesource.jansi.internal;@JniClass
/*     */ public class CLibrary { @JniMethod(flags = {MethodFlag.CONSTANT_INITIALIZER})
/*     */   private static native void init();
/*     */   @JniMethod(conditional = "FALSE")
/*     */   public static native int isatty(@JniArg int paramInt);
/*     */   
/*     */   @JniMethod(conditional = "FALSE")
/*     */   public static native String ttyname(@JniArg int paramInt);
/*     */   
/*     */   @JniMethod(conditional = "defined(HAVE_OPENPTY)")
/*     */   public static native int openpty(@JniArg(cast = "int *", flags = {ArgFlag.NO_IN}) int[] paramArrayOfint1, @JniArg(cast = "int *", flags = {ArgFlag.NO_IN}) int[] paramArrayOfint2, @JniArg(cast = "char *", flags = {ArgFlag.NO_IN}) byte[] paramArrayOfbyte, @JniArg(cast = "struct termios *", flags = {ArgFlag.NO_OUT}) Termios paramTermios, @JniArg(cast = "struct winsize *", flags = {ArgFlag.NO_OUT}) WinSize paramWinSize);
/*     */   
/*     */   @JniMethod(conditional = "defined(HAVE_TCGETATTR)")
/*     */   public static native int tcgetattr(@JniArg int paramInt, @JniArg(cast = "struct termios *", flags = {ArgFlag.NO_IN}) Termios paramTermios);
/*     */   
/*     */   @JniMethod(conditional = "defined(HAVE_TCSETATTR)")
/*     */   public static native int tcsetattr(@JniArg int paramInt1, @JniArg int paramInt2, @JniArg(cast = "struct termios *", flags = {ArgFlag.NO_OUT}) Termios paramTermios);
/*     */   
/*     */   @JniMethod(conditional = "defined(HAVE_IOCTL)")
/*     */   public static native int ioctl(@JniArg int paramInt, @JniArg long paramLong, @JniArg int[] paramArrayOfint);
/*     */   
/*     */   @JniMethod(conditional = "defined(HAVE_IOCTL)")
/*     */   public static native int ioctl(@JniArg int paramInt, @JniArg long paramLong, @JniArg(flags = {ArgFlag.POINTER_ARG}) WinSize paramWinSize);
/*     */   
/*     */   @JniField(flags = {FieldFlag.CONSTANT}, conditional = "defined(STDIN_FILENO)")
/*     */   public static int STDIN_FILENO;
/*     */   @JniField(flags = {FieldFlag.CONSTANT}, conditional = "defined(STDOUT_FILENO)")
/*     */   public static int STDOUT_FILENO;
/*     */   @JniField(flags = {FieldFlag.CONSTANT}, conditional = "defined(STDERR_FILENO)")
/*     */   public static int STDERR_FILENO;
/*     */   @JniField(flags = {FieldFlag.CONSTANT}, accessor = "1", conditional = "defined(HAVE_ISATTY)")
/*     */   public static boolean HAVE_ISATTY;
/*     */   @JniField(flags = {FieldFlag.CONSTANT}, accessor = "1", conditional = "defined(HAVE_TTYNAME)")
/*     */   public static boolean HAVE_TTYNAME;
/*     */   @JniField(flags = {FieldFlag.CONSTANT}, conditional = "defined(TCSANOW)")
/*     */   public static int TCSANOW;
/*     */   @JniField(flags = {FieldFlag.CONSTANT}, conditional = "defined(TCSADRAIN)")
/*     */   public static int TCSADRAIN;
/*  39 */   private static final Library LIBRARY = new Library("jansi", CLibrary.class); @JniField(flags = {FieldFlag.CONSTANT}, conditional = "defined(TCSAFLUSH)") public static int TCSAFLUSH; @JniField(flags = {FieldFlag.CONSTANT}, conditional = "defined(TIOCGETA)") public static long TIOCGETA; @JniField(flags = {FieldFlag.CONSTANT}, conditional = "defined(TIOCSETA)") public static long TIOCSETA; @JniField(flags = {FieldFlag.CONSTANT}, conditional = "defined(TIOCGETD)") public static long TIOCGETD; @JniField(flags = {FieldFlag.CONSTANT}, conditional = "defined(TIOCSETD)") public static long TIOCSETD; @JniField(flags = {FieldFlag.CONSTANT}, conditional = "defined(TIOCGWINSZ)")
/*     */   public static long TIOCGWINSZ; @JniField(flags = {FieldFlag.CONSTANT}, conditional = "defined(TIOCSWINSZ)")
/*  41 */   public static long TIOCSWINSZ; static { LIBRARY.load();
/*  42 */     init(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @JniClass(flags = {ClassFlag.STRUCT}, name = "winsize", conditional = "defined(HAVE_IOCTL)")
/*     */   public static class WinSize
/*     */   {
/*     */     @JniField(flags = {FieldFlag.CONSTANT}, accessor = "sizeof(struct winsize)")
/*     */     public static int SIZEOF;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @JniField(accessor = "ws_row")
/*     */     public short ws_row;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @JniField(accessor = "ws_col")
/*     */     public short ws_col;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @JniField(accessor = "ws_xpixel")
/*     */     public short ws_xpixel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @JniField(accessor = "ws_ypixel")
/*     */     public short ws_ypixel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     static {
/* 167 */       CLibrary.LIBRARY.load();
/* 168 */       init();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public WinSize() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public WinSize(short ws_row, short ws_col) {
/* 189 */       this.ws_row = ws_row;
/* 190 */       this.ws_col = ws_col;
/*     */     }
/*     */     @JniMethod(flags = {MethodFlag.CONSTANT_INITIALIZER})
/*     */     private static native void init(); }
/*     */   @JniClass(flags = {ClassFlag.STRUCT}, name = "termios", conditional = "defined(HAVE_IOCTL)")
/*     */   public static class Termios { @JniField(flags = {FieldFlag.CONSTANT}, accessor = "sizeof(struct termios)")
/*     */     public static int SIZEOF; @JniField(accessor = "c_iflag")
/*     */     public long c_iflag; @JniField(accessor = "c_oflag")
/*     */     public long c_oflag; @JniField(accessor = "c_cflag")
/*     */     public long c_cflag; @JniField(accessor = "c_lflag")
/*     */     public long c_lflag;
/*     */     @JniMethod(flags = {MethodFlag.CONSTANT_INITIALIZER})
/*     */     private static native void init();
/*     */     static {
/* 204 */       CLibrary.LIBRARY.load();
/* 205 */       init();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @JniField(accessor = "c_cc")
/* 221 */     public byte[] c_cc = new byte[32];
/*     */     @JniField(accessor = "c_ispeed")
/*     */     public long c_ispeed;
/*     */     @JniField(accessor = "c_ospeed")
/*     */     public long c_ospeed; }
/*     */    }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\fusesource\jansi\internal\CLibrary.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */