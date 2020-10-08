/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Queues;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Queue;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public interface PairedQueue<T, F>
/*     */ {
/*     */   @Nullable
/*     */   F a();
/*     */   
/*     */   boolean a(T paramT);
/*     */   
/*     */   boolean b();
/*     */   
/*     */   public static final class a
/*     */     implements PairedQueue<b, Runnable> {
/*     */     private final List<Queue<Runnable>> a;
/*     */     
/*     */     private final List<Queue<Runnable>> getQueues() {
/*  23 */       return this.a;
/*     */     }
/*     */     
/*     */     public a(int i) {
/*  27 */       this.a = new ArrayList<>(i);
/*  28 */       for (int j = 0; j < i; j++) {
/*  29 */         getQueues().add(Queues.newConcurrentLinkedQueue());
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public Runnable a() {
/*  38 */       for (int i = 0, len = getQueues().size(); i < len; i++) {
/*  39 */         Queue<Runnable> queue = getQueues().get(i);
/*  40 */         Runnable ret = queue.poll();
/*  41 */         if (ret != null) {
/*  42 */           return ret;
/*     */         }
/*     */       } 
/*  45 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a(PairedQueue.b pairedqueue_b) {
/*  50 */       int i = pairedqueue_b.a();
/*     */       
/*  52 */       ((Queue<PairedQueue.b>)this.a.get(i)).add(pairedqueue_b);
/*  53 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean b() {
/*  60 */       for (int i = 0, len = getQueues().size(); i < len; i++) {
/*  61 */         Queue<Runnable> queue = getQueues().get(i);
/*  62 */         if (!queue.isEmpty()) {
/*  63 */           return false;
/*     */         }
/*     */       } 
/*  66 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class b
/*     */     implements Runnable
/*     */   {
/*     */     private final int a;
/*     */     private final Runnable b;
/*     */     
/*     */     public b(int i, Runnable runnable) {
/*  77 */       this.a = i;
/*  78 */       this.b = runnable;
/*     */     }
/*     */     
/*     */     public void run() {
/*  82 */       this.b.run();
/*     */     }
/*     */     
/*     */     public int a() {
/*  86 */       return this.a;
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class c<T>
/*     */     implements PairedQueue<T, T> {
/*     */     private final Queue<T> a;
/*     */     
/*     */     public c(Queue<T> queue) {
/*  95 */       this.a = queue;
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public T a() {
/* 101 */       return this.a.poll();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a(T t0) {
/* 106 */       return this.a.add(t0);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 111 */       return this.a.isEmpty();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PairedQueue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */