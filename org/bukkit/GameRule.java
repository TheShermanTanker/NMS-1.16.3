/*     */ package org.bukkit;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
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
/*     */ public final class GameRule<T>
/*     */ {
/*  20 */   private static Map<String, GameRule<?>> gameRules = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  25 */   public static final GameRule<Boolean> ANNOUNCE_ADVANCEMENTS = new GameRule("announceAdvancements", (Class)Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  30 */   public static final GameRule<Boolean> COMMAND_BLOCK_OUTPUT = new GameRule("commandBlockOutput", (Class)Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  36 */   public static final GameRule<Boolean> DISABLE_ELYTRA_MOVEMENT_CHECK = new GameRule("disableElytraMovementCheck", (Class)Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  41 */   public static final GameRule<Boolean> DO_DAYLIGHT_CYCLE = new GameRule("doDaylightCycle", (Class)Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   public static final GameRule<Boolean> DO_ENTITY_DROPS = new GameRule("doEntityDrops", (Class)Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   public static final GameRule<Boolean> DO_FIRE_TICK = new GameRule("doFireTick", (Class)Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   public static final GameRule<Boolean> DO_LIMITED_CRAFTING = new GameRule("doLimitedCrafting", (Class)Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   public static final GameRule<Boolean> DO_MOB_LOOT = new GameRule("doMobLoot", (Class)Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   public static final GameRule<Boolean> DO_MOB_SPAWNING = new GameRule("doMobSpawning", (Class)Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   public static final GameRule<Boolean> DO_TILE_DROPS = new GameRule("doTileDrops", (Class)Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   public static final GameRule<Boolean> DO_WEATHER_CYCLE = new GameRule("doWeatherCycle", (Class)Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   public static final GameRule<Boolean> KEEP_INVENTORY = new GameRule("keepInventory", (Class)Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   public static final GameRule<Boolean> LOG_ADMIN_COMMANDS = new GameRule("logAdminCommands", (Class)Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  92 */   public static final GameRule<Boolean> MOB_GRIEFING = new GameRule("mobGriefing", (Class)Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   public static final GameRule<Boolean> NATURAL_REGENERATION = new GameRule("naturalRegeneration", (Class)Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   public static final GameRule<Boolean> REDUCED_DEBUG_INFO = new GameRule("reducedDebugInfo", (Class)Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 109 */   public static final GameRule<Boolean> SEND_COMMAND_FEEDBACK = new GameRule("sendCommandFeedback", (Class)Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   public static final GameRule<Boolean> SHOW_DEATH_MESSAGES = new GameRule("showDeathMessages", (Class)Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 119 */   public static final GameRule<Boolean> SPECTATORS_GENERATE_CHUNKS = new GameRule("spectatorsGenerateChunks", (Class)Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   public static final GameRule<Boolean> DISABLE_RAIDS = new GameRule("disableRaids", (Class)Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 129 */   public static final GameRule<Boolean> DO_INSOMNIA = new GameRule("doInsomnia", (Class)Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 134 */   public static final GameRule<Boolean> DO_IMMEDIATE_RESPAWN = new GameRule("doImmediateRespawn", (Class)Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 139 */   public static final GameRule<Boolean> DROWNING_DAMAGE = new GameRule("drowningDamage", (Class)Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 144 */   public static final GameRule<Boolean> FALL_DAMAGE = new GameRule("fallDamage", (Class)Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 149 */   public static final GameRule<Boolean> FIRE_DAMAGE = new GameRule("fireDamage", (Class)Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 154 */   public static final GameRule<Boolean> DO_PATROL_SPAWNING = new GameRule("doPatrolSpawning", (Class)Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 159 */   public static final GameRule<Boolean> DO_TRADER_SPAWNING = new GameRule("doTraderSpawning", (Class)Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 164 */   public static final GameRule<Boolean> FORGIVE_DEAD_PLAYERS = new GameRule("forgiveDeadPlayers", (Class)Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 169 */   public static final GameRule<Boolean> UNIVERSAL_ANGER = new GameRule("universalAnger", (Class)Boolean.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 177 */   public static final GameRule<Integer> RANDOM_TICK_SPEED = new GameRule("randomTickSpeed", (Class)Integer.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 184 */   public static final GameRule<Integer> SPAWN_RADIUS = new GameRule("spawnRadius", (Class)Integer.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 192 */   public static final GameRule<Integer> MAX_ENTITY_CRAMMING = new GameRule("maxEntityCramming", (Class)Integer.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 201 */   public static final GameRule<Integer> MAX_COMMAND_CHAIN_LENGTH = new GameRule("maxCommandChainLength", (Class)Integer.class);
/*     */   
/*     */   private final String name;
/*     */   
/*     */   private final Class<T> type;
/*     */   
/*     */   private GameRule(@NotNull String name, @NotNull Class<T> clazz) {
/* 208 */     Preconditions.checkNotNull(name, "GameRule name cannot be null");
/* 209 */     Preconditions.checkNotNull(clazz, "GameRule type cannot be null");
/* 210 */     Preconditions.checkArgument((clazz == Boolean.class || clazz == Integer.class), "Must be of type Boolean or Integer. Found %s ", clazz.getName());
/* 211 */     this.name = name;
/* 212 */     this.type = clazz;
/* 213 */     gameRules.put(name, this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getName() {
/* 223 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Class<T> getType() {
/* 233 */     return this.type;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 238 */     if (this == obj) {
/* 239 */       return true;
/*     */     }
/* 241 */     if (!(obj instanceof GameRule)) {
/* 242 */       return false;
/*     */     }
/* 244 */     GameRule<?> other = (GameRule)obj;
/* 245 */     return (getName().equals(other.getName()) && getType() == other.getType());
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 250 */     return "GameRule{key=" + this.name + ", type=" + this.type + '}';
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static GameRule<?> getByName(@NotNull String rule) {
/* 262 */     Preconditions.checkNotNull(rule, "Rule cannot be null");
/* 263 */     return gameRules.get(rule);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static GameRule<?>[] values() {
/* 273 */     return (GameRule<?>[])gameRules.values().toArray((Object[])new GameRule[gameRules.size()]);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\GameRule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */