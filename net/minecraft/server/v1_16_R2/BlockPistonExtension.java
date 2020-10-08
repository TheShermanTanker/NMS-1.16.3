/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Arrays;
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
/*     */ public class BlockPistonExtension
/*     */   extends BlockDirectional
/*     */ {
/*  30 */   public static final BlockStateEnum<BlockPropertyPistonType> TYPE = BlockProperties.aJ;
/*  31 */   public static final BlockStateBoolean SHORT = BlockProperties.x;
/*     */ 
/*     */ 
/*     */   
/*  35 */   protected static final VoxelShape d = Block.a(12.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
/*  36 */   protected static final VoxelShape e = Block.a(0.0D, 0.0D, 0.0D, 4.0D, 16.0D, 16.0D);
/*  37 */   protected static final VoxelShape f = Block.a(0.0D, 0.0D, 12.0D, 16.0D, 16.0D, 16.0D);
/*  38 */   protected static final VoxelShape g = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 4.0D);
/*  39 */   protected static final VoxelShape h = Block.a(0.0D, 12.0D, 0.0D, 16.0D, 16.0D, 16.0D);
/*  40 */   protected static final VoxelShape i = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   protected static final VoxelShape j = Block.a(6.0D, -4.0D, 6.0D, 10.0D, 12.0D, 10.0D);
/*  47 */   protected static final VoxelShape k = Block.a(6.0D, 4.0D, 6.0D, 10.0D, 20.0D, 10.0D);
/*  48 */   protected static final VoxelShape o = Block.a(6.0D, 6.0D, -4.0D, 10.0D, 10.0D, 12.0D);
/*  49 */   protected static final VoxelShape p = Block.a(6.0D, 6.0D, 4.0D, 10.0D, 10.0D, 20.0D);
/*  50 */   protected static final VoxelShape q = Block.a(-4.0D, 6.0D, 6.0D, 12.0D, 10.0D, 10.0D);
/*  51 */   protected static final VoxelShape r = Block.a(4.0D, 6.0D, 6.0D, 20.0D, 10.0D, 10.0D);
/*     */   
/*  53 */   protected static final VoxelShape s = Block.a(6.0D, 0.0D, 6.0D, 10.0D, 12.0D, 10.0D);
/*  54 */   protected static final VoxelShape t = Block.a(6.0D, 4.0D, 6.0D, 10.0D, 16.0D, 10.0D);
/*  55 */   protected static final VoxelShape u = Block.a(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 12.0D);
/*  56 */   protected static final VoxelShape v = Block.a(6.0D, 6.0D, 4.0D, 10.0D, 10.0D, 16.0D);
/*  57 */   protected static final VoxelShape w = Block.a(0.0D, 6.0D, 6.0D, 12.0D, 10.0D, 10.0D);
/*  58 */   protected static final VoxelShape x = Block.a(4.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D);
/*     */   
/*  60 */   private static final VoxelShape[] y = a(true);
/*  61 */   private static final VoxelShape[] z = a(false);
/*     */   
/*     */   private static VoxelShape[] a(boolean var0) {
/*  64 */     return (VoxelShape[])Arrays.<EnumDirection>stream(EnumDirection.values()).map(var1 -> a(var1, var0)).toArray(var0 -> new VoxelShape[var0]);
/*     */   }
/*     */   
/*     */   private static VoxelShape a(EnumDirection var0, boolean var1) {
/*  68 */     switch (null.a[var0.ordinal()])
/*     */     
/*     */     { default:
/*  71 */         return VoxelShapes.a(i, var1 ? t : k);
/*     */       case 2:
/*  73 */         return VoxelShapes.a(h, var1 ? s : j);
/*     */       case 3:
/*  75 */         return VoxelShapes.a(g, var1 ? v : p);
/*     */       case 4:
/*  77 */         return VoxelShapes.a(f, var1 ? u : o);
/*     */       case 5:
/*  79 */         return VoxelShapes.a(e, var1 ? x : r);
/*     */       case 6:
/*  81 */         break; }  return VoxelShapes.a(d, var1 ? w : q);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPistonExtension(BlockBase.Info var0) {
/*  86 */     super(var0);
/*  87 */     j(((IBlockData)this.blockStateList.getBlockData()).set(FACING, EnumDirection.NORTH).set(TYPE, BlockPropertyPistonType.DEFAULT).set(SHORT, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c_(IBlockData var0) {
/*  92 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/*  97 */     return (((Boolean)var0.get(SHORT)).booleanValue() ? y : z)[((EnumDirection)var0.get(FACING)).ordinal()];
/*     */   }
/*     */   
/*     */   private boolean a(IBlockData var0, IBlockData var1) {
/* 101 */     Block var2 = (var0.get(TYPE) == BlockPropertyPistonType.DEFAULT) ? Blocks.PISTON : Blocks.STICKY_PISTON;
/* 102 */     return (var1.a(var2) && ((Boolean)var1.get(BlockPiston.EXTENDED)).booleanValue() && var1.get(FACING) == var0.get(FACING));
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World var0, BlockPosition var1, IBlockData var2, EntityHuman var3) {
/* 107 */     if (!var0.isClientSide && var3.abilities.canInstantlyBuild) {
/* 108 */       BlockPosition var4 = var1.shift(((EnumDirection)var2.get(FACING)).opposite());
/* 109 */       if (a(var2, var0.getType(var4))) {
/* 110 */         var0.b(var4, false);
/*     */       }
/*     */     } 
/* 113 */     super.a(var0, var1, var2, var3);
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(IBlockData var0, World var1, BlockPosition var2, IBlockData var3, boolean var4) {
/* 118 */     if (var0.a(var3.getBlock())) {
/*     */       return;
/*     */     }
/* 121 */     super.remove(var0, var1, var2, var3, var4);
/*     */ 
/*     */     
/* 124 */     BlockPosition var5 = var2.shift(((EnumDirection)var0.get(FACING)).opposite());
/* 125 */     if (a(var0, var1.getType(var5))) {
/* 126 */       var1.b(var5, true);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/* 132 */     if (var1.opposite() == var0.get(FACING) && 
/* 133 */       !var0.canPlace(var3, var4)) {
/* 134 */       return Blocks.AIR.getBlockData();
/*     */     }
/*     */     
/* 137 */     return super.updateState(var0, var1, var2, var3, var4, var5);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(IBlockData var0, IWorldReader var1, BlockPosition var2) {
/* 142 */     IBlockData var3 = var1.getType(var2.shift(((EnumDirection)var0.get(FACING)).opposite()));
/*     */     
/* 144 */     return (a(var0, var3) || (var3.a(Blocks.MOVING_PISTON) && var3.get(FACING) == var0.get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void doPhysics(IBlockData var0, World var1, BlockPosition var2, Block var3, BlockPosition var4, boolean var5) {
/* 149 */     if (var0.canPlace(var1, var2)) {
/* 150 */       BlockPosition var6 = var2.shift(((EnumDirection)var0.get(FACING)).opposite());
/* 151 */       var1.getType(var6).doPhysics(var1, var6, var3, var4, false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData var0, EnumBlockRotation var1) {
/* 162 */     return var0.set(FACING, var1.a((EnumDirection)var0.get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData var0, EnumBlockMirror var1) {
/* 167 */     return var0.a(var1.a((EnumDirection)var0.get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 172 */     var0.a((IBlockState<?>[])new IBlockState[] { FACING, TYPE, SHORT });
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData var0, IBlockAccess var1, BlockPosition var2, PathMode var3) {
/* 177 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockPistonExtension.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */