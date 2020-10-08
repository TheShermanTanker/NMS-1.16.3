/*      */ package org.bukkit.craftbukkit.v1_16_R2;
/*      */ import com.destroystokyo.paper.PaperConfig;
/*      */ import com.destroystokyo.paper.entity.ai.MobGoals;
/*      */ import com.destroystokyo.paper.entity.ai.PaperMobGoals;
/*      */ import com.destroystokyo.paper.profile.CraftPlayerProfile;
/*      */ import com.destroystokyo.paper.profile.PlayerProfile;
/*      */ import com.google.common.base.Charsets;
/*      */ import com.google.common.base.Function;
/*      */ import com.google.common.base.Preconditions;
/*      */ import com.google.common.collect.ImmutableList;
/*      */ import com.google.common.collect.ImmutableSet;
/*      */ import com.google.common.collect.Iterators;
/*      */ import com.google.common.collect.Lists;
/*      */ import com.google.common.collect.MapMaker;
/*      */ import com.mojang.authlib.GameProfile;
/*      */ import com.mojang.brigadier.StringReader;
/*      */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*      */ import com.mojang.brigadier.tree.CommandNode;
/*      */ import com.mojang.brigadier.tree.LiteralCommandNode;
/*      */ import com.mojang.serialization.DynamicOps;
/*      */ import com.tuinity.tuinity.config.TuinityConfig;
/*      */ import io.netty.buffer.ByteBuf;
/*      */ import io.netty.buffer.ByteBufOutputStream;
/*      */ import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.FilenameFilter;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.OutputStream;
/*      */ import java.lang.management.ManagementFactory;
/*      */ import java.lang.reflect.Method;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.file.Files;
/*      */ import java.nio.file.Path;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.Objects;
/*      */ import java.util.Properties;
/*      */ import java.util.Set;
/*      */ import java.util.UUID;
/*      */ import java.util.function.Consumer;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;
/*      */ import java.util.stream.Collectors;
/*      */ import javax.annotation.Nonnull;
/*      */ import javax.annotation.Nullable;
/*      */ import javax.imageio.ImageIO;
/*      */ import javax.management.MBeanServer;
/*      */ import net.md_5.bungee.api.chat.BaseComponent;
/*      */ import net.minecraft.server.v1_16_R2.Advancement;
/*      */ import net.minecraft.server.v1_16_R2.ArgumentEntity;
/*      */ import net.minecraft.server.v1_16_R2.BiomeManager;
/*      */ import net.minecraft.server.v1_16_R2.Block;
/*      */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*      */ import net.minecraft.server.v1_16_R2.BossBattleCustom;
/*      */ import net.minecraft.server.v1_16_R2.BossBattleCustomData;
/*      */ import net.minecraft.server.v1_16_R2.ChunkCoordIntPair;
/*      */ import net.minecraft.server.v1_16_R2.ChunkGenerator;
/*      */ import net.minecraft.server.v1_16_R2.CommandDispatcher;
/*      */ import net.minecraft.server.v1_16_R2.CommandListenerWrapper;
/*      */ import net.minecraft.server.v1_16_R2.CommandReload;
/*      */ import net.minecraft.server.v1_16_R2.Convertable;
/*      */ import net.minecraft.server.v1_16_R2.DataConverterRegistry;
/*      */ import net.minecraft.server.v1_16_R2.DedicatedPlayerList;
/*      */ import net.minecraft.server.v1_16_R2.DedicatedServer;
/*      */ import net.minecraft.server.v1_16_R2.DedicatedServerProperties;
/*      */ import net.minecraft.server.v1_16_R2.DedicatedServerSettings;
/*      */ import net.minecraft.server.v1_16_R2.DimensionManager;
/*      */ import net.minecraft.server.v1_16_R2.Enchantments;
/*      */ import net.minecraft.server.v1_16_R2.Entity;
/*      */ import net.minecraft.server.v1_16_R2.EntityPlayer;
/*      */ import net.minecraft.server.v1_16_R2.EnumDifficulty;
/*      */ import net.minecraft.server.v1_16_R2.EnumGamemode;
/*      */ import net.minecraft.server.v1_16_R2.FluidType;
/*      */ import net.minecraft.server.v1_16_R2.GeneratorSettings;
/*      */ import net.minecraft.server.v1_16_R2.IChunkAccess;
/*      */ import net.minecraft.server.v1_16_R2.IRecipe;
/*      */ import net.minecraft.server.v1_16_R2.IRegistry;
/*      */ import net.minecraft.server.v1_16_R2.IWorldDataServer;
/*      */ import net.minecraft.server.v1_16_R2.Item;
/*      */ import net.minecraft.server.v1_16_R2.ItemStack;
/*      */ import net.minecraft.server.v1_16_R2.ItemWorldMap;
/*      */ import net.minecraft.server.v1_16_R2.JsonListEntry;
/*      */ import net.minecraft.server.v1_16_R2.LootTableRegistry;
/*      */ import net.minecraft.server.v1_16_R2.MinecraftKey;
/*      */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*      */ import net.minecraft.server.v1_16_R2.MobEffects;
/*      */ import net.minecraft.server.v1_16_R2.MobSpawnerPhantom;
/*      */ import net.minecraft.server.v1_16_R2.MobSpawnerTrader;
/*      */ import net.minecraft.server.v1_16_R2.NBTBase;
/*      */ import net.minecraft.server.v1_16_R2.PlayerList;
/*      */ import net.minecraft.server.v1_16_R2.ProtoChunk;
/*      */ import net.minecraft.server.v1_16_R2.RegionLimitedWorldAccess;
/*      */ import net.minecraft.server.v1_16_R2.RegistryMaterials;
/*      */ import net.minecraft.server.v1_16_R2.RegistryReadOps;
/*      */ import net.minecraft.server.v1_16_R2.ResourceKey;
/*      */ import net.minecraft.server.v1_16_R2.ServerCommand;
/*      */ import net.minecraft.server.v1_16_R2.Tags;
/*      */ import net.minecraft.server.v1_16_R2.TicketType;
/*      */ import net.minecraft.server.v1_16_R2.Vec3D;
/*      */ import net.minecraft.server.v1_16_R2.VillageSiege;
/*      */ import net.minecraft.server.v1_16_R2.World;
/*      */ import net.minecraft.server.v1_16_R2.WorldDataServer;
/*      */ import net.minecraft.server.v1_16_R2.WorldDimension;
/*      */ import net.minecraft.server.v1_16_R2.WorldMap;
/*      */ import net.minecraft.server.v1_16_R2.WorldNBTStorage;
/*      */ import net.minecraft.server.v1_16_R2.WorldServer;
/*      */ import net.minecraft.server.v1_16_R2.WorldSettings;
/*      */ import org.apache.commons.lang.Validate;
/*      */ import org.bukkit.BanList;
/*      */ import org.bukkit.Bukkit;
/*      */ import org.bukkit.GameMode;
/*      */ import org.bukkit.Location;
/*      */ import org.bukkit.Material;
/*      */ import org.bukkit.NamespacedKey;
/*      */ import org.bukkit.OfflinePlayer;
/*      */ import org.bukkit.Server;
/*      */ import org.bukkit.StructureType;
/*      */ import org.bukkit.Tag;
/*      */ import org.bukkit.UnsafeValues;
/*      */ import org.bukkit.Warning;
/*      */ import org.bukkit.World;
/*      */ import org.bukkit.WorldCreator;
/*      */ import org.bukkit.advancement.Advancement;
/*      */ import org.bukkit.block.data.BlockData;
/*      */ import org.bukkit.boss.BarColor;
/*      */ import org.bukkit.boss.BarFlag;
/*      */ import org.bukkit.boss.BarStyle;
/*      */ import org.bukkit.boss.BossBar;
/*      */ import org.bukkit.boss.KeyedBossBar;
/*      */ import org.bukkit.command.Command;
/*      */ import org.bukkit.command.CommandException;
/*      */ import org.bukkit.command.CommandMap;
/*      */ import org.bukkit.command.CommandSender;
/*      */ import org.bukkit.command.PluginCommand;
/*      */ import org.bukkit.command.SimpleCommandMap;
/*      */ import org.bukkit.configuration.Configuration;
/*      */ import org.bukkit.configuration.ConfigurationSection;
/*      */ import org.bukkit.configuration.file.YamlConfiguration;
/*      */ import org.bukkit.configuration.serialization.ConfigurationSerialization;
/*      */ import org.bukkit.conversations.Conversable;
/*      */ import org.bukkit.craftbukkit.Main;
/*      */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.StringUtils;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.boss.CraftKeyedBossbar;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.command.BukkitCommandWrapper;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.command.CraftCommandMap;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.command.VanillaCommandWrapper;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.generator.CraftChunkData;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.help.SimpleHelpMap;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftBlastingRecipe;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftCampfireRecipe;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftFurnaceRecipe;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemFactory;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftRecipe;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftShapedRecipe;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftShapelessRecipe;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftSmithingRecipe;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftSmokingRecipe;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftStonecuttingRecipe;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.inventory.util.CraftInventoryCreator;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.map.CraftMapView;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.metadata.EntityMetadataStore;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.metadata.PlayerMetadataStore;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.metadata.WorldMetadataStore;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.scheduler.CraftScheduler;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.scoreboard.CraftScoreboardManager;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.tag.CraftBlockTag;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.tag.CraftFluidTag;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.tag.CraftItemTag;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftIconCache;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftMagicNumbers;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftNamespacedKey;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.util.Versioning;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.util.Waitable;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.util.permissions.CraftDefaultPermissions;
/*      */ import org.bukkit.enchantments.Enchantment;
/*      */ import org.bukkit.entity.Entity;
/*      */ import org.bukkit.entity.Player;
/*      */ import org.bukkit.event.Event;
/*      */ import org.bukkit.event.command.UnknownCommandEvent;
/*      */ import org.bukkit.event.inventory.InventoryType;
/*      */ import org.bukkit.event.player.PlayerChatTabCompleteEvent;
/*      */ import org.bukkit.event.server.BroadcastMessageEvent;
/*      */ import org.bukkit.event.server.ServerLoadEvent;
/*      */ import org.bukkit.event.server.TabCompleteEvent;
/*      */ import org.bukkit.event.world.WorldUnloadEvent;
/*      */ import org.bukkit.generator.ChunkGenerator;
/*      */ import org.bukkit.help.HelpMap;
/*      */ import org.bukkit.inventory.BlastingRecipe;
/*      */ import org.bukkit.inventory.CampfireRecipe;
/*      */ import org.bukkit.inventory.Inventory;
/*      */ import org.bukkit.inventory.InventoryHolder;
/*      */ import org.bukkit.inventory.ItemFactory;
/*      */ import org.bukkit.inventory.ItemStack;
/*      */ import org.bukkit.inventory.Merchant;
/*      */ import org.bukkit.inventory.Recipe;
/*      */ import org.bukkit.inventory.SmokingRecipe;
/*      */ import org.bukkit.inventory.StonecuttingRecipe;
/*      */ import org.bukkit.map.MapView;
/*      */ import org.bukkit.permissions.Permissible;
/*      */ import org.bukkit.permissions.Permission;
/*      */ import org.bukkit.plugin.Plugin;
/*      */ import org.bukkit.plugin.PluginLoadOrder;
/*      */ import org.bukkit.plugin.PluginManager;
/*      */ import org.bukkit.plugin.ServicesManager;
/*      */ import org.bukkit.plugin.SimplePluginManager;
/*      */ import org.bukkit.plugin.messaging.Messenger;
/*      */ import org.bukkit.plugin.messaging.StandardMessenger;
/*      */ import org.bukkit.potion.PotionBrewer;
/*      */ import org.bukkit.scheduler.BukkitScheduler;
/*      */ import org.bukkit.scheduler.BukkitWorker;
/*      */ import org.bukkit.scoreboard.ScoreboardManager;
/*      */ import org.bukkit.util.CachedServerIcon;
/*      */ import org.bukkit.util.StringUtil;
/*      */ import org.bukkit.util.permissions.DefaultPermissions;
/*      */ import org.spigotmc.AsyncCatcher;
/*      */ import org.spigotmc.RestartCommand;
/*      */ import org.spigotmc.SpigotConfig;
/*      */ import org.spigotmc.WatchdogThread;
/*      */ import org.yaml.snakeyaml.Yaml;
/*      */ import org.yaml.snakeyaml.error.MarkedYAMLException;
/*      */ 
/*      */ public final class CraftServer implements Server {
/*  235 */   private final String serverName = "Tuinity";
/*      */   private final String serverVersion;
/*  237 */   private final String bukkitVersion = Versioning.getBukkitVersion();
/*  238 */   private final Logger logger = Logger.getLogger("Minecraft");
/*  239 */   private final ServicesManager servicesManager = (ServicesManager)new SimpleServicesManager();
/*  240 */   private final CraftScheduler scheduler = new CraftScheduler();
/*  241 */   private final CraftCommandMap commandMap = new CraftCommandMap(this);
/*  242 */   private final SimpleHelpMap helpMap = new SimpleHelpMap(this);
/*  243 */   private final StandardMessenger messenger = new StandardMessenger();
/*  244 */   private final SimplePluginManager pluginManager = new SimplePluginManager(this, (SimpleCommandMap)this.commandMap);
/*      */   protected final DedicatedServer console;
/*      */   protected final DedicatedPlayerList playerList;
/*  247 */   private final Map<String, World> worlds = new LinkedHashMap<>();
/*      */   private YamlConfiguration configuration;
/*      */   private YamlConfiguration commandsConfiguration;
/*  250 */   private final Yaml yaml = new Yaml((BaseConstructor)new SafeConstructor());
/*  251 */   private final Map<UUID, OfflinePlayer> offlinePlayers = (new MapMaker()).weakValues().makeMap();
/*  252 */   private final EntityMetadataStore entityMetadata = new EntityMetadataStore();
/*  253 */   private final PlayerMetadataStore playerMetadata = new PlayerMetadataStore();
/*  254 */   private final WorldMetadataStore worldMetadata = new WorldMetadataStore();
/*  255 */   private int monsterSpawn = -1;
/*  256 */   private int animalSpawn = -1;
/*  257 */   private int waterAnimalSpawn = -1;
/*  258 */   private int waterAmbientSpawn = -1;
/*  259 */   private int ambientSpawn = -1;
/*      */   private File container;
/*  261 */   private Warning.WarningState warningState = Warning.WarningState.DEFAULT; public String minimumAPI; public CraftScoreboardManager scoreboardManager;
/*      */   public boolean playerCommandState;
/*      */   private boolean printSaveWarning;
/*      */   private CraftIconCache icon;
/*      */   private boolean overrideAllCommandBlockCommands = false;
/*      */   public boolean ignoreVanillaPermissions = false;
/*      */   private final List<CraftPlayer> playerView;
/*      */   public int reloadCount;
/*      */   public static Exception excessiveVelEx;
/*      */   private final Server.Spigot spigot;
/*      */   private MobGoals mobGoals;
/*      */   
/*      */   static {
/*  274 */     ConfigurationSerialization.registerClass(CraftOfflinePlayer.class);
/*  275 */     CraftItemFactory.instance();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getCommandBlockOverride(String command) {
/*  356 */     return (this.overrideAllCommandBlockCommands || this.commandsConfiguration.getStringList("command-block-overrides").contains(command));
/*      */   }
/*      */   
/*      */   private File getConfigFile() {
/*  360 */     return (File)this.console.options.valueOf("bukkit-settings");
/*      */   }
/*      */   
/*      */   private File getCommandsConfigFile() {
/*  364 */     return (File)this.console.options.valueOf("commands-settings");
/*      */   }
/*      */   
/*      */   private void saveConfig() {
/*      */     try {
/*  369 */       this.configuration.save(getConfigFile());
/*  370 */     } catch (IOException ex) {
/*  371 */       Logger.getLogger(CraftServer.class.getName()).log(Level.SEVERE, "Could not save " + getConfigFile(), ex);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void saveCommandsConfig() {
/*      */     try {
/*  377 */       this.commandsConfiguration.save(getCommandsConfigFile());
/*  378 */     } catch (IOException ex) {
/*  379 */       Logger.getLogger(CraftServer.class.getName()).log(Level.SEVERE, "Could not save " + getCommandsConfigFile(), ex);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void loadPlugins() {
/*  384 */     this.pluginManager.registerInterface(JavaPluginLoader.class);
/*      */     
/*  386 */     File pluginFolder = (File)this.console.options.valueOf("plugins");
/*      */     
/*  388 */     if (pluginFolder.exists()) {
/*  389 */       Plugin[] plugins = this.pluginManager.loadPlugins(pluginFolder);
/*  390 */       for (Plugin plugin : plugins) {
/*      */         try {
/*  392 */           String message = String.format("Loading %s", new Object[] { plugin.getDescription().getFullName() });
/*  393 */           plugin.getLogger().info(message);
/*  394 */           plugin.onLoad();
/*  395 */         } catch (Throwable ex) {
/*  396 */           Logger.getLogger(CraftServer.class.getName()).log(Level.SEVERE, ex.getMessage() + " initializing " + plugin.getDescription().getFullName() + " (Is it up to date?)", ex);
/*      */         } 
/*      */       } 
/*      */     } else {
/*  400 */       pluginFolder.mkdir();
/*      */     } 
/*      */   }
/*      */   
/*      */   public void enablePlugins(PluginLoadOrder type) {
/*  405 */     if (type == PluginLoadOrder.STARTUP) {
/*  406 */       this.helpMap.clear();
/*  407 */       this.helpMap.initializeGeneralTopics();
/*  408 */       if (PaperConfig.loadPermsBeforePlugins) loadCustomPermissions();
/*      */     
/*      */     } 
/*  411 */     Plugin[] plugins = this.pluginManager.getPlugins();
/*      */     
/*  413 */     for (Plugin plugin : plugins) {
/*  414 */       if (!plugin.isEnabled() && plugin.getDescription().getLoad() == type) {
/*  415 */         enablePlugin(plugin);
/*      */       }
/*      */     } 
/*      */     
/*  419 */     if (type == PluginLoadOrder.POSTWORLD) {
/*      */       
/*  421 */       setVanillaCommands(true);
/*  422 */       this.commandMap.setFallbackCommands();
/*  423 */       setVanillaCommands(false);
/*      */       
/*  425 */       this.commandMap.registerServerAliases();
/*  426 */       DefaultPermissions.registerCorePermissions();
/*  427 */       CraftDefaultPermissions.registerCorePermissions();
/*  428 */       if (!PaperConfig.loadPermsBeforePlugins) loadCustomPermissions(); 
/*  429 */       this.helpMap.initializeCommands();
/*  430 */       syncCommands();
/*      */     } 
/*      */   }
/*      */   
/*      */   public void disablePlugins() {
/*  435 */     this.pluginManager.disablePlugins();
/*      */   }
/*      */   
/*      */   private void setVanillaCommands(boolean first) {
/*  439 */     CommandDispatcher dispatcher = this.console.vanillaCommandDispatcher;
/*      */ 
/*      */     
/*  442 */     for (CommandNode<CommandListenerWrapper> cmd : (Iterable<CommandNode<CommandListenerWrapper>>)dispatcher.a().getRoot().getChildren()) {
/*      */       
/*  444 */       VanillaCommandWrapper wrapper = new VanillaCommandWrapper(dispatcher, cmd);
/*  445 */       if (SpigotConfig.replaceCommands.contains(wrapper.getName())) {
/*  446 */         if (first)
/*  447 */           this.commandMap.register("minecraft", (Command)wrapper);  continue;
/*      */       } 
/*  449 */       if (!first) {
/*  450 */         this.commandMap.register("minecraft", (Command)wrapper);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void syncCommands() {
/*  458 */     CommandDispatcher dispatcher = this.console.dataPackResources.commandDispatcher = new CommandDispatcher();
/*      */ 
/*      */     
/*  461 */     for (Map.Entry<String, Command> entry : (Iterable<Map.Entry<String, Command>>)this.commandMap.getKnownCommands().entrySet()) {
/*  462 */       String label = entry.getKey();
/*  463 */       Command command = entry.getValue();
/*      */       
/*  465 */       if (command instanceof VanillaCommandWrapper) {
/*  466 */         LiteralCommandNode<CommandListenerWrapper> node = (LiteralCommandNode<CommandListenerWrapper>)((VanillaCommandWrapper)command).vanillaCommand;
/*  467 */         if (!node.getLiteral().equals(label)) {
/*  468 */           LiteralCommandNode<CommandListenerWrapper> clone = new LiteralCommandNode(label, node.getCommand(), node.getRequirement(), node.getRedirect(), node.getRedirectModifier(), node.isFork());
/*      */           
/*  470 */           for (CommandNode<CommandListenerWrapper> child : (Iterable<CommandNode<CommandListenerWrapper>>)node.getChildren()) {
/*  471 */             clone.addChild(child);
/*      */           }
/*  473 */           node = clone;
/*      */         } 
/*      */         
/*  476 */         dispatcher.a().getRoot().addChild((CommandNode)node); continue;
/*      */       } 
/*  478 */       (new BukkitCommandWrapper(this, entry.getValue())).register(dispatcher.a(), label);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  483 */     for (EntityPlayer player : (getHandle()).players) {
/*  484 */       dispatcher.a(player);
/*      */     }
/*      */   }
/*      */   
/*      */   private void enablePlugin(Plugin plugin) {
/*      */     try {
/*  490 */       List<Permission> perms = plugin.getDescription().getPermissions();
/*      */       
/*  492 */       for (Permission perm : perms) {
/*      */         try {
/*  494 */           this.pluginManager.addPermission(perm, false);
/*  495 */         } catch (IllegalArgumentException ex) {
/*  496 */           getLogger().log(Level.WARNING, "Plugin " + plugin.getDescription().getFullName() + " tried to register permission '" + perm.getName() + "' but it's already registered", ex);
/*      */         } 
/*      */       } 
/*  499 */       this.pluginManager.dirtyPermissibles();
/*      */       
/*  501 */       this.pluginManager.enablePlugin(plugin);
/*  502 */     } catch (Throwable ex) {
/*  503 */       Logger.getLogger(CraftServer.class.getName()).log(Level.SEVERE, ex.getMessage() + " loading " + plugin.getDescription().getFullName() + " (Is it up to date?)", ex);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public String getName() {
/*  509 */     return "Tuinity";
/*      */   }
/*      */ 
/*      */   
/*      */   public String getVersion() {
/*  514 */     return this.serverVersion + " (MC: " + this.console.getVersion() + ")";
/*      */   }
/*      */ 
/*      */   
/*      */   public String getBukkitVersion() {
/*  519 */     return this.bukkitVersion;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getMinecraftVersion() {
/*  525 */     return this.console.getVersion();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public List<CraftPlayer> getOnlinePlayers() {
/*  531 */     return this.playerView;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public Player getPlayer(String name) {
/*  537 */     Validate.notNull(name, "Name cannot be null");
/*      */     
/*  539 */     Player found = getPlayerExact(name);
/*      */     
/*  541 */     if (found != null) {
/*  542 */       return found;
/*      */     }
/*      */     
/*  545 */     String lowerName = name.toLowerCase(Locale.ENGLISH);
/*  546 */     int delta = Integer.MAX_VALUE;
/*  547 */     for (Player player : getOnlinePlayers()) {
/*  548 */       if (player.getName().toLowerCase(Locale.ENGLISH).startsWith(lowerName)) {
/*  549 */         int curDelta = Math.abs(player.getName().length() - lowerName.length());
/*  550 */         if (curDelta < delta) {
/*  551 */           found = player;
/*  552 */           delta = curDelta;
/*      */         } 
/*  554 */         if (curDelta == 0)
/*      */           break; 
/*      */       } 
/*  557 */     }  return found;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public Player getPlayerExact(String name) {
/*  563 */     Validate.notNull(name, "Name cannot be null");
/*      */     
/*  565 */     EntityPlayer player = this.playerList.getPlayer(name);
/*  566 */     return (player != null) ? (Player)player.getBukkitEntity() : null;
/*      */   }
/*      */ 
/*      */   
/*      */   public Player getPlayer(UUID id) {
/*  571 */     EntityPlayer player = this.playerList.getPlayer(id);
/*      */     
/*  573 */     if (player != null) {
/*  574 */       return (Player)player.getBukkitEntity();
/*      */     }
/*      */     
/*  577 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public int broadcastMessage(String message) {
/*  582 */     return broadcast(message, "bukkit.broadcast.user");
/*      */   }
/*      */   
/*      */   public Player getPlayer(EntityPlayer entity) {
/*  586 */     return (Player)entity.getBukkitEntity();
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public List<Player> matchPlayer(String partialName) {
/*  592 */     Validate.notNull(partialName, "PartialName cannot be null");
/*      */     
/*  594 */     List<Player> matchedPlayers = new ArrayList<>();
/*      */     
/*  596 */     for (Player iterPlayer : getOnlinePlayers()) {
/*  597 */       String iterPlayerName = iterPlayer.getName();
/*      */       
/*  599 */       if (partialName.equalsIgnoreCase(iterPlayerName)) {
/*      */         
/*  601 */         matchedPlayers.clear();
/*  602 */         matchedPlayers.add(iterPlayer);
/*      */         break;
/*      */       } 
/*  605 */       if (iterPlayerName.toLowerCase(Locale.ENGLISH).contains(partialName.toLowerCase(Locale.ENGLISH)))
/*      */       {
/*  607 */         matchedPlayers.add(iterPlayer);
/*      */       }
/*      */     } 
/*      */     
/*  611 */     return matchedPlayers;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMaxPlayers() {
/*  616 */     return this.playerList.getMaxPlayers();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMaxPlayers(int maxPlayers) {
/*  622 */     this.playerList.setMaxPlayers(maxPlayers);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPort() {
/*  630 */     return getServer().getPort();
/*      */   }
/*      */ 
/*      */   
/*      */   public int getViewDistance() {
/*  635 */     return (getProperties()).viewDistance;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getIp() {
/*  640 */     return getServer().getServerIp();
/*      */   }
/*      */ 
/*      */   
/*      */   public String getWorldType() {
/*  645 */     return (getProperties()).properties.getProperty("level-type");
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getGenerateStructures() {
/*  650 */     return (getProperties()).generatorSettings.shouldGenerateMapFeatures();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getAllowEnd() {
/*  655 */     return this.configuration.getBoolean("settings.allow-end");
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getAllowNether() {
/*  660 */     return getServer().getAllowNether();
/*      */   }
/*      */   
/*      */   public boolean getWarnOnOverload() {
/*  664 */     return this.configuration.getBoolean("settings.warn-on-overload");
/*      */   }
/*      */   
/*      */   public boolean getQueryPlugins() {
/*  668 */     return this.configuration.getBoolean("settings.query-plugins");
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasWhitelist() {
/*  673 */     return ((Boolean)(getProperties()).whiteList.get()).booleanValue();
/*      */   }
/*      */ 
/*      */   
/*      */   private DedicatedServerProperties getProperties() {
/*  678 */     return this.console.getDedicatedServerProperties();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getUpdateFolder() {
/*  684 */     return this.configuration.getString("settings.update-folder", "update");
/*      */   }
/*      */ 
/*      */   
/*      */   public File getUpdateFolderFile() {
/*  689 */     return new File((File)this.console.options.valueOf("plugins"), this.configuration.getString("settings.update-folder", "update"));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public long getConnectionThrottle() {
/*  695 */     if (SpigotConfig.bungee || PaperConfig.velocitySupport) {
/*  696 */       return -1L;
/*      */     }
/*  698 */     return this.configuration.getInt("settings.connection-throttle");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getTicksPerAnimalSpawns() {
/*  705 */     return this.configuration.getInt("ticks-per.animal-spawns");
/*      */   }
/*      */ 
/*      */   
/*      */   public int getTicksPerMonsterSpawns() {
/*  710 */     return this.configuration.getInt("ticks-per.monster-spawns");
/*      */   }
/*      */ 
/*      */   
/*      */   public int getTicksPerWaterSpawns() {
/*  715 */     return this.configuration.getInt("ticks-per.water-spawns");
/*      */   }
/*      */ 
/*      */   
/*      */   public int getTicksPerWaterAmbientSpawns() {
/*  720 */     return this.configuration.getInt("ticks-per.water-ambient-spawns");
/*      */   }
/*      */ 
/*      */   
/*      */   public int getTicksPerAmbientSpawns() {
/*  725 */     return this.configuration.getInt("ticks-per.ambient-spawns");
/*      */   }
/*      */ 
/*      */   
/*      */   public PluginManager getPluginManager() {
/*  730 */     return (PluginManager)this.pluginManager;
/*      */   }
/*      */ 
/*      */   
/*      */   public CraftScheduler getScheduler() {
/*  735 */     return this.scheduler;
/*      */   }
/*      */ 
/*      */   
/*      */   public ServicesManager getServicesManager() {
/*  740 */     return this.servicesManager;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<World> getWorlds() {
/*  745 */     return new ArrayList<>(this.worlds.values());
/*      */   }
/*      */   
/*      */   public DedicatedPlayerList getHandle() {
/*  749 */     return this.playerList;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean dispatchServerCommand(CommandSender sender, ServerCommand serverCommand) {
/*  754 */     if (sender instanceof Conversable) {
/*  755 */       Conversable conversable = (Conversable)sender;
/*      */       
/*  757 */       if (conversable.isConversing()) {
/*  758 */         conversable.acceptConversationInput(serverCommand.command);
/*  759 */         return true;
/*      */       } 
/*      */     } 
/*      */     try {
/*  763 */       this.playerCommandState = true;
/*  764 */       return dispatchCommand(sender, serverCommand.command);
/*  765 */     } catch (Exception ex) {
/*  766 */       getLogger().log(Level.WARNING, "Unexpected exception while parsing console command \"" + serverCommand.command + '"', ex);
/*  767 */       return false;
/*      */     } finally {
/*  769 */       this.playerCommandState = false;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean dispatchCommand(CommandSender sender, String commandLine) {
/*  775 */     Validate.notNull(sender, "Sender cannot be null");
/*  776 */     Validate.notNull(commandLine, "CommandLine cannot be null");
/*  777 */     AsyncCatcher.catchOp("command dispatch");
/*      */ 
/*      */     
/*  780 */     if (!AsyncCatcher.shuttingDown && !Bukkit.isPrimaryThread()) {
/*  781 */       final CommandSender fSender = sender;
/*  782 */       final String fCommandLine = commandLine;
/*  783 */       Bukkit.getLogger().log(Level.SEVERE, "Command Dispatched Async: " + commandLine);
/*  784 */       Bukkit.getLogger().log(Level.SEVERE, "Please notify author of plugin causing this execution to fix this bug! see: http://bit.ly/1oSiM6C", new Throwable());
/*  785 */       Waitable<Boolean> wait = new Waitable<Boolean>()
/*      */         {
/*      */           protected Boolean evaluate() {
/*  788 */             return Boolean.valueOf(CraftServer.this.dispatchCommand(fSender, fCommandLine));
/*      */           }
/*      */         };
/*  791 */       (MinecraftServer.getServer()).processQueue.add(wait);
/*      */       try {
/*  793 */         return ((Boolean)wait.get()).booleanValue();
/*  794 */       } catch (InterruptedException e) {
/*  795 */         Thread.currentThread().interrupt();
/*  796 */       } catch (Exception e) {
/*  797 */         throw new RuntimeException("Exception processing dispatch command", e.getCause());
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  802 */     if (this.commandMap.dispatch(sender, commandLine)) {
/*  803 */       return true;
/*      */     }
/*      */ 
/*      */     
/*  807 */     if (!SpigotConfig.unknownCommandMessage.isEmpty()) {
/*      */       
/*  809 */       UnknownCommandEvent event = new UnknownCommandEvent(sender, commandLine, SpigotConfig.unknownCommandMessage);
/*  810 */       Bukkit.getServer().getPluginManager().callEvent((Event)event);
/*  811 */       if (StringUtils.isNotEmpty(event.getMessage())) {
/*  812 */         sender.sendMessage(event.getMessage());
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  818 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void reload() {
/*  823 */     WatchdogThread.hasStarted = false;
/*  824 */     this.reloadCount++;
/*  825 */     this.configuration = YamlConfiguration.loadConfiguration(getConfigFile());
/*  826 */     this.commandsConfiguration = YamlConfiguration.loadConfiguration(getCommandsConfigFile());
/*      */     
/*  828 */     this.console.propertyManager = new DedicatedServerSettings(this.console.getCustomRegistry(), this.console.options);
/*  829 */     DedicatedServerProperties config = this.console.propertyManager.getProperties();
/*      */     
/*  831 */     this.console.setPVP(config.pvp);
/*  832 */     this.console.setAllowFlight(config.allowFlight);
/*  833 */     this.console.setMotd(config.motd);
/*  834 */     this.monsterSpawn = this.configuration.getInt("spawn-limits.monsters");
/*  835 */     this.animalSpawn = this.configuration.getInt("spawn-limits.animals");
/*  836 */     this.waterAnimalSpawn = this.configuration.getInt("spawn-limits.water-animals");
/*  837 */     this.waterAmbientSpawn = this.configuration.getInt("spawn-limits.water-ambient");
/*  838 */     this.ambientSpawn = this.configuration.getInt("spawn-limits.ambient");
/*  839 */     this.warningState = Warning.WarningState.value(this.configuration.getString("settings.deprecated-verbose"));
/*  840 */     TicketType.PLUGIN.loadPeriod = Math.min(20, this.configuration.getInt("chunk-gc.period-in-ticks"));
/*  841 */     this.minimumAPI = this.configuration.getString("settings.minimum-api");
/*  842 */     this.printSaveWarning = false;
/*  843 */     this.console.autosavePeriod = this.configuration.getInt("ticks-per.autosave");
/*  844 */     loadIcon();
/*      */     
/*      */     try {
/*  847 */       this.playerList.getIPBans().load();
/*  848 */     } catch (IOException ex) {
/*  849 */       this.logger.log(Level.WARNING, "Failed to load banned-ips.json, " + ex.getMessage());
/*      */     } 
/*      */     try {
/*  852 */       this.playerList.getProfileBans().load();
/*  853 */     } catch (IOException ex) {
/*  854 */       this.logger.log(Level.WARNING, "Failed to load banned-players.json, " + ex.getMessage());
/*      */     } 
/*      */     
/*  857 */     SpigotConfig.init((File)this.console.options.valueOf("spigot-settings"));
/*  858 */     PaperConfig.init((File)this.console.options.valueOf("paper-settings"));
/*  859 */     TuinityConfig.init((File)this.console.options.valueOf("tuinity-settings"));
/*  860 */     for (WorldServer world : this.console.getWorlds()) {
/*  861 */       world.worldDataServer.setDifficulty(config.difficulty);
/*  862 */       world.setSpawnFlags(config.spawnMonsters, config.spawnAnimals);
/*  863 */       if (getTicksPerAnimalSpawns() < 0) {
/*  864 */         world.ticksPerAnimalSpawns = 400L;
/*      */       } else {
/*  866 */         world.ticksPerAnimalSpawns = getTicksPerAnimalSpawns();
/*      */       } 
/*      */       
/*  869 */       if (getTicksPerMonsterSpawns() < 0) {
/*  870 */         world.ticksPerMonsterSpawns = 1L;
/*      */       } else {
/*  872 */         world.ticksPerMonsterSpawns = getTicksPerMonsterSpawns();
/*      */       } 
/*      */       
/*  875 */       if (getTicksPerWaterSpawns() < 0) {
/*  876 */         world.ticksPerWaterSpawns = 1L;
/*      */       } else {
/*  878 */         world.ticksPerWaterSpawns = getTicksPerWaterSpawns();
/*      */       } 
/*      */       
/*  881 */       if (getTicksPerWaterAmbientSpawns() < 0) {
/*  882 */         world.ticksPerWaterAmbientSpawns = 1L;
/*      */       } else {
/*  884 */         world.ticksPerWaterAmbientSpawns = getTicksPerWaterAmbientSpawns();
/*      */       } 
/*      */       
/*  887 */       if (getTicksPerAmbientSpawns() < 0) {
/*  888 */         world.ticksPerAmbientSpawns = 1L;
/*      */       } else {
/*  890 */         world.ticksPerAmbientSpawns = getTicksPerAmbientSpawns();
/*      */       } 
/*  892 */       world.spigotConfig.init();
/*  893 */       world.paperConfig.init();
/*  894 */       world.tuinityConfig.init();
/*      */     } 
/*      */     
/*  897 */     Plugin[] pluginClone = (Plugin[])this.pluginManager.getPlugins().clone();
/*  898 */     this.pluginManager.clearPlugins();
/*  899 */     this.commandMap.clearCommands();
/*      */ 
/*      */     
/*  902 */     for (Plugin plugin : pluginClone) {
/*  903 */       this.entityMetadata.removeAll(plugin);
/*  904 */       this.worldMetadata.removeAll(plugin);
/*  905 */       this.playerMetadata.removeAll(plugin);
/*      */     } 
/*      */ 
/*      */     
/*  909 */     resetRecipes();
/*  910 */     reloadData();
/*  911 */     SpigotConfig.registerCommands();
/*  912 */     PaperConfig.registerCommands();
/*  913 */     this.overrideAllCommandBlockCommands = this.commandsConfiguration.getStringList("command-block-overrides").contains("*");
/*  914 */     this.ignoreVanillaPermissions = this.commandsConfiguration.getBoolean("ignore-vanilla-permissions");
/*      */     
/*  916 */     int pollCount = 0;
/*      */ 
/*      */     
/*  919 */     while (pollCount < 50 && getScheduler().getActiveWorkers().size() > 0) {
/*      */       try {
/*  921 */         Thread.sleep(50L);
/*  922 */       } catch (InterruptedException interruptedException) {}
/*  923 */       pollCount++;
/*      */     } 
/*      */     
/*  926 */     List<BukkitWorker> overdueWorkers = getScheduler().getActiveWorkers();
/*  927 */     for (BukkitWorker worker : overdueWorkers) {
/*  928 */       Plugin plugin = worker.getOwner();
/*  929 */       String author = "<NoAuthorGiven>";
/*  930 */       if (plugin.getDescription().getAuthors().size() > 0) {
/*  931 */         author = plugin.getDescription().getAuthors().get(0);
/*      */       }
/*  933 */       getLogger().log(Level.SEVERE, String.format("Nag author: '%s' of '%s' about the following: %s", new Object[] { author, plugin
/*      */ 
/*      */               
/*  936 */               .getDescription().getName(), "This plugin is not properly shutting down its async tasks when it is being reloaded.  This may cause conflicts with the newly loaded version of the plugin" }));
/*      */     } 
/*      */ 
/*      */     
/*  940 */     loadPlugins();
/*  941 */     enablePlugins(PluginLoadOrder.STARTUP);
/*  942 */     enablePlugins(PluginLoadOrder.POSTWORLD);
/*  943 */     getPluginManager().callEvent((Event)new ServerLoadEvent(ServerLoadEvent.LoadType.RELOAD));
/*  944 */     WatchdogThread.hasStarted = true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void waitForAsyncTasksShutdown() {
/*  949 */     int pollCount = 0;
/*      */ 
/*      */     
/*  952 */     while (pollCount < 50 && getScheduler().getActiveWorkers().size() > 0) {
/*      */       try {
/*  954 */         Thread.sleep(100L);
/*  955 */       } catch (InterruptedException interruptedException) {}
/*  956 */       pollCount++;
/*      */     } 
/*      */     
/*  959 */     List<BukkitWorker> overdueWorkers = getScheduler().getActiveWorkers();
/*  960 */     for (BukkitWorker worker : overdueWorkers) {
/*  961 */       Plugin plugin = worker.getOwner();
/*  962 */       String author = "<NoAuthorGiven>";
/*  963 */       if (plugin.getDescription().getAuthors().size() > 0) {
/*  964 */         author = plugin.getDescription().getAuthors().get(0);
/*      */       }
/*  966 */       getLogger().log(Level.SEVERE, String.format("Nag author: '%s' of '%s' about the following: %s", new Object[] { author, plugin
/*      */ 
/*      */               
/*  969 */               .getDescription().getName(), "This plugin is not properly shutting down its async tasks when it is being shut down. This task may throw errors during the final shutdown logs and might not complete before process dies." }));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void reloadData() {
/*  978 */     CommandReload.reload((MinecraftServer)this.console);
/*      */   }
/*      */   
/*      */   private void loadIcon() {
/*  982 */     this.icon = new CraftIconCache(null);
/*      */     try {
/*  984 */       File file = new File(new File("."), "server-icon.png");
/*  985 */       if (file.isFile()) {
/*  986 */         this.icon = loadServerIcon0(file);
/*      */       }
/*  988 */     } catch (Exception ex) {
/*  989 */       getLogger().log(Level.WARNING, "Couldn't load server icon", ex);
/*      */     } 
/*      */   }
/*      */   private void loadCustomPermissions() {
/*      */     FileInputStream stream;
/*      */     Map<String, Map<String, Object>> perms;
/*  995 */     File file = new File(this.configuration.getString("settings.permissions-file"));
/*      */ 
/*      */     
/*      */     try {
/*  999 */       stream = new FileInputStream(file);
/* 1000 */     } catch (FileNotFoundException ex) {
/*      */       try {
/*      */         return;
/*      */       } finally {
/* 1004 */         Exception exception = null;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1011 */       perms = (Map<String, Map<String, Object>>)this.yaml.load(stream);
/* 1012 */     } catch (MarkedYAMLException ex) {
/* 1013 */       getLogger().log(Level.WARNING, "Server permissions file " + file + " is not valid YAML: " + ex.toString());
/*      */       return;
/* 1015 */     } catch (Throwable ex) {
/* 1016 */       getLogger().log(Level.WARNING, "Server permissions file " + file + " is not valid YAML.", ex);
/*      */       return;
/*      */     } finally {
/*      */       try {
/* 1020 */         stream.close();
/* 1021 */       } catch (IOException iOException) {}
/*      */     } 
/*      */     
/* 1024 */     if (perms == null) {
/* 1025 */       getLogger().log(Level.INFO, "Server permissions file " + file + " is empty, ignoring it");
/*      */       
/*      */       return;
/*      */     } 
/* 1029 */     List<Permission> permsList = Permission.loadPermissions(perms, "Permission node '%s' in " + file + " is invalid", Permission.DEFAULT_PERMISSION);
/*      */     
/* 1031 */     for (Permission perm : permsList) {
/*      */       try {
/* 1033 */         this.pluginManager.addPermission(perm);
/* 1034 */       } catch (IllegalArgumentException ex) {
/* 1035 */         getLogger().log(Level.SEVERE, "Permission in " + file + " was already defined", ex);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public String toString() {
/* 1042 */     return "CraftServer{serverName=Tuinity,serverVersion=" + this.serverVersion + ",minecraftVersion=" + this.console.getVersion() + '}';
/*      */   }
/*      */   
/*      */   public World createWorld(String name, World.Environment environment) {
/* 1046 */     return WorldCreator.name(name).environment(environment).createWorld();
/*      */   }
/*      */   
/*      */   public World createWorld(String name, World.Environment environment, long seed) {
/* 1050 */     return WorldCreator.name(name).environment(environment).seed(seed).createWorld();
/*      */   }
/*      */   
/*      */   public World createWorld(String name, World.Environment environment, ChunkGenerator generator) {
/* 1054 */     return WorldCreator.name(name).environment(environment).generator(generator).createWorld();
/*      */   }
/*      */   
/*      */   public World createWorld(String name, World.Environment environment, long seed, ChunkGenerator generator) {
/* 1058 */     return WorldCreator.name(name).environment(environment).seed(seed).generator(generator).createWorld(); } public World createWorld(WorldCreator creator) {
/*      */     ResourceKey<WorldDimension> actualDimension;
/*      */     Convertable.ConversionSession worldSession;
/*      */     DimensionManager dimensionmanager;
/*      */     ChunkGenerator chunkgenerator;
/* 1063 */     Preconditions.checkState(!this.console.worldServer.isEmpty(), "Cannot create additional worlds on STARTUP");
/* 1064 */     Validate.notNull(creator, "Creator may not be null");
/*      */     
/* 1066 */     String name = creator.name();
/* 1067 */     ChunkGenerator generator = creator.generator();
/* 1068 */     File folder = new File(getWorldContainer(), name);
/* 1069 */     World world = getWorld(name);
/*      */     
/* 1071 */     if (world != null) {
/* 1072 */       return world;
/*      */     }
/*      */     
/* 1075 */     if (folder.exists() && !folder.isDirectory()) {
/* 1076 */       throw new IllegalArgumentException("File exists with the name '" + name + "' and isn't a folder");
/*      */     }
/*      */     
/* 1079 */     if (generator == null) {
/* 1080 */       generator = getGenerator(name);
/*      */     }
/*      */ 
/*      */     
/* 1084 */     switch (creator.environment()) {
/*      */       case IP:
/* 1086 */         actualDimension = WorldDimension.OVERWORLD;
/*      */         break;
/*      */       case NAME:
/* 1089 */         actualDimension = WorldDimension.THE_NETHER;
/*      */         break;
/*      */       case null:
/* 1092 */         actualDimension = WorldDimension.THE_END;
/*      */         break;
/*      */       default:
/* 1095 */         throw new IllegalArgumentException("Illegal dimension");
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/* 1100 */       worldSession = Convertable.a(getWorldContainer().toPath()).c(name, actualDimension);
/* 1101 */     } catch (IOException ex) {
/* 1102 */       throw new RuntimeException(ex);
/*      */     } 
/* 1104 */     MinecraftServer.convertWorld(worldSession);
/*      */     
/* 1106 */     boolean hardcore = creator.hardcore();
/*      */     
/* 1108 */     RegistryReadOps<NBTBase> registryreadops = RegistryReadOps.a((DynamicOps)DynamicOpsNBT.a, this.console.dataPackResources.h(), this.console.customRegistry);
/* 1109 */     WorldDataServer worlddata = (WorldDataServer)worldSession.a((DynamicOps)registryreadops, this.console.datapackconfiguration);
/*      */ 
/*      */ 
/*      */     
/* 1113 */     if (worlddata == null) {
/* 1114 */       Properties properties = new Properties();
/* 1115 */       properties.put("generator-settings", Objects.toString(creator.generatorSettings()));
/* 1116 */       properties.put("level-seed", Objects.toString(Long.valueOf(creator.seed())));
/* 1117 */       properties.put("generate-structures", Objects.toString(Boolean.valueOf(creator.generateStructures())));
/* 1118 */       properties.put("level-type", Objects.toString(creator.type().getName()));
/*      */       
/* 1120 */       GeneratorSettings generatorsettings = GeneratorSettings.a(this.console.getCustomRegistry(), properties);
/* 1121 */       WorldSettings worldSettings = new WorldSettings(name, EnumGamemode.getById(getDefaultGameMode().getValue()), hardcore, EnumDifficulty.EASY, false, new GameRules(), this.console.datapackconfiguration);
/* 1122 */       worlddata = new WorldDataServer(worldSettings, generatorsettings, Lifecycle.stable());
/*      */     } 
/* 1124 */     worlddata.checkName(name);
/* 1125 */     worlddata.a(this.console.getServerModName(), this.console.getModded().isPresent());
/*      */     
/* 1127 */     if (this.console.options.has("forceUpgrade")) {
/* 1128 */       Main.convertWorld(worldSession, DataConverterRegistry.a(), this.console.options.has("eraseCache"), () -> true, (ImmutableSet)worlddata
/*      */           
/* 1130 */           .getGeneratorSettings().d().d().stream().map(entry -> ResourceKey.a(IRegistry.K, ((ResourceKey)entry.getKey()).a()))
/*      */           
/* 1132 */           .collect(ImmutableSet.toImmutableSet()));
/*      */     }
/*      */     
/* 1135 */     long j = BiomeManager.a(creator.seed());
/* 1136 */     ImmutableList immutableList = ImmutableList.of(new MobSpawnerPhantom(), new MobSpawnerPatrol(), new MobSpawnerCat(), new VillageSiege(), new MobSpawnerTrader((IWorldDataServer)worlddata));
/* 1137 */     RegistryMaterials<WorldDimension> registrymaterials = worlddata.getGeneratorSettings().d();
/* 1138 */     WorldDimension worlddimension = (WorldDimension)registrymaterials.a(actualDimension);
/*      */ 
/*      */ 
/*      */     
/* 1142 */     if (worlddimension == null) {
/* 1143 */       dimensionmanager = (DimensionManager)this.console.customRegistry.a().d(DimensionManager.OVERWORLD);
/* 1144 */       ChunkGeneratorAbstract chunkGeneratorAbstract = GeneratorSettings.a((IRegistry)this.console.customRegistry.b(IRegistry.ay), (IRegistry)this.console.customRegistry.b(IRegistry.ar), (new Random()).nextLong());
/*      */     } else {
/* 1146 */       dimensionmanager = worlddimension.b();
/* 1147 */       chunkgenerator = worlddimension.c();
/*      */     } 
/*      */     
/* 1150 */     ResourceKey<World> worldKey = ResourceKey.a(IRegistry.L, new MinecraftKey(name.toLowerCase(Locale.ENGLISH)));
/*      */ 
/*      */     
/* 1153 */     WorldServer internal = new WorldServer((MinecraftServer)this.console, this.console.executorService, worldSession, (IWorldDataServer)worlddata, worldKey, dimensionmanager, (getServer()).worldLoadListenerFactory.create(11), chunkgenerator, worlddata.getGeneratorSettings().isDebugWorld(), j, (creator.environment() == World.Environment.NORMAL) ? (List)immutableList : (List)ImmutableList.of(), true, creator.environment(), generator);
/*      */     
/* 1155 */     if (!this.worlds.containsKey(name.toLowerCase(Locale.ENGLISH))) {
/* 1156 */       return null;
/*      */     }
/*      */     
/* 1159 */     this.console.initWorld(internal, (IWorldDataServer)worlddata, (SaveData)worlddata, worlddata.getGeneratorSettings());
/*      */     
/* 1161 */     internal.setSpawnFlags(true, true);
/* 1162 */     this.console.worldServer.put(internal.getDimensionKey(), internal);
/*      */     
/* 1164 */     this.pluginManager.callEvent((Event)new WorldInitEvent(internal.getWorld()));
/*      */     
/* 1166 */     getServer().loadSpawn((internal.getChunkProvider()).playerChunkMap.worldLoadListener, internal);
/*      */     
/* 1168 */     this.pluginManager.callEvent((Event)new WorldLoadEvent(internal.getWorld()));
/* 1169 */     return internal.getWorld();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean unloadWorld(String name, boolean save) {
/* 1174 */     return unloadWorld(getWorld(name), save);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean unloadWorld(World world, boolean save) {
/* 1179 */     if (world == null) {
/* 1180 */       return false;
/*      */     }
/*      */     
/* 1183 */     WorldServer handle = ((CraftWorld)world).getHandle();
/*      */     
/* 1185 */     if (!this.console.worldServer.containsKey(handle.getDimensionKey())) {
/* 1186 */       return false;
/*      */     }
/*      */     
/* 1189 */     if (handle.getDimensionKey() == World.OVERWORLD) {
/* 1190 */       return false;
/*      */     }
/*      */     
/* 1193 */     if (handle.getPlayers().size() > 0) {
/* 1194 */       return false;
/*      */     }
/*      */     
/* 1197 */     WorldUnloadEvent e = new WorldUnloadEvent(handle.getWorld());
/* 1198 */     this.pluginManager.callEvent((Event)e);
/*      */     
/* 1200 */     if (e.isCancelled()) {
/* 1201 */       return false;
/*      */     }
/*      */     
/*      */     try {
/* 1205 */       if (save) {
/* 1206 */         handle.save(null, true, true);
/*      */       }
/*      */       
/* 1209 */       handle.getChunkProvider().close(save);
/* 1210 */       handle.convertable.close();
/* 1211 */     } catch (Exception ex) {
/* 1212 */       getLogger().log(Level.SEVERE, (String)null, ex);
/*      */     } 
/*      */     
/* 1215 */     this.worlds.remove(world.getName().toLowerCase(Locale.ENGLISH));
/* 1216 */     this.console.worldServer.remove(handle.getDimensionKey());
/* 1217 */     return true;
/*      */   }
/*      */   
/*      */   public DedicatedServer getServer() {
/* 1221 */     return this.console;
/*      */   }
/*      */ 
/*      */   
/*      */   public World getWorld(String name) {
/* 1226 */     Validate.notNull(name, "Name cannot be null");
/*      */     
/* 1228 */     return this.worlds.get(name.toLowerCase(Locale.ENGLISH));
/*      */   }
/*      */ 
/*      */   
/*      */   public World getWorld(UUID uid) {
/* 1233 */     for (World world : this.worlds.values()) {
/* 1234 */       if (world.getUID().equals(uid)) {
/* 1235 */         return world;
/*      */       }
/*      */     } 
/* 1238 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public void addWorld(World world) {
/* 1243 */     if (getWorld(world.getUID()) != null) {
/* 1244 */       System.out.println("World " + world.getName() + " is a duplicate of another world and has been prevented from loading. Please delete the uid.dat file from " + world.getName() + "'s world directory if you want to be able to load the duplicate world.");
/*      */       return;
/*      */     } 
/* 1247 */     this.worlds.put(world.getName().toLowerCase(Locale.ENGLISH), world);
/*      */   }
/*      */ 
/*      */   
/*      */   public Logger getLogger() {
/* 1252 */     return this.logger;
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
/*      */   public PluginCommand getPluginCommand(String name) {
/* 1265 */     Command command = this.commandMap.getCommand(name);
/*      */     
/* 1267 */     if (command instanceof PluginCommand) {
/* 1268 */       return (PluginCommand)command;
/*      */     }
/* 1270 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void savePlayers() {
/* 1276 */     checkSaveState();
/* 1277 */     this.playerList.savePlayers();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean addRecipe(Recipe recipe) {
/*      */     CraftSmithingRecipe craftSmithingRecipe;
/* 1283 */     if (recipe instanceof CraftRecipe)
/* 1284 */     { CraftRecipe toAdd = (CraftRecipe)recipe; }
/*      */     
/* 1286 */     else if (recipe instanceof ShapedRecipe)
/* 1287 */     { CraftShapedRecipe craftShapedRecipe = CraftShapedRecipe.fromBukkitRecipe((ShapedRecipe)recipe); }
/* 1288 */     else if (recipe instanceof ShapelessRecipe)
/* 1289 */     { CraftShapelessRecipe craftShapelessRecipe = CraftShapelessRecipe.fromBukkitRecipe((ShapelessRecipe)recipe); }
/* 1290 */     else if (recipe instanceof FurnaceRecipe)
/* 1291 */     { CraftFurnaceRecipe craftFurnaceRecipe = CraftFurnaceRecipe.fromBukkitRecipe((FurnaceRecipe)recipe); }
/* 1292 */     else if (recipe instanceof BlastingRecipe)
/* 1293 */     { CraftBlastingRecipe craftBlastingRecipe = CraftBlastingRecipe.fromBukkitRecipe((BlastingRecipe)recipe); }
/* 1294 */     else if (recipe instanceof CampfireRecipe)
/* 1295 */     { CraftCampfireRecipe craftCampfireRecipe = CraftCampfireRecipe.fromBukkitRecipe((CampfireRecipe)recipe); }
/* 1296 */     else if (recipe instanceof SmokingRecipe)
/* 1297 */     { CraftSmokingRecipe craftSmokingRecipe = CraftSmokingRecipe.fromBukkitRecipe((SmokingRecipe)recipe); }
/* 1298 */     else if (recipe instanceof StonecuttingRecipe)
/* 1299 */     { CraftStonecuttingRecipe craftStonecuttingRecipe = CraftStonecuttingRecipe.fromBukkitRecipe((StonecuttingRecipe)recipe); }
/* 1300 */     else if (recipe instanceof SmithingRecipe)
/* 1301 */     { craftSmithingRecipe = CraftSmithingRecipe.fromBukkitRecipe((SmithingRecipe)recipe); }
/* 1302 */     else { if (recipe instanceof org.bukkit.inventory.ComplexRecipe) {
/* 1303 */         throw new UnsupportedOperationException("Cannot add custom complex recipe");
/*      */       }
/* 1305 */       return false; }
/*      */ 
/*      */     
/* 1308 */     craftSmithingRecipe.addToCraftingManager();
/* 1309 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<Recipe> getRecipesFor(ItemStack result) {
/* 1314 */     Validate.notNull(result, "Result cannot be null");
/*      */     
/* 1316 */     List<Recipe> results = new ArrayList<>();
/* 1317 */     Iterator<Recipe> iter = recipeIterator();
/* 1318 */     while (iter.hasNext()) {
/* 1319 */       Recipe recipe = iter.next();
/* 1320 */       ItemStack stack = recipe.getResult();
/* 1321 */       if (stack.getType() != result.getType()) {
/*      */         continue;
/*      */       }
/* 1324 */       if (result.getDurability() == -1 || result.getDurability() == stack.getDurability()) {
/* 1325 */         results.add(recipe);
/*      */       }
/*      */     } 
/* 1328 */     return results;
/*      */   }
/*      */ 
/*      */   
/*      */   public Recipe getRecipe(NamespacedKey recipeKey) {
/* 1333 */     Preconditions.checkArgument((recipeKey != null), "recipeKey == null");
/*      */     
/* 1335 */     return getServer().getCraftingManager().getRecipe(CraftNamespacedKey.toMinecraft(recipeKey)).map(IRecipe::toBukkitRecipe).orElse(null);
/*      */   }
/*      */ 
/*      */   
/*      */   public Iterator<Recipe> recipeIterator() {
/* 1340 */     return (Iterator<Recipe>)new RecipeIterator();
/*      */   }
/*      */ 
/*      */   
/*      */   public void clearRecipes() {
/* 1345 */     this.console.getCraftingManager().clearRecipes();
/*      */   }
/*      */ 
/*      */   
/*      */   public void resetRecipes() {
/* 1350 */     reloadData();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean removeRecipe(NamespacedKey recipeKey) {
/* 1355 */     Preconditions.checkArgument((recipeKey != null), "recipeKey == null");
/*      */     
/* 1357 */     MinecraftKey mcKey = CraftNamespacedKey.toMinecraft(recipeKey);
/* 1358 */     for (Object2ObjectLinkedOpenHashMap<MinecraftKey, IRecipe<?>> recipes : (Iterable<Object2ObjectLinkedOpenHashMap<MinecraftKey, IRecipe<?>>>)(getServer().getCraftingManager()).recipes.values()) {
/* 1359 */       if (recipes.remove(mcKey) != null) {
/* 1360 */         return true;
/*      */       }
/*      */     } 
/*      */     
/* 1364 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public Map<String, String[]> getCommandAliases() {
/* 1369 */     ConfigurationSection section = this.commandsConfiguration.getConfigurationSection("aliases");
/* 1370 */     Map<String, String[]> result = (Map)new LinkedHashMap<>();
/*      */     
/* 1372 */     if (section != null) {
/* 1373 */       for (String key : section.getKeys(false)) {
/*      */         ImmutableList immutableList;
/*      */         
/* 1376 */         if (section.isList(key)) {
/* 1377 */           List<String> commands = section.getStringList(key);
/*      */         } else {
/* 1379 */           immutableList = ImmutableList.of(section.getString(key));
/*      */         } 
/*      */         
/* 1382 */         result.put(key, (String[])immutableList.toArray((Object[])new String[immutableList.size()]));
/*      */       } 
/*      */     }
/*      */     
/* 1386 */     return result;
/*      */   }
/*      */   
/*      */   public void removeBukkitSpawnRadius() {
/* 1390 */     this.configuration.set("settings.spawn-radius", null);
/* 1391 */     saveConfig();
/*      */   }
/*      */   
/*      */   public int getBukkitSpawnRadius() {
/* 1395 */     return this.configuration.getInt("settings.spawn-radius", -1);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getShutdownMessage() {
/* 1400 */     return this.configuration.getString("settings.shutdown-message");
/*      */   }
/*      */ 
/*      */   
/*      */   public int getSpawnRadius() {
/* 1405 */     return getServer().getSpawnProtection();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSpawnRadius(int value) {
/* 1410 */     this.configuration.set("settings.spawn-radius", Integer.valueOf(value));
/* 1411 */     saveConfig();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getOnlineMode() {
/* 1416 */     return this.console.getOnlineMode();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getAllowFlight() {
/* 1421 */     return this.console.getAllowFlight();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isHardcore() {
/* 1426 */     return this.console.isHardcore();
/*      */   }
/*      */   
/*      */   public ChunkGenerator getGenerator(String world) {
/* 1430 */     ConfigurationSection section = this.configuration.getConfigurationSection("worlds");
/* 1431 */     ChunkGenerator result = null;
/*      */     
/* 1433 */     if (section != null) {
/* 1434 */       section = section.getConfigurationSection(world);
/*      */       
/* 1436 */       if (section != null) {
/* 1437 */         String name = section.getString("generator");
/*      */         
/* 1439 */         if (name != null && !name.equals("")) {
/* 1440 */           String[] split = name.split(":", 2);
/* 1441 */           String id = (split.length > 1) ? split[1] : null;
/* 1442 */           Plugin plugin = this.pluginManager.getPlugin(split[0]);
/*      */           
/* 1444 */           if (plugin == null) {
/* 1445 */             getLogger().severe("Could not set generator for default world '" + world + "': Plugin '" + split[0] + "' does not exist");
/* 1446 */           } else if (!plugin.isEnabled()) {
/* 1447 */             getLogger().severe("Could not set generator for default world '" + world + "': Plugin '" + plugin.getDescription().getFullName() + "' is not enabled yet (is it load:STARTUP?)");
/*      */           } else {
/*      */             try {
/* 1450 */               result = plugin.getDefaultWorldGenerator(world, id);
/* 1451 */               if (result == null) {
/* 1452 */                 getLogger().severe("Could not set generator for default world '" + world + "': Plugin '" + plugin.getDescription().getFullName() + "' lacks a default world generator");
/*      */               }
/* 1454 */             } catch (Throwable t) {
/* 1455 */               plugin.getLogger().log(Level.SEVERE, "Could not set generator for default world '" + world + "': Plugin '" + plugin.getDescription().getFullName(), t);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1462 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public CraftMapView getMap(int id) {
/* 1468 */     WorldMap worldmap = this.console.getWorldServer(World.OVERWORLD).a("map_" + id);
/* 1469 */     if (worldmap == null) {
/* 1470 */       return null;
/*      */     }
/* 1472 */     return worldmap.mapView;
/*      */   }
/*      */ 
/*      */   
/*      */   public CraftMapView createMap(World world) {
/* 1477 */     Validate.notNull(world, "World cannot be null");
/*      */     
/* 1479 */     ItemStack stack = new ItemStack((IMaterial)Items.MAP, 1);
/* 1480 */     WorldMap worldmap = ItemWorldMap.getSavedMap(stack, (World)((CraftWorld)world).getHandle());
/* 1481 */     return worldmap.mapView;
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack createExplorerMap(World world, Location location, StructureType structureType) {
/* 1486 */     return createExplorerMap(world, location, structureType, 100, true);
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack createExplorerMap(World world, Location location, StructureType structureType, int radius, boolean findUnexplored) {
/* 1491 */     Validate.notNull(world, "World cannot be null");
/* 1492 */     Validate.notNull(structureType, "StructureType cannot be null");
/* 1493 */     Validate.notNull(structureType.getMapIcon(), "Cannot create explorer maps for StructureType " + structureType.getName());
/*      */     
/* 1495 */     WorldServer worldServer = ((CraftWorld)world).getHandle();
/* 1496 */     Location structureLocation = world.locateNearestStructure(location, structureType, radius, findUnexplored);
/* 1497 */     BlockPosition structurePosition = new BlockPosition(structureLocation.getBlockX(), structureLocation.getBlockY(), structureLocation.getBlockZ());
/*      */ 
/*      */     
/* 1500 */     ItemStack stack = ItemWorldMap.createFilledMapView((World)worldServer, structurePosition.getX(), structurePosition.getZ(), MapView.Scale.NORMAL.getValue(), true, true);
/* 1501 */     ItemWorldMap.applySepiaFilter(worldServer, stack);
/*      */     
/* 1503 */     ItemWorldMap.getSavedMap(stack, (World)worldServer); WorldMap.decorateMap(stack, structurePosition, "+", MapIcon.Type.a(structureType.getMapIcon().getValue()));
/*      */     
/* 1505 */     return CraftItemStack.asBukkitCopy(stack);
/*      */   }
/*      */ 
/*      */   
/*      */   public void shutdown() {
/* 1510 */     this.console.safeShutdown(false);
/*      */   }
/*      */ 
/*      */   
/*      */   public int broadcast(String message, String permission) {
/* 1515 */     Set<CommandSender> recipients = new HashSet<>();
/* 1516 */     for (Permissible permissible : getPluginManager().getPermissionSubscriptions(permission)) {
/* 1517 */       if (permissible instanceof CommandSender && permissible.hasPermission(permission)) {
/* 1518 */         recipients.add((CommandSender)permissible);
/*      */       }
/*      */     } 
/*      */     
/* 1522 */     BroadcastMessageEvent broadcastMessageEvent = new BroadcastMessageEvent(!Bukkit.isPrimaryThread(), message, recipients);
/* 1523 */     getPluginManager().callEvent((Event)broadcastMessageEvent);
/*      */     
/* 1525 */     if (broadcastMessageEvent.isCancelled()) {
/* 1526 */       return 0;
/*      */     }
/*      */     
/* 1529 */     message = broadcastMessageEvent.getMessage();
/*      */     
/* 1531 */     for (CommandSender recipient : recipients) {
/* 1532 */       recipient.sendMessage(message);
/*      */     }
/*      */     
/* 1535 */     return recipients.size();
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public UUID getPlayerUniqueId(String name) {
/*      */     GameProfile profile;
/* 1541 */     Player player = Bukkit.getPlayerExact(name);
/* 1542 */     if (player != null) {
/* 1543 */       return player.getUniqueId();
/*      */     }
/*      */ 
/*      */     
/* 1547 */     if (MinecraftServer.getServer().getOnlineMode() || (SpigotConfig.bungee && PaperConfig.bungeeOnlineMode)) {
/*      */       
/* 1549 */       profile = this.console.getUserCache().getProfile(name);
/*      */     } else {
/*      */       
/* 1552 */       profile = new GameProfile(UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes(Charsets.UTF_8)), name);
/*      */     } 
/* 1554 */     return (profile != null) ? profile.getId() : null;
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public OfflinePlayer getOfflinePlayer(String name) {
/*      */     OfflinePlayer offlinePlayer;
/* 1561 */     Validate.notNull(name, "Name cannot be null");
/* 1562 */     Validate.notEmpty(name, "Name cannot be empty");
/*      */     
/* 1564 */     Player player = getPlayerExact(name);
/* 1565 */     if (player == null) {
/*      */       
/* 1567 */       GameProfile profile = null;
/*      */       
/* 1569 */       if (getOnlineMode() || 
/* 1570 */         PaperConfig.isProxyOnlineMode())
/*      */       {
/* 1572 */         profile = this.console.getUserCache().getProfile(name);
/*      */       }
/*      */       
/* 1575 */       if (profile == null) {
/*      */         
/* 1577 */         offlinePlayer = getOfflinePlayer(new GameProfile(UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes(Charsets.UTF_8)), name));
/*      */       } else {
/*      */         
/* 1580 */         offlinePlayer = getOfflinePlayer(profile);
/*      */       } 
/*      */     } else {
/* 1583 */       this.offlinePlayers.remove(offlinePlayer.getUniqueId());
/*      */     } 
/*      */     
/* 1586 */     return offlinePlayer;
/*      */   }
/*      */   
/*      */   public OfflinePlayer getOfflinePlayer(UUID id) {
/*      */     OfflinePlayer offlinePlayer;
/* 1591 */     Validate.notNull(id, "UUID cannot be null");
/*      */     
/* 1593 */     Player player = getPlayer(id);
/* 1594 */     if (player == null) {
/* 1595 */       offlinePlayer = this.offlinePlayers.get(id);
/* 1596 */       if (offlinePlayer == null) {
/* 1597 */         offlinePlayer = new CraftOfflinePlayer(this, new GameProfile(id, null));
/* 1598 */         this.offlinePlayers.put(id, offlinePlayer);
/*      */       } 
/*      */     } else {
/* 1601 */       this.offlinePlayers.remove(id);
/*      */     } 
/*      */     
/* 1604 */     return offlinePlayer;
/*      */   }
/*      */   
/*      */   public OfflinePlayer getOfflinePlayer(GameProfile profile) {
/* 1608 */     OfflinePlayer player = new CraftOfflinePlayer(this, profile);
/* 1609 */     this.offlinePlayers.put(profile.getId(), player);
/* 1610 */     return player;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Set<String> getIPBans() {
/* 1616 */     return new HashSet<>(Arrays.asList(this.playerList.getIPBans().getEntries()));
/*      */   }
/*      */ 
/*      */   
/*      */   public void banIP(String address) {
/* 1621 */     Validate.notNull(address, "Address cannot be null.");
/*      */     
/* 1623 */     getBanList(BanList.Type.IP).addBan(address, null, null, null);
/*      */   }
/*      */ 
/*      */   
/*      */   public void unbanIP(String address) {
/* 1628 */     Validate.notNull(address, "Address cannot be null.");
/*      */     
/* 1630 */     getBanList(BanList.Type.IP).pardon(address);
/*      */   }
/*      */ 
/*      */   
/*      */   public Set<OfflinePlayer> getBannedPlayers() {
/* 1635 */     Set<OfflinePlayer> result = new HashSet<>();
/*      */     
/* 1637 */     for (JsonListEntry entry : this.playerList.getProfileBans().getValues()) {
/* 1638 */       result.add(getOfflinePlayer((GameProfile)entry.getKey()));
/*      */     }
/*      */     
/* 1641 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   public BanList getBanList(BanList.Type type) {
/* 1646 */     Validate.notNull(type, "Type cannot be null");
/*      */     
/* 1648 */     switch (type) {
/*      */       case IP:
/* 1650 */         return new CraftIpBanList(this.playerList.getIPBans());
/*      */     } 
/*      */     
/* 1653 */     return new CraftProfileBanList(this.playerList.getProfileBans());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setWhitelist(boolean value) {
/* 1659 */     this.playerList.setHasWhitelist(value);
/* 1660 */     this.console.setHasWhitelist(value);
/*      */   }
/*      */ 
/*      */   
/*      */   public Set<OfflinePlayer> getWhitelistedPlayers() {
/* 1665 */     Set<OfflinePlayer> result = new LinkedHashSet<>();
/*      */     
/* 1667 */     for (JsonListEntry entry : this.playerList.getWhitelist().getValues()) {
/* 1668 */       result.add(getOfflinePlayer((GameProfile)entry.getKey()));
/*      */     }
/*      */     
/* 1671 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   public Set<OfflinePlayer> getOperators() {
/* 1676 */     Set<OfflinePlayer> result = new HashSet<>();
/*      */     
/* 1678 */     for (JsonListEntry entry : this.playerList.getOPs().getValues()) {
/* 1679 */       result.add(getOfflinePlayer((GameProfile)entry.getKey()));
/*      */     }
/*      */     
/* 1682 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   public void reloadWhitelist() {
/* 1687 */     this.playerList.reloadWhitelist();
/*      */   }
/*      */ 
/*      */   
/*      */   public GameMode getDefaultGameMode() {
/* 1692 */     return GameMode.getByValue((this.console.getWorldServer(World.OVERWORLD)).worldDataServer.getGameType().getId());
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDefaultGameMode(GameMode mode) {
/* 1697 */     Validate.notNull(mode, "Mode cannot be null");
/*      */     
/* 1699 */     for (World world : getWorlds()) {
/* 1700 */       (((CraftWorld)world).getHandle()).worldDataServer.setGameType(EnumGamemode.getById(mode.getValue()));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public ConsoleCommandSender getConsoleSender() {
/* 1706 */     return this.console.console;
/*      */   }
/*      */   
/*      */   public EntityMetadataStore getEntityMetadata() {
/* 1710 */     return this.entityMetadata;
/*      */   }
/*      */   
/*      */   public PlayerMetadataStore getPlayerMetadata() {
/* 1714 */     return this.playerMetadata;
/*      */   }
/*      */   
/*      */   public WorldMetadataStore getWorldMetadata() {
/* 1718 */     return this.worldMetadata;
/*      */   }
/*      */ 
/*      */   
/*      */   public File getWorldContainer() {
/* 1723 */     return (getServer()).convertable.a(World.OVERWORLD).getParentFile();
/*      */   }
/*      */ 
/*      */   
/*      */   public OfflinePlayer[] getOfflinePlayers() {
/* 1728 */     WorldNBTStorage storage = this.console.worldNBTStorage;
/* 1729 */     String[] files = storage.getPlayerDir().list((FilenameFilter)new DatFileFilter());
/* 1730 */     Set<OfflinePlayer> players = new HashSet<>();
/*      */     
/* 1732 */     for (String file : files) {
/*      */       try {
/* 1734 */         players.add(getOfflinePlayer(UUID.fromString(file.substring(0, file.length() - 4))));
/* 1735 */       } catch (IllegalArgumentException illegalArgumentException) {}
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1740 */     players.addAll(getOnlinePlayers());
/*      */     
/* 1742 */     return players.<OfflinePlayer>toArray(new OfflinePlayer[players.size()]);
/*      */   }
/*      */ 
/*      */   
/*      */   public Messenger getMessenger() {
/* 1747 */     return (Messenger)this.messenger;
/*      */   }
/*      */ 
/*      */   
/*      */   public void sendPluginMessage(Plugin source, String channel, byte[] message) {
/* 1752 */     StandardMessenger.validatePluginMessage(getMessenger(), source, channel, message);
/*      */     
/* 1754 */     for (Player player : getOnlinePlayers()) {
/* 1755 */       player.sendPluginMessage(source, channel, message);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public Set<String> getListeningPluginChannels() {
/* 1761 */     Set<String> result = new HashSet<>();
/*      */     
/* 1763 */     for (Player player : getOnlinePlayers()) {
/* 1764 */       result.addAll(player.getListeningPluginChannels());
/*      */     }
/*      */     
/* 1767 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   public Inventory createInventory(InventoryHolder owner, InventoryType type) {
/* 1772 */     Validate.isTrue(type.isCreatable(), "Cannot open an inventory of type ", type);
/* 1773 */     return CraftInventoryCreator.INSTANCE.createInventory(owner, type);
/*      */   }
/*      */ 
/*      */   
/*      */   public Inventory createInventory(InventoryHolder owner, InventoryType type, String title) {
/* 1778 */     Validate.isTrue(type.isCreatable(), "Cannot open an inventory of type ", type);
/* 1779 */     return CraftInventoryCreator.INSTANCE.createInventory(owner, type, title);
/*      */   }
/*      */ 
/*      */   
/*      */   public Inventory createInventory(InventoryHolder owner, int size) throws IllegalArgumentException {
/* 1784 */     Validate.isTrue((9 <= size && size <= 54 && size % 9 == 0), "Size for custom inventory must be a multiple of 9 between 9 and 54 slots (got " + size + ")");
/* 1785 */     return CraftInventoryCreator.INSTANCE.createInventory(owner, size);
/*      */   }
/*      */ 
/*      */   
/*      */   public Inventory createInventory(InventoryHolder owner, int size, String title) throws IllegalArgumentException {
/* 1790 */     Validate.isTrue((9 <= size && size <= 54 && size % 9 == 0), "Size for custom inventory must be a multiple of 9 between 9 and 54 slots (got " + size + ")");
/* 1791 */     return CraftInventoryCreator.INSTANCE.createInventory(owner, size, title);
/*      */   }
/*      */ 
/*      */   
/*      */   public Merchant createMerchant(String title) {
/* 1796 */     return (Merchant)new CraftMerchantCustom((title == null) ? InventoryType.MERCHANT.getDefaultTitle() : title);
/*      */   }
/*      */ 
/*      */   
/*      */   public HelpMap getHelpMap() {
/* 1801 */     return (HelpMap)this.helpMap;
/*      */   }
/*      */ 
/*      */   
/*      */   public SimpleCommandMap getCommandMap() {
/* 1806 */     return (SimpleCommandMap)this.commandMap;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMonsterSpawnLimit() {
/* 1811 */     return this.monsterSpawn;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getAnimalSpawnLimit() {
/* 1816 */     return this.animalSpawn;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getWaterAnimalSpawnLimit() {
/* 1821 */     return this.waterAnimalSpawn;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getWaterAmbientSpawnLimit() {
/* 1826 */     return this.waterAmbientSpawn;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getAmbientSpawnLimit() {
/* 1831 */     return this.ambientSpawn;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isPrimaryThread() {
/* 1837 */     Thread currThread = Thread.currentThread();
/* 1838 */     return (currThread == this.console.serverThread || currThread instanceof com.tuinity.tuinity.util.TickThread || currThread.equals((MinecraftServer.getServer()).shutdownThread));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getMotd() {
/* 1844 */     return this.console.getMotd();
/*      */   }
/*      */ 
/*      */   
/*      */   public Warning.WarningState getWarningState() {
/* 1849 */     return this.warningState;
/*      */   }
/*      */   public List<String> tabComplete(CommandSender sender, String message, WorldServer world, Vec3D pos, boolean forceCommand) {
/*      */     List<String> offers;
/* 1853 */     if (!(sender instanceof Player)) {
/* 1854 */       return (List<String>)ImmutableList.of();
/*      */     }
/*      */ 
/*      */     
/* 1858 */     Player player = (Player)sender;
/* 1859 */     if (message.startsWith("/") || forceCommand) {
/* 1860 */       offers = tabCompleteCommand(player, message, world, pos);
/*      */     } else {
/* 1862 */       offers = tabCompleteChat(player, message);
/*      */     } 
/*      */     
/* 1865 */     TabCompleteEvent tabEvent = new TabCompleteEvent((CommandSender)player, message, offers, (message.startsWith("/") || forceCommand), (pos != null) ? MCUtil.toLocation((World)((CraftWorld)player.getWorld()).getHandle(), new BlockPosition(pos)) : null);
/* 1866 */     getPluginManager().callEvent((Event)tabEvent);
/*      */     
/* 1868 */     return tabEvent.isCancelled() ? Collections.EMPTY_LIST : tabEvent.getCompletions();
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> tabCompleteCommand(Player player, String message, WorldServer world, Vec3D pos) {
/* 1873 */     if ((SpigotConfig.tabComplete < 0 || message.length() <= SpigotConfig.tabComplete) && !message.contains(" "))
/*      */     {
/* 1875 */       return (List<String>)ImmutableList.of();
/*      */     }
/*      */ 
/*      */     
/* 1879 */     List<String> completions = null;
/*      */     try {
/* 1881 */       if (message.startsWith("/"))
/*      */       {
/* 1883 */         message = message.substring(1);
/*      */       }
/* 1885 */       if (pos == null) {
/* 1886 */         completions = getCommandMap().tabComplete((CommandSender)player, message);
/*      */       } else {
/* 1888 */         completions = getCommandMap().tabComplete((CommandSender)player, message, new Location(world.getWorld(), pos.x, pos.y, pos.z));
/*      */       } 
/* 1890 */     } catch (CommandException ex) {
/* 1891 */       player.sendMessage(ChatColor.RED + "An internal error occurred while attempting to tab-complete this command");
/* 1892 */       getLogger().log(Level.SEVERE, "Exception when " + player.getName() + " attempted to tab complete " + message, (Throwable)ex);
/*      */     } 
/*      */     
/* 1895 */     return (completions == null) ? (List<String>)ImmutableList.of() : completions;
/*      */   }
/*      */   
/*      */   public List<String> tabCompleteChat(Player player, String message) {
/* 1899 */     List<String> completions = new ArrayList<>();
/* 1900 */     PlayerChatTabCompleteEvent event = new PlayerChatTabCompleteEvent(player, message, completions);
/* 1901 */     String token = event.getLastToken();
/* 1902 */     for (Player p : getOnlinePlayers()) {
/* 1903 */       if (player.canSee(p) && StringUtil.startsWithIgnoreCase(p.getName(), token)) {
/* 1904 */         completions.add(p.getName());
/*      */       }
/*      */     } 
/* 1907 */     this.pluginManager.callEvent((Event)event);
/*      */     
/* 1909 */     Iterator<?> it = completions.iterator();
/* 1910 */     while (it.hasNext()) {
/* 1911 */       Object current = it.next();
/* 1912 */       if (!(current instanceof String))
/*      */       {
/* 1914 */         it.remove();
/*      */       }
/*      */     } 
/* 1917 */     Collections.sort(completions, String.CASE_INSENSITIVE_ORDER);
/* 1918 */     return completions;
/*      */   }
/*      */ 
/*      */   
/*      */   public CraftItemFactory getItemFactory() {
/* 1923 */     return CraftItemFactory.instance();
/*      */   }
/*      */ 
/*      */   
/*      */   public CraftScoreboardManager getScoreboardManager() {
/* 1928 */     return this.scoreboardManager;
/*      */   }
/*      */   
/*      */   public void checkSaveState() {
/* 1932 */     if (this.playerCommandState || this.printSaveWarning || this.console.autosavePeriod <= 0) {
/*      */       return;
/*      */     }
/* 1935 */     this.printSaveWarning = true;
/* 1936 */     getLogger().log(Level.WARNING, "A manual (plugin-induced) save has been detected while server is configured to auto-save. This may affect performance.", (this.warningState == Warning.WarningState.ON) ? new Throwable() : null);
/*      */   }
/*      */ 
/*      */   
/*      */   public CraftIconCache getServerIcon() {
/* 1941 */     return this.icon;
/*      */   }
/*      */ 
/*      */   
/*      */   public CraftIconCache loadServerIcon(File file) throws Exception {
/* 1946 */     Validate.notNull(file, "File cannot be null");
/* 1947 */     if (!file.isFile()) {
/* 1948 */       throw new IllegalArgumentException(file + " is not a file");
/*      */     }
/* 1950 */     return loadServerIcon0(file);
/*      */   }
/*      */   
/*      */   static CraftIconCache loadServerIcon0(File file) throws Exception {
/* 1954 */     return loadServerIcon0(ImageIO.read(file));
/*      */   }
/*      */ 
/*      */   
/*      */   public CraftIconCache loadServerIcon(BufferedImage image) throws Exception {
/* 1959 */     Validate.notNull(image, "Image cannot be null");
/* 1960 */     return loadServerIcon0(image);
/*      */   }
/*      */   
/*      */   static CraftIconCache loadServerIcon0(BufferedImage image) throws Exception {
/* 1964 */     ByteBuf bytebuf = Unpooled.buffer();
/*      */     
/* 1966 */     Validate.isTrue((image.getWidth() == 64), "Must be 64 pixels wide");
/* 1967 */     Validate.isTrue((image.getHeight() == 64), "Must be 64 pixels high");
/* 1968 */     ImageIO.write(image, "PNG", (OutputStream)new ByteBufOutputStream(bytebuf));
/* 1969 */     ByteBuffer bytebuffer = Base64.getEncoder().encode(bytebuf.nioBuffer());
/*      */     
/* 1971 */     return new CraftIconCache("data:image/png;base64," + StandardCharsets.UTF_8.decode(bytebuffer));
/*      */   }
/*      */ 
/*      */   
/*      */   public void setIdleTimeout(int threshold) {
/* 1976 */     this.console.setIdleTimeout(threshold);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getIdleTimeout() {
/* 1981 */     return this.console.getIdleTimeout();
/*      */   }
/*      */ 
/*      */   
/*      */   public ChunkGenerator.ChunkData createChunkData(World world) {
/* 1986 */     Validate.notNull(world, "World cannot be null");
/* 1987 */     return (ChunkGenerator.ChunkData)new CraftChunkData(world);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ChunkGenerator.ChunkData createVanillaChunkData(World world, int x, int z) {
/* 1994 */     CraftChunkData data = (CraftChunkData)createChunkData(world);
/*      */     
/* 1996 */     WorldServer nmsWorld = ((CraftWorld)world).getHandle();
/* 1997 */     ProtoChunk protoChunk = new ProtoChunk(new ChunkCoordIntPair(x, z), ChunkConverter.getEmptyConverter(), (World)nmsWorld);
/* 1998 */     List<IChunkAccess> list = new ArrayList<>();
/* 1999 */     list.add(protoChunk);
/* 2000 */     RegionLimitedWorldAccess genRegion = new RegionLimitedWorldAccess(nmsWorld, list);
/*      */     
/* 2002 */     ChunkGenerator chunkGenerator = (nmsWorld.getChunkProvider()).chunkGenerator;
/* 2003 */     chunkGenerator.createBiomes((IRegistry)nmsWorld.r().b(IRegistry.ay), (IChunkAccess)protoChunk);
/* 2004 */     chunkGenerator.buildNoise((GeneratorAccess)genRegion, nmsWorld.getStructureManager(), (IChunkAccess)protoChunk);
/* 2005 */     chunkGenerator.buildBase(genRegion, (IChunkAccess)protoChunk);
/*      */     
/* 2007 */     data.setRawChunkData(protoChunk.getSections());
/*      */     
/* 2009 */     return (ChunkGenerator.ChunkData)data;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public BossBar createBossBar(String title, BarColor color, BarStyle style, BarFlag... flags) {
/* 2015 */     return (BossBar)new CraftBossBar(title, color, style, flags);
/*      */   }
/*      */ 
/*      */   
/*      */   public KeyedBossBar createBossBar(NamespacedKey key, String title, BarColor barColor, BarStyle barStyle, BarFlag... barFlags) {
/* 2020 */     Preconditions.checkArgument((key != null), "key");
/*      */     
/* 2022 */     BossBattleCustom bossBattleCustom = getServer().getBossBattleCustomData().register(CraftNamespacedKey.toMinecraft(key), CraftChatMessage.fromString(title, true)[0]);
/* 2023 */     CraftKeyedBossbar craftKeyedBossbar = new CraftKeyedBossbar(bossBattleCustom);
/* 2024 */     craftKeyedBossbar.setColor(barColor);
/* 2025 */     craftKeyedBossbar.setStyle(barStyle);
/* 2026 */     for (BarFlag flag : barFlags) {
/* 2027 */       craftKeyedBossbar.addFlag(flag);
/*      */     }
/*      */     
/* 2030 */     return (KeyedBossBar)craftKeyedBossbar;
/*      */   }
/*      */ 
/*      */   
/*      */   public Iterator<KeyedBossBar> getBossBars() {
/* 2035 */     return (Iterator<KeyedBossBar>)Iterators.unmodifiableIterator(Iterators.transform(getServer().getBossBattleCustomData().getBattles().iterator(), new Function<BossBattleCustom, KeyedBossBar>()
/*      */           {
/*      */             public KeyedBossBar apply(BossBattleCustom bossBattleCustom) {
/* 2038 */               return bossBattleCustom.getBukkitEntity();
/*      */             }
/*      */           }));
/*      */   }
/*      */ 
/*      */   
/*      */   public KeyedBossBar getBossBar(NamespacedKey key) {
/* 2045 */     Preconditions.checkArgument((key != null), "key");
/* 2046 */     BossBattleCustom bossBattleCustom = getServer().getBossBattleCustomData().a(CraftNamespacedKey.toMinecraft(key));
/*      */     
/* 2048 */     return (bossBattleCustom == null) ? null : bossBattleCustom.getBukkitEntity();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean removeBossBar(NamespacedKey key) {
/* 2053 */     Preconditions.checkArgument((key != null), "key");
/* 2054 */     BossBattleCustomData bossBattleCustomData = getServer().getBossBattleCustomData();
/* 2055 */     BossBattleCustom bossBattleCustom = bossBattleCustomData.a(CraftNamespacedKey.toMinecraft(key));
/*      */     
/* 2057 */     if (bossBattleCustom != null) {
/* 2058 */       bossBattleCustomData.remove(bossBattleCustom);
/* 2059 */       return true;
/*      */     } 
/*      */     
/* 2062 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public Entity getEntity(UUID uuid) {
/* 2067 */     Validate.notNull(uuid, "UUID cannot be null");
/*      */     
/* 2069 */     for (WorldServer world : getServer().getWorlds()) {
/* 2070 */       Entity entity = world.getEntity(uuid);
/* 2071 */       if (entity != null) {
/* 2072 */         return (Entity)entity.getBukkitEntity();
/*      */       }
/*      */     } 
/*      */     
/* 2076 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public Advancement getAdvancement(NamespacedKey key) {
/* 2081 */     Preconditions.checkArgument((key != null), "key");
/*      */     
/* 2083 */     Advancement advancement = this.console.getAdvancementData().a(CraftNamespacedKey.toMinecraft(key));
/* 2084 */     return (advancement == null) ? null : advancement.bukkit;
/*      */   }
/*      */ 
/*      */   
/*      */   public Iterator<Advancement> advancementIterator() {
/* 2089 */     return (Iterator<Advancement>)Iterators.unmodifiableIterator(Iterators.transform(this.console.getAdvancementData().getAdvancements().iterator(), new Function<Advancement, Advancement>()
/*      */           {
/*      */             public Advancement apply(Advancement advancement) {
/* 2092 */               return advancement.bukkit;
/*      */             }
/*      */           }));
/*      */   }
/*      */ 
/*      */   
/*      */   public BlockData createBlockData(Material material) {
/* 2099 */     Validate.isTrue((material != null), "Must provide material");
/*      */     
/* 2101 */     return createBlockData(material, (String)null);
/*      */   }
/*      */ 
/*      */   
/*      */   public BlockData createBlockData(Material material, Consumer<BlockData> consumer) {
/* 2106 */     BlockData data = createBlockData(material);
/*      */     
/* 2108 */     if (consumer != null) {
/* 2109 */       consumer.accept(data);
/*      */     }
/*      */     
/* 2112 */     return data;
/*      */   }
/*      */ 
/*      */   
/*      */   public BlockData createBlockData(String data) throws IllegalArgumentException {
/* 2117 */     Validate.isTrue((data != null), "Must provide data");
/*      */     
/* 2119 */     return createBlockData((Material)null, data);
/*      */   }
/*      */ 
/*      */   
/*      */   public BlockData createBlockData(Material material, String data) {
/* 2124 */     Validate.isTrue((material != null || data != null), "Must provide one of material or data");
/*      */     
/* 2126 */     return (BlockData)CraftBlockData.newData(material, data);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public <T extends org.bukkit.Keyed> Tag<T> getTag(String registry, NamespacedKey tag, Class<T> clazz) {
/* 2132 */     MinecraftKey key = CraftNamespacedKey.toMinecraft(tag);
/*      */     
/* 2134 */     switch (registry) {
/*      */       case "blocks":
/* 2136 */         Preconditions.checkArgument((clazz == Material.class), "Block namespace must have material type");
/*      */         
/* 2138 */         return (Tag<T>)new CraftBlockTag(this.console.getTagRegistry().getBlockTags(), key);
/*      */       case "items":
/* 2140 */         Preconditions.checkArgument((clazz == Material.class), "Item namespace must have material type");
/*      */         
/* 2142 */         return (Tag<T>)new CraftItemTag(this.console.getTagRegistry().getItemTags(), key);
/*      */       case "fluids":
/* 2144 */         Preconditions.checkArgument((clazz == Fluid.class), "Fluid namespace must have fluid type");
/*      */         
/* 2146 */         return (Tag<T>)new CraftFluidTag(this.console.getTagRegistry().getFluidTags(), key);
/*      */     } 
/* 2148 */     throw new IllegalArgumentException();
/*      */   }
/*      */   
/*      */   public <T extends org.bukkit.Keyed> Iterable<Tag<T>> getTags(String registry, Class<T> clazz) {
/*      */     Tags<Block> blockTags;
/*      */     Tags<Item> itemTags;
/*      */     Tags<FluidType> fluidTags;
/* 2155 */     switch (registry) {
/*      */       case "blocks":
/* 2157 */         Preconditions.checkArgument((clazz == Material.class), "Block namespace must have material type");
/*      */         
/* 2159 */         blockTags = this.console.getTagRegistry().getBlockTags();
/* 2160 */         return (Iterable<Tag<T>>)blockTags.a().keySet().stream().map(key -> new CraftBlockTag(blockTags, key)).collect(ImmutableList.toImmutableList());
/*      */       case "items":
/* 2162 */         Preconditions.checkArgument((clazz == Material.class), "Item namespace must have material type");
/*      */         
/* 2164 */         itemTags = this.console.getTagRegistry().getItemTags();
/* 2165 */         return (Iterable<Tag<T>>)itemTags.a().keySet().stream().map(key -> new CraftItemTag(itemTags, key)).collect(ImmutableList.toImmutableList());
/*      */       case "fluids":
/* 2167 */         Preconditions.checkArgument((clazz == Material.class), "Fluid namespace must have fluid type");
/*      */         
/* 2169 */         fluidTags = this.console.getTagRegistry().getFluidTags();
/* 2170 */         return (Iterable<Tag<T>>)fluidTags.a().keySet().stream().map(key -> new CraftFluidTag(fluidTags, key)).collect(ImmutableList.toImmutableList());
/*      */     } 
/* 2172 */     throw new IllegalArgumentException();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public LootTable getLootTable(NamespacedKey key) {
/* 2178 */     Validate.notNull(key, "NamespacedKey cannot be null");
/*      */     
/* 2180 */     LootTableRegistry registry = getServer().getLootTableRegistry();
/* 2181 */     return new CraftLootTable(key, registry.getLootTable(CraftNamespacedKey.toMinecraft(key)));
/*      */   }
/*      */   
/*      */   public List<Entity> selectEntities(CommandSender sender, String selector) {
/*      */     List<? extends Entity> nms;
/* 2186 */     Preconditions.checkArgument((selector != null), "Selector cannot be null");
/* 2187 */     Preconditions.checkArgument((sender != null), "Sender cannot be null");
/*      */     
/* 2189 */     ArgumentEntity arg = ArgumentEntity.multipleEntities();
/*      */ 
/*      */     
/*      */     try {
/* 2193 */       StringReader reader = new StringReader(selector);
/* 2194 */       nms = arg.parse(reader, true).getEntities(VanillaCommandWrapper.getListener(sender));
/* 2195 */       Preconditions.checkArgument(!reader.canRead(), "Spurious trailing data in selector: " + selector);
/* 2196 */     } catch (CommandSyntaxException ex) {
/* 2197 */       throw new IllegalArgumentException("Could not parse selector: " + selector, ex);
/*      */     } 
/*      */     
/* 2200 */     return new ArrayList<>(Lists.transform(nms, entity -> entity.getBukkitEntity()));
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public UnsafeValues getUnsafe() {
/* 2206 */     return CraftMagicNumbers.INSTANCE;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public double[] getTPS() {
/* 2212 */     return new double[] {
/* 2213 */         (MinecraftServer.getServer()).tps1.getAverage(), 
/* 2214 */         (MinecraftServer.getServer()).tps5.getAverage(), 
/* 2215 */         (MinecraftServer.getServer()).tps15.getAverage()
/*      */       };
/*      */   }
/*      */ 
/*      */   
/*      */   public long[] getTickTimes() {
/* 2221 */     return (getServer()).tickTimes5s.getTimes();
/*      */   }
/*      */ 
/*      */   
/*      */   public double getAverageTickTime() {
/* 2226 */     return (getServer()).tickTimes5s.getAverage();
/*      */   }
/*      */   
/*      */   public CraftServer(DedicatedServer console, PlayerList playerList) {
/* 2230 */     this.spigot = new Server.Spigot()
/*      */       {
/*      */ 
/*      */         
/*      */         @Deprecated
/*      */         public YamlConfiguration getConfig()
/*      */         {
/* 2237 */           return SpigotConfig.config;
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public YamlConfiguration getBukkitConfig() {
/* 2243 */           return CraftServer.this.configuration;
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public YamlConfiguration getSpigotConfig() {
/* 2249 */           return SpigotConfig.config;
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public YamlConfiguration getPaperConfig() {
/* 2255 */           return PaperConfig.config;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public YamlConfiguration getTuinityConfig() {
/* 2262 */           return TuinityConfig.config;
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public void restart() {
/* 2268 */           RestartCommand.restart();
/*      */         }
/*      */ 
/*      */         
/*      */         public void broadcast(BaseComponent component) {
/* 2273 */           for (Player player : CraftServer.this.getOnlinePlayers()) {
/* 2274 */             player.spigot().sendMessage(component);
/*      */           }
/*      */         }
/*      */ 
/*      */         
/*      */         public void broadcast(BaseComponent... components) {
/* 2280 */           for (Player player : CraftServer.this.getOnlinePlayers()) {
/* 2281 */             player.spigot().sendMessage(components);
/*      */           }
/*      */         }
/*      */       };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2397 */     this.mobGoals = (MobGoals)new PaperMobGoals(); this.console = console; this.playerList = (DedicatedPlayerList)playerList; this.playerView = Collections.unmodifiableList(Lists.transform(playerList.players, new Function<EntityPlayer, CraftPlayer>() { public CraftPlayer apply(EntityPlayer player) { return player.getBukkitEntity(); } })); this.serverVersion = CraftServer.class.getPackage().getImplementationVersion(); Bukkit.setServer(this); Enchantments.DAMAGE_ALL.getClass(); Enchantment.stopAcceptingRegistrations(); Potion.setPotionBrewer((PotionBrewer)new CraftPotionBrewer()); MobEffects.BLINDNESS.getClass(); PotionEffectType.stopAcceptingRegistrations(); if (!Main.useConsole) getLogger().info("Console input is disabled due to --noconsole command argument");  this.configuration = YamlConfiguration.loadConfiguration(getConfigFile()); this.configuration.options().copyDefaults(true); this.configuration.setDefaults((Configuration)YamlConfiguration.loadConfiguration(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("configurations/bukkit.yml"), Charsets.UTF_8))); ConfigurationSection legacyAlias = null; if (!this.configuration.isString("aliases")) { legacyAlias = this.configuration.getConfigurationSection("aliases"); this.configuration.set("aliases", "now-in-commands.yml"); }  saveConfig(); if (getCommandsConfigFile().isFile())
/*      */       legacyAlias = null;  this.commandsConfiguration = YamlConfiguration.loadConfiguration(getCommandsConfigFile()); this.commandsConfiguration.options().copyDefaults(true); this.commandsConfiguration.setDefaults((Configuration)YamlConfiguration.loadConfiguration(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("configurations/commands.yml"), Charsets.UTF_8))); saveCommandsConfig(); if (legacyAlias != null) { ConfigurationSection aliases = this.commandsConfiguration.createSection("aliases"); for (String key : legacyAlias.getKeys(false)) { ArrayList<String> commands = new ArrayList<>(); if (legacyAlias.isList(key)) { for (String command : legacyAlias.getStringList(key))
/*      */             commands.add(command + " $1-");  } else { commands.add(legacyAlias.getString(key) + " $1-"); }  aliases.set(key, commands); }  }  saveCommandsConfig(); this.overrideAllCommandBlockCommands = this.commandsConfiguration.getStringList("command-block-overrides").contains("*"); this.ignoreVanillaPermissions = this.commandsConfiguration.getBoolean("ignore-vanilla-permissions"); this.pluginManager.useTimings(this.configuration.getBoolean("settings.plugin-profiling")); this.monsterSpawn = this.configuration.getInt("spawn-limits.monsters"); this.animalSpawn = this.configuration.getInt("spawn-limits.animals"); this.waterAnimalSpawn = this.configuration.getInt("spawn-limits.water-animals"); this.waterAmbientSpawn = this.configuration.getInt("spawn-limits.water-ambient"); this.ambientSpawn = this.configuration.getInt("spawn-limits.ambient"); console.autosavePeriod = this.configuration.getInt("ticks-per.autosave"); this.warningState = Warning.WarningState.value(this.configuration.getString("settings.deprecated-verbose")); TicketType.PLUGIN.loadPeriod = Math.min(20, this.configuration.getInt("chunk-gc.period-in-ticks")); this.minimumAPI = this.configuration.getString("settings.minimum-api"); loadIcon();
/* 2400 */   } public MobGoals getMobGoals() { return this.mobGoals; }
/*      */ 
/*      */   
/*      */   public Server.Spigot spigot() {
/*      */     return this.spigot;
/*      */   }
/*      */   
/*      */   public static Path dumpHeap(Path dir, String name) {
/*      */     try {
/*      */       Path file;
/*      */       Files.createDirectories(dir, (FileAttribute<?>[])new FileAttribute[0]);
/*      */       MBeanServer server = ManagementFactory.getPlatformMBeanServer();
/*      */       try {
/*      */         Class<?> clazz = Class.forName("openj9.lang.management.OpenJ9DiagnosticsMXBean");
/*      */         Object openj9Mbean = ManagementFactory.newPlatformMXBeanProxy(server, "openj9.lang.management:type=OpenJ9Diagnostics", clazz);
/*      */         Method m = clazz.getMethod("triggerDumpToFile", new Class[] { String.class, String.class });
/*      */         file = dir.resolve(name + ".phd");
/*      */         m.invoke(openj9Mbean, new Object[] { "heap", file.toString() });
/*      */       } catch (ClassNotFoundException e) {
/*      */         Class<?> clazz = Class.forName("com.sun.management.HotSpotDiagnosticMXBean");
/*      */         Object hotspotMBean = ManagementFactory.newPlatformMXBeanProxy(server, "com.sun.management:type=HotSpotDiagnostic", clazz);
/*      */         Method m = clazz.getMethod("dumpHeap", new Class[] { String.class, boolean.class });
/*      */         file = dir.resolve(name + ".hprof");
/*      */         m.invoke(hotspotMBean, new Object[] { file.toString(), Boolean.valueOf(true) });
/*      */       } 
/*      */       return file;
/*      */     } catch (Throwable t) {
/*      */       Bukkit.getLogger().log(Level.SEVERE, "Could not write heap", t);
/*      */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void reloadPermissions() {
/*      */     this.pluginManager.clearPermissions();
/*      */     if (PaperConfig.loadPermsBeforePlugins)
/*      */       loadCustomPermissions(); 
/*      */     for (Plugin plugin : this.pluginManager.getPlugins()) {
/*      */       for (Permission perm : plugin.getDescription().getPermissions()) {
/*      */         try {
/*      */           this.pluginManager.addPermission(perm);
/*      */         } catch (IllegalArgumentException ex) {
/*      */           getLogger().log(Level.WARNING, "Plugin " + plugin.getDescription().getFullName() + " tried to register permission '" + perm.getName() + "' but it's already registered", ex);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     if (!PaperConfig.loadPermsBeforePlugins)
/*      */       loadCustomPermissions(); 
/*      */     DefaultPermissions.registerCorePermissions();
/*      */     CraftDefaultPermissions.registerCorePermissions();
/*      */   }
/*      */   
/*      */   public boolean reloadCommandAliases() {
/*      */     Set<String> removals = (Set<String>)getCommandAliases().keySet().stream().map(key -> key.toLowerCase(Locale.ENGLISH)).collect(Collectors.toSet());
/*      */     Objects.requireNonNull(removals);
/*      */     getCommandMap().getKnownCommands().keySet().removeIf(removals::contains);
/*      */     File file = getCommandsConfigFile();
/*      */     try {
/*      */       this.commandsConfiguration.load(file);
/*      */     } catch (FileNotFoundException ex) {
/*      */       return false;
/*      */     } catch (IOException|org.bukkit.configuration.InvalidConfigurationException ex) {
/*      */       Bukkit.getLogger().log(Level.SEVERE, "Cannot load " + file, ex);
/*      */       return false;
/*      */     } 
/*      */     this.commandMap.registerServerAliases();
/*      */     return true;
/*      */   }
/*      */   
/*      */   public boolean suggestPlayerNamesWhenNullTabCompletions() {
/*      */     return PaperConfig.suggestPlayersWhenNullTabCompletions;
/*      */   }
/*      */   
/*      */   public String getPermissionMessage() {
/*      */     return PaperConfig.noPermissionMessage;
/*      */   }
/*      */   
/*      */   public PlayerProfile createProfile(@Nonnull UUID uuid) {
/*      */     return createProfile(uuid, null);
/*      */   }
/*      */   
/*      */   public PlayerProfile createProfile(@Nonnull String name) {
/*      */     return createProfile(null, name);
/*      */   }
/*      */   
/*      */   public PlayerProfile createProfile(@Nullable UUID uuid, @Nullable String name) {
/*      */     Player player = (uuid != null) ? Bukkit.getPlayer(uuid) : ((name != null) ? Bukkit.getPlayerExact(name) : null);
/*      */     if (player != null)
/*      */       return (PlayerProfile)new CraftPlayerProfile((CraftPlayer)player); 
/*      */     return (PlayerProfile)new CraftPlayerProfile(uuid, name);
/*      */   }
/*      */   
/*      */   public int getCurrentTick() {
/*      */     return MinecraftServer.currentTick;
/*      */   }
/*      */   
/*      */   public boolean isStopping() {
/*      */     return MinecraftServer.getServer().hasStopped();
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\CraftServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */