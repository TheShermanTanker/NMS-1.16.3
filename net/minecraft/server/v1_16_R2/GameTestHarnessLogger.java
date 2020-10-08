/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class GameTestHarnessLogger
/*    */   implements GameTestHarnessITestReporter {
/*  8 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */ 
/*    */   
/*    */   public void a(GameTestHarnessInfo var0) {
/* 12 */     if (var0.q()) {
/* 13 */       LOGGER.error(var0.c() + " failed! " + SystemUtils.d(var0.n()));
/*    */     } else {
/* 15 */       LOGGER.warn("(optional) " + var0.c() + " failed. " + SystemUtils.d(var0.n()));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GameTestHarnessLogger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */