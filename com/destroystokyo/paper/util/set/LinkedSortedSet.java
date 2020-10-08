/*     */ package com.destroystokyo.paper.util.set;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public final class LinkedSortedSet<E>
/*     */   implements Iterable<E>
/*     */ {
/*     */   public final Comparator<? super E> comparator;
/*     */   protected Link<E> head;
/*     */   protected Link<E> tail;
/*     */   
/*     */   public LinkedSortedSet() {
/*  15 */     this(Comparator.naturalOrder());
/*     */   }
/*     */   
/*     */   public LinkedSortedSet(Comparator<? super E> comparator) {
/*  19 */     this.comparator = comparator;
/*     */   }
/*     */   
/*     */   public void clear() {
/*  23 */     this.head = this.tail = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator<E> iterator() {
/*  28 */     return new Iterator<E>()
/*     */       {
/*  30 */         LinkedSortedSet.Link<E> next = LinkedSortedSet.this.head;
/*     */ 
/*     */         
/*     */         public boolean hasNext() {
/*  34 */           return (this.next != null);
/*     */         }
/*     */ 
/*     */         
/*     */         public E next() {
/*  39 */           LinkedSortedSet.Link<E> next = this.next;
/*  40 */           if (next == null) {
/*  41 */             throw new NoSuchElementException();
/*     */           }
/*  43 */           this.next = next.next;
/*  44 */           return next.element;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public boolean addLast(E element) {
/*  50 */     Comparator<? super E> comparator = this.comparator;
/*     */     
/*  52 */     Link<E> curr = this.tail;
/*  53 */     if (curr != null) {
/*     */       int compare;
/*     */       
/*  56 */       while ((compare = comparator.compare(element, curr.element)) < 0) {
/*  57 */         Link<E> prev = curr;
/*  58 */         curr = curr.prev;
/*  59 */         if (curr != null) {
/*     */           continue;
/*     */         }
/*  62 */         this.head = prev.prev = new Link<>(element, null, prev);
/*  63 */         return true;
/*     */       } 
/*     */       
/*  66 */       if (compare != 0) {
/*     */         
/*  68 */         Link<E> next = curr.next;
/*  69 */         Link<E> insert = new Link<>(element, curr, next);
/*  70 */         curr.next = insert;
/*     */         
/*  72 */         if (next == null) {
/*  73 */           this.tail = insert;
/*     */         } else {
/*  75 */           next.prev = insert;
/*     */         } 
/*  77 */         return true;
/*     */       } 
/*     */       
/*  80 */       return false;
/*     */     } 
/*  82 */     this.head = this.tail = new Link<>(element);
/*  83 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addFirst(E element) {
/*  88 */     Comparator<? super E> comparator = this.comparator;
/*     */     
/*  90 */     Link<E> curr = this.head;
/*  91 */     if (curr != null) {
/*     */       int compare;
/*     */       
/*  94 */       while ((compare = comparator.compare(element, curr.element)) > 0) {
/*  95 */         Link<E> prev = curr;
/*  96 */         curr = curr.next;
/*  97 */         if (curr != null) {
/*     */           continue;
/*     */         }
/* 100 */         this.tail = prev.next = new Link<>(element, prev, null);
/* 101 */         return true;
/*     */       } 
/*     */       
/* 104 */       if (compare != 0) {
/*     */         
/* 106 */         Link<E> prev = curr.prev;
/* 107 */         Link<E> insert = new Link<>(element, prev, curr);
/* 108 */         curr.prev = insert;
/*     */         
/* 110 */         if (prev == null) {
/* 111 */           this.head = insert;
/*     */         } else {
/* 113 */           prev.next = insert;
/*     */         } 
/* 115 */         return true;
/*     */       } 
/*     */       
/* 118 */       return false;
/*     */     } 
/* 120 */     this.head = this.tail = new Link<>(element);
/* 121 */     return true;
/*     */   }
/*     */   
/*     */   protected static final class Link<E>
/*     */   {
/*     */     public E element;
/*     */     public Link<E> prev;
/*     */     public Link<E> next;
/*     */     
/*     */     public Link() {}
/*     */     
/*     */     public Link(E element) {
/* 133 */       this.element = element;
/*     */     }
/*     */     
/*     */     public Link(E element, Link<E> prev, Link<E> next) {
/* 137 */       this.element = element;
/* 138 */       this.prev = prev;
/* 139 */       this.next = next;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\pape\\util\set\LinkedSortedSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */