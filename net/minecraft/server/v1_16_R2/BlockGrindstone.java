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
/*     */ 
/*     */ 
/*     */ public class BlockGrindstone
/*     */   extends BlockAttachable
/*     */ {
/*  29 */   public static final VoxelShape a = Block.a(2.0D, 0.0D, 6.0D, 4.0D, 7.0D, 10.0D);
/*  30 */   public static final VoxelShape b = Block.a(12.0D, 0.0D, 6.0D, 14.0D, 7.0D, 10.0D);
/*  31 */   public static final VoxelShape c = Block.a(2.0D, 7.0D, 5.0D, 4.0D, 13.0D, 11.0D);
/*  32 */   public static final VoxelShape d = Block.a(12.0D, 7.0D, 5.0D, 14.0D, 13.0D, 11.0D);
/*  33 */   public static final VoxelShape e = VoxelShapes.a(a, c);
/*  34 */   public static final VoxelShape f = VoxelShapes.a(b, d);
/*  35 */   public static final VoxelShape g = VoxelShapes.a(e, f);
/*  36 */   public static final VoxelShape h = VoxelShapes.a(g, Block.a(4.0D, 4.0D, 2.0D, 12.0D, 16.0D, 14.0D));
/*     */   
/*  38 */   public static final VoxelShape i = Block.a(6.0D, 0.0D, 2.0D, 10.0D, 7.0D, 4.0D);
/*  39 */   public static final VoxelShape j = Block.a(6.0D, 0.0D, 12.0D, 10.0D, 7.0D, 14.0D);
/*  40 */   public static final VoxelShape k = Block.a(5.0D, 7.0D, 2.0D, 11.0D, 13.0D, 4.0D);
/*  41 */   public static final VoxelShape o = Block.a(5.0D, 7.0D, 12.0D, 11.0D, 13.0D, 14.0D);
/*  42 */   public static final VoxelShape p = VoxelShapes.a(i, k);
/*  43 */   public static final VoxelShape q = VoxelShapes.a(j, o);
/*  44 */   public static final VoxelShape r = VoxelShapes.a(p, q);
/*  45 */   public static final VoxelShape s = VoxelShapes.a(r, Block.a(2.0D, 4.0D, 4.0D, 14.0D, 16.0D, 12.0D));
/*     */   
/*  47 */   public static final VoxelShape t = Block.a(2.0D, 6.0D, 0.0D, 4.0D, 10.0D, 7.0D);
/*  48 */   public static final VoxelShape v = Block.a(12.0D, 6.0D, 0.0D, 14.0D, 10.0D, 7.0D);
/*  49 */   public static final VoxelShape w = Block.a(2.0D, 5.0D, 7.0D, 4.0D, 11.0D, 13.0D);
/*  50 */   public static final VoxelShape x = Block.a(12.0D, 5.0D, 7.0D, 14.0D, 11.0D, 13.0D);
/*  51 */   public static final VoxelShape y = VoxelShapes.a(t, w);
/*  52 */   public static final VoxelShape z = VoxelShapes.a(v, x);
/*  53 */   public static final VoxelShape A = VoxelShapes.a(y, z);
/*  54 */   public static final VoxelShape B = VoxelShapes.a(A, Block.a(4.0D, 2.0D, 4.0D, 12.0D, 14.0D, 16.0D));
/*     */   
/*  56 */   public static final VoxelShape C = Block.a(2.0D, 6.0D, 7.0D, 4.0D, 10.0D, 16.0D);
/*  57 */   public static final VoxelShape D = Block.a(12.0D, 6.0D, 7.0D, 14.0D, 10.0D, 16.0D);
/*  58 */   public static final VoxelShape E = Block.a(2.0D, 5.0D, 3.0D, 4.0D, 11.0D, 9.0D);
/*  59 */   public static final VoxelShape F = Block.a(12.0D, 5.0D, 3.0D, 14.0D, 11.0D, 9.0D);
/*  60 */   public static final VoxelShape G = VoxelShapes.a(C, E);
/*  61 */   public static final VoxelShape H = VoxelShapes.a(D, F);
/*  62 */   public static final VoxelShape I = VoxelShapes.a(G, H);
/*  63 */   public static final VoxelShape J = VoxelShapes.a(I, Block.a(4.0D, 2.0D, 0.0D, 12.0D, 14.0D, 12.0D));
/*     */   
/*  65 */   public static final VoxelShape K = Block.a(7.0D, 6.0D, 2.0D, 16.0D, 10.0D, 4.0D);
/*  66 */   public static final VoxelShape L = Block.a(7.0D, 6.0D, 12.0D, 16.0D, 10.0D, 14.0D);
/*  67 */   public static final VoxelShape M = Block.a(3.0D, 5.0D, 2.0D, 9.0D, 11.0D, 4.0D);
/*  68 */   public static final VoxelShape N = Block.a(3.0D, 5.0D, 12.0D, 9.0D, 11.0D, 14.0D);
/*  69 */   public static final VoxelShape O = VoxelShapes.a(K, M);
/*  70 */   public static final VoxelShape P = VoxelShapes.a(L, N);
/*  71 */   public static final VoxelShape Q = VoxelShapes.a(O, P);
/*  72 */   public static final VoxelShape R = VoxelShapes.a(Q, Block.a(0.0D, 2.0D, 4.0D, 12.0D, 14.0D, 12.0D));
/*     */   
/*  74 */   public static final VoxelShape S = Block.a(0.0D, 6.0D, 2.0D, 9.0D, 10.0D, 4.0D);
/*  75 */   public static final VoxelShape T = Block.a(0.0D, 6.0D, 12.0D, 9.0D, 10.0D, 14.0D);
/*  76 */   public static final VoxelShape U = Block.a(7.0D, 5.0D, 2.0D, 13.0D, 11.0D, 4.0D);
/*  77 */   public static final VoxelShape V = Block.a(7.0D, 5.0D, 12.0D, 13.0D, 11.0D, 14.0D);
/*  78 */   public static final VoxelShape W = VoxelShapes.a(S, U);
/*  79 */   public static final VoxelShape X = VoxelShapes.a(T, V);
/*  80 */   public static final VoxelShape Y = VoxelShapes.a(W, X);
/*  81 */   public static final VoxelShape Z = VoxelShapes.a(Y, Block.a(4.0D, 2.0D, 4.0D, 16.0D, 14.0D, 12.0D));
/*     */   
/*  83 */   public static final VoxelShape aa = Block.a(2.0D, 9.0D, 6.0D, 4.0D, 16.0D, 10.0D);
/*  84 */   public static final VoxelShape ab = Block.a(12.0D, 9.0D, 6.0D, 14.0D, 16.0D, 10.0D);
/*  85 */   public static final VoxelShape ac = Block.a(2.0D, 3.0D, 5.0D, 4.0D, 9.0D, 11.0D);
/*  86 */   public static final VoxelShape ad = Block.a(12.0D, 3.0D, 5.0D, 14.0D, 9.0D, 11.0D);
/*  87 */   public static final VoxelShape ae = VoxelShapes.a(aa, ac);
/*  88 */   public static final VoxelShape af = VoxelShapes.a(ab, ad);
/*  89 */   public static final VoxelShape ag = VoxelShapes.a(ae, af);
/*  90 */   public static final VoxelShape ah = VoxelShapes.a(ag, Block.a(4.0D, 0.0D, 2.0D, 12.0D, 12.0D, 14.0D));
/*     */   
/*  92 */   public static final VoxelShape ai = Block.a(6.0D, 9.0D, 2.0D, 10.0D, 16.0D, 4.0D);
/*  93 */   public static final VoxelShape aj = Block.a(6.0D, 9.0D, 12.0D, 10.0D, 16.0D, 14.0D);
/*  94 */   public static final VoxelShape ak = Block.a(5.0D, 3.0D, 2.0D, 11.0D, 9.0D, 4.0D);
/*  95 */   public static final VoxelShape al = Block.a(5.0D, 3.0D, 12.0D, 11.0D, 9.0D, 14.0D);
/*  96 */   public static final VoxelShape am = VoxelShapes.a(ai, ak);
/*  97 */   public static final VoxelShape an = VoxelShapes.a(aj, al);
/*  98 */   public static final VoxelShape ao = VoxelShapes.a(am, an);
/*  99 */   public static final VoxelShape ap = VoxelShapes.a(ao, Block.a(2.0D, 0.0D, 4.0D, 14.0D, 12.0D, 12.0D));
/*     */   
/* 101 */   private static final IChatBaseComponent aD = new ChatMessage("container.grindstone_title");
/*     */   
/*     */   protected BlockGrindstone(BlockBase.Info var0) {
/* 104 */     super(var0);
/* 105 */     j(((IBlockData)this.blockStateList.getBlockData()).set(FACING, EnumDirection.NORTH).set(FACE, BlockPropertyAttachPosition.WALL));
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumRenderType b(IBlockData var0) {
/* 110 */     return EnumRenderType.MODEL;
/*     */   }
/*     */   
/*     */   private VoxelShape l(IBlockData var0) {
/* 114 */     EnumDirection var1 = (EnumDirection)var0.get(FACING);
/* 115 */     switch (null.a[((BlockPropertyAttachPosition)var0.get(FACE)).ordinal()]) {
/*     */       case 1:
/* 117 */         if (var1 == EnumDirection.NORTH || var1 == EnumDirection.SOUTH) {
/* 118 */           return h;
/*     */         }
/* 120 */         return s;
/*     */ 
/*     */       
/*     */       case 2:
/* 124 */         if (var1 == EnumDirection.NORTH)
/* 125 */           return J; 
/* 126 */         if (var1 == EnumDirection.SOUTH)
/* 127 */           return B; 
/* 128 */         if (var1 == EnumDirection.EAST) {
/* 129 */           return Z;
/*     */         }
/* 131 */         return R;
/*     */ 
/*     */       
/*     */       case 3:
/* 135 */         if (var1 == EnumDirection.NORTH || var1 == EnumDirection.SOUTH) {
/* 136 */           return ah;
/*     */         }
/* 138 */         return ap;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 143 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape c(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 148 */     return l(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 153 */     return l(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(IBlockData var0, IWorldReader var1, BlockPosition var2) {
/* 158 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData var0, World var1, BlockPosition var2, EntityHuman var3, EnumHand var4, MovingObjectPositionBlock var5) {
/* 163 */     if (var1.isClientSide) {
/* 164 */       return EnumInteractionResult.SUCCESS;
/*     */     }
/*     */     
/* 167 */     var3.openContainer(var0.b(var1, var2));
/* 168 */     var3.a(StatisticList.INTERACT_WITH_GRINDSTONE);
/* 169 */     return EnumInteractionResult.CONSUME;
/*     */   }
/*     */ 
/*     */   
/*     */   public ITileInventory getInventory(IBlockData var0, World var1, BlockPosition var2) {
/* 174 */     return new TileInventory((var2, var3, var4) -> new ContainerGrindstone(var2, var3, ContainerAccess.at(var0, var1)), aD);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData var0, EnumBlockRotation var1) {
/* 179 */     return var0.set(FACING, var1.a((EnumDirection)var0.get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData var0, EnumBlockMirror var1) {
/* 184 */     return var0.a(var1.a((EnumDirection)var0.get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 189 */     var0.a((IBlockState<?>[])new IBlockState[] { FACING, FACE });
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData var0, IBlockAccess var1, BlockPosition var2, PathMode var3) {
/* 194 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockGrindstone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */