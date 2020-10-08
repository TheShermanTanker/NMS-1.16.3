/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.lang.management.ManagementFactory;
/*    */ import java.lang.management.ThreadInfo;
/*    */ import java.lang.management.ThreadMXBean;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import java.util.Locale;
/*    */ import java.util.Timer;
/*    */ import java.util.TimerTask;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ThreadWatchdog
/*    */   implements Runnable
/*    */ {
/* 21 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   private final DedicatedServer b;
/*    */   
/*    */   private final long c;
/*    */ 
/*    */   
/*    */   public ThreadWatchdog(DedicatedServer var0) {
/* 29 */     this.b = var0;
/* 30 */     this.c = var0.getMaxTickTime();
/*    */   }
/*    */ 
/*    */   
/*    */   public void run() {
/* 35 */     while (this.b.isRunning()) {
/* 36 */       long var0 = this.b.ax();
/* 37 */       long var2 = SystemUtils.getMonotonicMillis();
/* 38 */       long var4 = var2 - var0;
/*    */       
/* 40 */       if (var4 > this.c) {
/* 41 */         LOGGER.fatal("A single server tick took {} seconds (should be max {})", String.format(Locale.ROOT, "%.2f", new Object[] { Float.valueOf((float)var4 / 1000.0F) }), String.format(Locale.ROOT, "%.2f", new Object[] { Float.valueOf(0.05F) }));
/* 42 */         LOGGER.fatal("Considering it to be crashed, server will forcibly shutdown.");
/*    */         
/* 44 */         ThreadMXBean var6 = ManagementFactory.getThreadMXBean();
/* 45 */         ThreadInfo[] var7 = var6.dumpAllThreads(true, true);
/*    */         
/* 47 */         StringBuilder var8 = new StringBuilder();
/* 48 */         Error var9 = new Error();
/*    */         
/* 50 */         for (ThreadInfo var13 : var7) {
/* 51 */           if (var13.getThreadId() == this.b.getThread().getId()) {
/* 52 */             var9.setStackTrace(var13.getStackTrace());
/*    */           }
/*    */           
/* 55 */           var8.append(var13);
/* 56 */           var8.append("\n");
/*    */         } 
/*    */         
/* 59 */         CrashReport var10 = new CrashReport("Watching Server", var9);
/* 60 */         this.b.b(var10);
/* 61 */         CrashReportSystemDetails var11 = var10.a("Thread Dump");
/* 62 */         var11.a("Threads", var8);
/*    */         
/* 64 */         File var12 = new File(new File(this.b.B(), "crash-reports"), "crash-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date()) + "-server.txt");
/* 65 */         if (var10.a(var12)) {
/* 66 */           LOGGER.error("This crash report has been saved to: {}", var12.getAbsolutePath());
/*    */         } else {
/* 68 */           LOGGER.error("We were unable to save this crash report to disk.");
/*    */         } 
/*    */         
/* 71 */         a();
/*    */       } 
/*    */       
/*    */       try {
/* 75 */         Thread.sleep(var0 + this.c - var2);
/* 76 */       } catch (InterruptedException interruptedException) {}
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private void a() {
/*    */     try {
/* 83 */       Timer var0 = new Timer();
/* 84 */       var0.schedule(new TimerTask(this)
/*    */           {
/*    */             public void run() {
/* 87 */               Runtime.getRuntime().halt(1);
/*    */             }
/*    */           },  10000L);
/*    */       
/* 91 */       System.exit(1);
/* 92 */     } catch (Throwable var0) {
/* 93 */       Runtime.getRuntime().halt(1);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ThreadWatchdog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */