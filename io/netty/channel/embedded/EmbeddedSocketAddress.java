/*    */ package io.netty.channel.embedded;
/*    */ 
/*    */ import java.net.SocketAddress;
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
/*    */ final class EmbeddedSocketAddress
/*    */   extends SocketAddress
/*    */ {
/*    */   private static final long serialVersionUID = 1400788804624980619L;
/*    */   
/*    */   public String toString() {
/* 25 */     return "embedded";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\channel\embedded\EmbeddedSocketAddress.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */