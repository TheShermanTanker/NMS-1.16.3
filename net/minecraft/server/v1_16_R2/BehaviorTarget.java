/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorTarget
/*    */   implements BehaviorPosition
/*    */ {
/*    */   private final BlockPosition a;
/*    */   private final Vec3D b;
/*    */   
/*    */   public BehaviorTarget(BlockPosition var0) {
/* 12 */     this.a = var0;
/* 13 */     this.b = Vec3D.a(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public Vec3D a() {
/* 18 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPosition b() {
/* 23 */     return this.a;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(EntityLiving var0) {
/* 28 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 33 */     return "BlockPosTracker{blockPos=" + this.a + ", centerPosition=" + this.b + '}';
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */