/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemMapEmpty
/*    */   extends ItemWorldMapBase
/*    */ {
/*    */   public ItemMapEmpty(Item.Info var0) {
/* 13 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World var0, EntityHuman var1, EnumHand var2) {
/* 18 */     ItemStack var3 = ItemWorldMap.createFilledMapView(var0, MathHelper.floor(var1.locX()), MathHelper.floor(var1.locZ()), (byte)0, true, false);
/*    */     
/* 20 */     ItemStack var4 = var1.b(var2);
/* 21 */     if (!var1.abilities.canInstantlyBuild) {
/* 22 */       var4.subtract(1);
/*    */     }
/*    */     
/* 25 */     var1.b(StatisticList.ITEM_USED.b(this));
/* 26 */     var1.playSound(SoundEffects.UI_CARTOGRAPHY_TABLE_TAKE_RESULT, 1.0F, 1.0F);
/*    */     
/* 28 */     if (var4.isEmpty()) {
/* 29 */       return InteractionResultWrapper.a(var3, var0.s_());
/*    */     }
/* 31 */     if (!var1.inventory.pickup(var3.cloneItemStack())) {
/* 32 */       var1.drop(var3, false);
/*    */     }
/*    */     
/* 35 */     return InteractionResultWrapper.a(var4, var0.s_());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemMapEmpty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */