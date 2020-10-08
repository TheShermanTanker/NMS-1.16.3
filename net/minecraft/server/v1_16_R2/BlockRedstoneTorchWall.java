/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockRedstoneTorchWall
/*     */   extends BlockRedstoneTorch
/*     */ {
/*  22 */   public static final BlockStateDirection b = BlockFacingHorizontal.FACING;
/*  23 */   public static final BlockStateBoolean c = BlockRedstoneTorch.LIT;
/*     */   
/*     */   protected BlockRedstoneTorchWall(BlockBase.Info var0) {
/*  26 */     super(var0);
/*  27 */     j(((IBlockData)this.blockStateList.getBlockData()).set(b, EnumDirection.NORTH).set(c, Boolean.valueOf(true)));
/*     */   }
/*     */ 
/*     */   
/*     */   public String i() {
/*  32 */     return getItem().getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/*  37 */     return BlockTorchWall.h(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(IBlockData var0, IWorldReader var1, BlockPosition var2) {
/*  42 */     return Blocks.WALL_TORCH.canPlace(var0, var1, var2);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/*  47 */     return Blocks.WALL_TORCH.updateState(var0, var1, var2, var3, var4, var5);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public IBlockData getPlacedState(BlockActionContext var0) {
/*  53 */     IBlockData var1 = Blocks.WALL_TORCH.getPlacedState(var0);
/*  54 */     return (var1 == null) ? null : getBlockData().set(b, var1.get(b));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean a(World var0, BlockPosition var1, IBlockData var2) {
/*  74 */     EnumDirection var3 = ((EnumDirection)var2.get(b)).opposite();
/*     */     
/*  76 */     return var0.isBlockFacePowered(var1.shift(var3), var3);
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(IBlockData var0, IBlockAccess var1, BlockPosition var2, EnumDirection var3) {
/*  81 */     if (((Boolean)var0.get(c)).booleanValue() && var0.get(b) != var3) {
/*  82 */       return 15;
/*     */     }
/*     */     
/*  85 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData var0, EnumBlockRotation var1) {
/*  90 */     return Blocks.WALL_TORCH.a(var0, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData var0, EnumBlockMirror var1) {
/*  95 */     return Blocks.WALL_TORCH.a(var0, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 100 */     var0.a((IBlockState<?>[])new IBlockState[] { b, c });
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockRedstoneTorchWall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */