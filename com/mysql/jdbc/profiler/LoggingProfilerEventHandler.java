/*    */ package com.mysql.jdbc.profiler;
/*    */ 
/*    */ import com.mysql.jdbc.Connection;
/*    */ import com.mysql.jdbc.Constants;
/*    */ import com.mysql.jdbc.MySQLConnection;
/*    */ import com.mysql.jdbc.ResultSetInternalMethods;
/*    */ import com.mysql.jdbc.Statement;
/*    */ import com.mysql.jdbc.log.Log;
/*    */ import java.sql.SQLException;
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
/*    */ public class LoggingProfilerEventHandler
/*    */   implements ProfilerEventHandler
/*    */ {
/*    */   private Log log;
/*    */   
/*    */   public void consumeEvent(ProfilerEvent evt) {
/* 46 */     switch (evt.getEventType()) {
/*    */       case 0:
/* 48 */         this.log.logWarn(evt);
/*    */         return;
/*    */     } 
/*    */     
/* 52 */     this.log.logInfo(evt);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void destroy() {
/* 58 */     this.log = null;
/*    */   }
/*    */   
/*    */   public void init(Connection conn, Properties props) throws SQLException {
/* 62 */     this.log = conn.getLog();
/*    */   }
/*    */ 
/*    */   
/*    */   public void processEvent(byte eventType, MySQLConnection conn, Statement stmt, ResultSetInternalMethods resultSet, long eventDuration, Throwable eventCreationPoint, String message) {
/* 67 */     String catalog = "";
/*    */     try {
/* 69 */       if (conn != null) {
/* 70 */         catalog = conn.getCatalog();
/*    */       }
/* 72 */     } catch (SQLException e) {}
/*    */ 
/*    */     
/* 75 */     consumeEvent(new ProfilerEvent(eventType, (conn == null) ? "" : conn.getHost(), catalog, (conn == null) ? -1L : conn.getId(), (stmt == null) ? -1 : stmt.getId(), (resultSet == null) ? -1 : resultSet.getId(), eventDuration, (conn == null) ? Constants.MILLIS_I18N : conn.getQueryTimingUnits(), eventCreationPoint, message));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\profiler\LoggingProfilerEventHandler.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */