/*     */ package io.netty.handler.ssl;
/*     */ 
/*     */ import io.netty.util.internal.SuppressJava6Requirement;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.function.BiFunction;
/*     */ import javax.net.ssl.SSLEngine;
/*     */ import javax.net.ssl.SSLEngineResult;
/*     */ import javax.net.ssl.SSLException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SuppressJava6Requirement(reason = "Usage guarded by java version check")
/*     */ final class JdkAlpnSslEngine
/*     */   extends JdkSslEngine
/*     */ {
/*     */   private final JdkApplicationProtocolNegotiator.ProtocolSelectionListener selectionListener;
/*     */   private final AlpnSelector alpnSelector;
/*     */   
/*     */   private final class AlpnSelector
/*     */     implements BiFunction<SSLEngine, List<String>, String>
/*     */   {
/*     */     private final JdkApplicationProtocolNegotiator.ProtocolSelector selector;
/*     */     private boolean called;
/*     */     
/*     */     AlpnSelector(JdkApplicationProtocolNegotiator.ProtocolSelector selector) {
/*  44 */       this.selector = selector;
/*     */     }
/*     */ 
/*     */     
/*     */     public String apply(SSLEngine sslEngine, List<String> strings) {
/*  49 */       assert !this.called;
/*  50 */       this.called = true;
/*     */       
/*     */       try {
/*  53 */         String selected = this.selector.select(strings);
/*  54 */         return (selected == null) ? "" : selected;
/*  55 */       } catch (Exception cause) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  60 */         return null;
/*     */       } 
/*     */     }
/*     */     
/*     */     void checkUnsupported() {
/*  65 */       if (this.called) {
/*     */         return;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  72 */       String protocol = JdkAlpnSslEngine.this.getApplicationProtocol();
/*  73 */       assert protocol != null;
/*     */       
/*  75 */       if (protocol.isEmpty())
/*     */       {
/*  77 */         this.selector.unsupported();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   JdkAlpnSslEngine(SSLEngine engine, JdkApplicationProtocolNegotiator applicationNegotiator, boolean isServer) {
/*  85 */     super(engine);
/*  86 */     if (isServer) {
/*  87 */       this.selectionListener = null;
/*  88 */       this
/*  89 */         .alpnSelector = new AlpnSelector(applicationNegotiator.protocolSelectorFactory().newSelector(this, new LinkedHashSet<String>(applicationNegotiator.protocols())));
/*  90 */       JdkAlpnSslUtils.setHandshakeApplicationProtocolSelector(engine, this.alpnSelector);
/*     */     } else {
/*  92 */       this
/*  93 */         .selectionListener = applicationNegotiator.protocolListenerFactory().newListener(this, applicationNegotiator.protocols());
/*  94 */       this.alpnSelector = null;
/*  95 */       JdkAlpnSslUtils.setApplicationProtocols(engine, applicationNegotiator.protocols());
/*     */     } 
/*     */   }
/*     */   
/*     */   private SSLEngineResult verifyProtocolSelection(SSLEngineResult result) throws SSLException {
/* 100 */     if (result.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.FINISHED) {
/* 101 */       if (this.alpnSelector == null) {
/*     */         
/*     */         try {
/* 104 */           String protocol = getApplicationProtocol();
/* 105 */           assert protocol != null;
/* 106 */           if (protocol.isEmpty()) {
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 111 */             this.selectionListener.unsupported();
/*     */           } else {
/* 113 */             this.selectionListener.selected(protocol);
/*     */           } 
/* 115 */         } catch (Throwable e) {
/* 116 */           throw SslUtils.toSSLHandshakeException(e);
/*     */         } 
/*     */       } else {
/* 119 */         assert this.selectionListener == null;
/* 120 */         this.alpnSelector.checkUnsupported();
/*     */       } 
/*     */     }
/* 123 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public SSLEngineResult wrap(ByteBuffer src, ByteBuffer dst) throws SSLException {
/* 128 */     return verifyProtocolSelection(super.wrap(src, dst));
/*     */   }
/*     */ 
/*     */   
/*     */   public SSLEngineResult wrap(ByteBuffer[] srcs, ByteBuffer dst) throws SSLException {
/* 133 */     return verifyProtocolSelection(super.wrap(srcs, dst));
/*     */   }
/*     */ 
/*     */   
/*     */   public SSLEngineResult wrap(ByteBuffer[] srcs, int offset, int len, ByteBuffer dst) throws SSLException {
/* 138 */     return verifyProtocolSelection(super.wrap(srcs, offset, len, dst));
/*     */   }
/*     */ 
/*     */   
/*     */   public SSLEngineResult unwrap(ByteBuffer src, ByteBuffer dst) throws SSLException {
/* 143 */     return verifyProtocolSelection(super.unwrap(src, dst));
/*     */   }
/*     */ 
/*     */   
/*     */   public SSLEngineResult unwrap(ByteBuffer src, ByteBuffer[] dsts) throws SSLException {
/* 148 */     return verifyProtocolSelection(super.unwrap(src, dsts));
/*     */   }
/*     */ 
/*     */   
/*     */   public SSLEngineResult unwrap(ByteBuffer src, ByteBuffer[] dst, int offset, int len) throws SSLException {
/* 153 */     return verifyProtocolSelection(super.unwrap(src, dst, offset, len));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void setNegotiatedApplicationProtocol(String applicationProtocol) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNegotiatedApplicationProtocol() {
/* 163 */     String protocol = getApplicationProtocol();
/* 164 */     if (protocol != null) {
/* 165 */       return protocol.isEmpty() ? null : protocol;
/*     */     }
/* 167 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getApplicationProtocol() {
/* 173 */     return JdkAlpnSslUtils.getApplicationProtocol(getWrappedEngine());
/*     */   }
/*     */   
/*     */   public String getHandshakeApplicationProtocol() {
/* 177 */     return JdkAlpnSslUtils.getHandshakeApplicationProtocol(getWrappedEngine());
/*     */   }
/*     */   
/*     */   public void setHandshakeApplicationProtocolSelector(BiFunction<SSLEngine, List<String>, String> selector) {
/* 181 */     JdkAlpnSslUtils.setHandshakeApplicationProtocolSelector(getWrappedEngine(), selector);
/*     */   }
/*     */   
/*     */   public BiFunction<SSLEngine, List<String>, String> getHandshakeApplicationProtocolSelector() {
/* 185 */     return JdkAlpnSslUtils.getHandshakeApplicationProtocolSelector(getWrappedEngine());
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\handler\ssl\JdkAlpnSslEngine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */