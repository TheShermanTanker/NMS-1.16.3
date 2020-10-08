/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import java.util.function.LongSupplier;
/*    */ import javax.annotation.Nullable;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GameProfilerTick
/*    */ {
/* 15 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   private final LongSupplier b;
/*    */   
/*    */   private final long c;
/*    */   
/*    */   private int d;
/*    */   
/*    */   private final File e;
/*    */   
/*    */   private GameProfilerFillerActive f;
/*    */ 
/*    */   
/*    */   public GameProfilerFiller a() {
/* 29 */     this.f = new MethodProfiler(this.b, () -> this.d, false);
/* 30 */     this.d++;
/* 31 */     return this.f;
/*    */   }
/*    */   
/*    */   public void b() {
/* 35 */     if (this.f == GameProfilerDisabled.a) {
/*    */       return;
/*    */     }
/*    */     
/* 39 */     MethodProfilerResults var0 = this.f.d();
/* 40 */     this.f = GameProfilerDisabled.a;
/*    */     
/* 42 */     if (var0.g() >= this.c) {
/* 43 */       File var1 = new File(this.e, "tick-results-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date()) + ".txt");
/* 44 */       var0.a(var1);
/* 45 */       LOGGER.info("Recorded long tick -- wrote info to: {}", var1.getAbsolutePath());
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public static GameProfilerTick a(String var0) {
/* 54 */     return null;
/*    */   }
/*    */   
/*    */   public static GameProfilerFiller a(GameProfilerFiller var0, @Nullable GameProfilerTick var1) {
/* 58 */     if (var1 != null) {
/* 59 */       return GameProfilerFiller.a(var1.a(), var0);
/*    */     }
/* 61 */     return var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GameProfilerTick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */