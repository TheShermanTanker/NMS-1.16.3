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
/*     */ public class BlockStructure
/*     */   extends BlockTileEntity
/*     */ {
/*  25 */   public static final BlockStateEnum<BlockPropertyStructureMode> a = BlockProperties.aM;
/*     */   
/*     */   protected BlockStructure(BlockBase.Info var0) {
/*  28 */     super(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity createTile(IBlockAccess var0) {
/*  33 */     return new TileEntityStructure();
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData var0, World var1, BlockPosition var2, EntityHuman var3, EnumHand var4, MovingObjectPositionBlock var5) {
/*  38 */     TileEntity var6 = var1.getTileEntity(var2);
/*  39 */     if (var6 instanceof TileEntityStructure) {
/*  40 */       return ((TileEntityStructure)var6).a(var3) ? EnumInteractionResult.a(var1.isClientSide) : EnumInteractionResult.PASS;
/*     */     }
/*     */     
/*  43 */     return EnumInteractionResult.PASS;
/*     */   }
/*     */ 
/*     */   
/*     */   public void postPlace(World var0, BlockPosition var1, IBlockData var2, @Nullable EntityLiving var3, ItemStack var4) {
/*  48 */     if (var0.isClientSide) {
/*     */       return;
/*     */     }
/*  51 */     if (var3 != null) {
/*  52 */       TileEntity var5 = var0.getTileEntity(var1);
/*  53 */       if (var5 instanceof TileEntityStructure) {
/*  54 */         ((TileEntityStructure)var5).setAuthor(var3);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumRenderType b(IBlockData var0) {
/*  61 */     return EnumRenderType.MODEL;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(BlockActionContext var0) {
/*  66 */     return getBlockData().set(a, BlockPropertyStructureMode.DATA);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/*  71 */     var0.a((IBlockState<?>[])new IBlockState[] { a });
/*     */   }
/*     */ 
/*     */   
/*     */   public void doPhysics(IBlockData var0, World var1, BlockPosition var2, Block var3, BlockPosition var4, boolean var5) {
/*  76 */     if (!(var1 instanceof WorldServer)) {
/*     */       return;
/*     */     }
/*     */     
/*  80 */     TileEntity var6 = var1.getTileEntity(var2);
/*  81 */     if (!(var6 instanceof TileEntityStructure)) {
/*     */       return;
/*     */     }
/*     */     
/*  85 */     TileEntityStructure var7 = (TileEntityStructure)var6;
/*     */     
/*  87 */     boolean var8 = var1.isBlockIndirectlyPowered(var2);
/*  88 */     boolean var9 = var7.G();
/*     */     
/*  90 */     if (var8 && !var9) {
/*  91 */       var7.c(true);
/*  92 */       a((WorldServer)var1, var7);
/*  93 */     } else if (!var8 && var9) {
/*  94 */       var7.c(false);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void a(WorldServer var0, TileEntityStructure var1) {
/*  99 */     switch (null.a[var1.getUsageMode().ordinal()]) {
/*     */       case 1:
/* 101 */         var1.b(false);
/*     */         break;
/*     */       case 2:
/* 104 */         var1.a(var0, false);
/*     */         break;
/*     */       case 3:
/* 107 */         var1.E();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */