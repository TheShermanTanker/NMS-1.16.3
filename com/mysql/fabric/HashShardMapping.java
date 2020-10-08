/*    */ package com.mysql.fabric;
/*    */ 
/*    */ import java.math.BigInteger;
/*    */ import java.security.MessageDigest;
/*    */ import java.security.NoSuchAlgorithmException;
/*    */ import java.util.Comparator;
/*    */ import java.util.Set;
/*    */ import java.util.TreeSet;
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
/*    */ public class HashShardMapping
/*    */   extends ShardMapping
/*    */ {
/*    */   private static final MessageDigest md5Hasher;
/*    */   
/*    */   private static class ReverseShardIndexSorter
/*    */     implements Comparator<ShardIndex>
/*    */   {
/*    */     public int compare(ShardIndex i1, ShardIndex i2) {
/* 39 */       return i2.getBound().compareTo(i1.getBound());
/*    */     }
/*    */ 
/*    */     
/* 43 */     public static final ReverseShardIndexSorter instance = new ReverseShardIndexSorter();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   static {
/*    */     try {
/* 50 */       md5Hasher = MessageDigest.getInstance("MD5");
/* 51 */     } catch (NoSuchAlgorithmException ex) {
/* 52 */       throw new ExceptionInInitializerError(ex);
/*    */     } 
/*    */   }
/*    */   
/*    */   public HashShardMapping(int mappingId, ShardingType shardingType, String globalGroupName, Set<ShardTable> shardTables, Set<ShardIndex> shardIndices) {
/* 57 */     super(mappingId, shardingType, globalGroupName, shardTables, new TreeSet<ShardIndex>(ReverseShardIndexSorter.instance));
/* 58 */     this.shardIndices.addAll(shardIndices);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ShardIndex getShardIndexForKey(String stringKey) {
/*    */     String hashedKey;
/* 64 */     synchronized (md5Hasher) {
/* 65 */       hashedKey = (new BigInteger(1, md5Hasher.digest(stringKey.getBytes()))).toString(16).toUpperCase();
/*    */     } 
/*    */ 
/*    */     
/* 69 */     for (int i = 0; i < 32 - hashedKey.length(); i++) {
/* 70 */       hashedKey = "0" + hashedKey;
/*    */     }
/*    */     
/* 73 */     for (ShardIndex shardIndex : this.shardIndices) {
/* 74 */       if (shardIndex.getBound().compareTo(hashedKey) <= 0) {
/* 75 */         return shardIndex;
/*    */       }
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 81 */     return this.shardIndices.iterator().next();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\fabric\HashShardMapping.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */