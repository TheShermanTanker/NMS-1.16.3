/*    */ package net.minecraft.server.v1_16_R2;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockRepeater
/*    */   extends BlockDiodeAbstract
/*    */ {
/* 24 */   public static final BlockStateBoolean LOCKED = BlockProperties.s;
/* 25 */   public static final BlockStateInteger DELAY = BlockProperties.am;
/*    */   
/*    */   protected BlockRepeater(BlockBase.Info var0) {
/* 28 */     super(var0);
/* 29 */     j(((IBlockData)this.blockStateList.getBlockData()).set(FACING, EnumDirection.NORTH).set(DELAY, Integer.valueOf(1)).set(LOCKED, Boolean.valueOf(false)).set(c, Boolean.valueOf(false)));
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult interact(IBlockData var0, World var1, BlockPosition var2, EntityHuman var3, EnumHand var4, MovingObjectPositionBlock var5) {
/* 34 */     if (!var3.abilities.mayBuild) {
/* 35 */       return EnumInteractionResult.PASS;
/*    */     }
/*    */     
/* 38 */     var1.setTypeAndData(var2, var0.a(DELAY), 3);
/* 39 */     return EnumInteractionResult.a(var1.isClientSide);
/*    */   }
/*    */ 
/*    */   
/*    */   protected int g(IBlockData var0) {
/* 44 */     return ((Integer)var0.get(DELAY)).intValue() * 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData getPlacedState(BlockActionContext var0) {
/* 49 */     IBlockData var1 = super.getPlacedState(var0);
/* 50 */     return var1.set(LOCKED, Boolean.valueOf(a(var0.getWorld(), var0.getClickPosition(), var1)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/* 56 */     if (!var3.s_() && var1.n() != ((EnumDirection)var0.get(FACING)).n()) {
/* 57 */       return var0.set(LOCKED, Boolean.valueOf(a(var3, var4, var0)));
/*    */     }
/* 59 */     return super.updateState(var0, var1, var2, var3, var4, var5);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IWorldReader var0, BlockPosition var1, IBlockData var2) {
/* 64 */     return (b(var0, var1, var2) > 0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean h(IBlockData var0) {
/* 69 */     return isDiode(var0);
/*    */   }
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
/*    */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 97 */     var0.a((IBlockState<?>[])new IBlockState[] { FACING, DELAY, LOCKED, c });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockRepeater.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */