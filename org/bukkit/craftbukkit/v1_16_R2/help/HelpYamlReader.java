/*     */ package org.bukkit.craftbukkit.v1_16_R2.help;
/*     */ 
/*     */ import com.google.common.base.Charsets;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.configuration.Configuration;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.help.HelpTopic;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HelpYamlReader
/*     */ {
/*     */   private YamlConfiguration helpYaml;
/*  22 */   private final char ALT_COLOR_CODE = '&';
/*     */   private final Server server;
/*     */   
/*     */   public HelpYamlReader(Server server) {
/*  26 */     this.server = server;
/*     */     
/*  28 */     File helpYamlFile = new File("help.yml");
/*  29 */     YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("configurations/help.yml"), Charsets.UTF_8));
/*     */     
/*     */     try {
/*  32 */       this.helpYaml = YamlConfiguration.loadConfiguration(helpYamlFile);
/*  33 */       this.helpYaml.options().copyDefaults(true);
/*  34 */       this.helpYaml.setDefaults((Configuration)defaultConfig);
/*     */       
/*     */       try {
/*  37 */         if (!helpYamlFile.exists()) {
/*  38 */           this.helpYaml.save(helpYamlFile);
/*     */         }
/*  40 */       } catch (IOException ex) {
/*  41 */         server.getLogger().log(Level.SEVERE, "Could not save " + helpYamlFile, ex);
/*     */       } 
/*  43 */     } catch (Exception ex) {
/*  44 */       server.getLogger().severe("Failed to load help.yml. Verify the yaml indentation is correct. Reverting to default help.yml.");
/*  45 */       this.helpYaml = defaultConfig;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<HelpTopic> getGeneralTopics() {
/*  55 */     List<HelpTopic> topics = new LinkedList<>();
/*  56 */     ConfigurationSection generalTopics = this.helpYaml.getConfigurationSection("general-topics");
/*  57 */     if (generalTopics != null) {
/*  58 */       for (String topicName : generalTopics.getKeys(false)) {
/*  59 */         ConfigurationSection section = generalTopics.getConfigurationSection(topicName);
/*  60 */         String shortText = ChatColor.translateAlternateColorCodes('&', section.getString("shortText", ""));
/*  61 */         String fullText = ChatColor.translateAlternateColorCodes('&', section.getString("fullText", ""));
/*  62 */         String permission = section.getString("permission", "");
/*  63 */         topics.add(new CustomHelpTopic(topicName, shortText, fullText, permission));
/*     */       } 
/*     */     }
/*  66 */     return topics;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<HelpTopic> getIndexTopics() {
/*  75 */     List<HelpTopic> topics = new LinkedList<>();
/*  76 */     ConfigurationSection indexTopics = this.helpYaml.getConfigurationSection("index-topics");
/*  77 */     if (indexTopics != null) {
/*  78 */       for (String topicName : indexTopics.getKeys(false)) {
/*  79 */         ConfigurationSection section = indexTopics.getConfigurationSection(topicName);
/*  80 */         String shortText = ChatColor.translateAlternateColorCodes('&', section.getString("shortText", ""));
/*  81 */         String preamble = ChatColor.translateAlternateColorCodes('&', section.getString("preamble", ""));
/*  82 */         String permission = ChatColor.translateAlternateColorCodes('&', section.getString("permission", ""));
/*  83 */         List<String> commands = section.getStringList("commands");
/*  84 */         topics.add(new CustomIndexHelpTopic(this.server.getHelpMap(), topicName, shortText, permission, commands, preamble));
/*     */       } 
/*     */     }
/*  87 */     return topics;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<HelpTopicAmendment> getTopicAmendments() {
/*  96 */     List<HelpTopicAmendment> amendments = new LinkedList<>();
/*  97 */     ConfigurationSection commandTopics = this.helpYaml.getConfigurationSection("amended-topics");
/*  98 */     if (commandTopics != null) {
/*  99 */       for (String topicName : commandTopics.getKeys(false)) {
/* 100 */         ConfigurationSection section = commandTopics.getConfigurationSection(topicName);
/* 101 */         String description = ChatColor.translateAlternateColorCodes('&', section.getString("shortText", ""));
/* 102 */         String usage = ChatColor.translateAlternateColorCodes('&', section.getString("fullText", ""));
/* 103 */         String permission = section.getString("permission", "");
/* 104 */         amendments.add(new HelpTopicAmendment(topicName, description, usage, permission));
/*     */       } 
/*     */     }
/* 107 */     return amendments;
/*     */   }
/*     */   
/*     */   public List<String> getIgnoredPlugins() {
/* 111 */     return this.helpYaml.getStringList("ignore-plugins");
/*     */   }
/*     */   
/*     */   public boolean commandTopicsInMasterIndex() {
/* 115 */     return this.helpYaml.getBoolean("command-topics-in-master-index", true);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\help\HelpYamlReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */