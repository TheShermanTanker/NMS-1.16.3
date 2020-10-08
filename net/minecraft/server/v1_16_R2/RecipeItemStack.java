/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import it.unimi.dsi.fastutil.ints.IntArrayList;
/*     */ import it.unimi.dsi.fastutil.ints.IntComparators;
/*     */ import it.unimi.dsi.fastutil.ints.IntList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.stream.Stream;
/*     */ import java.util.stream.StreamSupport;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public final class RecipeItemStack implements Predicate<ItemStack> {
/*  24 */   public static final RecipeItemStack a = new RecipeItemStack(Stream.empty());
/*     */   private final Provider[] b;
/*     */   public ItemStack[] choices;
/*     */   private IntList d;
/*     */   public boolean exact;
/*     */   
/*     */   public RecipeItemStack(Stream<? extends Provider> stream) {
/*  31 */     this.b = stream.<Provider>toArray(i -> new Provider[i]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildChoices() {
/*  37 */     if (this.choices == null) {
/*  38 */       this
/*     */         
/*  40 */         .choices = (ItemStack[])Arrays.<Provider>stream(this.b).flatMap(recipeitemstack_provider -> recipeitemstack_provider.a().stream()).distinct().toArray(i -> new ItemStack[i]);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean test(@Nullable ItemStack itemstack) {
/*  48 */     if (itemstack == null) {
/*  49 */       return false;
/*     */     }
/*  51 */     buildChoices();
/*  52 */     if (this.choices.length == 0) {
/*  53 */       return itemstack.isEmpty();
/*     */     }
/*  55 */     ItemStack[] aitemstack = this.choices;
/*  56 */     int i = aitemstack.length;
/*     */     
/*  58 */     for (int j = 0; j < i; j++) {
/*  59 */       ItemStack itemstack1 = aitemstack[j];
/*     */ 
/*     */       
/*  62 */       if (this.exact) {
/*  63 */         if (itemstack1.getItem() == itemstack.getItem() && ItemStack.equals(itemstack, itemstack1)) {
/*  64 */           return true;
/*     */ 
/*     */         
/*     */         }
/*     */       
/*     */       }
/*  70 */       else if (itemstack1.getItem() == itemstack.getItem()) {
/*  71 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/*  75 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IntList b() {
/*  81 */     if (this.d == null) {
/*  82 */       buildChoices();
/*  83 */       this.d = (IntList)new IntArrayList(this.choices.length);
/*  84 */       ItemStack[] aitemstack = this.choices;
/*  85 */       int i = aitemstack.length;
/*     */       
/*  87 */       for (int j = 0; j < i; j++) {
/*  88 */         ItemStack itemstack = aitemstack[j];
/*     */         
/*  90 */         this.d.add(AutoRecipeStackManager.c(itemstack));
/*     */       } 
/*     */       
/*  93 */       this.d.sort((Comparator)IntComparators.NATURAL_COMPARATOR);
/*     */     } 
/*     */     
/*  96 */     return this.d;
/*     */   }
/*     */   
/*     */   public void a(PacketDataSerializer packetdataserializer) {
/* 100 */     buildChoices();
/* 101 */     packetdataserializer.d(this.choices.length);
/*     */     
/* 103 */     for (int i = 0; i < this.choices.length; i++) {
/* 104 */       packetdataserializer.a(this.choices[i]);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonElement c() {
/* 110 */     if (this.b.length == 1) {
/* 111 */       return (JsonElement)this.b[0].b();
/*     */     }
/* 113 */     JsonArray jsonarray = new JsonArray();
/* 114 */     Provider[] arecipeitemstack_provider = this.b;
/* 115 */     int i = arecipeitemstack_provider.length;
/*     */     
/* 117 */     for (int j = 0; j < i; j++) {
/* 118 */       Provider recipeitemstack_provider = arecipeitemstack_provider[j];
/*     */       
/* 120 */       jsonarray.add((JsonElement)recipeitemstack_provider.b());
/*     */     } 
/*     */     
/* 123 */     return (JsonElement)jsonarray;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean d() {
/* 128 */     return (this.b.length == 0 && (this.choices == null || this.choices.length == 0) && (this.d == null || this.d.isEmpty()));
/*     */   }
/*     */   
/*     */   private static RecipeItemStack b(Stream<? extends Provider> stream) {
/* 132 */     RecipeItemStack recipeitemstack = new RecipeItemStack(stream);
/*     */     
/* 134 */     return (recipeitemstack.b.length == 0) ? a : recipeitemstack;
/*     */   }
/*     */   
/*     */   public static RecipeItemStack a(IMaterial... aimaterial) {
/* 138 */     return a(Arrays.<IMaterial>stream(aimaterial).map(ItemStack::new));
/*     */   }
/*     */   
/*     */   public static RecipeItemStack a(Stream<ItemStack> stream) {
/* 142 */     return b(stream.filter(itemstack -> !itemstack.isEmpty())
/*     */         
/* 144 */         .map(itemstack -> new StackProvider(itemstack)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static RecipeItemStack a(Tag<Item> tag) {
/* 150 */     return b(Stream.of(new b(tag)));
/*     */   }
/*     */   
/*     */   public static RecipeItemStack b(PacketDataSerializer packetdataserializer) {
/* 154 */     int i = packetdataserializer.i();
/*     */     
/* 156 */     return b(Stream.<Provider>generate(() -> new StackProvider(packetdataserializer.n()))
/*     */         
/* 158 */         .limit(i));
/*     */   }
/*     */   
/*     */   public static RecipeItemStack a(@Nullable JsonElement jsonelement) {
/* 162 */     if (jsonelement != null && !jsonelement.isJsonNull()) {
/* 163 */       if (jsonelement.isJsonObject())
/* 164 */         return b(Stream.of(a(jsonelement.getAsJsonObject()))); 
/* 165 */       if (jsonelement.isJsonArray()) {
/* 166 */         JsonArray jsonarray = jsonelement.getAsJsonArray();
/*     */         
/* 168 */         if (jsonarray.size() == 0) {
/* 169 */           throw new JsonSyntaxException("Item array cannot be empty, at least one item must be defined");
/*     */         }
/* 171 */         return b(StreamSupport.stream(jsonarray.spliterator(), false).map(jsonelement1 -> a(ChatDeserializer.m(jsonelement1, "item"))));
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 176 */       throw new JsonSyntaxException("Expected item to be object or array of objects");
/*     */     } 
/*     */     
/* 179 */     throw new JsonSyntaxException("Item cannot be null");
/*     */   }
/*     */ 
/*     */   
/*     */   private static Provider a(JsonObject jsonobject) {
/* 184 */     if (jsonobject.has("item") && jsonobject.has("tag")) {
/* 185 */       throw new JsonParseException("An ingredient entry is either a tag or an item, not both");
/*     */     }
/*     */ 
/*     */     
/* 189 */     if (jsonobject.has("item")) {
/* 190 */       MinecraftKey minecraftkey = new MinecraftKey(ChatDeserializer.h(jsonobject, "item"));
/* 191 */       Item item = (Item)IRegistry.ITEM.getOptional(minecraftkey).orElseThrow(() -> new JsonSyntaxException("Unknown item '" + minecraftkey + "'"));
/*     */ 
/*     */ 
/*     */       
/* 195 */       return new StackProvider(new ItemStack(item));
/* 196 */     }  if (jsonobject.has("tag")) {
/* 197 */       MinecraftKey minecraftkey = new MinecraftKey(ChatDeserializer.h(jsonobject, "tag"));
/* 198 */       Tag<Item> tag = TagsInstance.a().getItemTags().a(minecraftkey);
/*     */       
/* 200 */       if (tag == null) {
/* 201 */         throw new JsonSyntaxException("Unknown item tag '" + minecraftkey + "'");
/*     */       }
/* 203 */       return new b(tag);
/*     */     } 
/*     */     
/* 206 */     throw new JsonParseException("An ingredient entry needs either a tag or an item");
/*     */   }
/*     */   
/*     */   public static interface Provider {
/*     */     Collection<ItemStack> a();
/*     */     
/*     */     JsonObject b(); }
/*     */   
/*     */   static class b implements Provider {
/*     */     private b(Tag<Item> tag) {
/* 216 */       this.a = tag;
/*     */     }
/*     */     private final Tag<Item> a;
/*     */     
/*     */     public Collection<ItemStack> a() {
/* 221 */       List<ItemStack> list = Lists.newArrayList();
/* 222 */       Iterator<Item> iterator = this.a.getTagged().iterator();
/*     */       
/* 224 */       while (iterator.hasNext()) {
/* 225 */         Item item = iterator.next();
/*     */         
/* 227 */         list.add(new ItemStack(item));
/*     */       } 
/*     */       
/* 230 */       return list;
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonObject b() {
/* 235 */       JsonObject jsonobject = new JsonObject();
/*     */       
/* 237 */       jsonobject.addProperty("tag", TagsInstance.a().getItemTags().b(this.a).toString());
/* 238 */       return jsonobject;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class StackProvider
/*     */     implements Provider {
/*     */     private final ItemStack a;
/*     */     
/*     */     public StackProvider(ItemStack itemstack) {
/* 247 */       this.a = itemstack;
/*     */     }
/*     */ 
/*     */     
/*     */     public Collection<ItemStack> a() {
/* 252 */       return Collections.singleton(this.a);
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonObject b() {
/* 257 */       JsonObject jsonobject = new JsonObject();
/*     */       
/* 259 */       jsonobject.addProperty("item", IRegistry.ITEM.getKey(this.a.getItem()).toString());
/* 260 */       return jsonobject;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RecipeItemStack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */