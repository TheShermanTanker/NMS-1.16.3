/*     */ package com.mysql.jdbc.authentication;
/*     */ 
/*     */ import com.mysql.jdbc.AuthenticationPlugin;
/*     */ import com.mysql.jdbc.Buffer;
/*     */ import com.mysql.jdbc.Connection;
/*     */ import com.mysql.jdbc.ExportControlled;
/*     */ import com.mysql.jdbc.Messages;
/*     */ import com.mysql.jdbc.MySQLConnection;
/*     */ import com.mysql.jdbc.SQLError;
/*     */ import com.mysql.jdbc.Security;
/*     */ import com.mysql.jdbc.StringUtils;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.UnsupportedEncodingException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Sha256PasswordPlugin
/*     */   implements AuthenticationPlugin
/*     */ {
/*  50 */   public static String PLUGIN_NAME = "sha256_password";
/*     */   
/*     */   protected Connection connection;
/*  53 */   protected String password = null;
/*  54 */   protected String seed = null;
/*     */   protected boolean publicKeyRequested = false;
/*  56 */   protected String publicKeyString = null;
/*     */   
/*     */   public void init(Connection conn, Properties props) throws SQLException {
/*  59 */     this.connection = conn;
/*     */     
/*  61 */     String pkURL = this.connection.getServerRSAPublicKeyFile();
/*  62 */     if (pkURL != null) {
/*  63 */       this.publicKeyString = readRSAKey(this.connection, pkURL);
/*     */     }
/*     */   }
/*     */   
/*     */   public void destroy() {
/*  68 */     this.password = null;
/*  69 */     this.seed = null;
/*  70 */     this.publicKeyRequested = false;
/*     */   }
/*     */   
/*     */   public String getProtocolPluginName() {
/*  74 */     return PLUGIN_NAME;
/*     */   }
/*     */   
/*     */   public boolean requiresConfidentiality() {
/*  78 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isReusable() {
/*  82 */     return true;
/*     */   }
/*     */   
/*     */   public void setAuthenticationParameters(String user, String password) {
/*  86 */     this.password = password;
/*     */   }
/*     */   
/*     */   public boolean nextAuthenticationStep(Buffer fromServer, List<Buffer> toServer) throws SQLException {
/*  90 */     toServer.clear();
/*     */     
/*  92 */     if (this.password == null || this.password.length() == 0 || fromServer == null) {
/*     */       
/*  94 */       Buffer bresp = new Buffer(new byte[] { 0 });
/*  95 */       toServer.add(bresp);
/*     */     }
/*  97 */     else if (((MySQLConnection)this.connection).getIO().isSSLEstablished()) {
/*     */       Buffer bresp;
/*     */       
/*     */       try {
/* 101 */         bresp = new Buffer(StringUtils.getBytes(this.password, this.connection.getPasswordCharacterEncoding()));
/* 102 */       } catch (UnsupportedEncodingException e) {
/* 103 */         throw SQLError.createSQLException(Messages.getString("Sha256PasswordPlugin.3", new Object[] { this.connection.getPasswordCharacterEncoding() }), "S1000", null);
/*     */       } 
/*     */       
/* 106 */       bresp.setPosition(bresp.getBufLength());
/* 107 */       int oldBufLength = bresp.getBufLength();
/* 108 */       bresp.writeByte((byte)0);
/* 109 */       bresp.setBufLength(oldBufLength + 1);
/* 110 */       bresp.setPosition(0);
/* 111 */       toServer.add(bresp);
/*     */     }
/* 113 */     else if (this.connection.getServerRSAPublicKeyFile() != null) {
/*     */       
/* 115 */       this.seed = fromServer.readString();
/* 116 */       Buffer bresp = new Buffer(encryptPassword());
/* 117 */       toServer.add(bresp);
/*     */     } else {
/*     */       
/* 120 */       if (!this.connection.getAllowPublicKeyRetrieval()) {
/* 121 */         throw SQLError.createSQLException(Messages.getString("Sha256PasswordPlugin.2"), "08001", this.connection.getExceptionInterceptor());
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 126 */       if (this.publicKeyRequested && fromServer.getBufLength() > 20) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 131 */         this.publicKeyString = fromServer.readString();
/* 132 */         Buffer bresp = new Buffer(encryptPassword());
/* 133 */         toServer.add(bresp);
/* 134 */         this.publicKeyRequested = false;
/*     */       } else {
/*     */         
/* 137 */         this.seed = fromServer.readString();
/* 138 */         Buffer bresp = new Buffer(new byte[] { 1 });
/* 139 */         toServer.add(bresp);
/* 140 */         this.publicKeyRequested = true;
/*     */       } 
/*     */     } 
/* 143 */     return true;
/*     */   }
/*     */   
/*     */   protected byte[] encryptPassword() throws SQLException {
/* 147 */     return encryptPassword("RSA/ECB/OAEPWithSHA-1AndMGF1Padding");
/*     */   }
/*     */   
/*     */   protected byte[] encryptPassword(String transformation) throws SQLException {
/* 151 */     byte[] input = null;
/*     */     try {
/* 153 */       (new byte[1])[0] = 0; input = (this.password != null) ? StringUtils.getBytesNullTerminated(this.password, this.connection.getPasswordCharacterEncoding()) : new byte[1];
/*     */     }
/* 155 */     catch (UnsupportedEncodingException e) {
/* 156 */       throw SQLError.createSQLException(Messages.getString("Sha256PasswordPlugin.3", new Object[] { this.connection.getPasswordCharacterEncoding() }), "S1000", null);
/*     */     } 
/*     */     
/* 159 */     byte[] mysqlScrambleBuff = new byte[input.length];
/* 160 */     Security.xorString(input, mysqlScrambleBuff, this.seed.getBytes(), input.length);
/* 161 */     return ExportControlled.encryptWithRSAPublicKey(mysqlScrambleBuff, ExportControlled.decodeRSAPublicKey(this.publicKeyString, this.connection.getExceptionInterceptor()), transformation, this.connection.getExceptionInterceptor());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static String readRSAKey(Connection connection, String pkPath) throws SQLException {
/* 167 */     String res = null;
/* 168 */     byte[] fileBuf = new byte[2048];
/*     */     
/* 170 */     BufferedInputStream fileIn = null;
/*     */     
/*     */     try {
/* 173 */       File f = new File(pkPath);
/* 174 */       String canonicalPath = f.getCanonicalPath();
/* 175 */       fileIn = new BufferedInputStream(new FileInputStream(canonicalPath));
/*     */       
/* 177 */       int bytesRead = 0;
/*     */       
/* 179 */       StringBuilder sb = new StringBuilder();
/* 180 */       while ((bytesRead = fileIn.read(fileBuf)) != -1) {
/* 181 */         sb.append(StringUtils.toAsciiString(fileBuf, 0, bytesRead));
/*     */       }
/* 183 */       res = sb.toString();
/*     */     }
/* 185 */     catch (IOException ioEx) {
/*     */       
/* 187 */       if (connection.getParanoid()) {
/* 188 */         throw SQLError.createSQLException(Messages.getString("Sha256PasswordPlugin.0", new Object[] { "" }), "S1009", connection.getExceptionInterceptor());
/*     */       }
/*     */       
/* 191 */       throw SQLError.createSQLException(Messages.getString("Sha256PasswordPlugin.0", new Object[] { "'" + pkPath + "'" }), "S1009", ioEx, connection.getExceptionInterceptor());
/*     */     }
/*     */     finally {
/*     */       
/* 195 */       if (fileIn != null) {
/*     */         try {
/* 197 */           fileIn.close();
/* 198 */         } catch (Exception ex) {
/* 199 */           SQLException sqlEx = SQLError.createSQLException(Messages.getString("Sha256PasswordPlugin.1"), "S1000", ex, connection.getExceptionInterceptor());
/*     */ 
/*     */           
/* 202 */           throw sqlEx;
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 207 */     return res;
/*     */   }
/*     */   
/*     */   public void reset() {}
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\authentication\Sha256PasswordPlugin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */