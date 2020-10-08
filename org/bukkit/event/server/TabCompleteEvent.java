/*     */ package org.bukkit.event.server;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TabCompleteEvent
/*     */   extends Event
/*     */   implements Cancellable
/*     */ {
/*  26 */   private static final HandlerList handlers = new HandlerList();
/*     */   private final CommandSender sender;
/*     */   private final String buffer;
/*     */   private List<String> completions;
/*     */   private boolean cancelled;
/*     */   private final boolean isCommand;
/*     */   private final Location loc;
/*     */   
/*     */   public TabCompleteEvent(@NotNull CommandSender sender, @NotNull String buffer, @NotNull List<String> completions) {
/*  35 */     this(sender, buffer, completions, (sender instanceof org.bukkit.command.ConsoleCommandSender || buffer.startsWith("/")), null);
/*     */   }
/*     */   public TabCompleteEvent(@NotNull CommandSender sender, @NotNull String buffer, @NotNull List<String> completions, boolean isCommand, @Nullable Location location) {
/*  38 */     this.isCommand = isCommand;
/*  39 */     this.loc = location;
/*     */     
/*  41 */     Validate.notNull(sender, "sender");
/*  42 */     Validate.notNull(buffer, "buffer");
/*  43 */     Validate.notNull(completions, "completions");
/*     */     
/*  45 */     this.sender = sender;
/*  46 */     this.buffer = buffer;
/*  47 */     this.completions = completions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public CommandSender getSender() {
/*  57 */     return this.sender;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getBuffer() {
/*  67 */     return this.buffer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<String> getCompletions() {
/*  78 */     return this.completions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCommand() {
/*  88 */     return this.isCommand;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Location getLocation() {
/*  96 */     return this.loc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCompletions(@NotNull List<String> completions) {
/* 108 */     Validate.notNull(completions);
/* 109 */     this.completions = new ArrayList<>(completions);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/* 114 */     return this.cancelled;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCancelled(boolean cancelled) {
/* 119 */     this.cancelled = cancelled;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/* 125 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 130 */     return handlers;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\server\TabCompleteEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */