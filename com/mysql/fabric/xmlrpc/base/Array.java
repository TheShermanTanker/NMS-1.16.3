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
/*    */ public class Array
/*    */ {
/*    */   protected Data data;
/*    */   
/*    */   public Data getData() {
/* 34 */     return this.data;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setData(Data value) {
/* 41 */     this.data = value;
/*    */   }
/*    */   
/*    */   public void addValue(Value v) {
/* 45 */     if (this.data == null) {
/* 46 */       this.data = new Data();
/*    */     }
/* 48 */     this.data.addValue(v);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     StringBuilder sb = new StringBuilder("<array>");
/* 54 */     sb.append(this.data.toString());
/* 55 */     sb.append("</array>");
/* 56 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\fabric\xmlrpc\base\Array.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */