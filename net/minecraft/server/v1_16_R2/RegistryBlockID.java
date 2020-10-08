/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.base.Predicates;
/*    */ import com.google.common.collect.Iterators;
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.IdentityHashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public class RegistryBlockID<T>
/*    */   implements Registry<T> {
/*    */   private int a;
/*    */   private final IdentityHashMap<T, Integer> b;
/*    */   private final List<T> c;
/*    */   
/*    */   public RegistryBlockID() {
/* 18 */     this(512);
/*    */   }
/*    */   
/*    */   public RegistryBlockID(int i) {
/* 22 */     this.c = Lists.newArrayListWithExpectedSize(i);
/* 23 */     this.b = new IdentityHashMap<>(i);
/*    */   }
/*    */   
/*    */   public void a(T t0, int i) {
/* 27 */     this.b.put(t0, Integer.valueOf(i));
/*    */     
/* 29 */     while (this.c.size() <= i) {
/* 30 */       this.c.add(null);
/*    */     }
/*    */     
/* 33 */     this.c.set(i, t0);
/* 34 */     if (this.a <= i) {
/* 35 */       this.a = i + 1;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(T t0) {
/* 41 */     a(t0, this.a);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int a(T t) {
/* 47 */     return getId(t);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getId(T t0) {
/* 52 */     Integer integer = this.b.get(t0);
/*    */     
/* 54 */     return (integer == null) ? -1 : integer.intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public final T fromId(int i) {
/* 60 */     return (i >= 0 && i < this.c.size()) ? this.c.get(i) : null;
/*    */   }
/*    */   
/*    */   public Iterator<T> iterator() {
/* 64 */     return (Iterator<T>)Iterators.filter(this.c.iterator(), Predicates.notNull());
/*    */   }
/*    */   public int size() {
/* 67 */     return a();
/*    */   } public int a() {
/* 69 */     return this.b.size();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RegistryBlockID.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */