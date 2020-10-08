/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ContainerRecipeBook<C extends IInventory>
/*    */   extends Container
/*    */ {
/*    */   public ContainerRecipeBook(Containers<?> var0, int var1) {
/* 12 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(boolean var0, IRecipe<?> var1, EntityPlayer var2) {
/* 17 */     (new AutoRecipe(this)).a(var2, var1, var0);
/*    */   }
/*    */   
/*    */   public abstract void a(AutoRecipeStackManager paramAutoRecipeStackManager);
/*    */   
/*    */   public abstract void e();
/*    */   
/*    */   public abstract boolean a(IRecipe<? super C> paramIRecipe);
/*    */   
/*    */   public abstract int f();
/*    */   
/*    */   public abstract int g();
/*    */   
/*    */   public abstract int h();
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ContainerRecipeBook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */