/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public enum EnumInteractionResult {
/*  4 */   SUCCESS,
/*  5 */   CONSUME,
/*  6 */   PASS,
/*  7 */   FAIL;
/*    */   
/*    */   public boolean a() {
/* 10 */     return (this == SUCCESS || this == CONSUME);
/*    */   }
/*    */   
/*    */   public boolean b() {
/* 14 */     return (this == SUCCESS);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static EnumInteractionResult a(boolean var0) {
/* 22 */     return var0 ? SUCCESS : CONSUME;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnumInteractionResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */