/*    */ package com.mysql.fabric.xmlrpc.base;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
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
/*    */ public class Struct
/*    */ {
/*    */   protected List<Member> member;
/*    */   
/*    */   public List<Member> getMember() {
/* 34 */     if (this.member == null) {
/* 35 */       this.member = new ArrayList<Member>();
/*    */     }
/* 37 */     return this.member;
/*    */   }
/*    */   
/*    */   public void addMember(Member m) {
/* 41 */     getMember().add(m);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 46 */     StringBuilder sb = new StringBuilder();
/* 47 */     if (this.member != null) {
/* 48 */       sb.append("<struct>");
/* 49 */       for (int i = 0; i < this.member.size(); i++) {
/* 50 */         sb.append(((Member)this.member.get(i)).toString());
/*    */       }
/* 52 */       sb.append("</struct>");
/*    */     } 
/* 54 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\fabric\xmlrpc\base\Struct.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */