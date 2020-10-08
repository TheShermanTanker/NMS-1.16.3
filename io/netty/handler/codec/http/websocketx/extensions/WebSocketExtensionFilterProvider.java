/*    */ package io.netty.handler.codec.http.websocketx.extensions;
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
/*    */ public interface WebSocketExtensionFilterProvider
/*    */ {
/* 23 */   public static final WebSocketExtensionFilterProvider DEFAULT = new WebSocketExtensionFilterProvider()
/*    */     {
/*    */       public WebSocketExtensionFilter encoderFilter() {
/* 26 */         return WebSocketExtensionFilter.NEVER_SKIP;
/*    */       }
/*    */ 
/*    */       
/*    */       public WebSocketExtensionFilter decoderFilter() {
/* 31 */         return WebSocketExtensionFilter.NEVER_SKIP;
/*    */       }
/*    */     };
/*    */   
/*    */   WebSocketExtensionFilter encoderFilter();
/*    */   
/*    */   WebSocketExtensionFilter decoderFilter();
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\handler\codec\http\websocketx\extensions\WebSocketExtensionFilterProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */