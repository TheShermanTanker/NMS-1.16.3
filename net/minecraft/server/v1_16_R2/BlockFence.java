/*     */ package net.minecraft.server.v1_16_R2;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockFence
/*     */   extends BlockTall
/*     */ {
/*     */   private final VoxelShape[] i;
/*     */   
/*     */   public BlockFence(BlockBase.Info var0) {
/*  29 */     super(2.0F, 2.0F, 16.0F, 16.0F, 24.0F, var0);
/*  30 */     j(((IBlockData)this.blockStateList.getBlockData()).set(NORTH, Boolean.valueOf(false)).set(EAST, Boolean.valueOf(false)).set(SOUTH, Boolean.valueOf(false)).set(WEST, Boolean.valueOf(false)).set(e, Boolean.valueOf(false)));
/*     */     
/*  32 */     this.i = a(2.0F, 1.0F, 16.0F, 6.0F, 15.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape d(IBlockData var0, IBlockAccess var1, BlockPosition var2) {
/*  37 */     return this.i[g(var0)];
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape a(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/*  42 */     return b(var0, var1, var2, var3);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData var0, IBlockAccess var1, BlockPosition var2, PathMode var3) {
/*  47 */     return false;
/*     */   }
/*     */   
/*     */   public boolean a(IBlockData var0, boolean var1, EnumDirection var2) {
/*  51 */     Block var3 = var0.getBlock();
/*     */     
/*  53 */     boolean var4 = c(var3);
/*  54 */     boolean var5 = (var3 instanceof BlockFenceGate && BlockFenceGate.a(var0, var2));
/*  55 */     return ((!b(var3) && var1) || var4 || var5);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean c(Block var0) {
/*  60 */     return (var0.a(TagsBlock.FENCES) && var0.a(TagsBlock.WOODEN_FENCES) == getBlockData().a(TagsBlock.WOODEN_FENCES));
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData var0, World var1, BlockPosition var2, EntityHuman var3, EnumHand var4, MovingObjectPositionBlock var5) {
/*  65 */     if (var1.isClientSide) {
/*  66 */       ItemStack var6 = var3.b(var4);
/*  67 */       if (var6.getItem() == Items.LEAD) {
/*  68 */         return EnumInteractionResult.SUCCESS;
/*     */       }
/*  70 */       return EnumInteractionResult.PASS;
/*     */     } 
/*     */ 
/*     */     
/*  74 */     return ItemLeash.a(var3, var1, var2);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(BlockActionContext var0) {
/*  79 */     IBlockAccess var1 = var0.getWorld();
/*  80 */     BlockPosition var2 = var0.getClickPosition();
/*  81 */     Fluid var3 = var0.getWorld().getFluid(var0.getClickPosition());
/*     */ 
/*     */     
/*  84 */     BlockPosition var4 = var2.north();
/*  85 */     BlockPosition var5 = var2.east();
/*  86 */     BlockPosition var6 = var2.south();
/*  87 */     BlockPosition var7 = var2.west();
/*     */     
/*  89 */     IBlockData var8 = var1.getType(var4);
/*  90 */     IBlockData var9 = var1.getType(var5);
/*  91 */     IBlockData var10 = var1.getType(var6);
/*  92 */     IBlockData var11 = var1.getType(var7);
/*     */     
/*  94 */     return super.getPlacedState(var0)
/*  95 */       .set(NORTH, Boolean.valueOf(a(var8, var8.d(var1, var4, EnumDirection.SOUTH), EnumDirection.SOUTH)))
/*  96 */       .set(EAST, Boolean.valueOf(a(var9, var9.d(var1, var5, EnumDirection.WEST), EnumDirection.WEST)))
/*  97 */       .set(SOUTH, Boolean.valueOf(a(var10, var10.d(var1, var6, EnumDirection.NORTH), EnumDirection.NORTH)))
/*  98 */       .set(WEST, Boolean.valueOf(a(var11, var11.d(var1, var7, EnumDirection.EAST), EnumDirection.EAST)))
/*  99 */       .set(e, Boolean.valueOf((var3.getType() == FluidTypes.WATER)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/* 104 */     if (((Boolean)var0.get(e)).booleanValue()) {
/* 105 */       var3.getFluidTickList().a(var4, FluidTypes.WATER, FluidTypes.WATER.a(var3));
/*     */     }
/* 107 */     if (var1.n().e() == EnumDirection.EnumDirectionLimit.HORIZONTAL) {
/* 108 */       return var0.set(f.get(var1), Boolean.valueOf(a(var2, var2.d(var3, var5, var1.opposite()), var1.opposite())));
/*     */     }
/* 110 */     return super.updateState(var0, var1, var2, var3, var4, var5);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 115 */     var0.a((IBlockState<?>[])new IBlockState[] { NORTH, EAST, WEST, SOUTH, e });
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockFence.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */