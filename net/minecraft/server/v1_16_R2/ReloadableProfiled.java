/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.base.Stopwatch;
/*    */ import java.util.List;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ import java.util.concurrent.Executor;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ import java.util.concurrent.atomic.AtomicLong;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ReloadableProfiled
/*    */   extends Reloadable<ReloadableProfiled.a>
/*    */ {
/* 19 */   private static final Logger LOGGER = LogManager.getLogger();
/* 20 */   private final Stopwatch e = Stopwatch.createUnstarted();
/*    */   
/*    */   public ReloadableProfiled(IResourceManager var0, List<IReloadListener> var1, Executor var2, Executor var3, CompletableFuture<Unit> var4) {
/* 23 */     super(var2, var3, var0, var1, (var1, var2, var3, var4, var5) -> { AtomicLong var6 = new AtomicLong(); AtomicLong var7 = new AtomicLong(); MethodProfiler var8 = new MethodProfiler(SystemUtils.a, (), false); MethodProfiler var9 = new MethodProfiler(SystemUtils.a, (), false); CompletableFuture<Void> var10 = var3.a(var1, var2, var8, var9, (), ()); return var10.thenApplyAsync((), var0); }var4);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 46 */     this.e.start();
/* 47 */     this.c.thenAcceptAsync(this::a, var3);
/*    */   }
/*    */   
/*    */   private void a(List<a> var0) {
/* 51 */     this.e.stop();
/* 52 */     int var1 = 0;
/* 53 */     LOGGER.info("Resource reload finished after " + this.e.elapsed(TimeUnit.MILLISECONDS) + " ms");
/* 54 */     for (a var3 : var0) {
/* 55 */       MethodProfilerResults var4 = a.a(var3);
/* 56 */       MethodProfilerResults var5 = a.b(var3);
/* 57 */       int var6 = (int)(a.c(var3).get() / 1000000.0D);
/* 58 */       int var7 = (int)(a.d(var3).get() / 1000000.0D);
/* 59 */       int var8 = var6 + var7;
/* 60 */       String var9 = a.e(var3);
/* 61 */       LOGGER.info(var9 + " took approximately " + var8 + " ms (" + var6 + " ms preparing, " + var7 + " ms applying)");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 75 */       var1 += var7;
/*    */     } 
/*    */     
/* 78 */     LOGGER.info("Total blocking time: " + var1 + " ms");
/*    */   }
/*    */   
/*    */   public static class a {
/*    */     private final String a;
/*    */     private final MethodProfilerResults b;
/*    */     private final MethodProfilerResults c;
/*    */     private final AtomicLong d;
/*    */     private final AtomicLong e;
/*    */     
/*    */     private a(String var0, MethodProfilerResults var1, MethodProfilerResults var2, AtomicLong var3, AtomicLong var4) {
/* 89 */       this.a = var0;
/* 90 */       this.b = var1;
/* 91 */       this.c = var2;
/* 92 */       this.d = var3;
/* 93 */       this.e = var4;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ReloadableProfiled.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */