/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.AbstractList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import javax.annotation.Nonnull;
/*    */ import javax.annotation.Nullable;
/*    */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.Validate;
/*    */ 
/*    */ public class NonNullList<E> extends AbstractList<E> {
/*    */   private final List<E> a;
/*    */   
/*    */   public static <E> NonNullList<E> a() {
/* 14 */     return new NonNullList<>();
/*    */   }
/*    */   private final E b;
/*    */   
/*    */   public static <E> NonNullList<E> a(int var0, E var1) {
/* 19 */     Validate.notNull(var1);
/*    */     
/* 21 */     Object[] var2 = new Object[var0];
/* 22 */     Arrays.fill(var2, var1);
/* 23 */     return new NonNullList<>(Arrays.asList((E[])var2), var1);
/*    */   }
/*    */   
/*    */   @SafeVarargs
/*    */   public static <E> NonNullList<E> a(E var0, E... var1) {
/* 28 */     return new NonNullList<>(Arrays.asList(var1), var0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected NonNullList() {
/* 35 */     this(Lists.newArrayList(), null);
/*    */   }
/*    */   
/*    */   protected NonNullList(List<E> var0, @Nullable E var1) {
/* 39 */     this.a = var0;
/* 40 */     this.b = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nonnull
/*    */   public E get(int var0) {
/* 46 */     return this.a.get(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public E set(int var0, E var1) {
/* 51 */     Validate.notNull(var1);
/*    */     
/* 53 */     return this.a.set(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void add(int var0, E var1) {
/* 58 */     Validate.notNull(var1);
/*    */     
/* 60 */     this.a.add(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public E remove(int var0) {
/* 65 */     return this.a.remove(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public int size() {
/* 70 */     return this.a.size();
/*    */   }
/*    */ 
/*    */   
/*    */   public void clear() {
/* 75 */     if (this.b == null) {
/* 76 */       super.clear();
/*    */     } else {
/* 78 */       for (int var0 = 0; var0 < size(); var0++)
/* 79 */         set(var0, this.b); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NonNullList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */