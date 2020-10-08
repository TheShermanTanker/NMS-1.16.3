/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InventoryUtils
/*    */ {
/* 14 */   private static final Random a = new Random();
/*    */   
/*    */   public static void dropInventory(World var0, BlockPosition var1, IInventory var2) {
/* 17 */     dropInventory(var0, var1.getX(), var1.getY(), var1.getZ(), var2);
/*    */   }
/*    */   
/*    */   public static void dropEntity(World var0, Entity var1, IInventory var2) {
/* 21 */     dropInventory(var0, var1.locX(), var1.locY(), var1.locZ(), var2);
/*    */   }
/*    */   
/*    */   private static void dropInventory(World var0, double var1, double var3, double var5, IInventory var7) {
/* 25 */     for (int var8 = 0; var8 < var7.getSize(); var8++) {
/* 26 */       dropItem(var0, var1, var3, var5, var7.getItem(var8));
/*    */     }
/*    */   }
/*    */   
/*    */   public static void a(World var0, BlockPosition var1, NonNullList<ItemStack> var2) {
/* 31 */     var2.forEach(var2 -> dropItem(var0, var1.getX(), var1.getY(), var1.getZ(), var2));
/*    */   }
/*    */   
/*    */   public static void dropItem(World var0, double var1, double var3, double var5, ItemStack var7) {
/* 35 */     double var8 = EntityTypes.ITEM.j();
/* 36 */     double var10 = 1.0D - var8;
/* 37 */     double var12 = var8 / 2.0D;
/* 38 */     double var14 = Math.floor(var1) + a.nextDouble() * var10 + var12;
/* 39 */     double var16 = Math.floor(var3) + a.nextDouble() * var10;
/* 40 */     double var18 = Math.floor(var5) + a.nextDouble() * var10 + var12;
/*    */     
/* 42 */     while (!var7.isEmpty()) {
/* 43 */       EntityItem var20 = new EntityItem(var0, var14, var16, var18, var7.cloneAndSubtract(a.nextInt(21) + 10));
/*    */       
/* 45 */       float var21 = 0.05F;
/* 46 */       var20.setMot(a
/* 47 */           .nextGaussian() * 0.05000000074505806D, a
/* 48 */           .nextGaussian() * 0.05000000074505806D + 0.20000000298023224D, a
/* 49 */           .nextGaussian() * 0.05000000074505806D);
/*    */ 
/*    */       
/* 52 */       var0.addEntity(var20);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\InventoryUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */