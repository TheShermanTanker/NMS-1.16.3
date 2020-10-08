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
/*    */ public class BlockBannerWall
/*    */   extends BlockBannerAbstract
/*    */ {
/* 21 */   public static final BlockStateDirection a = BlockFacingHorizontal.FACING;
/*    */   
/* 23 */   private static final Map<EnumDirection, VoxelShape> b = Maps.newEnumMap((Map)ImmutableMap.of(EnumDirection.NORTH, 
/* 24 */         Block.a(0.0D, 0.0D, 14.0D, 16.0D, 12.5D, 16.0D), EnumDirection.SOUTH, 
/* 25 */         Block.a(0.0D, 0.0D, 0.0D, 16.0D, 12.5D, 2.0D), EnumDirection.WEST, 
/* 26 */         Block.a(14.0D, 0.0D, 0.0D, 16.0D, 12.5D, 16.0D), EnumDirection.EAST, 
/* 27 */         Block.a(0.0D, 0.0D, 0.0D, 2.0D, 12.5D, 16.0D)));
/*    */ 
/*    */   
/*    */   public BlockBannerWall(EnumColor var0, BlockBase.Info var1) {
/* 31 */     super(var0, var1);
/* 32 */     j(((IBlockData)this.blockStateList.getBlockData()).set(a, EnumDirection.NORTH));
/*    */   }
/*    */ 
/*    */   
/*    */   public String i() {
/* 37 */     return getItem().getName();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canPlace(IBlockData var0, IWorldReader var1, BlockPosition var2) {
/* 43 */     return var1.getType(var2.shift(((EnumDirection)var0.get(a)).opposite())).getMaterial().isBuildable();
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/* 48 */     if (var1 == ((EnumDirection)var0.get(a)).opposite() && !var0.canPlace(var3, var4)) {
/* 49 */       return Blocks.AIR.getBlockData();
/*    */     }
/*    */     
/* 52 */     return super.updateState(var0, var1, var2, var3, var4, var5);
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 57 */     return b.get(var0.get(a));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData getPlacedState(BlockActionContext var0) {
/* 62 */     IBlockData var1 = getBlockData();
/*    */     
/* 64 */     IWorldReader var2 = var0.getWorld();
/* 65 */     BlockPosition var3 = var0.getClickPosition();
/*    */     
/* 67 */     EnumDirection[] var4 = var0.e();
/* 68 */     for (EnumDirection var8 : var4) {
/* 69 */       if (var8.n().d()) {
/*    */ 
/*    */ 
/*    */         
/* 73 */         EnumDirection var9 = var8.opposite();
/*    */         
/* 75 */         var1 = var1.set(a, var9);
/* 76 */         if (var1.canPlace(var2, var3)) {
/* 77 */           return var1;
/*    */         }
/*    */       } 
/*    */     } 
/* 81 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(IBlockData var0, EnumBlockRotation var1) {
/* 86 */     return var0.set(a, var1.a((EnumDirection)var0.get(a)));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(IBlockData var0, EnumBlockMirror var1) {
/* 91 */     return var0.a(var1.a((EnumDirection)var0.get(a)));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 96 */     var0.a((IBlockState<?>[])new IBlockState[] { a });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockBannerWall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */