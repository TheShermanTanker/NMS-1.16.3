/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import org.bukkit.event.entity.EntityPotionEffectEvent;
/*    */ 
/*    */ public class ItemMilkBucket extends Item {
/*    */   public ItemMilkBucket(Item.Info item_info) {
/*  6 */     super(item_info);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(ItemStack itemstack, World world, EntityLiving entityliving) {
/* 11 */     if (entityliving instanceof EntityPlayer) {
/* 12 */       EntityPlayer entityplayer = (EntityPlayer)entityliving;
/*    */       
/* 14 */       CriterionTriggers.z.a(entityplayer, itemstack);
/* 15 */       entityplayer.b(StatisticList.ITEM_USED.b(this));
/*    */     } 
/*    */     
/* 18 */     if (entityliving instanceof EntityHuman && !((EntityHuman)entityliving).abilities.canInstantlyBuild) {
/* 19 */       itemstack.subtract(1);
/*    */     }
/*    */     
/* 22 */     if (!world.isClientSide) {
/* 23 */       entityliving.removeAllEffects(EntityPotionEffectEvent.Cause.MILK);
/*    */     }
/*    */     
/* 26 */     return itemstack.isEmpty() ? new ItemStack(Items.BUCKET) : itemstack;
/*    */   }
/*    */ 
/*    */   
/*    */   public int e_(ItemStack itemstack) {
/* 31 */     return 32;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumAnimation d_(ItemStack itemstack) {
/* 36 */     return EnumAnimation.DRINK;
/*    */   }
/*    */ 
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
/* 41 */     return ItemLiquidUtil.a(world, entityhuman, enumhand);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemMilkBucket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */