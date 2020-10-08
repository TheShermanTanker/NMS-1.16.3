/*    */ package com.mysql.jdbc;
/*    */ 
/*    */ import com.mysql.jdbc.util.LRUCache;
/*    */ import java.sql.SQLException;
/*    */ import java.util.Properties;
/*    */ import java.util.Set;
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
/*    */ public class PerConnectionLRUFactory
/*    */   implements CacheAdapterFactory<String, PreparedStatement.ParseInfo>
/*    */ {
/*    */   public CacheAdapter<String, PreparedStatement.ParseInfo> getInstance(Connection forConnection, String url, int cacheMaxSize, int maxKeySize, Properties connectionProperties) throws SQLException {
/* 38 */     return new PerConnectionLRU(forConnection, cacheMaxSize, maxKeySize);
/*    */   }
/*    */   
/*    */   class PerConnectionLRU implements CacheAdapter<String, PreparedStatement.ParseInfo> {
/*    */     private final int cacheSqlLimit;
/*    */     private final LRUCache<String, PreparedStatement.ParseInfo> cache;
/*    */     private final Connection conn;
/*    */     
/*    */     protected PerConnectionLRU(Connection forConnection, int cacheMaxSize, int maxKeySize) {
/* 47 */       int cacheSize = cacheMaxSize;
/* 48 */       this.cacheSqlLimit = maxKeySize;
/* 49 */       this.cache = new LRUCache(cacheSize);
/* 50 */       this.conn = forConnection;
/*    */     }
/*    */     
/*    */     public PreparedStatement.ParseInfo get(String key) {
/* 54 */       if (key == null || key.length() > this.cacheSqlLimit) {
/* 55 */         return null;
/*    */       }
/*    */       
/* 58 */       synchronized (this.conn.getConnectionMutex()) {
/* 59 */         return (PreparedStatement.ParseInfo)this.cache.get(key);
/*    */       } 
/*    */     }
/*    */     
/*    */     public void put(String key, PreparedStatement.ParseInfo value) {
/* 64 */       if (key == null || key.length() > this.cacheSqlLimit) {
/*    */         return;
/*    */       }
/*    */       
/* 68 */       synchronized (this.conn.getConnectionMutex()) {
/* 69 */         this.cache.put(key, value);
/*    */       } 
/*    */     }
/*    */     
/*    */     public void invalidate(String key) {
/* 74 */       synchronized (this.conn.getConnectionMutex()) {
/* 75 */         this.cache.remove(key);
/*    */       } 
/*    */     }
/*    */     
/*    */     public void invalidateAll(Set<String> keys) {
/* 80 */       synchronized (this.conn.getConnectionMutex()) {
/* 81 */         for (String key : keys) {
/* 82 */           this.cache.remove(key);
/*    */         }
/*    */       } 
/*    */     }
/*    */ 
/*    */     
/*    */     public void invalidateAll() {
/* 89 */       synchronized (this.conn.getConnectionMutex()) {
/* 90 */         this.cache.clear();
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\PerConnectionLRUFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */