/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RecipeMapExtend
/*    */   extends ShapedRecipes
/*    */ {
/*    */   public RecipeMapExtend(MinecraftKey var0) {
/* 15 */     super(var0, "", 3, 3, 
/* 16 */         NonNullList.a(RecipeItemStack.a, new RecipeItemStack[] {
/* 17 */             RecipeItemStack.a(new IMaterial[] { Items.PAPER }), RecipeItemStack.a(new IMaterial[] { Items.PAPER }), RecipeItemStack.a(new IMaterial[] { Items.PAPER
/* 18 */               }), RecipeItemStack.a(new IMaterial[] { Items.PAPER }), RecipeItemStack.a(new IMaterial[] { Items.FILLED_MAP }), RecipeItemStack.a(new IMaterial[] { Items.PAPER
/* 19 */               }), RecipeItemStack.a(new IMaterial[] { Items.PAPER }), RecipeItemStack.a(new IMaterial[] { Items.PAPER }), RecipeItemStack.a(new IMaterial[] { Items.PAPER })
/*    */           }), new ItemStack(Items.MAP));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean a(InventoryCrafting var0, World var1) {
/* 27 */     if (!super.a(var0, var1)) {
/* 28 */       return false;
/*    */     }
/* 30 */     ItemStack var2 = ItemStack.b;
/*    */     
/* 32 */     for (int i = 0; i < var0.getSize() && var2.isEmpty(); i++) {
/* 33 */       ItemStack var4 = var0.getItem(i);
/* 34 */       if (var4.getItem() == Items.FILLED_MAP) {
/* 35 */         var2 = var4;
/*    */       }
/*    */     } 
/*    */     
/* 39 */     if (var2.isEmpty()) {
/* 40 */       return false;
/*    */     }
/* 42 */     WorldMap var3 = ItemWorldMap.getSavedMap(var2, var1);
/* 43 */     if (var3 == null) {
/* 44 */       return false;
/*    */     }
/*    */     
/* 47 */     if (a(var3)) {
/* 48 */       return false;
/*    */     }
/*    */     
/* 51 */     return (var3.scale < 4);
/*    */   }
/*    */   
/*    */   private boolean a(WorldMap var0) {
/* 55 */     if (var0.decorations != null) {
/* 56 */       for (MapIcon var2 : var0.decorations.values()) {
/* 57 */         if (var2.getType() == MapIcon.Type.MANSION || var2.getType() == MapIcon.Type.MONUMENT) {
/* 58 */           return true;
/*    */         }
/*    */       } 
/*    */     }
/* 62 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(InventoryCrafting var0) {
/* 67 */     ItemStack var1 = ItemStack.b;
/*    */     
/* 69 */     for (int var2 = 0; var2 < var0.getSize() && var1.isEmpty(); var2++) {
/* 70 */       ItemStack var3 = var0.getItem(var2);
/* 71 */       if (var3.getItem() == Items.FILLED_MAP) {
/* 72 */         var1 = var3;
/*    */       }
/*    */     } 
/*    */     
/* 76 */     var1 = var1.cloneItemStack();
/* 77 */     var1.setCount(1);
/* 78 */     var1.getOrCreateTag().setInt("map_scale_direction", 1);
/*    */     
/* 80 */     return var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isComplex() {
/* 85 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public RecipeSerializer<?> getRecipeSerializer() {
/* 90 */     return RecipeSerializer.f;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RecipeMapExtend.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */