/*    */ package net.minecraft.server.v1_16_R2;
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
/*    */ public class PistonUtil
/*    */ {
/*    */   public static AxisAlignedBB a(AxisAlignedBB var0, EnumDirection var1, double var2) {
/* 15 */     double var4 = var2 * var1.e().a();
/* 16 */     double var6 = Math.min(var4, 0.0D);
/* 17 */     double var8 = Math.max(var4, 0.0D);
/* 18 */     switch (null.a[var1.ordinal()])
/*    */     { case 1:
/* 20 */         return new AxisAlignedBB(var0.minX + var6, var0.minY, var0.minZ, var0.minX + var8, var0.maxY, var0.maxZ);
/*    */       case 2:
/* 22 */         return new AxisAlignedBB(var0.maxX + var6, var0.minY, var0.minZ, var0.maxX + var8, var0.maxY, var0.maxZ);
/*    */       case 3:
/* 24 */         return new AxisAlignedBB(var0.minX, var0.minY + var6, var0.minZ, var0.maxX, var0.minY + var8, var0.maxZ);
/*    */       
/*    */       default:
/* 27 */         return new AxisAlignedBB(var0.minX, var0.maxY + var6, var0.minZ, var0.maxX, var0.maxY + var8, var0.maxZ);
/*    */       case 5:
/* 29 */         return new AxisAlignedBB(var0.minX, var0.minY, var0.minZ + var6, var0.maxX, var0.maxY, var0.minZ + var8);
/*    */       case 6:
/* 31 */         break; }  return new AxisAlignedBB(var0.minX, var0.minY, var0.maxZ + var6, var0.maxX, var0.maxY, var0.maxZ + var8);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PistonUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */