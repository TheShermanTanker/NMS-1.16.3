/*    */ package com.mysql.jdbc;
/*    */ 
/*    */ import java.sql.Driver;
/*    */ import java.sql.DriverManager;
/*    */ import java.sql.SQLException;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ReplicationDriver
/*    */   extends NonRegisteringReplicationDriver
/*    */   implements Driver
/*    */ {
/*    */   static {
/*    */     try {
/* 49 */       DriverManager.registerDriver(new NonRegisteringReplicationDriver());
/* 50 */     } catch (SQLException E) {
/* 51 */       throw new RuntimeException("Can't register driver!");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\ReplicationDriver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */