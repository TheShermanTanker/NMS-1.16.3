/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import co.aikar.timings.MinecraftTimings;
/*     */ import co.aikar.timings.Timing;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Function;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ 
/*     */ public class EntityTypes<T extends Entity> {
/*  16 */   private static final Logger LOGGER = LogManager.getLogger();
/*  17 */   public static final EntityTypes<EntityAreaEffectCloud> AREA_EFFECT_CLOUD = a("area_effect_cloud", Builder.<Entity>a(EntityAreaEffectCloud::new, EnumCreatureType.MISC).c().a(6.0F, 0.5F).trackingRange(10).updateInterval(10));
/*  18 */   public static final EntityTypes<EntityArmorStand> ARMOR_STAND = a("armor_stand", Builder.<Entity>a(EntityArmorStand::new, EnumCreatureType.MISC).a(0.5F, 1.975F).trackingRange(10));
/*  19 */   public static final EntityTypes<EntityTippedArrow> ARROW = a("arrow", Builder.<Entity>a(EntityTippedArrow::new, EnumCreatureType.MISC).a(0.5F, 0.5F).trackingRange(4).updateInterval(20));
/*  20 */   public static final EntityTypes<EntityBat> BAT = a("bat", Builder.<Entity>a(EntityBat::new, EnumCreatureType.AMBIENT).a(0.5F, 0.9F).trackingRange(5));
/*  21 */   public static final EntityTypes<EntityBee> BEE = a("bee", Builder.<Entity>a(EntityBee::new, EnumCreatureType.CREATURE).a(0.7F, 0.6F).trackingRange(8));
/*  22 */   public static final EntityTypes<EntityBlaze> BLAZE = a("blaze", Builder.<Entity>a(EntityBlaze::new, EnumCreatureType.MONSTER).c().a(0.6F, 1.8F).trackingRange(8));
/*  23 */   public static final EntityTypes<EntityBoat> BOAT = a("boat", Builder.<Entity>a(EntityBoat::new, EnumCreatureType.MISC).a(1.375F, 0.5625F).trackingRange(10));
/*  24 */   public static final EntityTypes<EntityCat> CAT = a("cat", Builder.<Entity>a(EntityCat::new, EnumCreatureType.CREATURE).a(0.6F, 0.7F).trackingRange(8));
/*  25 */   public static final EntityTypes<EntityCaveSpider> CAVE_SPIDER = a("cave_spider", Builder.<Entity>a(EntityCaveSpider::new, EnumCreatureType.MONSTER).a(0.7F, 0.5F).trackingRange(8));
/*  26 */   public static final EntityTypes<EntityChicken> CHICKEN = a("chicken", Builder.<Entity>a(EntityChicken::new, EnumCreatureType.CREATURE).a(0.4F, 0.7F).trackingRange(10));
/*  27 */   public static final EntityTypes<EntityCod> COD = a("cod", Builder.<Entity>a(EntityCod::new, EnumCreatureType.WATER_AMBIENT).a(0.5F, 0.3F).trackingRange(4));
/*  28 */   public static final EntityTypes<EntityCow> COW = a("cow", Builder.<Entity>a(EntityCow::new, EnumCreatureType.CREATURE).a(0.9F, 1.4F).trackingRange(10));
/*  29 */   public static final EntityTypes<EntityCreeper> CREEPER = a("creeper", Builder.<Entity>a(EntityCreeper::new, EnumCreatureType.MONSTER).a(0.6F, 1.7F).trackingRange(8));
/*  30 */   public static final EntityTypes<EntityDolphin> DOLPHIN = a("dolphin", Builder.<Entity>a(EntityDolphin::new, EnumCreatureType.WATER_CREATURE).a(0.9F, 0.6F));
/*  31 */   public static final EntityTypes<EntityHorseDonkey> DONKEY = a("donkey", Builder.<Entity>a(EntityHorseDonkey::new, EnumCreatureType.CREATURE).a(1.3964844F, 1.5F).trackingRange(10));
/*  32 */   public static final EntityTypes<EntityDragonFireball> DRAGON_FIREBALL = a("dragon_fireball", Builder.<Entity>a(EntityDragonFireball::new, EnumCreatureType.MISC).a(1.0F, 1.0F).trackingRange(4).updateInterval(10));
/*  33 */   public static final EntityTypes<EntityDrowned> DROWNED = a("drowned", Builder.<Entity>a(EntityDrowned::new, EnumCreatureType.MONSTER).a(0.6F, 1.95F).trackingRange(8));
/*  34 */   public static final EntityTypes<EntityGuardianElder> ELDER_GUARDIAN = a("elder_guardian", Builder.<Entity>a(EntityGuardianElder::new, EnumCreatureType.MONSTER).a(1.9975F, 1.9975F).trackingRange(10));
/*  35 */   public static final EntityTypes<EntityEnderCrystal> END_CRYSTAL = a("end_crystal", Builder.<Entity>a(EntityEnderCrystal::new, EnumCreatureType.MISC).a(2.0F, 2.0F).trackingRange(16).updateInterval(2147483647));
/*  36 */   public static final EntityTypes<EntityEnderDragon> ENDER_DRAGON = a("ender_dragon", Builder.<Entity>a(EntityEnderDragon::new, EnumCreatureType.MONSTER).c().a(16.0F, 8.0F).trackingRange(10));
/*  37 */   public static final EntityTypes<EntityEnderman> ENDERMAN = a("enderman", Builder.<Entity>a(EntityEnderman::new, EnumCreatureType.MONSTER).a(0.6F, 2.9F).trackingRange(8));
/*  38 */   public static final EntityTypes<EntityEndermite> ENDERMITE = a("endermite", Builder.<Entity>a(EntityEndermite::new, EnumCreatureType.MONSTER).a(0.4F, 0.3F).trackingRange(8));
/*  39 */   public static final EntityTypes<EntityEvoker> EVOKER = a("evoker", Builder.<Entity>a(EntityEvoker::new, EnumCreatureType.MONSTER).a(0.6F, 1.95F).trackingRange(8));
/*  40 */   public static final EntityTypes<EntityEvokerFangs> EVOKER_FANGS = a("evoker_fangs", Builder.<Entity>a(EntityEvokerFangs::new, EnumCreatureType.MISC).a(0.5F, 0.8F).trackingRange(6).updateInterval(2));
/*  41 */   public static final EntityTypes<EntityExperienceOrb> EXPERIENCE_ORB = a("experience_orb", Builder.<Entity>a(EntityExperienceOrb::new, EnumCreatureType.MISC).a(0.5F, 0.5F).trackingRange(6).updateInterval(20));
/*  42 */   public static final EntityTypes<EntityEnderSignal> EYE_OF_ENDER = a("eye_of_ender", Builder.<Entity>a(EntityEnderSignal::new, EnumCreatureType.MISC).a(0.25F, 0.25F).trackingRange(4).updateInterval(4));
/*  43 */   public static final EntityTypes<EntityFallingBlock> FALLING_BLOCK = a("falling_block", Builder.<Entity>a(EntityFallingBlock::new, EnumCreatureType.MISC).a(0.98F, 0.98F).trackingRange(10).updateInterval(20));
/*  44 */   public static final EntityTypes<EntityFireworks> FIREWORK_ROCKET = a("firework_rocket", Builder.<Entity>a(EntityFireworks::new, EnumCreatureType.MISC).a(0.25F, 0.25F).trackingRange(4).updateInterval(10));
/*  45 */   public static final EntityTypes<EntityFox> FOX = a("fox", Builder.<Entity>a(EntityFox::new, EnumCreatureType.CREATURE).a(0.6F, 0.7F).trackingRange(8).a(new Block[] { Blocks.SWEET_BERRY_BUSH }));
/*  46 */   public static final EntityTypes<EntityGhast> GHAST = a("ghast", Builder.<Entity>a(EntityGhast::new, EnumCreatureType.MONSTER).c().a(4.0F, 4.0F).trackingRange(10));
/*  47 */   public static final EntityTypes<EntityGiantZombie> GIANT = a("giant", Builder.<Entity>a(EntityGiantZombie::new, EnumCreatureType.MONSTER).a(3.6F, 12.0F).trackingRange(10));
/*  48 */   public static final EntityTypes<EntityGuardian> GUARDIAN = a("guardian", Builder.<Entity>a(EntityGuardian::new, EnumCreatureType.MONSTER).a(0.85F, 0.85F).trackingRange(8));
/*  49 */   public static final EntityTypes<EntityHoglin> HOGLIN = a("hoglin", Builder.<Entity>a(EntityHoglin::new, EnumCreatureType.MONSTER).a(1.3964844F, 1.4F).trackingRange(8));
/*  50 */   public static final EntityTypes<EntityHorse> HORSE = a("horse", Builder.<Entity>a(EntityHorse::new, EnumCreatureType.CREATURE).a(1.3964844F, 1.6F).trackingRange(10));
/*  51 */   public static final EntityTypes<EntityZombieHusk> HUSK = a("husk", Builder.<Entity>a(EntityZombieHusk::new, EnumCreatureType.MONSTER).a(0.6F, 1.95F).trackingRange(8));
/*  52 */   public static final EntityTypes<EntityIllagerIllusioner> ILLUSIONER = a("illusioner", Builder.<Entity>a(EntityIllagerIllusioner::new, EnumCreatureType.MONSTER).a(0.6F, 1.95F).trackingRange(8));
/*  53 */   public static final EntityTypes<EntityIronGolem> IRON_GOLEM = a("iron_golem", Builder.<Entity>a(EntityIronGolem::new, EnumCreatureType.MISC).a(1.4F, 2.7F).trackingRange(10));
/*  54 */   public static final EntityTypes<EntityItem> ITEM = a("item", Builder.<Entity>a(EntityItem::new, EnumCreatureType.MISC).a(0.25F, 0.25F).trackingRange(6).updateInterval(20));
/*  55 */   public static final EntityTypes<EntityItemFrame> ITEM_FRAME = a("item_frame", Builder.<Entity>a(EntityItemFrame::new, EnumCreatureType.MISC).a(0.5F, 0.5F).trackingRange(10).updateInterval(2147483647));
/*  56 */   public static final EntityTypes<EntityLargeFireball> FIREBALL = a("fireball", Builder.<Entity>a(EntityLargeFireball::new, EnumCreatureType.MISC).a(1.0F, 1.0F).trackingRange(4).updateInterval(10));
/*  57 */   public static final EntityTypes<EntityLeash> LEASH_KNOT = a("leash_knot", Builder.<Entity>a(EntityLeash::new, EnumCreatureType.MISC).b().a(0.5F, 0.5F).trackingRange(10).updateInterval(2147483647));
/*  58 */   public static final EntityTypes<EntityLightning> LIGHTNING_BOLT = a("lightning_bolt", Builder.<Entity>a(EntityLightning::new, EnumCreatureType.MISC).b().a(0.0F, 0.0F).trackingRange(16).updateInterval(2147483647));
/*  59 */   public static final EntityTypes<EntityLlama> LLAMA = a("llama", Builder.<Entity>a(EntityLlama::new, EnumCreatureType.CREATURE).a(0.9F, 1.87F).trackingRange(10));
/*  60 */   public static final EntityTypes<EntityLlamaSpit> LLAMA_SPIT = a("llama_spit", Builder.<Entity>a(EntityLlamaSpit::new, EnumCreatureType.MISC).a(0.25F, 0.25F).trackingRange(4).updateInterval(10));
/*  61 */   public static final EntityTypes<EntityMagmaCube> MAGMA_CUBE = a("magma_cube", Builder.<Entity>a(EntityMagmaCube::new, EnumCreatureType.MONSTER).c().a(2.04F, 2.04F).trackingRange(8));
/*  62 */   public static final EntityTypes<EntityMinecartRideable> MINECART = a("minecart", Builder.<Entity>a(EntityMinecartRideable::new, EnumCreatureType.MISC).a(0.98F, 0.7F).trackingRange(8));
/*  63 */   public static final EntityTypes<EntityMinecartChest> CHEST_MINECART = a("chest_minecart", Builder.<Entity>a(EntityMinecartChest::new, EnumCreatureType.MISC).a(0.98F, 0.7F).trackingRange(8));
/*  64 */   public static final EntityTypes<EntityMinecartCommandBlock> COMMAND_BLOCK_MINECART = a("command_block_minecart", Builder.<Entity>a(EntityMinecartCommandBlock::new, EnumCreatureType.MISC).a(0.98F, 0.7F).trackingRange(8));
/*  65 */   public static final EntityTypes<EntityMinecartFurnace> FURNACE_MINECART = a("furnace_minecart", Builder.<Entity>a(EntityMinecartFurnace::new, EnumCreatureType.MISC).a(0.98F, 0.7F).trackingRange(8));
/*  66 */   public static final EntityTypes<EntityMinecartHopper> HOPPER_MINECART = a("hopper_minecart", Builder.<Entity>a(EntityMinecartHopper::new, EnumCreatureType.MISC).a(0.98F, 0.7F).trackingRange(8));
/*  67 */   public static final EntityTypes<EntityMinecartMobSpawner> SPAWNER_MINECART = a("spawner_minecart", Builder.<Entity>a(EntityMinecartMobSpawner::new, EnumCreatureType.MISC).a(0.98F, 0.7F).trackingRange(8));
/*  68 */   public static final EntityTypes<EntityMinecartTNT> TNT_MINECART = a("tnt_minecart", Builder.<Entity>a(EntityMinecartTNT::new, EnumCreatureType.MISC).a(0.98F, 0.7F).trackingRange(8));
/*  69 */   public static final EntityTypes<EntityHorseMule> MULE = a("mule", Builder.<Entity>a(EntityHorseMule::new, EnumCreatureType.CREATURE).a(1.3964844F, 1.6F).trackingRange(8));
/*  70 */   public static final EntityTypes<EntityMushroomCow> MOOSHROOM = a("mooshroom", Builder.<Entity>a(EntityMushroomCow::new, EnumCreatureType.CREATURE).a(0.9F, 1.4F).trackingRange(10));
/*  71 */   public static final EntityTypes<EntityOcelot> OCELOT = a("ocelot", Builder.<Entity>a(EntityOcelot::new, EnumCreatureType.CREATURE).a(0.6F, 0.7F).trackingRange(10));
/*  72 */   public static final EntityTypes<EntityPainting> PAINTING = a("painting", Builder.<Entity>a(EntityPainting::new, EnumCreatureType.MISC).a(0.5F, 0.5F).trackingRange(10).updateInterval(2147483647));
/*  73 */   public static final EntityTypes<EntityPanda> PANDA = a("panda", Builder.<Entity>a(EntityPanda::new, EnumCreatureType.CREATURE).a(1.3F, 1.25F).trackingRange(10));
/*  74 */   public static final EntityTypes<EntityParrot> PARROT = a("parrot", Builder.<Entity>a(EntityParrot::new, EnumCreatureType.CREATURE).a(0.5F, 0.9F).trackingRange(8));
/*  75 */   public static final EntityTypes<EntityPhantom> PHANTOM = a("phantom", Builder.<Entity>a(EntityPhantom::new, EnumCreatureType.MONSTER).a(0.9F, 0.5F).trackingRange(8));
/*  76 */   public static final EntityTypes<EntityPig> PIG = a("pig", Builder.<Entity>a(EntityPig::new, EnumCreatureType.CREATURE).a(0.9F, 0.9F).trackingRange(10));
/*  77 */   public static final EntityTypes<EntityPiglin> PIGLIN = a("piglin", Builder.<Entity>a(EntityPiglin::new, EnumCreatureType.MONSTER).a(0.6F, 1.95F).trackingRange(8));
/*  78 */   public static final EntityTypes<EntityPiglinBrute> PIGLIN_BRUTE = a("piglin_brute", Builder.<Entity>a(EntityPiglinBrute::new, EnumCreatureType.MONSTER).a(0.6F, 1.95F).trackingRange(8));
/*  79 */   public static final EntityTypes<EntityPillager> PILLAGER = a("pillager", Builder.<Entity>a(EntityPillager::new, EnumCreatureType.MONSTER).d().a(0.6F, 1.95F).trackingRange(8));
/*  80 */   public static final EntityTypes<EntityPolarBear> POLAR_BEAR = a("polar_bear", Builder.<Entity>a(EntityPolarBear::new, EnumCreatureType.CREATURE).a(1.4F, 1.4F).trackingRange(10));
/*  81 */   public static final EntityTypes<EntityTNTPrimed> TNT = a("tnt", Builder.<Entity>a(EntityTNTPrimed::new, EnumCreatureType.MISC).c().a(0.98F, 0.98F).trackingRange(10).updateInterval(10));
/*  82 */   public static final EntityTypes<EntityPufferFish> PUFFERFISH = a("pufferfish", Builder.<Entity>a(EntityPufferFish::new, EnumCreatureType.WATER_AMBIENT).a(0.7F, 0.7F).trackingRange(4));
/*  83 */   public static final EntityTypes<EntityRabbit> RABBIT = a("rabbit", Builder.<Entity>a(EntityRabbit::new, EnumCreatureType.CREATURE).a(0.4F, 0.5F).trackingRange(8));
/*  84 */   public static final EntityTypes<EntityRavager> RAVAGER = a("ravager", Builder.<Entity>a(EntityRavager::new, EnumCreatureType.MONSTER).a(1.95F, 2.2F).trackingRange(10));
/*  85 */   public static final EntityTypes<EntitySalmon> SALMON = a("salmon", Builder.<Entity>a(EntitySalmon::new, EnumCreatureType.WATER_AMBIENT).a(0.7F, 0.4F).trackingRange(4));
/*  86 */   public static final EntityTypes<EntitySheep> SHEEP = a("sheep", Builder.<Entity>a(EntitySheep::new, EnumCreatureType.CREATURE).a(0.9F, 1.3F).trackingRange(10));
/*  87 */   public static final EntityTypes<EntityShulker> SHULKER = a("shulker", Builder.<Entity>a(EntityShulker::new, EnumCreatureType.MONSTER).c().d().a(1.0F, 1.0F).trackingRange(10));
/*  88 */   public static final EntityTypes<EntityShulkerBullet> SHULKER_BULLET = a("shulker_bullet", Builder.<Entity>a(EntityShulkerBullet::new, EnumCreatureType.MISC).a(0.3125F, 0.3125F).trackingRange(8));
/*  89 */   public static final EntityTypes<EntitySilverfish> SILVERFISH = a("silverfish", Builder.<Entity>a(EntitySilverfish::new, EnumCreatureType.MONSTER).a(0.4F, 0.3F).trackingRange(8));
/*  90 */   public static final EntityTypes<EntitySkeleton> SKELETON = a("skeleton", Builder.<Entity>a(EntitySkeleton::new, EnumCreatureType.MONSTER).a(0.6F, 1.99F).trackingRange(8));
/*  91 */   public static final EntityTypes<EntityHorseSkeleton> SKELETON_HORSE = a("skeleton_horse", Builder.<Entity>a(EntityHorseSkeleton::new, EnumCreatureType.CREATURE).a(1.3964844F, 1.6F).trackingRange(10));
/*  92 */   public static final EntityTypes<EntitySlime> SLIME = a("slime", Builder.<Entity>a(EntitySlime::new, EnumCreatureType.MONSTER).a(2.04F, 2.04F).trackingRange(10));
/*  93 */   public static final EntityTypes<EntitySmallFireball> SMALL_FIREBALL = a("small_fireball", Builder.<Entity>a(EntitySmallFireball::new, EnumCreatureType.MISC).a(0.3125F, 0.3125F).trackingRange(4).updateInterval(10));
/*  94 */   public static final EntityTypes<EntitySnowman> SNOW_GOLEM = a("snow_golem", Builder.<Entity>a(EntitySnowman::new, EnumCreatureType.MISC).a(0.7F, 1.9F).trackingRange(8));
/*  95 */   public static final EntityTypes<EntitySnowball> SNOWBALL = a("snowball", Builder.<Entity>a(EntitySnowball::new, EnumCreatureType.MISC).a(0.25F, 0.25F).trackingRange(4).updateInterval(10));
/*  96 */   public static final EntityTypes<EntitySpectralArrow> SPECTRAL_ARROW = a("spectral_arrow", Builder.<Entity>a(EntitySpectralArrow::new, EnumCreatureType.MISC).a(0.5F, 0.5F).trackingRange(4).updateInterval(20));
/*  97 */   public static final EntityTypes<EntitySpider> SPIDER = a("spider", Builder.<Entity>a(EntitySpider::new, EnumCreatureType.MONSTER).a(1.4F, 0.9F).trackingRange(8));
/*  98 */   public static final EntityTypes<EntitySquid> SQUID = a("squid", Builder.<Entity>a(EntitySquid::new, EnumCreatureType.WATER_CREATURE).a(0.8F, 0.8F).trackingRange(8));
/*  99 */   public static final EntityTypes<EntitySkeletonStray> STRAY = a("stray", Builder.<Entity>a(EntitySkeletonStray::new, EnumCreatureType.MONSTER).a(0.6F, 1.99F).trackingRange(8));
/* 100 */   public static final EntityTypes<EntityStrider> STRIDER = a("strider", Builder.<Entity>a(EntityStrider::new, EnumCreatureType.CREATURE).c().a(0.9F, 1.7F).trackingRange(10));
/* 101 */   public static final EntityTypes<EntityEgg> EGG = a("egg", Builder.<Entity>a(EntityEgg::new, EnumCreatureType.MISC).a(0.25F, 0.25F).trackingRange(4).updateInterval(10));
/* 102 */   public static final EntityTypes<EntityEnderPearl> ENDER_PEARL = a("ender_pearl", Builder.<Entity>a(EntityEnderPearl::new, EnumCreatureType.MISC).a(0.25F, 0.25F).trackingRange(4).updateInterval(10));
/* 103 */   public static final EntityTypes<EntityThrownExpBottle> EXPERIENCE_BOTTLE = a("experience_bottle", Builder.<Entity>a(EntityThrownExpBottle::new, EnumCreatureType.MISC).a(0.25F, 0.25F).trackingRange(4).updateInterval(10));
/* 104 */   public static final EntityTypes<EntityPotion> POTION = a("potion", Builder.<Entity>a(EntityPotion::new, EnumCreatureType.MISC).a(0.25F, 0.25F).trackingRange(4).updateInterval(10));
/* 105 */   public static final EntityTypes<EntityThrownTrident> TRIDENT = a("trident", Builder.<Entity>a(EntityThrownTrident::new, EnumCreatureType.MISC).a(0.5F, 0.5F).trackingRange(4).updateInterval(20));
/* 106 */   public static final EntityTypes<EntityLlamaTrader> TRADER_LLAMA = a("trader_llama", Builder.<Entity>a(EntityLlamaTrader::new, EnumCreatureType.CREATURE).a(0.9F, 1.87F).trackingRange(10));
/* 107 */   public static final EntityTypes<EntityTropicalFish> TROPICAL_FISH = a("tropical_fish", Builder.<Entity>a(EntityTropicalFish::new, EnumCreatureType.WATER_AMBIENT).a(0.5F, 0.4F).trackingRange(4));
/* 108 */   public static final EntityTypes<EntityTurtle> TURTLE = a("turtle", Builder.<Entity>a(EntityTurtle::new, EnumCreatureType.CREATURE).a(1.2F, 0.4F).trackingRange(10));
/* 109 */   public static final EntityTypes<EntityVex> VEX = a("vex", Builder.<Entity>a(EntityVex::new, EnumCreatureType.MONSTER).c().a(0.4F, 0.8F).trackingRange(8));
/* 110 */   public static final EntityTypes<EntityVillager> VILLAGER = a("villager", Builder.<Entity>a(EntityVillager::new, EnumCreatureType.MISC).a(0.6F, 1.95F).trackingRange(10));
/* 111 */   public static final EntityTypes<EntityVindicator> VINDICATOR = a("vindicator", Builder.<Entity>a(EntityVindicator::new, EnumCreatureType.MONSTER).a(0.6F, 1.95F).trackingRange(8));
/* 112 */   public static final EntityTypes<EntityVillagerTrader> WANDERING_TRADER = a("wandering_trader", Builder.<Entity>a(EntityVillagerTrader::new, EnumCreatureType.CREATURE).a(0.6F, 1.95F).trackingRange(10));
/* 113 */   public static final EntityTypes<EntityWitch> WITCH = a("witch", Builder.<Entity>a(EntityWitch::new, EnumCreatureType.MONSTER).a(0.6F, 1.95F).trackingRange(8));
/* 114 */   public static final EntityTypes<EntityWither> WITHER = a("wither", Builder.<Entity>a(EntityWither::new, EnumCreatureType.MONSTER).c().a(new Block[] { Blocks.WITHER_ROSE }).a(0.9F, 3.5F).trackingRange(10));
/* 115 */   public static final EntityTypes<EntitySkeletonWither> WITHER_SKELETON = a("wither_skeleton", Builder.<Entity>a(EntitySkeletonWither::new, EnumCreatureType.MONSTER).c().a(new Block[] { Blocks.WITHER_ROSE }).a(0.7F, 2.4F).trackingRange(8));
/* 116 */   public static final EntityTypes<EntityWitherSkull> WITHER_SKULL = a("wither_skull", Builder.<Entity>a(EntityWitherSkull::new, EnumCreatureType.MISC).a(0.3125F, 0.3125F).trackingRange(4).updateInterval(10));
/* 117 */   public static final EntityTypes<EntityWolf> WOLF = a("wolf", Builder.<Entity>a(EntityWolf::new, EnumCreatureType.CREATURE).a(0.6F, 0.85F).trackingRange(10));
/* 118 */   public static final EntityTypes<EntityZoglin> ZOGLIN = a("zoglin", Builder.<Entity>a(EntityZoglin::new, EnumCreatureType.MONSTER).c().a(1.3964844F, 1.4F).trackingRange(8));
/* 119 */   public static final EntityTypes<EntityZombie> ZOMBIE = a("zombie", Builder.<Entity>a(EntityZombie::new, EnumCreatureType.MONSTER).a(0.6F, 1.95F).trackingRange(8));
/* 120 */   public static final EntityTypes<EntityHorseZombie> ZOMBIE_HORSE = a("zombie_horse", Builder.<Entity>a(EntityHorseZombie::new, EnumCreatureType.CREATURE).a(1.3964844F, 1.6F).trackingRange(10));
/* 121 */   public static final EntityTypes<EntityZombieVillager> ZOMBIE_VILLAGER = a("zombie_villager", Builder.<Entity>a(EntityZombieVillager::new, EnumCreatureType.MONSTER).a(0.6F, 1.95F).trackingRange(8));
/* 122 */   public static final EntityTypes<EntityPigZombie> ZOMBIFIED_PIGLIN = a("zombified_piglin", Builder.<Entity>a(EntityPigZombie::new, EnumCreatureType.MONSTER).c().a(0.6F, 1.95F).trackingRange(8));
/* 123 */   public static final EntityTypes<EntityHuman> PLAYER = a("player", Builder.<Entity>a(EnumCreatureType.MISC).b().a().a(0.6F, 1.8F).trackingRange(32).updateInterval(2));
/* 124 */   public static final EntityTypes<EntityFishingHook> FISHING_BOBBER = a("fishing_bobber", Builder.<Entity>a(EnumCreatureType.MISC).b().a().a(0.25F, 0.25F).trackingRange(4).updateInterval(5));
/*     */   
/*     */   private final b<T> bf;
/*     */   
/*     */   private final EnumCreatureType bg;
/*     */   
/*     */   private final ImmutableSet<Block> bh;
/*     */   
/*     */   private final boolean bi;
/*     */   
/*     */   private final boolean bj;
/*     */   
/*     */   private final boolean bk;
/*     */   
/*     */   private final boolean bl;
/*     */   private final int bm;
/*     */   private final int bn;
/*     */   
/*     */   private static <T extends Entity> EntityTypes<T> a(String s, Builder<?> entitytypes_builder) {
/* 143 */     return (EntityTypes<T>)IRegistry.<EntityTypes<?>>a(IRegistry.ENTITY_TYPE, s, entitytypes_builder.a(s)); } @Nullable
/*     */   private String bo; @Nullable
/*     */   private IChatBaseComponent bp; @Nullable
/*     */   private MinecraftKey bq; private final EntitySize br; public final String id; public final Timing tickTimer; public final Timing inactiveTickTimer; public final Timing passengerTickTimer; public final Timing passengerInactiveTickTimer; public static MinecraftKey getName(EntityTypes<?> entitytypes) {
/* 147 */     return IRegistry.ENTITY_TYPE.getKey(entitytypes);
/*     */   }
/*     */   public static Optional<EntityTypes<?>> getByName(String name) {
/* 150 */     return a(name);
/*     */   } public static Optional<EntityTypes<?>> a(String s) {
/* 152 */     return IRegistry.ENTITY_TYPE.getOptional(MinecraftKey.a(s));
/*     */   }
/*     */   
/*     */   public EntityTypes(b<T> entitytypes_b, EnumCreatureType enumcreaturetype, boolean flag, boolean flag1, boolean flag2, boolean flag3, ImmutableSet<Block> immutableset, EntitySize entitysize, int i, int j) {
/* 156 */     this(entitytypes_b, enumcreaturetype, flag, flag1, flag2, flag3, immutableset, entitysize, i, j, "custom");
/*     */   } public EntityTypes(b<T> entitytypes_b, EnumCreatureType enumcreaturetype, boolean flag, boolean flag1, boolean flag2, boolean flag3, ImmutableSet<Block> immutableset, EntitySize entitysize, int i, int j, String id) {
/* 158 */     this.bf = entitytypes_b;
/* 159 */     this.bg = enumcreaturetype;
/* 160 */     this.bl = flag3;
/* 161 */     this.bi = flag;
/* 162 */     this.bj = flag1;
/* 163 */     this.bk = flag2;
/* 164 */     this.bh = immutableset;
/* 165 */     this.br = entitysize;
/* 166 */     this.bm = i;
/* 167 */     this.bn = j;
/*     */ 
/*     */     
/* 170 */     this.id = id;
/* 171 */     this.tickTimer = MinecraftTimings.getEntityTimings(id, "tick");
/* 172 */     this.inactiveTickTimer = MinecraftTimings.getEntityTimings(id, "inactiveTick");
/* 173 */     this.passengerTickTimer = MinecraftTimings.getEntityTimings(id, "passengerTick");
/* 174 */     this.passengerInactiveTickTimer = MinecraftTimings.getEntityTimings(id, "passengerInactiveTick");
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Entity spawnCreature(WorldServer worldserver, @Nullable ItemStack itemstack, @Nullable EntityHuman entityhuman, BlockPosition blockposition, EnumMobSpawn enummobspawn, boolean flag, boolean flag1) {
/* 180 */     return (Entity)spawnCreature(worldserver, (itemstack == null) ? null : itemstack.getTag(), (itemstack != null && itemstack.hasName()) ? itemstack.getName() : null, entityhuman, blockposition, enummobspawn, flag, flag1);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public T spawnCreature(WorldServer worldserver, @Nullable NBTTagCompound nbttagcompound, @Nullable IChatBaseComponent ichatbasecomponent, @Nullable EntityHuman entityhuman, BlockPosition blockposition, EnumMobSpawn enummobspawn, boolean flag, boolean flag1) {
/* 186 */     return spawnCreature(worldserver, nbttagcompound, ichatbasecomponent, entityhuman, blockposition, enummobspawn, flag, flag1, CreatureSpawnEvent.SpawnReason.SPAWNER_EGG);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public T spawnCreature(WorldServer worldserver, @Nullable NBTTagCompound nbttagcompound, @Nullable IChatBaseComponent ichatbasecomponent, @Nullable EntityHuman entityhuman, BlockPosition blockposition, EnumMobSpawn enummobspawn, boolean flag, boolean flag1, CreatureSpawnEvent.SpawnReason spawnReason) {
/* 191 */     T t0 = createCreature(worldserver, nbttagcompound, ichatbasecomponent, entityhuman, blockposition, enummobspawn, flag, flag1);
/*     */     
/* 193 */     if (t0 != null) {
/* 194 */       return worldserver.addAllEntities((Entity)t0, spawnReason) ? t0 : null;
/*     */     }
/*     */ 
/*     */     
/* 198 */     return t0;
/*     */   }
/*     */   @Nullable
/*     */   public T createCreature(WorldServer worldserver, @Nullable NBTTagCompound nbttagcompound, @Nullable IChatBaseComponent ichatbasecomponent, @Nullable EntityHuman entityhuman, BlockPosition blockposition, EnumMobSpawn enummobspawn, boolean flag, boolean flag1) {
/*     */     double d0;
/* 203 */     T t0 = a(worldserver);
/*     */     
/* 205 */     if (t0 == null) {
/* 206 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 210 */     if (flag) {
/* 211 */       t0.setPosition(blockposition.getX() + 0.5D, (blockposition.getY() + 1), blockposition.getZ() + 0.5D);
/* 212 */       d0 = a(worldserver, blockposition, flag1, t0.getBoundingBox());
/*     */     } else {
/* 214 */       d0 = 0.0D;
/*     */     } 
/*     */     
/* 217 */     t0.setPositionRotation(blockposition.getX() + 0.5D, blockposition.getY() + d0, blockposition.getZ() + 0.5D, MathHelper.g(worldserver.random.nextFloat() * 360.0F), 0.0F);
/* 218 */     if (t0 instanceof EntityInsentient) {
/* 219 */       EntityInsentient entityinsentient = (EntityInsentient)t0;
/*     */       
/* 221 */       entityinsentient.aC = entityinsentient.yaw;
/* 222 */       entityinsentient.aA = entityinsentient.yaw;
/* 223 */       entityinsentient.prepare(worldserver, worldserver.getDamageScaler(entityinsentient.getChunkCoordinates()), enummobspawn, (GroupDataEntity)null, nbttagcompound);
/* 224 */       entityinsentient.F();
/*     */     } 
/*     */     
/* 227 */     if (ichatbasecomponent != null && t0 instanceof EntityLiving) {
/* 228 */       t0.setCustomName(ichatbasecomponent);
/*     */     }
/*     */     
/* 231 */     try { a(worldserver, entityhuman, (Entity)t0, nbttagcompound); } catch (Throwable t) { LOGGER.warn("Error loading spawn egg NBT", t); }
/* 232 */      return t0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static double a(IWorldReader iworldreader, BlockPosition blockposition, boolean flag, AxisAlignedBB axisalignedbb) {
/* 237 */     AxisAlignedBB axisalignedbb1 = new AxisAlignedBB(blockposition);
/*     */     
/* 239 */     if (flag) {
/* 240 */       axisalignedbb1 = axisalignedbb1.b(0.0D, -1.0D, 0.0D);
/*     */     }
/*     */     
/* 243 */     Stream<VoxelShape> stream = iworldreader.d((Entity)null, axisalignedbb1, entity -> true);
/*     */ 
/*     */ 
/*     */     
/* 247 */     return 1.0D + VoxelShapes.a(EnumDirection.EnumAxis.Y, axisalignedbb, stream, flag ? -2.0D : -1.0D);
/*     */   }
/*     */   
/*     */   public static void a(World world, @Nullable EntityHuman entityhuman, @Nullable Entity entity, @Nullable NBTTagCompound nbttagcompound) {
/* 251 */     if (nbttagcompound != null && nbttagcompound.hasKeyOfType("EntityTag", 10)) {
/* 252 */       MinecraftServer minecraftserver = world.getMinecraftServer();
/*     */       
/* 254 */       if (minecraftserver != null && entity != null && (
/* 255 */         world.isClientSide || !entity.ci() || (entityhuman != null && minecraftserver.getPlayerList().isOp(entityhuman.getProfile())))) {
/* 256 */         NBTTagCompound nbttagcompound1 = entity.save(new NBTTagCompound());
/* 257 */         UUID uuid = entity.getUniqueID();
/*     */         
/* 259 */         nbttagcompound1.a(nbttagcompound.getCompound("EntityTag"));
/* 260 */         entity.a_(uuid);
/* 261 */         entity.load(nbttagcompound1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isPersistable() {
/* 267 */     return a();
/*     */   } public boolean a() {
/* 269 */     return this.bi;
/*     */   }
/*     */   
/*     */   public boolean b() {
/* 273 */     return this.bj;
/*     */   }
/*     */   
/*     */   public boolean c() {
/* 277 */     return this.bk;
/*     */   }
/*     */   
/*     */   public boolean d() {
/* 281 */     return this.bl;
/*     */   }
/*     */   public final EnumCreatureType getEnumCreatureType() {
/* 284 */     return e();
/*     */   } public EnumCreatureType e() {
/* 286 */     return this.bg;
/*     */   }
/*     */   public String getDescriptionId() {
/* 289 */     return f();
/*     */   } public String f() {
/* 291 */     if (this.bo == null) {
/* 292 */       this.bo = SystemUtils.a("entity", IRegistry.ENTITY_TYPE.getKey(this));
/*     */     }
/*     */     
/* 295 */     return this.bo;
/*     */   }
/*     */   
/*     */   public IChatBaseComponent g() {
/* 299 */     if (this.bp == null) {
/* 300 */       this.bp = new ChatMessage(f());
/*     */     }
/*     */     
/* 303 */     return this.bp;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 307 */     return f();
/*     */   }
/*     */   
/*     */   public MinecraftKey i() {
/* 311 */     if (this.bq == null) {
/* 312 */       MinecraftKey minecraftkey = IRegistry.ENTITY_TYPE.getKey(this);
/*     */       
/* 314 */       this.bq = new MinecraftKey(minecraftkey.getNamespace(), "entities/" + minecraftkey.getKey());
/*     */     } 
/*     */     
/* 317 */     return this.bq;
/*     */   }
/*     */   
/*     */   public float j() {
/* 321 */     return this.br.width;
/*     */   }
/*     */   
/*     */   public float k() {
/* 325 */     return this.br.height;
/*     */   }
/*     */   
/* 328 */   public T create(World world) { return a(world); } @Nullable
/*     */   public T a(World world) {
/* 330 */     return this.bf.create(this, world);
/*     */   }
/*     */   
/*     */   public static Optional<Entity> a(NBTTagCompound nbttagcompound, World world) {
/* 334 */     return SystemUtils.a(a(nbttagcompound).map(entitytypes -> entitytypes.a(world)), entity -> entity.load(nbttagcompound), () -> LOGGER.warn("Skipping Entity with id {}", nbttagcompound.getString("id")));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB a(double d0, double d1, double d2) {
/* 344 */     float f = j() / 2.0F;
/*     */     
/* 346 */     return new AxisAlignedBB(d0 - f, d1, d2 - f, d0 + f, d1 + k(), d2 + f);
/*     */   }
/*     */   
/*     */   public boolean a(IBlockData iblockdata) {
/* 350 */     return this.bh.contains(iblockdata.getBlock()) ? false : ((!this.bk && (iblockdata.a(TagsBlock.FIRE) || iblockdata.a(Blocks.MAGMA_BLOCK) || BlockCampfire.g(iblockdata) || iblockdata.a(Blocks.LAVA))) ? true : ((iblockdata.a(Blocks.WITHER_ROSE) || iblockdata.a(Blocks.SWEET_BERRY_BUSH) || iblockdata.a(Blocks.CACTUS))));
/*     */   }
/*     */   
/*     */   public EntitySize l() {
/* 354 */     return this.br;
/*     */   }
/*     */   
/*     */   public static Optional<EntityTypes<?>> a(NBTTagCompound nbttagcompound) {
/* 358 */     return IRegistry.ENTITY_TYPE.getOptional(new MinecraftKey(nbttagcompound.getString("id")));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static Entity a(NBTTagCompound nbttagcompound, World world, Function<Entity, Entity> function) {
/* 363 */     return b(nbttagcompound, world).<Entity>map(function).map(entity -> {
/*     */           if (nbttagcompound.hasKeyOfType("Passengers", 9)) {
/*     */             NBTTagList nbttaglist = nbttagcompound.getList("Passengers", 10);
/*     */             
/*     */             for (int i = 0; i < nbttaglist.size(); i++) {
/*     */               Entity entity1 = a(nbttaglist.getCompound(i), world, function);
/*     */               
/*     */               if (entity1 != null) {
/*     */                 entity1.a(entity, true);
/*     */               }
/*     */             } 
/*     */           } 
/*     */           
/*     */           return entity;
/* 377 */         }).orElse(null);
/*     */   }
/*     */   
/*     */   private static Optional<Entity> b(NBTTagCompound nbttagcompound, World world) {
/*     */     try {
/* 382 */       return a(nbttagcompound, world);
/* 383 */     } catch (RuntimeException runtimeexception) {
/* 384 */       LOGGER.warn("Exception loading entity: ", runtimeexception);
/* 385 */       return Optional.empty();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getChunkRange() {
/* 390 */     return this.bm;
/*     */   }
/*     */   
/*     */   public int getUpdateInterval() {
/* 394 */     return this.bn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDeltaTracking() {
/* 404 */     return (this != PLAYER && this != LLAMA_SPIT && this != WITHER && this != BAT && this != ITEM_FRAME && this != LEASH_KNOT && this != PAINTING && this != END_CRYSTAL && this != EVOKER_FANGS);
/*     */   }
/*     */   
/*     */   public boolean a(Tag<EntityTypes<?>> tag) {
/* 408 */     return tag.isTagged(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Builder<T extends Entity>
/*     */   {
/*     */     private final EntityTypes.b<T> a;
/*     */ 
/*     */     
/*     */     private final EnumCreatureType b;
/*     */     
/* 420 */     private ImmutableSet<Block> c = ImmutableSet.of();
/*     */     private boolean d = true;
/*     */     private boolean e = true;
/*     */     private boolean f;
/*     */     private boolean g;
/* 425 */     private int h = 5;
/* 426 */     private int i = 3;
/* 427 */     private EntitySize j = EntitySize.b(0.6F, 1.8F);
/*     */     
/*     */     private Builder(EntityTypes.b<T> entitytypes_b, EnumCreatureType enumcreaturetype) {
/* 430 */       this.a = entitytypes_b;
/* 431 */       this.b = enumcreaturetype;
/* 432 */       this.g = (enumcreaturetype == EnumCreatureType.CREATURE || enumcreaturetype == EnumCreatureType.MISC);
/*     */     }
/*     */     
/*     */     public static <T extends Entity> Builder<T> a(EntityTypes.b<T> entitytypes_b, EnumCreatureType enumcreaturetype) {
/* 436 */       return new Builder<>(entitytypes_b, enumcreaturetype);
/*     */     }
/*     */     
/*     */     public static <T extends Entity> Builder<T> a(EnumCreatureType enumcreaturetype) {
/* 440 */       return new Builder<>((entitytypes, world) -> null, enumcreaturetype);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder<T> a(float f, float f1) {
/* 446 */       this.j = EntitySize.b(f, f1);
/* 447 */       return this;
/*     */     }
/*     */     
/*     */     public Builder<T> a() {
/* 451 */       this.e = false;
/* 452 */       return this;
/*     */     }
/*     */     
/*     */     public Builder<T> b() {
/* 456 */       this.d = false;
/* 457 */       return this;
/*     */     }
/*     */     
/*     */     public Builder<T> c() {
/* 461 */       this.f = true;
/* 462 */       return this;
/*     */     }
/*     */     
/*     */     public Builder<T> a(Block... ablock) {
/* 466 */       this.c = ImmutableSet.copyOf((Object[])ablock);
/* 467 */       return this;
/*     */     }
/*     */     
/*     */     public Builder<T> d() {
/* 471 */       this.g = true;
/* 472 */       return this;
/*     */     }
/*     */     
/*     */     public Builder<T> trackingRange(int i) {
/* 476 */       this.h = i;
/* 477 */       return this;
/*     */     }
/*     */     
/*     */     public Builder<T> updateInterval(int i) {
/* 481 */       this.i = i;
/* 482 */       return this;
/*     */     }
/*     */     
/*     */     public EntityTypes<T> a(String s) {
/* 486 */       if (this.d) {
/* 487 */         SystemUtils.a(DataConverterTypes.ENTITY_TREE, s);
/*     */       }
/*     */       
/* 490 */       return new EntityTypes<>(this.a, this.b, this.d, this.e, this.f, this.g, this.c, this.j, this.h, this.i, s);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static Set<MinecraftKey> getEntityNameList() {
/* 496 */     return IRegistry.ENTITY_TYPE.keySet();
/*     */   }
/*     */   
/*     */   public static interface b<T extends Entity> {
/*     */     T create(EntityTypes<T> param1EntityTypes, World param1World);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityTypes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */