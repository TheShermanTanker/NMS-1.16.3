/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class ControllerJump
/*    */ {
/*    */   private final EntityInsentient b;
/*    */   protected boolean a;
/*    */   
/*    */   public ControllerJump(EntityInsentient entityinsentient) {
/*  9 */     this.b = entityinsentient;
/*    */   }
/*    */   
/*    */   public void jump() {
/* 13 */     this.a = true;
/*    */   }
/*    */   public final void jumpIfSet() {
/* 16 */     b();
/*    */   } public void b() {
/* 18 */     this.b.setJumping(this.a);
/* 19 */     this.a = false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ControllerJump.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */