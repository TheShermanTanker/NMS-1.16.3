/*     */ package org.bukkit.command.defaults;
/*     */ import com.destroystokyo.paper.util.VersionFetcher;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.PluginDescriptionFile;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class VersionCommand extends BukkitCommand {
/*     */   private VersionFetcher versionFetcher;
/*     */   private final ReentrantLock versionLock;
/*     */   private boolean hasVersion;
/*     */   private String versionMessage;
/*     */   private final Set<CommandSender> versionWaiters;
/*     */   private boolean versionTaskStarted;
/*     */   private long lastCheck;
/*     */   
/*     */   private VersionFetcher getVersionFetcher() {
/*  32 */     if (this.versionFetcher == null) {
/*  33 */       this.versionFetcher = Bukkit.getUnsafe().getVersionFetcher();
/*     */     }
/*     */     
/*  36 */     return this.versionFetcher;
/*     */   }
/*     */   
/*     */   public VersionCommand(@NotNull String name) {
/*  40 */     super(name);
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
/* 157 */     this.versionLock = new ReentrantLock();
/* 158 */     this.hasVersion = false;
/* 159 */     this.versionMessage = null;
/* 160 */     this.versionWaiters = new HashSet<>();
/* 161 */     this.versionTaskStarted = false;
/* 162 */     this.lastCheck = 0L; this.description = "Gets the version of this server including any plugins in use"; this.usageMessage = "/version [plugin name]"; setPermission("bukkit.command.version"); setAliases(Arrays.asList(new String[] { "ver", "about" }));
/*     */   }
/*     */   public boolean execute(@NotNull CommandSender sender, @NotNull String currentAlias, @NotNull String[] args) { if (!testPermission(sender)) return true;  if (args.length == 0) { sender.sendMessage("This server is running " + Bukkit.getName() + " version " + Bukkit.getVersion() + " (Implementing API version " + Bukkit.getBukkitVersion() + ")"); sendVersion(sender); } else { StringBuilder name = new StringBuilder(); for (String arg : args) { if (name.length() > 0) name.append(' ');  name.append(arg); }  String pluginName = name.toString(); Plugin exactPlugin = Bukkit.getPluginManager().getPlugin(pluginName); if (exactPlugin != null) { describeToSender(exactPlugin, sender); return true; }  boolean found = false; pluginName = pluginName.toLowerCase(Locale.ENGLISH); for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) { if (plugin.getName().toLowerCase(Locale.ENGLISH).contains(pluginName)) { describeToSender(plugin, sender); found = true; }  }  if (!found) { sender.sendMessage("This server is not running any plugin by that name."); sender.sendMessage("Use /plugins to get a list of plugins."); }  }  return true; } private void describeToSender(@NotNull Plugin plugin, @NotNull CommandSender sender) { PluginDescriptionFile desc = plugin.getDescription(); sender.sendMessage(ChatColor.GREEN + desc.getName() + ChatColor.WHITE + " version " + ChatColor.GREEN + desc.getVersion()); if (desc.getDescription() != null) sender.sendMessage(desc.getDescription());  if (desc.getWebsite() != null) sender.sendMessage("Website: " + ChatColor.GREEN + desc.getWebsite());  if (!desc.getAuthors().isEmpty()) if (desc.getAuthors().size() == 1) { sender.sendMessage("Author: " + getNameList(desc.getAuthors())); } else { sender.sendMessage("Authors: " + getNameList(desc.getAuthors())); }   if (!desc.getContributors().isEmpty())
/* 165 */       sender.sendMessage("Contributors: " + getNameList(desc.getContributors()));  } private void sendVersion(@NotNull CommandSender sender) { if (this.hasVersion) {
/* 166 */       if (System.currentTimeMillis() - this.lastCheck > getVersionFetcher().getCacheTime()) {
/* 167 */         this.lastCheck = System.currentTimeMillis();
/* 168 */         this.hasVersion = false;
/*     */       } else {
/* 170 */         sendMessages(this.versionMessage, sender);
/*     */         return;
/*     */       } 
/*     */     }
/* 174 */     this.versionLock.lock();
/*     */     
/* 176 */     try { if (this.hasVersion) {
/* 177 */         sendMessages(this.versionMessage, sender);
/*     */         return;
/*     */       } 
/* 180 */       this.versionWaiters.add(sender);
/* 181 */       sender.sendMessage("Checking version, please wait...");
/* 182 */       if (!this.versionTaskStarted) {
/* 183 */         this.versionTaskStarted = true;
/* 184 */         (new Thread(new Runnable()
/*     */             {
/*     */               public void run()
/*     */               {
/* 188 */                 VersionCommand.this.obtainVersion();
/*     */               }
/* 190 */             })).start();
/*     */       }  }
/*     */     finally
/* 193 */     { this.versionLock.unlock(); }  }
/*     */   @NotNull private String getNameList(@NotNull List<String> names) { StringBuilder result = new StringBuilder(); for (int i = 0; i < names.size(); i++) { if (result.length() > 0) { result.append(ChatColor.WHITE); if (i < names.size() - 1) { result.append(", "); } else { result.append(" and "); }  }  result.append(ChatColor.GREEN); result.append(names.get(i)); }  return result.toString(); }
/*     */   @NotNull public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) { Validate.notNull(sender, "Sender cannot be null"); Validate.notNull(args, "Arguments cannot be null"); Validate.notNull(alias, "Alias cannot be null"); if (args.length == 1) { List<String> completions = new ArrayList<>(); String toComplete = args[0].toLowerCase(Locale.ENGLISH); for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) { if (StringUtil.startsWithIgnoreCase(plugin.getName(), toComplete))
/*     */           completions.add(plugin.getName());  }
/*     */        return completions; }
/* 198 */      return (List<String>)ImmutableList.of(); } private void obtainVersion() { String version = Bukkit.getVersion();
/*     */     
/* 200 */     if (version.startsWith("null")) {
/* 201 */       setVersionMessage("Unknown version, custom build?");
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
/*     */       return;
/*     */     } 
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
/* 235 */     setVersionMessage(getVersionFetcher().getVersionMessage(version)); }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setVersionMessage(@NotNull String msg) {
/* 240 */     this.lastCheck = System.currentTimeMillis();
/* 241 */     this.versionMessage = msg;
/* 242 */     this.versionLock.lock();
/*     */     try {
/* 244 */       this.hasVersion = true;
/* 245 */       this.versionTaskStarted = false;
/*     */       
/* 247 */       String[] messages = this.versionMessage.split("\n");
/* 248 */       for (CommandSender sender : this.versionWaiters) {
/* 249 */         for (String message : messages) {
/* 250 */           sender.sendMessage(message);
/*     */         }
/*     */       } 
/*     */       
/* 254 */       this.versionWaiters.clear();
/*     */     } finally {
/* 256 */       this.versionLock.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void sendMessages(String toSplit, CommandSender target) {
/* 262 */     String[] messages = toSplit.split("\n");
/* 263 */     for (String message : messages) {
/* 264 */       target.sendMessage(message);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getDistance(@NotNull String repo, @NotNull String hash) {
/*     */     try {
/* 274 */       BufferedReader reader = Resources.asCharSource(new URL("https://hub.spigotmc.org/stash/rest/api/1.0/projects/SPIGOT/repos/" + repo + "/commits?since=" + URLEncoder.encode(hash, "UTF-8") + "&withCounts=true"), Charsets.UTF_8).openBufferedStream();
/*     */       try {
/* 276 */         JsonObject obj = (JsonObject)(new Gson()).fromJson(reader, JsonObject.class);
/* 277 */         return obj.get("totalCount").getAsInt();
/* 278 */       } catch (JsonSyntaxException ex) {
/* 279 */         ex.printStackTrace();
/* 280 */         return -1;
/*     */       } finally {
/* 282 */         reader.close();
/*     */       } 
/* 284 */     } catch (IOException e) {
/* 285 */       e.printStackTrace();
/* 286 */       return -1;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\command\defaults\VersionCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */