/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RecipeBookClone
/*     */   extends IRecipeComplex
/*     */ {
/*     */   public RecipeBookClone(MinecraftKey var0) {
/*  14 */     super(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(InventoryCrafting var0, World var1) {
/*  19 */     int var2 = 0;
/*  20 */     ItemStack var3 = ItemStack.b;
/*     */     
/*  22 */     for (int var4 = 0; var4 < var0.getSize(); var4++) {
/*  23 */       ItemStack var5 = var0.getItem(var4);
/*  24 */       if (!var5.isEmpty())
/*     */       {
/*     */ 
/*     */         
/*  28 */         if (var5.getItem() == Items.WRITTEN_BOOK) {
/*  29 */           if (!var3.isEmpty()) {
/*  30 */             return false;
/*     */           }
/*  32 */           var3 = var5;
/*  33 */         } else if (var5.getItem() == Items.WRITABLE_BOOK) {
/*  34 */           var2++;
/*     */         } else {
/*  36 */           return false;
/*     */         } 
/*     */       }
/*     */     } 
/*  40 */     return (!var3.isEmpty() && var3.hasTag() && var2 > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(InventoryCrafting var0) {
/*  45 */     int var1 = 0;
/*  46 */     ItemStack var2 = ItemStack.b;
/*     */     
/*  48 */     for (int i = 0; i < var0.getSize(); i++) {
/*  49 */       ItemStack itemStack = var0.getItem(i);
/*  50 */       if (!itemStack.isEmpty())
/*     */       {
/*     */ 
/*     */         
/*  54 */         if (itemStack.getItem() == Items.WRITTEN_BOOK) {
/*  55 */           if (!var2.isEmpty()) {
/*  56 */             return ItemStack.b;
/*     */           }
/*  58 */           var2 = itemStack;
/*  59 */         } else if (itemStack.getItem() == Items.WRITABLE_BOOK) {
/*  60 */           var1++;
/*     */         } else {
/*  62 */           return ItemStack.b;
/*     */         } 
/*     */       }
/*     */     } 
/*  66 */     if (var2.isEmpty() || !var2.hasTag() || var1 < 1 || ItemWrittenBook.d(var2) >= 2) {
/*  67 */       return ItemStack.b;
/*     */     }
/*     */     
/*  70 */     ItemStack var3 = new ItemStack(Items.WRITTEN_BOOK, var1);
/*     */     
/*  72 */     NBTTagCompound var4 = var2.getTag().clone();
/*     */     
/*  74 */     var4.setInt("generation", ItemWrittenBook.d(var2) + 1);
/*  75 */     var3.setTag(var4);
/*     */     
/*  77 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   public NonNullList<ItemStack> b(InventoryCrafting var0) {
/*  82 */     NonNullList<ItemStack> var1 = NonNullList.a(var0.getSize(), ItemStack.b);
/*     */     
/*  84 */     for (int var2 = 0; var2 < var1.size(); var2++) {
/*  85 */       ItemStack var3 = var0.getItem(var2);
/*  86 */       if (var3.getItem().p()) {
/*  87 */         var1.set(var2, new ItemStack(var3.getItem().getCraftingRemainingItem()));
/*  88 */       } else if (var3.getItem() instanceof ItemWrittenBook) {
/*  89 */         ItemStack var4 = var3.cloneItemStack();
/*  90 */         var4.setCount(1);
/*  91 */         var1.set(var2, var4);
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*  96 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public RecipeSerializer<?> getRecipeSerializer() {
/* 101 */     return RecipeSerializer.d;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RecipeBookClone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */