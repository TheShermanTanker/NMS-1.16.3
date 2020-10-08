/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class ItemToolMaterial extends Item {
/*    */   private final ToolMaterial a;
/*    */   
/*    */   public ItemToolMaterial(ToolMaterial var0, Item.Info var1) {
/*  7 */     super(var1.b(var0.a()));
/*  8 */     this.a = var0;
/*    */   }
/*    */   
/*    */   public ToolMaterial g() {
/* 12 */     return this.a;
/*    */   }
/*    */ 
/*    */   
/*    */   public int c() {
/* 17 */     return this.a.e();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(ItemStack var0, ItemStack var1) {
/* 22 */     return (this.a.f().test(var1) || super.a(var0, var1));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemToolMaterial.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */