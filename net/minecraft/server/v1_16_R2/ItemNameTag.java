/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemNameTag
/*    */   extends Item
/*    */ {
/*    */   public ItemNameTag(Item.Info var0) {
/* 11 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult a(ItemStack var0, EntityHuman var1, EntityLiving var2, EnumHand var3) {
/* 16 */     if (var0.hasName() && !(var2 instanceof EntityHuman)) {
/* 17 */       if (!var1.world.isClientSide && var2.isAlive()) {
/* 18 */         var2.setCustomName(var0.getName());
/* 19 */         if (var2 instanceof EntityInsentient) {
/* 20 */           ((EntityInsentient)var2).setPersistent();
/*    */         }
/*    */         
/* 23 */         var0.subtract(1);
/*    */       } 
/*    */       
/* 26 */       return EnumInteractionResult.a(var1.world.isClientSide);
/*    */     } 
/* 28 */     return EnumInteractionResult.PASS;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemNameTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */