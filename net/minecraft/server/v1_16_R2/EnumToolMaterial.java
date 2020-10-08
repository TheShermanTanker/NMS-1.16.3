/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum EnumToolMaterial
/*    */   implements ToolMaterial
/*    */ {
/* 10 */   WOOD(0, 59, 2.0F, 0.0F, 15, () -> RecipeItemStack.a(TagsItem.PLANKS)),
/* 11 */   STONE(1, 131, 4.0F, 1.0F, 5, () -> RecipeItemStack.a(TagsItem.STONE_TOOL_MATERIALS)),
/* 12 */   IRON(2, 250, 6.0F, 2.0F, 14, () -> RecipeItemStack.a(new IMaterial[] { Items.IRON_INGOT })),
/* 13 */   DIAMOND(3, 1561, 8.0F, 3.0F, 10, () -> RecipeItemStack.a(new IMaterial[] { Items.DIAMOND })),
/* 14 */   GOLD(0, 32, 12.0F, 0.0F, 22, () -> RecipeItemStack.a(new IMaterial[] { Items.GOLD_INGOT })),
/* 15 */   NETHERITE(4, 2031, 9.0F, 4.0F, 15, () -> RecipeItemStack.a(new IMaterial[] { Items.NETHERITE_INGOT }));
/*    */   
/*    */   private final int g;
/*    */   
/*    */   private final int h;
/*    */   private final float i;
/*    */   private final float j;
/*    */   private final int k;
/*    */   private final LazyInitVar<RecipeItemStack> l;
/*    */   
/*    */   EnumToolMaterial(int var2, int var3, float var4, float var5, int var6, Supplier<RecipeItemStack> var7) {
/* 26 */     this.g = var2;
/* 27 */     this.h = var3;
/* 28 */     this.i = var4;
/* 29 */     this.j = var5;
/* 30 */     this.k = var6;
/* 31 */     this.l = new LazyInitVar<>(var7);
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 36 */     return this.h;
/*    */   }
/*    */ 
/*    */   
/*    */   public float b() {
/* 41 */     return this.i;
/*    */   }
/*    */ 
/*    */   
/*    */   public float c() {
/* 46 */     return this.j;
/*    */   }
/*    */ 
/*    */   
/*    */   public int d() {
/* 51 */     return this.g;
/*    */   }
/*    */ 
/*    */   
/*    */   public int e() {
/* 56 */     return this.k;
/*    */   }
/*    */ 
/*    */   
/*    */   public RecipeItemStack f() {
/* 61 */     return this.l.a();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnumToolMaterial.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */