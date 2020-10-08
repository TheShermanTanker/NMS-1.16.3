/*   */ package net.minecraft.server.v1_16_R2;
/*   */ 
/*   */ import com.mojang.serialization.Codec;
/*   */ 
/*   */ public class WorldGenCarverConfigurationEmpty implements WorldGenCarverConfiguration {
/* 6 */   public static final Codec<WorldGenCarverConfigurationEmpty> b = Codec.unit(() -> c);
/*   */   
/* 8 */   public static final WorldGenCarverConfigurationEmpty c = new WorldGenCarverConfigurationEmpty();
/*   */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenCarverConfigurationEmpty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */