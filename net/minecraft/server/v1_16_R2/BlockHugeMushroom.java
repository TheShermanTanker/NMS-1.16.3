/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockHugeMushroom
/*    */   extends Block
/*    */ {
/* 15 */   public static final BlockStateBoolean a = BlockSprawling.a;
/* 16 */   public static final BlockStateBoolean b = BlockSprawling.b;
/* 17 */   public static final BlockStateBoolean c = BlockSprawling.c;
/* 18 */   public static final BlockStateBoolean d = BlockSprawling.d;
/* 19 */   public static final BlockStateBoolean e = BlockSprawling.e;
/* 20 */   public static final BlockStateBoolean f = BlockSprawling.f;
/*    */   
/* 22 */   private static final Map<EnumDirection, BlockStateBoolean> g = BlockSprawling.g;
/*    */   
/*    */   public BlockHugeMushroom(BlockBase.Info var0) {
/* 25 */     super(var0);
/* 26 */     j(((IBlockData)this.blockStateList.getBlockData()).set(a, Boolean.valueOf(true)).set(b, Boolean.valueOf(true)).set(c, Boolean.valueOf(true)).set(d, Boolean.valueOf(true)).set(e, Boolean.valueOf(true)).set(f, Boolean.valueOf(true)));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData getPlacedState(BlockActionContext var0) {
/* 31 */     IBlockAccess var1 = var0.getWorld();
/* 32 */     BlockPosition var2 = var0.getClickPosition();
/*    */     
/* 34 */     return getBlockData()
/* 35 */       .set(f, Boolean.valueOf((this != var1.getType(var2.down()).getBlock())))
/* 36 */       .set(e, Boolean.valueOf((this != var1.getType(var2.up()).getBlock())))
/* 37 */       .set(a, Boolean.valueOf((this != var1.getType(var2.north()).getBlock())))
/* 38 */       .set(b, Boolean.valueOf((this != var1.getType(var2.east()).getBlock())))
/* 39 */       .set(c, Boolean.valueOf((this != var1.getType(var2.south()).getBlock())))
/* 40 */       .set(d, Boolean.valueOf((this != var1.getType(var2.west()).getBlock())));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/* 46 */     if (var2.a(this)) {
/* 47 */       return var0.set(g.get(var1), Boolean.valueOf(false));
/*    */     }
/* 49 */     return super.updateState(var0, var1, var2, var3, var4, var5);
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(IBlockData var0, EnumBlockRotation var1) {
/* 54 */     return var0
/* 55 */       .set(g.get(var1.a(EnumDirection.NORTH)), var0.get(a))
/* 56 */       .set(g.get(var1.a(EnumDirection.SOUTH)), var0.get(c))
/* 57 */       .set(g.get(var1.a(EnumDirection.EAST)), var0.get(b))
/* 58 */       .set(g.get(var1.a(EnumDirection.WEST)), var0.get(d))
/* 59 */       .set(g.get(var1.a(EnumDirection.UP)), var0.get(e))
/* 60 */       .set(g.get(var1.a(EnumDirection.DOWN)), var0.get(f));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IBlockData a(IBlockData var0, EnumBlockMirror var1) {
/* 66 */     return var0
/* 67 */       .set(g.get(var1.b(EnumDirection.NORTH)), var0.get(a))
/* 68 */       .set(g.get(var1.b(EnumDirection.SOUTH)), var0.get(c))
/* 69 */       .set(g.get(var1.b(EnumDirection.EAST)), var0.get(b))
/* 70 */       .set(g.get(var1.b(EnumDirection.WEST)), var0.get(d))
/* 71 */       .set(g.get(var1.b(EnumDirection.UP)), var0.get(e))
/* 72 */       .set(g.get(var1.b(EnumDirection.DOWN)), var0.get(f));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 78 */     var0.a((IBlockState<?>[])new IBlockState[] { e, f, a, b, c, d });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockHugeMushroom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */