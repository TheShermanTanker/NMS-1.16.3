/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import java.util.List;
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
/*     */ public class ji
/*     */ {
/*  26 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   private final Item b;
/*     */   private final int c;
/*  30 */   private final List<RecipeItemStack> d = Lists.newArrayList();
/*  31 */   private final Advancement.SerializedAdvancement e = Advancement.SerializedAdvancement.a();
/*     */   private String f;
/*     */   
/*     */   public ji(IMaterial var0, int var1) {
/*  35 */     this.b = var0.getItem();
/*  36 */     this.c = var1;
/*     */   }
/*     */   
/*     */   public static ji a(IMaterial var0) {
/*  40 */     return new ji(var0, 1);
/*     */   }
/*     */   
/*     */   public static ji a(IMaterial var0, int var1) {
/*  44 */     return new ji(var0, var1);
/*     */   }
/*     */   
/*     */   public ji a(Tag<Item> var0) {
/*  48 */     return a(RecipeItemStack.a(var0));
/*     */   }
/*     */   
/*     */   public ji b(IMaterial var0) {
/*  52 */     return b(var0, 1);
/*     */   }
/*     */   
/*     */   public ji b(IMaterial var0, int var1) {
/*  56 */     for (int var2 = 0; var2 < var1; var2++) {
/*  57 */       a(RecipeItemStack.a(new IMaterial[] { var0 }));
/*     */     } 
/*  59 */     return this;
/*     */   }
/*     */   
/*     */   public ji a(RecipeItemStack var0) {
/*  63 */     return a(var0, 1);
/*     */   }
/*     */   
/*     */   public ji a(RecipeItemStack var0, int var1) {
/*  67 */     for (int var2 = 0; var2 < var1; var2++) {
/*  68 */       this.d.add(var0);
/*     */     }
/*  70 */     return this;
/*     */   }
/*     */   
/*     */   public ji a(String var0, CriterionInstance var1) {
/*  74 */     this.e.a(var0, var1);
/*  75 */     return this;
/*     */   }
/*     */   
/*     */   public ji a(String var0) {
/*  79 */     this.f = var0;
/*  80 */     return this;
/*     */   }
/*     */   
/*     */   public void a(Consumer<jf> var0) {
/*  84 */     a(var0, IRegistry.ITEM.getKey(this.b));
/*     */   }
/*     */   
/*     */   public void a(Consumer<jf> var0, String var1) {
/*  88 */     MinecraftKey var2 = IRegistry.ITEM.getKey(this.b);
/*  89 */     if ((new MinecraftKey(var1)).equals(var2)) {
/*  90 */       throw new IllegalStateException("Shapeless Recipe " + var1 + " should remove its 'save' argument");
/*     */     }
/*  92 */     a(var0, new MinecraftKey(var1));
/*     */   }
/*     */   
/*     */   public void a(Consumer<jf> var0, MinecraftKey var1) {
/*  96 */     a(var1);
/*  97 */     this.e
/*  98 */       .a(new MinecraftKey("recipes/root"))
/*  99 */       .a("has_the_recipe", CriterionTriggerRecipeUnlocked.a(var1))
/* 100 */       .a(AdvancementRewards.a.c(var1))
/* 101 */       .a(AdvancementRequirements.OR);
/* 102 */     var0.accept(new a(var1, this.b, this.c, (this.f == null) ? "" : this.f, this.d, this.e, new MinecraftKey(var1.getNamespace(), "recipes/" + this.b.q().b() + "/" + var1.getKey())));
/*     */   }
/*     */   
/*     */   private void a(MinecraftKey var0) {
/* 106 */     if (this.e.c().isEmpty())
/* 107 */       throw new IllegalStateException("No way of obtaining recipe " + var0); 
/*     */   }
/*     */   
/*     */   public static class a
/*     */     implements jf {
/*     */     private final MinecraftKey a;
/*     */     private final Item b;
/*     */     private final int c;
/*     */     private final String d;
/*     */     private final List<RecipeItemStack> e;
/*     */     private final Advancement.SerializedAdvancement f;
/*     */     private final MinecraftKey g;
/*     */     
/*     */     public a(MinecraftKey var0, Item var1, int var2, String var3, List<RecipeItemStack> var4, Advancement.SerializedAdvancement var5, MinecraftKey var6) {
/* 121 */       this.a = var0;
/* 122 */       this.b = var1;
/* 123 */       this.c = var2;
/* 124 */       this.d = var3;
/* 125 */       this.e = var4;
/* 126 */       this.f = var5;
/* 127 */       this.g = var6;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(JsonObject var0) {
/* 132 */       if (!this.d.isEmpty()) {
/* 133 */         var0.addProperty("group", this.d);
/*     */       }
/*     */       
/* 136 */       JsonArray var1 = new JsonArray();
/* 137 */       for (RecipeItemStack var3 : this.e) {
/* 138 */         var1.add(var3.c());
/*     */       }
/* 140 */       var0.add("ingredients", (JsonElement)var1);
/*     */       
/* 142 */       JsonObject var2 = new JsonObject();
/* 143 */       var2.addProperty("item", IRegistry.ITEM.getKey(this.b).toString());
/* 144 */       if (this.c > 1) {
/* 145 */         var2.addProperty("count", Integer.valueOf(this.c));
/*     */       }
/* 147 */       var0.add("result", (JsonElement)var2);
/*     */     }
/*     */ 
/*     */     
/*     */     public RecipeSerializer<?> c() {
/* 152 */       return RecipeSerializer.b;
/*     */     }
/*     */ 
/*     */     
/*     */     public MinecraftKey b() {
/* 157 */       return this.a;
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public JsonObject d() {
/* 163 */       return this.f.b();
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public MinecraftKey e() {
/* 169 */       return this.g;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ji.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */