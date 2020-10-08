/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.ints.Int2BooleanFunction;
/*     */ import java.util.concurrent.ConcurrentLinkedQueue;
/*     */ import java.util.concurrent.Executor;
/*     */ import java.util.concurrent.RejectedExecutionException;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class ThreadedMailbox<T>
/*     */   implements Mailbox<T>, AutoCloseable, Runnable {
/*  13 */   private static final Logger LOGGER = LogManager.getLogger();
/*  14 */   private final AtomicInteger c = new AtomicInteger(0);
/*     */   public final PairedQueue<? super T, ? extends Runnable> a;
/*     */   private final Executor d;
/*     */   private final String e;
/*     */   
/*     */   public static ThreadedMailbox<Runnable> a(Executor executor, String s) {
/*  20 */     return new ThreadedMailbox<>(new PairedQueue.c<>(new ConcurrentLinkedQueue<>()), executor, s);
/*     */   }
/*     */   
/*     */   public ThreadedMailbox(PairedQueue<? super T, ? extends Runnable> pairedqueue, Executor executor, String s) {
/*  24 */     this.d = executor;
/*  25 */     this.a = pairedqueue;
/*  26 */     this.e = s;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean a() {
/*     */     int i;
/*     */     do {
/*  33 */       i = this.c.get();
/*  34 */       if ((i & 0x3) != 0) {
/*  35 */         return false;
/*     */       }
/*  37 */     } while (!this.c.compareAndSet(i, i | 0x2));
/*     */     
/*  39 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void b() {
/*     */     int i;
/*     */     do {
/*  46 */       i = this.c.get();
/*  47 */     } while (!this.c.compareAndSet(i, i & 0xFFFFFFFD));
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean c() {
/*  52 */     return ((this.c.get() & 0x1) != 0) ? false : (!this.a.b());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     int i;
/*     */     do {
/*  60 */       i = this.c.get();
/*  61 */     } while (!this.c.compareAndSet(i, i | 0x1));
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean d() {
/*  66 */     return ((this.c.get() & 0x2) != 0);
/*     */   } private boolean e() {
/*     */     Thread thread;
/*     */     String s;
/*  70 */     if (!d()) {
/*  71 */       return false;
/*     */     }
/*  73 */     Runnable runnable = this.a.a();
/*     */     
/*  75 */     if (runnable == null) {
/*  76 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  81 */     if (SharedConstants.d) {
/*  82 */       thread = Thread.currentThread();
/*  83 */       s = thread.getName();
/*  84 */       thread.setName(this.e);
/*     */     } else {
/*  86 */       thread = null;
/*  87 */       s = null;
/*     */     } 
/*     */     
/*  90 */     runnable.run();
/*  91 */     if (thread != null) {
/*  92 */       thread.setName(s);
/*     */     }
/*     */     
/*  95 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     try {
/* 102 */       a(i -> (i == 0));
/*     */     }
/*     */     finally {
/*     */       
/* 106 */       b();
/* 107 */       f();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public final void queue(T t0) {
/* 113 */     a(t0);
/*     */   } public void a(T t0) {
/* 115 */     this.a.a(t0);
/* 116 */     f();
/*     */   }
/*     */   
/*     */   private void f() {
/* 120 */     if (c() && a()) {
/*     */       try {
/* 122 */         this.d.execute(this);
/* 123 */       } catch (RejectedExecutionException rejectedexecutionexception) {
/*     */         try {
/* 125 */           this.d.execute(this);
/* 126 */         } catch (RejectedExecutionException rejectedexecutionexception1) {
/* 127 */           LOGGER.error("Cound not schedule mailbox", rejectedexecutionexception1);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int a(Int2BooleanFunction int2booleanfunction) {
/*     */     int i;
/* 137 */     for (i = 0; int2booleanfunction.get(i) && e(); i++);
/*     */ 
/*     */ 
/*     */     
/* 141 */     return i;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 145 */     return this.e + " " + this.c.get() + " " + this.a.b();
/*     */   }
/*     */ 
/*     */   
/*     */   public String bi() {
/* 150 */     return this.e;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ThreadedMailbox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */