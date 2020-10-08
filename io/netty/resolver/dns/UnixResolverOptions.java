/*    */ package io.netty.resolver.dns;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class UnixResolverOptions
/*    */ {
/*    */   private final int ndots;
/*    */   private final int timeout;
/*    */   private final int attempts;
/*    */   
/*    */   UnixResolverOptions(int ndots, int timeout, int attempts) {
/* 28 */     this.ndots = ndots;
/* 29 */     this.timeout = timeout;
/* 30 */     this.attempts = attempts;
/*    */   }
/*    */   
/*    */   static Builder newBuilder() {
/* 34 */     return new Builder();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   int ndots() {
/* 42 */     return this.ndots;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   int timeout() {
/* 50 */     return this.timeout;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   int attempts() {
/* 58 */     return this.attempts;
/*    */   }
/*    */   
/*    */   static final class Builder
/*    */   {
/* 63 */     private int ndots = 1;
/* 64 */     private int timeout = 5;
/* 65 */     private int attempts = 16;
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     void setNdots(int ndots) {
/* 71 */       this.ndots = ndots;
/*    */     }
/*    */     
/*    */     void setTimeout(int timeout) {
/* 75 */       this.timeout = timeout;
/*    */     }
/*    */     
/*    */     void setAttempts(int attempts) {
/* 79 */       this.attempts = attempts;
/*    */     }
/*    */     
/*    */     UnixResolverOptions build() {
/* 83 */       return new UnixResolverOptions(this.ndots, this.timeout, this.attempts);
/*    */     }
/*    */     
/*    */     private Builder() {}
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\resolver\dns\UnixResolverOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */