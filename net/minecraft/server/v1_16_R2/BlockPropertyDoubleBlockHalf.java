/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public enum BlockPropertyDoubleBlockHalf
/*    */   implements INamable
/*    */ {
/*  6 */   UPPER,
/*  7 */   LOWER;
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 12 */     return getName();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 17 */     return (this == UPPER) ? "upper" : "lower";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockPropertyDoubleBlockHalf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */