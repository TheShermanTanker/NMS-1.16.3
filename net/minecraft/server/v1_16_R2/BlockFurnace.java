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
/*     */ 
/*     */ public abstract class BlockFurnace
/*     */   extends BlockTileEntity
/*     */ {
/*  27 */   public static final BlockStateDirection FACING = BlockFacingHorizontal.FACING;
/*  28 */   public static final BlockStateBoolean LIT = BlockProperties.r;
/*     */   
/*     */   protected BlockFurnace(BlockBase.Info var0) {
/*  31 */     super(var0);
/*  32 */     j(((IBlockData)this.blockStateList.getBlockData()).set(FACING, EnumDirection.NORTH).set(LIT, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData var0, World var1, BlockPosition var2, EntityHuman var3, EnumHand var4, MovingObjectPositionBlock var5) {
/*  37 */     if (var1.isClientSide) {
/*  38 */       return EnumInteractionResult.SUCCESS;
/*     */     }
/*     */     
/*  41 */     a(var1, var2, var3);
/*     */     
/*  43 */     return EnumInteractionResult.CONSUME;
/*     */   }
/*     */ 
/*     */   
/*     */   protected abstract void a(World paramWorld, BlockPosition paramBlockPosition, EntityHuman paramEntityHuman);
/*     */   
/*     */   public IBlockData getPlacedState(BlockActionContext var0) {
/*  50 */     return getBlockData().set(FACING, var0.f().opposite());
/*     */   }
/*     */ 
/*     */   
/*     */   public void postPlace(World var0, BlockPosition var1, IBlockData var2, EntityLiving var3, ItemStack var4) {
/*  55 */     if (var4.hasName()) {
/*  56 */       TileEntity var5 = var0.getTileEntity(var1);
/*  57 */       if (var5 instanceof TileEntityFurnace) {
/*  58 */         ((TileEntityFurnace)var5).setCustomName(var4.getName());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(IBlockData var0, World var1, BlockPosition var2, IBlockData var3, boolean var4) {
/*  65 */     if (var0.a(var3.getBlock())) {
/*     */       return;
/*     */     }
/*     */     
/*  69 */     TileEntity var5 = var1.getTileEntity(var2);
/*  70 */     if (var5 instanceof TileEntityFurnace) {
/*  71 */       InventoryUtils.dropInventory(var1, var2, (TileEntityFurnace)var5);
/*  72 */       ((TileEntityFurnace)var5).a(var1, Vec3D.a(var2));
/*  73 */       var1.updateAdjacentComparators(var2, this);
/*     */     } 
/*  75 */     super.remove(var0, var1, var2, var3, var4);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isComplexRedstone(IBlockData var0) {
/*  80 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(IBlockData var0, World var1, BlockPosition var2) {
/*  85 */     return Container.a(var1.getTileEntity(var2));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumRenderType b(IBlockData var0) {
/*  93 */     return EnumRenderType.MODEL;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData var0, EnumBlockRotation var1) {
/*  98 */     return var0.set(FACING, var1.a((EnumDirection)var0.get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData var0, EnumBlockMirror var1) {
/* 103 */     return var0.a(var1.a((EnumDirection)var0.get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 108 */     var0.a((IBlockState<?>[])new IBlockState[] { FACING, LIT });
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */