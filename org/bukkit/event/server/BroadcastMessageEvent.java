/*    */ package org.bukkit.event.server;
/*    */ 
/*    */ import java.util.Set;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BroadcastMessageEvent
/*    */   extends ServerEvent
/*    */   implements Cancellable
/*    */ {
/* 20 */   private static final HandlerList handlers = new HandlerList();
/*    */   private String message;
/*    */   private final Set<CommandSender> recipients;
/*    */   private boolean cancelled = false;
/*    */   
/*    */   @Deprecated
/*    */   public BroadcastMessageEvent(@NotNull String message, @NotNull Set<CommandSender> recipients) {
/* 27 */     this(false, message, recipients);
/*    */   }
/*    */   
/*    */   public BroadcastMessageEvent(boolean isAsync, @NotNull String message, @NotNull Set<CommandSender> recipients) {
/* 31 */     super(isAsync);
/* 32 */     this.message = message;
/* 33 */     this.recipients = recipients;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public String getMessage() {
/* 43 */     return this.message;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setMessage(@NotNull String message) {
/* 52 */     this.message = message;
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
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Set<CommandSender> getRecipients() {
/* 70 */     return this.recipients;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 75 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancelled) {
/* 80 */     this.cancelled = cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 86 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 91 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\server\BroadcastMessageEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */