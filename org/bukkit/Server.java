/*      */ package org.bukkit;
/*      */ 
/*      */ import com.destroystokyo.paper.entity.ai.MobGoals;
/*      */ import com.destroystokyo.paper.profile.PlayerProfile;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.io.File;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.UUID;
/*      */ import java.util.function.Consumer;
/*      */ import java.util.logging.Logger;
/*      */ import net.md_5.bungee.api.chat.BaseComponent;
/*      */ import org.bukkit.advancement.Advancement;
/*      */ import org.bukkit.block.data.BlockData;
/*      */ import org.bukkit.boss.BarColor;
/*      */ import org.bukkit.boss.BarFlag;
/*      */ import org.bukkit.boss.BarStyle;
/*      */ import org.bukkit.boss.BossBar;
/*      */ import org.bukkit.boss.KeyedBossBar;
/*      */ import org.bukkit.command.CommandException;
/*      */ import org.bukkit.command.CommandMap;
/*      */ import org.bukkit.command.CommandSender;
/*      */ import org.bukkit.command.ConsoleCommandSender;
/*      */ import org.bukkit.command.PluginCommand;
/*      */ import org.bukkit.configuration.file.YamlConfiguration;
/*      */ import org.bukkit.entity.Entity;
/*      */ import org.bukkit.entity.Player;
/*      */ import org.bukkit.event.inventory.InventoryType;
/*      */ import org.bukkit.generator.ChunkGenerator;
/*      */ import org.bukkit.help.HelpMap;
/*      */ import org.bukkit.inventory.Inventory;
/*      */ import org.bukkit.inventory.InventoryHolder;
/*      */ import org.bukkit.inventory.ItemFactory;
/*      */ import org.bukkit.inventory.ItemStack;
/*      */ import org.bukkit.inventory.Merchant;
/*      */ import org.bukkit.inventory.Recipe;
/*      */ import org.bukkit.loot.LootTable;
/*      */ import org.bukkit.map.MapView;
/*      */ import org.bukkit.plugin.PluginManager;
/*      */ import org.bukkit.plugin.ServicesManager;
/*      */ import org.bukkit.plugin.messaging.Messenger;
/*      */ import org.bukkit.plugin.messaging.PluginMessageRecipient;
/*      */ import org.bukkit.scheduler.BukkitScheduler;
/*      */ import org.bukkit.scoreboard.ScoreboardManager;
/*      */ import org.bukkit.util.CachedServerIcon;
/*      */ import org.jetbrains.annotations.Contract;
/*      */ import org.jetbrains.annotations.NotNull;
/*      */ import org.jetbrains.annotations.Nullable;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public interface Server
/*      */   extends PluginMessageRecipient
/*      */ {
/*      */   public static final String BROADCAST_CHANNEL_ADMINISTRATIVE = "bukkit.broadcast.admin";
/*      */   public static final String BROADCAST_CHANNEL_USERS = "bukkit.broadcast.user";
/*      */   
/*      */   @NotNull
/*      */   String getName();
/*      */   
/*      */   @NotNull
/*      */   String getVersion();
/*      */   
/*      */   @NotNull
/*      */   String getBukkitVersion();
/*      */   
/*      */   @NotNull
/*      */   String getMinecraftVersion();
/*      */   
/*      */   @NotNull
/*      */   Collection<? extends Player> getOnlinePlayers();
/*      */   
/*      */   int getMaxPlayers();
/*      */   
/*      */   void setMaxPlayers(int paramInt);
/*      */   
/*      */   int getPort();
/*      */   
/*      */   int getViewDistance();
/*      */   
/*      */   @NotNull
/*      */   String getIp();
/*      */   
/*      */   @NotNull
/*      */   String getWorldType();
/*      */   
/*      */   boolean getGenerateStructures();
/*      */   
/*      */   boolean getAllowEnd();
/*      */   
/*      */   boolean getAllowNether();
/*      */   
/*      */   boolean hasWhitelist();
/*      */   
/*      */   void setWhitelist(boolean paramBoolean);
/*      */   
/*      */   @NotNull
/*      */   Set<OfflinePlayer> getWhitelistedPlayers();
/*      */   
/*      */   void reloadWhitelist();
/*      */   
/*      */   int broadcastMessage(@NotNull String paramString);
/*      */   
/*      */   default void broadcast(@NotNull BaseComponent component) {
/*  254 */     spigot().broadcast(component);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void broadcast(@NotNull BaseComponent... components) {
/*  263 */     spigot().broadcast(components);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   String getUpdateFolder();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   File getUpdateFolderFile();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   long getConnectionThrottle();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getTicksPerAnimalSpawns();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getTicksPerMonsterSpawns();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getTicksPerWaterSpawns();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getTicksPerWaterAmbientSpawns();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getTicksPerAmbientSpawns();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   Player getPlayer(@NotNull String paramString);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   Player getPlayerExact(@NotNull String paramString);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   List<Player> matchPlayer(@NotNull String paramString);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   Player getPlayer(@NotNull UUID paramUUID);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   UUID getPlayerUniqueId(@NotNull String paramString);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   PluginManager getPluginManager();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   BukkitScheduler getScheduler();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   ServicesManager getServicesManager();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   List<World> getWorlds();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   World createWorld(@NotNull WorldCreator paramWorldCreator);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean unloadWorld(@NotNull String paramString, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean unloadWorld(@NotNull World paramWorld, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   World getWorld(@NotNull String paramString);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   World getWorld(@NotNull UUID paramUUID);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   @Nullable
/*      */   MapView getMap(int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   MapView createMap(@NotNull World paramWorld);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   ItemStack createExplorerMap(@NotNull World paramWorld, @NotNull Location paramLocation, @NotNull StructureType paramStructureType);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   ItemStack createExplorerMap(@NotNull World paramWorld, @NotNull Location paramLocation, @NotNull StructureType paramStructureType, int paramInt, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void reload();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void reloadData();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Logger getLogger();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   PluginCommand getPluginCommand(@NotNull String paramString);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void savePlayers();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean dispatchCommand(@NotNull CommandSender paramCommandSender, @NotNull String paramString) throws CommandException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract("null -> false")
/*      */   boolean addRecipe(@Nullable Recipe paramRecipe);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   List<Recipe> getRecipesFor(@NotNull ItemStack paramItemStack);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   Recipe getRecipe(@NotNull NamespacedKey paramNamespacedKey);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Iterator<Recipe> recipeIterator();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void clearRecipes();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void resetRecipes();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean removeRecipe(@NotNull NamespacedKey paramNamespacedKey);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Map<String, String[]> getCommandAliases();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getSpawnRadius();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setSpawnRadius(int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean getOnlineMode();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean getAllowFlight();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isHardcore();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void shutdown();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int broadcast(@NotNull String paramString1, @NotNull String paramString2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   @NotNull
/*      */   OfflinePlayer getOfflinePlayer(@NotNull String paramString);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   OfflinePlayer getOfflinePlayer(@NotNull UUID paramUUID);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Set<String> getIPBans();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void banIP(@NotNull String paramString);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void unbanIP(@NotNull String paramString);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Set<OfflinePlayer> getBannedPlayers();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   BanList getBanList(@NotNull BanList.Type paramType);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Set<OfflinePlayer> getOperators();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   GameMode getDefaultGameMode();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setDefaultGameMode(@NotNull GameMode paramGameMode);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   ConsoleCommandSender getConsoleSender();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   File getWorldContainer();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   OfflinePlayer[] getOfflinePlayers();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Messenger getMessenger();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   HelpMap getHelpMap();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Inventory createInventory(@Nullable InventoryHolder paramInventoryHolder, @NotNull InventoryType paramInventoryType);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Inventory createInventory(@Nullable InventoryHolder paramInventoryHolder, @NotNull InventoryType paramInventoryType, @NotNull String paramString);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Inventory createInventory(@Nullable InventoryHolder paramInventoryHolder, int paramInt) throws IllegalArgumentException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Inventory createInventory(@Nullable InventoryHolder paramInventoryHolder, int paramInt, @NotNull String paramString) throws IllegalArgumentException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Merchant createMerchant(@Nullable String paramString);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getMonsterSpawnLimit();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getAnimalSpawnLimit();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getWaterAnimalSpawnLimit();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getWaterAmbientSpawnLimit();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getAmbientSpawnLimit();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isPrimaryThread();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   String getMotd();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   String getShutdownMessage();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Warning.WarningState getWarningState();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   ItemFactory getItemFactory();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   ScoreboardManager getScoreboardManager();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   CachedServerIcon getServerIcon();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   CachedServerIcon loadServerIcon(@NotNull File paramFile) throws IllegalArgumentException, Exception;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   CachedServerIcon loadServerIcon(@NotNull BufferedImage paramBufferedImage) throws IllegalArgumentException, Exception;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setIdleTimeout(int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getIdleTimeout();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   ChunkGenerator.ChunkData createChunkData(@NotNull World paramWorld);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   ChunkGenerator.ChunkData createVanillaChunkData(@NotNull World paramWorld, int paramInt1, int paramInt2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   BossBar createBossBar(@Nullable String paramString, @NotNull BarColor paramBarColor, @NotNull BarStyle paramBarStyle, @NotNull BarFlag... paramVarArgs);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   KeyedBossBar createBossBar(@NotNull NamespacedKey paramNamespacedKey, @Nullable String paramString, @NotNull BarColor paramBarColor, @NotNull BarStyle paramBarStyle, @NotNull BarFlag... paramVarArgs);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Iterator<KeyedBossBar> getBossBars();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   KeyedBossBar getBossBar(@NotNull NamespacedKey paramNamespacedKey);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean removeBossBar(@NotNull NamespacedKey paramNamespacedKey);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   Entity getEntity(@NotNull UUID paramUUID);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   double[] getTPS();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   long[] getTickTimes();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   double getAverageTickTime();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   CommandMap getCommandMap();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   Advancement getAdvancement(@NotNull NamespacedKey paramNamespacedKey);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Iterator<Advancement> advancementIterator();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   BlockData createBlockData(@NotNull Material paramMaterial);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   BlockData createBlockData(@NotNull Material paramMaterial, @Nullable Consumer<BlockData> paramConsumer);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   BlockData createBlockData(@NotNull String paramString) throws IllegalArgumentException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   @Contract("null, null -> fail")
/*      */   BlockData createBlockData(@Nullable Material paramMaterial, @Nullable String paramString) throws IllegalArgumentException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @UndefinedNullability
/*      */   <T extends Keyed> Tag<T> getTag(@NotNull String paramString, @NotNull NamespacedKey paramNamespacedKey, @NotNull Class<T> paramClass);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   <T extends Keyed> Iterable<Tag<T>> getTags(@NotNull String paramString, @NotNull Class<T> paramClass);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   LootTable getLootTable(@NotNull NamespacedKey paramNamespacedKey);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   List<Entity> selectEntities(@NotNull CommandSender paramCommandSender, @NotNull String paramString) throws IllegalArgumentException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   @NotNull
/*      */   UnsafeValues getUnsafe();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Spigot spigot();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void reloadPermissions();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean reloadCommandAliases();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean suggestPlayerNamesWhenNullTabCompletions();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   String getPermissionMessage();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   PlayerProfile createProfile(@NotNull UUID paramUUID);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   PlayerProfile createProfile(@NotNull String paramString);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   PlayerProfile createProfile(@Nullable UUID paramUUID, @Nullable String paramString);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getCurrentTick();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isStopping();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   MobGoals getMobGoals();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class Spigot
/*      */   {
/*      */     @NotNull
/*      */     public YamlConfiguration getConfig() {
/* 1436 */       throw new UnsupportedOperationException("Not supported yet.");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     @NotNull
/*      */     public YamlConfiguration getBukkitConfig() {
/* 1443 */       throw new UnsupportedOperationException("Not supported yet.");
/*      */     }
/*      */ 
/*      */     
/*      */     @NotNull
/*      */     public YamlConfiguration getSpigotConfig() {
/* 1449 */       throw new UnsupportedOperationException("Not supported yet.");
/*      */     }
/*      */ 
/*      */     
/*      */     @NotNull
/*      */     public YamlConfiguration getPaperConfig() {
/* 1455 */       throw new UnsupportedOperationException("Not supported yet.");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     @NotNull
/*      */     public YamlConfiguration getTuinityConfig() {
/* 1463 */       throw new UnsupportedOperationException("Not supported yet.");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void broadcast(@NotNull BaseComponent component) {
/* 1473 */       throw new UnsupportedOperationException("Not supported yet.");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void broadcast(@NotNull BaseComponent... components) {
/* 1482 */       throw new UnsupportedOperationException("Not supported yet.");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void restart() {
/* 1489 */       throw new UnsupportedOperationException("Not supported yet.");
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\Server.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */