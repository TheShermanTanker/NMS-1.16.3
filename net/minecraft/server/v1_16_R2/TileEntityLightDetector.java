/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileEntityLightDetector
/*    */   extends TileEntity
/*    */   implements ITickable
/*    */ {
/*    */   public TileEntityLightDetector() {
/* 10 */     super(TileEntityTypes.DAYLIGHT_DETECTOR);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick() {
/* 15 */     if (this.world != null && !this.world.isClientSide && this.world.getTime() % 20L == 0L) {
/* 16 */       IBlockData var0 = getBlock();
/* 17 */       Block var1 = var0.getBlock();
/* 18 */       if (var1 instanceof BlockDaylightDetector)
/* 19 */         BlockDaylightDetector.d(var0, this.world, this.position); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntityLightDetector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */