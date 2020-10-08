/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public enum BlockPropertyStairsShape
/*    */   implements INamable
/*    */ {
/*  6 */   STRAIGHT("straight"),
/*  7 */   INNER_LEFT("inner_left"),
/*  8 */   INNER_RIGHT("inner_right"),
/*  9 */   OUTER_LEFT("outer_left"),
/* 10 */   OUTER_RIGHT("outer_right");
/*    */   
/*    */   private final String f;
/*    */ 
/*    */   
/*    */   BlockPropertyStairsShape(String var2) {
/* 16 */     this.f = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 21 */     return this.f;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 26 */     return this.f;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockPropertyStairsShape.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */