/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.function.Consumer;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class jh
/*     */ {
/*  30 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   private final Item b;
/*     */   private final int c;
/*  34 */   private final List<String> d = Lists.newArrayList();
/*  35 */   private final Map<Character, RecipeItemStack> e = Maps.newLinkedHashMap();
/*  36 */   private final Advancement.SerializedAdvancement f = Advancement.SerializedAdvancement.a();
/*     */   private String g;
/*     */   
/*     */   public jh(IMaterial var0, int var1) {
/*  40 */     this.b = var0.getItem();
/*  41 */     this.c = var1;
/*     */   }
/*     */   
/*     */   public static jh a(IMaterial var0) {
/*  45 */     return a(var0, 1);
/*     */   }
/*     */   
/*     */   public static jh a(IMaterial var0, int var1) {
/*  49 */     return new jh(var0, var1);
/*     */   }
/*     */   
/*     */   public jh a(Character var0, Tag<Item> var1) {
/*  53 */     return a(var0, RecipeItemStack.a(var1));
/*     */   }
/*     */   
/*     */   public jh a(Character var0, IMaterial var1) {
/*  57 */     return a(var0, RecipeItemStack.a(new IMaterial[] { var1 }));
/*     */   }
/*     */   
/*     */   public jh a(Character var0, RecipeItemStack var1) {
/*  61 */     if (this.e.containsKey(var0)) {
/*  62 */       throw new IllegalArgumentException("Symbol '" + var0 + "' is already defined!");
/*     */     }
/*  64 */     if (var0.charValue() == ' ') {
/*  65 */       throw new IllegalArgumentException("Symbol ' ' (whitespace) is reserved and cannot be defined");
/*     */     }
/*  67 */     this.e.put(var0, var1);
/*  68 */     return this;
/*     */   }
/*     */   
/*     */   public jh a(String var0) {
/*  72 */     if (!this.d.isEmpty() && var0.length() != ((String)this.d.get(0)).length()) {
/*  73 */       throw new IllegalArgumentException("Pattern must be the same width on every line!");
/*     */     }
/*  75 */     this.d.add(var0);
/*  76 */     return this;
/*     */   }
/*     */   
/*     */   public jh a(String var0, CriterionInstance var1) {
/*  80 */     this.f.a(var0, var1);
/*  81 */     return this;
/*     */   }
/*     */   
/*     */   public jh b(String var0) {
/*  85 */     this.g = var0;
/*  86 */     return this;
/*     */   }
/*     */   
/*     */   public void a(Consumer<jf> var0) {
/*  90 */     a(var0, IRegistry.ITEM.getKey(this.b));
/*     */   }
/*     */   
/*     */   public void a(Consumer<jf> var0, String var1) {
/*  94 */     MinecraftKey var2 = IRegistry.ITEM.getKey(this.b);
/*  95 */     if ((new MinecraftKey(var1)).equals(var2)) {
/*  96 */       throw new IllegalStateException("Shaped Recipe " + var1 + " should remove its 'save' argument");
/*     */     }
/*  98 */     a(var0, new MinecraftKey(var1));
/*     */   }
/*     */   
/*     */   public void a(Consumer<jf> var0, MinecraftKey var1) {
/* 102 */     a(var1);
/* 103 */     this.f
/* 104 */       .a(new MinecraftKey("recipes/root"))
/* 105 */       .a("has_the_recipe", CriterionTriggerRecipeUnlocked.a(var1))
/* 106 */       .a(AdvancementRewards.a.c(var1))
/* 107 */       .a(AdvancementRequirements.OR);
/* 108 */     var0.accept(new a(this, var1, this.b, this.c, (this.g == null) ? "" : this.g, this.d, this.e, this.f, new MinecraftKey(var1.getNamespace(), "recipes/" + this.b.q().b() + "/" + var1.getKey())));
/*     */   }
/*     */   
/*     */   private void a(MinecraftKey var0) {
/* 112 */     if (this.d.isEmpty()) {
/* 113 */       throw new IllegalStateException("No pattern is defined for shaped recipe " + var0 + "!");
/*     */     }
/*     */     
/* 116 */     Set<Character> var1 = Sets.newHashSet(this.e.keySet());
/* 117 */     var1.remove(Character.valueOf(' '));
/*     */     
/* 119 */     for (String var3 : this.d) {
/* 120 */       for (int var4 = 0; var4 < var3.length(); var4++) {
/* 121 */         char var5 = var3.charAt(var4);
/* 122 */         if (!this.e.containsKey(Character.valueOf(var5)) && var5 != ' ') {
/* 123 */           throw new IllegalStateException("Pattern in recipe " + var0 + " uses undefined symbol '" + var5 + "'");
/*     */         }
/* 125 */         var1.remove(Character.valueOf(var5));
/*     */       } 
/*     */     } 
/*     */     
/* 129 */     if (!var1.isEmpty()) {
/* 130 */       throw new IllegalStateException("Ingredients are defined but not used in pattern for recipe " + var0);
/*     */     }
/*     */     
/* 133 */     if (this.d.size() == 1 && ((String)this.d.get(0)).length() == 1) {
/* 134 */       throw new IllegalStateException("Shaped recipe " + var0 + " only takes in a single item - should it be a shapeless recipe instead?");
/*     */     }
/*     */     
/* 137 */     if (this.f.c().isEmpty())
/* 138 */       throw new IllegalStateException("No way of obtaining recipe " + var0); 
/*     */   }
/*     */   
/*     */   class a
/*     */     implements jf {
/*     */     private final MinecraftKey b;
/*     */     private final Item c;
/*     */     private final int d;
/*     */     private final String e;
/*     */     private final List<String> f;
/*     */     private final Map<Character, RecipeItemStack> g;
/*     */     private final Advancement.SerializedAdvancement h;
/*     */     private final MinecraftKey i;
/*     */     
/*     */     public a(jh this$0, MinecraftKey var1, Item var2, int var3, String var4, List<String> var5, Map<Character, RecipeItemStack> var6, Advancement.SerializedAdvancement var7, MinecraftKey var8) {
/* 153 */       this.b = var1;
/* 154 */       this.c = var2;
/* 155 */       this.d = var3;
/* 156 */       this.e = var4;
/* 157 */       this.f = var5;
/* 158 */       this.g = var6;
/* 159 */       this.h = var7;
/* 160 */       this.i = var8;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(JsonObject var0) {
/* 165 */       if (!this.e.isEmpty()) {
/* 166 */         var0.addProperty("group", this.e);
/*     */       }
/*     */       
/* 169 */       JsonArray var1 = new JsonArray();
/* 170 */       for (String str : this.f) {
/* 171 */         var1.add(str);
/*     */       }
/* 173 */       var0.add("pattern", (JsonElement)var1);
/*     */       
/* 175 */       JsonObject var2 = new JsonObject();
/* 176 */       for (Map.Entry<Character, RecipeItemStack> var4 : this.g.entrySet()) {
/* 177 */         var2.add(String.valueOf(var4.getKey()), ((RecipeItemStack)var4.getValue()).c());
/*     */       }
/* 179 */       var0.add("key", (JsonElement)var2);
/*     */       
/* 181 */       JsonObject var3 = new JsonObject();
/* 182 */       var3.addProperty("item", IRegistry.ITEM.getKey(this.c).toString());
/* 183 */       if (this.d > 1) {
/* 184 */         var3.addProperty("count", Integer.valueOf(this.d));
/*     */       }
/* 186 */       var0.add("result", (JsonElement)var3);
/*     */     }
/*     */ 
/*     */     
/*     */     public RecipeSerializer<?> c() {
/* 191 */       return RecipeSerializer.a;
/*     */     }
/*     */ 
/*     */     
/*     */     public MinecraftKey b() {
/* 196 */       return this.b;
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public JsonObject d() {
/* 202 */       return this.h.b();
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public MinecraftKey e() {
/* 208 */       return this.i;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\jh.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */