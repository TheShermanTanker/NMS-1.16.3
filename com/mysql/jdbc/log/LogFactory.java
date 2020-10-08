/*     */ package com.mysql.jdbc.log;
/*     */ 
/*     */ import com.mysql.jdbc.ExceptionInterceptor;
/*     */ import com.mysql.jdbc.SQLError;
/*     */ import com.mysql.jdbc.Util;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.sql.SQLException;
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
/*     */ public class LogFactory
/*     */ {
/*     */   public static Log getLogger(String className, String instanceName, ExceptionInterceptor exceptionInterceptor) throws SQLException {
/*  53 */     if (className == null) {
/*  54 */       throw SQLError.createSQLException("Logger class can not be NULL", "S1009", exceptionInterceptor);
/*     */     }
/*     */     
/*  57 */     if (instanceName == null) {
/*  58 */       throw SQLError.createSQLException("Logger instance name can not be NULL", "S1009", exceptionInterceptor);
/*     */     }
/*     */     
/*     */     try {
/*  62 */       Class<?> loggerClass = null;
/*     */       
/*     */       try {
/*  65 */         loggerClass = Class.forName(className);
/*  66 */       } catch (ClassNotFoundException nfe) {
/*  67 */         loggerClass = Class.forName(Util.getPackageName(Log.class) + "." + className);
/*     */       } 
/*     */       
/*  70 */       Constructor<?> constructor = loggerClass.getConstructor(new Class[] { String.class });
/*     */       
/*  72 */       return (Log)constructor.newInstance(new Object[] { instanceName });
/*  73 */     } catch (ClassNotFoundException cnfe) {
/*  74 */       SQLException sqlEx = SQLError.createSQLException("Unable to load class for logger '" + className + "'", "S1009", exceptionInterceptor);
/*     */       
/*  76 */       sqlEx.initCause(cnfe);
/*     */       
/*  78 */       throw sqlEx;
/*  79 */     } catch (NoSuchMethodException nsme) {
/*  80 */       SQLException sqlEx = SQLError.createSQLException("Logger class does not have a single-arg constructor that takes an instance name", "S1009", exceptionInterceptor);
/*     */       
/*  82 */       sqlEx.initCause(nsme);
/*     */       
/*  84 */       throw sqlEx;
/*  85 */     } catch (InstantiationException inse) {
/*  86 */       SQLException sqlEx = SQLError.createSQLException("Unable to instantiate logger class '" + className + "', exception in constructor?", "S1009", exceptionInterceptor);
/*     */       
/*  88 */       sqlEx.initCause(inse);
/*     */       
/*  90 */       throw sqlEx;
/*  91 */     } catch (InvocationTargetException ite) {
/*  92 */       SQLException sqlEx = SQLError.createSQLException("Unable to instantiate logger class '" + className + "', exception in constructor?", "S1009", exceptionInterceptor);
/*     */       
/*  94 */       sqlEx.initCause(ite);
/*     */       
/*  96 */       throw sqlEx;
/*  97 */     } catch (IllegalAccessException iae) {
/*  98 */       SQLException sqlEx = SQLError.createSQLException("Unable to instantiate logger class '" + className + "', constructor not public", "S1009", exceptionInterceptor);
/*     */       
/* 100 */       sqlEx.initCause(iae);
/*     */       
/* 102 */       throw sqlEx;
/* 103 */     } catch (ClassCastException cce) {
/* 104 */       SQLException sqlEx = SQLError.createSQLException("Logger class '" + className + "' does not implement the '" + Log.class.getName() + "' interface", "S1009", exceptionInterceptor);
/*     */       
/* 106 */       sqlEx.initCause(cce);
/*     */       
/* 108 */       throw sqlEx;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\log\LogFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */