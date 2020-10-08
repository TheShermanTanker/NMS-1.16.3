/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockMinecartTrack
/*     */   extends BlockMinecartTrackAbstract
/*     */ {
/*  13 */   public static final BlockStateEnum<BlockPropertyTrackPosition> SHAPE = BlockProperties.ac;
/*     */   
/*     */   protected BlockMinecartTrack(BlockBase.Info var0) {
/*  16 */     super(false, var0);
/*  17 */     j(((IBlockData)this.blockStateList.getBlockData()).set(SHAPE, BlockPropertyTrackPosition.NORTH_SOUTH));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(IBlockData var0, World var1, BlockPosition var2, Block var3) {
/*  22 */     if (var3.getBlockData().isPowerSource() && (
/*  23 */       new MinecartTrackLogic(var1, var2, var0)).b() == 3) {
/*  24 */       a(var1, var2, var0, false);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState<BlockPropertyTrackPosition> d() {
/*  31 */     return SHAPE;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData var0, EnumBlockRotation var1) {
/*  36 */     switch (null.b[var1.ordinal()]) {
/*     */       case 1:
/*  38 */         switch (null.a[((BlockPropertyTrackPosition)var0.get(SHAPE)).ordinal()]) {
/*     */           case 1:
/*  40 */             return var0.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_WEST);
/*     */           case 2:
/*  42 */             return var0.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_EAST);
/*     */           case 3:
/*  44 */             return var0.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_SOUTH);
/*     */           case 4:
/*  46 */             return var0.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_NORTH);
/*     */           case 5:
/*  48 */             return var0.set(SHAPE, BlockPropertyTrackPosition.NORTH_WEST);
/*     */           case 6:
/*  50 */             return var0.set(SHAPE, BlockPropertyTrackPosition.NORTH_EAST);
/*     */           case 7:
/*  52 */             return var0.set(SHAPE, BlockPropertyTrackPosition.SOUTH_EAST);
/*     */           case 8:
/*  54 */             return var0.set(SHAPE, BlockPropertyTrackPosition.SOUTH_WEST);
/*     */         } 
/*     */       case 2:
/*  57 */         switch (null.a[((BlockPropertyTrackPosition)var0.get(SHAPE)).ordinal()]) {
/*     */           case 9:
/*  59 */             return var0.set(SHAPE, BlockPropertyTrackPosition.EAST_WEST);
/*     */           case 10:
/*  61 */             return var0.set(SHAPE, BlockPropertyTrackPosition.NORTH_SOUTH);
/*     */           case 1:
/*  63 */             return var0.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_NORTH);
/*     */           case 2:
/*  65 */             return var0.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_SOUTH);
/*     */           case 3:
/*  67 */             return var0.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_WEST);
/*     */           case 4:
/*  69 */             return var0.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_EAST);
/*     */           case 5:
/*  71 */             return var0.set(SHAPE, BlockPropertyTrackPosition.NORTH_EAST);
/*     */           case 6:
/*  73 */             return var0.set(SHAPE, BlockPropertyTrackPosition.SOUTH_EAST);
/*     */           case 7:
/*  75 */             return var0.set(SHAPE, BlockPropertyTrackPosition.SOUTH_WEST);
/*     */           case 8:
/*  77 */             return var0.set(SHAPE, BlockPropertyTrackPosition.NORTH_WEST);
/*     */         } 
/*     */       case 3:
/*  80 */         switch (null.a[((BlockPropertyTrackPosition)var0.get(SHAPE)).ordinal()]) {
/*     */           case 9:
/*  82 */             return var0.set(SHAPE, BlockPropertyTrackPosition.EAST_WEST);
/*     */           case 10:
/*  84 */             return var0.set(SHAPE, BlockPropertyTrackPosition.NORTH_SOUTH);
/*     */           case 1:
/*  86 */             return var0.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_SOUTH);
/*     */           case 2:
/*  88 */             return var0.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_NORTH);
/*     */           case 3:
/*  90 */             return var0.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_EAST);
/*     */           case 4:
/*  92 */             return var0.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_WEST);
/*     */           case 5:
/*  94 */             return var0.set(SHAPE, BlockPropertyTrackPosition.SOUTH_WEST);
/*     */           case 6:
/*  96 */             return var0.set(SHAPE, BlockPropertyTrackPosition.NORTH_WEST);
/*     */           case 7:
/*  98 */             return var0.set(SHAPE, BlockPropertyTrackPosition.NORTH_EAST);
/*     */           case 8:
/* 100 */             return var0.set(SHAPE, BlockPropertyTrackPosition.SOUTH_EAST);
/*     */         }  break;
/*     */     } 
/* 103 */     return var0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData var0, EnumBlockMirror var1) {
/* 109 */     BlockPropertyTrackPosition var2 = (BlockPropertyTrackPosition)var0.get(SHAPE);
/* 110 */     switch (null.c[var1.ordinal()]) {
/*     */       case 1:
/* 112 */         switch (null.a[var2.ordinal()]) {
/*     */           case 3:
/* 114 */             return var0.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_SOUTH);
/*     */           case 4:
/* 116 */             return var0.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_NORTH);
/*     */           case 5:
/* 118 */             return var0.set(SHAPE, BlockPropertyTrackPosition.NORTH_EAST);
/*     */           case 6:
/* 120 */             return var0.set(SHAPE, BlockPropertyTrackPosition.NORTH_WEST);
/*     */           case 7:
/* 122 */             return var0.set(SHAPE, BlockPropertyTrackPosition.SOUTH_WEST);
/*     */           case 8:
/* 124 */             return var0.set(SHAPE, BlockPropertyTrackPosition.SOUTH_EAST);
/*     */         } 
/*     */         
/*     */         break;
/*     */       
/*     */       case 2:
/* 130 */         switch (null.a[var2.ordinal()]) {
/*     */           case 1:
/* 132 */             return var0.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_WEST);
/*     */           case 2:
/* 134 */             return var0.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_EAST);
/*     */           case 5:
/* 136 */             return var0.set(SHAPE, BlockPropertyTrackPosition.SOUTH_WEST);
/*     */           case 6:
/* 138 */             return var0.set(SHAPE, BlockPropertyTrackPosition.SOUTH_EAST);
/*     */           case 7:
/* 140 */             return var0.set(SHAPE, BlockPropertyTrackPosition.NORTH_EAST);
/*     */           case 8:
/* 142 */             return var0.set(SHAPE, BlockPropertyTrackPosition.NORTH_WEST);
/*     */         } 
/*     */ 
/*     */         
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 150 */     return super.a(var0, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 155 */     var0.a((IBlockState<?>[])new IBlockState[] { SHAPE });
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockMinecartTrack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */