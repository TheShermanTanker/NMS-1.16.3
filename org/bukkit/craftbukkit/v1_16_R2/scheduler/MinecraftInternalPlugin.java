/*     */ package org.bukkit.craftbukkit.v1_16_R2.scheduler;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.InputStream;
/*     */ import java.util.List;
/*     */ import java.util.logging.Logger;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.generator.ChunkGenerator;
/*     */ import org.bukkit.plugin.PluginBase;
/*     */ import org.bukkit.plugin.PluginDescriptionFile;
/*     */ import org.bukkit.plugin.PluginLoader;
/*     */ import org.bukkit.plugin.PluginLogger;
/*     */ 
/*     */ public class MinecraftInternalPlugin
/*     */   extends PluginBase
/*     */ {
/*     */   private boolean enabled = true;
/*     */   private final String pluginName;
/*     */   private PluginDescriptionFile pdf;
/*     */   
/*     */   public MinecraftInternalPlugin() {
/*  25 */     this.pluginName = "Minecraft";
/*  26 */     this.pdf = new PluginDescriptionFile(this.pluginName, "1.0", "nms");
/*     */   }
/*     */   
/*     */   public void setEnabled(boolean enabled) {
/*  30 */     this.enabled = enabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public File getDataFolder() {
/*  35 */     throw new UnsupportedOperationException("Not supported.");
/*     */   }
/*     */ 
/*     */   
/*     */   public PluginDescriptionFile getDescription() {
/*  40 */     return this.pdf;
/*     */   }
/*     */ 
/*     */   
/*     */   public FileConfiguration getConfig() {
/*  45 */     throw new UnsupportedOperationException("Not supported.");
/*     */   }
/*     */ 
/*     */   
/*     */   public InputStream getResource(String filename) {
/*  50 */     throw new UnsupportedOperationException("Not supported.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveConfig() {
/*  55 */     throw new UnsupportedOperationException("Not supported.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveDefaultConfig() {
/*  60 */     throw new UnsupportedOperationException("Not supported.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveResource(String resourcePath, boolean replace) {
/*  65 */     throw new UnsupportedOperationException("Not supported.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void reloadConfig() {
/*  70 */     throw new UnsupportedOperationException("Not supported.");
/*     */   }
/*     */ 
/*     */   
/*     */   public PluginLogger getLogger() {
/*  75 */     throw new UnsupportedOperationException("Not supported.");
/*     */   }
/*     */ 
/*     */   
/*     */   public PluginLoader getPluginLoader() {
/*  80 */     throw new UnsupportedOperationException("Not supported.");
/*     */   }
/*     */ 
/*     */   
/*     */   public Server getServer() {
/*  85 */     throw new UnsupportedOperationException("Not supported.");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnabled() {
/*  90 */     return this.enabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onDisable() {
/*  95 */     throw new UnsupportedOperationException("Not supported.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLoad() {
/* 100 */     throw new UnsupportedOperationException("Not supported.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnable() {
/* 105 */     throw new UnsupportedOperationException("Not supported.");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNaggable() {
/* 110 */     throw new UnsupportedOperationException("Not supported.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNaggable(boolean canNag) {
/* 115 */     throw new UnsupportedOperationException("Not supported.");
/*     */   }
/*     */ 
/*     */   
/*     */   public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
/* 120 */     throw new UnsupportedOperationException("Not supported.");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
/* 125 */     throw new UnsupportedOperationException("Not supported.");
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
/* 130 */     throw new UnsupportedOperationException("Not supported.");
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\scheduler\MinecraftInternalPlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */