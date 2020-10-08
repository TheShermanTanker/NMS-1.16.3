/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SlotFurnaceFuel
/*    */   extends Slot
/*    */ {
/*    */   private final ContainerFurnace a;
/*    */   
/*    */   public SlotFurnaceFuel(ContainerFurnace var0, IInventory var1, int var2, int var3, int var4) {
/* 11 */     super(var1, var2, var3, var4);
/* 12 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isAllowed(ItemStack var0) {
/* 17 */     return (this.a.b(var0) || c_(var0));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxStackSize(ItemStack var0) {
/* 22 */     return c_(var0) ? 1 : super.getMaxStackSize(var0);
/*    */   }
/*    */   
/*    */   public static boolean c_(ItemStack var0) {
/* 26 */     return (var0.getItem() == Items.BUCKET);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\SlotFurnaceFuel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */