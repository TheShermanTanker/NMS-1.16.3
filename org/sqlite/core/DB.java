/*      */ package org.sqlite.core;
/*      */ 
/*      */ import java.sql.BatchUpdateException;
/*      */ import java.sql.SQLException;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.concurrent.atomic.AtomicBoolean;
/*      */ import org.sqlite.BusyHandler;
/*      */ import org.sqlite.Function;
/*      */ import org.sqlite.ProgressHandler;
/*      */ import org.sqlite.SQLiteCommitListener;
/*      */ import org.sqlite.SQLiteConfig;
/*      */ import org.sqlite.SQLiteErrorCode;
/*      */ import org.sqlite.SQLiteException;
/*      */ import org.sqlite.SQLiteUpdateListener;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class DB
/*      */   implements Codes
/*      */ {
/*      */   private final String url;
/*      */   private final String fileName;
/*      */   private final SQLiteConfig config;
/*   53 */   private final AtomicBoolean closed = new AtomicBoolean(true);
/*      */ 
/*      */   
/*   56 */   long begin = 0L;
/*   57 */   long commit = 0L;
/*      */ 
/*      */   
/*   60 */   private final Map<Long, CoreStatement> stmts = new HashMap<Long, CoreStatement>();
/*      */   
/*   62 */   private final Set<SQLiteUpdateListener> updateListeners = new HashSet<SQLiteUpdateListener>();
/*   63 */   private final Set<SQLiteCommitListener> commitListeners = new HashSet<SQLiteCommitListener>();
/*      */ 
/*      */ 
/*      */   
/*      */   public DB(String url, String fileName, SQLiteConfig config) throws SQLException {
/*   68 */     this.url = url;
/*   69 */     this.fileName = fileName;
/*   70 */     this.config = config;
/*      */   }
/*      */   
/*      */   public String getUrl() {
/*   74 */     return this.url;
/*      */   }
/*      */   
/*      */   public boolean isClosed() {
/*   78 */     return this.closed.get();
/*      */   }
/*      */   
/*      */   public SQLiteConfig getConfig() {
/*   82 */     return this.config;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract void interrupt() throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract void busy_timeout(int paramInt) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract void busy_handler(BusyHandler paramBusyHandler) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract String errmsg() throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract String libversion() throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract int changes() throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract int total_changes() throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract int shared_cache(boolean paramBoolean) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract int enable_load_extension(boolean paramBoolean) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final synchronized void exec(String sql, boolean autoCommit) throws SQLException {
/*  174 */     long pointer = 0L;
/*      */     try {
/*  176 */       pointer = prepare(sql);
/*  177 */       int rc = step(pointer);
/*  178 */       switch (rc) {
/*      */         case 101:
/*  180 */           ensureAutoCommit(autoCommit);
/*      */           return;
/*      */         case 100:
/*      */           return;
/*      */       } 
/*  185 */       throwex(rc);
/*      */     }
/*      */     finally {
/*      */       
/*  189 */       finalize(pointer);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final synchronized void open(String file, int openFlags) throws SQLException {
/*  202 */     _open(file, openFlags);
/*  203 */     this.closed.set(false);
/*      */     
/*  205 */     if (this.fileName.startsWith("file:") && !this.fileName.contains("cache="))
/*      */     {
/*  207 */       shared_cache(this.config.isEnabledSharedCache());
/*      */     }
/*  209 */     enable_load_extension(this.config.isEnabledLoadExtension());
/*  210 */     busy_timeout(this.config.getBusyTimeout());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final synchronized void close() throws SQLException {
/*  221 */     synchronized (this.stmts) {
/*  222 */       Iterator<Map.Entry<Long, CoreStatement>> i = this.stmts.entrySet().iterator();
/*  223 */       while (i.hasNext()) {
/*  224 */         Map.Entry<Long, CoreStatement> entry = i.next();
/*  225 */         CoreStatement stmt = entry.getValue();
/*  226 */         finalize(((Long)entry.getKey()).longValue());
/*  227 */         if (stmt != null) {
/*  228 */           stmt.pointer = 0L;
/*      */         }
/*  230 */         i.remove();
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  235 */     free_functions();
/*      */ 
/*      */     
/*  238 */     if (this.begin != 0L) {
/*  239 */       finalize(this.begin);
/*  240 */       this.begin = 0L;
/*      */     } 
/*  242 */     if (this.commit != 0L) {
/*  243 */       finalize(this.commit);
/*  244 */       this.commit = 0L;
/*      */     } 
/*      */     
/*  247 */     this.closed.set(true);
/*  248 */     _close();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final synchronized void prepare(CoreStatement stmt) throws SQLException {
/*  258 */     if (stmt.sql == null) {
/*  259 */       throw new NullPointerException();
/*      */     }
/*  261 */     if (stmt.pointer != 0L) {
/*  262 */       finalize(stmt);
/*      */     }
/*  264 */     stmt.pointer = prepare(stmt.sql);
/*  265 */     this.stmts.put(new Long(stmt.pointer), stmt);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final synchronized int finalize(CoreStatement stmt) throws SQLException {
/*  276 */     if (stmt.pointer == 0L) {
/*  277 */       return 0;
/*      */     }
/*  279 */     int rc = 1;
/*      */     try {
/*  281 */       rc = finalize(stmt.pointer);
/*      */     } finally {
/*      */       
/*  284 */       this.stmts.remove(new Long(stmt.pointer));
/*  285 */       stmt.pointer = 0L;
/*      */     } 
/*  287 */     return rc;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected abstract void _open(String paramString, int paramInt) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected abstract void _close() throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract int _exec(String paramString) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected abstract long prepare(String paramString) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected abstract int finalize(long paramLong) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract int step(long paramLong) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract int reset(long paramLong) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract int clear_bindings(long paramLong) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract int bind_parameter_count(long paramLong) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract int column_count(long paramLong) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract int column_type(long paramLong, int paramInt) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract String column_decltype(long paramLong, int paramInt) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract String column_table_name(long paramLong, int paramInt) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract String column_name(long paramLong, int paramInt) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract String column_text(long paramLong, int paramInt) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract byte[] column_blob(long paramLong, int paramInt) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract double column_double(long paramLong, int paramInt) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract long column_long(long paramLong, int paramInt) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract int column_int(long paramLong, int paramInt) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract int bind_null(long paramLong, int paramInt) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract int bind_int(long paramLong, int paramInt1, int paramInt2) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract int bind_long(long paramLong1, int paramInt, long paramLong2) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract int bind_double(long paramLong, int paramInt, double paramDouble) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract int bind_text(long paramLong, int paramInt, String paramString) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract int bind_blob(long paramLong, int paramInt, byte[] paramArrayOfbyte) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract void result_null(long paramLong) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract void result_text(long paramLong, String paramString) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract void result_blob(long paramLong, byte[] paramArrayOfbyte) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract void result_double(long paramLong, double paramDouble) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract void result_long(long paramLong1, long paramLong2) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract void result_int(long paramLong, int paramInt) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract void result_error(long paramLong, String paramString) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract String value_text(Function paramFunction, int paramInt) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract byte[] value_blob(Function paramFunction, int paramInt) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract double value_double(Function paramFunction, int paramInt) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract long value_long(Function paramFunction, int paramInt) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract int value_int(Function paramFunction, int paramInt) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract int value_type(Function paramFunction, int paramInt) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract int create_function(String paramString, Function paramFunction, int paramInt1, int paramInt2) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract int destroy_function(String paramString, int paramInt) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract void free_functions() throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract int backup(String paramString1, String paramString2, ProgressObserver paramProgressObserver) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract int restore(String paramString1, String paramString2, ProgressObserver paramProgressObserver) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract void register_progress_handler(int paramInt, ProgressHandler paramProgressHandler) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract void clear_progress_handler() throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   abstract boolean[][] column_metadata(long paramLong) throws SQLException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final synchronized String[] column_names(long stmt) throws SQLException {
/*  729 */     String[] names = new String[column_count(stmt)];
/*  730 */     for (int i = 0; i < names.length; i++) {
/*  731 */       names[i] = column_name(stmt, i);
/*      */     }
/*  733 */     return names;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final synchronized int sqlbind(long stmt, int pos, Object v) throws SQLException {
/*  746 */     pos++;
/*  747 */     if (v == null) {
/*  748 */       return bind_null(stmt, pos);
/*      */     }
/*  750 */     if (v instanceof Integer) {
/*  751 */       return bind_int(stmt, pos, ((Integer)v).intValue());
/*      */     }
/*  753 */     if (v instanceof Short) {
/*  754 */       return bind_int(stmt, pos, ((Short)v).intValue());
/*      */     }
/*  756 */     if (v instanceof Long) {
/*  757 */       return bind_long(stmt, pos, ((Long)v).longValue());
/*      */     }
/*  759 */     if (v instanceof Float) {
/*  760 */       return bind_double(stmt, pos, ((Float)v).doubleValue());
/*      */     }
/*  762 */     if (v instanceof Double) {
/*  763 */       return bind_double(stmt, pos, ((Double)v).doubleValue());
/*      */     }
/*  765 */     if (v instanceof String) {
/*  766 */       return bind_text(stmt, pos, (String)v);
/*      */     }
/*  768 */     if (v instanceof byte[]) {
/*  769 */       return bind_blob(stmt, pos, (byte[])v);
/*      */     }
/*      */     
/*  772 */     throw new SQLException("unexpected param type: " + v.getClass());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final synchronized int[] executeBatch(long stmt, int count, Object[] vals, boolean autoCommit) throws SQLException {
/*  787 */     if (count < 1) {
/*  788 */       throw new SQLException("count (" + count + ") < 1");
/*      */     }
/*      */     
/*  791 */     int params = bind_parameter_count(stmt);
/*      */ 
/*      */     
/*  794 */     int[] changes = new int[count];
/*      */     
/*      */     try {
/*  797 */       for (int i = 0; i < count; i++) {
/*  798 */         reset(stmt);
/*  799 */         for (int j = 0; j < params; j++) {
/*  800 */           int k = sqlbind(stmt, j, vals[i * params + j]);
/*  801 */           if (k != 0) {
/*  802 */             throwex(k);
/*      */           }
/*      */         } 
/*      */         
/*  806 */         int rc = step(stmt);
/*  807 */         if (rc != 101) {
/*  808 */           reset(stmt);
/*  809 */           if (rc == 100) {
/*  810 */             throw new BatchUpdateException("batch entry " + i + ": query returns results", changes);
/*      */           }
/*  812 */           throwex(rc);
/*      */         } 
/*      */         
/*  815 */         changes[i] = changes();
/*      */       } 
/*      */     } finally {
/*      */       
/*  819 */       ensureAutoCommit(autoCommit);
/*      */     } 
/*      */     
/*  822 */     reset(stmt);
/*  823 */     return changes;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final synchronized boolean execute(CoreStatement stmt, Object[] vals) throws SQLException {
/*  834 */     if (vals != null) {
/*  835 */       int params = bind_parameter_count(stmt.pointer);
/*  836 */       if (params > vals.length) {
/*  837 */         throw new SQLException("assertion failure: param count (" + params + ") > value count (" + vals.length + ")");
/*      */       }
/*      */ 
/*      */       
/*  841 */       for (int i = 0; i < params; i++) {
/*  842 */         int rc = sqlbind(stmt.pointer, i, vals[i]);
/*  843 */         if (rc != 0) {
/*  844 */           throwex(rc);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  849 */     int statusCode = step(stmt.pointer) & 0xFF;
/*  850 */     switch (statusCode) {
/*      */       case 101:
/*  852 */         reset(stmt.pointer);
/*  853 */         ensureAutoCommit(stmt.conn.getAutoCommit());
/*  854 */         return false;
/*      */       case 100:
/*  856 */         return true;
/*      */       case 5:
/*      */       case 6:
/*      */       case 19:
/*      */       case 21:
/*  861 */         throw newSQLException(statusCode);
/*      */     } 
/*  863 */     finalize(stmt);
/*  864 */     throw newSQLException(statusCode);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final synchronized boolean execute(String sql, boolean autoCommit) throws SQLException {
/*  877 */     int statusCode = _exec(sql);
/*  878 */     switch (statusCode) {
/*      */       case 0:
/*  880 */         return false;
/*      */       case 101:
/*  882 */         ensureAutoCommit(autoCommit);
/*  883 */         return false;
/*      */       case 100:
/*  885 */         return true;
/*      */     } 
/*  887 */     throw newSQLException(statusCode);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final synchronized int executeUpdate(CoreStatement stmt, Object[] vals) throws SQLException {
/*      */     try {
/*  902 */       if (execute(stmt, vals)) {
/*  903 */         throw new SQLException("query returns results");
/*      */       }
/*      */     } finally {
/*  906 */       if (stmt.pointer != 0L) reset(stmt.pointer); 
/*      */     } 
/*  908 */     return changes();
/*      */   }
/*      */   abstract void set_commit_listener(boolean paramBoolean);
/*      */   
/*      */   abstract void set_update_listener(boolean paramBoolean);
/*      */   
/*      */   public synchronized void addUpdateListener(SQLiteUpdateListener listener) {
/*  915 */     if (this.updateListeners.add(listener) && this.updateListeners.size() == 1) {
/*  916 */       set_update_listener(true);
/*      */     }
/*      */   }
/*      */   
/*      */   public synchronized void addCommitListener(SQLiteCommitListener listener) {
/*  921 */     if (this.commitListeners.add(listener) && this.commitListeners.size() == 1) {
/*  922 */       set_commit_listener(true);
/*      */     }
/*      */   }
/*      */   
/*      */   public synchronized void removeUpdateListener(SQLiteUpdateListener listener) {
/*  927 */     if (this.updateListeners.remove(listener) && this.updateListeners.isEmpty()) {
/*  928 */       set_update_listener(false);
/*      */     }
/*      */   }
/*      */   
/*      */   public synchronized void removeCommitListener(SQLiteCommitListener listener) {
/*  933 */     if (this.commitListeners.remove(listener) && this.commitListeners.isEmpty()) {
/*  934 */       set_commit_listener(false);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   void onUpdate(int type, String database, String table, long rowId) {
/*      */     Set<SQLiteUpdateListener> listeners;
/*  941 */     synchronized (this) {
/*  942 */       listeners = new HashSet<SQLiteUpdateListener>(this.updateListeners);
/*      */     } 
/*      */     
/*  945 */     for (SQLiteUpdateListener listener : listeners) {
/*      */       SQLiteUpdateListener.Type operationType;
/*      */       
/*  948 */       switch (type) { case 18:
/*  949 */           operationType = SQLiteUpdateListener.Type.INSERT; break;
/*  950 */         case 9: operationType = SQLiteUpdateListener.Type.DELETE; break;
/*  951 */         case 23: operationType = SQLiteUpdateListener.Type.UPDATE; break;
/*  952 */         default: throw new AssertionError("Unknown type: " + type); }
/*      */ 
/*      */ 
/*      */       
/*  956 */       listener.onUpdate(operationType, database, table, rowId);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   void onCommit(boolean commit) {
/*      */     Set<SQLiteCommitListener> listeners;
/*  963 */     synchronized (this) {
/*  964 */       listeners = new HashSet<SQLiteCommitListener>(this.commitListeners);
/*      */     } 
/*      */     
/*  967 */     for (SQLiteCommitListener listener : listeners) {
/*  968 */       if (commit) { listener.onCommit(); continue; }
/*  969 */        listener.onRollback();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final void throwex() throws SQLException {
/*  978 */     throw new SQLException(errmsg());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void throwex(int errorCode) throws SQLException {
/*  987 */     throw newSQLException(errorCode);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final void throwex(int errorCode, String errorMessage) throws SQLiteException {
/*  997 */     throw newSQLException(errorCode, errorMessage);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static SQLiteException newSQLException(int errorCode, String errorMessage) {
/* 1008 */     SQLiteErrorCode code = SQLiteErrorCode.getErrorCode(errorCode);
/*      */     
/* 1010 */     SQLiteException e = new SQLiteException(String.format("%s (%s)", new Object[] { code, errorMessage }), code);
/*      */     
/* 1012 */     return e;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private SQLiteException newSQLException(int errorCode) throws SQLException {
/* 1022 */     return newSQLException(errorCode, errmsg());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final void ensureAutoCommit(boolean autoCommit) throws SQLException {
/* 1058 */     if (!autoCommit) {
/*      */       return;
/*      */     }
/*      */     
/* 1062 */     if (this.begin == 0L) {
/* 1063 */       this.begin = prepare("begin;");
/*      */     }
/* 1065 */     if (this.commit == 0L) {
/* 1066 */       this.commit = prepare("commit;");
/*      */     }
/*      */     
/*      */     try {
/* 1070 */       if (step(this.begin) != 101) {
/*      */         return;
/*      */       }
/*      */       
/* 1074 */       int rc = step(this.commit);
/* 1075 */       if (rc != 101) {
/* 1076 */         reset(this.commit);
/* 1077 */         throwex(rc);
/*      */       }
/*      */     
/*      */     } finally {
/*      */       
/* 1082 */       reset(this.begin);
/* 1083 */       reset(this.commit);
/*      */     } 
/*      */   }
/*      */   
/*      */   public static interface ProgressObserver {
/*      */     void progress(int param1Int1, int param1Int2);
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\sqlite\core\DB.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */