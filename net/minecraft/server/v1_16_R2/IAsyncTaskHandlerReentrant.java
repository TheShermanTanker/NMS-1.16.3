/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public abstract class IAsyncTaskHandlerReentrant<R extends Runnable> extends IAsyncTaskHandler<R> {
/*    */   private int depth;
/*    */   
/*    */   public IAsyncTaskHandlerReentrant(String var0) {
/*  7 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean isNotMainThread() {
/* 12 */     return (isEntered() || super.isNotMainThread());
/*    */   }
/*    */   
/*    */   protected boolean isEntered() {
/* 16 */     return (this.depth != 0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void executeTask(R var0) {
/* 21 */     this.depth++;
/*    */     try {
/* 23 */       super.executeTask(var0);
/*    */     } finally {
/* 25 */       this.depth--;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IAsyncTaskHandlerReentrant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */