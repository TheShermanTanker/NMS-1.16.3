/*    */ package org.fusesource.hawtjni.runtime;
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
/*    */ public class PointerMath
/*    */ {
/* 17 */   private static final boolean bits32 = (Library.getBitModel() == 32);
/*    */   
/*    */   public static final long add(long ptr, long n) {
/* 20 */     if (bits32) {
/* 21 */       return (int)(ptr + n);
/*    */     }
/* 23 */     return ptr + n;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\fusesource\hawtjni\runtime\PointerMath.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */