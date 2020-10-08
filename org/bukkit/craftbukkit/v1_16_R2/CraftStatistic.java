/*     */ package org.bukkit.craftbukkit.v1_16_R2;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.collect.BiMap;
/*     */ import com.google.common.collect.ImmutableBiMap;
/*     */ import net.minecraft.server.v1_16_R2.Block;
/*     */ import net.minecraft.server.v1_16_R2.EntityTypes;
/*     */ import net.minecraft.server.v1_16_R2.IRegistry;
/*     */ import net.minecraft.server.v1_16_R2.Item;
/*     */ import net.minecraft.server.v1_16_R2.MinecraftKey;
/*     */ import net.minecraft.server.v1_16_R2.ServerStatisticManager;
/*     */ import net.minecraft.server.v1_16_R2.Statistic;
/*     */ import net.minecraft.server.v1_16_R2.StatisticList;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Statistic;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftMagicNumbers;
/*     */ import org.bukkit.entity.EntityType;
/*     */ 
/*     */ public enum CraftStatistic {
/*  21 */   DAMAGE_DEALT(StatisticList.DAMAGE_DEALT),
/*  22 */   DAMAGE_TAKEN(StatisticList.DAMAGE_TAKEN),
/*  23 */   DEATHS(StatisticList.DEATHS),
/*  24 */   MOB_KILLS(StatisticList.MOB_KILLS),
/*  25 */   PLAYER_KILLS(StatisticList.PLAYER_KILLS),
/*  26 */   FISH_CAUGHT(StatisticList.FISH_CAUGHT),
/*  27 */   ANIMALS_BRED(StatisticList.ANIMALS_BRED),
/*  28 */   LEAVE_GAME(StatisticList.LEAVE_GAME),
/*  29 */   JUMP(StatisticList.JUMP),
/*  30 */   DROP_COUNT(StatisticList.DROP),
/*  31 */   DROP(new MinecraftKey("dropped")),
/*  32 */   PICKUP(new MinecraftKey("picked_up")),
/*  33 */   PLAY_ONE_MINUTE(StatisticList.PLAY_ONE_MINUTE),
/*  34 */   WALK_ONE_CM(StatisticList.WALK_ONE_CM),
/*  35 */   WALK_ON_WATER_ONE_CM(StatisticList.WALK_ON_WATER_ONE_CM),
/*  36 */   FALL_ONE_CM(StatisticList.FALL_ONE_CM),
/*  37 */   SNEAK_TIME(StatisticList.SNEAK_TIME),
/*  38 */   CLIMB_ONE_CM(StatisticList.CLIMB_ONE_CM),
/*  39 */   FLY_ONE_CM(StatisticList.FLY_ONE_CM),
/*  40 */   WALK_UNDER_WATER_ONE_CM(StatisticList.WALK_UNDER_WATER_ONE_CM),
/*  41 */   MINECART_ONE_CM(StatisticList.MINECART_ONE_CM),
/*  42 */   BOAT_ONE_CM(StatisticList.BOAT_ONE_CM),
/*  43 */   PIG_ONE_CM(StatisticList.PIG_ONE_CM),
/*  44 */   HORSE_ONE_CM(StatisticList.HORSE_ONE_CM),
/*  45 */   SPRINT_ONE_CM(StatisticList.SPRINT_ONE_CM),
/*  46 */   CROUCH_ONE_CM(StatisticList.CROUCH_ONE_CM),
/*  47 */   AVIATE_ONE_CM(StatisticList.AVIATE_ONE_CM),
/*  48 */   MINE_BLOCK(new MinecraftKey("mined")),
/*  49 */   USE_ITEM(new MinecraftKey("used")),
/*  50 */   BREAK_ITEM(new MinecraftKey("broken")),
/*  51 */   CRAFT_ITEM(new MinecraftKey("crafted")),
/*  52 */   KILL_ENTITY(new MinecraftKey("killed")),
/*  53 */   ENTITY_KILLED_BY(new MinecraftKey("killed_by")),
/*  54 */   TIME_SINCE_DEATH(StatisticList.TIME_SINCE_DEATH),
/*  55 */   TALKED_TO_VILLAGER(StatisticList.TALKED_TO_VILLAGER),
/*  56 */   TRADED_WITH_VILLAGER(StatisticList.TRADED_WITH_VILLAGER),
/*  57 */   CAKE_SLICES_EATEN(StatisticList.EAT_CAKE_SLICE),
/*  58 */   CAULDRON_FILLED(StatisticList.FILL_CAULDRON),
/*  59 */   CAULDRON_USED(StatisticList.USE_CAULDRON),
/*  60 */   ARMOR_CLEANED(StatisticList.CLEAN_ARMOR),
/*  61 */   BANNER_CLEANED(StatisticList.CLEAN_BANNER),
/*  62 */   BREWINGSTAND_INTERACTION(StatisticList.INTERACT_WITH_BREWINGSTAND),
/*  63 */   BEACON_INTERACTION(StatisticList.INTERACT_WITH_BEACON),
/*  64 */   DROPPER_INSPECTED(StatisticList.INSPECT_DROPPER),
/*  65 */   HOPPER_INSPECTED(StatisticList.INSPECT_HOPPER),
/*  66 */   DISPENSER_INSPECTED(StatisticList.INSPECT_DISPENSER),
/*  67 */   NOTEBLOCK_PLAYED(StatisticList.PLAY_NOTEBLOCK),
/*  68 */   NOTEBLOCK_TUNED(StatisticList.TUNE_NOTEBLOCK),
/*  69 */   FLOWER_POTTED(StatisticList.POT_FLOWER),
/*  70 */   TRAPPED_CHEST_TRIGGERED(StatisticList.TRIGGER_TRAPPED_CHEST),
/*  71 */   ENDERCHEST_OPENED(StatisticList.OPEN_ENDERCHEST),
/*  72 */   ITEM_ENCHANTED(StatisticList.ENCHANT_ITEM),
/*  73 */   RECORD_PLAYED(StatisticList.PLAY_RECORD),
/*  74 */   FURNACE_INTERACTION(StatisticList.INTERACT_WITH_FURNACE),
/*  75 */   CRAFTING_TABLE_INTERACTION(StatisticList.INTERACT_WITH_CRAFTING_TABLE),
/*  76 */   CHEST_OPENED(StatisticList.OPEN_CHEST),
/*  77 */   SLEEP_IN_BED(StatisticList.SLEEP_IN_BED),
/*  78 */   SHULKER_BOX_OPENED(StatisticList.OPEN_SHULKER_BOX),
/*  79 */   TIME_SINCE_REST(StatisticList.TIME_SINCE_REST),
/*  80 */   SWIM_ONE_CM(StatisticList.SWIM_ONE_CM),
/*  81 */   DAMAGE_DEALT_ABSORBED(StatisticList.DAMAGE_DEALT_ABSORBED),
/*  82 */   DAMAGE_DEALT_RESISTED(StatisticList.DAMAGE_DEALT_RESISTED),
/*  83 */   DAMAGE_BLOCKED_BY_SHIELD(StatisticList.DAMAGE_BLOCKED_BY_SHIELD),
/*  84 */   DAMAGE_ABSORBED(StatisticList.DAMAGE_ABSORBED),
/*  85 */   DAMAGE_RESISTED(StatisticList.DAMAGE_RESISTED),
/*  86 */   CLEAN_SHULKER_BOX(StatisticList.CLEAN_SHULKER_BOX),
/*  87 */   OPEN_BARREL(StatisticList.OPEN_BARREL),
/*  88 */   INTERACT_WITH_BLAST_FURNACE(StatisticList.INTERACT_WITH_BLAST_FURNACE),
/*  89 */   INTERACT_WITH_SMOKER(StatisticList.INTERACT_WITH_SMOKER),
/*  90 */   INTERACT_WITH_LECTERN(StatisticList.INTERACT_WITH_LECTERN),
/*  91 */   INTERACT_WITH_CAMPFIRE(StatisticList.INTERACT_WITH_CAMPFIRE),
/*  92 */   INTERACT_WITH_CARTOGRAPHY_TABLE(StatisticList.INTERACT_WITH_CARTOGRAPHY_TABLE),
/*  93 */   INTERACT_WITH_LOOM(StatisticList.INTERACT_WITH_LOOM),
/*  94 */   INTERACT_WITH_STONECUTTER(StatisticList.INTERACT_WITH_STONECUTTER),
/*  95 */   BELL_RING(StatisticList.BELL_RING),
/*  96 */   RAID_TRIGGER(StatisticList.RAID_TRIGGER),
/*  97 */   RAID_WIN(StatisticList.RAID_WIN),
/*  98 */   INTERACT_WITH_ANVIL(StatisticList.INTERACT_WITH_ANVIL),
/*  99 */   INTERACT_WITH_GRINDSTONE(StatisticList.INTERACT_WITH_GRINDSTONE),
/* 100 */   TARGET_HIT(StatisticList.TARGET_HIT),
/* 101 */   INTERACT_WITH_SMITHING_TABLE(StatisticList.INTERACT_WITH_SMITHING_TABLE),
/* 102 */   STRIDER_ONE_CM(StatisticList.STRIDER_ONE_CM);
/*     */   private final MinecraftKey minecraftKey;
/*     */   private final Statistic bukkit;
/*     */   private static final BiMap<MinecraftKey, Statistic> statistics;
/*     */   
/*     */   static {
/* 108 */     ImmutableBiMap.Builder<MinecraftKey, Statistic> statisticBuilder = ImmutableBiMap.builder();
/* 109 */     for (CraftStatistic statistic : values()) {
/* 110 */       statisticBuilder.put(statistic.minecraftKey, statistic.bukkit);
/*     */     }
/*     */     
/* 113 */     statistics = (BiMap<MinecraftKey, Statistic>)statisticBuilder.build();
/*     */   }
/*     */   
/*     */   CraftStatistic(MinecraftKey minecraftKey) {
/* 117 */     this.minecraftKey = minecraftKey;
/*     */     
/* 119 */     this.bukkit = Statistic.valueOf(name());
/* 120 */     Preconditions.checkState((this.bukkit != null), "Bukkit statistic %s does not exist", name());
/*     */   }
/*     */   
/*     */   public static Statistic getBukkitStatistic(Statistic<?> statistic) {
/* 124 */     IRegistry statRegistry = statistic.getWrapper().getRegistry();
/* 125 */     MinecraftKey nmsKey = IRegistry.STATS.getKey(statistic.getWrapper());
/*     */     
/* 127 */     if (statRegistry == IRegistry.CUSTOM_STAT) {
/* 128 */       nmsKey = (MinecraftKey)statistic.b();
/*     */     }
/*     */     
/* 131 */     return (Statistic)statistics.get(nmsKey);
/*     */   }
/*     */   
/*     */   public static Statistic getNMSStatistic(Statistic bukkit) {
/* 135 */     Preconditions.checkArgument((bukkit.getType() == Statistic.Type.UNTYPED), "This method only accepts untyped statistics");
/*     */     
/* 137 */     Statistic<MinecraftKey> nms = StatisticList.CUSTOM.b(statistics.inverse().get(bukkit));
/* 138 */     Preconditions.checkArgument((nms != null), "NMS Statistic %s does not exist", bukkit);
/*     */     
/* 140 */     return nms;
/*     */   }
/*     */   
/*     */   public static Statistic getMaterialStatistic(Statistic stat, Material material) {
/*     */     try {
/* 145 */       if (stat == Statistic.MINE_BLOCK) {
/* 146 */         return StatisticList.BLOCK_MINED.b(CraftMagicNumbers.getBlock(material));
/*     */       }
/* 148 */       if (stat == Statistic.CRAFT_ITEM) {
/* 149 */         return StatisticList.ITEM_CRAFTED.b(CraftMagicNumbers.getItem(material));
/*     */       }
/* 151 */       if (stat == Statistic.USE_ITEM) {
/* 152 */         return StatisticList.ITEM_USED.b(CraftMagicNumbers.getItem(material));
/*     */       }
/* 154 */       if (stat == Statistic.BREAK_ITEM) {
/* 155 */         return StatisticList.ITEM_BROKEN.b(CraftMagicNumbers.getItem(material));
/*     */       }
/* 157 */       if (stat == Statistic.PICKUP) {
/* 158 */         return StatisticList.ITEM_PICKED_UP.b(CraftMagicNumbers.getItem(material));
/*     */       }
/* 160 */       if (stat == Statistic.DROP) {
/* 161 */         return StatisticList.ITEM_DROPPED.b(CraftMagicNumbers.getItem(material));
/*     */       }
/* 163 */     } catch (ArrayIndexOutOfBoundsException e) {
/* 164 */       return null;
/*     */     } 
/* 166 */     return null;
/*     */   }
/*     */   
/*     */   public static Statistic getEntityStatistic(Statistic stat, EntityType entity) {
/* 170 */     if (entity.getName() != null) {
/* 171 */       EntityTypes<?> nmsEntity = (EntityTypes)IRegistry.ENTITY_TYPE.get(new MinecraftKey(entity.getName()));
/*     */       
/* 173 */       if (stat == Statistic.KILL_ENTITY) {
/* 174 */         return StatisticList.ENTITY_KILLED.b(nmsEntity);
/*     */       }
/* 176 */       if (stat == Statistic.ENTITY_KILLED_BY) {
/* 177 */         return StatisticList.ENTITY_KILLED_BY.b(nmsEntity);
/*     */       }
/*     */     } 
/* 180 */     return null;
/*     */   }
/*     */   
/*     */   public static EntityType getEntityTypeFromStatistic(Statistic<EntityTypes<?>> statistic) {
/* 184 */     MinecraftKey name = EntityTypes.getName((EntityTypes)statistic.b());
/* 185 */     return EntityType.fromName(name.getKey());
/*     */   }
/*     */   
/*     */   public static Material getMaterialFromStatistic(Statistic<?> statistic) {
/* 189 */     if (statistic.b() instanceof Item) {
/* 190 */       return CraftMagicNumbers.getMaterial((Item)statistic.b());
/*     */     }
/* 192 */     if (statistic.b() instanceof Block) {
/* 193 */       return CraftMagicNumbers.getMaterial((Block)statistic.b());
/*     */     }
/* 195 */     return null;
/*     */   }
/*     */   
/*     */   public static void incrementStatistic(ServerStatisticManager manager, Statistic statistic) {
/* 199 */     incrementStatistic(manager, statistic, 1);
/*     */   }
/*     */   
/*     */   public static void decrementStatistic(ServerStatisticManager manager, Statistic statistic) {
/* 203 */     decrementStatistic(manager, statistic, 1);
/*     */   }
/*     */   
/*     */   public static int getStatistic(ServerStatisticManager manager, Statistic statistic) {
/* 207 */     Validate.notNull(statistic, "Statistic cannot be null");
/* 208 */     Validate.isTrue((statistic.getType() == Statistic.Type.UNTYPED), "Must supply additional paramater for this statistic");
/* 209 */     return manager.getStatisticValue(getNMSStatistic(statistic));
/*     */   }
/*     */   
/*     */   public static void incrementStatistic(ServerStatisticManager manager, Statistic statistic, int amount) {
/* 213 */     Validate.isTrue((amount > 0), "Amount must be greater than 0");
/* 214 */     setStatistic(manager, statistic, getStatistic(manager, statistic) + amount);
/*     */   }
/*     */   
/*     */   public static void decrementStatistic(ServerStatisticManager manager, Statistic statistic, int amount) {
/* 218 */     Validate.isTrue((amount > 0), "Amount must be greater than 0");
/* 219 */     setStatistic(manager, statistic, getStatistic(manager, statistic) - amount);
/*     */   }
/*     */   
/*     */   public static void setStatistic(ServerStatisticManager manager, Statistic statistic, int newValue) {
/* 223 */     Validate.notNull(statistic, "Statistic cannot be null");
/* 224 */     Validate.isTrue((statistic.getType() == Statistic.Type.UNTYPED), "Must supply additional paramater for this statistic");
/* 225 */     Validate.isTrue((newValue >= 0), "Value must be greater than or equal to 0");
/* 226 */     Statistic nmsStatistic = getNMSStatistic(statistic);
/* 227 */     manager.setStatistic(null, nmsStatistic, newValue);
/*     */   }
/*     */   
/*     */   public static void incrementStatistic(ServerStatisticManager manager, Statistic statistic, Material material) {
/* 231 */     incrementStatistic(manager, statistic, material, 1);
/*     */   }
/*     */   
/*     */   public static void decrementStatistic(ServerStatisticManager manager, Statistic statistic, Material material) {
/* 235 */     decrementStatistic(manager, statistic, material, 1);
/*     */   }
/*     */   
/*     */   public static int getStatistic(ServerStatisticManager manager, Statistic statistic, Material material) {
/* 239 */     Validate.notNull(statistic, "Statistic cannot be null");
/* 240 */     Validate.notNull(material, "Material cannot be null");
/* 241 */     Validate.isTrue((statistic.getType() == Statistic.Type.BLOCK || statistic.getType() == Statistic.Type.ITEM), "This statistic does not take a Material parameter");
/* 242 */     Statistic nmsStatistic = getMaterialStatistic(statistic, material);
/* 243 */     Validate.notNull(nmsStatistic, "The supplied Material does not have a corresponding statistic");
/* 244 */     return manager.getStatisticValue(nmsStatistic);
/*     */   }
/*     */   
/*     */   public static void incrementStatistic(ServerStatisticManager manager, Statistic statistic, Material material, int amount) {
/* 248 */     Validate.isTrue((amount > 0), "Amount must be greater than 0");
/* 249 */     setStatistic(manager, statistic, material, getStatistic(manager, statistic, material) + amount);
/*     */   }
/*     */   
/*     */   public static void decrementStatistic(ServerStatisticManager manager, Statistic statistic, Material material, int amount) {
/* 253 */     Validate.isTrue((amount > 0), "Amount must be greater than 0");
/* 254 */     setStatistic(manager, statistic, material, getStatistic(manager, statistic, material) - amount);
/*     */   }
/*     */   
/*     */   public static void setStatistic(ServerStatisticManager manager, Statistic statistic, Material material, int newValue) {
/* 258 */     Validate.notNull(statistic, "Statistic cannot be null");
/* 259 */     Validate.notNull(material, "Material cannot be null");
/* 260 */     Validate.isTrue((newValue >= 0), "Value must be greater than or equal to 0");
/* 261 */     Validate.isTrue((statistic.getType() == Statistic.Type.BLOCK || statistic.getType() == Statistic.Type.ITEM), "This statistic does not take a Material parameter");
/* 262 */     Statistic nmsStatistic = getMaterialStatistic(statistic, material);
/* 263 */     Validate.notNull(nmsStatistic, "The supplied Material does not have a corresponding statistic");
/* 264 */     manager.setStatistic(null, nmsStatistic, newValue);
/*     */   }
/*     */   
/*     */   public static void incrementStatistic(ServerStatisticManager manager, Statistic statistic, EntityType entityType) {
/* 268 */     incrementStatistic(manager, statistic, entityType, 1);
/*     */   }
/*     */   
/*     */   public static void decrementStatistic(ServerStatisticManager manager, Statistic statistic, EntityType entityType) {
/* 272 */     decrementStatistic(manager, statistic, entityType, 1);
/*     */   }
/*     */   
/*     */   public static int getStatistic(ServerStatisticManager manager, Statistic statistic, EntityType entityType) {
/* 276 */     Validate.notNull(statistic, "Statistic cannot be null");
/* 277 */     Validate.notNull(entityType, "EntityType cannot be null");
/* 278 */     Validate.isTrue((statistic.getType() == Statistic.Type.ENTITY), "This statistic does not take an EntityType parameter");
/* 279 */     Statistic nmsStatistic = getEntityStatistic(statistic, entityType);
/* 280 */     Validate.notNull(nmsStatistic, "The supplied EntityType does not have a corresponding statistic");
/* 281 */     return manager.getStatisticValue(nmsStatistic);
/*     */   }
/*     */   
/*     */   public static void incrementStatistic(ServerStatisticManager manager, Statistic statistic, EntityType entityType, int amount) {
/* 285 */     Validate.isTrue((amount > 0), "Amount must be greater than 0");
/* 286 */     setStatistic(manager, statistic, entityType, getStatistic(manager, statistic, entityType) + amount);
/*     */   }
/*     */   
/*     */   public static void decrementStatistic(ServerStatisticManager manager, Statistic statistic, EntityType entityType, int amount) {
/* 290 */     Validate.isTrue((amount > 0), "Amount must be greater than 0");
/* 291 */     setStatistic(manager, statistic, entityType, getStatistic(manager, statistic, entityType) - amount);
/*     */   }
/*     */   
/*     */   public static void setStatistic(ServerStatisticManager manager, Statistic statistic, EntityType entityType, int newValue) {
/* 295 */     Validate.notNull(statistic, "Statistic cannot be null");
/* 296 */     Validate.notNull(entityType, "EntityType cannot be null");
/* 297 */     Validate.isTrue((newValue >= 0), "Value must be greater than or equal to 0");
/* 298 */     Validate.isTrue((statistic.getType() == Statistic.Type.ENTITY), "This statistic does not take an EntityType parameter");
/* 299 */     Statistic nmsStatistic = getEntityStatistic(statistic, entityType);
/* 300 */     Validate.notNull(nmsStatistic, "The supplied EntityType does not have a corresponding statistic");
/* 301 */     manager.setStatistic(null, nmsStatistic, newValue);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\CraftStatistic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */