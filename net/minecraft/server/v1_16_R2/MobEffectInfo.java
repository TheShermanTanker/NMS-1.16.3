/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ public enum MobEffectInfo
/*    */ {
/*  6 */   BENEFICIAL(EnumChatFormat.BLUE),
/*  7 */   HARMFUL(EnumChatFormat.RED),
/*  8 */   NEUTRAL(EnumChatFormat.BLUE);
/*    */   
/*    */   private final EnumChatFormat d;
/*    */   
/*    */   MobEffectInfo(EnumChatFormat var2) {
/* 13 */     this.d = var2;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MobEffectInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */