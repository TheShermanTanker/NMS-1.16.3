/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockAttachable
/*    */   extends BlockFacingHorizontal
/*    */ {
/* 16 */   public static final BlockStateEnum<BlockPropertyAttachPosition> FACE = BlockProperties.Q;
/*    */   
/*    */   protected BlockAttachable(BlockBase.Info var0) {
/* 19 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canPlace(IBlockData var0, IWorldReader var1, BlockPosition var2) {
/* 24 */     return b(var1, var2, h(var0).opposite());
/*    */   }
/*    */   
/*    */   public static boolean b(IWorldReader var0, BlockPosition var1, EnumDirection var2) {
/* 28 */     BlockPosition var3 = var1.shift(var2);
/* 29 */     return var0.getType(var3).d(var0, var3, var2.opposite());
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public IBlockData getPlacedState(BlockActionContext var0) {
/* 35 */     for (EnumDirection var4 : var0.e()) {
/*    */       IBlockData var5;
/* 37 */       if (var4.n() == EnumDirection.EnumAxis.Y) {
/* 38 */         var5 = getBlockData().set(FACE, (var4 == EnumDirection.UP) ? BlockPropertyAttachPosition.CEILING : BlockPropertyAttachPosition.FLOOR).set(FACING, var0.f());
/*    */       } else {
/* 40 */         var5 = getBlockData().set(FACE, BlockPropertyAttachPosition.WALL).set(FACING, var4.opposite());
/*    */       } 
/*    */       
/* 43 */       if (var5.canPlace(var0.getWorld(), var0.getClickPosition())) {
/* 44 */         return var5;
/*    */       }
/*    */     } 
/*    */     
/* 48 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/* 53 */     if (h(var0).opposite() == var1 && !var0.canPlace(var3, var4)) {
/* 54 */       return Blocks.AIR.getBlockData();
/*    */     }
/* 56 */     return super.updateState(var0, var1, var2, var3, var4, var5);
/*    */   }
/*    */   
/*    */   protected static EnumDirection h(IBlockData var0) {
/* 60 */     switch (null.a[((BlockPropertyAttachPosition)var0.get(FACE)).ordinal()]) {
/*    */       case 1:
/* 62 */         return EnumDirection.DOWN;
/*    */       case 2:
/* 64 */         return EnumDirection.UP;
/*    */     } 
/* 66 */     return (EnumDirection)var0.get(FACING);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockAttachable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */