/*    */ package com.mysql.jdbc.log;
/*    */ 
/*    */ import com.mysql.jdbc.Util;
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
/*    */ public class LogUtils
/*    */ {
/*    */   public static final String CALLER_INFORMATION_NOT_AVAILABLE = "Caller information not available";
/* 32 */   private static final String LINE_SEPARATOR = System.getProperty("line.separator");
/*    */   
/* 34 */   private static final int LINE_SEPARATOR_LENGTH = LINE_SEPARATOR.length();
/*    */   
/*    */   public static String findCallingClassAndMethod(Throwable t) {
/* 37 */     String stackTraceAsString = Util.stackTraceToString(t);
/*    */     
/* 39 */     String callingClassAndMethod = "Caller information not available";
/*    */     
/* 41 */     int endInternalMethods = stackTraceAsString.lastIndexOf("com.mysql.jdbc");
/*    */     
/* 43 */     if (endInternalMethods != -1) {
/* 44 */       int endOfLine = -1;
/* 45 */       int compliancePackage = stackTraceAsString.indexOf("com.mysql.jdbc.compliance", endInternalMethods);
/*    */       
/* 47 */       if (compliancePackage != -1) {
/* 48 */         endOfLine = compliancePackage - LINE_SEPARATOR_LENGTH;
/*    */       } else {
/* 50 */         endOfLine = stackTraceAsString.indexOf(LINE_SEPARATOR, endInternalMethods);
/*    */       } 
/*    */       
/* 53 */       if (endOfLine != -1) {
/* 54 */         int nextEndOfLine = stackTraceAsString.indexOf(LINE_SEPARATOR, endOfLine + LINE_SEPARATOR_LENGTH);
/*    */         
/* 56 */         if (nextEndOfLine != -1) {
/* 57 */           callingClassAndMethod = stackTraceAsString.substring(endOfLine + LINE_SEPARATOR_LENGTH, nextEndOfLine);
/*    */         } else {
/* 59 */           callingClassAndMethod = stackTraceAsString.substring(endOfLine + LINE_SEPARATOR_LENGTH);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 64 */     if (!callingClassAndMethod.startsWith("\tat ") && !callingClassAndMethod.startsWith("at ")) {
/* 65 */       return "at " + callingClassAndMethod;
/*    */     }
/*    */     
/* 68 */     return callingClassAndMethod;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\log\LogUtils.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */