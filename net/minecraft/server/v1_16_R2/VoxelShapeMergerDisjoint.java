/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.doubles.AbstractDoubleList;
/*    */ import it.unimi.dsi.fastutil.doubles.DoubleList;
/*    */ 
/*    */ public class VoxelShapeMergerDisjoint extends AbstractDoubleList implements VoxelShapeMerger {
/*    */   private final DoubleList a;
/*    */   private final DoubleList b;
/*    */   private final boolean c;
/*    */   
/*    */   public VoxelShapeMergerDisjoint(DoubleList var0, DoubleList var1, boolean var2) {
/* 12 */     this.a = var0;
/* 13 */     this.b = var1;
/* 14 */     this.c = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   public int size() {
/* 19 */     return this.a.size() + this.b.size();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(VoxelShapeMerger.a var0) {
/* 24 */     if (this.c) {
/* 25 */       return b((var1, var2, var3) -> var0.merge(var2, var1, var3));
/*    */     }
/* 27 */     return b(var0);
/*    */   }
/*    */   
/*    */   private boolean b(VoxelShapeMerger.a var0) {
/* 31 */     int var1 = this.a.size() - 1; int var2;
/* 32 */     for (var2 = 0; var2 < var1; var2++) {
/* 33 */       if (!var0.merge(var2, -1, var2)) {
/* 34 */         return false;
/*    */       }
/*    */     } 
/*    */     
/* 38 */     if (!var0.merge(var1, -1, var1)) {
/* 39 */       return false;
/*    */     }
/*    */     
/* 42 */     for (var2 = 0; var2 < this.b.size(); var2++) {
/* 43 */       if (!var0.merge(var1, var2, var1 + 1 + var2)) {
/* 44 */         return false;
/*    */       }
/*    */     } 
/* 47 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getDouble(int var0) {
/* 52 */     if (var0 < this.a.size()) {
/* 53 */       return this.a.getDouble(var0);
/*    */     }
/* 55 */     return this.b.getDouble(var0 - this.a.size());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public DoubleList a() {
/* 61 */     return (DoubleList)this;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\VoxelShapeMergerDisjoint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */