/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PathfinderGoalWater
/*    */   extends PathfinderGoal
/*    */ {
/*    */   private final EntityCreature a;
/*    */   
/*    */   public PathfinderGoalWater(EntityCreature var0) {
/* 12 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 17 */     return (this.a.isOnGround() && !this.a.world.getFluid(this.a.getChunkCoordinates()).a(TagsFluid.WATER));
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 22 */     BlockPosition var0 = null;
/*    */     
/* 24 */     Iterable<BlockPosition> var1 = BlockPosition.b(
/* 25 */         MathHelper.floor(this.a.locX() - 2.0D), 
/* 26 */         MathHelper.floor(this.a.locY() - 2.0D), 
/* 27 */         MathHelper.floor(this.a.locZ() - 2.0D), 
/* 28 */         MathHelper.floor(this.a.locX() + 2.0D), 
/* 29 */         MathHelper.floor(this.a.locY()), 
/* 30 */         MathHelper.floor(this.a.locZ() + 2.0D));
/*    */ 
/*    */     
/* 33 */     for (BlockPosition var3 : var1) {
/* 34 */       if (this.a.world.getFluid(var3).a(TagsFluid.WATER)) {
/* 35 */         var0 = var3;
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/* 40 */     if (var0 != null)
/* 41 */       this.a.getControllerMove().a(var0.getX(), var0.getY(), var0.getZ(), 1.0D); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalWater.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */