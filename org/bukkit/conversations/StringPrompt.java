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
/*    */ 
/*    */ 
/*    */ public abstract class StringPrompt
/*    */   implements Prompt
/*    */ {
/*    */   public boolean blocksForInput(@NotNull ConversationContext context) {
/* 19 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\conversations\StringPrompt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */