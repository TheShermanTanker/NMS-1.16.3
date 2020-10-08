/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Collections;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ public class LootTables
/*     */ {
/*  10 */   private static final Set<MinecraftKey> az = Sets.newHashSet();
/*  11 */   private static final Set<MinecraftKey> aA = Collections.unmodifiableSet(az);
/*     */   
/*  13 */   public static final MinecraftKey a = new MinecraftKey("empty");
/*     */ 
/*     */   
/*  16 */   public static final MinecraftKey b = a("chests/spawn_bonus_chest");
/*  17 */   public static final MinecraftKey c = a("chests/end_city_treasure");
/*  18 */   public static final MinecraftKey d = a("chests/simple_dungeon");
/*  19 */   public static final MinecraftKey e = a("chests/village/village_weaponsmith");
/*  20 */   public static final MinecraftKey f = a("chests/village/village_toolsmith");
/*  21 */   public static final MinecraftKey g = a("chests/village/village_armorer");
/*  22 */   public static final MinecraftKey h = a("chests/village/village_cartographer");
/*  23 */   public static final MinecraftKey i = a("chests/village/village_mason");
/*  24 */   public static final MinecraftKey j = a("chests/village/village_shepherd");
/*  25 */   public static final MinecraftKey k = a("chests/village/village_butcher");
/*  26 */   public static final MinecraftKey l = a("chests/village/village_fletcher");
/*  27 */   public static final MinecraftKey m = a("chests/village/village_fisher");
/*  28 */   public static final MinecraftKey n = a("chests/village/village_tannery");
/*  29 */   public static final MinecraftKey o = a("chests/village/village_temple");
/*  30 */   public static final MinecraftKey p = a("chests/village/village_desert_house");
/*  31 */   public static final MinecraftKey q = a("chests/village/village_plains_house");
/*  32 */   public static final MinecraftKey r = a("chests/village/village_taiga_house");
/*  33 */   public static final MinecraftKey s = a("chests/village/village_snowy_house");
/*  34 */   public static final MinecraftKey t = a("chests/village/village_savanna_house");
/*  35 */   public static final MinecraftKey u = a("chests/abandoned_mineshaft");
/*  36 */   public static final MinecraftKey v = a("chests/nether_bridge");
/*  37 */   public static final MinecraftKey w = a("chests/stronghold_library");
/*  38 */   public static final MinecraftKey x = a("chests/stronghold_crossing");
/*  39 */   public static final MinecraftKey y = a("chests/stronghold_corridor");
/*  40 */   public static final MinecraftKey z = a("chests/desert_pyramid");
/*  41 */   public static final MinecraftKey A = a("chests/jungle_temple");
/*  42 */   public static final MinecraftKey B = a("chests/jungle_temple_dispenser");
/*  43 */   public static final MinecraftKey C = a("chests/igloo_chest");
/*  44 */   public static final MinecraftKey D = a("chests/woodland_mansion");
/*  45 */   public static final MinecraftKey E = a("chests/underwater_ruin_small");
/*  46 */   public static final MinecraftKey F = a("chests/underwater_ruin_big");
/*  47 */   public static final MinecraftKey G = a("chests/buried_treasure");
/*  48 */   public static final MinecraftKey H = a("chests/shipwreck_map");
/*  49 */   public static final MinecraftKey I = a("chests/shipwreck_supply");
/*  50 */   public static final MinecraftKey J = a("chests/shipwreck_treasure");
/*  51 */   public static final MinecraftKey K = a("chests/pillager_outpost");
/*  52 */   public static final MinecraftKey L = a("chests/bastion_treasure");
/*  53 */   public static final MinecraftKey M = a("chests/bastion_other");
/*  54 */   public static final MinecraftKey N = a("chests/bastion_bridge");
/*  55 */   public static final MinecraftKey O = a("chests/bastion_hoglin_stable");
/*  56 */   public static final MinecraftKey P = a("chests/ruined_portal");
/*     */ 
/*     */   
/*  59 */   public static final MinecraftKey Q = a("entities/sheep/white");
/*  60 */   public static final MinecraftKey R = a("entities/sheep/orange");
/*  61 */   public static final MinecraftKey S = a("entities/sheep/magenta");
/*  62 */   public static final MinecraftKey T = a("entities/sheep/light_blue");
/*  63 */   public static final MinecraftKey U = a("entities/sheep/yellow");
/*  64 */   public static final MinecraftKey V = a("entities/sheep/lime");
/*  65 */   public static final MinecraftKey W = a("entities/sheep/pink");
/*  66 */   public static final MinecraftKey X = a("entities/sheep/gray");
/*  67 */   public static final MinecraftKey Y = a("entities/sheep/light_gray");
/*  68 */   public static final MinecraftKey Z = a("entities/sheep/cyan");
/*  69 */   public static final MinecraftKey aa = a("entities/sheep/purple");
/*  70 */   public static final MinecraftKey ab = a("entities/sheep/blue");
/*  71 */   public static final MinecraftKey ac = a("entities/sheep/brown");
/*  72 */   public static final MinecraftKey ad = a("entities/sheep/green");
/*  73 */   public static final MinecraftKey ae = a("entities/sheep/red");
/*  74 */   public static final MinecraftKey af = a("entities/sheep/black");
/*     */ 
/*     */   
/*  77 */   public static final MinecraftKey ag = a("gameplay/fishing");
/*  78 */   public static final MinecraftKey ah = a("gameplay/fishing/junk");
/*  79 */   public static final MinecraftKey ai = a("gameplay/fishing/treasure");
/*  80 */   public static final MinecraftKey aj = a("gameplay/fishing/fish");
/*     */ 
/*     */   
/*  83 */   public static final MinecraftKey ak = a("gameplay/cat_morning_gift");
/*  84 */   public static final MinecraftKey al = a("gameplay/hero_of_the_village/armorer_gift");
/*  85 */   public static final MinecraftKey am = a("gameplay/hero_of_the_village/butcher_gift");
/*  86 */   public static final MinecraftKey an = a("gameplay/hero_of_the_village/cartographer_gift");
/*  87 */   public static final MinecraftKey ao = a("gameplay/hero_of_the_village/cleric_gift");
/*  88 */   public static final MinecraftKey ap = a("gameplay/hero_of_the_village/farmer_gift");
/*  89 */   public static final MinecraftKey aq = a("gameplay/hero_of_the_village/fisherman_gift");
/*  90 */   public static final MinecraftKey ar = a("gameplay/hero_of_the_village/fletcher_gift");
/*  91 */   public static final MinecraftKey as = a("gameplay/hero_of_the_village/leatherworker_gift");
/*  92 */   public static final MinecraftKey at = a("gameplay/hero_of_the_village/librarian_gift");
/*  93 */   public static final MinecraftKey au = a("gameplay/hero_of_the_village/mason_gift");
/*  94 */   public static final MinecraftKey av = a("gameplay/hero_of_the_village/shepherd_gift");
/*  95 */   public static final MinecraftKey aw = a("gameplay/hero_of_the_village/toolsmith_gift");
/*  96 */   public static final MinecraftKey ax = a("gameplay/hero_of_the_village/weaponsmith_gift");
/*     */ 
/*     */   
/*  99 */   public static final MinecraftKey ay = a("gameplay/piglin_bartering");
/*     */   
/*     */   private static MinecraftKey a(String var0) {
/* 102 */     return a(new MinecraftKey(var0));
/*     */   }
/*     */   
/*     */   private static MinecraftKey a(MinecraftKey var0) {
/* 106 */     if (az.add(var0)) {
/* 107 */       return var0;
/*     */     }
/*     */     
/* 110 */     throw new IllegalArgumentException(var0 + " is already a registered built-in loot table");
/*     */   }
/*     */   
/*     */   public static Set<MinecraftKey> a() {
/* 114 */     return aA;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootTables.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */