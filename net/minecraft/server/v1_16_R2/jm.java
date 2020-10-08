/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import java.util.function.Consumer;
/*     */ import javax.annotation.Nullable;
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
/*     */ public class jm
/*     */ {
/*     */   private final RecipeItemStack a;
/*     */   private final RecipeItemStack b;
/*     */   private final Item c;
/*  22 */   private final Advancement.SerializedAdvancement d = Advancement.SerializedAdvancement.a();
/*     */   private final RecipeSerializer<?> e;
/*     */   
/*     */   public jm(RecipeSerializer<?> var0, RecipeItemStack var1, RecipeItemStack var2, Item var3) {
/*  26 */     this.e = var0;
/*  27 */     this.a = var1;
/*  28 */     this.b = var2;
/*  29 */     this.c = var3;
/*     */   }
/*     */   
/*     */   public static jm a(RecipeItemStack var0, RecipeItemStack var1, Item var2) {
/*  33 */     return new jm(RecipeSerializer.u, var0, var1, var2);
/*     */   }
/*     */   
/*     */   public jm a(String var0, CriterionInstance var1) {
/*  37 */     this.d.a(var0, var1);
/*  38 */     return this;
/*     */   }
/*     */   
/*     */   public void a(Consumer<jf> var0, String var1) {
/*  42 */     a(var0, new MinecraftKey(var1));
/*     */   }
/*     */   
/*     */   public void a(Consumer<jf> var0, MinecraftKey var1) {
/*  46 */     a(var1);
/*  47 */     this.d
/*  48 */       .a(new MinecraftKey("recipes/root"))
/*  49 */       .a("has_the_recipe", CriterionTriggerRecipeUnlocked.a(var1))
/*  50 */       .a(AdvancementRewards.a.c(var1))
/*  51 */       .a(AdvancementRequirements.OR);
/*     */     
/*  53 */     var0.accept(new a(var1, this.e, this.a, this.b, this.c, this.d, new MinecraftKey(var1.getNamespace(), "recipes/" + this.c.q().b() + "/" + var1.getKey())));
/*     */   }
/*     */   
/*     */   private void a(MinecraftKey var0) {
/*  57 */     if (this.d.c().isEmpty())
/*  58 */       throw new IllegalStateException("No way of obtaining recipe " + var0); 
/*     */   }
/*     */   
/*     */   public static class a
/*     */     implements jf {
/*     */     private final MinecraftKey a;
/*     */     private final RecipeItemStack b;
/*     */     private final RecipeItemStack c;
/*     */     private final Item d;
/*     */     private final Advancement.SerializedAdvancement e;
/*     */     private final MinecraftKey f;
/*     */     private final RecipeSerializer<?> g;
/*     */     
/*     */     public a(MinecraftKey var0, RecipeSerializer<?> var1, RecipeItemStack var2, RecipeItemStack var3, Item var4, Advancement.SerializedAdvancement var5, MinecraftKey var6) {
/*  72 */       this.a = var0;
/*  73 */       this.g = var1;
/*  74 */       this.b = var2;
/*  75 */       this.c = var3;
/*  76 */       this.d = var4;
/*  77 */       this.e = var5;
/*  78 */       this.f = var6;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(JsonObject var0) {
/*  83 */       var0.add("base", this.b.c());
/*  84 */       var0.add("addition", this.c.c());
/*  85 */       JsonObject var1 = new JsonObject();
/*  86 */       var1.addProperty("item", IRegistry.ITEM.getKey(this.d).toString());
/*  87 */       var0.add("result", (JsonElement)var1);
/*     */     }
/*     */ 
/*     */     
/*     */     public MinecraftKey b() {
/*  92 */       return this.a;
/*     */     }
/*     */ 
/*     */     
/*     */     public RecipeSerializer<?> c() {
/*  97 */       return this.g;
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public JsonObject d() {
/* 103 */       return this.e.b();
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public MinecraftKey e() {
/* 109 */       return this.f;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\jm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */