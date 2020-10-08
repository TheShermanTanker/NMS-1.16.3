/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum EnumMainHand
/*    */ {
/*  7 */   LEFT(new ChatMessage("options.mainHand.left")),
/*  8 */   RIGHT(new ChatMessage("options.mainHand.right"));
/*    */   
/*    */   private final IChatBaseComponent c;
/*    */ 
/*    */   
/*    */   EnumMainHand(IChatBaseComponent var2) {
/* 14 */     this.c = var2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 26 */     return this.c.getString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnumMainHand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */