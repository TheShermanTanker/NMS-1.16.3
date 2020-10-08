/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.base.Stopwatch;
/*    */ import com.google.common.collect.Lists;
/*    */ import java.io.IOException;
/*    */ import java.nio.file.Path;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ public class DebugReportGenerator
/*    */ {
/* 16 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   private final Collection<Path> b;
/*    */   private final Path c;
/* 20 */   private final List<DebugReportProvider> d = Lists.newArrayList();
/*    */   
/*    */   static {
/* 23 */     DispenserRegistry.init();
/*    */   }
/*    */   
/*    */   public DebugReportGenerator(Path var0, Collection<Path> var1) {
/* 27 */     this.c = var0;
/* 28 */     this.b = var1;
/*    */   }
/*    */   
/*    */   public Collection<Path> a() {
/* 32 */     return this.b;
/*    */   }
/*    */   
/*    */   public Path b() {
/* 36 */     return this.c;
/*    */   }
/*    */   
/*    */   public void c() throws IOException {
/* 40 */     HashCache var0 = new HashCache(this.c, "cache");
/* 41 */     var0.c(b().resolve("version.json"));
/*    */     
/* 43 */     Stopwatch var1 = Stopwatch.createStarted();
/* 44 */     Stopwatch var2 = Stopwatch.createUnstarted();
/* 45 */     for (DebugReportProvider var4 : this.d) {
/* 46 */       LOGGER.info("Starting provider: {}", var4.a());
/* 47 */       var2.start();
/* 48 */       var4.a(var0);
/* 49 */       var2.stop();
/* 50 */       LOGGER.info("{} finished after {} ms", var4.a(), Long.valueOf(var2.elapsed(TimeUnit.MILLISECONDS)));
/* 51 */       var2.reset();
/*    */     } 
/* 53 */     LOGGER.info("All providers took: {} ms", Long.valueOf(var1.elapsed(TimeUnit.MILLISECONDS)));
/*    */     
/* 55 */     var0.a();
/*    */   }
/*    */   
/*    */   public void a(DebugReportProvider var0) {
/* 59 */     this.d.add(var0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DebugReportGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */