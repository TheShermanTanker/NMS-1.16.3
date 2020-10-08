/*    */ package org.bukkit.craftbukkit.v1_16_R2.command;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.bukkit.Server;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.SimpleCommandMap;
/*    */ 
/*    */ public class CraftCommandMap
/*    */   extends SimpleCommandMap {
/*    */   public CraftCommandMap(Server server) {
/* 11 */     super(server);
/*    */   }
/*    */   
/*    */   public Map<String, Command> getKnownCommands() {
/* 15 */     return this.knownCommands;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\command\CraftCommandMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */