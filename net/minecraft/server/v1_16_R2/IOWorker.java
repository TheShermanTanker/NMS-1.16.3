/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.datafixers.util.Either;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.concurrent.CompletionException;
/*     */ import java.util.concurrent.CompletionStage;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Supplier;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class IOWorker
/*     */   implements AutoCloseable {
/*  21 */   private static final Logger LOGGER = LogManager.getLogger(); private final ThreadedMailbox<PairedQueue.b> c;
/*  22 */   private final AtomicBoolean b = new AtomicBoolean(); private final RegionFileCache d;
/*     */   public RegionFileCache getRegionFileCache() {
/*  24 */     return this.d;
/*  25 */   } private final Map<ChunkCoordIntPair, a> e = Maps.newLinkedHashMap();
/*     */   
/*     */   protected IOWorker(File file, boolean flag, String s) {
/*  28 */     this.d = new RegionFileCache(file, flag);
/*  29 */     this.c = new ThreadedMailbox<>(new PairedQueue.a((Priority.values()).length), SystemUtils.g(), "IOWorker-" + s);
/*     */   }
/*     */   
/*     */   public CompletableFuture<Void> a(ChunkCoordIntPair chunkcoordintpair, NBTTagCompound nbttagcompound) {
/*  33 */     return a(() -> {
/*     */           a ioworker_a = this.e.computeIfAbsent(chunkcoordintpair, ());
/*     */ 
/*     */           
/*     */           ioworker_a.a = nbttagcompound;
/*     */           
/*     */           return Either.left(ioworker_a.b);
/*  40 */         }).thenCompose(Function.identity());
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public NBTTagCompound a(ChunkCoordIntPair chunkcoordintpair) throws IOException {
/*  45 */     CompletableFuture<?> completablefuture = a(() -> {
/*     */           a ioworker_a = this.e.get(chunkcoordintpair);
/*     */           
/*     */           if (ioworker_a != null) {
/*     */             return Either.left(ioworker_a.a);
/*     */           }
/*     */           
/*     */           try {
/*     */             NBTTagCompound nbttagcompound = this.d.read(chunkcoordintpair);
/*     */             return Either.left(nbttagcompound);
/*  55 */           } catch (Exception exception) {
/*     */             LOGGER.warn("Failed to read chunk {}", chunkcoordintpair, exception);
/*     */             
/*     */             return Either.right(exception);
/*     */           } 
/*     */         });
/*     */     
/*     */     try {
/*  63 */       return (NBTTagCompound)completablefuture.join();
/*  64 */     } catch (CompletionException completionexception) {
/*  65 */       if (completionexception.getCause() instanceof IOException) {
/*  66 */         throw (IOException)completionexception.getCause();
/*     */       }
/*  68 */       throw completionexception;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompletableFuture<Void> a() {
/*  80 */     CompletableFuture<Void> completablefuture = a(() -> Either.left(CompletableFuture.allOf((CompletableFuture<?>[])this.e.values().stream().map(()).toArray(())))).thenCompose(Function.identity());
/*     */     
/*  82 */     return completablefuture.thenCompose(ovoid -> a(()));
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
/*     */   private <T> CompletableFuture<T> a(Supplier<Either<T, Exception>> supplier) {
/*  96 */     return this.c.c(mailbox -> new PairedQueue.b(Priority.HIGH.ordinal(), ()));
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
/*     */   private void b() {
/* 108 */     Iterator<Map.Entry<ChunkCoordIntPair, a>> iterator = this.e.entrySet().iterator();
/*     */     
/* 110 */     if (iterator.hasNext()) {
/* 111 */       Map.Entry<ChunkCoordIntPair, a> entry = iterator.next();
/*     */       
/* 113 */       iterator.remove();
/* 114 */       a(entry.getKey(), entry.getValue());
/* 115 */       c();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void c() {
/* 120 */     this.c.a(new PairedQueue.b(Priority.LOW.ordinal(), this::b));
/*     */   }
/*     */   
/*     */   private void a(ChunkCoordIntPair chunkcoordintpair, a ioworker_a) {
/*     */     try {
/* 125 */       this.d.write(chunkcoordintpair, ioworker_a.a);
/* 126 */       ioworker_a.b.complete(null);
/* 127 */     } catch (Exception exception) {
/* 128 */       LOGGER.error("Failed to store chunk {}", chunkcoordintpair, exception);
/* 129 */       ioworker_a.b.completeExceptionally(exception);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 135 */     if (this.b.compareAndSet(false, true)) {
/* 136 */       CompletableFuture completablefuture = this.c.b(mailbox -> new PairedQueue.b(Priority.HIGH.ordinal(), ()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 143 */         completablefuture.join();
/* 144 */       } catch (CompletionException completionexception) {
/* 145 */         if (completionexception.getCause() instanceof IOException) {
/* 146 */           throw (IOException)completionexception.getCause();
/*     */         }
/*     */         
/* 149 */         throw completionexception;
/*     */       } 
/*     */       
/* 152 */       this.c.close();
/* 153 */       this.e.forEach(this::a);
/* 154 */       this.e.clear();
/*     */       
/*     */       try {
/* 157 */         this.d.close();
/* 158 */       } catch (Exception exception) {
/* 159 */         LOGGER.error("Failed to close storage", exception);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   static class a
/*     */   {
/*     */     private NBTTagCompound a;
/* 168 */     private final CompletableFuture<Void> b = new CompletableFuture<>();
/*     */     
/*     */     public a(NBTTagCompound nbttagcompound) {
/* 171 */       this.a = nbttagcompound;
/*     */     }
/*     */   }
/*     */   
/*     */   enum Priority
/*     */   {
/* 177 */     HIGH, LOW;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IOWorker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */