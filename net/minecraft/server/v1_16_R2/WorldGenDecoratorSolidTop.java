/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ 
/*    */ public class WorldGenDecoratorSolidTop
/*    */   extends WorldGenDecoratorHeight<WorldGenFeatureEmptyConfiguration2>
/*    */ {
/*    */   public WorldGenDecoratorSolidTop(Codec<WorldGenFeatureEmptyConfiguration2> var0) {
/*  9 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected HeightMap.Type a(WorldGenFeatureEmptyConfiguration2 var0) {
/* 14 */     return HeightMap.Type.OCEAN_FLOOR_WG;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorSolidTop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */