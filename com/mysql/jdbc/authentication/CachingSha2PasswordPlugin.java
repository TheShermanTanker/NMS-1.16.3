/*     */ package com.mysql.jdbc.authentication;
/*     */ 
/*     */ import com.mysql.jdbc.Buffer;
/*     */ import com.mysql.jdbc.Connection;
/*     */ import com.mysql.jdbc.Messages;
/*     */ import com.mysql.jdbc.MySQLConnection;
/*     */ import com.mysql.jdbc.SQLError;
/*     */ import com.mysql.jdbc.Security;
/*     */ import com.mysql.jdbc.StringUtils;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.security.DigestException;
/*     */ import java.sql.SQLException;
/*     */ import java.util.List;
/*     */ import java.util.Properties;
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
/*     */ public class CachingSha2PasswordPlugin
/*     */   extends Sha256PasswordPlugin
/*     */ {
/*  42 */   public static String PLUGIN_NAME = "caching_sha2_password";
/*     */   
/*     */   public enum AuthStage {
/*  45 */     FAST_AUTH_SEND_SCRAMBLE, FAST_AUTH_READ_RESULT, FAST_AUTH_COMPLETE, FULL_AUTH;
/*     */   }
/*     */   
/*  48 */   private AuthStage stage = AuthStage.FAST_AUTH_SEND_SCRAMBLE;
/*     */ 
/*     */   
/*     */   public void init(Connection conn, Properties props) throws SQLException {
/*  52 */     super.init(conn, props);
/*  53 */     this.stage = AuthStage.FAST_AUTH_SEND_SCRAMBLE;
/*     */   }
/*     */ 
/*     */   
/*     */   public void destroy() {
/*  58 */     this.stage = AuthStage.FAST_AUTH_SEND_SCRAMBLE;
/*  59 */     super.destroy();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getProtocolPluginName() {
/*  64 */     return PLUGIN_NAME;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean nextAuthenticationStep(Buffer fromServer, List<Buffer> toServer) throws SQLException {
/*  69 */     toServer.clear();
/*     */     
/*  71 */     if (this.password == null || this.password.length() == 0 || fromServer == null) {
/*     */       
/*  73 */       Buffer bresp = new Buffer(new byte[] { 0 });
/*  74 */       toServer.add(bresp);
/*     */     } else {
/*     */       
/*  77 */       if (this.stage == AuthStage.FAST_AUTH_SEND_SCRAMBLE) {
/*     */         
/*  79 */         this.seed = fromServer.readString();
/*     */         try {
/*  81 */           toServer.add(new Buffer(Security.scrambleCachingSha2(StringUtils.getBytes(this.password, this.connection.getPasswordCharacterEncoding()), this.seed.getBytes())));
/*     */         }
/*  83 */         catch (DigestException e) {
/*  84 */           throw SQLError.createSQLException(e.getMessage(), "S1000", e, null);
/*  85 */         } catch (UnsupportedEncodingException e) {
/*  86 */           throw SQLError.createSQLException(e.getMessage(), "S1000", e, null);
/*     */         } 
/*  88 */         this.stage = AuthStage.FAST_AUTH_READ_RESULT;
/*  89 */         return true;
/*     */       } 
/*  91 */       if (this.stage == AuthStage.FAST_AUTH_READ_RESULT) {
/*  92 */         int fastAuthResult = fromServer.getByteBuffer()[0];
/*  93 */         switch (fastAuthResult) {
/*     */           case 3:
/*  95 */             this.stage = AuthStage.FAST_AUTH_COMPLETE;
/*  96 */             return true;
/*     */           case 4:
/*  98 */             this.stage = AuthStage.FULL_AUTH;
/*     */             break;
/*     */           default:
/* 101 */             throw SQLError.createSQLException("Unknown server response after fast auth.", "08001", this.connection.getExceptionInterceptor());
/*     */         } 
/*     */ 
/*     */       
/*     */       } 
/* 106 */       if (((MySQLConnection)this.connection).getIO().isSSLEstablished()) {
/*     */         Buffer bresp;
/*     */         
/*     */         try {
/* 110 */           bresp = new Buffer(StringUtils.getBytes(this.password, this.connection.getPasswordCharacterEncoding()));
/* 111 */         } catch (UnsupportedEncodingException e) {
/* 112 */           throw SQLError.createSQLException(Messages.getString("Sha256PasswordPlugin.3", new Object[] { this.connection.getPasswordCharacterEncoding() }), "S1000", null);
/*     */         } 
/*     */ 
/*     */         
/* 116 */         bresp.setPosition(bresp.getBufLength());
/* 117 */         int oldBufLength = bresp.getBufLength();
/* 118 */         bresp.writeByte((byte)0);
/* 119 */         bresp.setBufLength(oldBufLength + 1);
/* 120 */         bresp.setPosition(0);
/* 121 */         toServer.add(bresp);
/*     */       }
/* 123 */       else if (this.connection.getServerRSAPublicKeyFile() != null) {
/*     */         
/* 125 */         Buffer bresp = new Buffer(encryptPassword());
/* 126 */         toServer.add(bresp);
/*     */       } else {
/*     */         
/* 129 */         if (!this.connection.getAllowPublicKeyRetrieval()) {
/* 130 */           throw SQLError.createSQLException(Messages.getString("Sha256PasswordPlugin.2"), "08001", this.connection.getExceptionInterceptor());
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 135 */         if (this.publicKeyRequested && fromServer.getBufLength() > 20) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 140 */           this.publicKeyString = fromServer.readString();
/* 141 */           Buffer bresp = new Buffer(encryptPassword());
/* 142 */           toServer.add(bresp);
/* 143 */           this.publicKeyRequested = false;
/*     */         } else {
/*     */           
/* 146 */           Buffer bresp = new Buffer(new byte[] { 2 });
/* 147 */           toServer.add(bresp);
/* 148 */           this.publicKeyRequested = true;
/*     */         } 
/*     */       } 
/*     */     } 
/* 152 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected byte[] encryptPassword() throws SQLException {
/* 157 */     if (this.connection.versionMeetsMinimum(8, 0, 5)) {
/* 158 */       return super.encryptPassword();
/*     */     }
/* 160 */     return encryptPassword("RSA/ECB/PKCS1Padding");
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 165 */     this.stage = AuthStage.FAST_AUTH_SEND_SCRAMBLE;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\authentication\CachingSha2PasswordPlugin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */