/*    */ package co.aikar.util;
/*    */ 
/*    */ import com.google.common.collect.ForwardingMap;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class Counter<T>
/*    */   extends ForwardingMap<T, Long> {
/* 11 */   private final Map<T, Long> counts = new HashMap<>();
/*    */   
/*    */   public long decrement(@Nullable T key) {
/* 14 */     return increment(key, -1L);
/*    */   }
/*    */   public long increment(@Nullable T key) {
/* 17 */     return increment(key, 1L);
/*    */   }
/*    */   public long decrement(@Nullable T key, long amount) {
/* 20 */     return decrement(key, -amount);
/*    */   }
/*    */   public long increment(@Nullable T key, long amount) {
/* 23 */     Long count = Long.valueOf(getCount(key));
/* 24 */     count = Long.valueOf(count.longValue() + amount);
/* 25 */     this.counts.put(key, count);
/* 26 */     return count.longValue();
/*    */   }
/*    */   
/*    */   public long getCount(@Nullable T key) {
/* 30 */     return ((Long)this.counts.getOrDefault(key, Long.valueOf(0L))).longValue();
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   protected Map<T, Long> delegate() {
/* 36 */     return this.counts;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\co\aika\\util\Counter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */