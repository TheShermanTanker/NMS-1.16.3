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
/*     */ public class BlockWallSign
/*     */   extends BlockSign
/*     */ {
/*  24 */   public static final BlockStateDirection FACING = BlockFacingHorizontal.FACING;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  30 */   private static final Map<EnumDirection, VoxelShape> d = Maps.newEnumMap((Map)ImmutableMap.of(EnumDirection.NORTH, 
/*  31 */         Block.a(0.0D, 4.5D, 14.0D, 16.0D, 12.5D, 16.0D), EnumDirection.SOUTH, 
/*  32 */         Block.a(0.0D, 4.5D, 0.0D, 16.0D, 12.5D, 2.0D), EnumDirection.EAST, 
/*  33 */         Block.a(0.0D, 4.5D, 0.0D, 2.0D, 12.5D, 16.0D), EnumDirection.WEST, 
/*  34 */         Block.a(14.0D, 4.5D, 0.0D, 16.0D, 12.5D, 16.0D)));
/*     */ 
/*     */   
/*     */   public BlockWallSign(BlockBase.Info var0, BlockPropertyWood var1) {
/*  38 */     super(var0, var1);
/*  39 */     j(((IBlockData)this.blockStateList.getBlockData()).set(FACING, EnumDirection.NORTH).set(a, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */   
/*     */   public String i() {
/*  44 */     return getItem().getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/*  49 */     return d.get(var0.get(FACING));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(IBlockData var0, IWorldReader var1, BlockPosition var2) {
/*  54 */     return var1.getType(var2.shift(((EnumDirection)var0.get(FACING)).opposite())).getMaterial().isBuildable();
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public IBlockData getPlacedState(BlockActionContext var0) {
/*  60 */     IBlockData var1 = getBlockData();
/*  61 */     Fluid var2 = var0.getWorld().getFluid(var0.getClickPosition());
/*     */     
/*  63 */     IWorldReader var3 = var0.getWorld();
/*  64 */     BlockPosition var4 = var0.getClickPosition();
/*     */     
/*  66 */     EnumDirection[] var5 = var0.e();
/*  67 */     for (EnumDirection var9 : var5) {
/*  68 */       if (var9.n().d()) {
/*     */ 
/*     */ 
/*     */         
/*  72 */         EnumDirection var10 = var9.opposite();
/*     */         
/*  74 */         var1 = var1.set(FACING, var10);
/*  75 */         if (var1.canPlace(var3, var4)) {
/*  76 */           return var1.set(a, Boolean.valueOf((var2.getType() == FluidTypes.WATER)));
/*     */         }
/*     */       } 
/*     */     } 
/*  80 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/*  85 */     if (var1.opposite() == var0.get(FACING) && !var0.canPlace(var3, var4)) {
/*  86 */       return Blocks.AIR.getBlockData();
/*     */     }
/*  88 */     return super.updateState(var0, var1, var2, var3, var4, var5);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData var0, EnumBlockRotation var1) {
/*  93 */     return var0.set(FACING, var1.a((EnumDirection)var0.get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData var0, EnumBlockMirror var1) {
/*  98 */     return var0.a(var1.a((EnumDirection)var0.get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 103 */     var0.a((IBlockState<?>[])new IBlockState[] { FACING, a });
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockWallSign.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */