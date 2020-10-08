/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public final class CancelledPacketHandleException extends RuntimeException {
/*  4 */   public static final CancelledPacketHandleException INSTANCE = new CancelledPacketHandleException();
/*    */   
/*    */   private CancelledPacketHandleException() {
/*  7 */     setStackTrace(new StackTraceElement[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized Throwable fillInStackTrace() {
/* 12 */     setStackTrace(new StackTraceElement[0]);
/* 13 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CancelledPacketHandleException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */