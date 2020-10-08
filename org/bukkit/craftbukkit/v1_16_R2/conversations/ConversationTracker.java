/*    */ package org.bukkit.craftbukkit.v1_16_R2.conversations;
/*    */ 
/*    */ import java.util.LinkedList;
/*    */ import java.util.logging.Level;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.conversations.Conversation;
/*    */ import org.bukkit.conversations.ConversationAbandonedEvent;
/*    */ import org.bukkit.conversations.ConversationCanceller;
/*    */ import org.bukkit.conversations.ManuallyAbandonedConversationCanceller;
/*    */ 
/*    */ 
/*    */ public class ConversationTracker
/*    */ {
/* 14 */   private LinkedList<Conversation> conversationQueue = new LinkedList<>();
/*    */   
/*    */   public synchronized boolean beginConversation(Conversation conversation) {
/* 17 */     if (!this.conversationQueue.contains(conversation)) {
/* 18 */       this.conversationQueue.addLast(conversation);
/* 19 */       if (this.conversationQueue.getFirst() == conversation) {
/* 20 */         conversation.begin();
/* 21 */         conversation.outputNextPrompt();
/* 22 */         return true;
/*    */       } 
/*    */     } 
/* 25 */     return true;
/*    */   }
/*    */   
/*    */   public synchronized void abandonConversation(Conversation conversation, ConversationAbandonedEvent details) {
/* 29 */     if (!this.conversationQueue.isEmpty()) {
/* 30 */       if (this.conversationQueue.getFirst() == conversation) {
/* 31 */         conversation.abandon(details);
/*    */       }
/* 33 */       if (this.conversationQueue.contains(conversation)) {
/* 34 */         this.conversationQueue.remove(conversation);
/*    */       }
/* 36 */       if (!this.conversationQueue.isEmpty()) {
/* 37 */         ((Conversation)this.conversationQueue.getFirst()).outputNextPrompt();
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized void abandonAllConversations() {
/* 44 */     LinkedList<Conversation> oldQueue = this.conversationQueue;
/* 45 */     this.conversationQueue = new LinkedList<>();
/* 46 */     for (Conversation conversation : oldQueue) {
/*    */       try {
/* 48 */         conversation.abandon(new ConversationAbandonedEvent(conversation, (ConversationCanceller)new ManuallyAbandonedConversationCanceller()));
/* 49 */       } catch (Throwable t) {
/* 50 */         Bukkit.getLogger().log(Level.SEVERE, "Unexpected exception while abandoning a conversation", t);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public synchronized void acceptConversationInput(String input) {
/* 56 */     if (isConversing()) {
/* 57 */       Conversation conversation = this.conversationQueue.getFirst();
/*    */       try {
/* 59 */         conversation.acceptInput(input);
/* 60 */       } catch (Throwable t) {
/* 61 */         conversation.getContext().getPlugin().getLogger().log(Level.WARNING, 
/* 62 */             String.format("Plugin %s generated an exception whilst handling conversation input", new Object[] {
/* 63 */                 conversation.getContext().getPlugin().getDescription().getFullName()
/*    */               }), t);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public synchronized boolean isConversing() {
/* 70 */     return !this.conversationQueue.isEmpty();
/*    */   }
/*    */   
/*    */   public synchronized boolean isConversingModaly() {
/* 74 */     return (isConversing() && ((Conversation)this.conversationQueue.getFirst()).isModal());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\conversations\ConversationTracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */