/*    */ package com.lmax.disruptor.util;
/*    */ 
/*    */ import java.lang.invoke.MethodHandle;
/*    */ import java.lang.invoke.MethodHandles;
/*    */ import java.lang.invoke.MethodType;
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
/*    */ public final class ThreadHints
/*    */ {
/*    */   private static final MethodHandle ON_SPIN_WAIT_METHOD_HANDLE;
/*    */   
/*    */   static {
/* 37 */     MethodHandles.Lookup lookup = MethodHandles.lookup();
/*    */     
/* 39 */     MethodHandle methodHandle = null;
/*    */     
/*    */     try {
/* 42 */       methodHandle = lookup.findStatic(Thread.class, "onSpinWait", MethodType.methodType(void.class));
/*    */     }
/* 44 */     catch (Exception exception) {}
/*    */ 
/*    */ 
/*    */     
/* 48 */     ON_SPIN_WAIT_METHOD_HANDLE = methodHandle;
/*    */   }
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
/*    */   public static void onSpinWait() {
/* 66 */     if (null != ON_SPIN_WAIT_METHOD_HANDLE)
/*    */       
/*    */       try {
/*    */         
/* 70 */         ON_SPIN_WAIT_METHOD_HANDLE.invokeExact();
/*    */       }
/* 72 */       catch (Throwable throwable) {} 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\lmax\disrupto\\util\ThreadHints.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */