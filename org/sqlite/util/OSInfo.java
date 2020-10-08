/*     */ package org.sqlite.util;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class OSInfo
/*     */ {
/*  41 */   private static HashMap<String, String> archMapping = new HashMap<String, String>();
/*     */   
/*     */   public static final String X86 = "x86";
/*     */   
/*     */   public static final String X86_64 = "x86_64";
/*     */   public static final String IA64_32 = "ia64_32";
/*     */   public static final String IA64 = "ia64";
/*     */   public static final String PPC = "ppc";
/*     */   public static final String PPC64 = "ppc64";
/*     */   
/*     */   static {
/*  52 */     archMapping.put("x86", "x86");
/*  53 */     archMapping.put("i386", "x86");
/*  54 */     archMapping.put("i486", "x86");
/*  55 */     archMapping.put("i586", "x86");
/*  56 */     archMapping.put("i686", "x86");
/*  57 */     archMapping.put("pentium", "x86");
/*     */ 
/*     */     
/*  60 */     archMapping.put("x86_64", "x86_64");
/*  61 */     archMapping.put("amd64", "x86_64");
/*  62 */     archMapping.put("em64t", "x86_64");
/*  63 */     archMapping.put("universal", "x86_64");
/*     */ 
/*     */     
/*  66 */     archMapping.put("ia64", "ia64");
/*  67 */     archMapping.put("ia64w", "ia64");
/*     */ 
/*     */     
/*  70 */     archMapping.put("ia64_32", "ia64_32");
/*  71 */     archMapping.put("ia64n", "ia64_32");
/*     */ 
/*     */     
/*  74 */     archMapping.put("ppc", "ppc");
/*  75 */     archMapping.put("power", "ppc");
/*  76 */     archMapping.put("powerpc", "ppc");
/*  77 */     archMapping.put("power_pc", "ppc");
/*  78 */     archMapping.put("power_rs", "ppc");
/*     */ 
/*     */     
/*  81 */     archMapping.put("ppc64", "ppc64");
/*  82 */     archMapping.put("power64", "ppc64");
/*  83 */     archMapping.put("powerpc64", "ppc64");
/*  84 */     archMapping.put("power_pc64", "ppc64");
/*  85 */     archMapping.put("power_rs64", "ppc64");
/*     */   }
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/*  90 */     if (args.length >= 1) {
/*  91 */       if ("--os".equals(args[0])) {
/*  92 */         System.out.print(getOSName());
/*     */         return;
/*     */       } 
/*  95 */       if ("--arch".equals(args[0])) {
/*  96 */         System.out.print(getArchName());
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 101 */     System.out.print(getNativeLibFolderPathForCurrentOS());
/*     */   }
/*     */   
/*     */   public static String getNativeLibFolderPathForCurrentOS() {
/* 105 */     return getOSName() + "/" + getArchName();
/*     */   }
/*     */   
/*     */   public static String getOSName() {
/* 109 */     return translateOSNameToFolderName(System.getProperty("os.name"));
/*     */   }
/*     */   
/*     */   public static boolean isAndroid() {
/* 113 */     return System.getProperty("java.runtime.name", "").toLowerCase().contains("android");
/*     */   }
/*     */   
/*     */   public static boolean isAlpine() {
/*     */     try {
/* 118 */       Process p = Runtime.getRuntime().exec("cat /etc/os-release | grep ^ID");
/* 119 */       p.waitFor();
/*     */       
/* 121 */       InputStream in = p.getInputStream();
/*     */       try {
/* 123 */         int readLen = 0;
/* 124 */         ByteArrayOutputStream b = new ByteArrayOutputStream();
/* 125 */         byte[] buf = new byte[32];
/* 126 */         while ((readLen = in.read(buf, 0, buf.length)) >= 0) {
/* 127 */           b.write(buf, 0, readLen);
/*     */         }
/* 129 */         return b.toString().toLowerCase().contains("alpine");
/*     */       } finally {
/*     */         
/* 132 */         if (in != null) {
/* 133 */           in.close();
/*     */         }
/*     */       }
/*     */     
/* 137 */     } catch (Throwable e) {
/* 138 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   static String getHardwareName() {
/*     */     try {
/* 145 */       Process p = Runtime.getRuntime().exec("uname -m");
/* 146 */       p.waitFor();
/*     */       
/* 148 */       InputStream in = p.getInputStream();
/*     */       try {
/* 150 */         int readLen = 0;
/* 151 */         ByteArrayOutputStream b = new ByteArrayOutputStream();
/* 152 */         byte[] buf = new byte[32];
/* 153 */         while ((readLen = in.read(buf, 0, buf.length)) >= 0) {
/* 154 */           b.write(buf, 0, readLen);
/*     */         }
/* 156 */         return b.toString();
/*     */       } finally {
/*     */         
/* 159 */         if (in != null) {
/* 160 */           in.close();
/*     */         }
/*     */       }
/*     */     
/* 164 */     } catch (Throwable e) {
/* 165 */       System.err.println("Error while running uname -m: " + e.getMessage());
/* 166 */       return "unknown";
/*     */     } 
/*     */   }
/*     */   
/*     */   static String resolveArmArchType() {
/* 171 */     if (System.getProperty("os.name").contains("Linux")) {
/* 172 */       String armType = getHardwareName();
/*     */       
/* 174 */       if (armType.startsWith("armv6"))
/*     */       {
/* 176 */         return "armv6";
/*     */       }
/* 178 */       if (armType.startsWith("armv7"))
/*     */       {
/* 180 */         return "armv7";
/*     */       }
/* 182 */       if (armType.startsWith("armv5"))
/*     */       {
/* 184 */         return "arm";
/*     */       }
/* 186 */       if (armType.equals("aarch64"))
/*     */       {
/* 188 */         return "arm64";
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 193 */       String abi = System.getProperty("sun.arch.abi");
/* 194 */       if (abi != null && abi.startsWith("gnueabihf")) {
/* 195 */         return "armv7";
/*     */       }
/*     */ 
/*     */       
/* 199 */       String javaHome = System.getProperty("java.home");
/*     */       
/*     */       try {
/* 202 */         int exitCode = Runtime.getRuntime().exec("which readelf").waitFor();
/* 203 */         if (exitCode == 0) {
/* 204 */           String[] cmdarray = { "/bin/sh", "-c", "find '" + javaHome + "' -name 'libjvm.so' | head -1 | xargs readelf -A | grep 'Tag_ABI_VFP_args: VFP registers'" };
/*     */ 
/*     */           
/* 207 */           exitCode = Runtime.getRuntime().exec(cmdarray).waitFor();
/* 208 */           if (exitCode == 0) {
/* 209 */             return "armv7";
/*     */           }
/*     */         } else {
/* 212 */           System.err.println("WARNING! readelf not found. Cannot check if running on an armhf system, armel architecture will be presumed.");
/*     */         }
/*     */       
/*     */       }
/* 216 */       catch (IOException iOException) {
/*     */ 
/*     */       
/* 219 */       } catch (InterruptedException interruptedException) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 224 */     return "arm";
/*     */   }
/*     */   
/*     */   public static String getArchName() {
/* 228 */     String osArch = System.getProperty("os.arch");
/*     */     
/* 230 */     if (isAndroid()) {
/* 231 */       return "android-arm";
/*     */     }
/*     */     
/* 234 */     if (osArch.startsWith("arm")) {
/* 235 */       osArch = resolveArmArchType();
/*     */     } else {
/*     */       
/* 238 */       String lc = osArch.toLowerCase(Locale.US);
/* 239 */       if (archMapping.containsKey(lc))
/* 240 */         return archMapping.get(lc); 
/*     */     } 
/* 242 */     return translateArchNameToFolderName(osArch);
/*     */   }
/*     */   
/*     */   static String translateOSNameToFolderName(String osName) {
/* 246 */     if (osName.contains("Windows")) {
/* 247 */       return "Windows";
/*     */     }
/* 249 */     if (osName.contains("Mac") || osName.contains("Darwin")) {
/* 250 */       return "Mac";
/*     */     }
/* 252 */     if (isAlpine()) {
/* 253 */       return "Linux-Alpine";
/*     */     }
/* 255 */     if (osName.contains("Linux")) {
/* 256 */       return "Linux";
/*     */     }
/* 258 */     if (osName.contains("AIX")) {
/* 259 */       return "AIX";
/*     */     }
/*     */ 
/*     */     
/* 263 */     return osName.replaceAll("\\W", "");
/*     */   }
/*     */ 
/*     */   
/*     */   static String translateArchNameToFolderName(String archName) {
/* 268 */     return archName.replaceAll("\\W", "");
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\sqlit\\util\OSInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */