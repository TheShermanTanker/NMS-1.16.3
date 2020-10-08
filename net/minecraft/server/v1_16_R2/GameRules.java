/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.BoolArgumentType;
/*     */ import com.mojang.brigadier.arguments.IntegerArgumentType;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.serialization.DynamicLike;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Supplier;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class GameRules
/*     */ {
/*  24 */   private static final Logger LOGGER = LogManager.getLogger(); static {
/*  25 */     I = Maps.newTreeMap(Comparator.comparing(gamerules_gamerulekey -> gamerules_gamerulekey.a));
/*     */   }
/*     */   private static final Map<GameRuleKey<?>, GameRuleDefinition<?>> I;
/*  28 */   public static final GameRuleKey<GameRuleBoolean> DO_FIRE_TICK = a("doFireTick", GameRuleCategory.UPDATES, GameRuleBoolean.b(true));
/*  29 */   public static final GameRuleKey<GameRuleBoolean> MOB_GRIEFING = a("mobGriefing", GameRuleCategory.MOBS, GameRuleBoolean.b(true));
/*  30 */   public static final GameRuleKey<GameRuleBoolean> KEEP_INVENTORY = a("keepInventory", GameRuleCategory.PLAYER, GameRuleBoolean.b(false));
/*  31 */   public static final GameRuleKey<GameRuleBoolean> DO_MOB_SPAWNING = a("doMobSpawning", GameRuleCategory.SPAWNING, GameRuleBoolean.b(true));
/*  32 */   public static final GameRuleKey<GameRuleBoolean> DO_MOB_LOOT = a("doMobLoot", GameRuleCategory.DROPS, GameRuleBoolean.b(true));
/*  33 */   public static final GameRuleKey<GameRuleBoolean> DO_TILE_DROPS = a("doTileDrops", GameRuleCategory.DROPS, GameRuleBoolean.b(true));
/*  34 */   public static final GameRuleKey<GameRuleBoolean> DO_ENTITY_DROPS = a("doEntityDrops", GameRuleCategory.DROPS, GameRuleBoolean.b(true));
/*  35 */   public static final GameRuleKey<GameRuleBoolean> COMMAND_BLOCK_OUTPUT = a("commandBlockOutput", GameRuleCategory.CHAT, GameRuleBoolean.b(true));
/*  36 */   public static final GameRuleKey<GameRuleBoolean> NATURAL_REGENERATION = a("naturalRegeneration", GameRuleCategory.PLAYER, GameRuleBoolean.b(true));
/*  37 */   public static final GameRuleKey<GameRuleBoolean> DO_DAYLIGHT_CYCLE = a("doDaylightCycle", GameRuleCategory.UPDATES, GameRuleBoolean.b(true));
/*  38 */   public static final GameRuleKey<GameRuleBoolean> LOG_ADMIN_COMMANDS = a("logAdminCommands", GameRuleCategory.CHAT, GameRuleBoolean.b(true));
/*  39 */   public static final GameRuleKey<GameRuleBoolean> SHOW_DEATH_MESSAGES = a("showDeathMessages", GameRuleCategory.CHAT, GameRuleBoolean.b(true));
/*  40 */   public static final GameRuleKey<GameRuleInt> RANDOM_TICK_SPEED = a("randomTickSpeed", GameRuleCategory.UPDATES, GameRuleInt.b(3));
/*  41 */   public static final GameRuleKey<GameRuleBoolean> SEND_COMMAND_FEEDBACK = a("sendCommandFeedback", GameRuleCategory.CHAT, GameRuleBoolean.b(true)); public static final GameRuleKey<GameRuleBoolean> REDUCED_DEBUG_INFO; static {
/*  42 */     REDUCED_DEBUG_INFO = a("reducedDebugInfo", GameRuleCategory.MISC, GameRuleBoolean.b(false, (minecraftserver, gamerules_gameruleboolean) -> {
/*     */             int i = gamerules_gameruleboolean.a() ? 22 : 23;
/*     */             Iterator<EntityPlayer> iterator = minecraftserver.getPlayerList().getPlayers().iterator();
/*     */             while (iterator.hasNext()) {
/*     */               EntityPlayer entityplayer = iterator.next();
/*     */               entityplayer.playerConnection.sendPacket(new PacketPlayOutEntityStatus(entityplayer, (byte)i));
/*     */             } 
/*     */           }));
/*     */   }
/*     */ 
/*     */   
/*  53 */   public static final GameRuleKey<GameRuleBoolean> SPECTATORS_GENERATE_CHUNKS = a("spectatorsGenerateChunks", GameRuleCategory.PLAYER, GameRuleBoolean.b(true));
/*  54 */   public static final GameRuleKey<GameRuleInt> SPAWN_RADIUS = a("spawnRadius", GameRuleCategory.PLAYER, GameRuleInt.b(10));
/*  55 */   public static final GameRuleKey<GameRuleBoolean> DISABLE_ELYTRA_MOVEMENT_CHECK = a("disableElytraMovementCheck", GameRuleCategory.PLAYER, GameRuleBoolean.b(false));
/*  56 */   public static final GameRuleKey<GameRuleInt> MAX_ENTITY_CRAMMING = a("maxEntityCramming", GameRuleCategory.MOBS, GameRuleInt.b(24));
/*  57 */   public static final GameRuleKey<GameRuleBoolean> DO_WEATHER_CYCLE = a("doWeatherCycle", GameRuleCategory.UPDATES, GameRuleBoolean.b(true));
/*  58 */   public static final GameRuleKey<GameRuleBoolean> DO_LIMITED_CRAFTING = a("doLimitedCrafting", GameRuleCategory.PLAYER, GameRuleBoolean.b(false));
/*  59 */   public static final GameRuleKey<GameRuleInt> MAX_COMMAND_CHAIN_LENGTH = a("maxCommandChainLength", GameRuleCategory.MISC, GameRuleInt.b(65536));
/*  60 */   public static final GameRuleKey<GameRuleBoolean> ANNOUNCE_ADVANCEMENTS = a("announceAdvancements", GameRuleCategory.CHAT, GameRuleBoolean.b(true));
/*  61 */   public static final GameRuleKey<GameRuleBoolean> DISABLE_RAIDS = a("disableRaids", GameRuleCategory.MOBS, GameRuleBoolean.b(false));
/*  62 */   public static final GameRuleKey<GameRuleBoolean> DO_INSOMNIA = a("doInsomnia", GameRuleCategory.SPAWNING, GameRuleBoolean.b(true)); public static final GameRuleKey<GameRuleBoolean> DO_IMMEDIATE_RESPAWN; static {
/*  63 */     DO_IMMEDIATE_RESPAWN = a("doImmediateRespawn", GameRuleCategory.PLAYER, GameRuleBoolean.b(false, (minecraftserver, gamerules_gameruleboolean) -> {
/*     */             Iterator<EntityPlayer> iterator = minecraftserver.getPlayerList().getPlayers().iterator();
/*     */             while (iterator.hasNext()) {
/*     */               EntityPlayer entityplayer = iterator.next();
/*     */               entityplayer.playerConnection.sendPacket(new PacketPlayOutGameStateChange(PacketPlayOutGameStateChange.l, gamerules_gameruleboolean.a() ? 1.0F : 0.0F));
/*     */             } 
/*     */           }));
/*     */   }
/*     */ 
/*     */   
/*  73 */   public static final GameRuleKey<GameRuleBoolean> DROWNING_DAMAGE = a("drowningDamage", GameRuleCategory.PLAYER, GameRuleBoolean.b(true));
/*  74 */   public static final GameRuleKey<GameRuleBoolean> FALL_DAMAGE = a("fallDamage", GameRuleCategory.PLAYER, GameRuleBoolean.b(true));
/*  75 */   public static final GameRuleKey<GameRuleBoolean> FIRE_DAMAGE = a("fireDamage", GameRuleCategory.PLAYER, GameRuleBoolean.b(true));
/*  76 */   public static final GameRuleKey<GameRuleBoolean> DO_PATROL_SPAWNING = a("doPatrolSpawning", GameRuleCategory.SPAWNING, GameRuleBoolean.b(true));
/*  77 */   public static final GameRuleKey<GameRuleBoolean> DO_TRADER_SPAWNING = a("doTraderSpawning", GameRuleCategory.SPAWNING, GameRuleBoolean.b(true));
/*  78 */   public static final GameRuleKey<GameRuleBoolean> FORGIVE_DEAD_PLAYERS = a("forgiveDeadPlayers", GameRuleCategory.MOBS, GameRuleBoolean.b(true));
/*  79 */   public static final GameRuleKey<GameRuleBoolean> UNIVERSAL_ANGER = a("universalAnger", GameRuleCategory.MOBS, GameRuleBoolean.b(false));
/*     */   private final Map<GameRuleKey<?>, GameRuleValue<?>> J;
/*     */   
/*     */   private static <T extends GameRuleValue<T>> GameRuleKey<T> a(String s, GameRuleCategory gamerules_gamerulecategory, GameRuleDefinition<T> gamerules_gameruledefinition) {
/*  83 */     GameRuleKey<T> gamerules_gamerulekey = new GameRuleKey<>(s, gamerules_gamerulecategory);
/*  84 */     GameRuleDefinition<?> gamerules_gameruledefinition1 = I.put(gamerules_gamerulekey, gamerules_gameruledefinition);
/*     */     
/*  86 */     if (gamerules_gameruledefinition1 != null) {
/*  87 */       throw new IllegalStateException("Duplicate game rule registration for " + s);
/*     */     }
/*  89 */     return gamerules_gamerulekey;
/*     */   }
/*     */ 
/*     */   
/*     */   public GameRules(DynamicLike<?> dynamiclike) {
/*  94 */     this();
/*  95 */     a(dynamiclike);
/*     */   }
/*     */   
/*     */   public GameRules() {
/*  99 */     this.J = (Map<GameRuleKey<?>, GameRuleValue<?>>)I.entrySet().stream().collect(ImmutableMap.toImmutableMap(Map.Entry::getKey, entry -> ((GameRuleDefinition<GameRuleValue>)entry.getValue()).getValue()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private GameRules(Map<GameRuleKey<?>, GameRuleValue<?>> map) {
/* 105 */     this.J = map;
/*     */   }
/*     */   
/*     */   public <T extends GameRuleValue<T>> T get(GameRuleKey<T> gamerules_gamerulekey) {
/* 109 */     return (T)this.J.get(gamerules_gamerulekey);
/*     */   }
/*     */   
/*     */   public NBTTagCompound a() {
/* 113 */     NBTTagCompound nbttagcompound = new NBTTagCompound();
/*     */     
/* 115 */     this.J.forEach((gamerules_gamerulekey, gamerules_gamerulevalue) -> nbttagcompound.setString(gamerules_gamerulekey.a, gamerules_gamerulevalue.getValue()));
/*     */ 
/*     */     
/* 118 */     return nbttagcompound;
/*     */   }
/*     */   
/*     */   private void a(DynamicLike<?> dynamiclike) {
/* 122 */     this.J.forEach((gamerules_gamerulekey, gamerules_gamerulevalue) -> {
/*     */           Objects.requireNonNull(gamerules_gamerulevalue);
/*     */           dynamiclike.get(gamerules_gamerulekey.a).asString().result().ifPresent(gamerules_gamerulevalue::setValue);
/*     */         });
/*     */   }
/*     */   public GameRules b() {
/* 128 */     return new GameRules((Map<GameRuleKey<?>, GameRuleValue<?>>)this.J.entrySet().stream().collect(ImmutableMap.toImmutableMap(Map.Entry::getKey, entry -> ((GameRuleValue<GameRuleValue>)entry.getValue()).f())));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void a(GameRuleVisitor gamerules_gamerulevisitor) {
/* 134 */     I.forEach((gamerules_gamerulekey, gamerules_gameruledefinition) -> a(gamerules_gamerulevisitor, gamerules_gamerulekey, gamerules_gameruledefinition));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static <T extends GameRuleValue<T>> void a(GameRuleVisitor gamerules_gamerulevisitor, GameRuleKey<?> gamerules_gamerulekey, GameRuleDefinition<?> gamerules_gameruledefinition) {
/* 140 */     gamerules_gamerulevisitor.a(gamerules_gamerulekey, gamerules_gameruledefinition);
/* 141 */     gamerules_gameruledefinition.a(gamerules_gamerulevisitor, gamerules_gamerulekey);
/*     */   }
/*     */   
/*     */   public boolean getBoolean(GameRuleKey<GameRuleBoolean> gamerules_gamerulekey) {
/* 145 */     return ((GameRuleBoolean)get(gamerules_gamerulekey)).a();
/*     */   }
/*     */   
/*     */   public int getInt(GameRuleKey<GameRuleInt> gamerules_gamerulekey) {
/* 149 */     return ((GameRuleInt)get(gamerules_gamerulekey)).a();
/*     */   }
/*     */   
/*     */   public static class GameRuleBoolean
/*     */     extends GameRuleValue<GameRuleBoolean> {
/*     */     private boolean b;
/*     */     
/*     */     private static GameRules.GameRuleDefinition<GameRuleBoolean> b(boolean flag, BiConsumer<MinecraftServer, GameRuleBoolean> biconsumer) {
/* 157 */       return new GameRules.GameRuleDefinition<>(BoolArgumentType::bool, gamerules_gameruledefinition -> new GameRuleBoolean(gamerules_gameruledefinition, flag), biconsumer, GameRules.GameRuleVisitor::b);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private static GameRules.GameRuleDefinition<GameRuleBoolean> b(boolean flag) {
/* 163 */       return b(flag, (minecraftserver, gamerules_gameruleboolean) -> {
/*     */           
/*     */           });
/*     */     }
/*     */     public GameRuleBoolean(GameRules.GameRuleDefinition<GameRuleBoolean> gamerules_gameruledefinition, boolean flag) {
/* 168 */       super(gamerules_gameruledefinition);
/* 169 */       this.b = flag;
/*     */     }
/*     */ 
/*     */     
/*     */     protected void a(CommandContext<CommandListenerWrapper> commandcontext, String s) {
/* 174 */       this.b = BoolArgumentType.getBool(commandcontext, s);
/*     */     }
/*     */     
/*     */     public boolean a() {
/* 178 */       return this.b;
/*     */     }
/*     */     
/*     */     public void a(boolean flag, @Nullable MinecraftServer minecraftserver) {
/* 182 */       this.b = flag;
/* 183 */       onChange(minecraftserver);
/*     */     }
/*     */ 
/*     */     
/*     */     public String getValue() {
/* 188 */       return Boolean.toString(this.b);
/*     */     }
/*     */ 
/*     */     
/*     */     public void setValue(String s) {
/* 193 */       this.b = Boolean.parseBoolean(s);
/*     */     }
/*     */ 
/*     */     
/*     */     public int getIntValue() {
/* 198 */       return this.b ? 1 : 0;
/*     */     }
/*     */ 
/*     */     
/*     */     protected GameRuleBoolean g() {
/* 203 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     protected GameRuleBoolean f() {
/* 208 */       return new GameRuleBoolean(this.a, this.b);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class GameRuleInt
/*     */     extends GameRuleValue<GameRuleInt> {
/*     */     private int b;
/*     */     
/*     */     private static GameRules.GameRuleDefinition<GameRuleInt> a(int i, BiConsumer<MinecraftServer, GameRuleInt> biconsumer) {
/* 217 */       return new GameRules.GameRuleDefinition<>(IntegerArgumentType::integer, gamerules_gameruledefinition -> new GameRuleInt(gamerules_gameruledefinition, i), biconsumer, GameRules.GameRuleVisitor::c);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private static GameRules.GameRuleDefinition<GameRuleInt> b(int i) {
/* 223 */       return a(i, (minecraftserver, gamerules_gameruleint) -> {
/*     */           
/*     */           });
/*     */     }
/*     */     public GameRuleInt(GameRules.GameRuleDefinition<GameRuleInt> gamerules_gameruledefinition, int i) {
/* 228 */       super(gamerules_gameruledefinition);
/* 229 */       this.b = i;
/*     */     }
/*     */ 
/*     */     
/*     */     protected void a(CommandContext<CommandListenerWrapper> commandcontext, String s) {
/* 234 */       this.b = IntegerArgumentType.getInteger(commandcontext, s);
/*     */     }
/*     */     
/*     */     public int a() {
/* 238 */       return this.b;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getValue() {
/* 243 */       return Integer.toString(this.b);
/*     */     }
/*     */ 
/*     */     
/*     */     public void setValue(String s) {
/* 248 */       this.b = c(s);
/*     */     }
/*     */     
/*     */     private static int c(String s) {
/* 252 */       if (!s.isEmpty()) {
/*     */         try {
/* 254 */           return Integer.parseInt(s);
/* 255 */         } catch (NumberFormatException numberformatexception) {
/* 256 */           GameRules.LOGGER.warn("Failed to parse integer {}", s);
/*     */         } 
/*     */       }
/*     */       
/* 260 */       return 0;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getIntValue() {
/* 265 */       return this.b;
/*     */     }
/*     */ 
/*     */     
/*     */     protected GameRuleInt g() {
/* 270 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     protected GameRuleInt f() {
/* 275 */       return new GameRuleInt(this.a, this.b);
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class GameRuleValue<T extends GameRuleValue<T>>
/*     */   {
/*     */     protected final GameRules.GameRuleDefinition<T> a;
/*     */     
/*     */     public GameRuleValue(GameRules.GameRuleDefinition<T> gamerules_gameruledefinition) {
/* 284 */       this.a = gamerules_gameruledefinition;
/*     */     }
/*     */     
/*     */     protected abstract void a(CommandContext<CommandListenerWrapper> param1CommandContext, String param1String);
/*     */     
/*     */     public void b(CommandContext<CommandListenerWrapper> commandcontext, String s) {
/* 290 */       a(commandcontext, s);
/* 291 */       onChange(((CommandListenerWrapper)commandcontext.getSource()).getServer());
/*     */     }
/*     */     
/*     */     public void onChange(@Nullable MinecraftServer minecraftserver) {
/* 295 */       if (minecraftserver != null) {
/* 296 */         this.a.c.accept(minecraftserver, g());
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public abstract void setValue(String param1String);
/*     */     
/*     */     public abstract String getValue();
/*     */     
/*     */     public String toString() {
/* 306 */       return getValue();
/*     */     }
/*     */     
/*     */     public abstract int getIntValue();
/*     */     
/*     */     protected abstract T g();
/*     */     
/*     */     protected abstract T f();
/*     */   }
/*     */   
/*     */   public static class GameRuleDefinition<T extends GameRuleValue<T>>
/*     */   {
/*     */     private final Supplier<ArgumentType<?>> a;
/*     */     private final Function<GameRuleDefinition<T>, T> b;
/*     */     private final BiConsumer<MinecraftServer, T> c;
/*     */     private final GameRules.h<T> d;
/*     */     
/*     */     private GameRuleDefinition(Supplier<ArgumentType<?>> supplier, Function<GameRuleDefinition<T>, T> function, BiConsumer<MinecraftServer, T> biconsumer, GameRules.h<T> gamerules_h) {
/* 324 */       this.a = supplier;
/* 325 */       this.b = function;
/* 326 */       this.c = biconsumer;
/* 327 */       this.d = gamerules_h;
/*     */     }
/*     */     
/*     */     public RequiredArgumentBuilder<CommandListenerWrapper, ?> a(String s) {
/* 331 */       return CommandDispatcher.a(s, this.a.get());
/*     */     }
/*     */     
/*     */     public T getValue() {
/* 335 */       return this.b.apply(this);
/*     */     }
/*     */     
/*     */     public void a(GameRules.GameRuleVisitor gamerules_gamerulevisitor, GameRules.GameRuleKey<T> gamerules_gamerulekey) {
/* 339 */       this.d.call(gamerules_gamerulevisitor, gamerules_gamerulekey, this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class GameRuleKey<T extends GameRuleValue<T>>
/*     */   {
/*     */     private final String a;
/*     */     private final GameRules.GameRuleCategory b;
/*     */     
/*     */     public GameRuleKey(String s, GameRules.GameRuleCategory gamerules_gamerulecategory) {
/* 349 */       this.a = s;
/* 350 */       this.b = gamerules_gamerulecategory;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 354 */       return this.a;
/*     */     }
/*     */     
/*     */     public boolean equals(Object object) {
/* 358 */       return (this == object) ? true : ((object instanceof GameRuleKey && ((GameRuleKey)object).a.equals(this.a)));
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 362 */       return this.a.hashCode();
/*     */     }
/*     */     
/*     */     public String a() {
/* 366 */       return this.a;
/*     */     }
/*     */     
/*     */     public String b() {
/* 370 */       return "gamerule." + this.a;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static interface GameRuleVisitor
/*     */   {
/*     */     default <T extends GameRules.GameRuleValue<T>> void a(GameRules.GameRuleKey<T> gamerules_gamerulekey, GameRules.GameRuleDefinition<T> gamerules_gameruledefinition) {}
/*     */ 
/*     */     
/*     */     default void b(GameRules.GameRuleKey<GameRules.GameRuleBoolean> gamerules_gamerulekey, GameRules.GameRuleDefinition<GameRules.GameRuleBoolean> gamerules_gameruledefinition) {}
/*     */ 
/*     */     
/*     */     default void c(GameRules.GameRuleKey<GameRules.GameRuleInt> gamerules_gamerulekey, GameRules.GameRuleDefinition<GameRules.GameRuleInt> gamerules_gameruledefinition) {}
/*     */   }
/*     */ 
/*     */   
/*     */   public enum GameRuleCategory
/*     */   {
/* 390 */     PLAYER("gamerule.category.player"), MOBS("gamerule.category.mobs"), SPAWNING("gamerule.category.spawning"), DROPS("gamerule.category.drops"), UPDATES("gamerule.category.updates"), CHAT("gamerule.category.chat"), MISC("gamerule.category.misc");
/*     */     
/*     */     private final String h;
/*     */     
/*     */     GameRuleCategory(String s) {
/* 395 */       this.h = s;
/*     */     }
/*     */   }
/*     */   
/*     */   static interface h<T extends GameRuleValue<T>> {
/*     */     void call(GameRules.GameRuleVisitor param1GameRuleVisitor, GameRules.GameRuleKey<T> param1GameRuleKey, GameRules.GameRuleDefinition<T> param1GameRuleDefinition);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GameRules.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */