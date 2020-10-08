/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
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
/*     */ public class EntityPositionTypes
/*     */ {
/*     */   @FunctionalInterface
/*     */   public static interface b<T extends Entity>
/*     */   {
/*     */     boolean test(EntityTypes<T> param1EntityTypes, WorldAccess param1WorldAccess, EnumMobSpawn param1EnumMobSpawn, BlockPosition param1BlockPosition, Random param1Random);
/*     */   }
/*     */   
/*     */   static class a
/*     */   {
/*     */     private final HeightMap.Type a;
/*     */     private final EntityPositionTypes.Surface b;
/*     */     private final EntityPositionTypes.b<?> c;
/*     */     
/*     */     public a(HeightMap.Type var0, EntityPositionTypes.Surface var1, EntityPositionTypes.b<?> var2) {
/*  53 */       this.a = var0;
/*  54 */       this.b = var1;
/*  55 */       this.c = var2;
/*     */     }
/*     */   }
/*     */   
/*  59 */   private static final Map<EntityTypes<?>, a> a = Maps.newHashMap();
/*     */   
/*     */   private static <T extends EntityInsentient> void a(EntityTypes<T> var0, Surface var1, HeightMap.Type var2, b<T> var3) {
/*  62 */     a var4 = a.put(var0, new a(var2, var1, var3));
/*  63 */     if (var4 != null) {
/*  64 */       throw new IllegalStateException("Duplicate registration for type " + IRegistry.ENTITY_TYPE.getKey(var0));
/*     */     }
/*     */   }
/*     */   
/*     */   static {
/*  69 */     a(EntityTypes.COD, Surface.IN_WATER, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityFish::b);
/*  70 */     a(EntityTypes.DOLPHIN, Surface.IN_WATER, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityDolphin::b);
/*  71 */     a(EntityTypes.DROWNED, Surface.IN_WATER, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityDrowned::a);
/*  72 */     a(EntityTypes.GUARDIAN, Surface.IN_WATER, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityGuardian::b);
/*  73 */     a(EntityTypes.PUFFERFISH, Surface.IN_WATER, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityFish::b);
/*  74 */     a(EntityTypes.SALMON, Surface.IN_WATER, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityFish::b);
/*  75 */     a(EntityTypes.SQUID, Surface.IN_WATER, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntitySquid::b);
/*  76 */     a(EntityTypes.TROPICAL_FISH, Surface.IN_WATER, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityFish::b);
/*     */     
/*  78 */     a(EntityTypes.BAT, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityBat::b);
/*  79 */     a(EntityTypes.BLAZE, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityMonster::c);
/*  80 */     a(EntityTypes.CAVE_SPIDER, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityMonster::b);
/*  81 */     a(EntityTypes.CHICKEN, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityAnimal::b);
/*  82 */     a(EntityTypes.COW, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityAnimal::b);
/*  83 */     a(EntityTypes.CREEPER, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityMonster::b);
/*  84 */     a(EntityTypes.DONKEY, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityAnimal::b);
/*  85 */     a(EntityTypes.ENDERMAN, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityMonster::b);
/*  86 */     a(EntityTypes.ENDERMITE, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityEndermite::b);
/*  87 */     a(EntityTypes.ENDER_DRAGON, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityInsentient::a);
/*  88 */     a(EntityTypes.GHAST, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityGhast::b);
/*  89 */     a(EntityTypes.GIANT, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityMonster::b);
/*  90 */     a(EntityTypes.HORSE, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityAnimal::b);
/*  91 */     a(EntityTypes.HUSK, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityZombieHusk::a);
/*  92 */     a(EntityTypes.IRON_GOLEM, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityInsentient::a);
/*  93 */     a(EntityTypes.LLAMA, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityAnimal::b);
/*  94 */     a(EntityTypes.MAGMA_CUBE, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityMagmaCube::b);
/*  95 */     a(EntityTypes.MOOSHROOM, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityMushroomCow::c);
/*  96 */     a(EntityTypes.MULE, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityAnimal::b);
/*  97 */     a(EntityTypes.OCELOT, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING, EntityOcelot::c);
/*  98 */     a(EntityTypes.PARROT, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING, EntityParrot::c);
/*  99 */     a(EntityTypes.PIG, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityAnimal::b);
/* 100 */     a(EntityTypes.HOGLIN, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityHoglin::c);
/* 101 */     a(EntityTypes.PIGLIN, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityPiglin::b);
/* 102 */     a(EntityTypes.PILLAGER, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityMonsterPatrolling::b);
/* 103 */     a(EntityTypes.POLAR_BEAR, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityPolarBear::c);
/* 104 */     a(EntityTypes.RABBIT, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityRabbit::c);
/* 105 */     a(EntityTypes.SHEEP, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityAnimal::b);
/* 106 */     a(EntityTypes.SILVERFISH, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntitySilverfish::b);
/* 107 */     a(EntityTypes.SKELETON, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityMonster::b);
/* 108 */     a(EntityTypes.SKELETON_HORSE, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityAnimal::b);
/* 109 */     a(EntityTypes.SLIME, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntitySlime::c);
/* 110 */     a(EntityTypes.SNOW_GOLEM, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityInsentient::a);
/* 111 */     a(EntityTypes.SPIDER, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityMonster::b);
/* 112 */     a(EntityTypes.STRAY, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntitySkeletonStray::a);
/* 113 */     a(EntityTypes.STRIDER, Surface.IN_LAVA, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityStrider::c);
/* 114 */     a(EntityTypes.TURTLE, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityTurtle::c);
/* 115 */     a(EntityTypes.VILLAGER, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityInsentient::a);
/* 116 */     a(EntityTypes.WITCH, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityMonster::b);
/* 117 */     a(EntityTypes.WITHER, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityMonster::b);
/* 118 */     a(EntityTypes.WITHER_SKELETON, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityMonster::b);
/* 119 */     a(EntityTypes.WOLF, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityAnimal::b);
/* 120 */     a(EntityTypes.ZOMBIE, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityMonster::b);
/* 121 */     a(EntityTypes.ZOMBIE_HORSE, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityAnimal::b);
/* 122 */     a(EntityTypes.ZOMBIFIED_PIGLIN, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityPigZombie::b);
/* 123 */     a(EntityTypes.ZOMBIE_VILLAGER, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityMonster::b);
/*     */ 
/*     */     
/* 126 */     a(EntityTypes.CAT, Surface.ON_GROUND, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityAnimal::b);
/* 127 */     a(EntityTypes.ELDER_GUARDIAN, Surface.IN_WATER, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityGuardian::b);
/* 128 */     a(EntityTypes.EVOKER, Surface.NO_RESTRICTIONS, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityMonster::b);
/* 129 */     a(EntityTypes.FOX, Surface.NO_RESTRICTIONS, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityAnimal::b);
/* 130 */     a(EntityTypes.ILLUSIONER, Surface.NO_RESTRICTIONS, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityMonster::b);
/* 131 */     a(EntityTypes.PANDA, Surface.NO_RESTRICTIONS, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityAnimal::b);
/* 132 */     a(EntityTypes.PHANTOM, Surface.NO_RESTRICTIONS, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityInsentient::a);
/* 133 */     a(EntityTypes.RAVAGER, Surface.NO_RESTRICTIONS, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityMonster::b);
/* 134 */     a(EntityTypes.SHULKER, Surface.NO_RESTRICTIONS, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityInsentient::a);
/* 135 */     a(EntityTypes.TRADER_LLAMA, Surface.NO_RESTRICTIONS, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityAnimal::b);
/* 136 */     a(EntityTypes.VEX, Surface.NO_RESTRICTIONS, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityMonster::b);
/* 137 */     a(EntityTypes.VINDICATOR, Surface.NO_RESTRICTIONS, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityMonster::b);
/* 138 */     a(EntityTypes.WANDERING_TRADER, Surface.NO_RESTRICTIONS, HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, EntityInsentient::a);
/*     */   }
/*     */   
/*     */   public static Surface a(EntityTypes<?> var0) {
/* 142 */     a var1 = a.get(var0);
/* 143 */     return (var1 == null) ? Surface.NO_RESTRICTIONS : a.a(var1);
/*     */   }
/*     */   
/*     */   public static HeightMap.Type b(@Nullable EntityTypes<?> var0) {
/* 147 */     a var1 = a.get(var0);
/* 148 */     return (var1 == null) ? HeightMap.Type.MOTION_BLOCKING_NO_LEAVES : a.b(var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends Entity> boolean a(EntityTypes<T> var0, WorldAccess var1, EnumMobSpawn var2, BlockPosition var3, Random var4) {
/* 153 */     a var5 = a.get(var0);
/* 154 */     return (var5 == null || a.c(var5).test(var0, var1, var2, var3, var4));
/*     */   }
/*     */   
/*     */   public enum Surface {
/* 158 */     ON_GROUND,
/* 159 */     IN_WATER,
/* 160 */     NO_RESTRICTIONS,
/* 161 */     IN_LAVA;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityPositionTypes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */