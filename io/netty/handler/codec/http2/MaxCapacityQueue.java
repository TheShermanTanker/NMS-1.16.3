/*     */ package io.netty.handler.codec.http2;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Queue;
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
/*     */ final class MaxCapacityQueue<E>
/*     */   implements Queue<E>
/*     */ {
/*     */   private final Queue<E> queue;
/*     */   private final int maxCapacity;
/*     */   
/*     */   MaxCapacityQueue(Queue<E> queue, int maxCapacity) {
/*  27 */     this.queue = queue;
/*  28 */     this.maxCapacity = maxCapacity;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(E element) {
/*  33 */     if (offer(element)) {
/*  34 */       return true;
/*     */     }
/*  36 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean offer(E element) {
/*  41 */     if (this.maxCapacity <= this.queue.size()) {
/*  42 */       return false;
/*     */     }
/*  44 */     return this.queue.offer(element);
/*     */   }
/*     */ 
/*     */   
/*     */   public E remove() {
/*  49 */     return this.queue.remove();
/*     */   }
/*     */ 
/*     */   
/*     */   public E poll() {
/*  54 */     return this.queue.poll();
/*     */   }
/*     */ 
/*     */   
/*     */   public E element() {
/*  59 */     return this.queue.element();
/*     */   }
/*     */ 
/*     */   
/*     */   public E peek() {
/*  64 */     return this.queue.peek();
/*     */   }
/*     */ 
/*     */   
/*     */   public int size() {
/*  69 */     return this.queue.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/*  74 */     return this.queue.isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(Object o) {
/*  79 */     return this.queue.contains(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator<E> iterator() {
/*  84 */     return this.queue.iterator();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object[] toArray() {
/*  89 */     return this.queue.toArray();
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> T[] toArray(T[] a) {
/*  94 */     return (T[])this.queue.toArray((Object[])a);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean remove(Object o) {
/*  99 */     return this.queue.remove(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsAll(Collection<?> c) {
/* 104 */     return this.queue.containsAll(c);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addAll(Collection<? extends E> c) {
/* 109 */     if (this.maxCapacity >= size() + c.size()) {
/* 110 */       return this.queue.addAll(c);
/*     */     }
/* 112 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean removeAll(Collection<?> c) {
/* 117 */     return this.queue.removeAll(c);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean retainAll(Collection<?> c) {
/* 122 */     return this.queue.retainAll(c);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 127 */     this.queue.clear();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\handler\codec\http2\MaxCapacityQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */