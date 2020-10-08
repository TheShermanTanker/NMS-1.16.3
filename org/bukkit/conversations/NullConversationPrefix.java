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
/*    */ public class NullConversationPrefix
/*    */   implements ConversationPrefix
/*    */ {
/*    */   @NotNull
/*    */   public String getPrefix(@NotNull ConversationContext context) {
/* 20 */     return "";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\conversations\NullConversationPrefix.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */