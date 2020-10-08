/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ public class SpawnerCreatureProbabilities
/*    */ {
/*    */   static class a
/*    */   {
/*    */     private final BlockPosition a;
/*    */     private final double b;
/*    */     
/*    */     public a(BlockPosition var0, double var1) {
/* 15 */       this.a = var0;
/* 16 */       this.b = var1;
/*    */     }
/*    */     
/*    */     public double a(BlockPosition var0) {
/* 20 */       double var1 = this.a.j(var0);
/* 21 */       if (var1 == 0.0D)
/*    */       {
/* 23 */         return Double.POSITIVE_INFINITY;
/*    */       }
/* 25 */       return this.b / Math.sqrt(var1);
/*    */     }
/*    */   }
/*    */   
/* 29 */   private final List<a> a = Lists.newArrayList();
/*    */   
/*    */   public void a(BlockPosition var0, double var1) {
/* 32 */     if (var1 != 0.0D) {
/* 33 */       this.a.add(new a(var0, var1));
/*    */     }
/*    */   }
/*    */   
/*    */   public double b(BlockPosition var0, double var1) {
/* 38 */     if (var1 == 0.0D) {
/* 39 */       return 0.0D;
/*    */     }
/* 41 */     double var3 = 0.0D;
/* 42 */     for (a var6 : this.a) {
/* 43 */       var3 += var6.a(var0);
/*    */     }
/* 45 */     return var3 * var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\SpawnerCreatureProbabilities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */