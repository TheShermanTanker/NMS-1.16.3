/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.function.Predicate;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ContainerUtil
/*    */ {
/*    */   public static ItemStack a(List<ItemStack> var0, int var1, int var2) {
/* 14 */     if (var1 < 0 || var1 >= var0.size() || ((ItemStack)var0.get(var1)).isEmpty() || var2 <= 0) {
/* 15 */       return ItemStack.b;
/*    */     }
/*    */     
/* 18 */     return ((ItemStack)var0.get(var1)).cloneAndSubtract(var2);
/*    */   }
/*    */   
/*    */   public static ItemStack a(List<ItemStack> var0, int var1) {
/* 22 */     if (var1 < 0 || var1 >= var0.size()) {
/* 23 */       return ItemStack.b;
/*    */     }
/*    */     
/* 26 */     return var0.set(var1, ItemStack.b);
/*    */   }
/*    */   
/*    */   public static NBTTagCompound a(NBTTagCompound var0, NonNullList<ItemStack> var1) {
/* 30 */     return a(var0, var1, true);
/*    */   }
/*    */   
/*    */   public static NBTTagCompound a(NBTTagCompound var0, NonNullList<ItemStack> var1, boolean var2) {
/* 34 */     NBTTagList var3 = new NBTTagList();
/* 35 */     for (int var4 = 0; var4 < var1.size(); var4++) {
/* 36 */       ItemStack var5 = var1.get(var4);
/* 37 */       if (!var5.isEmpty()) {
/* 38 */         NBTTagCompound var6 = new NBTTagCompound();
/* 39 */         var6.setByte("Slot", (byte)var4);
/* 40 */         var5.save(var6);
/* 41 */         var3.add(var6);
/*    */       } 
/*    */     } 
/* 44 */     if (!var3.isEmpty() || var2) {
/* 45 */       var0.set("Items", var3);
/*    */     }
/* 47 */     return var0;
/*    */   }
/*    */   
/*    */   public static void b(NBTTagCompound var0, NonNullList<ItemStack> var1) {
/* 51 */     NBTTagList var2 = var0.getList("Items", 10);
/* 52 */     for (int var3 = 0; var3 < var2.size(); var3++) {
/* 53 */       NBTTagCompound var4 = var2.getCompound(var3);
/* 54 */       int var5 = var4.getByte("Slot") & 0xFF;
/* 55 */       if (var5 >= 0 && var5 < var1.size()) {
/* 56 */         var1.set(var5, ItemStack.a(var4));
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public static int a(IInventory var0, Predicate<ItemStack> var1, int var2, boolean var3) {
/* 62 */     int var4 = 0;
/* 63 */     for (int var5 = 0; var5 < var0.getSize(); var5++) {
/* 64 */       ItemStack var6 = var0.getItem(var5);
/* 65 */       int var7 = a(var6, var1, var2 - var4, var3);
/* 66 */       if (var7 > 0 && !var3 && var6.isEmpty()) {
/* 67 */         var0.setItem(var5, ItemStack.b);
/*    */       }
/* 69 */       var4 += var7;
/*    */     } 
/* 71 */     return var4;
/*    */   }
/*    */ 
/*    */   
/*    */   public static int a(ItemStack var0, Predicate<ItemStack> var1, int var2, boolean var3) {
/* 76 */     if (var0.isEmpty() || !var1.test(var0)) {
/* 77 */       return 0;
/*    */     }
/*    */     
/* 80 */     if (var3) {
/* 81 */       return var0.getCount();
/*    */     }
/*    */     
/* 84 */     int var4 = (var2 < 0) ? var0.getCount() : Math.min(var2, var0.getCount());
/* 85 */     var0.subtract(var4);
/* 86 */     return var4;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ContainerUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */