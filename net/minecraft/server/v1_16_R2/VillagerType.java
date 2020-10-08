/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.Optional;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class VillagerType
/*    */ {
/* 15 */   public static final VillagerType DESERT = a("desert");
/* 16 */   public static final VillagerType JUNGLE = a("jungle");
/* 17 */   public static final VillagerType PLAINS = a("plains");
/* 18 */   public static final VillagerType SAVANNA = a("savanna");
/* 19 */   public static final VillagerType SNOW = a("snow");
/* 20 */   public static final VillagerType SWAMP = a("swamp");
/* 21 */   public static final VillagerType TAIGA = a("taiga");
/*    */   private final String h;
/*    */   private static final Map<ResourceKey<BiomeBase>, VillagerType> i;
/*    */   
/*    */   private VillagerType(String var0) {
/* 26 */     this.h = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 31 */     return this.h;
/*    */   }
/*    */   
/*    */   private static VillagerType a(String var0) {
/* 35 */     return IRegistry.<VillagerType, VillagerType>a(IRegistry.VILLAGER_TYPE, new MinecraftKey(var0), new VillagerType(var0));
/*    */   }
/*    */   static {
/* 38 */     i = SystemUtils.<Map<ResourceKey<BiomeBase>, VillagerType>>a(Maps.newHashMap(), var0 -> {
/*    */           var0.put(Biomes.BADLANDS, DESERT);
/*    */           var0.put(Biomes.BADLANDS_PLATEAU, DESERT);
/*    */           var0.put(Biomes.DESERT, DESERT);
/*    */           var0.put(Biomes.DESERT_HILLS, DESERT);
/*    */           var0.put(Biomes.DESERT_LAKES, DESERT);
/*    */           var0.put(Biomes.ERODED_BADLANDS, DESERT);
/*    */           var0.put(Biomes.MODIFIED_BADLANDS_PLATEAU, DESERT);
/*    */           var0.put(Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU, DESERT);
/*    */           var0.put(Biomes.WOODED_BADLANDS_PLATEAU, DESERT);
/*    */           var0.put(Biomes.BAMBOO_JUNGLE, JUNGLE);
/*    */           var0.put(Biomes.BAMBOO_JUNGLE_HILLS, JUNGLE);
/*    */           var0.put(Biomes.JUNGLE, JUNGLE);
/*    */           var0.put(Biomes.JUNGLE_EDGE, JUNGLE);
/*    */           var0.put(Biomes.JUNGLE_HILLS, JUNGLE);
/*    */           var0.put(Biomes.MODIFIED_JUNGLE, JUNGLE);
/*    */           var0.put(Biomes.MODIFIED_JUNGLE_EDGE, JUNGLE);
/*    */           var0.put(Biomes.SAVANNA_PLATEAU, SAVANNA);
/*    */           var0.put(Biomes.SAVANNA, SAVANNA);
/*    */           var0.put(Biomes.SHATTERED_SAVANNA, SAVANNA);
/*    */           var0.put(Biomes.SHATTERED_SAVANNA_PLATEAU, SAVANNA);
/*    */           var0.put(Biomes.DEEP_FROZEN_OCEAN, SNOW);
/*    */           var0.put(Biomes.FROZEN_OCEAN, SNOW);
/*    */           var0.put(Biomes.FROZEN_RIVER, SNOW);
/*    */           var0.put(Biomes.ICE_SPIKES, SNOW);
/*    */           var0.put(Biomes.SNOWY_BEACH, SNOW);
/*    */           var0.put(Biomes.SNOWY_MOUNTAINS, SNOW);
/*    */           var0.put(Biomes.SNOWY_TAIGA, SNOW);
/*    */           var0.put(Biomes.SNOWY_TAIGA_HILLS, SNOW);
/*    */           var0.put(Biomes.SNOWY_TAIGA_MOUNTAINS, SNOW);
/*    */           var0.put(Biomes.SNOWY_TUNDRA, SNOW);
/*    */           var0.put(Biomes.SWAMP, SWAMP);
/*    */           var0.put(Biomes.SWAMP_HILLS, SWAMP);
/*    */           var0.put(Biomes.GIANT_SPRUCE_TAIGA, TAIGA);
/*    */           var0.put(Biomes.GIANT_SPRUCE_TAIGA_HILLS, TAIGA);
/*    */           var0.put(Biomes.GIANT_TREE_TAIGA, TAIGA);
/*    */           var0.put(Biomes.GIANT_TREE_TAIGA_HILLS, TAIGA);
/*    */           var0.put(Biomes.GRAVELLY_MOUNTAINS, TAIGA);
/*    */           var0.put(Biomes.MODIFIED_GRAVELLY_MOUNTAINS, TAIGA);
/*    */           var0.put(Biomes.MOUNTAIN_EDGE, TAIGA);
/*    */           var0.put(Biomes.MOUNTAINS, TAIGA);
/*    */           var0.put(Biomes.TAIGA, TAIGA);
/*    */           var0.put(Biomes.TAIGA_HILLS, TAIGA);
/*    */           var0.put(Biomes.TAIGA_MOUNTAINS, TAIGA);
/*    */           var0.put(Biomes.WOODED_MOUNTAINS, TAIGA);
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static VillagerType a(Optional<ResourceKey<BiomeBase>> var0) {
/* 93 */     return var0.<VillagerType>flatMap(var0 -> Optional.ofNullable(i.get(var0))).orElse(PLAINS);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\VillagerType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */