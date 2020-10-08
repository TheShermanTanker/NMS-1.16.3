/*    */ package co.aikar.util;
/*    */ 
/*    */ import com.google.common.base.Function;
/*    */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LoadingIntMap<V>
/*    */   extends Int2ObjectOpenHashMap<V>
/*    */ {
/*    */   private final Function<Integer, V> loader;
/*    */   
/*    */   public LoadingIntMap(@NotNull Function<Integer, V> loader) {
/* 34 */     this.loader = loader;
/*    */   }
/*    */   
/*    */   public LoadingIntMap(int expectedSize, @NotNull Function<Integer, V> loader) {
/* 38 */     super(expectedSize);
/* 39 */     this.loader = loader;
/*    */   }
/*    */   
/*    */   public LoadingIntMap(int expectedSize, float loadFactor, @NotNull Function<Integer, V> loader) {
/* 43 */     super(expectedSize, loadFactor);
/* 44 */     this.loader = loader;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public V get(int key) {
/* 51 */     V res = (V)super.get(key);
/* 52 */     if (res == null) {
/* 53 */       res = (V)this.loader.apply(Integer.valueOf(key));
/* 54 */       if (res != null) {
/* 55 */         put(key, res);
/*    */       }
/*    */     } 
/* 58 */     return res;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static abstract class Feeder<T>
/*    */     implements Function<T, T>
/*    */   {
/*    */     @Nullable
/*    */     public T apply(@Nullable Object input) {
/* 70 */       return apply();
/*    */     }
/*    */     
/*    */     @Nullable
/*    */     public abstract T apply();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\co\aika\\util\LoadingIntMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */