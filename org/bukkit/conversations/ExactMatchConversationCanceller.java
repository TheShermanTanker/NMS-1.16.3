/*    */ package org.bukkit.conversations;
/*    */ 
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExactMatchConversationCanceller
/*    */   implements ConversationCanceller
/*    */ {
/*    */   private String escapeSequence;
/*    */   
/*    */   public ExactMatchConversationCanceller(@NotNull String escapeSequence) {
/* 19 */     this.escapeSequence = escapeSequence;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setConversation(@NotNull Conversation conversation) {}
/*    */ 
/*    */   
/*    */   public boolean cancelBasedOnInput(@NotNull ConversationContext context, @NotNull String input) {
/* 27 */     return input.equals(this.escapeSequence);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ConversationCanceller clone() {
/* 33 */     return new ExactMatchConversationCanceller(this.escapeSequence);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\conversations\ExactMatchConversationCanceller.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */