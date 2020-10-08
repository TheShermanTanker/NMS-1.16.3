/*    */ package org.bukkit.conversations;
/*    */ 
/*    */ import java.util.EventObject;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConversationAbandonedEvent
/*    */   extends EventObject
/*    */ {
/*    */   private ConversationContext context;
/*    */   private ConversationCanceller canceller;
/*    */   
/*    */   public ConversationAbandonedEvent(@NotNull Conversation conversation) {
/* 17 */     this(conversation, null);
/*    */   }
/*    */   
/*    */   public ConversationAbandonedEvent(@NotNull Conversation conversation, @Nullable ConversationCanceller canceller) {
/* 21 */     super(conversation);
/* 22 */     this.context = conversation.getContext();
/* 23 */     this.canceller = canceller;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public ConversationCanceller getCanceller() {
/* 33 */     return this.canceller;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ConversationContext getContext() {
/* 43 */     return this.context;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean gracefulExit() {
/* 55 */     return (this.canceller == null);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\conversations\ConversationAbandonedEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */