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
/*     */ public class BlockLadder
/*     */   extends Block
/*     */   implements IBlockWaterlogged
/*     */ {
/*  22 */   public static final BlockStateDirection FACING = BlockFacingHorizontal.FACING;
/*  23 */   public static final BlockStateBoolean b = BlockProperties.C;
/*     */   
/*  25 */   protected static final VoxelShape c = Block.a(0.0D, 0.0D, 0.0D, 3.0D, 16.0D, 16.0D);
/*  26 */   protected static final VoxelShape d = Block.a(13.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
/*  27 */   protected static final VoxelShape e = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 3.0D);
/*  28 */   protected static final VoxelShape f = Block.a(0.0D, 0.0D, 13.0D, 16.0D, 16.0D, 16.0D);
/*     */   
/*     */   protected BlockLadder(BlockBase.Info var0) {
/*  31 */     super(var0);
/*  32 */     j(((IBlockData)this.blockStateList.getBlockData()).set(FACING, EnumDirection.NORTH).set(b, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/*  37 */     switch (null.a[((EnumDirection)var0.get(FACING)).ordinal()]) {
/*     */       case 1:
/*  39 */         return f;
/*     */       case 2:
/*  41 */         return e;
/*     */       case 3:
/*  43 */         return d;
/*     */     } 
/*     */     
/*  46 */     return c;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean a(IBlockAccess var0, BlockPosition var1, EnumDirection var2) {
/*  51 */     IBlockData var3 = var0.getType(var1);
/*  52 */     return var3.d(var0, var1, var2);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(IBlockData var0, IWorldReader var1, BlockPosition var2) {
/*  57 */     EnumDirection var3 = (EnumDirection)var0.get(FACING);
/*  58 */     return a(var1, var2.shift(var3.opposite()), var3);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/*  63 */     if (var1.opposite() == var0.get(FACING) && !var0.canPlace(var3, var4)) {
/*  64 */       return Blocks.AIR.getBlockData();
/*     */     }
/*  66 */     if (((Boolean)var0.get(b)).booleanValue()) {
/*  67 */       var3.getFluidTickList().a(var4, FluidTypes.WATER, FluidTypes.WATER.a(var3));
/*     */     }
/*     */     
/*  70 */     return super.updateState(var0, var1, var2, var3, var4, var5);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public IBlockData getPlacedState(BlockActionContext var0) {
/*  77 */     if (!var0.c()) {
/*  78 */       IBlockData iBlockData = var0.getWorld().getType(var0.getClickPosition().shift(var0.getClickedFace().opposite()));
/*  79 */       if (iBlockData.a(this) && iBlockData.get(FACING) == var0.getClickedFace()) {
/*  80 */         return null;
/*     */       }
/*     */     } 
/*     */     
/*  84 */     IBlockData var1 = getBlockData();
/*     */     
/*  86 */     IWorldReader var2 = var0.getWorld();
/*  87 */     BlockPosition var3 = var0.getClickPosition();
/*  88 */     Fluid var4 = var0.getWorld().getFluid(var0.getClickPosition());
/*     */     
/*  90 */     for (EnumDirection var8 : var0.e()) {
/*  91 */       if (var8.n().d()) {
/*  92 */         var1 = var1.set(FACING, var8.opposite());
/*  93 */         if (var1.canPlace(var2, var3)) {
/*  94 */           return var1.set(b, Boolean.valueOf((var4.getType() == FluidTypes.WATER)));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  99 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData var0, EnumBlockRotation var1) {
/* 104 */     return var0.set(FACING, var1.a((EnumDirection)var0.get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData var0, EnumBlockMirror var1) {
/* 109 */     return var0.a(var1.a((EnumDirection)var0.get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 114 */     var0.a((IBlockState<?>[])new IBlockState[] { FACING, b });
/*     */   }
/*     */ 
/*     */   
/*     */   public Fluid d(IBlockData var0) {
/* 119 */     if (((Boolean)var0.get(b)).booleanValue()) {
/* 120 */       return FluidTypes.WATER.a(false);
/*     */     }
/* 122 */     return super.d(var0);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockLadder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */