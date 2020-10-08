/*    */ package org.bukkit.conversations;
/*    */ 
/*    */ import java.util.regex.Pattern;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class RegexPrompt
/*    */   extends ValidatingPrompt
/*    */ {
/*    */   private Pattern pattern;
/*    */   
/*    */   public RegexPrompt(@NotNull String regex) {
/* 15 */     this(Pattern.compile(regex));
/*    */   }
/*    */ 
/*    */   
/*    */   public RegexPrompt(@NotNull Pattern pattern) {
/* 20 */     this.pattern = pattern;
/*    */   }
/*    */ 
/*    */   
/*    */   private RegexPrompt() {}
/*    */   
/*    */   protected boolean isInputValid(@NotNull ConversationContext context, @NotNull String input) {
/* 27 */     return this.pattern.matcher(input).matches();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\conversations\RegexPrompt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */