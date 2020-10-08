/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ public abstract class DispenseBehaviorMaybe
/*    */   extends DispenseBehaviorItem
/*    */ {
/*    */   private boolean dispensed = true;
/*    */   
/*    */   public boolean a() {
/* 10 */     return this.dispensed;
/*    */   }
/*    */   
/*    */   public void a(boolean var0) {
/* 14 */     this.dispensed = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(ISourceBlock var0) {
/* 19 */     var0.getWorld().triggerEffect(a() ? 1000 : 1001, var0.getBlockPosition(), 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DispenseBehaviorMaybe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */