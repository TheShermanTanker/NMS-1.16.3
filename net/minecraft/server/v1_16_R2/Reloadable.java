/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.google.common.collect.Sets;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ import java.util.concurrent.Executor;
/*    */ import java.util.concurrent.atomic.AtomicInteger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Reloadable<S>
/*    */   implements IReloadable
/*    */ {
/*    */   protected final IResourceManager a;
/* 21 */   protected final CompletableFuture<Unit> b = new CompletableFuture<>();
/*    */   
/*    */   protected final CompletableFuture<List<S>> c;
/*    */   
/*    */   private final Set<IReloadListener> d;
/*    */   private final int e;
/*    */   private int f;
/*    */   private int g;
/* 29 */   private final AtomicInteger h = new AtomicInteger();
/* 30 */   private final AtomicInteger i = new AtomicInteger();
/*    */   
/*    */   public static Reloadable<Void> a(IResourceManager var0, List<IReloadListener> var1, Executor var2, Executor var3, CompletableFuture<Unit> var4) {
/* 33 */     return new Reloadable<>(var2, var3, var0, var1, (var1, var2, var3, var4, var5) -> var3.a(var1, var2, GameProfilerDisabled.a, GameProfilerDisabled.a, var0, var5), var4);
/*    */   }
/*    */   
/*    */   protected Reloadable(Executor var0, Executor var1, IResourceManager var2, List<IReloadListener> var3, a<S> var4, CompletableFuture<Unit> var5) {
/* 37 */     this.a = var2;
/* 38 */     this.e = var3.size();
/* 39 */     this.h.incrementAndGet();
/* 40 */     var5.thenRun(this.i::incrementAndGet);
/* 41 */     List<CompletableFuture<S>> var6 = Lists.newArrayList();
/* 42 */     CompletableFuture<?> var7 = var5;
/* 43 */     this.d = Sets.newHashSet(var3);
/* 44 */     for (IReloadListener var9 : var3) {
/* 45 */       CompletableFuture<?> var10 = var7;
/* 46 */       CompletableFuture<S> var11 = var4.create(new IReloadListener.a(this, var1, var9, var10)
/*    */           {
/*    */             public <T> CompletableFuture<T> a(T var0)
/*    */             {
/* 50 */               this.a.execute(() -> {
/*    */                     Reloadable.a(this.d).remove(var0);
/*    */                     if (Reloadable.a(this.d).isEmpty()) {
/*    */                       this.d.b.complete(Unit.INSTANCE);
/*    */                     }
/*    */                   });
/* 56 */               return this.d.b.thenCombine(this.c, (var1, var2) -> var0);
/*    */             }
/*    */           }var2, var9, var1 -> {
/*    */             this.h.incrementAndGet();
/*    */ 
/*    */ 
/*    */ 
/*    */             
/*    */             var0.execute(());
/*    */           }var1 -> {
/*    */             this.f++;
/*    */ 
/*    */ 
/*    */ 
/*    */             
/*    */             var0.execute(());
/*    */           });
/*    */ 
/*    */ 
/*    */       
/* 76 */       var6.add(var11);
/* 77 */       var7 = var11;
/*    */     } 
/* 79 */     this.c = SystemUtils.b(var6);
/*    */   }
/*    */ 
/*    */   
/*    */   public CompletableFuture<Unit> a() {
/* 84 */     return this.c.thenApply(var0 -> Unit.INSTANCE);
/*    */   }
/*    */   
/*    */   public static interface a<S> {
/*    */     CompletableFuture<S> create(IReloadListener.a param1a, IResourceManager param1IResourceManager, IReloadListener param1IReloadListener, Executor param1Executor1, Executor param1Executor2);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Reloadable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */