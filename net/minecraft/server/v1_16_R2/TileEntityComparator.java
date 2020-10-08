/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ public class TileEntityComparator
/*    */   extends TileEntity
/*    */ {
/*    */   private int a;
/*    */   
/*    */   public TileEntityComparator() {
/* 10 */     super(TileEntityTypes.COMPARATOR);
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound save(NBTTagCompound var0) {
/* 15 */     super.save(var0);
/* 16 */     var0.setInt("OutputSignal", this.a);
/*    */     
/* 18 */     return var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void load(IBlockData var0, NBTTagCompound var1) {
/* 23 */     super.load(var0, var1);
/* 24 */     this.a = var1.getInt("OutputSignal");
/*    */   }
/*    */   
/*    */   public int d() {
/* 28 */     return this.a;
/*    */   }
/*    */   
/*    */   public void a(int var0) {
/* 32 */     this.a = var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntityComparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */