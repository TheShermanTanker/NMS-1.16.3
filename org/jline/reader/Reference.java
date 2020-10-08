/*    */ package org.jline.reader;
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
/*    */ public class Reference
/*    */   implements Binding
/*    */ {
/*    */   private final String name;
/*    */   
/*    */   public Reference(String name) {
/* 19 */     this.name = name;
/*    */   }
/*    */   
/*    */   public String name() {
/* 23 */     return this.name;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object o) {
/* 28 */     if (this == o) return true; 
/* 29 */     if (o == null || getClass() != o.getClass()) return false; 
/* 30 */     Reference func = (Reference)o;
/* 31 */     return this.name.equals(func.name);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 36 */     return this.name.hashCode();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 41 */     return "Reference[" + this.name + ']';
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\reader\Reference.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */