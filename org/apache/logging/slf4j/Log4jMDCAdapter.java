/*    */ package org.apache.logging.slf4j;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.apache.logging.log4j.ThreadContext;
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
/*    */ public class Log4jMDCAdapter
/*    */   implements MDCAdapter
/*    */ {
/*    */   public void put(String key, String val) {
/* 31 */     ThreadContext.put(key, val);
/*    */   }
/*    */ 
/*    */   
/*    */   public String get(String key) {
/* 36 */     return ThreadContext.get(key);
/*    */   }
/*    */ 
/*    */   
/*    */   public void remove(String key) {
/* 41 */     ThreadContext.remove(key);
/*    */   }
/*    */ 
/*    */   
/*    */   public void clear() {
/* 46 */     ThreadContext.clearMap();
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, String> getCopyOfContextMap() {
/* 51 */     return ThreadContext.getContext();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setContextMap(Map map) {
/* 57 */     ThreadContext.clearMap();
/* 58 */     for (Map.Entry<String, String> entry : (Iterable<Map.Entry<String, String>>)map.entrySet())
/* 59 */       ThreadContext.put(entry.getKey(), entry.getValue()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\slf4j\Log4jMDCAdapter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */