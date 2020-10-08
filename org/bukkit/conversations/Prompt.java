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
/*    */ public interface Prompt
/*    */   extends Cloneable
/*    */ {
/* 18 */   public static final Prompt END_OF_CONVERSATION = null;
/*    */   
/*    */   @NotNull
/*    */   String getPromptText(@NotNull ConversationContext paramConversationContext);
/*    */   
/*    */   boolean blocksForInput(@NotNull ConversationContext paramConversationContext);
/*    */   
/*    */   @Nullable
/*    */   Prompt acceptInput(@NotNull ConversationContext paramConversationContext, @Nullable String paramString);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\conversations\Prompt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */