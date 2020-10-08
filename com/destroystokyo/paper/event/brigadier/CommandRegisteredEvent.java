/*     */ package com.destroystokyo.paper.event.brigadier;
/*     */ 
/*     */ import com.destroystokyo.paper.brigadier.BukkitBrigadierCommand;
/*     */ import com.destroystokyo.paper.brigadier.BukkitBrigadierCommandSource;
/*     */ import com.mojang.brigadier.tree.ArgumentCommandNode;
/*     */ import com.mojang.brigadier.tree.LiteralCommandNode;
/*     */ import com.mojang.brigadier.tree.RootCommandNode;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.bukkit.event.server.ServerEvent;
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
/*     */ public class CommandRegisteredEvent<S extends BukkitBrigadierCommandSource>
/*     */   extends ServerEvent
/*     */   implements Cancellable
/*     */ {
/*  26 */   private static final HandlerList handlers = new HandlerList();
/*     */   private final String commandLabel;
/*     */   private final Command command;
/*     */   private final BukkitBrigadierCommand<S> brigadierCommand;
/*     */   private final RootCommandNode<S> root;
/*     */   private final ArgumentCommandNode<S, String> defaultArgs;
/*     */   private LiteralCommandNode<S> literal;
/*     */   private boolean cancelled = false;
/*     */   
/*     */   public CommandRegisteredEvent(String commandLabel, BukkitBrigadierCommand<S> brigadierCommand, Command command, RootCommandNode<S> root, LiteralCommandNode<S> literal, ArgumentCommandNode<S, String> defaultArgs) {
/*  36 */     this.commandLabel = commandLabel;
/*  37 */     this.brigadierCommand = brigadierCommand;
/*  38 */     this.command = command;
/*  39 */     this.root = root;
/*  40 */     this.literal = literal;
/*  41 */     this.defaultArgs = defaultArgs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCommandLabel() {
/*  48 */     return this.commandLabel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BukkitBrigadierCommand<S> getBrigadierCommand() {
/*  55 */     return this.brigadierCommand;
/*     */   }
/*     */   
/*     */   public Command getCommand() {
/*  59 */     return this.command;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RootCommandNode<S> getRoot() {
/*  66 */     return this.root;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArgumentCommandNode<S, String> getDefaultArgs() {
/*  74 */     return this.defaultArgs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LiteralCommandNode<S> getLiteral() {
/*  82 */     return this.literal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLiteral(LiteralCommandNode<S> literal) {
/*  91 */     this.literal = literal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/*  99 */     return this.cancelled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCancelled(boolean cancel) {
/* 110 */     this.cancelled = cancel;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/* 115 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 120 */     return handlers;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\brigadier\CommandRegisteredEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */