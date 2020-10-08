/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StructureFeatures
/*    */ {
/* 19 */   public static final StructureFeature<WorldGenFeatureVillageConfiguration, ? extends StructureGenerator<WorldGenFeatureVillageConfiguration>> a = a("pillager_outpost", StructureGenerator.PILLAGER_OUTPOST.a(new WorldGenFeatureVillageConfiguration(() -> WorldGenFeaturePillagerOutpostPieces.a, 7)));
/* 20 */   public static final StructureFeature<WorldGenMineshaftConfiguration, ? extends StructureGenerator<WorldGenMineshaftConfiguration>> b = a("mineshaft", StructureGenerator.MINESHAFT.a(new WorldGenMineshaftConfiguration(0.004F, WorldGenMineshaft.Type.NORMAL)));
/* 21 */   public static final StructureFeature<WorldGenMineshaftConfiguration, ? extends StructureGenerator<WorldGenMineshaftConfiguration>> c = a("mineshaft_mesa", StructureGenerator.MINESHAFT.a(new WorldGenMineshaftConfiguration(0.004F, WorldGenMineshaft.Type.MESA)));
/* 22 */   public static final StructureFeature<WorldGenFeatureEmptyConfiguration, ? extends StructureGenerator<WorldGenFeatureEmptyConfiguration>> d = a("mansion", StructureGenerator.MANSION.a(WorldGenFeatureEmptyConfiguration.b));
/* 23 */   public static final StructureFeature<WorldGenFeatureEmptyConfiguration, ? extends StructureGenerator<WorldGenFeatureEmptyConfiguration>> e = a("jungle_pyramid", StructureGenerator.JUNGLE_PYRAMID.a(WorldGenFeatureEmptyConfiguration.b));
/* 24 */   public static final StructureFeature<WorldGenFeatureEmptyConfiguration, ? extends StructureGenerator<WorldGenFeatureEmptyConfiguration>> f = a("desert_pyramid", StructureGenerator.DESERT_PYRAMID.a(WorldGenFeatureEmptyConfiguration.b));
/* 25 */   public static final StructureFeature<WorldGenFeatureEmptyConfiguration, ? extends StructureGenerator<WorldGenFeatureEmptyConfiguration>> g = a("igloo", StructureGenerator.IGLOO.a(WorldGenFeatureEmptyConfiguration.b));
/* 26 */   public static final StructureFeature<WorldGenFeatureShipwreckConfiguration, ? extends StructureGenerator<WorldGenFeatureShipwreckConfiguration>> h = a("shipwreck", StructureGenerator.SHIPWRECK.a(new WorldGenFeatureShipwreckConfiguration(false)));
/* 27 */   public static final StructureFeature<WorldGenFeatureShipwreckConfiguration, ? extends StructureGenerator<WorldGenFeatureShipwreckConfiguration>> i = a("shipwreck_beached", StructureGenerator.SHIPWRECK.a(new WorldGenFeatureShipwreckConfiguration(true)));
/* 28 */   public static final StructureFeature<WorldGenFeatureEmptyConfiguration, ? extends StructureGenerator<WorldGenFeatureEmptyConfiguration>> j = a("swamp_hut", StructureGenerator.SWAMP_HUT.a(WorldGenFeatureEmptyConfiguration.b));
/* 29 */   public static final StructureFeature<WorldGenFeatureEmptyConfiguration, ? extends StructureGenerator<WorldGenFeatureEmptyConfiguration>> k = a("stronghold", StructureGenerator.STRONGHOLD.a(WorldGenFeatureEmptyConfiguration.b));
/* 30 */   public static final StructureFeature<WorldGenFeatureEmptyConfiguration, ? extends StructureGenerator<WorldGenFeatureEmptyConfiguration>> l = a("monument", StructureGenerator.MONUMENT.a(WorldGenFeatureEmptyConfiguration.b));
/* 31 */   public static final StructureFeature<WorldGenFeatureOceanRuinConfiguration, ? extends StructureGenerator<WorldGenFeatureOceanRuinConfiguration>> m = a("ocean_ruin_cold", StructureGenerator.OCEAN_RUIN.a(new WorldGenFeatureOceanRuinConfiguration(WorldGenFeatureOceanRuin.Temperature.COLD, 0.3F, 0.9F)));
/* 32 */   public static final StructureFeature<WorldGenFeatureOceanRuinConfiguration, ? extends StructureGenerator<WorldGenFeatureOceanRuinConfiguration>> n = a("ocean_ruin_warm", StructureGenerator.OCEAN_RUIN.a(new WorldGenFeatureOceanRuinConfiguration(WorldGenFeatureOceanRuin.Temperature.WARM, 0.3F, 0.9F)));
/* 33 */   public static final StructureFeature<WorldGenFeatureEmptyConfiguration, ? extends StructureGenerator<WorldGenFeatureEmptyConfiguration>> o = a("fortress", StructureGenerator.FORTRESS.a(WorldGenFeatureEmptyConfiguration.b));
/* 34 */   public static final StructureFeature<WorldGenFeatureEmptyConfiguration, ? extends StructureGenerator<WorldGenFeatureEmptyConfiguration>> p = a("nether_fossil", StructureGenerator.NETHER_FOSSIL.a(WorldGenFeatureEmptyConfiguration.b));
/* 35 */   public static final StructureFeature<WorldGenFeatureEmptyConfiguration, ? extends StructureGenerator<WorldGenFeatureEmptyConfiguration>> q = a("end_city", StructureGenerator.ENDCITY.a(WorldGenFeatureEmptyConfiguration.b));
/* 36 */   public static final StructureFeature<WorldGenFeatureConfigurationChance, ? extends StructureGenerator<WorldGenFeatureConfigurationChance>> r = a("buried_treasure", StructureGenerator.BURIED_TREASURE.a(new WorldGenFeatureConfigurationChance(0.01F)));
/* 37 */   public static final StructureFeature<WorldGenFeatureVillageConfiguration, ? extends StructureGenerator<WorldGenFeatureVillageConfiguration>> s = a("bastion_remnant", StructureGenerator.BASTION_REMNANT.a(new WorldGenFeatureVillageConfiguration(() -> WorldGenFeatureBastionPieces.a, 6)));
/*    */   
/* 39 */   public static final StructureFeature<WorldGenFeatureVillageConfiguration, ? extends StructureGenerator<WorldGenFeatureVillageConfiguration>> t = a("village_plains", StructureGenerator.VILLAGE.a(new WorldGenFeatureVillageConfiguration(() -> WorldGenFeatureVillagePlain.a, 6)));
/* 40 */   public static final StructureFeature<WorldGenFeatureVillageConfiguration, ? extends StructureGenerator<WorldGenFeatureVillageConfiguration>> u = a("village_desert", StructureGenerator.VILLAGE.a(new WorldGenFeatureVillageConfiguration(() -> WorldGenFeatureDesertVillage.a, 6)));
/* 41 */   public static final StructureFeature<WorldGenFeatureVillageConfiguration, ? extends StructureGenerator<WorldGenFeatureVillageConfiguration>> v = a("village_savanna", StructureGenerator.VILLAGE.a(new WorldGenFeatureVillageConfiguration(() -> WorldGenFeatureVillageSavanna.a, 6)));
/* 42 */   public static final StructureFeature<WorldGenFeatureVillageConfiguration, ? extends StructureGenerator<WorldGenFeatureVillageConfiguration>> w = a("village_snowy", StructureGenerator.VILLAGE.a(new WorldGenFeatureVillageConfiguration(() -> WorldGenFeatureVillageSnowy.a, 6)));
/* 43 */   public static final StructureFeature<WorldGenFeatureVillageConfiguration, ? extends StructureGenerator<WorldGenFeatureVillageConfiguration>> x = a("village_taiga", StructureGenerator.VILLAGE.a(new WorldGenFeatureVillageConfiguration(() -> WorldGenFeatureVillageTaiga.a, 6)));
/*    */   
/* 45 */   public static final StructureFeature<WorldGenFeatureRuinedPortalConfiguration, ? extends StructureGenerator<WorldGenFeatureRuinedPortalConfiguration>> y = a("ruined_portal", StructureGenerator.RUINED_PORTAL.a(new WorldGenFeatureRuinedPortalConfiguration(WorldGenFeatureRuinedPortal.Type.STANDARD)));
/* 46 */   public static final StructureFeature<WorldGenFeatureRuinedPortalConfiguration, ? extends StructureGenerator<WorldGenFeatureRuinedPortalConfiguration>> z = a("ruined_portal_desert", StructureGenerator.RUINED_PORTAL.a(new WorldGenFeatureRuinedPortalConfiguration(WorldGenFeatureRuinedPortal.Type.DESERT)));
/* 47 */   public static final StructureFeature<WorldGenFeatureRuinedPortalConfiguration, ? extends StructureGenerator<WorldGenFeatureRuinedPortalConfiguration>> A = a("ruined_portal_jungle", StructureGenerator.RUINED_PORTAL.a(new WorldGenFeatureRuinedPortalConfiguration(WorldGenFeatureRuinedPortal.Type.JUNGLE)));
/* 48 */   public static final StructureFeature<WorldGenFeatureRuinedPortalConfiguration, ? extends StructureGenerator<WorldGenFeatureRuinedPortalConfiguration>> B = a("ruined_portal_swamp", StructureGenerator.RUINED_PORTAL.a(new WorldGenFeatureRuinedPortalConfiguration(WorldGenFeatureRuinedPortal.Type.SWAMP)));
/* 49 */   public static final StructureFeature<WorldGenFeatureRuinedPortalConfiguration, ? extends StructureGenerator<WorldGenFeatureRuinedPortalConfiguration>> C = a("ruined_portal_mountain", StructureGenerator.RUINED_PORTAL.a(new WorldGenFeatureRuinedPortalConfiguration(WorldGenFeatureRuinedPortal.Type.MOUNTAIN)));
/* 50 */   public static final StructureFeature<WorldGenFeatureRuinedPortalConfiguration, ? extends StructureGenerator<WorldGenFeatureRuinedPortalConfiguration>> D = a("ruined_portal_ocean", StructureGenerator.RUINED_PORTAL.a(new WorldGenFeatureRuinedPortalConfiguration(WorldGenFeatureRuinedPortal.Type.OCEAN)));
/* 51 */   public static final StructureFeature<WorldGenFeatureRuinedPortalConfiguration, ? extends StructureGenerator<WorldGenFeatureRuinedPortalConfiguration>> E = a("ruined_portal_nether", StructureGenerator.RUINED_PORTAL.a(new WorldGenFeatureRuinedPortalConfiguration(WorldGenFeatureRuinedPortal.Type.NETHER)));
/*    */   
/*    */   private static <FC extends WorldGenFeatureConfiguration, F extends StructureGenerator<FC>> StructureFeature<FC, F> a(String var0, StructureFeature<FC, F> var1) {
/* 54 */     return (StructureFeature<FC, F>)RegistryGeneration.<StructureFeature<?, ?>>a(RegistryGeneration.f, var0, var1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\StructureFeatures.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */