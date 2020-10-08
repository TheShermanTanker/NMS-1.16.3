/*    */ package org.bukkit.conversations;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class FixedSetPrompt
/*    */   extends ValidatingPrompt
/*    */ {
/*    */   protected List<String> fixedSet;
/*    */   
/*    */   public FixedSetPrompt(@NotNull String... fixedSet) {
/* 26 */     this.fixedSet = Arrays.asList(fixedSet);
/*    */   }
/*    */ 
/*    */   
/*    */   private FixedSetPrompt() {}
/*    */   
/*    */   protected boolean isInputValid(@NotNull ConversationContext context, @NotNull String input) {
/* 33 */     return this.fixedSet.contains(input);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   protected String formatFixedSet() {
/* 45 */     return "[" + StringUtils.join(this.fixedSet, ", ") + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\conversations\FixedSetPrompt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */