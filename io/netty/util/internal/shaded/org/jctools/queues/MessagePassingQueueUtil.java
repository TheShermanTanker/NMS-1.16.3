/*     */ package io.netty.util.internal.shaded.org.jctools.queues;
/*     */ 
/*     */ import io.netty.util.internal.shaded.org.jctools.util.PortableJvmInfo;
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
/*     */ public final class MessagePassingQueueUtil
/*     */ {
/*     */   public static <E> int drain(MessagePassingQueue<E> queue, MessagePassingQueue.Consumer<E> c, int limit) {
/*  35 */     if (null == c)
/*  36 */       throw new IllegalArgumentException("c is null"); 
/*  37 */     if (limit < 0)
/*  38 */       throw new IllegalArgumentException("limit is negative: " + limit); 
/*  39 */     if (limit == 0) {
/*  40 */       return 0;
/*     */     }
/*  42 */     int i = 0; E e;
/*  43 */     for (; i < limit && (e = queue.relaxedPoll()) != null; i++)
/*     */     {
/*  45 */       c.accept(e);
/*     */     }
/*  47 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public static <E> int drain(MessagePassingQueue<E> queue, MessagePassingQueue.Consumer<E> c) {
/*  52 */     if (null == c) {
/*  53 */       throw new IllegalArgumentException("c is null");
/*     */     }
/*  55 */     int i = 0; E e;
/*  56 */     while ((e = queue.relaxedPoll()) != null) {
/*     */       
/*  58 */       i++;
/*  59 */       c.accept(e);
/*     */     } 
/*  61 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public static <E> void drain(MessagePassingQueue<E> queue, MessagePassingQueue.Consumer<E> c, MessagePassingQueue.WaitStrategy wait, MessagePassingQueue.ExitCondition exit) {
/*  66 */     if (null == c)
/*  67 */       throw new IllegalArgumentException("c is null"); 
/*  68 */     if (null == wait)
/*  69 */       throw new IllegalArgumentException("wait is null"); 
/*  70 */     if (null == exit) {
/*  71 */       throw new IllegalArgumentException("exit condition is null");
/*     */     }
/*  73 */     int idleCounter = 0;
/*  74 */     while (exit.keepRunning()) {
/*     */       
/*  76 */       E e = queue.relaxedPoll();
/*  77 */       if (e == null) {
/*     */         
/*  79 */         idleCounter = wait.idle(idleCounter);
/*     */         continue;
/*     */       } 
/*  82 */       idleCounter = 0;
/*  83 */       c.accept(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static <E> void fill(MessagePassingQueue<E> q, MessagePassingQueue.Supplier<E> s, MessagePassingQueue.WaitStrategy wait, MessagePassingQueue.ExitCondition exit) {
/*  89 */     if (null == wait)
/*  90 */       throw new IllegalArgumentException("waiter is null"); 
/*  91 */     if (null == exit) {
/*  92 */       throw new IllegalArgumentException("exit condition is null");
/*     */     }
/*  94 */     int idleCounter = 0;
/*  95 */     while (exit.keepRunning()) {
/*     */       
/*  97 */       if (q.fill(s, PortableJvmInfo.RECOMENDED_OFFER_BATCH) == 0) {
/*     */         
/*  99 */         idleCounter = wait.idle(idleCounter);
/*     */         continue;
/*     */       } 
/* 102 */       idleCounter = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static <E> int fillBounded(MessagePassingQueue<E> q, MessagePassingQueue.Supplier<E> s) {
/* 108 */     return fillInBatchesToLimit(q, s, PortableJvmInfo.RECOMENDED_OFFER_BATCH, q.capacity());
/*     */   }
/*     */ 
/*     */   
/*     */   public static <E> int fillInBatchesToLimit(MessagePassingQueue<E> q, MessagePassingQueue.Supplier<E> s, int batch, int limit) {
/* 113 */     long result = 0L;
/*     */     
/*     */     while (true) {
/* 116 */       int filled = q.fill(s, batch);
/* 117 */       if (filled == 0)
/*     */       {
/* 119 */         return (int)result;
/*     */       }
/* 121 */       result += filled;
/*     */       
/* 123 */       if (result > limit)
/* 124 */         return (int)result; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static <E> int fillUnbounded(MessagePassingQueue<E> q, MessagePassingQueue.Supplier<E> s) {
/* 129 */     return fillInBatchesToLimit(q, s, PortableJvmInfo.RECOMENDED_OFFER_BATCH, 4096);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\nett\\util\internal\shaded\org\jctools\queues\MessagePassingQueueUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */