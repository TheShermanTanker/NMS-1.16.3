/*    */ package io.netty.handler.codec.http2;
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
/*    */ public interface Http2SettingsAckFrame
/*    */   extends Http2Frame
/*    */ {
/* 25 */   public static final Http2SettingsAckFrame INSTANCE = new DefaultHttp2SettingsAckFrame();
/*    */   
/*    */   String name();
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\handler\codec\http2\Http2SettingsAckFrame.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */