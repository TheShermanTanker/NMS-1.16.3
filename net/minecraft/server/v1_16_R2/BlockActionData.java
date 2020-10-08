/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockActionData
/*    */ {
/*    */   private final BlockPosition a;
/*    */   private final Block b;
/*    */   private final int c;
/*    */   private final int d;
/*    */   
/*    */   public BlockActionData(BlockPosition var0, Block var1, int var2, int var3) {
/* 13 */     this.a = var0;
/* 14 */     this.b = var1;
/* 15 */     this.c = var2;
/* 16 */     this.d = var3;
/*    */   }
/*    */   
/*    */   public BlockPosition a() {
/* 20 */     return this.a;
/*    */   }
/*    */   
/*    */   public Block b() {
/* 24 */     return this.b;
/*    */   }
/*    */   
/*    */   public int c() {
/* 28 */     return this.c;
/*    */   }
/*    */   
/*    */   public int d() {
/* 32 */     return this.d;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object var0) {
/* 37 */     if (var0 instanceof BlockActionData) {
/* 38 */       BlockActionData var1 = (BlockActionData)var0;
/* 39 */       return (this.a.equals(var1.a) && this.c == var1.c && this.d == var1.d && this.b == var1.b);
/*    */     } 
/* 41 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 46 */     int var0 = this.a.hashCode();
/* 47 */     var0 = 31 * var0 + this.b.hashCode();
/* 48 */     var0 = 31 * var0 + this.c;
/* 49 */     var0 = 31 * var0 + this.d;
/* 50 */     return var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 55 */     return "TE(" + this.a + ")," + this.c + "," + this.d + "," + this.b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockActionData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */