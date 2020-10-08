/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
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
/*    */ public class BlockChorusFruit
/*    */   extends BlockSprawling
/*    */ {
/*    */   protected BlockChorusFruit(BlockBase.Info var0) {
/* 18 */     super(0.3125F, var0);
/*    */     
/* 20 */     j(((IBlockData)this.blockStateList.getBlockData()).set(a, Boolean.valueOf(false)).set(b, Boolean.valueOf(false)).set(c, Boolean.valueOf(false)).set(d, Boolean.valueOf(false)).set(e, Boolean.valueOf(false)).set(f, Boolean.valueOf(false)));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData getPlacedState(BlockActionContext var0) {
/* 25 */     return a(var0.getWorld(), var0.getClickPosition());
/*    */   }
/*    */   
/*    */   public IBlockData a(IBlockAccess var0, BlockPosition var1) {
/* 29 */     Block var2 = var0.getType(var1.down()).getBlock();
/* 30 */     Block var3 = var0.getType(var1.up()).getBlock();
/* 31 */     Block var4 = var0.getType(var1.north()).getBlock();
/* 32 */     Block var5 = var0.getType(var1.east()).getBlock();
/* 33 */     Block var6 = var0.getType(var1.south()).getBlock();
/* 34 */     Block var7 = var0.getType(var1.west()).getBlock();
/*    */     
/* 36 */     return getBlockData()
/* 37 */       .set(f, Boolean.valueOf((var2 == this || var2 == Blocks.CHORUS_FLOWER || var2 == Blocks.END_STONE)))
/* 38 */       .set(e, Boolean.valueOf((var3 == this || var3 == Blocks.CHORUS_FLOWER)))
/* 39 */       .set(a, Boolean.valueOf((var4 == this || var4 == Blocks.CHORUS_FLOWER)))
/* 40 */       .set(b, Boolean.valueOf((var5 == this || var5 == Blocks.CHORUS_FLOWER)))
/* 41 */       .set(c, Boolean.valueOf((var6 == this || var6 == Blocks.CHORUS_FLOWER)))
/* 42 */       .set(d, Boolean.valueOf((var7 == this || var7 == Blocks.CHORUS_FLOWER)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/* 48 */     if (!var0.canPlace(var3, var4)) {
/* 49 */       var3.getBlockTickList().a(var4, this, 1);
/* 50 */       return super.updateState(var0, var1, var2, var3, var4, var5);
/*    */     } 
/*    */     
/* 53 */     boolean var6 = (var2.getBlock() == this || var2.a(Blocks.CHORUS_FLOWER) || (var1 == EnumDirection.DOWN && var2.a(Blocks.END_STONE)));
/*    */     
/* 55 */     return var0.set(g.get(var1), Boolean.valueOf(var6));
/*    */   }
/*    */ 
/*    */   
/*    */   public void tickAlways(IBlockData var0, WorldServer var1, BlockPosition var2, Random var3) {
/* 60 */     if (!var0.canPlace(var1, var2)) {
/* 61 */       var1.b(var2, true);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canPlace(IBlockData var0, IWorldReader var1, BlockPosition var2) {
/* 70 */     IBlockData var3 = var1.getType(var2.down());
/* 71 */     boolean var4 = (!var1.getType(var2.up()).isAir() && !var3.isAir());
/*    */     
/* 73 */     for (EnumDirection var6 : EnumDirection.EnumDirectionLimit.HORIZONTAL) {
/* 74 */       BlockPosition var7 = var2.shift(var6);
/* 75 */       Block var8 = var1.getType(var7).getBlock();
/* 76 */       if (var8 == this) {
/* 77 */         if (var4) {
/* 78 */           return false;
/*    */         }
/* 80 */         Block var9 = var1.getType(var7.down()).getBlock();
/* 81 */         if (var9 == this || var9 == Blocks.END_STONE) {
/* 82 */           return true;
/*    */         }
/*    */       } 
/*    */     } 
/* 86 */     Block var5 = var3.getBlock();
/* 87 */     return (var5 == this || var5 == Blocks.END_STONE);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 92 */     var0.a((IBlockState<?>[])new IBlockState[] { a, b, c, d, e, f });
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IBlockData var0, IBlockAccess var1, BlockPosition var2, PathMode var3) {
/* 97 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockChorusFruit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */