/*    */ package com.mysql.jdbc;
/*    */ 
/*    */ import java.text.MessageFormat;
/*    */ import java.util.Locale;
/*    */ import java.util.MissingResourceException;
/*    */ import java.util.ResourceBundle;
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
/*    */ public class Messages
/*    */ {
/*    */   private static final String BUNDLE_NAME = "com.mysql.jdbc.LocalizedErrorMessages";
/*    */   private static final ResourceBundle RESOURCE_BUNDLE;
/*    */   
/*    */   static {
/* 41 */     ResourceBundle temp = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     try {
/* 49 */       temp = ResourceBundle.getBundle("com.mysql.jdbc.LocalizedErrorMessages", Locale.getDefault(), Messages.class.getClassLoader());
/* 50 */     } catch (Throwable t) {
/*    */       try {
/* 52 */         temp = ResourceBundle.getBundle("com.mysql.jdbc.LocalizedErrorMessages");
/* 53 */       } catch (Throwable t2) {
/* 54 */         RuntimeException rt = new RuntimeException("Can't load resource bundle due to underlying exception " + t.toString());
/* 55 */         rt.initCause(t2);
/*    */         
/* 57 */         throw rt;
/*    */       } 
/*    */     } finally {
/* 60 */       RESOURCE_BUNDLE = temp;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getString(String key) {
/* 72 */     if (RESOURCE_BUNDLE == null) {
/* 73 */       throw new RuntimeException("Localized messages from resource bundle 'com.mysql.jdbc.LocalizedErrorMessages' not loaded during initialization of driver.");
/*    */     }
/*    */     
/*    */     try {
/* 77 */       if (key == null) {
/* 78 */         throw new IllegalArgumentException("Message key can not be null");
/*    */       }
/*    */       
/* 81 */       String message = RESOURCE_BUNDLE.getString(key);
/*    */       
/* 83 */       if (message == null) {
/* 84 */         message = "Missing error message for key '" + key + "'";
/*    */       }
/*    */       
/* 87 */       return message;
/* 88 */     } catch (MissingResourceException e) {
/* 89 */       return '!' + key + '!';
/*    */     } 
/*    */   }
/*    */   
/*    */   public static String getString(String key, Object[] args) {
/* 94 */     return MessageFormat.format(getString(key), args);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\Messages.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */