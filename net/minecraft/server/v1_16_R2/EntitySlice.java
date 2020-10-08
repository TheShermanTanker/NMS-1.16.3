/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.google.common.collect.Iterators;
/*    */ import com.google.common.collect.Lists;
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.AbstractCollection;
/*    */ import java.util.Collection;
/*    */ import java.util.Collections;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.stream.Collectors;
/*    */ 
/*    */ public class EntitySlice<T>
/*    */   extends AbstractCollection<T> {
/* 17 */   private final Map<Class<?>, List<T>> a = Maps.newHashMap();
/*    */   
/*    */   private final Class<T> b;
/* 20 */   private final List<T> c = Lists.newArrayList();
/*    */   
/*    */   public EntitySlice(Class<T> var0) {
/* 23 */     this.b = var0;
/* 24 */     this.a.put(var0, this.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean add(T var0) {
/* 29 */     boolean var1 = false;
/* 30 */     for (Map.Entry<Class<?>, List<T>> var3 : this.a.entrySet()) {
/* 31 */       if (((Class)var3.getKey()).isInstance(var0)) {
/* 32 */         var1 |= ((List<T>)var3.getValue()).add(var0);
/*    */       }
/*    */     } 
/* 35 */     return var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean remove(Object var0) {
/* 40 */     boolean var1 = false;
/* 41 */     for (Map.Entry<Class<?>, List<T>> var3 : this.a.entrySet()) {
/* 42 */       if (((Class)var3.getKey()).isInstance(var0)) {
/* 43 */         List<T> var4 = var3.getValue();
/* 44 */         var1 |= var4.remove(var0);
/*    */       } 
/*    */     } 
/* 47 */     return var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean contains(Object var0) {
/* 52 */     return a(var0.getClass()).contains(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public <S> Collection<S> a(Class<S> var0) {
/* 57 */     if (!this.b.isAssignableFrom(var0)) {
/* 58 */       throw new IllegalArgumentException("Don't know how to search for " + var0);
/*    */     }
/* 60 */     List<T> var1 = this.a.computeIfAbsent(var0, var0 -> (List)this.c.stream().filter(var0::isInstance).collect(Collectors.toList()));
/* 61 */     return Collections.unmodifiableCollection(var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public Iterator<T> iterator() {
/* 66 */     if (this.c.isEmpty()) {
/* 67 */       return Collections.emptyIterator();
/*    */     }
/* 69 */     return (Iterator<T>)Iterators.unmodifiableIterator(this.c.iterator());
/*    */   }
/*    */   
/*    */   public List<T> a() {
/* 73 */     return (List<T>)ImmutableList.copyOf(this.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public int size() {
/* 78 */     return this.c.size();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntitySlice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */