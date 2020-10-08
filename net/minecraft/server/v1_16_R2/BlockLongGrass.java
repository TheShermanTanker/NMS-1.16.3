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
/*    */ public class BlockLongGrass
/*    */   extends BlockPlant
/*    */   implements IBlockFragilePlantElement
/*    */ {
/* 15 */   protected static final VoxelShape a = Block.a(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
/*    */   
/*    */   protected BlockLongGrass(BlockBase.Info var0) {
/* 18 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 23 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IBlockAccess var0, BlockPosition var1, IBlockData var2, boolean var3) {
/* 28 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(World var0, Random var1, BlockPosition var2, IBlockData var3) {
/* 33 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(WorldServer var0, Random var1, BlockPosition var2, IBlockData var3) {
/* 38 */     BlockTallPlant var4 = (this == Blocks.FERN) ? (BlockTallPlant)Blocks.LARGE_FERN : (BlockTallPlant)Blocks.TALL_GRASS;
/*    */     
/* 40 */     if (var4.getBlockData().canPlace(var0, var2) && var0.isEmpty(var2.up())) {
/* 41 */       var4.a(var0, var2, 2);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockBase.EnumRandomOffset ah_() {
/* 47 */     return BlockBase.EnumRandomOffset.XYZ;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockLongGrass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */