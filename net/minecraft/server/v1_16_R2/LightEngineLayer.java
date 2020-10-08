/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.mutable.MutableInt;
/*     */ 
/*     */ public abstract class LightEngineLayer<M extends LightEngineStorageArray<M>, S extends LightEngineStorage<M>>
/*     */   extends LightEngineGraph implements LightEngineLayerEventListener {
/*   9 */   private static final EnumDirection[] e = EnumDirection.values(); protected final ILightAccess a;
/*     */   protected final EnumSkyBlock b;
/*     */   protected final S c;
/*     */   private boolean f;
/*     */   protected final BlockPosition.MutableBlockPosition d;
/*  14 */   protected final BlockPosition.MutableBlockPosition pos = this.d = new BlockPosition.MutableBlockPosition();
/*  15 */   private final long[] g = new long[2];
/*  16 */   private final IChunkAccess[] h = new IChunkAccess[2];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final IBlockData getBlockOptimized(int x, int y, int z, MutableInt mutableint) {
/*  22 */     IChunkAccess iblockaccess = a(x >> 4, z >> 4);
/*     */     
/*  24 */     if (iblockaccess == null) {
/*  25 */       mutableint.setValue(16);
/*  26 */       return Blocks.BEDROCK.getBlockData();
/*     */     } 
/*  28 */     this.pos.setValues(x, y, z);
/*  29 */     IBlockData iblockdata = iblockaccess.getType(x, y, z);
/*  30 */     mutableint.setValue(iblockdata.b(this.a.getWorld(), this.pos));
/*  31 */     return (iblockdata.l() && iblockdata.e()) ? iblockdata : Blocks.AIR.getBlockData();
/*     */   }
/*     */   
/*     */   protected final IBlockData getBlockOptimized(int x, int y, int z) {
/*  35 */     IChunkAccess iblockaccess = a(x >> 4, z >> 4);
/*     */     
/*  37 */     if (iblockaccess == null) {
/*  38 */       return Blocks.BEDROCK.getBlockData();
/*     */     }
/*  40 */     IBlockData iblockdata = iblockaccess.getType(x, y, z);
/*  41 */     return (iblockdata.l() && iblockdata.e()) ? iblockdata : Blocks.AIR.getBlockData();
/*     */   }
/*     */ 
/*     */   
/*     */   public LightEngineLayer(ILightAccess ilightaccess, EnumSkyBlock enumskyblock, S s0) {
/*  46 */     super(16, 256, 8192);
/*  47 */     this.a = ilightaccess;
/*  48 */     this.b = enumskyblock;
/*  49 */     this.c = s0;
/*  50 */     d();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void f(long i) {
/*  55 */     this.c.d();
/*  56 */     if (this.c.g(SectionPosition.e(i))) {
/*  57 */       super.f(i);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   private IChunkAccess a(int i, int j) {
/*  64 */     long k = ChunkCoordIntPair.pair(i, j);
/*     */     
/*  66 */     for (int l = 0; l < 2; l++) {
/*  67 */       if (k == this.g[l]) {
/*  68 */         return this.h[l];
/*     */       }
/*     */     } 
/*     */     
/*  72 */     IChunkAccess iblockaccess = (IChunkAccess)this.a.c(i, j);
/*     */     
/*  74 */     for (int i1 = 1; i1 > 0; i1--) {
/*  75 */       this.g[i1] = this.g[i1 - 1];
/*  76 */       this.h[i1] = this.h[i1 - 1];
/*     */     } 
/*     */     
/*  79 */     this.g[0] = k;
/*  80 */     this.h[0] = iblockaccess;
/*  81 */     return iblockaccess;
/*     */   }
/*     */   
/*     */   private void d() {
/*  85 */     Arrays.fill(this.g, ChunkCoordIntPair.a);
/*  86 */     Arrays.fill((Object[])this.h, (Object)null);
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected VoxelShape a(IBlockData iblockdata, long i, EnumDirection enumdirection) {
/* 124 */     return iblockdata.l() ? iblockdata.a(this.a.getWorld(), this.d.g(i), enumdirection) : VoxelShapes.a();
/*     */   }
/*     */   
/*     */   public static int a(IBlockAccess iblockaccess, IBlockData iblockdata, BlockPosition blockposition, IBlockData iblockdata1, BlockPosition blockposition1, EnumDirection enumdirection, int i) {
/* 128 */     boolean flag = (iblockdata.l() && iblockdata.e());
/* 129 */     boolean flag1 = (iblockdata1.l() && iblockdata1.e());
/*     */     
/* 131 */     if (!flag && !flag1) {
/* 132 */       return i;
/*     */     }
/* 134 */     VoxelShape voxelshape = flag ? iblockdata.c(iblockaccess, blockposition) : VoxelShapes.a();
/* 135 */     VoxelShape voxelshape1 = flag1 ? iblockdata1.c(iblockaccess, blockposition1) : VoxelShapes.a();
/*     */     
/* 137 */     return VoxelShapes.b(voxelshape, voxelshape1, enumdirection) ? 16 : i;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean a(long i) {
/* 143 */     return (i == Long.MAX_VALUE);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int a(long i, long j, int k) {
/* 148 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int c(long i) {
/* 153 */     return (i == Long.MAX_VALUE) ? 0 : (15 - this.c.i(i));
/*     */   }
/*     */   protected int getNibbleLightInverse(NibbleArray nibblearray, int x, int y, int z) {
/* 156 */     return 15 - nibblearray.a(x & 0xF, y & 0xF, z & 0xF);
/*     */   } protected int a(NibbleArray nibblearray, long i) {
/* 158 */     return 15 - nibblearray.a((int)(i >> 38L) & 0xF, (int)(i << 52L >> 52L) & 0xF, (int)(i << 26L >> 38L) & 0xF);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(long i, int j) {
/* 163 */     this.c.b(i, Math.min(15, 15 - j));
/*     */   }
/*     */ 
/*     */   
/*     */   protected int b(long i, long j, int k) {
/* 168 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean a() {
/* 172 */     return (b() || this.c.b() || this.c.a());
/*     */   }
/*     */   
/*     */   public int a(int i, boolean flag, boolean flag1) {
/* 176 */     if (!this.f) {
/* 177 */       if (this.c.b()) {
/* 178 */         i = this.c.b(i);
/* 179 */         if (i == 0) {
/* 180 */           return i;
/*     */         }
/*     */       } 
/*     */       
/* 184 */       this.c.a(this, flag, flag1);
/*     */     } 
/*     */     
/* 187 */     this.f = true;
/* 188 */     if (b()) {
/* 189 */       i = b(i);
/* 190 */       d();
/* 191 */       if (i == 0) {
/* 192 */         return i;
/*     */       }
/*     */     } 
/*     */     
/* 196 */     this.f = false;
/* 197 */     this.c.e();
/* 198 */     return i;
/*     */   }
/*     */   
/*     */   protected void a(long i, @Nullable NibbleArray nibblearray, boolean flag) {
/* 202 */     this.c.a(i, nibblearray, flag);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public NibbleArray a(SectionPosition sectionposition) {
/* 208 */     return this.c.h(sectionposition.s());
/*     */   }
/*     */ 
/*     */   
/*     */   public int b(BlockPosition blockposition) {
/* 213 */     return this.c.d(blockposition.asLong());
/*     */   }
/*     */   
/*     */   public void a(BlockPosition blockposition) {
/* 217 */     long i = blockposition.asLong();
/*     */     
/* 219 */     f(i);
/* 220 */     EnumDirection[] aenumdirection = e;
/* 221 */     int j = aenumdirection.length;
/*     */     
/* 223 */     for (int k = 0; k < j; k++) {
/* 224 */       EnumDirection enumdirection = aenumdirection[k];
/*     */       
/* 226 */       f(BlockPosition.a(i, enumdirection));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(BlockPosition blockposition, int i) {}
/*     */ 
/*     */   
/*     */   public void a(SectionPosition sectionposition, boolean flag) {
/* 235 */     this.c.d(sectionposition.s(), flag);
/*     */   }
/*     */   
/*     */   public void a(ChunkCoordIntPair chunkcoordintpair, boolean flag) {
/* 239 */     long i = SectionPosition.f(SectionPosition.b(chunkcoordintpair.x, 0, chunkcoordintpair.z));
/*     */     
/* 241 */     this.c.b(i, flag);
/*     */   }
/*     */   
/*     */   public void b(ChunkCoordIntPair chunkcoordintpair, boolean flag) {
/* 245 */     long i = SectionPosition.f(SectionPosition.b(chunkcoordintpair.x, 0, chunkcoordintpair.z));
/*     */     
/* 247 */     this.c.c(i, flag);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LightEngineLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */