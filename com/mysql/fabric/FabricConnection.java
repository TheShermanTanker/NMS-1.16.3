/*     */ package com.mysql.fabric;
/*     */ 
/*     */ import com.mysql.fabric.proto.xmlrpc.XmlRpcClient;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.TimeUnit;
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
/*     */ public class FabricConnection
/*     */ {
/*     */   private XmlRpcClient client;
/*  37 */   private Map<String, ShardMapping> shardMappingsByTableName = new HashMap<String, ShardMapping>();
/*  38 */   private Map<String, ServerGroup> serverGroupsByName = new HashMap<String, ServerGroup>();
/*     */   private long shardMappingsExpiration;
/*     */   private int shardMappingsTtl;
/*     */   private long serverGroupsExpiration;
/*     */   private int serverGroupsTtl;
/*     */   
/*     */   public FabricConnection(String url, String username, String password) throws FabricCommunicationException {
/*  45 */     this.client = new XmlRpcClient(url, username, password);
/*  46 */     refreshState();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FabricConnection(Set<String> urls, String username, String password) throws FabricCommunicationException {
/*  56 */     throw new UnsupportedOperationException("Multiple connections not supported.");
/*     */   }
/*     */   
/*     */   public String getInstanceUuid() {
/*  60 */     return null;
/*     */   }
/*     */   
/*     */   public int getVersion() {
/*  64 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int refreshState() throws FabricCommunicationException {
/*  71 */     FabricStateResponse<Set<ServerGroup>> serverGroups = this.client.getServerGroups();
/*  72 */     FabricStateResponse<Set<ShardMapping>> shardMappings = this.client.getShardMappings();
/*     */     
/*  74 */     this.serverGroupsExpiration = serverGroups.getExpireTimeMillis();
/*  75 */     this.serverGroupsTtl = serverGroups.getTtl();
/*  76 */     for (ServerGroup g : serverGroups.getData()) {
/*  77 */       this.serverGroupsByName.put(g.getName(), g);
/*     */     }
/*     */     
/*  80 */     this.shardMappingsExpiration = shardMappings.getExpireTimeMillis();
/*  81 */     this.shardMappingsTtl = shardMappings.getTtl();
/*  82 */     for (ShardMapping m : shardMappings.getData()) {
/*     */       
/*  84 */       for (ShardTable t : m.getShardTables()) {
/*  85 */         this.shardMappingsByTableName.put(t.getDatabase() + "." + t.getTable(), m);
/*     */       }
/*     */     } 
/*     */     
/*  89 */     return 0;
/*     */   }
/*     */   
/*     */   public int refreshStatePassive() {
/*     */     try {
/*  94 */       return refreshState();
/*  95 */     } catch (FabricCommunicationException e) {
/*     */       
/*  97 */       this.serverGroupsExpiration = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(this.serverGroupsTtl);
/*  98 */       this.shardMappingsExpiration = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(this.shardMappingsTtl);
/*     */ 
/*     */       
/* 101 */       return 0;
/*     */     } 
/*     */   }
/*     */   public ServerGroup getServerGroup(String serverGroupName) {
/* 105 */     if (isStateExpired()) {
/* 106 */       refreshStatePassive();
/*     */     }
/* 108 */     return this.serverGroupsByName.get(serverGroupName);
/*     */   }
/*     */   
/*     */   public ShardMapping getShardMapping(String database, String table) {
/* 112 */     if (isStateExpired()) {
/* 113 */       refreshStatePassive();
/*     */     }
/* 115 */     return this.shardMappingsByTableName.get(database + "." + table);
/*     */   }
/*     */   
/*     */   public boolean isStateExpired() {
/* 119 */     return (System.currentTimeMillis() > this.shardMappingsExpiration || System.currentTimeMillis() > this.serverGroupsExpiration);
/*     */   }
/*     */   
/*     */   public Set<String> getFabricHosts() {
/* 123 */     return null;
/*     */   }
/*     */   
/*     */   public XmlRpcClient getClient() {
/* 127 */     return this.client;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\fabric\FabricConnection.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */