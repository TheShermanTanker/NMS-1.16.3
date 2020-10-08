/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum BlockPropertyStructureMode
/*    */   implements INamable
/*    */ {
/*  8 */   SAVE("save"),
/*  9 */   LOAD("load"),
/* 10 */   CORNER("corner"),
/* 11 */   DATA("data");
/*    */   
/*    */   private final String e;
/*    */   
/*    */   private final IChatBaseComponent f;
/*    */   
/*    */   BlockPropertyStructureMode(String var2) {
/* 18 */     this.e = var2;
/* 19 */     this.f = new ChatMessage("structure_block.mode_info." + var2);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 24 */     return this.e;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockPropertyStructureMode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */