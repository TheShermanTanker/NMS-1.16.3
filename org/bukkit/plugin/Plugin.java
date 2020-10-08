/*     */ package org.bukkit.plugin;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.InputStream;
/*     */ import java.util.logging.Logger;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.command.TabExecutor;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.generator.ChunkGenerator;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
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
/*     */ public interface Plugin
/*     */   extends TabExecutor
/*     */ {
/*     */   @NotNull
/*     */   File getDataFolder();
/*     */   
/*     */   @NotNull
/*     */   PluginDescriptionFile getDescription();
/*     */   
/*     */   @NotNull
/*     */   FileConfiguration getConfig();
/*     */   
/*     */   @Nullable
/*     */   InputStream getResource(@NotNull String paramString);
/*     */   
/*     */   void saveConfig();
/*     */   
/*     */   void saveDefaultConfig();
/*     */   
/*     */   void saveResource(@NotNull String paramString, boolean paramBoolean);
/*     */   
/*     */   void reloadConfig();
/*     */   
/*     */   @NotNull
/*     */   PluginLoader getPluginLoader();
/*     */   
/*     */   @NotNull
/*     */   Server getServer();
/*     */   
/*     */   boolean isEnabled();
/*     */   
/*     */   void onDisable();
/*     */   
/*     */   void onLoad();
/*     */   
/*     */   void onEnable();
/*     */   
/*     */   boolean isNaggable();
/*     */   
/*     */   void setNaggable(boolean paramBoolean);
/*     */   
/*     */   @Nullable
/*     */   ChunkGenerator getDefaultWorldGenerator(@NotNull String paramString1, @Nullable String paramString2);
/*     */   
/*     */   @NotNull
/*     */   Logger getLogger();
/*     */   
/*     */   @NotNull
/*     */   default Logger getSLF4JLogger() {
/* 172 */     return LoggerFactory.getLogger(getLogger().getName());
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   String getName();
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\plugin\Plugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */