/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.datafixers.DSL;
/*     */ import com.mojang.datafixers.DataFix;
/*     */ import com.mojang.datafixers.TypeRewriteRule;
/*     */ import com.mojang.datafixers.Typed;
/*     */ import com.mojang.datafixers.schemas.Schema;
/*     */ import com.mojang.datafixers.types.Type;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.StringUtils;
/*     */ 
/*     */ public class DataConverterStatistic extends DataFix {
/*     */   public DataConverterStatistic(Schema var0, boolean var1) {
/*  21 */     super(var0, var1);
/*     */   }
/*     */   
/*  24 */   private static final Set<String> a = (Set<String>)ImmutableSet.builder()
/*  25 */     .add("stat.craftItem.minecraft.spawn_egg")
/*  26 */     .add("stat.useItem.minecraft.spawn_egg")
/*  27 */     .add("stat.breakItem.minecraft.spawn_egg")
/*  28 */     .add("stat.pickup.minecraft.spawn_egg")
/*  29 */     .add("stat.drop.minecraft.spawn_egg")
/*  30 */     .build();
/*     */   
/*  32 */   private static final Map<String, String> b = (Map<String, String>)ImmutableMap.builder()
/*  33 */     .put("stat.leaveGame", "minecraft:leave_game")
/*  34 */     .put("stat.playOneMinute", "minecraft:play_one_minute")
/*  35 */     .put("stat.timeSinceDeath", "minecraft:time_since_death")
/*  36 */     .put("stat.sneakTime", "minecraft:sneak_time")
/*  37 */     .put("stat.walkOneCm", "minecraft:walk_one_cm")
/*  38 */     .put("stat.crouchOneCm", "minecraft:crouch_one_cm")
/*  39 */     .put("stat.sprintOneCm", "minecraft:sprint_one_cm")
/*  40 */     .put("stat.swimOneCm", "minecraft:swim_one_cm")
/*  41 */     .put("stat.fallOneCm", "minecraft:fall_one_cm")
/*  42 */     .put("stat.climbOneCm", "minecraft:climb_one_cm")
/*  43 */     .put("stat.flyOneCm", "minecraft:fly_one_cm")
/*  44 */     .put("stat.diveOneCm", "minecraft:dive_one_cm")
/*  45 */     .put("stat.minecartOneCm", "minecraft:minecart_one_cm")
/*  46 */     .put("stat.boatOneCm", "minecraft:boat_one_cm")
/*  47 */     .put("stat.pigOneCm", "minecraft:pig_one_cm")
/*  48 */     .put("stat.horseOneCm", "minecraft:horse_one_cm")
/*  49 */     .put("stat.aviateOneCm", "minecraft:aviate_one_cm")
/*  50 */     .put("stat.jump", "minecraft:jump")
/*  51 */     .put("stat.drop", "minecraft:drop")
/*  52 */     .put("stat.damageDealt", "minecraft:damage_dealt")
/*  53 */     .put("stat.damageTaken", "minecraft:damage_taken")
/*  54 */     .put("stat.deaths", "minecraft:deaths")
/*  55 */     .put("stat.mobKills", "minecraft:mob_kills")
/*  56 */     .put("stat.animalsBred", "minecraft:animals_bred")
/*  57 */     .put("stat.playerKills", "minecraft:player_kills")
/*  58 */     .put("stat.fishCaught", "minecraft:fish_caught")
/*  59 */     .put("stat.talkedToVillager", "minecraft:talked_to_villager")
/*  60 */     .put("stat.tradedWithVillager", "minecraft:traded_with_villager")
/*  61 */     .put("stat.cakeSlicesEaten", "minecraft:eat_cake_slice")
/*  62 */     .put("stat.cauldronFilled", "minecraft:fill_cauldron")
/*  63 */     .put("stat.cauldronUsed", "minecraft:use_cauldron")
/*  64 */     .put("stat.armorCleaned", "minecraft:clean_armor")
/*  65 */     .put("stat.bannerCleaned", "minecraft:clean_banner")
/*  66 */     .put("stat.brewingstandInteraction", "minecraft:interact_with_brewingstand")
/*  67 */     .put("stat.beaconInteraction", "minecraft:interact_with_beacon")
/*  68 */     .put("stat.dropperInspected", "minecraft:inspect_dropper")
/*  69 */     .put("stat.hopperInspected", "minecraft:inspect_hopper")
/*  70 */     .put("stat.dispenserInspected", "minecraft:inspect_dispenser")
/*  71 */     .put("stat.noteblockPlayed", "minecraft:play_noteblock")
/*  72 */     .put("stat.noteblockTuned", "minecraft:tune_noteblock")
/*  73 */     .put("stat.flowerPotted", "minecraft:pot_flower")
/*  74 */     .put("stat.trappedChestTriggered", "minecraft:trigger_trapped_chest")
/*  75 */     .put("stat.enderchestOpened", "minecraft:open_enderchest")
/*  76 */     .put("stat.itemEnchanted", "minecraft:enchant_item")
/*  77 */     .put("stat.recordPlayed", "minecraft:play_record")
/*  78 */     .put("stat.furnaceInteraction", "minecraft:interact_with_furnace")
/*  79 */     .put("stat.craftingTableInteraction", "minecraft:interact_with_crafting_table")
/*  80 */     .put("stat.chestOpened", "minecraft:open_chest")
/*  81 */     .put("stat.sleepInBed", "minecraft:sleep_in_bed")
/*  82 */     .put("stat.shulkerBoxOpened", "minecraft:open_shulker_box")
/*  83 */     .build();
/*     */ 
/*     */ 
/*     */   
/*  87 */   private static final Map<String, String> c = (Map<String, String>)ImmutableMap.builder()
/*  88 */     .put("stat.craftItem", "minecraft:crafted")
/*  89 */     .put("stat.useItem", "minecraft:used")
/*  90 */     .put("stat.breakItem", "minecraft:broken")
/*  91 */     .put("stat.pickup", "minecraft:picked_up")
/*  92 */     .put("stat.drop", "minecraft:dropped")
/*  93 */     .build();
/*     */   
/*  95 */   private static final Map<String, String> d = (Map<String, String>)ImmutableMap.builder()
/*  96 */     .put("stat.entityKilledBy", "minecraft:killed_by")
/*  97 */     .put("stat.killEntity", "minecraft:killed")
/*  98 */     .build();
/*     */   
/* 100 */   private static final Map<String, String> e = (Map<String, String>)ImmutableMap.builder()
/* 101 */     .put("Bat", "minecraft:bat")
/* 102 */     .put("Blaze", "minecraft:blaze")
/* 103 */     .put("CaveSpider", "minecraft:cave_spider")
/* 104 */     .put("Chicken", "minecraft:chicken")
/* 105 */     .put("Cow", "minecraft:cow")
/* 106 */     .put("Creeper", "minecraft:creeper")
/* 107 */     .put("Donkey", "minecraft:donkey")
/* 108 */     .put("ElderGuardian", "minecraft:elder_guardian")
/* 109 */     .put("Enderman", "minecraft:enderman")
/* 110 */     .put("Endermite", "minecraft:endermite")
/* 111 */     .put("EvocationIllager", "minecraft:evocation_illager")
/* 112 */     .put("Ghast", "minecraft:ghast")
/* 113 */     .put("Guardian", "minecraft:guardian")
/* 114 */     .put("Horse", "minecraft:horse")
/* 115 */     .put("Husk", "minecraft:husk")
/* 116 */     .put("Llama", "minecraft:llama")
/* 117 */     .put("LavaSlime", "minecraft:magma_cube")
/* 118 */     .put("MushroomCow", "minecraft:mooshroom")
/* 119 */     .put("Mule", "minecraft:mule")
/* 120 */     .put("Ozelot", "minecraft:ocelot")
/* 121 */     .put("Parrot", "minecraft:parrot")
/* 122 */     .put("Pig", "minecraft:pig")
/* 123 */     .put("PolarBear", "minecraft:polar_bear")
/* 124 */     .put("Rabbit", "minecraft:rabbit")
/* 125 */     .put("Sheep", "minecraft:sheep")
/* 126 */     .put("Shulker", "minecraft:shulker")
/* 127 */     .put("Silverfish", "minecraft:silverfish")
/* 128 */     .put("SkeletonHorse", "minecraft:skeleton_horse")
/* 129 */     .put("Skeleton", "minecraft:skeleton")
/* 130 */     .put("Slime", "minecraft:slime")
/* 131 */     .put("Spider", "minecraft:spider")
/* 132 */     .put("Squid", "minecraft:squid")
/* 133 */     .put("Stray", "minecraft:stray")
/* 134 */     .put("Vex", "minecraft:vex")
/* 135 */     .put("Villager", "minecraft:villager")
/* 136 */     .put("VindicationIllager", "minecraft:vindication_illager")
/* 137 */     .put("Witch", "minecraft:witch")
/* 138 */     .put("WitherSkeleton", "minecraft:wither_skeleton")
/* 139 */     .put("Wolf", "minecraft:wolf")
/* 140 */     .put("ZombieHorse", "minecraft:zombie_horse")
/* 141 */     .put("PigZombie", "minecraft:zombie_pigman")
/* 142 */     .put("ZombieVillager", "minecraft:zombie_villager")
/* 143 */     .put("Zombie", "minecraft:zombie")
/* 144 */     .build();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TypeRewriteRule makeRule() {
/* 150 */     Type<?> var0 = getOutputSchema().getType(DataConverterTypes.STATS);
/* 151 */     return fixTypeEverywhereTyped("StatsCounterFix", getInputSchema().getType(DataConverterTypes.STATS), var0, var1 -> {
/*     */           Dynamic<?> var2 = (Dynamic)var1.get(DSL.remainderFinder());
/*     */           Map<Dynamic<?>, Dynamic<?>> var3 = Maps.newHashMap();
/*     */           Optional<? extends Map<? extends Dynamic<?>, ? extends Dynamic<?>>> var4 = var2.getMapValues().result();
/*     */           if (var4.isPresent()) {
/*     */             for (Map.Entry<? extends Dynamic<?>, ? extends Dynamic<?>> var6 : (Iterable<Map.Entry<? extends Dynamic<?>, ? extends Dynamic<?>>>)((Map)var4.get()).entrySet()) {
/*     */               if (((Dynamic)var6.getValue()).asNumber().result().isPresent()) {
/*     */                 String var8;
/*     */                 String var9;
/*     */                 String var7 = ((Dynamic)var6.getKey()).asString("");
/*     */                 if (a.contains(var7)) {
/*     */                   continue;
/*     */                 }
/*     */                 if (b.containsKey(var7)) {
/*     */                   var8 = "minecraft:custom";
/*     */                   var9 = b.get(var7);
/*     */                 } else {
/*     */                   int i = StringUtils.ordinalIndexOf(var7, ".", 2);
/*     */                   if (i < 0) {
/*     */                     continue;
/*     */                   }
/*     */                   String str = var7.substring(0, i);
/*     */                   if ("stat.mineBlock".equals(str)) {
/*     */                     var8 = "minecraft:mined";
/*     */                     var9 = b(var7.substring(i + 1).replace('.', ':'));
/*     */                   } else if (c.containsKey(str)) {
/*     */                     var8 = c.get(str);
/*     */                     String var12 = var7.substring(i + 1).replace('.', ':');
/*     */                     String var13 = a(var12);
/*     */                     var9 = (var13 == null) ? var12 : var13;
/*     */                   } else if (d.containsKey(str)) {
/*     */                     var8 = d.get(str);
/*     */                     String var12 = var7.substring(i + 1).replace('.', ':');
/*     */                     var9 = e.getOrDefault(var12, var12);
/*     */                   } else {
/*     */                     continue;
/*     */                   } 
/*     */                 } 
/*     */                 Dynamic<?> var10 = var2.createString(var8);
/*     */                 Dynamic<?> var11 = var3.computeIfAbsent(var10, ());
/*     */                 var3.put(var10, var11.set(var9, var6.getValue()));
/*     */               } 
/*     */             } 
/*     */           }
/*     */           return (Typed)((Pair)var0.readTyped(var2.emptyMap().set("stats", var2.createMap(var3))).result().orElseThrow(())).getFirst();
/*     */         });
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected String a(String var0) {
/* 201 */     return DataConverterFlatten.a(var0, 0);
/*     */   }
/*     */   
/*     */   protected String b(String var0) {
/* 205 */     return DataConverterFlattenData.a(var0);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterStatistic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */