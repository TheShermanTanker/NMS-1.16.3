/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public enum BlockPropertyBedPart
/*    */   implements INamable
/*    */ {
/*  6 */   HEAD("head"),
/*  7 */   FOOT("foot");
/*    */   
/*    */   private final String c;
/*    */ 
/*    */   
/*    */   BlockPropertyBedPart(String var2) {
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockPropertyBedPart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */