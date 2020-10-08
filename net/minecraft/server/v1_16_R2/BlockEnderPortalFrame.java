/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.base.Predicates;
/*     */ import java.util.function.Predicate;
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
/*     */ public class BlockEnderPortalFrame
/*     */   extends Block
/*     */ {
/*  25 */   public static final BlockStateDirection FACING = BlockFacingHorizontal.FACING;
/*  26 */   public static final BlockStateBoolean EYE = BlockProperties.h;
/*  27 */   protected static final VoxelShape c = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 13.0D, 16.0D);
/*  28 */   protected static final VoxelShape d = Block.a(4.0D, 13.0D, 4.0D, 12.0D, 16.0D, 12.0D);
/*  29 */   protected static final VoxelShape e = VoxelShapes.a(c, d);
/*     */   private static ShapeDetector f;
/*     */   
/*     */   public BlockEnderPortalFrame(BlockBase.Info var0) {
/*  33 */     super(var0);
/*  34 */     j(((IBlockData)this.blockStateList.getBlockData()).set(FACING, EnumDirection.NORTH).set(EYE, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c_(IBlockData var0) {
/*  39 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/*  44 */     return ((Boolean)var0.get(EYE)).booleanValue() ? e : c;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(BlockActionContext var0) {
/*  49 */     return getBlockData().set(FACING, var0.f().opposite()).set(EYE, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isComplexRedstone(IBlockData var0) {
/*  54 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(IBlockData var0, World var1, BlockPosition var2) {
/*  59 */     if (((Boolean)var0.get(EYE)).booleanValue()) {
/*  60 */       return 15;
/*     */     }
/*     */     
/*  63 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData var0, EnumBlockRotation var1) {
/*  68 */     return var0.set(FACING, var1.a((EnumDirection)var0.get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData var0, EnumBlockMirror var1) {
/*  73 */     return var0.a(var1.a((EnumDirection)var0.get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/*  78 */     var0.a((IBlockState<?>[])new IBlockState[] { FACING, EYE });
/*     */   }
/*     */   
/*     */   public static ShapeDetector c() {
/*  82 */     if (f == null)
/*     */     {
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
/*  96 */       f = ShapeDetectorBuilder.a().a(new String[] { "?vvv?", ">???<", ">???<", ">???<", "?^^^?" }).a('?', ShapeDetectorBlock.a(BlockStatePredicate.a)).a('^', ShapeDetectorBlock.a(BlockStatePredicate.a(Blocks.END_PORTAL_FRAME).<Comparable>a(EYE, (Predicate)Predicates.equalTo(Boolean.valueOf(true))).a(FACING, (Predicate)Predicates.equalTo(EnumDirection.SOUTH)))).a('>', ShapeDetectorBlock.a(BlockStatePredicate.a(Blocks.END_PORTAL_FRAME).<Comparable>a(EYE, (Predicate)Predicates.equalTo(Boolean.valueOf(true))).a(FACING, (Predicate)Predicates.equalTo(EnumDirection.WEST)))).a('v', ShapeDetectorBlock.a(BlockStatePredicate.a(Blocks.END_PORTAL_FRAME).<Comparable>a(EYE, (Predicate)Predicates.equalTo(Boolean.valueOf(true))).a(FACING, (Predicate)Predicates.equalTo(EnumDirection.NORTH)))).a('<', ShapeDetectorBlock.a(BlockStatePredicate.a(Blocks.END_PORTAL_FRAME).<Comparable>a(EYE, (Predicate)Predicates.equalTo(Boolean.valueOf(true))).a(FACING, (Predicate)Predicates.equalTo(EnumDirection.EAST)))).b();
/*     */     }
/*  98 */     return f;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData var0, IBlockAccess var1, BlockPosition var2, PathMode var3) {
/* 103 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockEnderPortalFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */