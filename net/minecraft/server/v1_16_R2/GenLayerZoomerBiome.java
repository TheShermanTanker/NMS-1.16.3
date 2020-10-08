/*   */ package net.minecraft.server.v1_16_R2;
/*   */ 
/*   */ public enum GenLayerZoomerBiome implements GenLayerZoomer {
/* 4 */   INSTANCE;
/*   */ 
/*   */   
/*   */   public BiomeBase a(long var0, int var2, int var3, int var4, BiomeManager.Provider var5) {
/* 8 */     return var5.getBiome(var2 >> 2, var3 >> 2, var4 >> 2);
/*   */   }
/*   */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GenLayerZoomerBiome.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */