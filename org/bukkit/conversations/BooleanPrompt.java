/*    */ package org.bukkit.conversations;
/*    */ 
/*    */ import org.apache.commons.lang.ArrayUtils;
/*    */ import org.apache.commons.lang.BooleanUtils;
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
/*    */ public abstract class BooleanPrompt
/*    */   extends ValidatingPrompt
/*    */ {
/*    */   protected boolean isInputValid(@NotNull ConversationContext context, @NotNull String input) {
/* 20 */     String[] accepted = { "true", "false", "on", "off", "yes", "no", "y", "n", "1", "0", "right", "wrong", "correct", "incorrect", "valid", "invalid" };
/* 21 */     return ArrayUtils.contains((Object[])accepted, input.toLowerCase());
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   protected Prompt acceptValidatedInput(@NotNull ConversationContext context, @NotNull String input) {
/* 27 */     if (input.equalsIgnoreCase("y") || input.equals("1") || input.equalsIgnoreCase("right") || input.equalsIgnoreCase("correct") || input.equalsIgnoreCase("valid")) input = "true"; 
/* 28 */     return acceptValidatedInput(context, BooleanUtils.toBoolean(input));
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   protected abstract Prompt acceptValidatedInput(@NotNull ConversationContext paramConversationContext, boolean paramBoolean);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\conversations\BooleanPrompt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */