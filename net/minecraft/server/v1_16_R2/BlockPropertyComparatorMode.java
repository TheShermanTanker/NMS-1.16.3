/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public enum BlockPropertyComparatorMode
/*    */   implements INamable
/*    */ {
/*  6 */   COMPARE("compare"),
/*  7 */   SUBTRACT("subtract");
/*    */   
/*    */   private final String c;
/*    */ 
/*    */   
/*    */   BlockPropertyComparatorMode(String var2) {
/* 13 */     this.c = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 18 */     return this.c;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 23 */     return this.c;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockPropertyComparatorMode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */