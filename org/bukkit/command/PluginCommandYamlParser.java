/*    */ package org.bukkit.command;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class PluginCommandYamlParser
/*    */ {
/*    */   @NotNull
/*    */   public static List<Command> parse(@NotNull Plugin plugin) {
/* 15 */     List<Command> pluginCmds = new ArrayList<>();
/*    */     
/* 17 */     Map<String, Map<String, Object>> map = plugin.getDescription().getCommands();
/*    */     
/* 19 */     if (map == null) {
/* 20 */       return pluginCmds;
/*    */     }
/*    */     
/* 23 */     for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
/* 24 */       if (((String)entry.getKey()).contains(":")) {
/* 25 */         Bukkit.getServer().getLogger().severe("Could not load command " + (String)entry.getKey() + " for plugin " + plugin.getName() + ": Illegal Characters");
/*    */         continue;
/*    */       } 
/* 28 */       Command newCmd = new PluginCommand(entry.getKey(), plugin);
/* 29 */       Object description = ((Map)entry.getValue()).get("description");
/* 30 */       Object usage = ((Map)entry.getValue()).get("usage");
/* 31 */       Object aliases = ((Map)entry.getValue()).get("aliases");
/* 32 */       Object permission = ((Map)entry.getValue()).get("permission");
/* 33 */       Object permissionMessage = ((Map)entry.getValue()).get("permission-message");
/*    */       
/* 35 */       if (description != null) {
/* 36 */         newCmd.setDescription(description.toString());
/*    */       }
/*    */       
/* 39 */       if (usage != null) {
/* 40 */         newCmd.setUsage(usage.toString());
/*    */       }
/*    */       
/* 43 */       if (aliases != null) {
/* 44 */         List<String> aliasList = new ArrayList<>();
/*    */         
/* 46 */         if (aliases instanceof List) {
/* 47 */           for (Object o : aliases) {
/* 48 */             if (o.toString().contains(":")) {
/* 49 */               Bukkit.getServer().getLogger().severe("Could not load alias " + o.toString() + " for plugin " + plugin.getName() + ": Illegal Characters");
/*    */               continue;
/*    */             } 
/* 52 */             aliasList.add(o.toString());
/*    */           }
/*    */         
/* 55 */         } else if (aliases.toString().contains(":")) {
/* 56 */           Bukkit.getServer().getLogger().severe("Could not load alias " + aliases.toString() + " for plugin " + plugin.getName() + ": Illegal Characters");
/*    */         } else {
/* 58 */           aliasList.add(aliases.toString());
/*    */         } 
/*    */ 
/*    */         
/* 62 */         newCmd.setAliases(aliasList);
/*    */       } 
/*    */       
/* 65 */       if (permission != null) {
/* 66 */         newCmd.setPermission(permission.toString());
/*    */       }
/*    */       
/* 69 */       if (permissionMessage != null) {
/* 70 */         newCmd.setPermissionMessage(permissionMessage.toString());
/*    */       }
/*    */       
/* 73 */       pluginCmds.add(newCmd);
/*    */     } 
/* 75 */     return pluginCmds;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\command\PluginCommandYamlParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */