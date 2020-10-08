/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ 
/*    */ public class WorldGenDecoratorHeightmap<DC extends WorldGenFeatureDecoratorConfiguration>
/*    */   extends WorldGenDecoratorHeight<DC>
/*    */ {
/*    */   public WorldGenDecoratorHeightmap(Codec<DC> var0) {
/*  9 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected HeightMap.Type a(DC var0) {
/* 14 */     return HeightMap.Type.MOTION_BLOCKING;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorHeightmap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */