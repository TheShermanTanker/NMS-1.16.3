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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockCoralBase
/*    */   extends Block
/*    */   implements IBlockWaterlogged
/*    */ {
/* 22 */   public static final BlockStateBoolean b = BlockProperties.C;
/* 23 */   private static final VoxelShape a = Block.a(2.0D, 0.0D, 2.0D, 14.0D, 4.0D, 14.0D);
/*    */   
/*    */   protected BlockCoralBase(BlockBase.Info var0) {
/* 26 */     super(var0);
/* 27 */     j(((IBlockData)this.blockStateList.getBlockData()).set(b, Boolean.valueOf(true)));
/*    */   }
/*    */   
/*    */   protected void a(IBlockData var0, GeneratorAccess var1, BlockPosition var2) {
/* 31 */     if (!c(var0, var1, var2)) {
/* 32 */       var1.getBlockTickList().a(var2, this, 60 + var1.getRandom().nextInt(40));
/*    */     }
/*    */   }
/*    */   
/*    */   protected static boolean c(IBlockData var0, IBlockAccess var1, BlockPosition var2) {
/* 37 */     if (((Boolean)var0.get(b)).booleanValue()) {
/* 38 */       return true;
/*    */     }
/*    */     
/* 41 */     for (EnumDirection var6 : EnumDirection.values()) {
/* 42 */       if (var1.getFluid(var2.shift(var6)).a(TagsFluid.WATER)) {
/* 43 */         return true;
/*    */       }
/*    */     } 
/* 46 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public IBlockData getPlacedState(BlockActionContext var0) {
/* 52 */     Fluid var1 = var0.getWorld().getFluid(var0.getClickPosition());
/*    */     
/* 54 */     return getBlockData().set(b, Boolean.valueOf((var1.a(TagsFluid.WATER) && var1.e() == 8)));
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 59 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/* 64 */     if (((Boolean)var0.get(b)).booleanValue()) {
/* 65 */       var3.getFluidTickList().a(var4, FluidTypes.WATER, FluidTypes.WATER.a(var3));
/*    */     }
/*    */     
/* 68 */     if (var1 == EnumDirection.DOWN && !canPlace(var0, var3, var4)) {
/* 69 */       return Blocks.AIR.getBlockData();
/*    */     }
/* 71 */     return super.updateState(var0, var1, var2, var3, var4, var5);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canPlace(IBlockData var0, IWorldReader var1, BlockPosition var2) {
/* 76 */     BlockPosition var3 = var2.down();
/* 77 */     return var1.getType(var3).d(var1, var3, EnumDirection.UP);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 82 */     var0.a((IBlockState<?>[])new IBlockState[] { b });
/*    */   }
/*    */ 
/*    */   
/*    */   public Fluid d(IBlockData var0) {
/* 87 */     if (((Boolean)var0.get(b)).booleanValue()) {
/* 88 */       return FluidTypes.WATER.a(false);
/*    */     }
/*    */     
/* 91 */     return super.d(var0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockCoralBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */