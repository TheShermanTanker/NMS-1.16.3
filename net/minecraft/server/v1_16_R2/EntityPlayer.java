/*      */ package net.minecraft.server.v1_16_R2;
/*      */ import com.destroystokyo.paper.event.player.PlayerStartSpectatingEntityEvent;
/*      */ import com.destroystokyo.paper.event.player.PlayerStopSpectatingEntityEvent;
/*      */ import com.destroystokyo.paper.util.PooledHashSets;
/*      */ import com.destroystokyo.paper.util.misc.PooledLinkedHashSets;
/*      */ import com.mojang.authlib.GameProfile;
/*      */ import com.mojang.datafixers.util.Either;
/*      */ import com.mojang.serialization.DataResult;
/*      */ import io.netty.util.concurrent.Future;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Objects;
/*      */ import java.util.Optional;
/*      */ import java.util.OptionalInt;
/*      */ import java.util.Random;
/*      */ import java.util.UUID;
/*      */ import javax.annotation.Nullable;
/*      */ import org.apache.logging.log4j.Logger;
/*      */ import org.bukkit.Bukkit;
/*      */ import org.bukkit.Location;
/*      */ import org.bukkit.WeatherType;
/*      */ import org.bukkit.World;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftEntity;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftPortalEvent;
/*      */ import org.bukkit.entity.Entity;
/*      */ import org.bukkit.entity.Player;
/*      */ import org.bukkit.event.Event;
/*      */ import org.bukkit.event.entity.PlayerDeathEvent;
/*      */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*      */ import org.bukkit.event.inventory.InventoryType;
/*      */ import org.bukkit.event.player.PlayerChangedMainHandEvent;
/*      */ import org.bukkit.event.player.PlayerChangedWorldEvent;
/*      */ import org.bukkit.event.player.PlayerGameModeChangeEvent;
/*      */ import org.bukkit.event.player.PlayerLocaleChangeEvent;
/*      */ import org.bukkit.event.player.PlayerPortalEvent;
/*      */ import org.bukkit.event.player.PlayerTeleportEvent;
/*      */ import org.bukkit.inventory.ItemStack;
/*      */ import org.bukkit.inventory.MainHand;
/*      */ 
/*      */ public class EntityPlayer extends EntityHuman implements ICrafting {
/*   45 */   private static final Logger LOGGER = LogManager.getLogger();
/*   46 */   public long lastSave = MinecraftServer.currentTick;
/*      */   public PlayerConnection playerConnection;
/*      */   public NetworkManager networkManager;
/*      */   public final MinecraftServer server;
/*      */   public final PlayerInteractManager playerInteractManager;
/*   51 */   public final Deque<Integer> removeQueue = new ArrayDeque<>();
/*      */   private final AdvancementDataPlayer advancementDataPlayer;
/*      */   private final ServerStatisticManager serverStatisticManager;
/*   54 */   private float lastHealthScored = Float.MIN_VALUE;
/*   55 */   private int lastFoodScored = Integer.MIN_VALUE;
/*   56 */   private int lastAirScored = Integer.MIN_VALUE;
/*   57 */   private int lastArmorScored = Integer.MIN_VALUE;
/*   58 */   private int lastExpLevelScored = Integer.MIN_VALUE;
/*   59 */   private int lastExpTotalScored = Integer.MIN_VALUE; public long lastHighPriorityChecked; public boolean isRealPlayer;
/*      */   
/*      */   public void forceCheckHighPriority() {
/*   62 */     this.lastHighPriorityChecked = -1L;
/*   63 */     (getWorldServer().getChunkProvider()).playerChunkMap.checkHighPriorityChunks(this);
/*      */   }
/*      */   
/*   66 */   private float lastHealthSent = -1.0E8F;
/*   67 */   private int lastFoodSent = -99999999;
/*      */   private boolean lastSentSaturationZero = true;
/*   69 */   public int lastSentExp = -99999999;
/*   70 */   public int invulnerableTicks = 60; private EnumChatVisibility bY; private boolean bZ = true;
/*      */   public boolean hasChatColorsEnabled() {
/*   72 */     return this.bZ;
/*   73 */   } private long ca = SystemUtils.getMonotonicMillis(); private Entity spectatedEntity; public boolean worldChangeInvuln; private boolean cd;
/*      */   
/*      */   private void setHasSeenCredits(boolean has) {
/*   76 */     this.cd = has;
/*   77 */   } private final RecipeBookServer recipeBook = new RecipeBookServer();
/*      */   private Vec3D cf;
/*      */   private int cg;
/*      */   private boolean ch;
/*      */   @Nullable
/*      */   private Vec3D ci;
/*   83 */   private SectionPosition cj = SectionPosition.a(0, 0, 0);
/*      */   
/*      */   private ResourceKey<World> spawnDimension;
/*      */   
/*      */   @Nullable
/*      */   private BlockPosition spawn;
/*      */   
/*      */   private boolean spawnForced;
/*      */   private float spawnAngle;
/*      */   private int containerCounter;
/*      */   public boolean e;
/*      */   public int ping;
/*      */   public boolean viewingCredits;
/*      */   private int containerUpdateDelay;
/*      */   public long loginTime;
/*      */   public int patrolSpawnDelay;
/*      */   public boolean queueHealthUpdatePacket = false;
/*      */   public PacketPlayOutUpdateHealth queuedHealthUpdatePacket;
/*  101 */   public static final int ENUMCREATURETYPE_TOTAL_ENUMS = (EnumCreatureType.values()).length;
/*  102 */   public final int[] mobCounts = new int[ENUMCREATURETYPE_TOTAL_ENUMS];
/*      */   
/*      */   public final PooledHashSets.PooledObjectLinkedOpenHashSet<EntityPlayer> cachedSingleMobDistanceMap;
/*      */   
/*      */   public String displayName;
/*      */   
/*      */   public IChatBaseComponent listName;
/*      */   public Location compassTarget;
/*  110 */   public int newExp = 0;
/*  111 */   public int newLevel = 0;
/*  112 */   public int newTotalExp = 0; public boolean keepLevel = false;
/*      */   public double maxHealthCache;
/*      */   public boolean joining = true;
/*      */   public boolean sentListPacket = false;
/*      */   public boolean supressTrackerForLogin = false;
/*      */   public boolean didPlayerJoinEvent = false;
/*      */   public Integer clientViewDistance;
/*      */   public PlayerNaturallySpawnCreaturesEvent playerNaturallySpawnedEvent;
/*      */   public final PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<EntityPlayer> cachedSingleHashSet;
/*      */   double lastEntitySpawnRadiusSquared;
/*      */   boolean needsChunkCenterUpdate;
/*      */   public String locale;
/*      */   public long timeOffset;
/*      */   public boolean relativeTime;
/*      */   public WeatherType weather;
/*      */   private float pluginRainPosition;
/*      */   private float pluginRainPositionPrevious;
/*      */   
/*  130 */   public EntityPlayer(MinecraftServer minecraftserver, WorldServer worldserver, GameProfile gameprofile, PlayerInteractManager playerinteractmanager) { super(worldserver, worldserver.getSpawn(), worldserver.v(), gameprofile);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1685 */     this.locale = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1988 */     this.timeOffset = 0L;
/* 1989 */     this.relativeTime = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2001 */     this.weather = null; this.spawnDimension = World.OVERWORLD; playerinteractmanager.player = this; this.playerInteractManager = playerinteractmanager; this.server = minecraftserver; this.serverStatisticManager = minecraftserver.getPlayerList().getStatisticManager(this); this.advancementDataPlayer = minecraftserver.getPlayerList().f(this); this.G = 1.0F; this.cachedSingleHashSet = new PooledLinkedHashSets.PooledObjectLinkedOpenHashSet(this); this.displayName = getName(); this.canPickUpLoot = true; this.maxHealthCache = getMaxHealth(); this.cachedSingleMobDistanceMap = new PooledHashSets.PooledObjectLinkedOpenHashSet(this); }
/*      */   public BlockPosition getPointInFront(double inFront) { double rads = Math.toRadians(MCUtil.normalizeYaw(this.yaw + 90.0F)); double x = locX() + inFront * Math.cos(rads); double z = locZ() + inFront * Math.sin(rads); return new BlockPosition(x, locY(), z); }
/*      */   public ChunkCoordIntPair getChunkInFront(double inFront) { double rads = Math.toRadians(MCUtil.normalizeYaw(this.yaw + 90.0F)); double x = locX() + inFront * 16.0D * Math.cos(rads); double z = locZ() + inFront * 16.0D * Math.sin(rads); return new ChunkCoordIntPair(MathHelper.floor(x) >> 4, MathHelper.floor(z) >> 4); }
/* 2004 */   public final BlockPosition getSpawnPoint(WorldServer worldserver) { BlockPosition blockposition = worldserver.getSpawn(); if (worldserver.getDimensionManager().hasSkyLight() && worldserver.worldDataServer.getGameType() != EnumGamemode.ADVENTURE) { int i = Math.max(0, this.server.a(worldserver)); int j = MathHelper.floor(worldserver.getWorldBorder().b(blockposition.getX(), blockposition.getZ())); if (j < i) i = j;  if (j <= 1) i = 1;  long k = (i * 2 + 1); long l = k * k; int i1 = (l > 2147483647L) ? Integer.MAX_VALUE : (int)l; int j1 = u(i1); int k1 = (new Random()).nextInt(i1); for (int l1 = 0; l1 < i1; l1++) { int i2 = (k1 + j1 * l1) % i1; int j2 = i2 % (i * 2 + 1); int k2 = i2 / (i * 2 + 1); BlockPosition blockposition1 = WorldProviderNormal.a(worldserver, blockposition.getX() + j2 - i, blockposition.getZ() + k2 - i, false); if (blockposition1 != null) return blockposition1;  }  }  return blockposition; } public final void moveToSpawn(WorldServer worldserver) { c(worldserver); } private void c(WorldServer worldserver) { BlockPosition blockposition = worldserver.getSpawn(); if (worldserver.getDimensionManager().hasSkyLight() && worldserver.worldDataServer.getGameType() != EnumGamemode.ADVENTURE) { int i = Math.max(0, this.server.a(worldserver)); int j = MathHelper.floor(worldserver.getWorldBorder().b(blockposition.getX(), blockposition.getZ())); if (j < i) i = j;  if (j <= 1) i = 1;  long k = (i * 2 + 1); long l = k * k; int i1 = (l > 2147483647L) ? Integer.MAX_VALUE : (int)l; int j1 = u(i1); int k1 = (new Random()).nextInt(i1); for (int l1 = 0; l1 < i1; l1++) { int i2 = (k1 + j1 * l1) % i1; int j2 = i2 % (i * 2 + 1); int k2 = i2 / (i * 2 + 1); BlockPosition blockposition1 = WorldProviderNormal.a(worldserver, blockposition.getX() + j2 - i, blockposition.getZ() + k2 - i, false); if (blockposition1 != null) { setPositionRotation(blockposition1, 0.0F, 0.0F); if (worldserver.getCubes(this)) break;  }  }  } else { setPositionRotation(blockposition, 0.0F, 0.0F); while (!worldserver.getCubes(this) && locY() < 255.0D) setPosition(locX(), locY() + 1.0D, locZ());  }  } private int u(int i) { return (i <= 16) ? (i - 1) : 17; } public void loadData(NBTTagCompound nbttagcompound) { super.loadData(nbttagcompound); if (locY() > 300.0D) setPositionRaw(locX(), 257.0D, locZ());  if (nbttagcompound.hasKeyOfType("playerGameType", 99)) if (getMinecraftServer().getForceGamemode()) { this.playerInteractManager.a(getMinecraftServer().getGamemode(), EnumGamemode.NOT_SET); } else { this.playerInteractManager.a(EnumGamemode.getById(nbttagcompound.getInt("playerGameType")), nbttagcompound.hasKeyOfType("previousPlayerGameType", 3) ? EnumGamemode.getById(nbttagcompound.getInt("previousPlayerGameType")) : EnumGamemode.NOT_SET); }   if (nbttagcompound.hasKeyOfType("enteredNetherPosition", 10)) { NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("enteredNetherPosition"); this.ci = new Vec3D(nbttagcompound1.getDouble("x"), nbttagcompound1.getDouble("y"), nbttagcompound1.getDouble("z")); }  this.cd = nbttagcompound.getBoolean("seenCredits"); if (nbttagcompound.hasKeyOfType("recipeBook", 10)) this.recipeBook.a(nbttagcompound.getCompound("recipeBook"), this.server.getCraftingManager());  getBukkitEntity().readExtraData(nbttagcompound); if (isSleeping()) entityWakeup();  String spawnWorld = nbttagcompound.getString("SpawnWorld"); CraftWorld oldWorld = (CraftWorld)Bukkit.getWorld(spawnWorld); if (oldWorld != null) this.spawnDimension = oldWorld.getHandle().getDimensionKey();  if (nbttagcompound.hasKeyOfType("SpawnX", 99) && nbttagcompound.hasKeyOfType("SpawnY", 99) && nbttagcompound.hasKeyOfType("SpawnZ", 99)) { this.spawn = new BlockPosition(nbttagcompound.getInt("SpawnX"), nbttagcompound.getInt("SpawnY"), nbttagcompound.getInt("SpawnZ")); this.spawnForced = nbttagcompound.getBoolean("SpawnForced"); this.spawnAngle = nbttagcompound.getFloat("SpawnAngle"); if (nbttagcompound.hasKey("SpawnDimension")) { DataResult dataresult = World.f.parse(DynamicOpsNBT.a, nbttagcompound.get("SpawnDimension")); Logger logger = LOGGER; logger.getClass(); Objects.requireNonNull(logger); this.spawnDimension = dataresult.resultOrPartial(logger::error).orElse(World.OVERWORLD); }  }  } public void saveData(NBTTagCompound nbttagcompound) { super.saveData(nbttagcompound); nbttagcompound.setInt("playerGameType", this.playerInteractManager.getGameMode().getId()); nbttagcompound.setInt("previousPlayerGameType", this.playerInteractManager.c().getId()); nbttagcompound.setBoolean("seenCredits", this.cd); if (this.ci != null) { NBTTagCompound nbttagcompound1 = new NBTTagCompound(); nbttagcompound1.setDouble("x", this.ci.x); nbttagcompound1.setDouble("y", this.ci.y); nbttagcompound1.setDouble("z", this.ci.z); nbttagcompound.set("enteredNetherPosition", nbttagcompound1); }  Entity entity = getRootVehicle(); Entity entity1 = getVehicle(); boolean persistVehicle = true; if (entity1 != null) for (Entity vehicle = entity1; vehicle != null; vehicle = vehicle.getVehicle()) { if (!vehicle.persist) { persistVehicle = false; break; }  }   if (persistVehicle && entity1 != null && entity != this && entity.hasSinglePlayerPassenger()) { NBTTagCompound nbttagcompound2 = new NBTTagCompound(); NBTTagCompound nbttagcompound3 = new NBTTagCompound(); entity.d(nbttagcompound3); nbttagcompound2.a("Attach", entity1.getUniqueID()); nbttagcompound2.set("Entity", nbttagcompound3); nbttagcompound.set("RootVehicle", nbttagcompound2); }  nbttagcompound.set("recipeBook", this.recipeBook.save()); nbttagcompound.setString("Dimension", this.world.getDimensionKey().a().toString()); if (this.spawn != null) { nbttagcompound.setInt("SpawnX", this.spawn.getX()); nbttagcompound.setInt("SpawnY", this.spawn.getY()); nbttagcompound.setInt("SpawnZ", this.spawn.getZ()); nbttagcompound.setBoolean("SpawnForced", this.spawnForced); nbttagcompound.setFloat("SpawnAngle", this.spawnAngle); DataResult<NBTBase> dataresult = MinecraftKey.a.encodeStart(DynamicOpsNBT.a, this.spawnDimension.a()); Logger logger = LOGGER; logger.getClass(); Objects.requireNonNull(logger); dataresult.resultOrPartial(logger::error).ifPresent(nbtbase -> nbttagcompound.set("SpawnDimension", nbtbase)); }  getBukkitEntity().setExtraData(nbttagcompound); } public void spawnIn(World world) { super.spawnIn(world); if (world == null) { this.dead = false; Vec3D position = null; if (this.spawnDimension != null) { world = getWorldServer().getServer().getHandle().getServer().getWorldServer(this.spawnDimension); if (world != null && getSpawn() != null) position = EntityHuman.getBed((WorldServer)world, getSpawn(), getSpawnAngle(), false, false).orElse(null);  }  if (world == null || position == null) { world = ((CraftWorld)Bukkit.getServer().getWorlds().get(0)).getHandle(); position = Vec3D.a(((WorldServer)world).getSpawn()); }  this.world = world; setPositionRaw(position.getX(), position.getY(), position.getZ()); }  this.playerInteractManager.a((WorldServer)world); } public void a(int i) { float f = getExpToLevel(); float f1 = (f - 1.0F) / f; this.exp = MathHelper.a(i / f, 0.0F, f1); this.lastSentExp = -1; } public void b(int i) { this.expLevel = i; this.lastSentExp = -1; } public void levelDown(int i) { super.levelDown(i); this.lastSentExp = -1; } public void enchantDone(ItemStack itemstack, int i) { super.enchantDone(itemstack, i); this.lastSentExp = -1; } public void syncInventory() { this.activeContainer.addSlotListener(this); } public void enterCombat() { super.enterCombat(); this.playerConnection.sendPacket(new PacketPlayOutCombatEvent(getCombatTracker(), PacketPlayOutCombatEvent.EnumCombatEventType.ENTER_COMBAT)); } public void exitCombat() { super.exitCombat(); this.playerConnection.sendPacket(new PacketPlayOutCombatEvent(getCombatTracker(), PacketPlayOutCombatEvent.EnumCombatEventType.END_COMBAT)); } protected void a(IBlockData iblockdata) { CriterionTriggers.d.a(this, iblockdata); } protected ItemCooldown i() { return new ItemCooldownPlayer(this); } public void tick() { if (this.joining) this.joining = false;  this.playerInteractManager.a(); this.invulnerableTicks--; if (this.noDamageTicks > 0) this.noDamageTicks--;  if (--this.containerUpdateDelay <= 0) { this.activeContainer.c(); this.containerUpdateDelay = this.world.paperConfig.containerUpdateTickRate; }  if (!this.world.isClientSide && this.activeContainer != this.defaultContainer && (isFrozen() || !this.activeContainer.canUse(this))) { closeInventory(InventoryCloseEvent.Reason.CANT_USE); this.activeContainer = this.defaultContainer; }  while (!this.removeQueue.isEmpty()) { int i = Math.min(this.removeQueue.size(), 2147483647); int[] aint = new int[i]; int j = 0; Integer integer; while (j < i && (integer = this.removeQueue.poll()) != null) aint[j++] = integer.intValue();  this.playerConnection.sendPacket(new PacketPlayOutEntityDestroy(aint)); }  Entity entity = getSpecatorTarget(); if (entity != this) if (entity.isAlive()) { setLocation(entity.locX(), entity.locY(), entity.locZ(), entity.yaw, entity.pitch); getWorldServer().getChunkProvider().movePlayer(this); if (eq()) setSpectatorTarget(this);  } else { setSpectatorTarget(this); }   CriterionTriggers.w.a(this); if (this.cf != null) CriterionTriggers.u.a(this, this.cf, this.ticksLived - this.cg);  this.advancementDataPlayer.b(this); } public void playerTick() { try { if ((this.valid && !isSpectator()) || this.world.isLoaded(getChunkCoordinates())) super.tick();  if (this.valid && isAlive() && this.playerConnection != null) (((WorldServer)this.world).getChunkProvider()).playerChunkMap.checkHighPriorityChunks(this);  for (int i = 0; i < this.inventory.getSize(); i++) { ItemStack itemstack = this.inventory.getItem(i); if (itemstack.getItem().ac_()) { Packet<?> packet = ((ItemWorldMapBase)itemstack.getItem()).a(itemstack, this.world, this); if (packet != null) this.playerConnection.sendPacket(packet);  }  }  if (getHealth() != this.lastHealthSent || this.lastFoodSent != this.foodData.getFoodLevel() || ((this.foodData.getSaturationLevel() == 0.0F)) != this.lastSentSaturationZero) { this.playerConnection.sendPacket(new PacketPlayOutUpdateHealth(getBukkitEntity().getScaledHealth(), this.foodData.getFoodLevel(), this.foodData.getSaturationLevel())); this.lastHealthSent = getHealth(); this.lastFoodSent = this.foodData.getFoodLevel(); this.lastSentSaturationZero = (this.foodData.getSaturationLevel() == 0.0F); }  if (getHealth() + getAbsorptionHearts() != this.lastHealthScored) { this.lastHealthScored = getHealth() + getAbsorptionHearts(); a(IScoreboardCriteria.HEALTH, MathHelper.f(this.lastHealthScored)); }  if (this.foodData.getFoodLevel() != this.lastFoodScored) { this.lastFoodScored = this.foodData.getFoodLevel(); a(IScoreboardCriteria.FOOD, MathHelper.f(this.lastFoodScored)); }  if (getAirTicks() != this.lastAirScored) { this.lastAirScored = getAirTicks(); a(IScoreboardCriteria.AIR, MathHelper.f(this.lastAirScored)); }  if (getArmorStrength() != this.lastArmorScored) { this.lastArmorScored = getArmorStrength(); a(IScoreboardCriteria.ARMOR, MathHelper.f(this.lastArmorScored)); }  if (this.expTotal != this.lastExpTotalScored) { this.lastExpTotalScored = this.expTotal; a(IScoreboardCriteria.XP, MathHelper.f(this.lastExpTotalScored)); }  if (this.maxHealthCache != getMaxHealth()) getBukkitEntity().updateScaledHealth();  if (this.expLevel != this.lastExpLevelScored) { this.lastExpLevelScored = this.expLevel; a(IScoreboardCriteria.LEVEL, MathHelper.f(this.lastExpLevelScored)); }  if (this.expTotal != this.lastSentExp) { this.lastSentExp = this.expTotal; this.playerConnection.sendPacket(new PacketPlayOutExperience(this.exp, this.expTotal, this.expLevel)); }  if (this.ticksLived % 20 == 0) CriterionTriggers.p.a(this);  if (this.oldLevel == -1) this.oldLevel = this.expLevel;  if (this.oldLevel != this.expLevel) { CraftEventFactory.callPlayerLevelChangeEvent(this.world.getServer().getPlayer(this), this.oldLevel, this.expLevel); this.oldLevel = this.expLevel; }  } catch (Throwable throwable) { CrashReport crashreport = CrashReport.a(throwable, "Ticking player"); CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Player being ticked"); appendEntityCrashDetails(crashreportsystemdetails); throw new ReportedException(crashreport); }  } private void a(IScoreboardCriteria iscoreboardcriteria, int i) { this.world.getServer().getScoreboardManager().getScoreboardScores(iscoreboardcriteria, getName(), scoreboardscore -> scoreboardscore.setScore(i)); } private static void processKeep(PlayerDeathEvent event, NonNullList<ItemStack> inv) { List<ItemStack> itemsToKeep = event.getItemsToKeep(); if (inv == null) { if (!itemsToKeep.isEmpty()) for (ItemStack itemStack : itemsToKeep) { event.getEntity().getInventory().addItem(new ItemStack[] { itemStack }); }   return; }  for (int i = 0; i < inv.size(); i++) { ItemStack item = inv.get(i); if (EnchantmentManager.shouldNotDrop(item) || itemsToKeep.isEmpty() || item.isEmpty()) { inv.set(i, ItemStack.NULL_ITEM); } else { ItemStack bukkitStack = item.getBukkitStack(); boolean keep = false; Iterator<ItemStack> iterator = itemsToKeep.iterator(); while (iterator.hasNext()) { ItemStack itemStack = iterator.next(); if (bukkitStack.equals(itemStack)) { iterator.remove(); keep = true; break; }  }  if (!keep) inv.set(i, ItemStack.NULL_ITEM);  }  }  } public void die(DamageSource damagesource) { boolean flag = this.world.getGameRules().getBoolean(GameRules.SHOW_DEATH_MESSAGES); if (this.dead) return;  List<ItemStack> loot = new ArrayList<>(this.inventory.getSize()); boolean keepInventory = (this.world.getGameRules().getBoolean(GameRules.KEEP_INVENTORY) || isSpectator()); if (!keepInventory) for (ItemStack item : this.inventory.getContents()) { if (!item.isEmpty() && !EnchantmentManager.shouldNotDrop(item)) loot.add(CraftItemStack.asCraftMirror(item));  }   a(damagesource, (this.lastDamageByPlayerTime > 0)); for (ItemStack item : this.drops) loot.add(item);  this.drops.clear(); IChatBaseComponent defaultMessage = getCombatTracker().getDeathMessage(); String deathmessage = defaultMessage.getString(); PlayerDeathEvent event = CraftEventFactory.callPlayerDeathEvent(this, loot, deathmessage, keepInventory); if (event.isCancelled()) { if (getHealth() <= 0.0F) setHealth((float)event.getReviveHealth());  return; }  if (this.activeContainer != this.defaultContainer) closeInventory(InventoryCloseEvent.Reason.DEATH);  String deathMessage = event.getDeathMessage(); if (deathMessage != null && deathMessage.length() > 0 && flag) { IChatBaseComponent ichatbasecomponent; if (deathMessage.equals(deathmessage)) { ichatbasecomponent = getCombatTracker().getDeathMessage(); } else { ichatbasecomponent = CraftChatMessage.fromStringOrNull(deathMessage); }  this.playerConnection.a(new PacketPlayOutCombatEvent(getCombatTracker(), PacketPlayOutCombatEvent.EnumCombatEventType.ENTITY_DIED, ichatbasecomponent), future -> { if (!future.isSuccess()) { boolean flag1 = true; String s = ichatbasecomponent.a(256); ChatMessage chatmessage = new ChatMessage("death.attack.message_too_long", new Object[] { (new ChatComponentText(s)).a(EnumChatFormat.YELLOW) }); IChatMutableComponent ichatmutablecomponent = (new ChatMessage("death.attack.even_more_magic", new Object[] { getScoreboardDisplayName() })).format(()); this.playerConnection.sendPacket(new PacketPlayOutCombatEvent(getCombatTracker(), PacketPlayOutCombatEvent.EnumCombatEventType.ENTITY_DIED, ichatmutablecomponent)); }  }); ScoreboardTeamBase scoreboardteambase = getScoreboardTeam(); if (scoreboardteambase != null && scoreboardteambase.getDeathMessageVisibility() != ScoreboardTeamBase.EnumNameTagVisibility.ALWAYS) { if (scoreboardteambase.getDeathMessageVisibility() == ScoreboardTeamBase.EnumNameTagVisibility.HIDE_FOR_OTHER_TEAMS) { this.server.getPlayerList().a(this, ichatbasecomponent); } else if (scoreboardteambase.getDeathMessageVisibility() == ScoreboardTeamBase.EnumNameTagVisibility.HIDE_FOR_OWN_TEAM) { this.server.getPlayerList().b(this, ichatbasecomponent); }  } else { this.server.getPlayerList().sendMessage(ichatbasecomponent, ChatMessageType.SYSTEM, SystemUtils.b); }  } else { this.playerConnection.sendPacket(new PacketPlayOutCombatEvent(getCombatTracker(), PacketPlayOutCombatEvent.EnumCombatEventType.ENTITY_DIED)); }  releaseShoulderEntities(); if (this.world.getGameRules().getBoolean(GameRules.FORGIVE_DEAD_PLAYERS)) eV();  if (event.shouldDropExperience()) dropExperience();  if (!event.getKeepInventory()) { for (NonNullList<ItemStack> inv : this.inventory.getComponents()) processKeep(event, inv);  processKeep(event, (NonNullList<ItemStack>)null); }  setSpectatorTarget(this); this.world.getServer().getScoreboardManager().getScoreboardScores(IScoreboardCriteria.DEATH_COUNT, getName(), ScoreboardScore::incrementScore); EntityLiving entityliving = getKillingEntity(); if (entityliving != null) { b(StatisticList.ENTITY_KILLED_BY.b(entityliving.getEntityType())); entityliving.a(this, this.aO, damagesource); f(entityliving); }  this.world.broadcastEntityEffect(this, (byte)3); a(StatisticList.DEATHS); a(StatisticList.CUSTOM.b(StatisticList.TIME_SINCE_DEATH)); a(StatisticList.CUSTOM.b(StatisticList.TIME_SINCE_REST)); extinguish(); setFlag(0, false); getCombatTracker().g(); } private void eV() { AxisAlignedBB axisalignedbb = (new AxisAlignedBB(getChunkCoordinates())).grow(32.0D, 10.0D, 32.0D); this.world.<EntityInsentient>b(EntityInsentient.class, axisalignedbb).stream().filter(entityinsentient -> entityinsentient instanceof IEntityAngerable).forEach(entityinsentient -> ((IEntityAngerable)entityinsentient).b(this)); } public void a(Entity entity, int i, DamageSource damagesource) { if (entity != this) { super.a(entity, i, damagesource); addScore(i); String s = getName(); String s1 = entity.getName(); this.world.getServer().getScoreboardManager().getScoreboardScores(IScoreboardCriteria.TOTAL_KILL_COUNT, s, ScoreboardScore::incrementScore); if (entity instanceof EntityHuman) { a(StatisticList.PLAYER_KILLS); this.world.getServer().getScoreboardManager().getScoreboardScores(IScoreboardCriteria.PLAYER_KILL_COUNT, s, ScoreboardScore::incrementScore); } else { a(StatisticList.MOB_KILLS); }  a(s, s1, IScoreboardCriteria.m); a(s1, s, IScoreboardCriteria.n); CriterionTriggers.b.a(this, entity, damagesource); }  } private void a(String s, String s1, IScoreboardCriteria[] aiscoreboardcriteria) { ScoreboardTeam scoreboardteam = getScoreboard().getPlayerTeam(s1); if (scoreboardteam != null) { int i = scoreboardteam.getColor().b(); if (i >= 0 && i < aiscoreboardcriteria.length) this.world.getServer().getScoreboardManager().getScoreboardScores(aiscoreboardcriteria[i], s, ScoreboardScore::incrementScore);  }  } public boolean damageEntity(DamageSource damagesource, float f) { if (isInvulnerable(damagesource)) return false;  boolean flag = (this.server.j() && canPvP() && "fall".equals(damagesource.translationIndex)); if (!flag && this.invulnerableTicks > 0 && damagesource != DamageSource.OUT_OF_WORLD) return false;  if (damagesource instanceof EntityDamageSource) { Entity entity = damagesource.getEntity(); if (entity instanceof EntityHuman && !a((EntityHuman)entity)) return false;  if (entity instanceof EntityArrow) { EntityArrow entityarrow = (EntityArrow)entity; Entity entity1 = entityarrow.getShooter(); if (entity1 instanceof EntityHuman && !a((EntityHuman)entity1)) return false;  }  }  this.queueHealthUpdatePacket = true; boolean damaged = super.damageEntity(damagesource, f); this.queueHealthUpdatePacket = false; if (this.queuedHealthUpdatePacket != null) { this.playerConnection.sendPacket(this.queuedHealthUpdatePacket); this.queuedHealthUpdatePacket = null; }  return damaged; } public boolean a(EntityHuman entityhuman) { return !canPvP() ? false : super.a(entityhuman); } private boolean canPvP() { return this.world.pvpMode; } @Nullable protected ShapeDetectorShape a(WorldServer worldserver) { ShapeDetectorShape shapedetectorshape = super.a(worldserver); worldserver = (shapedetectorshape == null) ? worldserver : shapedetectorshape.world; if (shapedetectorshape != null && this.world.getTypeKey() == DimensionManager.OVERWORLD && worldserver != null && worldserver.getTypeKey() == DimensionManager.THE_END) { Vec3D vec3d = shapedetectorshape.position.add(0.0D, -1.0D, 0.0D); return new ShapeDetectorShape(vec3d, Vec3D.ORIGIN, 90.0F, 0.0F, worldserver, shapedetectorshape.portalEventInfo); }  return shapedetectorshape; } @Nullable public Entity b(WorldServer worldserver) { return b(worldserver, PlayerTeleportEvent.TeleportCause.UNKNOWN); } @Nullable public Entity b(WorldServer worldserver, PlayerTeleportEvent.TeleportCause cause) { if (isSleeping()) return this;  WorldServer worldserver1 = getWorldServer(); ResourceKey<DimensionManager> resourcekey = worldserver1.getTypeKey(); if (resourcekey == DimensionManager.THE_END && worldserver != null && worldserver.getTypeKey() == DimensionManager.OVERWORLD) { this.worldChangeInvuln = true; decouple(); getWorldServer().removePlayer(this); if (!this.viewingCredits) { if (this.world.paperConfig.disableEndCredits) setHasSeenCredits(true);  this.viewingCredits = true; this.playerConnection.sendPacket(new PacketPlayOutGameStateChange(PacketPlayOutGameStateChange.e, this.cd ? 0.0F : 1.0F)); this.cd = true; }  return this; }  ShapeDetectorShape shapedetectorshape = a(worldserver); if (shapedetectorshape != null) { worldserver1.getMethodProfiler().enter("moving"); worldserver = shapedetectorshape.world; if (worldserver != null) if (resourcekey == DimensionManager.OVERWORLD && worldserver.getTypeKey() == DimensionManager.THE_NETHER) { this.ci = getPositionVector(); } else if (worldserver.getTypeKey() == DimensionManager.THE_END && shapedetectorshape.portalEventInfo != null && shapedetectorshape.portalEventInfo.getCanCreatePortal()) { a(worldserver, new BlockPosition(shapedetectorshape.position)); }   } else { return null; }  Location enter = getBukkitEntity().getLocation(); Location exit = (worldserver == null) ? null : new Location((World)worldserver.getWorld(), shapedetectorshape.position.x, shapedetectorshape.position.y, shapedetectorshape.position.z, shapedetectorshape.yaw, shapedetectorshape.pitch); PlayerTeleportEvent tpEvent = new PlayerTeleportEvent((Player)getBukkitEntity(), enter, exit, cause); Bukkit.getServer().getPluginManager().callEvent((Event)tpEvent); if (tpEvent.isCancelled() || tpEvent.getTo() == null) return null;  exit = tpEvent.getTo(); worldserver = ((CraftWorld)exit.getWorld()).getHandle(); worldserver1.getMethodProfiler().exit(); worldserver1.getMethodProfiler().enter("placing"); this.worldChangeInvuln = true; this.playerConnection.sendPacket(new PacketPlayOutRespawn(worldserver.getDimensionManager(), worldserver.getDimensionKey(), BiomeManager.a(worldserver.getSeed()), this.playerInteractManager.getGameMode(), this.playerInteractManager.c(), worldserver.isDebugWorld(), worldserver.isFlatWorld(), true)); this.playerConnection.sendPacket(new PacketPlayOutServerDifficulty(this.world.getDifficulty(), this.world.getWorldData().isDifficultyLocked())); PlayerList playerlist = this.server.getPlayerList(); playerlist.d(this); worldserver1.removePlayer(this); this.dead = false; spawnIn(worldserver); worldserver.addPlayerPortal(this); this.playerConnection.teleport(exit); this.playerConnection.syncPosition(); worldserver1.getMethodProfiler().exit(); triggerDimensionAdvancements(worldserver1); this.playerInteractManager.a(worldserver); this.playerConnection.sendPacket(new PacketPlayOutAbilities(this.abilities)); playerlist.a(this, worldserver); playerlist.updateClient(this); Iterator<MobEffect> iterator = getEffects().iterator(); while (iterator.hasNext()) { MobEffect mobeffect = iterator.next(); this.playerConnection.sendPacket(new PacketPlayOutEntityEffect(getId(), mobeffect)); }  this.playerConnection.sendPacket(new PacketPlayOutWorldEvent(1032, BlockPosition.ZERO, 0, false)); this.lastSentExp = -1; this.lastHealthSent = -1.0F; this.lastFoodSent = -1; setSneaking(false); PlayerChangedWorldEvent changeEvent = new PlayerChangedWorldEvent((Player)getBukkitEntity(), (World)worldserver1.getWorld()); this.world.getServer().getPluginManager().callEvent((Event)changeEvent); return this; } protected CraftPortalEvent callPortalEvent(Entity entity, WorldServer exitWorldServer, BlockPosition exitPosition, PlayerTeleportEvent.TeleportCause cause, int searchRadius, int creationRadius) { Location enter = getBukkitEntity().getLocation(); Location exit = new Location((World)exitWorldServer.getWorld(), exitPosition.getX(), exitPosition.getY(), exitPosition.getZ(), this.yaw, this.pitch); PlayerPortalEvent event = new PlayerPortalEvent((Player)getBukkitEntity(), enter, exit, cause, searchRadius, true, creationRadius); Bukkit.getServer().getPluginManager().callEvent((Event)event); if (event.isCancelled() || event.getTo() == null || event.getTo().getWorld() == null) return null;  return new CraftPortalEvent(event); } private void a(WorldServer worldserver, BlockPosition blockposition) { BlockPosition.MutableBlockPosition blockposition_mutableblockposition = blockposition.i(); for (int i = -2; i <= 2; i++) { for (int j = -2; j <= 2; j++) { for (int k = -1; k < 3; k++) { IBlockData iblockdata = (k == -1) ? Blocks.OBSIDIAN.getBlockData() : Blocks.AIR.getBlockData(); worldserver.setTypeUpdate(blockposition_mutableblockposition.g(blockposition).e(j, k, i), iblockdata); }  }  }  } protected Optional<BlockUtil.Rectangle> a(WorldServer worldserver, BlockPosition blockposition, boolean flag, int searchRadius, boolean canCreatePortal, int createRadius) { Optional<BlockUtil.Rectangle> optional = super.a(worldserver, blockposition, flag, searchRadius, canCreatePortal, createRadius); if (optional.isPresent() || !canCreatePortal) return optional;  EnumDirection.EnumAxis enumdirection_enumaxis = this.world.getType(this.ac).d(BlockPortal.AXIS).orElse(EnumDirection.EnumAxis.X); Optional<BlockUtil.Rectangle> optional1 = worldserver.getTravelAgent().createPortal(blockposition, enumdirection_enumaxis, this, createRadius); if (!optional1.isPresent()); return optional1; } public void triggerDimensionAdvancements(WorldServer worldserver) { ResourceKey<World> resourcekey = worldserver.getDimensionKey(); ResourceKey<World> resourcekey1 = this.world.getDimensionKey(); CriterionTriggers.v.a(this, resourcekey, resourcekey1); if (resourcekey == World.THE_NETHER && resourcekey1 == World.OVERWORLD && this.ci != null) CriterionTriggers.C.a(this, this.ci);  if (resourcekey1 != World.THE_NETHER) this.ci = null;  } public boolean a(EntityPlayer entityplayer) { return entityplayer.isSpectator() ? ((getSpecatorTarget() == this)) : (isSpectator() ? false : super.a(entityplayer)); } private void a(TileEntity tileentity) { if (tileentity != null) { PacketPlayOutTileEntityData packetplayouttileentitydata = tileentity.getUpdatePacket(); if (packetplayouttileentitydata != null) this.playerConnection.sendPacket(packetplayouttileentitydata);  }  } public void receive(Entity entity, int i) { super.receive(entity, i); this.activeContainer.c(); } private Either<EntityHuman.EnumBedResult, Unit> getBedResult(BlockPosition blockposition, EnumDirection enumdirection) { if (!isSleeping() && isAlive()) { if (!this.world.getDimensionManager().isNatural()) return Either.left(EntityHuman.EnumBedResult.NOT_POSSIBLE_HERE);  if (!a(blockposition, enumdirection)) return Either.left(EntityHuman.EnumBedResult.TOO_FAR_AWAY);  if (b(blockposition, enumdirection)) return Either.left(EntityHuman.EnumBedResult.OBSTRUCTED);  setRespawnPosition(this.world.getDimensionKey(), blockposition, this.yaw, false, true); if (this.world.isDay()) return Either.left(EntityHuman.EnumBedResult.NOT_POSSIBLE_NOW);  if (!isCreative()) { double d0 = 8.0D; double d1 = 5.0D; Vec3D vec3d = Vec3D.c(blockposition); List<EntityMonster> list = this.world.a(EntityMonster.class, new AxisAlignedBB(vec3d.getX() - 8.0D, vec3d.getY() - 5.0D, vec3d.getZ() - 8.0D, vec3d.getX() + 8.0D, vec3d.getY() + 5.0D, vec3d.getZ() + 8.0D), entitymonster -> entitymonster.f(this)); if (!list.isEmpty()) return Either.left(EntityHuman.EnumBedResult.NOT_SAFE);  }  return Either.right(Unit.INSTANCE); }  return Either.left(EntityHuman.EnumBedResult.OTHER_PROBLEM); } public Either<EntityHuman.EnumBedResult, Unit> sleep(BlockPosition blockposition, boolean force) { EnumDirection enumdirection = (EnumDirection)this.world.getType(blockposition).get(BlockFacingHorizontal.FACING); Either<EntityHuman.EnumBedResult, Unit> bedResult = getBedResult(blockposition, enumdirection); if (bedResult.left().orElse(null) == EntityHuman.EnumBedResult.OTHER_PROBLEM) return bedResult;  if (force) bedResult = Either.right(Unit.INSTANCE);  bedResult = CraftEventFactory.callPlayerBedEnterEvent(this, blockposition, bedResult); if (bedResult.left().isPresent()) return bedResult;  Either<EntityHuman.EnumBedResult, Unit> either = super.sleep(blockposition, force).ifRight(unit -> { a(StatisticList.SLEEP_IN_BED); CriterionTriggers.q.a(this); }); ((WorldServer)this.world).everyoneSleeping(); return either; } public void entitySleep(BlockPosition blockposition) { a(StatisticList.CUSTOM.b(StatisticList.TIME_SINCE_REST)); super.entitySleep(blockposition); } private boolean a(BlockPosition blockposition, EnumDirection enumdirection) { return (g(blockposition) || g(blockposition.shift(enumdirection.opposite()))); } private boolean g(BlockPosition blockposition) { Vec3D vec3d = Vec3D.c(blockposition); return (Math.abs(locX() - vec3d.getX()) <= 3.0D && Math.abs(locY() - vec3d.getY()) <= 2.0D && Math.abs(locZ() - vec3d.getZ()) <= 3.0D); } private boolean b(BlockPosition blockposition, EnumDirection enumdirection) { BlockPosition blockposition1 = blockposition.up(); return (!f(blockposition1) || !f(blockposition1.shift(enumdirection.opposite()))); } public void wakeup(boolean flag, boolean flag1) { if (!isSleeping()) return;  if (isSleeping()) getWorldServer().getChunkProvider().broadcastIncludingSelf(this, new PacketPlayOutAnimation(this, 2));  super.wakeup(flag, flag1); if (this.playerConnection != null) this.playerConnection.a(locX(), locY(), locZ(), this.yaw, this.pitch);  } public boolean a(Entity entity, boolean flag) { Entity entity1 = getVehicle(); if (!super.a(entity, flag)) return false;  Entity entity2 = getVehicle(); if (entity2 != entity1 && this.playerConnection != null) this.playerConnection.a(locX(), locY(), locZ(), this.yaw, this.pitch);  return true; } public void stopRiding() { stopRiding(false); } public void stopRiding(boolean suppressCancellation) { Entity entity = getVehicle(); super.stopRiding(suppressCancellation); Entity entity1 = getVehicle(); if (entity1 != entity && this.playerConnection != null) this.playerConnection.a(locX(), locY(), locZ(), this.yaw, this.pitch);  } public boolean isInvulnerable(DamageSource damagesource) { return (super.isInvulnerable(damagesource) || H() || (this.abilities.isInvulnerable && damagesource == DamageSource.WITHER)); } protected void a(double d0, boolean flag, IBlockData iblockdata, BlockPosition blockposition) {} protected void c(BlockPosition blockposition) { if (!isSpectator()) super.c(blockposition);  } public void a(double d0, boolean flag) { BlockPosition blockposition = ao(); if (this.world.isLoaded(blockposition)) super.a(d0, flag, this.world.getType(blockposition), blockposition);  } public void openSign(TileEntitySign tileentitysign) { tileentitysign.a(this); this.playerConnection.sendPacket(new PacketPlayOutOpenSignEditor(tileentitysign.getPosition())); } public int nextContainerCounter() { this.containerCounter = this.containerCounter % 100 + 1; return this.containerCounter; } public OptionalInt openContainer(@Nullable ITileInventory itileinventory) { if (itileinventory == null) return OptionalInt.empty();  if (this.activeContainer != this.defaultContainer) closeInventory(InventoryCloseEvent.Reason.OPEN_NEW);  nextContainerCounter(); Container container = itileinventory.createMenu(this.containerCounter, this.inventory, this); if (container != null) { container.setTitle(itileinventory.getScoreboardDisplayName()); boolean cancelled = false; container = CraftEventFactory.callInventoryOpenEvent(this, container, cancelled); if (container == null && !cancelled) { if (itileinventory instanceof IInventory) { ((IInventory)itileinventory).closeContainer(this); } else if (itileinventory instanceof BlockChest.DoubleInventory) { ((BlockChest.DoubleInventory)itileinventory).inventorylargechest.closeContainer(this); }  return OptionalInt.empty(); }  }  if (container == null) { if (isSpectator()) a((new ChatMessage("container.spectatorCantOpen")).a(EnumChatFormat.RED), true);  return OptionalInt.empty(); }  this.activeContainer = container; if (!isFrozen()) this.playerConnection.sendPacket(new PacketPlayOutOpenWindow(container.windowId, container.getType(), container.getTitle()));  container.addSlotListener(this); return OptionalInt.of(this.containerCounter); } public void openTrade(int i, MerchantRecipeList merchantrecipelist, int j, int k, boolean flag, boolean flag1) { this.playerConnection.sendPacket(new PacketPlayOutOpenWindowMerchant(i, merchantrecipelist, j, k, flag, flag1)); } public void openHorseInventory(EntityHorseAbstract entityhorseabstract, IInventory iinventory) { nextContainerCounter(); Container container = new ContainerHorse(this.containerCounter, this.inventory, iinventory, entityhorseabstract); container.setTitle(entityhorseabstract.getScoreboardDisplayName()); container = CraftEventFactory.callInventoryOpenEvent(this, container); if (container == null) { iinventory.closeContainer(this); return; }  if (this.activeContainer != this.defaultContainer) closeInventory(InventoryCloseEvent.Reason.OPEN_NEW);  this.playerConnection.sendPacket(new PacketPlayOutOpenWindowHorse(this.containerCounter, iinventory.getSize(), entityhorseabstract.getId())); this.activeContainer = container; this.activeContainer.addSlotListener(this); } public void openBook(ItemStack itemstack, EnumHand enumhand) { Item item = itemstack.getItem(); if (item == Items.WRITTEN_BOOK) { if (ItemWrittenBook.a(itemstack, getCommandListener(), this)) this.activeContainer.c();  this.playerConnection.sendPacket(new PacketPlayOutOpenBook(enumhand)); }  } public void a(TileEntityCommand tileentitycommand) { tileentitycommand.c(true); a(tileentitycommand); } public void a(Container container, int i, ItemStack itemstack) { if (!(container.getSlot(i) instanceof SlotResult)) { if (container == this.defaultContainer) CriterionTriggers.e.a(this, this.inventory, itemstack);  if (!this.e) this.playerConnection.sendPacket(new PacketPlayOutSetSlot(container.windowId, i, itemstack));  }  } public void updateInventory(Container container) { a(container, container.b()); } public void a(Container container, NonNullList<ItemStack> nonnulllist) { this.playerConnection.sendPacket(new PacketPlayOutWindowItems(container.windowId, nonnulllist)); this.playerConnection.sendPacket(new PacketPlayOutSetSlot(-1, -1, this.inventory.getCarried())); if (EnumSet.<InventoryType>of(InventoryType.CRAFTING, InventoryType.WORKBENCH).contains(container.getBukkitView().getType())) this.playerConnection.sendPacket(new PacketPlayOutSetSlot(container.windowId, 0, container.getSlot(0).getItem()));  } public void setContainerData(Container container, int i, int j) { this.playerConnection.sendPacket(new PacketPlayOutWindowData(container.windowId, i, j)); } public void closeInventory() { closeInventory(InventoryCloseEvent.Reason.UNKNOWN); } public void closeInventory(InventoryCloseEvent.Reason reason) { CraftEventFactory.handleInventoryCloseEvent(this, reason); this.playerConnection.sendPacket(new PacketPlayOutCloseWindow(this.activeContainer.windowId)); o(); } public void broadcastCarriedItem() { if (!this.e) this.playerConnection.sendPacket(new PacketPlayOutSetSlot(-1, -1, this.inventory.getCarried()));  } public WeatherType getPlayerWeather() { return this.weather; }
/*      */   public void o() { this.activeContainer.b(this); this.activeContainer = this.defaultContainer; }
/*      */   public void a(float f, float f1, boolean flag, boolean flag1) { if (isPassenger()) { if (f >= -1.0F && f <= 1.0F) this.aR = f;  if (f1 >= -1.0F && f1 <= 1.0F) this.aT = f1;  this.jumping = flag; setSneaking(flag1); }  }
/*      */   public void a(Statistic<?> statistic, int i) { this.serverStatisticManager.b(this, statistic, i); this.world.getServer().getScoreboardManager().getScoreboardScores(statistic, getName(), scoreboardscore -> scoreboardscore.addScore(i)); }
/* 2008 */   public void a(Statistic<?> statistic) { this.serverStatisticManager.setStatistic(this, statistic, 0); this.world.getServer().getScoreboardManager().getScoreboardScores(statistic, getName(), ScoreboardScore::c); } public int discoverRecipes(Collection<IRecipe<?>> collection) { return this.recipeBook.a(collection, this); } public void a(MinecraftKey[] aminecraftkey) { List<IRecipe<?>> list = Lists.newArrayList(); MinecraftKey[] aminecraftkey1 = aminecraftkey; int i = aminecraftkey.length; for (int j = 0; j < i; j++) { MinecraftKey minecraftkey = aminecraftkey1[j]; Objects.requireNonNull(list); this.server.getCraftingManager().getRecipe(minecraftkey).ifPresent(list::add); }  discoverRecipes(list); } public int undiscoverRecipes(Collection<IRecipe<?>> collection) { return this.recipeBook.b(collection, this); } public void giveExp(int i) { super.giveExp(i); this.lastSentExp = -1; } public void p() { this.ch = true; ejectPassengers(); if (isPassenger() && getVehicle() instanceof EntityPlayer) stopRiding();  if (isSleeping()) wakeup(true, false);  } public boolean q() { return this.ch; } public void triggerHealthUpdate() { this.lastHealthSent = -1.0E8F; this.lastSentExp = -1; } public void sendMessage(IChatBaseComponent[] ichatbasecomponent) { for (IChatBaseComponent component : ichatbasecomponent) sendMessage(component, SystemUtils.b);  } public void a(IChatBaseComponent ichatbasecomponent, boolean flag) { this.playerConnection.sendPacket(new PacketPlayOutChat(ichatbasecomponent, flag ? ChatMessageType.GAME_INFO : ChatMessageType.CHAT, SystemUtils.b)); } protected void s() { if (!this.activeItem.isEmpty() && isHandRaised()) { this.playerConnection.sendPacket(new PacketPlayOutEntityStatus(this, (byte)9)); super.s(); }  } public void a(ArgumentAnchor.Anchor argumentanchor_anchor, Vec3D vec3d) { super.a(argumentanchor_anchor, vec3d); this.playerConnection.sendPacket(new PacketPlayOutLookAt(argumentanchor_anchor, vec3d.x, vec3d.y, vec3d.z)); } public void a(ArgumentAnchor.Anchor argumentanchor_anchor, Entity entity, ArgumentAnchor.Anchor argumentanchor_anchor1) { Vec3D vec3d = argumentanchor_anchor1.a(entity); super.a(argumentanchor_anchor, vec3d); this.playerConnection.sendPacket(new PacketPlayOutLookAt(argumentanchor_anchor, entity, argumentanchor_anchor1)); } public void copyFrom(EntityPlayer entityplayer, boolean flag) { if (flag) { this.inventory.a(entityplayer.inventory); setHealth(entityplayer.getHealth()); this.foodData = entityplayer.foodData; this.expLevel = entityplayer.expLevel; this.expTotal = entityplayer.expTotal; this.exp = entityplayer.exp; setScore(entityplayer.getScore()); this.ac = entityplayer.ac; } else if (this.world.getGameRules().getBoolean(GameRules.KEEP_INVENTORY) || entityplayer.isSpectator()) { this.inventory.a(entityplayer.inventory); this.expLevel = entityplayer.expLevel; this.expTotal = entityplayer.expTotal; this.exp = entityplayer.exp; setScore(entityplayer.getScore()); }  this.bG = entityplayer.bG; this.enderChest = entityplayer.enderChest; getDataWatcher().set(bi, entityplayer.getDataWatcher().<Byte>get(bi)); this.lastSentExp = -1; this.lastHealthSent = -1.0F; this.lastFoodSent = -1; if (this.removeQueue != entityplayer.removeQueue) this.removeQueue.addAll(entityplayer.removeQueue);  this.cd = entityplayer.cd; this.ci = entityplayer.ci; setShoulderEntityLeft(entityplayer.getShoulderEntityLeft()); setShoulderEntityRight(entityplayer.getShoulderEntityRight()); } protected void a(MobEffect mobeffect) { super.a(mobeffect); this.playerConnection.sendPacket(new PacketPlayOutEntityEffect(getId(), mobeffect)); if (mobeffect.getMobEffect() == MobEffects.LEVITATION) { this.cg = this.ticksLived; this.cf = getPositionVector(); }  CriterionTriggers.A.a(this); } protected void a(MobEffect mobeffect, boolean flag) { super.a(mobeffect, flag); this.playerConnection.sendPacket(new PacketPlayOutEntityEffect(getId(), mobeffect)); CriterionTriggers.A.a(this); } protected void b(MobEffect mobeffect) { super.b(mobeffect); this.playerConnection.sendPacket(new PacketPlayOutRemoveEntityEffect(getId(), mobeffect.getMobEffect())); if (mobeffect.getMobEffect() == MobEffects.LEVITATION) this.cf = null;  CriterionTriggers.A.a(this); } public void enderTeleportTo(double d0, double d1, double d2) { this.playerConnection.a(d0, d1, d2, this.yaw, this.pitch); } public void teleportAndSync(double d0, double d1, double d2) { enderTeleportTo(d0, d1, d2); this.playerConnection.syncPosition(); } public void a(Entity entity) { getWorldServer().getChunkProvider().broadcastIncludingSelf(this, new PacketPlayOutAnimation(entity, 4)); } public void b(Entity entity) { getWorldServer().getChunkProvider().broadcastIncludingSelf(this, new PacketPlayOutAnimation(entity, 5)); } public void updateAbilities() { if (this.playerConnection != null) { this.playerConnection.sendPacket(new PacketPlayOutAbilities(this.abilities)); C(); }  } public WorldServer getWorldServer() { return (WorldServer)this.world; } public void a(EnumGamemode enumgamemode) { if (enumgamemode == this.playerInteractManager.getGameMode()) return;  PlayerGameModeChangeEvent event = new PlayerGameModeChangeEvent((Player)getBukkitEntity(), GameMode.getByValue(enumgamemode.getId())); this.world.getServer().getPluginManager().callEvent((Event)event); if (event.isCancelled()) return;  this.playerInteractManager.setGameMode(enumgamemode); this.playerConnection.sendPacket(new PacketPlayOutGameStateChange(PacketPlayOutGameStateChange.d, enumgamemode.getId())); if (enumgamemode == EnumGamemode.SPECTATOR) { releaseShoulderEntities(); stopRiding(); } else { setSpectatorTarget(this); }  updateAbilities(); dT(); } public boolean isSpectator() { return (this.playerInteractManager.getGameMode() == EnumGamemode.SPECTATOR); } public boolean isCreative() { return (this.playerInteractManager.getGameMode() == EnumGamemode.CREATIVE); } public void sendMessage(IChatBaseComponent ichatbasecomponent, UUID uuid) { a(ichatbasecomponent, ChatMessageType.SYSTEM, uuid); } public void a(IChatBaseComponent ichatbasecomponent, ChatMessageType chatmessagetype, UUID uuid) { this.playerConnection.a(new PacketPlayOutChat(ichatbasecomponent, chatmessagetype, uuid), future -> { if (!future.isSuccess() && (chatmessagetype == ChatMessageType.GAME_INFO || chatmessagetype == ChatMessageType.SYSTEM)) { boolean flag = true; String s = ichatbasecomponent.a(256); IChatMutableComponent ichatmutablecomponent = (new ChatComponentText(s)).a(EnumChatFormat.YELLOW); this.playerConnection.sendPacket(new PacketPlayOutChat((new ChatMessage("multiplayer.message_not_delivered", new Object[] { ichatmutablecomponent })).a(EnumChatFormat.RED), ChatMessageType.SYSTEM, uuid)); }  }); } public String v() { String s = this.playerConnection.networkManager.getSocketAddress().toString(); s = s.substring(s.indexOf("/") + 1); s = s.substring(0, s.indexOf(":")); return s; } public void a(PacketPlayInSettings packetplayinsettings) { (new PlayerClientOptionsChangeEvent((Player)getBukkitEntity(), packetplayinsettings.locale, packetplayinsettings.viewDistance, ClientOption.ChatVisibility.valueOf(packetplayinsettings.getChatVisibility().name()), packetplayinsettings.hasChatColorsEnabled(), (SkinParts)new PaperSkinParts(packetplayinsettings.getSkinParts()), (packetplayinsettings.getMainHand() == EnumMainHand.LEFT) ? MainHand.LEFT : MainHand.RIGHT)).callEvent(); if (getMainHand() != packetplayinsettings.getMainHand()) { PlayerChangedMainHandEvent event = new PlayerChangedMainHandEvent((Player)getBukkitEntity(), (getMainHand() == EnumMainHand.LEFT) ? MainHand.LEFT : MainHand.RIGHT); this.server.server.getPluginManager().callEvent((Event)event); }  if (this.locale == null || !this.locale.equals(packetplayinsettings.locale)) { PlayerLocaleChangeEvent event = new PlayerLocaleChangeEvent((Player)getBukkitEntity(), packetplayinsettings.locale); this.server.server.getPluginManager().callEvent((Event)event); (new PlayerLocaleChangeEvent((Player)getBukkitEntity(), this.locale, packetplayinsettings.locale)).callEvent(); }  this.locale = packetplayinsettings.locale; this.clientViewDistance = Integer.valueOf(packetplayinsettings.viewDistance); this.bY = packetplayinsettings.d(); this.bZ = packetplayinsettings.e(); getDataWatcher().set(bi, Byte.valueOf((byte)packetplayinsettings.f())); getDataWatcher().set(bj, Byte.valueOf((byte)((packetplayinsettings.getMainHand() == EnumMainHand.LEFT) ? 0 : 1))); } public EnumChatVisibility getChatFlags() { return this.bY; } public void setResourcePack(String s, String s1) { this.playerConnection.sendPacket(new PacketPlayOutResourcePackSend(s, s1)); } protected int y() { return this.server.b(getProfile()); } public void resetIdleTimer() { this.ca = SystemUtils.getMonotonicMillis(); } public ServerStatisticManager getStatisticManager() { return this.serverStatisticManager; } public RecipeBookServer getRecipeBook() { return this.recipeBook; } public void c(Entity entity) { if (entity instanceof EntityHuman) { this.playerConnection.sendPacket(new PacketPlayOutEntityDestroy(new int[] { entity.getId() })); } else { this.removeQueue.add(Integer.valueOf(entity.getId())); }  } public void d(Entity entity) { this.removeQueue.remove(Integer.valueOf(entity.getId())); } protected void C() { if (isSpectator()) { de(); setInvisible(true); } else { super.C(); }  } public Entity getSpecatorTarget() { return (this.spectatedEntity == null) ? this : this.spectatedEntity; } public void setSpectatorTarget(Entity newSpectatorTarget) { Entity entity1 = getSpecatorTarget(); if (newSpectatorTarget == null) newSpectatorTarget = this;  if (entity1 == newSpectatorTarget) return;  if (newSpectatorTarget == this) { PlayerStopSpectatingEntityEvent playerStopSpectatingEntityEvent = new PlayerStopSpectatingEntityEvent((Player)getBukkitEntity(), (Entity)entity1.getBukkitEntity()); if (!playerStopSpectatingEntityEvent.callEvent()) return;  } else { PlayerStartSpectatingEntityEvent playerStartSpectatingEntityEvent = new PlayerStartSpectatingEntityEvent((Player)getBukkitEntity(), (Entity)entity1.getBukkitEntity(), (Entity)newSpectatorTarget.getBukkitEntity()); if (!playerStartSpectatingEntityEvent.callEvent()) return;  }  if (newSpectatorTarget != this) { if (newSpectatorTarget.dead || newSpectatorTarget.shouldBeRemoved || !newSpectatorTarget.valid || newSpectatorTarget.world == null) { MinecraftServer.LOGGER.info("Blocking player " + toString() + " from spectating invalid entity " + newSpectatorTarget.toString()); return; }  if (isFrozen()) { MinecraftServer.LOGGER.debug("Blocking frozen player " + toString() + " from spectating entity " + newSpectatorTarget.toString()); return; }  }  this.spectatedEntity = newSpectatorTarget; if (newSpectatorTarget != this) { ejectPassengers(); getBukkitEntity().teleport(new Location((World)newSpectatorTarget.getWorld().getWorld(), newSpectatorTarget.locX(), newSpectatorTarget.locY(), newSpectatorTarget.locZ(), this.yaw, this.pitch), PlayerTeleportEvent.TeleportCause.SPECTATE); PlayerChunkMap.EntityTracker tracker = (PlayerChunkMap.EntityTracker)(((WorldServer)newSpectatorTarget.world).getChunkProvider()).playerChunkMap.trackedEntities.get(newSpectatorTarget.getId()); if (tracker != null) tracker.updatePlayer(this);  } else { this.playerConnection.teleport(this.spectatedEntity.locX(), this.spectatedEntity.locY(), this.spectatedEntity.locZ(), this.yaw, this.pitch, PlayerTeleportEvent.TeleportCause.SPECTATE); }  this.playerConnection.sendPacket(new PacketPlayOutCamera(newSpectatorTarget)); } protected void E() { if (!this.worldChangeInvuln) super.E();  } public void attack(Entity entity) { if (this.playerInteractManager.getGameMode() == EnumGamemode.SPECTATOR) { setSpectatorTarget(entity); } else { super.attack(entity); }  } public long F() { return this.ca; } @Nullable public IChatBaseComponent getPlayerListName() { return this.listName; } public void swingHand(EnumHand enumhand) { super.swingHand(enumhand); resetAttackCooldown(); } public boolean H() { return this.worldChangeInvuln; } public void I() { this.worldChangeInvuln = false; } public AdvancementDataPlayer getAdvancementData() { return this.advancementDataPlayer; } public void a(WorldServer worldserver, double d0, double d1, double d2, float f, float f1) { a(worldserver, d0, d1, d2, f, f1, PlayerTeleportEvent.TeleportCause.UNKNOWN); } public void a(WorldServer worldserver, double d0, double d1, double d2, float f, float f1, PlayerTeleportEvent.TeleportCause cause) { setSpectatorTarget(this); stopRiding(); getBukkitEntity().teleport(new Location((World)worldserver.getWorld(), d0, d1, d2, f, f1), cause); } @Nullable public BlockPosition getSpawn() { return this.spawn; } public float getSpawnAngle() { return this.spawnAngle; } public ResourceKey<World> getSpawnDimension() { return this.spawnDimension; } public boolean isSpawnForced() { return this.spawnForced; } public void setRespawnPosition(ResourceKey<World> resourcekey, @Nullable BlockPosition blockposition, float f, boolean flag, boolean flag1) { if (blockposition != null) { boolean flag2 = (blockposition.equals(this.spawn) && resourcekey.equals(this.spawnDimension)); if (flag1 && !flag2) sendMessage(new ChatMessage("block.minecraft.set_spawn"), SystemUtils.b);  this.spawn = blockposition; this.spawnDimension = resourcekey; this.spawnAngle = f; this.spawnForced = flag; } else { this.spawn = null; this.spawnDimension = World.OVERWORLD; this.spawnAngle = 0.0F; this.spawnForced = false; }  } public void a(ChunkCoordIntPair chunkcoordintpair, Packet<?> packet, Packet<?> packet1) { this.playerConnection.sendPacket(packet1); this.playerConnection.sendPacket(packet); } public void a(ChunkCoordIntPair chunkcoordintpair) { if (isAlive()) this.playerConnection.sendPacket(new PacketPlayOutUnloadChunk(chunkcoordintpair.x, chunkcoordintpair.z));  } public final SectionPosition getPlayerMapSection() { return O(); } public SectionPosition O() { return this.cj; } public void a(SectionPosition sectionposition) { this.cj = sectionposition; } public void a(SoundEffect soundeffect, SoundCategory soundcategory, float f, float f1) { this.playerConnection.sendPacket(new PacketPlayOutNamedSoundEffect(soundeffect, soundcategory, locX(), locY(), locZ(), f, f1)); } public Packet<?> P() { return new PacketPlayOutNamedEntitySpawn(this); } public EntityItem a(ItemStack itemstack, boolean flag, boolean flag1) { EntityItem entityitem = super.a(itemstack, flag, flag1); if (entityitem == null) return null;  this.world.addEntity(entityitem); ItemStack itemstack1 = entityitem.getItemStack(); if (flag1) { if (!itemstack1.isEmpty()) a(StatisticList.ITEM_DROPPED.b(itemstack1.getItem()), itemstack.getCount());  a(StatisticList.DROP); }  return entityitem; } public long getPlayerTime() { if (this.relativeTime) return this.world.getDayTime() + this.timeOffset;  return this.world.getDayTime() - this.world.getDayTime() % 24000L + this.timeOffset; } public void setPlayerWeather(WeatherType type, boolean plugin) { if (!plugin && this.weather != null) {
/*      */       return;
/*      */     }
/*      */     
/* 2012 */     if (plugin) {
/* 2013 */       this.weather = type;
/*      */     }
/*      */     
/* 2016 */     if (type == WeatherType.DOWNFALL) {
/* 2017 */       this.playerConnection.sendPacket(new PacketPlayOutGameStateChange(PacketPlayOutGameStateChange.c, 0.0F));
/*      */     } else {
/* 2019 */       this.playerConnection.sendPacket(new PacketPlayOutGameStateChange(PacketPlayOutGameStateChange.b, 0.0F));
/*      */     }  }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateWeather(float oldRain, float newRain, float oldThunder, float newThunder) {
/* 2027 */     if (this.weather == null) {
/*      */       
/* 2029 */       if (oldRain != newRain) {
/* 2030 */         this.playerConnection.sendPacket(new PacketPlayOutGameStateChange(PacketPlayOutGameStateChange.h, newRain));
/*      */       
/*      */       }
/*      */     }
/* 2034 */     else if (this.pluginRainPositionPrevious != this.pluginRainPosition) {
/* 2035 */       this.playerConnection.sendPacket(new PacketPlayOutGameStateChange(PacketPlayOutGameStateChange.h, this.pluginRainPosition));
/*      */     } 
/*      */ 
/*      */     
/* 2039 */     if (oldThunder != newThunder) {
/* 2040 */       if (this.weather == WeatherType.DOWNFALL || this.weather == null) {
/* 2041 */         this.playerConnection.sendPacket(new PacketPlayOutGameStateChange(PacketPlayOutGameStateChange.i, newThunder));
/*      */       } else {
/* 2043 */         this.playerConnection.sendPacket(new PacketPlayOutGameStateChange(PacketPlayOutGameStateChange.i, 0.0F));
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public void tickWeather() {
/* 2049 */     if (this.weather == null)
/*      */       return; 
/* 2051 */     this.pluginRainPositionPrevious = this.pluginRainPosition;
/* 2052 */     if (this.weather == WeatherType.DOWNFALL) {
/* 2053 */       this.pluginRainPosition = (float)(this.pluginRainPosition + 0.01D);
/*      */     } else {
/* 2055 */       this.pluginRainPosition = (float)(this.pluginRainPosition - 0.01D);
/*      */     } 
/*      */     
/* 2058 */     this.pluginRainPosition = MathHelper.a(this.pluginRainPosition, 0.0F, 1.0F);
/*      */   }
/*      */   
/*      */   public void resetPlayerWeather() {
/* 2062 */     this.weather = null;
/* 2063 */     setPlayerWeather(this.world.getWorldData().hasStorm() ? WeatherType.DOWNFALL : WeatherType.CLEAR, false);
/*      */   }
/*      */ 
/*      */   
/*      */   public String toString() {
/* 2068 */     return super.toString() + "(" + getName() + " at " + locX() + "," + locY() + "," + locZ() + ")";
/*      */   }
/*      */ 
/*      */   
/*      */   public void forceSetPositionRotation(double x, double y, double z, float yaw, float pitch) {
/* 2073 */     setPositionRotation(x, y, z, yaw, pitch);
/* 2074 */     this.playerConnection.syncPosition();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isFrozen() {
/* 2079 */     return (super.isFrozen() || (this.playerConnection != null && this.playerConnection.isDisconnected()));
/*      */   }
/*      */ 
/*      */   
/*      */   public Scoreboard getScoreboard() {
/* 2084 */     return getBukkitEntity().getScoreboard().getHandle();
/*      */   }
/*      */   
/*      */   public void reset() {
/* 2088 */     float exp = 0.0F;
/* 2089 */     boolean keepInventory = this.world.getGameRules().getBoolean(GameRules.KEEP_INVENTORY);
/*      */     
/* 2091 */     if (this.keepLevel || keepInventory) {
/* 2092 */       exp = this.exp;
/* 2093 */       this.newTotalExp = this.expTotal;
/* 2094 */       this.newLevel = this.expLevel;
/*      */     } 
/*      */     
/* 2097 */     setHealth(getMaxHealth());
/* 2098 */     setAirTicks(getMaxAirTicks());
/* 2099 */     this.fireTicks = 0;
/* 2100 */     this.fallDistance = 0.0F;
/* 2101 */     this.foodData = new FoodMetaData(this);
/* 2102 */     this.expLevel = this.newLevel;
/* 2103 */     this.expTotal = this.newTotalExp;
/* 2104 */     this.exp = 0.0F;
/* 2105 */     this.deathTicks = 0;
/* 2106 */     setArrowCount(0, true);
/* 2107 */     removeAllEffects(EntityPotionEffectEvent.Cause.DEATH);
/* 2108 */     this.updateEffects = true;
/* 2109 */     this.activeContainer = this.defaultContainer;
/* 2110 */     this.killer = null;
/* 2111 */     this.lastDamager = null;
/* 2112 */     this.combatTracker = new CombatTracker(this);
/* 2113 */     this.lastSentExp = -1;
/* 2114 */     if (this.keepLevel || keepInventory) {
/* 2115 */       this.exp = exp;
/*      */     } else {
/* 2117 */       giveExp(this.newExp);
/*      */     } 
/* 2119 */     this.keepLevel = false;
/*      */   }
/*      */ 
/*      */   
/*      */   public CraftPlayer getBukkitEntity() {
/* 2124 */     return (CraftPlayer)super.getBukkitEntity();
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */