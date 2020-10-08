/*     */ package com.mysql.fabric.proto.xmlrpc;
/*     */ 
/*     */ import com.mysql.fabric.FabricCommunicationException;
/*     */ import com.mysql.fabric.FabricStateResponse;
/*     */ import com.mysql.fabric.Response;
/*     */ import com.mysql.fabric.Server;
/*     */ import com.mysql.fabric.ServerGroup;
/*     */ import com.mysql.fabric.ServerMode;
/*     */ import com.mysql.fabric.ServerRole;
/*     */ import com.mysql.fabric.ShardIndex;
/*     */ import com.mysql.fabric.ShardMapping;
/*     */ import com.mysql.fabric.ShardMappingFactory;
/*     */ import com.mysql.fabric.ShardTable;
/*     */ import com.mysql.fabric.ShardingType;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XmlRpcClient
/*     */ {
/*     */   private static final String THREAT_REPORTER_NAME = "MySQL Connector/J";
/*     */   private static final String METHOD_DUMP_FABRIC_NODES = "dump.fabric_nodes";
/*     */   private static final String METHOD_DUMP_SERVERS = "dump.servers";
/*     */   private static final String METHOD_DUMP_SHARD_TABLES = "dump.shard_tables";
/*     */   private static final String METHOD_DUMP_SHARD_INDEX = "dump.shard_index";
/*     */   private static final String METHOD_DUMP_SHARD_MAPS = "dump.shard_maps";
/*     */   private static final String METHOD_SHARDING_LOOKUP_SERVERS = "sharding.lookup_servers";
/*     */   private static final String METHOD_SHARDING_CREATE_DEFINITION = "sharding.create_definition";
/*     */   private static final String METHOD_SHARDING_ADD_TABLE = "sharding.add_table";
/*     */   private static final String METHOD_SHARDING_ADD_SHARD = "sharding.add_shard";
/*     */   private static final String METHOD_GROUP_LOOKUP_GROUPS = "group.lookup_groups";
/*     */   private static final String METHOD_GROUP_CREATE = "group.create";
/*     */   private static final String METHOD_GROUP_ADD = "group.add";
/*     */   private static final String METHOD_GROUP_REMOVE = "group.remove";
/*     */   private static final String METHOD_GROUP_PROMOTE = "group.promote";
/*     */   private static final String METHOD_GROUP_DESTROY = "group.destroy";
/*     */   private static final String METHOD_THREAT_REPORT_ERROR = "threat.report_error";
/*     */   private static final String METHOD_THREAT_REPORT_FAILURE = "threat.report_failure";
/*     */   private static final String FIELD_MODE = "mode";
/*     */   private static final String FIELD_STATUS = "status";
/*     */   private static final String FIELD_HOST = "host";
/*     */   private static final String FIELD_PORT = "port";
/*     */   private static final String FIELD_ADDRESS = "address";
/*     */   private static final String FIELD_GROUP_ID = "group_id";
/*     */   private static final String FIELD_SERVER_UUID = "server_uuid";
/*     */   private static final String FIELD_WEIGHT = "weight";
/*     */   private static final String FIELD_SCHEMA_NAME = "schema_name";
/*     */   private static final String FIELD_TABLE_NAME = "table_name";
/*     */   private static final String FIELD_COLUMN_NAME = "column_name";
/*     */   private static final String FIELD_LOWER_BOUND = "lower_bound";
/*     */   private static final String FIELD_SHARD_ID = "shard_id";
/*     */   private static final String FIELD_MAPPING_ID = "mapping_id";
/*     */   private static final String FIELD_GLOBAL_GROUP_ID = "global_group_id";
/*     */   private static final String FIELD_TYPE_NAME = "type_name";
/*     */   private static final String FIELD_RESULT = "result";
/*     */   private XmlRpcMethodCaller methodCaller;
/*     */   
/*     */   public XmlRpcClient(String url, String username, String password) throws FabricCommunicationException {
/*  94 */     this.methodCaller = new InternalXmlRpcMethodCaller(url);
/*  95 */     if (username != null && !"".equals(username) && password != null) {
/*  96 */       this.methodCaller = new AuthenticatedXmlRpcMethodCaller(this.methodCaller, url, username, password);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Server unmarshallServer(Map<String, ?> serverData) throws FabricCommunicationException {
/*     */     try {
/*     */       ServerMode mode;
/*     */       ServerRole role;
/*     */       String host;
/*     */       int port;
/* 112 */       if (Integer.class.equals(serverData.get("mode").getClass())) {
/* 113 */         mode = ServerMode.getFromConstant((Integer)serverData.get("mode"));
/* 114 */         role = ServerRole.getFromConstant((Integer)serverData.get("status"));
/* 115 */         host = (String)serverData.get("host");
/* 116 */         port = ((Integer)serverData.get("port")).intValue();
/*     */       } else {
/*     */         
/* 119 */         mode = ServerMode.valueOf((String)serverData.get("mode"));
/* 120 */         role = ServerRole.valueOf((String)serverData.get("status"));
/* 121 */         String[] hostnameAndPort = ((String)serverData.get("address")).split(":");
/* 122 */         host = hostnameAndPort[0];
/* 123 */         port = Integer.valueOf(hostnameAndPort[1]).intValue();
/*     */       } 
/* 125 */       Server s = new Server((String)serverData.get("group_id"), (String)serverData.get("server_uuid"), host, port, mode, role, ((Double)serverData.get("weight")).doubleValue());
/*     */       
/* 127 */       return s;
/* 128 */     } catch (Exception ex) {
/* 129 */       throw new FabricCommunicationException("Unable to parse server definition", ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Set<Server> toServerSet(List<Map<String, ?>> l) throws FabricCommunicationException {
/* 137 */     Set<Server> servers = new HashSet<Server>();
/* 138 */     for (Map<String, ?> serverData : l) {
/* 139 */       servers.add(unmarshallServer(serverData));
/*     */     }
/* 141 */     return servers;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Response errorSafeCallMethod(String methodName, Object[] args) throws FabricCommunicationException {
/* 151 */     List<?> responseData = this.methodCaller.call(methodName, args);
/* 152 */     Response response = new Response(responseData);
/* 153 */     if (response.getErrorMessage() != null) {
/* 154 */       throw new FabricCommunicationException("Call failed to method `" + methodName + "':\n" + response.getErrorMessage());
/*     */     }
/* 156 */     return response;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<String> getFabricNames() throws FabricCommunicationException {
/* 163 */     Response resp = errorSafeCallMethod("dump.fabric_nodes", new Object[0]);
/* 164 */     Set<String> names = new HashSet<String>();
/* 165 */     for (Map<String, ?> node : (Iterable<Map<String, ?>>)resp.getResultSet()) {
/* 166 */       names.add((new StringBuilder()).append(node.get("host")).append(":").append(node.get("port")).toString());
/*     */     }
/* 168 */     return names;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<String> getGroupNames() throws FabricCommunicationException {
/* 175 */     Set<String> groupNames = new HashSet<String>();
/* 176 */     for (Map<String, ?> row : (Iterable<Map<String, ?>>)errorSafeCallMethod("group.lookup_groups", null).getResultSet()) {
/* 177 */       groupNames.add((String)row.get("group_id"));
/*     */     }
/* 179 */     return groupNames;
/*     */   }
/*     */   
/*     */   public ServerGroup getServerGroup(String groupName) throws FabricCommunicationException {
/* 183 */     Set<ServerGroup> groups = (Set<ServerGroup>)getServerGroups(groupName).getData();
/* 184 */     if (groups.size() == 1) {
/* 185 */       return groups.iterator().next();
/*     */     }
/* 187 */     return null;
/*     */   }
/*     */   
/*     */   public Set<Server> getServersForKey(String tableName, int key) throws FabricCommunicationException {
/* 191 */     Response r = errorSafeCallMethod("sharding.lookup_servers", new Object[] { tableName, Integer.valueOf(key) });
/* 192 */     return toServerSet(r.getResultSet());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FabricStateResponse<Set<ServerGroup>> getServerGroups(String groupPattern) throws FabricCommunicationException {
/* 199 */     int version = 0;
/* 200 */     Response response = errorSafeCallMethod("dump.servers", new Object[] { Integer.valueOf(version), groupPattern });
/*     */     
/* 202 */     Map<String, Set<Server>> serversByGroupName = new HashMap<String, Set<Server>>();
/* 203 */     for (Map<String, ?> server : (Iterable<Map<String, ?>>)response.getResultSet()) {
/* 204 */       Server s = unmarshallServer(server);
/* 205 */       if (serversByGroupName.get(s.getGroupName()) == null) {
/* 206 */         serversByGroupName.put(s.getGroupName(), new HashSet<Server>());
/*     */       }
/* 208 */       ((Set<Server>)serversByGroupName.get(s.getGroupName())).add(s);
/*     */     } 
/*     */     
/* 211 */     Set<ServerGroup> serverGroups = new HashSet<ServerGroup>();
/* 212 */     for (Map.Entry<String, Set<Server>> entry : serversByGroupName.entrySet()) {
/* 213 */       ServerGroup g = new ServerGroup(entry.getKey(), entry.getValue());
/* 214 */       serverGroups.add(g);
/*     */     } 
/* 216 */     return new FabricStateResponse(serverGroups, response.getTtl());
/*     */   }
/*     */   
/*     */   public FabricStateResponse<Set<ServerGroup>> getServerGroups() throws FabricCommunicationException {
/* 220 */     return getServerGroups("");
/*     */   }
/*     */   
/*     */   private FabricStateResponse<Set<ShardTable>> getShardTables(int shardMappingId) throws FabricCommunicationException {
/* 224 */     int version = 0;
/* 225 */     Object[] args = { Integer.valueOf(version), String.valueOf(shardMappingId) };
/* 226 */     Response tablesResponse = errorSafeCallMethod("dump.shard_tables", args);
/* 227 */     Set<ShardTable> tables = new HashSet<ShardTable>();
/*     */     
/* 229 */     for (Map<String, ?> rawTable : (Iterable<Map<String, ?>>)tablesResponse.getResultSet()) {
/* 230 */       String database = (String)rawTable.get("schema_name");
/* 231 */       String table = (String)rawTable.get("table_name");
/* 232 */       String column = (String)rawTable.get("column_name");
/* 233 */       ShardTable st = new ShardTable(database, table, column);
/* 234 */       tables.add(st);
/*     */     } 
/* 236 */     return new FabricStateResponse(tables, tablesResponse.getTtl());
/*     */   }
/*     */   
/*     */   private FabricStateResponse<Set<ShardIndex>> getShardIndices(int shardMappingId) throws FabricCommunicationException {
/* 240 */     int version = 0;
/* 241 */     Object[] args = { Integer.valueOf(version), String.valueOf(shardMappingId) };
/* 242 */     Response indexResponse = errorSafeCallMethod("dump.shard_index", args);
/* 243 */     Set<ShardIndex> indices = new HashSet<ShardIndex>();
/*     */ 
/*     */     
/* 246 */     for (Map<String, ?> rawIndexEntry : (Iterable<Map<String, ?>>)indexResponse.getResultSet()) {
/* 247 */       String bound = (String)rawIndexEntry.get("lower_bound");
/* 248 */       int shardId = ((Integer)rawIndexEntry.get("shard_id")).intValue();
/* 249 */       String groupName = (String)rawIndexEntry.get("group_id");
/* 250 */       ShardIndex si = new ShardIndex(bound, Integer.valueOf(shardId), groupName);
/* 251 */       indices.add(si);
/*     */     } 
/* 253 */     return new FabricStateResponse(indices, indexResponse.getTtl());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FabricStateResponse<Set<ShardMapping>> getShardMappings(String shardMappingIdPattern) throws FabricCommunicationException {
/* 264 */     int version = 0;
/* 265 */     Object[] args = { Integer.valueOf(version), shardMappingIdPattern };
/* 266 */     Response mapsResponse = errorSafeCallMethod("dump.shard_maps", args);
/*     */     
/* 268 */     long minExpireTimeMillis = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(mapsResponse.getTtl());
/* 269 */     int baseTtl = mapsResponse.getTtl();
/*     */ 
/*     */     
/* 272 */     Set<ShardMapping> mappings = new HashSet<ShardMapping>();
/* 273 */     for (Map<String, ?> rawMapping : (Iterable<Map<String, ?>>)mapsResponse.getResultSet()) {
/* 274 */       int mappingId = ((Integer)rawMapping.get("mapping_id")).intValue();
/* 275 */       ShardingType shardingType = ShardingType.valueOf((String)rawMapping.get("type_name"));
/* 276 */       String globalGroupName = (String)rawMapping.get("global_group_id");
/*     */       
/* 278 */       FabricStateResponse<Set<ShardTable>> tables = getShardTables(mappingId);
/* 279 */       FabricStateResponse<Set<ShardIndex>> indices = getShardIndices(mappingId);
/*     */       
/* 281 */       if (tables.getExpireTimeMillis() < minExpireTimeMillis) {
/* 282 */         minExpireTimeMillis = tables.getExpireTimeMillis();
/*     */       }
/* 284 */       if (indices.getExpireTimeMillis() < minExpireTimeMillis) {
/* 285 */         minExpireTimeMillis = indices.getExpireTimeMillis();
/*     */       }
/*     */       
/* 288 */       ShardMapping m = (new ShardMappingFactory()).createShardMapping(mappingId, shardingType, globalGroupName, (Set)tables.getData(), (Set)indices.getData());
/* 289 */       mappings.add(m);
/*     */     } 
/*     */     
/* 292 */     return new FabricStateResponse(mappings, baseTtl, minExpireTimeMillis);
/*     */   }
/*     */   
/*     */   public FabricStateResponse<Set<ShardMapping>> getShardMappings() throws FabricCommunicationException {
/* 296 */     return getShardMappings("");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createGroup(String groupName) throws FabricCommunicationException {
/* 303 */     errorSafeCallMethod("group.create", new Object[] { groupName });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void destroyGroup(String groupName) throws FabricCommunicationException {
/* 310 */     errorSafeCallMethod("group.destroy", new Object[] { groupName });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createServerInGroup(String groupName, String hostname, int port) throws FabricCommunicationException {
/* 317 */     errorSafeCallMethod("group.add", new Object[] { groupName, hostname + ":" + port });
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
/*     */   public int createShardMapping(ShardingType type, String globalGroupName) throws FabricCommunicationException {
/* 330 */     Response r = errorSafeCallMethod("sharding.create_definition", new Object[] { type.toString(), globalGroupName });
/* 331 */     return ((Integer)((Map)r.getResultSet().get(0)).get("result")).intValue();
/*     */   }
/*     */   
/*     */   public void createShardTable(int shardMappingId, String database, String table, String column) throws FabricCommunicationException {
/* 335 */     errorSafeCallMethod("sharding.add_table", new Object[] { Integer.valueOf(shardMappingId), database + "." + table, column });
/*     */   }
/*     */   
/*     */   public void createShardIndex(int shardMappingId, String groupNameLowerBoundList) throws FabricCommunicationException {
/* 339 */     String status = "ENABLED";
/* 340 */     errorSafeCallMethod("sharding.add_shard", new Object[] { Integer.valueOf(shardMappingId), groupNameLowerBoundList, status });
/*     */   }
/*     */   
/*     */   public void addServerToGroup(String groupName, String hostname, int port) throws FabricCommunicationException {
/* 344 */     errorSafeCallMethod("group.add", new Object[] { groupName, hostname + ":" + port });
/*     */   }
/*     */   
/*     */   public void removeServerFromGroup(String groupName, String hostname, int port) throws FabricCommunicationException {
/* 348 */     errorSafeCallMethod("group.remove", new Object[] { groupName, hostname + ":" + port });
/*     */   }
/*     */   
/*     */   public void promoteServerInGroup(String groupName, String hostname, int port) throws FabricCommunicationException {
/* 352 */     ServerGroup serverGroup = getServerGroup(groupName);
/* 353 */     for (Server s : serverGroup.getServers()) {
/* 354 */       if (s.getHostname().equals(hostname) && s.getPort() == port) {
/* 355 */         errorSafeCallMethod("group.promote", new Object[] { groupName, s.getUuid() });
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void reportServerError(Server server, String errorDescription, boolean forceFaulty) throws FabricCommunicationException {
/* 362 */     String reporter = "MySQL Connector/J";
/* 363 */     String command = "threat.report_error";
/* 364 */     if (forceFaulty) {
/* 365 */       command = "threat.report_failure";
/*     */     }
/* 367 */     errorSafeCallMethod(command, new Object[] { server.getUuid(), reporter, errorDescription });
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\fabric\proto\xmlrpc\XmlRpcClient.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */