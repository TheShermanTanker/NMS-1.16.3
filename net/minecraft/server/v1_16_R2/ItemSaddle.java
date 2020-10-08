/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemSaddle
/*    */   extends Item
/*    */ {
/*    */   public ItemSaddle(Item.Info var0) {
/* 12 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult a(ItemStack var0, EntityHuman var1, EntityLiving var2, EnumHand var3) {
/* 17 */     if (var2 instanceof ISaddleable && var2.isAlive()) {
/* 18 */       ISaddleable var4 = (ISaddleable)var2;
/* 19 */       if (!var4.hasSaddle() && var4.canSaddle()) {
/* 20 */         if (!var1.world.isClientSide) {
/* 21 */           var4.saddle(SoundCategory.NEUTRAL);
/* 22 */           var0.subtract(1);
/*    */         } 
/* 24 */         return EnumInteractionResult.a(var1.world.isClientSide);
/*    */       } 
/*    */     } 
/* 27 */     return EnumInteractionResult.PASS;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemSaddle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */