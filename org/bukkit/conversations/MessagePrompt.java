/*    */ package org.bukkit.conversations;
/*    */ 
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class MessagePrompt
/*    */   implements Prompt
/*    */ {
/*    */   public boolean blocksForInput(@NotNull ConversationContext context) {
/* 24 */     return false;
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
/*    */   
/*    */   @Nullable
/*    */   public Prompt acceptInput(@NotNull ConversationContext context, @Nullable String input) {
/* 38 */     return getNextPrompt(context);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   protected abstract Prompt getNextPrompt(@NotNull ConversationContext paramConversationContext);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\conversations\MessagePrompt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */