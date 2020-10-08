/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.IdentityHashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class StatisticWrapper<T>
/*    */   implements Iterable<Statistic<T>> {
/*    */   private final IRegistry<T> a;
/* 10 */   private final Map<T, Statistic<T>> b = new IdentityHashMap<>();
/*    */   
/*    */   public StatisticWrapper(IRegistry<T> iregistry) {
/* 13 */     this.a = iregistry;
/*    */   }
/*    */   
/*    */   public Statistic<T> a(T t0, Counter counter) {
/* 17 */     return this.b.computeIfAbsent(t0, object -> new Statistic<>(this, (T)object, counter));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IRegistry<T> getRegistry() {
/* 23 */     return this.a;
/*    */   }
/*    */   
/*    */   public Iterator<Statistic<T>> iterator() {
/* 27 */     return this.b.values().iterator();
/*    */   }
/*    */   public final Statistic<T> get(T t) {
/* 30 */     return b(t);
/*    */   } public Statistic<T> b(T t0) {
/* 32 */     return a(t0, Counter.DEFAULT);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\StatisticWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */