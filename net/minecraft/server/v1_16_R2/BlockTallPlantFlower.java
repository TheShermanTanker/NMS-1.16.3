/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockTallPlantFlower
/*    */   extends BlockTallPlant
/*    */   implements IBlockFragilePlantElement
/*    */ {
/*    */   public BlockTallPlantFlower(BlockBase.Info var0) {
/* 15 */     super(var0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean a(IBlockData var0, BlockActionContext var1) {
/* 21 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IBlockAccess var0, BlockPosition var1, IBlockData var2, boolean var3) {
/* 26 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(World var0, Random var1, BlockPosition var2, IBlockData var3) {
/* 31 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(WorldServer var0, Random var1, BlockPosition var2, IBlockData var3) {
/* 36 */     a(var0, var2, new ItemStack(this));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockTallPlantFlower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */