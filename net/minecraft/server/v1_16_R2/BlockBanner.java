/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
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
/*    */ 
/*    */ 
/*    */ public class BlockBanner
/*    */   extends BlockBannerAbstract
/*    */ {
/* 22 */   public static final BlockStateInteger ROTATION = BlockProperties.aD;
/*    */   
/* 24 */   private static final Map<EnumColor, Block> b = Maps.newHashMap();
/* 25 */   private static final VoxelShape c = Block.a(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);
/*    */   
/*    */   public BlockBanner(EnumColor var0, BlockBase.Info var1) {
/* 28 */     super(var0, var1);
/* 29 */     j(((IBlockData)this.blockStateList.getBlockData()).set(ROTATION, Integer.valueOf(0)));
/*    */     
/* 31 */     b.put(var0, this);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canPlace(IBlockData var0, IWorldReader var1, BlockPosition var2) {
/* 36 */     return var1.getType(var2.down()).getMaterial().isBuildable();
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 41 */     return c;
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData getPlacedState(BlockActionContext var0) {
/* 46 */     return getBlockData().set(ROTATION, Integer.valueOf(MathHelper.floor(((180.0F + var0.h()) * 16.0F / 360.0F) + 0.5D) & 0xF));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/* 51 */     if (var1 == EnumDirection.DOWN && !var0.canPlace(var3, var4)) {
/* 52 */       return Blocks.AIR.getBlockData();
/*    */     }
/*    */     
/* 55 */     return super.updateState(var0, var1, var2, var3, var4, var5);
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(IBlockData var0, EnumBlockRotation var1) {
/* 60 */     return var0.set(ROTATION, Integer.valueOf(var1.a(((Integer)var0.get(ROTATION)).intValue(), 16)));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(IBlockData var0, EnumBlockMirror var1) {
/* 65 */     return var0.set(ROTATION, Integer.valueOf(var1.a(((Integer)var0.get(ROTATION)).intValue(), 16)));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 70 */     var0.a((IBlockState<?>[])new IBlockState[] { ROTATION });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockBanner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */