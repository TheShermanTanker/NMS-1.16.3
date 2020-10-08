/*    */ package com.mysql.fabric.xmlrpc.exceptions;
/*    */ 
/*    */ import com.mysql.fabric.xmlrpc.base.Fault;
/*    */ import com.mysql.fabric.xmlrpc.base.Member;
/*    */ import com.mysql.fabric.xmlrpc.base.Struct;
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
/*    */ public class MySQLFabricException
/*    */   extends SQLException
/*    */ {
/*    */   static final long serialVersionUID = -8776763137552613517L;
/*    */   
/*    */   public MySQLFabricException() {}
/*    */   
/*    */   public MySQLFabricException(Fault fault) {
/* 40 */     super((String)((Member)((Struct)fault.getValue().getValue()).getMember().get(1)).getValue().getValue(), "", ((Integer)((Member)((Struct)fault.getValue().getValue()).getMember().get(0)).getValue().getValue()).intValue());
/*    */   }
/*    */ 
/*    */   
/*    */   public MySQLFabricException(String reason, String SQLState, int vendorCode) {
/* 45 */     super(reason, SQLState, vendorCode);
/*    */   }
/*    */   
/*    */   public MySQLFabricException(String reason, String SQLState) {
/* 49 */     super(reason, SQLState);
/*    */   }
/*    */   
/*    */   public MySQLFabricException(String reason) {
/* 53 */     super(reason);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\fabric\xmlrpc\exceptions\MySQLFabricException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */