/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.math.IntMath;
/*    */ import it.unimi.dsi.fastutil.doubles.DoubleList;
/*    */ 
/*    */ public final class VoxelShapeCubeMerger implements VoxelShapeMerger {
/*    */   private final VoxelShapeCubePoint a;
/*    */   private final int b;
/*    */   private final int c;
/*    */   private final int d;
/*    */   
/*    */   VoxelShapeCubeMerger(int var0, int var1) {
/* 13 */     this.a = new VoxelShapeCubePoint((int)VoxelShapes.a(var0, var1));
/* 14 */     this.b = var0;
/* 15 */     this.c = var1;
/* 16 */     this.d = IntMath.gcd(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(VoxelShapeMerger.a var0) {
/* 21 */     int var1 = this.b / this.d;
/* 22 */     int var2 = this.c / this.d;
/* 23 */     for (int var3 = 0; var3 <= this.a.size(); var3++) {
/* 24 */       if (!var0.merge(var3 / var2, var3 / var1, var3)) {
/* 25 */         return false;
/*    */       }
/*    */     } 
/* 28 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public DoubleList a() {
/* 33 */     return (DoubleList)this.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\VoxelShapeCubeMerger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */