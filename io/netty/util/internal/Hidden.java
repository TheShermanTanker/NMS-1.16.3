/*     */ package io.netty.util.internal;
/*     */ 
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Predicate;
/*     */ import reactor.blockhound.BlockHound;
/*     */ import reactor.blockhound.integration.BlockHoundIntegration;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class Hidden
/*     */ {
/*     */   @SuppressJava6Requirement(reason = "BlockHound is Java 8+, but this class is only loaded by it's SPI")
/*     */   public static final class NettyBlockHoundIntegration
/*     */     implements BlockHoundIntegration
/*     */   {
/*     */     public void applyTo(BlockHound.Builder builder) {
/*  43 */       builder.allowBlockingCallsInside("io.netty.channel.nio.NioEventLoop", "handleLoopException");
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  48 */       builder.allowBlockingCallsInside("io.netty.channel.kqueue.KQueueEventLoop", "handleLoopException");
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  53 */       builder.allowBlockingCallsInside("io.netty.channel.epoll.EpollEventLoop", "handleLoopException");
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  58 */       builder.allowBlockingCallsInside("io.netty.util.HashedWheelTimer$Worker", "waitForNextTick");
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  63 */       builder.allowBlockingCallsInside("io.netty.util.concurrent.SingleThreadEventExecutor", "confirmShutdown");
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  68 */       builder.allowBlockingCallsInside("io.netty.handler.ssl.SslHandler", "handshake");
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  73 */       builder.allowBlockingCallsInside("io.netty.handler.ssl.SslHandler", "runAllDelegatedTasks");
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  78 */       builder.allowBlockingCallsInside("io.netty.util.concurrent.GlobalEventExecutor", "takeTask");
/*     */ 
/*     */ 
/*     */       
/*  82 */       builder.allowBlockingCallsInside("io.netty.util.concurrent.GlobalEventExecutor", "addTask");
/*     */ 
/*     */ 
/*     */       
/*  86 */       builder.allowBlockingCallsInside("io.netty.util.concurrent.SingleThreadEventExecutor", "takeTask");
/*     */ 
/*     */ 
/*     */       
/*  90 */       builder.nonBlockingThreadPredicate(new Function<Predicate<Thread>, Predicate<Thread>>()
/*     */           {
/*     */             public Predicate<Thread> apply(final Predicate<Thread> p) {
/*  93 */               return new Predicate<Thread>()
/*     */                 {
/*     */                   @SuppressJava6Requirement(reason = "Predicate#test")
/*     */                   public boolean test(Thread thread) {
/*  97 */                     return (p.test(thread) || thread instanceof io.netty.util.concurrent.FastThreadLocalThread);
/*     */                   }
/*     */                 };
/*     */             }
/*     */           });
/*     */     }
/*     */ 
/*     */     
/*     */     public int compareTo(BlockHoundIntegration o) {
/* 106 */       return 0;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\nett\\util\internal\Hidden.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */