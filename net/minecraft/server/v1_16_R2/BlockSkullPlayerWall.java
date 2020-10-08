/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.List;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockSkullPlayerWall
/*    */   extends BlockSkullWall
/*    */ {
/*    */   protected BlockSkullPlayerWall(BlockBase.Info var0) {
/* 15 */     super(BlockSkull.Type.PLAYER, var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void postPlace(World var0, BlockPosition var1, IBlockData var2, @Nullable EntityLiving var3, ItemStack var4) {
/* 20 */     Blocks.PLAYER_HEAD.postPlace(var0, var1, var2, var3, var4);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<ItemStack> a(IBlockData var0, LootTableInfo.Builder var1) {
/* 25 */     return Blocks.PLAYER_HEAD.a(var0, var1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockSkullPlayerWall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */