/*     */ package org.sqlite.core;
/*     */ 
/*     */ import java.sql.Date;
/*     */ import java.sql.SQLException;
/*     */ import java.util.Calendar;
/*     */ import org.sqlite.SQLiteConfig;
/*     */ import org.sqlite.SQLiteConnection;
/*     */ import org.sqlite.SQLiteConnectionConfig;
/*     */ import org.sqlite.date.FastDateFormat;
/*     */ import org.sqlite.jdbc4.JDBC4Statement;
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
/*     */ public abstract class CorePreparedStatement
/*     */   extends JDBC4Statement
/*     */ {
/*     */   protected int columnCount;
/*     */   protected int paramCount;
/*     */   protected int batchQueryCount;
/*     */   
/*     */   protected CorePreparedStatement(SQLiteConnection conn, String sql) throws SQLException {
/*  41 */     super(conn);
/*     */     
/*  43 */     this.sql = sql;
/*  44 */     DB db = conn.getDatabase();
/*  45 */     db.prepare((CoreStatement)this);
/*  46 */     this.rs.colsMeta = db.column_names(this.pointer);
/*  47 */     this.columnCount = db.column_count(this.pointer);
/*  48 */     this.paramCount = db.bind_parameter_count(this.pointer);
/*  49 */     this.batchQueryCount = 0;
/*  50 */     this.batch = null;
/*  51 */     this.batchPos = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void finalize() throws SQLException {
/*  59 */     close();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] executeBatch() throws SQLException {
/*  67 */     if (this.batchQueryCount == 0) {
/*  68 */       return new int[0];
/*     */     }
/*     */     
/*     */     try {
/*  72 */       return this.conn.getDatabase().executeBatch(this.pointer, this.batchQueryCount, this.batch, this.conn.getAutoCommit());
/*     */     } finally {
/*     */       
/*  75 */       clearBatch();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearBatch() throws SQLException {
/*  84 */     super.clearBatch();
/*  85 */     this.batchQueryCount = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getUpdateCount() throws SQLException {
/*  93 */     if (this.pointer == 0L || this.resultsWaiting || this.rs.isOpen()) {
/*  94 */       return -1;
/*     */     }
/*     */     
/*  97 */     return this.conn.getDatabase().changes();
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
/*     */   protected void batch(int pos, Object value) throws SQLException {
/* 110 */     checkOpen();
/* 111 */     if (this.batch == null) {
/* 112 */       this.batch = new Object[this.paramCount];
/*     */     }
/* 114 */     this.batch[this.batchPos + pos - 1] = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setDateByMilliseconds(int pos, Long value, Calendar calendar) throws SQLException {
/* 122 */     SQLiteConnectionConfig config = this.conn.getConnectionConfig();
/* 123 */     switch (config.getDateClass()) {
/*     */       case TEXT:
/* 125 */         batch(pos, FastDateFormat.getInstance(config.getDateStringFormat(), calendar.getTimeZone()).format(new Date(value.longValue())));
/*     */         return;
/*     */ 
/*     */       
/*     */       case REAL:
/* 130 */         batch(pos, new Double(value.longValue() / 8.64E7D + 2440587.5D));
/*     */         return;
/*     */     } 
/*     */     
/* 134 */     batch(pos, new Long(value.longValue() / config.getDateMultiplier()));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\sqlite\core\CorePreparedStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */