/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.mojang.datafixers.DataFixer;
/*     */ import com.mojang.serialization.DataResult;
/*     */ import com.mojang.serialization.Decoder;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import com.mojang.serialization.DynamicLike;
/*     */ import com.mojang.serialization.Lifecycle;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.weather.ThunderChangeEvent;
/*     */ import org.bukkit.event.weather.WeatherChangeEvent;
/*     */ 
/*     */ public class WorldDataServer implements IWorldDataServer, SaveData {
/*  24 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   public WorldSettings b;
/*     */   private final GeneratorSettings c;
/*     */   private final Lifecycle d;
/*     */   private int e;
/*     */   private int f;
/*     */   private int g;
/*     */   private float h;
/*     */   private long i;
/*     */   private long j;
/*     */   @Nullable
/*     */   private final DataFixer k;
/*     */   private final int l;
/*     */   private boolean m;
/*     */   @Nullable
/*     */   private NBTTagCompound n;
/*     */   private final int o;
/*     */   private int clearWeatherTime;
/*     */   private boolean raining;
/*     */   private int rainTime;
/*     */   private boolean thundering;
/*     */   private int thunderTime;
/*     */   private boolean u;
/*     */   private boolean v;
/*     */   private WorldBorder.c w;
/*     */   private NBTTagCompound x;
/*     */   @Nullable
/*     */   private NBTTagCompound customBossEvents;
/*     */   private int z;
/*     */   private int A;
/*     */   @Nullable
/*     */   private UUID B;
/*     */   private final Set<String> C;
/*     */   private boolean D;
/*     */   private final CustomFunctionCallbackTimerQueue<MinecraftServer> E;
/*     */   public WorldServer world;
/*     */   
/*     */   private WorldDataServer(@Nullable DataFixer datafixer, int i, @Nullable NBTTagCompound nbttagcompound, boolean flag, int j, int k, int l, float f, long i1, long j1, int k1, int l1, int i2, boolean flag1, int j2, boolean flag2, boolean flag3, boolean flag4, WorldBorder.c worldborder_c, int k2, int l2, @Nullable UUID uuid, LinkedHashSet<String> linkedhashset, CustomFunctionCallbackTimerQueue<MinecraftServer> customfunctioncallbacktimerqueue, @Nullable NBTTagCompound nbttagcompound1, NBTTagCompound nbttagcompound2, WorldSettings worldsettings, GeneratorSettings generatorsettings, Lifecycle lifecycle) {
/*  62 */     this.k = datafixer;
/*  63 */     this.D = flag;
/*  64 */     this.e = j;
/*  65 */     this.f = k;
/*  66 */     this.g = l;
/*  67 */     this.h = f;
/*  68 */     this.i = i1;
/*  69 */     this.j = j1;
/*  70 */     this.o = k1;
/*  71 */     this.clearWeatherTime = l1;
/*  72 */     this.rainTime = i2;
/*  73 */     this.raining = flag1;
/*  74 */     this.thunderTime = j2;
/*  75 */     this.thundering = flag2;
/*  76 */     this.u = flag3;
/*  77 */     this.v = flag4;
/*  78 */     this.w = worldborder_c;
/*  79 */     this.z = k2;
/*  80 */     this.A = l2;
/*  81 */     this.B = uuid;
/*  82 */     this.C = linkedhashset;
/*  83 */     this.n = nbttagcompound;
/*  84 */     this.l = i;
/*  85 */     this.E = customfunctioncallbacktimerqueue;
/*  86 */     this.customBossEvents = nbttagcompound1;
/*  87 */     this.x = nbttagcompound2;
/*  88 */     this.b = worldsettings;
/*  89 */     this.c = generatorsettings;
/*  90 */     this.d = lifecycle;
/*     */   }
/*     */   
/*     */   public WorldDataServer(WorldSettings worldsettings, GeneratorSettings generatorsettings, Lifecycle lifecycle) {
/*  94 */     this((DataFixer)null, SharedConstants.getGameVersion().getWorldVersion(), (NBTTagCompound)null, false, 0, 0, 0, 0.0F, 0L, 0L, 19133, 0, 0, false, 0, false, false, false, WorldBorder.c, 0, 0, (UUID)null, Sets.newLinkedHashSet(), new CustomFunctionCallbackTimerQueue<>(CustomFunctionCallbackTimers.a), (NBTTagCompound)null, new NBTTagCompound(), worldsettings.h(), generatorsettings, lifecycle);
/*     */   }
/*     */   
/*     */   public static WorldDataServer a(Dynamic<NBTBase> dynamic, DataFixer datafixer, int i, @Nullable NBTTagCompound nbttagcompound, WorldSettings worldsettings, LevelVersion levelversion, GeneratorSettings generatorsettings, Lifecycle lifecycle) {
/*  98 */     long j = dynamic.get("Time").asLong(0L);
/*  99 */     NBTTagCompound nbttagcompound1 = dynamic.get("DragonFight").result().map(Dynamic::getValue).orElseGet(() -> (NBTBase)dynamic.get("DimensionData").get("1").get("DragonFight").orElseEmptyMap().getValue());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 104 */     return new WorldDataServer(datafixer, i, nbttagcompound, dynamic.get("WasModded").asBoolean(false), dynamic.get("SpawnX").asInt(0), dynamic.get("SpawnY").asInt(0), dynamic.get("SpawnZ").asInt(0), dynamic.get("SpawnAngle").asFloat(0.0F), j, dynamic.get("DayTime").asLong(j), levelversion.a(), dynamic.get("clearWeatherTime").asInt(0), dynamic.get("rainTime").asInt(0), dynamic.get("raining").asBoolean(false), dynamic.get("thunderTime").asInt(0), dynamic.get("thundering").asBoolean(false), dynamic.get("initialized").asBoolean(true), dynamic.get("DifficultyLocked").asBoolean(false), WorldBorder.c.a((DynamicLike<?>)dynamic, WorldBorder.c), dynamic.get("WanderingTraderSpawnDelay").asInt(0), dynamic.get("WanderingTraderSpawnChance").asInt(0), dynamic.get("WanderingTraderId").read((Decoder)MinecraftSerializableUUID.a).result().orElse(null), (LinkedHashSet<String>)dynamic.get("ServerBrands").asStream().flatMap(dynamic1 -> SystemUtils.a(dynamic1.asString().result()))
/*     */         
/* 106 */         .collect(Collectors.toCollection(Sets::newLinkedHashSet)), new CustomFunctionCallbackTimerQueue<>(CustomFunctionCallbackTimers.a, dynamic.get("ScheduledEvents").asStream()), (NBTTagCompound)dynamic.get("CustomBossEvents").orElseEmptyMap().getValue(), nbttagcompound1, worldsettings, generatorsettings, lifecycle);
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound a(IRegistryCustom iregistrycustom, @Nullable NBTTagCompound nbttagcompound) {
/* 111 */     J();
/* 112 */     if (nbttagcompound == null) {
/* 113 */       nbttagcompound = this.n;
/*     */     }
/*     */     
/* 116 */     NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*     */     
/* 118 */     a(iregistrycustom, nbttagcompound1, nbttagcompound);
/* 119 */     return nbttagcompound1;
/*     */   }
/*     */   
/*     */   private void a(IRegistryCustom iregistrycustom, NBTTagCompound nbttagcompound, @Nullable NBTTagCompound nbttagcompound1) {
/* 123 */     NBTTagList nbttaglist = new NBTTagList();
/*     */     
/* 125 */     Objects.requireNonNull(nbttaglist); this.C.stream().map(NBTTagString::a).forEach(nbttaglist::add);
/* 126 */     nbttagcompound.set("ServerBrands", nbttaglist);
/* 127 */     nbttagcompound.setBoolean("WasModded", this.D);
/* 128 */     NBTTagCompound nbttagcompound2 = new NBTTagCompound();
/*     */     
/* 130 */     nbttagcompound2.setString("Name", SharedConstants.getGameVersion().getName());
/* 131 */     nbttagcompound2.setInt("Id", SharedConstants.getGameVersion().getWorldVersion());
/* 132 */     nbttagcompound2.setBoolean("Snapshot", !SharedConstants.getGameVersion().isStable());
/* 133 */     nbttagcompound.set("Version", nbttagcompound2);
/* 134 */     nbttagcompound.setInt("DataVersion", SharedConstants.getGameVersion().getWorldVersion());
/* 135 */     RegistryWriteOps<NBTBase> registrywriteops = RegistryWriteOps.a(DynamicOpsNBT.a, iregistrycustom);
/* 136 */     DataResult<NBTBase> dataresult = GeneratorSettings.a.encodeStart(registrywriteops, this.c);
/* 137 */     Logger logger = LOGGER;
/*     */     
/* 139 */     logger.getClass();
/* 140 */     Objects.requireNonNull(logger); dataresult.resultOrPartial(SystemUtils.a("WorldGenSettings: ", logger::error)).ifPresent(nbtbase -> nbttagcompound.set("WorldGenSettings", nbtbase));
/*     */ 
/*     */     
/* 143 */     nbttagcompound.setInt("GameType", this.b.getGameType().getId());
/* 144 */     nbttagcompound.setInt("SpawnX", this.e);
/* 145 */     nbttagcompound.setInt("SpawnY", this.f);
/* 146 */     nbttagcompound.setInt("SpawnZ", this.g);
/* 147 */     nbttagcompound.setFloat("SpawnAngle", this.h);
/* 148 */     nbttagcompound.setLong("Time", this.i);
/* 149 */     nbttagcompound.setLong("DayTime", this.j);
/* 150 */     nbttagcompound.setLong("LastPlayed", SystemUtils.getTimeMillis());
/* 151 */     nbttagcompound.setString("LevelName", this.b.getLevelName());
/* 152 */     nbttagcompound.setInt("version", 19133);
/* 153 */     nbttagcompound.setInt("clearWeatherTime", this.clearWeatherTime);
/* 154 */     nbttagcompound.setInt("rainTime", this.rainTime);
/* 155 */     nbttagcompound.setBoolean("raining", this.raining);
/* 156 */     nbttagcompound.setInt("thunderTime", this.thunderTime);
/* 157 */     nbttagcompound.setBoolean("thundering", this.thundering);
/* 158 */     nbttagcompound.setBoolean("hardcore", this.b.isHardcore());
/* 159 */     nbttagcompound.setBoolean("allowCommands", this.b.e());
/* 160 */     nbttagcompound.setBoolean("initialized", this.u);
/* 161 */     this.w.a(nbttagcompound);
/* 162 */     nbttagcompound.setByte("Difficulty", (byte)this.b.getDifficulty().a());
/* 163 */     nbttagcompound.setBoolean("DifficultyLocked", this.v);
/* 164 */     nbttagcompound.set("GameRules", this.b.getGameRules().a());
/* 165 */     nbttagcompound.set("DragonFight", this.x);
/* 166 */     if (nbttagcompound1 != null) {
/* 167 */       nbttagcompound.set("Player", nbttagcompound1);
/*     */     }
/*     */     
/* 170 */     DataPackConfiguration.b.encodeStart(DynamicOpsNBT.a, this.b.g()).result().ifPresent(nbtbase -> nbttagcompound.set("DataPacks", nbtbase));
/*     */ 
/*     */     
/* 173 */     if (this.customBossEvents != null) {
/* 174 */       nbttagcompound.set("CustomBossEvents", this.customBossEvents);
/*     */     }
/*     */     
/* 177 */     nbttagcompound.set("ScheduledEvents", this.E.b());
/* 178 */     nbttagcompound.setInt("WanderingTraderSpawnDelay", this.z);
/* 179 */     nbttagcompound.setInt("WanderingTraderSpawnChance", this.A);
/* 180 */     if (this.B != null) {
/* 181 */       nbttagcompound.a("WanderingTraderId", this.B);
/*     */     }
/*     */     
/* 184 */     nbttagcompound.setString("Bukkit.Version", Bukkit.getName() + "/" + Bukkit.getVersion() + "/" + Bukkit.getBukkitVersion());
/*     */   }
/*     */ 
/*     */   
/*     */   public int a() {
/* 189 */     return this.e;
/*     */   }
/*     */ 
/*     */   
/*     */   public int b() {
/* 194 */     return this.f;
/*     */   }
/*     */ 
/*     */   
/*     */   public int c() {
/* 199 */     return this.g;
/*     */   }
/*     */ 
/*     */   
/*     */   public float d() {
/* 204 */     return this.h;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getTime() {
/* 209 */     return this.i;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getDayTime() {
/* 214 */     return this.j;
/*     */   }
/*     */   
/*     */   private void J() {
/* 218 */     if (!this.m && this.n != null) {
/* 219 */       if (this.l < SharedConstants.getGameVersion().getWorldVersion()) {
/* 220 */         if (this.k == null) {
/* 221 */           throw (NullPointerException)SystemUtils.c(new NullPointerException("Fixer Upper not set inside LevelData, and the player tag is not upgraded."));
/*     */         }
/*     */         
/* 224 */         this.n = GameProfileSerializer.a(this.k, DataFixTypes.PLAYER, this.n, this.l);
/*     */       } 
/*     */       
/* 227 */       this.m = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound y() {
/* 233 */     J();
/* 234 */     return this.n;
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(int i) {
/* 239 */     this.e = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public void c(int i) {
/* 244 */     this.f = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public void d(int i) {
/* 249 */     this.g = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(float f) {
/* 254 */     this.h = f;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTime(long i) {
/* 259 */     this.i = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDayTime(long i) {
/* 264 */     this.j = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSpawn(BlockPosition blockposition, float f) {
/* 269 */     this.e = blockposition.getX();
/* 270 */     this.f = blockposition.getY();
/* 271 */     this.g = blockposition.getZ();
/* 272 */     this.h = f;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 277 */     return this.b.getLevelName();
/*     */   }
/*     */ 
/*     */   
/*     */   public int z() {
/* 282 */     return this.o;
/*     */   }
/*     */ 
/*     */   
/*     */   public int h() {
/* 287 */     return this.clearWeatherTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(int i) {
/* 292 */     this.clearWeatherTime = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isThundering() {
/* 297 */     return this.thundering;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setThundering(boolean flag) {
/* 303 */     if (this.thundering == flag) {
/*     */       return;
/*     */     }
/*     */     
/* 307 */     World world = Bukkit.getWorld(getName());
/* 308 */     if (world != null) {
/* 309 */       ThunderChangeEvent thunder = new ThunderChangeEvent(world, flag);
/* 310 */       Bukkit.getServer().getPluginManager().callEvent((Event)thunder);
/* 311 */       if (thunder.isCancelled()) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */     
/* 316 */     this.thundering = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getThunderDuration() {
/* 321 */     return this.thunderTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setThunderDuration(int i) {
/* 326 */     this.thunderTime = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasStorm() {
/* 331 */     return this.raining;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStorm(boolean flag) {
/* 337 */     if (this.raining == flag) {
/*     */       return;
/*     */     }
/*     */     
/* 341 */     World world = Bukkit.getWorld(getName());
/* 342 */     if (world != null) {
/* 343 */       WeatherChangeEvent weather = new WeatherChangeEvent(world, flag);
/* 344 */       Bukkit.getServer().getPluginManager().callEvent((Event)weather);
/* 345 */       if (weather.isCancelled()) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */     
/* 350 */     this.raining = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWeatherDuration() {
/* 355 */     return this.rainTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWeatherDuration(int i) {
/* 360 */     this.rainTime = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumGamemode getGameType() {
/* 365 */     return this.b.getGameType();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGameType(EnumGamemode enumgamemode) {
/* 370 */     this.b = this.b.a(enumgamemode);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHardcore() {
/* 375 */     return this.b.isHardcore();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean o() {
/* 380 */     return this.b.e();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean p() {
/* 385 */     return this.u;
/*     */   }
/*     */ 
/*     */   
/*     */   public void c(boolean flag) {
/* 390 */     this.u = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public GameRules q() {
/* 395 */     return this.b.getGameRules();
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldBorder.c r() {
/* 400 */     return this.w;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(WorldBorder.c worldborder_c) {
/* 405 */     this.w = worldborder_c;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumDifficulty getDifficulty() {
/* 410 */     return this.b.getDifficulty();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDifficulty(EnumDifficulty enumdifficulty) {
/* 415 */     this.b = this.b.a(enumdifficulty);
/*     */     
/* 417 */     PacketPlayOutServerDifficulty packet = new PacketPlayOutServerDifficulty(getDifficulty(), isDifficultyLocked());
/* 418 */     for (EntityPlayer player : this.world.getPlayers()) {
/* 419 */       player.playerConnection.sendPacket(packet);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDifficultyLocked() {
/* 426 */     return this.v;
/*     */   }
/*     */ 
/*     */   
/*     */   public void d(boolean flag) {
/* 431 */     this.v = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public CustomFunctionCallbackTimerQueue<MinecraftServer> u() {
/* 436 */     return this.E;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(CrashReportSystemDetails crashreportsystemdetails) {
/* 441 */     super.a(crashreportsystemdetails);
/* 442 */     super.a(crashreportsystemdetails);
/*     */   }
/*     */ 
/*     */   
/*     */   public GeneratorSettings getGeneratorSettings() {
/* 447 */     return this.c;
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound C() {
/* 452 */     return this.x;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/* 457 */     this.x = nbttagcompound;
/*     */   }
/*     */ 
/*     */   
/*     */   public DataPackConfiguration D() {
/* 462 */     return this.b.g();
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(DataPackConfiguration datapackconfiguration) {
/* 467 */     this.b = this.b.a(datapackconfiguration);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public NBTTagCompound getCustomBossEvents() {
/* 473 */     return this.customBossEvents;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCustomBossEvents(@Nullable NBTTagCompound nbttagcompound) {
/* 478 */     this.customBossEvents = nbttagcompound;
/*     */   }
/*     */ 
/*     */   
/*     */   public int v() {
/* 483 */     return this.z;
/*     */   }
/*     */ 
/*     */   
/*     */   public void g(int i) {
/* 488 */     this.z = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public int w() {
/* 493 */     return this.A;
/*     */   }
/*     */ 
/*     */   
/*     */   public void h(int i) {
/* 498 */     this.A = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(UUID uuid) {
/* 503 */     this.B = uuid;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(String s, boolean flag) {
/* 508 */     this.C.add(s);
/* 509 */     this.D |= flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean F() {
/* 514 */     return this.D;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<String> G() {
/* 519 */     return (Set<String>)ImmutableSet.copyOf(this.C);
/*     */   }
/*     */ 
/*     */   
/*     */   public IWorldDataServer H() {
/* 524 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void checkName(String name) {
/* 529 */     if (!this.b.levelName.equals(name))
/* 530 */       this.b.levelName = name; 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldDataServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */