/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import co.aikar.util.Counter;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftRecipe;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftShapelessRecipe;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.Recipe;
/*     */ import org.bukkit.inventory.ShapelessRecipe;
/*     */ 
/*     */ public class ShapelessRecipes implements RecipeCrafting {
/*     */   private final MinecraftKey key;
/*     */   
/*     */   public ShapelessRecipes(MinecraftKey minecraftkey, String s, ItemStack itemstack, NonNullList<RecipeItemStack> nonnulllist) {
/*  22 */     this.key = minecraftkey;
/*  23 */     this.group = s;
/*  24 */     this.result = itemstack;
/*  25 */     this.ingredients = nonnulllist;
/*     */   }
/*     */   private final String group; private final ItemStack result;
/*     */   private final NonNullList<RecipeItemStack> ingredients;
/*     */   
/*     */   public ShapelessRecipe toBukkitRecipe() {
/*  31 */     CraftItemStack result = CraftItemStack.asCraftMirror(this.result);
/*  32 */     CraftShapelessRecipe recipe = new CraftShapelessRecipe((ItemStack)result, this);
/*  33 */     recipe.setGroup(this.group);
/*     */     
/*  35 */     for (RecipeItemStack list : this.ingredients) {
/*  36 */       recipe.addIngredient(CraftRecipe.toBukkit(list));
/*     */     }
/*  38 */     return (ShapelessRecipe)recipe;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MinecraftKey getKey() {
/*  44 */     return this.key;
/*     */   }
/*     */ 
/*     */   
/*     */   public RecipeSerializer<?> getRecipeSerializer() {
/*  49 */     return RecipeSerializer.b;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getResult() {
/*  54 */     return this.result;
/*     */   }
/*     */ 
/*     */   
/*     */   public NonNullList<RecipeItemStack> a() {
/*  59 */     return this.ingredients;
/*     */   }
/*     */   
/*     */   public boolean a(InventoryCrafting inventorycrafting, World world) {
/*  63 */     AutoRecipeStackManager autorecipestackmanager = new AutoRecipeStackManager();
/*  64 */     int i = 0;
/*     */ 
/*     */     
/*  67 */     List<ItemStack> providedItems = new ArrayList<>();
/*  68 */     Counter<ItemStack> matchedProvided = new Counter();
/*  69 */     Counter<RecipeItemStack> matchedIngredients = new Counter();
/*     */     
/*  71 */     for (int j = 0; j < inventorycrafting.getSize(); j++) {
/*  72 */       ItemStack itemstack = inventorycrafting.getItem(j);
/*     */       
/*  74 */       if (!itemstack.isEmpty()) {
/*     */         
/*  76 */         itemstack = itemstack.cloneItemStack();
/*  77 */         providedItems.add(itemstack);
/*  78 */         for (RecipeItemStack ingredient : this.ingredients) {
/*  79 */           if (ingredient.test(itemstack)) {
/*  80 */             matchedProvided.increment(itemstack);
/*  81 */             matchedIngredients.increment(ingredient);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  89 */     List<RecipeItemStack> ingredients = new ArrayList<>(this.ingredients);
/*  90 */     providedItems.sort(Comparator.<ItemStack>comparingInt(c -> (int)matchedProvided.getCount(c)).reversed());
/*  91 */     ingredients.sort(Comparator.comparingInt(c -> (int)matchedIngredients.getCount(c)));
/*     */ 
/*     */     
/*  94 */     for (ItemStack provided : providedItems) {
/*  95 */       Iterator<RecipeItemStack> itIngredient; for (itIngredient = ingredients.iterator(); itIngredient.hasNext(); ) {
/*  96 */         RecipeItemStack ingredient = itIngredient.next();
/*  97 */         if (ingredient.test(provided)) {
/*  98 */           itIngredient.remove();
/*     */         }
/*     */       } 
/*     */       
/* 102 */       return false;
/*     */     } 
/* 104 */     return ingredients.isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(InventoryCrafting inventorycrafting) {
/* 109 */     return this.result.cloneItemStack();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class a
/*     */     implements RecipeSerializer<ShapelessRecipes>
/*     */   {
/*     */     public ShapelessRecipes a(MinecraftKey minecraftkey, JsonObject jsonobject) {
/* 118 */       String s = ChatDeserializer.a(jsonobject, "group", "");
/* 119 */       NonNullList<RecipeItemStack> nonnulllist = a(ChatDeserializer.u(jsonobject, "ingredients"));
/*     */       
/* 121 */       if (nonnulllist.isEmpty())
/* 122 */         throw new JsonParseException("No ingredients for shapeless recipe"); 
/* 123 */       if (nonnulllist.size() > 9) {
/* 124 */         throw new JsonParseException("Too many ingredients for shapeless recipe");
/*     */       }
/* 126 */       ItemStack itemstack = ShapedRecipes.a(ChatDeserializer.t(jsonobject, "result"));
/*     */       
/* 128 */       return new ShapelessRecipes(minecraftkey, s, itemstack, nonnulllist);
/*     */     }
/*     */ 
/*     */     
/*     */     private static NonNullList<RecipeItemStack> a(JsonArray jsonarray) {
/* 133 */       NonNullList<RecipeItemStack> nonnulllist = NonNullList.a();
/*     */       
/* 135 */       for (int i = 0; i < jsonarray.size(); i++) {
/* 136 */         RecipeItemStack recipeitemstack = RecipeItemStack.a(jsonarray.get(i));
/*     */         
/* 138 */         if (!recipeitemstack.d()) {
/* 139 */           nonnulllist.add(recipeitemstack);
/*     */         }
/*     */       } 
/*     */       
/* 143 */       return nonnulllist;
/*     */     }
/*     */ 
/*     */     
/*     */     public ShapelessRecipes a(MinecraftKey minecraftkey, PacketDataSerializer packetdataserializer) {
/* 148 */       String s = packetdataserializer.e(32767);
/* 149 */       int i = packetdataserializer.i();
/* 150 */       NonNullList<RecipeItemStack> nonnulllist = NonNullList.a(i, RecipeItemStack.a);
/*     */       
/* 152 */       for (int j = 0; j < nonnulllist.size(); j++) {
/* 153 */         nonnulllist.set(j, RecipeItemStack.b(packetdataserializer));
/*     */       }
/*     */       
/* 156 */       ItemStack itemstack = packetdataserializer.n();
/*     */       
/* 158 */       return new ShapelessRecipes(minecraftkey, s, itemstack, nonnulllist);
/*     */     }
/*     */     
/*     */     public void a(PacketDataSerializer packetdataserializer, ShapelessRecipes shapelessrecipes) {
/* 162 */       packetdataserializer.a(shapelessrecipes.group);
/* 163 */       packetdataserializer.d(shapelessrecipes.ingredients.size());
/* 164 */       Iterator<RecipeItemStack> iterator = shapelessrecipes.ingredients.iterator();
/*     */       
/* 166 */       while (iterator.hasNext()) {
/* 167 */         RecipeItemStack recipeitemstack = iterator.next();
/*     */         
/* 169 */         recipeitemstack.a(packetdataserializer);
/*     */       } 
/*     */       
/* 172 */       packetdataserializer.a(shapelessrecipes.result);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ShapelessRecipes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */