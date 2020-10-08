/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.annotations.VisibleForTesting;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.mojang.datafixers.util.Either;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.concurrent.Executor;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.IntConsumer;
/*     */ import java.util.function.IntSupplier;
/*     */ import java.util.stream.Collectors;
/*     */ import java.util.stream.Stream;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChunkTaskQueueSorter
/*     */   implements AutoCloseable, PlayerChunk.c
/*     */ {
/*  29 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   private final Map<Mailbox<?>, ChunkTaskQueue<? extends Function<Mailbox<Unit>, ?>>> b;
/*     */   private final Set<Mailbox<?>> c;
/*     */   private final ThreadedMailbox<PairedQueue.b> d;
/*     */   
/*     */   public ChunkTaskQueueSorter(List<Mailbox<?>> var0, Executor var1, int var2) {
/*  35 */     this.b = (Map<Mailbox<?>, ChunkTaskQueue<? extends Function<Mailbox<Unit>, ?>>>)var0.stream().collect(Collectors.toMap(Function.identity(), var1 -> new ChunkTaskQueue(var1.bi() + "_queue", var0)));
/*  36 */     this.c = Sets.newHashSet(var0);
/*  37 */     this.d = new ThreadedMailbox<>(new PairedQueue.a(4), var1, "sorter");
/*     */   }
/*     */   
/*     */   public static final class a<T> {
/*     */     private final Function<Mailbox<Unit>, T> a;
/*     */     private final long b;
/*     */     private final IntSupplier c;
/*     */     
/*     */     private a(Function<Mailbox<Unit>, T> var0, long var1, IntSupplier var3) {
/*  46 */       this.a = var0;
/*  47 */       this.b = var1;
/*  48 */       this.c = var3;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static a<Runnable> a(Runnable var0, long var1, IntSupplier var3) {
/*  57 */     return new a<>(var1 -> (), var1, var3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static a<Runnable> a(PlayerChunk var0, Runnable var1) {
/*  64 */     return a(var1, var0.i().pair(), var0::k);
/*     */   }
/*     */ 
/*     */   
/*     */   public static final class b
/*     */   {
/*     */     private final Runnable a;
/*     */     
/*     */     private final long b;
/*     */     
/*     */     private final boolean c;
/*     */     
/*     */     private b(Runnable var0, long var1, boolean var3) {
/*  77 */       this.a = var0;
/*  78 */       this.b = var1;
/*  79 */       this.c = var3;
/*     */     }
/*     */   }
/*     */   
/*     */   public static b a(Runnable var0, long var1, boolean var3) {
/*  84 */     return new b(var0, var1, var3);
/*     */   }
/*     */   
/*     */   public <T> Mailbox<a<T>> a(Mailbox<T> var0, boolean var1) {
/*  88 */     return this.d.b(var2 -> new PairedQueue.b(0, ()))
/*     */ 
/*     */ 
/*     */       
/*  92 */       .join();
/*     */   }
/*     */   
/*     */   public Mailbox<b> a(Mailbox<Runnable> var0) {
/*  96 */     return this.d.b(var1 -> new PairedQueue.b(0, ()))
/*     */       
/*  98 */       .join();
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(ChunkCoordIntPair var0, IntSupplier var1, int var2, IntConsumer var3) {
/* 103 */     this.d.a(new PairedQueue.b(0, () -> {
/*     */             int var4 = var0.getAsInt();
/*     */             this.b.values().forEach(());
/*     */             var3.accept(var2);
/*     */           }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private <T> void a(Mailbox<T> var0, long var1, Runnable var3, boolean var4) {
/* 114 */     this.d.a(new PairedQueue.b(1, () -> {
/*     */             ChunkTaskQueue<Function<Mailbox<Unit>, T>> var5 = b(var0);
/*     */             var5.a(var1, var3);
/*     */             if (this.c.remove(var0)) {
/*     */               a(var5, var0);
/*     */             }
/*     */             var4.run();
/*     */           }));
/*     */   }
/*     */   
/*     */   private <T> void a(Mailbox<T> var0, Function<Mailbox<Unit>, T> var1, long var2, IntSupplier var4, boolean var5) {
/* 125 */     this.d.a(new PairedQueue.b(2, () -> {
/*     */             ChunkTaskQueue<Function<Mailbox<Unit>, T>> var6 = b(var0);
/*     */             int var7 = var1.getAsInt();
/*     */             var6.a(Optional.of(var4), var2, var7);
/*     */             if (var5) {
/*     */               var6.a(Optional.empty(), var2, var7);
/*     */             }
/*     */             if (this.c.remove(var0)) {
/*     */               a(var6, var0);
/*     */             }
/*     */           }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private <T> void a(ChunkTaskQueue<Function<Mailbox<Unit>, T>> var0, Mailbox<T> var1) {
/* 142 */     this.d.a(new PairedQueue.b(3, () -> {
/*     */             Stream<Either<Function<Mailbox<Unit>, T>, Runnable>> var2 = var0.a();
/*     */             if (var2 == null) {
/*     */               this.c.add(var1);
/*     */             } else {
/*     */               SystemUtils.b((List<? extends CompletableFuture<?>>)var2.map(()).collect(Collectors.toList())).thenAccept(());
/*     */             } 
/*     */           }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private <T> ChunkTaskQueue<Function<Mailbox<Unit>, T>> b(Mailbox<T> var0) {
/* 157 */     ChunkTaskQueue<? extends Function<Mailbox<Unit>, ?>> var1 = this.b.get(var0);
/* 158 */     if (var1 == null) {
/* 159 */       throw (IllegalArgumentException)SystemUtils.c(new IllegalArgumentException("No queue for: " + var0));
/*     */     }
/* 161 */     return (ChunkTaskQueue)var1;
/*     */   }
/*     */   
/*     */   @VisibleForTesting
/*     */   public String a() {
/* 166 */     return (String)this.b.entrySet().stream()
/* 167 */       .map(var0 -> ((Mailbox)var0.getKey()).bi() + "=[" + (String)((ChunkTaskQueue)var0.getValue()).b().stream().map(()).collect(Collectors.joining(",")) + "]")
/* 168 */       .collect(Collectors.joining(",")) + ", s=" + this.c.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() {
/* 173 */     this.b.keySet().forEach(Mailbox::close);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ChunkTaskQueueSorter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */