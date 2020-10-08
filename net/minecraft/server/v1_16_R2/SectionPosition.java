/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Spliterators;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.stream.Stream;
/*     */ import java.util.stream.StreamSupport;
/*     */ 
/*     */ public class SectionPosition
/*     */   extends BaseBlockPosition {
/*     */   private SectionPosition(int i, int j, int k) {
/*  11 */     super(i, j, k);
/*     */   }
/*     */   
/*     */   public static SectionPosition a(int i, int j, int k) {
/*  15 */     return new SectionPosition(i, j, k);
/*     */   }
/*     */   
/*     */   public static SectionPosition a(BlockPosition blockposition) {
/*  19 */     return new SectionPosition(blockposition.getX() >> 4, blockposition.getY() >> 4, blockposition.getZ() >> 4);
/*     */   }
/*     */   
/*     */   public static SectionPosition a(ChunkCoordIntPair chunkcoordintpair, int i) {
/*  23 */     return new SectionPosition(chunkcoordintpair.x, i, chunkcoordintpair.z);
/*     */   }
/*     */   
/*     */   public static SectionPosition a(Entity entity) {
/*  27 */     return new SectionPosition(a(MathHelper.floor(entity.locX())), a(MathHelper.floor(entity.locY())), a(MathHelper.floor(entity.locZ())));
/*     */   }
/*     */   
/*     */   public static SectionPosition a(long i) {
/*  31 */     return new SectionPosition((int)(i >> 42L), (int)(i << 44L >> 44L), (int)(i << 22L >> 42L));
/*     */   }
/*     */   
/*     */   public static long a(long i, EnumDirection enumdirection) {
/*  35 */     return a(i, enumdirection.getAdjacentX(), enumdirection.getAdjacentY(), enumdirection.getAdjacentZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public static long getAdjacentFromBlockPos(int x, int y, int z, EnumDirection enumdirection) {
/*  40 */     return (((x >> 4) + enumdirection.getAdjacentX()) & 0x3FFFFFL) << 42L | ((y >> 4) + enumdirection.getAdjacentY()) & 0xFFFFFL | (((z >> 4) + enumdirection.getAdjacentZ()) & 0x3FFFFFL) << 20L;
/*     */   }
/*     */   public static long getAdjacentFromSectionPos(int x, int y, int z, EnumDirection enumdirection) {
/*  43 */     return ((x + enumdirection.getAdjacentX()) & 0x3FFFFFL) << 42L | (y + enumdirection.getAdjacentY()) & 0xFFFFFL | ((z + enumdirection.getAdjacentZ()) & 0x3FFFFFL) << 20L;
/*     */   }
/*     */   
/*     */   public static long a(long i, int j, int k, int l) {
/*  47 */     return (((int)(i >> 42L) + j) & 0x3FFFFFL) << 42L | ((int)(i << 44L >> 44L) + k) & 0xFFFFFL | (((int)(i << 22L >> 42L) + l) & 0x3FFFFFL) << 20L;
/*     */   }
/*     */   
/*     */   public static int a(int i) {
/*  51 */     return i >> 4;
/*     */   }
/*     */   
/*     */   public static int b(int i) {
/*  55 */     return i & 0xF;
/*     */   }
/*     */   
/*     */   public static short b(BlockPosition blockposition) {
/*  59 */     return (short)((blockposition.getX() & 0xF) << 8 | (blockposition.getZ() & 0xF) << 4 | blockposition.getY() & 0xF);
/*     */   }
/*     */   
/*     */   public static int a(short short0) {
/*  63 */     return short0 >>> 8 & 0xF;
/*     */   }
/*     */   
/*     */   public static int b(short short0) {
/*  67 */     return short0 >>> 0 & 0xF;
/*     */   }
/*     */   
/*     */   public static int c(short short0) {
/*  71 */     return short0 >>> 4 & 0xF;
/*     */   }
/*     */   
/*     */   public int d(short short0) {
/*  75 */     return d() + a(short0);
/*     */   }
/*     */   
/*     */   public int e(short short0) {
/*  79 */     return e() + b(short0);
/*     */   }
/*     */   
/*     */   public int f(short short0) {
/*  83 */     return f() + c(short0);
/*     */   }
/*     */   
/*     */   public BlockPosition g(short short0) {
/*  87 */     return new BlockPosition(d(short0), e(short0), f(short0));
/*     */   }
/*     */   
/*     */   public static int c(int i) {
/*  91 */     return i << 4;
/*     */   }
/*     */   
/*     */   public static int b(long i) {
/*  95 */     return (int)(i << 0L >> 42L);
/*     */   }
/*     */   
/*     */   public static int c(long i) {
/*  99 */     return (int)(i << 44L >> 44L);
/*     */   }
/*     */   
/*     */   public static int d(long i) {
/* 103 */     return (int)(i << 22L >> 42L);
/*     */   }
/*     */   
/*     */   public int a() {
/* 107 */     return getX();
/*     */   }
/*     */   
/*     */   public int b() {
/* 111 */     return getY();
/*     */   }
/*     */   
/*     */   public int c() {
/* 115 */     return getZ();
/*     */   }
/*     */   
/*     */   public final int d() {
/* 119 */     return getX() << 4;
/*     */   }
/*     */   
/*     */   public final int e() {
/* 123 */     return getY() << 4;
/*     */   }
/*     */   
/*     */   public final int f() {
/* 127 */     return getZ() << 4;
/*     */   }
/*     */   
/*     */   public int g() {
/* 131 */     return (a() << 4) + 15;
/*     */   }
/*     */   
/*     */   public int h() {
/* 135 */     return (b() << 4) + 15;
/*     */   }
/*     */   
/*     */   public int i() {
/* 139 */     return (c() << 4) + 15;
/*     */   }
/*     */   public static long blockToSection(long i) {
/* 142 */     return e(i);
/*     */   }
/*     */   public static long e(long i) {
/* 145 */     return ((int)(i >> 42L) & 0x3FFFFFL) << 42L | (int)(i << 52L >> 56L) & 0xFFFFFL | ((int)(i << 26L >> 42L) & 0x3FFFFFL) << 20L;
/*     */   }
/*     */   
/*     */   public static long f(long i) {
/* 149 */     return i & 0xFFFFFFFFFFF00000L;
/*     */   }
/*     */   
/*     */   public BlockPosition p() {
/* 153 */     return new BlockPosition(c(a()), c(b()), c(c()));
/*     */   }
/*     */   
/*     */   public BlockPosition q() {
/* 157 */     boolean flag = true;
/*     */     
/* 159 */     return p().b(8, 8, 8);
/*     */   }
/*     */   
/*     */   public ChunkCoordIntPair r() {
/* 163 */     return new ChunkCoordIntPair(a(), c());
/*     */   }
/*     */ 
/*     */   
/*     */   public static long blockPosAsSectionLong(int i, int j, int k) {
/* 168 */     return ((i >> 4) & 0x3FFFFFL) << 42L | (j >> 4) & 0xFFFFFL | ((k >> 4) & 0x3FFFFFL) << 20L;
/*     */   }
/*     */   public static long asLong(int i, int j, int k) {
/* 171 */     return b(i, j, k);
/*     */   } public static long b(int i, int j, int k) {
/* 173 */     return (i & 0x3FFFFFL) << 42L | j & 0xFFFFFL | (k & 0x3FFFFFL) << 20L;
/*     */   }
/*     */   
/*     */   public long s() {
/* 177 */     return (getX() & 0x3FFFFFL) << 42L | getY() & 0xFFFFFL | (getZ() & 0x3FFFFFL) << 20L;
/*     */   }
/*     */   
/*     */   public Stream<BlockPosition> t() {
/* 181 */     return BlockPosition.a(d(), e(), f(), g(), h(), i());
/*     */   }
/*     */   
/*     */   public static Stream<SectionPosition> a(SectionPosition sectionposition, int i) {
/* 185 */     return a(sectionposition.getX() - i, sectionposition.getY() - i, sectionposition.getZ() - i, sectionposition.getX() + i, sectionposition.getY() + i, sectionposition.getZ() + i);
/*     */   }
/*     */   
/*     */   public static Stream<SectionPosition> b(ChunkCoordIntPair chunkcoordintpair, int i) {
/* 189 */     return a(chunkcoordintpair.x - i, 0, chunkcoordintpair.z - i, chunkcoordintpair.x + i, 15, chunkcoordintpair.z + i);
/*     */   }
/*     */   
/*     */   public static Stream<SectionPosition> a(final int i, final int j, final int k, final int l, final int i1, final int j1) {
/* 193 */     return StreamSupport.stream(new Spliterators.AbstractSpliterator<SectionPosition>(((l - i + 1) * (i1 - j + 1) * (j1 - k + 1)), 64) {
/* 194 */           final CursorPosition a = new CursorPosition(i, j, k, l, i1, j1);
/*     */           
/*     */           public boolean tryAdvance(Consumer<? super SectionPosition> consumer) {
/* 197 */             if (this.a.a()) {
/* 198 */               consumer.accept(new SectionPosition(this.a.b(), this.a.c(), this.a.d()));
/* 199 */               return true;
/*     */             } 
/* 201 */             return false;
/*     */           }
/*     */         }false);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\SectionPosition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */