/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockNylium
/*    */   extends Block
/*    */   implements IBlockFragilePlantElement
/*    */ {
/*    */   protected BlockNylium(BlockBase.Info var0) {
/* 20 */     super(var0);
/*    */   }
/*    */   
/*    */   private static boolean b(IBlockData var0, IWorldReader var1, BlockPosition var2) {
/* 24 */     BlockPosition var3 = var2.up();
/* 25 */     IBlockData var4 = var1.getType(var3);
/*    */ 
/*    */     
/* 28 */     int var5 = LightEngineLayer.a(var1, var0, var2, var4, var3, EnumDirection.UP, var4.b(var1, var3));
/* 29 */     return (var5 < var1.J());
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick(IBlockData var0, WorldServer var1, BlockPosition var2, Random var3) {
/* 34 */     if (!b(var0, var1, var2)) {
/* 35 */       var1.setTypeUpdate(var2, Blocks.NETHERRACK.getBlockData());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IBlockAccess var0, BlockPosition var1, IBlockData var2, boolean var3) {
/* 41 */     return var0.getType(var1.up()).isAir();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(World var0, Random var1, BlockPosition var2, IBlockData var3) {
/* 46 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(WorldServer var0, Random var1, BlockPosition var2, IBlockData var3) {
/* 51 */     IBlockData var4 = var0.getType(var2);
/* 52 */     BlockPosition var5 = var2.up();
/* 53 */     if (var4.a(Blocks.CRIMSON_NYLIUM)) {
/* 54 */       WorldGenFeatureNetherForestVegetation.a(var0, var1, var5, BiomeDecoratorGroups.a.k, 3, 1);
/* 55 */     } else if (var4.a(Blocks.WARPED_NYLIUM)) {
/* 56 */       WorldGenFeatureNetherForestVegetation.a(var0, var1, var5, BiomeDecoratorGroups.a.l, 3, 1);
/* 57 */       WorldGenFeatureNetherForestVegetation.a(var0, var1, var5, BiomeDecoratorGroups.a.m, 3, 1);
/* 58 */       if (var1.nextInt(8) == 0)
/* 59 */         WorldGenFeatureTwistingVines.a(var0, var1, var5, 3, 1, 2); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockNylium.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */