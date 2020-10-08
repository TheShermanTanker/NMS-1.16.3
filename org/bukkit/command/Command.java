/*     */ package org.bukkit.command;
/*     */ 
/*     */ import co.aikar.timings.Timing;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.GameRule;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.minecart.CommandMinecart;
/*     */ import org.bukkit.permissions.Permissible;
/*     */ import org.bukkit.util.StringUtil;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public abstract class Command
/*     */ {
/*     */   private String name;
/*     */   private String nextLabel;
/*     */   private String label;
/*     */   private List<String> aliases;
/*     */   private List<String> activeAliases;
/*     */   private CommandMap commandMap;
/*     */   protected String description;
/*     */   protected String usageMessage;
/*     */   private String permission;
/*     */   private String permissionMessage;
/*     */   public Timing timings;
/*     */   
/*     */   @NotNull
/*     */   public String getTimingName() {
/*  37 */     return getName();
/*     */   }
/*     */   protected Command(@NotNull String name) {
/*  40 */     this(name, "", "/" + name, new ArrayList<>());
/*     */   }
/*     */   
/*     */   protected Command(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
/*  44 */     this.name = name;
/*  45 */     this.nextLabel = name;
/*  46 */     this.label = name;
/*  47 */     this.description = (description == null) ? "" : description;
/*  48 */     this.usageMessage = (usageMessage == null) ? ("/" + name) : usageMessage;
/*  49 */     this.aliases = aliases;
/*  50 */     this.activeAliases = new ArrayList<>(aliases);
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
/*     */   public abstract boolean execute(@NotNull CommandSender paramCommandSender, @NotNull String paramString, @NotNull String[] paramArrayOfString);
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
/*     */   public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
/*  76 */     return tabComplete0(sender, alias, args, null);
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
/*     */   @NotNull
/*     */   public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args, @Nullable Location location) throws IllegalArgumentException {
/*  93 */     return tabComplete(sender, alias, args);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   private List<String> tabComplete0(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args, @Nullable Location location) throws IllegalArgumentException {
/*  98 */     Validate.notNull(sender, "Sender cannot be null");
/*  99 */     Validate.notNull(args, "Arguments cannot be null");
/* 100 */     Validate.notNull(alias, "Alias cannot be null");
/*     */     
/* 102 */     if (args.length == 0 || !sender.getServer().suggestPlayerNamesWhenNullTabCompletions()) {
/* 103 */       return (List<String>)ImmutableList.of();
/*     */     }
/*     */     
/* 106 */     String lastWord = args[args.length - 1];
/*     */     
/* 108 */     Player senderPlayer = (sender instanceof Player) ? (Player)sender : null;
/*     */     
/* 110 */     ArrayList<String> matchedPlayers = new ArrayList<>();
/* 111 */     for (Player player : sender.getServer().getOnlinePlayers()) {
/* 112 */       String name = player.getName();
/* 113 */       if ((senderPlayer == null || senderPlayer.canSee(player)) && StringUtil.startsWithIgnoreCase(name, lastWord)) {
/* 114 */         matchedPlayers.add(name);
/*     */       }
/*     */     } 
/*     */     
/* 118 */     Collections.sort(matchedPlayers, String.CASE_INSENSITIVE_ORDER);
/* 119 */     return matchedPlayers;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getName() {
/* 129 */     return this.name;
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
/*     */   public boolean setName(@NotNull String name) {
/* 144 */     if (!isRegistered()) {
/* 145 */       this.name = (name == null) ? "" : name;
/* 146 */       return true;
/*     */     } 
/* 148 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public String getPermission() {
/* 159 */     return this.permission;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPermission(@Nullable String permission) {
/* 169 */     this.permission = permission;
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
/*     */   public boolean testPermission(@NotNull CommandSender target) {
/* 183 */     if (testPermissionSilent(target)) {
/* 184 */       return true;
/*     */     }
/*     */     
/* 187 */     if (this.permissionMessage == null) {
/* 188 */       target.sendMessage(Bukkit.getPermissionMessage());
/* 189 */     } else if (this.permissionMessage.length() != 0) {
/* 190 */       for (String line : this.permissionMessage.replace("<permission>", this.permission).split("\n")) {
/* 191 */         target.sendMessage(line);
/*     */       }
/*     */     } 
/*     */     
/* 195 */     return false;
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
/*     */   public boolean testPermissionSilent(@NotNull CommandSender target) {
/* 208 */     if (this.permission == null || this.permission.length() == 0) {
/* 209 */       return true;
/*     */     }
/*     */     
/* 212 */     for (String p : this.permission.split(";")) {
/* 213 */       if (target.hasPermission(p)) {
/* 214 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 218 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getLabel() {
/* 228 */     return this.label;
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
/*     */   public boolean setLabel(@NotNull String name) {
/* 243 */     if (name == null) {
/* 244 */       name = "";
/*     */     }
/* 246 */     this.nextLabel = name;
/* 247 */     if (!isRegistered()) {
/* 248 */       this.label = name;
/* 249 */       return true;
/*     */     } 
/* 251 */     return false;
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
/*     */   public boolean register(@NotNull CommandMap commandMap) {
/* 263 */     if (allowChangesFrom(commandMap)) {
/* 264 */       this.commandMap = commandMap;
/* 265 */       return true;
/*     */     } 
/*     */     
/* 268 */     return false;
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
/*     */   public boolean unregister(@NotNull CommandMap commandMap) {
/* 281 */     if (allowChangesFrom(commandMap)) {
/* 282 */       this.commandMap = null;
/* 283 */       this.activeAliases = new ArrayList<>(this.aliases);
/* 284 */       this.label = this.nextLabel;
/* 285 */       return true;
/*     */     } 
/*     */     
/* 288 */     return false;
/*     */   }
/*     */   
/*     */   private boolean allowChangesFrom(@NotNull CommandMap commandMap) {
/* 292 */     return (null == this.commandMap || this.commandMap == commandMap);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRegistered() {
/* 301 */     return (null != this.commandMap);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<String> getAliases() {
/* 311 */     return this.activeAliases;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public String getPermissionMessage() {
/* 322 */     return this.permissionMessage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getDescription() {
/* 332 */     return this.description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getUsage() {
/* 342 */     return this.usageMessage;
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
/*     */   @NotNull
/*     */   public Command setAliases(@NotNull List<String> aliases) {
/* 356 */     this.aliases = aliases;
/* 357 */     if (!isRegistered()) {
/* 358 */       this.activeAliases = new ArrayList<>(aliases);
/*     */     }
/* 360 */     return this;
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
/*     */   @NotNull
/*     */   public Command setDescription(@NotNull String description) {
/* 373 */     this.description = (description == null) ? "" : description;
/* 374 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Command setPermissionMessage(@Nullable String permissionMessage) {
/* 386 */     this.permissionMessage = permissionMessage;
/* 387 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Command setUsage(@NotNull String usage) {
/* 398 */     this.usageMessage = (usage == null) ? "" : usage;
/* 399 */     return this;
/*     */   }
/*     */   
/*     */   public static void broadcastCommandMessage(@NotNull CommandSender source, @NotNull String message) {
/* 403 */     broadcastCommandMessage(source, message, true);
/*     */   }
/*     */   
/*     */   public static void broadcastCommandMessage(@NotNull CommandSender source, @NotNull String message, boolean sendToSource) {
/* 407 */     String result = source.getName() + ": " + message;
/*     */     
/* 409 */     if (source instanceof BlockCommandSender) {
/* 410 */       BlockCommandSender blockCommandSender = (BlockCommandSender)source;
/*     */       
/* 412 */       if (!((Boolean)blockCommandSender.getBlock().getWorld().getGameRuleValue(GameRule.COMMAND_BLOCK_OUTPUT)).booleanValue()) {
/* 413 */         Bukkit.getConsoleSender().sendMessage(result);
/*     */         return;
/*     */       } 
/* 416 */     } else if (source instanceof CommandMinecart) {
/* 417 */       CommandMinecart commandMinecart = (CommandMinecart)source;
/*     */       
/* 419 */       if (!((Boolean)commandMinecart.getWorld().getGameRuleValue(GameRule.COMMAND_BLOCK_OUTPUT)).booleanValue()) {
/* 420 */         Bukkit.getConsoleSender().sendMessage(result);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 425 */     Set<Permissible> users = Bukkit.getPluginManager().getPermissionSubscriptions("bukkit.broadcast.admin");
/* 426 */     String colored = ChatColor.GRAY + "" + ChatColor.ITALIC + "[" + result + ChatColor.GRAY + ChatColor.ITALIC + "]";
/*     */     
/* 428 */     if (sendToSource && !(source instanceof ConsoleCommandSender)) {
/* 429 */       source.sendMessage(message);
/*     */     }
/*     */     
/* 432 */     for (Permissible user : users) {
/* 433 */       if (user instanceof CommandSender && user.hasPermission("bukkit.broadcast.admin")) {
/* 434 */         CommandSender target = (CommandSender)user;
/*     */         
/* 436 */         if (target instanceof ConsoleCommandSender) {
/* 437 */           target.sendMessage(result); continue;
/* 438 */         }  if (target != source) {
/* 439 */           target.sendMessage(colored);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 447 */     return getClass().getName() + '(' + this.name + ')';
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\command\Command.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */