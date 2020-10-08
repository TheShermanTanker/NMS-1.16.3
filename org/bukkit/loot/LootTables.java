/*     */ package org.bukkit.loot;
/*     */ 
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Keyed;
/*     */ import org.bukkit.NamespacedKey;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum LootTables
/*     */   implements Keyed
/*     */ {
/*  18 */   EMPTY("empty"),
/*     */   
/*  20 */   ABANDONED_MINESHAFT("chests/abandoned_mineshaft"),
/*  21 */   BURIED_TREASURE("chests/buried_treasure"),
/*  22 */   DESERT_PYRAMID("chests/desert_pyramid"),
/*  23 */   END_CITY_TREASURE("chests/end_city_treasure"),
/*  24 */   IGLOO_CHEST("chests/igloo_chest"),
/*  25 */   JUNGLE_TEMPLE("chests/jungle_temple"),
/*  26 */   JUNGLE_TEMPLE_DISPENSER("chests/jungle_temple_dispenser"),
/*  27 */   NETHER_BRIDGE("chests/nether_bridge"),
/*  28 */   PILLAGER_OUTPOST("chests/pillager_outpost"),
/*  29 */   BASTION_TREASURE("chests/bastion_treasure"),
/*  30 */   BASTION_OTHER("chests/bastion_other"),
/*  31 */   BASTION_BRIDGE("chests/bastion_bridge"),
/*  32 */   BASTION_HOGLIN_STABLE("chests/bastion_hoglin_stable"),
/*  33 */   RUINED_PORTAL("chests/ruined_portal"),
/*  34 */   SHIPWRECK_MAP("chests/shipwreck_map"),
/*  35 */   SHIPWRECK_SUPPLY("chests/shipwreck_supply"),
/*  36 */   SHIPWRECK_TREASURE("chests/shipwreck_treasure"),
/*  37 */   SIMPLE_DUNGEON("chests/simple_dungeon"),
/*  38 */   SPAWN_BONUS_CHEST("chests/spawn_bonus_chest"),
/*  39 */   STRONGHOLD_CORRIDOR("chests/stronghold_corridor"),
/*  40 */   STRONGHOLD_CROSSING("chests/stronghold_crossing"),
/*  41 */   STRONGHOLD_LIBRARY("chests/stronghold_library"),
/*  42 */   UNDERWATER_RUIN_BIG("chests/underwater_ruin_big"),
/*  43 */   UNDERWATER_RUIN_SMALL("chests/underwater_ruin_small"),
/*  44 */   VILLAGE_ARMORER("chests/village/village_armorer"),
/*  45 */   VILLAGE_BUTCHER("chests/village/village_butcher"),
/*  46 */   VILLAGE_CARTOGRAPHER("chests/village/village_cartographer"),
/*  47 */   VILLAGE_DESERT_HOUSE("chests/village/village_desert_house"),
/*  48 */   VILLAGE_FISHER("chests/village/village_fisher"),
/*  49 */   VILLAGE_FLETCHER("chests/village/village_fletcher"),
/*  50 */   VILLAGE_MASON("chests/village/village_mason"),
/*  51 */   VILLAGE_PLAINS_HOUSE("chests/village/village_plains_house"),
/*  52 */   VILLAGE_SAVANNA_HOUSE("chests/village/village_savanna_house"),
/*  53 */   VILLAGE_SHEPHERD("chests/village/village_shepherd"),
/*  54 */   VILLAGE_SNOWY_HOUSE("chests/village/village_snowy_house"),
/*  55 */   VILLAGE_TAIGA_HOUSE("chests/village/village_taiga_house"),
/*  56 */   VILLAGE_TANNERY("chests/village/village_tannery"),
/*  57 */   VILLAGE_TEMPLE("chests/village/village_temple"),
/*  58 */   VILLAGE_TOOLSMITH("chests/village/village_toolsmith"),
/*  59 */   VILLAGE_WEAPONSMITH("chests/village/village_weaponsmith"),
/*  60 */   WOODLAND_MANSION("chests/woodland_mansion"),
/*     */   
/*  62 */   ARMOR_STAND("entities/armor_stand"),
/*  63 */   BAT("entities/bat"),
/*  64 */   BLAZE("entities/blaze"),
/*  65 */   CAT("entities/cat"),
/*  66 */   CAVE_SPIDER("entities/cave_spider"),
/*  67 */   CHICKEN("entities/chicken"),
/*  68 */   COD("entities/cod"),
/*  69 */   COW("entities/cow"),
/*  70 */   CREEPER("entities/creeper"),
/*  71 */   DOLPHIN("entities/dolphin"),
/*  72 */   DONKEY("entities/donkey"),
/*  73 */   DROWNED("entities/drowned"),
/*  74 */   ELDER_GUARDIAN("entities/elder_guardian"),
/*  75 */   ENDERMAN("entities/enderman"),
/*  76 */   ENDERMITE("entities/endermite"),
/*  77 */   ENDER_DRAGON("entities/ender_dragon"),
/*  78 */   EVOKER("entities/evoker"),
/*  79 */   FOX("entities/fox"),
/*  80 */   GHAST("entities/ghast"),
/*  81 */   GIANT("entities/giant"),
/*  82 */   GUARDIAN("entities/guardian"),
/*  83 */   HORSE("entities/horse"),
/*  84 */   HUSK("entities/husk"),
/*  85 */   ILLUSIONER("entities/illusioner"),
/*  86 */   IRON_GOLEM("entities/iron_golem"),
/*  87 */   LLAMA("entities/llama"),
/*  88 */   MAGMA_CUBE("entities/magma_cube"),
/*  89 */   MOOSHROOM("entities/mooshroom"),
/*  90 */   MULE("entities/mule"),
/*  91 */   OCELOT("entities/ocelot"),
/*  92 */   PANDA("entities/panda"),
/*  93 */   PARROT("entities/parrot"),
/*  94 */   PHANTOM("entities/phantom"),
/*  95 */   PIG("entities/pig"),
/*  96 */   PILLAGER("entities/pillager"),
/*  97 */   POLAR_BEAR("entities/polar_bear"),
/*  98 */   PUFFERFISH("entities/pufferfish"),
/*  99 */   RABBIT("entities/rabbit"),
/* 100 */   RAVAGER("entities/ravager"),
/* 101 */   SALMON("entities/salmon"),
/*     */   
/* 103 */   SHULKER("entities/shulker"),
/* 104 */   SILVERFISH("entities/silverfish"),
/* 105 */   SKELETON("entities/skeleton"),
/* 106 */   SKELETON_HORSE("entities/skeleton_horse"),
/* 107 */   SLIME("entities/slime"),
/* 108 */   SNOW_GOLEM("entities/snow_golem"),
/* 109 */   SPIDER("entities/spider"),
/* 110 */   SQUID("entities/squid"),
/* 111 */   STRAY("entities/stray"),
/* 112 */   TRADER_LLAMA("entities/trader_llama"),
/* 113 */   TROPICAL_FISH("entities/tropical_fish"),
/* 114 */   TURTLE("entities/turtle"),
/* 115 */   VEX("entities/vex"),
/* 116 */   VILLAGER("entities/villager"),
/* 117 */   VINDICATOR("entities/vindicator"),
/* 118 */   WANDERING_TRADER("entities/wandering_trader"),
/* 119 */   WITCH("entities/witch"),
/* 120 */   WITHER("entities/wither"),
/* 121 */   WITHER_SKELETON("entities/wither_skeleton"),
/* 122 */   WOLF("entities/wolf"),
/* 123 */   ZOMBIE("entities/zombie"),
/* 124 */   ZOMBIE_HORSE("entities/zombie_horse"),
/* 125 */   ZOMBIE_PIGMAN("entities/zombie_pigman"),
/* 126 */   ZOMBIE_VILLAGER("entities/zombie_villager"),
/*     */   
/* 128 */   ARMORER_GIFT("gameplay/hero_of_the_village/armorer_gift"),
/* 129 */   BUTCHER_GIFT("gameplay/hero_of_the_village/butcher_gift"),
/* 130 */   CARTOGRAPHER_GIFT("gameplay/hero_of_the_village/cartographer_gift"),
/* 131 */   CAT_MORNING_GIFT("gameplay/cat_morning_gift"),
/* 132 */   CLERIC_GIFT("gameplay/hero_of_the_village/cleric_gift"),
/* 133 */   FARMER_GIFT("gameplay/hero_of_the_village/farmer_gift"),
/* 134 */   FISHERMAN_GIFT("gameplay/hero_of_the_village/fisherman_gift"),
/* 135 */   FISHING("gameplay/fishing"),
/* 136 */   FISHING_FISH("gameplay/fishing/fish"),
/* 137 */   FISHING_JUNK("gameplay/fishing/junk"),
/* 138 */   FISHING_TREASURE("gameplay/fishing/treasure"),
/* 139 */   FLETCHER_GIFT("gameplay/hero_of_the_village/fletcher_gift"),
/* 140 */   LEATHERWORKER_GIFT("gameplay/hero_of_the_village/leatherworker_gift"),
/* 141 */   LIBRARIAN_GIFT("gameplay/hero_of_the_village/librarian_gift"),
/* 142 */   MASON_GIFT("gameplay/hero_of_the_village/mason_gift"),
/* 143 */   SHEPHERD_GIFT("gameplay/hero_of_the_village/shepherd_gift"),
/* 144 */   TOOLSMITH_GIFT("gameplay/hero_of_the_village/toolsmith_gift"),
/* 145 */   WEAPONSMITH_GIFT("gameplay/hero_of_the_village/weaponsmith_gift"),
/* 146 */   PIGLIN_BARTERING("gameplay/piglin_bartering"),
/*     */   
/* 148 */   SHEEP("entities/sheep"),
/* 149 */   SHEEP_BLACK("entities/sheep/black"),
/* 150 */   SHEEP_BLUE("entities/sheep/blue"),
/* 151 */   SHEEP_BROWN("entities/sheep/brown"),
/* 152 */   SHEEP_CYAN("entities/sheep/cyan"),
/* 153 */   SHEEP_GRAY("entities/sheep/gray"),
/* 154 */   SHEEP_GREEN("entities/sheep/green"),
/* 155 */   SHEEP_LIGHT_BLUE("entities/sheep/light_blue"),
/* 156 */   SHEEP_LIGHT_GRAY("entities/sheep/light_gray"),
/* 157 */   SHEEP_LIME("entities/sheep/lime"),
/* 158 */   SHEEP_MAGENTA("entities/sheep/magenta"),
/* 159 */   SHEEP_ORANGE("entities/sheep/orange"),
/* 160 */   SHEEP_PINK("entities/sheep/pink"),
/* 161 */   SHEEP_PURPLE("entities/sheep/purple"),
/* 162 */   SHEEP_RED("entities/sheep/red"),
/* 163 */   SHEEP_WHITE("entities/sheep/white"),
/* 164 */   SHEEP_YELLOW("entities/sheep/yellow");
/*     */   
/*     */   private final String location;
/*     */ 
/*     */   
/*     */   LootTables(String location) {
/* 170 */     this.location = location;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public NamespacedKey getKey() {
/* 176 */     return NamespacedKey.minecraft(this.location);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public LootTable getLootTable() {
/* 187 */     return Bukkit.getLootTable(getKey());
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\loot\LootTables.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */