/*     */ package org.fusesource.jansi;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintStream;
/*     */ import java.io.Reader;
/*     */ import java.util.Properties;
/*     */ import org.fusesource.hawtjni.runtime.Library;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AnsiMain
/*     */ {
/*     */   public static void main(String... args) throws IOException {
/*  53 */     System.out.println("Jansi " + getJansiVersion() + " (Jansi native " + 
/*  54 */         getPomPropertiesVersion("org.fusesource.jansi/jansi-native") + ", HawtJNI runtime " + 
/*  55 */         getPomPropertiesVersion("org.fusesource.hawtjni/hawtjni-runtime") + ")");
/*     */     
/*  57 */     System.out.println();
/*     */ 
/*     */     
/*  60 */     System.out.println("library.jansi.path= " + System.getProperty("library.jansi.path", ""));
/*  61 */     System.out.println("library.jansi.version= " + System.getProperty("library.jansi.version", ""));
/*  62 */     Library lib = new Library("jansi", CLibrary.class);
/*  63 */     lib.load();
/*  64 */     System.out.println("Jansi native library loaded from " + lib.getNativeLibraryPath());
/*  65 */     if (lib.getNativeLibrarySourceUrl() != null) {
/*  66 */       System.out.println("   which was auto-extracted from " + lib.getNativeLibrarySourceUrl());
/*     */     }
/*     */     
/*  69 */     System.out.println();
/*     */     
/*  71 */     System.out.println("os.name= " + System.getProperty("os.name") + ", os.version= " + 
/*  72 */         System.getProperty("os.version") + ", os.arch= " + 
/*  73 */         System.getProperty("os.arch"));
/*  74 */     System.out.println("file.encoding= " + System.getProperty("file.encoding"));
/*  75 */     System.out.println("java.version= " + System.getProperty("java.version") + ", java.vendor= " + 
/*  76 */         System.getProperty("java.vendor") + ", java.home= " + 
/*  77 */         System.getProperty("java.home"));
/*     */     
/*  79 */     System.out.println();
/*     */     
/*  81 */     System.out.println("jansi.passthrough= " + Boolean.getBoolean("jansi.passthrough"));
/*  82 */     System.out.println("jansi.strip= " + Boolean.getBoolean("jansi.strip"));
/*  83 */     System.out.println("jansi.force= " + Boolean.getBoolean("jansi.force"));
/*  84 */     System.out.println(Ansi.DISABLE + "= " + Boolean.getBoolean(Ansi.DISABLE));
/*     */     
/*  86 */     System.out.println();
/*     */     
/*  88 */     System.out.println("IS_WINDOWS: " + AnsiConsole.IS_WINDOWS);
/*  89 */     if (AnsiConsole.IS_WINDOWS) {
/*  90 */       System.out.println("IS_CYGWIN: " + AnsiConsole.IS_CYGWIN);
/*  91 */       System.out.println("IS_MINGW_XTERM: " + AnsiConsole.IS_MINGW_XTERM);
/*     */     } 
/*     */     
/*  94 */     System.out.println();
/*     */     
/*  96 */     diagnoseTty(false);
/*  97 */     diagnoseTty(true);
/*     */     
/*  99 */     AnsiConsole.systemInstall();
/*     */     
/* 101 */     System.out.println();
/*     */     
/* 103 */     System.out.println("Resulting Jansi modes for stout/stderr streams:");
/* 104 */     System.out.println("  - System.out: " + AnsiConsole.JANSI_STDOUT_TYPE);
/* 105 */     System.out.println("  - System.err: " + AnsiConsole.JANSI_STDERR_TYPE);
/* 106 */     System.out.println("modes description:");
/* 107 */     int n = 1;
/* 108 */     for (AnsiConsole.JansiOutputType type : AnsiConsole.JansiOutputType.values()) {
/* 109 */       System.out.println(n++ + ". " + type + ": " + type.getDescription());
/*     */     }
/*     */     
/*     */     try {
/* 113 */       System.out.println();
/*     */       
/* 115 */       testAnsi(false);
/* 116 */       testAnsi(true);
/*     */       
/* 118 */       if (args.length == 0) {
/* 119 */         printJansiLogoDemo();
/*     */         
/*     */         return;
/*     */       } 
/* 123 */       System.out.println();
/*     */       
/* 125 */       if (args.length == 1) {
/* 126 */         File f = new File(args[0]);
/* 127 */         if (f.exists()) {
/*     */ 
/*     */           
/* 130 */           System.out.println(Ansi.ansi().bold().a("\"" + args[0] + "\" content:").reset());
/* 131 */           writeFileContent(f);
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */       
/* 137 */       System.out.println(Ansi.ansi().bold().a("original args:").reset());
/* 138 */       int i = 1;
/* 139 */       for (String arg : args) {
/* 140 */         AnsiConsole.system_out.print(i++ + ": ");
/* 141 */         AnsiConsole.system_out.println(arg);
/*     */       } 
/*     */       
/* 144 */       System.out.println(Ansi.ansi().bold().a("Jansi filtered args:").reset());
/* 145 */       i = 1;
/* 146 */       for (String arg : args) {
/* 147 */         System.out.print(i++ + ": ");
/* 148 */         System.out.println(arg);
/*     */       } 
/*     */     } finally {
/* 151 */       AnsiConsole.systemUninstall();
/*     */     } 
/*     */   }
/*     */   
/*     */   private static String getJansiVersion() {
/* 156 */     Package p = AnsiMain.class.getPackage();
/* 157 */     return (p == null) ? null : p.getImplementationVersion();
/*     */   }
/*     */   
/*     */   private static void diagnoseTty(boolean stderr) {
/* 161 */     int fd = stderr ? CLibrary.STDERR_FILENO : CLibrary.STDOUT_FILENO;
/* 162 */     int isatty = CLibrary.isatty(fd);
/*     */     
/* 164 */     System.out.println("isatty(STD" + (stderr ? "ERR" : "OUT") + "_FILENO): " + isatty + ", System." + (stderr ? "err" : "out") + " " + ((isatty == 0) ? "is *NOT*" : "is") + " a terminal");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void testAnsi(boolean stderr) {
/* 170 */     PrintStream s = stderr ? System.err : System.out;
/* 171 */     s.print("test on System." + (stderr ? "err" : "out") + ":");
/* 172 */     for (Ansi.Color c : Ansi.Color.values()) {
/* 173 */       s.print(" " + Ansi.ansi().fg(c) + c + Ansi.ansi().reset());
/*     */     }
/* 175 */     s.println();
/* 176 */     s.print("            bright:");
/* 177 */     for (Ansi.Color c : Ansi.Color.values()) {
/* 178 */       s.print(" " + Ansi.ansi().fgBright(c) + c + Ansi.ansi().reset());
/*     */     }
/* 180 */     s.println();
/* 181 */     s.print("              bold:");
/* 182 */     for (Ansi.Color c : Ansi.Color.values()) {
/* 183 */       s.print(" " + Ansi.ansi().bold().fg(c) + c + Ansi.ansi().reset());
/*     */     }
/* 185 */     s.println();
/*     */   }
/*     */   
/*     */   private static String getPomPropertiesVersion(String path) throws IOException {
/* 189 */     InputStream in = AnsiMain.class.getResourceAsStream("/META-INF/maven/" + path + "/pom.properties");
/* 190 */     if (in == null) {
/* 191 */       return null;
/*     */     }
/*     */     try {
/* 194 */       Properties p = new Properties();
/* 195 */       p.load(in);
/* 196 */       return p.getProperty("version");
/*     */     } finally {
/* 198 */       closeQuietly(in);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void printJansiLogoDemo() throws IOException {
/* 203 */     Reader in = new InputStreamReader(AnsiMain.class.getResourceAsStream("jansi.txt"), "UTF-8");
/*     */     try {
/* 205 */       char[] buf = new char[1024];
/* 206 */       int l = 0;
/* 207 */       while ((l = in.read(buf)) >= 0) {
/* 208 */         for (int i = 0; i < l; i++) {
/* 209 */           System.out.print(buf[i]);
/*     */         }
/*     */       } 
/*     */     } finally {
/* 213 */       closeQuietly(in);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void writeFileContent(File f) throws IOException {
/* 218 */     InputStream in = new FileInputStream(f);
/*     */     try {
/* 220 */       byte[] buf = new byte[1024];
/* 221 */       int l = 0;
/* 222 */       while ((l = in.read(buf)) >= 0) {
/* 223 */         System.out.write(buf, 0, l);
/*     */       }
/*     */     } finally {
/* 226 */       closeQuietly(in);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void closeQuietly(Closeable c) {
/*     */     try {
/* 232 */       c.close();
/* 233 */     } catch (IOException ioe) {
/* 234 */       ioe.printStackTrace(System.err);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\fusesource\jansi\AnsiMain.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */