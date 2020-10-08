/*    */ package org.jline.terminal;
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
/*    */ public class Cursor
/*    */ {
/*    */   private final int x;
/*    */   private final int y;
/*    */   
/*    */   public Cursor(int x, int y) {
/* 22 */     this.x = x;
/* 23 */     this.y = y;
/*    */   }
/*    */   
/*    */   public int getX() {
/* 27 */     return this.x;
/*    */   }
/*    */   
/*    */   public int getY() {
/* 31 */     return this.y;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object o) {
/* 36 */     if (o instanceof Cursor) {
/* 37 */       Cursor c = (Cursor)o;
/* 38 */       return (this.x == c.x && this.y == c.y);
/*    */     } 
/* 40 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 46 */     return this.x * 31 + this.y;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 51 */     return "Cursor[x=" + this.x + ", y=" + this.y + ']';
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\terminal\Cursor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */