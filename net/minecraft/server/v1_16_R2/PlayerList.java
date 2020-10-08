/*      */ package net.minecraft.server.v1_16_R2;
/*      */ import co.aikar.timings.MinecraftTimings;
/*      */ import com.destroystokyo.paper.PaperConfig;
/*      */ import com.destroystokyo.paper.event.player.PlayerInitialSpawnEvent;
/*      */ import com.destroystokyo.paper.event.profile.ProfileWhitelistVerifyEvent;
/*      */ import com.destroystokyo.paper.util.misc.PooledLinkedHashSets;
/*      */ import com.google.common.base.Predicate;
/*      */ import com.google.common.collect.Lists;
/*      */ import com.google.common.collect.Maps;
/*      */ import com.mojang.authlib.GameProfile;
/*      */ import com.mojang.datafixers.util.Either;
/*      */ import com.mojang.serialization.DataResult;
/*      */ import io.netty.util.concurrent.Future;
/*      */ import java.io.File;
/*      */ import java.net.InetSocketAddress;
/*      */ import java.net.SocketAddress;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.Optional;
/*      */ import java.util.Set;
/*      */ import java.util.UUID;
/*      */ import java.util.concurrent.CompletableFuture;
/*      */ import java.util.concurrent.CopyOnWriteArrayList;
/*      */ import javax.annotation.Nullable;
/*      */ import org.apache.logging.log4j.LogManager;
/*      */ import org.apache.logging.log4j.Logger;
/*      */ import org.bukkit.Bukkit;
/*      */ import org.bukkit.Location;
/*      */ import org.bukkit.World;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
/*      */ import org.bukkit.entity.Player;
/*      */ import org.bukkit.event.Event;
/*      */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*      */ import org.bukkit.event.player.PlayerChangedWorldEvent;
/*      */ import org.bukkit.event.player.PlayerJoinEvent;
/*      */ import org.bukkit.event.player.PlayerLoginEvent;
/*      */ import org.bukkit.event.player.PlayerQuitEvent;
/*      */ import org.bukkit.event.player.PlayerRespawnEvent;
/*      */ import org.spigotmc.SpigotConfig;
/*      */ 
/*      */ public abstract class PlayerList {
/*   49 */   public static final File b = new File("banned-players.json");
/*   50 */   public static final File c = new File("banned-ips.json");
/*   51 */   public static final File d = new File("ops.json");
/*   52 */   public static final File e = new File("whitelist.json");
/*   53 */   private static final Logger LOGGER = LogManager.getLogger();
/*   54 */   private static final SimpleDateFormat g = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
/*      */   private final MinecraftServer server;
/*   56 */   public final List<EntityPlayer> players = new CopyOnWriteArrayList<>();
/*   57 */   private final Map<UUID, EntityPlayer> j = Maps.newHashMap(); private final GameProfileBanList k; private final IpBanList l; private final OpList operators; private final WhiteList whitelist; Map<UUID, EntityPlayer> getUUIDMap() { return this.j; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   62 */   private final Map<UUID, EntityPlayer> pendingPlayers = Maps.newHashMap(); public final WorldNBTStorage playerFileData; private boolean hasWhitelist; private final IRegistryCustom.Dimension s; protected int maxPlayers;
/*      */   private int viewDistance;
/*      */   private EnumGamemode u;
/*      */   private boolean v;
/*      */   private int w;
/*      */   private CraftServer cserver;
/*      */   
/*      */   public final void setMaxPlayers(int maxPlayers) {
/*   70 */     this.maxPlayers = maxPlayers;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   78 */   private final Map<String, EntityPlayer> playersByName = new HashMap<>(); @Nullable
/*      */   String collideRuleTeamName;
/*      */   
/*      */   public PlayerList(MinecraftServer minecraftserver, IRegistryCustom.Dimension iregistrycustom_dimension, WorldNBTStorage worldnbtstorage, int i) {
/*   82 */     this.cserver = minecraftserver.server = new CraftServer((DedicatedServer)minecraftserver, this);
/*   83 */     minecraftserver.console = (ConsoleCommandSender)new TerminalConsoleCommandSender();
/*      */ 
/*      */     
/*   86 */     this.k = new GameProfileBanList(b);
/*   87 */     this.l = new IpBanList(c);
/*   88 */     this.operators = new OpList(d);
/*   89 */     this.whitelist = new WhiteList(e);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   94 */     this.server = minecraftserver;
/*   95 */     this.s = iregistrycustom_dimension;
/*   96 */     this.maxPlayers = i;
/*   97 */     this.playerFileData = worldnbtstorage;
/*      */   }
/*      */   public void a(NetworkManager networkmanager, EntityPlayer entityplayer) {
/*      */     ResourceKey<World> resourcekey;
/*  101 */     EntityPlayer prev = this.pendingPlayers.put(entityplayer.getUniqueID(), entityplayer);
/*  102 */     if (prev != null) {
/*  103 */       disconnectPendingPlayer(prev);
/*      */     }
/*  105 */     entityplayer.networkManager = networkmanager;
/*  106 */     entityplayer.loginTime = System.currentTimeMillis();
/*  107 */     GameProfile gameprofile = entityplayer.getProfile();
/*  108 */     UserCache usercache = this.server.getUserCache();
/*  109 */     GameProfile gameprofile1 = usercache.getProfile(gameprofile.getId());
/*  110 */     String s = (gameprofile1 == null) ? gameprofile.getName() : gameprofile1.getName();
/*      */     
/*  112 */     usercache.a(gameprofile);
/*  113 */     NBTTagCompound nbttagcompound = a(entityplayer);
/*      */ 
/*      */     
/*  116 */     if (nbttagcompound != null && nbttagcompound.hasKey("bukkit")) {
/*  117 */       NBTTagCompound bukkit = nbttagcompound.getCompound("bukkit");
/*  118 */       s = bukkit.hasKeyOfType("lastKnownName", 8) ? bukkit.getString("lastKnownName") : s;
/*  119 */     }  String lastKnownName = s;
/*      */ 
/*      */ 
/*      */     
/*  123 */     if (nbttagcompound != null && nbttagcompound.hasKey("WorldUUIDMost") && nbttagcompound.hasKey("WorldUUIDLeast")) {
/*  124 */       UUID uid = new UUID(nbttagcompound.getLong("WorldUUIDMost"), nbttagcompound.getLong("WorldUUIDLeast"));
/*  125 */       World bWorld = Bukkit.getServer().getWorld(uid);
/*  126 */       if (bWorld != null) {
/*  127 */         resourcekey = ((CraftWorld)bWorld).getHandle().getDimensionKey();
/*      */       } else {
/*  129 */         resourcekey = World.OVERWORLD;
/*      */       } 
/*  131 */     } else if (nbttagcompound != null) {
/*      */ 
/*      */       
/*  134 */       DataResult<ResourceKey<World>> dataresult = DimensionManager.a(new Dynamic(DynamicOpsNBT.a, nbttagcompound.get("Dimension")));
/*  135 */       Logger logger = LOGGER;
/*      */       
/*  137 */       logger.getClass();
/*  138 */       Objects.requireNonNull(logger); resourcekey = dataresult.resultOrPartial(logger::error).orElse(World.OVERWORLD);
/*      */     } else {
/*  140 */       resourcekey = World.OVERWORLD;
/*      */     } 
/*      */     
/*  143 */     ResourceKey<World> resourcekey1 = resourcekey;
/*  144 */     WorldServer worldserver = this.server.getWorldServer(resourcekey1);
/*      */ 
/*      */     
/*  147 */     if (worldserver == null) {
/*  148 */       LOGGER.warn("Unknown respawn dimension {}, defaulting to overworld", resourcekey1);
/*  149 */       worldserver1 = this.server.E();
/*      */     } else {
/*  151 */       worldserver1 = worldserver;
/*      */     } 
/*      */     
/*  154 */     if (nbttagcompound == null) entityplayer.moveToSpawn(worldserver1);
/*      */     
/*  156 */     entityplayer.spawnIn(worldserver1);
/*  157 */     entityplayer.playerInteractManager.a((WorldServer)entityplayer.world);
/*  158 */     String s1 = "local";
/*      */     
/*  160 */     if (networkmanager.getSocketAddress() != null) {
/*  161 */       s1 = networkmanager.getSocketAddress().toString();
/*      */     }
/*      */ 
/*      */     
/*  165 */     CraftPlayer craftPlayer = entityplayer.getBukkitEntity();
/*  166 */     PlayerInitialSpawnEvent playerInitialSpawnEvent = new PlayerInitialSpawnEvent((Player)craftPlayer, craftPlayer.getLocation());
/*  167 */     Bukkit.getPluginManager().callEvent((Event)playerInitialSpawnEvent);
/*      */     
/*  169 */     Location loc = playerInitialSpawnEvent.getSpawnLocation();
/*  170 */     WorldServer worldserver1 = ((CraftWorld)loc.getWorld()).getHandle();
/*      */     
/*  172 */     entityplayer.spawnIn(worldserver1);
/*  173 */     entityplayer.playerInteractManager.a((WorldServer)entityplayer.world);
/*  174 */     entityplayer.setPositionRaw(loc.getX(), loc.getY(), loc.getZ());
/*  175 */     entityplayer.setYawPitch(loc.getYaw(), loc.getPitch());
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  180 */     WorldData worlddata = worldserver1.getWorldData();
/*      */     
/*  182 */     a(entityplayer, (EntityPlayer)null, worldserver1);
/*  183 */     PlayerConnection playerconnection = new PlayerConnection(this.server, networkmanager, entityplayer);
/*  184 */     GameRules gamerules = worldserver1.getGameRules();
/*  185 */     boolean flag = gamerules.getBoolean(GameRules.DO_IMMEDIATE_RESPAWN);
/*  186 */     boolean flag1 = gamerules.getBoolean(GameRules.REDUCED_DEBUG_INFO);
/*      */ 
/*      */     
/*  189 */     playerconnection.sendPacket(new PacketPlayOutLogin(entityplayer.getId(), entityplayer.playerInteractManager.getGameMode(), entityplayer.playerInteractManager.c(), BiomeManager.a(worldserver1.getSeed()), worlddata.isHardcore(), this.server.F(), this.s, worldserver1.getDimensionManager(), worldserver1.getDimensionKey(), getMaxPlayers(), (worldserver1.getChunkProvider()).playerChunkMap.getLoadViewDistance(), flag1, !flag, worldserver1.isDebugWorld(), worldserver1.isFlatWorld()));
/*  190 */     entityplayer.getBukkitEntity().sendSupportedChannels();
/*  191 */     playerconnection.sendPacket(new PacketPlayOutCustomPayload(PacketPlayOutCustomPayload.a, (new PacketDataSerializer(Unpooled.buffer())).a(getServer().getServerModName())));
/*  192 */     playerconnection.sendPacket(new PacketPlayOutServerDifficulty(worlddata.getDifficulty(), worlddata.isDifficultyLocked()));
/*  193 */     playerconnection.sendPacket(new PacketPlayOutAbilities(entityplayer.abilities));
/*  194 */     playerconnection.sendPacket(new PacketPlayOutHeldItemSlot(entityplayer.inventory.itemInHandIndex));
/*  195 */     playerconnection.sendPacket(new PacketPlayOutRecipeUpdate(this.server.getCraftingManager().b()));
/*  196 */     playerconnection.sendPacket(new PacketPlayOutTags(this.server.getTagRegistry()));
/*  197 */     playerconnection.sendPacket(new PacketPlayOutEntityStatus(entityplayer, (byte)(worldserver1.getGameRules().getBoolean(GameRules.REDUCED_DEBUG_INFO) ? 22 : 23)));
/*  198 */     d(entityplayer);
/*  199 */     entityplayer.getStatisticManager().c();
/*  200 */     entityplayer.getRecipeBook().a(entityplayer);
/*  201 */     sendScoreboard(worldserver1.getScoreboard(), entityplayer);
/*  202 */     this.server.invalidatePingSample();
/*      */     
/*  204 */     WorldServer finalWorldserver = worldserver1;
/*  205 */     int chunkX = loc.getBlockX() >> 4;
/*  206 */     int chunkZ = loc.getBlockZ() >> 4;
/*  207 */     ChunkCoordIntPair pos = new ChunkCoordIntPair(chunkX, chunkZ);
/*  208 */     PlayerChunkMap playerChunkMap = (worldserver1.getChunkProvider()).playerChunkMap;
/*  209 */     playerChunkMap.chunkDistanceManager.addTicketAtLevel(TicketType.LOGIN, pos, 31, Long.valueOf(pos.pair()));
/*  210 */     worldserver1.getChunkProvider().markAreaHighPriority(pos, 28, 3);
/*  211 */     worldserver1.getChunkProvider().getChunkAtAsynchronously(chunkX, chunkZ, true, false).thenApply(chunk -> {
/*      */           PlayerChunk updatingChunk = playerChunkMap.getUpdatingChunk(pos.pair());
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           return (CompletableFuture)((updatingChunk != null) ? updatingChunk.getEntityTickingFuture() : CompletableFuture.completedFuture(chunk));
/*  218 */         }).thenAccept(chunk -> playerconnection.playerJoinReady = (()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   EntityPlayer getActivePlayer(UUID uuid) {
/*  229 */     EntityPlayer player = getUUIDMap().get(uuid);
/*  230 */     return (player != null) ? player : this.pendingPlayers.get(uuid);
/*      */   }
/*      */   
/*      */   void disconnectPendingPlayer(EntityPlayer entityplayer) {
/*  234 */     ChatMessage msg = new ChatMessage("multiplayer.disconnect.duplicate_login", new Object[0]);
/*  235 */     entityplayer.networkManager.sendPacket(new PacketPlayOutKickDisconnect(msg), future -> {
/*      */           entityplayer.networkManager.close(msg);
/*      */           entityplayer.networkManager = null;
/*      */         });
/*      */   }
/*      */   private void postChunkLoadJoin(EntityPlayer entityplayer, WorldServer worldserver1, NetworkManager networkmanager, PlayerConnection playerconnection, NBTTagCompound nbttagcompound, String s1, String s) {
/*      */     ChatMessage chatmessage;
/*  242 */     this.pendingPlayers.remove(entityplayer.getUniqueID(), entityplayer);
/*  243 */     if (!networkmanager.isConnected()) {
/*      */       return;
/*      */     }
/*  246 */     entityplayer.didPlayerJoinEvent = true;
/*      */ 
/*      */ 
/*      */     
/*  250 */     if (entityplayer.getProfile().getName().equalsIgnoreCase(s)) {
/*  251 */       chatmessage = new ChatMessage("multiplayer.player.joined", new Object[] { entityplayer.getScoreboardDisplayName() });
/*      */     } else {
/*  253 */       chatmessage = new ChatMessage("multiplayer.player.joined.renamed", new Object[] { entityplayer.getScoreboardDisplayName(), s });
/*      */     } 
/*      */     
/*  256 */     chatmessage.a(EnumChatFormat.YELLOW);
/*  257 */     String joinMessage = CraftChatMessage.fromComponent(chatmessage);
/*      */     
/*  259 */     playerconnection.a(entityplayer.locX(), entityplayer.locY(), entityplayer.locZ(), entityplayer.yaw, entityplayer.pitch);
/*  260 */     this.players.add(entityplayer);
/*  261 */     this.playersByName.put(entityplayer.getName().toLowerCase(Locale.ROOT), entityplayer);
/*  262 */     this.j.put(entityplayer.getUniqueID(), entityplayer);
/*      */ 
/*      */ 
/*      */     
/*  266 */     entityplayer.supressTrackerForLogin = true;
/*  267 */     worldserver1.addPlayerJoin(entityplayer);
/*  268 */     this.server.getBossBattleCustomData().a(entityplayer);
/*  269 */     mountSavedVehicle(entityplayer, worldserver1, nbttagcompound);
/*      */ 
/*      */     
/*  272 */     PlayerJoinEvent playerJoinEvent = new PlayerJoinEvent(this.cserver.getPlayer(entityplayer), joinMessage);
/*  273 */     this.cserver.getPluginManager().callEvent((Event)playerJoinEvent);
/*      */     
/*  275 */     if (!entityplayer.playerConnection.networkManager.isConnected()) {
/*      */       return;
/*      */     }
/*      */     
/*  279 */     joinMessage = playerJoinEvent.getJoinMessage();
/*      */     
/*  281 */     if (joinMessage != null && joinMessage.length() > 0)
/*      */     {
/*  283 */       this.server.getPlayerList().sendMessage(CraftChatMessage.fromString(joinMessage));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  289 */     PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, new EntityPlayer[] { entityplayer });
/*      */     
/*  291 */     for (int i = 0; i < this.players.size(); i++) {
/*  292 */       EntityPlayer entityplayer1 = this.players.get(i);
/*      */       
/*  294 */       if (entityplayer1.getBukkitEntity().canSee((Player)entityplayer.getBukkitEntity())) {
/*  295 */         entityplayer1.playerConnection.sendPacket(packet);
/*      */       }
/*      */       
/*  298 */       if (entityplayer.getBukkitEntity().canSee((Player)entityplayer1.getBukkitEntity()))
/*      */       {
/*      */ 
/*      */         
/*  302 */         entityplayer.playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, new EntityPlayer[] { entityplayer1 })); } 
/*      */     } 
/*  304 */     entityplayer.sentListPacket = true;
/*  305 */     entityplayer.supressTrackerForLogin = false;
/*  306 */     (((WorldServer)entityplayer.world).getChunkProvider()).playerChunkMap.addEntity(entityplayer);
/*      */ 
/*      */     
/*  309 */     entityplayer.playerConnection.sendPacket(new PacketPlayOutEntityMetadata(entityplayer.getId(), entityplayer.datawatcher, true));
/*      */ 
/*      */     
/*  312 */     if (entityplayer.world == worldserver1 && !worldserver1.getPlayers().contains(entityplayer)) {
/*  313 */       worldserver1.addPlayerJoin(entityplayer);
/*  314 */       this.server.getBossBattleCustomData().a(entityplayer);
/*      */     } 
/*      */     
/*  317 */     worldserver1 = entityplayer.getWorldServer();
/*      */     
/*  319 */     a(entityplayer, worldserver1);
/*  320 */     if (!this.server.getResourcePack().isEmpty()) {
/*  321 */       entityplayer.setResourcePack(this.server.getResourcePack(), this.server.getResourcePackHash());
/*      */     }
/*      */     
/*  324 */     Iterator<MobEffect> iterator = entityplayer.getEffects().iterator();
/*      */     
/*  326 */     while (iterator.hasNext()) {
/*  327 */       MobEffect mobeffect = iterator.next();
/*      */       
/*  329 */       playerconnection.sendPacket(new PacketPlayOutEntityEffect(entityplayer.getId(), mobeffect));
/*      */     } 
/*      */ 
/*      */     
/*  333 */     onPlayerJoinFinish(entityplayer, worldserver1, s1);
/*      */   }
/*      */   
/*      */   private void mountSavedVehicle(EntityPlayer entityplayer, WorldServer worldserver1, NBTTagCompound nbttagcompound) {
/*  337 */     if (nbttagcompound != null && nbttagcompound.hasKeyOfType("RootVehicle", 10)) {
/*  338 */       NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("RootVehicle");
/*      */       
/*  340 */       WorldServer finalWorldServer = worldserver1;
/*  341 */       Entity entity = EntityTypes.a(nbttagcompound1.getCompound("Entity"), finalWorldServer, entity1 -> !finalWorldServer.addEntitySerialized(entity1, CreatureSpawnEvent.SpawnReason.MOUNT) ? null : entity1);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  346 */       if (entity != null) {
/*      */         UUID uuid;
/*      */         
/*  349 */         if (nbttagcompound1.b("Attach")) {
/*  350 */           uuid = nbttagcompound1.a("Attach");
/*      */         } else {
/*  352 */           uuid = null;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  358 */         if (entity.getUniqueID().equals(uuid)) {
/*  359 */           entityplayer.a(entity, true);
/*      */         } else {
/*  361 */           Iterator<Entity> iterator1 = entity.getAllPassengers().iterator();
/*      */           
/*  363 */           while (iterator1.hasNext()) {
/*  364 */             Entity entity1 = iterator1.next();
/*  365 */             if (entity1.getUniqueID().equals(uuid)) {
/*  366 */               entityplayer.a(entity1, true);
/*      */               
/*      */               break;
/*      */             } 
/*      */           } 
/*      */         } 
/*  372 */         if (!entityplayer.isPassenger()) {
/*  373 */           LOGGER.warn("Couldn't reattach entity to player");
/*  374 */           worldserver1.removeEntity(entity);
/*  375 */           Iterator<Entity> iterator1 = entity.getAllPassengers().iterator();
/*      */           
/*  377 */           while (iterator1.hasNext()) {
/*  378 */             Entity entity1 = iterator1.next();
/*  379 */             worldserver1.removeEntity(entity1);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void onPlayerJoinFinish(EntityPlayer entityplayer, WorldServer worldserver1, String s1) {
/*  389 */     entityplayer.syncInventory();
/*      */     
/*  391 */     Scoreboard scoreboard = getServer().getWorldServer(World.OVERWORLD).getScoreboard();
/*  392 */     ScoreboardTeam collideRuleTeam = scoreboard.getTeam(this.collideRuleTeamName);
/*  393 */     if (this.collideRuleTeamName != null && collideRuleTeam != null && entityplayer.getScoreboardTeam() == null) {
/*  394 */       scoreboard.addPlayerToTeam(entityplayer.getName(), collideRuleTeam);
/*      */     }
/*      */ 
/*      */     
/*  398 */     LOGGER.info("{}[{}] logged in with entity id {} at ([{}]{}, {}, {})", entityplayer.getDisplayName().getString(), s1, Integer.valueOf(entityplayer.getId()), worldserver1.worldDataServer.getName(), Double.valueOf(entityplayer.locX()), Double.valueOf(entityplayer.locY()), Double.valueOf(entityplayer.locZ()));
/*      */   }
/*      */   
/*      */   public void sendScoreboard(ScoreboardServer scoreboardserver, EntityPlayer entityplayer) {
/*  402 */     Set<ScoreboardObjective> set = Sets.newHashSet();
/*  403 */     Iterator<ScoreboardTeam> iterator = scoreboardserver.getTeams().iterator();
/*      */     
/*  405 */     while (iterator.hasNext()) {
/*  406 */       ScoreboardTeam scoreboardteam = iterator.next();
/*      */       
/*  408 */       entityplayer.playerConnection.sendPacket(new PacketPlayOutScoreboardTeam(scoreboardteam, 0));
/*      */     } 
/*      */     
/*  411 */     for (int i = 0; i < 19; i++) {
/*  412 */       ScoreboardObjective scoreboardobjective = scoreboardserver.getObjectiveForSlot(i);
/*      */       
/*  414 */       if (scoreboardobjective != null && !set.contains(scoreboardobjective)) {
/*  415 */         List<Packet<?>> list = scoreboardserver.getScoreboardScorePacketsForObjective(scoreboardobjective);
/*  416 */         Iterator<Packet<?>> iterator1 = list.iterator();
/*      */         
/*  418 */         while (iterator1.hasNext()) {
/*  419 */           Packet<?> packet = iterator1.next();
/*      */           
/*  421 */           entityplayer.playerConnection.sendPacket(packet);
/*      */         } 
/*      */         
/*  424 */         set.add(scoreboardobjective);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPlayerFileData(WorldServer worldserver) {
/*  431 */     if (this.playerFileData != null)
/*  432 */       return;  worldserver.getWorldBorder().a(new IWorldBorderListener()
/*      */         {
/*      */           public void a(WorldBorder worldborder, double d0) {
/*  435 */             PlayerList.this.sendAll(new PacketPlayOutWorldBorder(worldborder, PacketPlayOutWorldBorder.EnumWorldBorderAction.SET_SIZE), worldborder.world);
/*      */           }
/*      */ 
/*      */           
/*      */           public void a(WorldBorder worldborder, double d0, double d1, long i) {
/*  440 */             PlayerList.this.sendAll(new PacketPlayOutWorldBorder(worldborder, PacketPlayOutWorldBorder.EnumWorldBorderAction.LERP_SIZE), worldborder.world);
/*      */           }
/*      */ 
/*      */           
/*      */           public void a(WorldBorder worldborder, double d0, double d1) {
/*  445 */             PlayerList.this.sendAll(new PacketPlayOutWorldBorder(worldborder, PacketPlayOutWorldBorder.EnumWorldBorderAction.SET_CENTER), worldborder.world);
/*      */           }
/*      */ 
/*      */           
/*      */           public void a(WorldBorder worldborder, int i) {
/*  450 */             PlayerList.this.sendAll(new PacketPlayOutWorldBorder(worldborder, PacketPlayOutWorldBorder.EnumWorldBorderAction.SET_WARNING_TIME), worldborder.world);
/*      */           }
/*      */ 
/*      */           
/*      */           public void b(WorldBorder worldborder, int i) {
/*  455 */             PlayerList.this.sendAll(new PacketPlayOutWorldBorder(worldborder, PacketPlayOutWorldBorder.EnumWorldBorderAction.SET_WARNING_BLOCKS), worldborder.world);
/*      */           }
/*      */ 
/*      */           
/*      */           public void b(WorldBorder worldborder, double d0) {}
/*      */ 
/*      */           
/*      */           public void c(WorldBorder worldborder, double d0) {}
/*      */         });
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public NBTTagCompound a(EntityPlayer entityplayer) {
/*  468 */     NBTTagCompound nbttagcompound1, nbttagcompound = this.server.getSaveData().y();
/*      */ 
/*      */     
/*  471 */     if (entityplayer.getDisplayName().getString().equals(this.server.getSinglePlayerName()) && nbttagcompound != null) {
/*  472 */       nbttagcompound1 = nbttagcompound;
/*  473 */       entityplayer.load(nbttagcompound);
/*  474 */       LOGGER.debug("loading single player");
/*      */     } else {
/*  476 */       nbttagcompound1 = this.playerFileData.load(entityplayer);
/*      */     } 
/*      */     
/*  479 */     return nbttagcompound1;
/*      */   }
/*      */   
/*      */   protected void savePlayerFile(EntityPlayer entityplayer) {
/*  483 */     if (!entityplayer.getBukkitEntity().isPersistent())
/*  484 */       return;  if (!entityplayer.didPlayerJoinEvent)
/*  485 */       return;  entityplayer.lastSave = MinecraftServer.currentTick;
/*  486 */     this.playerFileData.save(entityplayer);
/*  487 */     ServerStatisticManager serverstatisticmanager = entityplayer.getStatisticManager();
/*      */     
/*  489 */     if (serverstatisticmanager != null) {
/*  490 */       serverstatisticmanager.save();
/*      */     }
/*      */     
/*  493 */     AdvancementDataPlayer advancementdataplayer = entityplayer.getAdvancementData();
/*      */     
/*  495 */     if (advancementdataplayer != null) {
/*  496 */       advancementdataplayer.b();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public String disconnect(EntityPlayer entityplayer) {
/*  502 */     WorldServer worldserver = entityplayer.getWorldServer();
/*      */     
/*  504 */     entityplayer.a(StatisticList.LEAVE_GAME);
/*      */ 
/*      */     
/*  507 */     entityplayer.closeInventory(InventoryCloseEvent.Reason.DISCONNECT);
/*      */     
/*  509 */     PlayerQuitEvent playerQuitEvent = new PlayerQuitEvent(this.cserver.getPlayer(entityplayer), "§e" + entityplayer.getName() + " left the game");
/*  510 */     if (entityplayer.didPlayerJoinEvent) this.cserver.getPluginManager().callEvent((Event)playerQuitEvent); 
/*  511 */     entityplayer.getBukkitEntity().disconnect(playerQuitEvent.getQuitMessage());
/*      */     
/*  513 */     if (this.server.isMainThread()) entityplayer.playerTick();
/*      */ 
/*      */ 
/*      */     
/*  517 */     if (this.collideRuleTeamName != null) {
/*  518 */       Scoreboard scoreBoard = this.server.getWorldServer(World.OVERWORLD).getScoreboard();
/*  519 */       ScoreboardTeam team = scoreBoard.getTeam(this.collideRuleTeamName);
/*  520 */       if (entityplayer.getScoreboardTeam() == team && team != null) {
/*  521 */         scoreBoard.removePlayerFromTeam(entityplayer.getName(), team);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  526 */     savePlayerFile(entityplayer);
/*  527 */     if (entityplayer.isPassenger()) {
/*  528 */       Entity entity = entityplayer.getRootVehicle();
/*      */       
/*  530 */       if (entity.hasSinglePlayerPassenger()) {
/*  531 */         LOGGER.debug("Removing player mount");
/*  532 */         entityplayer.stopRiding();
/*  533 */         worldserver.removeEntity(entity);
/*  534 */         entity.dead = true;
/*      */ 
/*      */ 
/*      */         
/*  538 */         for (Iterator<Entity> iterator = entity.getAllPassengers().iterator(); iterator.hasNext(); entity1.dead = true) {
/*  539 */           Entity entity1 = iterator.next();
/*  540 */           worldserver.removeEntity(entity1);
/*      */         } 
/*      */         
/*  543 */         worldserver.getChunkAt(entityplayer.chunkX, entityplayer.chunkZ).markDirty();
/*      */       } 
/*      */     } 
/*      */     
/*  547 */     entityplayer.decouple();
/*  548 */     worldserver.removePlayer(entityplayer);
/*  549 */     entityplayer.getAdvancementData().a();
/*  550 */     this.players.remove(entityplayer);
/*  551 */     this.playersByName.remove(entityplayer.getName().toLowerCase(Locale.ROOT));
/*  552 */     this.server.getBossBattleCustomData().b(entityplayer);
/*  553 */     UUID uuid = entityplayer.getUniqueID();
/*  554 */     EntityPlayer entityplayer1 = this.j.get(uuid);
/*      */     
/*  556 */     if (entityplayer1 == entityplayer) {
/*  557 */       this.j.remove(uuid);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  564 */     entityplayer1 = this.pendingPlayers.get(uuid);
/*  565 */     if (entityplayer1 == entityplayer) {
/*  566 */       this.pendingPlayers.remove(uuid);
/*      */     }
/*  568 */     entityplayer.networkManager = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  573 */     PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, new EntityPlayer[] { entityplayer });
/*  574 */     for (int i = 0; i < this.players.size(); i++) {
/*  575 */       EntityPlayer entityplayer2 = this.players.get(i);
/*      */       
/*  577 */       if (entityplayer2.getBukkitEntity().canSee((Player)entityplayer.getBukkitEntity())) {
/*  578 */         entityplayer2.playerConnection.sendPacket(packet);
/*      */       } else {
/*  580 */         entityplayer2.getBukkitEntity().removeDisconnectingPlayer((Player)entityplayer.getBukkitEntity());
/*      */       } 
/*      */     } 
/*      */     
/*  584 */     this.cserver.getScoreboardManager().removePlayer((Player)entityplayer.getBukkitEntity());
/*      */ 
/*      */     
/*  587 */     return entityplayer.didPlayerJoinEvent ? playerQuitEvent.getQuitMessage() : null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityPlayer attemptLogin(LoginListener loginlistener, GameProfile gameprofile, String hostname) {
/*  595 */     UUID uuid = EntityHuman.a(gameprofile);
/*  596 */     List<EntityPlayer> list = Lists.newArrayList();
/*      */ 
/*      */ 
/*      */     
/*  600 */     for (int i = 0; i < this.players.size(); i++) {
/*  601 */       EntityPlayer entityPlayer = this.players.get(i);
/*  602 */       if (entityPlayer.getUniqueID().equals(uuid)) {
/*  603 */         list.add(entityPlayer);
/*      */       }
/*      */     } 
/*      */     
/*  607 */     EntityPlayer entityplayer = this.pendingPlayers.get(uuid);
/*  608 */     if (entityplayer != null) {
/*  609 */       this.pendingPlayers.remove(uuid);
/*  610 */       disconnectPendingPlayer(entityplayer);
/*      */     } 
/*      */ 
/*      */     
/*  614 */     Iterator<EntityPlayer> iterator = list.iterator();
/*      */     
/*  616 */     while (iterator.hasNext()) {
/*  617 */       entityplayer = iterator.next();
/*  618 */       savePlayerFile(entityplayer);
/*  619 */       entityplayer.playerConnection.disconnect(new ChatMessage("multiplayer.disconnect.duplicate_login", new Object[0]));
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  625 */     SocketAddress socketaddress = loginlistener.networkManager.getSocketAddress();
/*      */     
/*  627 */     EntityPlayer entity = new EntityPlayer(this.server, this.server.getWorldServer(World.OVERWORLD), gameprofile, new PlayerInteractManager(this.server.getWorldServer(World.OVERWORLD)));
/*  628 */     entity.isRealPlayer = true;
/*  629 */     CraftPlayer craftPlayer = entity.getBukkitEntity();
/*  630 */     PlayerLoginEvent event = new PlayerLoginEvent((Player)craftPlayer, hostname, ((InetSocketAddress)socketaddress).getAddress(), ((InetSocketAddress)loginlistener.networkManager.getRawAddress()).getAddress());
/*      */     
/*      */     GameProfileBanEntry gameprofilebanentry;
/*      */     
/*  634 */     if (getProfileBans().isBanned(gameprofile) && (gameprofilebanentry = getProfileBans().get(gameprofile)) != null) {
/*      */ 
/*      */       
/*  637 */       ChatMessage chatmessage = new ChatMessage("multiplayer.disconnect.banned.reason", new Object[] { gameprofilebanentry.getReason() });
/*  638 */       if (gameprofilebanentry.getExpires() != null) {
/*  639 */         chatmessage.addSibling(new ChatMessage("multiplayer.disconnect.banned.expiration", new Object[] { g.format(gameprofilebanentry.getExpires()) }));
/*      */       }
/*      */ 
/*      */       
/*  643 */       if (!gameprofilebanentry.hasExpired()) event.disallow(PlayerLoginEvent.Result.KICK_BANNED, CraftChatMessage.fromComponent(chatmessage)); 
/*  644 */     } else if (!isWhitelisted(gameprofile, event)) {
/*  645 */       ChatMessage chatmessage = new ChatMessage("multiplayer.disconnect.not_whitelisted");
/*      */     }
/*  647 */     else if (getIPBans().isBanned(socketaddress) && getIPBans().get(socketaddress) != null && !getIPBans().get(socketaddress).hasExpired()) {
/*  648 */       IpBanEntry ipbanentry = this.l.get(socketaddress);
/*      */       
/*  650 */       ChatMessage chatmessage = new ChatMessage("multiplayer.disconnect.banned_ip.reason", new Object[] { ipbanentry.getReason() });
/*  651 */       if (ipbanentry.getExpires() != null) {
/*  652 */         chatmessage.addSibling(new ChatMessage("multiplayer.disconnect.banned_ip.expiration", new Object[] { g.format(ipbanentry.getExpires()) }));
/*      */       }
/*      */ 
/*      */       
/*  656 */       event.disallow(PlayerLoginEvent.Result.KICK_BANNED, CraftChatMessage.fromComponent(chatmessage));
/*      */     
/*      */     }
/*  659 */     else if (this.players.size() >= this.maxPlayers && !f(gameprofile)) {
/*  660 */       event.disallow(PlayerLoginEvent.Result.KICK_FULL, SpigotConfig.serverFullMessage);
/*      */     } 
/*      */ 
/*      */     
/*  664 */     this.cserver.getPluginManager().callEvent((Event)event);
/*  665 */     if (event.getResult() != PlayerLoginEvent.Result.ALLOWED) {
/*  666 */       loginlistener.disconnect(event.getKickMessage());
/*  667 */       return null;
/*      */     } 
/*  669 */     return entity;
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
/*      */   public EntityPlayer processLogin(GameProfile gameprofile, EntityPlayer player) {
/*  710 */     return player;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityPlayer moveToWorld(EntityPlayer entityplayer, boolean flag) {
/*  716 */     return moveToWorld(entityplayer, this.server.getWorldServer(entityplayer.getSpawnDimension()), flag, null, true);
/*      */   }
/*      */   
/*      */   public EntityPlayer moveToWorld(EntityPlayer entityplayer, WorldServer worldserver, boolean flag, Location location, boolean avoidSuffocation) {
/*  720 */     entityplayer.stopRiding();
/*  721 */     this.players.remove(entityplayer);
/*  722 */     this.playersByName.remove(entityplayer.getName().toLowerCase(Locale.ROOT));
/*  723 */     entityplayer.getWorldServer().removePlayer(entityplayer);
/*  724 */     BlockPosition blockposition = entityplayer.getSpawn();
/*  725 */     float f = entityplayer.getSpawnAngle();
/*  726 */     boolean flag1 = entityplayer.isSpawnForced();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  748 */     EntityPlayer entityplayer1 = entityplayer;
/*  749 */     World fromWorld = entityplayer.getBukkitEntity().getWorld();
/*  750 */     entityplayer.viewingCredits = false;
/*      */ 
/*      */     
/*  753 */     entityplayer1.playerConnection = entityplayer.playerConnection;
/*  754 */     entityplayer1.copyFrom(entityplayer, flag);
/*  755 */     entityplayer1.e(entityplayer.getId());
/*  756 */     entityplayer1.a(entityplayer.getMainHand());
/*  757 */     Iterator<String> iterator = entityplayer.getScoreboardTags().iterator();
/*      */     
/*  759 */     while (iterator.hasNext()) {
/*  760 */       String s = iterator.next();
/*      */       
/*  762 */       entityplayer1.addScoreboardTag(s);
/*      */     } 
/*      */ 
/*      */     
/*  766 */     boolean flag2 = false;
/*      */ 
/*      */     
/*  769 */     boolean isBedSpawn = false;
/*  770 */     boolean isRespawn = false;
/*  771 */     boolean isLocAltered = false;
/*      */ 
/*      */ 
/*      */     
/*  775 */     if (location == null) {
/*      */       
/*  777 */       WorldServer worldServer = this.server.getWorldServer(entityplayer.getSpawnDimension());
/*  778 */       if (worldServer != null) {
/*      */         Optional<?> optional;
/*      */         
/*  781 */         if (blockposition != null) {
/*  782 */           optional = EntityHuman.getBed(worldServer, blockposition, f, flag1, true);
/*      */         } else {
/*  784 */           optional = Optional.empty();
/*      */         } 
/*      */         
/*  787 */         if (optional.isPresent()) {
/*  788 */           IBlockData iblockdata = worldServer.getType(blockposition);
/*  789 */           boolean flag3 = iblockdata.a(Blocks.RESPAWN_ANCHOR);
/*  790 */           Vec3D vec3d = (Vec3D)optional.get();
/*      */ 
/*      */           
/*  793 */           if (!iblockdata.a(TagsBlock.BEDS) && !flag3) {
/*  794 */             float f1 = f;
/*      */           } else {
/*  796 */             Vec3D vec3d1 = Vec3D.c(blockposition).d(vec3d).d();
/*      */             
/*  798 */             float f1 = (float)MathHelper.g(MathHelper.d(vec3d1.z, vec3d1.x) * 57.2957763671875D - 90.0D);
/*      */           } 
/*      */           
/*  801 */           entityplayer1.setRespawnPosition(worldServer.getDimensionKey(), blockposition, f, flag1, false);
/*  802 */           flag2 = (!flag && flag3);
/*  803 */           isBedSpawn = true;
/*  804 */           location = new Location((World)worldServer.getWorld(), vec3d.x, vec3d.y, vec3d.z);
/*  805 */         } else if (blockposition != null) {
/*  806 */           entityplayer1.playerConnection.sendPacket(new PacketPlayOutGameStateChange(PacketPlayOutGameStateChange.a, 0.0F));
/*      */         } 
/*      */       } 
/*      */       
/*  810 */       if (location == null) {
/*  811 */         worldServer = this.server.getWorldServer(World.OVERWORLD);
/*  812 */         blockposition = entityplayer1.getSpawnPoint(worldServer);
/*  813 */         location = new Location((World)worldServer.getWorld(), (blockposition.getX() + 0.5F), (blockposition.getY() + 0.1F), (blockposition.getZ() + 0.5F));
/*      */       } 
/*      */       
/*  816 */       Player respawnPlayer = this.cserver.getPlayer(entityplayer1);
/*  817 */       PlayerRespawnEvent respawnEvent = new PlayerRespawnEvent(respawnPlayer, location, (isBedSpawn && !flag2), flag2);
/*  818 */       this.cserver.getPluginManager().callEvent((Event)respawnEvent);
/*      */       
/*  820 */       if (entityplayer.playerConnection.isDisconnected()) {
/*  821 */         return entityplayer;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  826 */       if (!location.equals(respawnEvent.getRespawnLocation())) {
/*  827 */         location = respawnEvent.getRespawnLocation();
/*  828 */         isLocAltered = true;
/*      */       } 
/*      */       
/*  831 */       if (!flag) entityplayer.reset(); 
/*  832 */       isRespawn = true;
/*      */     } else {
/*  834 */       location.setWorld((World)worldserver.getWorld());
/*      */     } 
/*  836 */     WorldServer worldserver1 = ((CraftWorld)location.getWorld()).getHandle();
/*  837 */     entityplayer1.forceSetPositionRotation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
/*      */ 
/*      */     
/*  840 */     worldserver1.getChunkProvider().addTicket(TicketType.POST_TELEPORT, new ChunkCoordIntPair(location.getBlockX() >> 4, location.getBlockZ() >> 4), 1, Integer.valueOf(entityplayer.getId()));
/*  841 */     entityplayer1.forceCheckHighPriority();
/*  842 */     while (avoidSuffocation && !worldserver1.getCubes(entityplayer1) && entityplayer1.locY() < 256.0D) {
/*  843 */       entityplayer1.setPosition(entityplayer1.locX(), entityplayer1.locY() + 1.0D, entityplayer1.locZ());
/*      */     }
/*      */     
/*  846 */     WorldData worlddata = worldserver1.getWorldData();
/*  847 */     entityplayer1.playerConnection.sendPacket(new PacketPlayOutRespawn(worldserver1.getDimensionManager(), worldserver1.getDimensionKey(), BiomeManager.a(worldserver1.getSeed()), entityplayer1.playerInteractManager.getGameMode(), entityplayer1.playerInteractManager.c(), worldserver1.isDebugWorld(), worldserver1.isFlatWorld(), flag));
/*  848 */     entityplayer1.playerConnection.sendPacket(new PacketPlayOutViewDistance((worldserver1.getChunkProvider()).playerChunkMap.getLoadViewDistance()));
/*  849 */     entityplayer1.spawnIn(worldserver1);
/*  850 */     entityplayer1.dead = false;
/*  851 */     entityplayer1.playerConnection.teleport(new Location((World)worldserver1.getWorld(), entityplayer1.locX(), entityplayer1.locY(), entityplayer1.locZ(), entityplayer1.yaw, entityplayer1.pitch));
/*  852 */     entityplayer1.setSneaking(false);
/*      */ 
/*      */     
/*  855 */     entityplayer1.playerConnection.sendPacket(new PacketPlayOutSpawnPosition(worldserver1.getSpawn(), worldserver1.v()));
/*  856 */     entityplayer1.playerConnection.sendPacket(new PacketPlayOutServerDifficulty(worlddata.getDifficulty(), worlddata.isDifficultyLocked()));
/*  857 */     entityplayer1.playerConnection.sendPacket(new PacketPlayOutExperience(entityplayer1.exp, entityplayer1.expTotal, entityplayer1.expLevel));
/*  858 */     a(entityplayer1, worldserver1);
/*  859 */     d(entityplayer1);
/*  860 */     if (!entityplayer.playerConnection.isDisconnected()) {
/*  861 */       worldserver1.addPlayerRespawn(entityplayer1);
/*  862 */       this.players.add(entityplayer1);
/*  863 */       this.playersByName.put(entityplayer1.getName().toLowerCase(Locale.ROOT), entityplayer1);
/*  864 */       this.j.put(entityplayer1.getUniqueID(), entityplayer1);
/*      */     } 
/*      */     
/*  867 */     entityplayer1.setHealth(entityplayer1.getHealth());
/*      */     
/*  869 */     if (flag2 && !isLocAltered) {
/*  870 */       IBlockData data = worldserver1.getType(blockposition);
/*  871 */       worldserver1.setTypeAndData(blockposition, data.set(BlockRespawnAnchor.a, Integer.valueOf(((Integer)data.get(BlockRespawnAnchor.a)).intValue() - 1)), 3);
/*  872 */       entityplayer1.playerConnection.sendPacket(new PacketPlayOutNamedSoundEffect(SoundEffects.BLOCK_RESPAWN_ANCHOR_DEPLETE, SoundCategory.BLOCKS, location.getX(), location.getY(), location.getZ(), 1.0F, 1.0F));
/*      */     } 
/*      */ 
/*      */     
/*  876 */     updateClient(entityplayer);
/*  877 */     entityplayer.updateAbilities();
/*  878 */     for (MobEffect o1 : entityplayer.getEffects()) {
/*  879 */       MobEffect mobEffect = o1;
/*  880 */       entityplayer.playerConnection.sendPacket(new PacketPlayOutEntityEffect(entityplayer.getId(), mobEffect));
/*      */     } 
/*      */     
/*  883 */     entityplayer.setSneaking(false);
/*      */ 
/*      */     
/*  886 */     entityplayer.triggerDimensionAdvancements(((CraftWorld)fromWorld).getHandle());
/*      */ 
/*      */     
/*  889 */     if (fromWorld != location.getWorld()) {
/*  890 */       PlayerChangedWorldEvent event = new PlayerChangedWorldEvent((Player)entityplayer.getBukkitEntity(), fromWorld);
/*  891 */       this.server.server.getPluginManager().callEvent((Event)event);
/*      */     } 
/*      */ 
/*      */     
/*  895 */     if (entityplayer.playerConnection.isDisconnected()) {
/*  896 */       savePlayerFile(entityplayer);
/*      */     }
/*      */ 
/*      */     
/*  900 */     if (isRespawn) {
/*  901 */       this.cserver.getPluginManager().callEvent((Event)new PlayerPostRespawnEvent((Player)entityplayer.getBukkitEntity(), location, isBedSpawn));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  906 */     return entityplayer1;
/*      */   }
/*      */   
/*      */   public void d(EntityPlayer entityplayer) {
/*  910 */     GameProfile gameprofile = entityplayer.getProfile();
/*  911 */     int i = this.server.b(gameprofile);
/*      */     
/*  913 */     a(entityplayer, i);
/*      */   }
/*      */   
/*      */   public void tick() {
/*  917 */     if (++this.w > 600) {
/*      */       
/*  919 */       for (int i = 0; i < this.players.size(); i++) {
/*  920 */         final EntityPlayer target = this.players.get(i);
/*      */         
/*  922 */         target.playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.UPDATE_LATENCY, Iterables.filter(this.players, new Predicate<EntityPlayer>()
/*      */                 {
/*      */                   public boolean apply(EntityPlayer input) {
/*  925 */                     return target.getBukkitEntity().canSee((Player)input.getBukkitEntity());
/*      */                   }
/*      */                 })));
/*      */       } 
/*      */       
/*  930 */       this.w = 0;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void sendAll(Packet<?> packet) {
/*  936 */     for (int i = 0; i < this.players.size(); i++) {
/*  937 */       ((EntityPlayer)this.players.get(i)).playerConnection.sendPacket(packet);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendAll(Packet<?> packet, EntityHuman entityhuman) {
/*  944 */     for (int i = 0; i < this.players.size(); i++) {
/*  945 */       EntityPlayer entityplayer = this.players.get(i);
/*  946 */       if (entityhuman == null || !(entityhuman instanceof EntityPlayer) || entityplayer.getBukkitEntity().canSee((Player)((EntityPlayer)entityhuman).getBukkitEntity()))
/*      */       {
/*      */         
/*  949 */         ((EntityPlayer)this.players.get(i)).playerConnection.sendPacket(packet); } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void sendAll(Packet<?> packet, World world) {
/*  954 */     for (int i = 0; i < world.getPlayers().size(); i++) {
/*  955 */       ((EntityPlayer)world.getPlayers().get(i)).playerConnection.sendPacket(packet);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(Packet<?> packet, ResourceKey<World> resourcekey) {
/*  962 */     for (int i = 0; i < this.players.size(); i++) {
/*  963 */       EntityPlayer entityplayer = this.players.get(i);
/*      */       
/*  965 */       if (entityplayer.world.getDimensionKey() == resourcekey) {
/*  966 */         entityplayer.playerConnection.sendPacket(packet);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void a(EntityHuman entityhuman, IChatBaseComponent ichatbasecomponent) {
/*  973 */     ScoreboardTeamBase scoreboardteambase = entityhuman.getScoreboardTeam();
/*      */     
/*  975 */     if (scoreboardteambase != null) {
/*  976 */       Collection<String> collection = scoreboardteambase.getPlayerNameSet();
/*  977 */       Iterator<String> iterator = collection.iterator();
/*      */       
/*  979 */       while (iterator.hasNext()) {
/*  980 */         String s = iterator.next();
/*  981 */         EntityPlayer entityplayer = getPlayer(s);
/*      */         
/*  983 */         if (entityplayer != null && entityplayer != entityhuman) {
/*  984 */           entityplayer.sendMessage(ichatbasecomponent, entityhuman.getUniqueID());
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void b(EntityHuman entityhuman, IChatBaseComponent ichatbasecomponent) {
/*  992 */     ScoreboardTeamBase scoreboardteambase = entityhuman.getScoreboardTeam();
/*      */     
/*  994 */     if (scoreboardteambase == null) {
/*  995 */       sendMessage(ichatbasecomponent, ChatMessageType.SYSTEM, entityhuman.getUniqueID());
/*      */     } else {
/*  997 */       for (int i = 0; i < this.players.size(); i++) {
/*  998 */         EntityPlayer entityplayer = this.players.get(i);
/*      */         
/* 1000 */         if (entityplayer.getScoreboardTeam() != scoreboardteambase) {
/* 1001 */           entityplayer.sendMessage(ichatbasecomponent, entityhuman.getUniqueID());
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public String[] e() {
/* 1009 */     String[] astring = new String[this.players.size()];
/*      */     
/* 1011 */     for (int i = 0; i < this.players.size(); i++) {
/* 1012 */       astring[i] = ((EntityPlayer)this.players.get(i)).getProfile().getName();
/*      */     }
/*      */     
/* 1015 */     return astring;
/*      */   }
/*      */   
/*      */   public GameProfileBanList getProfileBans() {
/* 1019 */     return this.k;
/*      */   }
/*      */   
/*      */   public IpBanList getIPBans() {
/* 1023 */     return this.l;
/*      */   }
/*      */   
/*      */   public void addOp(GameProfile gameprofile) {
/* 1027 */     this.operators.add(new OpListEntry(gameprofile, this.server.g(), this.operators.b(gameprofile)));
/* 1028 */     EntityPlayer entityplayer = getPlayer(gameprofile.getId());
/*      */     
/* 1030 */     if (entityplayer != null) {
/* 1031 */       d(entityplayer);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void removeOp(GameProfile gameprofile) {
/* 1037 */     this.operators.remove(gameprofile);
/* 1038 */     EntityPlayer entityplayer = getPlayer(gameprofile.getId());
/*      */     
/* 1040 */     if (entityplayer != null) {
/* 1041 */       d(entityplayer);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void a(EntityPlayer entityplayer, int i) {
/* 1047 */     if (entityplayer.playerConnection != null) {
/*      */       byte b0;
/*      */       
/* 1050 */       if (i <= 0) {
/* 1051 */         b0 = 24;
/* 1052 */       } else if (i >= 4) {
/* 1053 */         b0 = 28;
/*      */       } else {
/* 1055 */         b0 = (byte)(24 + i);
/*      */       } 
/*      */       
/* 1058 */       entityplayer.playerConnection.sendPacket(new PacketPlayOutEntityStatus(entityplayer, b0));
/*      */     } 
/*      */     
/* 1061 */     entityplayer.getBukkitEntity().recalculatePermissions();
/* 1062 */     this.server.getCommandDispatcher().a(entityplayer);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isWhitelisted(GameProfile gameprofile) {
/* 1067 */     return isWhitelisted(gameprofile, null);
/*      */   }
/*      */   public boolean isWhitelisted(GameProfile gameprofile, PlayerLoginEvent loginEvent) {
/* 1070 */     boolean isOp = this.operators.d(gameprofile);
/* 1071 */     boolean isWhitelisted = (!getHasWhitelist() || isOp || this.whitelist.d(gameprofile));
/*      */     
/* 1073 */     ProfileWhitelistVerifyEvent event = new ProfileWhitelistVerifyEvent(MCUtil.toBukkit(gameprofile), getHasWhitelist(), isWhitelisted, isOp, SpigotConfig.whitelistMessage);
/* 1074 */     event.callEvent();
/* 1075 */     if (!event.isWhitelisted()) {
/* 1076 */       if (loginEvent != null) {
/* 1077 */         loginEvent.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, (event.getKickMessage() == null) ? SpigotConfig.whitelistMessage : event.getKickMessage());
/*      */       }
/* 1079 */       return false;
/*      */     } 
/* 1081 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isOp(GameProfile gameprofile) {
/* 1086 */     return (this.operators.d(gameprofile) || (this.server.a(gameprofile) && this.server.getSaveData().o()) || this.v);
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public EntityPlayer getPlayer(String s) {
/* 1091 */     return this.playersByName.get(s.toLowerCase(Locale.ROOT));
/*      */   }
/*      */   public void sendPacketNearby(@Nullable EntityHuman entityhuman, double d0, double d1, double d2, double d3, ResourceKey<World> resourcekey, Packet<?> packet) {
/*      */     Object[] backingSet;
/* 1095 */     WorldServer world = null;
/* 1096 */     if (entityhuman != null && entityhuman.world instanceof WorldServer) {
/* 1097 */       world = (WorldServer)entityhuman.world;
/*      */     }
/*      */ 
/*      */     
/* 1101 */     if (world == null) {
/* 1102 */       world = this.server.getWorldServer(resourcekey);
/*      */     }
/* 1104 */     PlayerChunkMap chunkMap = (world != null) ? (world.getChunkProvider()).playerChunkMap : null;
/*      */     
/* 1106 */     if (chunkMap == null) {
/*      */       
/* 1108 */       backingSet = (world != null) ? world.players.toArray() : this.players.toArray();
/*      */     } else {
/* 1110 */       PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<EntityPlayer> nearbyPlayers = chunkMap.playerViewDistanceBroadcastMap.getObjectsInRange(MCUtil.fastFloor(d0) >> 4, MCUtil.fastFloor(d2) >> 4);
/* 1111 */       if (nearbyPlayers == null) {
/*      */         return;
/*      */       }
/* 1114 */       backingSet = nearbyPlayers.getBackingSet();
/*      */     } 
/*      */     
/* 1117 */     for (Object object : backingSet) {
/* 1118 */       if (object instanceof EntityPlayer) {
/* 1119 */         EntityPlayer entityplayer = (EntityPlayer)object;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1128 */         if (entityplayer != entityhuman && entityplayer.world.getDimensionKey() == resourcekey && (!(entityhuman instanceof EntityPlayer) || entityplayer.getBukkitEntity().canSee((Player)((EntityPlayer)entityhuman).getBukkitEntity()))) {
/* 1129 */           double d4 = d0 - entityplayer.locX();
/* 1130 */           double d5 = d1 - entityplayer.locY();
/* 1131 */           double d6 = d2 - entityplayer.locZ();
/*      */           
/* 1133 */           if (d4 * d4 + d5 * d5 + d6 * d6 < d3 * d3) {
/* 1134 */             entityplayer.playerConnection.sendPacket(packet);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void savePlayers() {
/* 1143 */     savePlayers(null);
/*      */   }
/*      */   public void savePlayers(Integer interval) {
/* 1146 */     MCUtil.ensureMain("Save Players", () -> {
/*      */           MinecraftTimings.savePlayers.startTiming();
/*      */           int numSaved = 0;
/*      */           long now = MinecraftServer.currentTick;
/*      */           for (int i = 0; i < this.players.size(); i++) {
/*      */             EntityPlayer entityplayer = this.players.get(i);
/*      */             if (interval == null || now - entityplayer.lastSave >= interval.intValue()) {
/*      */               savePlayerFile(entityplayer);
/*      */               if (interval != null && ++numSaved <= PaperConfig.maxPlayerAutoSavePerTick)
/*      */                 break; 
/*      */             } 
/*      */           } 
/*      */           MinecraftTimings.savePlayers.stopTiming();
/*      */           return null;
/*      */         });
/*      */   }
/*      */   public WhiteList getWhitelist() {
/* 1163 */     return this.whitelist;
/*      */   }
/*      */   
/*      */   public String[] getWhitelisted() {
/* 1167 */     return this.whitelist.getEntries();
/*      */   }
/*      */   
/*      */   public OpList getOPs() {
/* 1171 */     return this.operators;
/*      */   }
/*      */   
/*      */   public String[] l() {
/* 1175 */     return this.operators.getEntries();
/*      */   }
/*      */   
/*      */   public void reloadWhitelist() {}
/*      */   
/*      */   public void a(EntityPlayer entityplayer, WorldServer worldserver) {
/* 1181 */     WorldBorder worldborder = entityplayer.world.getWorldBorder();
/*      */     
/* 1183 */     entityplayer.playerConnection.sendPacket(new PacketPlayOutWorldBorder(worldborder, PacketPlayOutWorldBorder.EnumWorldBorderAction.INITIALIZE));
/* 1184 */     entityplayer.playerConnection.sendPacket(new PacketPlayOutUpdateTime(worldserver.getTime(), worldserver.getDayTime(), worldserver.getGameRules().getBoolean(GameRules.DO_DAYLIGHT_CYCLE)));
/* 1185 */     entityplayer.playerConnection.sendPacket(new PacketPlayOutSpawnPosition(worldserver.getSpawn(), worldserver.v()));
/* 1186 */     if (worldserver.isRaining()) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1191 */       entityplayer.setPlayerWeather(WeatherType.DOWNFALL, false);
/* 1192 */       entityplayer.updateWeather(-worldserver.rainLevel, worldserver.rainLevel, -worldserver.thunderLevel, worldserver.thunderLevel);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateClient(EntityPlayer entityplayer) {
/* 1199 */     entityplayer.updateInventory(entityplayer.defaultContainer);
/*      */     
/* 1201 */     entityplayer.getBukkitEntity().updateScaledHealth();
/* 1202 */     entityplayer.playerConnection.sendPacket(new PacketPlayOutHeldItemSlot(entityplayer.inventory.itemInHandIndex));
/*      */     
/* 1204 */     int i = entityplayer.world.getGameRules().getBoolean(GameRules.REDUCED_DEBUG_INFO) ? 22 : 23;
/* 1205 */     entityplayer.playerConnection.sendPacket(new PacketPlayOutEntityStatus(entityplayer, (byte)i));
/* 1206 */     float immediateRespawn = entityplayer.world.getGameRules().getBoolean(GameRules.DO_IMMEDIATE_RESPAWN) ? 1.0F : 0.0F;
/* 1207 */     entityplayer.playerConnection.sendPacket(new PacketPlayOutGameStateChange(PacketPlayOutGameStateChange.l, immediateRespawn));
/*      */   }
/*      */ 
/*      */   
/*      */   public int getPlayerCount() {
/* 1212 */     return this.players.size();
/*      */   }
/*      */   
/*      */   public int getMaxPlayers() {
/* 1216 */     return this.maxPlayers;
/*      */   }
/*      */   
/*      */   public boolean getHasWhitelist() {
/* 1220 */     return this.hasWhitelist;
/*      */   }
/*      */   
/*      */   public void setHasWhitelist(boolean flag) {
/* 1224 */     (new WhitelistToggleEvent(flag)).callEvent();
/* 1225 */     this.hasWhitelist = flag;
/*      */   }
/*      */   
/*      */   public List<EntityPlayer> b(String s) {
/* 1229 */     List<EntityPlayer> list = Lists.newArrayList();
/* 1230 */     Iterator<EntityPlayer> iterator = this.players.iterator();
/*      */     
/* 1232 */     while (iterator.hasNext()) {
/* 1233 */       EntityPlayer entityplayer = iterator.next();
/*      */       
/* 1235 */       if (entityplayer.v().equals(s)) {
/* 1236 */         list.add(entityplayer);
/*      */       }
/*      */     } 
/*      */     
/* 1240 */     return list;
/*      */   }
/*      */   
/*      */   public int getViewDistance() {
/* 1244 */     return this.viewDistance;
/*      */   }
/*      */   
/*      */   public MinecraftServer getServer() {
/* 1248 */     return this.server;
/*      */   }
/*      */   
/*      */   public NBTTagCompound save() {
/* 1252 */     return null;
/*      */   }
/*      */   
/*      */   private void a(EntityPlayer entityplayer, @Nullable EntityPlayer entityplayer1, WorldServer worldserver) {
/* 1256 */     if (entityplayer1 != null) {
/* 1257 */       entityplayer.playerInteractManager.a(entityplayer1.playerInteractManager.getGameMode(), entityplayer1.playerInteractManager.c());
/* 1258 */     } else if (this.u != null) {
/* 1259 */       entityplayer.playerInteractManager.a(this.u, EnumGamemode.NOT_SET);
/*      */     } 
/*      */     
/* 1262 */     entityplayer.playerInteractManager.b(worldserver.worldDataServer.getGameType());
/*      */   }
/*      */ 
/*      */   
/*      */   public void shutdown() {
/* 1267 */     shutdown(false);
/*      */   }
/*      */ 
/*      */   
/*      */   public void shutdown(boolean isRestarting) {
/* 1272 */     for (EntityPlayer player : this.players) {
/* 1273 */       player.playerConnection.disconnect(!isRestarting ? this.server.server.getShutdownMessage() : SpigotConfig.restartMessage);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1278 */     if (this.collideRuleTeamName != null) {
/* 1279 */       Scoreboard scoreboard = getServer().getWorldServer(World.OVERWORLD).getScoreboard();
/* 1280 */       ScoreboardTeam team = scoreboard.getTeam(this.collideRuleTeamName);
/* 1281 */       if (team != null) scoreboard.removeTeam(team);
/*      */     
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendMessage(IChatBaseComponent[] iChatBaseComponents) {
/* 1289 */     for (IChatBaseComponent component : iChatBaseComponents) {
/* 1290 */       sendMessage(component, ChatMessageType.SYSTEM, SystemUtils.b);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void sendMessage(IChatBaseComponent ichatbasecomponent, ChatMessageType chatmessagetype, UUID uuid) {
/* 1296 */     this.server.sendMessage(ichatbasecomponent, uuid);
/*      */     
/* 1298 */     sendAll(new PacketPlayOutChat(CraftChatMessage.fixComponent(ichatbasecomponent), chatmessagetype, uuid));
/*      */   }
/*      */ 
/*      */   
/*      */   public void sendMessage(IChatBaseComponent ichatbasecomponent) {
/* 1303 */     sendMessage(ichatbasecomponent, ChatMessageType.SYSTEM, SystemUtils.b);
/*      */   }
/*      */ 
/*      */   
/*      */   public ServerStatisticManager getStatisticManager(EntityPlayer entityhuman) {
/* 1308 */     ServerStatisticManager serverstatisticmanager = entityhuman.getStatisticManager();
/* 1309 */     return (serverstatisticmanager == null) ? getStatisticManager(entityhuman.getUniqueID(), entityhuman.getDisplayName().getString()) : serverstatisticmanager;
/*      */   }
/*      */   
/*      */   public ServerStatisticManager getStatisticManager(UUID uuid, String displayName) {
/* 1313 */     EntityPlayer entityhuman = getPlayer(uuid);
/* 1314 */     ServerStatisticManager serverstatisticmanager = (entityhuman == null) ? null : entityhuman.getStatisticManager();
/*      */ 
/*      */     
/* 1317 */     if (serverstatisticmanager == null) {
/* 1318 */       File file = this.server.a(SavedFile.STATS).toFile();
/* 1319 */       File file1 = new File(file, uuid + ".json");
/*      */       
/* 1321 */       if (!file1.exists()) {
/* 1322 */         File file2 = new File(file, displayName + ".json");
/*      */         
/* 1324 */         if (file2.exists() && file2.isFile()) {
/* 1325 */           file2.renameTo(file1);
/*      */         }
/*      */       } 
/*      */       
/* 1329 */       serverstatisticmanager = new ServerStatisticManager(this.server, file1);
/*      */     } 
/*      */ 
/*      */     
/* 1333 */     return serverstatisticmanager;
/*      */   }
/*      */   
/*      */   public AdvancementDataPlayer f(EntityPlayer entityplayer) {
/* 1337 */     UUID uuid = entityplayer.getUniqueID();
/* 1338 */     AdvancementDataPlayer advancementdataplayer = entityplayer.getAdvancementData();
/*      */     
/* 1340 */     if (advancementdataplayer == null) {
/* 1341 */       File file = this.server.a(SavedFile.ADVANCEMENTS).toFile();
/* 1342 */       File file1 = new File(file, uuid + ".json");
/*      */       
/* 1344 */       advancementdataplayer = new AdvancementDataPlayer(this.server.getDataFixer(), this, this.server.getAdvancementData(), file1, entityplayer);
/*      */     } 
/*      */ 
/*      */     
/* 1348 */     advancementdataplayer.a(entityplayer);
/* 1349 */     return advancementdataplayer;
/*      */   }
/*      */   
/*      */   public void a(int i) {
/* 1353 */     this.viewDistance = i;
/*      */     
/* 1355 */     Iterator<WorldServer> iterator = this.server.getWorlds().iterator();
/*      */     
/* 1357 */     while (iterator.hasNext()) {
/* 1358 */       WorldServer worldserver = iterator.next();
/*      */       
/* 1360 */       if (worldserver != null) {
/* 1361 */         worldserver.getChunkProvider().setViewDistance(i);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public List<EntityPlayer> getPlayers() {
/* 1368 */     return this.players;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public EntityPlayer getPlayer(UUID uuid) {
/* 1373 */     return this.j.get(uuid);
/*      */   }
/*      */   
/*      */   public boolean f(GameProfile gameprofile) {
/* 1377 */     return false;
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
/*      */   public void reload() {
/* 1390 */     for (EntityPlayer player : this.players) {
/* 1391 */       player.getAdvancementData().a(this.server.getAdvancementData());
/* 1392 */       player.getAdvancementData().b(player);
/*      */     } 
/*      */ 
/*      */     
/* 1396 */     sendAll(new PacketPlayOutTags(this.server.getTagRegistry()));
/* 1397 */     PacketPlayOutRecipeUpdate packetplayoutrecipeupdate = new PacketPlayOutRecipeUpdate(this.server.getCraftingManager().b());
/* 1398 */     Iterator<EntityPlayer> iterator1 = this.players.iterator();
/*      */     
/* 1400 */     while (iterator1.hasNext()) {
/* 1401 */       EntityPlayer entityplayer = iterator1.next();
/*      */       
/* 1403 */       entityplayer.playerConnection.sendPacket(packetplayoutrecipeupdate);
/* 1404 */       entityplayer.getRecipeBook().a(entityplayer);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean u() {
/* 1410 */     return this.v;
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PlayerList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */