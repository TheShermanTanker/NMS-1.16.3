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
/*    */ public class PathfinderGoalRandomFly
/*    */   extends PathfinderGoalRandomStrollLand
/*    */ {
/*    */   public PathfinderGoalRandomFly(EntityCreature var0, double var1) {
/* 17 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   protected Vec3D g() {
/* 23 */     Vec3D var0 = null;
/* 24 */     if (this.a.isInWater()) {
/* 25 */       var0 = RandomPositionGenerator.b(this.a, 15, 15);
/*    */     }
/* 27 */     if (this.a.getRandom().nextFloat() >= this.h) {
/* 28 */       var0 = j();
/*    */     }
/* 30 */     return (var0 == null) ? super.g() : var0;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   private Vec3D j() {
/* 35 */     BlockPosition var0 = this.a.getChunkCoordinates();
/*    */     
/* 37 */     BlockPosition.MutableBlockPosition var1 = new BlockPosition.MutableBlockPosition();
/* 38 */     BlockPosition.MutableBlockPosition var2 = new BlockPosition.MutableBlockPosition();
/* 39 */     Iterable<BlockPosition> var3 = BlockPosition.b(
/* 40 */         MathHelper.floor(this.a.locX() - 3.0D), 
/* 41 */         MathHelper.floor(this.a.locY() - 6.0D), 
/* 42 */         MathHelper.floor(this.a.locZ() - 3.0D), 
/* 43 */         MathHelper.floor(this.a.locX() + 3.0D), 
/* 44 */         MathHelper.floor(this.a.locY() + 6.0D), 
/* 45 */         MathHelper.floor(this.a.locZ() + 3.0D));
/*    */ 
/*    */     
/* 48 */     for (BlockPosition var5 : var3) {
/* 49 */       if (var0.equals(var5)) {
/*    */         continue;
/*    */       }
/*    */       
/* 53 */       Block var6 = this.a.world.getType(var2.a(var5, EnumDirection.DOWN)).getBlock();
/* 54 */       boolean var7 = (var6 instanceof BlockLeaves || var6.a(TagsBlock.LOGS));
/* 55 */       if (var7 && this.a.world.isEmpty(var5) && this.a.world.isEmpty(var1.a(var5, EnumDirection.UP))) {
/* 56 */         return Vec3D.c(var5);
/*    */       }
/*    */     } 
/*    */     
/* 60 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalRandomFly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */