/*      */ package net.minecraft.server.v1_16_R2;
/*      */ import co.aikar.timings.MinecraftTimings;
/*      */ import com.destroystokyo.paper.PaperConfig;
/*      */ import com.destroystokyo.paper.event.brigadier.AsyncPlayerSendSuggestionsEvent;
/*      */ import com.destroystokyo.paper.event.player.PlayerJumpEvent;
/*      */ import com.destroystokyo.paper.event.player.PlayerRecipeBookClickEvent;
/*      */ import com.destroystokyo.paper.event.server.AsyncTabCompleteEvent;
/*      */ import com.google.common.primitives.Doubles;
/*      */ import com.google.common.primitives.Floats;
/*      */ import com.mojang.brigadier.ParseResults;
/*      */ import com.mojang.brigadier.StringReader;
/*      */ import com.mojang.brigadier.suggestion.Suggestions;
/*      */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*      */ import io.netty.util.concurrent.Future;
/*      */ import io.netty.util.concurrent.GenericFutureListener;
/*      */ import it.unimi.dsi.fastutil.ints.Int2ShortMap;
/*      */ import java.util.Collections;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Objects;
/*      */ import java.util.Optional;
/*      */ import java.util.Set;
/*      */ import java.util.concurrent.ExecutionException;
/*      */ import java.util.concurrent.atomic.AtomicInteger;
/*      */ import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
/*      */ import org.bukkit.Bukkit;
/*      */ import org.bukkit.Location;
/*      */ import org.bukkit.command.CommandException;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftNamespacedKey;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.util.LazyPlayerSet;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.util.Waitable;
/*      */ import org.bukkit.entity.Player;
/*      */ import org.bukkit.event.Event;
/*      */ import org.bukkit.event.block.Action;
/*      */ import org.bukkit.event.block.SignChangeEvent;
/*      */ import org.bukkit.event.inventory.ClickType;
/*      */ import org.bukkit.event.inventory.CraftItemEvent;
/*      */ import org.bukkit.event.inventory.InventoryAction;
/*      */ import org.bukkit.event.inventory.InventoryClickEvent;
/*      */ import org.bukkit.event.inventory.InventoryCloseEvent;
/*      */ import org.bukkit.event.inventory.InventoryCreativeEvent;
/*      */ import org.bukkit.event.inventory.InventoryType;
/*      */ import org.bukkit.event.player.AsyncPlayerChatEvent;
/*      */ import org.bukkit.event.player.PlayerAnimationEvent;
/*      */ import org.bukkit.event.player.PlayerChatEvent;
/*      */ import org.bukkit.event.player.PlayerCommandPreprocessEvent;
/*      */ import org.bukkit.event.player.PlayerInteractAtEntityEvent;
/*      */ import org.bukkit.event.player.PlayerInteractEvent;
/*      */ import org.bukkit.event.player.PlayerItemHeldEvent;
/*      */ import org.bukkit.event.player.PlayerKickEvent;
/*      */ import org.bukkit.event.player.PlayerMoveEvent;
/*      */ import org.bukkit.event.player.PlayerResourcePackStatusEvent;
/*      */ import org.bukkit.event.player.PlayerSwapHandItemsEvent;
/*      */ import org.bukkit.event.player.PlayerTeleportEvent;
/*      */ import org.bukkit.event.player.PlayerToggleFlightEvent;
/*      */ import org.bukkit.event.player.PlayerToggleSneakEvent;
/*      */ import org.bukkit.event.player.PlayerToggleSprintEvent;
/*      */ import org.bukkit.inventory.EquipmentSlot;
/*      */ import org.bukkit.inventory.Inventory;
/*      */ import org.bukkit.inventory.InventoryView;
/*      */ import org.bukkit.inventory.ItemStack;
/*      */ import org.bukkit.inventory.Recipe;
/*      */ import org.bukkit.util.NumberConversions;
/*      */ import org.spigotmc.SpigotConfig;
/*      */ 
/*      */ public class PlayerConnection implements PacketListenerPlayIn {
/*   71 */   private static final Logger LOGGER = LogManager.getLogger();
/*      */   public final NetworkManager networkManager;
/*      */   private final MinecraftServer minecraftServer;
/*      */   Runnable playerJoinReady;
/*      */   public EntityPlayer player;
/*      */   private int e;
/*   77 */   private long lastKeepAlive = SystemUtils.getMonotonicMillis(); private boolean awaitingKeepAlive; private long h; private volatile int chatThrottle; private void setLastPing(long lastPing) { this.lastKeepAlive = lastPing; } private long getLastPing() { return this.lastKeepAlive; }
/*   78 */   private void setPendingPing(boolean isPending) { this.awaitingKeepAlive = isPending; } private boolean isPendingPing() { return this.awaitingKeepAlive; }
/*   79 */   private void setKeepAliveID(long keepAliveID) { this.h = keepAliveID; } private long getKeepAliveID() { return this.h; }
/*      */ 
/*      */   
/*   82 */   private static final AtomicIntegerFieldUpdater chatSpamField = AtomicIntegerFieldUpdater.newUpdater(PlayerConnection.class, "chatThrottle");
/*   83 */   private final AtomicInteger tabSpamLimiter = new AtomicInteger();
/*      */   
/*      */   private int j;
/*   86 */   private final Int2ShortMap k = (Int2ShortMap)new Int2ShortOpenHashMap();
/*      */   private double l;
/*      */   private double m;
/*      */   private double n;
/*      */   private double o;
/*      */   private double p;
/*      */   private double q;
/*      */   private Entity r;
/*      */   private double s;
/*      */   private double t;
/*      */   private double u;
/*      */   private double v;
/*      */   private double w;
/*      */   private double x;
/*      */   private Vec3D teleportPos;
/*      */   private int teleportAwait;
/*      */   private int A;
/*      */   private boolean B;
/*      */   private int C;
/*      */   private boolean D;
/*      */   private int E;
/*      */   private int receivedMovePackets;
/*      */   private int processedMovePackets;
/*  109 */   private static final int MAX_SIGN_LINE_LENGTH = Integer.getInteger("Paper.maxSignLength", 80).intValue();
/*  110 */   private static final long KEEPALIVE_LIMIT = Long.getLong("paper.playerconnection.keepalive", 30L).longValue() * 1000L;
/*      */   
/*  112 */   private String clientBrandName = null; private final CraftServer server; public boolean processedDisconnect; private int lastTick; private int allowedPlayerTicks; private int lastDropTick;
/*      */   private int lastBookTick;
/*      */   private int dropCount;
/*      */   private static final int SURVIVAL_PLACE_DISTANCE_SQUARED = 36;
/*      */   private static final int CREATIVE_PLACE_DISTANCE_SQUARED = 49;
/*      */   private double lastPosX;
/*      */   private double lastPosY;
/*      */   private double lastPosZ;
/*      */   private float lastPitch;
/*      */   private float lastYaw;
/*      */   private boolean justTeleported;
/*      */   private boolean hasMoved;
/*      */   private int limitedPackets;
/*      */   private long lastLimitedPacket;
/*      */   
/*  127 */   public PlayerConnection(MinecraftServer minecraftserver, NetworkManager networkmanager, EntityPlayer entityplayer) { this.lastTick = MinecraftServer.currentTick;
/*  128 */     this.allowedPlayerTicks = 1;
/*  129 */     this.lastDropTick = MinecraftServer.currentTick;
/*  130 */     this.lastBookTick = MinecraftServer.currentTick;
/*  131 */     this.dropCount = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  136 */     this.lastPosX = Double.MAX_VALUE;
/*  137 */     this.lastPosY = Double.MAX_VALUE;
/*  138 */     this.lastPosZ = Double.MAX_VALUE;
/*  139 */     this.lastPitch = Float.MAX_VALUE;
/*  140 */     this.lastYaw = Float.MAX_VALUE;
/*  141 */     this.justTeleported = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1417 */     this.lastLimitedPacket = -1L; this.minecraftServer = minecraftserver; this.networkManager = networkmanager; networkmanager.setPacketListener(this); this.player = entityplayer; entityplayer.playerConnection = this; this.server = minecraftserver.server; }
/* 1418 */   public CraftPlayer getPlayer() { return (this.player == null) ? null : this.player.getBukkitEntity(); } public void tick() { Runnable playerJoinReady = this.playerJoinReady; if (playerJoinReady != null) { this.playerJoinReady = null; playerJoinReady.run(); }  if (this.player.valid) { syncPosition(); this.player.lastX = this.player.locX(); this.player.lastY = this.player.locY(); this.player.lastZ = this.player.locZ(); this.player.playerTick(); this.player.setLocation(this.l, this.m, this.n, this.player.yaw, this.player.pitch); this.e++; this.processedMovePackets = this.receivedMovePackets; if (this.B && !this.player.isSleeping()) { if (++this.C > 80) { LOGGER.warn("{} was kicked for floating too long!", this.player.getDisplayName().getString()); disconnect(PaperConfig.flyingKickPlayerMessage); return; }  } else { this.B = false; this.C = 0; }  this.r = this.player.getRootVehicle(); if (this.r != this.player && this.r.getRidingPassenger() == this.player) { this.s = this.r.locX(); this.t = this.r.locY(); this.u = this.r.locZ(); this.v = this.r.locX(); this.w = this.r.locY(); this.x = this.r.locZ(); if (this.D && this.player.getRootVehicle().getRidingPassenger() == this.player) { if (++this.E > 80) { LOGGER.warn("{} was kicked for floating a vehicle too long!", this.player.getDisplayName().getString()); disconnect(PaperConfig.flyingKickVehicleMessage); return; }  } else { this.D = false; this.E = 0; }  } else { this.r = null; this.D = false; this.E = 0; }  }  this.minecraftServer.getMethodProfiler().enter("keepAlive"); long currentTime = SystemUtils.getMonotonicMillis(); long elapsedTime = currentTime - getLastPing(); if (isPendingPing()) { if (!this.processedDisconnect && elapsedTime >= KEEPALIVE_LIMIT) { LOGGER.warn("{} was kicked due to keepalive timeout!", this.player.getName()); disconnect(new ChatMessage("disconnect.timeout", new Object[0])); }  } else if (elapsedTime >= 15000L) { setPendingPing(true); setLastPing(currentTime); setKeepAliveID(currentTime); sendPacket(new PacketPlayOutKeepAlive(getKeepAliveID())); }  this.minecraftServer.getMethodProfiler().exit(); int spam; while ((spam = this.chatThrottle) > 0 && !chatSpamField.compareAndSet(this, spam, spam - 1)); if (this.tabSpamLimiter.get() > 0) this.tabSpamLimiter.getAndDecrement();  if (this.j > 0) this.j--;  if (this.player.F() > 0L && this.minecraftServer.getIdleTimeout() > 0 && SystemUtils.getMonotonicMillis() - this.player.F() > (this.minecraftServer.getIdleTimeout() * 1000 * 60)) { this.player.resetIdleTimer(); disconnect(new ChatMessage("multiplayer.disconnect.idling")); }  } public void syncPosition() { this.l = this.player.locX(); this.m = this.player.locY(); this.n = this.player.locZ(); this.o = this.player.locX(); this.p = this.player.locY(); this.q = this.player.locZ(); } public NetworkManager a() { return this.networkManager; } private boolean isExemptPlayer() { return this.minecraftServer.a(this.player.getProfile()); } @Deprecated public void disconnect(IChatBaseComponent ichatbasecomponent) { disconnect(CraftChatMessage.fromComponent(ichatbasecomponent)); } public void disconnect(String s) { if (this.processedDisconnect) return;  String leaveMessage = EnumChatFormat.YELLOW + this.player.getName() + " left the game."; PlayerKickEvent event = new PlayerKickEvent(this.server.getPlayer(this.player), s, leaveMessage); if (this.server.getServer().isRunning()) this.server.getPluginManager().callEvent((Event)event);  if (event.isCancelled()) return;  s = event.getReason(); IChatBaseComponent ichatbasecomponent = CraftChatMessage.fromString(s, true)[0]; this.networkManager.sendPacket(new PacketPlayOutKickDisconnect(ichatbasecomponent), future -> this.networkManager.close(ichatbasecomponent)); a(ichatbasecomponent); this.networkManager.stopReading(); MinecraftServer minecraftserver = this.minecraftServer; NetworkManager networkmanager = this.networkManager; this.networkManager.getClass(); Objects.requireNonNull(networkmanager); minecraftserver.scheduleOnMain(networkmanager::handleDisconnection); } public void a(PacketPlayInSteerVehicle packetplayinsteervehicle) { PlayerConnectionUtils.ensureMainThread(packetplayinsteervehicle, this, this.player.getWorldServer()); this.player.a(packetplayinsteervehicle.b(), packetplayinsteervehicle.c(), packetplayinsteervehicle.d(), packetplayinsteervehicle.e()); } private static boolean b(PacketPlayInFlying packetplayinflying) { return (Doubles.isFinite(packetplayinflying.a(0.0D)) && Doubles.isFinite(packetplayinflying.b(0.0D)) && Doubles.isFinite(packetplayinflying.c(0.0D)) && Floats.isFinite(packetplayinflying.b(0.0F)) && Floats.isFinite(packetplayinflying.a(0.0F))) ? ((Math.abs(packetplayinflying.a(0.0D)) > 3.0E7D || Math.abs(packetplayinflying.b(0.0D)) > 3.0E7D || Math.abs(packetplayinflying.c(0.0D)) > 3.0E7D)) : true; } private static boolean b(PacketPlayInVehicleMove packetplayinvehiclemove) { return (!Doubles.isFinite(packetplayinvehiclemove.getX()) || !Doubles.isFinite(packetplayinvehiclemove.getY()) || !Doubles.isFinite(packetplayinvehiclemove.getZ()) || !Floats.isFinite(packetplayinvehiclemove.getPitch()) || !Floats.isFinite(packetplayinvehiclemove.getYaw())); } public void a(PacketPlayInVehicleMove packetplayinvehiclemove) { PlayerConnectionUtils.ensureMainThread(packetplayinvehiclemove, this, this.player.getWorldServer()); if (b(packetplayinvehiclemove)) { disconnect(new ChatMessage("multiplayer.disconnect.invalid_vehicle_movement")); } else { Entity entity = this.player.getRootVehicle(); if (entity != this.player && entity.getRidingPassenger() == this.player && entity == this.r) { double speed; WorldServer worldserver = this.player.getWorldServer(); double d0 = entity.locX(), fromX = d0; double d1 = entity.locY(), fromY = d1; double d2 = entity.locZ(), fromZ = d2; double d3 = packetplayinvehiclemove.getX(), toX = d3; double d4 = packetplayinvehiclemove.getY(), toY = d4; double d5 = packetplayinvehiclemove.getZ(), toZ = d5; float f = packetplayinvehiclemove.getYaw(); float f1 = packetplayinvehiclemove.getPitch(); double d6 = d3 - this.s; double d7 = d4 - this.t; double d8 = d5 - this.u; double d9 = entity.getMot().g(); double currDeltaX = toX - fromX; double currDeltaY = toY - fromY; double currDeltaZ = toZ - fromZ; double d10 = Math.max(d6 * d6 + d7 * d7 + d8 * d8, currDeltaX * currDeltaX + currDeltaY * currDeltaY + currDeltaZ * currDeltaZ - 1.0D); this.allowedPlayerTicks = (int)(this.allowedPlayerTicks + System.currentTimeMillis() / 50L - this.lastTick); this.allowedPlayerTicks = Math.max(this.allowedPlayerTicks, 1); this.lastTick = (int)(System.currentTimeMillis() / 50L); this.receivedMovePackets++; int i = this.receivedMovePackets - this.processedMovePackets; if (i > Math.max(this.allowedPlayerTicks, 5)) { LOGGER.debug(this.player.getName() + " is sending move packets too frequently (" + i + " packets since last tick)"); i = 1; }  if (d10 > 0.0D) { this.allowedPlayerTicks--; } else { this.allowedPlayerTicks = 20; }  if (this.player.abilities.isFlying) { speed = (this.player.abilities.flySpeed * 20.0F); } else { speed = (this.player.abilities.walkSpeed * 10.0F); }  speed *= 2.0D; if ((this.player.world.paperConfig.preventMovingIntoUnloadedChunks && !worldserver.areChunksLoadedForMove(this.player.getBoundingBoxAt(this.player.locX(), this.player.locY(), this.player.locZ()).expand(toX - this.player.locX(), toY - this.player.locY(), toZ - this.player.locZ()))) || !worldserver.areChunksLoadedForMove(entity.getBoundingBoxAt(entity.locX(), entity.locY(), entity.locZ()).expand(toX - entity.locX(), toY - entity.locY(), toZ - entity.locZ()))) { this.networkManager.sendPacket(new PacketPlayOutVehicleMove(entity)); return; }  if (d10 - d9 > Math.max(100.0D, Math.pow(SpigotConfig.movedTooQuicklyMultiplier * i * speed, 2.0D)) && !isExemptPlayer()) { LOGGER.warn("{} (vehicle of {}) moved too quickly! {},{},{}", entity.getDisplayName().getString(), this.player.getDisplayName().getString(), Double.valueOf(d6), Double.valueOf(d7), Double.valueOf(d8)); this.networkManager.sendPacket(new PacketPlayOutVehicleMove(entity)); return; }  boolean flag = worldserver.getCubes(entity, entity.getBoundingBox().shrink(0.0625D)); d6 = d3 - this.v; d7 = d4 - this.w - 1.0E-6D; d8 = d5 - this.x; entity.move(EnumMoveType.PLAYER, new Vec3D(d6, d7, d8)); double d11 = d7; d6 = d3 - entity.locX(); d7 = d4 - entity.locY(); if (d7 > -0.5D || d7 < 0.5D) d7 = 0.0D;  d8 = d5 - entity.locZ(); d10 = d6 * d6 + d7 * d7 + d8 * d8; boolean flag1 = false; if (d10 > SpigotConfig.movedWronglyThreshold) { flag1 = true; LOGGER.warn("{} (vehicle of {}) moved wrongly! {}", entity.getDisplayName().getString(), this.player.getDisplayName().getString(), Double.valueOf(Math.sqrt(d10))); }  Location curPos = getPlayer().getLocation(); entity.setLocation(d3, d4, d5, f, f1); this.player.setLocation(d3, d4, d5, this.player.yaw, this.player.pitch); boolean flag2 = worldserver.getCubes(entity, entity.getBoundingBox().shrink(0.0625D)); if (flag && (flag1 || !flag2)) { entity.setLocation(d0, d1, d2, f, f1); this.player.setLocation(d0, d1, d2, this.player.yaw, this.player.pitch); this.networkManager.sendPacket(new PacketPlayOutVehicleMove(entity)); return; }  CraftPlayer craftPlayer = getPlayer(); if (!this.hasMoved) { this.lastPosX = curPos.getX(); this.lastPosY = curPos.getY(); this.lastPosZ = curPos.getZ(); this.lastYaw = curPos.getYaw(); this.lastPitch = curPos.getPitch(); this.hasMoved = true; }  Location from = new Location(craftPlayer.getWorld(), this.lastPosX, this.lastPosY, this.lastPosZ, this.lastYaw, this.lastPitch); Location to = craftPlayer.getLocation().clone(); to.setX(packetplayinvehiclemove.getX()); to.setY(packetplayinvehiclemove.getY()); to.setZ(packetplayinvehiclemove.getZ()); to.setYaw(packetplayinvehiclemove.getYaw()); to.setPitch(packetplayinvehiclemove.getPitch()); double delta = Math.pow(this.lastPosX - to.getX(), 2.0D) + Math.pow(this.lastPosY - to.getY(), 2.0D) + Math.pow(this.lastPosZ - to.getZ(), 2.0D); float deltaAngle = Math.abs(this.lastYaw - to.getYaw()) + Math.abs(this.lastPitch - to.getPitch()); if ((delta > 0.00390625D || deltaAngle > 10.0F) && !this.player.isFrozen()) { this.lastPosX = to.getX(); this.lastPosY = to.getY(); this.lastPosZ = to.getZ(); this.lastYaw = to.getYaw(); this.lastPitch = to.getPitch(); Location oldTo = to.clone(); PlayerMoveEvent event = new PlayerMoveEvent((Player)craftPlayer, from, to); this.server.getPluginManager().callEvent((Event)event); if (event.isCancelled()) { teleport(from); return; }  if (!oldTo.equals(event.getTo()) && !event.isCancelled()) { this.player.getBukkitEntity().teleport(event.getTo(), PlayerTeleportEvent.TeleportCause.PLUGIN); return; }  if (!from.equals(getPlayer().getLocation()) && this.justTeleported) { this.justTeleported = false; return; }  }  this.player.getWorldServer().getChunkProvider().movePlayer(this.player); this.player.checkMovement(this.player.locX() - d0, this.player.locY() - d1, this.player.locZ() - d2); this.D = (d11 >= -0.03125D && !this.minecraftServer.getAllowFlight() && a(entity)); this.v = entity.locX(); this.w = entity.locY(); this.x = entity.locZ(); }  }  } private boolean a(Entity entity) { return entity.world.a(entity.getBoundingBox().g(0.0625D).b(0.0D, -0.55D, 0.0D)).allMatch(BlockBase.BlockData::isAir); } public void a(PacketPlayInTeleportAccept packetplayinteleportaccept) { PlayerConnectionUtils.ensureMainThread(packetplayinteleportaccept, this, this.player.getWorldServer()); if (packetplayinteleportaccept.b() == this.teleportAwait && this.teleportPos != null) { this.player.setPositionRotation(this.teleportPos.x, this.teleportPos.y, this.teleportPos.z, this.player.yaw, this.player.pitch); this.o = this.teleportPos.x; this.p = this.teleportPos.y; this.q = this.teleportPos.z; if (this.player.H()) this.player.I();  this.teleportPos = null; this.player.getWorldServer().getChunkProvider().movePlayer(this.player); }  } public void a(PacketPlayInRecipeDisplayed packetplayinrecipedisplayed) { PlayerConnectionUtils.ensureMainThread(packetplayinrecipedisplayed, this, this.player.getWorldServer()); Optional<? extends IRecipe<?>> optional = this.minecraftServer.getCraftingManager().getRecipe(packetplayinrecipedisplayed.b()); RecipeBookServer recipebookserver = this.player.getRecipeBook(); Objects.requireNonNull(recipebookserver); optional.ifPresent(recipebookserver::e); } public void a(PacketPlayInRecipeSettings packetplayinrecipesettings) { PlayerConnectionUtils.ensureMainThread(packetplayinrecipesettings, this, this.player.getWorldServer()); this.player.getRecipeBook().a(packetplayinrecipesettings.b(), packetplayinrecipesettings.c(), packetplayinrecipesettings.d()); } public void a(PacketPlayInAdvancements packetplayinadvancements) { PlayerConnectionUtils.ensureMainThread(packetplayinadvancements, this, this.player.getWorldServer()); if (packetplayinadvancements.c() == PacketPlayInAdvancements.Status.OPENED_TAB) { MinecraftKey minecraftkey = packetplayinadvancements.d(); Advancement advancement = this.minecraftServer.getAdvancementData().a(minecraftkey); if (advancement != null) this.player.getAdvancementData().a(advancement);  }  } public void a(PacketPlayInTabComplete packetplayintabcomplete) { if (this.tabSpamLimiter.addAndGet(PaperConfig.tabSpamIncrement) > PaperConfig.tabSpamLimit && !this.minecraftServer.getPlayerList().isOp(this.player.getProfile())) { this.minecraftServer.scheduleOnMain(() -> disconnect(new ChatMessage("disconnect.spam", new Object[0]))); return; }  String str = packetplayintabcomplete.c(); int index = -1; if (str.length() > 64 && ((index = str.indexOf(' ')) == -1 || index >= 64)) { this.minecraftServer.scheduleOnMain(() -> disconnect(new ChatMessage("disconnect.spam", new Object[0]))); return; }  StringReader stringreader = new StringReader(packetplayintabcomplete.c()); if (stringreader.canRead() && stringreader.peek() == '/') stringreader.skip();  List<String> completions = new ArrayList<>(); String buffer = packetplayintabcomplete.c(); AsyncTabCompleteEvent event = new AsyncTabCompleteEvent((CommandSender)getPlayer(), completions, buffer, true, null); event.callEvent(); completions = event.isCancelled() ? (List<String>)ImmutableList.of() : event.getCompletions(); if (!event.isHandled()) { if (!event.isCancelled()) this.minecraftServer.scheduleOnMain(() -> { ParseResults<CommandListenerWrapper> parseresults = this.minecraftServer.getCommandDispatcher().a().parse(stringreader, this.player.getCommandListener()); this.minecraftServer.getCommandDispatcher().a().getCompletionSuggestions(parseresults).thenAccept(()); });  } else if (!completions.isEmpty()) { SuggestionsBuilder builder = new SuggestionsBuilder(packetplayintabcomplete.c(), stringreader.getTotalLength()); builder = builder.createOffset(builder.getInput().lastIndexOf(' ') + 1); Objects.requireNonNull(builder); completions.forEach(builder::suggest); Suggestions suggestions = builder.buildFuture().join(); AsyncPlayerSendSuggestionsEvent suggestEvent = new AsyncPlayerSendSuggestionsEvent((Player)getPlayer(), suggestions, buffer); suggestEvent.setCancelled(suggestions.isEmpty()); if (!suggestEvent.callEvent()) return;  this.networkManager.sendPacket(new PacketPlayOutTabComplete(packetplayintabcomplete.b(), suggestEvent.getSuggestions())); }  } public void a(PacketPlayInSetCommandBlock packetplayinsetcommandblock) { PlayerConnectionUtils.ensureMainThread(packetplayinsetcommandblock, this, this.player.getWorldServer()); if (!this.minecraftServer.getEnableCommandBlock()) { this.player.sendMessage(new ChatMessage("advMode.notEnabled"), SystemUtils.b); } else if (!this.player.isCreativeAndOp() && !this.player.isCreative() && !this.player.getBukkitEntity().hasPermission("minecraft.commandblock")) { this.player.sendMessage(new ChatMessage("advMode.notAllowed"), SystemUtils.b); } else { CommandBlockListenerAbstract commandblocklistenerabstract = null; TileEntityCommand tileentitycommand = null; BlockPosition blockposition = packetplayinsetcommandblock.b(); TileEntity tileentity = this.player.world.getTileEntity(blockposition); if (tileentity instanceof TileEntityCommand) { tileentitycommand = (TileEntityCommand)tileentity; commandblocklistenerabstract = tileentitycommand.getCommandBlock(); }  String s = packetplayinsetcommandblock.c(); boolean flag = packetplayinsetcommandblock.d(); if (commandblocklistenerabstract != null) { IBlockData iblockdata; TileEntityCommand.Type tileentitycommand_type = tileentitycommand.m(); EnumDirection enumdirection = (EnumDirection)this.player.world.getType(blockposition).get(BlockCommand.a); switch (packetplayinsetcommandblock.g()) { case ALLOW: iblockdata = Blocks.CHAIN_COMMAND_BLOCK.getBlockData(); this.player.world.setTypeAndData(blockposition, iblockdata.set(BlockCommand.a, enumdirection).set(BlockCommand.b, Boolean.valueOf(packetplayinsetcommandblock.e())), 2); break;case DEFAULT: iblockdata = Blocks.REPEATING_COMMAND_BLOCK.getBlockData(); this.player.world.setTypeAndData(blockposition, iblockdata.set(BlockCommand.a, enumdirection).set(BlockCommand.b, Boolean.valueOf(packetplayinsetcommandblock.e())), 2); break;default: iblockdata = Blocks.COMMAND_BLOCK.getBlockData(); this.player.world.setTypeAndData(blockposition, iblockdata.set(BlockCommand.a, enumdirection).set(BlockCommand.b, Boolean.valueOf(packetplayinsetcommandblock.e())), 2); break; }  tileentity.r(); this.player.world.setTileEntity(blockposition, tileentity); commandblocklistenerabstract.setCommand(s); commandblocklistenerabstract.a(flag); if (!flag) commandblocklistenerabstract.b((IChatBaseComponent)null);  tileentitycommand.b(packetplayinsetcommandblock.f()); if (tileentitycommand_type != packetplayinsetcommandblock.g()) tileentitycommand.h();  commandblocklistenerabstract.e(); if (!UtilColor.b(s)) this.player.sendMessage(new ChatMessage("advMode.setCommand.success", new Object[] { s }), SystemUtils.b);  }  }  } public void a(PacketPlayInSetCommandMinecart packetplayinsetcommandminecart) { PlayerConnectionUtils.ensureMainThread(packetplayinsetcommandminecart, this, this.player.getWorldServer()); if (!this.minecraftServer.getEnableCommandBlock()) { this.player.sendMessage(new ChatMessage("advMode.notEnabled"), SystemUtils.b); } else if (!this.player.isCreativeAndOp() && !this.player.isCreative() && !this.player.getBukkitEntity().hasPermission("minecraft.commandblock")) { this.player.sendMessage(new ChatMessage("advMode.notAllowed"), SystemUtils.b); } else { CommandBlockListenerAbstract commandblocklistenerabstract = packetplayinsetcommandminecart.a(this.player.world); if (commandblocklistenerabstract != null) { commandblocklistenerabstract.setCommand(packetplayinsetcommandminecart.b()); commandblocklistenerabstract.a(packetplayinsetcommandminecart.c()); if (!packetplayinsetcommandminecart.c()) commandblocklistenerabstract.b((IChatBaseComponent)null);  commandblocklistenerabstract.e(); this.player.sendMessage(new ChatMessage("advMode.setCommand.success", new Object[] { packetplayinsetcommandminecart.b() }), SystemUtils.b); }  }  } public void a(PacketPlayInPickItem packetplayinpickitem) { PlayerConnectionUtils.ensureMainThread(packetplayinpickitem, this, this.player.getWorldServer()); if (packetplayinpickitem.b() < 0 || packetplayinpickitem.b() >= this.player.inventory.items.size()) { LOGGER.warn("{} tried to set an invalid carried item", this.player.getDisplayName().getString()); disconnect("Invalid hotbar selection (Hacking?)"); return; }  this.player.inventory.c(packetplayinpickitem.b()); this.player.playerConnection.sendPacket(new PacketPlayOutSetSlot(-2, this.player.inventory.itemInHandIndex, this.player.inventory.getItem(this.player.inventory.itemInHandIndex))); this.player.playerConnection.sendPacket(new PacketPlayOutSetSlot(-2, packetplayinpickitem.b(), this.player.inventory.getItem(packetplayinpickitem.b()))); this.player.playerConnection.sendPacket(new PacketPlayOutHeldItemSlot(this.player.inventory.itemInHandIndex)); } private static final int THRESHOLD = PaperConfig.packetInSpamThreshold;
/*      */   public void a(PacketPlayInItemName packetplayinitemname) { PlayerConnectionUtils.ensureMainThread(packetplayinitemname, this, this.player.getWorldServer()); if (this.player.activeContainer instanceof ContainerAnvil) { ContainerAnvil containeranvil = (ContainerAnvil)this.player.activeContainer; String s = SharedConstants.a(packetplayinitemname.b()); if (s.length() <= 35) containeranvil.a(s);  }  }
/*      */   public void a(PacketPlayInBeacon packetplayinbeacon) { PlayerConnectionUtils.ensureMainThread(packetplayinbeacon, this, this.player.getWorldServer()); if (this.player.activeContainer instanceof ContainerBeacon) ((ContainerBeacon)this.player.activeContainer).c(packetplayinbeacon.b(), packetplayinbeacon.c());  }
/* 1421 */   public void a(PacketPlayInStruct packetplayinstruct) { PlayerConnectionUtils.ensureMainThread(packetplayinstruct, this, this.player.getWorldServer()); if (this.player.isCreativeAndOp()) { BlockPosition blockposition = packetplayinstruct.b(); IBlockData iblockdata = this.player.world.getType(blockposition); TileEntity tileentity = this.player.world.getTileEntity(blockposition); if (tileentity instanceof TileEntityStructure) { TileEntityStructure tileentitystructure = (TileEntityStructure)tileentity; tileentitystructure.setUsageMode(packetplayinstruct.d()); tileentitystructure.setStructureName(packetplayinstruct.e()); tileentitystructure.b(packetplayinstruct.f()); tileentitystructure.c(packetplayinstruct.g()); tileentitystructure.b(packetplayinstruct.h()); tileentitystructure.b(packetplayinstruct.i()); tileentitystructure.b(packetplayinstruct.j()); tileentitystructure.a(packetplayinstruct.k()); tileentitystructure.d(packetplayinstruct.l()); tileentitystructure.e(packetplayinstruct.m()); tileentitystructure.a(packetplayinstruct.n()); tileentitystructure.a(packetplayinstruct.o()); if (tileentitystructure.g()) { String s = tileentitystructure.getStructureName(); if (packetplayinstruct.c() == TileEntityStructure.UpdateType.SAVE_AREA) { if (tileentitystructure.D()) { this.player.a(new ChatMessage("structure_block.save_success", new Object[] { s }), false); } else { this.player.a(new ChatMessage("structure_block.save_failure", new Object[] { s }), false); }  } else if (packetplayinstruct.c() == TileEntityStructure.UpdateType.LOAD_AREA) { if (!tileentitystructure.F()) { this.player.a(new ChatMessage("structure_block.load_not_found", new Object[] { s }), false); } else if (tileentitystructure.a(this.player.getWorldServer())) { this.player.a(new ChatMessage("structure_block.load_success", new Object[] { s }), false); } else { this.player.a(new ChatMessage("structure_block.load_prepare", new Object[] { s }), false); }  } else if (packetplayinstruct.c() == TileEntityStructure.UpdateType.SCAN_AREA) { if (tileentitystructure.C()) { this.player.a(new ChatMessage("structure_block.size_success", new Object[] { s }), false); } else { this.player.a(new ChatMessage("structure_block.size_failure"), false); }  }  } else { this.player.a(new ChatMessage("structure_block.invalid_structure_name", new Object[] { packetplayinstruct.e() }), false); }  tileentitystructure.update(); this.player.world.notify(blockposition, iblockdata, iblockdata, 3); }  }  } public void a(PacketPlayInSetJigsaw packetplayinsetjigsaw) { PlayerConnectionUtils.ensureMainThread(packetplayinsetjigsaw, this, this.player.getWorldServer()); if (this.player.isCreativeAndOp()) { BlockPosition blockposition = packetplayinsetjigsaw.b(); IBlockData iblockdata = this.player.world.getType(blockposition); TileEntity tileentity = this.player.world.getTileEntity(blockposition); if (tileentity instanceof TileEntityJigsaw) { TileEntityJigsaw tileentityjigsaw = (TileEntityJigsaw)tileentity; tileentityjigsaw.a(packetplayinsetjigsaw.c()); tileentityjigsaw.b(packetplayinsetjigsaw.d()); tileentityjigsaw.c(packetplayinsetjigsaw.e()); tileentityjigsaw.a(packetplayinsetjigsaw.f()); tileentityjigsaw.a(packetplayinsetjigsaw.g()); tileentityjigsaw.update(); this.player.world.notify(blockposition, iblockdata, iblockdata, 3); }  }  } public void a(PacketPlayInJigsawGenerate packetplayinjigsawgenerate) { PlayerConnectionUtils.ensureMainThread(packetplayinjigsawgenerate, this, this.player.getWorldServer()); if (this.player.isCreativeAndOp()) { BlockPosition blockposition = packetplayinjigsawgenerate.b(); TileEntity tileentity = this.player.world.getTileEntity(blockposition); if (tileentity instanceof TileEntityJigsaw) { TileEntityJigsaw tileentityjigsaw = (TileEntityJigsaw)tileentity; tileentityjigsaw.a(this.player.getWorldServer(), packetplayinjigsawgenerate.c(), packetplayinjigsawgenerate.d()); }  }  } public void a(PacketPlayInTrSel packetplayintrsel) { PlayerConnectionUtils.ensureMainThread(packetplayintrsel, this, this.player.getWorldServer()); int i = packetplayintrsel.b(); Container container = this.player.activeContainer; if (container instanceof ContainerMerchant) { ContainerMerchant containermerchant = (ContainerMerchant)container; CraftEventFactory.callTradeSelectEvent(this.player, i, containermerchant); containermerchant.d(i); containermerchant.g(i); }  } public void a(PacketPlayInBEdit packetplayinbedit) { ItemStack testStack = packetplayinbedit.b(); if (!this.server.isPrimaryThread() && !testStack.isEmpty() && testStack.getTag() != null) { NBTTagList pageList = testStack.getTag().getList("pages", 8); long byteTotal = 0L; int maxBookPageSize = PaperConfig.maxBookPageSize; double multiplier = Math.max(0.3D, Math.min(1.0D, PaperConfig.maxBookTotalSizeMultiplier)); long byteAllowed = maxBookPageSize; for (int i = 0; i < pageList.size(); i++) { String testString = pageList.getString(i); int byteLength = (testString.getBytes(StandardCharsets.UTF_8)).length; byteTotal += byteLength; int length = testString.length(); int multibytes = 0; if (byteLength != length) for (char c : testString.toCharArray()) { if (c > '') multibytes++;  }   byteAllowed = (long)(byteAllowed + maxBookPageSize * Math.min(1.0D, Math.max(0.1D, length / 255.0D)) * multiplier); if (multibytes > 1) byteAllowed -= multibytes;  }  if (byteTotal > byteAllowed) { LOGGER.warn(this.player.getName() + " tried to send too large of a book. Book Size: " + byteTotal + " - Allowed:  " + byteAllowed + " - Pages: " + pageList.size()); this.minecraftServer.scheduleOnMain(() -> disconnect("Book too large!")); return; }  }  PlayerConnectionUtils.ensureMainThread(packetplayinbedit, this, this.player.getWorldServer()); if (this.lastBookTick + 20 > MinecraftServer.currentTick) { disconnect("Book edited too quickly!"); return; }  this.lastBookTick = MinecraftServer.currentTick; EnumItemSlot enumitemslot = (packetplayinbedit.d() == EnumHand.MAIN_HAND) ? EnumItemSlot.MAINHAND : EnumItemSlot.OFFHAND; ItemStack itemstack = packetplayinbedit.b(); if (!itemstack.isEmpty() && ItemBookAndQuill.a(itemstack.getTag())) { ItemStack itemstack1 = this.player.b(packetplayinbedit.d()); if (itemstack.getItem() == Items.WRITABLE_BOOK && itemstack1.getItem() == Items.WRITABLE_BOOK) if (packetplayinbedit.c()) { ItemStack itemstack2 = new ItemStack(Items.WRITTEN_BOOK); NBTTagCompound nbttagcompound = itemstack1.getTag(); if (nbttagcompound != null) itemstack2.setTag(nbttagcompound.clone());  itemstack2.a("author", NBTTagString.a(this.player.getDisplayName().getString())); itemstack2.a("title", NBTTagString.a(itemstack.getTag().getString("title"))); NBTTagList nbttaglist = itemstack.getTag().getList("pages", 8); for (int i = 0; i < nbttaglist.size(); i++) { String s = nbttaglist.getString(i); ChatComponentText chatcomponenttext = new ChatComponentText(s); s = IChatBaseComponent.ChatSerializer.a(chatcomponenttext); nbttaglist.set(i, NBTTagString.a(s)); }  itemstack2.a("pages", nbttaglist); this.player.a(packetplayinbedit.d(), CraftEventFactory.handleEditBookEvent(this.player, enumitemslot, itemstack1, itemstack2)); } else { ItemStack newBook = itemstack1.cloneItemStack(); newBook.getOrCreateTagAndSet("pages", itemstack.getTag().getList("pages", 8)); this.player.setSlot(enumitemslot, CraftEventFactory.handleEditBookEvent(this.player, enumitemslot, itemstack1, newBook)); }   }  } public void a(PacketPlayInEntityNBTQuery packetplayinentitynbtquery) { PlayerConnectionUtils.ensureMainThread(packetplayinentitynbtquery, this, this.player.getWorldServer()); if (this.player.k(2)) { Entity entity = this.player.getWorldServer().getEntity(packetplayinentitynbtquery.c()); if (entity != null) { NBTTagCompound nbttagcompound = entity.save(new NBTTagCompound()); this.player.playerConnection.sendPacket(new PacketPlayOutNBTQuery(packetplayinentitynbtquery.b(), nbttagcompound)); }  }  } public void a(PacketPlayInTileNBTQuery packetplayintilenbtquery) { PlayerConnectionUtils.ensureMainThread(packetplayintilenbtquery, this, this.player.getWorldServer()); if (this.player.k(2)) { TileEntity tileentity = this.player.getWorldServer().getTileEntity(packetplayintilenbtquery.c()); NBTTagCompound nbttagcompound = (tileentity != null) ? tileentity.save(new NBTTagCompound()) : null; this.player.playerConnection.sendPacket(new PacketPlayOutNBTQuery(packetplayintilenbtquery.b(), nbttagcompound)); }  } public void a(PacketPlayInFlying packetplayinflying) { PlayerConnectionUtils.ensureMainThread(packetplayinflying, this, this.player.getWorldServer()); if (b(packetplayinflying)) { disconnect(new ChatMessage("multiplayer.disconnect.invalid_player_movement")); } else { WorldServer worldserver = this.player.getWorldServer(); if (!this.player.viewingCredits && !this.player.isFrozen()) { if (this.e == 0) syncPosition();  if (this.teleportPos != null) { this.allowedPlayerTicks = 20; } else { this.A = this.e; if (this.player.isPassenger()) { this.player.setLocation(this.player.locX(), this.player.locY(), this.player.locZ(), packetplayinflying.a(this.player.yaw), packetplayinflying.b(this.player.pitch)); this.player.getWorldServer().getChunkProvider().movePlayer(this.player); this.allowedPlayerTicks = 20; } else { double prevX = this.player.locX(); double prevY = this.player.locY(); double prevZ = this.player.locZ(); float prevYaw = this.player.yaw; float prevPitch = this.player.pitch; double d0 = this.player.locX(); double d1 = this.player.locY(); double d2 = this.player.locZ(); double d3 = this.player.locY(); double d4 = packetplayinflying.a(this.player.locX()), toX = d4; double d5 = packetplayinflying.b(this.player.locY()), toY = d5; double d6 = packetplayinflying.c(this.player.locZ()), toZ = d6; float f = packetplayinflying.a(this.player.yaw); float f1 = packetplayinflying.b(this.player.pitch); double d7 = d4 - this.l; double d8 = d5 - this.m; double d9 = d6 - this.n; double d10 = this.player.getMot().g(); double currDeltaX = toX - prevX; double currDeltaY = toY - prevY; double currDeltaZ = toZ - prevZ; double d11 = Math.max(d7 * d7 + d8 * d8 + d9 * d9, currDeltaX * currDeltaX + currDeltaY * currDeltaY + currDeltaZ * currDeltaZ - 1.0D); if (this.player.isSleeping()) { if (d11 > 1.0D) a(this.player.locX(), this.player.locY(), this.player.locZ(), packetplayinflying.a(this.player.yaw), packetplayinflying.b(this.player.pitch));  } else { double speed; this.receivedMovePackets++; int i = this.receivedMovePackets - this.processedMovePackets; this.allowedPlayerTicks = (int)(this.allowedPlayerTicks + System.currentTimeMillis() / 50L - this.lastTick); this.allowedPlayerTicks = Math.max(this.allowedPlayerTicks, 1); this.lastTick = (int)(System.currentTimeMillis() / 50L); if (i > Math.max(this.allowedPlayerTicks, 5)) { LOGGER.debug("{} is sending move packets too frequently ({} packets since last tick)", this.player.getDisplayName().getString(), Integer.valueOf(i)); i = 1; }  if (packetplayinflying.hasLook || d11 > 0.0D) { this.allowedPlayerTicks--; } else { this.allowedPlayerTicks = 20; }  if (this.player.abilities.isFlying) { speed = (this.player.abilities.flySpeed * 20.0F); } else { speed = (this.player.abilities.walkSpeed * 10.0F); }  if (this.player.world.paperConfig.preventMovingIntoUnloadedChunks && !((WorldServer)this.player.world).areChunksLoadedForMove(this.player.getBoundingBoxAt(this.player.locX(), this.player.locY(), this.player.locZ()).expand(toX - this.player.locX(), toY - this.player.locY(), toZ - this.player.locZ()))) { internalTeleport(this.player.locX(), this.player.locY(), this.player.locZ(), this.player.yaw, this.player.pitch, Collections.emptySet()); return; }  if (!this.player.H() && (!this.player.getWorldServer().getGameRules().getBoolean(GameRules.DISABLE_ELYTRA_MOVEMENT_CHECK) || !this.player.isGliding())) { float f2 = this.player.isGliding() ? 300.0F : 100.0F; if (d11 - d10 > Math.max(f2, Math.pow(SpigotConfig.movedTooQuicklyMultiplier * i * speed, 2.0D)) && !isExemptPlayer()) { LOGGER.warn("{} moved too quickly! {},{},{}", this.player.getDisplayName().getString(), Double.valueOf(d7), Double.valueOf(d8), Double.valueOf(d9)); a(this.player.locX(), this.player.locY(), this.player.locZ(), this.player.yaw, this.player.pitch); return; }  }  AxisAlignedBB axisalignedbb = this.player.getBoundingBox(); d7 = d4 - this.o; d8 = d5 - this.p; d9 = d6 - this.q; boolean flag = (d8 > 0.0D); if (this.player.isOnGround() && !packetplayinflying.b() && flag) { CraftPlayer craftPlayer = getPlayer(); Location from = new Location(craftPlayer.getWorld(), this.lastPosX, this.lastPosY, this.lastPosZ, this.lastYaw, this.lastPitch); Location to = craftPlayer.getLocation().clone(); if (packetplayinflying.hasPos) { to.setX(packetplayinflying.x); to.setY(packetplayinflying.y); to.setZ(packetplayinflying.z); }  if (packetplayinflying.hasLook) { to.setYaw(packetplayinflying.yaw); to.setPitch(packetplayinflying.pitch); }  PlayerJumpEvent event = new PlayerJumpEvent((Player)craftPlayer, from, to); if (event.callEvent()) { this.player.jump(); } else { from = event.getFrom(); internalTeleport(from.getX(), from.getY(), from.getZ(), from.getYaw(), from.getPitch(), Collections.emptySet()); return; }  }  this.player.move(EnumMoveType.PLAYER, new Vec3D(d7, d8, d9)); boolean didCollide = (toX != this.player.locX() || toY != this.player.locY() || toZ != this.player.locZ()); this.player.setOnGround(packetplayinflying.b()); if (this.teleportPos != null) return;  double d12 = d8; d7 = d4 - this.player.locX(); d8 = d5 - this.player.locY(); if (d8 > -0.5D || d8 < 0.5D) d8 = 0.0D;  d9 = d6 - this.player.locZ(); d11 = d7 * d7 + d8 * d8 + d9 * d9; boolean flag1 = false; if (!this.player.H() && d11 > SpigotConfig.movedWronglyThreshold && !this.player.isSleeping() && !this.player.playerInteractManager.isCreative() && this.player.playerInteractManager.getGameMode() != EnumGamemode.SPECTATOR) { flag1 = true; LOGGER.warn("{} moved wrongly!", this.player.getDisplayName().getString()); }  this.player.setLocation(d4, d5, d6, f, f1); if (!this.player.noclip && !this.player.isSleeping() && ((flag1 && worldserver.getCubes(this.player, axisalignedbb)) || (didCollide && a(worldserver, axisalignedbb)))) { a(d0, d1, d2, f, f1); } else { this.player.setLocation(prevX, prevY, prevZ, prevYaw, prevPitch); CraftPlayer craftPlayer = getPlayer(); Location from = new Location(craftPlayer.getWorld(), this.lastPosX, this.lastPosY, this.lastPosZ, this.lastYaw, this.lastPitch); Location to = craftPlayer.getLocation().clone(); if (packetplayinflying.hasPos) { to.setX(packetplayinflying.x); to.setY(packetplayinflying.y); to.setZ(packetplayinflying.z); }  if (packetplayinflying.hasLook) { to.setYaw(packetplayinflying.yaw); to.setPitch(packetplayinflying.pitch); }  double delta = Math.pow(this.lastPosX - to.getX(), 2.0D) + Math.pow(this.lastPosY - to.getY(), 2.0D) + Math.pow(this.lastPosZ - to.getZ(), 2.0D); float deltaAngle = Math.abs(this.lastYaw - to.getYaw()) + Math.abs(this.lastPitch - to.getPitch()); if ((delta > 0.00390625D || deltaAngle > 10.0F) && !this.player.isFrozen()) { this.lastPosX = to.getX(); this.lastPosY = to.getY(); this.lastPosZ = to.getZ(); this.lastYaw = to.getYaw(); this.lastPitch = to.getPitch(); if (from.getX() != Double.MAX_VALUE) { Location oldTo = to.clone(); PlayerMoveEvent event = new PlayerMoveEvent((Player)craftPlayer, from, to); this.server.getPluginManager().callEvent((Event)event); if (event.isCancelled()) { teleport(from); return; }  if (!oldTo.equals(event.getTo()) && !event.isCancelled()) { this.player.getBukkitEntity().teleport(event.getTo(), PlayerTeleportEvent.TeleportCause.PLUGIN); return; }  if (!from.equals(getPlayer().getLocation()) && this.justTeleported) { this.justTeleported = false; return; }  }  }  this.player.setLocation(d4, d5, d6, f, f1); this.B = (d12 >= -0.03125D && this.player.playerInteractManager.getGameMode() != EnumGamemode.SPECTATOR && !this.minecraftServer.getAllowFlight() && !this.player.abilities.canFly && !this.player.hasEffect(MobEffects.LEVITATION) && !this.player.isGliding() && a(this.player) && !this.player.isRiptiding()); this.player.getWorldServer().getChunkProvider().movePlayer(this.player); this.player.a(this.player.locY() - d3, packetplayinflying.b()); if (flag) this.player.fallDistance = 0.0F;  this.player.checkMovement(this.player.locX() - d0, this.player.locY() - d1, this.player.locZ() - d2); this.o = this.player.locX(); this.p = this.player.locY(); this.q = this.player.locZ(); }  }  }  }  }  }  } private boolean a(IWorldReader iworldreader, AxisAlignedBB axisalignedbb) { Stream<VoxelShape> stream = iworldreader.d(this.player, this.player.getBoundingBox().shrink(9.999999747378752E-6D), entity -> true); VoxelShape voxelshape = VoxelShapes.a(axisalignedbb.shrink(9.999999747378752E-6D)); return stream.anyMatch(voxelshape1 -> !VoxelShapes.c(voxelshape1, voxelshape, OperatorBoolean.AND)); } public void a(double d0, double d1, double d2, float f, float f1) { a(d0, d1, d2, f, f1, Collections.emptySet()); } public final void teleport(double d0, double d1, double d2, float f, float f1, PlayerTeleportEvent.TeleportCause cause) { a(d0, d1, d2, f, f1, cause); } public void a(double d0, double d1, double d2, float f, float f1, PlayerTeleportEvent.TeleportCause cause) { a(d0, d1, d2, f, f1, Collections.emptySet(), cause); } public void a(double d0, double d1, double d2, float f, float f1, Set<PacketPlayOutPosition.EnumPlayerTeleportFlags> set) { a(d0, d1, d2, f, f1, set, PlayerTeleportEvent.TeleportCause.UNKNOWN); } public void a(double d0, double d1, double d2, float f, float f1, Set<PacketPlayOutPosition.EnumPlayerTeleportFlags> set, PlayerTeleportEvent.TeleportCause cause) { CraftPlayer craftPlayer = getPlayer(); Location from = craftPlayer.getLocation(); double x = d0; double y = d1; double z = d2; float yaw = f; float pitch = f1; Location to = new Location(getPlayer().getWorld(), x, y, z, yaw, pitch); if (from.equals(to)) { internalTeleport(d0, d1, d2, f, f1, set); return; }  PlayerTeleportEvent event = new PlayerTeleportEvent((Player)craftPlayer, from.clone(), to.clone(), cause); this.server.getPluginManager().callEvent((Event)event); if (event.isCancelled() || !to.equals(event.getTo())) { set.clear(); to = event.isCancelled() ? event.getFrom() : event.getTo(); d0 = to.getX(); d1 = to.getY(); d2 = to.getZ(); f = to.getYaw(); f1 = to.getPitch(); }  internalTeleport(d0, d1, d2, f, f1, set); } public void teleport(Location dest) { internalTeleport(dest.getX(), dest.getY(), dest.getZ(), dest.getYaw(), dest.getPitch(), Collections.emptySet()); } private void internalTeleport(double d0, double d1, double d2, float f, float f1, Set<PacketPlayOutPosition.EnumPlayerTeleportFlags> set) { if (this.player.dead) { LOGGER.info("Attempt to teleport dead player {} restricted", this.player.getName()); return; }  if (Float.isNaN(f)) f = 0.0F;  if (Float.isNaN(f1)) f1 = 0.0F;  this.justTeleported = true; double d3 = set.contains(PacketPlayOutPosition.EnumPlayerTeleportFlags.X) ? this.player.locX() : 0.0D; double d4 = set.contains(PacketPlayOutPosition.EnumPlayerTeleportFlags.Y) ? this.player.locY() : 0.0D; double d5 = set.contains(PacketPlayOutPosition.EnumPlayerTeleportFlags.Z) ? this.player.locZ() : 0.0D; float f2 = set.contains(PacketPlayOutPosition.EnumPlayerTeleportFlags.Y_ROT) ? this.player.yaw : 0.0F; float f3 = set.contains(PacketPlayOutPosition.EnumPlayerTeleportFlags.X_ROT) ? this.player.pitch : 0.0F; this.teleportPos = new Vec3D(d0, d1, d2); if (++this.teleportAwait == Integer.MAX_VALUE) this.teleportAwait = 0;  this.lastPosX = this.teleportPos.x; this.lastPosY = this.teleportPos.y; this.lastPosZ = this.teleportPos.z; this.lastYaw = f; this.lastPitch = f1; this.A = this.e; this.player.setPositionRotation(d0, d1, d2, f, f1); this.player.forceCheckHighPriority(); this.player.playerConnection.sendPacket(new PacketPlayOutPosition(d0 - d3, d1 - d4, d2 - d5, f - f2, f1 - f3, set, this.teleportAwait)); } public void a(PacketPlayInBlockDig packetplayinblockdig) { PlayerConnectionUtils.ensureMainThread(packetplayinblockdig, this, this.player.getWorldServer()); if (this.player.isFrozen()) return;  BlockPosition blockposition = packetplayinblockdig.b(); this.player.resetIdleTimer(); PacketPlayInBlockDig.EnumPlayerDigType packetplayinblockdig_enumplayerdigtype = packetplayinblockdig.d(); switch (packetplayinblockdig_enumplayerdigtype) { case ALLOW: if (!this.player.isSpectator()) { ItemStack itemstack = this.player.b(EnumHand.OFF_HAND); CraftItemStack mainHand = CraftItemStack.asCraftMirror(itemstack); CraftItemStack offHand = CraftItemStack.asCraftMirror(this.player.b(EnumHand.MAIN_HAND)); PlayerSwapHandItemsEvent swapItemsEvent = new PlayerSwapHandItemsEvent((Player)getPlayer(), (ItemStack)mainHand.clone(), (ItemStack)offHand.clone()); this.server.getPluginManager().callEvent((Event)swapItemsEvent); if (swapItemsEvent.isCancelled()) return;  if (swapItemsEvent.getOffHandItem().equals(offHand)) { this.player.a(EnumHand.OFF_HAND, this.player.b(EnumHand.MAIN_HAND)); } else { this.player.a(EnumHand.OFF_HAND, CraftItemStack.asNMSCopy(swapItemsEvent.getOffHandItem())); }  if (swapItemsEvent.getMainHandItem().equals(mainHand)) { this.player.a(EnumHand.MAIN_HAND, itemstack); } else { this.player.a(EnumHand.MAIN_HAND, CraftItemStack.asNMSCopy(swapItemsEvent.getMainHandItem())); }  this.player.clearActiveItem(); }  return;case DEFAULT: if (!this.player.isSpectator()) { if (this.lastDropTick != MinecraftServer.currentTick) { this.dropCount = 0; this.lastDropTick = MinecraftServer.currentTick; } else { this.dropCount++; if (this.dropCount >= 20) { LOGGER.warn(this.player.getName() + " dropped their items too quickly!"); disconnect("You dropped your items too quickly (Hacking?)"); return; }  }  this.player.dropItem(false); }  return;case DENY: if (!this.player.isSpectator()) this.player.dropItem(true);  return;case null: this.player.releaseActiveItem(); return;case null: case null: case null: if (this.player.world.getChunkIfLoadedImmediately(blockposition.getX() >> 4, blockposition.getZ() >> 4) == null) return;  this.player.playerInteractManager.a(blockposition, packetplayinblockdig_enumplayerdigtype, packetplayinblockdig.c(), this.minecraftServer.getMaxBuildHeight()); return; }  throw new IllegalArgumentException("Invalid player action"); } private static boolean a(EntityPlayer entityplayer, ItemStack itemstack) { if (itemstack.isEmpty()) return false;  Item item = itemstack.getItem(); return ((item instanceof ItemBlock || item instanceof ItemBucket) && !entityplayer.getCooldownTracker().hasCooldown(item)); } private boolean checkLimit(long timestamp) { if (this.lastLimitedPacket != -1L && timestamp - this.lastLimitedPacket < THRESHOLD && this.limitedPackets++ >= 8) {
/* 1422 */       return false;
/*      */     }
/*      */     
/* 1425 */     if (this.lastLimitedPacket == -1L || timestamp - this.lastLimitedPacket >= THRESHOLD) {
/* 1426 */       this.lastLimitedPacket = timestamp;
/* 1427 */       this.limitedPackets = 0;
/* 1428 */       return true;
/*      */     } 
/*      */     
/* 1431 */     return true; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(PacketPlayInUseItem packetplayinuseitem) {
/* 1437 */     PlayerConnectionUtils.ensureMainThread(packetplayinuseitem, this, this.player.getWorldServer());
/* 1438 */     if (this.player.isFrozen())
/* 1439 */       return;  if (!checkLimit(packetplayinuseitem.timestamp))
/* 1440 */       return;  WorldServer worldserver = this.player.getWorldServer();
/* 1441 */     EnumHand enumhand = packetplayinuseitem.b();
/* 1442 */     ItemStack itemstack = this.player.b(enumhand);
/* 1443 */     MovingObjectPositionBlock movingobjectpositionblock = packetplayinuseitem.c();
/* 1444 */     BlockPosition blockposition = movingobjectpositionblock.getBlockPosition();
/* 1445 */     EnumDirection enumdirection = movingobjectpositionblock.getDirection();
/*      */ 
/*      */     
/* 1448 */     Location eyeLoc = getPlayer().getEyeLocation();
/* 1449 */     double reachDistance = NumberConversions.square(eyeLoc.getX() - blockposition.getX()) + NumberConversions.square(eyeLoc.getY() - blockposition.getY()) + NumberConversions.square(eyeLoc.getZ() - blockposition.getZ());
/* 1450 */     if (reachDistance > ((getPlayer().getGameMode() == GameMode.CREATIVE) ? 49 : 36)) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/* 1455 */     this.player.resetIdleTimer();
/* 1456 */     if (blockposition.getY() < this.minecraftServer.getMaxBuildHeight()) {
/* 1457 */       if (this.teleportPos == null && this.player.h(blockposition.getX() + 0.5D, blockposition.getY() + 0.5D, blockposition.getZ() + 0.5D) < 64.0D && worldserver.a(this.player, blockposition)) {
/*      */ 
/*      */         
/* 1460 */         this.player.clearActiveItem();
/*      */         
/* 1462 */         EnumInteractionResult enuminteractionresult = this.player.playerInteractManager.a(this.player, worldserver, itemstack, enumhand, movingobjectpositionblock);
/*      */         
/* 1464 */         if (enumdirection == EnumDirection.UP && !enuminteractionresult.a() && blockposition.getY() >= this.minecraftServer.getMaxBuildHeight() - 1 && a(this.player, itemstack)) {
/* 1465 */           IChatMutableComponent ichatmutablecomponent = (new ChatMessage("build.tooHigh", new Object[] { Integer.valueOf(this.minecraftServer.getMaxBuildHeight()) })).a(EnumChatFormat.RED);
/*      */           
/* 1467 */           this.player.playerConnection.sendPacket(new PacketPlayOutChat(ichatmutablecomponent, ChatMessageType.GAME_INFO, SystemUtils.b));
/* 1468 */         } else if (enuminteractionresult.b()) {
/* 1469 */           this.player.swingHand(enumhand, true);
/*      */         } 
/*      */       } 
/*      */     } else {
/* 1473 */       IChatMutableComponent ichatmutablecomponent1 = (new ChatMessage("build.tooHigh", new Object[] { Integer.valueOf(this.minecraftServer.getMaxBuildHeight()) })).a(EnumChatFormat.RED);
/*      */       
/* 1475 */       this.player.playerConnection.sendPacket(new PacketPlayOutChat(ichatmutablecomponent1, ChatMessageType.GAME_INFO, SystemUtils.b));
/*      */     } 
/*      */     
/* 1478 */     this.player.playerConnection.sendPacket(new PacketPlayOutBlockChange(worldserver, blockposition));
/* 1479 */     this.player.playerConnection.sendPacket(new PacketPlayOutBlockChange(worldserver, blockposition.shift(enumdirection)));
/*      */   }
/*      */ 
/*      */   
/*      */   public void a(PacketPlayInBlockPlace packetplayinblockplace) {
/* 1484 */     PlayerConnectionUtils.ensureMainThread(packetplayinblockplace, this, this.player.getWorldServer());
/* 1485 */     if (this.player.isFrozen())
/* 1486 */       return;  if (!checkLimit(packetplayinblockplace.timestamp))
/* 1487 */       return;  WorldServer worldserver = this.player.getWorldServer();
/* 1488 */     EnumHand enumhand = packetplayinblockplace.b();
/* 1489 */     ItemStack itemstack = this.player.b(enumhand);
/*      */     
/* 1491 */     this.player.resetIdleTimer();
/* 1492 */     if (!itemstack.isEmpty()) {
/*      */       boolean cancelled;
/*      */       
/* 1495 */       float f1 = this.player.pitch;
/* 1496 */       float f2 = this.player.yaw;
/* 1497 */       double d0 = this.player.locX();
/* 1498 */       double d1 = this.player.locY() + this.player.getHeadHeight();
/* 1499 */       double d2 = this.player.locZ();
/* 1500 */       Vec3D vec3d = new Vec3D(d0, d1, d2);
/*      */       
/* 1502 */       float f3 = MathHelper.cos(-f2 * 0.017453292F - 3.1415927F);
/* 1503 */       float f4 = MathHelper.sin(-f2 * 0.017453292F - 3.1415927F);
/* 1504 */       float f5 = -MathHelper.cos(-f1 * 0.017453292F);
/* 1505 */       float f6 = MathHelper.sin(-f1 * 0.017453292F);
/* 1506 */       float f7 = f4 * f5;
/* 1507 */       float f8 = f3 * f5;
/* 1508 */       double d3 = (this.player.playerInteractManager.getGameMode() == EnumGamemode.CREATIVE) ? 5.0D : 4.5D;
/* 1509 */       Vec3D vec3d1 = vec3d.add(f7 * d3, f6 * d3, f8 * d3);
/* 1510 */       MovingObjectPosition movingobjectposition = this.player.world.rayTrace(new RayTrace(vec3d, vec3d1, RayTrace.BlockCollisionOption.OUTLINE, RayTrace.FluidCollisionOption.NONE, this.player));
/*      */ 
/*      */       
/* 1513 */       if (movingobjectposition == null || movingobjectposition.getType() != MovingObjectPosition.EnumMovingObjectType.BLOCK) {
/* 1514 */         PlayerInteractEvent event = CraftEventFactory.callPlayerInteractEvent(this.player, Action.RIGHT_CLICK_AIR, itemstack, enumhand);
/* 1515 */         cancelled = (event.useItemInHand() == Event.Result.DENY);
/*      */       } else {
/* 1517 */         MovingObjectPositionBlock movingobjectpositionblock = (MovingObjectPositionBlock)movingobjectposition;
/* 1518 */         if (this.player.playerInteractManager.firedInteract && this.player.playerInteractManager.interactPosition.equals(movingobjectpositionblock.getBlockPosition()) && this.player.playerInteractManager.interactHand == enumhand && ItemStack.equals(this.player.playerInteractManager.interactItemStack, itemstack)) {
/* 1519 */           cancelled = this.player.playerInteractManager.interactResult;
/*      */         } else {
/* 1521 */           PlayerInteractEvent event = CraftEventFactory.callPlayerInteractEvent(this.player, Action.RIGHT_CLICK_BLOCK, movingobjectpositionblock.getBlockPosition(), movingobjectpositionblock.getDirection(), itemstack, true, enumhand);
/* 1522 */           cancelled = (event.useItemInHand() == Event.Result.DENY);
/*      */         } 
/* 1524 */         this.player.playerInteractManager.firedInteract = false;
/*      */       } 
/*      */       
/* 1527 */       if (cancelled) {
/* 1528 */         this.player.getBukkitEntity().updateInventory();
/*      */         
/*      */         return;
/*      */       } 
/* 1532 */       itemstack = this.player.getItemInHand(enumhand);
/* 1533 */       if (itemstack.isEmpty())
/*      */         return; 
/* 1535 */       EnumInteractionResult enuminteractionresult = this.player.playerInteractManager.a(this.player, worldserver, itemstack, enumhand);
/*      */       
/* 1537 */       if (enuminteractionresult.b()) {
/* 1538 */         this.player.swingHand(enumhand, true);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(PacketPlayInSpectate packetplayinspectate) {
/* 1546 */     PlayerConnectionUtils.ensureMainThread(packetplayinspectate, this, this.player.getWorldServer());
/* 1547 */     if (this.player.isSpectator()) {
/* 1548 */       Iterator<WorldServer> iterator = this.minecraftServer.getWorlds().iterator();
/*      */       
/* 1550 */       while (iterator.hasNext()) {
/* 1551 */         WorldServer worldserver = iterator.next();
/* 1552 */         Entity entity = packetplayinspectate.a(worldserver);
/*      */         
/* 1554 */         if (entity != null) {
/* 1555 */           this.player.a(worldserver, entity.locX(), entity.locY(), entity.locZ(), entity.yaw, entity.pitch, PlayerTeleportEvent.TeleportCause.SPECTATE);
/*      */           return;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(PacketPlayInResourcePackStatus packetplayinresourcepackstatus) {
/* 1566 */     PlayerConnectionUtils.ensureMainThread(packetplayinresourcepackstatus, this, this.player.getWorldServer());
/*      */     
/* 1568 */     PlayerResourcePackStatusEvent.Status packStatus = PlayerResourcePackStatusEvent.Status.values()[packetplayinresourcepackstatus.status.ordinal()];
/* 1569 */     this.player.getBukkitEntity().setResourcePackStatus(packStatus);
/* 1570 */     this.server.getPluginManager().callEvent((Event)new PlayerResourcePackStatusEvent((Player)getPlayer(), packStatus));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(PacketPlayInBoatMove packetplayinboatmove) {
/* 1577 */     PlayerConnectionUtils.ensureMainThread(packetplayinboatmove, this, this.player.getWorldServer());
/* 1578 */     Entity entity = this.player.getVehicle();
/*      */     
/* 1580 */     if (entity instanceof EntityBoat) {
/* 1581 */       ((EntityBoat)entity).a(packetplayinboatmove.b(), packetplayinboatmove.c());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(IChatBaseComponent ichatbasecomponent) {
/* 1589 */     if (this.processedDisconnect) {
/*      */       return;
/*      */     }
/* 1592 */     this.processedDisconnect = true;
/*      */ 
/*      */     
/* 1595 */     LOGGER.info("{} lost connection: {}", this.player.getDisplayName().getString(), ichatbasecomponent.getString());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1602 */     this.player.p();
/* 1603 */     String quitMessage = this.minecraftServer.getPlayerList().disconnect(this.player);
/* 1604 */     if (quitMessage != null && quitMessage.length() > 0) {
/* 1605 */       this.minecraftServer.getPlayerList().sendMessage(CraftChatMessage.fromString(quitMessage));
/*      */     }
/*      */     
/* 1608 */     if (isExemptPlayer()) {
/* 1609 */       LOGGER.info("Stopping singleplayer server as player logged out");
/* 1610 */       this.minecraftServer.safeShutdown(false);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void sendPacket(Packet<?> packet) {
/* 1616 */     a(packet, (GenericFutureListener<? extends Future<? super Void>>)null);
/*      */   }
/*      */   
/*      */   public void a(Packet<?> packet, @Nullable GenericFutureListener<? extends Future<? super Void>> genericfuturelistener) {
/* 1620 */     if (packet instanceof PacketPlayOutChat) {
/* 1621 */       PacketPlayOutChat packetplayoutchat = (PacketPlayOutChat)packet;
/* 1622 */       EnumChatVisibility enumchatvisibility = this.player.getChatFlags();
/*      */       
/* 1624 */       if (enumchatvisibility == EnumChatVisibility.HIDDEN && packetplayoutchat.d() != ChatMessageType.GAME_INFO) {
/*      */         return;
/*      */       }
/*      */       
/* 1628 */       if (enumchatvisibility == EnumChatVisibility.SYSTEM && !packetplayoutchat.c()) {
/*      */         return;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1634 */     if (packet == null || this.processedDisconnect)
/*      */       return; 
/* 1636 */     if (packet instanceof PacketPlayOutSpawnPosition) {
/* 1637 */       PacketPlayOutSpawnPosition packet6 = (PacketPlayOutSpawnPosition)packet;
/* 1638 */       this.player.compassTarget = new Location(getPlayer().getWorld(), packet6.position.getX(), packet6.position.getY(), packet6.position.getZ());
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/* 1643 */       this.networkManager.sendPacket(packet, genericfuturelistener);
/* 1644 */     } catch (Throwable throwable) {
/* 1645 */       CrashReport crashreport = CrashReport.a(throwable, "Sending packet");
/* 1646 */       CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Packet being sent");
/*      */       
/* 1648 */       crashreportsystemdetails.a("Packet class", () -> packet.getClass().getCanonicalName());
/*      */ 
/*      */       
/* 1651 */       throw new ReportedException(crashreport);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void a(PacketPlayInHeldItemSlot packetplayinhelditemslot) {
/* 1657 */     PlayerConnectionUtils.ensureMainThread(packetplayinhelditemslot, this, this.player.getWorldServer());
/* 1658 */     if (this.player.isFrozen())
/* 1659 */       return;  if (packetplayinhelditemslot.b() >= 0 && packetplayinhelditemslot.b() < PlayerInventory.getHotbarSize()) {
/* 1660 */       PlayerItemHeldEvent event = new PlayerItemHeldEvent((Player)getPlayer(), this.player.inventory.itemInHandIndex, packetplayinhelditemslot.b());
/* 1661 */       this.server.getPluginManager().callEvent((Event)event);
/* 1662 */       if (event.isCancelled()) {
/* 1663 */         sendPacket(new PacketPlayOutHeldItemSlot(this.player.inventory.itemInHandIndex));
/* 1664 */         this.player.resetIdleTimer();
/*      */         
/*      */         return;
/*      */       } 
/* 1668 */       if (this.player.inventory.itemInHandIndex != packetplayinhelditemslot.b() && this.player.getRaisedHand() == EnumHand.MAIN_HAND) {
/* 1669 */         this.player.clearActiveItem();
/*      */       }
/*      */       
/* 1672 */       this.player.inventory.itemInHandIndex = packetplayinhelditemslot.b();
/* 1673 */       this.player.resetIdleTimer();
/*      */     } else {
/* 1675 */       LOGGER.warn("{} tried to set an invalid carried item", this.player.getDisplayName().getString());
/* 1676 */       disconnect("Invalid hotbar selection (Hacking?)");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(PacketPlayInChat packetplayinchat) {
/* 1684 */     if (this.minecraftServer.isStopped()) {
/*      */       return;
/*      */     }
/*      */     
/* 1688 */     boolean isSync = packetplayinchat.b().startsWith("/");
/* 1689 */     if (packetplayinchat.b().startsWith("/")) {
/* 1690 */       PlayerConnectionUtils.ensureMainThread(packetplayinchat, this, this.player.getWorldServer());
/*      */     }
/*      */     
/* 1693 */     if (this.player.dead || this.player.getChatFlags() == EnumChatVisibility.HIDDEN) {
/* 1694 */       sendPacket(new PacketPlayOutChat((new ChatMessage("chat.cannotSend")).a(EnumChatFormat.RED), ChatMessageType.SYSTEM, SystemUtils.b));
/*      */     } else {
/* 1696 */       this.player.resetIdleTimer();
/* 1697 */       String s = StringUtils.normalizeSpace(packetplayinchat.b());
/*      */       
/* 1699 */       for (int i = 0; i < s.length(); i++) {
/* 1700 */         if (!SharedConstants.isAllowedChatCharacter(s.charAt(i))) {
/*      */           
/* 1702 */           if (!isSync) {
/* 1703 */             Waitable waitable = new Waitable()
/*      */               {
/*      */                 protected Object evaluate() {
/* 1706 */                   PlayerConnection.this.disconnect(new ChatMessage("multiplayer.disconnect.illegal_characters"));
/* 1707 */                   return null;
/*      */                 }
/*      */               };
/*      */             
/* 1711 */             this.minecraftServer.processQueue.add(waitable);
/*      */             
/*      */             try {
/* 1714 */               waitable.get();
/* 1715 */             } catch (InterruptedException e) {
/* 1716 */               Thread.currentThread().interrupt();
/* 1717 */             } catch (ExecutionException e) {
/* 1718 */               throw new RuntimeException(e);
/*      */             } 
/*      */           } else {
/* 1721 */             disconnect(new ChatMessage("multiplayer.disconnect.illegal_characters"));
/*      */           } 
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */       } 
/*      */       
/* 1729 */       if (isSync) {
/*      */         try {
/* 1731 */           this.minecraftServer.server.playerCommandState = true;
/* 1732 */           handleCommand(s);
/*      */         } finally {
/* 1734 */           this.minecraftServer.server.playerCommandState = false;
/*      */         } 
/* 1736 */       } else if (s.isEmpty()) {
/* 1737 */         LOGGER.warn(this.player.getName() + " tried to send an empty message");
/* 1738 */       } else if (getPlayer().isConversing()) {
/* 1739 */         final String conversationInput = s;
/* 1740 */         this.minecraftServer.processQueue.add(new Runnable()
/*      */             {
/*      */               public void run() {
/* 1743 */                 PlayerConnection.this.getPlayer().acceptConversationInput(conversationInput);
/*      */               }
/*      */             });
/* 1746 */       } else if (this.player.getChatFlags() == EnumChatVisibility.SYSTEM) {
/* 1747 */         sendPacket(new PacketPlayOutChat((new ChatMessage("chat.cannotSend")).a(EnumChatFormat.RED), ChatMessageType.SYSTEM, SystemUtils.b));
/*      */       } else {
/* 1749 */         chat(s, true);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1758 */       boolean counted = true;
/* 1759 */       for (String exclude : SpigotConfig.spamExclusions) {
/*      */         
/* 1761 */         if (exclude != null && s.startsWith(exclude)) {
/*      */           
/* 1763 */           counted = false;
/*      */ 
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */       
/* 1770 */       if (counted && chatSpamField.addAndGet((T)this, 20) > 200 && !this.minecraftServer.getPlayerList().isOp(this.player.getProfile())) {
/* 1771 */         if (!isSync) {
/* 1772 */           Waitable waitable = new Waitable()
/*      */             {
/*      */               protected Object evaluate() {
/* 1775 */                 PlayerConnection.this.disconnect(new ChatMessage("disconnect.spam"));
/* 1776 */                 return null;
/*      */               }
/*      */             };
/*      */           
/* 1780 */           this.minecraftServer.processQueue.add(waitable);
/*      */           
/*      */           try {
/* 1783 */             waitable.get();
/* 1784 */           } catch (InterruptedException e) {
/* 1785 */             Thread.currentThread().interrupt();
/* 1786 */           } catch (ExecutionException e) {
/* 1787 */             throw new RuntimeException(e);
/*      */           } 
/*      */         } else {
/* 1790 */           disconnect(new ChatMessage("disconnect.spam"));
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void chat(String s, boolean async) {
/* 1800 */     if (s.isEmpty() || this.player.getChatFlags() == EnumChatVisibility.HIDDEN) {
/*      */       return;
/*      */     }
/*      */     
/* 1804 */     if (!async && s.startsWith("/")) {
/*      */       
/* 1806 */       if (!AsyncCatcher.shuttingDown && !Bukkit.isPrimaryThread()) {
/* 1807 */         final String fCommandLine = s;
/* 1808 */         MinecraftServer.LOGGER.log(Level.ERROR, "Command Dispatched Async: " + fCommandLine);
/* 1809 */         MinecraftServer.LOGGER.log(Level.ERROR, "Please notify author of plugin causing this execution to fix this bug! see: http://bit.ly/1oSiM6C", new Throwable());
/* 1810 */         Waitable wait = new Waitable()
/*      */           {
/*      */             protected Object evaluate() {
/* 1813 */               PlayerConnection.this.chat(fCommandLine, false);
/* 1814 */               return null;
/*      */             }
/*      */           };
/* 1817 */         this.minecraftServer.processQueue.add(wait);
/*      */         try {
/* 1819 */           wait.get();
/*      */           return;
/* 1821 */         } catch (InterruptedException e) {
/* 1822 */           Thread.currentThread().interrupt();
/* 1823 */         } catch (Exception e) {
/* 1824 */           throw new RuntimeException("Exception processing chat command", e.getCause());
/*      */         } 
/*      */       } 
/*      */       
/* 1828 */       handleCommand(s);
/* 1829 */     } else if (this.player.getChatFlags() != EnumChatVisibility.SYSTEM) {
/*      */ 
/*      */       
/* 1832 */       CraftPlayer craftPlayer = getPlayer();
/* 1833 */       AsyncPlayerChatEvent event = new AsyncPlayerChatEvent(async, (Player)craftPlayer, s, (Set)new LazyPlayerSet(this.minecraftServer));
/* 1834 */       this.server.getPluginManager().callEvent((Event)event);
/*      */       
/* 1836 */       if ((PlayerChatEvent.getHandlerList().getRegisteredListeners()).length != 0) {
/*      */         
/* 1838 */         final PlayerChatEvent queueEvent = new PlayerChatEvent((Player)craftPlayer, event.getMessage(), event.getFormat(), event.getRecipients());
/* 1839 */         queueEvent.setCancelled(event.isCancelled());
/* 1840 */         Waitable waitable = new Waitable()
/*      */           {
/*      */             protected Object evaluate() {
/* 1843 */               Bukkit.getPluginManager().callEvent((Event)queueEvent);
/*      */               
/* 1845 */               if (queueEvent.isCancelled()) {
/* 1846 */                 return null;
/*      */               }
/*      */               
/* 1849 */               String message = String.format(queueEvent.getFormat(), new Object[] { this.val$queueEvent.getPlayer().getDisplayName(), this.val$queueEvent.getMessage() });
/* 1850 */               PlayerConnection.this.minecraftServer.console.sendMessage(message);
/* 1851 */               if (((LazyPlayerSet)queueEvent.getRecipients()).isLazy()) {
/* 1852 */                 for (EntityPlayer player : (PlayerConnection.this.minecraftServer.getPlayerList()).players) {
/* 1853 */                   ((EntityPlayer)player).sendMessage(CraftChatMessage.fromString(message));
/*      */                 }
/*      */               } else {
/* 1856 */                 for (Player player : queueEvent.getRecipients()) {
/* 1857 */                   player.sendMessage(message);
/*      */                 }
/*      */               } 
/* 1860 */               return null; }
/*      */           };
/* 1862 */         if (async) {
/* 1863 */           this.minecraftServer.processQueue.add(waitable);
/*      */         } else {
/* 1865 */           waitable.run();
/*      */         } 
/*      */         try {
/* 1868 */           waitable.get();
/* 1869 */         } catch (InterruptedException e) {
/* 1870 */           Thread.currentThread().interrupt();
/* 1871 */         } catch (ExecutionException e) {
/* 1872 */           throw new RuntimeException("Exception processing chat event", e.getCause());
/*      */         } 
/*      */       } else {
/* 1875 */         if (event.isCancelled()) {
/*      */           return;
/*      */         }
/*      */ 
/*      */         
/* 1880 */         String displayName = event.getPlayer().getDisplayName();
/* 1881 */         if ((this.player.getWorld()).paperConfig.useVanillaScoreboardColoring) {
/* 1882 */           IChatBaseComponent nameFromTeam = ScoreboardTeam.a(this.player.getScoreboardTeam(), craftPlayer.getHandle().getDisplayName());
/*      */           
/* 1884 */           displayName = (new TextComponent(ComponentSerializer.parse(IChatBaseComponent.ChatSerializer.componentToJson(nameFromTeam)))).toLegacyText() + ChatColor.RESET;
/*      */         } 
/*      */         
/* 1887 */         s = String.format(event.getFormat(), new Object[] { displayName, event.getMessage() });
/*      */         
/* 1889 */         this.minecraftServer.console.sendMessage(s);
/* 1890 */         if (((LazyPlayerSet)event.getRecipients()).isLazy()) {
/* 1891 */           for (EntityPlayer recipient : (this.minecraftServer.getPlayerList()).players) {
/* 1892 */             ((EntityPlayer)recipient).sendMessage(CraftChatMessage.fromString(s));
/*      */           }
/*      */         } else {
/* 1895 */           for (Player recipient : event.getRecipients()) {
/* 1896 */             recipient.sendMessage(s);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void handleCommand(String s) {
/* 1905 */     MinecraftTimings.playerCommandTimer.startTiming();
/*      */     
/* 1907 */     if (SpigotConfig.logCommands) {
/* 1908 */       this; LOGGER.info(this.player.getName() + " issued server command: " + s);
/*      */     } 
/* 1910 */     CraftPlayer player = getPlayer();
/*      */     
/* 1912 */     PlayerCommandPreprocessEvent event = new PlayerCommandPreprocessEvent((Player)player, s, (Set)new LazyPlayerSet(this.minecraftServer));
/* 1913 */     this.server.getPluginManager().callEvent((Event)event);
/*      */     
/* 1915 */     if (event.isCancelled()) {
/* 1916 */       MinecraftTimings.playerCommandTimer.stopTiming();
/*      */       
/*      */       return;
/*      */     } 
/*      */     try {
/* 1921 */       if (this.server.dispatchCommand((CommandSender)event.getPlayer(), event.getMessage().substring(1))) {
/*      */         return;
/*      */       }
/* 1924 */     } catch (CommandException ex) {
/* 1925 */       player.sendMessage(ChatColor.RED + "An internal error occurred while attempting to perform this command");
/* 1926 */       Logger.getLogger(PlayerConnection.class.getName()).log(Level.SEVERE, (String)null, (Throwable)ex);
/*      */       return;
/*      */     } finally {
/* 1929 */       MinecraftTimings.playerCommandTimer.stopTiming();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(PacketPlayInArmAnimation packetplayinarmanimation) {
/* 1937 */     PlayerConnectionUtils.ensureMainThread(packetplayinarmanimation, this, this.player.getWorldServer());
/* 1938 */     if (this.player.isFrozen())
/* 1939 */       return;  this.player.resetIdleTimer();
/*      */     
/* 1941 */     float f1 = this.player.pitch;
/* 1942 */     float f2 = this.player.yaw;
/* 1943 */     double d0 = this.player.locX();
/* 1944 */     double d1 = this.player.locY() + this.player.getHeadHeight();
/* 1945 */     double d2 = this.player.locZ();
/* 1946 */     Vec3D vec3d = new Vec3D(d0, d1, d2);
/*      */     
/* 1948 */     float f3 = MathHelper.cos(-f2 * 0.017453292F - 3.1415927F);
/* 1949 */     float f4 = MathHelper.sin(-f2 * 0.017453292F - 3.1415927F);
/* 1950 */     float f5 = -MathHelper.cos(-f1 * 0.017453292F);
/* 1951 */     float f6 = MathHelper.sin(-f1 * 0.017453292F);
/* 1952 */     float f7 = f4 * f5;
/* 1953 */     float f8 = f3 * f5;
/* 1954 */     double d3 = (this.player.playerInteractManager.getGameMode() == EnumGamemode.CREATIVE) ? 5.0D : 4.5D;
/* 1955 */     Vec3D vec3d1 = vec3d.add(f7 * d3, f6 * d3, f8 * d3);
/* 1956 */     MovingObjectPosition movingobjectposition = this.player.world.rayTrace(new RayTrace(vec3d, vec3d1, RayTrace.BlockCollisionOption.OUTLINE, RayTrace.FluidCollisionOption.NONE, this.player));
/*      */     
/* 1958 */     if (movingobjectposition == null || movingobjectposition.getType() != MovingObjectPosition.EnumMovingObjectType.BLOCK) {
/* 1959 */       CraftEventFactory.callPlayerInteractEvent(this.player, Action.LEFT_CLICK_AIR, this.player.inventory.getItemInHand(), EnumHand.MAIN_HAND);
/*      */     }
/*      */ 
/*      */     
/* 1963 */     PlayerAnimationEvent event = new PlayerAnimationEvent((Player)getPlayer());
/* 1964 */     this.server.getPluginManager().callEvent((Event)event);
/*      */     
/* 1966 */     if (event.isCancelled())
/*      */       return; 
/* 1968 */     this.player.swingHand(packetplayinarmanimation.b());
/*      */   }
/*      */   public void a(PacketPlayInEntityAction packetplayinentityaction) {
/*      */     PlayerToggleSneakEvent event;
/*      */     PlayerToggleSprintEvent e2;
/* 1973 */     PlayerConnectionUtils.ensureMainThread(packetplayinentityaction, this, this.player.getWorldServer());
/*      */     
/* 1975 */     if (this.player.dead)
/* 1976 */       return;  switch (packetplayinentityaction.c()) {
/*      */       case ALLOW:
/*      */       case DEFAULT:
/* 1979 */         event = new PlayerToggleSneakEvent((Player)getPlayer(), (packetplayinentityaction.c() == PacketPlayInEntityAction.EnumPlayerAction.PRESS_SHIFT_KEY));
/* 1980 */         this.server.getPluginManager().callEvent((Event)event);
/*      */         
/* 1982 */         if (event.isCancelled()) {
/*      */           return;
/*      */         }
/*      */         break;
/*      */       case DENY:
/*      */       case null:
/* 1988 */         e2 = new PlayerToggleSprintEvent((Player)getPlayer(), (packetplayinentityaction.c() == PacketPlayInEntityAction.EnumPlayerAction.START_SPRINTING));
/* 1989 */         this.server.getPluginManager().callEvent((Event)e2);
/*      */         
/* 1991 */         if (e2.isCancelled()) {
/*      */           return;
/*      */         }
/*      */         break;
/*      */     } 
/*      */     
/* 1997 */     this.player.resetIdleTimer();
/*      */ 
/*      */     
/* 2000 */     switch (packetplayinentityaction.c()) {
/*      */       case ALLOW:
/* 2002 */         this.player.setSneaking(true);
/*      */ 
/*      */         
/* 2005 */         if (this.player.world.paperConfig.parrotsHangOnBetter) {
/* 2006 */           this.player.releaseShoulderEntities();
/*      */         }
/*      */         return;
/*      */ 
/*      */       
/*      */       case DEFAULT:
/* 2012 */         this.player.setSneaking(false);
/*      */         return;
/*      */       case DENY:
/* 2015 */         this.player.setSprinting(true);
/*      */         return;
/*      */       case null:
/* 2018 */         this.player.setSprinting(false);
/*      */         return;
/*      */       case null:
/* 2021 */         if (this.player.isSleeping()) {
/* 2022 */           this.player.wakeup(false, true);
/* 2023 */           this.teleportPos = this.player.getPositionVector();
/*      */         } 
/*      */         return;
/*      */       case null:
/* 2027 */         if (this.player.getVehicle() instanceof IJumpable) {
/* 2028 */           IJumpable ijumpable = (IJumpable)this.player.getVehicle();
/* 2029 */           int i = packetplayinentityaction.d();
/*      */           
/* 2031 */           if (ijumpable.P_() && i > 0) {
/* 2032 */             ijumpable.b(i);
/*      */           }
/*      */         } 
/*      */         return;
/*      */       case null:
/* 2037 */         if (this.player.getVehicle() instanceof IJumpable) {
/* 2038 */           IJumpable ijumpable = (IJumpable)this.player.getVehicle();
/* 2039 */           ijumpable.c();
/*      */         } 
/*      */         return;
/*      */       case null:
/* 2043 */         if (this.player.getVehicle() instanceof EntityHorseAbstract) {
/* 2044 */           ((EntityHorseAbstract)this.player.getVehicle()).f(this.player);
/*      */         }
/*      */         return;
/*      */       case null:
/* 2048 */         if (!this.player.eC()) {
/* 2049 */           this.player.stopGliding();
/*      */         }
/*      */         return;
/*      */     } 
/* 2053 */     throw new IllegalArgumentException("Invalid client command!");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(PacketPlayInUseEntity packetplayinuseentity) {
/* 2060 */     PlayerConnectionUtils.ensureMainThread(packetplayinuseentity, this, this.player.getWorldServer());
/* 2061 */     if (this.player.isFrozen())
/* 2062 */       return;  WorldServer worldserver = this.player.getWorldServer();
/* 2063 */     Entity entity = packetplayinuseentity.a(worldserver);
/*      */     
/* 2065 */     if (entity == this.player && !this.player.isSpectator()) {
/*      */       
/* 2067 */       disconnect("Cannot interact with self!");
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 2072 */     this.player.resetIdleTimer();
/* 2073 */     this.player.setSneaking(packetplayinuseentity.e());
/* 2074 */     if (entity != null) {
/* 2075 */       double d0 = 36.0D;
/*      */       
/* 2077 */       if (this.player.h(entity) < 36.0D) {
/* 2078 */         EnumHand enumhand = packetplayinuseentity.c();
/* 2079 */         ItemStack itemstack = (enumhand != null) ? this.player.b(enumhand).cloneItemStack() : ItemStack.b;
/* 2080 */         Optional<EnumInteractionResult> optional = Optional.empty();
/*      */         
/* 2082 */         ItemStack itemInHand = this.player.b((packetplayinuseentity.c() == null) ? EnumHand.MAIN_HAND : packetplayinuseentity.c());
/*      */         
/* 2084 */         if (packetplayinuseentity.b() == PacketPlayInUseEntity.EnumEntityUseAction.INTERACT || packetplayinuseentity
/* 2085 */           .b() == PacketPlayInUseEntity.EnumEntityUseAction.INTERACT_AT) {
/*      */           PlayerInteractAtEntityEvent playerInteractAtEntityEvent;
/* 2087 */           boolean triggerLeashUpdate = (itemInHand != null && itemInHand.getItem() == Items.LEAD && entity instanceof EntityInsentient);
/* 2088 */           Item origItem = (this.player.inventory.getItemInHand() == null) ? null : this.player.inventory.getItemInHand().getItem();
/*      */           
/* 2090 */           if (packetplayinuseentity.b() == PacketPlayInUseEntity.EnumEntityUseAction.INTERACT) {
/* 2091 */             PlayerInteractEntityEvent event = new PlayerInteractEntityEvent((Player)getPlayer(), (Entity)entity.getBukkitEntity(), (packetplayinuseentity.c() == EnumHand.OFF_HAND) ? EquipmentSlot.OFF_HAND : EquipmentSlot.HAND);
/*      */           } else {
/* 2093 */             Vec3D target = packetplayinuseentity.d();
/* 2094 */             playerInteractAtEntityEvent = new PlayerInteractAtEntityEvent((Player)getPlayer(), (Entity)entity.getBukkitEntity(), new Vector(target.x, target.y, target.z), (packetplayinuseentity.c() == EnumHand.OFF_HAND) ? EquipmentSlot.OFF_HAND : EquipmentSlot.HAND);
/*      */           } 
/* 2096 */           this.server.getPluginManager().callEvent((Event)playerInteractAtEntityEvent);
/*      */ 
/*      */           
/* 2099 */           if (entity instanceof EntityFish && origItem != null && origItem.getItem() == Items.WATER_BUCKET && (playerInteractAtEntityEvent.isCancelled() || this.player.inventory.getItemInHand() == null || this.player.inventory.getItemInHand().getItem() != origItem)) {
/* 2100 */             sendPacket(new PacketPlayOutSpawnEntityLiving((EntityFish)entity));
/* 2101 */             this.player.updateInventory(this.player.activeContainer);
/*      */           } 
/*      */           
/* 2104 */           if (triggerLeashUpdate && (playerInteractAtEntityEvent.isCancelled() || this.player.inventory.getItemInHand() == null || this.player.inventory.getItemInHand().getItem() != origItem))
/*      */           {
/* 2106 */             sendPacket(new PacketPlayOutAttachEntity(entity, ((EntityInsentient)entity).getLeashHolder()));
/*      */           }
/*      */           
/* 2109 */           if (playerInteractAtEntityEvent.isCancelled() || this.player.inventory.getItemInHand() == null || this.player.inventory.getItemInHand().getItem() != origItem) {
/*      */ 
/*      */             
/* 2112 */             PacketPlayOutEntityMetadata packet = new PacketPlayOutEntityMetadata(entity.getId(), entity.datawatcher, true);
/* 2113 */             if (entity.tracker != null) {
/* 2114 */               entity.tracker.broadcast(packet);
/*      */             } else {
/* 2116 */               sendPacket(packet);
/*      */             } 
/*      */           } 
/*      */ 
/*      */           
/* 2121 */           if (playerInteractAtEntityEvent.isCancelled()) {
/* 2122 */             this.player.updateInventory(this.player.activeContainer);
/*      */             
/*      */             return;
/*      */           } 
/*      */         } 
/*      */         
/* 2128 */         if (packetplayinuseentity.b() == PacketPlayInUseEntity.EnumEntityUseAction.INTERACT) {
/* 2129 */           optional = Optional.of(this.player.a(entity, enumhand));
/*      */           
/* 2131 */           if (!itemInHand.isEmpty() && itemInHand.getCount() <= -1) {
/* 2132 */             this.player.updateInventory(this.player.activeContainer);
/*      */           }
/*      */         }
/* 2135 */         else if (packetplayinuseentity.b() == PacketPlayInUseEntity.EnumEntityUseAction.INTERACT_AT) {
/* 2136 */           optional = Optional.of(entity.a(this.player, packetplayinuseentity.d(), enumhand));
/*      */           
/* 2138 */           if (!itemInHand.isEmpty() && itemInHand.getCount() <= -1) {
/* 2139 */             this.player.updateInventory(this.player.activeContainer);
/*      */           }
/*      */         }
/* 2142 */         else if (packetplayinuseentity.b() == PacketPlayInUseEntity.EnumEntityUseAction.ATTACK) {
/* 2143 */           if (entity instanceof EntityItem || entity instanceof EntityExperienceOrb || entity instanceof EntityArrow || (entity == this.player && !this.player.isSpectator())) {
/* 2144 */             disconnect(new ChatMessage("multiplayer.disconnect.invalid_entity_attacked"));
/* 2145 */             LOGGER.warn("Player {} tried to attack an invalid entity", this.player.getDisplayName().getString());
/*      */             
/*      */             return;
/*      */           } 
/* 2149 */           this.player.attack(entity);
/*      */ 
/*      */           
/* 2152 */           if (!itemInHand.isEmpty() && itemInHand.getCount() <= -1) {
/* 2153 */             this.player.updateInventory(this.player.activeContainer);
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 2158 */         if (optional.isPresent() && ((EnumInteractionResult)optional.get()).a()) {
/* 2159 */           CriterionTriggers.P.a(this.player, itemstack, entity);
/* 2160 */           if (((EnumInteractionResult)optional.get()).b()) {
/* 2161 */             this.player.swingHand(enumhand, true);
/*      */           }
/*      */         }
/*      */       
/*      */       } 
/*      */     } else {
/*      */       
/* 2168 */       this.server.getPluginManager().callEvent((Event)new PlayerUseUnknownEntityEvent((Player)
/* 2169 */             getPlayer(), packetplayinuseentity
/* 2170 */             .getEntityId(), 
/* 2171 */             (packetplayinuseentity.b() == PacketPlayInUseEntity.EnumEntityUseAction.ATTACK), 
/* 2172 */             (packetplayinuseentity.c() == EnumHand.MAIN_HAND) ? EquipmentSlot.HAND : EquipmentSlot.OFF_HAND));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(PacketPlayInClientCommand packetplayinclientcommand) {
/* 2181 */     PlayerConnectionUtils.ensureMainThread(packetplayinclientcommand, this, this.player.getWorldServer());
/* 2182 */     this.player.resetIdleTimer();
/* 2183 */     PacketPlayInClientCommand.EnumClientCommand packetplayinclientcommand_enumclientcommand = packetplayinclientcommand.b();
/*      */     
/* 2185 */     switch (packetplayinclientcommand_enumclientcommand) {
/*      */       case ALLOW:
/* 2187 */         if (this.player.viewingCredits) {
/* 2188 */           this.player.viewingCredits = false;
/* 2189 */           this.player = this.minecraftServer.getPlayerList().moveToWorld(this.player, true);
/* 2190 */           CriterionTriggers.v.a(this.player, World.THE_END, World.OVERWORLD); break;
/*      */         } 
/* 2192 */         if (this.player.getHealth() > 0.0F) {
/*      */           return;
/*      */         }
/*      */         
/* 2196 */         this.player = this.minecraftServer.getPlayerList().moveToWorld(this.player, false);
/* 2197 */         if (this.minecraftServer.isHardcore()) {
/* 2198 */           this.player.a(EnumGamemode.SPECTATOR);
/* 2199 */           ((GameRules.GameRuleBoolean)this.player.getWorldServer().getGameRules().<GameRules.GameRuleBoolean>get(GameRules.SPECTATORS_GENERATE_CHUNKS)).a(false, this.minecraftServer);
/*      */         } 
/*      */         break;
/*      */       
/*      */       case DEFAULT:
/* 2204 */         this.player.getStatisticManager().a(this.player);
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(PacketPlayInCloseWindow packetplayinclosewindow) {
/* 2212 */     handleContainerClose(packetplayinclosewindow, InventoryCloseEvent.Reason.PLAYER);
/*      */   }
/*      */   
/*      */   public void handleContainerClose(PacketPlayInCloseWindow packetplayinclosewindow, InventoryCloseEvent.Reason reason) {
/* 2216 */     PlayerConnectionUtils.ensureMainThread(packetplayinclosewindow, this, this.player.getWorldServer());
/*      */     
/* 2218 */     if (this.player.isFrozen())
/* 2219 */       return;  CraftEventFactory.handleInventoryCloseEvent(this.player, reason);
/*      */     
/* 2221 */     this.player.o();
/*      */   }
/*      */ 
/*      */   
/*      */   public void a(PacketPlayInWindowClick packetplayinwindowclick) {
/* 2226 */     PlayerConnectionUtils.ensureMainThread(packetplayinwindowclick, this, this.player.getWorldServer());
/* 2227 */     if (this.player.isFrozen())
/* 2228 */       return;  this.player.resetIdleTimer();
/* 2229 */     if (this.player.activeContainer.windowId == packetplayinwindowclick.b() && this.player.activeContainer.c(this.player) && this.player.activeContainer.canUse(this.player)) {
/* 2230 */       boolean cancelled = this.player.isSpectator();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2241 */       if (packetplayinwindowclick.c() < -1 && packetplayinwindowclick.c() != -999) {
/*      */         return;
/*      */       }
/*      */       
/* 2245 */       InventoryView inventory = this.player.activeContainer.getBukkitView();
/* 2246 */       InventoryType.SlotType type = inventory.getSlotType(packetplayinwindowclick.c());
/*      */ 
/*      */       
/* 2249 */       ClickType click = ClickType.UNKNOWN;
/* 2250 */       InventoryAction action = InventoryAction.UNKNOWN;
/*      */       
/* 2252 */       ItemStack itemstack = ItemStack.b;
/*      */       
/* 2254 */       switch (packetplayinwindowclick.g()) {
/*      */         case ALLOW:
/* 2256 */           if (packetplayinwindowclick.d() == 0) {
/* 2257 */             click = ClickType.LEFT;
/* 2258 */           } else if (packetplayinwindowclick.d() == 1) {
/* 2259 */             click = ClickType.RIGHT;
/*      */           } 
/* 2261 */           if (packetplayinwindowclick.d() == 0 || packetplayinwindowclick.d() == 1) {
/* 2262 */             action = InventoryAction.NOTHING;
/* 2263 */             if (packetplayinwindowclick.c() == -999) {
/* 2264 */               if (!this.player.inventory.getCarried().isEmpty())
/* 2265 */                 action = (packetplayinwindowclick.d() == 0) ? InventoryAction.DROP_ALL_CURSOR : InventoryAction.DROP_ONE_CURSOR;  break;
/*      */             } 
/* 2267 */             if (packetplayinwindowclick.c() < 0) {
/* 2268 */               action = InventoryAction.NOTHING; break;
/*      */             } 
/* 2270 */             Slot slot = this.player.activeContainer.getSlot(packetplayinwindowclick.c());
/* 2271 */             if (slot != null) {
/* 2272 */               ItemStack clickedItem = slot.getItem();
/* 2273 */               ItemStack cursor = this.player.inventory.getCarried();
/* 2274 */               if (clickedItem.isEmpty()) {
/* 2275 */                 if (!cursor.isEmpty())
/* 2276 */                   action = (packetplayinwindowclick.d() == 0) ? InventoryAction.PLACE_ALL : InventoryAction.PLACE_ONE;  break;
/*      */               } 
/* 2278 */               if (slot.isAllowed(this.player)) {
/* 2279 */                 if (cursor.isEmpty()) {
/* 2280 */                   action = (packetplayinwindowclick.d() == 0) ? InventoryAction.PICKUP_ALL : InventoryAction.PICKUP_HALF; break;
/* 2281 */                 }  if (slot.isAllowed(cursor)) {
/* 2282 */                   if (clickedItem.doMaterialsMatch(cursor) && ItemStack.equals(clickedItem, cursor)) {
/* 2283 */                     int toPlace = (packetplayinwindowclick.d() == 0) ? cursor.getCount() : 1;
/* 2284 */                     toPlace = Math.min(toPlace, clickedItem.getMaxStackSize() - clickedItem.getCount());
/* 2285 */                     toPlace = Math.min(toPlace, slot.inventory.getMaxStackSize() - clickedItem.getCount());
/* 2286 */                     if (toPlace == 1) {
/* 2287 */                       action = InventoryAction.PLACE_ONE; break;
/* 2288 */                     }  if (toPlace == cursor.getCount()) {
/* 2289 */                       action = InventoryAction.PLACE_ALL; break;
/* 2290 */                     }  if (toPlace < 0) {
/* 2291 */                       action = (toPlace != -1) ? InventoryAction.PICKUP_SOME : InventoryAction.PICKUP_ONE; break;
/* 2292 */                     }  if (toPlace != 0)
/* 2293 */                       action = InventoryAction.PLACE_SOME;  break;
/*      */                   } 
/* 2295 */                   if (cursor.getCount() <= slot.getMaxStackSize())
/* 2296 */                     action = InventoryAction.SWAP_WITH_CURSOR;  break;
/*      */                 } 
/* 2298 */                 if (cursor.getItem() == clickedItem.getItem() && ItemStack.equals(cursor, clickedItem) && 
/* 2299 */                   clickedItem.getCount() >= 0 && 
/* 2300 */                   clickedItem.getCount() + cursor.getCount() <= cursor.getMaxStackSize())
/*      */                 {
/* 2302 */                   action = InventoryAction.PICKUP_ALL;
/*      */                 }
/*      */               } 
/*      */             } 
/*      */           } 
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case DEFAULT:
/* 2313 */           if (packetplayinwindowclick.d() == 0) {
/* 2314 */             click = ClickType.SHIFT_LEFT;
/* 2315 */           } else if (packetplayinwindowclick.d() == 1) {
/* 2316 */             click = ClickType.SHIFT_RIGHT;
/*      */           } 
/* 2318 */           if (packetplayinwindowclick.d() == 0 || packetplayinwindowclick.d() == 1) {
/* 2319 */             if (packetplayinwindowclick.c() < 0) {
/* 2320 */               action = InventoryAction.NOTHING; break;
/*      */             } 
/* 2322 */             Slot slot = this.player.activeContainer.getSlot(packetplayinwindowclick.c());
/* 2323 */             if (slot != null && slot.isAllowed(this.player) && slot.hasItem()) {
/* 2324 */               action = InventoryAction.MOVE_TO_OTHER_INVENTORY; break;
/*      */             } 
/* 2326 */             action = InventoryAction.NOTHING;
/*      */           } 
/*      */           break;
/*      */ 
/*      */         
/*      */         case DENY:
/* 2332 */           if ((packetplayinwindowclick.d() >= 0 && packetplayinwindowclick.d() < 9) || packetplayinwindowclick.d() == 40) {
/* 2333 */             click = (packetplayinwindowclick.d() == 40) ? ClickType.SWAP_OFFHAND : ClickType.NUMBER_KEY;
/* 2334 */             Slot clickedSlot = this.player.activeContainer.getSlot(packetplayinwindowclick.c());
/* 2335 */             if (clickedSlot.isAllowed(this.player)) {
/* 2336 */               ItemStack hotbar = this.player.inventory.getItem(packetplayinwindowclick.d());
/* 2337 */               boolean canCleanSwap = (hotbar.isEmpty() || (clickedSlot.inventory == this.player.inventory && clickedSlot.isAllowed(hotbar)));
/* 2338 */               if (clickedSlot.hasItem()) {
/* 2339 */                 if (canCleanSwap) {
/* 2340 */                   action = InventoryAction.HOTBAR_SWAP; break;
/*      */                 } 
/* 2342 */                 action = InventoryAction.HOTBAR_MOVE_AND_READD; break;
/*      */               } 
/* 2344 */               if (!clickedSlot.hasItem() && !hotbar.isEmpty() && clickedSlot.isAllowed(hotbar)) {
/* 2345 */                 action = InventoryAction.HOTBAR_SWAP; break;
/*      */               } 
/* 2347 */               action = InventoryAction.NOTHING;
/*      */               break;
/*      */             } 
/* 2350 */             action = InventoryAction.NOTHING;
/*      */           } 
/*      */           break;
/*      */         
/*      */         case null:
/* 2355 */           if (packetplayinwindowclick.d() == 2) {
/* 2356 */             click = ClickType.MIDDLE;
/* 2357 */             if (packetplayinwindowclick.c() < 0) {
/* 2358 */               action = InventoryAction.NOTHING; break;
/*      */             } 
/* 2360 */             Slot slot = this.player.activeContainer.getSlot(packetplayinwindowclick.c());
/* 2361 */             if (slot != null && slot.hasItem() && this.player.abilities.canInstantlyBuild && this.player.inventory.getCarried().isEmpty()) {
/* 2362 */               action = InventoryAction.CLONE_STACK; break;
/*      */             } 
/* 2364 */             action = InventoryAction.NOTHING;
/*      */             
/*      */             break;
/*      */           } 
/* 2368 */           click = ClickType.UNKNOWN;
/* 2369 */           action = InventoryAction.UNKNOWN;
/*      */           break;
/*      */         
/*      */         case null:
/* 2373 */           if (packetplayinwindowclick.c() >= 0) {
/* 2374 */             if (packetplayinwindowclick.d() == 0) {
/* 2375 */               click = ClickType.DROP;
/* 2376 */               Slot slot = this.player.activeContainer.getSlot(packetplayinwindowclick.c());
/* 2377 */               if (slot != null && slot.hasItem() && slot.isAllowed(this.player) && !slot.getItem().isEmpty() && slot.getItem().getItem() != Item.getItemOf(Blocks.AIR)) {
/* 2378 */                 action = InventoryAction.DROP_ONE_SLOT; break;
/*      */               } 
/* 2380 */               action = InventoryAction.NOTHING; break;
/*      */             } 
/* 2382 */             if (packetplayinwindowclick.d() == 1) {
/* 2383 */               click = ClickType.CONTROL_DROP;
/* 2384 */               Slot slot = this.player.activeContainer.getSlot(packetplayinwindowclick.c());
/* 2385 */               if (slot != null && slot.hasItem() && slot.isAllowed(this.player) && !slot.getItem().isEmpty() && slot.getItem().getItem() != Item.getItemOf(Blocks.AIR)) {
/* 2386 */                 action = InventoryAction.DROP_ALL_SLOT; break;
/*      */               } 
/* 2388 */               action = InventoryAction.NOTHING;
/*      */             } 
/*      */             
/*      */             break;
/*      */           } 
/* 2393 */           click = ClickType.LEFT;
/* 2394 */           if (packetplayinwindowclick.d() == 1) {
/* 2395 */             click = ClickType.RIGHT;
/*      */           }
/* 2397 */           action = InventoryAction.NOTHING;
/*      */           break;
/*      */         
/*      */         case null:
/* 2401 */           itemstack = this.player.activeContainer.a(packetplayinwindowclick.c(), packetplayinwindowclick.d(), packetplayinwindowclick.g(), this.player);
/*      */           break;
/*      */         case null:
/* 2404 */           click = ClickType.DOUBLE_CLICK;
/* 2405 */           action = InventoryAction.NOTHING;
/* 2406 */           if (packetplayinwindowclick.c() >= 0 && !this.player.inventory.getCarried().isEmpty()) {
/* 2407 */             ItemStack cursor = this.player.inventory.getCarried();
/* 2408 */             action = InventoryAction.NOTHING;
/*      */             
/* 2410 */             if (inventory.getTopInventory().contains(CraftMagicNumbers.getMaterial(cursor.getItem())) || inventory.getBottomInventory().contains(CraftMagicNumbers.getMaterial(cursor.getItem()))) {
/* 2411 */               action = InventoryAction.COLLECT_TO_CURSOR;
/*      */             }
/*      */           } 
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2419 */       if (packetplayinwindowclick.g() != InventoryClickType.QUICK_CRAFT) {
/* 2420 */         CraftItemEvent craftItemEvent; if (click == ClickType.NUMBER_KEY) {
/* 2421 */           InventoryClickEvent event = new InventoryClickEvent(inventory, type, packetplayinwindowclick.c(), click, action, packetplayinwindowclick.d());
/*      */         } else {
/* 2423 */           InventoryClickEvent event = new InventoryClickEvent(inventory, type, packetplayinwindowclick.c(), click, action);
/*      */         } 
/*      */         
/* 2426 */         Inventory top = inventory.getTopInventory();
/* 2427 */         if (packetplayinwindowclick.c() == 0 && top instanceof CraftingInventory) {
/* 2428 */           Recipe recipe = ((CraftingInventory)top).getRecipe();
/* 2429 */           if (recipe != null) {
/* 2430 */             if (click == ClickType.NUMBER_KEY) {
/* 2431 */               craftItemEvent = new CraftItemEvent(recipe, inventory, type, packetplayinwindowclick.c(), click, action, packetplayinwindowclick.d());
/*      */             } else {
/* 2433 */               craftItemEvent = new CraftItemEvent(recipe, inventory, type, packetplayinwindowclick.c(), click, action);
/*      */             } 
/*      */           }
/*      */         } 
/*      */         
/* 2438 */         craftItemEvent.setCancelled(cancelled);
/* 2439 */         Container oldContainer = this.player.activeContainer;
/* 2440 */         this.server.getPluginManager().callEvent((Event)craftItemEvent);
/* 2441 */         if (this.player.activeContainer != oldContainer) {
/*      */           return;
/*      */         }
/*      */         
/* 2445 */         switch (craftItemEvent.getResult()) {
/*      */           case ALLOW:
/*      */           case DEFAULT:
/* 2448 */             itemstack = this.player.activeContainer.a(packetplayinwindowclick.c(), packetplayinwindowclick.d(), packetplayinwindowclick.g(), this.player);
/*      */             break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case DENY:
/* 2462 */             switch (action) {
/*      */               
/*      */               case ALLOW:
/*      */               case DEFAULT:
/*      */               case DENY:
/*      */               case null:
/*      */               case null:
/*      */               case null:
/* 2470 */                 this.player.updateInventory(this.player.activeContainer);
/*      */                 break;
/*      */               
/*      */               case null:
/*      */               case null:
/*      */               case null:
/*      */               case null:
/*      */               case null:
/*      */               case null:
/*      */               case null:
/* 2480 */                 this.player.playerConnection.sendPacket(new PacketPlayOutSetSlot(-1, -1, this.player.inventory.getCarried()));
/* 2481 */                 this.player.playerConnection.sendPacket(new PacketPlayOutSetSlot(this.player.activeContainer.windowId, packetplayinwindowclick.c(), this.player.activeContainer.getSlot(packetplayinwindowclick.c()).getItem()));
/*      */                 break;
/*      */               
/*      */               case null:
/*      */               case null:
/* 2486 */                 this.player.playerConnection.sendPacket(new PacketPlayOutSetSlot(this.player.activeContainer.windowId, packetplayinwindowclick.c(), this.player.activeContainer.getSlot(packetplayinwindowclick.c()).getItem()));
/*      */                 break;
/*      */               
/*      */               case null:
/*      */               case null:
/*      */               case null:
/* 2492 */                 this.player.playerConnection.sendPacket(new PacketPlayOutSetSlot(-1, -1, this.player.inventory.getCarried()));
/*      */                 break;
/*      */             } 
/*      */ 
/*      */             
/*      */             return;
/*      */         } 
/*      */ 
/*      */         
/* 2501 */         if (craftItemEvent instanceof CraftItemEvent)
/*      */         {
/*      */           
/* 2504 */           this.player.updateInventory(this.player.activeContainer);
/*      */         }
/*      */       } 
/*      */       
/* 2508 */       if (ItemStack.matches(packetplayinwindowclick.f(), itemstack)) {
/* 2509 */         this.player.playerConnection.sendPacket(new PacketPlayOutTransaction(packetplayinwindowclick.b(), packetplayinwindowclick.e(), true));
/* 2510 */         this.player.e = true;
/* 2511 */         this.player.activeContainer.c();
/* 2512 */         this.player.broadcastCarriedItem();
/* 2513 */         this.player.e = false;
/*      */       } else {
/* 2515 */         this.k.put(this.player.activeContainer.windowId, packetplayinwindowclick.e());
/* 2516 */         this.player.playerConnection.sendPacket(new PacketPlayOutTransaction(packetplayinwindowclick.b(), packetplayinwindowclick.e(), false));
/* 2517 */         this.player.activeContainer.a(this.player, false);
/* 2518 */         NonNullList<ItemStack> nonnulllist1 = NonNullList.a();
/*      */         
/* 2520 */         for (int j = 0; j < this.player.activeContainer.slots.size(); j++) {
/* 2521 */           ItemStack itemstack1 = ((Slot)this.player.activeContainer.slots.get(j)).getItem();
/*      */           
/* 2523 */           nonnulllist1.add(itemstack1.isEmpty() ? ItemStack.b : itemstack1);
/*      */         } 
/*      */         
/* 2526 */         this.player.a(this.player.activeContainer, nonnulllist1);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(PacketPlayInAutoRecipe packetplayinautorecipe) {
/* 2535 */     PlayerConnectionUtils.ensureMainThread(packetplayinautorecipe, this, this.player.getWorldServer());
/* 2536 */     this.player.resetIdleTimer();
/* 2537 */     if (!this.player.isSpectator() && this.player.activeContainer.windowId == packetplayinautorecipe.b() && this.player.activeContainer.c(this.player) && this.player.activeContainer instanceof ContainerRecipeBook) {
/*      */ 
/*      */       
/* 2540 */       PlayerRecipeBookClickEvent event = new PlayerRecipeBookClickEvent((Player)this.player.getBukkitEntity(), CraftNamespacedKey.fromMinecraft(packetplayinautorecipe.c()), packetplayinautorecipe.d());
/* 2541 */       if (event.callEvent()) {
/* 2542 */         this.minecraftServer.getCraftingManager().getRecipe(CraftNamespacedKey.toMinecraft(event.getRecipe())).ifPresent(irecipe -> ((ContainerRecipeBook)this.player.activeContainer).a(event.isMakeAll(), irecipe, this.player));
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(PacketPlayInEnchantItem packetplayinenchantitem) {
/* 2552 */     PlayerConnectionUtils.ensureMainThread(packetplayinenchantitem, this, this.player.getWorldServer());
/* 2553 */     if (this.player.isFrozen())
/* 2554 */       return;  this.player.resetIdleTimer();
/* 2555 */     if (this.player.activeContainer.windowId == packetplayinenchantitem.b() && this.player.activeContainer.c(this.player) && !this.player.isSpectator()) {
/* 2556 */       this.player.activeContainer.a(this.player, packetplayinenchantitem.c());
/* 2557 */       this.player.activeContainer.c();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(PacketPlayInSetCreativeSlot packetplayinsetcreativeslot) {
/* 2564 */     PlayerConnectionUtils.ensureMainThread(packetplayinsetcreativeslot, this, this.player.getWorldServer());
/* 2565 */     if (this.player.playerInteractManager.isCreative()) {
/* 2566 */       boolean flag = (packetplayinsetcreativeslot.b() < 0);
/* 2567 */       ItemStack itemstack = packetplayinsetcreativeslot.getItemStack();
/* 2568 */       NBTTagCompound nbttagcompound = itemstack.b("BlockEntityTag");
/*      */       
/* 2570 */       if (!itemstack.isEmpty() && nbttagcompound != null && nbttagcompound.hasKey("x") && nbttagcompound.hasKey("y") && nbttagcompound.hasKey("z") && this.player.getBukkitEntity().hasPermission("minecraft.nbt.copy")) {
/* 2571 */         BlockPosition blockposition = new BlockPosition(nbttagcompound.getInt("x"), nbttagcompound.getInt("y"), nbttagcompound.getInt("z"));
/* 2572 */         TileEntity tileentity = this.player.world.getTileEntity(blockposition);
/*      */         
/* 2574 */         if (tileentity != null) {
/* 2575 */           NBTTagCompound nbttagcompound1 = tileentity.save(new NBTTagCompound());
/*      */           
/* 2577 */           nbttagcompound1.remove("x");
/* 2578 */           nbttagcompound1.remove("y");
/* 2579 */           nbttagcompound1.remove("z");
/* 2580 */           itemstack.a("BlockEntityTag", nbttagcompound1);
/*      */         } 
/*      */       } 
/*      */       
/* 2584 */       boolean flag1 = (packetplayinsetcreativeslot.b() >= 1 && packetplayinsetcreativeslot.b() <= 45);
/* 2585 */       boolean flag2 = (itemstack.isEmpty() || (itemstack.getDamage() >= 0 && itemstack.getCount() <= 64 && !itemstack.isEmpty()));
/* 2586 */       if (flag || (flag1 && !ItemStack.matches(this.player.defaultContainer.getSlot(packetplayinsetcreativeslot.b()).getItem(), packetplayinsetcreativeslot.getItemStack()))) {
/*      */         
/* 2588 */         CraftInventoryView craftInventoryView = this.player.defaultContainer.getBukkitView();
/* 2589 */         ItemStack item = CraftItemStack.asBukkitCopy(packetplayinsetcreativeslot.getItemStack());
/*      */         
/* 2591 */         InventoryType.SlotType type = InventoryType.SlotType.QUICKBAR;
/* 2592 */         if (flag) {
/* 2593 */           type = InventoryType.SlotType.OUTSIDE;
/* 2594 */         } else if (packetplayinsetcreativeslot.b() < 36) {
/* 2595 */           if (packetplayinsetcreativeslot.b() >= 5 && packetplayinsetcreativeslot.b() < 9) {
/* 2596 */             type = InventoryType.SlotType.ARMOR;
/*      */           } else {
/* 2598 */             type = InventoryType.SlotType.CONTAINER;
/*      */           } 
/*      */         } 
/* 2601 */         InventoryCreativeEvent event = new InventoryCreativeEvent((InventoryView)craftInventoryView, type, flag ? -999 : packetplayinsetcreativeslot.b(), item);
/* 2602 */         this.server.getPluginManager().callEvent((Event)event);
/*      */         
/* 2604 */         itemstack = CraftItemStack.asNMSCopy(event.getCursor());
/*      */         
/* 2606 */         switch (event.getResult()) {
/*      */           
/*      */           case ALLOW:
/* 2609 */             flag2 = true;
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           case DENY:
/* 2615 */             if (packetplayinsetcreativeslot.b() >= 0) {
/* 2616 */               this.player.playerConnection.sendPacket(new PacketPlayOutSetSlot(this.player.defaultContainer.windowId, packetplayinsetcreativeslot.b(), this.player.defaultContainer.getSlot(packetplayinsetcreativeslot.b()).getItem()));
/* 2617 */               this.player.playerConnection.sendPacket(new PacketPlayOutSetSlot(-1, -1, ItemStack.b));
/*      */             } 
/*      */             return;
/*      */         } 
/*      */ 
/*      */       
/*      */       } 
/* 2624 */       if (flag1 && flag2) {
/* 2625 */         if (itemstack.isEmpty()) {
/* 2626 */           this.player.defaultContainer.setItem(packetplayinsetcreativeslot.b(), ItemStack.b);
/*      */         } else {
/* 2628 */           this.player.defaultContainer.setItem(packetplayinsetcreativeslot.b(), itemstack);
/*      */         } 
/*      */         
/* 2631 */         this.player.defaultContainer.a(this.player, true);
/* 2632 */         this.player.defaultContainer.c();
/* 2633 */       } else if (flag && flag2 && this.j < 200) {
/* 2634 */         this.j += 20;
/* 2635 */         this.player.drop(itemstack, true);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(PacketPlayInTransaction packetplayintransaction) {
/* 2643 */     PlayerConnectionUtils.ensureMainThread(packetplayintransaction, this, this.player.getWorldServer());
/* 2644 */     if (this.player.isFrozen())
/* 2645 */       return;  int i = this.player.activeContainer.windowId;
/*      */     
/* 2647 */     if (i == packetplayintransaction.b() && this.k.getOrDefault(i, (short)(packetplayintransaction.c() + 1)) == packetplayintransaction.c() && !this.player.activeContainer.c(this.player) && !this.player.isSpectator()) {
/* 2648 */       this.player.activeContainer.a(this.player, true);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(PacketPlayInUpdateSign packetplayinupdatesign) {
/* 2655 */     PlayerConnectionUtils.ensureMainThread(packetplayinupdatesign, this, this.player.getWorldServer());
/* 2656 */     if (this.player.isFrozen())
/* 2657 */       return;  this.player.resetIdleTimer();
/* 2658 */     WorldServer worldserver = this.player.getWorldServer();
/* 2659 */     BlockPosition blockposition = packetplayinupdatesign.b();
/*      */     
/* 2661 */     if (worldserver.isLoaded(blockposition)) {
/* 2662 */       IBlockData iblockdata = worldserver.getType(blockposition);
/* 2663 */       TileEntity tileentity = worldserver.getTileEntity(blockposition);
/*      */       
/* 2665 */       if (!(tileentity instanceof TileEntitySign)) {
/*      */         return;
/*      */       }
/*      */       
/* 2669 */       TileEntitySign tileentitysign = (TileEntitySign)tileentity;
/*      */       
/* 2671 */       if (!tileentitysign.d() || tileentitysign.signEditor == null || !tileentitysign.signEditor.equals(this.player.getUniqueID())) {
/* 2672 */         LOGGER.warn("Player {} just tried to change non-editable sign", this.player.getDisplayName().getString());
/* 2673 */         sendPacket(tileentity.getUpdatePacket());
/*      */         
/*      */         return;
/*      */       } 
/* 2677 */       String[] astring = packetplayinupdatesign.c();
/*      */ 
/*      */       
/* 2680 */       Player player = this.server.getPlayer(this.player);
/* 2681 */       int x = packetplayinupdatesign.b().getX();
/* 2682 */       int y = packetplayinupdatesign.b().getY();
/* 2683 */       int z = packetplayinupdatesign.b().getZ();
/* 2684 */       String[] lines = new String[4];
/*      */       
/* 2686 */       for (int i = 0; i < astring.length; i++) {
/*      */         
/* 2688 */         if (MAX_SIGN_LINE_LENGTH > 0 && astring[i].length() > MAX_SIGN_LINE_LENGTH) {
/*      */           
/* 2690 */           int offset = astring[i].codePoints().limit(MAX_SIGN_LINE_LENGTH).map(Character::charCount).sum();
/* 2691 */           if (offset < astring[i].length()) {
/* 2692 */             astring[i] = astring[i].substring(0, offset);
/*      */           }
/*      */         } 
/*      */         
/* 2696 */         lines[i] = SharedConstants.a(astring[i]);
/*      */       } 
/* 2698 */       SignChangeEvent event = new SignChangeEvent(player.getWorld().getBlockAt(x, y, z), this.server.getPlayer(this.player), lines);
/* 2699 */       this.server.getPluginManager().callEvent((Event)event);
/*      */       
/* 2701 */       if (!event.isCancelled()) {
/* 2702 */         System.arraycopy(CraftSign.sanitizeLines(event.getLines()), 0, tileentitysign.lines, 0, 4);
/* 2703 */         tileentitysign.isEditable = false;
/*      */       } 
/*      */ 
/*      */       
/* 2707 */       tileentitysign.update();
/* 2708 */       worldserver.notify(blockposition, iblockdata, iblockdata, 3);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(PacketPlayInKeepAlive packetplayinkeepalive) {
/* 2716 */     if (this.awaitingKeepAlive && packetplayinkeepalive.b() == this.h) {
/* 2717 */       int i = (int)(SystemUtils.getMonotonicMillis() - this.lastKeepAlive);
/*      */       
/* 2719 */       this.player.ping = (this.player.ping * 3 + i) / 4;
/* 2720 */       this.awaitingKeepAlive = false;
/* 2721 */     } else if (!isExemptPlayer()) {
/*      */       
/* 2723 */       this.minecraftServer.scheduleOnMain(() -> disconnect(new ChatMessage("disconnect.timeout")));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(PacketPlayInAbilities packetplayinabilities) {
/* 2733 */     PlayerConnectionUtils.ensureMainThread(packetplayinabilities, this, this.player.getWorldServer());
/*      */     
/* 2735 */     if (this.player.abilities.canFly && this.player.abilities.isFlying != packetplayinabilities.isFlying()) {
/* 2736 */       PlayerToggleFlightEvent event = new PlayerToggleFlightEvent(this.server.getPlayer(this.player), packetplayinabilities.isFlying());
/* 2737 */       this.server.getPluginManager().callEvent((Event)event);
/* 2738 */       if (!event.isCancelled()) {
/* 2739 */         this.player.abilities.isFlying = packetplayinabilities.isFlying();
/*      */       } else {
/* 2741 */         this.player.updateAbilities();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(PacketPlayInSettings packetplayinsettings) {
/* 2749 */     PlayerConnectionUtils.ensureMainThread(packetplayinsettings, this, this.player.getWorldServer());
/* 2750 */     this.player.a(packetplayinsettings);
/*      */   }
/*      */ 
/*      */   
/* 2754 */   private static final MinecraftKey CUSTOM_REGISTER = new MinecraftKey("register");
/* 2755 */   private static final MinecraftKey CUSTOM_UNREGISTER = new MinecraftKey("unregister");
/*      */   
/* 2757 */   private static final MinecraftKey MINECRAFT_BRAND = new MinecraftKey("brand");
/*      */ 
/*      */   
/*      */   public void a(PacketPlayInCustomPayload packetplayincustompayload) {
/* 2761 */     PlayerConnectionUtils.ensureMainThread(packetplayincustompayload, this, this.player.getWorldServer());
/* 2762 */     if (packetplayincustompayload.tag.equals(CUSTOM_REGISTER)) {
/*      */       try {
/* 2764 */         String channels = packetplayincustompayload.data.toString(Charsets.UTF_8);
/* 2765 */         for (String channel : channels.split("\000")) {
/* 2766 */           getPlayer().addChannel(channel);
/*      */         }
/* 2768 */       } catch (Exception ex) {
/* 2769 */         LOGGER.error("Couldn't register custom payload", ex);
/* 2770 */         disconnect("Invalid payload REGISTER!");
/*      */       } 
/* 2772 */     } else if (packetplayincustompayload.tag.equals(CUSTOM_UNREGISTER)) {
/*      */       try {
/* 2774 */         String channels = packetplayincustompayload.data.toString(Charsets.UTF_8);
/* 2775 */         for (String channel : channels.split("\000")) {
/* 2776 */           getPlayer().removeChannel(channel);
/*      */         }
/* 2778 */       } catch (Exception ex) {
/* 2779 */         LOGGER.error("Couldn't unregister custom payload", ex);
/* 2780 */         disconnect("Invalid payload UNREGISTER!");
/*      */       } 
/*      */     } else {
/*      */       try {
/* 2784 */         byte[] data = new byte[packetplayincustompayload.data.readableBytes()];
/* 2785 */         packetplayincustompayload.data.readBytes(data);
/*      */ 
/*      */         
/* 2788 */         if (packetplayincustompayload.tag.equals(MINECRAFT_BRAND)) {
/*      */           try {
/* 2790 */             this.clientBrandName = (new PacketDataSerializer(Unpooled.copiedBuffer(data))).readUTF(256);
/* 2791 */           } catch (StringIndexOutOfBoundsException ex) {
/* 2792 */             this.clientBrandName = "illegal";
/*      */           } 
/*      */         }
/*      */         
/* 2796 */         this.server.getMessenger().dispatchIncomingMessage((Player)this.player.getBukkitEntity(), packetplayincustompayload.tag.toString(), data);
/* 2797 */       } catch (Exception ex) {
/* 2798 */         LOGGER.error("Couldn't dispatch custom payload", ex);
/* 2799 */         disconnect("Invalid custom payload!");
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getClientBrandName() {
/* 2807 */     return this.clientBrandName;
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean isDisconnected() {
/* 2812 */     return ((!this.player.joining && !this.networkManager.isConnected()) || this.processedDisconnect);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(PacketPlayInDifficultyChange packetplayindifficultychange) {
/* 2818 */     PlayerConnectionUtils.ensureMainThread(packetplayindifficultychange, this, this.player.getWorldServer());
/* 2819 */     if (this.player.k(2) || isExemptPlayer());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(PacketPlayInDifficultyLock packetplayindifficultylock) {
/* 2826 */     PlayerConnectionUtils.ensureMainThread(packetplayindifficultylock, this, this.player.getWorldServer());
/* 2827 */     if (this.player.k(2) || isExemptPlayer())
/* 2828 */       this.minecraftServer.b(packetplayindifficultylock.b()); 
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PlayerConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */