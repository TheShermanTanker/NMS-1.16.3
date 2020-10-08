/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StatisticList
/*     */ {
/*  11 */   public static final StatisticWrapper<Block> BLOCK_MINED = a("mined", IRegistry.BLOCK);
/*  12 */   public static final StatisticWrapper<Item> ITEM_CRAFTED = a("crafted", IRegistry.ITEM);
/*  13 */   public static final StatisticWrapper<Item> ITEM_USED = a("used", IRegistry.ITEM);
/*  14 */   public static final StatisticWrapper<Item> ITEM_BROKEN = a("broken", IRegistry.ITEM);
/*  15 */   public static final StatisticWrapper<Item> ITEM_PICKED_UP = a("picked_up", IRegistry.ITEM);
/*  16 */   public static final StatisticWrapper<Item> ITEM_DROPPED = a("dropped", IRegistry.ITEM);
/*  17 */   public static final StatisticWrapper<EntityTypes<?>> ENTITY_KILLED = a("killed", IRegistry.ENTITY_TYPE);
/*  18 */   public static final StatisticWrapper<EntityTypes<?>> ENTITY_KILLED_BY = a("killed_by", IRegistry.ENTITY_TYPE);
/*  19 */   public static final StatisticWrapper<MinecraftKey> CUSTOM = a("custom", IRegistry.CUSTOM_STAT);
/*     */ 
/*     */   
/*  22 */   public static final MinecraftKey LEAVE_GAME = a("leave_game", Counter.DEFAULT);
/*     */   
/*  24 */   public static final MinecraftKey PLAY_ONE_MINUTE = a("play_one_minute", Counter.TIME);
/*  25 */   public static final MinecraftKey TIME_SINCE_DEATH = a("time_since_death", Counter.TIME);
/*  26 */   public static final MinecraftKey TIME_SINCE_REST = a("time_since_rest", Counter.TIME);
/*  27 */   public static final MinecraftKey SNEAK_TIME = a("sneak_time", Counter.TIME);
/*  28 */   public static final MinecraftKey WALK_ONE_CM = a("walk_one_cm", Counter.DISTANCE);
/*  29 */   public static final MinecraftKey CROUCH_ONE_CM = a("crouch_one_cm", Counter.DISTANCE);
/*  30 */   public static final MinecraftKey SPRINT_ONE_CM = a("sprint_one_cm", Counter.DISTANCE);
/*  31 */   public static final MinecraftKey WALK_ON_WATER_ONE_CM = a("walk_on_water_one_cm", Counter.DISTANCE);
/*  32 */   public static final MinecraftKey FALL_ONE_CM = a("fall_one_cm", Counter.DISTANCE);
/*  33 */   public static final MinecraftKey CLIMB_ONE_CM = a("climb_one_cm", Counter.DISTANCE);
/*  34 */   public static final MinecraftKey FLY_ONE_CM = a("fly_one_cm", Counter.DISTANCE);
/*  35 */   public static final MinecraftKey WALK_UNDER_WATER_ONE_CM = a("walk_under_water_one_cm", Counter.DISTANCE);
/*  36 */   public static final MinecraftKey MINECART_ONE_CM = a("minecart_one_cm", Counter.DISTANCE);
/*  37 */   public static final MinecraftKey BOAT_ONE_CM = a("boat_one_cm", Counter.DISTANCE);
/*  38 */   public static final MinecraftKey PIG_ONE_CM = a("pig_one_cm", Counter.DISTANCE);
/*  39 */   public static final MinecraftKey HORSE_ONE_CM = a("horse_one_cm", Counter.DISTANCE);
/*  40 */   public static final MinecraftKey AVIATE_ONE_CM = a("aviate_one_cm", Counter.DISTANCE);
/*  41 */   public static final MinecraftKey SWIM_ONE_CM = a("swim_one_cm", Counter.DISTANCE);
/*  42 */   public static final MinecraftKey STRIDER_ONE_CM = a("strider_one_cm", Counter.DISTANCE);
/*     */   
/*  44 */   public static final MinecraftKey JUMP = a("jump", Counter.DEFAULT);
/*  45 */   public static final MinecraftKey DROP = a("drop", Counter.DEFAULT);
/*     */   
/*  47 */   public static final MinecraftKey DAMAGE_DEALT = a("damage_dealt", Counter.DIVIDE_BY_TEN);
/*  48 */   public static final MinecraftKey DAMAGE_DEALT_ABSORBED = a("damage_dealt_absorbed", Counter.DIVIDE_BY_TEN);
/*  49 */   public static final MinecraftKey DAMAGE_DEALT_RESISTED = a("damage_dealt_resisted", Counter.DIVIDE_BY_TEN);
/*     */   
/*  51 */   public static final MinecraftKey DAMAGE_TAKEN = a("damage_taken", Counter.DIVIDE_BY_TEN);
/*  52 */   public static final MinecraftKey DAMAGE_BLOCKED_BY_SHIELD = a("damage_blocked_by_shield", Counter.DIVIDE_BY_TEN);
/*  53 */   public static final MinecraftKey DAMAGE_ABSORBED = a("damage_absorbed", Counter.DIVIDE_BY_TEN);
/*  54 */   public static final MinecraftKey DAMAGE_RESISTED = a("damage_resisted", Counter.DIVIDE_BY_TEN);
/*     */   
/*  56 */   public static final MinecraftKey DEATHS = a("deaths", Counter.DEFAULT);
/*  57 */   public static final MinecraftKey MOB_KILLS = a("mob_kills", Counter.DEFAULT);
/*  58 */   public static final MinecraftKey ANIMALS_BRED = a("animals_bred", Counter.DEFAULT);
/*  59 */   public static final MinecraftKey PLAYER_KILLS = a("player_kills", Counter.DEFAULT);
/*  60 */   public static final MinecraftKey FISH_CAUGHT = a("fish_caught", Counter.DEFAULT);
/*     */   
/*  62 */   public static final MinecraftKey TALKED_TO_VILLAGER = a("talked_to_villager", Counter.DEFAULT);
/*  63 */   public static final MinecraftKey TRADED_WITH_VILLAGER = a("traded_with_villager", Counter.DEFAULT);
/*     */   
/*  65 */   public static final MinecraftKey EAT_CAKE_SLICE = a("eat_cake_slice", Counter.DEFAULT);
/*  66 */   public static final MinecraftKey FILL_CAULDRON = a("fill_cauldron", Counter.DEFAULT);
/*  67 */   public static final MinecraftKey USE_CAULDRON = a("use_cauldron", Counter.DEFAULT);
/*  68 */   public static final MinecraftKey CLEAN_ARMOR = a("clean_armor", Counter.DEFAULT);
/*  69 */   public static final MinecraftKey CLEAN_BANNER = a("clean_banner", Counter.DEFAULT);
/*  70 */   public static final MinecraftKey CLEAN_SHULKER_BOX = a("clean_shulker_box", Counter.DEFAULT);
/*  71 */   public static final MinecraftKey INTERACT_WITH_BREWINGSTAND = a("interact_with_brewingstand", Counter.DEFAULT);
/*  72 */   public static final MinecraftKey INTERACT_WITH_BEACON = a("interact_with_beacon", Counter.DEFAULT);
/*  73 */   public static final MinecraftKey INSPECT_DROPPER = a("inspect_dropper", Counter.DEFAULT);
/*  74 */   public static final MinecraftKey INSPECT_HOPPER = a("inspect_hopper", Counter.DEFAULT);
/*  75 */   public static final MinecraftKey INSPECT_DISPENSER = a("inspect_dispenser", Counter.DEFAULT);
/*  76 */   public static final MinecraftKey PLAY_NOTEBLOCK = a("play_noteblock", Counter.DEFAULT);
/*  77 */   public static final MinecraftKey TUNE_NOTEBLOCK = a("tune_noteblock", Counter.DEFAULT);
/*  78 */   public static final MinecraftKey POT_FLOWER = a("pot_flower", Counter.DEFAULT);
/*  79 */   public static final MinecraftKey TRIGGER_TRAPPED_CHEST = a("trigger_trapped_chest", Counter.DEFAULT);
/*  80 */   public static final MinecraftKey OPEN_ENDERCHEST = a("open_enderchest", Counter.DEFAULT);
/*  81 */   public static final MinecraftKey ENCHANT_ITEM = a("enchant_item", Counter.DEFAULT);
/*  82 */   public static final MinecraftKey PLAY_RECORD = a("play_record", Counter.DEFAULT);
/*  83 */   public static final MinecraftKey INTERACT_WITH_FURNACE = a("interact_with_furnace", Counter.DEFAULT);
/*  84 */   public static final MinecraftKey INTERACT_WITH_CRAFTING_TABLE = a("interact_with_crafting_table", Counter.DEFAULT);
/*  85 */   public static final MinecraftKey OPEN_CHEST = a("open_chest", Counter.DEFAULT);
/*  86 */   public static final MinecraftKey SLEEP_IN_BED = a("sleep_in_bed", Counter.DEFAULT);
/*  87 */   public static final MinecraftKey OPEN_SHULKER_BOX = a("open_shulker_box", Counter.DEFAULT);
/*  88 */   public static final MinecraftKey OPEN_BARREL = a("open_barrel", Counter.DEFAULT);
/*  89 */   public static final MinecraftKey INTERACT_WITH_BLAST_FURNACE = a("interact_with_blast_furnace", Counter.DEFAULT);
/*  90 */   public static final MinecraftKey INTERACT_WITH_SMOKER = a("interact_with_smoker", Counter.DEFAULT);
/*  91 */   public static final MinecraftKey INTERACT_WITH_LECTERN = a("interact_with_lectern", Counter.DEFAULT);
/*  92 */   public static final MinecraftKey INTERACT_WITH_CAMPFIRE = a("interact_with_campfire", Counter.DEFAULT);
/*  93 */   public static final MinecraftKey INTERACT_WITH_CARTOGRAPHY_TABLE = a("interact_with_cartography_table", Counter.DEFAULT);
/*  94 */   public static final MinecraftKey INTERACT_WITH_LOOM = a("interact_with_loom", Counter.DEFAULT);
/*  95 */   public static final MinecraftKey INTERACT_WITH_STONECUTTER = a("interact_with_stonecutter", Counter.DEFAULT);
/*  96 */   public static final MinecraftKey BELL_RING = a("bell_ring", Counter.DEFAULT);
/*  97 */   public static final MinecraftKey RAID_TRIGGER = a("raid_trigger", Counter.DEFAULT);
/*  98 */   public static final MinecraftKey RAID_WIN = a("raid_win", Counter.DEFAULT);
/*  99 */   public static final MinecraftKey INTERACT_WITH_ANVIL = a("interact_with_anvil", Counter.DEFAULT);
/* 100 */   public static final MinecraftKey INTERACT_WITH_GRINDSTONE = a("interact_with_grindstone", Counter.DEFAULT);
/* 101 */   public static final MinecraftKey TARGET_HIT = a("target_hit", Counter.DEFAULT);
/* 102 */   public static final MinecraftKey INTERACT_WITH_SMITHING_TABLE = a("interact_with_smithing_table", Counter.DEFAULT);
/*     */   
/*     */   private static MinecraftKey a(String var0, Counter var1) {
/* 105 */     MinecraftKey var2 = new MinecraftKey(var0);
/* 106 */     IRegistry.a(IRegistry.CUSTOM_STAT, var0, var2);
/* 107 */     CUSTOM.a(var2, var1);
/* 108 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   private static <T> StatisticWrapper<T> a(String var0, IRegistry<T> var1) {
/* 113 */     return (StatisticWrapper<T>)IRegistry.<StatisticWrapper<?>>a(IRegistry.STATS, var0, new StatisticWrapper(var1));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\StatisticList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */