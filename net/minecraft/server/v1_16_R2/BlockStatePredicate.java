/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Map;
/*    */ import java.util.function.Predicate;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockStatePredicate
/*    */   implements Predicate<IBlockData>
/*    */ {
/*    */   public static final Predicate<IBlockData> a = var0 -> true;
/*    */   private final BlockStateList<Block, IBlockData> b;
/* 17 */   private final Map<IBlockState<?>, Predicate<Object>> c = Maps.newHashMap();
/*    */   
/*    */   private BlockStatePredicate(BlockStateList<Block, IBlockData> var0) {
/* 20 */     this.b = var0;
/*    */   }
/*    */   
/*    */   public static BlockStatePredicate a(Block var0) {
/* 24 */     return new BlockStatePredicate(var0.getStates());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean test(@Nullable IBlockData var0) {
/* 33 */     if (var0 == null || !var0.getBlock().equals(this.b.getBlock())) {
/* 34 */       return false;
/*    */     }
/*    */     
/* 37 */     if (this.c.isEmpty()) {
/* 38 */       return true;
/*    */     }
/*    */     
/* 41 */     for (Map.Entry<IBlockState<?>, Predicate<Object>> var2 : this.c.entrySet()) {
/* 42 */       if (!a(var0, (IBlockState<Comparable>)var2.getKey(), var2.getValue())) {
/* 43 */         return false;
/*    */       }
/*    */     } 
/*    */     
/* 47 */     return true;
/*    */   }
/*    */   
/*    */   protected <T extends Comparable<T>> boolean a(IBlockData var0, IBlockState<T> var1, Predicate<Object> var2) {
/* 51 */     T var3 = (T)var0.get(var1);
/* 52 */     return var2.test(var3);
/*    */   }
/*    */   
/*    */   public <V extends Comparable<V>> BlockStatePredicate a(IBlockState<V> var0, Predicate<Object> var1) {
/* 56 */     if (!this.b.d().contains(var0)) {
/* 57 */       throw new IllegalArgumentException(this.b + " cannot support property " + var0);
/*    */     }
/* 59 */     this.c.put(var0, var1);
/* 60 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockStatePredicate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */