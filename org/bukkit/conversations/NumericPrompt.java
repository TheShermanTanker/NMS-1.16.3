/*    */ package org.bukkit.conversations;
/*    */ 
/*    */ import org.apache.commons.lang.math.NumberUtils;
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
/*    */ public abstract class NumericPrompt
/*    */   extends ValidatingPrompt
/*    */ {
/*    */   protected boolean isInputValid(@NotNull ConversationContext context, @NotNull String input) {
/* 18 */     return (NumberUtils.isNumber(input) && isNumberValid(context, NumberUtils.createNumber(input)));
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
/*    */   protected boolean isNumberValid(@NotNull ConversationContext context, @NotNull Number input) {
/* 30 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   protected Prompt acceptValidatedInput(@NotNull ConversationContext context, @NotNull String input) {
/*    */     try {
/* 37 */       return acceptValidatedInput(context, NumberUtils.createNumber(input));
/* 38 */     } catch (NumberFormatException e) {
/* 39 */       return acceptValidatedInput(context, NumberUtils.INTEGER_ZERO);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   protected abstract Prompt acceptValidatedInput(@NotNull ConversationContext paramConversationContext, @NotNull Number paramNumber);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   protected String getFailedValidationText(@NotNull ConversationContext context, @NotNull String invalidInput) {
/* 57 */     if (NumberUtils.isNumber(invalidInput)) {
/* 58 */       return getFailedValidationText(context, NumberUtils.createNumber(invalidInput));
/*    */     }
/* 60 */     return getInputNotNumericText(context, invalidInput);
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
/*    */   protected String getInputNotNumericText(@NotNull ConversationContext context, @NotNull String invalidInput) {
/* 74 */     return null;
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
/*    */   @Nullable
/*    */   protected String getFailedValidationText(@NotNull ConversationContext context, @NotNull Number invalidInput) {
/* 87 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\conversations\NumericPrompt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */