/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Map;
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
/*    */ 
/*    */ 
/*    */ public class BlockCoralFanWallAbstract
/*    */   extends BlockCoralFanAbstract
/*    */ {
/* 22 */   public static final BlockStateDirection a = BlockFacingHorizontal.FACING;
/*    */   
/* 24 */   private static final Map<EnumDirection, VoxelShape> c = Maps.newEnumMap((Map)ImmutableMap.of(EnumDirection.NORTH, 
/* 25 */         Block.a(0.0D, 4.0D, 5.0D, 16.0D, 12.0D, 16.0D), EnumDirection.SOUTH, 
/* 26 */         Block.a(0.0D, 4.0D, 0.0D, 16.0D, 12.0D, 11.0D), EnumDirection.WEST, 
/* 27 */         Block.a(5.0D, 4.0D, 0.0D, 16.0D, 12.0D, 16.0D), EnumDirection.EAST, 
/* 28 */         Block.a(0.0D, 4.0D, 0.0D, 11.0D, 12.0D, 16.0D)));
/*    */ 
/*    */   
/*    */   protected BlockCoralFanWallAbstract(BlockBase.Info var0) {
/* 32 */     super(var0);
/* 33 */     j(((IBlockData)this.blockStateList.getBlockData()).set(a, EnumDirection.NORTH).set(b, Boolean.valueOf(true)));
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 38 */     return c.get(var0.get(a));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(IBlockData var0, EnumBlockRotation var1) {
/* 43 */     return var0.set(a, var1.a((EnumDirection)var0.get(a)));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(IBlockData var0, EnumBlockMirror var1) {
/* 48 */     return var0.a(var1.a((EnumDirection)var0.get(a)));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 53 */     var0.a((IBlockState<?>[])new IBlockState[] { a, b });
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/* 58 */     if (((Boolean)var0.get(b)).booleanValue()) {
/* 59 */       var3.getFluidTickList().a(var4, FluidTypes.WATER, FluidTypes.WATER.a(var3));
/*    */     }
/*    */     
/* 62 */     if (var1.opposite() == var0.get(a) && !var0.canPlace(var3, var4)) {
/* 63 */       return Blocks.AIR.getBlockData();
/*    */     }
/*    */     
/* 66 */     return var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canPlace(IBlockData var0, IWorldReader var1, BlockPosition var2) {
/* 71 */     EnumDirection var3 = (EnumDirection)var0.get(a);
/* 72 */     BlockPosition var4 = var2.shift(var3.opposite());
/* 73 */     IBlockData var5 = var1.getType(var4);
/*    */     
/* 75 */     return var5.d(var1, var4, var3);
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public IBlockData getPlacedState(BlockActionContext var0) {
/* 81 */     IBlockData var1 = super.getPlacedState(var0);
/*    */     
/* 83 */     IWorldReader var2 = var0.getWorld();
/* 84 */     BlockPosition var3 = var0.getClickPosition();
/*    */     
/* 86 */     EnumDirection[] var4 = var0.e();
/* 87 */     for (EnumDirection var8 : var4) {
/* 88 */       if (var8.n().d()) {
/*    */ 
/*    */ 
/*    */         
/* 92 */         var1 = var1.set(a, var8.opposite());
/* 93 */         if (var1.canPlace(var2, var3)) {
/* 94 */           return var1;
/*    */         }
/*    */       } 
/*    */     } 
/* 98 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockCoralFanWallAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */