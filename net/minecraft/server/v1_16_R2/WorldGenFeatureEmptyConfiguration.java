/*   */ package net.minecraft.server.v1_16_R2;
/*   */ 
/*   */ import com.mojang.serialization.Codec;
/*   */ 
/*   */ public class WorldGenFeatureEmptyConfiguration implements WorldGenFeatureConfiguration {
/* 6 */   public static final Codec<WorldGenFeatureEmptyConfiguration> a = Codec.unit(() -> b);
/*   */   
/* 8 */   public static final WorldGenFeatureEmptyConfiguration b = new WorldGenFeatureEmptyConfiguration();
/*   */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureEmptyConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */