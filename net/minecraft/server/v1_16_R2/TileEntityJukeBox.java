/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TileEntityJukeBox
/*    */   extends TileEntity
/*    */   implements Clearable
/*    */ {
/* 10 */   private ItemStack a = ItemStack.b;
/*    */   
/*    */   public TileEntityJukeBox() {
/* 13 */     super(TileEntityTypes.JUKEBOX);
/*    */   }
/*    */ 
/*    */   
/*    */   public void load(IBlockData var0, NBTTagCompound var1) {
/* 18 */     super.load(var0, var1);
/*    */     
/* 20 */     if (var1.hasKeyOfType("RecordItem", 10)) {
/* 21 */       setRecord(ItemStack.a(var1.getCompound("RecordItem")));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound save(NBTTagCompound var0) {
/* 27 */     super.save(var0);
/*    */     
/* 29 */     if (!getRecord().isEmpty()) {
/* 30 */       var0.set("RecordItem", getRecord().save(new NBTTagCompound()));
/*    */     }
/*    */     
/* 33 */     return var0;
/*    */   }
/*    */   
/*    */   public ItemStack getRecord() {
/* 37 */     return this.a;
/*    */   }
/*    */   
/*    */   public void setRecord(ItemStack var0) {
/* 41 */     this.a = var0;
/* 42 */     update();
/*    */   }
/*    */ 
/*    */   
/*    */   public void clear() {
/* 47 */     setRecord(ItemStack.b);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntityJukeBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */