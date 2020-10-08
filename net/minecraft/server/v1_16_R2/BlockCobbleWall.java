/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.Map;
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
/*     */ public class BlockCobbleWall
/*     */   extends Block
/*     */   implements IBlockWaterlogged
/*     */ {
/*  29 */   public static final BlockStateBoolean UP = BlockProperties.G;
/*  30 */   public static final BlockStateEnum<BlockPropertyWallHeight> b = BlockProperties.S;
/*  31 */   public static final BlockStateEnum<BlockPropertyWallHeight> c = BlockProperties.T;
/*  32 */   public static final BlockStateEnum<BlockPropertyWallHeight> d = BlockProperties.U;
/*  33 */   public static final BlockStateEnum<BlockPropertyWallHeight> e = BlockProperties.V;
/*  34 */   public static final BlockStateBoolean f = BlockProperties.C;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final Map<IBlockData, VoxelShape> g;
/*     */ 
/*     */ 
/*     */   
/*     */   private final Map<IBlockData, VoxelShape> h;
/*     */ 
/*     */ 
/*     */   
/*  47 */   private static final VoxelShape i = Block.a(7.0D, 0.0D, 7.0D, 9.0D, 16.0D, 9.0D);
/*  48 */   private static final VoxelShape j = Block.a(7.0D, 0.0D, 0.0D, 9.0D, 16.0D, 9.0D);
/*  49 */   private static final VoxelShape k = Block.a(7.0D, 0.0D, 7.0D, 9.0D, 16.0D, 16.0D);
/*  50 */   private static final VoxelShape o = Block.a(0.0D, 0.0D, 7.0D, 9.0D, 16.0D, 9.0D);
/*  51 */   private static final VoxelShape p = Block.a(7.0D, 0.0D, 7.0D, 16.0D, 16.0D, 9.0D);
/*     */   
/*     */   public BlockCobbleWall(BlockBase.Info var0) {
/*  54 */     super(var0);
/*  55 */     j(((IBlockData)this.blockStateList.getBlockData()).set(UP, Boolean.valueOf(true)).set(c, BlockPropertyWallHeight.NONE).set(b, BlockPropertyWallHeight.NONE).set(d, BlockPropertyWallHeight.NONE).set(e, BlockPropertyWallHeight.NONE).set(f, Boolean.valueOf(false)));
/*     */     
/*  57 */     this.g = a(4.0F, 3.0F, 16.0F, 0.0F, 14.0F, 16.0F);
/*  58 */     this.h = a(4.0F, 3.0F, 24.0F, 0.0F, 24.0F, 24.0F);
/*     */   }
/*     */   
/*     */   private static VoxelShape a(VoxelShape var0, BlockPropertyWallHeight var1, VoxelShape var2, VoxelShape var3) {
/*  62 */     if (var1 == BlockPropertyWallHeight.TALL) {
/*  63 */       return VoxelShapes.a(var0, var3);
/*     */     }
/*  65 */     if (var1 == BlockPropertyWallHeight.LOW) {
/*  66 */       return VoxelShapes.a(var0, var2);
/*     */     }
/*  68 */     return var0;
/*     */   }
/*     */   
/*     */   private Map<IBlockData, VoxelShape> a(float var0, float var1, float var2, float var3, float var4, float var5) {
/*  72 */     float var6 = 8.0F - var0;
/*  73 */     float var7 = 8.0F + var0;
/*     */     
/*  75 */     float var8 = 8.0F - var1;
/*  76 */     float var9 = 8.0F + var1;
/*     */     
/*  78 */     VoxelShape var10 = Block.a(var6, 0.0D, var6, var7, var2, var7);
/*  79 */     VoxelShape var11 = Block.a(var8, var3, 0.0D, var9, var4, var9);
/*  80 */     VoxelShape var12 = Block.a(var8, var3, var8, var9, var4, 16.0D);
/*  81 */     VoxelShape var13 = Block.a(0.0D, var3, var8, var9, var4, var9);
/*  82 */     VoxelShape var14 = Block.a(var8, var3, var8, 16.0D, var4, var9);
/*     */     
/*  84 */     VoxelShape var15 = Block.a(var8, var3, 0.0D, var9, var5, var9);
/*  85 */     VoxelShape var16 = Block.a(var8, var3, var8, var9, var5, 16.0D);
/*  86 */     VoxelShape var17 = Block.a(0.0D, var3, var8, var9, var5, var9);
/*  87 */     VoxelShape var18 = Block.a(var8, var3, var8, 16.0D, var5, var9);
/*     */ 
/*     */     
/*  90 */     ImmutableMap.Builder<IBlockData, VoxelShape> var19 = ImmutableMap.builder();
/*     */     
/*  92 */     for (Boolean var21 : UP.getValues()) {
/*  93 */       for (BlockPropertyWallHeight var23 : b.getValues()) {
/*  94 */         for (BlockPropertyWallHeight var25 : c.getValues()) {
/*  95 */           for (BlockPropertyWallHeight var27 : e.getValues()) {
/*  96 */             for (BlockPropertyWallHeight var29 : d.getValues()) {
/*  97 */               VoxelShape var30 = VoxelShapes.a();
/*  98 */               var30 = a(var30, var23, var14, var18);
/*  99 */               var30 = a(var30, var27, var13, var17);
/* 100 */               var30 = a(var30, var25, var11, var15);
/* 101 */               var30 = a(var30, var29, var12, var16);
/* 102 */               if (var21.booleanValue()) {
/* 103 */                 var30 = VoxelShapes.a(var30, var10);
/*     */               }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 110 */               IBlockData var31 = getBlockData().set(UP, var21).set(b, var23).set(e, var27).set(c, var25).set(d, var29);
/*     */               
/* 112 */               var19.put(var31.set(f, Boolean.valueOf(false)), var30);
/* 113 */               var19.put(var31.set(f, Boolean.valueOf(true)), var30);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 119 */     return (Map<IBlockData, VoxelShape>)var19.build();
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 124 */     return this.g.get(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape c(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 129 */     return this.h.get(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData var0, IBlockAccess var1, BlockPosition var2, PathMode var3) {
/* 134 */     return false;
/*     */   }
/*     */   
/*     */   private boolean a(IBlockData var0, boolean var1, EnumDirection var2) {
/* 138 */     Block var3 = var0.getBlock();
/*     */     
/* 140 */     boolean var4 = (var3 instanceof BlockFenceGate && BlockFenceGate.a(var0, var2));
/* 141 */     return (var0.a(TagsBlock.WALLS) || (!b(var3) && var1) || var3 instanceof BlockIronBars || var4);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(BlockActionContext var0) {
/* 146 */     IWorldReader var1 = var0.getWorld();
/* 147 */     BlockPosition var2 = var0.getClickPosition();
/* 148 */     Fluid var3 = var0.getWorld().getFluid(var0.getClickPosition());
/*     */     
/* 150 */     BlockPosition var4 = var2.north();
/* 151 */     BlockPosition var5 = var2.east();
/* 152 */     BlockPosition var6 = var2.south();
/* 153 */     BlockPosition var7 = var2.west();
/* 154 */     BlockPosition var8 = var2.up();
/*     */     
/* 156 */     IBlockData var9 = var1.getType(var4);
/* 157 */     IBlockData var10 = var1.getType(var5);
/* 158 */     IBlockData var11 = var1.getType(var6);
/* 159 */     IBlockData var12 = var1.getType(var7);
/* 160 */     IBlockData var13 = var1.getType(var8);
/*     */     
/* 162 */     boolean var14 = a(var9, var9.d(var1, var4, EnumDirection.SOUTH), EnumDirection.SOUTH);
/* 163 */     boolean var15 = a(var10, var10.d(var1, var5, EnumDirection.WEST), EnumDirection.WEST);
/* 164 */     boolean var16 = a(var11, var11.d(var1, var6, EnumDirection.NORTH), EnumDirection.NORTH);
/* 165 */     boolean var17 = a(var12, var12.d(var1, var7, EnumDirection.EAST), EnumDirection.EAST);
/*     */     
/* 167 */     IBlockData var18 = getBlockData().set(f, Boolean.valueOf((var3.getType() == FluidTypes.WATER)));
/* 168 */     return a(var1, var18, var8, var13, var14, var15, var16, var17);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/* 173 */     if (((Boolean)var0.get(f)).booleanValue()) {
/* 174 */       var3.getFluidTickList().a(var4, FluidTypes.WATER, FluidTypes.WATER.a(var3));
/*     */     }
/*     */     
/* 177 */     if (var1 == EnumDirection.DOWN) {
/* 178 */       return super.updateState(var0, var1, var2, var3, var4, var5);
/*     */     }
/*     */     
/* 181 */     if (var1 == EnumDirection.UP) {
/* 182 */       return a(var3, var0, var5, var2);
/*     */     }
/*     */     
/* 185 */     return a(var3, var4, var0, var5, var2, var1);
/*     */   }
/*     */   
/*     */   private static boolean a(IBlockData var0, IBlockState<BlockPropertyWallHeight> var1) {
/* 189 */     return (var0.get(var1) != BlockPropertyWallHeight.NONE);
/*     */   }
/*     */   
/*     */   private static boolean a(VoxelShape var0, VoxelShape var1) {
/* 193 */     return !VoxelShapes.c(var1, var0, OperatorBoolean.ONLY_FIRST);
/*     */   }
/*     */   
/*     */   private IBlockData a(IWorldReader var0, IBlockData var1, BlockPosition var2, IBlockData var3) {
/* 197 */     boolean var4 = a(var1, c);
/* 198 */     boolean var5 = a(var1, b);
/* 199 */     boolean var6 = a(var1, d);
/* 200 */     boolean var7 = a(var1, e);
/*     */     
/* 202 */     return a(var0, var1, var2, var3, var4, var5, var6, var7);
/*     */   }
/*     */   
/*     */   private IBlockData a(IWorldReader var0, BlockPosition var1, IBlockData var2, BlockPosition var3, IBlockData var4, EnumDirection var5) {
/* 206 */     EnumDirection var6 = var5.opposite();
/* 207 */     boolean var7 = (var5 == EnumDirection.NORTH) ? a(var4, var4.d(var0, var3, var6), var6) : a(var2, c);
/* 208 */     boolean var8 = (var5 == EnumDirection.EAST) ? a(var4, var4.d(var0, var3, var6), var6) : a(var2, b);
/* 209 */     boolean var9 = (var5 == EnumDirection.SOUTH) ? a(var4, var4.d(var0, var3, var6), var6) : a(var2, d);
/* 210 */     boolean var10 = (var5 == EnumDirection.WEST) ? a(var4, var4.d(var0, var3, var6), var6) : a(var2, e);
/*     */     
/* 212 */     BlockPosition var11 = var1.up();
/* 213 */     IBlockData var12 = var0.getType(var11);
/* 214 */     return a(var0, var2, var11, var12, var7, var8, var9, var10);
/*     */   }
/*     */   
/*     */   private IBlockData a(IWorldReader var0, IBlockData var1, BlockPosition var2, IBlockData var3, boolean var4, boolean var5, boolean var6, boolean var7) {
/* 218 */     VoxelShape var8 = var3.getCollisionShape(var0, var2).a(EnumDirection.DOWN);
/* 219 */     IBlockData var9 = a(var1, var4, var5, var6, var7, var8);
/*     */     
/* 221 */     return var9.set(UP, Boolean.valueOf(a(var9, var3, var8)));
/*     */   }
/*     */   
/*     */   private boolean a(IBlockData var0, IBlockData var1, VoxelShape var2) {
/* 225 */     boolean var3 = (var1.getBlock() instanceof BlockCobbleWall && ((Boolean)var1.get(UP)).booleanValue());
/* 226 */     if (var3) {
/* 227 */       return true;
/*     */     }
/*     */     
/* 230 */     BlockPropertyWallHeight var4 = (BlockPropertyWallHeight)var0.get(c);
/* 231 */     BlockPropertyWallHeight var5 = (BlockPropertyWallHeight)var0.get(d);
/* 232 */     BlockPropertyWallHeight var6 = (BlockPropertyWallHeight)var0.get(b);
/* 233 */     BlockPropertyWallHeight var7 = (BlockPropertyWallHeight)var0.get(e);
/*     */     
/* 235 */     boolean var8 = (var5 == BlockPropertyWallHeight.NONE);
/* 236 */     boolean var9 = (var7 == BlockPropertyWallHeight.NONE);
/* 237 */     boolean var10 = (var6 == BlockPropertyWallHeight.NONE);
/* 238 */     boolean var11 = (var4 == BlockPropertyWallHeight.NONE);
/*     */     
/* 240 */     boolean var12 = ((var11 && var8 && var9 && var10) || var11 != var8 || var9 != var10);
/*     */ 
/*     */     
/* 243 */     if (var12) {
/* 244 */       return true;
/*     */     }
/*     */     
/* 247 */     boolean var13 = ((var4 == BlockPropertyWallHeight.TALL && var5 == BlockPropertyWallHeight.TALL) || (var6 == BlockPropertyWallHeight.TALL && var7 == BlockPropertyWallHeight.TALL));
/*     */ 
/*     */     
/* 250 */     if (var13) {
/* 251 */       return false;
/*     */     }
/*     */     
/* 254 */     return (var1.getBlock().a(TagsBlock.WALL_POST_OVERRIDE) || a(var2, i));
/*     */   }
/*     */ 
/*     */   
/*     */   private IBlockData a(IBlockData var0, boolean var1, boolean var2, boolean var3, boolean var4, VoxelShape var5) {
/* 259 */     return var0
/* 260 */       .set(c, a(var1, var5, j))
/* 261 */       .set(b, a(var2, var5, p))
/* 262 */       .set(d, a(var3, var5, k))
/* 263 */       .set(e, a(var4, var5, o));
/*     */   }
/*     */   
/*     */   private BlockPropertyWallHeight a(boolean var0, VoxelShape var1, VoxelShape var2) {
/* 267 */     if (var0) {
/* 268 */       if (a(var1, var2)) {
/* 269 */         return BlockPropertyWallHeight.TALL;
/*     */       }
/* 271 */       return BlockPropertyWallHeight.LOW;
/*     */     } 
/*     */     
/* 274 */     return BlockPropertyWallHeight.NONE;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Fluid d(IBlockData var0) {
/* 280 */     if (((Boolean)var0.get(f)).booleanValue()) {
/* 281 */       return FluidTypes.WATER.a(false);
/*     */     }
/* 283 */     return super.d(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(IBlockData var0, IBlockAccess var1, BlockPosition var2) {
/* 288 */     return !((Boolean)var0.get(f)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 293 */     var0.a((IBlockState<?>[])new IBlockState[] { UP, c, b, e, d, f });
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData var0, EnumBlockRotation var1) {
/* 298 */     switch (null.a[var1.ordinal()]) {
/*     */       case 1:
/* 300 */         return var0.set(c, var0.get(d)).set(b, var0.get(e)).set(d, var0.get(c)).set(e, var0.get(b));
/*     */       case 2:
/* 302 */         return var0.set(c, var0.get(b)).set(b, var0.get(d)).set(d, var0.get(e)).set(e, var0.get(c));
/*     */       case 3:
/* 304 */         return var0.set(c, var0.get(e)).set(b, var0.get(c)).set(d, var0.get(b)).set(e, var0.get(d));
/*     */     } 
/* 306 */     return var0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData var0, EnumBlockMirror var1) {
/* 312 */     switch (null.b[var1.ordinal()]) {
/*     */       case 1:
/* 314 */         return var0.set(c, var0.get(d)).set(d, var0.get(c));
/*     */       case 2:
/* 316 */         return var0.set(b, var0.get(e)).set(e, var0.get(b));
/*     */     } 
/*     */ 
/*     */     
/* 320 */     return super.a(var0, var1);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockCobbleWall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */