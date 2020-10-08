/*    */ package org.bukkit.conversations;
/*    */ 
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InactivityConversationCanceller
/*    */   implements ConversationCanceller
/*    */ {
/*    */   protected Plugin plugin;
/*    */   protected int timeoutSeconds;
/*    */   protected Conversation conversation;
/* 14 */   private int taskId = -1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public InactivityConversationCanceller(@NotNull Plugin plugin, int timeoutSeconds) {
/* 23 */     this.plugin = plugin;
/* 24 */     this.timeoutSeconds = timeoutSeconds;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setConversation(@NotNull Conversation conversation) {
/* 29 */     this.conversation = conversation;
/* 30 */     startTimer();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean cancelBasedOnInput(@NotNull ConversationContext context, @NotNull String input) {
/* 36 */     stopTimer();
/* 37 */     startTimer();
/* 38 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ConversationCanceller clone() {
/* 44 */     return new InactivityConversationCanceller(this.plugin, this.timeoutSeconds);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void startTimer() {
/* 51 */     this.taskId = this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
/*    */         {
/*    */           public void run() {
/* 54 */             if (InactivityConversationCanceller.this.conversation.getState() == Conversation.ConversationState.UNSTARTED) {
/* 55 */               InactivityConversationCanceller.this.startTimer();
/* 56 */             } else if (InactivityConversationCanceller.this.conversation.getState() == Conversation.ConversationState.STARTED) {
/* 57 */               InactivityConversationCanceller.this.cancelling(InactivityConversationCanceller.this.conversation);
/* 58 */               InactivityConversationCanceller.this.conversation.abandon(new ConversationAbandonedEvent(InactivityConversationCanceller.this.conversation, InactivityConversationCanceller.this));
/*    */             } 
/*    */           }
/*    */         }(this.timeoutSeconds * 20));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void stopTimer() {
/* 68 */     if (this.taskId != -1) {
/* 69 */       this.plugin.getServer().getScheduler().cancelTask(this.taskId);
/* 70 */       this.taskId = -1;
/*    */     } 
/*    */   }
/*    */   
/*    */   protected void cancelling(@NotNull Conversation conversation) {}
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\conversations\InactivityConversationCanceller.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */