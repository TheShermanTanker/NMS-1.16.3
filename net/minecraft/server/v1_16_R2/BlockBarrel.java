/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Random;
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
/*     */ 
/*     */ public class BlockBarrel
/*     */   extends BlockTileEntity
/*     */ {
/*  32 */   public static final BlockStateDirection a = BlockProperties.M;
/*  33 */   public static final BlockStateBoolean OPEN = BlockProperties.u;
/*     */   
/*     */   public BlockBarrel(BlockBase.Info var0) {
/*  36 */     super(var0);
/*  37 */     j(((IBlockData)this.blockStateList.getBlockData()).set(a, EnumDirection.NORTH).set(OPEN, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData var0, World var1, BlockPosition var2, EntityHuman var3, EnumHand var4, MovingObjectPositionBlock var5) {
/*  42 */     if (var1.isClientSide) {
/*  43 */       return EnumInteractionResult.SUCCESS;
/*     */     }
/*     */     
/*  46 */     TileEntity var6 = var1.getTileEntity(var2);
/*  47 */     if (var6 instanceof TileEntityBarrel) {
/*  48 */       var3.openContainer((TileEntityBarrel)var6);
/*  49 */       var3.a(StatisticList.OPEN_BARREL);
/*  50 */       PiglinAI.a(var3, true);
/*     */     } 
/*     */     
/*  53 */     return EnumInteractionResult.CONSUME;
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(IBlockData var0, World var1, BlockPosition var2, IBlockData var3, boolean var4) {
/*  58 */     if (var0.a(var3.getBlock())) {
/*     */       return;
/*     */     }
/*  61 */     TileEntity var5 = var1.getTileEntity(var2);
/*  62 */     if (var5 instanceof IInventory) {
/*  63 */       InventoryUtils.dropInventory(var1, var2, (IInventory)var5);
/*     */       
/*  65 */       var1.updateAdjacentComparators(var2, this);
/*     */     } 
/*  67 */     super.remove(var0, var1, var2, var3, var4);
/*     */   }
/*     */ 
/*     */   
/*     */   public void tickAlways(IBlockData var0, WorldServer var1, BlockPosition var2, Random var3) {
/*  72 */     TileEntity var4 = var1.getTileEntity(var2);
/*     */     
/*  74 */     if (var4 instanceof TileEntityBarrel) {
/*  75 */       ((TileEntityBarrel)var4).h();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public TileEntity createTile(IBlockAccess var0) {
/*  82 */     return new TileEntityBarrel();
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumRenderType b(IBlockData var0) {
/*  87 */     return EnumRenderType.MODEL;
/*     */   }
/*     */ 
/*     */   
/*     */   public void postPlace(World var0, BlockPosition var1, IBlockData var2, @Nullable EntityLiving var3, ItemStack var4) {
/*  92 */     if (var4.hasName()) {
/*  93 */       TileEntity var5 = var0.getTileEntity(var1);
/*  94 */       if (var5 instanceof TileEntityBarrel) {
/*  95 */         ((TileEntityBarrel)var5).setCustomName(var4.getName());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isComplexRedstone(IBlockData var0) {
/* 102 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(IBlockData var0, World var1, BlockPosition var2) {
/* 107 */     return Container.a(var1.getTileEntity(var2));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData var0, EnumBlockRotation var1) {
/* 112 */     return var0.set(a, var1.a((EnumDirection)var0.get(a)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData var0, EnumBlockMirror var1) {
/* 117 */     return var0.a(var1.a((EnumDirection)var0.get(a)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 122 */     var0.a((IBlockState<?>[])new IBlockState[] { a, OPEN });
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(BlockActionContext var0) {
/* 127 */     return getBlockData().set(a, var0.d().opposite());
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockBarrel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */