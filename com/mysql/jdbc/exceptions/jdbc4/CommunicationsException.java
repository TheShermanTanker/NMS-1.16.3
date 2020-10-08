/*    */ package com.mysql.jdbc.exceptions.jdbc4;
/*    */ 
/*    */ import com.mysql.jdbc.Messages;
/*    */ import com.mysql.jdbc.MySQLConnection;
/*    */ import com.mysql.jdbc.SQLError;
/*    */ import com.mysql.jdbc.StreamingNotifiable;
/*    */ import java.sql.SQLRecoverableException;
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
/*    */ 
/*    */ 
/*    */ public class CommunicationsException
/*    */   extends SQLRecoverableException
/*    */   implements StreamingNotifiable
/*    */ {
/*    */   static final long serialVersionUID = 4317904269797988677L;
/*    */   private String exceptionMessage;
/*    */   
/*    */   public CommunicationsException(MySQLConnection conn, long lastPacketSentTimeMs, long lastPacketReceivedTimeMs, Exception underlyingException) {
/* 47 */     this.exceptionMessage = SQLError.createLinkFailureMessageBasedOnHeuristics(conn, lastPacketSentTimeMs, lastPacketReceivedTimeMs, underlyingException);
/*    */     
/* 49 */     if (underlyingException != null) {
/* 50 */       initCause(underlyingException);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getMessage() {
/* 58 */     return this.exceptionMessage;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getSQLState() {
/* 65 */     return "08S01";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setWasStreamingResults() {
/* 73 */     this.exceptionMessage = Messages.getString("CommunicationsException.ClientWasStreaming");
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\exceptions\jdbc4\CommunicationsException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */