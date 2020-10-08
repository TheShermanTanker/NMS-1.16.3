/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RecipeFireworks
/*    */   extends IRecipeComplex
/*    */ {
/* 13 */   private static final RecipeItemStack a = RecipeItemStack.a(new IMaterial[] { Items.PAPER });
/* 14 */   private static final RecipeItemStack b = RecipeItemStack.a(new IMaterial[] { Items.GUNPOWDER });
/* 15 */   private static final RecipeItemStack c = RecipeItemStack.a(new IMaterial[] { Items.FIREWORK_STAR });
/*    */   
/*    */   public RecipeFireworks(MinecraftKey var0) {
/* 18 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(InventoryCrafting var0, World var1) {
/* 23 */     boolean var2 = false;
/* 24 */     int var3 = 0;
/*    */     
/* 26 */     for (int var4 = 0; var4 < var0.getSize(); var4++) {
/* 27 */       ItemStack var5 = var0.getItem(var4);
/* 28 */       if (!var5.isEmpty())
/*    */       {
/*    */ 
/*    */         
/* 32 */         if (a.test(var5)) {
/* 33 */           if (var2) {
/* 34 */             return false;
/*    */           }
/* 36 */           var2 = true;
/* 37 */         } else if (b.test(var5)) {
/* 38 */           var3++;
/* 39 */           if (var3 > 3) {
/* 40 */             return false;
/*    */           }
/* 42 */         } else if (!c.test(var5)) {
/* 43 */           return false;
/*    */         } 
/*    */       }
/*    */     } 
/* 47 */     return (var2 && var3 >= 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(InventoryCrafting var0) {
/* 52 */     ItemStack var1 = new ItemStack(Items.FIREWORK_ROCKET, 3);
/* 53 */     NBTTagCompound var2 = var1.a("Fireworks");
/* 54 */     NBTTagList var3 = new NBTTagList();
/*    */     
/* 56 */     int var4 = 0;
/*    */     
/* 58 */     for (int var5 = 0; var5 < var0.getSize(); var5++) {
/* 59 */       ItemStack var6 = var0.getItem(var5);
/* 60 */       if (!var6.isEmpty())
/*    */       {
/*    */ 
/*    */         
/* 64 */         if (b.test(var6)) {
/* 65 */           var4++;
/* 66 */         } else if (c.test(var6)) {
/* 67 */           NBTTagCompound var7 = var6.b("Explosion");
/* 68 */           if (var7 != null) {
/* 69 */             var3.add(var7);
/*    */           }
/*    */         } 
/*    */       }
/*    */     } 
/* 74 */     var2.setByte("Flight", (byte)var4);
/* 75 */     if (!var3.isEmpty()) {
/* 76 */       var2.set("Explosions", var3);
/*    */     }
/*    */     
/* 79 */     return var1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack getResult() {
/* 89 */     return new ItemStack(Items.FIREWORK_ROCKET);
/*    */   }
/*    */ 
/*    */   
/*    */   public RecipeSerializer<?> getRecipeSerializer() {
/* 94 */     return RecipeSerializer.g;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RecipeFireworks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */