/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockRotatable
/*    */   extends Block
/*    */ {
/* 11 */   public static final BlockStateEnum<EnumDirection.EnumAxis> AXIS = BlockProperties.F;
/*    */   
/*    */   public BlockRotatable(BlockBase.Info var0) {
/* 14 */     super(var0);
/* 15 */     j(getBlockData().set(AXIS, EnumDirection.EnumAxis.Y));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(IBlockData var0, EnumBlockRotation var1) {
/* 20 */     switch (null.b[var1.ordinal()]) {
/*    */       case 1:
/*    */       case 2:
/* 23 */         switch (null.a[((EnumDirection.EnumAxis)var0.get(AXIS)).ordinal()]) {
/*    */           case 1:
/* 25 */             return var0.set(AXIS, EnumDirection.EnumAxis.Z);
/*    */           case 2:
/* 27 */             return var0.set(AXIS, EnumDirection.EnumAxis.X);
/*    */         } 
/* 29 */         return var0;
/*    */     } 
/*    */     
/* 32 */     return var0;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 38 */     var0.a((IBlockState<?>[])new IBlockState[] { AXIS });
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData getPlacedState(BlockActionContext var0) {
/* 43 */     return getBlockData().set(AXIS, var0.getClickedFace().n());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockRotatable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */