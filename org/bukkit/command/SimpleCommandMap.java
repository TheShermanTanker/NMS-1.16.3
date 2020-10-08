/*     */ package org.bukkit.command;
/*     */ import co.aikar.timings.Timing;
/*     */ import co.aikar.timings.TimingsManager;
/*     */ import com.destroystokyo.paper.event.server.ServerExceptionEvent;
/*     */ import com.destroystokyo.paper.exception.ServerCommandException;
/*     */ import com.destroystokyo.paper.exception.ServerException;
/*     */ import com.destroystokyo.paper.exception.ServerTabCompleteException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.command.defaults.PluginsCommand;
/*     */ import org.bukkit.command.defaults.ReloadCommand;
/*     */ import org.bukkit.command.defaults.VersionCommand;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.util.StringUtil;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class SimpleCommandMap implements CommandMap {
/*  29 */   protected final Map<String, Command> knownCommands = new HashMap<>();
/*     */   private final Server server;
/*     */   
/*     */   public SimpleCommandMap(@NotNull Server server) {
/*  33 */     this.server = server;
/*  34 */     setDefaultCommands();
/*     */   }
/*     */   
/*     */   private void setDefaultCommands() {
/*  38 */     register("bukkit", (Command)new VersionCommand("version"));
/*  39 */     register("bukkit", (Command)new ReloadCommand("reload"));
/*  40 */     register("bukkit", (Command)new PluginsCommand("plugins"));
/*  41 */     register("bukkit", (Command)new TimingsCommand("timings"));
/*     */   }
/*     */   
/*     */   public void setFallbackCommands() {
/*  45 */     register("bukkit", (Command)new HelpCommand());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerAll(@NotNull String fallbackPrefix, @NotNull List<Command> commands) {
/*  53 */     if (commands != null) {
/*  54 */       for (Command c : commands) {
/*  55 */         register(fallbackPrefix, c);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean register(@NotNull String fallbackPrefix, @NotNull Command command) {
/*  65 */     return register(command.getName(), fallbackPrefix, command);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean register(@NotNull String label, @NotNull String fallbackPrefix, @NotNull Command command) {
/*  73 */     command.timings = TimingsManager.getCommandTiming(fallbackPrefix, command);
/*  74 */     label = label.toLowerCase(Locale.ENGLISH).trim();
/*  75 */     fallbackPrefix = fallbackPrefix.toLowerCase(Locale.ENGLISH).trim();
/*  76 */     boolean registered = register(label, command, false, fallbackPrefix);
/*     */     
/*  78 */     Iterator<String> iterator = command.getAliases().iterator();
/*  79 */     while (iterator.hasNext()) {
/*  80 */       if (!register(iterator.next(), command, true, fallbackPrefix)) {
/*  81 */         iterator.remove();
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  86 */     if (!registered) {
/*  87 */       command.setLabel(fallbackPrefix + ":" + label);
/*     */     }
/*     */ 
/*     */     
/*  91 */     command.register(this);
/*     */     
/*  93 */     return registered;
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
/*     */   private synchronized boolean register(@NotNull String label, @NotNull Command command, boolean isAlias, @NotNull String fallbackPrefix) {
/* 108 */     this.knownCommands.put(fallbackPrefix + ":" + label, command);
/* 109 */     if ((command instanceof org.bukkit.command.defaults.BukkitCommand || isAlias) && this.knownCommands.containsKey(label))
/*     */     {
/*     */ 
/*     */       
/* 113 */       return false;
/*     */     }
/*     */     
/* 116 */     boolean registered = true;
/*     */ 
/*     */     
/* 119 */     Command conflict = this.knownCommands.get(label);
/* 120 */     if (conflict != null && conflict.getLabel().equals(label)) {
/* 121 */       return false;
/*     */     }
/*     */     
/* 124 */     if (!isAlias) {
/* 125 */       command.setLabel(label);
/*     */     }
/* 127 */     this.knownCommands.put(label, command);
/*     */     
/* 129 */     return registered;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean dispatch(@NotNull CommandSender sender, @NotNull String commandLine) throws CommandException {
/* 137 */     String[] args = commandLine.split(" ");
/*     */     
/* 139 */     if (args.length == 0) {
/* 140 */       return false;
/*     */     }
/*     */     
/* 143 */     String sentCommandLabel = args[0].toLowerCase(Locale.ENGLISH);
/* 144 */     Command target = getCommand(sentCommandLabel);
/*     */     
/* 146 */     if (target == null) {
/* 147 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 151 */     if (target.timings == null) {
/* 152 */       target.timings = TimingsManager.getCommandTiming(null, target);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 157 */     try { Timing ignored = target.timings.startTiming();
/*     */       
/* 159 */       try { target.execute(sender, sentCommandLabel, Arrays.<String>copyOfRange(args, 1, args.length));
/* 160 */         if (ignored != null) ignored.close();  } catch (Throwable throwable) { if (ignored != null)
/* 161 */           try { ignored.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (CommandException ex)
/* 162 */     { this.server.getPluginManager().callEvent((Event)new ServerExceptionEvent((ServerException)new ServerCommandException(ex, target, sender, args)));
/*     */       
/* 164 */       throw ex; }
/* 165 */     catch (Throwable ex)
/*     */     
/* 167 */     { String msg = "Unhandled exception executing '" + commandLine + "' in " + target;
/* 168 */       this.server.getPluginManager().callEvent((Event)new ServerExceptionEvent((ServerException)new ServerCommandException(ex, target, sender, args)));
/* 169 */       throw new CommandException(msg, ex); }
/*     */ 
/*     */ 
/*     */     
/* 173 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void clearCommands() {
/* 178 */     for (Map.Entry<String, Command> entry : this.knownCommands.entrySet()) {
/* 179 */       ((Command)entry.getValue()).unregister(this);
/*     */     }
/* 181 */     this.knownCommands.clear();
/* 182 */     setDefaultCommands();
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Command getCommand(@NotNull String name) {
/* 188 */     Command target = this.knownCommands.get(name.toLowerCase(Locale.ENGLISH));
/* 189 */     return target;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String cmdLine) {
/* 195 */     return tabComplete(sender, cmdLine, null);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String cmdLine, @Nullable Location location) {
/* 201 */     Validate.notNull(sender, "Sender cannot be null");
/* 202 */     Validate.notNull(cmdLine, "Command line cannot null");
/*     */     
/* 204 */     int spaceIndex = cmdLine.indexOf(' ');
/*     */     
/* 206 */     if (spaceIndex == -1) {
/* 207 */       ArrayList<String> completions = new ArrayList<>();
/* 208 */       Map<String, Command> knownCommands = this.knownCommands;
/*     */       
/* 210 */       String prefix = (sender instanceof org.bukkit.entity.Player) ? "/" : "";
/*     */       
/* 212 */       for (Map.Entry<String, Command> commandEntry : knownCommands.entrySet()) {
/* 213 */         Command command = commandEntry.getValue();
/*     */         
/* 215 */         if (!command.testPermissionSilent(sender)) {
/*     */           continue;
/*     */         }
/*     */         
/* 219 */         String name = commandEntry.getKey();
/*     */         
/* 221 */         if (StringUtil.startsWithIgnoreCase(name, cmdLine)) {
/* 222 */           completions.add(prefix + name);
/*     */         }
/*     */       } 
/*     */       
/* 226 */       Collections.sort(completions, String.CASE_INSENSITIVE_ORDER);
/* 227 */       return completions;
/*     */     } 
/*     */     
/* 230 */     String commandName = cmdLine.substring(0, spaceIndex);
/* 231 */     Command target = getCommand(commandName);
/*     */     
/* 233 */     if (target == null) {
/* 234 */       return null;
/*     */     }
/*     */     
/* 237 */     if (!target.testPermissionSilent(sender)) {
/* 238 */       return null;
/*     */     }
/*     */     
/* 241 */     String[] args = cmdLine.substring(spaceIndex + 1, cmdLine.length()).split(" ", -1);
/*     */     
/*     */     try {
/* 244 */       return target.tabComplete(sender, commandName, args, location);
/* 245 */     } catch (CommandException ex) {
/* 246 */       throw ex;
/* 247 */     } catch (Throwable ex) {
/* 248 */       String msg = "Unhandled exception executing tab-completer for '" + cmdLine + "' in " + target;
/* 249 */       this.server.getPluginManager().callEvent((Event)new ServerExceptionEvent((ServerException)new ServerTabCompleteException(msg, ex, target, sender, args)));
/* 250 */       throw new CommandException(msg, ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Collection<Command> getCommands() {
/* 256 */     return Collections.unmodifiableCollection(this.knownCommands.values());
/*     */   }
/*     */   
/*     */   public void registerServerAliases() {
/* 260 */     Map<String, String[]> values = this.server.getCommandAliases();
/*     */     
/* 262 */     for (Map.Entry<String, String[]> entry : values.entrySet()) {
/* 263 */       String alias = entry.getKey();
/* 264 */       if (alias.contains(" ")) {
/* 265 */         this.server.getLogger().warning("Could not register alias " + alias + " because it contains illegal characters");
/*     */         
/*     */         continue;
/*     */       } 
/* 269 */       String[] commandStrings = entry.getValue();
/* 270 */       List<String> targets = new ArrayList<>();
/* 271 */       StringBuilder bad = new StringBuilder();
/*     */       
/* 273 */       for (String commandString : commandStrings) {
/* 274 */         String[] commandArgs = commandString.split(" ");
/* 275 */         Command command = getCommand(commandArgs[0]);
/*     */         
/* 277 */         if (command == null) {
/* 278 */           if (bad.length() > 0) {
/* 279 */             bad.append(", ");
/*     */           }
/* 281 */           bad.append(commandString);
/*     */         } else {
/* 283 */           targets.add(commandString);
/*     */         } 
/*     */       } 
/*     */       
/* 287 */       if (bad.length() > 0) {
/* 288 */         this.server.getLogger().warning("Could not register alias " + alias + " because it contains commands that do not exist: " + bad);
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 293 */       if (targets.size() > 0) {
/* 294 */         this.knownCommands.put(alias.toLowerCase(Locale.ENGLISH), new FormattedCommandAlias(alias.toLowerCase(Locale.ENGLISH), targets.<String>toArray(new String[targets.size()]))); continue;
/*     */       } 
/* 296 */       this.knownCommands.remove(alias.toLowerCase(Locale.ENGLISH));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Map<String, Command> getKnownCommands() {
/* 304 */     return this.knownCommands;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\command\SimpleCommandMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */