/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public enum BlockPropertyBambooSize
/*    */   implements INamable
/*    */ {
/*  6 */   NONE("none"),
/*  7 */   SMALL("small"),
/*  8 */   LARGE("large");
/*    */   
/*    */   private final String d;
/*    */ 
/*    */   
/*    */   BlockPropertyBambooSize(String var2) {
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockPropertyBambooSize.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */