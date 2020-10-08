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
/*    */ public class BlockSkullWall
/*    */   extends BlockSkullAbstract
/*    */ {
/* 18 */   public static final BlockStateDirection a = BlockFacingHorizontal.FACING;
/*    */   
/* 20 */   private static final Map<EnumDirection, VoxelShape> b = Maps.newEnumMap((Map)ImmutableMap.of(EnumDirection.NORTH, 
/* 21 */         Block.a(4.0D, 4.0D, 8.0D, 12.0D, 12.0D, 16.0D), EnumDirection.SOUTH, 
/* 22 */         Block.a(4.0D, 4.0D, 0.0D, 12.0D, 12.0D, 8.0D), EnumDirection.EAST, 
/* 23 */         Block.a(0.0D, 4.0D, 4.0D, 8.0D, 12.0D, 12.0D), EnumDirection.WEST, 
/* 24 */         Block.a(8.0D, 4.0D, 4.0D, 16.0D, 12.0D, 12.0D)));
/*    */ 
/*    */   
/*    */   protected BlockSkullWall(BlockSkull.a var0, BlockBase.Info var1) {
/* 28 */     super(var0, var1);
/* 29 */     j(((IBlockData)this.blockStateList.getBlockData()).set(a, EnumDirection.NORTH));
/*    */   }
/*    */ 
/*    */   
/*    */   public String i() {
/* 34 */     return getItem().getName();
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 39 */     return b.get(var0.get(a));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData getPlacedState(BlockActionContext var0) {
/* 44 */     IBlockData var1 = getBlockData();
/*    */     
/* 46 */     IBlockAccess var2 = var0.getWorld();
/* 47 */     BlockPosition var3 = var0.getClickPosition();
/*    */     
/* 49 */     EnumDirection[] var4 = var0.e();
/* 50 */     for (EnumDirection var8 : var4) {
/* 51 */       if (var8.n().d()) {
/*    */ 
/*    */ 
/*    */         
/* 55 */         EnumDirection var9 = var8.opposite();
/*    */         
/* 57 */         var1 = var1.set(a, var9);
/* 58 */         if (!var2.getType(var3.shift(var8)).a(var0)) {
/* 59 */           return var1;
/*    */         }
/*    */       } 
/*    */     } 
/* 63 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(IBlockData var0, EnumBlockRotation var1) {
/* 68 */     return var0.set(a, var1.a((EnumDirection)var0.get(a)));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(IBlockData var0, EnumBlockMirror var1) {
/* 73 */     return var0.a(var1.a((EnumDirection)var0.get(a)));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 78 */     var0.a((IBlockState<?>[])new IBlockState[] { a });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockSkullWall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */