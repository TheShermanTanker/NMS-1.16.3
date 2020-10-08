/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ 
/*    */ public class WorldGenFeatureMushroomConfiguration implements WorldGenFeatureConfiguration {
/*    */   static {
/*  8 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)WorldGenFeatureStateProvider.a.fieldOf("cap_provider").forGetter(()), (App)WorldGenFeatureStateProvider.a.fieldOf("stem_provider").forGetter(()), (App)Codec.INT.fieldOf("foliage_radius").orElse(Integer.valueOf(2)).forGetter(())).apply((Applicative)var0, WorldGenFeatureMushroomConfiguration::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<WorldGenFeatureMushroomConfiguration> a;
/*    */   
/*    */   public final WorldGenFeatureStateProvider b;
/*    */   public final WorldGenFeatureStateProvider c;
/*    */   public final int d;
/*    */   
/*    */   public WorldGenFeatureMushroomConfiguration(WorldGenFeatureStateProvider var0, WorldGenFeatureStateProvider var1, int var2) {
/* 19 */     this.b = var0;
/* 20 */     this.c = var1;
/* 21 */     this.d = var2;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureMushroomConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */