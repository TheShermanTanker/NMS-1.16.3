/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public enum BlockPropertyHalf
/*    */   implements INamable
/*    */ {
/*  6 */   TOP("top"),
/*  7 */   BOTTOM("bottom");
/*    */   
/*    */   private final String c;
/*    */ 
/*    */   
/*    */   BlockPropertyHalf(String var2) {
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockPropertyHalf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */