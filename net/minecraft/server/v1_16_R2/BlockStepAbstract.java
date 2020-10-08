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
/*     */ public class BlockStepAbstract
/*     */   extends Block
/*     */   implements IBlockWaterlogged
/*     */ {
/*  27 */   public static final BlockStateEnum<BlockPropertySlabType> a = BlockProperties.aK;
/*  28 */   public static final BlockStateBoolean b = BlockProperties.C;
/*     */   
/*  30 */   protected static final VoxelShape c = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
/*  31 */   protected static final VoxelShape d = Block.a(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D);
/*     */   
/*     */   public BlockStepAbstract(BlockBase.Info var0) {
/*  34 */     super(var0);
/*     */     
/*  36 */     j(getBlockData().set(a, BlockPropertySlabType.BOTTOM).set(b, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c_(IBlockData var0) {
/*  41 */     return (var0.get(a) != BlockPropertySlabType.DOUBLE);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/*  46 */     var0.a((IBlockState<?>[])new IBlockState[] { a, b });
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/*  51 */     BlockPropertySlabType var4 = (BlockPropertySlabType)var0.get(a);
/*  52 */     switch (null.a[var4.ordinal()]) {
/*     */       case 1:
/*  54 */         return VoxelShapes.b();
/*     */       case 2:
/*  56 */         return d;
/*     */     } 
/*  58 */     return c;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public IBlockData getPlacedState(BlockActionContext var0) {
/*  65 */     BlockPosition var1 = var0.getClickPosition();
/*  66 */     IBlockData var2 = var0.getWorld().getType(var1);
/*  67 */     if (var2.a(this)) {
/*  68 */       return var2.set(a, BlockPropertySlabType.DOUBLE).set(b, Boolean.valueOf(false));
/*     */     }
/*     */     
/*  71 */     Fluid var3 = var0.getWorld().getFluid(var1);
/*  72 */     IBlockData var4 = getBlockData().set(a, BlockPropertySlabType.BOTTOM).set(b, Boolean.valueOf((var3.getType() == FluidTypes.WATER)));
/*     */     
/*  74 */     EnumDirection var5 = var0.getClickedFace();
/*  75 */     if (var5 == EnumDirection.DOWN || (var5 != EnumDirection.UP && (var0.getPos()).y - var1.getY() > 0.5D)) {
/*  76 */       return var4.set(a, BlockPropertySlabType.TOP);
/*     */     }
/*  78 */     return var4;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData var0, BlockActionContext var1) {
/*  83 */     ItemStack var2 = var1.getItemStack();
/*     */     
/*  85 */     BlockPropertySlabType var3 = (BlockPropertySlabType)var0.get(a);
/*  86 */     if (var3 == BlockPropertySlabType.DOUBLE || var2.getItem() != getItem()) {
/*  87 */       return false;
/*     */     }
/*     */     
/*  90 */     if (var1.c()) {
/*  91 */       boolean var4 = ((var1.getPos()).y - var1.getClickPosition().getY() > 0.5D);
/*  92 */       EnumDirection var5 = var1.getClickedFace();
/*  93 */       if (var3 == BlockPropertySlabType.BOTTOM) {
/*  94 */         return (var5 == EnumDirection.UP || (var4 && var5.n().d()));
/*     */       }
/*  96 */       return (var5 == EnumDirection.DOWN || (!var4 && var5.n().d()));
/*     */     } 
/*     */     
/*  99 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Fluid d(IBlockData var0) {
/* 104 */     if (((Boolean)var0.get(b)).booleanValue()) {
/* 105 */       return FluidTypes.WATER.a(false);
/*     */     }
/* 107 */     return super.d(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean place(GeneratorAccess var0, BlockPosition var1, IBlockData var2, Fluid var3) {
/* 112 */     if (var2.get(a) != BlockPropertySlabType.DOUBLE) {
/* 113 */       return super.place(var0, var1, var2, var3);
/*     */     }
/* 115 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(IBlockAccess var0, BlockPosition var1, IBlockData var2, FluidType var3) {
/* 120 */     if (var2.get(a) != BlockPropertySlabType.DOUBLE) {
/* 121 */       return super.canPlace(var0, var1, var2, var3);
/*     */     }
/* 123 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/* 128 */     if (((Boolean)var0.get(b)).booleanValue()) {
/* 129 */       var3.getFluidTickList().a(var4, FluidTypes.WATER, FluidTypes.WATER.a(var3));
/*     */     }
/* 131 */     return super.updateState(var0, var1, var2, var3, var4, var5);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData var0, IBlockAccess var1, BlockPosition var2, PathMode var3) {
/* 136 */     switch (null.b[var3.ordinal()]) {
/*     */       case 1:
/* 138 */         return false;
/*     */       case 2:
/* 140 */         return var1.getFluid(var2).a(TagsFluid.WATER);
/*     */       case 3:
/* 142 */         return false;
/*     */     } 
/* 144 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockStepAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */