/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ import java.util.concurrent.Executor;
/*    */ 
/*    */ 
/*    */ public abstract class ResourceDataAbstract<T>
/*    */   implements IReloadListener
/*    */ {
/*    */   public final CompletableFuture<Void> a(IReloadListener.a var0, IResourceManager var1, GameProfilerFiller var2, GameProfilerFiller var3, Executor var4, Executor var5) {
/* 11 */     return CompletableFuture.supplyAsync(() -> b(var0, var1), var4)
/* 12 */       .thenCompose(var0::a)
/* 13 */       .thenAcceptAsync(var2 -> a((T)var2, var0, var1), var5);
/*    */   }
/*    */   
/*    */   protected abstract T b(IResourceManager paramIResourceManager, GameProfilerFiller paramGameProfilerFiller);
/*    */   
/*    */   protected abstract void a(T paramT, IResourceManager paramIResourceManager, GameProfilerFiller paramGameProfilerFiller);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ResourceDataAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */