/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ 
/*    */ public class WorldGenDecoratorHeightmapWorldSurface
/*    */   extends WorldGenDecoratorHeight<WorldGenFeatureEmptyConfiguration2>
/*    */ {
/*    */   public WorldGenDecoratorHeightmapWorldSurface(Codec<WorldGenFeatureEmptyConfiguration2> var0) {
/*  9 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected HeightMap.Type a(WorldGenFeatureEmptyConfiguration2 var0) {
/* 14 */     return HeightMap.Type.WORLD_SURFACE_WG;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorHeightmapWorldSurface.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */