/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class RecipeSingleItem
/*     */   implements IRecipe<IInventory>
/*     */ {
/*     */   protected final RecipeItemStack ingredient;
/*     */   protected final ItemStack result;
/*     */   private final Recipes<?> e;
/*     */   private final RecipeSerializer<?> f;
/*     */   protected final MinecraftKey key;
/*     */   protected final String group;
/*     */   
/*     */   public RecipeSingleItem(Recipes<?> var0, RecipeSerializer<?> var1, MinecraftKey var2, String var3, RecipeItemStack var4, ItemStack var5) {
/*  21 */     this.e = var0;
/*  22 */     this.f = var1;
/*  23 */     this.key = var2;
/*  24 */     this.group = var3;
/*  25 */     this.ingredient = var4;
/*  26 */     this.result = var5;
/*     */   }
/*     */ 
/*     */   
/*     */   public Recipes<?> g() {
/*  31 */     return this.e;
/*     */   }
/*     */ 
/*     */   
/*     */   public RecipeSerializer<?> getRecipeSerializer() {
/*  36 */     return this.f;
/*     */   }
/*     */ 
/*     */   
/*     */   public MinecraftKey getKey() {
/*  41 */     return this.key;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getResult() {
/*  51 */     return this.result;
/*     */   }
/*     */ 
/*     */   
/*     */   public NonNullList<RecipeItemStack> a() {
/*  56 */     NonNullList<RecipeItemStack> var0 = NonNullList.a();
/*  57 */     var0.add(this.ingredient);
/*  58 */     return var0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack a(IInventory var0) {
/*  68 */     return this.result.cloneItemStack();
/*     */   }
/*     */   
/*     */   public static class a<T extends RecipeSingleItem> implements RecipeSerializer<T> {
/*     */     final a<T> v;
/*     */     
/*     */     protected a(a<T> var0) {
/*  75 */       this.v = var0;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public T a(MinecraftKey var0, JsonObject var1) {
/*     */       RecipeItemStack var3;
/*  84 */       String var2 = ChatDeserializer.a(var1, "group", "");
/*     */ 
/*     */       
/*  87 */       if (ChatDeserializer.d(var1, "ingredient")) {
/*  88 */         var3 = RecipeItemStack.a((JsonElement)ChatDeserializer.u(var1, "ingredient"));
/*     */       } else {
/*  90 */         var3 = RecipeItemStack.a((JsonElement)ChatDeserializer.t(var1, "ingredient"));
/*     */       } 
/*     */       
/*  93 */       String var4 = ChatDeserializer.h(var1, "result");
/*  94 */       int var5 = ChatDeserializer.n(var1, "count");
/*  95 */       ItemStack var6 = new ItemStack(IRegistry.ITEM.get(new MinecraftKey(var4)), var5);
/*     */       
/*  97 */       return this.v.create(var0, var2, var3, var6);
/*     */     }
/*     */ 
/*     */     
/*     */     public T a(MinecraftKey var0, PacketDataSerializer var1) {
/* 102 */       String var2 = var1.e(32767);
/* 103 */       RecipeItemStack var3 = RecipeItemStack.b(var1);
/* 104 */       ItemStack var4 = var1.n();
/* 105 */       return this.v.create(var0, var2, var3, var4);
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(PacketDataSerializer var0, T var1) {
/* 110 */       var0.a(((RecipeSingleItem)var1).group);
/* 111 */       ((RecipeSingleItem)var1).ingredient.a(var0);
/* 112 */       var0.a(((RecipeSingleItem)var1).result);
/*     */     }
/*     */     
/*     */     static interface a<T extends RecipeSingleItem> {
/*     */       T create(MinecraftKey param2MinecraftKey, String param2String, RecipeItemStack param2RecipeItemStack, ItemStack param2ItemStack);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RecipeSingleItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */