/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GameTestHarnessAssertionPosition
/*    */   extends GameTestHarnessAssertion
/*    */ {
/*    */   private final BlockPosition a;
/*    */   private final BlockPosition b;
/*    */   private final long c;
/*    */   
/*    */   public String getMessage() {
/* 21 */     String var0 = "" + this.a.getX() + "," + this.a.getY() + "," + this.a.getZ() + " (relative: " + this.b.getX() + "," + this.b.getY() + "," + this.b.getZ() + ")";
/* 22 */     return super.getMessage() + " at " + var0 + " (t=" + this.c + ")";
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public String a() {
/* 27 */     return super.getMessage() + " here";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public BlockPosition c() {
/* 37 */     return this.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GameTestHarnessAssertionPosition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */