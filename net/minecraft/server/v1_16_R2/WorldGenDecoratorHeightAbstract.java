/*   */ package net.minecraft.server.v1_16_R2;
/*   */ 
/*   */ import com.mojang.serialization.Codec;
/*   */ 
/*   */ public abstract class WorldGenDecoratorHeightAbstract<DC extends WorldGenFeatureDecoratorConfiguration>
/*   */   extends WorldGenDecorator<DC>
/*   */ {
/*   */   public WorldGenDecoratorHeightAbstract(Codec<DC> var0) {
/* 9 */     super(var0);
/*   */   }
/*   */   
/*   */   protected abstract HeightMap.Type a(DC paramDC);
/*   */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorHeightAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */