/*    */ package com.mysql.jdbc.util;
/*    */ 
/*    */ import com.mysql.jdbc.ConnectionPropertiesImpl;
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
/*    */ public class PropertiesDocGenerator
/*    */   extends ConnectionPropertiesImpl
/*    */ {
/*    */   static final long serialVersionUID = -4869689139143855383L;
/*    */   
/*    */   public static void main(String[] args) throws SQLException {
/* 38 */     System.out.println((new PropertiesDocGenerator()).exposeAsXml());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdb\\util\PropertiesDocGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */