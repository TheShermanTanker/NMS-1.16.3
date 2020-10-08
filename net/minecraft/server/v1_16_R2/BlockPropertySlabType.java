/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public enum BlockPropertySlabType
/*    */   implements INamable
/*    */ {
/*  6 */   TOP("top"),
/*  7 */   BOTTOM("bottom"),
/*  8 */   DOUBLE("double");
/*    */   
/*    */   private final String d;
/*    */ 
/*    */   
/*    */   BlockPropertySlabType(String var2) {
/* 14 */     this.d = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 19 */     return this.d;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 24 */     return this.d;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockPropertySlabType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */