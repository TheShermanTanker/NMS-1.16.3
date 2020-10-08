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
/*      */ public final class Bukkit
/*      */ {
/*      */   private static Server server;
/*      */   
/*      */   @NotNull
/*      */   public static Server getServer() {
/*   72 */     return server;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setServer(@NotNull Server server) {
/*   83 */     if (Bukkit.server != null) {
/*   84 */       throw new UnsupportedOperationException("Cannot redefine singleton Server");
/*      */     }
/*      */     
/*   87 */     Bukkit.server = server;
/*   88 */     server.getLogger().info("This server is running " + getName() + " version " + getVersion() + " (Implementing API version " + getBukkitVersion() + ")");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static String getName() {
/*   98 */     return server.getName();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static String getVersion() {
/*  108 */     return server.getVersion();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static String getBukkitVersion() {
/*  118 */     return server.getBukkitVersion();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static String getMinecraftVersion() {
/*  129 */     return server.getMinecraftVersion();
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
/*      */   @NotNull
/*      */   public static Collection<? extends Player> getOnlinePlayers() {
/*  162 */     return server.getOnlinePlayers();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getMaxPlayers() {
/*  171 */     return server.getMaxPlayers();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setMaxPlayers(int maxPlayers) {
/*  181 */     server.setMaxPlayers(maxPlayers);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getPort() {
/*  191 */     return server.getPort();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getViewDistance() {
/*  200 */     return server.getViewDistance();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static String getIp() {
/*  212 */     return server.getIp();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static String getWorldType() {
/*  222 */     return server.getWorldType();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean getGenerateStructures() {
/*  231 */     return server.getGenerateStructures();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean getAllowEnd() {
/*  240 */     return server.getAllowEnd();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean getAllowNether() {
/*  249 */     return server.getAllowNether();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean hasWhitelist() {
/*  258 */     return server.hasWhitelist();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setWhitelist(boolean value) {
/*  267 */     server.setWhitelist(value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static Set<OfflinePlayer> getWhitelistedPlayers() {
/*  277 */     return server.getWhitelistedPlayers();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void reloadWhitelist() {
/*  284 */     server.reloadWhitelist();
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
/*      */   
/*      */   public static int broadcastMessage(@NotNull String message) {
/*  297 */     return server.broadcastMessage(message);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void broadcast(@NotNull BaseComponent component) {
/*  307 */     server.broadcast(component);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void broadcast(@NotNull BaseComponent... components) {
/*  316 */     server.broadcast(components);
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
/*      */   
/*      */   @NotNull
/*      */   public static String getUpdateFolder() {
/*  330 */     return server.getUpdateFolder();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static File getUpdateFolderFile() {
/*  341 */     return server.getUpdateFolderFile();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static long getConnectionThrottle() {
/*  350 */     return server.getConnectionThrottle();
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
/*      */   public static int getTicksPerAnimalSpawns() {
/*  373 */     return server.getTicksPerAnimalSpawns();
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
/*      */   public static int getTicksPerMonsterSpawns() {
/*  396 */     return server.getTicksPerMonsterSpawns();
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
/*      */   public static int getTicksPerWaterSpawns() {
/*  418 */     return server.getTicksPerWaterSpawns();
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
/*      */   public static int getTicksPerAmbientSpawns() {
/*  440 */     return server.getTicksPerAmbientSpawns();
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
/*      */   public static int getTicksPerWaterAmbientSpawns() {
/*  462 */     return server.getTicksPerAmbientSpawns();
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
/*      */   @Nullable
/*      */   public static Player getPlayer(@NotNull String name) {
/*  475 */     return server.getPlayer(name);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public static Player getPlayerExact(@NotNull String name) {
/*  486 */     return server.getPlayerExact(name);
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
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static List<Player> matchPlayer(@NotNull String name) {
/*  501 */     return server.matchPlayer(name);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public static Player getPlayer(@NotNull UUID id) {
/*  512 */     return server.getPlayer(id);
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
/*      */   @Nullable
/*      */   public static UUID getPlayerUniqueId(@NotNull String playerName) {
/*  525 */     return server.getPlayerUniqueId(playerName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static PluginManager getPluginManager() {
/*  536 */     return server.getPluginManager();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static BukkitScheduler getScheduler() {
/*  546 */     return server.getScheduler();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static ServicesManager getServicesManager() {
/*  556 */     return server.getServicesManager();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static List<World> getWorlds() {
/*  566 */     return server.getWorlds();
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
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public static World createWorld(@NotNull WorldCreator creator) {
/*  581 */     return server.createWorld(creator);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean unloadWorld(@NotNull String name, boolean save) {
/*  592 */     return server.unloadWorld(name, save);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean unloadWorld(@NotNull World world, boolean save) {
/*  603 */     return server.unloadWorld(world, save);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public static World getWorld(@NotNull String name) {
/*  614 */     return server.getWorld(name);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public static World getWorld(@NotNull UUID uid) {
/*  625 */     return server.getWorld(uid);
/*      */   }
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
/*      */   public static MapView getMap(int id) {
/*  638 */     return server.getMap(id);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static MapView createMap(@NotNull World world) {
/*  649 */     return server.createMap(world);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static ItemStack createExplorerMap(@NotNull World world, @NotNull Location location, @NotNull StructureType structureType) {
/*  669 */     return server.createExplorerMap(world, location, structureType);
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
/*      */   @NotNull
/*      */   public static ItemStack createExplorerMap(@NotNull World world, @NotNull Location location, @NotNull StructureType structureType, int radius, boolean findUnexplored) {
/*  692 */     return server.createExplorerMap(world, location, structureType, radius, findUnexplored);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void reload() {
/*  699 */     server.reload();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void reloadData() {
/*  707 */     server.reloadData();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static Logger getLogger() {
/*  717 */     return server.getLogger();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public static PluginCommand getPluginCommand(@NotNull String name) {
/*  728 */     return server.getPluginCommand(name);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void savePlayers() {
/*  735 */     server.savePlayers();
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
/*      */ 
/*      */   
/*      */   public static boolean dispatchCommand(@NotNull CommandSender sender, @NotNull String commandLine) throws CommandException {
/*  749 */     return server.dispatchCommand(sender, commandLine);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract("null -> false")
/*      */   public static boolean addRecipe(@Nullable Recipe recipe) {
/*  761 */     return server.addRecipe(recipe);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static List<Recipe> getRecipesFor(@NotNull ItemStack result) {
/*  773 */     return server.getRecipesFor(result);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public static Recipe getRecipe(@NotNull NamespacedKey recipeKey) {
/*  784 */     return server.getRecipe(recipeKey);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static Iterator<Recipe> recipeIterator() {
/*  794 */     return server.recipeIterator();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void clearRecipes() {
/*  801 */     server.clearRecipes();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void resetRecipes() {
/*  808 */     server.resetRecipes();
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
/*      */ 
/*      */   
/*      */   public static boolean removeRecipe(@NotNull NamespacedKey key) {
/*  822 */     return server.removeRecipe(key);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static Map<String, String[]> getCommandAliases() {
/*  832 */     return server.getCommandAliases();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSpawnRadius() {
/*  841 */     return server.getSpawnRadius();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setSpawnRadius(int value) {
/*  850 */     server.setSpawnRadius(value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean getOnlineMode() {
/*  859 */     return server.getOnlineMode();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean getAllowFlight() {
/*  868 */     return server.getAllowFlight();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isHardcore() {
/*  877 */     return server.isHardcore();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void shutdown() {
/*  884 */     server.shutdown();
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
/*      */   
/*      */   public static int broadcast(@NotNull String message, @NotNull String permission) {
/*  897 */     return server.broadcast(message, permission);
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
/*      */   public static OfflinePlayer getOfflinePlayer(@NotNull String name) {
/*  919 */     return server.getOfflinePlayer(name);
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
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static OfflinePlayer getOfflinePlayer(@NotNull UUID id) {
/*  934 */     return server.getOfflinePlayer(id);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static Set<String> getIPBans() {
/*  944 */     return server.getIPBans();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void banIP(@NotNull String address) {
/*  953 */     server.banIP(address);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void unbanIP(@NotNull String address) {
/*  962 */     server.unbanIP(address);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static Set<OfflinePlayer> getBannedPlayers() {
/*  972 */     return server.getBannedPlayers();
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
/*      */   
/*      */   @NotNull
/*      */   public static BanList getBanList(@NotNull BanList.Type type) {
/*  986 */     return server.getBanList(type);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static Set<OfflinePlayer> getOperators() {
/*  996 */     return server.getOperators();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static GameMode getDefaultGameMode() {
/* 1006 */     return server.getDefaultGameMode();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setDefaultGameMode(@NotNull GameMode mode) {
/* 1015 */     server.setDefaultGameMode(mode);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static ConsoleCommandSender getConsoleSender() {
/* 1026 */     return server.getConsoleSender();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static File getWorldContainer() {
/* 1036 */     return server.getWorldContainer();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static OfflinePlayer[] getOfflinePlayers() {
/* 1046 */     return server.getOfflinePlayers();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static Messenger getMessenger() {
/* 1056 */     return server.getMessenger();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static HelpMap getHelpMap() {
/* 1066 */     return server.getHelpMap();
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
/*      */   @NotNull
/*      */   public static Inventory createInventory(@Nullable InventoryHolder owner, @NotNull InventoryType type) {
/* 1092 */     return server.createInventory(owner, type);
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
/*      */   @NotNull
/*      */   public static Inventory createInventory(@Nullable InventoryHolder owner, @NotNull InventoryType type, @NotNull String title) {
/* 1121 */     return server.createInventory(owner, type, title);
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
/*      */   
/*      */   @NotNull
/*      */   public static Inventory createInventory(@Nullable InventoryHolder owner, int size) throws IllegalArgumentException {
/* 1135 */     return server.createInventory(owner, size);
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
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static Inventory createInventory(@Nullable InventoryHolder owner, int size, @NotNull String title) throws IllegalArgumentException {
/* 1151 */     return server.createInventory(owner, size, title);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static Merchant createMerchant(@Nullable String title) {
/* 1163 */     return server.createMerchant(title);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getMonsterSpawnLimit() {
/* 1173 */     return server.getMonsterSpawnLimit();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getAnimalSpawnLimit() {
/* 1183 */     return server.getAnimalSpawnLimit();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getWaterAnimalSpawnLimit() {
/* 1193 */     return server.getWaterAnimalSpawnLimit();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getWaterAmbientSpawnLimit() {
/* 1203 */     return server.getAmbientSpawnLimit();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getAmbientSpawnLimit() {
/* 1213 */     return server.getAmbientSpawnLimit();
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isPrimaryThread() {
/* 1229 */     return server.isPrimaryThread();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static String getMotd() {
/* 1239 */     return server.getMotd();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public static String getShutdownMessage() {
/* 1249 */     return server.getShutdownMessage();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static Warning.WarningState getWarningState() {
/* 1259 */     return server.getWarningState();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static ItemFactory getItemFactory() {
/* 1270 */     return server.getItemFactory();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static ScoreboardManager getScoreboardManager() {
/* 1282 */     return server.getScoreboardManager();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public static CachedServerIcon getServerIcon() {
/* 1294 */     return server.getServerIcon();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static CachedServerIcon loadServerIcon(@NotNull File file) throws IllegalArgumentException, Exception {
/* 1313 */     return server.loadServerIcon(file);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static CachedServerIcon loadServerIcon(@NotNull BufferedImage image) throws IllegalArgumentException, Exception {
/* 1331 */     return server.loadServerIcon(image);
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
/*      */   public static void setIdleTimeout(int threshold) {
/* 1343 */     server.setIdleTimeout(threshold);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getIdleTimeout() {
/* 1352 */     return server.getIdleTimeout();
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
/*      */   
/*      */   @NotNull
/*      */   public static ChunkGenerator.ChunkData createChunkData(@NotNull World world) {
/* 1366 */     return server.createChunkData(world);
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
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static ChunkGenerator.ChunkData createVanillaChunkData(@NotNull World world, int x, int z) {
/* 1381 */     return server.createVanillaChunkData(world, x, z);
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
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static BossBar createBossBar(@Nullable String title, @NotNull BarColor color, @NotNull BarStyle style, @NotNull BarFlag... flags) {
/* 1397 */     return server.createBossBar(title, color, style, flags);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static KeyedBossBar createBossBar(@NotNull NamespacedKey key, @Nullable String title, @NotNull BarColor color, @NotNull BarStyle style, @NotNull BarFlag... flags) {
/* 1416 */     return server.createBossBar(key, title, color, style, flags);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static Iterator<KeyedBossBar> getBossBars() {
/* 1435 */     return server.getBossBars();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public static KeyedBossBar getBossBar(@NotNull NamespacedKey key) {
/* 1455 */     return server.getBossBar(key);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean removeBossBar(@NotNull NamespacedKey key) {
/* 1474 */     return server.removeBossBar(key);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public static Entity getEntity(@NotNull UUID uuid) {
/* 1485 */     return server.getEntity(uuid);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static double[] getTPS() {
/* 1495 */     return server.getTPS();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static long[] getTickTimes() {
/* 1505 */     return server.getTickTimes();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double getAverageTickTime() {
/* 1514 */     return (server == null) ? 0.0D : server.getAverageTickTime();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public static Advancement getAdvancement(@NotNull NamespacedKey key) {
/* 1526 */     return server.getAdvancement(key);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static Iterator<Advancement> advancementIterator() {
/* 1537 */     return server.advancementIterator();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static BlockData createBlockData(@NotNull Material material) {
/* 1549 */     return server.createBlockData(material);
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
/*      */   public static BlockData createBlockData(@NotNull Material material, @Nullable Consumer<BlockData> consumer) {
/* 1562 */     return server.createBlockData(material, consumer);
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
/*      */   public static BlockData createBlockData(@NotNull String data) throws IllegalArgumentException {
/* 1575 */     return server.createBlockData(data);
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
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   @Contract("null, null -> fail")
/*      */   public static BlockData createBlockData(@Nullable Material material, @Nullable String data) throws IllegalArgumentException {
/* 1591 */     return server.createBlockData(material, data);
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
/*      */   @UndefinedNullability
/*      */   public static <T extends Keyed> Tag<T> getTag(@NotNull String registry, @NotNull NamespacedKey tag, @NotNull Class<T> clazz) {
/* 1614 */     return server.getTag(registry, tag, clazz);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static <T extends Keyed> Iterable<Tag<T>> getTags(@NotNull String registry, @NotNull Class<T> clazz) {
/* 1632 */     return server.getTags(registry, clazz);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public static LootTable getLootTable(@NotNull NamespacedKey key) {
/* 1643 */     return server.getLootTable(key);
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
/*      */   @NotNull
/*      */   public static List<Entity> selectEntities(@NotNull CommandSender sender, @NotNull String selector) throws IllegalArgumentException {
/* 1668 */     return server.selectEntities(sender, selector);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   @NotNull
/*      */   public static UnsafeValues getUnsafe() {
/* 1678 */     return server.getUnsafe();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static CommandMap getCommandMap() {
/* 1690 */     return server.getCommandMap();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void reloadPermissions() {
/* 1697 */     server.reloadPermissions();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean reloadCommandAliases() {
/* 1706 */     return server.reloadCommandAliases();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean suggestPlayerNamesWhenNullTabCompletions() {
/* 1716 */     return server.suggestPlayerNamesWhenNullTabCompletions();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static String getPermissionMessage() {
/* 1725 */     return server.getPermissionMessage();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static PlayerProfile createProfile(@NotNull UUID uuid) {
/* 1735 */     return server.createProfile(uuid);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static PlayerProfile createProfile(@NotNull String name) {
/* 1745 */     return server.createProfile(name);
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
/*      */   
/*      */   @NotNull
/*      */   public static PlayerProfile createProfile(@Nullable UUID uuid, @Nullable String name) {
/* 1759 */     return server.createProfile(uuid, name);
/*      */   }
/*      */   
/*      */   public static int getCurrentTick() {
/* 1763 */     return server.getCurrentTick();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isStopping() {
/* 1772 */     return server.isStopping();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static MobGoals getMobGoals() {
/* 1782 */     return server.getMobGoals();
/*      */   }
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static Server.Spigot spigot() {
/* 1788 */     return server.spigot();
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\Bukkit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */