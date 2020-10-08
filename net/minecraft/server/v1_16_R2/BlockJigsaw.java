/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockJigsaw
/*    */   extends Block
/*    */   implements ITileEntity
/*    */ {
/* 24 */   public static final BlockStateEnum<BlockPropertyJigsawOrientation> a = BlockProperties.P;
/*    */   
/*    */   protected BlockJigsaw(BlockBase.Info var0) {
/* 27 */     super(var0);
/* 28 */     j(((IBlockData)this.blockStateList.getBlockData()).set(a, BlockPropertyJigsawOrientation.NORTH_UP));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 33 */     var0.a((IBlockState<?>[])new IBlockState[] { a });
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(IBlockData var0, EnumBlockRotation var1) {
/* 38 */     return var0.set(a, var1.a().a((BlockPropertyJigsawOrientation)var0.get(a)));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(IBlockData var0, EnumBlockMirror var1) {
/* 43 */     return var0.set(a, var1.a().a((BlockPropertyJigsawOrientation)var0.get(a)));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData getPlacedState(BlockActionContext var0) {
/* 48 */     EnumDirection var2, var1 = var0.getClickedFace();
/*    */     
/* 50 */     if (var1.n() == EnumDirection.EnumAxis.Y) {
/* 51 */       var2 = var0.f().opposite();
/*    */     } else {
/* 53 */       var2 = EnumDirection.UP;
/*    */     } 
/*    */     
/* 56 */     return getBlockData().set(a, BlockPropertyJigsawOrientation.a(var1, var2));
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public TileEntity createTile(IBlockAccess var0) {
/* 62 */     return new TileEntityJigsaw();
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult interact(IBlockData var0, World var1, BlockPosition var2, EntityHuman var3, EnumHand var4, MovingObjectPositionBlock var5) {
/* 67 */     TileEntity var6 = var1.getTileEntity(var2);
/* 68 */     if (var6 instanceof TileEntityJigsaw && var3.isCreativeAndOp()) {
/* 69 */       var3.a((TileEntityJigsaw)var6);
/* 70 */       return EnumInteractionResult.a(var1.isClientSide);
/*    */     } 
/*    */ 
/*    */     
/* 74 */     return EnumInteractionResult.PASS;
/*    */   }
/*    */   
/*    */   public static boolean a(DefinedStructure.BlockInfo var0, DefinedStructure.BlockInfo var1) {
/* 78 */     EnumDirection var2 = h(var0.b);
/* 79 */     EnumDirection var3 = h(var1.b);
/* 80 */     EnumDirection var4 = l(var0.b);
/* 81 */     EnumDirection var5 = l(var1.b);
/*    */ 
/*    */ 
/*    */     
/* 85 */     TileEntityJigsaw.JointType var6 = TileEntityJigsaw.JointType.a(var0.c.getString("joint")).orElseGet(() -> var0.n().d() ? TileEntityJigsaw.JointType.ALIGNED : TileEntityJigsaw.JointType.ROLLABLE);
/* 86 */     boolean var7 = (var6 == TileEntityJigsaw.JointType.ROLLABLE);
/*    */     
/* 88 */     return (var2 == var3.opposite() && (var7 || var4 == var5) && var0.c
/*    */       
/* 90 */       .getString("target").equals(var1.c.getString("name")));
/*    */   }
/*    */   
/*    */   public static EnumDirection h(IBlockData var0) {
/* 94 */     return ((BlockPropertyJigsawOrientation)var0.get(a)).b();
/*    */   }
/*    */   
/*    */   public static EnumDirection l(IBlockData var0) {
/* 98 */     return ((BlockPropertyJigsawOrientation)var0.get(a)).c();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockJigsaw.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */