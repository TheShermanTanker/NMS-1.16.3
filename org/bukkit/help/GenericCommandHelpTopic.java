/*    */ package org.bukkit.help;
/*    */ 
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GenericCommandHelpTopic
/*    */   extends HelpTopic
/*    */ {
/*    */   protected Command command;
/*    */   
/*    */   public GenericCommandHelpTopic(@NotNull Command command) {
/* 21 */     this.command = command;
/*    */     
/* 23 */     if (command.getLabel().startsWith("/")) {
/* 24 */       this.name = command.getLabel();
/*    */     } else {
/* 26 */       this.name = "/" + command.getLabel();
/*    */     } 
/*    */ 
/*    */     
/* 30 */     int i = command.getDescription().indexOf('\n');
/* 31 */     if (i > 1) {
/* 32 */       this.shortText = command.getDescription().substring(0, i - 1);
/*    */     } else {
/* 34 */       this.shortText = command.getDescription();
/*    */     } 
/*    */ 
/*    */     
/* 38 */     StringBuilder sb = new StringBuilder();
/*    */     
/* 40 */     sb.append(ChatColor.GOLD);
/* 41 */     sb.append("Description: ");
/* 42 */     sb.append(ChatColor.WHITE);
/* 43 */     sb.append(command.getDescription());
/*    */     
/* 45 */     sb.append("\n");
/*    */     
/* 47 */     sb.append(ChatColor.GOLD);
/* 48 */     sb.append("Usage: ");
/* 49 */     sb.append(ChatColor.WHITE);
/* 50 */     sb.append(command.getUsage().replace("<command>", this.name.substring(1)));
/*    */     
/* 52 */     if (command.getAliases().size() > 0) {
/* 53 */       sb.append("\n");
/* 54 */       sb.append(ChatColor.GOLD);
/* 55 */       sb.append("Aliases: ");
/* 56 */       sb.append(ChatColor.WHITE);
/* 57 */       sb.append(ChatColor.WHITE + StringUtils.join(command.getAliases(), ", "));
/*    */     } 
/* 59 */     this.fullText = sb.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canSee(@NotNull CommandSender sender) {
/* 64 */     if (!this.command.isRegistered())
/*    */     {
/* 66 */       return false;
/*    */     }
/*    */     
/* 69 */     if (sender instanceof org.bukkit.command.ConsoleCommandSender) {
/* 70 */       return true;
/*    */     }
/*    */     
/* 73 */     if (this.amendedPermission != null) {
/* 74 */       return sender.hasPermission(this.amendedPermission);
/*    */     }
/* 76 */     return this.command.testPermissionSilent(sender);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\help\GenericCommandHelpTopic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */