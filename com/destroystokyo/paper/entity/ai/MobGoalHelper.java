/*     */ package com.destroystokyo.paper.entity.ai;
/*     */ import com.destroystokyo.paper.entity.RangedEntity;
/*     */ import com.destroystokyo.paper.util.set.OptimizedSmallEnumSet;
/*     */ import com.google.common.collect.BiMap;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.util.EnumSet;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.server.v1_16_R2.EntityIllagerAbstract;
/*     */ import net.minecraft.server.v1_16_R2.EntityInsentient;
/*     */ import net.minecraft.server.v1_16_R2.EntityPigZombie;
/*     */ import net.minecraft.server.v1_16_R2.EntityRabbit;
/*     */ import net.minecraft.server.v1_16_R2.IRangedEntity;
/*     */ import net.minecraft.server.v1_16_R2.PathfinderGoal;
/*     */ import org.bukkit.NamespacedKey;
/*     */ import org.bukkit.entity.Fish;
/*     */ import org.bukkit.entity.Mob;
/*     */ import org.bukkit.entity.Monster;
/*     */ import org.bukkit.entity.Parrot;
/*     */ import org.bukkit.entity.Rabbit;
/*     */ import org.bukkit.entity.Skeleton;
/*     */ import org.bukkit.entity.Stray;
/*     */ 
/*     */ public class MobGoalHelper {
/*  25 */   private static final BiMap<String, String> deobfuscationMap = (BiMap<String, String>)HashBiMap.create();
/*  26 */   private static final Map<Class<? extends PathfinderGoal>, Class<? extends Mob>> entityClassCache = new HashMap<>();
/*  27 */   private static final Map<Class<? extends EntityInsentient>, Class<? extends Mob>> bukkitMap = new HashMap<>();
/*     */   
/*  29 */   static final Set<String> ignored = new HashSet<>();
/*     */ 
/*     */   
/*     */   static {
/*  33 */     deobfuscationMap.put("bee_b", "bee_attack");
/*  34 */     deobfuscationMap.put("bee_c", "bee_become_angry");
/*  35 */     deobfuscationMap.put("bee_d", "bee_enter_hive");
/*  36 */     deobfuscationMap.put("bee_e", "bee_go_to_hive");
/*  37 */     deobfuscationMap.put("bee_f", "bee_go_to_known_flower");
/*  38 */     deobfuscationMap.put("bee_g", "bee_grow_crop");
/*  39 */     deobfuscationMap.put("bee_h", "bee_hurt_by_other");
/*  40 */     deobfuscationMap.put("bee_i", "bee_locate_hive");
/*  41 */     deobfuscationMap.put("bee_k", "bee_pollinate");
/*  42 */     deobfuscationMap.put("bee_l", "bee_wander");
/*  43 */     deobfuscationMap.put("cat_a", "cat_avoid_entity");
/*  44 */     deobfuscationMap.put("cat_b", "cat_relax_on_owner");
/*  45 */     deobfuscationMap.put("dolphin_b", "dolphin_swim_to_treasure");
/*  46 */     deobfuscationMap.put("dolphin_c", "dolphin_swim_with_player");
/*  47 */     deobfuscationMap.put("dolphin_d", "dolphin_play_with_items");
/*  48 */     deobfuscationMap.put("drowned_a", "drowned_attack");
/*  49 */     deobfuscationMap.put("drowned_b", "drowned_goto_beach");
/*  50 */     deobfuscationMap.put("drowned_c", "drowned_goto_water");
/*  51 */     deobfuscationMap.put("drowned_e", "drowned_swim_up");
/*  52 */     deobfuscationMap.put("drowned_f", "drowned_trident_attack");
/*  53 */     deobfuscationMap.put("enderman_a", "enderman_freeze_when_looked_at");
/*  54 */     deobfuscationMap.put("evoker_a", "evoker_attack_spell");
/*  55 */     deobfuscationMap.put("evoker_b", "evoker_cast_spell");
/*  56 */     deobfuscationMap.put("evoker_c", "evoker_summon_spell");
/*  57 */     deobfuscationMap.put("evoker_d", "evoker_wololo_spell");
/*  58 */     deobfuscationMap.put("fish_b", "fish_swim");
/*  59 */     deobfuscationMap.put("fox_a", "fox_defend_trusted");
/*  60 */     deobfuscationMap.put("fox_b", "fox_faceplant");
/*  61 */     deobfuscationMap.put("fox_e", "fox_breed");
/*  62 */     deobfuscationMap.put("fox_f", "fox_eat_berries");
/*  63 */     deobfuscationMap.put("fox_g", "fox_float");
/*  64 */     deobfuscationMap.put("fox_h", "fox_follow_parent");
/*  65 */     deobfuscationMap.put("fox_j", "fox_look_at_player");
/*  66 */     deobfuscationMap.put("fox_l", "fox_melee_attack");
/*  67 */     deobfuscationMap.put("fox_n", "fox_panic");
/*  68 */     deobfuscationMap.put("fox_o", "fox_pounce");
/*  69 */     deobfuscationMap.put("fox_p", "fox_search_for_items");
/*  70 */     deobfuscationMap.put("fox_q", "fox_stroll_through_village");
/*  71 */     deobfuscationMap.put("fox_r", "fox_perch_and_search");
/*  72 */     deobfuscationMap.put("fox_s", "fox_seek_shelter");
/*  73 */     deobfuscationMap.put("fox_t", "fox_sleep");
/*  74 */     deobfuscationMap.put("fox_u", "fox_stalk_prey");
/*  75 */     deobfuscationMap.put("illager_abstract_b", "raider_open_door");
/*  76 */     deobfuscationMap.put("illager_illusioner_a", "illusioner_blindness_spell");
/*  77 */     deobfuscationMap.put("illager_illusioner_b", "illusioner_mirror_spell");
/*  78 */     deobfuscationMap.put("illager_wizard_b", "spellcaster_cast_spell");
/*  79 */     deobfuscationMap.put("llama_a", "llama_attack_wolf");
/*  80 */     deobfuscationMap.put("llama_c", "llama_hurt_by");
/*  81 */     deobfuscationMap.put("llama_trader_a", "llamatrader_defended_wandering_trader");
/*  82 */     deobfuscationMap.put("monster_patrolling_a", "long_distance_patrol");
/*  83 */     deobfuscationMap.put("ocelot_a", "ocelot_avoid_entity");
/*  84 */     deobfuscationMap.put("ocelot_b", "ocelot_tempt");
/*  85 */     deobfuscationMap.put("panda_b", "panda_attack");
/*  86 */     deobfuscationMap.put("panda_c", "panda_avoid");
/*  87 */     deobfuscationMap.put("panda_d", "panda_breed");
/*  88 */     deobfuscationMap.put("panda_e", "panda_hurt_by_target");
/*  89 */     deobfuscationMap.put("panda_f", "panda_lie_on_back");
/*  90 */     deobfuscationMap.put("panda_g", "panda_look_at_player");
/*  91 */     deobfuscationMap.put("panda_i", "panda_panic");
/*  92 */     deobfuscationMap.put("panda_j", "panda_roll");
/*  93 */     deobfuscationMap.put("panda_k", "panda_sit");
/*  94 */     deobfuscationMap.put("panda_l", "panda_sneeze");
/*  95 */     deobfuscationMap.put("phantom_b", "phantom_attack_player");
/*  96 */     deobfuscationMap.put("phantom_c", "phantom_attack_strategy");
/*  97 */     deobfuscationMap.put("phantom_e", "phantom_circle_around_anchor");
/*  98 */     deobfuscationMap.put("phantom_i", "phantom_sweep_attack");
/*  99 */     deobfuscationMap.put("polar_bear_a", "polarbear_attack_players");
/* 100 */     deobfuscationMap.put("polar_bear_b", "polarbear_hurt_by");
/* 101 */     deobfuscationMap.put("polar_bear_c", "polarbear_melee");
/* 102 */     deobfuscationMap.put("polar_bear_d", "polarbear_panic");
/* 103 */     deobfuscationMap.put("puffer_fish_a", "pufferfish_puff");
/* 104 */     deobfuscationMap.put("raider_a", "raider_hold_ground");
/* 105 */     deobfuscationMap.put("raider_b", "raider_obtain_banner");
/* 106 */     deobfuscationMap.put("raider_c", "raider_celebration");
/* 107 */     deobfuscationMap.put("raider_d", "raider_move_through_village");
/* 108 */     deobfuscationMap.put("ravager_a", "ravager_melee_attack");
/* 109 */     deobfuscationMap.put("shulker_a", "shulker_attack");
/* 110 */     deobfuscationMap.put("shulker_c", "shulker_defense");
/* 111 */     deobfuscationMap.put("shulker_d", "shulker_nearest");
/* 112 */     deobfuscationMap.put("shulker_e", "shulker_peek");
/* 113 */     deobfuscationMap.put("squid_a", "squid_flee");
/* 114 */     deobfuscationMap.put("skeleton_abstract_1", "skeleton_melee");
/* 115 */     deobfuscationMap.put("strider_a", "strider_go_to_lava");
/* 116 */     deobfuscationMap.put("turtle_a", "turtle_breed");
/* 117 */     deobfuscationMap.put("turtle_b", "turtle_go_home");
/* 118 */     deobfuscationMap.put("turtle_c", "turtle_goto_water");
/* 119 */     deobfuscationMap.put("turtle_d", "turtle_lay_egg");
/* 120 */     deobfuscationMap.put("turtle_f", "turtle_panic");
/* 121 */     deobfuscationMap.put("turtle_h", "turtle_random_stroll");
/* 122 */     deobfuscationMap.put("turtle_i", "turtle_tempt");
/* 123 */     deobfuscationMap.put("turtle_j", "turtle_travel");
/* 124 */     deobfuscationMap.put("vex_a", "vex_charge_attack");
/* 125 */     deobfuscationMap.put("vex_b", "vex_copy_target_of_owner");
/* 126 */     deobfuscationMap.put("vex_d", "vex_random_move");
/* 127 */     deobfuscationMap.put("villager_trader_a", "villagertrader_wander_to_position");
/* 128 */     deobfuscationMap.put("vindicator_a", "vindicator_break_door");
/* 129 */     deobfuscationMap.put("vindicator_b", "vindicator_johnny_attack");
/* 130 */     deobfuscationMap.put("vindicator_c", "vindicator_melee_attack");
/* 131 */     deobfuscationMap.put("wither_a", "wither_do_nothing");
/* 132 */     deobfuscationMap.put("wolf_a", "wolf_avoid_entity");
/* 133 */     deobfuscationMap.put("zombie_a", "zombie_attack_turtle_egg");
/*     */     
/* 135 */     ignored.add("selector_1");
/* 136 */     ignored.add("selector_2");
/* 137 */     ignored.add("wrapped");
/*     */     
/* 139 */     bukkitMap.put(EntityInsentient.class, Mob.class);
/* 140 */     bukkitMap.put(EntityAgeable.class, Ageable.class);
/* 141 */     bukkitMap.put(EntityAmbient.class, Ambient.class);
/* 142 */     bukkitMap.put(EntityAnimal.class, Animals.class);
/* 143 */     bukkitMap.put(EntityBat.class, Bat.class);
/* 144 */     bukkitMap.put(EntityBee.class, Bee.class);
/* 145 */     bukkitMap.put(EntityBlaze.class, Blaze.class);
/* 146 */     bukkitMap.put(EntityCat.class, Cat.class);
/* 147 */     bukkitMap.put(EntityCaveSpider.class, CaveSpider.class);
/* 148 */     bukkitMap.put(EntityChicken.class, Chicken.class);
/* 149 */     bukkitMap.put(EntityCod.class, Cod.class);
/* 150 */     bukkitMap.put(EntityCow.class, Cow.class);
/* 151 */     bukkitMap.put(EntityCreature.class, Creature.class);
/* 152 */     bukkitMap.put(EntityCreeper.class, Creeper.class);
/* 153 */     bukkitMap.put(EntityDolphin.class, Dolphin.class);
/* 154 */     bukkitMap.put(EntityDrowned.class, Drowned.class);
/* 155 */     bukkitMap.put(EntityEnderDragon.class, EnderDragon.class);
/* 156 */     bukkitMap.put(EntityEnderman.class, Enderman.class);
/* 157 */     bukkitMap.put(EntityEndermite.class, Endermite.class);
/* 158 */     bukkitMap.put(EntityEvoker.class, Evoker.class);
/* 159 */     bukkitMap.put(EntityFish.class, Fish.class);
/* 160 */     bukkitMap.put(EntityFishSchool.class, Fish.class);
/* 161 */     bukkitMap.put(EntityFlying.class, Flying.class);
/* 162 */     bukkitMap.put(EntityFox.class, Fox.class);
/* 163 */     bukkitMap.put(EntityGhast.class, Ghast.class);
/* 164 */     bukkitMap.put(EntityGiantZombie.class, Giant.class);
/* 165 */     bukkitMap.put(EntityGolem.class, Golem.class);
/* 166 */     bukkitMap.put(EntityGuardian.class, Guardian.class);
/* 167 */     bukkitMap.put(EntityGuardianElder.class, ElderGuardian.class);
/* 168 */     bukkitMap.put(EntityHorse.class, Horse.class);
/* 169 */     bukkitMap.put(EntityHorseAbstract.class, AbstractHorse.class);
/* 170 */     bukkitMap.put(EntityHorseChestedAbstract.class, ChestedHorse.class);
/* 171 */     bukkitMap.put(EntityHorseDonkey.class, Donkey.class);
/* 172 */     bukkitMap.put(EntityHorseMule.class, Mule.class);
/* 173 */     bukkitMap.put(EntityHorseSkeleton.class, SkeletonHorse.class);
/* 174 */     bukkitMap.put(EntityHorseZombie.class, ZombieHorse.class);
/* 175 */     bukkitMap.put(EntityIllagerAbstract.class, Illager.class);
/* 176 */     bukkitMap.put(EntityIllagerIllusioner.class, Illusioner.class);
/* 177 */     bukkitMap.put(EntityIllagerWizard.class, Spellcaster.class);
/* 178 */     bukkitMap.put(EntityIronGolem.class, IronGolem.class);
/* 179 */     bukkitMap.put(EntityLlama.class, Llama.class);
/* 180 */     bukkitMap.put(EntityLlamaTrader.class, TraderLlama.class);
/* 181 */     bukkitMap.put(EntityMagmaCube.class, MagmaCube.class);
/* 182 */     bukkitMap.put(EntityMonster.class, Monster.class);
/* 183 */     bukkitMap.put(EntityMonsterPatrolling.class, Monster.class);
/* 184 */     bukkitMap.put(EntityMushroomCow.class, MushroomCow.class);
/* 185 */     bukkitMap.put(EntityOcelot.class, Ocelot.class);
/* 186 */     bukkitMap.put(EntityPanda.class, Panda.class);
/* 187 */     bukkitMap.put(EntityParrot.class, Parrot.class);
/* 188 */     bukkitMap.put(EntityPerchable.class, Parrot.class);
/* 189 */     bukkitMap.put(EntityPhantom.class, Phantom.class);
/* 190 */     bukkitMap.put(EntityPig.class, Pig.class);
/* 191 */     bukkitMap.put(EntityPigZombie.class, PigZombie.class);
/* 192 */     bukkitMap.put(EntityPillager.class, Pillager.class);
/* 193 */     bukkitMap.put(EntityPolarBear.class, PolarBear.class);
/* 194 */     bukkitMap.put(EntityPufferFish.class, PufferFish.class);
/* 195 */     bukkitMap.put(EntityRabbit.class, Rabbit.class);
/* 196 */     bukkitMap.put(EntityRaider.class, Raider.class);
/* 197 */     bukkitMap.put(EntityRavager.class, Ravager.class);
/* 198 */     bukkitMap.put(EntitySalmon.class, Salmon.class);
/* 199 */     bukkitMap.put(EntitySheep.class, Sheep.class);
/* 200 */     bukkitMap.put(EntityShulker.class, Shulker.class);
/* 201 */     bukkitMap.put(EntitySilverfish.class, Silverfish.class);
/* 202 */     bukkitMap.put(EntitySkeleton.class, Skeleton.class);
/* 203 */     bukkitMap.put(EntitySkeletonAbstract.class, Skeleton.class);
/* 204 */     bukkitMap.put(EntitySkeletonStray.class, Stray.class);
/* 205 */     bukkitMap.put(EntitySkeletonWither.class, WitherSkeleton.class);
/* 206 */     bukkitMap.put(EntitySlime.class, Slime.class);
/* 207 */     bukkitMap.put(EntitySnowman.class, Snowman.class);
/* 208 */     bukkitMap.put(EntitySpider.class, Spider.class);
/* 209 */     bukkitMap.put(EntitySquid.class, Squid.class);
/* 210 */     bukkitMap.put(EntityTameableAnimal.class, Tameable.class);
/* 211 */     bukkitMap.put(EntityTropicalFish.class, TropicalFish.class);
/* 212 */     bukkitMap.put(EntityTurtle.class, Turtle.class);
/* 213 */     bukkitMap.put(EntityVex.class, Vex.class);
/* 214 */     bukkitMap.put(EntityVillager.class, Villager.class);
/* 215 */     bukkitMap.put(EntityVillagerAbstract.class, AbstractVillager.class);
/* 216 */     bukkitMap.put(EntityVillagerTrader.class, WanderingTrader.class);
/* 217 */     bukkitMap.put(EntityVindicator.class, Vindicator.class);
/* 218 */     bukkitMap.put(EntityWaterAnimal.class, WaterMob.class);
/* 219 */     bukkitMap.put(EntityWitch.class, Witch.class);
/* 220 */     bukkitMap.put(EntityWither.class, Wither.class);
/* 221 */     bukkitMap.put(EntityWolf.class, Wolf.class);
/* 222 */     bukkitMap.put(EntityZombie.class, Zombie.class);
/* 223 */     bukkitMap.put(EntityZombieHusk.class, Husk.class);
/* 224 */     bukkitMap.put(EntityZombieVillager.class, ZombieVillager.class);
/* 225 */     bukkitMap.put(EntityHoglin.class, Hoglin.class);
/* 226 */     bukkitMap.put(EntityPiglin.class, Piglin.class);
/* 227 */     bukkitMap.put(EntityPiglinAbstract.class, PiglinAbstract.class);
/* 228 */     bukkitMap.put(EntityPiglinBrute.class, PiglinBrute.class);
/* 229 */     bukkitMap.put(EntityStrider.class, Strider.class);
/* 230 */     bukkitMap.put(EntityZoglin.class, Zoglin.class);
/*     */   }
/*     */   
/*     */   public static String getUsableName(Class<?> clazz) {
/* 234 */     String name = clazz.getName();
/* 235 */     name = name.substring(name.lastIndexOf(".") + 1);
/* 236 */     boolean flag = false;
/*     */     
/* 238 */     if (name.contains("$")) {
/* 239 */       String cut = name.substring(name.indexOf("$") + 1);
/* 240 */       if (cut.length() <= 2) {
/* 241 */         name = name.replace("Entity", "");
/* 242 */         name = name.replace("$", "_");
/* 243 */         flag = true;
/*     */       } else {
/*     */         
/* 246 */         name = cut;
/*     */       } 
/*     */     } 
/* 249 */     name = name.replace("PathfinderGoal", "");
/* 250 */     StringBuilder sb = new StringBuilder();
/* 251 */     for (char c : name.toCharArray()) {
/* 252 */       if (c >= 'A' && c <= 'Z') {
/* 253 */         sb.append("_");
/* 254 */         sb.append(Character.toLowerCase(c));
/*     */       } else {
/* 256 */         sb.append(c);
/*     */       } 
/*     */     } 
/* 259 */     name = sb.toString();
/* 260 */     name = name.replaceFirst("_", "");
/*     */     
/* 262 */     if (flag && !deobfuscationMap.containsKey(name.toLowerCase()) && !ignored.contains(name)) {
/* 263 */       System.out.println("need to map " + clazz.getName() + " (" + name.toLowerCase() + ")");
/*     */     }
/*     */ 
/*     */     
/* 267 */     return (String)deobfuscationMap.getOrDefault(name, name);
/*     */   }
/*     */   
/*     */   public static EnumSet<GoalType> vanillaToPaper(OptimizedSmallEnumSet<PathfinderGoal.Type> types) {
/* 271 */     EnumSet<GoalType> goals = EnumSet.noneOf(GoalType.class);
/* 272 */     for (GoalType type : GoalType.values()) {
/* 273 */       if (types.hasElement((Enum)paperToVanilla(type))) {
/* 274 */         goals.add(type);
/*     */       }
/*     */     } 
/* 277 */     return goals;
/*     */   }
/*     */   
/*     */   public static GoalType vanillaToPaper(PathfinderGoal.Type type) {
/* 281 */     switch (type) {
/*     */       case MOVE:
/* 283 */         return GoalType.MOVE;
/*     */       case LOOK:
/* 285 */         return GoalType.LOOK;
/*     */       case JUMP:
/* 287 */         return GoalType.JUMP;
/*     */       case UNKNOWN_BEHAVIOR:
/* 289 */         return GoalType.UNKNOWN_BEHAVIOR;
/*     */       case TARGET:
/* 291 */         return GoalType.TARGET;
/*     */     } 
/* 293 */     throw new IllegalArgumentException("Unknown vanilla mob goal type " + type.name());
/*     */   }
/*     */ 
/*     */   
/*     */   public static EnumSet<PathfinderGoal.Type> paperToVanilla(EnumSet<GoalType> types) {
/* 298 */     EnumSet<PathfinderGoal.Type> goals = EnumSet.noneOf(PathfinderGoal.Type.class);
/* 299 */     for (GoalType type : types) {
/* 300 */       goals.add(paperToVanilla(type));
/*     */     }
/* 302 */     return goals;
/*     */   }
/*     */   
/*     */   public static PathfinderGoal.Type paperToVanilla(GoalType type) {
/* 306 */     switch (type) {
/*     */       case MOVE:
/* 308 */         return PathfinderGoal.Type.MOVE;
/*     */       case LOOK:
/* 310 */         return PathfinderGoal.Type.LOOK;
/*     */       case JUMP:
/* 312 */         return PathfinderGoal.Type.JUMP;
/*     */       case UNKNOWN_BEHAVIOR:
/* 314 */         return PathfinderGoal.Type.UNKNOWN_BEHAVIOR;
/*     */       case TARGET:
/* 316 */         return PathfinderGoal.Type.TARGET;
/*     */     } 
/* 318 */     throw new IllegalArgumentException("Unknown paper mob goal type " + type.name());
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends Mob> GoalKey<T> getKey(Class<? extends PathfinderGoal> goalClass) {
/* 323 */     String name = getUsableName(goalClass);
/* 324 */     if (ignored.contains(name))
/*     */     {
/* 326 */       return GoalKey.of((Class)Mob.class, NamespacedKey.minecraft(name));
/*     */     }
/* 328 */     return GoalKey.of(getEntity(goalClass), NamespacedKey.minecraft(name));
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends Mob> Class<T> getEntity(Class<? extends PathfinderGoal> goalClass) {
/* 333 */     return (Class<T>)entityClassCache.computeIfAbsent(goalClass, key -> {
/*     */           for (Constructor<?> ctor : key.getDeclaredConstructors()) {
/*     */             for (int i = 0; i < ctor.getParameterCount(); i++) {
/*     */               Class<?> param = ctor.getParameterTypes()[i];
/*     */               if (EntityInsentient.class.isAssignableFrom(param)) {
/*     */                 return toBukkitClass((Class)param);
/*     */               }
/*     */               if (IRangedEntity.class.isAssignableFrom(param)) {
/*     */                 return RangedEntity.class;
/*     */               }
/*     */             } 
/*     */           } 
/*     */           throw new RuntimeException("Can't figure out applicable entity for mob goal " + goalClass);
/*     */         });
/*     */   }
/*     */   
/*     */   public static Class<? extends Mob> toBukkitClass(Class<? extends EntityInsentient> nmsClass) {
/* 350 */     Class<? extends Mob> bukkitClass = bukkitMap.get(nmsClass);
/* 351 */     if (bukkitClass == null) {
/* 352 */       throw new RuntimeException("Can't figure out applicable bukkit entity for nms entity " + nmsClass);
/*     */     }
/* 354 */     return bukkitClass;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\entity\ai\MobGoalHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */