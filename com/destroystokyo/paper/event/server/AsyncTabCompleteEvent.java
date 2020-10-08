/*     */ package com.destroystokyo.paper.event.server;
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
/*     */ public class AsyncTabCompleteEvent
/*     */   extends Event
/*     */   implements Cancellable
/*     */ {
/*     */   @NotNull
/*     */   private final CommandSender sender;
/*     */   @NotNull
/*     */   private final String buffer;
/*     */   private final boolean isCommand;
/*     */   @Nullable
/*     */   private final Location loc;
/*     */   @NotNull
/*     */   private List<String> completions;
/*     */   private boolean cancelled;
/*     */   private boolean handled = false;
/*     */   private boolean fireSyncHandler = true;
/*     */   
/*     */   public AsyncTabCompleteEvent(@NotNull CommandSender sender, @NotNull List<String> completions, @NotNull String buffer, boolean isCommand, @Nullable Location loc) {
/*  57 */     super(true);
/*  58 */     this.sender = sender;
/*  59 */     this.completions = completions;
/*  60 */     this.buffer = buffer;
/*  61 */     this.isCommand = isCommand;
/*  62 */     this.loc = loc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public CommandSender getSender() {
/*  72 */     return this.sender;
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
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<String> getCompletions() {
/*  87 */     return this.completions;
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
/*     */ 
/*     */   
/*     */   public void setCompletions(@NotNull List<String> completions) {
/* 101 */     Validate.notNull(completions);
/* 102 */     this.completions = new ArrayList<>(completions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getBuffer() {
/* 112 */     return this.buffer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCommand() {
/* 119 */     return this.isCommand;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Location getLocation() {
/* 127 */     return this.loc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isHandled() {
/* 137 */     return (!this.completions.isEmpty() || this.handled);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHandled(boolean handled) {
/* 148 */     this.handled = handled;
/*     */   }
/*     */   
/* 151 */   private static final HandlerList handlers = new HandlerList();
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/* 156 */     return this.cancelled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCancelled(boolean cancelled) {
/* 165 */     this.cancelled = cancelled;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/* 170 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 175 */     return handlers;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\server\AsyncTabCompleteEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */