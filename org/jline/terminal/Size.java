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
/*    */ public class Size
/*    */ {
/*    */   private int rows;
/*    */   private int cols;
/*    */   
/*    */   public Size() {}
/*    */   
/*    */   public Size(int columns, int rows) {
/* 20 */     this();
/* 21 */     setColumns(columns);
/* 22 */     setRows(rows);
/*    */   }
/*    */   
/*    */   public int getColumns() {
/* 26 */     return this.cols;
/*    */   }
/*    */   
/*    */   public void setColumns(int columns) {
/* 30 */     this.cols = (short)columns;
/*    */   }
/*    */   
/*    */   public int getRows() {
/* 34 */     return this.rows;
/*    */   }
/*    */   
/*    */   public void setRows(int rows) {
/* 38 */     this.rows = (short)rows;
/*    */   }
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
/*    */   public int cursorPos(int row, int col) {
/* 53 */     return row * (this.cols + 1) + col;
/*    */   }
/*    */   
/*    */   public void copy(Size size) {
/* 57 */     setColumns(size.getColumns());
/* 58 */     setRows(size.getRows());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object o) {
/* 63 */     if (o instanceof Size) {
/* 64 */       Size size = (Size)o;
/* 65 */       return (this.rows == size.rows && this.cols == size.cols);
/*    */     } 
/* 67 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 73 */     return this.rows * 31 + this.cols;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 78 */     return "Size[cols=" + this.cols + ", rows=" + this.rows + ']';
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\terminal\Size.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */