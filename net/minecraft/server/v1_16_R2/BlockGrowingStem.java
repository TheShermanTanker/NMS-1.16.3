/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Optional;
/*    */ import java.util.Random;
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
/*    */ public abstract class BlockGrowingStem
/*    */   extends BlockGrowingAbstract
/*    */   implements IBlockFragilePlantElement
/*    */ {
/*    */   protected BlockGrowingStem(BlockBase.Info var0, EnumDirection var1, VoxelShape var2, boolean var3) {
/* 20 */     super(var0, var1, var2, var3);
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/* 25 */     if (var1 == this.a.opposite() && !var0.canPlace(var3, var4)) {
/* 26 */       var3.getBlockTickList().a(var4, this, 1);
/*    */     }
/*    */     
/* 29 */     BlockGrowingTop var6 = c();
/* 30 */     if (var1 == this.a) {
/* 31 */       Block var7 = var2.getBlock();
/* 32 */       if (var7 != this && var7 != var6) {
/* 33 */         return var6.a(var3);
/*    */       }
/*    */     } 
/*    */     
/* 37 */     if (this.b) {
/* 38 */       var3.getFluidTickList().a(var4, FluidTypes.WATER, FluidTypes.WATER.a(var3));
/*    */     }
/*    */     
/* 41 */     return super.updateState(var0, var1, var2, var3, var4, var5);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean a(IBlockAccess var0, BlockPosition var1, IBlockData var2, boolean var3) {
/* 52 */     Optional<BlockPosition> var4 = b(var0, var1, var2);
/* 53 */     return (var4.isPresent() && c().h(var0.getType(((BlockPosition)var4.get()).shift(this.a))));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(World var0, Random var1, BlockPosition var2, IBlockData var3) {
/* 58 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(WorldServer var0, Random var1, BlockPosition var2, IBlockData var3) {
/* 63 */     Optional<BlockPosition> var4 = b(var0, var2, var3);
/*    */     
/* 65 */     if (var4.isPresent()) {
/* 66 */       IBlockData var5 = var0.getType(var4.get());
/* 67 */       ((BlockGrowingTop)var5.getBlock()).a(var0, var1, var4.get(), var5);
/*    */     } 
/*    */   }
/*    */   private Optional<BlockPosition> b(IBlockAccess var0, BlockPosition var1, IBlockData var2) {
/*    */     Block var4;
/* 72 */     BlockPosition var3 = var1;
/*    */     
/*    */     do {
/* 75 */       var3 = var3.shift(this.a);
/* 76 */       var4 = var0.getType(var3).getBlock();
/* 77 */     } while (var4 == var2.getBlock());
/*    */     
/* 79 */     if (var4 == c()) {
/* 80 */       return Optional.of(var3);
/*    */     }
/* 82 */     return Optional.empty();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean a(IBlockData var0, BlockActionContext var1) {
/* 88 */     boolean var2 = super.a(var0, var1);
/* 89 */     if (var2 && var1.getItemStack().getItem() == c().getItem()) {
/* 90 */       return false;
/*    */     }
/* 92 */     return var2;
/*    */   }
/*    */ 
/*    */   
/*    */   protected Block d() {
/* 97 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockGrowingStem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */