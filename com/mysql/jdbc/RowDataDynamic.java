/*     */ package com.mysql.jdbc;
/*     */ 
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RowDataDynamic
/*     */   implements RowData
/*     */ {
/*     */   private int columnCount;
/*     */   private Field[] metadata;
/*  39 */   private int index = -1;
/*     */ 
/*     */ 
/*     */   
/*     */   private MysqlIO io;
/*     */ 
/*     */   
/*     */   private boolean isAfterEnd = false;
/*     */ 
/*     */   
/*     */   private boolean noMoreRows = false;
/*     */ 
/*     */   
/*     */   private boolean isBinaryEncoded = false;
/*     */ 
/*     */   
/*     */   private ResultSetRow nextRow;
/*     */ 
/*     */   
/*     */   private ResultSetImpl owner;
/*     */ 
/*     */   
/*     */   private boolean streamerClosed = false;
/*     */ 
/*     */   
/*     */   private boolean wasEmpty = false;
/*     */ 
/*     */   
/*     */   private boolean useBufferRowExplicit;
/*     */ 
/*     */   
/*     */   private boolean moreResultsExisted;
/*     */ 
/*     */   
/*     */   private ExceptionInterceptor exceptionInterceptor;
/*     */ 
/*     */ 
/*     */   
/*     */   public RowDataDynamic(MysqlIO io, int colCount, Field[] fields, boolean isBinaryEncoded) throws SQLException {
/*  78 */     this.io = io;
/*  79 */     this.columnCount = colCount;
/*  80 */     this.isBinaryEncoded = isBinaryEncoded;
/*  81 */     this.metadata = fields;
/*  82 */     this.exceptionInterceptor = this.io.getExceptionInterceptor();
/*  83 */     this.useBufferRowExplicit = MysqlIO.useBufferRowExplicit(this.metadata);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addRow(ResultSetRow row) throws SQLException {
/*  95 */     notSupported();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void afterLast() throws SQLException {
/* 105 */     notSupported();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void beforeFirst() throws SQLException {
/* 115 */     notSupported();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void beforeLast() throws SQLException {
/* 125 */     notSupported();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws SQLException {
/* 137 */     Object mutex = this;
/*     */     
/* 139 */     MySQLConnection conn = null;
/*     */     
/* 141 */     if (this.owner != null) {
/* 142 */       conn = this.owner.connection;
/*     */       
/* 144 */       if (conn != null) {
/* 145 */         mutex = conn.getConnectionMutex();
/*     */       }
/*     */     } 
/*     */     
/* 149 */     boolean hadMore = false;
/* 150 */     int howMuchMore = 0;
/*     */     
/* 152 */     synchronized (mutex) {
/*     */       
/* 154 */       while (next() != null) {
/* 155 */         hadMore = true;
/* 156 */         howMuchMore++;
/*     */         
/* 158 */         if (howMuchMore % 100 == 0) {
/* 159 */           Thread.yield();
/*     */         }
/*     */       } 
/*     */       
/* 163 */       if (conn != null) {
/* 164 */         if (!conn.getClobberStreamingResults() && conn.getNetTimeoutForStreamingResults() > 0) {
/* 165 */           String oldValue = conn.getServerVariable("net_write_timeout");
/*     */           
/* 167 */           if (oldValue == null || oldValue.length() == 0) {
/* 168 */             oldValue = "60";
/*     */           }
/*     */           
/* 171 */           this.io.clearInputStream();
/*     */           
/* 173 */           Statement stmt = null;
/*     */           
/*     */           try {
/* 176 */             stmt = conn.createStatement();
/* 177 */             ((StatementImpl)stmt).executeSimpleNonQuery(conn, "SET net_write_timeout=" + oldValue);
/*     */           } finally {
/* 179 */             if (stmt != null) {
/* 180 */               stmt.close();
/*     */             }
/*     */           } 
/*     */         } 
/*     */         
/* 185 */         if (conn.getUseUsageAdvisor() && 
/* 186 */           hadMore) {
/* 187 */           this.owner.connection.getProfilerEventHandlerInstance().processEvent((byte)0, this.owner.connection, this.owner.owningStatement, null, 0L, null, Messages.getString("RowDataDynamic.1", (Object[])new String[] { String.valueOf(howMuchMore), this.owner.pointOfOrigin }));
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 195 */     this.metadata = null;
/* 196 */     this.owner = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResultSetRow getAt(int ind) throws SQLException {
/* 209 */     notSupported();
/*     */     
/* 211 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCurrentRowNumber() throws SQLException {
/* 222 */     notSupported();
/*     */     
/* 224 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResultSetInternalMethods getOwner() {
/* 231 */     return this.owner;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasNext() throws SQLException {
/* 242 */     boolean hasNext = (this.nextRow != null);
/*     */     
/* 244 */     if (!hasNext && !this.streamerClosed) {
/* 245 */       this.io.closeStreamer(this);
/* 246 */       this.streamerClosed = true;
/*     */     } 
/*     */     
/* 249 */     return hasNext;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAfterLast() throws SQLException {
/* 260 */     return this.isAfterEnd;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBeforeFirst() throws SQLException {
/* 271 */     return (this.index < 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDynamic() {
/* 283 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() throws SQLException {
/* 294 */     notSupported();
/*     */     
/* 296 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFirst() throws SQLException {
/* 307 */     notSupported();
/*     */     
/* 309 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLast() throws SQLException {
/* 320 */     notSupported();
/*     */     
/* 322 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void moveRowRelative(int rows) throws SQLException {
/* 334 */     notSupported();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResultSetRow next() throws SQLException {
/* 346 */     nextRecord();
/*     */     
/* 348 */     if (this.nextRow == null && !this.streamerClosed && !this.moreResultsExisted) {
/* 349 */       this.io.closeStreamer(this);
/* 350 */       this.streamerClosed = true;
/*     */     } 
/*     */     
/* 353 */     if (this.nextRow != null && 
/* 354 */       this.index != Integer.MAX_VALUE) {
/* 355 */       this.index++;
/*     */     }
/*     */ 
/*     */     
/* 359 */     return this.nextRow;
/*     */   }
/*     */ 
/*     */   
/*     */   private void nextRecord() throws SQLException {
/*     */     try {
/* 365 */       if (!this.noMoreRows) {
/* 366 */         this.nextRow = this.io.nextRow(this.metadata, this.columnCount, this.isBinaryEncoded, 1007, true, this.useBufferRowExplicit, true, null);
/*     */ 
/*     */         
/* 369 */         if (this.nextRow == null) {
/* 370 */           this.noMoreRows = true;
/* 371 */           this.isAfterEnd = true;
/* 372 */           this.moreResultsExisted = this.io.tackOnMoreStreamingResults(this.owner);
/*     */           
/* 374 */           if (this.index == -1) {
/* 375 */             this.wasEmpty = true;
/*     */           }
/*     */         } 
/*     */       } else {
/* 379 */         this.nextRow = null;
/* 380 */         this.isAfterEnd = true;
/*     */       } 
/* 382 */     } catch (SQLException sqlEx) {
/* 383 */       if (sqlEx instanceof StreamingNotifiable) {
/* 384 */         ((StreamingNotifiable)sqlEx).setWasStreamingResults();
/*     */       }
/*     */ 
/*     */       
/* 388 */       this.noMoreRows = true;
/*     */ 
/*     */       
/* 391 */       throw sqlEx;
/* 392 */     } catch (Exception ex) {
/* 393 */       SQLException sqlEx = SQLError.createSQLException(Messages.getString("RowDataDynamic.2", (Object[])new String[] { ex.getClass().getName(), ex.getMessage(), Util.stackTraceToString(ex) }), "S1000", this.exceptionInterceptor);
/*     */ 
/*     */       
/* 396 */       sqlEx.initCause(ex);
/*     */       
/* 398 */       throw sqlEx;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void notSupported() throws SQLException {
/* 403 */     throw new OperationNotSupportedException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeRow(int ind) throws SQLException {
/* 415 */     notSupported();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCurrentRow(int rowNumber) throws SQLException {
/* 427 */     notSupported();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOwner(ResultSetImpl rs) {
/* 434 */     this.owner = rs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 443 */     return -1;
/*     */   }
/*     */   
/*     */   public boolean wasEmpty() {
/* 447 */     return this.wasEmpty;
/*     */   }
/*     */   
/*     */   public void setMetadata(Field[] metadata) {
/* 451 */     this.metadata = metadata;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\RowDataDynamic.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */