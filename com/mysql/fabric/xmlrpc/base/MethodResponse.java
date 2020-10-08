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
/*    */ public class MethodResponse
/*    */ {
/*    */   protected Params params;
/*    */   protected Fault fault;
/*    */   
/*    */   public Params getParams() {
/* 35 */     return this.params;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setParams(Params value) {
/* 42 */     this.params = value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Fault getFault() {
/* 49 */     return this.fault;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setFault(Fault value) {
/* 56 */     this.fault = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 61 */     StringBuilder sb = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
/* 62 */     sb.append("<methodResponse>");
/* 63 */     if (this.params != null) {
/* 64 */       sb.append(this.params.toString());
/*    */     }
/* 66 */     if (this.fault != null) {
/* 67 */       sb.append(this.fault.toString());
/*    */     }
/* 69 */     sb.append("</methodResponse>");
/* 70 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\fabric\xmlrpc\base\MethodResponse.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */