/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public enum BlockPropertyBellAttach
/*    */   implements INamable
/*    */ {
/*  6 */   FLOOR("floor"),
/*  7 */   CEILING("ceiling"),
/*  8 */   SINGLE_WALL("single_wall"),
/*  9 */   DOUBLE_WALL("double_wall");
/*    */   
/*    */   private final String e;
/*    */ 
/*    */   
/*    */   BlockPropertyBellAttach(String var2) {
/* 15 */     this.e = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 20 */     return this.e;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockPropertyBellAttach.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */