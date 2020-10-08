/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.stream.Collectors;
/*     */ import java.util.stream.Stream;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.spigotmc.AsyncCatcher;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CraftingManager
/*     */   extends ResourceDataJson
/*     */ {
/*  30 */   private static final Gson a = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
/*  31 */   private static final Logger LOGGER = LogManager.getLogger();
/*  32 */   public Map<Recipes<?>, Object2ObjectLinkedOpenHashMap<MinecraftKey, IRecipe<?>>> recipes = (Map<Recipes<?>, Object2ObjectLinkedOpenHashMap<MinecraftKey, IRecipe<?>>>)ImmutableMap.of();
/*     */   private boolean d;
/*     */   
/*     */   public CraftingManager() {
/*  36 */     super(a, "recipes");
/*     */   }
/*     */   
/*     */   protected void a(Map<MinecraftKey, JsonElement> map, IResourceManager iresourcemanager, GameProfilerFiller gameprofilerfiller) {
/*  40 */     this.d = false;
/*     */     
/*  42 */     Map<Recipes<?>, Object2ObjectLinkedOpenHashMap<MinecraftKey, IRecipe<?>>> map1 = Maps.newHashMap();
/*  43 */     for (Recipes<?> recipeType : IRegistry.RECIPE_TYPE) {
/*  44 */       map1.put(recipeType, new Object2ObjectLinkedOpenHashMap());
/*     */     }
/*     */     
/*  47 */     Iterator<Map.Entry<MinecraftKey, JsonElement>> iterator = map.entrySet().iterator();
/*     */     
/*  49 */     while (iterator.hasNext()) {
/*  50 */       Map.Entry<MinecraftKey, JsonElement> entry = iterator.next();
/*  51 */       MinecraftKey minecraftkey = entry.getKey();
/*     */       
/*     */       try {
/*  54 */         IRecipe<?> irecipe = a(minecraftkey, ChatDeserializer.m(entry.getValue(), "top element"));
/*     */ 
/*     */         
/*  57 */         ((Object2ObjectLinkedOpenHashMap)map1.computeIfAbsent(irecipe.g(), recipes -> new Object2ObjectLinkedOpenHashMap()))
/*     */           
/*  59 */           .putAndMoveToFirst(minecraftkey, irecipe);
/*     */       }
/*  61 */       catch (IllegalArgumentException|com.google.gson.JsonParseException jsonparseexception) {
/*  62 */         LOGGER.error("Parsing error loading recipe {}", minecraftkey, jsonparseexception);
/*     */       } 
/*     */     } 
/*     */     
/*  66 */     this.recipes = (Map<Recipes<?>, Object2ObjectLinkedOpenHashMap<MinecraftKey, IRecipe<?>>>)map1.entrySet().stream().collect(ImmutableMap.toImmutableMap(Map.Entry::getKey, entry1 -> (Object2ObjectLinkedOpenHashMap)entry1.getValue()));
/*     */ 
/*     */     
/*  69 */     LOGGER.info("Loaded {} recipes", Integer.valueOf(map1.size()));
/*     */   }
/*     */ 
/*     */   
/*     */   public void addRecipe(IRecipe<?> irecipe) {
/*  74 */     AsyncCatcher.catchOp("Recipe Add");
/*  75 */     Object2ObjectLinkedOpenHashMap<MinecraftKey, IRecipe<?>> map = this.recipes.get(irecipe.g());
/*     */     
/*  77 */     if (map.containsKey(irecipe.getKey())) {
/*  78 */       throw new IllegalStateException("Duplicate recipe ignored with ID " + irecipe.getKey());
/*     */     }
/*  80 */     map.putAndMoveToFirst(irecipe.getKey(), irecipe);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <C extends IInventory, T extends IRecipe<C>> Optional<T> craft(Recipes<T> recipes, C c0, World world) {
/*  89 */     Optional<T> recipe = b(recipes).values().stream().flatMap(irecipe -> SystemUtils.a(recipes.a(irecipe, world, c0))).findFirst();
/*  90 */     c0.setCurrentRecipe((IRecipe)recipe.orElse(null));
/*     */     
/*  92 */     return recipe;
/*     */   }
/*     */   
/*     */   public <C extends IInventory, T extends IRecipe<C>> List<T> a(Recipes<T> recipes) {
/*  96 */     return (List<T>)b(recipes).values().stream().map(irecipe -> irecipe)
/*     */       
/*  98 */       .collect(Collectors.toList());
/*     */   }
/*     */   
/*     */   public <C extends IInventory, T extends IRecipe<C>> List<T> b(Recipes<T> recipes, C c0, World world) {
/* 102 */     return (List<T>)b(recipes).values().stream().flatMap(irecipe -> SystemUtils.a(recipes.a(irecipe, world, c0)))
/*     */       
/* 104 */       .sorted(Comparator.comparing(irecipe -> irecipe.getResult().j()))
/*     */       
/* 106 */       .collect(Collectors.toList());
/*     */   }
/*     */   
/*     */   private <C extends IInventory, T extends IRecipe<C>> Map<MinecraftKey, IRecipe<C>> b(Recipes<T> recipes) {
/* 110 */     return (Map<MinecraftKey, IRecipe<C>>)this.recipes.getOrDefault(recipes, new Object2ObjectLinkedOpenHashMap());
/*     */   }
/*     */   
/*     */   public <C extends IInventory, T extends IRecipe<C>> NonNullList<ItemStack> c(Recipes<T> recipes, C c0, World world) {
/* 114 */     Optional<T> optional = craft(recipes, c0, world);
/*     */     
/* 116 */     if (optional.isPresent()) {
/* 117 */       return ((IRecipe<C>)optional.get()).b(c0);
/*     */     }
/* 119 */     NonNullList<ItemStack> nonnulllist = NonNullList.a(c0.getSize(), ItemStack.b);
/*     */     
/* 121 */     for (int i = 0; i < nonnulllist.size(); i++) {
/* 122 */       nonnulllist.set(i, c0.getItem(i));
/*     */     }
/*     */     
/* 125 */     return nonnulllist;
/*     */   }
/*     */ 
/*     */   
/*     */   public Optional<? extends IRecipe<?>> getRecipe(MinecraftKey minecraftkey) {
/* 130 */     return this.recipes.values().stream().map(map -> (IRecipe)map.get(minecraftkey))
/*     */       
/* 132 */       .filter(Objects::nonNull).findFirst();
/*     */   }
/*     */   
/*     */   public Collection<IRecipe<?>> b() {
/* 136 */     return (Collection<IRecipe<?>>)this.recipes.values().stream().flatMap(map -> map.values().stream())
/*     */       
/* 138 */       .collect(Collectors.toSet());
/*     */   }
/*     */   
/*     */   public Stream<MinecraftKey> d() {
/* 142 */     return this.recipes.values().stream().flatMap(map -> map.keySet().stream());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static IRecipe<?> a(MinecraftKey minecraftkey, JsonObject jsonobject) {
/* 148 */     String s = ChatDeserializer.h(jsonobject, "type");
/*     */     
/* 150 */     return ((RecipeSerializer<IRecipe<?>>)IRegistry.RECIPE_SERIALIZER.getOptional(new MinecraftKey(s)).orElseThrow(() -> new JsonSyntaxException("Invalid or unsupported recipe type '" + s + "'")))
/*     */       
/* 152 */       .a(minecraftkey, jsonobject);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearRecipes() {
/* 157 */     this.recipes = Maps.newHashMap();
/*     */     
/* 159 */     for (Recipes<?> recipeType : IRegistry.RECIPE_TYPE)
/* 160 */       this.recipes.put(recipeType, new Object2ObjectLinkedOpenHashMap()); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CraftingManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */