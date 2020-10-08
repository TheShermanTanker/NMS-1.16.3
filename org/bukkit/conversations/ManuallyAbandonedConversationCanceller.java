/*    */ package org.bukkit.conversations;
/*    */ 
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ManuallyAbandonedConversationCanceller
/*    */   implements ConversationCanceller
/*    */ {
/*    */   public void setConversation(@NotNull Conversation conversation) {
/* 13 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean cancelBasedOnInput(@NotNull ConversationContext context, @NotNull String input) {
/* 18 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ConversationCanceller clone() {
/* 24 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\conversations\ManuallyAbandonedConversationCanceller.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */