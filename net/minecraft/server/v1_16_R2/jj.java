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
/*     */ 
/*     */ 
/*     */ public class jj
/*     */ {
/*     */   private final Item a;
/*     */   private final RecipeItemStack b;
/*     */   private final float c;
/*     */   private final int d;
/*  26 */   private final Advancement.SerializedAdvancement e = Advancement.SerializedAdvancement.a();
/*     */   private String f;
/*     */   private final RecipeSerializerCooking<?> g;
/*     */   
/*     */   private jj(IMaterial var0, RecipeItemStack var1, float var2, int var3, RecipeSerializerCooking<?> var4) {
/*  31 */     this.a = var0.getItem();
/*  32 */     this.b = var1;
/*  33 */     this.c = var2;
/*  34 */     this.d = var3;
/*  35 */     this.g = var4;
/*     */   }
/*     */   
/*     */   public static jj a(RecipeItemStack var0, IMaterial var1, float var2, int var3, RecipeSerializerCooking<?> var4) {
/*  39 */     return new jj(var1, var0, var2, var3, var4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static jj b(RecipeItemStack var0, IMaterial var1, float var2, int var3) {
/*  47 */     return a(var0, var1, var2, var3, RecipeSerializer.q);
/*     */   }
/*     */   
/*     */   public static jj c(RecipeItemStack var0, IMaterial var1, float var2, int var3) {
/*  51 */     return a(var0, var1, var2, var3, RecipeSerializer.p);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public jj a(String var0, CriterionInstance var1) {
/*  59 */     this.e.a(var0, var1);
/*  60 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(Consumer<jf> var0) {
/*  69 */     a(var0, IRegistry.ITEM.getKey(this.a));
/*     */   }
/*     */   
/*     */   public void a(Consumer<jf> var0, String var1) {
/*  73 */     MinecraftKey var2 = IRegistry.ITEM.getKey(this.a);
/*  74 */     MinecraftKey var3 = new MinecraftKey(var1);
/*  75 */     if (var3.equals(var2)) {
/*  76 */       throw new IllegalStateException("Recipe " + var3 + " should remove its 'save' argument");
/*     */     }
/*     */     
/*  79 */     a(var0, var3);
/*     */   }
/*     */   
/*     */   public void a(Consumer<jf> var0, MinecraftKey var1) {
/*  83 */     a(var1);
/*  84 */     this.e
/*  85 */       .a(new MinecraftKey("recipes/root"))
/*  86 */       .a("has_the_recipe", CriterionTriggerRecipeUnlocked.a(var1))
/*  87 */       .a(AdvancementRewards.a.c(var1))
/*  88 */       .a(AdvancementRequirements.OR);
/*     */     
/*  90 */     var0.accept(new a(var1, (this.f == null) ? "" : this.f, this.b, this.a, this.c, this.d, this.e, new MinecraftKey(var1.getNamespace(), "recipes/" + this.a.q().b() + "/" + var1.getKey()), (RecipeSerializer)this.g));
/*     */   }
/*     */   
/*     */   private void a(MinecraftKey var0) {
/*  94 */     if (this.e.c().isEmpty())
/*  95 */       throw new IllegalStateException("No way of obtaining recipe " + var0); 
/*     */   }
/*     */   
/*     */   public static class a
/*     */     implements jf {
/*     */     private final MinecraftKey a;
/*     */     private final String b;
/*     */     private final RecipeItemStack c;
/*     */     private final Item d;
/*     */     private final float e;
/*     */     private final int f;
/*     */     private final Advancement.SerializedAdvancement g;
/*     */     private final MinecraftKey h;
/*     */     private final RecipeSerializer<? extends RecipeCooking> i;
/*     */     
/*     */     public a(MinecraftKey var0, String var1, RecipeItemStack var2, Item var3, float var4, int var5, Advancement.SerializedAdvancement var6, MinecraftKey var7, RecipeSerializer<? extends RecipeCooking> var8) {
/* 111 */       this.a = var0;
/* 112 */       this.b = var1;
/* 113 */       this.c = var2;
/* 114 */       this.d = var3;
/* 115 */       this.e = var4;
/* 116 */       this.f = var5;
/* 117 */       this.g = var6;
/* 118 */       this.h = var7;
/* 119 */       this.i = var8;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(JsonObject var0) {
/* 124 */       if (!this.b.isEmpty()) {
/* 125 */         var0.addProperty("group", this.b);
/*     */       }
/*     */       
/* 128 */       var0.add("ingredient", this.c.c());
/* 129 */       var0.addProperty("result", IRegistry.ITEM.getKey(this.d).toString());
/* 130 */       var0.addProperty("experience", Float.valueOf(this.e));
/* 131 */       var0.addProperty("cookingtime", Integer.valueOf(this.f));
/*     */     }
/*     */ 
/*     */     
/*     */     public RecipeSerializer<?> c() {
/* 136 */       return this.i;
/*     */     }
/*     */ 
/*     */     
/*     */     public MinecraftKey b() {
/* 141 */       return this.a;
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public JsonObject d() {
/* 147 */       return this.g.b();
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public MinecraftKey e() {
/* 153 */       return this.h;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\jj.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */