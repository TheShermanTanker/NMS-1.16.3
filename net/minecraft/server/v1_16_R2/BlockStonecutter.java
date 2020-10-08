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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockStonecutter
/*    */   extends Block
/*    */ {
/* 29 */   private static final IChatBaseComponent c = new ChatMessage("container.stonecutter");
/*    */   
/* 31 */   public static final BlockStateDirection a = BlockFacingHorizontal.FACING;
/* 32 */   protected static final VoxelShape b = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D);
/*    */   
/*    */   public BlockStonecutter(BlockBase.Info var0) {
/* 35 */     super(var0);
/* 36 */     j(((IBlockData)this.blockStateList.getBlockData()).set(a, EnumDirection.NORTH));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData getPlacedState(BlockActionContext var0) {
/* 41 */     return getBlockData().set(a, var0.f().opposite());
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult interact(IBlockData var0, World var1, BlockPosition var2, EntityHuman var3, EnumHand var4, MovingObjectPositionBlock var5) {
/* 46 */     if (var1.isClientSide) {
/* 47 */       return EnumInteractionResult.SUCCESS;
/*    */     }
/*    */     
/* 50 */     var3.openContainer(var0.b(var1, var2));
/* 51 */     var3.a(StatisticList.INTERACT_WITH_STONECUTTER);
/*    */     
/* 53 */     return EnumInteractionResult.CONSUME;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public ITileInventory getInventory(IBlockData var0, World var1, BlockPosition var2) {
/* 59 */     return new TileInventory((var2, var3, var4) -> new ContainerStonecutter(var2, var3, ContainerAccess.at(var0, var1)), c);
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 64 */     return b;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean c_(IBlockData var0) {
/* 69 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumRenderType b(IBlockData var0) {
/* 74 */     return EnumRenderType.MODEL;
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(IBlockData var0, EnumBlockRotation var1) {
/* 79 */     return var0.set(a, var1.a((EnumDirection)var0.get(a)));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(IBlockData var0, EnumBlockMirror var1) {
/* 84 */     return var0.a(var1.a((EnumDirection)var0.get(a)));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 89 */     var0.a((IBlockState<?>[])new IBlockState[] { a });
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IBlockData var0, IBlockAccess var1, BlockPosition var2, PathMode var3) {
/* 94 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockStonecutter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */