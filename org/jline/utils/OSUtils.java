/*    */ package org.jline.utils;
/*    */ 
/*    */ import java.io.File;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OSUtils
/*    */ {
/* 15 */   public static final boolean IS_WINDOWS = System.getProperty("os.name").toLowerCase().contains("win");
/*    */   
/* 17 */   public static final boolean IS_CYGWIN = (IS_WINDOWS && 
/* 18 */     System.getenv("PWD") != null && 
/* 19 */     System.getenv("PWD").startsWith("/"));
/*    */   
/*    */   @Deprecated
/* 22 */   public static final boolean IS_MINGW = (IS_WINDOWS && 
/* 23 */     System.getenv("MSYSTEM") != null && 
/* 24 */     System.getenv("MSYSTEM").startsWith("MINGW"));
/*    */   
/* 26 */   public static final boolean IS_MSYSTEM = (IS_WINDOWS && 
/* 27 */     System.getenv("MSYSTEM") != null && (
/* 28 */     System.getenv("MSYSTEM").startsWith("MINGW") || 
/* 29 */     System.getenv("MSYSTEM").equals("MSYS")));
/*    */   
/* 31 */   public static final boolean IS_CONEMU = (IS_WINDOWS && 
/* 32 */     System.getenv("ConEmuPID") != null);
/*    */   
/* 34 */   public static final boolean IS_OSX = System.getProperty("os.name").toLowerCase().contains("mac");
/*    */   
/*    */   public static String TTY_COMMAND;
/*    */   
/*    */   public static String STTY_COMMAND;
/*    */   
/*    */   public static String STTY_F_OPTION;
/*    */   
/*    */   public static String INFOCMP_COMMAND;
/*    */ 
/*    */   
/*    */   static {
/* 46 */     if (IS_CYGWIN || IS_MSYSTEM) {
/* 47 */       tty = "tty.exe";
/* 48 */       stty = "stty.exe";
/* 49 */       sttyfopt = null;
/* 50 */       infocmp = "infocmp.exe";
/* 51 */       String path = System.getenv("PATH");
/* 52 */       if (path != null) {
/* 53 */         String[] paths = path.split(";");
/* 54 */         for (String p : paths) {
/* 55 */           if (tty == null && (new File(p, "tty.exe")).exists()) {
/* 56 */             tty = (new File(p, "tty.exe")).getAbsolutePath();
/*    */           }
/* 58 */           if (stty == null && (new File(p, "stty.exe")).exists()) {
/* 59 */             stty = (new File(p, "stty.exe")).getAbsolutePath();
/*    */           }
/* 61 */           if (infocmp == null && (new File(p, "infocmp.exe")).exists()) {
/* 62 */             infocmp = (new File(p, "infocmp.exe")).getAbsolutePath();
/*    */           }
/*    */         } 
/*    */       } 
/*    */     } else {
/* 67 */       tty = "tty";
/* 68 */       stty = "stty";
/* 69 */       infocmp = "infocmp";
/* 70 */       if (IS_OSX) {
/* 71 */         sttyfopt = "-f";
/*    */       } else {
/*    */         
/* 74 */         sttyfopt = "-F";
/*    */       } 
/*    */     } 
/* 77 */     TTY_COMMAND = tty;
/* 78 */     STTY_COMMAND = stty;
/* 79 */     STTY_F_OPTION = sttyfopt;
/* 80 */     INFOCMP_COMMAND = infocmp;
/*    */   }
/*    */   
/*    */   static {
/*    */     String tty, stty, sttyfopt, infocmp;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jlin\\utils\OSUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */