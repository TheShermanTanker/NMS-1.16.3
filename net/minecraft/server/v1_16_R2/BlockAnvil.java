/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
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
/*     */ public class BlockAnvil
/*     */   extends BlockFalling
/*     */ {
/*  31 */   public static final BlockStateDirection FACING = BlockFacingHorizontal.FACING;
/*     */   
/*  33 */   private static final VoxelShape b = Block.a(2.0D, 0.0D, 2.0D, 14.0D, 4.0D, 14.0D);
/*     */   
/*  35 */   private static final VoxelShape c = Block.a(3.0D, 4.0D, 4.0D, 13.0D, 5.0D, 12.0D);
/*  36 */   private static final VoxelShape d = Block.a(4.0D, 5.0D, 6.0D, 12.0D, 10.0D, 10.0D);
/*  37 */   private static final VoxelShape e = Block.a(0.0D, 10.0D, 3.0D, 16.0D, 16.0D, 13.0D);
/*     */   
/*  39 */   private static final VoxelShape f = Block.a(4.0D, 4.0D, 3.0D, 12.0D, 5.0D, 13.0D);
/*  40 */   private static final VoxelShape g = Block.a(6.0D, 5.0D, 4.0D, 10.0D, 10.0D, 12.0D);
/*  41 */   private static final VoxelShape h = Block.a(3.0D, 10.0D, 0.0D, 13.0D, 16.0D, 16.0D);
/*     */   
/*  43 */   private static final VoxelShape i = VoxelShapes.a(b, new VoxelShape[] { c, d, e });
/*  44 */   private static final VoxelShape j = VoxelShapes.a(b, new VoxelShape[] { f, g, h });
/*     */   
/*  46 */   private static final IChatBaseComponent k = new ChatMessage("container.repair");
/*     */   
/*     */   public BlockAnvil(BlockBase.Info var0) {
/*  49 */     super(var0);
/*  50 */     j(((IBlockData)this.blockStateList.getBlockData()).set(FACING, EnumDirection.NORTH));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(BlockActionContext var0) {
/*  55 */     return getBlockData().set(FACING, var0.f().g());
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData var0, World var1, BlockPosition var2, EntityHuman var3, EnumHand var4, MovingObjectPositionBlock var5) {
/*  60 */     if (var1.isClientSide) {
/*  61 */       return EnumInteractionResult.SUCCESS;
/*     */     }
/*     */     
/*  64 */     var3.openContainer(var0.b(var1, var2));
/*  65 */     var3.a(StatisticList.INTERACT_WITH_ANVIL);
/*  66 */     return EnumInteractionResult.CONSUME;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ITileInventory getInventory(IBlockData var0, World var1, BlockPosition var2) {
/*  72 */     return new TileInventory((var2, var3, var4) -> new ContainerAnvil(var2, var3, ContainerAccess.at(var0, var1)), k);
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/*  77 */     EnumDirection var4 = (EnumDirection)var0.get(FACING);
/*  78 */     if (var4.n() == EnumDirection.EnumAxis.X) {
/*  79 */       return i;
/*     */     }
/*  81 */     return j;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(EntityFallingBlock var0) {
/*  87 */     var0.a(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World var0, BlockPosition var1, IBlockData var2, IBlockData var3, EntityFallingBlock var4) {
/*  92 */     if (!var4.isSilent()) {
/*  93 */       var0.triggerEffect(1031, var1, 0);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World var0, BlockPosition var1, EntityFallingBlock var2) {
/*  99 */     if (!var2.isSilent()) {
/* 100 */       var0.triggerEffect(1029, var1, 0);
/*     */     }
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static IBlockData c(IBlockData var0) {
/* 106 */     if (var0.a(Blocks.ANVIL)) {
/* 107 */       return Blocks.CHIPPED_ANVIL.getBlockData().set(FACING, var0.get(FACING));
/*     */     }
/* 109 */     if (var0.a(Blocks.CHIPPED_ANVIL)) {
/* 110 */       return Blocks.DAMAGED_ANVIL.getBlockData().set(FACING, var0.get(FACING));
/*     */     }
/* 112 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData var0, EnumBlockRotation var1) {
/* 117 */     return var0.set(FACING, var1.a((EnumDirection)var0.get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 122 */     var0.a((IBlockState<?>[])new IBlockState[] { FACING });
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData var0, IBlockAccess var1, BlockPosition var2, PathMode var3) {
/* 127 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockAnvil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */