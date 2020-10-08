/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ import java.util.concurrent.Executor;
/*    */ 
/*    */ 
/*    */ public interface IReloadableResourceManager
/*    */   extends IResourceManager, AutoCloseable
/*    */ {
/*    */   default CompletableFuture<Unit> a(Executor var0, Executor var1, List<IResourcePack> var2, CompletableFuture<Unit> var3) {
/* 12 */     return a(var0, var1, var3, var2).a();
/*    */   }
/*    */   
/*    */   IReloadable a(Executor paramExecutor1, Executor paramExecutor2, CompletableFuture<Unit> paramCompletableFuture, List<IResourcePack> paramList);
/*    */   
/*    */   void a(IReloadListener paramIReloadListener);
/*    */   
/*    */   void close();
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IReloadableResourceManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */