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
/*    */ 
/*    */ public abstract class BlockSign
/*    */   extends BlockTileEntity
/*    */   implements IBlockWaterlogged
/*    */ {
/* 26 */   public static final BlockStateBoolean a = BlockProperties.C;
/*    */   
/* 28 */   protected static final VoxelShape b = Block.a(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);
/*    */   private final BlockPropertyWood c;
/*    */   
/*    */   protected BlockSign(BlockBase.Info var0, BlockPropertyWood var1) {
/* 32 */     super(var0);
/* 33 */     this.c = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/* 38 */     if (((Boolean)var0.get(a)).booleanValue()) {
/* 39 */       var3.getFluidTickList().a(var4, FluidTypes.WATER, FluidTypes.WATER.a(var3));
/*    */     }
/*    */     
/* 42 */     return super.updateState(var0, var1, var2, var3, var4, var5);
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 47 */     return b;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean ai_() {
/* 52 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public TileEntity createTile(IBlockAccess var0) {
/* 57 */     return new TileEntitySign();
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult interact(IBlockData var0, World var1, BlockPosition var2, EntityHuman var3, EnumHand var4, MovingObjectPositionBlock var5) {
/* 62 */     ItemStack var6 = var3.b(var4);
/* 63 */     boolean var7 = (var6.getItem() instanceof ItemDye && var3.abilities.mayBuild);
/*    */     
/* 65 */     if (var1.isClientSide) {
/* 66 */       return var7 ? EnumInteractionResult.SUCCESS : EnumInteractionResult.CONSUME;
/*    */     }
/*    */     
/* 69 */     TileEntity var8 = var1.getTileEntity(var2);
/* 70 */     if (var8 instanceof TileEntitySign) {
/* 71 */       TileEntitySign var9 = (TileEntitySign)var8;
/* 72 */       if (var7) {
/* 73 */         boolean var10 = var9.setColor(((ItemDye)var6.getItem()).d());
/* 74 */         if (var10 && !var3.isCreative()) {
/* 75 */           var6.subtract(1);
/*    */         }
/*    */       } 
/*    */       
/* 79 */       return var9.b(var3) ? EnumInteractionResult.SUCCESS : EnumInteractionResult.PASS;
/*    */     } 
/*    */     
/* 82 */     return EnumInteractionResult.PASS;
/*    */   }
/*    */ 
/*    */   
/*    */   public Fluid d(IBlockData var0) {
/* 87 */     if (((Boolean)var0.get(a)).booleanValue()) {
/* 88 */       return FluidTypes.WATER.a(false);
/*    */     }
/* 90 */     return super.d(var0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockSign.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */