/*    */ package com.mysql.jdbc.util;
/*    */ 
/*    */ import com.mysql.jdbc.TimeUtil;
/*    */ import java.sql.DriverManager;
/*    */ import java.sql.ResultSet;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TimezoneDump
/*    */ {
/*    */   private static final String DEFAULT_URL = "jdbc:mysql:///test";
/*    */   
/*    */   public static void main(String[] args) throws Exception {
/* 53 */     String jdbcUrl = "jdbc:mysql:///test";
/*    */     
/* 55 */     if (args.length == 1 && args[0] != null) {
/* 56 */       jdbcUrl = args[0];
/*    */     }
/*    */     
/* 59 */     Class.forName("com.mysql.jdbc.Driver").newInstance();
/*    */     
/* 61 */     ResultSet rs = null;
/*    */     
/*    */     try {
/* 64 */       rs = DriverManager.getConnection(jdbcUrl).createStatement().executeQuery("SHOW VARIABLES LIKE 'timezone'");
/*    */       
/* 66 */       while (rs.next()) {
/* 67 */         String timezoneFromServer = rs.getString(2);
/* 68 */         System.out.println("MySQL timezone name: " + timezoneFromServer);
/*    */         
/* 70 */         String canonicalTimezone = TimeUtil.getCanonicalTimezone(timezoneFromServer, null);
/* 71 */         System.out.println("Java timezone name: " + canonicalTimezone);
/*    */       } 
/*    */     } finally {
/* 74 */       if (rs != null)
/* 75 */         rs.close(); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdb\\util\TimezoneDump.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */