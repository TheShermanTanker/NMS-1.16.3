/*     */ package com.mysql.jdbc;
/*     */ 
/*     */ import java.net.InetAddress;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.net.SocketAddress;
/*     */ import java.net.UnknownHostException;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
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
/*     */ 
/*     */ 
/*     */ public interface SocketMetadata
/*     */ {
/*     */   boolean isLocallyConnected(ConnectionImpl paramConnectionImpl) throws SQLException;
/*     */   
/*     */   public static class Helper
/*     */   {
/*     */     public static final String IS_LOCAL_HOSTNAME_REPLACEMENT_PROPERTY_NAME = "com.mysql.jdbc.test.isLocalHostnameReplacement";
/*     */     
/*     */     public static boolean isLocallyConnected(ConnectionImpl conn) throws SQLException {
/*  48 */       long threadId = conn.getId();
/*  49 */       Statement processListStmt = conn.getMetadataSafeStatement();
/*  50 */       ResultSet rs = null;
/*  51 */       String processHost = null;
/*     */ 
/*     */       
/*  54 */       if (System.getProperty("com.mysql.jdbc.test.isLocalHostnameReplacement") != null) {
/*  55 */         processHost = System.getProperty("com.mysql.jdbc.test.isLocalHostnameReplacement");
/*     */       }
/*  57 */       else if (conn.getProperties().getProperty("com.mysql.jdbc.test.isLocalHostnameReplacement") != null) {
/*  58 */         processHost = conn.getProperties().getProperty("com.mysql.jdbc.test.isLocalHostnameReplacement");
/*     */       } else {
/*     */         
/*     */         try {
/*  62 */           processHost = findProcessHost(threadId, processListStmt);
/*     */           
/*  64 */           if (processHost == null) {
/*     */             
/*  66 */             conn.getLog().logWarn(String.format("Connection id %d not found in \"SHOW PROCESSLIST\", assuming 32-bit overflow, using SELECT CONNECTION_ID() instead", new Object[] { Long.valueOf(threadId) }));
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*  71 */             rs = processListStmt.executeQuery("SELECT CONNECTION_ID()");
/*     */             
/*  73 */             if (rs.next()) {
/*  74 */               threadId = rs.getLong(1);
/*     */               
/*  76 */               processHost = findProcessHost(threadId, processListStmt);
/*     */             } else {
/*  78 */               conn.getLog().logError("No rows returned for statement \"SELECT CONNECTION_ID()\", local connection check will most likely be incorrect");
/*     */             } 
/*     */           } 
/*     */         } finally {
/*     */           
/*  83 */           processListStmt.close();
/*     */         } 
/*     */       } 
/*     */       
/*  87 */       if (processHost != null) {
/*  88 */         conn.getLog().logDebug(Messages.getString("SocketMetadata.0", new Object[] { processHost }));
/*     */         
/*  90 */         int endIndex = processHost.lastIndexOf(":");
/*  91 */         if (endIndex != -1) {
/*  92 */           processHost = processHost.substring(0, endIndex);
/*     */         }
/*     */ 
/*     */         
/*     */         try {
/*  97 */           InetAddress[] allHostAddr = InetAddress.getAllByName(processHost);
/*     */           
/*  99 */           SocketAddress remoteSocketAddr = (conn.getIO()).mysqlConnection.getRemoteSocketAddress();
/*     */           
/* 101 */           if (remoteSocketAddr instanceof InetSocketAddress) {
/* 102 */             InetAddress whereIConnectedTo = ((InetSocketAddress)remoteSocketAddr).getAddress();
/*     */             
/* 104 */             for (InetAddress hostAddr : allHostAddr) {
/* 105 */               if (hostAddr.equals(whereIConnectedTo)) {
/* 106 */                 conn.getLog().logDebug(Messages.getString("SocketMetadata.1", new Object[] { hostAddr, whereIConnectedTo }));
/* 107 */                 return true;
/*     */               } 
/* 109 */               conn.getLog().logDebug(Messages.getString("SocketMetadata.2", new Object[] { hostAddr, whereIConnectedTo }));
/*     */             } 
/*     */           } else {
/*     */             
/* 113 */             conn.getLog().logDebug(Messages.getString("SocketMetadata.3", new Object[] { remoteSocketAddr }));
/*     */           } 
/*     */           
/* 116 */           return false;
/* 117 */         } catch (UnknownHostException e) {
/* 118 */           conn.getLog().logWarn(Messages.getString("Connection.CantDetectLocalConnect", new Object[] { processHost }), e);
/*     */           
/* 120 */           return false;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 125 */       return false;
/*     */     }
/*     */     
/*     */     private static String findProcessHost(long threadId, Statement processListStmt) throws SQLException {
/* 129 */       String processHost = null;
/* 130 */       String ps = ((MySQLConnection)processListStmt.getConnection()).getServerVariable("performance_schema");
/*     */       
/* 132 */       ResultSet rs = (((MySQLConnection)processListStmt.getConnection()).versionMeetsMinimum(5, 6, 0) && ps != null && ("1".contentEquals(ps) || "ON".contentEquals(ps))) ? processListStmt.executeQuery("select PROCESSLIST_ID, PROCESSLIST_USER, PROCESSLIST_HOST from performance_schema.threads where PROCESSLIST_ID=" + threadId) : processListStmt.executeQuery("SHOW PROCESSLIST");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 139 */       while (rs.next()) {
/* 140 */         long id = rs.getLong(1);
/*     */         
/* 142 */         if (threadId == id) {
/* 143 */           processHost = rs.getString(3);
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 148 */       return processHost;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\SocketMetadata.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */