/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ShulkerUtil
/*    */ {
/*    */   public static AxisAlignedBB a(BlockPosition var0, EnumDirection var1) {
/* 12 */     return VoxelShapes.b().getBoundingBox().b((0.5F * var1
/* 13 */         .getAdjacentX()), (0.5F * var1
/* 14 */         .getAdjacentY()), (0.5F * var1
/* 15 */         .getAdjacentZ()))
/* 16 */       .a(var1
/* 17 */         .getAdjacentX(), var1
/* 18 */         .getAdjacentY(), var1
/* 19 */         .getAdjacentZ())
/* 20 */       .a(var0.shift(var1));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ShulkerUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */