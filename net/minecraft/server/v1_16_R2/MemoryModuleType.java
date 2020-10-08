/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.serialization.Codec;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MemoryModuleType<U>
/*     */ {
/*  28 */   public static final MemoryModuleType<Void> DUMMY = a("dummy");
/*  29 */   public static final MemoryModuleType<GlobalPos> HOME = a("home", GlobalPos.a);
/*  30 */   public static final MemoryModuleType<GlobalPos> JOB_SITE = a("job_site", GlobalPos.a);
/*  31 */   public static final MemoryModuleType<GlobalPos> POTENTIAL_JOB_SITE = a("potential_job_site", GlobalPos.a);
/*  32 */   public static final MemoryModuleType<GlobalPos> MEETING_POINT = a("meeting_point", GlobalPos.a);
/*  33 */   public static final MemoryModuleType<List<GlobalPos>> SECONDARY_JOB_SITE = a("secondary_job_site");
/*  34 */   public static final MemoryModuleType<List<EntityLiving>> MOBS = a("mobs");
/*  35 */   public static final MemoryModuleType<List<EntityLiving>> VISIBLE_MOBS = a("visible_mobs");
/*  36 */   public static final MemoryModuleType<List<EntityLiving>> VISIBLE_VILLAGER_BABIES = a("visible_villager_babies");
/*  37 */   public static final MemoryModuleType<List<EntityHuman>> NEAREST_PLAYERS = a("nearest_players");
/*  38 */   public static final MemoryModuleType<EntityHuman> NEAREST_VISIBLE_PLAYER = a("nearest_visible_player");
/*  39 */   public static final MemoryModuleType<EntityHuman> NEAREST_VISIBLE_TARGETABLE_PLAYER = a("nearest_visible_targetable_player");
/*  40 */   public static final MemoryModuleType<MemoryTarget> WALK_TARGET = a("walk_target");
/*  41 */   public static final MemoryModuleType<BehaviorPosition> LOOK_TARGET = a("look_target");
/*  42 */   public static final MemoryModuleType<EntityLiving> ATTACK_TARGET = a("attack_target");
/*  43 */   public static final MemoryModuleType<Boolean> ATTACK_COOLING_DOWN = a("attack_cooling_down");
/*  44 */   public static final MemoryModuleType<EntityLiving> INTERACTION_TARGET = a("interaction_target");
/*  45 */   public static final MemoryModuleType<EntityAgeable> BREED_TARGET = a("breed_target");
/*  46 */   public static final MemoryModuleType<Entity> RIDE_TARGET = a("ride_target");
/*  47 */   public static final MemoryModuleType<PathEntity> PATH = a("path");
/*  48 */   public static final MemoryModuleType<List<GlobalPos>> INTERACTABLE_DOORS = a("interactable_doors");
/*  49 */   public static final MemoryModuleType<Set<GlobalPos>> DOORS_TO_CLOSE = a("doors_to_close");
/*  50 */   public static final MemoryModuleType<BlockPosition> NEAREST_BED = a("nearest_bed");
/*  51 */   public static final MemoryModuleType<DamageSource> HURT_BY = a("hurt_by");
/*  52 */   public static final MemoryModuleType<EntityLiving> HURT_BY_ENTITY = a("hurt_by_entity");
/*  53 */   public static final MemoryModuleType<EntityLiving> AVOID_TARGET = a("avoid_target");
/*  54 */   public static final MemoryModuleType<EntityLiving> NEAREST_HOSTILE = a("nearest_hostile");
/*  55 */   public static final MemoryModuleType<GlobalPos> HIDING_PLACE = a("hiding_place");
/*  56 */   public static final MemoryModuleType<Long> HEARD_BELL_TIME = a("heard_bell_time");
/*  57 */   public static final MemoryModuleType<Long> CANT_REACH_WALK_TARGET_SINCE = a("cant_reach_walk_target_since");
/*  58 */   public static final MemoryModuleType<Boolean> GOLEM_DETECTED_RECENTLY = a("golem_detected_recently", (Codec<Boolean>)Codec.BOOL);
/*  59 */   public static final MemoryModuleType<Long> LAST_SLEPT = a("last_slept", (Codec<Long>)Codec.LONG);
/*  60 */   public static final MemoryModuleType<Long> LAST_WOKEN = a("last_woken", (Codec<Long>)Codec.LONG);
/*  61 */   public static final MemoryModuleType<Long> LAST_WORKED_AT_POI = a("last_worked_at_poi", (Codec<Long>)Codec.LONG);
/*  62 */   public static final MemoryModuleType<EntityAgeable> NEAREST_VISIBLE_ADULY = a("nearest_visible_adult");
/*  63 */   public static final MemoryModuleType<EntityItem> NEAREST_VISIBLE_WANTED_ITEM = a("nearest_visible_wanted_item");
/*  64 */   public static final MemoryModuleType<EntityInsentient> NEAREST_VISIBLE_NEMSIS = a("nearest_visible_nemesis");
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
/*  75 */   public static final MemoryModuleType<UUID> ANGRY_AT = a("angry_at", MinecraftSerializableUUID.a);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   public static final MemoryModuleType<Boolean> UNIVERSAL_ANGER = a("universal_anger", (Codec<Boolean>)Codec.BOOL);
/*  81 */   public static final MemoryModuleType<Boolean> ADMIRING_ITEM = a("admiring_item", (Codec<Boolean>)Codec.BOOL);
/*  82 */   public static final MemoryModuleType<Integer> TIME_TRYING_TO_REACH_ADMIRE_ITEM = a("time_trying_to_reach_admire_item");
/*  83 */   public static final MemoryModuleType<Boolean> DISABLE_WALK_TO_ADMIRE_ITEM = a("disable_walk_to_admire_item");
/*  84 */   public static final MemoryModuleType<Boolean> ADMIRING_DISABLED = a("admiring_disabled", (Codec<Boolean>)Codec.BOOL);
/*  85 */   public static final MemoryModuleType<Boolean> HUNTED_RECENTLY = a("hunted_recently", (Codec<Boolean>)Codec.BOOL);
/*     */   
/*  87 */   public static final MemoryModuleType<BlockPosition> CELEBRATE_LOCATION = a("celebrate_location");
/*  88 */   public static final MemoryModuleType<Boolean> DANCING = a("dancing");
/*  89 */   public static final MemoryModuleType<EntityHoglin> NEAREST_VISIBLE_HUNTABLE_HOGLIN = a("nearest_visible_huntable_hoglin");
/*  90 */   public static final MemoryModuleType<EntityHoglin> NEAREST_VISIBLE_BABY_HOGLIN = a("nearest_visible_baby_hoglin");
/*  91 */   public static final MemoryModuleType<EntityHuman> NEAREST_TARGETABLE_PLAYER_NOT_WEARING_GOLD = a("nearest_targetable_player_not_wearing_gold");
/*  92 */   public static final MemoryModuleType<List<EntityPiglinAbstract>> NEARBY_ADULT_PIGLINS = a("nearby_adult_piglins");
/*  93 */   public static final MemoryModuleType<List<EntityPiglinAbstract>> NEAREST_VISIBLE_ADULT_PIGLINS = a("nearest_visible_adult_piglins");
/*  94 */   public static final MemoryModuleType<List<EntityHoglin>> NEAREST_VISIBLE_ADULT_HOGLINS = a("nearest_visible_adult_hoglins");
/*     */   
/*  96 */   public static final MemoryModuleType<EntityPiglinAbstract> NEAREST_VISIBLE_ADULT_PIGLIN = a("nearest_visible_adult_piglin");
/*  97 */   public static final MemoryModuleType<EntityLiving> NEAREST_VISIBLE_ZOMBIFIED = a("nearest_visible_zombified");
/*  98 */   public static final MemoryModuleType<Integer> VISIBLE_ADULT_PIGLIN_COUNT = a("visible_adult_piglin_count");
/*  99 */   public static final MemoryModuleType<Integer> VISIBLE_ADULT_HOGLIN_COUNT = a("visible_adult_hoglin_count");
/* 100 */   public static final MemoryModuleType<EntityHuman> NEAREST_PLAYER_HOLDING_WANTED_ITEM = a("nearest_player_holding_wanted_item");
/* 101 */   public static final MemoryModuleType<Boolean> ATE_RECENTLY = a("ate_recently");
/* 102 */   public static final MemoryModuleType<BlockPosition> NEAREST_REPELLENT = a("nearest_repellent");
/* 103 */   public static final MemoryModuleType<Boolean> PACIFIED = a("pacified");
/*     */   
/*     */   private final Optional<Codec<ExpirableMemory<U>>> ai;
/*     */   
/*     */   private MemoryModuleType(Optional<Codec<U>> var0) {
/* 108 */     this.ai = var0.map(ExpirableMemory::a);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 113 */     return IRegistry.MEMORY_MODULE_TYPE.getKey(this).toString();
/*     */   }
/*     */   
/*     */   public Optional<Codec<ExpirableMemory<U>>> getSerializer() {
/* 117 */     return this.ai;
/*     */   }
/*     */   
/*     */   private static <U> MemoryModuleType<U> a(String var0, Codec<U> var1) {
/* 121 */     return IRegistry.<MemoryModuleType<?>, MemoryModuleType<U>>a(IRegistry.MEMORY_MODULE_TYPE, new MinecraftKey(var0), new MemoryModuleType<>(Optional.of(var1)));
/*     */   }
/*     */   
/*     */   private static <U> MemoryModuleType<U> a(String var0) {
/* 125 */     return IRegistry.<MemoryModuleType<?>, MemoryModuleType<U>>a(IRegistry.MEMORY_MODULE_TYPE, new MinecraftKey(var0), new MemoryModuleType<>(Optional.empty()));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MemoryModuleType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */