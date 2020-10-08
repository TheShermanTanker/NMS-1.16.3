/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public enum BlockPropertyAttachPosition
/*    */   implements INamable
/*    */ {
/*  6 */   FLOOR("floor"),
/*  7 */   WALL("wall"),
/*  8 */   CEILING("ceiling");
/*    */   
/*    */   private final String d;
/*    */ 
/*    */   
/*    */   BlockPropertyAttachPosition(String var2) {
/* 14 */     this.d = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 19 */     return this.d;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockPropertyAttachPosition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */