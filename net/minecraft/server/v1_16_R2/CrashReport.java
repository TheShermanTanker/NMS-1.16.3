/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.destroystokyo.paper.util.SneakyThrow;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.StringWriter;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collector;
/*     */ import java.util.stream.Collectors;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.ArrayUtils;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftCrashReport;
/*     */ 
/*     */ public class CrashReport {
/*  23 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   private final String b;
/*     */   private final Throwable c;
/*  26 */   private final CrashReportSystemDetails d = new CrashReportSystemDetails(this, "System Details");
/*  27 */   private final List<CrashReportSystemDetails> e = Lists.newArrayList();
/*     */   private File f;
/*     */   private boolean g = true;
/*  30 */   private StackTraceElement[] h = new StackTraceElement[0];
/*     */   
/*     */   public CrashReport(String s, Throwable throwable) {
/*  33 */     this.b = s;
/*  34 */     this.c = throwable;
/*  35 */     i();
/*     */   }
/*     */   
/*     */   private void i() {
/*  39 */     this.d.a("Minecraft Version", () -> SharedConstants.getGameVersion().getName());
/*     */ 
/*     */     
/*  42 */     this.d.a("Minecraft Version ID", () -> SharedConstants.getGameVersion().getId());
/*     */ 
/*     */     
/*  45 */     this.d.a("Operating System", () -> System.getProperty("os.name") + " (" + System.getProperty("os.arch") + ") version " + System.getProperty("os.version"));
/*     */ 
/*     */     
/*  48 */     this.d.a("Java Version", () -> System.getProperty("java.version") + ", " + System.getProperty("java.vendor"));
/*     */ 
/*     */     
/*  51 */     this.d.a("Java VM Version", () -> System.getProperty("java.vm.name") + " (" + System.getProperty("java.vm.info") + "), " + System.getProperty("java.vm.vendor"));
/*     */ 
/*     */     
/*  54 */     this.d.a("Memory", () -> {
/*     */           Runtime runtime = Runtime.getRuntime();
/*     */           
/*     */           long i = runtime.maxMemory();
/*     */           long j = runtime.totalMemory();
/*     */           long k = runtime.freeMemory();
/*     */           long l = i / 1024L / 1024L;
/*     */           long i1 = j / 1024L / 1024L;
/*     */           long j1 = k / 1024L / 1024L;
/*     */           return k + " bytes (" + j1 + " MB) / " + j + " bytes (" + i1 + " MB) up to " + i + " bytes (" + l + " MB)";
/*     */         });
/*  65 */     this.d.a("CPUs", Integer.valueOf(Runtime.getRuntime().availableProcessors()));
/*  66 */     this.d.a("JVM Flags", () -> {
/*     */           List<String> list = SystemUtils.j().collect((Collector)Collectors.toList());
/*     */           
/*     */           return String.format("%d total; %s", new Object[] { Integer.valueOf(list.size()), list.stream().collect(Collectors.joining(" ")) });
/*     */         });
/*  71 */     this.d.a("CraftBukkit Information", (CrashReportCallable<String>)new CraftCrashReport());
/*     */   }
/*     */   
/*     */   public String a() {
/*  75 */     return this.b;
/*     */   }
/*     */   
/*     */   public Throwable b() {
/*  79 */     return this.c;
/*     */   }
/*     */   
/*     */   public void a(StringBuilder stringbuilder) {
/*  83 */     if ((this.h == null || this.h.length <= 0) && !this.e.isEmpty()) {
/*  84 */       this.h = (StackTraceElement[])ArrayUtils.subarray((Object[])((CrashReportSystemDetails)this.e.get(0)).a(), 0, 1);
/*     */     }
/*     */     
/*  87 */     if (this.h != null && this.h.length > 0) {
/*  88 */       stringbuilder.append("-- Head --\n");
/*  89 */       stringbuilder.append("Thread: ").append(Thread.currentThread().getName()).append("\n");
/*  90 */       stringbuilder.append("Stacktrace:\n");
/*  91 */       StackTraceElement[] astacktraceelement = this.h;
/*  92 */       int i = astacktraceelement.length;
/*     */       
/*  94 */       for (int j = 0; j < i; j++) {
/*  95 */         StackTraceElement stacktraceelement = astacktraceelement[j];
/*     */         
/*  97 */         stringbuilder.append("\t").append("at ").append(stacktraceelement);
/*  98 */         stringbuilder.append("\n");
/*     */       } 
/*     */       
/* 101 */       stringbuilder.append("\n");
/*     */     } 
/*     */     
/* 104 */     Iterator<CrashReportSystemDetails> iterator = this.e.iterator();
/*     */     
/* 106 */     while (iterator.hasNext()) {
/* 107 */       CrashReportSystemDetails crashreportsystemdetails = iterator.next();
/*     */       
/* 109 */       crashreportsystemdetails.a(stringbuilder);
/* 110 */       stringbuilder.append("\n\n");
/*     */     } 
/*     */     
/* 113 */     this.d.a(stringbuilder);
/*     */   }
/*     */   public String d() {
/*     */     String s;
/* 117 */     StringWriter stringwriter = null;
/* 118 */     PrintWriter printwriter = null;
/* 119 */     Object object = this.c;
/*     */     
/* 121 */     if (((Throwable)object).getMessage() == null) {
/* 122 */       if (object instanceof NullPointerException) {
/* 123 */         object = new NullPointerException(this.b);
/* 124 */       } else if (object instanceof StackOverflowError) {
/* 125 */         object = new StackOverflowError(this.b);
/* 126 */       } else if (object instanceof OutOfMemoryError) {
/* 127 */         object = new OutOfMemoryError(this.b);
/*     */       } 
/*     */       
/* 130 */       ((Throwable)object).setStackTrace(this.c.getStackTrace());
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 136 */       stringwriter = new StringWriter();
/* 137 */       printwriter = new PrintWriter(stringwriter);
/* 138 */       ((Throwable)object).printStackTrace(printwriter);
/* 139 */       s = stringwriter.toString();
/*     */     } finally {
/* 141 */       IOUtils.closeQuietly(stringwriter);
/* 142 */       IOUtils.closeQuietly(printwriter);
/*     */     } 
/*     */     
/* 145 */     return s;
/*     */   }
/*     */   
/*     */   public String e() {
/* 149 */     StringBuilder stringbuilder = new StringBuilder();
/*     */     
/* 151 */     stringbuilder.append("---- Minecraft Crash Report ----\n");
/* 152 */     stringbuilder.append("// ");
/* 153 */     stringbuilder.append(j());
/* 154 */     stringbuilder.append("\n\n");
/* 155 */     stringbuilder.append("Time: ");
/* 156 */     stringbuilder.append((new SimpleDateFormat()).format(new Date()));
/* 157 */     stringbuilder.append("\n");
/* 158 */     stringbuilder.append("Description: ");
/* 159 */     stringbuilder.append(this.b);
/* 160 */     stringbuilder.append("\n\n");
/* 161 */     stringbuilder.append(d());
/* 162 */     stringbuilder.append("\n\nA detailed walkthrough of the error, its code path and all known details is as follows:\n");
/*     */     
/* 164 */     for (int i = 0; i < 87; i++) {
/* 165 */       stringbuilder.append("-");
/*     */     }
/*     */     
/* 168 */     stringbuilder.append("\n\n");
/* 169 */     a(stringbuilder);
/* 170 */     return stringbuilder.toString();
/*     */   }
/*     */   public boolean a(File file) {
/*     */     boolean flag;
/* 174 */     if (this.f != null) {
/* 175 */       return false;
/*     */     }
/* 177 */     if (file.getParentFile() != null) {
/* 178 */       file.getParentFile().mkdirs();
/*     */     }
/*     */     
/* 181 */     OutputStreamWriter outputstreamwriter = null;
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 186 */       outputstreamwriter = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
/* 187 */       outputstreamwriter.write(e());
/* 188 */       this.f = file;
/* 189 */       boolean flag1 = true;
/*     */       
/* 191 */       return flag1;
/* 192 */     } catch (Throwable throwable) {
/* 193 */       LOGGER.error("Could not save crash report to {}", file, throwable);
/* 194 */       flag = false;
/*     */     } finally {
/* 196 */       IOUtils.closeQuietly(outputstreamwriter);
/*     */     } 
/*     */     
/* 199 */     return flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public CrashReportSystemDetails g() {
/* 204 */     return this.d;
/*     */   }
/*     */   
/*     */   public CrashReportSystemDetails a(String s) {
/* 208 */     return a(s, 1);
/*     */   }
/*     */   
/*     */   public CrashReportSystemDetails a(String s, int i) {
/* 212 */     CrashReportSystemDetails crashreportsystemdetails = new CrashReportSystemDetails(this, s);
/*     */     
/* 214 */     if (this.g) {
/* 215 */       int j = crashreportsystemdetails.a(i);
/* 216 */       StackTraceElement[] astacktraceelement = this.c.getStackTrace();
/* 217 */       StackTraceElement stacktraceelement = null;
/* 218 */       StackTraceElement stacktraceelement1 = null;
/* 219 */       int k = astacktraceelement.length - j;
/*     */       
/* 221 */       if (k < 0) {
/* 222 */         System.out.println("Negative index in crash report handler (" + astacktraceelement.length + "/" + j + ")");
/*     */       }
/*     */       
/* 225 */       if (astacktraceelement != null && 0 <= k && k < astacktraceelement.length) {
/* 226 */         stacktraceelement = astacktraceelement[k];
/* 227 */         if (astacktraceelement.length + 1 - j < astacktraceelement.length) {
/* 228 */           stacktraceelement1 = astacktraceelement[astacktraceelement.length + 1 - j];
/*     */         }
/*     */       } 
/*     */       
/* 232 */       this.g = crashreportsystemdetails.a(stacktraceelement, stacktraceelement1);
/* 233 */       if (j > 0 && !this.e.isEmpty()) {
/* 234 */         CrashReportSystemDetails crashreportsystemdetails1 = this.e.get(this.e.size() - 1);
/*     */         
/* 236 */         crashreportsystemdetails1.b(j);
/* 237 */       } else if (astacktraceelement != null && astacktraceelement.length >= j && 0 <= k && k < astacktraceelement.length) {
/* 238 */         this.h = new StackTraceElement[k];
/* 239 */         System.arraycopy(astacktraceelement, 0, this.h, 0, this.h.length);
/*     */       } else {
/* 241 */         this.g = false;
/*     */       } 
/*     */     } 
/*     */     
/* 245 */     this.e.add(crashreportsystemdetails);
/* 246 */     return crashreportsystemdetails;
/*     */   }
/*     */   
/*     */   private static String j() {
/* 250 */     String[] astring = { "Who set us up the TNT?", "Everything's going to plan. No, really, that was supposed to happen.", "Uh... Did I do that?", "Oops.", "Why did you do that?", "I feel sad now :(", "My bad.", "I'm sorry, Dave.", "I let you down. Sorry :(", "On the bright side, I bought you a teddy bear!", "Daisy, daisy...", "Oh - I know what I did wrong!", "Hey, that tickles! Hehehe!", "I blame Dinnerbone.", "You should try our sister game, Minceraft!", "Don't be sad. I'll do better next time, I promise!", "Don't be sad, have a hug! <3", "I just don't know what went wrong :(", "Shall we play a game?", "Quite honestly, I wouldn't worry myself about that.", "I bet Cylons wouldn't have this problem.", "Sorry :(", "Surprise! Haha. Well, this is awkward.", "Would you like a cupcake?", "Hi. I'm Minecraft, and I'm a crashaholic.", "Ooh. Shiny.", "This doesn't make any sense!", "Why is it breaking :(", "Don't do that.", "Ouch. That hurt :(", "You're mean.", "This is a token for 1 free hug. Redeem at your nearest Mojangsta: [~~HUG~~]", "There are four lights!", "But it works on my machine." };
/*     */     
/*     */     try {
/* 253 */       return astring[(int)(SystemUtils.getMonotonicNanos() % astring.length)];
/* 254 */     } catch (Throwable throwable) {
/* 255 */       return "Witty comment unavailable :(";
/*     */     } 
/*     */   }
/*     */   public static CrashReport a(Throwable throwable, String s) {
/*     */     CrashReport crashreport;
/* 260 */     if (throwable instanceof ThreadDeath) SneakyThrow.sneaky(throwable); 
/* 261 */     while (throwable instanceof java.util.concurrent.CompletionException && throwable.getCause() != null) {
/* 262 */       throwable = throwable.getCause();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 267 */     if (throwable instanceof ReportedException) {
/* 268 */       crashreport = ((ReportedException)throwable).a();
/*     */     } else {
/* 270 */       crashreport = new CrashReport(s, throwable);
/*     */     } 
/*     */     
/* 273 */     return crashreport;
/*     */   }
/*     */   
/*     */   public static void h() {
/* 277 */     (new CrashReport("Don't panic!", new Throwable())).e();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CrashReport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */