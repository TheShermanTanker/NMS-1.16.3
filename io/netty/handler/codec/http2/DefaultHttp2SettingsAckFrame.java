/*    */ package io.netty.handler.codec.http2;
/*    */ 
/*    */ import io.netty.util.internal.StringUtil;
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
/*    */ final class DefaultHttp2SettingsAckFrame
/*    */   implements Http2SettingsAckFrame
/*    */ {
/*    */   public String name() {
/* 26 */     return "SETTINGS(ACK)";
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 31 */     return StringUtil.simpleClassName(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\handler\codec\http2\DefaultHttp2SettingsAckFrame.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */