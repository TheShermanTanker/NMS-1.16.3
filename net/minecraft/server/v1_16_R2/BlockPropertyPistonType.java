/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public enum BlockPropertyPistonType
/*    */   implements INamable
/*    */ {
/*  6 */   DEFAULT("normal"),
/*  7 */   STICKY("sticky");
/*    */   
/*    */   private final String c;
/*    */   
/*    */   BlockPropertyPistonType(String var2) {
/* 12 */     this.c = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 17 */     return this.c;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 22 */     return this.c;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockPropertyPistonType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */