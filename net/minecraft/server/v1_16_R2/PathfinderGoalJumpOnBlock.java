/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PathfinderGoalJumpOnBlock
/*    */   extends PathfinderGoalGotoTarget
/*    */ {
/*    */   private final EntityCat g;
/*    */   
/*    */   public PathfinderGoalJumpOnBlock(EntityCat var0, double var1) {
/* 18 */     super(var0, var1, 8);
/* 19 */     this.g = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 24 */     return (this.g.isTamed() && !this.g.isWillSit() && super.a());
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 29 */     super.c();
/* 30 */     this.g.setSitting(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 35 */     super.d();
/* 36 */     this.g.setSitting(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void e() {
/* 41 */     super.e();
/*    */     
/* 43 */     this.g.setSitting(l());
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(IWorldReader var0, BlockPosition var1) {
/* 48 */     if (!var0.isEmpty(var1.up())) {
/* 49 */       return false;
/*    */     }
/*    */     
/* 52 */     IBlockData var2 = var0.getType(var1);
/*    */ 
/*    */     
/* 55 */     if (var2.a(Blocks.CHEST))
/* 56 */       return (TileEntityChest.a(var0, var1) < 1); 
/* 57 */     if (var2.a(Blocks.FURNACE) && ((Boolean)var2.get(BlockFurnaceFurace.LIT)).booleanValue()) {
/* 58 */       return true;
/*    */     }
/* 60 */     return var2.a(TagsBlock.BEDS, var0 -> ((Boolean)var0.d(BlockBed.PART).map(()).orElse(Boolean.valueOf(true))).booleanValue());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalJumpOnBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */