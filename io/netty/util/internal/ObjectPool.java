/*    */ package io.netty.util.internal;
/*    */ 
/*    */ import io.netty.util.Recycler;
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
/*    */ public abstract class ObjectPool<T>
/*    */ {
/*    */   public abstract T get();
/*    */   
/*    */   public static <T> ObjectPool<T> newPool(ObjectCreator<T> creator) {
/* 67 */     return new RecyclerObjectPool<T>(ObjectUtil.<ObjectCreator<T>>checkNotNull(creator, "creator"));
/*    */   }
/*    */ 
/*    */   
/*    */   private static final class RecyclerObjectPool<T>
/*    */     extends ObjectPool<T>
/*    */   {
/* 74 */     private final Recycler<T> recycler = new Recycler<T>()
/*    */       {
/*    */         protected T newObject(Recycler.Handle<T> handle) {
/* 77 */           return creator.newObject((ObjectPool.Handle<T>)handle);
/*    */         }
/*    */       };
/*    */     
/*    */     RecyclerObjectPool(final ObjectPool.ObjectCreator<T> creator) {}
/*    */     
/*    */     public T get() {
/* 84 */       return (T)this.recycler.get();
/*    */     }
/*    */   }
/*    */   
/*    */   public static interface ObjectCreator<T> {
/*    */     T newObject(ObjectPool.Handle<T> param1Handle);
/*    */   }
/*    */   
/*    */   public static interface Handle<T> {
/*    */     void recycle(T param1T);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\nett\\util\internal\ObjectPool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */