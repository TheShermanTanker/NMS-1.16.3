/*     */ package org.bukkit.entity;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Keyed;
/*     */ import org.bukkit.NamespacedKey;
/*     */ import org.bukkit.entity.minecart.CommandMinecart;
/*     */ import org.bukkit.entity.minecart.ExplosiveMinecart;
/*     */ import org.bukkit.entity.minecart.HopperMinecart;
/*     */ import org.bukkit.entity.minecart.PoweredMinecart;
/*     */ import org.bukkit.entity.minecart.RideableMinecart;
/*     */ import org.bukkit.entity.minecart.SpawnerMinecart;
/*     */ import org.bukkit.entity.minecart.StorageMinecart;
/*     */ import org.jetbrains.annotations.Contract;
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
/*     */ public enum EntityType
/*     */   implements Keyed
/*     */ {
/*  32 */   DROPPED_ITEM("item", (Class)Item.class, 1, false),
/*     */ 
/*     */ 
/*     */   
/*  36 */   EXPERIENCE_ORB("experience_orb", (Class)ExperienceOrb.class, 2),
/*     */ 
/*     */ 
/*     */   
/*  40 */   AREA_EFFECT_CLOUD("area_effect_cloud", (Class)AreaEffectCloud.class, 3),
/*     */ 
/*     */ 
/*     */   
/*  44 */   ELDER_GUARDIAN("elder_guardian", (Class)ElderGuardian.class, 4),
/*     */ 
/*     */ 
/*     */   
/*  48 */   WITHER_SKELETON("wither_skeleton", (Class)WitherSkeleton.class, 5),
/*     */ 
/*     */ 
/*     */   
/*  52 */   STRAY("stray", (Class)Stray.class, 6),
/*     */ 
/*     */ 
/*     */   
/*  56 */   EGG("egg", (Class)Egg.class, 7),
/*     */ 
/*     */ 
/*     */   
/*  60 */   LEASH_HITCH("leash_knot", (Class)LeashHitch.class, 8),
/*     */ 
/*     */ 
/*     */   
/*  64 */   PAINTING("painting", (Class)Painting.class, 9),
/*     */ 
/*     */ 
/*     */   
/*  68 */   ARROW("arrow", (Class)Arrow.class, 10),
/*     */ 
/*     */ 
/*     */   
/*  72 */   SNOWBALL("snowball", (Class)Snowball.class, 11),
/*     */ 
/*     */ 
/*     */   
/*  76 */   FIREBALL("fireball", (Class)LargeFireball.class, 12),
/*     */ 
/*     */ 
/*     */   
/*  80 */   SMALL_FIREBALL("small_fireball", (Class)SmallFireball.class, 13),
/*     */ 
/*     */ 
/*     */   
/*  84 */   ENDER_PEARL("ender_pearl", (Class)EnderPearl.class, 14),
/*     */ 
/*     */ 
/*     */   
/*  88 */   ENDER_SIGNAL("eye_of_ender", (Class)EnderSignal.class, 15),
/*     */ 
/*     */ 
/*     */   
/*  92 */   SPLASH_POTION("potion", (Class)ThrownPotion.class, 16, false),
/*     */ 
/*     */ 
/*     */   
/*  96 */   THROWN_EXP_BOTTLE("experience_bottle", (Class)ThrownExpBottle.class, 17),
/*     */ 
/*     */ 
/*     */   
/* 100 */   ITEM_FRAME("item_frame", (Class)ItemFrame.class, 18),
/*     */ 
/*     */ 
/*     */   
/* 104 */   WITHER_SKULL("wither_skull", (Class)WitherSkull.class, 19),
/*     */ 
/*     */ 
/*     */   
/* 108 */   PRIMED_TNT("tnt", (Class)TNTPrimed.class, 20),
/*     */ 
/*     */ 
/*     */   
/* 112 */   FALLING_BLOCK("falling_block", (Class)FallingBlock.class, 21, false),
/*     */ 
/*     */ 
/*     */   
/* 116 */   FIREWORK("firework_rocket", (Class)Firework.class, 22, false),
/*     */ 
/*     */ 
/*     */   
/* 120 */   HUSK("husk", (Class)Husk.class, 23),
/*     */ 
/*     */ 
/*     */   
/* 124 */   SPECTRAL_ARROW("spectral_arrow", (Class)SpectralArrow.class, 24),
/*     */ 
/*     */ 
/*     */   
/* 128 */   SHULKER_BULLET("shulker_bullet", (Class)ShulkerBullet.class, 25),
/*     */ 
/*     */ 
/*     */   
/* 132 */   DRAGON_FIREBALL("dragon_fireball", (Class)DragonFireball.class, 26),
/*     */ 
/*     */ 
/*     */   
/* 136 */   ZOMBIE_VILLAGER("zombie_villager", (Class)ZombieVillager.class, 27),
/*     */ 
/*     */ 
/*     */   
/* 140 */   SKELETON_HORSE("skeleton_horse", (Class)SkeletonHorse.class, 28),
/*     */ 
/*     */ 
/*     */   
/* 144 */   ZOMBIE_HORSE("zombie_horse", (Class)ZombieHorse.class, 29),
/*     */ 
/*     */ 
/*     */   
/* 148 */   ARMOR_STAND("armor_stand", (Class)ArmorStand.class, 30),
/*     */ 
/*     */ 
/*     */   
/* 152 */   DONKEY("donkey", (Class)Donkey.class, 31),
/*     */ 
/*     */ 
/*     */   
/* 156 */   MULE("mule", (Class)Mule.class, 32),
/*     */ 
/*     */ 
/*     */   
/* 160 */   EVOKER_FANGS("evoker_fangs", (Class)EvokerFangs.class, 33),
/*     */ 
/*     */ 
/*     */   
/* 164 */   EVOKER("evoker", (Class)Evoker.class, 34),
/*     */ 
/*     */ 
/*     */   
/* 168 */   VEX("vex", (Class)Vex.class, 35),
/*     */ 
/*     */ 
/*     */   
/* 172 */   VINDICATOR("vindicator", (Class)Vindicator.class, 36),
/*     */ 
/*     */ 
/*     */   
/* 176 */   ILLUSIONER("illusioner", (Class)Illusioner.class, 37),
/*     */ 
/*     */ 
/*     */   
/* 180 */   MINECART_COMMAND("command_block_minecart", (Class)CommandMinecart.class, 40),
/*     */ 
/*     */ 
/*     */   
/* 184 */   BOAT("boat", (Class)Boat.class, 41),
/*     */ 
/*     */ 
/*     */   
/* 188 */   MINECART("minecart", (Class)RideableMinecart.class, 42),
/*     */ 
/*     */ 
/*     */   
/* 192 */   MINECART_CHEST("chest_minecart", (Class)StorageMinecart.class, 43),
/*     */ 
/*     */ 
/*     */   
/* 196 */   MINECART_FURNACE("furnace_minecart", (Class)PoweredMinecart.class, 44),
/*     */ 
/*     */ 
/*     */   
/* 200 */   MINECART_TNT("tnt_minecart", (Class)ExplosiveMinecart.class, 45),
/*     */ 
/*     */ 
/*     */   
/* 204 */   MINECART_HOPPER("hopper_minecart", (Class)HopperMinecart.class, 46),
/*     */ 
/*     */ 
/*     */   
/* 208 */   MINECART_MOB_SPAWNER("spawner_minecart", (Class)SpawnerMinecart.class, 47),
/* 209 */   CREEPER("creeper", (Class)Creeper.class, 50),
/* 210 */   SKELETON("skeleton", (Class)Skeleton.class, 51),
/* 211 */   SPIDER("spider", (Class)Spider.class, 52),
/* 212 */   GIANT("giant", (Class)Giant.class, 53),
/* 213 */   ZOMBIE("zombie", (Class)Zombie.class, 54),
/* 214 */   SLIME("slime", (Class)Slime.class, 55),
/* 215 */   GHAST("ghast", (Class)Ghast.class, 56),
/* 216 */   ZOMBIFIED_PIGLIN("zombified_piglin", (Class)PigZombie.class, 57),
/* 217 */   ENDERMAN("enderman", (Class)Enderman.class, 58),
/* 218 */   CAVE_SPIDER("cave_spider", (Class)CaveSpider.class, 59),
/* 219 */   SILVERFISH("silverfish", (Class)Silverfish.class, 60),
/* 220 */   BLAZE("blaze", (Class)Blaze.class, 61),
/* 221 */   MAGMA_CUBE("magma_cube", (Class)MagmaCube.class, 62),
/* 222 */   ENDER_DRAGON("ender_dragon", (Class)EnderDragon.class, 63),
/* 223 */   WITHER("wither", (Class)Wither.class, 64),
/* 224 */   BAT("bat", (Class)Bat.class, 65),
/* 225 */   WITCH("witch", (Class)Witch.class, 66),
/* 226 */   ENDERMITE("endermite", (Class)Endermite.class, 67),
/* 227 */   GUARDIAN("guardian", (Class)Guardian.class, 68),
/* 228 */   SHULKER("shulker", (Class)Shulker.class, 69),
/* 229 */   PIG("pig", (Class)Pig.class, 90),
/* 230 */   SHEEP("sheep", (Class)Sheep.class, 91),
/* 231 */   COW("cow", (Class)Cow.class, 92),
/* 232 */   CHICKEN("chicken", (Class)Chicken.class, 93),
/* 233 */   SQUID("squid", (Class)Squid.class, 94),
/* 234 */   WOLF("wolf", (Class)Wolf.class, 95),
/* 235 */   MUSHROOM_COW("mooshroom", (Class)MushroomCow.class, 96),
/* 236 */   SNOWMAN("snow_golem", (Class)Snowman.class, 97),
/* 237 */   OCELOT("ocelot", (Class)Ocelot.class, 98),
/* 238 */   IRON_GOLEM("iron_golem", (Class)IronGolem.class, 99),
/* 239 */   HORSE("horse", (Class)Horse.class, 100),
/* 240 */   RABBIT("rabbit", (Class)Rabbit.class, 101),
/* 241 */   POLAR_BEAR("polar_bear", (Class)PolarBear.class, 102),
/* 242 */   LLAMA("llama", (Class)Llama.class, 103),
/* 243 */   LLAMA_SPIT("llama_spit", (Class)LlamaSpit.class, 104),
/* 244 */   PARROT("parrot", (Class)Parrot.class, 105),
/* 245 */   VILLAGER("villager", (Class)Villager.class, 120),
/* 246 */   ENDER_CRYSTAL("end_crystal", (Class)EnderCrystal.class, 200),
/* 247 */   TURTLE("turtle", (Class)Turtle.class, -1),
/* 248 */   PHANTOM("phantom", (Class)Phantom.class, -1),
/* 249 */   TRIDENT("trident", (Class)Trident.class, -1),
/* 250 */   COD("cod", (Class)Cod.class, -1),
/* 251 */   SALMON("salmon", (Class)Salmon.class, -1),
/* 252 */   PUFFERFISH("pufferfish", (Class)PufferFish.class, -1),
/* 253 */   TROPICAL_FISH("tropical_fish", (Class)TropicalFish.class, -1),
/* 254 */   DROWNED("drowned", (Class)Drowned.class, -1),
/* 255 */   DOLPHIN("dolphin", (Class)Dolphin.class, -1),
/* 256 */   CAT("cat", (Class)Cat.class, -1),
/* 257 */   PANDA("panda", (Class)Panda.class, -1),
/* 258 */   PILLAGER("pillager", (Class)Pillager.class, -1),
/* 259 */   RAVAGER("ravager", (Class)Ravager.class, -1),
/* 260 */   TRADER_LLAMA("trader_llama", (Class)TraderLlama.class, -1),
/* 261 */   WANDERING_TRADER("wandering_trader", (Class)WanderingTrader.class, -1),
/* 262 */   FOX("fox", (Class)Fox.class, -1),
/* 263 */   BEE("bee", (Class)Bee.class, -1),
/* 264 */   HOGLIN("hoglin", (Class)Hoglin.class, -1),
/* 265 */   PIGLIN("piglin", (Class)Piglin.class, -1),
/* 266 */   STRIDER("strider", (Class)Strider.class, -1),
/* 267 */   ZOGLIN("zoglin", (Class)Zoglin.class, -1),
/* 268 */   PIGLIN_BRUTE("piglin_brute", (Class)PiglinBrute.class, -1),
/*     */ 
/*     */ 
/*     */   
/* 272 */   FISHING_HOOK("fishing_bobber", (Class)FishHook.class, -1, false),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 278 */   LIGHTNING("lightning_bolt", (Class)LightningStrike.class, -1, false),
/* 279 */   PLAYER("player", (Class)Player.class, -1, false),
/*     */ 
/*     */ 
/*     */   
/* 283 */   UNKNOWN(null, null, -1, false);
/*     */   
/*     */   private final String name;
/*     */   private final Class<? extends Entity> clazz;
/*     */   private final short typeId;
/*     */   private final boolean independent;
/*     */   
/*     */   static {
/* 291 */     NAME_MAP = new HashMap<>();
/* 292 */     ID_MAP = new HashMap<>();
/*     */ 
/*     */     
/* 295 */     for (EntityType type : values()) {
/* 296 */       if (type.name != null) {
/* 297 */         NAME_MAP.put(type.name.toLowerCase(Locale.ENGLISH), type);
/*     */       }
/* 299 */       if (type.typeId > 0) {
/* 300 */         ID_MAP.put(Short.valueOf(type.typeId), type);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 305 */     NAME_MAP.put("xp_orb", EXPERIENCE_ORB);
/* 306 */     NAME_MAP.put("eye_of_ender_signal", ENDER_SIGNAL);
/* 307 */     NAME_MAP.put("xp_bottle", THROWN_EXP_BOTTLE);
/* 308 */     NAME_MAP.put("fireworks_rocket", FIREWORK);
/* 309 */     NAME_MAP.put("evocation_fangs", EVOKER_FANGS);
/* 310 */     NAME_MAP.put("evocation_illager", EVOKER);
/* 311 */     NAME_MAP.put("vindication_illager", VINDICATOR);
/* 312 */     NAME_MAP.put("illusion_illager", ILLUSIONER);
/* 313 */     NAME_MAP.put("commandblock_minecart", MINECART_COMMAND);
/* 314 */     NAME_MAP.put("snowman", SNOWMAN);
/* 315 */     NAME_MAP.put("villager_golem", IRON_GOLEM);
/* 316 */     NAME_MAP.put("ender_crystal", ENDER_CRYSTAL);
/* 317 */     NAME_MAP.put("zombie_pigman", ZOMBIFIED_PIGLIN);
/*     */   }
/*     */ 
/*     */   
/*     */   private final boolean living;
/*     */   private final NamespacedKey key;
/*     */   
/*     */   EntityType(String name, Class<? extends Entity> clazz, int typeId, boolean independent) {
/* 325 */     this.name = name;
/* 326 */     this.clazz = clazz;
/* 327 */     this.typeId = (short)typeId;
/* 328 */     this.independent = independent;
/* 329 */     this.living = (clazz != null && LivingEntity.class.isAssignableFrom(clazz));
/* 330 */     this.key = (name == null) ? null : NamespacedKey.minecraft(name);
/*     */   }
/*     */ 
/*     */   
/*     */   private static final Map<String, EntityType> NAME_MAP;
/*     */   
/*     */   private static final Map<Short, EntityType> ID_MAP;
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   @Nullable
/*     */   public String getName() {
/* 342 */     return this.name;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public NamespacedKey getKey() {
/* 348 */     Preconditions.checkArgument((this.key != null), "EntityType doesn't have key! Is it UNKNOWN?");
/*     */     
/* 350 */     return this.key;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Class<? extends Entity> getEntityClass() {
/* 355 */     return this.clazz;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public short getTypeId() {
/* 366 */     return this.typeId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   @Contract("null -> null")
/*     */   @Nullable
/*     */   public static EntityType fromName(@Nullable String name) {
/* 380 */     if (name == null) {
/* 381 */       return null;
/*     */     }
/* 383 */     return NAME_MAP.get(name.toLowerCase(Locale.ENGLISH));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   @Nullable
/*     */   public static EntityType fromId(int id) {
/* 396 */     if (id > 32767) {
/* 397 */       return null;
/*     */     }
/* 399 */     return ID_MAP.get(Short.valueOf((short)id));
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
/*     */   public boolean isSpawnable() {
/* 411 */     return this.independent;
/*     */   }
/*     */   
/*     */   public boolean isAlive() {
/* 415 */     return this.living;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   String getTranslationKey() {
/* 426 */     return Bukkit.getUnsafe().getTranslationKey(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\EntityType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */