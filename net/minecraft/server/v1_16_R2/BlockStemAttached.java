/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Map;
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
/*    */ 
/*    */ 
/*    */ public class BlockStemAttached
/*    */   extends BlockPlant
/*    */ {
/* 21 */   public static final BlockStateDirection a = BlockFacingHorizontal.FACING;
/*    */ 
/*    */   
/*    */   private final BlockStemmed b;
/*    */   
/* 26 */   private static final Map<EnumDirection, VoxelShape> c = Maps.newEnumMap((Map)ImmutableMap.of(EnumDirection.SOUTH, 
/* 27 */         Block.a(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 16.0D), EnumDirection.WEST, 
/* 28 */         Block.a(0.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D), EnumDirection.NORTH, 
/* 29 */         Block.a(6.0D, 0.0D, 0.0D, 10.0D, 10.0D, 10.0D), EnumDirection.EAST, 
/* 30 */         Block.a(6.0D, 0.0D, 6.0D, 16.0D, 10.0D, 10.0D)));
/*    */ 
/*    */   
/*    */   protected BlockStemAttached(BlockStemmed var0, BlockBase.Info var1) {
/* 34 */     super(var1);
/* 35 */     j(((IBlockData)this.blockStateList.getBlockData()).set(a, EnumDirection.NORTH));
/* 36 */     this.b = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 41 */     return c.get(var0.get(a));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/* 46 */     if (!var2.a(this.b) && var1 == var0.get(a)) {
/* 47 */       return this.b.c().getBlockData().set(BlockStem.AGE, Integer.valueOf(7));
/*    */     }
/* 49 */     return super.updateState(var0, var1, var2, var3, var4, var5);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean c(IBlockData var0, IBlockAccess var1, BlockPosition var2) {
/* 54 */     return var0.a(Blocks.FARMLAND);
/*    */   }
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IBlockData a(IBlockData var0, EnumBlockRotation var1) {
/* 76 */     return var0.set(a, var1.a((EnumDirection)var0.get(a)));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(IBlockData var0, EnumBlockMirror var1) {
/* 81 */     return var0.a(var1.a((EnumDirection)var0.get(a)));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 86 */     var0.a((IBlockState<?>[])new IBlockState[] { a });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockStemAttached.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */