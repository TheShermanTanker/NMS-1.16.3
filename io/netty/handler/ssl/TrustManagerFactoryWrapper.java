/*    */ package io.netty.handler.ssl;
/*    */ 
/*    */ import io.netty.handler.ssl.util.SimpleTrustManagerFactory;
/*    */ import io.netty.util.internal.ObjectUtil;
/*    */ import java.security.KeyStore;
/*    */ import javax.net.ssl.ManagerFactoryParameters;
/*    */ import javax.net.ssl.TrustManager;
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
/*    */ final class TrustManagerFactoryWrapper
/*    */   extends SimpleTrustManagerFactory
/*    */ {
/*    */   private final TrustManager tm;
/*    */   
/*    */   TrustManagerFactoryWrapper(TrustManager tm) {
/* 30 */     this.tm = (TrustManager)ObjectUtil.checkNotNull(tm, "tm");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void engineInit(KeyStore keyStore) throws Exception {}
/*    */ 
/*    */   
/*    */   protected void engineInit(ManagerFactoryParameters managerFactoryParameters) throws Exception {}
/*    */ 
/*    */   
/*    */   protected TrustManager[] engineGetTrustManagers() {
/* 42 */     return new TrustManager[] { this.tm };
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\handler\ssl\TrustManagerFactoryWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */