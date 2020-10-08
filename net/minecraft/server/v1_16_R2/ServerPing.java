/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import com.google.gson.JsonSerializer;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.UUID;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ServerPing
/*     */ {
/*     */   private IChatBaseComponent a;
/*     */   private ServerPingPlayerSample b;
/*     */   private ServerData c;
/*     */   private String d;
/*     */   
/*     */   public IChatBaseComponent a() {
/*  25 */     return this.a;
/*     */   }
/*     */   
/*     */   public void setMOTD(IChatBaseComponent ichatbasecomponent) {
/*  29 */     this.a = ichatbasecomponent;
/*     */   }
/*     */   public ServerPingPlayerSample getPlayers() {
/*  32 */     return b();
/*     */   } public ServerPingPlayerSample b() {
/*  34 */     return this.b;
/*     */   }
/*     */   
/*     */   public void setPlayerSample(ServerPingPlayerSample serverping_serverpingplayersample) {
/*  38 */     this.b = serverping_serverpingplayersample;
/*     */   }
/*     */   
/*     */   public ServerData getServerData() {
/*  42 */     return this.c;
/*     */   }
/*     */   
/*     */   public void setServerInfo(ServerData serverping_serverdata) {
/*  46 */     this.c = serverping_serverdata;
/*     */   }
/*     */   
/*     */   public void setFavicon(String s) {
/*  50 */     this.d = s;
/*     */   }
/*     */   
/*     */   public String d() {
/*  54 */     return this.d;
/*     */   }
/*     */ 
/*     */   
/*     */   public static class Serializer
/*     */     implements JsonDeserializer<ServerPing>, JsonSerializer<ServerPing>
/*     */   {
/*     */     public ServerPing deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
/*  62 */       JsonObject jsonobject = ChatDeserializer.m(jsonelement, "status");
/*  63 */       ServerPing serverping = new ServerPing();
/*     */       
/*  65 */       if (jsonobject.has("description")) {
/*  66 */         serverping.setMOTD((IChatBaseComponent)jsondeserializationcontext.deserialize(jsonobject.get("description"), IChatBaseComponent.class));
/*     */       }
/*     */       
/*  69 */       if (jsonobject.has("players")) {
/*  70 */         serverping.setPlayerSample((ServerPing.ServerPingPlayerSample)jsondeserializationcontext.deserialize(jsonobject.get("players"), ServerPing.ServerPingPlayerSample.class));
/*     */       }
/*     */       
/*  73 */       if (jsonobject.has("version")) {
/*  74 */         serverping.setServerInfo((ServerPing.ServerData)jsondeserializationcontext.deserialize(jsonobject.get("version"), ServerPing.ServerData.class));
/*     */       }
/*     */       
/*  77 */       if (jsonobject.has("favicon")) {
/*  78 */         serverping.setFavicon(ChatDeserializer.h(jsonobject, "favicon"));
/*     */       }
/*     */       
/*  81 */       return serverping;
/*     */     }
/*     */     
/*     */     public JsonElement serialize(ServerPing serverping, Type type, JsonSerializationContext jsonserializationcontext) {
/*  85 */       JsonObject jsonobject = new JsonObject();
/*     */       
/*  87 */       if (serverping.a() != null) {
/*  88 */         jsonobject.add("description", jsonserializationcontext.serialize(serverping.a()));
/*     */       }
/*     */       
/*  91 */       if (serverping.b() != null) {
/*  92 */         jsonobject.add("players", jsonserializationcontext.serialize(serverping.b()));
/*     */       }
/*     */       
/*  95 */       if (serverping.getServerData() != null) {
/*  96 */         jsonobject.add("version", jsonserializationcontext.serialize(serverping.getServerData()));
/*     */       }
/*     */       
/*  99 */       if (serverping.d() != null) {
/* 100 */         jsonobject.addProperty("favicon", serverping.d());
/*     */       }
/*     */       
/* 103 */       return (JsonElement)jsonobject;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ServerData
/*     */   {
/*     */     private final String a;
/*     */     private final int b;
/*     */     
/*     */     public ServerData(String s, int i) {
/* 113 */       this.a = s;
/* 114 */       this.b = i;
/*     */     }
/*     */     
/*     */     public String a() {
/* 118 */       return this.a;
/*     */     }
/*     */     
/*     */     public int getProtocolVersion() {
/* 122 */       return this.b;
/*     */     }
/*     */ 
/*     */     
/*     */     public static class Serializer
/*     */       implements JsonDeserializer<ServerData>, JsonSerializer<ServerData>
/*     */     {
/*     */       public ServerPing.ServerData deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
/* 130 */         JsonObject jsonobject = ChatDeserializer.m(jsonelement, "version");
/*     */         
/* 132 */         return new ServerPing.ServerData(ChatDeserializer.h(jsonobject, "name"), ChatDeserializer.n(jsonobject, "protocol"));
/*     */       }
/*     */       
/*     */       public JsonElement serialize(ServerPing.ServerData serverping_serverdata, Type type, JsonSerializationContext jsonserializationcontext) {
/* 136 */         JsonObject jsonobject = new JsonObject();
/*     */         
/* 138 */         jsonobject.addProperty("name", serverping_serverdata.a());
/* 139 */         jsonobject.addProperty("protocol", Integer.valueOf(serverping_serverdata.getProtocolVersion()));
/* 140 */         return (JsonElement)jsonobject;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ServerPingPlayerSample
/*     */   {
/*     */     private final int a;
/*     */     private final int b;
/*     */     private GameProfile[] c;
/*     */     
/*     */     public ServerPingPlayerSample(int i, int j) {
/* 152 */       this.a = i;
/* 153 */       this.b = j;
/*     */     }
/*     */     
/*     */     public int a() {
/* 157 */       return this.a;
/*     */     }
/*     */     
/*     */     public int b() {
/* 161 */       return this.b;
/*     */     }
/*     */     public GameProfile[] getSample() {
/* 164 */       return c();
/*     */     } public GameProfile[] c() {
/* 166 */       return this.c;
/*     */     }
/*     */     public void setSample(GameProfile[] sample) {
/* 169 */       a(sample);
/*     */     } public void a(GameProfile[] agameprofile) {
/* 171 */       this.c = agameprofile;
/*     */     }
/*     */ 
/*     */     
/*     */     public static class Serializer
/*     */       implements JsonDeserializer<ServerPingPlayerSample>, JsonSerializer<ServerPingPlayerSample>
/*     */     {
/*     */       public ServerPing.ServerPingPlayerSample deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
/* 179 */         JsonObject jsonobject = ChatDeserializer.m(jsonelement, "players");
/* 180 */         ServerPing.ServerPingPlayerSample serverping_serverpingplayersample = new ServerPing.ServerPingPlayerSample(ChatDeserializer.n(jsonobject, "max"), ChatDeserializer.n(jsonobject, "online"));
/*     */         
/* 182 */         if (ChatDeserializer.d(jsonobject, "sample")) {
/* 183 */           JsonArray jsonarray = ChatDeserializer.u(jsonobject, "sample");
/*     */           
/* 185 */           if (jsonarray.size() > 0) {
/* 186 */             GameProfile[] agameprofile = new GameProfile[jsonarray.size()];
/*     */             
/* 188 */             for (int i = 0; i < agameprofile.length; i++) {
/* 189 */               JsonObject jsonobject1 = ChatDeserializer.m(jsonarray.get(i), "player[" + i + "]");
/* 190 */               String s = ChatDeserializer.h(jsonobject1, "id");
/*     */               
/* 192 */               agameprofile[i] = new GameProfile(UUID.fromString(s), ChatDeserializer.h(jsonobject1, "name"));
/*     */             } 
/*     */             
/* 195 */             serverping_serverpingplayersample.a(agameprofile);
/*     */           } 
/*     */         } 
/*     */         
/* 199 */         return serverping_serverpingplayersample;
/*     */       }
/*     */       
/*     */       public JsonElement serialize(ServerPing.ServerPingPlayerSample serverping_serverpingplayersample, Type type, JsonSerializationContext jsonserializationcontext) {
/* 203 */         JsonObject jsonobject = new JsonObject();
/*     */         
/* 205 */         jsonobject.addProperty("max", Integer.valueOf(serverping_serverpingplayersample.a()));
/* 206 */         jsonobject.addProperty("online", Integer.valueOf(serverping_serverpingplayersample.b()));
/* 207 */         if (serverping_serverpingplayersample.c() != null && (serverping_serverpingplayersample.c()).length > 0) {
/* 208 */           JsonArray jsonarray = new JsonArray();
/*     */           
/* 210 */           for (int i = 0; i < (serverping_serverpingplayersample.c()).length; i++) {
/* 211 */             JsonObject jsonobject1 = new JsonObject();
/* 212 */             UUID uuid = serverping_serverpingplayersample.c()[i].getId();
/*     */             
/* 214 */             jsonobject1.addProperty("id", (uuid == null) ? "" : uuid.toString());
/* 215 */             jsonobject1.addProperty("name", serverping_serverpingplayersample.c()[i].getName());
/* 216 */             jsonarray.add((JsonElement)jsonobject1);
/*     */           } 
/*     */           
/* 219 */           jsonobject.add("sample", (JsonElement)jsonarray);
/*     */         } 
/*     */         
/* 222 */         return (JsonElement)jsonobject;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ServerPing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */