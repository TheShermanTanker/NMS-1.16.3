/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ public class BlockWoodButton
/*    */   extends BlockButtonAbstract
/*    */ {
/*    */   protected BlockWoodButton(BlockBase.Info var0) {
/*  8 */     super(true, var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect a(boolean var0) {
/* 13 */     return var0 ? SoundEffects.BLOCK_WOODEN_BUTTON_CLICK_ON : SoundEffects.BLOCK_WOODEN_BUTTON_CLICK_OFF;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockWoodButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */