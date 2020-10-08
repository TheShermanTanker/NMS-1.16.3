/*    */ package com.mysql.jdbc;
/*    */ 
/*    */ import java.sql.SQLException;
/*    */ import java.util.Map;
/*    */ import java.util.Properties;
/*    */ import java.util.Set;
/*    */ import java.util.concurrent.ConcurrentHashMap;
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
/*    */ public class PerVmServerConfigCacheFactory
/*    */   implements CacheAdapterFactory<String, Map<String, String>>
/*    */ {
/* 33 */   static final ConcurrentHashMap<String, Map<String, String>> serverConfigByUrl = new ConcurrentHashMap<String, Map<String, String>>();
/*    */   
/* 35 */   private static final CacheAdapter<String, Map<String, String>> serverConfigCache = new CacheAdapter<String, Map<String, String>>()
/*    */     {
/*    */       public Map<String, String> get(String key) {
/* 38 */         return PerVmServerConfigCacheFactory.serverConfigByUrl.get(key);
/*    */       }
/*    */       
/*    */       public void put(String key, Map<String, String> value) {
/* 42 */         PerVmServerConfigCacheFactory.serverConfigByUrl.putIfAbsent(key, value);
/*    */       }
/*    */       
/*    */       public void invalidate(String key) {
/* 46 */         PerVmServerConfigCacheFactory.serverConfigByUrl.remove(key);
/*    */       }
/*    */       
/*    */       public void invalidateAll(Set<String> keys) {
/* 50 */         for (String key : keys) {
/* 51 */           PerVmServerConfigCacheFactory.serverConfigByUrl.remove(key);
/*    */         }
/*    */       }
/*    */       
/*    */       public void invalidateAll() {
/* 56 */         PerVmServerConfigCacheFactory.serverConfigByUrl.clear();
/*    */       }
/*    */     };
/*    */ 
/*    */   
/*    */   public CacheAdapter<String, Map<String, String>> getInstance(Connection forConn, String url, int cacheMaxSize, int maxKeySize, Properties connectionProperties) throws SQLException {
/* 62 */     return serverConfigCache;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\PerVmServerConfigCacheFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */