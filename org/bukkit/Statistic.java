/*     */ package org.bukkit;
/*     */ 
/*     */ import java.util.Locale;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ public enum Statistic
/*     */   implements Keyed
/*     */ {
/*  10 */   DAMAGE_DEALT,
/*  11 */   DAMAGE_TAKEN,
/*  12 */   DEATHS,
/*  13 */   MOB_KILLS,
/*  14 */   PLAYER_KILLS,
/*  15 */   FISH_CAUGHT,
/*  16 */   ANIMALS_BRED,
/*  17 */   LEAVE_GAME,
/*  18 */   JUMP,
/*  19 */   DROP_COUNT,
/*  20 */   DROP(Type.ITEM),
/*  21 */   PICKUP(Type.ITEM),
/*     */ 
/*     */ 
/*     */   
/*  25 */   PLAY_ONE_MINUTE,
/*  26 */   WALK_ONE_CM,
/*  27 */   WALK_ON_WATER_ONE_CM,
/*  28 */   FALL_ONE_CM,
/*  29 */   SNEAK_TIME,
/*  30 */   CLIMB_ONE_CM,
/*  31 */   FLY_ONE_CM,
/*  32 */   WALK_UNDER_WATER_ONE_CM,
/*  33 */   MINECART_ONE_CM,
/*  34 */   BOAT_ONE_CM,
/*  35 */   PIG_ONE_CM,
/*  36 */   HORSE_ONE_CM,
/*  37 */   SPRINT_ONE_CM,
/*  38 */   CROUCH_ONE_CM,
/*  39 */   AVIATE_ONE_CM,
/*  40 */   MINE_BLOCK(Type.BLOCK),
/*  41 */   USE_ITEM(Type.ITEM),
/*  42 */   BREAK_ITEM(Type.ITEM),
/*  43 */   CRAFT_ITEM(Type.ITEM),
/*  44 */   KILL_ENTITY(Type.ENTITY),
/*  45 */   ENTITY_KILLED_BY(Type.ENTITY),
/*  46 */   TIME_SINCE_DEATH,
/*  47 */   TALKED_TO_VILLAGER,
/*  48 */   TRADED_WITH_VILLAGER,
/*  49 */   CAKE_SLICES_EATEN,
/*  50 */   CAULDRON_FILLED,
/*  51 */   CAULDRON_USED,
/*  52 */   ARMOR_CLEANED,
/*  53 */   BANNER_CLEANED,
/*  54 */   BREWINGSTAND_INTERACTION,
/*  55 */   BEACON_INTERACTION,
/*  56 */   DROPPER_INSPECTED,
/*  57 */   HOPPER_INSPECTED,
/*  58 */   DISPENSER_INSPECTED,
/*  59 */   NOTEBLOCK_PLAYED,
/*  60 */   NOTEBLOCK_TUNED,
/*  61 */   FLOWER_POTTED,
/*  62 */   TRAPPED_CHEST_TRIGGERED,
/*  63 */   ENDERCHEST_OPENED,
/*  64 */   ITEM_ENCHANTED,
/*  65 */   RECORD_PLAYED,
/*  66 */   FURNACE_INTERACTION,
/*  67 */   CRAFTING_TABLE_INTERACTION,
/*  68 */   CHEST_OPENED,
/*  69 */   SLEEP_IN_BED,
/*  70 */   SHULKER_BOX_OPENED,
/*  71 */   TIME_SINCE_REST,
/*  72 */   SWIM_ONE_CM,
/*  73 */   DAMAGE_DEALT_ABSORBED,
/*  74 */   DAMAGE_DEALT_RESISTED,
/*  75 */   DAMAGE_BLOCKED_BY_SHIELD,
/*  76 */   DAMAGE_ABSORBED,
/*  77 */   DAMAGE_RESISTED,
/*  78 */   CLEAN_SHULKER_BOX,
/*  79 */   OPEN_BARREL,
/*  80 */   INTERACT_WITH_BLAST_FURNACE,
/*  81 */   INTERACT_WITH_SMOKER,
/*  82 */   INTERACT_WITH_LECTERN,
/*  83 */   INTERACT_WITH_CAMPFIRE,
/*  84 */   INTERACT_WITH_CARTOGRAPHY_TABLE,
/*  85 */   INTERACT_WITH_LOOM,
/*  86 */   INTERACT_WITH_STONECUTTER,
/*  87 */   BELL_RING,
/*  88 */   RAID_TRIGGER,
/*  89 */   RAID_WIN,
/*  90 */   INTERACT_WITH_ANVIL,
/*  91 */   INTERACT_WITH_GRINDSTONE,
/*  92 */   TARGET_HIT,
/*  93 */   INTERACT_WITH_SMITHING_TABLE,
/*  94 */   STRIDER_ONE_CM;
/*     */ 
/*     */   
/*     */   private final Type type;
/*     */ 
/*     */   
/*     */   private final NamespacedKey key;
/*     */ 
/*     */   
/*     */   Statistic(Type type) {
/* 104 */     this.type = type;
/* 105 */     this.key = NamespacedKey.minecraft(name().toLowerCase(Locale.ROOT));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Type getType() {
/* 115 */     return this.type;
/*     */   }
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
/*     */   public boolean isSubstatistic() {
/* 130 */     return (this.type != Type.UNTYPED);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBlock() {
/* 142 */     return (this.type == Type.BLOCK);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public NamespacedKey getKey() {
/* 148 */     return this.key;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum Type
/*     */   {
/* 159 */     UNTYPED,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 164 */     ITEM,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 169 */     BLOCK,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 174 */     ENTITY;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\Statistic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */