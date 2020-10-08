/*    */ package org.jline.reader.impl;
/*    */ 
/*    */ import org.jline.reader.LineReader;
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
/*    */ public class ReaderUtils
/*    */ {
/*    */   public static boolean isSet(LineReader reader, LineReader.Option option) {
/* 18 */     return (reader != null && reader.isSet(option));
/*    */   }
/*    */   
/*    */   public static String getString(LineReader reader, String name, String def) {
/* 22 */     Object v = (reader != null) ? reader.getVariable(name) : null;
/* 23 */     return (v != null) ? v.toString() : def;
/*    */   }
/*    */   
/*    */   public static boolean getBoolean(LineReader reader, String name, boolean def) {
/* 27 */     Object v = (reader != null) ? reader.getVariable(name) : null;
/* 28 */     if (v instanceof Boolean)
/* 29 */       return ((Boolean)v).booleanValue(); 
/* 30 */     if (v != null) {
/* 31 */       String s = v.toString();
/* 32 */       return (s.isEmpty() || s.equalsIgnoreCase("on") || s
/* 33 */         .equalsIgnoreCase("1") || s.equalsIgnoreCase("true"));
/*    */     } 
/* 35 */     return def;
/*    */   }
/*    */   
/*    */   public static int getInt(LineReader reader, String name, int def) {
/* 39 */     int nb = def;
/* 40 */     Object v = (reader != null) ? reader.getVariable(name) : null;
/* 41 */     if (v instanceof Number)
/* 42 */       return ((Number)v).intValue(); 
/* 43 */     if (v != null) {
/* 44 */       nb = 0;
/*    */       try {
/* 46 */         nb = Integer.parseInt(v.toString());
/* 47 */       } catch (NumberFormatException numberFormatException) {}
/*    */     } 
/*    */ 
/*    */     
/* 51 */     return nb;
/*    */   }
/*    */   
/*    */   public static long getLong(LineReader reader, String name, long def) {
/* 55 */     long nb = def;
/* 56 */     Object v = (reader != null) ? reader.getVariable(name) : null;
/* 57 */     if (v instanceof Number)
/* 58 */       return ((Number)v).longValue(); 
/* 59 */     if (v != null) {
/* 60 */       nb = 0L;
/*    */       try {
/* 62 */         nb = Long.parseLong(v.toString());
/* 63 */       } catch (NumberFormatException numberFormatException) {}
/*    */     } 
/*    */ 
/*    */     
/* 67 */     return nb;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\reader\impl\ReaderUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */