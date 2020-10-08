/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockTorchWall
/*     */   extends BlockTorch
/*     */ {
/*  25 */   public static final BlockStateDirection a = BlockFacingHorizontal.FACING;
/*     */ 
/*     */   
/*  28 */   private static final Map<EnumDirection, VoxelShape> b = Maps.newEnumMap((Map)ImmutableMap.of(EnumDirection.NORTH, 
/*  29 */         Block.a(5.5D, 3.0D, 11.0D, 10.5D, 13.0D, 16.0D), EnumDirection.SOUTH, 
/*  30 */         Block.a(5.5D, 3.0D, 0.0D, 10.5D, 13.0D, 5.0D), EnumDirection.WEST, 
/*  31 */         Block.a(11.0D, 3.0D, 5.5D, 16.0D, 13.0D, 10.5D), EnumDirection.EAST, 
/*  32 */         Block.a(0.0D, 3.0D, 5.5D, 5.0D, 13.0D, 10.5D)));
/*     */ 
/*     */   
/*     */   protected BlockTorchWall(BlockBase.Info var0, ParticleParam var1) {
/*  36 */     super(var0, var1);
/*  37 */     j(((IBlockData)this.blockStateList.getBlockData()).set(a, EnumDirection.NORTH));
/*     */   }
/*     */ 
/*     */   
/*     */   public String i() {
/*  42 */     return getItem().getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/*  47 */     return h(var0);
/*     */   }
/*     */   
/*     */   public static VoxelShape h(IBlockData var0) {
/*  51 */     return b.get(var0.get(a));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(IBlockData var0, IWorldReader var1, BlockPosition var2) {
/*  56 */     EnumDirection var3 = (EnumDirection)var0.get(a);
/*  57 */     BlockPosition var4 = var2.shift(var3.opposite());
/*  58 */     IBlockData var5 = var1.getType(var4);
/*     */     
/*  60 */     return var5.d(var1, var4, var3);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public IBlockData getPlacedState(BlockActionContext var0) {
/*  66 */     IBlockData var1 = getBlockData();
/*     */     
/*  68 */     IWorldReader var2 = var0.getWorld();
/*  69 */     BlockPosition var3 = var0.getClickPosition();
/*     */     
/*  71 */     EnumDirection[] var4 = var0.e();
/*  72 */     for (EnumDirection var8 : var4) {
/*  73 */       if (var8.n().d()) {
/*     */ 
/*     */ 
/*     */         
/*  77 */         EnumDirection var9 = var8.opposite();
/*     */         
/*  79 */         var1 = var1.set(a, var9);
/*  80 */         if (var1.canPlace(var2, var3)) {
/*  81 */           return var1;
/*     */         }
/*     */       } 
/*     */     } 
/*  85 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/*  90 */     if (var1.opposite() == var0.get(a) && !var0.canPlace(var3, var4)) {
/*  91 */       return Blocks.AIR.getBlockData();
/*     */     }
/*  93 */     return var0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData var0, EnumBlockRotation var1) {
/* 112 */     return var0.set(a, var1.a((EnumDirection)var0.get(a)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData var0, EnumBlockMirror var1) {
/* 117 */     return var0.a(var1.a((EnumDirection)var0.get(a)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 122 */     var0.a((IBlockState<?>[])new IBlockState[] { a });
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockTorchWall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */