/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class BlockFacingHorizontal
/*    */   extends Block
/*    */ {
/*  8 */   public static final BlockStateDirection FACING = BlockProperties.O;
/*    */   
/*    */   protected BlockFacingHorizontal(BlockBase.Info var0) {
/* 11 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(IBlockData var0, EnumBlockRotation var1) {
/* 16 */     return var0.set(FACING, var1.a((EnumDirection)var0.get(FACING)));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(IBlockData var0, EnumBlockMirror var1) {
/* 21 */     return var0.a(var1.a((EnumDirection)var0.get(FACING)));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockFacingHorizontal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */