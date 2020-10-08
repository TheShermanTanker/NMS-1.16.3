/*     */ package com.mysql.jdbc.jdbc2.optional;
/*     */ 
/*     */ import com.mysql.jdbc.ExceptionInterceptor;
/*     */ import com.mysql.jdbc.Util;
/*     */ import java.lang.reflect.InvocationHandler;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Proxy;
/*     */ import java.sql.SQLException;
/*     */ import java.util.Map;
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
/*     */ abstract class WrapperBase
/*     */ {
/*     */   protected MysqlPooledConnection pooledConnection;
/*     */   
/*     */   protected void checkAndFireConnectionError(SQLException sqlEx) throws SQLException {
/*  52 */     if (this.pooledConnection != null && 
/*  53 */       "08S01".equals(sqlEx.getSQLState())) {
/*  54 */       this.pooledConnection.callConnectionEventListeners(1, sqlEx);
/*     */     }
/*     */ 
/*     */     
/*  58 */     throw sqlEx;
/*     */   }
/*     */   
/*  61 */   protected Map<Class<?>, Object> unwrappedInterfaces = null;
/*     */   protected ExceptionInterceptor exceptionInterceptor;
/*     */   
/*     */   protected WrapperBase(MysqlPooledConnection pooledConnection) {
/*  65 */     this.pooledConnection = pooledConnection;
/*  66 */     this.exceptionInterceptor = this.pooledConnection.getExceptionInterceptor();
/*     */   }
/*     */   
/*     */   protected class ConnectionErrorFiringInvocationHandler implements InvocationHandler {
/*  70 */     Object invokeOn = null;
/*     */     
/*     */     public ConnectionErrorFiringInvocationHandler(Object toInvokeOn) {
/*  73 */       this.invokeOn = toInvokeOn;
/*     */     }
/*     */     
/*     */     public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
/*  77 */       if ("equals".equals(method.getName()))
/*     */       {
/*  79 */         return Boolean.valueOf(args[0].equals(this));
/*     */       }
/*     */       
/*  82 */       Object result = null;
/*     */       
/*     */       try {
/*  85 */         result = method.invoke(this.invokeOn, args);
/*     */         
/*  87 */         if (result != null) {
/*  88 */           result = proxyIfInterfaceIsJdbc(result, result.getClass());
/*     */         }
/*  90 */       } catch (InvocationTargetException e) {
/*  91 */         if (e.getTargetException() instanceof SQLException) {
/*  92 */           WrapperBase.this.checkAndFireConnectionError((SQLException)e.getTargetException());
/*     */         } else {
/*  94 */           throw e;
/*     */         } 
/*     */       } 
/*     */       
/*  98 */       return result;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private Object proxyIfInterfaceIsJdbc(Object toProxy, Class<?> clazz) {
/* 110 */       Class<?>[] interfaces = clazz.getInterfaces();
/*     */       
/* 112 */       Class[] arr$ = interfaces; int len$ = arr$.length, i$ = 0; if (i$ < len$) { Class<?> iclass = arr$[i$];
/* 113 */         String packageName = Util.getPackageName(iclass);
/*     */         
/* 115 */         if ("java.sql".equals(packageName) || "javax.sql".equals(packageName)) {
/* 116 */           return Proxy.newProxyInstance(toProxy.getClass().getClassLoader(), interfaces, new ConnectionErrorFiringInvocationHandler(toProxy));
/*     */         }
/*     */         
/* 119 */         return proxyIfInterfaceIsJdbc(toProxy, iclass); }
/*     */ 
/*     */       
/* 122 */       return toProxy;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\jdbc2\optional\WrapperBase.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */