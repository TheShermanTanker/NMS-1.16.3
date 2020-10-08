/*    */ package com.mysql.jdbc;
/*    */ 
/*    */ import java.sql.Connection;
/*    */ import java.sql.SQLClientInfoException;
/*    */ import java.sql.SQLException;
/*    */ import java.util.Enumeration;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Properties;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JDBC4CommentClientInfoProvider
/*    */   implements JDBC4ClientInfoProvider
/*    */ {
/*    */   private Properties clientInfo;
/*    */   
/*    */   public synchronized void initialize(Connection conn, Properties configurationProps) throws SQLException {
/* 45 */     this.clientInfo = new Properties();
/*    */   }
/*    */   
/*    */   public synchronized void destroy() throws SQLException {
/* 49 */     this.clientInfo = null;
/*    */   }
/*    */   
/*    */   public synchronized Properties getClientInfo(Connection conn) throws SQLException {
/* 53 */     return this.clientInfo;
/*    */   }
/*    */   
/*    */   public synchronized String getClientInfo(Connection conn, String name) throws SQLException {
/* 57 */     return this.clientInfo.getProperty(name);
/*    */   }
/*    */   
/*    */   public synchronized void setClientInfo(Connection conn, Properties properties) throws SQLClientInfoException {
/* 61 */     this.clientInfo = new Properties();
/*    */     
/* 63 */     Enumeration<?> propNames = properties.propertyNames();
/*    */     
/* 65 */     while (propNames.hasMoreElements()) {
/* 66 */       String name = (String)propNames.nextElement();
/*    */       
/* 68 */       this.clientInfo.put(name, properties.getProperty(name));
/*    */     } 
/*    */     
/* 71 */     setComment(conn);
/*    */   }
/*    */   
/*    */   public synchronized void setClientInfo(Connection conn, String name, String value) throws SQLClientInfoException {
/* 75 */     this.clientInfo.setProperty(name, value);
/* 76 */     setComment(conn);
/*    */   }
/*    */   
/*    */   private synchronized void setComment(Connection conn) {
/* 80 */     StringBuilder commentBuf = new StringBuilder();
/* 81 */     Iterator<Map.Entry<Object, Object>> elements = this.clientInfo.entrySet().iterator();
/*    */     
/* 83 */     while (elements.hasNext()) {
/* 84 */       if (commentBuf.length() > 0) {
/* 85 */         commentBuf.append(", ");
/*    */       }
/*    */       
/* 88 */       Map.Entry<Object, Object> entry = elements.next();
/* 89 */       commentBuf.append("" + entry.getKey());
/* 90 */       commentBuf.append("=");
/* 91 */       commentBuf.append("" + entry.getValue());
/*    */     } 
/*    */     
/* 94 */     ((Connection)conn).setStatementComment(commentBuf.toString());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\JDBC4CommentClientInfoProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */