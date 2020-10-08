/*    */ package org.bukkit.craftbukkit.v1_16_R2.help;
/*    */ 
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.help.HelpTopic;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CustomHelpTopic
/*    */   extends HelpTopic
/*    */ {
/*    */   private final String permissionNode;
/*    */   
/*    */   public CustomHelpTopic(String name, String shortText, String fullText, String permissionNode) {
/* 14 */     this.permissionNode = permissionNode;
/* 15 */     this.name = name;
/* 16 */     this.shortText = shortText;
/* 17 */     this.fullText = shortText + "\n" + fullText;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canSee(CommandSender sender) {
/* 22 */     if (sender instanceof org.bukkit.command.ConsoleCommandSender) {
/* 23 */       return true;
/*    */     }
/*    */     
/* 26 */     if (!this.permissionNode.equals("")) {
/* 27 */       return sender.hasPermission(this.permissionNode);
/*    */     }
/* 29 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\help\CustomHelpTopic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */