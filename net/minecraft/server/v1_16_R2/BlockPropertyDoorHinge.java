/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public enum BlockPropertyDoorHinge
/*    */   implements INamable
/*    */ {
/*  6 */   LEFT,
/*  7 */   RIGHT;
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 12 */     return getName();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 17 */     return (this == LEFT) ? "left" : "right";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockPropertyDoorHinge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */