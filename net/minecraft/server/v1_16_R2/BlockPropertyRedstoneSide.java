/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public enum BlockPropertyRedstoneSide
/*    */   implements INamable
/*    */ {
/*  6 */   UP("up"),
/*  7 */   SIDE("side"),
/*  8 */   NONE("none");
/*    */   
/*    */   private final String d;
/*    */ 
/*    */   
/*    */   BlockPropertyRedstoneSide(String var2) {
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
/*    */   
/*    */   public boolean b() {
/* 28 */     return (this != NONE);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockPropertyRedstoneSide.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */