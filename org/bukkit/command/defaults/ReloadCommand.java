/*    */ package org.bukkit.command.defaults;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class ReloadCommand extends BukkitCommand {
/*    */   public ReloadCommand(@NotNull String name) {
/* 14 */     super(name);
/* 15 */     this.description = "Reloads the server configuration and plugins";
/* 16 */     this.usageMessage = "/reload [permissions|commands|confirm]";
/* 17 */     setPermission("bukkit.command.reload");
/* 18 */     setAliases(Arrays.asList(new String[] { "rl" }));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean execute(@NotNull CommandSender sender, @NotNull String currentAlias, @NotNull String[] args) {
/* 23 */     if (!testPermission(sender)) return true;
/*    */ 
/*    */     
/* 26 */     boolean confirmed = (System.getProperty("LetMeReload") != null);
/* 27 */     if (args.length == 1) {
/* 28 */       if (args[0].equalsIgnoreCase("permissions")) {
/* 29 */         Bukkit.getServer().reloadPermissions();
/* 30 */         Command.broadcastCommandMessage(sender, ChatColor.GREEN + "Permissions successfully reloaded.");
/* 31 */         return true;
/* 32 */       }  if ("commands".equalsIgnoreCase(args[0])) {
/* 33 */         if (Bukkit.getServer().reloadCommandAliases()) {
/* 34 */           Command.broadcastCommandMessage(sender, ChatColor.GREEN + "Command aliases successfully reloaded.");
/*    */         } else {
/* 36 */           Command.broadcastCommandMessage(sender, ChatColor.RED + "An error occurred while trying to reload command aliases.");
/*    */         } 
/* 38 */         return true;
/* 39 */       }  if ("confirm".equalsIgnoreCase(args[0])) {
/* 40 */         confirmed = true;
/*    */       } else {
/* 42 */         Command.broadcastCommandMessage(sender, ChatColor.RED + "Usage: " + this.usageMessage);
/* 43 */         return true;
/*    */       } 
/*    */     } 
/* 46 */     if (!confirmed) {
/* 47 */       Command.broadcastCommandMessage(sender, ChatColor.RED + "Are you sure you wish to reload your server? Doing so may cause bugs and memory leaks. It is recommended to restart instead of using /reload. To confirm, please type " + ChatColor.YELLOW + "/reload confirm");
/* 48 */       return true;
/*    */     } 
/*    */ 
/*    */     
/* 52 */     Command.broadcastCommandMessage(sender, ChatColor.RED + "Please note that this command is not supported and may cause issues when using some plugins.");
/* 53 */     Command.broadcastCommandMessage(sender, ChatColor.RED + "If you encounter any issues please use the /stop command to restart your server.");
/* 54 */     Bukkit.reload();
/* 55 */     Command.broadcastCommandMessage(sender, ChatColor.GREEN + "Reload complete.");
/*    */     
/* 57 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
/* 63 */     return Lists.newArrayList((Object[])new String[] { "permissions", "commands" });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\command\defaults\ReloadCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */