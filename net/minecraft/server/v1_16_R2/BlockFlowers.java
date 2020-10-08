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
/*    */ public class BlockFlowers
/*    */   extends BlockPlant
/*    */ {
/* 14 */   protected static final VoxelShape a = Block.a(5.0D, 0.0D, 5.0D, 11.0D, 10.0D, 11.0D);
/*    */   private final MobEffectList b;
/*    */   private final int c;
/*    */   
/*    */   public BlockFlowers(MobEffectList var0, int var1, BlockBase.Info var2) {
/* 19 */     super(var2);
/* 20 */     this.b = var0;
/* 21 */     if (var0.isInstant()) {
/* 22 */       this.c = var1;
/*    */     } else {
/* 24 */       this.c = var1 * 20;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 30 */     Vec3D var4 = var0.n(var1, var2);
/* 31 */     return a.a(var4.x, var4.y, var4.z);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockBase.EnumRandomOffset ah_() {
/* 36 */     return BlockBase.EnumRandomOffset.XZ;
/*    */   }
/*    */   
/*    */   public MobEffectList c() {
/* 40 */     return this.b;
/*    */   }
/*    */   
/*    */   public int d() {
/* 44 */     return this.c;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockFlowers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */