/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class PathfinderGoalDoorOpen
/*    */   extends PathfinderGoalDoorInteract
/*    */ {
/*    */   private final boolean a;
/*    */   private int b;
/*    */   
/*    */   public PathfinderGoalDoorOpen(EntityInsentient var0, boolean var1) {
/* 10 */     super(var0);
/* 11 */     this.entity = var0;
/* 12 */     this.a = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b() {
/* 17 */     return (this.a && this.b > 0 && super.b());
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 22 */     this.b = 20;
/* 23 */     a(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 28 */     a(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void e() {
/* 33 */     this.b--;
/* 34 */     super.e();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalDoorOpen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */