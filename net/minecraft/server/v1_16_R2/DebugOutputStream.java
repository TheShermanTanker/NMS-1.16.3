/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.OutputStream;
/*    */ 
/*    */ public class DebugOutputStream extends RedirectStream {
/*    */   public DebugOutputStream(String var0, OutputStream var1) {
/*  7 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(String var0) {
/* 12 */     StackTraceElement[] var1 = Thread.currentThread().getStackTrace();
/* 13 */     StackTraceElement var2 = var1[Math.min(3, var1.length)];
/* 14 */     LOGGER.info("[{}]@.({}:{}): {}", this.b, var2.getFileName(), Integer.valueOf(var2.getLineNumber()), var0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DebugOutputStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */