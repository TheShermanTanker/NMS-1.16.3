/*    */ package com.mysql.jdbc;
/*    */ 
/*    */ import java.io.CharArrayWriter;
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
/*    */ 
/*    */ 
/*    */ class WatchableWriter
/*    */   extends CharArrayWriter
/*    */ {
/*    */   private WriterWatcher watcher;
/*    */   
/*    */   public void close() {
/* 39 */     super.close();
/*    */ 
/*    */     
/* 42 */     if (this.watcher != null) {
/* 43 */       this.watcher.writerClosed(this);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setWatcher(WriterWatcher watcher) {
/* 51 */     this.watcher = watcher;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\WatchableWriter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */