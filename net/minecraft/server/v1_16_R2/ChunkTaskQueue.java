/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.annotations.VisibleForTesting;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.datafixers.util.Either;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectLinkedOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.longs.LongCollection;
/*     */ import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
/*     */ import it.unimi.dsi.fastutil.longs.LongSet;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.stream.Collectors;
/*     */ import java.util.stream.IntStream;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class ChunkTaskQueue<T>
/*     */ {
/*  19 */   public static final int a = PlayerChunkMap.GOLDEN_TICKET + 2;
/*  20 */   private final List<Long2ObjectLinkedOpenHashMap<List<Optional<T>>>> b = (List<Long2ObjectLinkedOpenHashMap<List<Optional<T>>>>)IntStream.range(0, a).mapToObj(var0 -> new Long2ObjectLinkedOpenHashMap()).collect(Collectors.toList());
/*  21 */   private volatile int c = a;
/*     */   
/*     */   private final String d;
/*     */   
/*  25 */   private final LongSet e = (LongSet)new LongOpenHashSet();
/*     */   private final int f;
/*     */   
/*     */   public ChunkTaskQueue(String var0, int var1) {
/*  29 */     this.d = var0;
/*  30 */     this.f = var1;
/*     */   }
/*     */   
/*     */   protected void a(int var0, ChunkCoordIntPair var1, int var2) {
/*  34 */     if (var0 >= a) {
/*     */       return;
/*     */     }
/*  37 */     Long2ObjectLinkedOpenHashMap<List<Optional<T>>> var3 = this.b.get(var0);
/*  38 */     List<Optional<T>> var4 = (List<Optional<T>>)var3.remove(var1.pair());
/*  39 */     if (var0 == this.c) {
/*  40 */       while (this.c < a && ((Long2ObjectLinkedOpenHashMap)this.b.get(this.c)).isEmpty()) {
/*  41 */         this.c++;
/*     */       }
/*     */     }
/*  44 */     if (var4 != null && !var4.isEmpty()) {
/*  45 */       ((List<Optional<T>>)((Long2ObjectLinkedOpenHashMap)this.b.get(var2)).computeIfAbsent(var1.pair(), var0 -> Lists.newArrayList())).addAll(var4);
/*  46 */       this.c = Math.min(this.c, var2);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void a(Optional<T> var0, long var1, int var3) {
/*  51 */     ((List<Optional<T>>)((Long2ObjectLinkedOpenHashMap)this.b.get(var3)).computeIfAbsent(var1, var0 -> Lists.newArrayList())).add(var0);
/*  52 */     this.c = Math.min(this.c, var3);
/*     */   }
/*     */   
/*     */   protected void a(long var0, boolean var2) {
/*  56 */     for (Long2ObjectLinkedOpenHashMap<List<Optional<T>>> var4 : this.b) {
/*  57 */       List<Optional<T>> var5 = (List<Optional<T>>)var4.get(var0);
/*  58 */       if (var5 == null) {
/*     */         continue;
/*     */       }
/*  61 */       if (var2) {
/*  62 */         var5.clear();
/*     */       } else {
/*  64 */         var5.removeIf(var0 -> !var0.isPresent());
/*     */       } 
/*  66 */       if (var5.isEmpty()) {
/*  67 */         var4.remove(var0);
/*     */       }
/*     */     } 
/*  70 */     while (this.c < a && ((Long2ObjectLinkedOpenHashMap)this.b.get(this.c)).isEmpty()) {
/*  71 */       this.c++;
/*     */     }
/*  73 */     this.e.remove(var0);
/*     */   }
/*     */   
/*     */   private Runnable a(long var0) {
/*  77 */     return () -> this.e.add(var0);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Stream<Either<T, Runnable>> a() {
/*  82 */     if (this.e.size() >= this.f) {
/*  83 */       return null;
/*     */     }
/*  85 */     if (this.c < a) {
/*  86 */       int var0 = this.c;
/*  87 */       Long2ObjectLinkedOpenHashMap<List<Optional<T>>> var1 = this.b.get(var0);
/*  88 */       long var2 = var1.firstLongKey();
/*  89 */       List<Optional<T>> var4 = (List<Optional<T>>)var1.removeFirst();
/*  90 */       while (this.c < a && ((Long2ObjectLinkedOpenHashMap)this.b.get(this.c)).isEmpty()) {
/*  91 */         this.c++;
/*     */       }
/*  93 */       return var4.stream().map(var2 -> (Either)var2.map(Either::left).orElseGet(()));
/*     */     } 
/*  95 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 100 */     return this.d + " " + this.c + "...";
/*     */   }
/*     */   
/*     */   @VisibleForTesting
/*     */   LongSet b() {
/* 105 */     return (LongSet)new LongOpenHashSet((LongCollection)this.e);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ChunkTaskQueue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */