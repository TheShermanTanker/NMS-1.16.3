/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RecipeBook
/*     */ {
/*  13 */   public final Set<MinecraftKey> recipes = Sets.newHashSet();
/*  14 */   protected final Set<MinecraftKey> toBeDisplayed = Sets.newHashSet();
/*     */   
/*  16 */   private final RecipeBookSettings c = new RecipeBookSettings();
/*     */   
/*     */   public void a(RecipeBook var0) {
/*  19 */     this.recipes.clear();
/*  20 */     this.toBeDisplayed.clear();
/*     */     
/*  22 */     this.c.a(var0.c);
/*     */     
/*  24 */     this.recipes.addAll(var0.recipes);
/*  25 */     this.toBeDisplayed.addAll(var0.toBeDisplayed);
/*     */   }
/*     */   
/*     */   public void a(IRecipe<?> var0) {
/*  29 */     if (!var0.isComplex()) {
/*  30 */       a(var0.getKey());
/*     */     }
/*     */   }
/*     */   
/*     */   protected void a(MinecraftKey var0) {
/*  35 */     this.recipes.add(var0);
/*     */   }
/*     */   
/*     */   public boolean b(@Nullable IRecipe<?> var0) {
/*  39 */     if (var0 == null) {
/*  40 */       return false;
/*     */     }
/*  42 */     return this.recipes.contains(var0.getKey());
/*     */   }
/*     */   
/*     */   public boolean hasDiscoveredRecipe(MinecraftKey var0) {
/*  46 */     return this.recipes.contains(var0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void c(MinecraftKey var0) {
/*  54 */     this.recipes.remove(var0);
/*  55 */     this.toBeDisplayed.remove(var0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void e(IRecipe<?> var0) {
/*  63 */     this.toBeDisplayed.remove(var0.getKey());
/*     */   }
/*     */   
/*     */   public void f(IRecipe<?> var0) {
/*  67 */     d(var0.getKey());
/*     */   }
/*     */   
/*     */   protected void d(MinecraftKey var0) {
/*  71 */     this.toBeDisplayed.add(var0);
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(RecipeBookSettings var0) {
/*  95 */     this.c.a(var0);
/*     */   }
/*     */   
/*     */   public RecipeBookSettings a() {
/*  99 */     return this.c.a();
/*     */   }
/*     */   
/*     */   public void a(RecipeBookType var0, boolean var1, boolean var2) {
/* 103 */     this.c.a(var0, var1);
/* 104 */     this.c.b(var0, var2);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RecipeBook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */