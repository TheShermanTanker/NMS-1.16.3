/*     */ package io.netty.handler.codec.compression;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import net.jpountz.xxhash.XXHash32;
/*     */ import net.jpountz.xxhash.XXHashFactory;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Lz4XXHash32
/*     */   extends ByteBufChecksum
/*     */ {
/*  51 */   private static final XXHash32 XXHASH32 = XXHashFactory.fastestInstance().hash32();
/*     */   
/*     */   private final int seed;
/*     */   
/*     */   private boolean used;
/*     */   private int value;
/*     */   
/*     */   public Lz4XXHash32(int seed) {
/*  59 */     this.seed = seed;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update(int b) {
/*  64 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public void update(byte[] b, int off, int len) {
/*  69 */     if (this.used) {
/*  70 */       throw new IllegalStateException();
/*     */     }
/*  72 */     this.value = XXHASH32.hash(b, off, len, this.seed);
/*  73 */     this.used = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update(ByteBuf b, int off, int len) {
/*  78 */     if (this.used) {
/*  79 */       throw new IllegalStateException();
/*     */     }
/*  81 */     if (b.hasArray()) {
/*  82 */       this.value = XXHASH32.hash(b.array(), b.arrayOffset() + off, len, this.seed);
/*     */     } else {
/*  84 */       this.value = XXHASH32.hash(CompressionUtil.safeNioBuffer(b, off, len), this.seed);
/*     */     } 
/*  86 */     this.used = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getValue() {
/*  91 */     if (!this.used) {
/*  92 */       throw new IllegalStateException();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 100 */     return this.value & 0xFFFFFFFL;
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 105 */     this.used = false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\handler\codec\compression\Lz4XXHash32.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */