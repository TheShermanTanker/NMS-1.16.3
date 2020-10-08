/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface IDyeable
/*     */ {
/*     */   default boolean a(ItemStack var0) {
/*  14 */     NBTTagCompound var1 = var0.b("display");
/*  15 */     return (var1 != null && var1.hasKeyOfType("color", 99));
/*     */   }
/*     */   
/*     */   default int b(ItemStack var0) {
/*  19 */     NBTTagCompound var1 = var0.b("display");
/*  20 */     if (var1 != null && var1.hasKeyOfType("color", 99)) {
/*  21 */       return var1.getInt("color");
/*     */     }
/*  23 */     return 10511680;
/*     */   }
/*     */   
/*     */   default void c(ItemStack var0) {
/*  27 */     NBTTagCompound var1 = var0.b("display");
/*  28 */     if (var1 != null && var1.hasKey("color")) {
/*  29 */       var1.remove("color");
/*     */     }
/*     */   }
/*     */   
/*     */   default void a(ItemStack var0, int var1) {
/*  34 */     var0.a("display").setInt("color", var1);
/*     */   }
/*     */   
/*     */   static ItemStack a(ItemStack var0, List<ItemDye> var1) {
/*  38 */     ItemStack var2 = ItemStack.b;
/*  39 */     int[] var3 = new int[3];
/*  40 */     int var4 = 0;
/*  41 */     int var5 = 0;
/*  42 */     IDyeable var6 = null;
/*     */     
/*  44 */     Item var7 = var0.getItem();
/*  45 */     if (var7 instanceof IDyeable) {
/*  46 */       var6 = (IDyeable)var7;
/*     */       
/*  48 */       var2 = var0.cloneItemStack();
/*  49 */       var2.setCount(1);
/*     */ 
/*     */       
/*  52 */       if (var6.a(var0)) {
/*  53 */         int i = var6.b(var2);
/*  54 */         float f1 = (i >> 16 & 0xFF) / 255.0F;
/*  55 */         float f2 = (i >> 8 & 0xFF) / 255.0F;
/*  56 */         float f3 = (i & 0xFF) / 255.0F;
/*     */         
/*  58 */         var4 = (int)(var4 + Math.max(f1, Math.max(f2, f3)) * 255.0F);
/*     */         
/*  60 */         var3[0] = (int)(var3[0] + f1 * 255.0F);
/*  61 */         var3[1] = (int)(var3[1] + f2 * 255.0F);
/*  62 */         var3[2] = (int)(var3[2] + f3 * 255.0F);
/*  63 */         var5++;
/*     */       } 
/*     */ 
/*     */       
/*  67 */       for (ItemDye itemDye : var1) {
/*  68 */         float[] arrayOfFloat = itemDye.d().getColor();
/*  69 */         int i = (int)(arrayOfFloat[0] * 255.0F);
/*  70 */         int j = (int)(arrayOfFloat[1] * 255.0F);
/*  71 */         int k = (int)(arrayOfFloat[2] * 255.0F);
/*     */         
/*  73 */         var4 += Math.max(i, Math.max(j, k));
/*     */         
/*  75 */         var3[0] = var3[0] + i;
/*  76 */         var3[1] = var3[1] + j;
/*  77 */         var3[2] = var3[2] + k;
/*  78 */         var5++;
/*     */       } 
/*     */     } 
/*     */     
/*  82 */     if (var6 == null) {
/*  83 */       return ItemStack.b;
/*     */     }
/*     */     
/*  86 */     int var8 = var3[0] / var5;
/*  87 */     int var9 = var3[1] / var5;
/*  88 */     int var10 = var3[2] / var5;
/*     */     
/*  90 */     float var11 = var4 / var5;
/*  91 */     float var12 = Math.max(var8, Math.max(var9, var10));
/*     */     
/*  93 */     var8 = (int)(var8 * var11 / var12);
/*  94 */     var9 = (int)(var9 * var11 / var12);
/*  95 */     var10 = (int)(var10 * var11 / var12);
/*     */     
/*  97 */     int var13 = var8;
/*  98 */     var13 = (var13 << 8) + var9;
/*  99 */     var13 = (var13 << 8) + var10;
/*     */     
/* 101 */     var6.a(var2, var13);
/* 102 */     return var2;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IDyeable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */