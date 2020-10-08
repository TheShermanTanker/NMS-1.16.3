/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileEntityBlastFurnace
/*    */   extends TileEntityFurnace
/*    */ {
/*    */   public TileEntityBlastFurnace() {
/* 13 */     super(TileEntityTypes.BLAST_FURNACE, (Recipes)Recipes.BLASTING);
/*    */   }
/*    */ 
/*    */   
/*    */   protected IChatBaseComponent getContainerName() {
/* 18 */     return new ChatMessage("container.blast_furnace");
/*    */   }
/*    */ 
/*    */   
/*    */   protected int fuelTime(ItemStack var0) {
/* 23 */     return super.fuelTime(var0) / 2;
/*    */   }
/*    */ 
/*    */   
/*    */   protected Container createContainer(int var0, PlayerInventory var1) {
/* 28 */     return new ContainerBlastFurnace(var0, var1, this, this.b);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntityBlastFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */