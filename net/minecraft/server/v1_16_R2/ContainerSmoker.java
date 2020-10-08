/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ContainerSmoker
/*    */   extends ContainerFurnace
/*    */ {
/*    */   public ContainerSmoker(int var0, PlayerInventory var1) {
/*  9 */     super(Containers.SMOKER, (Recipes)Recipes.SMOKING, RecipeBookType.SMOKER, var0, var1);
/*    */   }
/*    */   
/*    */   public ContainerSmoker(int var0, PlayerInventory var1, IInventory var2, IContainerProperties var3) {
/* 13 */     super(Containers.SMOKER, (Recipes)Recipes.SMOKING, RecipeBookType.SMOKER, var0, var1, var2, var3);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ContainerSmoker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */