/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public enum BlockPropertyWallHeight
/*    */   implements INamable
/*    */ {
/*  6 */   NONE("none"),
/*  7 */   LOW("low"),
/*  8 */   TALL("tall");
/*    */   
/*    */   private final String d;
/*    */ 
/*    */   
/*    */   BlockPropertyWallHeight(String var2) {
/* 14 */     this.d = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 19 */     return getName();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 24 */     return this.d;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockPropertyWallHeight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */