/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileEntityFurnaceFurnace
/*    */   extends TileEntityFurnace
/*    */ {
/*    */   public TileEntityFurnaceFurnace() {
/* 12 */     super(TileEntityTypes.FURNACE, (Recipes)Recipes.SMELTING);
/*    */   }
/*    */ 
/*    */   
/*    */   protected IChatBaseComponent getContainerName() {
/* 17 */     return new ChatMessage("container.furnace");
/*    */   }
/*    */ 
/*    */   
/*    */   protected Container createContainer(int var0, PlayerInventory var1) {
/* 22 */     return new ContainerFurnaceFurnace(var0, var1, this, this.b);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntityFurnaceFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */