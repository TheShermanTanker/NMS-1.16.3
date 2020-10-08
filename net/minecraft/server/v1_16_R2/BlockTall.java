/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import it.unimi.dsi.fastutil.objects.Object2IntMap;
/*     */ import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
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
/*     */ public class BlockTall
/*     */   extends Block
/*     */   implements IBlockWaterlogged
/*     */ {
/*  22 */   public static final BlockStateBoolean NORTH = BlockSprawling.a;
/*  23 */   public static final BlockStateBoolean EAST = BlockSprawling.b;
/*  24 */   public static final BlockStateBoolean SOUTH = BlockSprawling.c;
/*  25 */   public static final BlockStateBoolean WEST = BlockSprawling.d; protected static final Map<EnumDirection, BlockStateBoolean> f;
/*  26 */   public static final BlockStateBoolean e = BlockProperties.C; static {
/*  27 */     f = (Map<EnumDirection, BlockStateBoolean>)BlockSprawling.g.entrySet().stream().filter(var0 -> ((EnumDirection)var0.getKey()).n().d()).collect(SystemUtils.a());
/*     */   }
/*     */   protected final VoxelShape[] g;
/*     */   protected final VoxelShape[] h;
/*  31 */   private final Object2IntMap<IBlockData> i = (Object2IntMap<IBlockData>)new Object2IntOpenHashMap();
/*     */   
/*     */   protected BlockTall(float var0, float var1, float var2, float var3, float var4, BlockBase.Info var5) {
/*  34 */     super(var5);
/*     */     
/*  36 */     this.g = a(var0, var1, var4, 0.0F, var4);
/*  37 */     this.h = a(var0, var1, var2, 0.0F, var3);
/*     */     
/*  39 */     for (UnmodifiableIterator<IBlockData> unmodifiableIterator = this.blockStateList.a().iterator(); unmodifiableIterator.hasNext(); ) { IBlockData var7 = unmodifiableIterator.next();
/*  40 */       g(var7); }
/*     */   
/*     */   }
/*     */   
/*     */   protected VoxelShape[] a(float var0, float var1, float var2, float var3, float var4) {
/*  45 */     float var5 = 8.0F - var0;
/*  46 */     float var6 = 8.0F + var0;
/*     */     
/*  48 */     float var7 = 8.0F - var1;
/*  49 */     float var8 = 8.0F + var1;
/*     */     
/*  51 */     VoxelShape var9 = Block.a(var5, 0.0D, var5, var6, var2, var6);
/*  52 */     VoxelShape var10 = Block.a(var7, var3, 0.0D, var8, var4, var8);
/*  53 */     VoxelShape var11 = Block.a(var7, var3, var7, var8, var4, 16.0D);
/*  54 */     VoxelShape var12 = Block.a(0.0D, var3, var7, var8, var4, var8);
/*  55 */     VoxelShape var13 = Block.a(var7, var3, var7, 16.0D, var4, var8);
/*     */     
/*  57 */     VoxelShape var14 = VoxelShapes.a(var10, var13);
/*  58 */     VoxelShape var15 = VoxelShapes.a(var11, var12);
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
/*  76 */     VoxelShape[] var16 = { VoxelShapes.a(), var11, var12, var15, var10, VoxelShapes.a(var11, var10), VoxelShapes.a(var12, var10), VoxelShapes.a(var15, var10), var13, VoxelShapes.a(var11, var13), VoxelShapes.a(var12, var13), VoxelShapes.a(var15, var13), var14, VoxelShapes.a(var11, var14), VoxelShapes.a(var12, var14), VoxelShapes.a(var15, var14) };
/*     */     
/*  78 */     for (int var17 = 0; var17 < 16; var17++) {
/*  79 */       var16[var17] = VoxelShapes.a(var9, var16[var17]);
/*     */     }
/*  81 */     return var16;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(IBlockData var0, IBlockAccess var1, BlockPosition var2) {
/*  86 */     return !((Boolean)var0.get(e)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/*  91 */     return this.h[g(var0)];
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape c(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/*  96 */     return this.g[g(var0)];
/*     */   }
/*     */   
/*     */   private static int a(EnumDirection var0) {
/* 100 */     return 1 << var0.get2DRotationValue();
/*     */   }
/*     */   
/*     */   protected int g(IBlockData var0) {
/* 104 */     return this.i.computeIntIfAbsent(var0, var0 -> {
/*     */           int var1 = 0;
/*     */           if (((Boolean)var0.get(NORTH)).booleanValue()) {
/*     */             var1 |= a(EnumDirection.NORTH);
/*     */           }
/*     */           if (((Boolean)var0.get(EAST)).booleanValue()) {
/*     */             var1 |= a(EnumDirection.EAST);
/*     */           }
/*     */           if (((Boolean)var0.get(SOUTH)).booleanValue()) {
/*     */             var1 |= a(EnumDirection.SOUTH);
/*     */           }
/*     */           if (((Boolean)var0.get(WEST)).booleanValue()) {
/*     */             var1 |= a(EnumDirection.WEST);
/*     */           }
/*     */           return var1;
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public Fluid d(IBlockData var0) {
/* 124 */     if (((Boolean)var0.get(e)).booleanValue()) {
/* 125 */       return FluidTypes.WATER.a(false);
/*     */     }
/* 127 */     return super.d(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData var0, IBlockAccess var1, BlockPosition var2, PathMode var3) {
/* 132 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData var0, EnumBlockRotation var1) {
/* 137 */     switch (null.a[var1.ordinal()]) {
/*     */       case 1:
/* 139 */         return var0.set(NORTH, var0.get(SOUTH)).set(EAST, var0.get(WEST)).set(SOUTH, var0.get(NORTH)).set(WEST, var0.get(EAST));
/*     */       case 2:
/* 141 */         return var0.set(NORTH, var0.get(EAST)).set(EAST, var0.get(SOUTH)).set(SOUTH, var0.get(WEST)).set(WEST, var0.get(NORTH));
/*     */       case 3:
/* 143 */         return var0.set(NORTH, var0.get(WEST)).set(EAST, var0.get(NORTH)).set(SOUTH, var0.get(EAST)).set(WEST, var0.get(SOUTH));
/*     */     } 
/* 145 */     return var0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData var0, EnumBlockMirror var1) {
/* 151 */     switch (null.b[var1.ordinal()]) {
/*     */       case 1:
/* 153 */         return var0.set(NORTH, var0.get(SOUTH)).set(SOUTH, var0.get(NORTH));
/*     */       case 2:
/* 155 */         return var0.set(EAST, var0.get(WEST)).set(WEST, var0.get(EAST));
/*     */     } 
/*     */ 
/*     */     
/* 159 */     return super.a(var0, var1);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockTall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */