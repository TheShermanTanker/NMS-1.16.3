/*    */ package org.bukkit.craftbukkit.v1_16_R2.command;
/*    */ 
/*    */ import com.destroystokyo.paper.PaperConfig;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.ConsoleCommandSender;
/*    */ import org.bukkit.conversations.Conversation;
/*    */ import org.bukkit.conversations.ConversationAbandonedEvent;
/*    */ import org.bukkit.conversations.ConversationCanceller;
/*    */ import org.bukkit.conversations.ManuallyAbandonedConversationCanceller;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.conversations.ConversationTracker;
/*    */ import org.bukkit.permissions.Permission;
/*    */ 
/*    */ public class CraftConsoleCommandSender
/*    */   extends ServerCommandSender implements ConsoleCommandSender {
/* 15 */   protected final ConversationTracker conversationTracker = new ConversationTracker();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void sendMessage(String message) {
/* 23 */     sendRawMessage(message);
/*    */   }
/*    */ 
/*    */   
/*    */   public void sendRawMessage(String message) {
/* 28 */     System.out.println(ChatColor.stripColor(message));
/*    */   }
/*    */ 
/*    */   
/*    */   public void sendMessage(String[] messages) {
/* 33 */     for (String message : messages) {
/* 34 */       sendMessage(message);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 40 */     return "CONSOLE";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isOp() {
/* 45 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setOp(boolean value) {
/* 50 */     throw new UnsupportedOperationException("Cannot change operator status of server console");
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean beginConversation(Conversation conversation) {
/* 55 */     return this.conversationTracker.beginConversation(conversation);
/*    */   }
/*    */ 
/*    */   
/*    */   public void abandonConversation(Conversation conversation) {
/* 60 */     this.conversationTracker.abandonConversation(conversation, new ConversationAbandonedEvent(conversation, (ConversationCanceller)new ManuallyAbandonedConversationCanceller()));
/*    */   }
/*    */ 
/*    */   
/*    */   public void abandonConversation(Conversation conversation, ConversationAbandonedEvent details) {
/* 65 */     this.conversationTracker.abandonConversation(conversation, details);
/*    */   }
/*    */ 
/*    */   
/*    */   public void acceptConversationInput(String input) {
/* 70 */     this.conversationTracker.acceptConversationInput(input);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConversing() {
/* 75 */     return this.conversationTracker.isConversing();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasPermission(String name) {
/* 81 */     return (PaperConfig.consoleHasAllPermissions || super.hasPermission(name));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasPermission(Permission perm) {
/* 86 */     return (PaperConfig.consoleHasAllPermissions || super.hasPermission(perm));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\command\CraftConsoleCommandSender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */