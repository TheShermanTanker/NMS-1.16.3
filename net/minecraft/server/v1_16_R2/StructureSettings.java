/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.google.common.collect.Maps;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.Map;
/*    */ import java.util.Optional;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public class StructureSettings {
/*    */   static {
/* 14 */     a = RecordCodecBuilder.create(instance -> instance.group((App)StructureSettingsStronghold.a.optionalFieldOf("stronghold").forGetter(()), (App)Codec.simpleMap(IRegistry.STRUCTURE_FEATURE, StructureSettingsFeature.a, IRegistry.STRUCTURE_FEATURE).fieldOf("structures").forGetter(())).apply((Applicative)instance, StructureSettings::new));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static final Codec<StructureSettings> a;
/*    */   
/* 21 */   public static final ImmutableMap<StructureGenerator<?>, StructureSettingsFeature> b = ImmutableMap.builder().put(StructureGenerator.VILLAGE, new StructureSettingsFeature(32, 8, 10387312)).put(StructureGenerator.DESERT_PYRAMID, new StructureSettingsFeature(32, 8, 14357617)).put(StructureGenerator.IGLOO, new StructureSettingsFeature(32, 8, 14357618)).put(StructureGenerator.JUNGLE_PYRAMID, new StructureSettingsFeature(32, 8, 14357619)).put(StructureGenerator.SWAMP_HUT, new StructureSettingsFeature(32, 8, 14357620)).put(StructureGenerator.PILLAGER_OUTPOST, new StructureSettingsFeature(32, 8, 165745296)).put(StructureGenerator.STRONGHOLD, new StructureSettingsFeature(1, 0, 0)).put(StructureGenerator.MONUMENT, new StructureSettingsFeature(32, 5, 10387313)).put(StructureGenerator.ENDCITY, new StructureSettingsFeature(20, 11, 10387313)).put(StructureGenerator.MANSION, new StructureSettingsFeature(80, 20, 10387319)).put(StructureGenerator.BURIED_TREASURE, new StructureSettingsFeature(1, 0, 0)).put(StructureGenerator.MINESHAFT, new StructureSettingsFeature(1, 0, 0)).put(StructureGenerator.RUINED_PORTAL, new StructureSettingsFeature(40, 15, 34222645)).put(StructureGenerator.SHIPWRECK, new StructureSettingsFeature(24, 4, 165745295)).put(StructureGenerator.OCEAN_RUIN, new StructureSettingsFeature(20, 8, 14357621)).put(StructureGenerator.BASTION_REMNANT, new StructureSettingsFeature(27, 4, 30084232)).put(StructureGenerator.FORTRESS, new StructureSettingsFeature(27, 4, 30084232)).put(StructureGenerator.NETHER_FOSSIL, new StructureSettingsFeature(2, 1, 14357921)).build();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public StructureSettings(Optional<StructureSettingsStronghold> optional, Map<StructureGenerator<?>, StructureSettingsFeature> map) {
/* 28 */     this.e = optional.orElse(null);
/* 29 */     this.d = Maps.newHashMap(map);
/*    */   }
/*    */   
/*    */   public StructureSettings(boolean flag) {
/* 33 */     this.d = Maps.newHashMap((Map)b);
/* 34 */     this.e = flag ? c : null;
/*    */   }
/*    */   
/*    */   public Map<StructureGenerator<?>, StructureSettingsFeature> a() {
/* 38 */     return this.d;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public StructureSettingsFeature a(StructureGenerator<?> structuregenerator) {
/* 43 */     return this.d.get(structuregenerator);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public StructureSettingsStronghold b() {
/* 48 */     return this.e;
/*    */   }
/*    */ 
/*    */   
/* 52 */   public static final StructureSettingsStronghold c = new StructureSettingsStronghold(32, 3, 128); private final Map<StructureGenerator<?>, StructureSettingsFeature> d;
/*    */   static {
/* 54 */     for (StructureGenerator<?> var1 : IRegistry.STRUCTURE_FEATURE) {
/* 55 */       if (!b.containsKey(var1))
/* 56 */         throw new IllegalStateException("Structure feature without default settings: " + IRegistry.STRUCTURE_FEATURE.getKey(var1)); 
/*    */     } 
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   private final StructureSettingsStronghold e;
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\StructureSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */