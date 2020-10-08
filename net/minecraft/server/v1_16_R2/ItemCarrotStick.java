/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemCarrotStick<T extends Entity & ISteerable>
/*    */   extends Item
/*    */ {
/*    */   private final EntityTypes<T> a;
/*    */   private final int b;
/*    */   
/*    */   public ItemCarrotStick(Item.Info var0, EntityTypes<T> var1, int var2) {
/* 17 */     super(var0);
/*    */     
/* 19 */     this.a = var1;
/* 20 */     this.b = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World var0, EntityHuman var1, EnumHand var2) {
/* 25 */     ItemStack var3 = var1.b(var2);
/* 26 */     if (var0.isClientSide) {
/* 27 */       return InteractionResultWrapper.pass(var3);
/*    */     }
/*    */     
/* 30 */     Entity var4 = var1.getVehicle();
/*    */     
/* 32 */     if (var1.isPassenger() && var4 instanceof ISteerable && var4.getEntityType() == this.a) {
/* 33 */       ISteerable var5 = (ISteerable)var4;
/*    */       
/* 35 */       if (var5.O_()) {
/* 36 */         var3.damage(this.b, var1, var1 -> var1.broadcastItemBreak(var0));
/* 37 */         if (var3.isEmpty()) {
/* 38 */           ItemStack var6 = new ItemStack(Items.FISHING_ROD);
/* 39 */           var6.setTag(var3.getTag());
/* 40 */           return InteractionResultWrapper.success(var6);
/*    */         } 
/* 42 */         return InteractionResultWrapper.success(var3);
/*    */       } 
/*    */     } 
/*    */     
/* 46 */     var1.b(StatisticList.ITEM_USED.b(this));
/*    */     
/* 48 */     return InteractionResultWrapper.pass(var3);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemCarrotStick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */