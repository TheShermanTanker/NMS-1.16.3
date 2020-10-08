/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.Map;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
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
/*     */ public class AttributeDefaults
/*     */ {
/*  74 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*  76 */   private static final Map<EntityTypes<? extends EntityLiving>, AttributeProvider> b = (Map<EntityTypes<? extends EntityLiving>, AttributeProvider>)ImmutableMap.builder()
/*  77 */     .put(EntityTypes.ARMOR_STAND, EntityLiving.cK().a())
/*  78 */     .put(EntityTypes.BAT, EntityBat.m().a())
/*  79 */     .put(EntityTypes.BEE, EntityBee.eZ().a())
/*  80 */     .put(EntityTypes.BLAZE, EntityBlaze.m().a())
/*  81 */     .put(EntityTypes.CAT, EntityCat.fa().a())
/*  82 */     .put(EntityTypes.CAVE_SPIDER, EntityCaveSpider.m().a())
/*  83 */     .put(EntityTypes.CHICKEN, EntityChicken.eK().a())
/*  84 */     .put(EntityTypes.COD, EntityFish.m().a())
/*  85 */     .put(EntityTypes.COW, EntityCow.eK().a())
/*  86 */     .put(EntityTypes.CREEPER, EntityCreeper.m().a())
/*  87 */     .put(EntityTypes.DOLPHIN, EntityDolphin.eM().a())
/*  88 */     .put(EntityTypes.DONKEY, EntityHorseChestedAbstract.eL().a())
/*  89 */     .put(EntityTypes.DROWNED, EntityZombie.eS().a())
/*  90 */     .put(EntityTypes.ELDER_GUARDIAN, EntityGuardianElder.m().a())
/*  91 */     .put(EntityTypes.ENDERMAN, EntityEnderman.m().a())
/*  92 */     .put(EntityTypes.ENDERMITE, EntityEndermite.m().a())
/*  93 */     .put(EntityTypes.ENDER_DRAGON, EntityEnderDragon.m().a())
/*  94 */     .put(EntityTypes.EVOKER, EntityEvoker.eK().a())
/*  95 */     .put(EntityTypes.FOX, EntityFox.eK().a())
/*  96 */     .put(EntityTypes.GHAST, EntityGhast.eJ().a())
/*  97 */     .put(EntityTypes.GIANT, EntityGiantZombie.m().a())
/*  98 */     .put(EntityTypes.GUARDIAN, EntityGuardian.eM().a())
/*  99 */     .put(EntityTypes.HOGLIN, EntityHoglin.eK().a())
/* 100 */     .put(EntityTypes.HORSE, EntityHorseAbstract.fi().a())
/* 101 */     .put(EntityTypes.HUSK, EntityZombie.eS().a())
/* 102 */     .put(EntityTypes.ILLUSIONER, EntityIllagerIllusioner.eK().a())
/* 103 */     .put(EntityTypes.IRON_GOLEM, EntityIronGolem.m().a())
/* 104 */     .put(EntityTypes.LLAMA, EntityLlama.fw().a())
/* 105 */     .put(EntityTypes.MAGMA_CUBE, EntityMagmaCube.m().a())
/* 106 */     .put(EntityTypes.MOOSHROOM, EntityCow.eK().a())
/* 107 */     .put(EntityTypes.MULE, EntityHorseChestedAbstract.eL().a())
/* 108 */     .put(EntityTypes.OCELOT, EntityOcelot.eK().a())
/* 109 */     .put(EntityTypes.PANDA, EntityPanda.eY().a())
/* 110 */     .put(EntityTypes.PARROT, EntityParrot.eU().a())
/* 111 */     .put(EntityTypes.PHANTOM, EntityMonster.eR().a())
/* 112 */     .put(EntityTypes.PIG, EntityPig.eK().a())
/* 113 */     .put(EntityTypes.PIGLIN, EntityPiglin.eT().a())
/* 114 */     .put(EntityTypes.PIGLIN_BRUTE, EntityPiglinBrute.eS().a())
/* 115 */     .put(EntityTypes.PILLAGER, EntityPillager.eK().a())
/* 116 */     .put(EntityTypes.PLAYER, EntityHuman.eo().a())
/* 117 */     .put(EntityTypes.POLAR_BEAR, EntityPolarBear.eK().a())
/* 118 */     .put(EntityTypes.PUFFERFISH, EntityFish.m().a())
/* 119 */     .put(EntityTypes.RABBIT, EntityRabbit.eL().a())
/* 120 */     .put(EntityTypes.RAVAGER, EntityRavager.m().a())
/* 121 */     .put(EntityTypes.SALMON, EntityFish.m().a())
/* 122 */     .put(EntityTypes.SHEEP, EntitySheep.eK().a())
/* 123 */     .put(EntityTypes.SHULKER, EntityShulker.m().a())
/* 124 */     .put(EntityTypes.SILVERFISH, EntitySilverfish.m().a())
/* 125 */     .put(EntityTypes.SKELETON, EntitySkeletonAbstract.m().a())
/* 126 */     .put(EntityTypes.SKELETON_HORSE, EntityHorseSkeleton.eL().a())
/* 127 */     .put(EntityTypes.SLIME, EntityMonster.eR().a())
/* 128 */     .put(EntityTypes.SNOW_GOLEM, EntitySnowman.m().a())
/* 129 */     .put(EntityTypes.SPIDER, EntitySpider.eK().a())
/* 130 */     .put(EntityTypes.SQUID, EntitySquid.m().a())
/* 131 */     .put(EntityTypes.STRAY, EntitySkeletonAbstract.m().a())
/* 132 */     .put(EntityTypes.STRIDER, EntityStrider.eM().a())
/* 133 */     .put(EntityTypes.TRADER_LLAMA, EntityLlama.fw().a())
/* 134 */     .put(EntityTypes.TROPICAL_FISH, EntityFish.m().a())
/* 135 */     .put(EntityTypes.TURTLE, EntityTurtle.eM().a())
/* 136 */     .put(EntityTypes.VEX, EntityVex.m().a())
/* 137 */     .put(EntityTypes.VILLAGER, EntityVillager.eY().a())
/* 138 */     .put(EntityTypes.VINDICATOR, EntityVindicator.eK().a())
/* 139 */     .put(EntityTypes.WANDERING_TRADER, EntityInsentient.p().a())
/* 140 */     .put(EntityTypes.WITCH, EntityWitch.eK().a())
/* 141 */     .put(EntityTypes.WITHER, EntityWither.eK().a())
/* 142 */     .put(EntityTypes.WITHER_SKELETON, EntitySkeletonAbstract.m().a())
/* 143 */     .put(EntityTypes.WOLF, EntityWolf.eU().a())
/* 144 */     .put(EntityTypes.ZOGLIN, EntityZoglin.m().a())
/* 145 */     .put(EntityTypes.ZOMBIE, EntityZombie.eS().a())
/* 146 */     .put(EntityTypes.ZOMBIE_HORSE, EntityHorseZombie.eL().a())
/* 147 */     .put(EntityTypes.ZOMBIE_VILLAGER, EntityZombie.eS().a())
/* 148 */     .put(EntityTypes.ZOMBIFIED_PIGLIN, EntityPigZombie.eW().a())
/* 149 */     .build();
/*     */   
/*     */   public static AttributeProvider a(EntityTypes<? extends EntityLiving> var0) {
/* 152 */     return b.get(var0);
/*     */   }
/*     */   
/*     */   public static boolean b(EntityTypes<?> var0) {
/* 156 */     return b.containsKey(var0);
/*     */   }
/*     */   
/*     */   public static void a() {
/* 160 */     IRegistry.ENTITY_TYPE.g()
/* 161 */       .filter(var0 -> (var0.e() != EnumCreatureType.MISC))
/* 162 */       .filter(var0 -> !b(var0))
/* 163 */       .map(IRegistry.ENTITY_TYPE::getKey)
/* 164 */       .forEach(var0 -> {
/*     */           if (SharedConstants.d)
/*     */             throw new IllegalStateException("Entity " + var0 + " has no attributes"); 
/*     */           LOGGER.error("Entity {} has no attributes", var0);
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\AttributeDefaults.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */