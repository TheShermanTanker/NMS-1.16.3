/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ import java.util.concurrent.Executor;
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface acg
/*    */   extends IReloadListener
/*    */ {
/*    */   default CompletableFuture<Void> a(IReloadListener.a var0, IResourceManager var1, GameProfilerFiller var2, GameProfilerFiller var3, Executor var4, Executor var5) {
/* 12 */     return var0.<Unit>a(Unit.INSTANCE).thenRunAsync(() -> { var0.a(); var0.enter("listener"); a(var1); var0.exit(); var0.b(); }var5);
/*    */   }
/*    */   
/*    */   void a(IResourceManager paramIResourceManager);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\acg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */