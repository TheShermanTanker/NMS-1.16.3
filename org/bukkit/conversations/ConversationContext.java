/*    */ package org.bukkit.conversations;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.bukkit.plugin.Plugin;
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
/*    */ public class ConversationContext
/*    */ {
/*    */   private final Conversable forWhom;
/*    */   private final Map<Object, Object> sessionData;
/*    */   private final Plugin plugin;
/*    */   
/*    */   public ConversationContext(@Nullable Plugin plugin, @NotNull Conversable forWhom, @NotNull Map<Object, Object> initialSessionData) {
/* 26 */     this.plugin = plugin;
/* 27 */     this.forWhom = forWhom;
/* 28 */     this.sessionData = initialSessionData;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Plugin getPlugin() {
/* 38 */     return this.plugin;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Conversable getForWhom() {
/* 48 */     return this.forWhom;
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
/*    */   public Map<Object, Object> getAllSessionData() {
/* 60 */     return this.sessionData;
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
/*    */   public Object getSessionData(@NotNull Object key) {
/* 73 */     return this.sessionData.get(key);
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
/*    */   public void setSessionData(@NotNull Object key, @Nullable Object value) {
/* 85 */     this.sessionData.put(key, value);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\conversations\ConversationContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */