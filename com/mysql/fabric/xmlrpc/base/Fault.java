/*    */ package com.mysql.fabric.xmlrpc.base;
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
/*    */ public class Fault
/*    */ {
/*    */   protected Value value;
/*    */   
/*    */   public Value getValue() {
/* 34 */     return this.value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setValue(Value value) {
/* 41 */     this.value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 46 */     StringBuilder sb = new StringBuilder();
/* 47 */     if (this.value != null) {
/* 48 */       sb.append("<fault>");
/* 49 */       sb.append(this.value.toString());
/* 50 */       sb.append("</fault>");
/*    */     } 
/* 52 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\fabric\xmlrpc\base\Fault.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */