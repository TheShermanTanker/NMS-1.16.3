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
/*    */ 
/*    */ 
/*    */ public class BlockFalling
/*    */   extends Block
/*    */ {
/*    */   public BlockFalling(BlockBase.Info var0) {
/* 21 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onPlace(IBlockData var0, World var1, BlockPosition var2, IBlockData var3, boolean var4) {
/* 26 */     var1.getBlockTickList().a(var2, this, c());
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/* 31 */     var3.getBlockTickList().a(var4, this, c());
/*    */     
/* 33 */     return super.updateState(var0, var1, var2, var3, var4, var5);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tickAlways(IBlockData var0, WorldServer var1, BlockPosition var2, Random var3) {
/* 38 */     if (!canFallThrough(var1.getType(var2.down())) || var2.getY() < 0) {
/*    */       return;
/*    */     }
/*    */ 
/*    */     
/* 43 */     EntityFallingBlock var4 = new EntityFallingBlock(var1, var2.getX() + 0.5D, var2.getY(), var2.getZ() + 0.5D, var1.getType(var2));
/* 44 */     a(var4);
/* 45 */     var1.addEntity(var4);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(EntityFallingBlock var0) {}
/*    */   
/*    */   protected int c() {
/* 52 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean canFallThrough(IBlockData var0) {
/* 57 */     Material var1 = var0.getMaterial();
/* 58 */     return (var0.isAir() || var0.a(TagsBlock.FIRE) || var1.isLiquid() || var1.isReplaceable());
/*    */   }
/*    */   
/*    */   public void a(World var0, BlockPosition var1, IBlockData var2, IBlockData var3, EntityFallingBlock var4) {}
/*    */   
/*    */   public void a(World var0, BlockPosition var1, EntityFallingBlock var2) {}
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockFalling.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */