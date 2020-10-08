/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.annotations.VisibleForTesting;
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TagSet<T>
/*    */   implements Tag<T>
/*    */ {
/*    */   private final ImmutableList<T> b;
/*    */   private final Set<T> c;
/*    */   @VisibleForTesting
/*    */   protected final Class<?> a;
/*    */   
/*    */   protected TagSet(Set<T> var0, Class<?> var1) {
/* 22 */     this.a = var1;
/* 23 */     this.c = var0;
/* 24 */     this.b = ImmutableList.copyOf(var0);
/*    */   }
/*    */   
/*    */   public static <T> TagSet<T> a() {
/* 28 */     return new TagSet<>((Set<T>)ImmutableSet.of(), Void.class);
/*    */   }
/*    */   
/*    */   public static <T> TagSet<T> a(Set<T> var0) {
/* 32 */     return new TagSet<>(var0, c(var0));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isTagged(T var0) {
/* 38 */     return (this.a.isInstance(var0) && this.c.contains(var0));
/*    */   }
/*    */ 
/*    */   
/*    */   public List<T> getTagged() {
/* 43 */     return (List<T>)this.b;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static <T> Class<?> c(Set<T> var0) {
/* 54 */     if (var0.isEmpty()) {
/* 55 */       return Void.class;
/*    */     }
/*    */     
/* 58 */     Class<?> var1 = null;
/* 59 */     for (T var3 : var0) {
/* 60 */       if (var1 == null) {
/* 61 */         var1 = var3.getClass(); continue;
/*    */       } 
/* 63 */       var1 = a(var1, var3.getClass());
/*    */     } 
/*    */     
/* 66 */     return var1;
/*    */   }
/*    */   
/*    */   private static Class<?> a(Class<?> var0, Class<?> var1) {
/* 70 */     while (!var0.isAssignableFrom(var1)) {
/* 71 */       var0 = var0.getSuperclass();
/*    */     }
/* 73 */     return var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TagSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */