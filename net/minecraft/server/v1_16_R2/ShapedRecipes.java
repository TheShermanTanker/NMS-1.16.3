/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.annotations.VisibleForTesting;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftRecipe;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftShapedRecipe;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.Recipe;
/*     */ import org.bukkit.inventory.RecipeChoice;
/*     */ import org.bukkit.inventory.ShapedRecipe;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ShapedRecipes
/*     */   implements RecipeCrafting
/*     */ {
/*     */   private final int width;
/*     */   private final int height;
/*     */   private final NonNullList<RecipeItemStack> items;
/*     */   private final ItemStack result;
/*     */   private final MinecraftKey key;
/*     */   private final String group;
/*     */   
/*     */   public ShapedRecipes(MinecraftKey minecraftkey, String s, int i, int j, NonNullList<RecipeItemStack> nonnulllist, ItemStack itemstack) {
/*  35 */     this.key = minecraftkey;
/*  36 */     this.group = s;
/*  37 */     this.width = i;
/*  38 */     this.height = j;
/*  39 */     this.items = nonnulllist;
/*  40 */     this.result = itemstack;
/*     */   }
/*     */ 
/*     */   
/*     */   public ShapedRecipe toBukkitRecipe() {
/*  45 */     CraftItemStack result = CraftItemStack.asCraftMirror(this.result);
/*  46 */     CraftShapedRecipe recipe = new CraftShapedRecipe((ItemStack)result, this);
/*  47 */     recipe.setGroup(this.group);
/*     */     
/*  49 */     switch (this.height) {
/*     */       case 1:
/*  51 */         switch (this.width) {
/*     */           case 1:
/*  53 */             recipe.shape(new String[] { "a" });
/*     */             break;
/*     */           case 2:
/*  56 */             recipe.shape(new String[] { "ab" });
/*     */             break;
/*     */           case 3:
/*  59 */             recipe.shape(new String[] { "abc" });
/*     */             break;
/*     */         } 
/*     */         break;
/*     */       case 2:
/*  64 */         switch (this.width) {
/*     */           case 1:
/*  66 */             recipe.shape(new String[] { "a", "b" });
/*     */             break;
/*     */           case 2:
/*  69 */             recipe.shape(new String[] { "ab", "cd" });
/*     */             break;
/*     */           case 3:
/*  72 */             recipe.shape(new String[] { "abc", "def" });
/*     */             break;
/*     */         } 
/*     */         break;
/*     */       case 3:
/*  77 */         switch (this.width) {
/*     */           case 1:
/*  79 */             recipe.shape(new String[] { "a", "b", "c" });
/*     */             break;
/*     */           case 2:
/*  82 */             recipe.shape(new String[] { "ab", "cd", "ef" });
/*     */             break;
/*     */           case 3:
/*  85 */             recipe.shape(new String[] { "abc", "def", "ghi" });
/*     */             break;
/*     */         } 
/*     */         break;
/*     */     } 
/*  90 */     char c = 'a';
/*  91 */     for (RecipeItemStack list : this.items) {
/*  92 */       RecipeChoice choice = CraftRecipe.toBukkit(list);
/*  93 */       if (choice != null) {
/*  94 */         recipe.setIngredient(c, choice);
/*     */       }
/*     */       
/*  97 */       c = (char)(c + 1);
/*     */     } 
/*  99 */     return (ShapedRecipe)recipe;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MinecraftKey getKey() {
/* 105 */     return this.key;
/*     */   }
/*     */ 
/*     */   
/*     */   public RecipeSerializer<?> getRecipeSerializer() {
/* 110 */     return RecipeSerializer.a;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getResult() {
/* 115 */     return this.result;
/*     */   }
/*     */ 
/*     */   
/*     */   public NonNullList<RecipeItemStack> a() {
/* 120 */     return this.items;
/*     */   }
/*     */   
/*     */   public boolean a(InventoryCrafting inventorycrafting, World world) {
/* 124 */     for (int i = 0; i <= inventorycrafting.g() - this.width; i++) {
/* 125 */       for (int j = 0; j <= inventorycrafting.f() - this.height; j++) {
/* 126 */         if (a(inventorycrafting, i, j, true)) {
/* 127 */           return true;
/*     */         }
/*     */         
/* 130 */         if (a(inventorycrafting, i, j, false)) {
/* 131 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 136 */     return false;
/*     */   }
/*     */   
/*     */   private boolean a(InventoryCrafting inventorycrafting, int i, int j, boolean flag) {
/* 140 */     for (int k = 0; k < inventorycrafting.g(); k++) {
/* 141 */       for (int l = 0; l < inventorycrafting.f(); l++) {
/* 142 */         int i1 = k - i;
/* 143 */         int j1 = l - j;
/* 144 */         RecipeItemStack recipeitemstack = RecipeItemStack.a;
/*     */         
/* 146 */         if (i1 >= 0 && j1 >= 0 && i1 < this.width && j1 < this.height) {
/* 147 */           if (flag) {
/* 148 */             recipeitemstack = this.items.get(this.width - i1 - 1 + j1 * this.width);
/*     */           } else {
/* 150 */             recipeitemstack = this.items.get(i1 + j1 * this.width);
/*     */           } 
/*     */         }
/*     */         
/* 154 */         if (!recipeitemstack.test(inventorycrafting.getItem(k + l * inventorycrafting.g()))) {
/* 155 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 160 */     return true;
/*     */   }
/*     */   
/*     */   public ItemStack a(InventoryCrafting inventorycrafting) {
/* 164 */     return getResult().cloneItemStack();
/*     */   }
/*     */   
/*     */   public int i() {
/* 168 */     return this.width;
/*     */   }
/*     */   
/*     */   public int j() {
/* 172 */     return this.height;
/*     */   }
/*     */   
/*     */   private static NonNullList<RecipeItemStack> b(String[] astring, Map<String, RecipeItemStack> map, int i, int j) {
/* 176 */     NonNullList<RecipeItemStack> nonnulllist = NonNullList.a(i * j, RecipeItemStack.a);
/* 177 */     Set<String> set = Sets.newHashSet(map.keySet());
/*     */     
/* 179 */     set.remove(" ");
/*     */     
/* 181 */     for (int k = 0; k < astring.length; k++) {
/* 182 */       for (int l = 0; l < astring[k].length(); l++) {
/* 183 */         String s = astring[k].substring(l, l + 1);
/* 184 */         RecipeItemStack recipeitemstack = map.get(s);
/*     */         
/* 186 */         if (recipeitemstack == null) {
/* 187 */           throw new JsonSyntaxException("Pattern references symbol '" + s + "' but it's not defined in the key");
/*     */         }
/*     */         
/* 190 */         set.remove(s);
/* 191 */         nonnulllist.set(l + i * k, recipeitemstack);
/*     */       } 
/*     */     } 
/*     */     
/* 195 */     if (!set.isEmpty()) {
/* 196 */       throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + set);
/*     */     }
/* 198 */     return nonnulllist;
/*     */   }
/*     */ 
/*     */   
/*     */   @VisibleForTesting
/*     */   static String[] a(String... astring) {
/* 204 */     int i = Integer.MAX_VALUE;
/* 205 */     int j = 0;
/* 206 */     int k = 0;
/* 207 */     int l = 0;
/*     */     
/* 209 */     for (int i1 = 0; i1 < astring.length; i1++) {
/* 210 */       String s = astring[i1];
/*     */       
/* 212 */       i = Math.min(i, a(s));
/* 213 */       int j1 = b(s);
/*     */       
/* 215 */       j = Math.max(j, j1);
/* 216 */       if (j1 < 0) {
/* 217 */         if (k == i1) {
/* 218 */           k++;
/*     */         }
/*     */         
/* 221 */         l++;
/*     */       } else {
/* 223 */         l = 0;
/*     */       } 
/*     */     } 
/*     */     
/* 227 */     if (astring.length == l) {
/* 228 */       return new String[0];
/*     */     }
/* 230 */     String[] astring1 = new String[astring.length - l - k];
/*     */     
/* 232 */     for (int k1 = 0; k1 < astring1.length; k1++) {
/* 233 */       astring1[k1] = astring[k1 + k].substring(i, j + 1);
/*     */     }
/*     */     
/* 236 */     return astring1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int a(String s) {
/*     */     int i;
/* 243 */     for (i = 0; i < s.length() && s.charAt(i) == ' '; i++);
/*     */ 
/*     */ 
/*     */     
/* 247 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int b(String s) {
/*     */     int i;
/* 253 */     for (i = s.length() - 1; i >= 0 && s.charAt(i) == ' '; i--);
/*     */ 
/*     */ 
/*     */     
/* 257 */     return i;
/*     */   }
/*     */   
/*     */   private static String[] b(JsonArray jsonarray) {
/* 261 */     String[] astring = new String[jsonarray.size()];
/*     */     
/* 263 */     if (astring.length > 3)
/* 264 */       throw new JsonSyntaxException("Invalid pattern: too many rows, 3 is maximum"); 
/* 265 */     if (astring.length == 0) {
/* 266 */       throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");
/*     */     }
/* 268 */     for (int i = 0; i < astring.length; i++) {
/* 269 */       String s = ChatDeserializer.a(jsonarray.get(i), "pattern[" + i + "]");
/*     */       
/* 271 */       if (s.length() > 3) {
/* 272 */         throw new JsonSyntaxException("Invalid pattern: too many columns, 3 is maximum");
/*     */       }
/*     */       
/* 275 */       if (i > 0 && astring[0].length() != s.length()) {
/* 276 */         throw new JsonSyntaxException("Invalid pattern: each row must be the same width");
/*     */       }
/*     */       
/* 279 */       astring[i] = s;
/*     */     } 
/*     */     
/* 282 */     return astring;
/*     */   }
/*     */ 
/*     */   
/*     */   private static Map<String, RecipeItemStack> c(JsonObject jsonobject) {
/* 287 */     Map<String, RecipeItemStack> map = Maps.newHashMap();
/* 288 */     Iterator<Map.Entry<String, JsonElement>> iterator = jsonobject.entrySet().iterator();
/*     */     
/* 290 */     while (iterator.hasNext()) {
/* 291 */       Map.Entry<String, JsonElement> entry = iterator.next();
/*     */       
/* 293 */       if (((String)entry.getKey()).length() != 1) {
/* 294 */         throw new JsonSyntaxException("Invalid key entry: '" + (String)entry.getKey() + "' is an invalid symbol (must be 1 character only).");
/*     */       }
/*     */       
/* 297 */       if (" ".equals(entry.getKey())) {
/* 298 */         throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
/*     */       }
/*     */       
/* 301 */       map.put(entry.getKey(), RecipeItemStack.a(entry.getValue()));
/*     */     } 
/*     */     
/* 304 */     map.put(" ", RecipeItemStack.a);
/* 305 */     return map;
/*     */   }
/*     */   
/*     */   public static ItemStack a(JsonObject jsonobject) {
/* 309 */     String s = ChatDeserializer.h(jsonobject, "item");
/* 310 */     Item item = (Item)IRegistry.ITEM.getOptional(new MinecraftKey(s)).orElseThrow(() -> new JsonSyntaxException("Unknown item '" + s + "'"));
/*     */ 
/*     */ 
/*     */     
/* 314 */     if (jsonobject.has("data")) {
/* 315 */       throw new JsonParseException("Disallowed data tag found");
/*     */     }
/* 317 */     int i = ChatDeserializer.a(jsonobject, "count", 1);
/*     */     
/* 319 */     return new ItemStack(item, i);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class a
/*     */     implements RecipeSerializer<ShapedRecipes>
/*     */   {
/*     */     public ShapedRecipes a(MinecraftKey minecraftkey, JsonObject jsonobject) {
/* 329 */       String s = ChatDeserializer.a(jsonobject, "group", "");
/* 330 */       Map<String, RecipeItemStack> map = ShapedRecipes.c(ChatDeserializer.t(jsonobject, "key"));
/* 331 */       String[] astring = ShapedRecipes.a(ShapedRecipes.b(ChatDeserializer.u(jsonobject, "pattern")));
/* 332 */       int i = astring[0].length();
/* 333 */       int j = astring.length;
/* 334 */       NonNullList<RecipeItemStack> nonnulllist = ShapedRecipes.b(astring, map, i, j);
/* 335 */       ItemStack itemstack = ShapedRecipes.a(ChatDeserializer.t(jsonobject, "result"));
/*     */       
/* 337 */       return new ShapedRecipes(minecraftkey, s, i, j, nonnulllist, itemstack);
/*     */     }
/*     */ 
/*     */     
/*     */     public ShapedRecipes a(MinecraftKey minecraftkey, PacketDataSerializer packetdataserializer) {
/* 342 */       int i = packetdataserializer.i();
/* 343 */       int j = packetdataserializer.i();
/* 344 */       String s = packetdataserializer.e(32767);
/* 345 */       NonNullList<RecipeItemStack> nonnulllist = NonNullList.a(i * j, RecipeItemStack.a);
/*     */       
/* 347 */       for (int k = 0; k < nonnulllist.size(); k++) {
/* 348 */         nonnulllist.set(k, RecipeItemStack.b(packetdataserializer));
/*     */       }
/*     */       
/* 351 */       ItemStack itemstack = packetdataserializer.n();
/*     */       
/* 353 */       return new ShapedRecipes(minecraftkey, s, i, j, nonnulllist, itemstack);
/*     */     }
/*     */     
/*     */     public void a(PacketDataSerializer packetdataserializer, ShapedRecipes shapedrecipes) {
/* 357 */       packetdataserializer.d(shapedrecipes.width);
/* 358 */       packetdataserializer.d(shapedrecipes.height);
/* 359 */       packetdataserializer.a(shapedrecipes.group);
/* 360 */       Iterator<RecipeItemStack> iterator = shapedrecipes.items.iterator();
/*     */       
/* 362 */       while (iterator.hasNext()) {
/* 363 */         RecipeItemStack recipeitemstack = iterator.next();
/*     */         
/* 365 */         recipeitemstack.a(packetdataserializer);
/*     */       } 
/*     */       
/* 368 */       packetdataserializer.a(shapedrecipes.result);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ShapedRecipes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */