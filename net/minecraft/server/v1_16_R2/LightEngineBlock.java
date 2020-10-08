/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.mutable.MutableInt;
/*     */ 
/*     */ public final class LightEngineBlock
/*     */   extends LightEngineLayer<LightEngineStorageBlock.a, LightEngineStorageBlock> {
/*   7 */   private static final EnumDirection[] e = EnumDirection.values();
/*   8 */   private final BlockPosition.MutableBlockPosition f = new BlockPosition.MutableBlockPosition();
/*   9 */   private final MutableInt mutableint = new MutableInt();
/*     */   
/*     */   public LightEngineBlock(ILightAccess ilightaccess) {
/*  12 */     super(ilightaccess, EnumSkyBlock.BLOCK, new LightEngineStorageBlock(ilightaccess));
/*     */   }
/*     */ 
/*     */   
/*     */   private int d(long i) {
/*  17 */     int j = (int)(i >> 38L);
/*  18 */     int k = (int)(i << 52L >> 52L);
/*  19 */     int l = (int)(i << 26L >> 38L);
/*     */     
/*  21 */     IBlockAccess iblockaccess = this.a.c(j >> 4, l >> 4);
/*     */     
/*  23 */     return (iblockaccess != null) ? iblockaccess.g(this.f.d(j, k, l)) : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int b(long i, long j, int k) {
/*  28 */     if (j == Long.MAX_VALUE)
/*  29 */       return 15; 
/*  30 */     if (i == Long.MAX_VALUE)
/*  31 */       return k + 15 - d(j); 
/*  32 */     if (k >= 15) {
/*  33 */       return k;
/*     */     }
/*     */     
/*  36 */     int jx = (int)(j >> 38L);
/*  37 */     int jy = (int)(j << 52L >> 52L);
/*  38 */     int jz = (int)(j << 26L >> 38L);
/*  39 */     int ix = (int)(i >> 38L);
/*  40 */     int iy = (int)(i << 52L >> 52L);
/*  41 */     int iz = (int)(i << 26L >> 38L);
/*  42 */     int l = Integer.signum(jx - ix);
/*  43 */     int i1 = Integer.signum(jy - iy);
/*  44 */     int j1 = Integer.signum(jz - iz);
/*     */     
/*  46 */     EnumDirection enumdirection = EnumDirection.a(l, i1, j1);
/*     */     
/*  48 */     if (enumdirection == null) {
/*  49 */       return 15;
/*     */     }
/*     */     
/*  52 */     IBlockData iblockdata = getBlockOptimized(jx, jy, jz, this.mutableint);
/*  53 */     int blockedLight = this.mutableint.getValue().intValue();
/*  54 */     if (blockedLight >= 15) {
/*  55 */       return 15;
/*     */     }
/*  57 */     IBlockData iblockdata1 = getBlockOptimized(ix, iy, iz);
/*  58 */     VoxelShape voxelshape = a(iblockdata1, i, enumdirection);
/*  59 */     VoxelShape voxelshape1 = a(iblockdata, j, enumdirection.opposite());
/*     */     
/*  61 */     return VoxelShapes.b(voxelshape, voxelshape1) ? 15 : (k + Math.max(1, blockedLight));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(long i, int j, boolean flag) {
/*  70 */     int x = (int)(i >> 38L);
/*  71 */     int y = (int)(i << 52L >> 52L);
/*  72 */     int z = (int)(i << 26L >> 38L);
/*  73 */     long k = SectionPosition.blockPosAsSectionLong(x, y, z);
/*     */     
/*  75 */     EnumDirection[] aenumdirection = e;
/*  76 */     int l = aenumdirection.length;
/*     */     
/*  78 */     for (int i1 = 0; i1 < l; i1++) {
/*  79 */       EnumDirection enumdirection = aenumdirection[i1];
/*  80 */       long j1 = BlockPosition.getAdjacent(x, y, z, enumdirection);
/*  81 */       long k1 = SectionPosition.getAdjacentFromBlockPos(x, y, z, enumdirection);
/*     */       
/*  83 */       if (k == k1 || this.c.g(k1)) {
/*  84 */         b(i, j1, j, flag);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int a(long i, long j, int k) {
/*  92 */     int l = k;
/*     */     
/*  94 */     if (Long.MAX_VALUE != j) {
/*  95 */       int i1 = b(Long.MAX_VALUE, i, 0);
/*     */       
/*  97 */       if (k > i1) {
/*  98 */         l = i1;
/*     */       }
/*     */       
/* 101 */       if (l == 0) {
/* 102 */         return l;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 107 */     int baseX = (int)(i >> 38L);
/* 108 */     int baseY = (int)(i << 52L >> 52L);
/* 109 */     int baseZ = (int)(i << 26L >> 38L);
/* 110 */     long j1 = SectionPosition.blockPosAsSectionLong(baseX, baseY, baseZ);
/* 111 */     NibbleArray nibblearray = this.c.updating.getUpdatingOptimized(j1);
/*     */     
/* 113 */     EnumDirection[] aenumdirection = e;
/* 114 */     int k1 = aenumdirection.length;
/*     */     
/* 116 */     for (int l1 = 0; l1 < k1; l1++) {
/* 117 */       EnumDirection enumdirection = aenumdirection[l1];
/*     */       
/* 119 */       int newX = baseX + enumdirection.getAdjacentX();
/* 120 */       int newY = baseY + enumdirection.getAdjacentY();
/* 121 */       int newZ = baseZ + enumdirection.getAdjacentZ();
/* 122 */       long i2 = BlockPosition.asLong(newX, newY, newZ);
/*     */       
/* 124 */       if (i2 != j) {
/* 125 */         NibbleArray nibblearray1; long j2 = SectionPosition.blockPosAsSectionLong(newX, newY, newZ);
/*     */ 
/*     */ 
/*     */         
/* 129 */         if (j1 == j2) {
/* 130 */           nibblearray1 = nibblearray;
/*     */         } else {
/* 132 */           nibblearray1 = this.c.updating.getUpdatingOptimized(j2);
/*     */         } 
/*     */         
/* 135 */         if (nibblearray1 != null) {
/* 136 */           int k2 = b(i2, i, getNibbleLightInverse(nibblearray1, newX, newY, newZ));
/*     */           
/* 138 */           if (l > k2) {
/* 139 */             l = k2;
/*     */           }
/*     */           
/* 142 */           if (l == 0) {
/* 143 */             return l;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 149 */     return l;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(BlockPosition blockposition, int i) {
/* 154 */     this.c.d();
/* 155 */     a(Long.MAX_VALUE, blockposition.asLong(), 15 - i, true);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LightEngineBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */