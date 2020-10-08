/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockPosition2D
/*    */ {
/*    */   public final int a;
/*    */   public final int b;
/*    */   
/*    */   public BlockPosition2D(int var0, int var1) {
/* 17 */     this.a = var0;
/* 18 */     this.b = var1;
/*    */   }
/*    */   
/*    */   public BlockPosition2D(BlockPosition var0) {
/* 22 */     this.a = var0.getX();
/* 23 */     this.b = var0.getZ();
/*    */   }
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
/*    */   public String toString() {
/* 40 */     return "[" + this.a + ", " + this.b + "]";
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 45 */     int var0 = 1664525 * this.a + 1013904223;
/* 46 */     int var1 = 1664525 * (this.b ^ 0xDEADBEEF) + 1013904223;
/* 47 */     return var0 ^ var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object var0) {
/* 52 */     if (this == var0) {
/* 53 */       return true;
/*    */     }
/*    */     
/* 56 */     if (var0 instanceof BlockPosition2D) {
/* 57 */       BlockPosition2D var1 = (BlockPosition2D)var0;
/* 58 */       return (this.a == var1.a && this.b == var1.b);
/*    */     } 
/*    */     
/* 61 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockPosition2D.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */