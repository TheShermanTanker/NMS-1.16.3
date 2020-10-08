/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.function.Predicate;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class ChunkCache
/*     */   implements IBlockAccess, ICollisionAccess {
/*     */   protected final int a;
/*     */   protected final int b;
/*     */   
/*     */   protected final World getWorld() {
/*  13 */     return this.e;
/*     */   } protected final IChunkAccess[][] c; protected boolean d; protected final World e;
/*     */   public ChunkCache(World world, BlockPosition blockposition, BlockPosition blockposition1) {
/*  16 */     this.e = world;
/*  17 */     this.a = blockposition.getX() >> 4;
/*  18 */     this.b = blockposition.getZ() >> 4;
/*  19 */     int i = blockposition1.getX() >> 4;
/*  20 */     int j = blockposition1.getZ() >> 4;
/*     */     
/*  22 */     this.c = new IChunkAccess[i - this.a + 1][j - this.b + 1];
/*  23 */     IChunkProvider ichunkprovider = world.getChunkProvider();
/*     */     
/*  25 */     this.d = true;
/*     */ 
/*     */     
/*     */     int k;
/*     */     
/*  30 */     for (k = this.a; k <= i; k++) {
/*  31 */       for (int l = this.b; l <= j; l++) {
/*  32 */         this.c[k - this.a][l - this.b] = ((WorldServer)world).getChunkProvider().getChunkAtIfLoadedMainThreadNoCache(k, l);
/*     */       }
/*     */     } 
/*     */     
/*  36 */     for (k = blockposition.getX() >> 4; k <= blockposition1.getX() >> 4; k++) {
/*  37 */       for (int l = blockposition.getZ() >> 4; l <= blockposition1.getZ() >> 4; l++) {
/*  38 */         IChunkAccess ichunkaccess = this.c[k - this.a][l - this.b];
/*     */         
/*  40 */         if (ichunkaccess != null && !ichunkaccess.a(blockposition.getY(), blockposition1.getY())) {
/*  41 */           this.d = false;
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private IChunkAccess d(BlockPosition blockposition) {
/*  50 */     return a(blockposition.getX() >> 4, blockposition.getZ() >> 4);
/*     */   }
/*     */   
/*     */   private IChunkAccess a(int i, int j) {
/*  54 */     int k = i - this.a;
/*  55 */     int l = j - this.b;
/*     */     
/*  57 */     if (k >= 0 && k < this.c.length && l >= 0 && l < (this.c[k]).length) {
/*  58 */       IChunkAccess ichunkaccess = this.c[k][l];
/*     */       
/*  60 */       return (ichunkaccess != null) ? ichunkaccess : new ChunkEmpty(this.e, new ChunkCoordIntPair(i, j));
/*     */     } 
/*  62 */     return new ChunkEmpty(this.e, new ChunkCoordIntPair(i, j));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WorldBorder getWorldBorder() {
/*  68 */     return this.e.getWorldBorder();
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockAccess c(int i, int j) {
/*  73 */     return a(i, j);
/*     */   }
/*     */ 
/*     */   
/*     */   private IChunkAccess getChunkIfLoaded(int x, int z) {
/*  78 */     int k = x - this.a;
/*  79 */     int l = z - this.b;
/*     */     
/*  81 */     if (k >= 0 && k < this.c.length && l >= 0 && l < (this.c[k]).length) {
/*  82 */       return this.c[k][l];
/*     */     }
/*  84 */     return null;
/*     */   }
/*     */   
/*     */   public Fluid getFluidIfLoaded(BlockPosition blockposition) {
/*  88 */     IChunkAccess chunk = getChunkIfLoaded(blockposition.getX() >> 4, blockposition.getZ() >> 4);
/*  89 */     return (chunk == null) ? null : chunk.getFluid(blockposition);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getTypeIfLoaded(BlockPosition blockposition) {
/*  94 */     IChunkAccess chunk = getChunkIfLoaded(blockposition.getX() >> 4, blockposition.getZ() >> 4);
/*  95 */     return (chunk == null) ? null : chunk.getType(blockposition);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public TileEntity getTileEntity(BlockPosition blockposition) {
/* 102 */     IChunkAccess ichunkaccess = d(blockposition);
/*     */     
/* 104 */     return ichunkaccess.getTileEntity(blockposition);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getType(BlockPosition blockposition) {
/* 109 */     if (World.isOutsideWorld(blockposition)) {
/* 110 */       return Blocks.AIR.getBlockData();
/*     */     }
/* 112 */     IChunkAccess ichunkaccess = d(blockposition);
/*     */     
/* 114 */     return ichunkaccess.getType(blockposition);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Stream<VoxelShape> c(@Nullable Entity entity, AxisAlignedBB axisalignedbb, Predicate<Entity> predicate) {
/* 120 */     return Stream.empty();
/*     */   }
/*     */ 
/*     */   
/*     */   public Stream<VoxelShape> d(@Nullable Entity entity, AxisAlignedBB axisalignedbb, Predicate<Entity> predicate) {
/* 125 */     return b(entity, axisalignedbb);
/*     */   }
/*     */ 
/*     */   
/*     */   public Fluid getFluid(BlockPosition blockposition) {
/* 130 */     if (World.isOutsideWorld(blockposition)) {
/* 131 */       return FluidTypes.EMPTY.h();
/*     */     }
/* 133 */     IChunkAccess ichunkaccess = d(blockposition);
/*     */     
/* 135 */     return ichunkaccess.getFluid(blockposition);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ChunkCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */