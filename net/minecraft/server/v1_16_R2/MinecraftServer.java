/*      */ package net.minecraft.server.v1_16_R2;
/*      */ import co.aikar.timings.MinecraftTimings;
/*      */ import co.aikar.timings.Timing;
/*      */ import co.aikar.timings.TimingsManager;
/*      */ import com.destroystokyo.paper.PaperConfig;
/*      */ import com.destroystokyo.paper.event.server.ServerTickEndEvent;
/*      */ import com.google.common.collect.ImmutableList;
/*      */ import com.google.common.collect.ImmutableSet;
/*      */ import com.google.common.collect.Lists;
/*      */ import com.google.common.io.Files;
/*      */ import com.mojang.authlib.GameProfile;
/*      */ import com.mojang.authlib.GameProfileRepository;
/*      */ import com.mojang.authlib.minecraft.MinecraftSessionService;
/*      */ import com.mojang.datafixers.DataFixer;
/*      */ import com.tuinity.tuinity.util.CachedLists;
/*      */ import io.netty.buffer.ByteBuf;
/*      */ import it.unimi.dsi.fastutil.longs.LongIterator;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.io.BufferedWriter;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.lang.management.ManagementFactory;
/*      */ import java.lang.management.ThreadInfo;
/*      */ import java.lang.management.ThreadMXBean;
/*      */ import java.math.BigDecimal;
/*      */ import java.math.RoundingMode;
/*      */ import java.net.Proxy;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.charset.StandardCharsets;
/*      */ import java.nio.file.Files;
/*      */ import java.nio.file.Path;
/*      */ import java.nio.file.attribute.FileAttribute;
/*      */ import java.security.KeyPair;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.Date;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Objects;
/*      */ import java.util.Queue;
/*      */ import java.util.Random;
/*      */ import java.util.Set;
/*      */ import java.util.concurrent.CompletableFuture;
/*      */ import java.util.concurrent.CompletionStage;
/*      */ import java.util.concurrent.ConcurrentLinkedQueue;
/*      */ import java.util.concurrent.atomic.AtomicReference;
/*      */ import java.util.function.BooleanSupplier;
/*      */ import java.util.function.Function;
/*      */ import java.util.function.Supplier;
/*      */ import java.util.stream.Collectors;
/*      */ import java.util.stream.Stream;
/*      */ import javax.annotation.Nullable;
/*      */ import javax.imageio.ImageIO;
/*      */ import joptsimple.OptionSet;
/*      */ import net.minecrell.terminalconsole.TerminalConsoleAppender;
/*      */ import org.apache.logging.log4j.LogManager;
/*      */ import org.apache.logging.log4j.Logger;
/*      */ import org.bukkit.Location;
/*      */ import org.bukkit.World;
/*      */ import org.bukkit.command.ConsoleCommandSender;
/*      */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.Validate;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
/*      */ import org.bukkit.event.Event;
/*      */ import org.bukkit.event.server.ServerLoadEvent;
/*      */ import org.bukkit.event.world.WorldInitEvent;
/*      */ import org.bukkit.event.world.WorldLoadEvent;
/*      */ import org.bukkit.generator.ChunkGenerator;
/*      */ import org.spigotmc.SlackActivityAccountant;
/*      */ import org.spigotmc.SpigotConfig;
/*      */ import org.spigotmc.WatchdogThread;
/*      */ 
/*      */ public abstract class MinecraftServer extends IAsyncTaskHandlerReentrant<TickTask> implements IMojangStatistics, ICommandListener, AutoCloseable {
/*      */   private static MinecraftServer SERVER;
/*   77 */   public static final Logger LOGGER = LogManager.getLogger();
/*   78 */   public static final File b = new File("usercache.json");
/*   79 */   public static final WorldSettings c = new WorldSettings("Demo World", EnumGamemode.SURVIVAL, false, EnumDifficulty.NORMAL, false, new GameRules(), DataPackConfiguration.a);
/*      */   public Convertable.ConversionSession convertable;
/*      */   public final WorldNBTStorage worldNBTStorage;
/*   82 */   private final MojangStatisticsGenerator snooper = new MojangStatisticsGenerator("server", this, SystemUtils.getMonotonicMillis());
/*   83 */   private final List<Runnable> tickables = Lists.newArrayList(); private final GameProfilerSwitcher m; private GameProfilerFiller methodProfiler; private ServerConnection serverConnection;
/*      */   public final WorldLoadListenerFactory worldLoadListenerFactory;
/*      */   private final ServerPing serverPing;
/*      */   private final Random r;
/*      */   public final DataFixer dataConverterManager;
/*      */   private String serverIp;
/*      */   private int serverPort;
/*      */   public final IRegistryCustom.Dimension customRegistry;
/*      */   public final Map<ResourceKey<World>, WorldServer> worldServer;
/*      */   private PlayerList playerList;
/*      */   private volatile boolean isRunning;
/*      */   private volatile boolean isRestarting = false;
/*      */   private boolean isStopped;
/*      */   private int ticks;
/*      */   protected final Proxy proxy;
/*      */   private boolean onlineMode;
/*      */   private boolean B;
/*      */   private boolean pvpMode;
/*      */   private boolean allowFlight;
/*      */   @Nullable
/*      */   private String motd;
/*      */   private int F;
/*      */   private int G;
/*      */   public final long[] h;
/*      */   
/*      */   public long[] getTickTimes() {
/*  109 */     return this.h;
/*      */   }
/*  111 */   public final TickTimes tickTimes5s = new TickTimes(100);
/*  112 */   public final TickTimes tickTimes10s = new TickTimes(200);
/*  113 */   public final TickTimes tickTimes60s = new TickTimes(1200); @Nullable
/*      */   private KeyPair H; @Nullable
/*      */   private String I; private boolean demoMode; private String K; private String L; private volatile boolean hasTicked; private long lastOverloadTime; private boolean O; private boolean P; private final MinecraftSessionService minecraftSessionService; private final GameProfileRepository gameProfileRepository; private final UserCache userCache; private long T; public final Thread serverThread; private long nextTick; private long W; private boolean X; private final ResourcePackRepository resourcePackRepository; private final ScoreboardServer scoreboardServer; @Nullable
/*      */   private PersistentCommandStorage persistentCommandStorage; private final BossBattleCustomData bossBattleCustomData; private final CustomFunctionData customFunctionData;
/*      */   private final CircularTimer circularTimer;
/*      */   private boolean af;
/*      */   private float ag;
/*      */   public final Executor executorService;
/*      */   @Nullable
/*      */   private String ai;
/*      */   public DataPackResources dataPackResources;
/*      */   private final DefinedStructureManager ak;
/*      */   protected SaveData saveData;
/*      */   public DataPackConfiguration datapackconfiguration;
/*      */   public CraftServer server;
/*      */   public OptionSet options;
/*      */   public ConsoleCommandSender console;
/*      */   public RemoteConsoleCommandSender remoteConsole;
/*      */   
/*  132 */   final long getTickOversleepMaxTime() { return this.W; } final boolean hasExecutedTask() {
/*  133 */     return this.X;
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
/*  157 */   public static int currentTick = 0;
/*  158 */   public Queue<Runnable> processQueue = new ConcurrentLinkedQueue<>();
/*      */   
/*      */   public int autosavePeriod;
/*      */   
/*      */   public boolean serverAutoSave = false;
/*      */   public CommandDispatcher vanillaCommandDispatcher;
/*      */   public boolean forceTicks;
/*      */   public static final int TPS = 20;
/*      */   public static final int TICK_TIME = 50000000;
/*      */   private static final int SAMPLE_INTERVAL = 20;
/*  168 */   public final double[] recentTps = new double[3];
/*  169 */   public final SlackActivityAccountant slackActivityAccountant = new SlackActivityAccountant(); public volatile Thread shutdownThread; private boolean hasStopped; public volatile boolean hasFullyShutdown; private final Object stopLock; private static final long SEC_IN_NANO = 1000000000L; private static final long MAX_CATCHUP_BUFFER = 60000000000L; private long lastTick; private long catchupTime;
/*      */   public final RollingAverage tps1;
/*      */   public final RollingAverage tps5;
/*      */   public final RollingAverage tps15;
/*      */   
/*      */   public static <S extends MinecraftServer> S a(Function<Thread, S> function) {
/*  175 */     AtomicReference<S> atomicreference = new AtomicReference<>();
/*  176 */     Thread thread = new Thread(() -> ((MinecraftServer)atomicreference.get()).w(), "Server thread");
/*      */ 
/*      */ 
/*      */     
/*  180 */     thread.setUncaughtExceptionHandler((thread1, throwable) -> LOGGER.error(throwable));
/*      */ 
/*      */     
/*  183 */     MinecraftServer minecraftServer = (MinecraftServer)function.apply(thread);
/*      */     
/*  185 */     atomicreference.set((S)minecraftServer);
/*  186 */     thread.setPriority(7);
/*  187 */     thread.start();
/*  188 */     return (S)minecraftServer;
/*      */   }
/*      */   
/*      */   public MinecraftServer(OptionSet options, DataPackConfiguration datapackconfiguration, Thread thread, IRegistryCustom.Dimension iregistrycustom_dimension, Convertable.ConversionSession convertable_conversionsession, SaveData savedata, ResourcePackRepository resourcepackrepository, Proxy proxy, DataFixer datafixer, DataPackResources datapackresources, MinecraftSessionService minecraftsessionservice, GameProfileRepository gameprofilerepository, UserCache usercache, WorldLoadListenerFactory worldloadlistenerfactory) {
/*  192 */     super("Server");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  739 */     this.hasStopped = false;
/*  740 */     this.hasFullyShutdown = false;
/*  741 */     this.stopLock = new Object();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  887 */     this.lastTick = 0L;
/*  888 */     this.catchupTime = 0L;
/*  889 */     this.tps1 = new RollingAverage(60);
/*  890 */     this.tps5 = new RollingAverage(300);
/*  891 */     this.tps15 = new RollingAverage(900);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1071 */     this.isOversleep = false; SERVER = this; this.m = new GameProfilerSwitcher(SystemUtils.a, this::ah); this.methodProfiler = GameProfilerDisabled.a; this.serverPing = new ServerPing(); this.r = new Random(); this.serverPort = -1; this.worldServer = Maps.newLinkedHashMap(); this.isRunning = true; this.h = new long[100]; this.K = ""; this.L = ""; this.nextTick = SystemUtils.getMonotonicMillis(); this.scoreboardServer = new ScoreboardServer(this); this.bossBattleCustomData = new BossBattleCustomData(); this.circularTimer = new CircularTimer(); this.customRegistry = iregistrycustom_dimension; this.saveData = savedata; this.proxy = proxy; this.resourcePackRepository = resourcepackrepository; this.dataPackResources = datapackresources; this.minecraftSessionService = minecraftsessionservice; this.gameProfileRepository = gameprofilerepository; this.userCache = usercache; this.worldLoadListenerFactory = worldloadlistenerfactory; this.convertable = convertable_conversionsession; this.worldNBTStorage = convertable_conversionsession.b(); this.dataConverterManager = datafixer; this.customFunctionData = new CustomFunctionData(this, datapackresources.a()); this.ak = new DefinedStructureManager(datapackresources.h(), convertable_conversionsession, datafixer); this.serverThread = thread; this.executorService = SystemUtils.f(); this.options = options; this.datapackconfiguration = datapackconfiguration; this.vanillaCommandDispatcher = datapackresources.commandDispatcher; Runtime.getRuntime().addShutdownHook((Thread)new ServerShutdownThread(this));
/*      */   } private void initializeScoreboards(WorldPersistentData worldpersistentdata) { PersistentScoreboard persistentscoreboard = worldpersistentdata.<PersistentScoreboard>a(PersistentScoreboard::new, "scoreboard"); persistentscoreboard.a(getScoreboard()); getScoreboard().a(new RunnableSaveScoreboard(persistentscoreboard)); } public static void convertWorld(Convertable.ConversionSession convertable_conversionsession) { if (convertable_conversionsession.isConvertable()) { LOGGER.info("Converting map! {}", convertable_conversionsession.getLevelName()); convertable_conversionsession.convert(new IProgressUpdate() { private long a = SystemUtils.getMonotonicMillis(); public void a(IChatBaseComponent ichatbasecomponent) {} public void a(int i) { if (SystemUtils.getMonotonicMillis() - this.a >= 1000L) { this.a = SystemUtils.getMonotonicMillis(); MinecraftServer.LOGGER.info("Converting... {}%", Integer.valueOf(i)); }  } public void c(IChatBaseComponent ichatbasecomponent) {} }
/* 1073 */         ); }  } protected void loadWorld(String s) { int worldCount = 3; for (int worldId = 0; worldId < worldCount; worldId++) { WorldServer world; Convertable.ConversionSession worldSession; DimensionManager dimensionmanager; ChunkGenerator chunkgenerator; byte dimension = 0; ResourceKey<WorldDimension> dimensionKey = WorldDimension.OVERWORLD; if (worldId == 1) if (getAllowNether()) { dimension = -1; dimensionKey = WorldDimension.THE_NETHER; } else { continue; }   if (worldId == 2) if (this.server.getAllowEnd()) { dimension = 1; dimensionKey = WorldDimension.THE_END; } else { continue; }   String worldType = World.Environment.getEnvironment(dimension).toString().toLowerCase(); String name = (dimension == 0) ? s : (s + "_" + worldType); if (dimension == 0) { worldSession = this.convertable; } else { String dim = "DIM" + dimension; File newWorld = new File(new File(name), dim); File oldWorld = new File(new File(s), dim); File oldLevelDat = new File(new File(s), "level.dat"); if (!newWorld.isDirectory() && oldWorld.isDirectory() && oldLevelDat.isFile()) { LOGGER.info("---- Migration of old " + worldType + " folder required ----"); LOGGER.info("Unfortunately due to the way that Minecraft implemented multiworld support in 1.6, Bukkit requires that you move your " + worldType + " folder to a new location in order to operate correctly."); LOGGER.info("We will move this folder for you, but it will mean that you need to move it back should you wish to stop using Bukkit in the future."); LOGGER.info("Attempting to move " + oldWorld + " to " + newWorld + "..."); if (newWorld.exists()) { LOGGER.warn("A file or folder already exists at " + newWorld + "!"); LOGGER.info("---- Migration of old " + worldType + " folder failed ----"); } else if (newWorld.getParentFile().mkdirs()) { if (oldWorld.renameTo(newWorld)) { LOGGER.info("Success! To restore " + worldType + " in the future, simply move " + newWorld + " to " + oldWorld); try { Files.copy(oldLevelDat, new File(new File(name), "level.dat")); FileUtils.copyDirectory(new File(new File(s), "data"), new File(new File(name), "data")); } catch (IOException exception) { LOGGER.warn("Unable to migrate world data."); }  LOGGER.info("---- Migration of old " + worldType + " folder complete ----"); } else { LOGGER.warn("Could not move folder " + oldWorld + " to " + newWorld + "!"); LOGGER.info("---- Migration of old " + worldType + " folder failed ----"); }  } else { LOGGER.warn("Could not create path for " + newWorld + "!"); LOGGER.info("---- Migration of old " + worldType + " folder failed ----"); }  }  try { worldSession = Convertable.a(this.server.getWorldContainer().toPath()).c(name, dimensionKey); } catch (IOException ex) { throw new RuntimeException(ex); }  convertWorld(worldSession); }  ChunkGenerator gen = this.server.getGenerator(name); IRegistryCustom.Dimension iregistrycustom_dimension = this.customRegistry; RegistryReadOps<NBTBase> registryreadops = RegistryReadOps.a(DynamicOpsNBT.a, this.dataPackResources.h(), iregistrycustom_dimension); WorldDataServer worlddata = (WorldDataServer)worldSession.a(registryreadops, this.datapackconfiguration); if (worlddata == null) { WorldSettings worldsettings; GeneratorSettings generatorSettings; if (isDemoMode()) { worldsettings = c; generatorSettings = GeneratorSettings.a(iregistrycustom_dimension); } else { DedicatedServerProperties dedicatedserverproperties = ((DedicatedServer)this).getDedicatedServerProperties(); worldsettings = new WorldSettings(dedicatedserverproperties.levelName, dedicatedserverproperties.gamemode, dedicatedserverproperties.hardcore, dedicatedserverproperties.difficulty, false, new GameRules(), this.datapackconfiguration); generatorSettings = this.options.has("bonusChest") ? dedicatedserverproperties.generatorSettings.j() : dedicatedserverproperties.generatorSettings; }  worlddata = new WorldDataServer(worldsettings, generatorSettings, Lifecycle.stable()); }  worlddata.checkName(name); if (this.options.has("forceUpgrade")) Main.convertWorld(worldSession, DataConverterRegistry.a(), this.options.has("eraseCache"), () -> true, (ImmutableSet<ResourceKey<DimensionManager>>)worlddata.getGeneratorSettings().d().d().stream().map(entry -> ResourceKey.a(IRegistry.K, ((ResourceKey)entry.getKey()).a())).collect(ImmutableSet.toImmutableSet()));  IWorldDataServer iworlddataserver = worlddata; GeneratorSettings generatorsettings = worlddata.getGeneratorSettings(); boolean flag = generatorsettings.isDebugWorld(); long i = generatorsettings.getSeed(); long j = BiomeManager.a(i); ImmutableList immutableList = ImmutableList.of(new MobSpawnerPhantom(), new MobSpawnerPatrol(), new MobSpawnerCat(), new VillageSiege(), new MobSpawnerTrader(iworlddataserver)); RegistryMaterials<WorldDimension> registrymaterials = generatorsettings.d(); WorldDimension worlddimension = registrymaterials.a(dimensionKey); if (worlddimension == null) { dimensionmanager = this.customRegistry.a().d(DimensionManager.OVERWORLD); chunkgenerator = GeneratorSettings.a(this.customRegistry.b(IRegistry.ay), this.customRegistry.b(IRegistry.ar), (new Random()).nextLong()); } else { dimensionmanager = worlddimension.b(); chunkgenerator = worlddimension.c(); }  ResourceKey<World> worldKey = ResourceKey.a(IRegistry.L, dimensionKey.a()); if (worldId == 0) { this.saveData = worlddata; this.saveData.setGameType((((DedicatedServer)this).getDedicatedServerProperties()).gamemode); WorldLoadListener worldloadlistener = this.worldLoadListenerFactory.create(11); world = new WorldServer(this, this.executorService, worldSession, iworlddataserver, worldKey, dimensionmanager, worldloadlistener, chunkgenerator, flag, j, (List<MobSpawner>)immutableList, true, World.Environment.getEnvironment(dimension), gen); WorldPersistentData worldpersistentdata = world.getWorldPersistentData(); initializeScoreboards(worldpersistentdata); this.server.scoreboardManager = new CraftScoreboardManager(this, world.getScoreboard()); this.persistentCommandStorage = new PersistentCommandStorage(worldpersistentdata); } else { WorldLoadListener worldloadlistener = this.worldLoadListenerFactory.create(11); world = new WorldServer(this, this.executorService, worldSession, iworlddataserver, worldKey, dimensionmanager, worldloadlistener, chunkgenerator, flag, j, (List<MobSpawner>)ImmutableList.of(), true, World.Environment.getEnvironment(dimension), gen); }  worlddata.a(getServerModName(), getModded().isPresent()); initWorld(world, worlddata, this.saveData, worlddata.getGeneratorSettings()); this.server.getPluginManager().callEvent((Event)new WorldInitEvent((World)world.getWorld())); this.worldServer.put(world.getDimensionKey(), world); getPlayerList().setPlayerFileData(world); if (worlddata.getCustomBossEvents() != null) getBossBattleCustomData().load(worlddata.getCustomBossEvents());  continue; }  updateWorldSettings(); for (WorldServer worldserver : getWorlds()) { loadSpawn((worldserver.getChunkProvider()).playerChunkMap.worldLoadListener, worldserver); this.server.getPluginManager().callEvent((Event)new WorldLoadEvent((World)worldserver.getWorld())); }  Scoreboard scoreboard = getScoreboard(); Collection<String> toRemove = (Collection<String>)scoreboard.getTeams().stream().filter(team -> team.getName().startsWith("collideRule_")).map(ScoreboardTeam::getName).collect(Collectors.toList()); for (String teamName : toRemove) scoreboard.removeTeam(scoreboard.getTeam(teamName));  if (!PaperConfig.enablePlayerCollisions) { (getPlayerList()).collideRuleTeamName = StringUtils.left("collideRule_" + ThreadLocalRandom.current().nextInt(), 16); ScoreboardTeam collideTeam = scoreboard.createTeam((getPlayerList()).collideRuleTeamName); collideTeam.setCanSeeFriendlyInvisibles(false); }  this.server.enablePlugins(PluginLoadOrder.POSTWORLD); this.server.getPluginManager().callEvent((Event)new ServerLoadEvent(ServerLoadEvent.LoadType.STARTUP)); this.serverConnection.acceptConnections(); } protected void updateWorldSettings() {} public void initWorld(WorldServer worldserver, IWorldDataServer iworlddataserver, SaveData saveData, GeneratorSettings generatorsettings) { boolean flag = generatorsettings.isDebugWorld(); if (worldserver.generator != null) worldserver.getWorld().getPopulators().addAll(worldserver.generator.getDefaultPopulators((World)worldserver.getWorld()));  WorldBorder worldborder = worldserver.getWorldBorder(); worldborder.a(iworlddataserver.r()); if (!iworlddataserver.p()) { try { a(worldserver, iworlddataserver, generatorsettings.c(), flag, true); iworlddataserver.c(true); if (flag) a(this.saveData);  } catch (Throwable throwable) { CrashReport crashreport = CrashReport.a(throwable, "Exception initializing level"); try { worldserver.a(crashreport); } catch (Throwable throwable1) {} throw new ReportedException(crashreport); }  iworlddataserver.c(true); }  } private static void a(WorldServer worldserver, IWorldDataServer iworlddataserver, boolean flag, boolean flag1, boolean flag2) { ChunkGenerator chunkgenerator = worldserver.getChunkProvider().getChunkGenerator(); if (!flag2) { iworlddataserver.setSpawn(BlockPosition.ZERO.up(chunkgenerator.getSpawnHeight()), 0.0F); } else if (flag1) { iworlddataserver.setSpawn(BlockPosition.ZERO.up(), 0.0F); } else { if (worldserver.generator != null) { Random rand = new Random(worldserver.getSeed()); Location spawn = worldserver.generator.getFixedSpawnLocation((World)worldserver.getWorld(), rand); if (spawn != null) { if (spawn.getWorld() != worldserver.getWorld()) throw new IllegalStateException("Cannot set spawn point for " + iworlddataserver.getName() + " to be in another world (" + spawn.getWorld().getName() + ")");  iworlddataserver.setSpawn(new BlockPosition(spawn.getBlockX(), spawn.getBlockY(), spawn.getBlockZ()), spawn.getYaw()); return; }  }  WorldChunkManager worldchunkmanager = chunkgenerator.getWorldChunkManager(); Random random = new Random(worldserver.getSeed()); BlockPosition blockposition = worldchunkmanager.a(0, worldserver.getSeaLevel(), 0, 256, biomebase -> biomebase.b().b(), random); ChunkCoordIntPair chunkcoordintpair = (blockposition == null) ? new ChunkCoordIntPair(0, 0) : new ChunkCoordIntPair(blockposition); if (blockposition == null) LOGGER.warn("Unable to find spawn biome");  boolean flag3 = false; Iterator<Block> iterator = TagsBlock.VALID_SPAWN.getTagged().iterator(); while (iterator.hasNext()) { Block block = iterator.next(); if (worldchunkmanager.c().contains(block.getBlockData())) { flag3 = true; break; }  }  iworlddataserver.setSpawn(chunkcoordintpair.l().b(8, chunkgenerator.getSpawnHeight(), 8), 0.0F); int i = 0; int j = 0; int k = 0; int l = -1; boolean flag4 = true; for (int i1 = 0; i1 < 1024; i1++) { if (i > -16 && i <= 16 && j > -16 && j <= 16) { BlockPosition blockposition1 = WorldProviderNormal.a(worldserver, new ChunkCoordIntPair(chunkcoordintpair.x + i, chunkcoordintpair.z + j), flag3); if (blockposition1 != null) { iworlddataserver.setSpawn(blockposition1, 0.0F); break; }  }  if (i == j || (i < 0 && i == -j) || (i > 0 && i == 1 - j)) { int j1 = k; k = -l; l = j1; }  i += k; j += l; }  if (flag) { WorldGenFeatureConfigured<?, ?> worldgenfeatureconfigured = BiomeDecoratorGroups.BONUS_CHEST; worldgenfeatureconfigured.a(worldserver, chunkgenerator, worldserver.random, new BlockPosition(iworlddataserver.a(), iworlddataserver.b(), iworlddataserver.c())); }  }  } private void a(SaveData savedata) { savedata.setDifficulty(EnumDifficulty.PEACEFUL); savedata.d(true); IWorldDataServer iworlddataserver = savedata.H(); iworlddataserver.setStorm(false); iworlddataserver.setThundering(false); iworlddataserver.a(1000000000); iworlddataserver.setDayTime(6000L); iworlddataserver.setGameType(EnumGamemode.SPECTATOR); } public void loadSpawn(WorldLoadListener worldloadlistener, WorldServer worldserver) { if (!worldserver.getWorld().getKeepSpawnInMemory()) return;  this.forceTicks = true; int radiusBlocks = worldserver.paperConfig.keepLoadedRange; int radiusChunks = radiusBlocks / 16 + (((radiusBlocks & 0xF) != 0) ? 1 : 0); int totalChunks = radiusChunks * 2 + 1; totalChunks *= totalChunks; worldloadlistener.setChunkRadius(radiusBlocks / 16); LOGGER.info("Preparing start region for dimension {}", worldserver.getDimensionKey().a()); BlockPosition blockposition = worldserver.getSpawn(); worldloadlistener.a(new ChunkCoordIntPair(blockposition)); ChunkProviderServer chunkproviderserver = worldserver.getChunkProvider(); chunkproviderserver.getLightEngine().a(500); this.nextTick = SystemUtils.getMonotonicMillis(); if (worldserver.keepSpawnInMemory) worldserver.addTicketsForSpawn(radiusBlocks, blockposition);  LOGGER.info("Loaded " + chunkproviderserver.b() + " spawn chunks for world " + worldserver.getWorld().getName()); executeModerately(); WorldServer worldserver1 = worldserver; ForcedChunk forcedchunk = worldserver.getWorldPersistentData().<ForcedChunk>b(ForcedChunk::new, "chunks"); if (forcedchunk != null) { LongIterator longiterator = forcedchunk.a().iterator(); while (longiterator.hasNext()) { long i = longiterator.nextLong(); ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(i); worldserver1.getChunkProvider().a(chunkcoordintpair, true); }  }  executeModerately(); worldloadlistener.b(); chunkproviderserver.getLightEngine().a(worldserver.paperConfig.lightQueueSize); bb(); this.forceTicks = false; } protected void loadResourcesZip() { File file = this.convertable.getWorldFolder(SavedFile.RESOURCES_ZIP).toFile(); if (file.isFile()) { String s = this.convertable.getLevelName(); try { setResourcePack("level://" + URLEncoder.encode(s, StandardCharsets.UTF_8.toString()) + "/resources.zip", ""); } catch (UnsupportedEncodingException unsupportedencodingexception) { LOGGER.warn("Something went wrong url encoding {}", s); }  }  } public EnumGamemode getGamemode() { return this.saveData.getGameType(); } public boolean isHardcore() { return this.saveData.isHardcore(); } public boolean saveChunks(boolean flag, boolean flag1, boolean flag2) { boolean flag3 = false; for (Iterator<WorldServer> iterator = getWorlds().iterator(); iterator.hasNext(); flag3 = true) { WorldServer worldserver = iterator.next(); if (!flag) LOGGER.info("Saving chunks for level '{}'/{}", worldserver, worldserver.getDimensionKey().a());  worldserver.save((IProgressUpdate)null, flag1, (worldserver.savingDisabled && !flag2)); }  return flag3; } public void close() { stop(); } public final boolean hasStopped() { synchronized (this.stopLock) { return this.hasStopped; }  } protected void stop() { synchronized (this.stopLock) { if (this.hasStopped) return;  this.hasStopped = true; }  this.shutdownThread = Thread.currentThread(); WatchdogThread.doStop(); if (!isMainThread()) { LOGGER.info("Stopping main thread (Ignore any thread death message you see! - DO NOT REPORT THREAD DEATH TO PAPER)"); while (getThread().isAlive()) { getThread().stop(); try { Thread.sleep(1L); } catch (InterruptedException interruptedException) {} }  }  LOGGER.info("Stopping server"); MinecraftTimings.stopServer(); if (this.server != null) { this.server.disablePlugins(); this.server.waitForAsyncTasksShutdown(); }  if (getServerConnection() != null) getServerConnection().b();  if (this.playerList != null) { LOGGER.info("Saving players"); this.playerList.savePlayers(); this.playerList.shutdown(this.isRestarting); try { Thread.sleep(100L); } catch (InterruptedException interruptedException) {} }  LOGGER.info("Saving worlds"); Iterator<WorldServer> iterator = getWorlds().iterator(); while (iterator.hasNext()) { WorldServer worldserver = iterator.next(); if (worldserver != null) worldserver.savingDisabled = false;  }  saveChunks(false, true, false); iterator = getWorlds().iterator(); while (iterator.hasNext()) { WorldServer worldserver = iterator.next(); if (worldserver != null) try { worldserver.close(); } catch (IOException ioexception) { LOGGER.error("Exception closing the level", ioexception); }   }  if (this.snooper.d()) this.snooper.e();  this.dataPackResources.close(); try { this.convertable.close(); } catch (IOException ioexception1) { LOGGER.error("Failed to unlock level {}", this.convertable.getLevelName(), ioexception1); }  MCUtil.asyncExecutor.shutdown(); try { MCUtil.asyncExecutor.awaitTermination(30L, TimeUnit.SECONDS); } catch (InterruptedException interruptedException) {} if (SpigotConfig.saveUserCacheOnStopOnly) { LOGGER.info("Saving usercache.json"); getUserCache().b(false); }  LOGGER.info("Flushing Chunk IO"); PaperFileIOThread.Holder.INSTANCE.close(true, true); LOGGER.info("Closing Thread Pool"); SystemUtils.shutdownServerThreadPool(); LOGGER.info("Closing Server"); try { TerminalConsoleAppender.close(); } catch (Exception exception) {} exit(); } private boolean canOversleep() { return (hasExecutedTask() && SystemUtils.getMonotonicMillis() < getTickOversleepMaxTime()); }
/*      */   public String getServerIp() { return this.serverIp; }
/*      */   public void a_(String s) { this.serverIp = s; }
/*      */   public boolean isRunning() { return this.isRunning; } public void safeShutdown(boolean flag) { safeShutdown(flag, false); } public void safeShutdown(boolean flag, boolean isRestarting) { this.isRunning = false; this.isRestarting = isRestarting; if (flag) try { this.serverThread.join(); } catch (InterruptedException interruptedexception) { LOGGER.error("Error while shutting down", interruptedexception); }   } private static double calcTps(double avg, double exp, double tps) { return avg * exp + tps * (1.0D - exp); } public static class RollingAverage {
/* 1077 */     private final int size; private long time; private BigDecimal total; private int index = 0; private final BigDecimal[] samples; private final long[] times; RollingAverage(int size) { this.size = size; this.time = size * 1000000000L; this.total = dec(20L).multiply(dec(1000000000L)).multiply(dec(size)); this.samples = new BigDecimal[size]; this.times = new long[size]; for (int i = 0; i < size; i++) { this.samples[i] = dec(20L); this.times[i] = 1000000000L; }  } private static BigDecimal dec(long t) { return new BigDecimal(t); } public void add(BigDecimal x, long t) { this.time -= this.times[this.index]; this.total = this.total.subtract(this.samples[this.index].multiply(dec(this.times[this.index]))); this.samples[this.index] = x; this.times[this.index] = t; this.time += t; this.total = this.total.add(x.multiply(dec(t))); if (++this.index == this.size) this.index = 0;  } public double getAverage() { return this.total.divide(dec(this.time), 30, RoundingMode.HALF_UP).doubleValue(); } } private static final BigDecimal TPS_BASE = (new BigDecimal(1.0E9D)).multiply(new BigDecimal(20)); boolean isOversleep; static final long CHUNK_TASK_QUEUE_BACKOFF_MIN_TIME = 25000L; static final long MAX_CHUNK_EXEC_TIME = 1000L; static final long TASK_EXECUTION_FAILURE_BACKOFF = 5000L; private static long lastMidTickExecute; private static long lastMidTickExecuteFailure; protected void w() { try { long serverStartTime = SystemUtils.getMonotonicNanos(); if (init()) { this.nextTick = SystemUtils.getMonotonicMillis(); this.serverPing.setMOTD(new ChatComponentText(this.motd)); this.serverPing.setServerInfo(new ServerPing.ServerData(SharedConstants.getGameVersion().getName(), SharedConstants.getGameVersion().getProtocolVersion())); a(this.serverPing); LOGGER.info("Running delayed init tasks"); this.server.getScheduler().mainThreadHeartbeat(this.ticks); String doneTime = String.format(Locale.ROOT, "%.3fs", new Object[] { Double.valueOf((SystemUtils.getMonotonicNanos() - serverStartTime) / 1.0E9D) }); LOGGER.info("Done ({})! For help, type \"help\"", doneTime); WatchdogThread.tick(); WatchdogThread.hasStarted = true; Arrays.fill(this.recentTps, 20.0D); long start = System.nanoTime(), tickSection = start; this.lastTick = start - 50000000L; while (this.isRunning) { long curTime, i = (curTime = System.nanoTime()) / 1000000L - this.nextTick; if (i > 5000L && this.nextTick - this.lastOverloadTime >= 30000L) { long j = i / 50L; if (this.server.getWarnOnOverload()) LOGGER.warn("Can't keep up! Is the server overloaded? Running {}ms or {} ticks behind", Long.valueOf(i), Long.valueOf(j));  this.nextTick += j * 50L; this.lastOverloadTime = this.nextTick; }  if (++currentTick % 20 == 0) { long diff = curTime - tickSection; BigDecimal currentTps = TPS_BASE.divide(new BigDecimal(diff), 30, RoundingMode.HALF_UP); this.tps1.add(currentTps, diff); this.tps5.add(currentTps, diff); this.tps15.add(currentTps, diff); this.recentTps[0] = this.tps1.getAverage(); this.recentTps[1] = this.tps5.getAverage(); this.recentTps[2] = this.tps15.getAverage(); tickSection = curTime; }  this.lastTick = curTime; this.nextTick += 50L; GameProfilerTick gameprofilertick = GameProfilerTick.a("Server"); a(gameprofilertick); this.methodProfiler.a(); this.methodProfiler.enter("tick"); a(this::canSleepForTick); this.methodProfiler.exitEnter("nextTickWait"); this.X = true; this.W = Math.max(SystemUtils.getMonotonicMillis() + 50L, this.nextTick); sleepForTick(); this.methodProfiler.exit(); this.methodProfiler.b(); b(gameprofilertick); this.hasTicked = true; }  } else { a((CrashReport)null); }  } catch (Throwable throwable) { CrashReport crashreport; if (throwable instanceof ThreadDeath) { LOGGER.error("Main thread terminated by WatchDog due to hard crash", throwable); return; }  LOGGER.error("Encountered an unexpected exception", throwable); if (throwable.getCause() != null) LOGGER.error("\tCause of unexpected exception was", throwable.getCause());  if (throwable instanceof ReportedException) { crashreport = b(((ReportedException)throwable).a()); } else { crashreport = b(new CrashReport("Exception in server tick loop", throwable)); }  File file = new File(new File(B(), "crash-reports"), "crash-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date()) + "-server.txt"); if (crashreport.a(file)) { LOGGER.error("This crash report has been saved to: {}", file.getAbsolutePath()); } else { LOGGER.error("We were unable to save this crash report to disk."); }  a(crashreport); } finally { try { this.isStopped = true; stop(); } catch (Throwable throwable1) { LOGGER.error("Exception stopping the server", throwable1); } finally {} }  } public boolean canSleepForTick() { if (this.isOversleep) return canOversleep();  return (this.forceTicks || isEntered() || SystemUtils.getMonotonicMillis() < (this.X ? this.W : this.nextTick)); } private boolean canSleepForTickNoOversleep() { return (this.forceTicks || isEntered() || SystemUtils.getMonotonicMillis() < this.nextTick); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean tickMidTickTasks() {
/* 1093 */     boolean executed = false;
/* 1094 */     for (WorldServer world : getWorlds()) {
/* 1095 */       long currTime = System.nanoTime();
/* 1096 */       if (currTime - world.lastMidTickExecuteFailure <= 5000L) {
/*      */         continue;
/*      */       }
/* 1099 */       if (!world.getChunkProvider().runTasks()) {
/*      */         
/* 1101 */         world.lastMidTickExecuteFailure = currTime; continue;
/*      */       } 
/* 1103 */       executed = true;
/*      */     } 
/*      */ 
/*      */     
/* 1107 */     return executed;
/*      */   }
/*      */   
/*      */   public final void executeMidTickTasks() {
/* 1111 */     AsyncCatcher.catchOp("mid tick chunk task execution");
/* 1112 */     long startTime = System.nanoTime();
/* 1113 */     if (startTime - lastMidTickExecute <= 25000L || startTime - lastMidTickExecuteFailure <= 5000L) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1119 */     MinecraftTimings.midTickChunkTasks.startTiming(); try {
/*      */       boolean moreTasks; long currTime, diff;
/*      */       do {
/* 1122 */         moreTasks = tickMidTickTasks();
/* 1123 */         currTime = System.nanoTime();
/* 1124 */         diff = currTime - startTime;
/*      */       }
/* 1126 */       while (moreTasks && diff < 1000L);
/* 1127 */       if (!moreTasks) {
/* 1128 */         lastMidTickExecuteFailure = currTime;
/*      */       }
/*      */ 
/*      */       
/* 1132 */       long overuse = diff - 1000L;
/* 1133 */       if (overuse >= 10000000L)
/*      */       {
/* 1135 */         overuse = 10000000L;
/*      */       }
/*      */       
/* 1138 */       double overuseCount = overuse / 1000.0D;
/* 1139 */       long extraSleep = Math.round(overuseCount * 25000.0D);
/*      */       
/* 1141 */       lastMidTickExecute = currTime + extraSleep;
/*      */ 
/*      */       
/*      */       return;
/*      */     } finally {
/* 1146 */       MinecraftTimings.midTickChunkTasks.stopTiming();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void executeModerately() {
/* 1152 */     executeAll();
/* 1153 */     LockSupport.parkNanos("executing tasks", 1000L);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void sleepForTick() {
/* 1159 */     awaitTasks(() -> !canSleepForTickNoOversleep());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected TickTask postToMainThread(Runnable runnable) {
/* 1169 */     if (this.hasStopped && Thread.currentThread().equals(this.shutdownThread)) {
/* 1170 */       runnable.run();
/* 1171 */       runnable = (() -> {
/*      */         
/*      */         });
/* 1174 */     }  return new TickTask(this.ticks, runnable);
/*      */   }
/*      */   
/*      */   protected boolean canExecute(TickTask ticktask) {
/* 1178 */     return (ticktask.a() + 3 < this.ticks || canSleepForTick());
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean executeNext() {
/* 1183 */     boolean flag = ba();
/*      */     
/* 1185 */     this.X = flag;
/* 1186 */     return flag;
/*      */   }
/*      */   
/*      */   private boolean ba() {
/* 1190 */     if (super.executeNext()) {
/* 1191 */       executeMidTickTasks();
/* 1192 */       return true;
/*      */     } 
/* 1194 */     if (canSleepForTick()) {
/* 1195 */       Iterator<WorldServer> iterator = getWorlds().iterator();
/*      */       
/* 1197 */       while (iterator.hasNext()) {
/* 1198 */         WorldServer worldserver = iterator.next();
/*      */         
/* 1200 */         if (worldserver.getChunkProvider().runTasks()) {
/* 1201 */           return true;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1206 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void c(TickTask ticktask) {
/* 1211 */     getMethodProfiler().c("runTask");
/* 1212 */     executeTask((R)ticktask);
/*      */   }
/*      */   
/*      */   private void a(ServerPing serverping) {
/* 1216 */     File file = c("server-icon.png");
/*      */     
/* 1218 */     if (!file.exists()) {
/* 1219 */       file = this.convertable.f();
/*      */     }
/*      */     
/* 1222 */     if (file.isFile()) {
/* 1223 */       ByteBuf bytebuf = Unpooled.buffer();
/*      */       
/*      */       try {
/* 1226 */         BufferedImage bufferedimage = ImageIO.read(file);
/*      */         
/* 1228 */         Validate.validState((bufferedimage.getWidth() == 64), "Must be 64 pixels wide", new Object[0]);
/* 1229 */         Validate.validState((bufferedimage.getHeight() == 64), "Must be 64 pixels high", new Object[0]);
/* 1230 */         ImageIO.write(bufferedimage, "PNG", (OutputStream)new ByteBufOutputStream(bytebuf));
/* 1231 */         ByteBuffer bytebuffer = Base64.getEncoder().encode(bytebuf.nioBuffer());
/*      */         
/* 1233 */         serverping.setFavicon("data:image/png;base64," + StandardCharsets.UTF_8.decode(bytebuffer));
/* 1234 */       } catch (Exception exception) {
/* 1235 */         LOGGER.error("Couldn't load server icon", exception);
/*      */       } finally {
/* 1237 */         bytebuf.release();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public File B() {
/* 1244 */     return new File(".");
/*      */   }
/*      */   
/*      */   protected void a(CrashReport crashreport) {}
/*      */   
/*      */   protected void exit() {}
/*      */   
/*      */   protected void a(BooleanSupplier booleansupplier) {
/* 1252 */     TimingsManager.FULL_SERVER_TICK.startTiming();
/* 1253 */     this.slackActivityAccountant.tickStarted();
/* 1254 */     long i = SystemUtils.getMonotonicNanos();
/*      */ 
/*      */     
/* 1257 */     this.isOversleep = true; MinecraftTimings.serverOversleep.startTiming();
/* 1258 */     awaitTasks(() -> !canOversleep());
/*      */ 
/*      */ 
/*      */     
/* 1262 */     this.isOversleep = false; MinecraftTimings.serverOversleep.stopTiming();
/*      */     
/* 1264 */     (new ServerTickStartEvent(this.ticks + 1)).callEvent();
/*      */     
/* 1266 */     this.ticks++;
/* 1267 */     b(booleansupplier);
/* 1268 */     if (i - this.T >= 5000000000L) {
/* 1269 */       this.T = i;
/* 1270 */       this.serverPing.setPlayerSample(new ServerPing.ServerPingPlayerSample(getMaxPlayers(), getPlayerCount()));
/* 1271 */       GameProfile[] agameprofile = new GameProfile[Math.min(getPlayerCount(), SpigotConfig.playerSample)];
/* 1272 */       int j = MathHelper.nextInt(this.r, 0, getPlayerCount() - agameprofile.length);
/*      */       
/* 1274 */       for (int k = 0; k < agameprofile.length; k++) {
/* 1275 */         agameprofile[k] = ((EntityPlayer)this.playerList.getPlayers().get(j + k)).getProfile();
/*      */       }
/*      */       
/* 1278 */       Collections.shuffle(Arrays.asList((Object[])agameprofile));
/* 1279 */       this.serverPing.b().a(agameprofile);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1284 */     this.serverAutoSave = (this.autosavePeriod > 0 && this.ticks % this.autosavePeriod == 0);
/*      */     
/* 1286 */     int playerSaveInterval = PaperConfig.playerAutoSaveRate;
/* 1287 */     if (playerSaveInterval < 0) {
/* 1288 */       playerSaveInterval = this.autosavePeriod;
/*      */     }
/*      */     
/* 1291 */     this.methodProfiler.enter("save");
/* 1292 */     if (playerSaveInterval > 0) {
/* 1293 */       this.playerList.savePlayers(Integer.valueOf(playerSaveInterval));
/*      */     }
/*      */     
/* 1296 */     for (WorldServer world : getWorlds()) {
/* 1297 */       if (world.paperConfig.autoSavePeriod > 0) {
/* 1298 */         world.saveIncrementally(this.serverAutoSave);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1303 */     this.methodProfiler.exit();
/*      */ 
/*      */ 
/*      */     
/* 1307 */     this.methodProfiler.enter("snooper");
/* 1308 */     if ((((DedicatedServer)this).getDedicatedServerProperties()).snooperEnabled && !this.snooper.d() && this.ticks > 100) {
/* 1309 */       this.snooper.a();
/*      */     }
/*      */     
/* 1312 */     if ((((DedicatedServer)this).getDedicatedServerProperties()).snooperEnabled && this.ticks % 6000 == 0) {
/* 1313 */       this.snooper.b();
/*      */     }
/*      */     
/* 1316 */     this.methodProfiler.exit();
/*      */ 
/*      */     
/* 1319 */     Timing ignored = MinecraftTimings.processTasksTimer.startTiming(); 
/* 1320 */     try { executeAll();
/* 1321 */       if (ignored != null) ignored.close();  } catch (Throwable throwable) { if (ignored != null)
/*      */         try { ignored.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
/*      */           throw throwable; }
/* 1324 */      CachedLists.reset();
/*      */ 
/*      */     
/* 1327 */     long endTime = System.nanoTime();
/* 1328 */     long remaining = 50000000L - endTime - this.lastTick - this.catchupTime;
/* 1329 */     (new ServerTickEndEvent(this.ticks, (endTime - this.lastTick) / 1000000.0D, remaining)).callEvent();
/*      */ 
/*      */     
/* 1332 */     this.methodProfiler.enter("tallying");
/* 1333 */     long l = this.h[this.ticks % 100] = SystemUtils.getMonotonicNanos() - i;
/*      */     
/* 1335 */     this.ag = this.ag * 0.8F + (float)l / 1000000.0F * 0.19999999F;
/* 1336 */     long i1 = SystemUtils.getMonotonicNanos();
/*      */ 
/*      */     
/* 1339 */     this.tickTimes5s.add(this.ticks, l);
/* 1340 */     this.tickTimes10s.add(this.ticks, l);
/* 1341 */     this.tickTimes60s.add(this.ticks, l);
/*      */ 
/*      */     
/* 1344 */     this.circularTimer.a(i1 - i);
/* 1345 */     this.methodProfiler.exit();
/* 1346 */     WatchdogThread.tick();
/* 1347 */     this.slackActivityAccountant.tickEnded(l);
/* 1348 */     TimingsManager.FULL_SERVER_TICK.stopTiming();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void b(BooleanSupplier booleansupplier) {
/* 1353 */     MinecraftTimings.bukkitSchedulerTimer.startTiming();
/* 1354 */     this.server.getScheduler().mainThreadHeartbeat(this.ticks);
/* 1355 */     MinecraftTimings.bukkitSchedulerTimer.stopTiming();
/*      */     
/* 1357 */     this.methodProfiler.enter("commandFunctions");
/* 1358 */     MinecraftTimings.commandFunctionsTimer.startTiming();
/* 1359 */     getFunctionData().tick();
/* 1360 */     MinecraftTimings.commandFunctionsTimer.stopTiming();
/*      */     
/* 1362 */     this.methodProfiler.exitEnter("levels");
/* 1363 */     Iterator<WorldServer> iterator = getWorlds().iterator();
/*      */ 
/*      */ 
/*      */     
/* 1367 */     MinecraftTimings.processQueueTimer.startTiming();
/* 1368 */     while (!this.processQueue.isEmpty()) {
/* 1369 */       ((Runnable)this.processQueue.remove()).run();
/*      */     }
/* 1371 */     MinecraftTimings.processQueueTimer.stopTiming();
/*      */     
/* 1373 */     MinecraftTimings.timeUpdateTimer.startTiming();
/*      */ 
/*      */     
/* 1376 */     for (WorldServer world : getWorlds()) {
/* 1377 */       boolean doDaylight = world.getGameRules().getBoolean(GameRules.DO_DAYLIGHT_CYCLE);
/* 1378 */       long dayTime = world.getDayTime();
/* 1379 */       long worldTime = world.getTime();
/* 1380 */       PacketPlayOutUpdateTime worldPacket = new PacketPlayOutUpdateTime(worldTime, dayTime, doDaylight);
/* 1381 */       for (EntityHuman entityhuman : world.getPlayers()) {
/* 1382 */         if (!(entityhuman instanceof EntityPlayer) || (this.ticks + entityhuman.getId()) % 20 != 0) {
/*      */           continue;
/*      */         }
/* 1385 */         EntityPlayer entityplayer = (EntityPlayer)entityhuman;
/* 1386 */         long playerTime = entityplayer.getPlayerTime();
/*      */         
/* 1388 */         PacketPlayOutUpdateTime packet = (playerTime == dayTime) ? worldPacket : new PacketPlayOutUpdateTime(worldTime, playerTime, doDaylight);
/* 1389 */         entityplayer.playerConnection.sendPacket(packet);
/*      */       } 
/*      */     } 
/*      */     
/* 1393 */     MinecraftTimings.timeUpdateTimer.stopTiming();
/*      */     
/* 1395 */     while (iterator.hasNext()) {
/* 1396 */       WorldServer worldserver = iterator.next();
/* 1397 */       worldserver.hasPhysicsEvent = ((BlockPhysicsEvent.getHandlerList().getRegisteredListeners()).length > 0);
/* 1398 */       TileEntityHopper.skipHopperEvents = (worldserver.paperConfig.disableHopperMoveEvents || (InventoryMoveItemEvent.getHandlerList().getRegisteredListeners()).length == 0);
/*      */       
/* 1400 */       this.methodProfiler.a(() -> worldserver + " " + worldserver.getDimensionKey().a());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1411 */       this.methodProfiler.enter("tick");
/*      */ 
/*      */       
/*      */       try {
/* 1415 */         worldserver.timings.doTick.startTiming();
/* 1416 */         worldserver.doTick(booleansupplier);
/*      */         
/* 1418 */         worldserver.timings.doTick.stopTiming();
/*      */       }
/* 1420 */       catch (Throwable throwable) {
/*      */         CrashReport crashreport;
/*      */         
/*      */         try {
/* 1424 */           crashreport = CrashReport.a(throwable, "Exception ticking world");
/* 1425 */         } catch (Throwable t) {
/* 1426 */           if (throwable instanceof ThreadDeath) throw (ThreadDeath)throwable; 
/* 1427 */           throw new RuntimeException("Error generating crash report", t);
/*      */         } 
/*      */ 
/*      */         
/* 1431 */         worldserver.a(crashreport);
/* 1432 */         throw new ReportedException(crashreport);
/*      */       } 
/*      */       
/* 1435 */       this.methodProfiler.exit();
/* 1436 */       this.methodProfiler.exit();
/* 1437 */       worldserver.explosionDensityCache.clear();
/*      */     } 
/*      */     
/* 1440 */     this.methodProfiler.exitEnter("connection");
/* 1441 */     MinecraftTimings.connectionTimer.startTiming();
/* 1442 */     getServerConnection().c();
/* 1443 */     MinecraftTimings.connectionTimer.stopTiming();
/* 1444 */     this.methodProfiler.exitEnter("players");
/* 1445 */     MinecraftTimings.playerListTimer.startTiming();
/* 1446 */     this.playerList.tick();
/* 1447 */     MinecraftTimings.playerListTimer.stopTiming();
/* 1448 */     if (SharedConstants.d) {
/* 1449 */       GameTestHarnessTicker.a.b();
/*      */     }
/*      */     
/* 1452 */     this.methodProfiler.exitEnter("server gui refresh");
/*      */     
/* 1454 */     MinecraftTimings.tickablesTimer.startTiming();
/* 1455 */     for (int i = 0; i < this.tickables.size(); i++) {
/* 1456 */       ((Runnable)this.tickables.get(i)).run();
/*      */     }
/* 1458 */     MinecraftTimings.tickablesTimer.stopTiming();
/*      */     
/* 1460 */     this.methodProfiler.exit();
/*      */   }
/*      */   
/*      */   public boolean getAllowNether() {
/* 1464 */     return true;
/*      */   }
/*      */   
/*      */   public void b(Runnable runnable) {
/* 1468 */     this.tickables.add(runnable);
/*      */   }
/*      */   
/*      */   protected void b(String s) {
/* 1472 */     this.ai = s;
/*      */   }
/*      */   
/*      */   public File c(String s) {
/* 1476 */     return new File(B(), s);
/*      */   }
/*      */   
/*      */   public final WorldServer E() {
/* 1480 */     return this.worldServer.get(World.OVERWORLD);
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public WorldServer getWorldServer(ResourceKey<World> resourcekey) {
/* 1485 */     return this.worldServer.get(resourcekey);
/*      */   }
/*      */   
/*      */   public Set<ResourceKey<World>> F() {
/* 1489 */     return this.worldServer.keySet();
/*      */   }
/*      */   
/*      */   public Iterable<WorldServer> getWorlds() {
/* 1493 */     return this.worldServer.values();
/*      */   }
/*      */   
/*      */   public String getVersion() {
/* 1497 */     return SharedConstants.getGameVersion().getName();
/*      */   }
/*      */   
/*      */   public int getPlayerCount() {
/* 1501 */     return this.playerList.getPlayerCount();
/*      */   }
/*      */   
/*      */   public int getMaxPlayers() {
/* 1505 */     return this.playerList.getMaxPlayers();
/*      */   }
/*      */   
/*      */   public String[] getPlayers() {
/* 1509 */     return this.playerList.e();
/*      */   }
/*      */   
/*      */   public String getServerModName() {
/* 1513 */     return "Tuinity";
/*      */   }
/*      */   
/*      */   public CrashReport b(CrashReport crashreport) {
/* 1517 */     if (this.playerList != null) {
/* 1518 */       crashreport.g().a("Player Count", () -> this.playerList.getPlayerCount() + " / " + this.playerList.getMaxPlayers() + "; " + this.playerList.getPlayers());
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1523 */     crashreport.g().a("Data Packs", () -> {
/*      */           StringBuilder stringbuilder = new StringBuilder();
/*      */           
/*      */           Iterator<ResourcePackLoader> iterator = this.resourcePackRepository.e().iterator();
/*      */           
/*      */           while (iterator.hasNext()) {
/*      */             ResourcePackLoader resourcepackloader = iterator.next();
/*      */             
/*      */             if (stringbuilder.length() > 0) {
/*      */               stringbuilder.append(", ");
/*      */             }
/*      */             
/*      */             stringbuilder.append(resourcepackloader.e());
/*      */             if (!resourcepackloader.c().a()) {
/*      */               stringbuilder.append(" (incompatible)");
/*      */             }
/*      */           } 
/*      */           return stringbuilder.toString();
/*      */         });
/* 1542 */     if (this.ai != null) {
/* 1543 */       crashreport.g().a("Server Id", () -> this.ai);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1548 */     return crashreport;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendMessage(IChatBaseComponent ichatbasecomponent, UUID uuid) {
/* 1555 */     LOGGER.info(CraftChatMessage.fromComponent(ichatbasecomponent));
/*      */   }
/*      */   
/*      */   public KeyPair getKeyPair() {
/* 1559 */     return this.H;
/*      */   }
/*      */   
/*      */   public int getPort() {
/* 1563 */     return this.serverPort;
/*      */   }
/*      */   
/*      */   public void setPort(int i) {
/* 1567 */     this.serverPort = i;
/*      */   }
/*      */   
/*      */   public String getSinglePlayerName() {
/* 1571 */     return this.I;
/*      */   }
/*      */   
/*      */   public void d(String s) {
/* 1575 */     this.I = s;
/*      */   }
/*      */   
/*      */   public boolean isEmbeddedServer() {
/* 1579 */     return (this.I != null);
/*      */   }
/*      */   
/*      */   public void a(KeyPair keypair) {
/* 1583 */     this.H = keypair;
/*      */   }
/*      */ 
/*      */   
/*      */   public void a(WorldServer world, EnumDifficulty enumdifficulty, boolean flag) {
/* 1588 */     WorldDataServer worldData = world.worldDataServer;
/* 1589 */     if (flag || !worldData.isDifficultyLocked()) {
/* 1590 */       worldData.setDifficulty(worldData.isHardcore() ? EnumDifficulty.HARD : enumdifficulty);
/* 1591 */       world.setSpawnFlags((worldData.getDifficulty() != EnumDifficulty.PEACEFUL && (((DedicatedServer)this).propertyManager.getProperties()).spawnMonsters), getSpawnAnimals());
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public final int applyTrackingRangeScale(int value) {
/* 1597 */     return b(value);
/*      */   } public int b(int i) {
/* 1599 */     return i;
/*      */   }
/*      */   
/*      */   private void bb() {
/* 1603 */     Iterator<WorldServer> iterator = getWorlds().iterator();
/*      */     
/* 1605 */     while (iterator.hasNext()) {
/* 1606 */       WorldServer worldserver = iterator.next();
/*      */       
/* 1608 */       worldserver.setSpawnFlags(getSpawnMonsters(), getSpawnAnimals());
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void b(boolean flag) {
/* 1614 */     this.saveData.d(flag);
/* 1615 */     getPlayerList().getPlayers().forEach(this::a);
/*      */   }
/*      */   
/*      */   private void a(EntityPlayer entityplayer) {
/* 1619 */     WorldData worlddata = entityplayer.getWorldServer().getWorldData();
/*      */     
/* 1621 */     entityplayer.playerConnection.sendPacket(new PacketPlayOutServerDifficulty(worlddata.getDifficulty(), worlddata.isDifficultyLocked()));
/*      */   }
/*      */   
/*      */   protected boolean getSpawnMonsters() {
/* 1625 */     return (this.saveData.getDifficulty() != EnumDifficulty.PEACEFUL);
/*      */   }
/*      */   
/*      */   public boolean isDemoMode() {
/* 1629 */     return this.demoMode;
/*      */   }
/*      */   
/*      */   public void c(boolean flag) {
/* 1633 */     this.demoMode = flag;
/*      */   }
/*      */   
/*      */   public String getResourcePack() {
/* 1637 */     return this.K;
/*      */   }
/*      */   
/*      */   public String getResourcePackHash() {
/* 1641 */     return this.L;
/*      */   }
/*      */   
/*      */   public void setResourcePack(String s, String s1) {
/* 1645 */     this.K = s;
/* 1646 */     this.L = s1;
/*      */   }
/*      */ 
/*      */   
/*      */   public void a(MojangStatisticsGenerator mojangstatisticsgenerator) {
/* 1651 */     mojangstatisticsgenerator.a("whitelist_enabled", Boolean.valueOf(false));
/* 1652 */     mojangstatisticsgenerator.a("whitelist_count", Integer.valueOf(0));
/* 1653 */     if (this.playerList != null) {
/* 1654 */       mojangstatisticsgenerator.a("players_current", Integer.valueOf(getPlayerCount()));
/* 1655 */       mojangstatisticsgenerator.a("players_max", Integer.valueOf(getMaxPlayers()));
/* 1656 */       mojangstatisticsgenerator.a("players_seen", Integer.valueOf((this.worldNBTStorage.getSeenPlayers()).length));
/*      */     } 
/*      */     
/* 1659 */     mojangstatisticsgenerator.a("uses_auth", Boolean.valueOf(this.onlineMode));
/* 1660 */     mojangstatisticsgenerator.a("gui_state", ag() ? "enabled" : "disabled");
/* 1661 */     mojangstatisticsgenerator.a("run_time", Long.valueOf((SystemUtils.getMonotonicMillis() - mojangstatisticsgenerator.g()) / 60L * 1000L));
/* 1662 */     mojangstatisticsgenerator.a("avg_tick_ms", Integer.valueOf((int)(MathHelper.a(this.h) * 1.0E-6D)));
/* 1663 */     int i = 0;
/* 1664 */     Iterator<WorldServer> iterator = getWorlds().iterator();
/*      */     
/* 1666 */     while (iterator.hasNext()) {
/* 1667 */       WorldServer worldserver = iterator.next();
/*      */       
/* 1669 */       if (worldserver != null) {
/* 1670 */         mojangstatisticsgenerator.a("world[" + i + "][dimension]", worldserver.getDimensionKey().a());
/* 1671 */         mojangstatisticsgenerator.a("world[" + i + "][mode]", this.saveData.getGameType());
/* 1672 */         mojangstatisticsgenerator.a("world[" + i + "][difficulty]", worldserver.getDifficulty());
/* 1673 */         mojangstatisticsgenerator.a("world[" + i + "][hardcore]", Boolean.valueOf(this.saveData.isHardcore()));
/* 1674 */         mojangstatisticsgenerator.a("world[" + i + "][height]", Integer.valueOf(this.F));
/* 1675 */         mojangstatisticsgenerator.a("world[" + i + "][chunks_loaded]", Integer.valueOf(worldserver.getChunkProvider().h()));
/* 1676 */         i++;
/*      */       } 
/*      */     } 
/*      */     
/* 1680 */     mojangstatisticsgenerator.a("worlds", Integer.valueOf(i));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getOnlineMode() {
/* 1688 */     return this.onlineMode;
/*      */   }
/*      */   
/*      */   public void setOnlineMode(boolean flag) {
/* 1692 */     this.onlineMode = flag;
/*      */   }
/*      */   
/*      */   public boolean V() {
/* 1696 */     return this.B;
/*      */   }
/*      */   
/*      */   public void e(boolean flag) {
/* 1700 */     this.B = flag;
/*      */   }
/*      */   
/*      */   public boolean getSpawnAnimals() {
/* 1704 */     return true;
/*      */   }
/*      */   
/*      */   public boolean getSpawnNPCs() {
/* 1708 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getPVP() {
/* 1714 */     return this.pvpMode;
/*      */   }
/*      */   
/*      */   public void setPVP(boolean flag) {
/* 1718 */     this.pvpMode = flag;
/*      */   }
/*      */   
/*      */   public boolean getAllowFlight() {
/* 1722 */     return this.allowFlight;
/*      */   }
/*      */   
/*      */   public void setAllowFlight(boolean flag) {
/* 1726 */     this.allowFlight = flag;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public String getMotd() {
/* 1732 */     return this.motd;
/*      */   }
/*      */   
/*      */   public void setMotd(String s) {
/* 1736 */     this.motd = s;
/*      */   }
/*      */   
/*      */   public int getMaxBuildHeight() {
/* 1740 */     return this.F;
/*      */   }
/*      */   
/*      */   public void c(int i) {
/* 1744 */     this.F = i;
/*      */   }
/*      */   
/*      */   public boolean isStopped() {
/* 1748 */     return this.isStopped;
/*      */   }
/*      */   
/*      */   public PlayerList getPlayerList() {
/* 1752 */     return this.playerList;
/*      */   }
/*      */   
/*      */   public void a(PlayerList playerlist) {
/* 1756 */     this.playerList = playerlist;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(EnumGamemode enumgamemode) {
/* 1762 */     this.saveData.setGameType(enumgamemode);
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public ServerConnection getServerConnection() {
/* 1767 */     return (this.serverConnection == null) ? (this.serverConnection = new ServerConnection(this)) : this.serverConnection;
/*      */   }
/*      */   
/*      */   public boolean ag() {
/* 1771 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int ah() {
/* 1777 */     return this.ticks;
/*      */   }
/*      */   
/*      */   public int getSpawnProtection() {
/* 1781 */     return 16;
/*      */   }
/*      */   
/*      */   public boolean a(WorldServer worldserver, BlockPosition blockposition, EntityHuman entityhuman) {
/* 1785 */     return false;
/*      */   }
/*      */   
/*      */   public void setForceGamemode(boolean flag) {
/* 1789 */     this.P = flag;
/*      */   }
/*      */   
/*      */   public boolean getForceGamemode() {
/* 1793 */     return this.P;
/*      */   }
/*      */   
/*      */   public boolean al() {
/* 1797 */     return true;
/*      */   }
/*      */   
/*      */   public int getIdleTimeout() {
/* 1801 */     return this.G;
/*      */   }
/*      */   
/*      */   public void setIdleTimeout(int i) {
/* 1805 */     this.G = i;
/*      */   }
/*      */   
/*      */   public MinecraftSessionService getMinecraftSessionService() {
/* 1809 */     return this.minecraftSessionService;
/*      */   }
/*      */   
/*      */   public GameProfileRepository getGameProfileRepository() {
/* 1813 */     return this.gameProfileRepository;
/*      */   }
/*      */   
/*      */   public UserCache getUserCache() {
/* 1817 */     return this.userCache;
/*      */   }
/*      */   
/*      */   public ServerPing getServerPing() {
/* 1821 */     return this.serverPing;
/*      */   }
/*      */   
/*      */   public void invalidatePingSample() {
/* 1825 */     this.T = 0L;
/*      */   }
/*      */   
/*      */   public int at() {
/* 1829 */     return 29999984;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isNotMainThread() {
/* 1834 */     return (super.isNotMainThread() && !isStopped());
/*      */   }
/*      */ 
/*      */   
/*      */   public Thread getThread() {
/* 1839 */     return this.serverThread;
/*      */   }
/*      */   
/*      */   public int aw() {
/* 1843 */     return 256;
/*      */   }
/*      */   
/*      */   public long ax() {
/* 1847 */     return this.nextTick;
/*      */   }
/*      */   
/*      */   public DataFixer getDataFixer() {
/* 1851 */     return this.dataConverterManager;
/*      */   }
/*      */   
/*      */   public int a(@Nullable WorldServer worldserver) {
/* 1855 */     return (worldserver != null) ? worldserver.getGameRules().getInt(GameRules.SPAWN_RADIUS) : 10;
/*      */   }
/*      */   
/*      */   public AdvancementDataWorld getAdvancementData() {
/* 1859 */     return this.dataPackResources.g();
/*      */   }
/*      */   
/*      */   public CustomFunctionData getFunctionData() {
/* 1863 */     return this.customFunctionData;
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
/*      */   public CompletableFuture<Void> a(Collection<String> collection) {
/* 1875 */     CompletableFuture<Void> completablefuture = CompletableFuture.supplyAsync(() -> { Stream<String> stream = collection.stream(); ResourcePackRepository resourcepackrepository = this.resourcePackRepository; this.resourcePackRepository.getClass(); Objects.requireNonNull(resourcepackrepository); return (ImmutableList)stream.map(resourcepackrepository::a).filter(Objects::nonNull).map(ResourcePackLoader::d).collect(ImmutableList.toImmutableList()); }this).thenCompose(immutablelist -> DataPackResources.a((List<IResourcePack>)immutablelist, j() ? CommandDispatcher.ServerType.DEDICATED : CommandDispatcher.ServerType.INTEGRATED, h(), this.executorService, this)).thenAcceptAsync(datapackresources -> {
/*      */           this.dataPackResources.close();
/*      */           this.dataPackResources = datapackresources;
/*      */           this.server.syncCommands();
/*      */           this.resourcePackRepository.a(collection);
/*      */           this.saveData.a(a(this.resourcePackRepository));
/*      */           datapackresources.i();
/*      */           if (Thread.currentThread() != this.serverThread) {
/*      */             return;
/*      */           }
/*      */           getPlayerList().reload();
/*      */           this.customFunctionData.a(this.dataPackResources.a());
/*      */           this.ak.a(this.dataPackResources.h());
/*      */           CraftBlockData.reloadCache();
/*      */         }this);
/* 1890 */     if (isMainThread()) {
/* 1891 */       Objects.requireNonNull(completablefuture); awaitTasks(completablefuture::isDone);
/*      */     } 
/*      */     
/* 1894 */     return completablefuture;
/*      */   }
/*      */   
/*      */   public static DataPackConfiguration a(ResourcePackRepository resourcepackrepository, DataPackConfiguration datapackconfiguration, boolean flag) {
/* 1898 */     resourcepackrepository.a();
/* 1899 */     if (flag) {
/* 1900 */       resourcepackrepository.a(Collections.singleton("vanilla"));
/* 1901 */       return new DataPackConfiguration((List<String>)ImmutableList.of("vanilla"), (List<String>)ImmutableList.of());
/*      */     } 
/* 1903 */     Set<String> set = Sets.newLinkedHashSet();
/* 1904 */     Iterator<String> iterator = datapackconfiguration.a().iterator();
/*      */     
/* 1906 */     while (iterator.hasNext()) {
/* 1907 */       String s = iterator.next();
/*      */       
/* 1909 */       if (resourcepackrepository.b(s)) {
/* 1910 */         set.add(s); continue;
/*      */       } 
/* 1912 */       LOGGER.warn("Missing data pack {}", s);
/*      */     } 
/*      */ 
/*      */     
/* 1916 */     iterator = (Iterator)resourcepackrepository.c().iterator();
/*      */     
/* 1918 */     while (iterator.hasNext()) {
/* 1919 */       ResourcePackLoader resourcepackloader = (ResourcePackLoader)iterator.next();
/* 1920 */       String s1 = resourcepackloader.e();
/*      */       
/* 1922 */       if (!datapackconfiguration.b().contains(s1) && !set.contains(s1)) {
/* 1923 */         LOGGER.info("Found new data pack {}, loading it automatically", s1);
/* 1924 */         set.add(s1);
/*      */       } 
/*      */     } 
/*      */     
/* 1928 */     if (set.isEmpty()) {
/* 1929 */       LOGGER.info("No datapacks selected, forcing vanilla");
/* 1930 */       set.add("vanilla");
/*      */     } 
/*      */     
/* 1933 */     resourcepackrepository.a(set);
/* 1934 */     return a(resourcepackrepository);
/*      */   }
/*      */ 
/*      */   
/*      */   private static DataPackConfiguration a(ResourcePackRepository resourcepackrepository) {
/* 1939 */     Collection<String> collection = resourcepackrepository.d();
/* 1940 */     ImmutableList immutableList = ImmutableList.copyOf(collection);
/*      */ 
/*      */     
/* 1943 */     List<String> list1 = (List<String>)resourcepackrepository.b().stream().filter(s -> !collection.contains(s)).collect(ImmutableList.toImmutableList());
/*      */     
/* 1945 */     return new DataPackConfiguration((List<String>)immutableList, list1);
/*      */   }
/*      */   
/*      */   public void a(CommandListenerWrapper commandlistenerwrapper) {
/* 1949 */     if (aM()) {
/* 1950 */       PlayerList playerlist = commandlistenerwrapper.getServer().getPlayerList();
/* 1951 */       WhiteList whitelist = playerlist.getWhitelist();
/* 1952 */       List<EntityPlayer> list = Lists.newArrayList(playerlist.getPlayers());
/* 1953 */       Iterator<EntityPlayer> iterator = list.iterator();
/*      */       
/* 1955 */       while (iterator.hasNext()) {
/* 1956 */         EntityPlayer entityplayer = iterator.next();
/*      */         
/* 1958 */         if (!whitelist.isWhitelisted(entityplayer.getProfile())) {
/* 1959 */           entityplayer.playerConnection.disconnect(new ChatMessage("multiplayer.disconnect.not_whitelisted"));
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public ResourcePackRepository getResourcePackRepository() {
/* 1967 */     return this.resourcePackRepository;
/*      */   }
/*      */   
/*      */   public CommandDispatcher getCommandDispatcher() {
/* 1971 */     return this.dataPackResources.f();
/*      */   }
/*      */   
/*      */   public CommandListenerWrapper getServerCommandListener() {
/* 1975 */     WorldServer worldserver = E();
/*      */     
/* 1977 */     return new CommandListenerWrapper(this, (worldserver == null) ? Vec3D.ORIGIN : Vec3D.b(worldserver.getSpawn()), Vec2F.a, worldserver, 4, "Server", new ChatComponentText("Server"), this, (Entity)null);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean shouldSendSuccess() {
/* 1982 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean shouldSendFailure() {
/* 1987 */     return true;
/*      */   }
/*      */   
/*      */   public CraftingManager getCraftingManager() {
/* 1991 */     return this.dataPackResources.e();
/*      */   }
/*      */   
/*      */   public ITagRegistry getTagRegistry() {
/* 1995 */     return this.dataPackResources.d();
/*      */   }
/*      */   
/*      */   public ScoreboardServer getScoreboard() {
/* 1999 */     return this.scoreboardServer;
/*      */   }
/*      */   
/*      */   public PersistentCommandStorage aH() {
/* 2003 */     if (this.persistentCommandStorage == null) {
/* 2004 */       throw new NullPointerException("Called before server init");
/*      */     }
/* 2006 */     return this.persistentCommandStorage;
/*      */   }
/*      */ 
/*      */   
/*      */   public LootTableRegistry getLootTableRegistry() {
/* 2011 */     return this.dataPackResources.c();
/*      */   }
/*      */   
/*      */   public LootPredicateManager getLootPredicateManager() {
/* 2015 */     return this.dataPackResources.b();
/*      */   }
/*      */   
/*      */   public GameRules getGameRules() {
/* 2019 */     return E().getGameRules();
/*      */   }
/*      */   
/*      */   public BossBattleCustomData getBossBattleCustomData() {
/* 2023 */     return this.bossBattleCustomData;
/*      */   }
/*      */   
/*      */   public boolean aM() {
/* 2027 */     return this.af;
/*      */   }
/*      */   
/*      */   public void i(boolean flag) {
/* 2031 */     this.af = flag;
/*      */   }
/*      */   
/*      */   public float aN() {
/* 2035 */     return this.ag;
/*      */   }
/*      */   
/*      */   public int b(GameProfile gameprofile) {
/* 2039 */     if (getPlayerList().isOp(gameprofile)) {
/* 2040 */       OpListEntry oplistentry = getPlayerList().getOPs().get(gameprofile);
/*      */       
/* 2042 */       return (oplistentry != null) ? oplistentry.a() : (a(gameprofile) ? 4 : (isEmbeddedServer() ? (getPlayerList().u() ? 4 : 0) : g()));
/*      */     } 
/* 2044 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public GameProfilerFiller getMethodProfiler() {
/* 2049 */     return this.methodProfiler;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(Path java_nio_file_path) throws IOException {
/* 2055 */     Path java_nio_file_path1 = java_nio_file_path.resolve("levels");
/* 2056 */     Iterator<Map.Entry<ResourceKey<World>, WorldServer>> iterator = this.worldServer.entrySet().iterator();
/*      */     
/* 2058 */     while (iterator.hasNext()) {
/* 2059 */       Map.Entry<ResourceKey<World>, WorldServer> entry = iterator.next();
/* 2060 */       MinecraftKey minecraftkey = ((ResourceKey)entry.getKey()).a();
/* 2061 */       Path java_nio_file_path2 = java_nio_file_path1.resolve(minecraftkey.getNamespace()).resolve(minecraftkey.getKey());
/*      */       
/* 2063 */       Files.createDirectories(java_nio_file_path2, (FileAttribute<?>[])new FileAttribute[0]);
/* 2064 */       ((WorldServer)entry.getValue()).a(java_nio_file_path2);
/*      */     } 
/*      */     
/* 2067 */     d(java_nio_file_path.resolve("gamerules.txt"));
/* 2068 */     e(java_nio_file_path.resolve("classpath.txt"));
/* 2069 */     c(java_nio_file_path.resolve("example_crash.txt"));
/* 2070 */     b(java_nio_file_path.resolve("stats.txt"));
/* 2071 */     f(java_nio_file_path.resolve("threads.txt"));
/*      */   }
/*      */   
/*      */   private void b(Path java_nio_file_path) throws IOException {
/* 2075 */     BufferedWriter bufferedwriter = Files.newBufferedWriter(java_nio_file_path, new java.nio.file.OpenOption[0]);
/* 2076 */     Throwable throwable = null;
/*      */     
/*      */     try {
/* 2079 */       bufferedwriter.write(String.format("pending_tasks: %d\n", new Object[] { Integer.valueOf(bh()) }));
/* 2080 */       bufferedwriter.write(String.format("average_tick_time: %f\n", new Object[] { Float.valueOf(aN()) }));
/* 2081 */       bufferedwriter.write(String.format("tick_times: %s\n", new Object[] { Arrays.toString(this.h) }));
/* 2082 */       bufferedwriter.write(String.format("queue: %s\n", new Object[] { SystemUtils.f() }));
/* 2083 */     } catch (Throwable throwable1) {
/* 2084 */       throwable = throwable1;
/* 2085 */       throw throwable1;
/*      */     } finally {
/* 2087 */       if (bufferedwriter != null) {
/* 2088 */         if (throwable != null) {
/*      */           try {
/* 2090 */             bufferedwriter.close();
/* 2091 */           } catch (Throwable throwable2) {
/* 2092 */             throwable.addSuppressed(throwable2);
/*      */           } 
/*      */         } else {
/* 2095 */           bufferedwriter.close();
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void c(Path java_nio_file_path) throws IOException {
/* 2104 */     CrashReport crashreport = new CrashReport("Server dump", new Exception("dummy"));
/*      */     
/* 2106 */     b(crashreport);
/* 2107 */     BufferedWriter bufferedwriter = Files.newBufferedWriter(java_nio_file_path, new java.nio.file.OpenOption[0]);
/* 2108 */     Throwable throwable = null;
/*      */     
/*      */     try {
/* 2111 */       bufferedwriter.write(crashreport.e());
/* 2112 */     } catch (Throwable throwable1) {
/* 2113 */       throwable = throwable1;
/* 2114 */       throw throwable1;
/*      */     } finally {
/* 2116 */       if (bufferedwriter != null) {
/* 2117 */         if (throwable != null) {
/*      */           try {
/* 2119 */             bufferedwriter.close();
/* 2120 */           } catch (Throwable throwable2) {
/* 2121 */             throwable.addSuppressed(throwable2);
/*      */           } 
/*      */         } else {
/* 2124 */           bufferedwriter.close();
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void d(Path java_nio_file_path) throws IOException {
/* 2133 */     BufferedWriter bufferedwriter = Files.newBufferedWriter(java_nio_file_path, new java.nio.file.OpenOption[0]);
/* 2134 */     Throwable throwable = null;
/*      */     
/*      */     try {
/* 2137 */       final List<String> list = Lists.newArrayList();
/* 2138 */       final GameRules gamerules = getGameRules();
/*      */       
/* 2140 */       GameRules.a(new GameRules.GameRuleVisitor()
/*      */           {
/*      */             public <T extends GameRules.GameRuleValue<T>> void a(GameRules.GameRuleKey<T> gamerules_gamerulekey, GameRules.GameRuleDefinition<T> gamerules_gameruledefinition) {
/* 2143 */               list.add(String.format("%s=%s\n", new Object[] { gamerules_gamerulekey.a(), this.val$gamerules.<T>get(gamerules_gamerulekey).toString() }));
/*      */             }
/*      */           });
/* 2146 */       Iterator<String> iterator = list.iterator();
/*      */       
/* 2148 */       while (iterator.hasNext()) {
/* 2149 */         String s = iterator.next();
/*      */         
/* 2151 */         bufferedwriter.write(s);
/*      */       } 
/* 2153 */     } catch (Throwable throwable1) {
/* 2154 */       throwable = throwable1;
/* 2155 */       throw throwable1;
/*      */     } finally {
/* 2157 */       if (bufferedwriter != null) {
/* 2158 */         if (throwable != null) {
/*      */           try {
/* 2160 */             bufferedwriter.close();
/* 2161 */           } catch (Throwable throwable2) {
/* 2162 */             throwable.addSuppressed(throwable2);
/*      */           } 
/*      */         } else {
/* 2165 */           bufferedwriter.close();
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void e(Path java_nio_file_path) throws IOException {
/* 2174 */     BufferedWriter bufferedwriter = Files.newBufferedWriter(java_nio_file_path, new java.nio.file.OpenOption[0]);
/* 2175 */     Throwable throwable = null;
/*      */     
/*      */     try {
/* 2178 */       String s = System.getProperty("java.class.path");
/* 2179 */       String s1 = System.getProperty("path.separator");
/* 2180 */       Iterator<String> iterator = Splitter.on(s1).split(s).iterator();
/*      */       
/* 2182 */       while (iterator.hasNext()) {
/* 2183 */         String s2 = iterator.next();
/*      */         
/* 2185 */         bufferedwriter.write(s2);
/* 2186 */         bufferedwriter.write("\n");
/*      */       } 
/* 2188 */     } catch (Throwable throwable1) {
/* 2189 */       throwable = throwable1;
/* 2190 */       throw throwable1;
/*      */     } finally {
/* 2192 */       if (bufferedwriter != null) {
/* 2193 */         if (throwable != null) {
/*      */           try {
/* 2195 */             bufferedwriter.close();
/* 2196 */           } catch (Throwable throwable2) {
/* 2197 */             throwable.addSuppressed(throwable2);
/*      */           } 
/*      */         } else {
/* 2200 */           bufferedwriter.close();
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void f(Path java_nio_file_path) throws IOException {
/* 2209 */     ThreadMXBean threadmxbean = ManagementFactory.getThreadMXBean();
/* 2210 */     ThreadInfo[] athreadinfo = threadmxbean.dumpAllThreads(true, true);
/*      */     
/* 2212 */     Arrays.sort(athreadinfo, Comparator.comparing(ThreadInfo::getThreadName));
/* 2213 */     BufferedWriter bufferedwriter = Files.newBufferedWriter(java_nio_file_path, new java.nio.file.OpenOption[0]);
/* 2214 */     Throwable throwable = null;
/*      */     
/*      */     try {
/* 2217 */       ThreadInfo[] athreadinfo1 = athreadinfo;
/* 2218 */       int i = athreadinfo.length;
/*      */       
/* 2220 */       for (int j = 0; j < i; j++) {
/* 2221 */         ThreadInfo threadinfo = athreadinfo1[j];
/*      */         
/* 2223 */         bufferedwriter.write(threadinfo.toString());
/* 2224 */         bufferedwriter.write(10);
/*      */       } 
/* 2226 */     } catch (Throwable throwable1) {
/* 2227 */       throwable = throwable1;
/* 2228 */       throw throwable1;
/*      */     } finally {
/* 2230 */       if (bufferedwriter != null) {
/* 2231 */         if (throwable != null) {
/*      */           try {
/* 2233 */             bufferedwriter.close();
/* 2234 */           } catch (Throwable throwable2) {
/* 2235 */             throwable.addSuppressed(throwable2);
/*      */           } 
/*      */         } else {
/* 2238 */           bufferedwriter.close();
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isMainThread() {
/* 2249 */     return super.isMainThread();
/*      */   }
/*      */   
/*      */   public boolean isDebugging() {
/* 2253 */     return false;
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public static MinecraftServer getServer() {
/* 2258 */     return SERVER;
/*      */   }
/*      */ 
/*      */   
/*      */   private void a(@Nullable GameProfilerTick gameprofilertick) {
/* 2263 */     if (this.O) {
/* 2264 */       this.O = false;
/* 2265 */       this.m.c();
/*      */     } 
/*      */     
/* 2268 */     this.methodProfiler = GameProfilerTick.a(this.m.d(), gameprofilertick);
/*      */   }
/*      */   
/*      */   private void b(@Nullable GameProfilerTick gameprofilertick) {
/* 2272 */     if (gameprofilertick != null) {
/* 2273 */       gameprofilertick.b();
/*      */     }
/*      */     
/* 2276 */     this.methodProfiler = this.m.d();
/*      */   }
/*      */   
/*      */   public boolean aR() {
/* 2280 */     return this.m.a();
/*      */   }
/*      */   
/*      */   public void aS() {
/* 2284 */     this.O = true;
/*      */   }
/*      */   
/*      */   public MethodProfilerResults aT() {
/* 2288 */     MethodProfilerResults methodprofilerresults = this.m.e();
/*      */     
/* 2290 */     this.m.b();
/* 2291 */     return methodprofilerresults;
/*      */   }
/*      */   
/*      */   public Path a(SavedFile savedfile) {
/* 2295 */     return this.convertable.getWorldFolder(savedfile);
/*      */   }
/*      */   
/*      */   public boolean isSyncChunkWrites() {
/* 2299 */     return true;
/*      */   }
/*      */   
/*      */   public DefinedStructureManager getDefinedStructureManager() {
/* 2303 */     return this.ak;
/*      */   }
/*      */   
/*      */   public SaveData getSaveData() {
/* 2307 */     return this.saveData;
/*      */   }
/*      */   
/*      */   public IRegistryCustom getCustomRegistry() {
/* 2311 */     return this.customRegistry;
/*      */   } protected abstract boolean init() throws IOException; public abstract int g(); public abstract int h(); public abstract boolean i(); public abstract Optional<String> getModded(); public abstract boolean j(); public abstract int k(); public abstract boolean l();
/*      */   public abstract boolean getEnableCommandBlock();
/*      */   public abstract boolean n();
/*      */   public abstract boolean a(EnumGamemode paramEnumGamemode, boolean paramBoolean, int paramInt);
/*      */   public abstract boolean a(GameProfile paramGameProfile);
/*      */   public static class TickTimes { private final long[] times;
/*      */     public TickTimes(int length) {
/* 2319 */       this.times = new long[length];
/*      */     }
/*      */     
/*      */     void add(int index, long time) {
/* 2323 */       this.times[index % this.times.length] = time;
/*      */     }
/*      */     
/*      */     public long[] getTimes() {
/* 2327 */       return (long[])this.times.clone();
/*      */     }
/*      */     
/*      */     public double getAverage() {
/* 2331 */       long total = 0L;
/* 2332 */       for (long value : this.times) {
/* 2333 */         total += value;
/*      */       }
/* 2335 */       return total / this.times.length * 1.0E-6D;
/*      */     } }
/*      */ 
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MinecraftServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */