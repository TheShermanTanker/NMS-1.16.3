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
/*    */ public class ServerGroup
/*    */ {
/*    */   private String name;
/*    */   private Set<Server> servers;
/*    */   
/*    */   public ServerGroup(String name, Set<Server> servers) {
/* 36 */     this.name = name;
/* 37 */     this.servers = servers;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 41 */     return this.name;
/*    */   }
/*    */   
/*    */   public Set<Server> getServers() {
/* 45 */     return this.servers;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Server getMaster() {
/* 54 */     for (Server s : this.servers) {
/* 55 */       if (s.getRole() == ServerRole.PRIMARY) {
/* 56 */         return s;
/*    */       }
/*    */     } 
/* 59 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Server getServer(String hostPortString) {
/* 68 */     for (Server s : this.servers) {
/* 69 */       if (s.getHostPortString().equals(hostPortString)) {
/* 70 */         return s;
/*    */       }
/*    */     } 
/* 73 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 78 */     return String.format("Group[name=%s, servers=%s]", new Object[] { this.name, this.servers });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\fabric\ServerGroup.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */