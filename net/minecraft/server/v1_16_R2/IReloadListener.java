/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ import java.util.concurrent.Executor;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface IReloadListener
/*    */ {
/*    */   CompletableFuture<Void> a(a parama, IResourceManager paramIResourceManager, GameProfilerFiller paramGameProfilerFiller1, GameProfilerFiller paramGameProfilerFiller2, Executor paramExecutor1, Executor paramExecutor2);
/*    */   
/*    */   default String c() {
/* 16 */     return getClass().getSimpleName();
/*    */   }
/*    */   
/*    */   public static interface a {
/*    */     <T> CompletableFuture<T> a(T param1T);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IReloadListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */