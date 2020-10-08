/*    */ package org.bukkit.conversations;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class PlayerNamePrompt
/*    */   extends ValidatingPrompt
/*    */ {
/*    */   private Plugin plugin;
/*    */   
/*    */   public PlayerNamePrompt(@NotNull Plugin plugin) {
/* 17 */     this.plugin = plugin;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean isInputValid(@NotNull ConversationContext context, @NotNull String input) {
/* 22 */     return (this.plugin.getServer().getPlayer(input) != null);
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   protected Prompt acceptValidatedInput(@NotNull ConversationContext context, @NotNull String input) {
/* 28 */     return acceptValidatedInput(context, this.plugin.getServer().getPlayer(input));
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   protected abstract Prompt acceptValidatedInput(@NotNull ConversationContext paramConversationContext, @NotNull Player paramPlayer);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\conversations\PlayerNamePrompt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */