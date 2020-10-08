/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Random;
/*     */ import java.util.stream.IntStream;
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
/*     */ public class BlockStairs
/*     */   extends Block
/*     */   implements IBlockWaterlogged
/*     */ {
/*  35 */   public static final BlockStateDirection FACING = BlockFacingHorizontal.FACING;
/*  36 */   public static final BlockStateEnum<BlockPropertyHalf> HALF = BlockProperties.ab;
/*  37 */   public static final BlockStateEnum<BlockPropertyStairsShape> SHAPE = BlockProperties.aL;
/*  38 */   public static final BlockStateBoolean d = BlockProperties.C;
/*     */   
/*  40 */   protected static final VoxelShape e = BlockStepAbstract.d;
/*  41 */   protected static final VoxelShape f = BlockStepAbstract.c;
/*     */   
/*  43 */   protected static final VoxelShape g = Block.a(0.0D, 0.0D, 0.0D, 8.0D, 8.0D, 8.0D);
/*  44 */   protected static final VoxelShape h = Block.a(0.0D, 0.0D, 8.0D, 8.0D, 8.0D, 16.0D);
/*  45 */   protected static final VoxelShape i = Block.a(0.0D, 8.0D, 0.0D, 8.0D, 16.0D, 8.0D);
/*  46 */   protected static final VoxelShape j = Block.a(0.0D, 8.0D, 8.0D, 8.0D, 16.0D, 16.0D);
/*  47 */   protected static final VoxelShape k = Block.a(8.0D, 0.0D, 0.0D, 16.0D, 8.0D, 8.0D);
/*  48 */   protected static final VoxelShape o = Block.a(8.0D, 0.0D, 8.0D, 16.0D, 8.0D, 16.0D);
/*  49 */   protected static final VoxelShape p = Block.a(8.0D, 8.0D, 0.0D, 16.0D, 16.0D, 8.0D);
/*  50 */   protected static final VoxelShape q = Block.a(8.0D, 8.0D, 8.0D, 16.0D, 16.0D, 16.0D);
/*     */   
/*  52 */   protected static final VoxelShape[] r = a(e, g, k, h, o);
/*  53 */   protected static final VoxelShape[] s = a(f, i, p, j, q);
/*     */   
/*     */   private static VoxelShape[] a(VoxelShape var0, VoxelShape var1, VoxelShape var2, VoxelShape var3, VoxelShape var4) {
/*  56 */     return (VoxelShape[])IntStream.range(0, 16).mapToObj(var5 -> a(var5, var0, var1, var2, var3, var4)).toArray(var0 -> new VoxelShape[var0]);
/*     */   }
/*     */   
/*     */   private static VoxelShape a(int var0, VoxelShape var1, VoxelShape var2, VoxelShape var3, VoxelShape var4, VoxelShape var5) {
/*  60 */     VoxelShape var6 = var1;
/*  61 */     if ((var0 & 0x1) != 0) {
/*  62 */       var6 = VoxelShapes.a(var6, var2);
/*     */     }
/*  64 */     if ((var0 & 0x2) != 0) {
/*  65 */       var6 = VoxelShapes.a(var6, var3);
/*     */     }
/*  67 */     if ((var0 & 0x4) != 0) {
/*  68 */       var6 = VoxelShapes.a(var6, var4);
/*     */     }
/*  70 */     if ((var0 & 0x8) != 0) {
/*  71 */       var6 = VoxelShapes.a(var6, var5);
/*     */     }
/*  73 */     return var6;
/*     */   }
/*     */   
/*  76 */   private static final int[] t = new int[] { 12, 5, 3, 10, 14, 13, 7, 11, 13, 7, 11, 14, 8, 4, 1, 2, 4, 1, 2, 8 };
/*     */ 
/*     */ 
/*     */   
/*     */   private final Block u;
/*     */ 
/*     */   
/*     */   private final IBlockData v;
/*     */ 
/*     */ 
/*     */   
/*     */   protected BlockStairs(IBlockData var0, BlockBase.Info var1) {
/*  88 */     super(var1);
/*  89 */     j(((IBlockData)this.blockStateList.getBlockData()).set(FACING, EnumDirection.NORTH).set(HALF, BlockPropertyHalf.BOTTOM).set(SHAPE, BlockPropertyStairsShape.STRAIGHT).set(d, Boolean.valueOf(false)));
/*  90 */     this.u = var0.getBlock();
/*  91 */     this.v = var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c_(IBlockData var0) {
/*  96 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 101 */     return ((var0.get(HALF) == BlockPropertyHalf.TOP) ? r : s)[t[l(var0)]];
/*     */   }
/*     */   
/*     */   private int l(IBlockData var0) {
/* 105 */     return ((BlockPropertyStairsShape)var0.get(SHAPE)).ordinal() * 4 + ((EnumDirection)var0.get(FACING)).get2DRotationValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attack(IBlockData var0, World var1, BlockPosition var2, EntityHuman var3) {
/* 115 */     this.v.attack(var1, var2, var3);
/*     */   }
/*     */ 
/*     */   
/*     */   public void postBreak(GeneratorAccess var0, BlockPosition var1, IBlockData var2) {
/* 120 */     this.u.postBreak(var0, var1, var2);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getDurability() {
/* 125 */     return this.u.getDurability();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlace(IBlockData var0, World var1, BlockPosition var2, IBlockData var3, boolean var4) {
/* 130 */     if (var0.a(var0.getBlock())) {
/*     */       return;
/*     */     }
/* 133 */     this.v.doPhysics(var1, var2, Blocks.AIR, var2, false);
/* 134 */     this.u.onPlace(this.v, var1, var2, var3, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(IBlockData var0, World var1, BlockPosition var2, IBlockData var3, boolean var4) {
/* 139 */     if (var0.a(var3.getBlock())) {
/*     */       return;
/*     */     }
/* 142 */     this.v.remove(var1, var2, var3, var4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stepOn(World var0, BlockPosition var1, Entity var2) {
/* 152 */     this.u.stepOn(var0, var1, var2);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTicking(IBlockData var0) {
/* 157 */     return this.u.isTicking(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick(IBlockData var0, WorldServer var1, BlockPosition var2, Random var3) {
/* 162 */     this.u.tick(var0, var1, var2, var3);
/*     */   }
/*     */ 
/*     */   
/*     */   public void tickAlways(IBlockData var0, WorldServer var1, BlockPosition var2, Random var3) {
/* 167 */     this.u.tickAlways(var0, var1, var2, var3);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData var0, World var1, BlockPosition var2, EntityHuman var3, EnumHand var4, MovingObjectPositionBlock var5) {
/* 172 */     return this.v.interact(var1, var3, var4, var5);
/*     */   }
/*     */ 
/*     */   
/*     */   public void wasExploded(World var0, BlockPosition var1, Explosion var2) {
/* 177 */     this.u.wasExploded(var0, var1, var2);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(BlockActionContext var0) {
/* 182 */     EnumDirection var1 = var0.getClickedFace();
/* 183 */     BlockPosition var2 = var0.getClickPosition();
/* 184 */     Fluid var3 = var0.getWorld().getFluid(var2);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 189 */     IBlockData var4 = getBlockData().set(FACING, var0.f()).set(HALF, (var1 == EnumDirection.DOWN || (var1 != EnumDirection.UP && (var0.getPos()).y - var2.getY() > 0.5D)) ? BlockPropertyHalf.TOP : BlockPropertyHalf.BOTTOM).set(d, Boolean.valueOf((var3.getType() == FluidTypes.WATER)));
/*     */     
/* 191 */     return var4.set(SHAPE, g(var4, var0.getWorld(), var2));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/* 196 */     if (((Boolean)var0.get(d)).booleanValue()) {
/* 197 */       var3.getFluidTickList().a(var4, FluidTypes.WATER, FluidTypes.WATER.a(var3));
/*     */     }
/* 199 */     if (var1.n().d()) {
/* 200 */       return var0.set(SHAPE, g(var0, var3, var4));
/*     */     }
/* 202 */     return super.updateState(var0, var1, var2, var3, var4, var5);
/*     */   }
/*     */   
/*     */   private static BlockPropertyStairsShape g(IBlockData var0, IBlockAccess var1, BlockPosition var2) {
/* 206 */     EnumDirection var3 = (EnumDirection)var0.get(FACING);
/* 207 */     IBlockData var4 = var1.getType(var2.shift(var3));
/* 208 */     if (h(var4) && var0.get(HALF) == var4.get(HALF)) {
/* 209 */       EnumDirection enumDirection = (EnumDirection)var4.get(FACING);
/* 210 */       if (enumDirection.n() != ((EnumDirection)var0.get(FACING)).n() && d(var0, var1, var2, enumDirection.opposite())) {
/* 211 */         if (enumDirection == var3.h()) {
/* 212 */           return BlockPropertyStairsShape.OUTER_LEFT;
/*     */         }
/* 214 */         return BlockPropertyStairsShape.OUTER_RIGHT;
/*     */       } 
/*     */     } 
/*     */     
/* 218 */     IBlockData var5 = var1.getType(var2.shift(var3.opposite()));
/* 219 */     if (h(var5) && var0.get(HALF) == var5.get(HALF)) {
/* 220 */       EnumDirection var6 = (EnumDirection)var5.get(FACING);
/* 221 */       if (var6.n() != ((EnumDirection)var0.get(FACING)).n() && d(var0, var1, var2, var6)) {
/* 222 */         if (var6 == var3.h()) {
/* 223 */           return BlockPropertyStairsShape.INNER_LEFT;
/*     */         }
/* 225 */         return BlockPropertyStairsShape.INNER_RIGHT;
/*     */       } 
/*     */     } 
/*     */     
/* 229 */     return BlockPropertyStairsShape.STRAIGHT;
/*     */   }
/*     */   
/*     */   private static boolean d(IBlockData var0, IBlockAccess var1, BlockPosition var2, EnumDirection var3) {
/* 233 */     IBlockData var4 = var1.getType(var2.shift(var3));
/* 234 */     return (!h(var4) || var4.get(FACING) != var0.get(FACING) || var4.get(HALF) != var0.get(HALF));
/*     */   }
/*     */   
/*     */   public static boolean h(IBlockData var0) {
/* 238 */     return var0.getBlock() instanceof BlockStairs;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData var0, EnumBlockRotation var1) {
/* 243 */     return var0.set(FACING, var1.a((EnumDirection)var0.get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData var0, EnumBlockMirror var1) {
/* 248 */     EnumDirection var2 = (EnumDirection)var0.get(FACING);
/* 249 */     BlockPropertyStairsShape var3 = (BlockPropertyStairsShape)var0.get(SHAPE);
/* 250 */     switch (null.b[var1.ordinal()]) {
/*     */       case 1:
/* 252 */         if (var2.n() == EnumDirection.EnumAxis.Z) {
/* 253 */           switch (null.a[var3.ordinal()]) {
/*     */             case 1:
/* 255 */               return var0.a(EnumBlockRotation.CLOCKWISE_180).set(SHAPE, BlockPropertyStairsShape.INNER_RIGHT);
/*     */             case 2:
/* 257 */               return var0.a(EnumBlockRotation.CLOCKWISE_180).set(SHAPE, BlockPropertyStairsShape.INNER_LEFT);
/*     */             case 3:
/* 259 */               return var0.a(EnumBlockRotation.CLOCKWISE_180).set(SHAPE, BlockPropertyStairsShape.OUTER_RIGHT);
/*     */             case 4:
/* 261 */               return var0.a(EnumBlockRotation.CLOCKWISE_180).set(SHAPE, BlockPropertyStairsShape.OUTER_LEFT);
/*     */           } 
/* 263 */           return var0.a(EnumBlockRotation.CLOCKWISE_180);
/*     */         } 
/*     */         break;
/*     */       
/*     */       case 2:
/* 268 */         if (var2.n() == EnumDirection.EnumAxis.X) {
/* 269 */           switch (null.a[var3.ordinal()]) {
/*     */             case 1:
/* 271 */               return var0.a(EnumBlockRotation.CLOCKWISE_180).set(SHAPE, BlockPropertyStairsShape.INNER_LEFT);
/*     */             case 2:
/* 273 */               return var0.a(EnumBlockRotation.CLOCKWISE_180).set(SHAPE, BlockPropertyStairsShape.INNER_RIGHT);
/*     */             case 3:
/* 275 */               return var0.a(EnumBlockRotation.CLOCKWISE_180).set(SHAPE, BlockPropertyStairsShape.OUTER_RIGHT);
/*     */             case 4:
/* 277 */               return var0.a(EnumBlockRotation.CLOCKWISE_180).set(SHAPE, BlockPropertyStairsShape.OUTER_LEFT);
/*     */             case 5:
/* 279 */               return var0.a(EnumBlockRotation.CLOCKWISE_180);
/*     */           } 
/*     */         
/*     */         }
/*     */         break;
/*     */     } 
/*     */     
/* 286 */     return super.a(var0, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 291 */     var0.a((IBlockState<?>[])new IBlockState[] { FACING, HALF, SHAPE, d });
/*     */   }
/*     */ 
/*     */   
/*     */   public Fluid d(IBlockData var0) {
/* 296 */     if (((Boolean)var0.get(d)).booleanValue()) {
/* 297 */       return FluidTypes.WATER.a(false);
/*     */     }
/* 299 */     return super.d(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData var0, IBlockAccess var1, BlockPosition var2, PathMode var3) {
/* 304 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockStairs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */