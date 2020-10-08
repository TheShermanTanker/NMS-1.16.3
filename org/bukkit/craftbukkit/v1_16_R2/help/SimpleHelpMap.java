/*     */ package org.bukkit.craftbukkit.v1_16_R2.help;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.base.Predicates;
/*     */ import com.google.common.collect.Collections2;
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ import java.util.TreeSet;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.MultipleCommandAlias;
/*     */ import org.bukkit.command.PluginCommand;
/*     */ import org.bukkit.command.PluginIdentifiableCommand;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*     */ import org.bukkit.help.GenericCommandHelpTopic;
/*     */ import org.bukkit.help.HelpMap;
/*     */ import org.bukkit.help.HelpTopic;
/*     */ import org.bukkit.help.HelpTopicComparator;
/*     */ import org.bukkit.help.HelpTopicFactory;
/*     */ import org.bukkit.help.IndexHelpTopic;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SimpleHelpMap
/*     */   implements HelpMap
/*     */ {
/*     */   private HelpTopic defaultTopic;
/*     */   private final Map<String, HelpTopic> helpTopics;
/*     */   private final Map<Class, HelpTopicFactory<Command>> topicFactoryMap;
/*     */   private final CraftServer server;
/*     */   private HelpYamlReader yaml;
/*     */   
/*     */   public SimpleHelpMap(CraftServer server) {
/*  42 */     this.helpTopics = new TreeMap<>((Comparator<? super String>)HelpTopicComparator.topicNameComparatorInstance());
/*  43 */     this.topicFactoryMap = (Map)new HashMap<>();
/*  44 */     this.server = server;
/*  45 */     this.yaml = new HelpYamlReader((Server)server);
/*     */     
/*  47 */     Predicate indexFilter = Predicates.not(Predicates.instanceOf(CommandAliasHelpTopic.class));
/*  48 */     if (!this.yaml.commandTopicsInMasterIndex()) {
/*  49 */       indexFilter = Predicates.and(indexFilter, Predicates.not(new IsCommandTopicPredicate()));
/*     */     }
/*     */     
/*  52 */     this.defaultTopic = (HelpTopic)new IndexHelpTopic("Index", null, null, Collections2.filter(this.helpTopics.values(), indexFilter), "Use /help [n] to get page n of help.");
/*     */     
/*  54 */     registerHelpTopicFactory(MultipleCommandAlias.class, new MultipleCommandAliasHelpTopicFactory());
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized HelpTopic getHelpTopic(String topicName) {
/*  59 */     if (topicName.equals("")) {
/*  60 */       return this.defaultTopic;
/*     */     }
/*     */     
/*  63 */     if (this.helpTopics.containsKey(topicName)) {
/*  64 */       return this.helpTopics.get(topicName);
/*     */     }
/*     */     
/*  67 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<HelpTopic> getHelpTopics() {
/*  72 */     return this.helpTopics.values();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void addTopic(HelpTopic topic) {
/*  78 */     if (!this.helpTopics.containsKey(topic.getName())) {
/*  79 */       this.helpTopics.put(topic.getName(), topic);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void clear() {
/*  85 */     this.helpTopics.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> getIgnoredPlugins() {
/*  90 */     return this.yaml.getIgnoredPlugins();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void initializeGeneralTopics() {
/*  97 */     this.yaml = new HelpYamlReader((Server)this.server);
/*     */ 
/*     */     
/* 100 */     for (HelpTopic topic : this.yaml.getGeneralTopics()) {
/* 101 */       addTopic(topic);
/*     */     }
/*     */ 
/*     */     
/* 105 */     for (HelpTopic topic : this.yaml.getIndexTopics()) {
/* 106 */       if (topic.getName().equals("Default")) {
/* 107 */         this.defaultTopic = topic; continue;
/*     */       } 
/* 109 */       addTopic(topic);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void initializeCommands() {
/* 119 */     Set<String> ignoredPlugins = new HashSet<>(this.yaml.getIgnoredPlugins());
/*     */ 
/*     */     
/* 122 */     if (ignoredPlugins.contains("All")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 127 */     label61: for (Command command : this.server.getCommandMap().getCommands()) {
/* 128 */       if (commandInIgnoredPlugin(command, ignoredPlugins)) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */       
/* 133 */       for (Class c : this.topicFactoryMap.keySet()) {
/* 134 */         if (c.isAssignableFrom(command.getClass())) {
/* 135 */           HelpTopic t = ((HelpTopicFactory)this.topicFactoryMap.get(c)).createTopic(command);
/* 136 */           if (t != null) { addTopic(t); continue label61; }
/*     */            continue label61;
/*     */         } 
/* 139 */         if (command instanceof PluginCommand && c.isAssignableFrom(((PluginCommand)command).getExecutor().getClass())) {
/* 140 */           HelpTopic t = ((HelpTopicFactory)this.topicFactoryMap.get(c)).createTopic(command);
/* 141 */           if (t != null) addTopic(t);
/*     */         
/*     */         } 
/*     */       } 
/* 145 */       addTopic((HelpTopic)new GenericCommandHelpTopic(command));
/*     */     } 
/*     */ 
/*     */     
/* 149 */     for (Command command : this.server.getCommandMap().getCommands()) {
/* 150 */       if (commandInIgnoredPlugin(command, ignoredPlugins)) {
/*     */         continue;
/*     */       }
/* 153 */       for (String alias : command.getAliases()) {
/*     */         
/* 155 */         if (this.server.getCommandMap().getCommand(alias) == command) {
/* 156 */           addTopic(new CommandAliasHelpTopic("/" + alias, "/" + command.getLabel(), this));
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 162 */     Collection<HelpTopic> filteredTopics = Collections2.filter(this.helpTopics.values(), Predicates.instanceOf(CommandAliasHelpTopic.class));
/* 163 */     if (!filteredTopics.isEmpty()) {
/* 164 */       addTopic((HelpTopic)new IndexHelpTopic("Aliases", "Lists command aliases", null, filteredTopics));
/*     */     }
/*     */ 
/*     */     
/* 168 */     Map<String, Set<HelpTopic>> pluginIndexes = new HashMap<>();
/* 169 */     fillPluginIndexes(pluginIndexes, this.server.getCommandMap().getCommands());
/*     */     
/* 171 */     for (Map.Entry<String, Set<HelpTopic>> entry : pluginIndexes.entrySet()) {
/* 172 */       addTopic((HelpTopic)new IndexHelpTopic(entry.getKey(), "All commands for " + (String)entry.getKey(), null, entry.getValue(), "Below is a list of all " + (String)entry.getKey() + " commands:"));
/*     */     }
/*     */ 
/*     */     
/* 176 */     for (HelpTopicAmendment amendment : this.yaml.getTopicAmendments()) {
/* 177 */       if (this.helpTopics.containsKey(amendment.getTopicName())) {
/* 178 */         ((HelpTopic)this.helpTopics.get(amendment.getTopicName())).amendTopic(amendment.getShortText(), amendment.getFullText());
/* 179 */         if (amendment.getPermission() != null) {
/* 180 */           ((HelpTopic)this.helpTopics.get(amendment.getTopicName())).amendCanSee(amendment.getPermission());
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void fillPluginIndexes(Map<String, Set<HelpTopic>> pluginIndexes, Collection<? extends Command> commands) {
/* 187 */     for (Command command : commands) {
/* 188 */       String pluginName = getCommandPluginName(command);
/* 189 */       if (pluginName != null) {
/* 190 */         HelpTopic topic = getHelpTopic("/" + command.getLabel());
/* 191 */         if (topic != null) {
/* 192 */           if (!pluginIndexes.containsKey(pluginName)) {
/* 193 */             pluginIndexes.put(pluginName, new TreeSet<>((Comparator<? super HelpTopic>)HelpTopicComparator.helpTopicComparatorInstance()));
/*     */           }
/* 195 */           ((Set<HelpTopic>)pluginIndexes.get(pluginName)).add(topic);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private String getCommandPluginName(Command command) {
/* 202 */     if (command instanceof org.bukkit.craftbukkit.v1_16_R2.command.VanillaCommandWrapper) {
/* 203 */       return "Minecraft";
/*     */     }
/* 205 */     if (command instanceof org.bukkit.command.defaults.BukkitCommand) {
/* 206 */       return "Bukkit";
/*     */     }
/* 208 */     if (command instanceof PluginIdentifiableCommand) {
/* 209 */       return ((PluginIdentifiableCommand)command).getPlugin().getName();
/*     */     }
/* 211 */     return null;
/*     */   }
/*     */   
/*     */   private boolean commandInIgnoredPlugin(Command command, Set<String> ignoredPlugins) {
/* 215 */     if (command instanceof org.bukkit.command.defaults.BukkitCommand && ignoredPlugins.contains("Bukkit")) {
/* 216 */       return true;
/*     */     }
/* 218 */     if (command instanceof PluginIdentifiableCommand && ignoredPlugins.contains(((PluginIdentifiableCommand)command).getPlugin().getName())) {
/* 219 */       return true;
/*     */     }
/* 221 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerHelpTopicFactory(Class<?> commandClass, HelpTopicFactory<Command> factory) {
/* 226 */     if (!Command.class.isAssignableFrom(commandClass) && !CommandExecutor.class.isAssignableFrom(commandClass)) {
/* 227 */       throw new IllegalArgumentException("commandClass must implement either Command or CommandExecutor!");
/*     */     }
/* 229 */     this.topicFactoryMap.put(commandClass, factory);
/*     */   }
/*     */   
/*     */   private class IsCommandTopicPredicate implements Predicate<HelpTopic> {
/*     */     private IsCommandTopicPredicate() {}
/*     */     
/*     */     public boolean apply(HelpTopic topic) {
/* 236 */       return (topic.getName().charAt(0) == '/');
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\help\SimpleHelpMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */