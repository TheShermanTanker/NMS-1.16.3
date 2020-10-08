/*    */ package com.mysql.jdbc;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.OutputStream;
/*    */ import java.net.Socket;
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
/*    */ class NetworkResources
/*    */ {
/*    */   private final Socket mysqlConnection;
/*    */   private final InputStream mysqlInput;
/*    */   private final OutputStream mysqlOutput;
/*    */   
/*    */   protected NetworkResources(Socket mysqlConnection, InputStream mysqlInput, OutputStream mysqlOutput) {
/* 37 */     this.mysqlConnection = mysqlConnection;
/* 38 */     this.mysqlInput = mysqlInput;
/* 39 */     this.mysqlOutput = mysqlOutput;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected final void forceClose() {
/*    */     try {
/* 47 */       if (!ExportControlled.isSSLEstablished(this.mysqlConnection)) {
/*    */         try {
/* 49 */           if (this.mysqlInput != null) {
/* 50 */             this.mysqlInput.close();
/*    */           }
/*    */         } finally {
/* 53 */           if (this.mysqlConnection != null && !this.mysqlConnection.isClosed() && !this.mysqlConnection.isInputShutdown()) {
/*    */             try {
/* 55 */               this.mysqlConnection.shutdownInput();
/* 56 */             } catch (UnsupportedOperationException e) {}
/*    */           }
/*    */         }
/*    */       
/*    */       }
/*    */     }
/* 62 */     catch (IOException e) {}
/*    */ 
/*    */ 
/*    */     
/*    */     try {
/* 67 */       if (!ExportControlled.isSSLEstablished(this.mysqlConnection)) {
/*    */         try {
/* 69 */           if (this.mysqlOutput != null) {
/* 70 */             this.mysqlOutput.close();
/*    */           }
/*    */         } finally {
/* 73 */           if (this.mysqlConnection != null && !this.mysqlConnection.isClosed() && !this.mysqlConnection.isOutputShutdown()) {
/*    */             try {
/* 75 */               this.mysqlConnection.shutdownOutput();
/* 76 */             } catch (UnsupportedOperationException e) {}
/*    */           }
/*    */         }
/*    */       
/*    */       }
/*    */     }
/* 82 */     catch (IOException e) {}
/*    */ 
/*    */ 
/*    */     
/*    */     try {
/* 87 */       if (this.mysqlConnection != null) {
/* 88 */         this.mysqlConnection.close();
/*    */       }
/* 90 */     } catch (IOException e) {}
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\NetworkResources.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */