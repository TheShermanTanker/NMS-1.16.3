/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemWaterLily
/*    */   extends ItemBlock
/*    */ {
/*    */   public ItemWaterLily(Block var0, Item.Info var1) {
/* 15 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult a(ItemActionContext var0) {
/* 20 */     return EnumInteractionResult.PASS;
/*    */   }
/*    */ 
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World var0, EntityHuman var1, EnumHand var2) {
/* 25 */     MovingObjectPositionBlock var3 = a(var0, var1, RayTrace.FluidCollisionOption.SOURCE_ONLY);
/* 26 */     MovingObjectPositionBlock var4 = var3.a(var3.getBlockPosition().up());
/* 27 */     EnumInteractionResult var5 = super.a(new ItemActionContext(var1, var2, var4));
/* 28 */     return new InteractionResultWrapper<>(var5, var1.b(var2));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemWaterLily.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */