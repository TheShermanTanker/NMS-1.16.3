/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.ints.IntArrayList;
/*    */ import it.unimi.dsi.fastutil.ints.IntList;
/*    */ import it.unimi.dsi.fastutil.ints.IntListIterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AutoRecipeFurnace<C extends IInventory>
/*    */   extends AutoRecipe<C>
/*    */ {
/*    */   private boolean e;
/*    */   
/*    */   public AutoRecipeFurnace(ContainerRecipeBook<C> var0) {
/* 19 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(IRecipe<C> var0, boolean var1) {
/* 24 */     this.e = this.d.a(var0);
/* 25 */     int var2 = this.b.b(var0, (IntList)null);
/*    */ 
/*    */     
/* 28 */     if (this.e) {
/* 29 */       ItemStack itemStack = this.d.getSlot(0).getItem();
/* 30 */       if (itemStack.isEmpty() || var2 <= itemStack.getCount()) {
/*    */         return;
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 36 */     int var3 = a(var1, var2, this.e);
/*    */ 
/*    */     
/* 39 */     IntArrayList intArrayList = new IntArrayList();
/* 40 */     if (!this.b.a(var0, (IntList)intArrayList, var3)) {
/*    */       return;
/*    */     }
/*    */     
/* 44 */     if (!this.e) {
/* 45 */       a(this.d.f());
/* 46 */       a(0);
/*    */     } 
/* 48 */     a(var3, (IntList)intArrayList);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a() {
/* 53 */     a(this.d.f());
/* 54 */     super.a();
/*    */   }
/*    */   
/*    */   protected void a(int var0, IntList var1) {
/* 58 */     IntListIterator<Integer> intListIterator = var1.iterator();
/*    */     
/* 60 */     Slot var3 = this.d.getSlot(0);
/* 61 */     ItemStack var4 = AutoRecipeStackManager.a(((Integer)intListIterator.next()).intValue());
/* 62 */     if (var4.isEmpty()) {
/*    */       return;
/*    */     }
/*    */     
/* 66 */     int var5 = Math.min(var4.getMaxStackSize(), var0);
/* 67 */     if (this.e) {
/* 68 */       var5 -= var3.getItem().getCount();
/*    */     }
/* 70 */     for (int var6 = 0; var6 < var5; var6++)
/* 71 */       a(var3, var4); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\AutoRecipeFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */