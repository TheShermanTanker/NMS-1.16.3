/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.doubles.DoubleList;
/*    */ 
/*    */ public class VoxelShapeMergerIdentical implements VoxelShapeMerger {
/*    */   private final DoubleList a;
/*    */   
/*    */   public VoxelShapeMergerIdentical(DoubleList var0) {
/*  9 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(VoxelShapeMerger.a var0) {
/* 14 */     for (int var1 = 0; var1 <= this.a.size(); var1++) {
/* 15 */       if (!var0.merge(var1, var1, var1)) {
/* 16 */         return false;
/*    */       }
/*    */     } 
/* 19 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public DoubleList a() {
/* 24 */     return this.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\VoxelShapeMergerIdentical.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */