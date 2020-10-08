/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SlotResult
/*    */   extends Slot
/*    */ {
/*    */   private final InventoryCrafting a;
/*    */   private final EntityHuman b;
/*    */   private int g;
/*    */   
/*    */   public SlotResult(EntityHuman var0, InventoryCrafting var1, IInventory var2, int var3, int var4, int var5) {
/* 15 */     super(var2, var3, var4, var5);
/* 16 */     this.b = var0;
/* 17 */     this.a = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isAllowed(ItemStack var0) {
/* 22 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(int var0) {
/* 27 */     if (hasItem()) {
/* 28 */       this.g += Math.min(var0, getItem().getCount());
/*    */     }
/* 30 */     return super.a(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(ItemStack var0, int var1) {
/* 35 */     this.g += var1;
/* 36 */     c(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void b(int var0) {
/* 41 */     this.g += var0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void c(ItemStack var0) {
/* 46 */     if (this.g > 0) {
/* 47 */       var0.a(this.b.world, this.b, this.g);
/*    */     }
/* 49 */     if (this.inventory instanceof RecipeHolder) {
/* 50 */       ((RecipeHolder)this.inventory).b(this.b);
/*    */     }
/* 52 */     this.g = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(EntityHuman var0, ItemStack var1) {
/* 57 */     c(var1);
/*    */     
/* 59 */     NonNullList<ItemStack> var2 = var0.world.getCraftingManager().c(Recipes.CRAFTING, this.a, var0.world);
/*    */     
/* 61 */     for (int var3 = 0; var3 < var2.size(); var3++) {
/* 62 */       ItemStack var4 = this.a.getItem(var3);
/* 63 */       ItemStack var5 = var2.get(var3);
/*    */       
/* 65 */       if (!var4.isEmpty()) {
/* 66 */         this.a.splitStack(var3, 1);
/* 67 */         var4 = this.a.getItem(var3);
/*    */       } 
/*    */       
/* 70 */       if (!var5.isEmpty()) {
/* 71 */         if (var4.isEmpty()) {
/*    */           
/* 73 */           this.a.setItem(var3, var5);
/* 74 */         } else if (ItemStack.c(var4, var5) && ItemStack.equals(var4, var5)) {
/* 75 */           var5.add(var4.getCount());
/* 76 */           this.a.setItem(var3, var5);
/* 77 */         } else if (!this.b.inventory.pickup(var5)) {
/*    */           
/* 79 */           this.b.drop(var5, false);
/*    */         } 
/*    */       }
/*    */     } 
/* 83 */     return var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\SlotResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */