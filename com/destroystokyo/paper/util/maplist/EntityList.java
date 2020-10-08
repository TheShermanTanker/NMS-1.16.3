/*     */ package com.destroystokyo.paper.util.maplist;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import net.minecraft.server.v1_16_R2.Entity;
/*     */ 
/*     */ public final class EntityList
/*     */   implements Iterable<Entity>
/*     */ {
/*     */   protected final Int2IntOpenHashMap entityToIndex;
/*     */   
/*     */   public EntityList() {
/*  15 */     this.entityToIndex = new Int2IntOpenHashMap(2, 0.8F);
/*     */     
/*  17 */     this.entityToIndex.defaultReturnValue(-2147483648);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  22 */     this.entities = EMPTY_LIST;
/*     */   }
/*     */   protected static final Entity[] EMPTY_LIST = new Entity[0];
/*     */   public int size() {
/*  26 */     return this.count;
/*     */   }
/*     */   protected Entity[] entities; protected int count;
/*     */   public boolean contains(Entity entity) {
/*  30 */     return this.entityToIndex.containsKey(entity.getId());
/*     */   }
/*     */   
/*     */   public boolean remove(Entity entity) {
/*  34 */     int index = this.entityToIndex.remove(entity.getId());
/*  35 */     if (index == Integer.MIN_VALUE) {
/*  36 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  40 */     int endIndex = --this.count;
/*  41 */     Entity end = this.entities[endIndex];
/*  42 */     if (index != endIndex)
/*     */     {
/*  44 */       this.entityToIndex.put(end.getId(), index);
/*     */     }
/*  46 */     this.entities[index] = end;
/*  47 */     this.entities[endIndex] = null;
/*     */     
/*  49 */     return true;
/*     */   }
/*     */   
/*     */   public boolean add(Entity entity) {
/*  53 */     int count = this.count;
/*  54 */     int currIndex = this.entityToIndex.putIfAbsent(entity.getId(), count);
/*     */     
/*  56 */     if (currIndex != Integer.MIN_VALUE) {
/*  57 */       return false;
/*     */     }
/*     */     
/*  60 */     Entity[] list = this.entities;
/*     */     
/*  62 */     if (list.length == count)
/*     */     {
/*  64 */       list = this.entities = Arrays.copyOf(list, (int)Math.max(4L, count * 2L));
/*     */     }
/*     */     
/*  67 */     list[count] = entity;
/*  68 */     this.count = count + 1;
/*     */     
/*  70 */     return true;
/*     */   }
/*     */   
/*     */   public Entity getChecked(int index) {
/*  74 */     if (index < 0 || index >= this.count) {
/*  75 */       throw new IndexOutOfBoundsException("Index: " + index + " is out of bounds, size: " + this.count);
/*     */     }
/*  77 */     return this.entities[index];
/*     */   }
/*     */   
/*     */   public Entity getUnchecked(int index) {
/*  81 */     return this.entities[index];
/*     */   }
/*     */   
/*     */   public Entity[] getRawData() {
/*  85 */     return this.entities;
/*     */   }
/*     */   
/*     */   public void clear() {
/*  89 */     this.entityToIndex.clear();
/*  90 */     Arrays.fill((Object[])this.entities, 0, this.count, (Object)null);
/*  91 */     this.count = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator<Entity> iterator() {
/*  96 */     return new Iterator<Entity>()
/*     */       {
/*     */         Entity lastRet;
/*     */         
/*     */         int current;
/*     */         
/*     */         public boolean hasNext() {
/* 103 */           return (this.current < EntityList.this.count);
/*     */         }
/*     */ 
/*     */         
/*     */         public Entity next() {
/* 108 */           if (this.current >= EntityList.this.count) {
/* 109 */             throw new NoSuchElementException();
/*     */           }
/* 111 */           return this.lastRet = EntityList.this.entities[this.current++];
/*     */         }
/*     */ 
/*     */         
/*     */         public void remove() {
/* 116 */           Entity lastRet = this.lastRet;
/*     */           
/* 118 */           if (lastRet == null) {
/* 119 */             throw new IllegalStateException();
/*     */           }
/* 121 */           this.lastRet = null;
/*     */           
/* 123 */           EntityList.this.remove(lastRet);
/* 124 */           this.current--;
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\pape\\util\maplist\EntityList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */