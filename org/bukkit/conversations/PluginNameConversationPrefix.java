/*    */ package org.bukkit.conversations;
/*    */ 
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PluginNameConversationPrefix
/*    */   implements ConversationPrefix
/*    */ {
/*    */   protected String separator;
/*    */   protected ChatColor prefixColor;
/*    */   protected Plugin plugin;
/*    */   private String cachedPrefix;
/*    */   
/*    */   public PluginNameConversationPrefix(@NotNull Plugin plugin) {
/* 20 */     this(plugin, " > ", ChatColor.LIGHT_PURPLE);
/*    */   }
/*    */   
/*    */   public PluginNameConversationPrefix(@NotNull Plugin plugin, @NotNull String separator, @NotNull ChatColor prefixColor) {
/* 24 */     this.separator = separator;
/* 25 */     this.prefixColor = prefixColor;
/* 26 */     this.plugin = plugin;
/*    */     
/* 28 */     this.cachedPrefix = prefixColor + plugin.getDescription().getName() + separator + ChatColor.WHITE;
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
/*    */   public String getPrefix(@NotNull ConversationContext context) {
/* 40 */     return this.cachedPrefix;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\conversations\PluginNameConversationPrefix.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */