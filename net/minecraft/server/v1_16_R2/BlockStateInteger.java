/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import com.google.common.collect.Sets;
/*    */ import java.util.Collection;
/*    */ import java.util.Optional;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class BlockStateInteger
/*    */   extends IBlockState<Integer>
/*    */ {
/*    */   private final ImmutableSet<Integer> a;
/*    */   public final int min;
/*    */   public final int max;
/*    */   
/*    */   protected BlockStateInteger(String s, int i, int j) {
/* 17 */     super(s, Integer.class);
/* 18 */     this.min = i;
/* 19 */     this.max = j;
/*    */     
/* 21 */     if (i < 0)
/* 22 */       throw new IllegalArgumentException("Min value of " + s + " must be 0 or greater"); 
/* 23 */     if (j <= i) {
/* 24 */       throw new IllegalArgumentException("Max value of " + s + " must be greater than min (" + i + ")");
/*    */     }
/* 26 */     Set<Integer> set = Sets.newHashSet();
/*    */     
/* 28 */     for (int k = i; k <= j; k++) {
/* 29 */       set.add(Integer.valueOf(k));
/*    */     }
/*    */     
/* 32 */     this.a = ImmutableSet.copyOf(set);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Collection<Integer> getValues() {
/* 38 */     return (Collection<Integer>)this.a;
/*    */   }
/*    */   
/*    */   public boolean equals_unused(Object object) {
/* 42 */     if (this == object)
/* 43 */       return true; 
/* 44 */     if (object instanceof BlockStateInteger && equals(object)) {
/* 45 */       BlockStateInteger blockstateinteger = (BlockStateInteger)object;
/*    */       
/* 47 */       return this.a.equals(blockstateinteger.a);
/*    */     } 
/* 49 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int b() {
/* 55 */     return 31 * super.b() + this.a.hashCode();
/*    */   }
/*    */   
/*    */   public static BlockStateInteger of(String s, int i, int j) {
/* 59 */     return new BlockStateInteger(s, i, j);
/*    */   }
/*    */ 
/*    */   
/*    */   public Optional<Integer> b(String s) {
/*    */     try {
/* 65 */       Integer integer = Integer.valueOf(s);
/*    */       
/* 67 */       return this.a.contains(integer) ? Optional.<Integer>of(integer) : Optional.<Integer>empty();
/* 68 */     } catch (NumberFormatException numberformatexception) {
/* 69 */       return Optional.empty();
/*    */     } 
/*    */   }
/*    */   
/*    */   public String a(Integer integer) {
/* 74 */     return integer.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockStateInteger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */