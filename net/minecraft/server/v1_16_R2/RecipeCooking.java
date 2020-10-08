/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class RecipeCooking
/*    */   implements IRecipe<IInventory>
/*    */ {
/*    */   protected final Recipes<?> a;
/*    */   protected final MinecraftKey key;
/*    */   protected final String group;
/*    */   protected final RecipeItemStack ingredient;
/*    */   protected final ItemStack result;
/*    */   protected final float experience;
/*    */   protected final int cookingTime;
/*    */   
/*    */   public RecipeCooking(Recipes<?> var0, MinecraftKey var1, String var2, RecipeItemStack var3, ItemStack var4, float var5, int var6) {
/* 20 */     this.a = var0;
/* 21 */     this.key = var1;
/* 22 */     this.group = var2;
/* 23 */     this.ingredient = var3;
/* 24 */     this.result = var4;
/* 25 */     this.experience = var5;
/* 26 */     this.cookingTime = var6;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IInventory var0, World var1) {
/* 31 */     return this.ingredient.test(var0.getItem(0));
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(IInventory var0) {
/* 36 */     return this.result.cloneItemStack();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NonNullList<RecipeItemStack> a() {
/* 46 */     NonNullList<RecipeItemStack> var0 = NonNullList.a();
/* 47 */     var0.add(this.ingredient);
/* 48 */     return var0;
/*    */   }
/*    */   
/*    */   public float getExperience() {
/* 52 */     return this.experience;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getResult() {
/* 57 */     return this.result;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCookingTime() {
/* 66 */     return this.cookingTime;
/*    */   }
/*    */ 
/*    */   
/*    */   public MinecraftKey getKey() {
/* 71 */     return this.key;
/*    */   }
/*    */ 
/*    */   
/*    */   public Recipes<?> g() {
/* 76 */     return this.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RecipeCooking.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */