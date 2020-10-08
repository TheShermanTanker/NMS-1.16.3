/*     */ package com.mysql.jdbc;
/*     */ 
/*     */ import com.mysql.jdbc.util.Base64Decoder;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.Socket;
/*     */ import java.net.SocketException;
/*     */ import java.net.URL;
/*     */ import java.security.InvalidAlgorithmParameterException;
/*     */ import java.security.KeyFactory;
/*     */ import java.security.KeyManagementException;
/*     */ import java.security.KeyStore;
/*     */ import java.security.KeyStoreException;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.UnrecoverableKeyException;
/*     */ import java.security.cert.CertPath;
/*     */ import java.security.cert.CertPathValidator;
/*     */ import java.security.cert.CertPathValidatorException;
/*     */ import java.security.cert.CertPathValidatorResult;
/*     */ import java.security.cert.Certificate;
/*     */ import java.security.cert.CertificateException;
/*     */ import java.security.cert.CertificateFactory;
/*     */ import java.security.cert.PKIXCertPathValidatorResult;
/*     */ import java.security.cert.PKIXParameters;
/*     */ import java.security.cert.TrustAnchor;
/*     */ import java.security.cert.X509CertSelector;
/*     */ import java.security.cert.X509Certificate;
/*     */ import java.security.interfaces.RSAPublicKey;
/*     */ import java.security.spec.X509EncodedKeySpec;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.net.ssl.KeyManager;
/*     */ import javax.net.ssl.KeyManagerFactory;
/*     */ import javax.net.ssl.SSLContext;
/*     */ import javax.net.ssl.SSLSocket;
/*     */ import javax.net.ssl.SSLSocketFactory;
/*     */ import javax.net.ssl.TrustManager;
/*     */ import javax.net.ssl.TrustManagerFactory;
/*     */ import javax.net.ssl.X509TrustManager;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExportControlled
/*     */ {
/*     */   private static final String SQL_STATE_BAD_SSL_PARAMS = "08000";
/*     */   private static final String TLSv1 = "TLSv1";
/*     */   private static final String TLSv1_1 = "TLSv1.1";
/*     */   private static final String TLSv1_2 = "TLSv1.2";
/*  82 */   private static final String[] TLS_PROTOCOLS = new String[] { "TLSv1.2", "TLSv1.1", "TLSv1" };
/*     */ 
/*     */   
/*     */   protected static boolean enabled() {
/*  86 */     return true;
/*     */   }
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
/*     */   protected static void transformSocketToSSLSocket(MysqlIO mysqlIO) throws SQLException {
/* 103 */     SocketFactory sslFact = new StandardSSLSocketFactory(getSSLSocketFactoryDefaultOrConfigured(mysqlIO), mysqlIO.socketFactory, mysqlIO.mysqlConnection);
/*     */     
/*     */     try {
/* 106 */       mysqlIO.mysqlConnection = sslFact.connect(mysqlIO.host, mysqlIO.port, null);
/*     */       
/* 108 */       String[] tryProtocols = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 115 */       String enabledTLSProtocols = mysqlIO.connection.getEnabledTLSProtocols();
/* 116 */       if (enabledTLSProtocols != null && enabledTLSProtocols.length() > 0) {
/* 117 */         tryProtocols = enabledTLSProtocols.split("\\s*,\\s*");
/* 118 */       } else if (mysqlIO.versionMeetsMinimum(5, 7, 28) || (mysqlIO.versionMeetsMinimum(5, 6, 46) && !mysqlIO.versionMeetsMinimum(5, 7, 0)) || (mysqlIO.versionMeetsMinimum(5, 6, 0) && Util.isEnterpriseEdition(mysqlIO.getServerVersion()))) {
/*     */ 
/*     */         
/* 121 */         tryProtocols = TLS_PROTOCOLS;
/*     */       } else {
/*     */         
/* 124 */         tryProtocols = new String[] { "TLSv1.1", "TLSv1" };
/*     */       } 
/*     */ 
/*     */       
/* 128 */       List<String> configuredProtocols = new ArrayList<String>(Arrays.asList(tryProtocols));
/* 129 */       List<String> jvmSupportedProtocols = Arrays.asList(((SSLSocket)mysqlIO.mysqlConnection).getSupportedProtocols());
/* 130 */       List<String> allowedProtocols = new ArrayList<String>();
/* 131 */       for (String protocol : TLS_PROTOCOLS) {
/* 132 */         if (jvmSupportedProtocols.contains(protocol) && configuredProtocols.contains(protocol)) {
/* 133 */           allowedProtocols.add(protocol);
/*     */         }
/*     */       } 
/* 136 */       ((SSLSocket)mysqlIO.mysqlConnection).setEnabledProtocols(allowedProtocols.<String>toArray(new String[0]));
/*     */ 
/*     */       
/* 139 */       String enabledSSLCipherSuites = mysqlIO.connection.getEnabledSSLCipherSuites();
/* 140 */       boolean overrideCiphers = (enabledSSLCipherSuites != null && enabledSSLCipherSuites.length() > 0);
/*     */       
/* 142 */       List<String> allowedCiphers = null;
/* 143 */       if (overrideCiphers) {
/*     */ 
/*     */         
/* 146 */         allowedCiphers = new ArrayList<String>();
/* 147 */         List<String> availableCiphers = Arrays.asList(((SSLSocket)mysqlIO.mysqlConnection).getEnabledCipherSuites());
/* 148 */         for (String cipher : enabledSSLCipherSuites.split("\\s*,\\s*")) {
/* 149 */           if (availableCiphers.contains(cipher)) {
/* 150 */             allowedCiphers.add(cipher);
/*     */           }
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 156 */         boolean disableDHAlgorithm = false;
/* 157 */         if ((mysqlIO.versionMeetsMinimum(5, 5, 45) && !mysqlIO.versionMeetsMinimum(5, 6, 0)) || (mysqlIO.versionMeetsMinimum(5, 6, 26) && !mysqlIO.versionMeetsMinimum(5, 7, 0)) || mysqlIO.versionMeetsMinimum(5, 7, 6)) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 163 */           if (Util.getJVMVersion() < 8) {
/* 164 */             disableDHAlgorithm = true;
/*     */           }
/* 166 */         } else if (Util.getJVMVersion() >= 8) {
/*     */ 
/*     */           
/* 169 */           disableDHAlgorithm = true;
/*     */         } 
/*     */         
/* 172 */         if (disableDHAlgorithm) {
/* 173 */           allowedCiphers = new ArrayList<String>();
/* 174 */           for (String cipher : ((SSLSocket)mysqlIO.mysqlConnection).getEnabledCipherSuites()) {
/* 175 */             if (!disableDHAlgorithm || (cipher.indexOf("_DHE_") <= -1 && cipher.indexOf("_DH_") <= -1)) {
/* 176 */               allowedCiphers.add(cipher);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 183 */       if (allowedCiphers != null) {
/* 184 */         ((SSLSocket)mysqlIO.mysqlConnection).setEnabledCipherSuites(allowedCiphers.<String>toArray(new String[0]));
/*     */       }
/*     */       
/* 187 */       ((SSLSocket)mysqlIO.mysqlConnection).startHandshake();
/*     */       
/* 189 */       if (mysqlIO.connection.getUseUnbufferedInput()) {
/* 190 */         mysqlIO.mysqlInput = mysqlIO.mysqlConnection.getInputStream();
/*     */       } else {
/* 192 */         mysqlIO.mysqlInput = new BufferedInputStream(mysqlIO.mysqlConnection.getInputStream(), 16384);
/*     */       } 
/*     */       
/* 195 */       mysqlIO.mysqlOutput = new BufferedOutputStream(mysqlIO.mysqlConnection.getOutputStream(), 16384);
/*     */       
/* 197 */       mysqlIO.mysqlOutput.flush();
/*     */       
/* 199 */       mysqlIO.socketFactory = sslFact;
/*     */     }
/* 201 */     catch (IOException ioEx) {
/* 202 */       throw SQLError.createCommunicationsException(mysqlIO.connection, mysqlIO.getLastPacketSentTimeMs(), mysqlIO.getLastPacketReceivedTimeMs(), ioEx, mysqlIO.getExceptionInterceptor());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class StandardSSLSocketFactory
/*     */     implements SocketFactory, SocketMetadata
/*     */   {
/* 211 */     private SSLSocket rawSocket = null;
/*     */     private final SSLSocketFactory sslFact;
/*     */     private final SocketFactory existingSocketFactory;
/*     */     private final Socket existingSocket;
/*     */     
/*     */     public StandardSSLSocketFactory(SSLSocketFactory sslFact, SocketFactory existingSocketFactory, Socket existingSocket) {
/* 217 */       this.sslFact = sslFact;
/* 218 */       this.existingSocketFactory = existingSocketFactory;
/* 219 */       this.existingSocket = existingSocket;
/*     */     }
/*     */     
/*     */     public Socket afterHandshake() throws SocketException, IOException {
/* 223 */       this.existingSocketFactory.afterHandshake();
/* 224 */       return this.rawSocket;
/*     */     }
/*     */     
/*     */     public Socket beforeHandshake() throws SocketException, IOException {
/* 228 */       return this.rawSocket;
/*     */     }
/*     */     
/*     */     public Socket connect(String host, int portNumber, Properties props) throws SocketException, IOException {
/* 232 */       this.rawSocket = (SSLSocket)this.sslFact.createSocket(this.existingSocket, host, portNumber, true);
/* 233 */       return this.rawSocket;
/*     */     }
/*     */     
/*     */     public boolean isLocallyConnected(ConnectionImpl conn) throws SQLException {
/* 237 */       return SocketMetadata.Helper.isLocallyConnected(conn);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class X509TrustManagerWrapper
/*     */     implements X509TrustManager
/*     */   {
/* 250 */     private X509TrustManager origTm = null;
/*     */     private boolean verifyServerCert = false;
/* 252 */     private CertificateFactory certFactory = null;
/* 253 */     private PKIXParameters validatorParams = null;
/* 254 */     private CertPathValidator validator = null;
/*     */     
/*     */     public X509TrustManagerWrapper(X509TrustManager tm, boolean verifyServerCertificate) throws CertificateException {
/* 257 */       this.origTm = tm;
/* 258 */       this.verifyServerCert = verifyServerCertificate;
/*     */       
/* 260 */       if (verifyServerCertificate) {
/*     */         try {
/* 262 */           Set<TrustAnchor> anch = new HashSet<TrustAnchor>();
/* 263 */           for (X509Certificate cert : tm.getAcceptedIssuers()) {
/* 264 */             anch.add(new TrustAnchor(cert, null));
/*     */           }
/* 266 */           this.validatorParams = new PKIXParameters(anch);
/* 267 */           this.validatorParams.setRevocationEnabled(false);
/* 268 */           this.validator = CertPathValidator.getInstance("PKIX");
/* 269 */           this.certFactory = CertificateFactory.getInstance("X.509");
/* 270 */         } catch (Exception e) {
/* 271 */           throw new CertificateException(e);
/*     */         } 
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public X509TrustManagerWrapper() {}
/*     */     
/*     */     public X509Certificate[] getAcceptedIssuers() {
/* 280 */       return (this.origTm != null) ? this.origTm.getAcceptedIssuers() : new X509Certificate[0];
/*     */     }
/*     */     
/*     */     public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
/* 284 */       for (int i = 0; i < chain.length; i++) {
/* 285 */         chain[i].checkValidity();
/*     */       }
/*     */       
/* 288 */       if (this.validatorParams != null) {
/*     */         
/* 290 */         X509CertSelector certSelect = new X509CertSelector();
/* 291 */         certSelect.setSerialNumber(chain[0].getSerialNumber());
/*     */         
/*     */         try {
/* 294 */           CertPath certPath = this.certFactory.generateCertPath(Arrays.asList((Certificate[])chain));
/*     */           
/* 296 */           CertPathValidatorResult result = this.validator.validate(certPath, this.validatorParams);
/*     */           
/* 298 */           ((PKIXCertPathValidatorResult)result).getTrustAnchor().getTrustedCert().checkValidity();
/*     */         }
/* 300 */         catch (InvalidAlgorithmParameterException e) {
/* 301 */           throw new CertificateException(e);
/* 302 */         } catch (CertPathValidatorException e) {
/* 303 */           throw new CertificateException(e);
/*     */         } 
/*     */       } 
/*     */       
/* 307 */       if (this.verifyServerCert) {
/* 308 */         this.origTm.checkServerTrusted(chain, authType);
/*     */       }
/*     */     }
/*     */     
/*     */     public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
/* 313 */       this.origTm.checkClientTrusted(chain, authType);
/*     */     }
/*     */   }
/*     */   
/*     */   private static SSLSocketFactory getSSLSocketFactoryDefaultOrConfigured(MysqlIO mysqlIO) throws SQLException {
/* 318 */     String clientCertificateKeyStoreUrl = mysqlIO.connection.getClientCertificateKeyStoreUrl();
/* 319 */     String clientCertificateKeyStorePassword = mysqlIO.connection.getClientCertificateKeyStorePassword();
/* 320 */     String clientCertificateKeyStoreType = mysqlIO.connection.getClientCertificateKeyStoreType();
/* 321 */     String trustCertificateKeyStoreUrl = mysqlIO.connection.getTrustCertificateKeyStoreUrl();
/* 322 */     String trustCertificateKeyStorePassword = mysqlIO.connection.getTrustCertificateKeyStorePassword();
/* 323 */     String trustCertificateKeyStoreType = mysqlIO.connection.getTrustCertificateKeyStoreType();
/*     */     
/* 325 */     if (StringUtils.isNullOrEmpty(clientCertificateKeyStoreUrl)) {
/* 326 */       clientCertificateKeyStoreUrl = System.getProperty("javax.net.ssl.keyStore");
/* 327 */       clientCertificateKeyStorePassword = System.getProperty("javax.net.ssl.keyStorePassword");
/* 328 */       clientCertificateKeyStoreType = System.getProperty("javax.net.ssl.keyStoreType");
/* 329 */       if (StringUtils.isNullOrEmpty(clientCertificateKeyStoreType)) {
/* 330 */         clientCertificateKeyStoreType = "JKS";
/*     */       }
/*     */       
/* 333 */       if (!StringUtils.isNullOrEmpty(clientCertificateKeyStoreUrl)) {
/*     */         try {
/* 335 */           new URL(clientCertificateKeyStoreUrl);
/* 336 */         } catch (MalformedURLException e) {
/* 337 */           clientCertificateKeyStoreUrl = "file:" + clientCertificateKeyStoreUrl;
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 342 */     if (StringUtils.isNullOrEmpty(trustCertificateKeyStoreUrl)) {
/* 343 */       trustCertificateKeyStoreUrl = System.getProperty("javax.net.ssl.trustStore");
/* 344 */       trustCertificateKeyStorePassword = System.getProperty("javax.net.ssl.trustStorePassword");
/* 345 */       trustCertificateKeyStoreType = System.getProperty("javax.net.ssl.trustStoreType");
/* 346 */       if (StringUtils.isNullOrEmpty(trustCertificateKeyStoreType)) {
/* 347 */         trustCertificateKeyStoreType = "JKS";
/*     */       }
/*     */       
/* 350 */       if (!StringUtils.isNullOrEmpty(trustCertificateKeyStoreUrl)) {
/*     */         try {
/* 352 */           new URL(trustCertificateKeyStoreUrl);
/* 353 */         } catch (MalformedURLException e) {
/* 354 */           trustCertificateKeyStoreUrl = "file:" + trustCertificateKeyStoreUrl;
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 359 */     TrustManagerFactory tmf = null;
/* 360 */     KeyManagerFactory kmf = null;
/*     */     
/* 362 */     KeyManager[] kms = null;
/* 363 */     List<TrustManager> tms = new ArrayList<TrustManager>();
/*     */     
/*     */     try {
/* 366 */       tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
/* 367 */       kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
/* 368 */     } catch (NoSuchAlgorithmException nsae) {
/* 369 */       throw SQLError.createSQLException("Default algorithm definitions for TrustManager and/or KeyManager are invalid.  Check java security properties file.", "08000", 0, false, mysqlIO.getExceptionInterceptor());
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 374 */     if (!StringUtils.isNullOrEmpty(clientCertificateKeyStoreUrl)) {
/* 375 */       InputStream ksIS = null;
/*     */       try {
/* 377 */         if (!StringUtils.isNullOrEmpty(clientCertificateKeyStoreType)) {
/* 378 */           KeyStore clientKeyStore = KeyStore.getInstance(clientCertificateKeyStoreType);
/* 379 */           URL ksURL = new URL(clientCertificateKeyStoreUrl);
/* 380 */           char[] password = (clientCertificateKeyStorePassword == null) ? new char[0] : clientCertificateKeyStorePassword.toCharArray();
/* 381 */           ksIS = ksURL.openStream();
/* 382 */           clientKeyStore.load(ksIS, password);
/* 383 */           kmf.init(clientKeyStore, password);
/* 384 */           kms = kmf.getKeyManagers();
/*     */         } 
/* 386 */       } catch (UnrecoverableKeyException uke) {
/* 387 */         throw SQLError.createSQLException("Could not recover keys from client keystore.  Check password?", "08000", 0, false, mysqlIO.getExceptionInterceptor());
/*     */       }
/* 389 */       catch (NoSuchAlgorithmException nsae) {
/* 390 */         throw SQLError.createSQLException("Unsupported keystore algorithm [" + nsae.getMessage() + "]", "08000", 0, false, mysqlIO.getExceptionInterceptor());
/*     */       }
/* 392 */       catch (KeyStoreException kse) {
/* 393 */         throw SQLError.createSQLException("Could not create KeyStore instance [" + kse.getMessage() + "]", "08000", 0, false, mysqlIO.getExceptionInterceptor());
/*     */       }
/* 395 */       catch (CertificateException nsae) {
/* 396 */         throw SQLError.createSQLException("Could not load client" + clientCertificateKeyStoreType + " keystore from " + clientCertificateKeyStoreUrl, mysqlIO.getExceptionInterceptor());
/*     */       }
/* 398 */       catch (MalformedURLException mue) {
/* 399 */         throw SQLError.createSQLException(clientCertificateKeyStoreUrl + " does not appear to be a valid URL.", "08000", 0, false, mysqlIO.getExceptionInterceptor());
/*     */       }
/* 401 */       catch (IOException ioe) {
/* 402 */         SQLException sqlEx = SQLError.createSQLException("Cannot open " + clientCertificateKeyStoreUrl + " [" + ioe.getMessage() + "]", "08000", 0, false, mysqlIO.getExceptionInterceptor());
/*     */         
/* 404 */         sqlEx.initCause(ioe);
/*     */         
/* 406 */         throw sqlEx;
/*     */       } finally {
/* 408 */         if (ksIS != null) {
/*     */           try {
/* 410 */             ksIS.close();
/* 411 */           } catch (IOException e) {}
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 418 */     InputStream trustStoreIS = null;
/*     */     try {
/* 420 */       KeyStore trustKeyStore = null;
/*     */       
/* 422 */       if (!StringUtils.isNullOrEmpty(trustCertificateKeyStoreUrl) && !StringUtils.isNullOrEmpty(trustCertificateKeyStoreType)) {
/* 423 */         trustStoreIS = (new URL(trustCertificateKeyStoreUrl)).openStream();
/* 424 */         char[] trustStorePassword = (trustCertificateKeyStorePassword == null) ? new char[0] : trustCertificateKeyStorePassword.toCharArray();
/*     */         
/* 426 */         trustKeyStore = KeyStore.getInstance(trustCertificateKeyStoreType);
/* 427 */         trustKeyStore.load(trustStoreIS, trustStorePassword);
/*     */       } 
/*     */       
/* 430 */       tmf.init(trustKeyStore);
/*     */ 
/*     */       
/* 433 */       TrustManager[] origTms = tmf.getTrustManagers();
/* 434 */       boolean verifyServerCert = mysqlIO.connection.getVerifyServerCertificate();
/*     */       
/* 436 */       for (TrustManager tm : origTms)
/*     */       {
/* 438 */         tms.add((tm instanceof X509TrustManager) ? new X509TrustManagerWrapper((X509TrustManager)tm, verifyServerCert) : tm);
/*     */       }
/*     */     }
/* 441 */     catch (MalformedURLException e) {
/* 442 */       throw SQLError.createSQLException(trustCertificateKeyStoreUrl + " does not appear to be a valid URL.", "08000", 0, false, mysqlIO.getExceptionInterceptor());
/*     */     }
/* 444 */     catch (KeyStoreException e) {
/* 445 */       throw SQLError.createSQLException("Could not create KeyStore instance [" + e.getMessage() + "]", "08000", 0, false, mysqlIO.getExceptionInterceptor());
/*     */     }
/* 447 */     catch (NoSuchAlgorithmException e) {
/* 448 */       throw SQLError.createSQLException("Unsupported keystore algorithm [" + e.getMessage() + "]", "08000", 0, false, mysqlIO.getExceptionInterceptor());
/*     */     }
/* 450 */     catch (CertificateException e) {
/* 451 */       throw SQLError.createSQLException("Could not load trust" + trustCertificateKeyStoreType + " keystore from " + trustCertificateKeyStoreUrl, "08000", 0, false, mysqlIO.getExceptionInterceptor());
/*     */     }
/* 453 */     catch (IOException e) {
/* 454 */       SQLException sqlEx = SQLError.createSQLException("Cannot open " + trustCertificateKeyStoreType + " [" + e.getMessage() + "]", "08000", 0, false, mysqlIO.getExceptionInterceptor());
/*     */       
/* 456 */       sqlEx.initCause(e);
/* 457 */       throw sqlEx;
/*     */     } finally {
/* 459 */       if (trustStoreIS != null) {
/*     */         try {
/* 461 */           trustStoreIS.close();
/* 462 */         } catch (IOException e) {}
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 469 */     if (tms.size() == 0) {
/* 470 */       tms.add(new X509TrustManagerWrapper());
/*     */     }
/*     */     
/*     */     try {
/* 474 */       SSLContext sslContext = SSLContext.getInstance("TLS");
/* 475 */       sslContext.init(kms, tms.<TrustManager>toArray(new TrustManager[tms.size()]), null);
/* 476 */       return sslContext.getSocketFactory();
/*     */     }
/* 478 */     catch (NoSuchAlgorithmException nsae) {
/* 479 */       throw SQLError.createSQLException("TLS is not a valid SSL protocol.", "08000", 0, false, mysqlIO.getExceptionInterceptor());
/* 480 */     } catch (KeyManagementException kme) {
/* 481 */       throw SQLError.createSQLException("KeyManagementException: " + kme.getMessage(), "08000", 0, false, mysqlIO.getExceptionInterceptor());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isSSLEstablished(Socket socket) {
/* 487 */     return (socket == null) ? false : SSLSocket.class.isAssignableFrom(socket.getClass());
/*     */   }
/*     */ 
/*     */   
/*     */   public static RSAPublicKey decodeRSAPublicKey(String key, ExceptionInterceptor interceptor) throws SQLException {
/*     */     try {
/* 493 */       if (key == null) {
/* 494 */         throw new SQLException("key parameter is null");
/*     */       }
/*     */       
/* 497 */       int offset = key.indexOf("\n") + 1;
/* 498 */       int len = key.indexOf("-----END PUBLIC KEY-----") - offset;
/*     */ 
/*     */       
/* 501 */       byte[] certificateData = Base64Decoder.decode(key.getBytes(), offset, len);
/*     */       
/* 503 */       X509EncodedKeySpec spec = new X509EncodedKeySpec(certificateData);
/* 504 */       KeyFactory kf = KeyFactory.getInstance("RSA");
/* 505 */       return (RSAPublicKey)kf.generatePublic(spec);
/* 506 */     } catch (Exception ex) {
/* 507 */       throw SQLError.createSQLException("Unable to decode public key", "S1009", ex, interceptor);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static byte[] encryptWithRSAPublicKey(byte[] source, RSAPublicKey key, String transformation, ExceptionInterceptor interceptor) throws SQLException {
/*     */     try {
/* 513 */       Cipher cipher = Cipher.getInstance(transformation);
/* 514 */       cipher.init(1, key);
/* 515 */       return cipher.doFinal(source);
/* 516 */     } catch (Exception ex) {
/* 517 */       throw SQLError.createSQLException(ex.getMessage(), "S1009", ex, interceptor);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\ExportControlled.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */