/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.Warning;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ @Warning(reason = "This event is no longer fired due to client changes")
/*    */ public class PlayerChatTabCompleteEvent
/*    */   extends PlayerEvent
/*    */ {
/* 18 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final String message;
/*    */   private final String lastToken;
/*    */   private final Collection<String> completions;
/*    */   
/*    */   public PlayerChatTabCompleteEvent(@NotNull Player who, @NotNull String message, @NotNull Collection<String> completions) {
/* 24 */     super(who);
/* 25 */     Validate.notNull(message, "Message cannot be null");
/* 26 */     Validate.notNull(completions, "Completions cannot be null");
/* 27 */     this.message = message;
/* 28 */     int i = message.lastIndexOf(' ');
/* 29 */     if (i < 0) {
/* 30 */       this.lastToken = message;
/*    */     } else {
/* 32 */       this.lastToken = message.substring(i + 1);
/*    */     } 
/* 34 */     this.completions = completions;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public String getChatMessage() {
/* 44 */     return this.message;
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
/*    */   @NotNull
/*    */   public String getLastToken() {
/* 57 */     return this.lastToken;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Collection<String> getTabCompletions() {
/* 67 */     return this.completions;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 73 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 78 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerChatTabCompleteEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */