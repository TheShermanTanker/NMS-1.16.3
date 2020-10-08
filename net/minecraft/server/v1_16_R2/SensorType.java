/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SensorType<U extends Sensor<?>>
/*    */ {
/*  9 */   public static final SensorType<SensorDummy> a = a("dummy", SensorDummy::new);
/* 10 */   public static final SensorType<SensorNearestItems> b = a("nearest_items", SensorNearestItems::new);
/* 11 */   public static final SensorType<SensorNearestLivingEntities> c = a("nearest_living_entities", SensorNearestLivingEntities::new);
/* 12 */   public static final SensorType<SensorNearestPlayers> d = a("nearest_players", SensorNearestPlayers::new);
/* 13 */   public static final SensorType<SensorNearestBed> e = a("nearest_bed", SensorNearestBed::new);
/* 14 */   public static final SensorType<SensorHurtBy> f = a("hurt_by", SensorHurtBy::new);
/* 15 */   public static final SensorType<SensorVillagerHostiles> g = a("villager_hostiles", SensorVillagerHostiles::new);
/* 16 */   public static final SensorType<SensorVillagerBabies> h = a("villager_babies", SensorVillagerBabies::new);
/* 17 */   public static final SensorType<SensorSecondaryPlaces> i = a("secondary_pois", SensorSecondaryPlaces::new);
/* 18 */   public static final SensorType<SensorGolemLastSeen> j = a("golem_detected", SensorGolemLastSeen::new);
/* 19 */   public static final SensorType<SensorPiglinSpecific> k = a("piglin_specific_sensor", SensorPiglinSpecific::new);
/* 20 */   public static final SensorType<SensorPiglinBruteSpecific> l = a("piglin_brute_specific_sensor", SensorPiglinBruteSpecific::new);
/* 21 */   public static final SensorType<SensorHoglinSpecific> m = a("hoglin_specific_sensor", SensorHoglinSpecific::new);
/* 22 */   public static final SensorType<SensorAdult> n = a("nearest_adult", SensorAdult::new);
/*    */   
/*    */   private final Supplier<U> o;
/*    */   
/*    */   private SensorType(Supplier<U> var0) {
/* 27 */     this.o = var0;
/*    */   }
/*    */   
/*    */   public U a() {
/* 31 */     return this.o.get();
/*    */   }
/*    */   
/*    */   private static <U extends Sensor<?>> SensorType<U> a(String var0, Supplier<U> var1) {
/* 35 */     return IRegistry.<SensorType<?>, SensorType<U>>a(IRegistry.SENSOR_TYPE, new MinecraftKey(var0), new SensorType<>(var1));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\SensorType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */