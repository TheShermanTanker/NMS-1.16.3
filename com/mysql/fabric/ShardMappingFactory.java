/*    */ package com.mysql.fabric;
/*    */ 
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
/*    */ public class ShardMappingFactory
/*    */ {
/*    */   public ShardMapping createShardMapping(int mappingId, ShardingType shardingType, String globalGroupName, Set<ShardTable> shardTables, Set<ShardIndex> shardIndices) {
/* 34 */     ShardMapping sm = null;
/* 35 */     switch (shardingType) {
/*    */       case RANGE:
/* 37 */         sm = new RangeShardMapping(mappingId, shardingType, globalGroupName, shardTables, shardIndices);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 45 */         return sm;case HASH: sm = new HashShardMapping(mappingId, shardingType, globalGroupName, shardTables, shardIndices); return sm;
/*    */     } 
/*    */     throw new IllegalArgumentException("Invalid ShardingType");
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\fabric\ShardMappingFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */