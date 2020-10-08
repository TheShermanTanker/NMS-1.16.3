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
/*    */ public class RecipiesShield
/*    */   extends IRecipeComplex
/*    */ {
/*    */   public RecipiesShield(MinecraftKey var0) {
/* 15 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(InventoryCrafting var0, World var1) {
/* 20 */     ItemStack var2 = ItemStack.b;
/* 21 */     ItemStack var3 = ItemStack.b;
/*    */     
/* 23 */     for (int var4 = 0; var4 < var0.getSize(); var4++) {
/* 24 */       ItemStack var5 = var0.getItem(var4);
/* 25 */       if (!var5.isEmpty())
/*    */       {
/*    */ 
/*    */         
/* 29 */         if (var5.getItem() instanceof ItemBanner) {
/* 30 */           if (!var3.isEmpty())
/*    */           {
/* 32 */             return false;
/*    */           }
/* 34 */           var3 = var5;
/* 35 */         } else if (var5.getItem() == Items.SHIELD) {
/* 36 */           if (!var2.isEmpty())
/*    */           {
/* 38 */             return false;
/*    */           }
/* 40 */           if (var5.b("BlockEntityTag") != null)
/*    */           {
/* 42 */             return false;
/*    */           }
/* 44 */           var2 = var5;
/*    */         } else {
/*    */           
/* 47 */           return false;
/*    */         } 
/*    */       }
/*    */     } 
/* 51 */     if (var2.isEmpty() || var3.isEmpty())
/*    */     {
/* 53 */       return false;
/*    */     }
/*    */     
/* 56 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(InventoryCrafting var0) {
/* 61 */     ItemStack var1 = ItemStack.b;
/* 62 */     ItemStack var2 = ItemStack.b;
/*    */     
/* 64 */     for (int i = 0; i < var0.getSize(); i++) {
/* 65 */       ItemStack itemStack = var0.getItem(i);
/* 66 */       if (!itemStack.isEmpty())
/*    */       {
/*    */ 
/*    */         
/* 70 */         if (itemStack.getItem() instanceof ItemBanner) {
/* 71 */           var1 = itemStack;
/* 72 */         } else if (itemStack.getItem() == Items.SHIELD) {
/* 73 */           var2 = itemStack.cloneItemStack();
/*    */         } 
/*    */       }
/*    */     } 
/* 77 */     if (var2.isEmpty()) {
/* 78 */       return var2;
/*    */     }
/*    */     
/* 81 */     NBTTagCompound var3 = var1.b("BlockEntityTag");
/* 82 */     NBTTagCompound var4 = (var3 == null) ? new NBTTagCompound() : var3.clone();
/*    */     
/* 84 */     var4.setInt("Base", ((ItemBanner)var1.getItem()).b().getColorIndex());
/*    */     
/* 86 */     var2.a("BlockEntityTag", var4);
/*    */     
/* 88 */     return var2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RecipeSerializer<?> getRecipeSerializer() {
/* 98 */     return RecipeSerializer.l;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RecipiesShield.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */