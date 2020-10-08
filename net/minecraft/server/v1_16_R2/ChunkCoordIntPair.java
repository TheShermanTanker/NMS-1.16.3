/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Spliterators;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.stream.Stream;
/*     */ import java.util.stream.StreamSupport;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class ChunkCoordIntPair
/*     */ {
/*  11 */   public static final long a = pair(1875016, 1875016);
/*     */   public final int x;
/*     */   public final int z;
/*     */   public final long longKey;
/*     */   
/*     */   public ChunkCoordIntPair(int i, int j) {
/*  17 */     this.x = i;
/*  18 */     this.z = j;
/*  19 */     this.longKey = pair(this.x, this.z);
/*     */   }
/*     */   
/*     */   public ChunkCoordIntPair(BlockPosition blockposition) {
/*  23 */     this.x = blockposition.getX() >> 4;
/*  24 */     this.z = blockposition.getZ() >> 4;
/*  25 */     this.longKey = pair(this.x, this.z);
/*     */   }
/*     */   
/*     */   public ChunkCoordIntPair(long i) {
/*  29 */     this.x = (int)i;
/*  30 */     this.z = (int)(i >> 32L);
/*  31 */     this.longKey = pair(this.x, this.z);
/*     */   }
/*     */   
/*     */   public long pair() {
/*  35 */     return this.longKey;
/*     */   }
/*     */   public static long pair(BlockPosition pos) {
/*  38 */     return pair(pos.getX() >> 4, pos.getZ() >> 4);
/*     */   } public static long pair(int i, int j) {
/*  40 */     return i & 0xFFFFFFFFL | (j & 0xFFFFFFFFL) << 32L;
/*     */   }
/*     */   
/*     */   public static int getX(long i) {
/*  44 */     return (int)(i & 0xFFFFFFFFL);
/*     */   }
/*     */   
/*     */   public static int getZ(long i) {
/*  48 */     return (int)(i >>> 32L & 0xFFFFFFFFL);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  52 */     int i = 1664525 * this.x + 1013904223;
/*  53 */     int j = 1664525 * (this.z ^ 0xDEADBEEF) + 1013904223;
/*     */     
/*  55 */     return i ^ j;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/*  59 */     if (this == object)
/*  60 */       return true; 
/*  61 */     if (!(object instanceof ChunkCoordIntPair)) {
/*  62 */       return false;
/*     */     }
/*  64 */     ChunkCoordIntPair chunkcoordintpair = (ChunkCoordIntPair)object;
/*     */     
/*  66 */     return (this.x == chunkcoordintpair.x && this.z == chunkcoordintpair.z);
/*     */   }
/*     */ 
/*     */   
/*     */   public int d() {
/*  71 */     return this.x << 4;
/*     */   }
/*     */   
/*     */   public int e() {
/*  75 */     return this.z << 4;
/*     */   }
/*     */   
/*     */   public int f() {
/*  79 */     return (this.x << 4) + 15;
/*     */   }
/*     */   
/*     */   public int g() {
/*  83 */     return (this.z << 4) + 15;
/*     */   }
/*     */   
/*     */   public int getRegionX() {
/*  87 */     return this.x >> 5;
/*     */   }
/*     */   
/*     */   public int getRegionZ() {
/*  91 */     return this.z >> 5;
/*     */   }
/*     */   
/*     */   public int j() {
/*  95 */     return this.x & 0x1F;
/*     */   }
/*     */   
/*     */   public int k() {
/*  99 */     return this.z & 0x1F;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 103 */     return "[" + this.x + ", " + this.z + "]";
/*     */   }
/*     */   public final BlockPosition asPosition() {
/* 106 */     return l();
/*     */   } public BlockPosition l() {
/* 108 */     return new BlockPosition(d(), 0, e());
/*     */   }
/*     */   
/*     */   public int a(ChunkCoordIntPair chunkcoordintpair) {
/* 112 */     return Math.max(Math.abs(this.x - chunkcoordintpair.x), Math.abs(this.z - chunkcoordintpair.z));
/*     */   }
/*     */   
/*     */   public static Stream<ChunkCoordIntPair> a(ChunkCoordIntPair chunkcoordintpair, int i) {
/* 116 */     return a(new ChunkCoordIntPair(chunkcoordintpair.x - i, chunkcoordintpair.z - i), new ChunkCoordIntPair(chunkcoordintpair.x + i, chunkcoordintpair.z + i));
/*     */   }
/*     */   
/*     */   public static Stream<ChunkCoordIntPair> a(final ChunkCoordIntPair chunkcoordintpair, final ChunkCoordIntPair chunkcoordintpair1) {
/* 120 */     int i = Math.abs(chunkcoordintpair.x - chunkcoordintpair1.x) + 1;
/* 121 */     int j = Math.abs(chunkcoordintpair.z - chunkcoordintpair1.z) + 1;
/* 122 */     final int k = (chunkcoordintpair.x < chunkcoordintpair1.x) ? 1 : -1;
/* 123 */     final int l = (chunkcoordintpair.z < chunkcoordintpair1.z) ? 1 : -1;
/*     */     
/* 125 */     return StreamSupport.stream(new Spliterators.AbstractSpliterator<ChunkCoordIntPair>((i * j), 64) {
/*     */           @Nullable
/*     */           private ChunkCoordIntPair e;
/*     */           
/*     */           public boolean tryAdvance(Consumer<? super ChunkCoordIntPair> consumer) {
/* 130 */             if (this.e == null) {
/* 131 */               this.e = chunkcoordintpair;
/*     */             } else {
/* 133 */               int i1 = this.e.x;
/* 134 */               int j1 = this.e.z;
/*     */               
/* 136 */               if (i1 == chunkcoordintpair1.x) {
/* 137 */                 if (j1 == chunkcoordintpair1.z) {
/* 138 */                   return false;
/*     */                 }
/*     */                 
/* 141 */                 this.e = new ChunkCoordIntPair(chunkcoordintpair.x, j1 + l);
/*     */               } else {
/* 143 */                 this.e = new ChunkCoordIntPair(i1 + k, j1);
/*     */               } 
/*     */             } 
/*     */             
/* 147 */             consumer.accept(this.e);
/* 148 */             return true;
/*     */           }
/*     */         }false);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ChunkCoordIntPair.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */