/*     */ package io.netty.handler.ssl;
/*     */ 
/*     */ import io.netty.util.internal.EmptyArrays;
/*     */ import io.netty.util.internal.PlatformDependent;
/*     */ import io.netty.util.internal.SuppressJava6Requirement;
/*     */ import io.netty.util.internal.logging.InternalLogger;
/*     */ import io.netty.util.internal.logging.InternalLoggerFactory;
/*     */ import java.lang.reflect.Method;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedExceptionAction;
/*     */ import java.util.List;
/*     */ import java.util.function.BiFunction;
/*     */ import javax.net.ssl.SSLContext;
/*     */ import javax.net.ssl.SSLEngine;
/*     */ import javax.net.ssl.SSLParameters;
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
/*     */ final class JdkAlpnSslUtils
/*     */ {
/*  36 */   private static final InternalLogger logger = InternalLoggerFactory.getInstance(JdkAlpnSslUtils.class);
/*     */   
/*     */   private static final Method SET_APPLICATION_PROTOCOLS;
/*     */   
/*     */   private static final Method GET_APPLICATION_PROTOCOL;
/*     */   private static final Method GET_HANDSHAKE_APPLICATION_PROTOCOL;
/*     */   private static final Method SET_HANDSHAKE_APPLICATION_PROTOCOL_SELECTOR;
/*     */   private static final Method GET_HANDSHAKE_APPLICATION_PROTOCOL_SELECTOR;
/*     */   
/*     */   static {
/*     */     Method getHandshakeApplicationProtocol, getApplicationProtocol, setApplicationProtocols, setHandshakeApplicationProtocolSelector, getHandshakeApplicationProtocolSelector;
/*     */   }
/*     */   
/*     */   static {
/*     */     try {
/*  51 */       SSLContext context = SSLContext.getInstance("TLS");
/*  52 */       context.init(null, null, null);
/*  53 */       SSLEngine engine = context.createSSLEngine();
/*  54 */       getHandshakeApplicationProtocol = AccessController.<Method>doPrivileged(new PrivilegedExceptionAction<Method>()
/*     */           {
/*     */             public Method run() throws Exception {
/*  57 */               return SSLEngine.class.getMethod("getHandshakeApplicationProtocol", new Class[0]);
/*     */             }
/*     */           });
/*  60 */       getHandshakeApplicationProtocol.invoke(engine, new Object[0]);
/*  61 */       getApplicationProtocol = AccessController.<Method>doPrivileged(new PrivilegedExceptionAction<Method>()
/*     */           {
/*     */             public Method run() throws Exception {
/*  64 */               return SSLEngine.class.getMethod("getApplicationProtocol", new Class[0]);
/*     */             }
/*     */           });
/*  67 */       getApplicationProtocol.invoke(engine, new Object[0]);
/*     */       
/*  69 */       setApplicationProtocols = AccessController.<Method>doPrivileged(new PrivilegedExceptionAction<Method>()
/*     */           {
/*     */             public Method run() throws Exception {
/*  72 */               return SSLParameters.class.getMethod("setApplicationProtocols", new Class[] { String[].class });
/*     */             }
/*     */           });
/*  75 */       setApplicationProtocols.invoke(engine.getSSLParameters(), new Object[] { EmptyArrays.EMPTY_STRINGS });
/*     */ 
/*     */       
/*  78 */       setHandshakeApplicationProtocolSelector = AccessController.<Method>doPrivileged(new PrivilegedExceptionAction<Method>()
/*     */           {
/*     */             public Method run() throws Exception {
/*  81 */               return SSLEngine.class.getMethod("setHandshakeApplicationProtocolSelector", new Class[] { BiFunction.class });
/*     */             }
/*     */           });
/*  84 */       setHandshakeApplicationProtocolSelector.invoke(engine, new Object[] { new BiFunction<SSLEngine, List<String>, String>()
/*     */             {
/*     */               public String apply(SSLEngine sslEngine, List<String> strings) {
/*  87 */                 return null;
/*     */               }
/*     */             } });
/*     */ 
/*     */       
/*  92 */       getHandshakeApplicationProtocolSelector = AccessController.<Method>doPrivileged(new PrivilegedExceptionAction<Method>()
/*     */           {
/*     */             public Method run() throws Exception {
/*  95 */               return SSLEngine.class.getMethod("getHandshakeApplicationProtocolSelector", new Class[0]);
/*     */             }
/*     */           });
/*  98 */       getHandshakeApplicationProtocolSelector.invoke(engine, new Object[0]);
/*  99 */     } catch (Throwable t) {
/* 100 */       int version = PlatformDependent.javaVersion();
/* 101 */       if (version >= 9)
/*     */       {
/* 103 */         logger.error("Unable to initialize JdkAlpnSslUtils, but the detected java version was: {}", Integer.valueOf(version), t);
/*     */       }
/* 105 */       getHandshakeApplicationProtocol = null;
/* 106 */       getApplicationProtocol = null;
/* 107 */       setApplicationProtocols = null;
/* 108 */       setHandshakeApplicationProtocolSelector = null;
/* 109 */       getHandshakeApplicationProtocolSelector = null;
/*     */     } 
/* 111 */     GET_HANDSHAKE_APPLICATION_PROTOCOL = getHandshakeApplicationProtocol;
/* 112 */     GET_APPLICATION_PROTOCOL = getApplicationProtocol;
/* 113 */     SET_APPLICATION_PROTOCOLS = setApplicationProtocols;
/* 114 */     SET_HANDSHAKE_APPLICATION_PROTOCOL_SELECTOR = setHandshakeApplicationProtocolSelector;
/* 115 */     GET_HANDSHAKE_APPLICATION_PROTOCOL_SELECTOR = getHandshakeApplicationProtocolSelector;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static boolean supportsAlpn() {
/* 122 */     return (GET_APPLICATION_PROTOCOL != null);
/*     */   }
/*     */   
/*     */   static String getApplicationProtocol(SSLEngine sslEngine) {
/*     */     try {
/* 127 */       return (String)GET_APPLICATION_PROTOCOL.invoke(sslEngine, new Object[0]);
/* 128 */     } catch (UnsupportedOperationException ex) {
/* 129 */       throw ex;
/* 130 */     } catch (Exception ex) {
/* 131 */       throw new IllegalStateException(ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   static String getHandshakeApplicationProtocol(SSLEngine sslEngine) {
/*     */     try {
/* 137 */       return (String)GET_HANDSHAKE_APPLICATION_PROTOCOL.invoke(sslEngine, new Object[0]);
/* 138 */     } catch (UnsupportedOperationException ex) {
/* 139 */       throw ex;
/* 140 */     } catch (Exception ex) {
/* 141 */       throw new IllegalStateException(ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   static void setApplicationProtocols(SSLEngine engine, List<String> supportedProtocols) {
/* 146 */     SSLParameters parameters = engine.getSSLParameters();
/*     */     
/* 148 */     String[] protocolArray = supportedProtocols.<String>toArray(EmptyArrays.EMPTY_STRINGS);
/*     */     try {
/* 150 */       SET_APPLICATION_PROTOCOLS.invoke(parameters, new Object[] { protocolArray });
/* 151 */     } catch (UnsupportedOperationException ex) {
/* 152 */       throw ex;
/* 153 */     } catch (Exception ex) {
/* 154 */       throw new IllegalStateException(ex);
/*     */     } 
/* 156 */     engine.setSSLParameters(parameters);
/*     */   }
/*     */ 
/*     */   
/*     */   static void setHandshakeApplicationProtocolSelector(SSLEngine engine, BiFunction<SSLEngine, List<String>, String> selector) {
/*     */     try {
/* 162 */       SET_HANDSHAKE_APPLICATION_PROTOCOL_SELECTOR.invoke(engine, new Object[] { selector });
/* 163 */     } catch (UnsupportedOperationException ex) {
/* 164 */       throw ex;
/* 165 */     } catch (Exception ex) {
/* 166 */       throw new IllegalStateException(ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   static BiFunction<SSLEngine, List<String>, String> getHandshakeApplicationProtocolSelector(SSLEngine engine) {
/*     */     try {
/* 173 */       return (BiFunction<SSLEngine, List<String>, String>)GET_HANDSHAKE_APPLICATION_PROTOCOL_SELECTOR
/* 174 */         .invoke(engine, new Object[0]);
/* 175 */     } catch (UnsupportedOperationException ex) {
/* 176 */       throw ex;
/* 177 */     } catch (Exception ex) {
/* 178 */       throw new IllegalStateException(ex);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\handler\ssl\JdkAlpnSslUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */