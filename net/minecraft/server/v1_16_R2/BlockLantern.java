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
/*     */ 
/*     */ public class BlockLantern
/*     */   extends Block
/*     */   implements IBlockWaterlogged
/*     */ {
/*  24 */   public static final BlockStateBoolean a = BlockProperties.j;
/*  25 */   public static final BlockStateBoolean b = BlockProperties.C;
/*     */   
/*  27 */   protected static final VoxelShape c = VoxelShapes.a(Block.a(5.0D, 0.0D, 5.0D, 11.0D, 7.0D, 11.0D), Block.a(6.0D, 7.0D, 6.0D, 10.0D, 9.0D, 10.0D));
/*  28 */   protected static final VoxelShape d = VoxelShapes.a(Block.a(5.0D, 1.0D, 5.0D, 11.0D, 8.0D, 11.0D), Block.a(6.0D, 8.0D, 6.0D, 10.0D, 10.0D, 10.0D));
/*     */   
/*     */   public BlockLantern(BlockBase.Info var0) {
/*  31 */     super(var0);
/*  32 */     j(((IBlockData)this.blockStateList.getBlockData()).set(a, Boolean.valueOf(false)).set(b, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public IBlockData getPlacedState(BlockActionContext var0) {
/*  38 */     Fluid var1 = var0.getWorld().getFluid(var0.getClickPosition());
/*     */     
/*  40 */     for (EnumDirection var5 : var0.e()) {
/*     */       
/*  42 */       if (var5.n() == EnumDirection.EnumAxis.Y) {
/*  43 */         IBlockData var6 = getBlockData().set(a, Boolean.valueOf((var5 == EnumDirection.UP)));
/*     */         
/*  45 */         if (var6.canPlace(var0.getWorld(), var0.getClickPosition())) {
/*  46 */           return var6.set(b, Boolean.valueOf((var1.getType() == FluidTypes.WATER)));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  51 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/*  56 */     return ((Boolean)var0.get(a)).booleanValue() ? d : c;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/*  61 */     var0.a((IBlockState<?>[])new IBlockState[] { a, b });
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(IBlockData var0, IWorldReader var1, BlockPosition var2) {
/*  66 */     EnumDirection var3 = h(var0).opposite();
/*  67 */     return Block.a(var1, var2.shift(var3), var3.opposite());
/*     */   }
/*     */   
/*     */   protected static EnumDirection h(IBlockData var0) {
/*  71 */     return ((Boolean)var0.get(a)).booleanValue() ? EnumDirection.DOWN : EnumDirection.UP;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumPistonReaction getPushReaction(IBlockData var0) {
/*  76 */     return EnumPistonReaction.DESTROY;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/*  81 */     if (((Boolean)var0.get(b)).booleanValue()) {
/*  82 */       var3.getFluidTickList().a(var4, FluidTypes.WATER, FluidTypes.WATER.a(var3));
/*     */     }
/*  84 */     if (h(var0).opposite() == var1 && !var0.canPlace(var3, var4)) {
/*  85 */       return Blocks.AIR.getBlockData();
/*     */     }
/*  87 */     return super.updateState(var0, var1, var2, var3, var4, var5);
/*     */   }
/*     */ 
/*     */   
/*     */   public Fluid d(IBlockData var0) {
/*  92 */     if (((Boolean)var0.get(b)).booleanValue()) {
/*  93 */       return FluidTypes.WATER.a(false);
/*     */     }
/*  95 */     return super.d(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData var0, IBlockAccess var1, BlockPosition var2, PathMode var3) {
/* 100 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockLantern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */