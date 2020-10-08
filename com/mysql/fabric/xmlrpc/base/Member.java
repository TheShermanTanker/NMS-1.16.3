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
/*    */ public class Member
/*    */ {
/*    */   protected String name;
/*    */   protected Value value;
/*    */   
/*    */   public Member() {}
/*    */   
/*    */   public Member(String name, Value value) {
/* 35 */     setName(name);
/* 36 */     setValue(value);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 43 */     return this.name;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setName(String value) {
/* 50 */     this.name = value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Value getValue() {
/* 57 */     return this.value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setValue(Value value) {
/* 64 */     this.value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 69 */     StringBuilder sb = new StringBuilder();
/* 70 */     sb.append("<member>");
/* 71 */     sb.append("<name>" + this.name + "</name>");
/* 72 */     sb.append(this.value.toString());
/* 73 */     sb.append("</member>");
/* 74 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\fabric\xmlrpc\base\Member.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */