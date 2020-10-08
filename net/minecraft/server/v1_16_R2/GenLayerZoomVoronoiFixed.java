/*   */ package net.minecraft.server.v1_16_R2;
/*   */ 
/*   */ public enum GenLayerZoomVoronoiFixed implements GenLayerZoomer {
/* 4 */   INSTANCE;
/*   */ 
/*   */   
/*   */   public BiomeBase a(long var0, int var2, int var3, int var4, BiomeManager.Provider var5) {
/* 8 */     return GenLayerZoomVoronoi.INSTANCE.a(var0, var2, 0, var4, var5);
/*   */   }
/*   */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GenLayerZoomVoronoiFixed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */