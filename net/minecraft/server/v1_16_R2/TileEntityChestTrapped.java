/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class TileEntityChestTrapped extends TileEntityChest {
/*    */   public TileEntityChestTrapped() {
/*  5 */     super(TileEntityTypes.TRAPPED_CHEST);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void onOpen() {
/* 10 */     super.onOpen();
/* 11 */     this.world.applyPhysics(this.position.down(), getBlock().getBlock());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntityChestTrapped.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */