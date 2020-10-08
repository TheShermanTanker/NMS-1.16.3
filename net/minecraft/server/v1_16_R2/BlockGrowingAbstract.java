/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
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
/*    */ 
/*    */ public abstract class BlockGrowingAbstract
/*    */   extends Block
/*    */ {
/*    */   protected final EnumDirection a;
/*    */   protected final boolean b;
/*    */   protected final VoxelShape c;
/*    */   
/*    */   protected BlockGrowingAbstract(BlockBase.Info var0, EnumDirection var1, VoxelShape var2, boolean var3) {
/* 23 */     super(var0);
/* 24 */     this.a = var1;
/* 25 */     this.c = var2;
/* 26 */     this.b = var3;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public IBlockData getPlacedState(BlockActionContext var0) {
/* 32 */     IBlockData var1 = var0.getWorld().getType(var0.getClickPosition().shift(this.a));
/* 33 */     if (var1.a(c()) || var1.a(d())) {
/* 34 */       return d().getBlockData();
/*    */     }
/* 36 */     return a(var0.getWorld());
/*    */   }
/*    */   
/*    */   public IBlockData a(GeneratorAccess var0) {
/* 40 */     return getBlockData();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canPlace(IBlockData var0, IWorldReader var1, BlockPosition var2) {
/* 45 */     BlockPosition var3 = var2.shift(this.a.opposite());
/* 46 */     IBlockData var4 = var1.getType(var3);
/* 47 */     Block var5 = var4.getBlock();
/* 48 */     if (!c(var5)) {
/* 49 */       return false;
/*    */     }
/*    */     
/* 52 */     return (var5 == c() || var5 == d() || var4.d(var1, var3, this.a));
/*    */   }
/*    */ 
/*    */   
/*    */   public void tickAlways(IBlockData var0, WorldServer var1, BlockPosition var2, Random var3) {
/* 57 */     if (!var0.canPlace(var1, var2)) {
/* 58 */       var1.b(var2, true);
/*    */     }
/*    */   }
/*    */   
/*    */   protected boolean c(Block var0) {
/* 63 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 68 */     return this.c;
/*    */   }
/*    */   
/*    */   protected abstract BlockGrowingTop c();
/*    */   
/*    */   protected abstract Block d();
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockGrowingAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */