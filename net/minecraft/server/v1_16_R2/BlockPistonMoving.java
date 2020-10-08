/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.List;
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
/*     */ public class BlockPistonMoving
/*     */   extends BlockTileEntity
/*     */ {
/*  35 */   public static final BlockStateDirection a = BlockPistonExtension.FACING;
/*  36 */   public static final BlockStateEnum<BlockPropertyPistonType> b = BlockPistonExtension.TYPE;
/*     */   
/*     */   public BlockPistonMoving(BlockBase.Info var0) {
/*  39 */     super(var0);
/*  40 */     j(((IBlockData)this.blockStateList.getBlockData()).set(a, EnumDirection.NORTH).set(b, BlockPropertyPistonType.DEFAULT));
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public TileEntity createTile(IBlockAccess var0) {
/*  46 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static TileEntity a(IBlockData var0, EnumDirection var1, boolean var2, boolean var3) {
/*  51 */     return new TileEntityPiston(var0, var1, var2, var3);
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(IBlockData var0, World var1, BlockPosition var2, IBlockData var3, boolean var4) {
/*  56 */     if (var0.a(var3.getBlock())) {
/*     */       return;
/*     */     }
/*  59 */     TileEntity var5 = var1.getTileEntity(var2);
/*  60 */     if (var5 instanceof TileEntityPiston) {
/*  61 */       ((TileEntityPiston)var5).l();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void postBreak(GeneratorAccess var0, BlockPosition var1, IBlockData var2) {
/*  68 */     BlockPosition var3 = var1.shift(((EnumDirection)var2.get(a)).opposite());
/*  69 */     IBlockData var4 = var0.getType(var3);
/*  70 */     if (var4.getBlock() instanceof BlockPiston && ((Boolean)var4.get(BlockPiston.EXTENDED)).booleanValue()) {
/*  71 */       var0.a(var3, false);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData var0, World var1, BlockPosition var2, EntityHuman var3, EnumHand var4, MovingObjectPositionBlock var5) {
/*  78 */     if (!var1.isClientSide && var1.getTileEntity(var2) == null) {
/*     */       
/*  80 */       var1.a(var2, false);
/*  81 */       return EnumInteractionResult.CONSUME;
/*     */     } 
/*  83 */     return EnumInteractionResult.PASS;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<ItemStack> a(IBlockData var0, LootTableInfo.Builder var1) {
/*  89 */     TileEntityPiston var2 = a(var1.a(), new BlockPosition(var1.<Vec3D>a(LootContextParameters.ORIGIN)));
/*  90 */     if (var2 == null) {
/*  91 */       return Collections.emptyList();
/*     */     }
/*     */     
/*  94 */     return var2.k().a(var1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 100 */     return VoxelShapes.a();
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape c(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 105 */     TileEntityPiston var4 = a(var1, var2);
/* 106 */     if (var4 != null) {
/* 107 */       return var4.a(var1, var2);
/*     */     }
/* 109 */     return VoxelShapes.a();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private TileEntityPiston a(IBlockAccess var0, BlockPosition var1) {
/* 114 */     TileEntity var2 = var0.getTileEntity(var1);
/* 115 */     if (var2 instanceof TileEntityPiston) {
/* 116 */       return (TileEntityPiston)var2;
/*     */     }
/* 118 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData var0, EnumBlockRotation var1) {
/* 128 */     return var0.set(a, var1.a((EnumDirection)var0.get(a)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData var0, EnumBlockMirror var1) {
/* 133 */     return var0.a(var1.a((EnumDirection)var0.get(a)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 138 */     var0.a((IBlockState<?>[])new IBlockState[] { a, b });
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData var0, IBlockAccess var1, BlockPosition var2, PathMode var3) {
/* 143 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockPistonMoving.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */