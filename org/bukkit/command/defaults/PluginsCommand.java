/*    */ package org.bukkit.command.defaults;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.TreeMap;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.UnsafeValues;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class PluginsCommand extends BukkitCommand {
/*    */   public PluginsCommand(@NotNull String name) {
/* 17 */     super(name);
/* 18 */     this.description = "Gets a list of plugins running on the server";
/* 19 */     this.usageMessage = "/plugins";
/* 20 */     setPermission("bukkit.command.plugins");
/* 21 */     setAliases(Arrays.asList(new String[] { "pl" }));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean execute(@NotNull CommandSender sender, @NotNull String currentAlias, @NotNull String[] args) {
/* 26 */     if (!testPermission(sender)) return true;
/*    */     
/* 28 */     sender.sendMessage("Plugins " + getPluginList());
/* 29 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
/* 35 */     return Collections.emptyList();
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   private String getPluginList() {
/* 41 */     TreeMap<String, Plugin> plugins = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
/*    */     
/* 43 */     for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
/* 44 */       plugins.put(plugin.getDescription().getName(), plugin);
/*    */     }
/*    */     
/* 47 */     StringBuilder pluginList = new StringBuilder();
/* 48 */     for (Map.Entry<String, Plugin> entry : plugins.entrySet()) {
/* 49 */       if (pluginList.length() > 0) {
/* 50 */         pluginList.append(ChatColor.WHITE);
/* 51 */         pluginList.append(", ");
/*    */       } 
/*    */       
/* 54 */       Plugin plugin = entry.getValue();
/*    */       
/* 56 */       if (plugin.getDescription().getProvides().size() > 0) {
/* 57 */         pluginList.append(" (").append(String.join(", ", plugin.getDescription().getProvides())).append(")");
/*    */       }
/*    */ 
/*    */       
/* 61 */       pluginList.append(plugin.isEnabled() ? ChatColor.GREEN : ChatColor.RED);
/*    */       
/* 63 */       String pluginName = plugin.getDescription().getName();
/* 64 */       if (UnsafeValues.isLegacyPlugin(plugin)) {
/* 65 */         pluginName = pluginName + "*";
/*    */       }
/* 67 */       pluginList.append(pluginName);
/*    */     } 
/*    */ 
/*    */     
/* 71 */     return "(" + plugins.size() + "): " + pluginList.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\command\defaults\PluginsCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */