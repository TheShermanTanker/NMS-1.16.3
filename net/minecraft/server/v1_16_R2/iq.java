/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import java.util.Comparator;
/*    */ import java.util.List;
/*    */ import java.util.stream.Collectors;
/*    */ 
/*    */ 
/*    */ public final class iq
/*    */ {
/* 11 */   private static final iq a = new iq((List<IBlockState.a<?>>)ImmutableList.of()); private static final Comparator<IBlockState.a<?>> b; static {
/* 12 */     b = Comparator.comparing(var0 -> var0.a().getName());
/*    */   }
/*    */   private final List<IBlockState.a<?>> c;
/*    */   
/*    */   public iq a(IBlockState.a<?> var0) {
/* 17 */     return new iq((List<IBlockState.a<?>>)ImmutableList.builder().addAll(this.c).add(var0).build());
/*    */   }
/*    */   
/*    */   public iq a(iq var0) {
/* 21 */     return new iq((List<IBlockState.a<?>>)ImmutableList.builder().addAll(this.c).addAll(var0.c).build());
/*    */   }
/*    */   
/*    */   private iq(List<IBlockState.a<?>> var0) {
/* 25 */     this.c = var0;
/*    */   }
/*    */   
/*    */   public static iq a() {
/* 29 */     return a;
/*    */   }
/*    */   
/*    */   public static iq a(IBlockState.a<?>... var0) {
/* 33 */     return new iq((List<IBlockState.a<?>>)ImmutableList.copyOf((Object[])var0));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object var0) {
/* 38 */     return (this == var0 || (var0 instanceof iq && this.c.equals(((iq)var0).c)));
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 43 */     return this.c.hashCode();
/*    */   }
/*    */   
/*    */   public String b() {
/* 47 */     return this.c.stream().sorted(b).map(IBlockState.a::toString).collect(Collectors.joining(","));
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 52 */     return b();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\iq.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */