/*    */ package com.destroystokyo.paper.util.pooled;
/*    */ 
/*    */ import java.util.ArrayDeque;
/*    */ import java.util.function.Consumer;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.server.v1_16_R2.MCUtil;
/*    */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.mutable.MutableInt;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class PooledObjects<E>
/*    */ {
/*    */   public class AutoReleased
/*    */   {
/*    */     private final E object;
/*    */     private final Runnable cleaner;
/*    */     
/*    */     public AutoReleased(E object, Runnable cleaner) {
/* 20 */       this.object = object;
/* 21 */       this.cleaner = cleaner;
/*    */     }
/*    */     
/*    */     public final E getObject() {
/* 25 */       return this.object;
/*    */     }
/*    */     
/*    */     public final Runnable getCleaner() {
/* 29 */       return this.cleaner;
/*    */     }
/*    */   }
/*    */   
/* 33 */   public static final PooledObjects<MutableInt> POOLED_MUTABLE_INTEGERS = new PooledObjects(MutableInt::new, 1024);
/*    */   
/*    */   private final Supplier<E> creator;
/*    */   private final Consumer<E> releaser;
/*    */   private final int maxPoolSize;
/*    */   private final ArrayDeque<E> queue;
/*    */   
/*    */   public PooledObjects(Supplier<E> creator, int maxPoolSize) {
/* 41 */     this(creator, maxPoolSize, null);
/*    */   }
/*    */   public PooledObjects(Supplier<E> creator, int maxPoolSize, Consumer<E> releaser) {
/* 44 */     if (creator == null) {
/* 45 */       throw new NullPointerException("Creator must not be null");
/*    */     }
/* 47 */     if (maxPoolSize <= 0) {
/* 48 */       throw new IllegalArgumentException("Max pool size must be greater-than 0");
/*    */     }
/*    */     
/* 51 */     this.queue = new ArrayDeque<>(maxPoolSize);
/* 52 */     this.maxPoolSize = maxPoolSize;
/* 53 */     this.creator = creator;
/* 54 */     this.releaser = releaser;
/*    */   }
/*    */   
/*    */   public AutoReleased acquireCleaner(Object holder) {
/* 58 */     return acquireCleaner(holder, this::release);
/*    */   }
/*    */   
/*    */   public AutoReleased acquireCleaner(Object holder, Consumer<E> releaser) {
/* 62 */     E resource = acquire();
/* 63 */     Runnable cleaner = MCUtil.registerCleaner(holder, resource, releaser);
/* 64 */     return new AutoReleased(resource, cleaner);
/*    */   }
/*    */   
/*    */   public final E acquire() {
/*    */     E value;
/* 69 */     synchronized (this.queue) {
/* 70 */       value = this.queue.pollLast();
/*    */     } 
/* 72 */     return (value != null) ? value : this.creator.get();
/*    */   }
/*    */   
/*    */   public final void release(E value) {
/* 76 */     if (this.releaser != null) {
/* 77 */       this.releaser.accept(value);
/*    */     }
/* 79 */     synchronized (this.queue) {
/* 80 */       if (this.queue.size() < this.maxPoolSize)
/* 81 */         this.queue.addLast(value); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\pape\\util\pooled\PooledObjects.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */