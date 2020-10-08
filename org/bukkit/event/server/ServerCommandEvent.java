/*     */ package org.bukkit.event.server;
/*     */ 
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ServerCommandEvent
/*     */   extends ServerEvent
/*     */   implements Cancellable
/*     */ {
/*  43 */   private static final HandlerList handlers = new HandlerList();
/*     */   private String command;
/*     */   private final CommandSender sender;
/*     */   private boolean cancel = false;
/*     */   
/*     */   public ServerCommandEvent(@NotNull CommandSender sender, @NotNull String command) {
/*  49 */     this.command = command;
/*  50 */     this.sender = sender;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getCommand() {
/*  61 */     return this.command;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCommand(@NotNull String message) {
/*  70 */     this.command = message;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public CommandSender getSender() {
/*  80 */     return this.sender;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/*  86 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/*  91 */     return handlers;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/*  96 */     return this.cancel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCancelled(boolean cancel) {
/* 101 */     this.cancel = cancel;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\server\ServerCommandEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */