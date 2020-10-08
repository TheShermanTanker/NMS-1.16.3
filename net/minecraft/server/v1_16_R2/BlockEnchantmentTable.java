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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockEnchantmentTable
/*     */   extends BlockTileEntity
/*     */ {
/*  30 */   protected static final VoxelShape a = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);
/*     */   
/*     */   protected BlockEnchantmentTable(BlockBase.Info var0) {
/*  33 */     super(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c_(IBlockData var0) {
/*  38 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/*  43 */     return a;
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
/*     */   public EnumRenderType b(IBlockData var0) {
/*  74 */     return EnumRenderType.MODEL;
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity createTile(IBlockAccess var0) {
/*  79 */     return new TileEntityEnchantTable();
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData var0, World var1, BlockPosition var2, EntityHuman var3, EnumHand var4, MovingObjectPositionBlock var5) {
/*  84 */     if (var1.isClientSide) {
/*  85 */       return EnumInteractionResult.SUCCESS;
/*     */     }
/*     */     
/*  88 */     var3.openContainer(var0.b(var1, var2));
/*  89 */     return EnumInteractionResult.CONSUME;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ITileInventory getInventory(IBlockData var0, World var1, BlockPosition var2) {
/*  95 */     TileEntity var3 = var1.getTileEntity(var2);
/*  96 */     if (var3 instanceof TileEntityEnchantTable) {
/*  97 */       IChatBaseComponent var4 = ((INamableTileEntity)var3).getScoreboardDisplayName();
/*     */       
/*  99 */       return new TileInventory((var2, var3, var4) -> new ContainerEnchantTable(var2, var3, ContainerAccess.at(var0, var1)), var4);
/*     */     } 
/* 101 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void postPlace(World var0, BlockPosition var1, IBlockData var2, EntityLiving var3, ItemStack var4) {
/* 106 */     if (var4.hasName()) {
/* 107 */       TileEntity var5 = var0.getTileEntity(var1);
/* 108 */       if (var5 instanceof TileEntityEnchantTable) {
/* 109 */         ((TileEntityEnchantTable)var5).setCustomName(var4.getName());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData var0, IBlockAccess var1, BlockPosition var2, PathMode var3) {
/* 116 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockEnchantmentTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */