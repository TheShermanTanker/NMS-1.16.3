/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RecipeBannerDuplicate
/*     */   extends IRecipeComplex
/*     */ {
/*     */   public RecipeBannerDuplicate(MinecraftKey var0) {
/*  15 */     super(var0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(InventoryCrafting var0, World var1) {
/*  21 */     EnumColor var2 = null;
/*  22 */     ItemStack var3 = null;
/*  23 */     ItemStack var4 = null;
/*     */     
/*  25 */     for (int var5 = 0; var5 < var0.getSize(); var5++) {
/*  26 */       ItemStack var6 = var0.getItem(var5);
/*  27 */       Item var7 = var6.getItem();
/*  28 */       if (var7 instanceof ItemBanner) {
/*     */ 
/*     */ 
/*     */         
/*  32 */         ItemBanner var8 = (ItemBanner)var7;
/*     */         
/*  34 */         if (var2 == null) {
/*  35 */           var2 = var8.b();
/*  36 */         } else if (var2 != var8.b()) {
/*  37 */           return false;
/*     */         } 
/*     */         
/*  40 */         int var9 = TileEntityBanner.b(var6);
/*  41 */         if (var9 > 6) {
/*  42 */           return false;
/*     */         }
/*     */         
/*  45 */         if (var9 > 0) {
/*  46 */           if (var3 == null) {
/*  47 */             var3 = var6;
/*     */           } else {
/*  49 */             return false;
/*     */           }
/*     */         
/*  52 */         } else if (var4 == null) {
/*  53 */           var4 = var6;
/*     */         } else {
/*  55 */           return false;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  60 */     return (var3 != null && var4 != null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack a(InventoryCrafting var0) {
/*  66 */     for (int var1 = 0; var1 < var0.getSize(); var1++) {
/*  67 */       ItemStack var2 = var0.getItem(var1);
/*  68 */       if (!var2.isEmpty()) {
/*     */ 
/*     */         
/*  71 */         int var3 = TileEntityBanner.b(var2);
/*  72 */         if (var3 > 0 && var3 <= 6) {
/*  73 */           ItemStack var4 = var2.cloneItemStack();
/*  74 */           var4.setCount(1);
/*  75 */           return var4;
/*     */         } 
/*     */       } 
/*     */     } 
/*  79 */     return ItemStack.b;
/*     */   }
/*     */ 
/*     */   
/*     */   public NonNullList<ItemStack> b(InventoryCrafting var0) {
/*  84 */     NonNullList<ItemStack> var1 = NonNullList.a(var0.getSize(), ItemStack.b);
/*     */     
/*  86 */     for (int var2 = 0; var2 < var1.size(); var2++) {
/*  87 */       ItemStack var3 = var0.getItem(var2);
/*  88 */       if (!var3.isEmpty()) {
/*  89 */         if (var3.getItem().p()) {
/*  90 */           var1.set(var2, new ItemStack(var3.getItem().getCraftingRemainingItem()));
/*  91 */         } else if (var3.hasTag() && 
/*  92 */           TileEntityBanner.b(var3) > 0) {
/*  93 */           ItemStack var4 = var3.cloneItemStack();
/*  94 */           var4.setCount(1);
/*  95 */           var1.set(var2, var4);
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 101 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public RecipeSerializer<?> getRecipeSerializer() {
/* 106 */     return RecipeSerializer.k;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RecipeBannerDuplicate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */