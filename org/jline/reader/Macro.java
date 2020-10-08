/*    */ package org.jline.reader;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Macro
/*    */   implements Binding
/*    */ {
/*    */   private final String sequence;
/*    */   
/*    */   public Macro(String sequence) {
/* 16 */     this.sequence = sequence;
/*    */   }
/*    */   
/*    */   public String getSequence() {
/* 20 */     return this.sequence;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object o) {
/* 25 */     if (this == o) return true; 
/* 26 */     if (o == null || getClass() != o.getClass()) return false; 
/* 27 */     Macro macro = (Macro)o;
/* 28 */     return this.sequence.equals(macro.sequence);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 33 */     return this.sequence.hashCode();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 38 */     return "Macro[" + this.sequence + ']';
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\reader\Macro.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */