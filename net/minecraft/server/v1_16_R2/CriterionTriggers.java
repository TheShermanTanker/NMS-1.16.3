/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CriterionTriggers
/*     */ {
/*  48 */   private static final Map<MinecraftKey, CriterionTrigger<?>> Q = Maps.newHashMap();
/*     */   
/*  50 */   public static final CriterionTriggerImpossible a = a(new CriterionTriggerImpossible());
/*  51 */   public static final CriterionTriggerKilled b = a(new CriterionTriggerKilled(new MinecraftKey("player_killed_entity")));
/*  52 */   public static final CriterionTriggerKilled c = a(new CriterionTriggerKilled(new MinecraftKey("entity_killed_player")));
/*  53 */   public static final CriterionTriggerEnterBlock d = a(new CriterionTriggerEnterBlock());
/*  54 */   public static final CriterionTriggerInventoryChanged e = a(new CriterionTriggerInventoryChanged());
/*  55 */   public static final CriterionTriggerRecipeUnlocked f = a(new CriterionTriggerRecipeUnlocked());
/*  56 */   public static final CriterionTriggerPlayerHurtEntity g = a(new CriterionTriggerPlayerHurtEntity());
/*  57 */   public static final CriterionTriggerEntityHurtPlayer h = a(new CriterionTriggerEntityHurtPlayer());
/*  58 */   public static final CriterionTriggerEnchantedItem i = a(new CriterionTriggerEnchantedItem());
/*  59 */   public static final CriterionTriggerFilledBucket j = a(new CriterionTriggerFilledBucket());
/*  60 */   public static final CriterionTriggerBrewedPotion k = a(new CriterionTriggerBrewedPotion());
/*  61 */   public static final CriterionTriggerConstructBeacon l = a(new CriterionTriggerConstructBeacon());
/*  62 */   public static final CriterionTriggerUsedEnderEye m = a(new CriterionTriggerUsedEnderEye());
/*  63 */   public static final CriterionTriggerSummonedEntity n = a(new CriterionTriggerSummonedEntity());
/*  64 */   public static final CriterionTriggerBredAnimals o = a(new CriterionTriggerBredAnimals());
/*  65 */   public static final CriterionTriggerLocation p = a(new CriterionTriggerLocation(new MinecraftKey("location")));
/*  66 */   public static final CriterionTriggerLocation q = a(new CriterionTriggerLocation(new MinecraftKey("slept_in_bed")));
/*  67 */   public static final CriterionTriggerCuredZombieVillager r = a(new CriterionTriggerCuredZombieVillager());
/*  68 */   public static final CriterionTriggerVillagerTrade s = a(new CriterionTriggerVillagerTrade());
/*  69 */   public static final CriterionTriggerItemDurabilityChanged t = a(new CriterionTriggerItemDurabilityChanged());
/*  70 */   public static final CriterionTriggerLevitation u = a(new CriterionTriggerLevitation());
/*  71 */   public static final CriterionTriggerChangedDimension v = a(new CriterionTriggerChangedDimension());
/*  72 */   public static final CriterionTriggerTick w = a(new CriterionTriggerTick());
/*  73 */   public static final CriterionTriggerTamedAnimal x = a(new CriterionTriggerTamedAnimal());
/*  74 */   public static final CriterionTriggerPlacedBlock y = a(new CriterionTriggerPlacedBlock());
/*  75 */   public static final CriterionTriggerConsumeItem z = a(new CriterionTriggerConsumeItem());
/*  76 */   public static final CriterionTriggerEffectsChanged A = a(new CriterionTriggerEffectsChanged());
/*  77 */   public static final CriterionTriggerUsedTotem B = a(new CriterionTriggerUsedTotem());
/*  78 */   public static final CriterionTriggerNetherTravel C = a(new CriterionTriggerNetherTravel());
/*  79 */   public static final CriterionTriggerFishingRodHooked D = a(new CriterionTriggerFishingRodHooked());
/*  80 */   public static final CriterionTriggerChanneledLightning E = a(new CriterionTriggerChanneledLightning());
/*  81 */   public static final CriterionTriggerShotCrossbow F = a(new CriterionTriggerShotCrossbow());
/*  82 */   public static final CriterionTriggerKilledByCrossbow G = a(new CriterionTriggerKilledByCrossbow());
/*  83 */   public static final CriterionTriggerLocation H = a(new CriterionTriggerLocation(new MinecraftKey("hero_of_the_village")));
/*  84 */   public static final CriterionTriggerLocation I = a(new CriterionTriggerLocation(new MinecraftKey("voluntary_exile")));
/*  85 */   public static final CriterionSlideDownBlock J = a(new CriterionSlideDownBlock());
/*  86 */   public static final CriterionTriggerBeeNestDestroyed K = a(new CriterionTriggerBeeNestDestroyed());
/*  87 */   public static final CriterionTriggerTargetHit L = a(new CriterionTriggerTargetHit());
/*  88 */   public static final CriterionTriggerInteractBlock M = a(new CriterionTriggerInteractBlock());
/*  89 */   public static final CriterionTriggerPlayerGeneratesContainerLoot N = a(new CriterionTriggerPlayerGeneratesContainerLoot());
/*  90 */   public static final CriterionTriggerThrownItemPickedUpByEntity O = a(new CriterionTriggerThrownItemPickedUpByEntity());
/*  91 */   public static final CriterionTriggerPlayerInteractedWithEntity P = a(new CriterionTriggerPlayerInteractedWithEntity());
/*     */   
/*     */   private static <T extends CriterionTrigger<?>> T a(T var0) {
/*  94 */     if (Q.containsKey(var0.a())) {
/*  95 */       throw new IllegalArgumentException("Duplicate criterion id " + var0.a());
/*     */     }
/*  97 */     Q.put(var0.a(), (CriterionTrigger<?>)var0);
/*  98 */     return var0;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static <T extends CriterionInstance> CriterionTrigger<T> a(MinecraftKey var0) {
/* 104 */     return (CriterionTrigger<T>)Q.get(var0);
/*     */   }
/*     */   
/*     */   public static Iterable<? extends CriterionTrigger<?>> a() {
/* 108 */     return Q.values();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */