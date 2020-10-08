/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class TileInventory
/*    */   implements ITileInventory
/*    */ {
/*    */   private final IChatBaseComponent a;
/*    */   private final ITileEntityContainer b;
/*    */   
/*    */   public TileInventory(ITileEntityContainer var0, IChatBaseComponent var1) {
/* 14 */     this.b = var0;
/* 15 */     this.a = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatBaseComponent getScoreboardDisplayName() {
/* 20 */     return this.a;
/*    */   }
/*    */ 
/*    */   
/*    */   public Container createMenu(int var0, PlayerInventory var1, EntityHuman var2) {
/* 25 */     return this.b.createMenu(var0, var1, var2);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */