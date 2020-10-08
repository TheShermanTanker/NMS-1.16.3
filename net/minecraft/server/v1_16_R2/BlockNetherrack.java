/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockNetherrack
/*    */   extends Block
/*    */   implements IBlockFragilePlantElement
/*    */ {
/*    */   public BlockNetherrack(BlockBase.Info var0) {
/* 14 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IBlockAccess var0, BlockPosition var1, IBlockData var2, boolean var3) {
/* 19 */     if (!var0.getType(var1.up()).a(var0, var1)) {
/* 20 */       return false;
/*    */     }
/*    */     
/* 23 */     for (BlockPosition var5 : BlockPosition.a(var1.b(-1, -1, -1), var1.b(1, 1, 1))) {
/* 24 */       if (var0.getType(var5).a(TagsBlock.NYLIUM)) {
/* 25 */         return true;
/*    */       }
/*    */     } 
/* 28 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(World var0, Random var1, BlockPosition var2, IBlockData var3) {
/* 33 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(WorldServer var0, Random var1, BlockPosition var2, IBlockData var3) {
/* 38 */     boolean var4 = false;
/* 39 */     boolean var5 = false;
/* 40 */     for (BlockPosition var7 : BlockPosition.a(var2.b(-1, -1, -1), var2.b(1, 1, 1))) {
/* 41 */       IBlockData var8 = var0.getType(var7);
/* 42 */       if (var8.a(Blocks.WARPED_NYLIUM)) {
/* 43 */         var5 = true;
/*    */       }
/*    */       
/* 46 */       if (var8.a(Blocks.CRIMSON_NYLIUM)) {
/* 47 */         var4 = true;
/*    */       }
/*    */       
/* 50 */       if (var5 && var4) {
/*    */         break;
/*    */       }
/*    */     } 
/*    */     
/* 55 */     if (var5 && var4) {
/* 56 */       var0.setTypeAndData(var2, var1.nextBoolean() ? Blocks.WARPED_NYLIUM.getBlockData() : Blocks.CRIMSON_NYLIUM.getBlockData(), 3);
/* 57 */     } else if (var5) {
/* 58 */       var0.setTypeAndData(var2, Blocks.WARPED_NYLIUM.getBlockData(), 3);
/* 59 */     } else if (var4) {
/* 60 */       var0.setTypeAndData(var2, Blocks.CRIMSON_NYLIUM.getBlockData(), 3);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockNetherrack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */