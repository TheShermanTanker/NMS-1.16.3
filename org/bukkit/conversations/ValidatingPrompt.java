/*    */ package org.bukkit.conversations;
/*    */ 
/*    */ import org.bukkit.ChatColor;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ValidatingPrompt
/*    */   implements Prompt
/*    */ {
/*    */   @Nullable
/*    */   public Prompt acceptInput(@NotNull ConversationContext context, @Nullable String input) {
/* 29 */     if (isInputValid(context, input)) {
/* 30 */       return acceptValidatedInput(context, input);
/*    */     }
/* 32 */     String failPrompt = getFailedValidationText(context, input);
/* 33 */     if (failPrompt != null) {
/* 34 */       context.getForWhom().sendRawMessage(ChatColor.RED + failPrompt);
/*    */     }
/*    */     
/* 37 */     return this;
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
/*    */   public boolean blocksForInput(@NotNull ConversationContext context) {
/* 49 */     return true;
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
/*    */   protected abstract boolean isInputValid(@NotNull ConversationContext paramConversationContext, @NotNull String paramString);
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
/*    */   protected abstract Prompt acceptValidatedInput(@NotNull ConversationContext paramConversationContext, @NotNull String paramString);
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
/*    */   protected String getFailedValidationText(@NotNull ConversationContext context, @NotNull String invalidInput) {
/* 83 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\conversations\ValidatingPrompt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */