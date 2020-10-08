/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.mutable.MutableInt;
/*     */ 
/*     */ public final class LightEngineSky
/*     */   extends LightEngineLayer<LightEngineStorageSky.a, LightEngineStorageSky> {
/*   7 */   private static final EnumDirection[] e = EnumDirection.values();
/*   8 */   private static final EnumDirection[] f = new EnumDirection[] { EnumDirection.NORTH, EnumDirection.SOUTH, EnumDirection.WEST, EnumDirection.EAST };
/*   9 */   private final MutableInt mutableint = new MutableInt();
/*     */   
/*     */   public LightEngineSky(ILightAccess ilightaccess) {
/*  12 */     super(ilightaccess, EnumSkyBlock.SKY, new LightEngineStorageSky(ilightaccess));
/*     */   }
/*     */   
/*     */   protected int b(long i, long j, int k) {
/*     */     EnumDirection enumdirection;
/*  17 */     if (j == Long.MAX_VALUE) {
/*  18 */       return 15;
/*     */     }
/*  20 */     if (i == Long.MAX_VALUE) {
/*  21 */       if (!this.c.m(j)) {
/*  22 */         return 15;
/*     */       }
/*     */       
/*  25 */       k = 0;
/*     */     } 
/*     */     
/*  28 */     if (k >= 15) {
/*  29 */       return k;
/*     */     }
/*     */ 
/*     */     
/*  33 */     int jx = (int)(j >> 38L);
/*  34 */     int jy = (int)(j << 52L >> 52L);
/*  35 */     int jz = (int)(j << 26L >> 38L);
/*  36 */     IBlockData iblockdata = getBlockOptimized(jx, jy, jz, this.mutableint);
/*  37 */     int blockedLight = this.mutableint.getValue().intValue();
/*  38 */     if (blockedLight >= 15)
/*     */     {
/*  40 */       return 15;
/*     */     }
/*     */     
/*  43 */     int ix = (int)(i >> 38L);
/*  44 */     int iy = (int)(i << 52L >> 52L);
/*  45 */     int iz = (int)(i << 26L >> 38L);
/*  46 */     boolean flag = (ix == jx && iz == jz);
/*  47 */     int j2 = Integer.signum(jx - ix);
/*  48 */     int k2 = Integer.signum(jy - iy);
/*  49 */     int l2 = Integer.signum(jz - iz);
/*     */ 
/*     */ 
/*     */     
/*  53 */     if (i == Long.MAX_VALUE) {
/*  54 */       enumdirection = EnumDirection.DOWN;
/*     */     } else {
/*  56 */       enumdirection = EnumDirection.a(j2, k2, l2);
/*     */     } 
/*     */     
/*  59 */     IBlockData iblockdata1 = (i == Long.MAX_VALUE) ? Blocks.AIR.getBlockData() : getBlockOptimized(ix, iy, iz);
/*     */ 
/*     */     
/*  62 */     if (enumdirection != null) {
/*  63 */       VoxelShape voxelshape = a(iblockdata1, i, enumdirection);
/*  64 */       VoxelShape voxelshape1 = a(iblockdata, j, enumdirection.opposite());
/*     */       
/*  66 */       if (VoxelShapes.b(voxelshape, voxelshape1)) {
/*  67 */         return 15;
/*     */       }
/*     */     } else {
/*  70 */       VoxelShape voxelshape = a(iblockdata1, i, EnumDirection.DOWN);
/*  71 */       if (VoxelShapes.b(voxelshape, VoxelShapes.a())) {
/*  72 */         return 15;
/*     */       }
/*     */       
/*  75 */       int i3 = flag ? -1 : 0;
/*  76 */       EnumDirection enumdirection1 = EnumDirection.a(j2, i3, l2);
/*     */       
/*  78 */       if (enumdirection1 == null) {
/*  79 */         return 15;
/*     */       }
/*     */       
/*  82 */       VoxelShape voxelshape2 = a(iblockdata, j, enumdirection1.opposite());
/*     */       
/*  84 */       if (VoxelShapes.b(VoxelShapes.a(), voxelshape2)) {
/*  85 */         return 15;
/*     */       }
/*     */     } 
/*     */     
/*  89 */     boolean flag1 = (i == Long.MAX_VALUE || (flag && iy > jy));
/*     */     
/*  91 */     return (flag1 && k == 0 && blockedLight == 0) ? 0 : (k + Math.max(1, blockedLight));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(long i, int j, boolean flag) {
/* 100 */     int k1, baseX = (int)(i >> 38L);
/* 101 */     int baseY = (int)(i << 52L >> 52L);
/* 102 */     int baseZ = (int)(i << 26L >> 38L);
/* 103 */     long k = SectionPosition.blockPosAsSectionLong(baseX, baseY, baseZ);
/* 104 */     int i1 = baseY & 0xF;
/* 105 */     int j1 = baseY >> 4;
/*     */ 
/*     */ 
/*     */     
/* 109 */     if (i1 != 0) {
/* 110 */       k1 = 0;
/*     */     } else {
/*     */       int l1;
/*     */       
/* 114 */       for (l1 = 0; !this.c.g(SectionPosition.a(k, 0, -l1 - 1, 0)) && this.c.a(j1 - l1 - 1); l1++);
/*     */ 
/*     */ 
/*     */       
/* 118 */       k1 = l1;
/*     */     } 
/*     */     
/* 121 */     int newBaseY = baseY + -1 - k1 * 16;
/* 122 */     long i2 = BlockPosition.asLong(baseX, newBaseY, baseZ);
/* 123 */     long j2 = SectionPosition.blockPosAsSectionLong(baseX, newBaseY, baseZ);
/*     */     
/* 125 */     if (k == j2 || this.c.g(j2)) {
/* 126 */       b(i, i2, j, flag);
/*     */     }
/*     */     
/* 129 */     long k2 = BlockPosition.asLong(baseX, baseY + 1, baseZ);
/* 130 */     long l2 = SectionPosition.blockPosAsSectionLong(baseX, baseY + 1, baseZ);
/*     */     
/* 132 */     if (k == l2 || this.c.g(l2)) {
/* 133 */       b(i, k2, j, flag);
/*     */     }
/*     */     
/* 136 */     EnumDirection[] aenumdirection = f;
/* 137 */     int i3 = aenumdirection.length;
/* 138 */     int j3 = 0;
/*     */     
/* 140 */     while (j3 < i3) {
/* 141 */       EnumDirection enumdirection = aenumdirection[j3];
/* 142 */       int k3 = 0;
/*     */       
/*     */       while (true) {
/* 145 */         long l3 = BlockPosition.asLong(baseX + enumdirection.getAdjacentX(), baseY - k3, baseZ + enumdirection.getAdjacentZ());
/* 146 */         long i4 = SectionPosition.blockPosAsSectionLong(baseX + enumdirection.getAdjacentX(), baseY - k3, baseZ + enumdirection.getAdjacentZ());
/*     */         
/* 148 */         if (k == i4) {
/* 149 */           b(i, l3, j, flag); break;
/*     */         } 
/* 151 */         if (this.c.g(i4)) {
/* 152 */           b(i, l3, j, flag);
/*     */         }
/*     */         
/* 155 */         k3++;
/* 156 */         if (k3 <= k1 * 16) {
/*     */           continue;
/*     */         }
/*     */         break;
/*     */       } 
/* 161 */       j3++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int a(long i, long j, int k) {
/* 170 */     int l = k;
/*     */     
/* 172 */     if (Long.MAX_VALUE != j) {
/* 173 */       int i1 = b(Long.MAX_VALUE, i, 0);
/*     */       
/* 175 */       if (k > i1) {
/* 176 */         l = i1;
/*     */       }
/*     */       
/* 179 */       if (l == 0) {
/* 180 */         return l;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 185 */     int baseX = (int)(i >> 38L);
/* 186 */     int baseY = (int)(i << 52L >> 52L);
/* 187 */     int baseZ = (int)(i << 26L >> 38L);
/* 188 */     long j1 = SectionPosition.blockPosAsSectionLong(baseX, baseY, baseZ);
/* 189 */     NibbleArray nibblearray = this.c.updating.getUpdatingOptimized(j1);
/*     */     
/* 191 */     EnumDirection[] aenumdirection = e;
/* 192 */     int k1 = aenumdirection.length;
/*     */     
/* 194 */     for (int l1 = 0; l1 < k1; l1++) {
/* 195 */       NibbleArray nibblearray1; EnumDirection enumdirection = aenumdirection[l1];
/*     */       
/* 197 */       int newX = baseX + enumdirection.getAdjacentX();
/* 198 */       int newY = baseY + enumdirection.getAdjacentY();
/* 199 */       int newZ = baseZ + enumdirection.getAdjacentZ();
/* 200 */       long i2 = BlockPosition.asLong(newX, newY, newZ);
/* 201 */       long j2 = SectionPosition.blockPosAsSectionLong(newX, newY, newZ);
/*     */ 
/*     */ 
/*     */       
/* 205 */       if (j1 == j2) {
/* 206 */         nibblearray1 = nibblearray;
/*     */       } else {
/* 208 */         nibblearray1 = this.c.updating.getUpdatingOptimized(j2);
/*     */       } 
/*     */       
/* 211 */       if (nibblearray1 != null) {
/* 212 */         if (i2 != j) {
/* 213 */           int k2 = b(i2, i, getNibbleLightInverse(nibblearray1, newX, newY, newZ));
/*     */           
/* 215 */           if (l > k2) {
/* 216 */             l = k2;
/*     */           }
/*     */           
/* 219 */           if (l == 0) {
/* 220 */             return l;
/*     */           }
/*     */         } 
/* 223 */       } else if (enumdirection != EnumDirection.DOWN) {
/* 224 */         for (i2 = BlockPosition.f(i2); !this.c.g(j2) && !this.c.n(j2); i2 = BlockPosition.a(i2, 0, 16, 0)) {
/* 225 */           j2 = SectionPosition.a(j2, EnumDirection.UP);
/*     */         }
/*     */         
/* 228 */         NibbleArray nibblearray2 = this.c.updating.getUpdatingOptimized(j2);
/*     */         
/* 230 */         if (i2 != j) {
/*     */           int l2;
/*     */           
/* 233 */           if (nibblearray2 != null) {
/* 234 */             l2 = b(i2, i, a(nibblearray2, i2));
/*     */           } else {
/* 236 */             l2 = this.c.o(j2) ? 0 : 15;
/*     */           } 
/*     */           
/* 239 */           if (l > l2) {
/* 240 */             l = l2;
/*     */           }
/*     */           
/* 243 */           if (l == 0) {
/* 244 */             return l;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 250 */     return l;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void f(long i) {
/* 255 */     this.c.d();
/* 256 */     long j = SectionPosition.e(i);
/*     */     
/* 258 */     if (this.c.g(j)) {
/* 259 */       super.f(i);
/*     */     } else {
/* 261 */       for (i = BlockPosition.f(i); !this.c.g(j) && !this.c.n(j); i = BlockPosition.a(i, 0, 16, 0)) {
/* 262 */         j = SectionPosition.a(j, EnumDirection.UP);
/*     */       }
/*     */       
/* 265 */       if (this.c.g(j))
/* 266 */         super.f(i); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LightEngineSky.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */