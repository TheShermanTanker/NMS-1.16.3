/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SourceBlock
/*    */   implements ISourceBlock
/*    */ {
/*    */   private final WorldServer a;
/*    */   private final BlockPosition b;
/*    */   
/*    */   public SourceBlock(WorldServer var0, BlockPosition var1) {
/* 14 */     this.a = var0;
/* 15 */     this.b = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldServer getWorld() {
/* 20 */     return this.a;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getX() {
/* 25 */     return this.b.getX() + 0.5D;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getY() {
/* 30 */     return this.b.getY() + 0.5D;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getZ() {
/* 35 */     return this.b.getZ() + 0.5D;
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPosition getBlockPosition() {
/* 40 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData getBlockData() {
/* 45 */     return this.a.getType(this.b);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public <T extends TileEntity> T getTileEntity() {
/* 57 */     return (T)this.a.getTileEntity(this.b);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\SourceBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */