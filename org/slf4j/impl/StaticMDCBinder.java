/*    */ package org.slf4j.impl;
/*    */ 
/*    */ import org.apache.logging.slf4j.Log4jMDCAdapter;
/*    */ import org.slf4j.spi.MDCAdapter;
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
/*    */ public final class StaticMDCBinder
/*    */ {
/* 30 */   public static final StaticMDCBinder SINGLETON = new StaticMDCBinder();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MDCAdapter getMDCA() {
/* 40 */     return (MDCAdapter)new Log4jMDCAdapter();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getMDCAdapterClassStr() {
/* 48 */     return Log4jMDCAdapter.class.getName();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\slf4j\impl\StaticMDCBinder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */