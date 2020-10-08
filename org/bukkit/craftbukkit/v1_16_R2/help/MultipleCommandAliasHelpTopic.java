/*    */ package org.bukkit.craftbukkit.v1_16_R2.help;
/*    */ 
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.command.MultipleCommandAlias;
/*    */ import org.bukkit.help.HelpTopic;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MultipleCommandAliasHelpTopic
/*    */   extends HelpTopic
/*    */ {
/*    */   private final MultipleCommandAlias alias;
/*    */   
/*    */   public MultipleCommandAliasHelpTopic(MultipleCommandAlias alias) {
/* 18 */     this.alias = alias;
/*    */     
/* 20 */     this.name = "/" + alias.getLabel();
/*    */ 
/*    */     
/* 23 */     StringBuilder sb = new StringBuilder();
/* 24 */     for (int i = 0; i < (alias.getCommands()).length; i++) {
/* 25 */       if (i != 0) {
/* 26 */         sb.append(ChatColor.GOLD + " > " + ChatColor.WHITE);
/*    */       }
/* 28 */       sb.append("/");
/* 29 */       sb.append(alias.getCommands()[i].getLabel());
/*    */     } 
/* 31 */     this.shortText = sb.toString();
/*    */ 
/*    */     
/* 34 */     this.fullText = ChatColor.GOLD + "Alias for: " + ChatColor.WHITE + getShortText();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canSee(CommandSender sender) {
/* 39 */     if (this.amendedPermission == null) {
/* 40 */       if (sender instanceof org.bukkit.command.ConsoleCommandSender) {
/* 41 */         return true;
/*    */       }
/*    */       
/* 44 */       for (Command command : this.alias.getCommands()) {
/* 45 */         if (!command.testPermissionSilent(sender)) {
/* 46 */           return false;
/*    */         }
/*    */       } 
/*    */       
/* 50 */       return true;
/*    */     } 
/* 52 */     return sender.hasPermission(this.amendedPermission);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\help\MultipleCommandAliasHelpTopic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */