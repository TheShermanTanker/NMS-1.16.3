/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ public class jk
/*     */ {
/*     */   private final Item a;
/*     */   private final RecipeItemStack b;
/*     */   private final int c;
/*  23 */   private final Advancement.SerializedAdvancement d = Advancement.SerializedAdvancement.a();
/*     */   private String e;
/*     */   private final RecipeSerializer<?> f;
/*     */   
/*     */   public jk(RecipeSerializer<?> var0, RecipeItemStack var1, IMaterial var2, int var3) {
/*  28 */     this.f = var0;
/*  29 */     this.a = var2.getItem();
/*  30 */     this.b = var1;
/*  31 */     this.c = var3;
/*     */   }
/*     */   
/*     */   public static jk a(RecipeItemStack var0, IMaterial var1) {
/*  35 */     return new jk(RecipeSerializer.t, var0, var1, 1);
/*     */   }
/*     */   
/*     */   public static jk a(RecipeItemStack var0, IMaterial var1, int var2) {
/*  39 */     return new jk(RecipeSerializer.t, var0, var1, var2);
/*     */   }
/*     */   
/*     */   public jk a(String var0, CriterionInstance var1) {
/*  43 */     this.d.a(var0, var1);
/*  44 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(Consumer<jf> var0, String var1) {
/*  53 */     MinecraftKey var2 = IRegistry.ITEM.getKey(this.a);
/*  54 */     if ((new MinecraftKey(var1)).equals(var2)) {
/*  55 */       throw new IllegalStateException("Single Item Recipe " + var1 + " should remove its 'save' argument");
/*     */     }
/*  57 */     a(var0, new MinecraftKey(var1));
/*     */   }
/*     */   
/*     */   public void a(Consumer<jf> var0, MinecraftKey var1) {
/*  61 */     a(var1);
/*  62 */     this.d
/*  63 */       .a(new MinecraftKey("recipes/root"))
/*  64 */       .a("has_the_recipe", CriterionTriggerRecipeUnlocked.a(var1))
/*  65 */       .a(AdvancementRewards.a.c(var1))
/*  66 */       .a(AdvancementRequirements.OR);
/*     */     
/*  68 */     var0.accept(new a(var1, this.f, (this.e == null) ? "" : this.e, this.b, this.a, this.c, this.d, new MinecraftKey(var1.getNamespace(), "recipes/" + this.a.q().b() + "/" + var1.getKey())));
/*     */   }
/*     */   
/*     */   private void a(MinecraftKey var0) {
/*  72 */     if (this.d.c().isEmpty())
/*  73 */       throw new IllegalStateException("No way of obtaining recipe " + var0); 
/*     */   }
/*     */   
/*     */   public static class a
/*     */     implements jf {
/*     */     private final MinecraftKey a;
/*     */     private final String b;
/*     */     private final RecipeItemStack c;
/*     */     private final Item d;
/*     */     private final int e;
/*     */     private final Advancement.SerializedAdvancement f;
/*     */     private final MinecraftKey g;
/*     */     private final RecipeSerializer<?> h;
/*     */     
/*     */     public a(MinecraftKey var0, RecipeSerializer<?> var1, String var2, RecipeItemStack var3, Item var4, int var5, Advancement.SerializedAdvancement var6, MinecraftKey var7) {
/*  88 */       this.a = var0;
/*  89 */       this.h = var1;
/*  90 */       this.b = var2;
/*  91 */       this.c = var3;
/*  92 */       this.d = var4;
/*  93 */       this.e = var5;
/*  94 */       this.f = var6;
/*  95 */       this.g = var7;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(JsonObject var0) {
/* 100 */       if (!this.b.isEmpty()) {
/* 101 */         var0.addProperty("group", this.b);
/*     */       }
/*     */       
/* 104 */       var0.add("ingredient", this.c.c());
/* 105 */       var0.addProperty("result", IRegistry.ITEM.getKey(this.d).toString());
/* 106 */       var0.addProperty("count", Integer.valueOf(this.e));
/*     */     }
/*     */ 
/*     */     
/*     */     public MinecraftKey b() {
/* 111 */       return this.a;
/*     */     }
/*     */ 
/*     */     
/*     */     public RecipeSerializer<?> c() {
/* 116 */       return this.h;
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public JsonObject d() {
/* 122 */       return this.f.b();
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public MinecraftKey e() {
/* 128 */       return this.g;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\jk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */