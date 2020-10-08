/*    */ package org.slf4j.impl;
/*    */ 
/*    */ import org.apache.logging.slf4j.Log4jMarkerFactory;
/*    */ import org.slf4j.IMarkerFactory;
/*    */ import org.slf4j.spi.MarkerFactoryBinder;
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
/*    */ public class StaticMarkerBinder
/*    */   implements MarkerFactoryBinder
/*    */ {
/* 32 */   public static final StaticMarkerBinder SINGLETON = new StaticMarkerBinder();
/*    */   
/* 34 */   private final IMarkerFactory markerFactory = (IMarkerFactory)new Log4jMarkerFactory();
/*    */ 
/*    */   
/*    */   public IMarkerFactory getMarkerFactory() {
/* 38 */     return this.markerFactory;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMarkerFactoryClassStr() {
/* 43 */     return Log4jMarkerFactory.class.getName();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\slf4j\impl\StaticMarkerBinder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */