/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface IBlockLightAccess
/*    */   extends IBlockAccess
/*    */ {
/*    */   LightEngine e();
/*    */   
/*    */   default int getBrightness(EnumSkyBlock var0, BlockPosition var1) {
/* 15 */     return e().a(var0).b(var1);
/*    */   }
/*    */   
/*    */   default int getLightLevel(BlockPosition var0, int var1) {
/* 19 */     return e().b(var0, var1);
/*    */   }
/*    */   
/*    */   default boolean e(BlockPosition var0) {
/* 23 */     return (getBrightness(EnumSkyBlock.SKY, var0) >= J());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IBlockLightAccess.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */