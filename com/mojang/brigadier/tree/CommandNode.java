/*     */ package com.mojang.brigadier.tree;
/*     */ 
/*     */ import com.google.common.collect.ComparisonChain;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.mojang.brigadier.AmbiguityConsumer;
/*     */ import com.mojang.brigadier.Command;
/*     */ import com.mojang.brigadier.RedirectModifier;
/*     */ import com.mojang.brigadier.StringReader;
/*     */ import com.mojang.brigadier.builder.ArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.context.CommandContextBuilder;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.suggestion.Suggestions;
/*     */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.server.v1_16_R2.CommandListenerWrapper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class CommandNode<S>
/*     */   implements Comparable<CommandNode<S>>
/*     */ {
/*  30 */   private Map<String, CommandNode<S>> children = Maps.newTreeMap();
/*  31 */   private Map<String, LiteralCommandNode<S>> literals = Maps.newLinkedHashMap();
/*  32 */   private Map<String, ArgumentCommandNode<S, ?>> arguments = Maps.newLinkedHashMap();
/*     */   private final Predicate<S> requirement;
/*     */   private final CommandNode<S> redirect;
/*     */   private final RedirectModifier<S> modifier;
/*     */   private final boolean forks;
/*     */   private Command<S> command;
/*     */   
/*     */   public void removeCommand(String name) {
/*  40 */     this.children.remove(name);
/*  41 */     this.literals.remove(name);
/*  42 */     this.arguments.remove(name);
/*     */   }
/*     */ 
/*     */   
/*     */   protected CommandNode(Command<S> command, Predicate<S> requirement, CommandNode<S> redirect, RedirectModifier<S> modifier, boolean forks) {
/*  47 */     this.command = command;
/*  48 */     this.requirement = requirement;
/*  49 */     this.redirect = redirect;
/*  50 */     this.modifier = modifier;
/*  51 */     this.forks = forks;
/*     */   }
/*     */   
/*     */   public Command<S> getCommand() {
/*  55 */     return this.command;
/*     */   }
/*     */   
/*     */   public Collection<CommandNode<S>> getChildren() {
/*  59 */     return this.children.values();
/*     */   }
/*     */   
/*     */   public CommandNode<S> getChild(String name) {
/*  63 */     return this.children.get(name);
/*     */   }
/*     */   
/*     */   public CommandNode<S> getRedirect() {
/*  67 */     return this.redirect;
/*     */   }
/*     */   
/*     */   public RedirectModifier<S> getRedirectModifier() {
/*  71 */     return this.modifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized boolean canUse(S source) {
/*  76 */     if (source instanceof CommandListenerWrapper) {
/*     */       try {
/*  78 */         ((CommandListenerWrapper)source).currentCommand.set(this);
/*  79 */         return this.requirement.test(source);
/*     */       } finally {
/*  81 */         ((CommandListenerWrapper)source).currentCommand.set(null);
/*     */       } 
/*     */     }
/*     */     
/*  85 */     return this.requirement.test(source);
/*     */   }
/*     */   
/*     */   public void addChild(CommandNode<S> node) {
/*  89 */     if (node instanceof RootCommandNode) {
/*  90 */       throw new UnsupportedOperationException("Cannot add a RootCommandNode as a child to any other CommandNode");
/*     */     }
/*     */     
/*  93 */     CommandNode<S> child = this.children.get(node.getName());
/*  94 */     if (child != null) {
/*     */       
/*  96 */       if (node.getCommand() != null) {
/*  97 */         child.command = node.getCommand();
/*     */       }
/*  99 */       for (CommandNode<S> grandchild : node.getChildren()) {
/* 100 */         child.addChild(grandchild);
/*     */       }
/*     */     } else {
/* 103 */       this.children.put(node.getName(), node);
/* 104 */       if (node instanceof LiteralCommandNode) {
/* 105 */         this.literals.put(node.getName(), (LiteralCommandNode<S>)node);
/* 106 */       } else if (node instanceof ArgumentCommandNode) {
/* 107 */         this.arguments.put(node.getName(), (ArgumentCommandNode)node);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void findAmbiguities(AmbiguityConsumer<S> consumer) {
/* 114 */     Set<String> matches = Sets.newHashSet();
/*     */     
/* 116 */     for (CommandNode<S> child : this.children.values()) {
/* 117 */       for (CommandNode<S> sibling : this.children.values()) {
/* 118 */         if (child == sibling) {
/*     */           continue;
/*     */         }
/*     */         
/* 122 */         for (String input : child.getExamples()) {
/* 123 */           if (sibling.isValidInput(input)) {
/* 124 */             matches.add(input);
/*     */           }
/*     */         } 
/*     */         
/* 128 */         if (matches.size() > 0) {
/* 129 */           consumer.ambiguous(this, child, sibling, matches);
/* 130 */           matches = Sets.newHashSet();
/*     */         } 
/*     */       } 
/*     */       
/* 134 */       child.findAmbiguities(consumer);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 142 */     if (this == o) return true; 
/* 143 */     if (!(o instanceof CommandNode)) return false;
/*     */     
/* 145 */     CommandNode<S> that = (CommandNode<S>)o;
/*     */     
/* 147 */     if (!this.children.equals(that.children)) return false; 
/* 148 */     if ((this.command != null) ? !this.command.equals(that.command) : (that.command != null)) return false;
/*     */     
/* 150 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 155 */     return 31 * this.children.hashCode() + ((this.command != null) ? this.command.hashCode() : 0);
/*     */   }
/*     */   
/*     */   public Predicate<S> getRequirement() {
/* 159 */     return this.requirement;
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
/*     */ 
/*     */   
/*     */   public Collection<? extends CommandNode<S>> getRelevantNodes(StringReader input) {
/* 175 */     if (this.literals.size() > 0) {
/* 176 */       int cursor = input.getCursor();
/* 177 */       while (input.canRead() && input.peek() != ' ') {
/* 178 */         input.skip();
/*     */       }
/* 180 */       String text = input.getString().substring(cursor, input.getCursor());
/* 181 */       input.setCursor(cursor);
/* 182 */       LiteralCommandNode<S> literal = this.literals.get(text);
/* 183 */       if (literal != null) {
/* 184 */         return Collections.singleton(literal);
/*     */       }
/* 186 */       return this.arguments.values();
/*     */     } 
/*     */     
/* 189 */     return this.arguments.values();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareTo(CommandNode<S> o) {
/* 195 */     return 
/* 196 */       ComparisonChain.start()
/* 197 */       .compareTrueFirst(this instanceof LiteralCommandNode, o instanceof LiteralCommandNode)
/* 198 */       .compare(getSortedKey(), o.getSortedKey())
/* 199 */       .result();
/*     */   }
/*     */   
/*     */   public boolean isFork() {
/* 203 */     return this.forks;
/*     */   }
/*     */   
/*     */   protected abstract boolean isValidInput(String paramString);
/*     */   
/*     */   public abstract String getName();
/*     */   
/*     */   public abstract String getUsageText();
/*     */   
/*     */   public abstract void parse(StringReader paramStringReader, CommandContextBuilder<S> paramCommandContextBuilder) throws CommandSyntaxException;
/*     */   
/*     */   public abstract CompletableFuture<Suggestions> listSuggestions(CommandContext<S> paramCommandContext, SuggestionsBuilder paramSuggestionsBuilder) throws CommandSyntaxException;
/*     */   
/*     */   public abstract ArgumentBuilder<S, ?> createBuilder();
/*     */   
/*     */   protected abstract String getSortedKey();
/*     */   
/*     */   public abstract Collection<String> getExamples();
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mojang\brigadier\tree\CommandNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */