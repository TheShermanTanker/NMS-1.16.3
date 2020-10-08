/*     */ package org.bukkit.command;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public final class PluginCommand
/*     */   extends Command
/*     */   implements PluginIdentifiableCommand
/*     */ {
/*     */   private final Plugin owningPlugin;
/*     */   private CommandExecutor executor;
/*     */   private TabCompleter completer;
/*     */   
/*     */   protected PluginCommand(@NotNull String name, @NotNull Plugin owner) {
/*  18 */     super(name);
/*  19 */     this.executor = (CommandExecutor)owner;
/*  20 */     this.owningPlugin = owner;
/*  21 */     this.usageMessage = "";
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
/*     */   public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
/*  34 */     boolean success = false;
/*     */     
/*  36 */     if (!this.owningPlugin.isEnabled()) {
/*  37 */       throw new CommandException("Cannot execute command '" + commandLabel + "' in plugin " + this.owningPlugin.getDescription().getFullName() + " - plugin is disabled.");
/*     */     }
/*     */     
/*  40 */     if (!testPermission(sender)) {
/*  41 */       return true;
/*     */     }
/*     */     
/*     */     try {
/*  45 */       success = this.executor.onCommand(sender, this, commandLabel, args);
/*  46 */     } catch (Throwable ex) {
/*  47 */       throw new CommandException("Unhandled exception executing command '" + commandLabel + "' in plugin " + this.owningPlugin.getDescription().getFullName(), ex);
/*     */     } 
/*     */     
/*  50 */     if (!success && this.usageMessage.length() > 0) {
/*  51 */       for (String line : this.usageMessage.replace("<command>", commandLabel).split("\n")) {
/*  52 */         sender.sendMessage(line);
/*     */       }
/*     */     }
/*     */     
/*  56 */     return success;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExecutor(@Nullable CommandExecutor executor) {
/*  65 */     this.executor = (executor == null) ? (CommandExecutor)this.owningPlugin : executor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public CommandExecutor getExecutor() {
/*  75 */     return this.executor;
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
/*     */   public void setTabCompleter(@Nullable TabCompleter completer) {
/*  87 */     this.completer = completer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public TabCompleter getTabCompleter() {
/*  97 */     return this.completer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Plugin getPlugin() {
/* 108 */     return this.owningPlugin;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws CommandException, IllegalArgumentException {
/* 131 */     Validate.notNull(sender, "Sender cannot be null");
/* 132 */     Validate.notNull(args, "Arguments cannot be null");
/* 133 */     Validate.notNull(alias, "Alias cannot be null");
/*     */     
/* 135 */     List<String> completions = null;
/*     */     try {
/* 137 */       if (this.completer != null) {
/* 138 */         completions = this.completer.onTabComplete(sender, this, alias, args);
/*     */       }
/* 140 */       if (completions == null && this.executor instanceof TabCompleter) {
/* 141 */         completions = ((TabCompleter)this.executor).onTabComplete(sender, this, alias, args);
/*     */       }
/* 143 */     } catch (Throwable ex) {
/* 144 */       StringBuilder message = new StringBuilder();
/* 145 */       message.append("Unhandled exception during tab completion for command '/").append(alias).append(' ');
/* 146 */       for (String arg : args) {
/* 147 */         message.append(arg).append(' ');
/*     */       }
/* 149 */       message.deleteCharAt(message.length() - 1).append("' in plugin ").append(this.owningPlugin.getDescription().getFullName());
/* 150 */       throw new CommandException(message.toString(), ex);
/*     */     } 
/*     */     
/* 153 */     if (completions == null) {
/* 154 */       return super.tabComplete(sender, alias, args);
/*     */     }
/* 156 */     return completions;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 161 */     StringBuilder stringBuilder = new StringBuilder(super.toString());
/* 162 */     stringBuilder.deleteCharAt(stringBuilder.length() - 1);
/* 163 */     stringBuilder.append(", ").append(this.owningPlugin.getDescription().getFullName()).append(')');
/* 164 */     return stringBuilder.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\command\PluginCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */