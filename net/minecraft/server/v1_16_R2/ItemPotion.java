/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.bukkit.event.entity.EntityPotionEffectEvent;
/*    */ 
/*    */ public class ItemPotion extends Item {
/*    */   public ItemPotion(Item.Info item_info) {
/*  9 */     super(item_info);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack createItemStack() {
/* 14 */     return PotionUtil.a(super.createItemStack(), Potions.WATER);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(ItemStack itemstack, World world, EntityLiving entityliving) {
/* 19 */     EntityHuman entityhuman = (entityliving instanceof EntityHuman) ? (EntityHuman)entityliving : null;
/*    */     
/* 21 */     if (entityhuman instanceof EntityPlayer) {
/* 22 */       CriterionTriggers.z.a((EntityPlayer)entityhuman, itemstack);
/*    */     }
/*    */     
/* 25 */     if (!world.isClientSide) {
/* 26 */       List<MobEffect> list = PotionUtil.getEffects(itemstack);
/* 27 */       Iterator<MobEffect> iterator = list.iterator();
/*    */       
/* 29 */       while (iterator.hasNext()) {
/* 30 */         MobEffect mobeffect = iterator.next();
/*    */         
/* 32 */         if (mobeffect.getMobEffect().isInstant()) {
/* 33 */           mobeffect.getMobEffect().applyInstantEffect(entityhuman, entityhuman, entityliving, mobeffect.getAmplifier(), 1.0D); continue;
/*    */         } 
/* 35 */         entityliving.addEffect(new MobEffect(mobeffect), EntityPotionEffectEvent.Cause.POTION_DRINK);
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 40 */     if (entityhuman != null) {
/* 41 */       entityhuman.b(StatisticList.ITEM_USED.b(this));
/* 42 */       if (!entityhuman.abilities.canInstantlyBuild) {
/* 43 */         itemstack.subtract(1);
/*    */       }
/*    */     } 
/*    */     
/* 47 */     if (entityhuman == null || !entityhuman.abilities.canInstantlyBuild) {
/* 48 */       if (itemstack.isEmpty()) {
/* 49 */         return new ItemStack(Items.GLASS_BOTTLE);
/*    */       }
/*    */       
/* 52 */       if (entityhuman != null) {
/* 53 */         entityhuman.inventory.pickup(new ItemStack(Items.GLASS_BOTTLE));
/*    */       }
/*    */     } 
/*    */     
/* 57 */     return itemstack;
/*    */   }
/*    */ 
/*    */   
/*    */   public int e_(ItemStack itemstack) {
/* 62 */     return 32;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumAnimation d_(ItemStack itemstack) {
/* 67 */     return EnumAnimation.DRINK;
/*    */   }
/*    */ 
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
/* 72 */     return ItemLiquidUtil.a(world, entityhuman, enumhand);
/*    */   }
/*    */ 
/*    */   
/*    */   public String f(ItemStack itemstack) {
/* 77 */     return PotionUtil.d(itemstack).b(getName() + ".effect.");
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean e(ItemStack itemstack) {
/* 82 */     return (super.e(itemstack) || !PotionUtil.getEffects(itemstack).isEmpty());
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(CreativeModeTab creativemodetab, NonNullList<ItemStack> nonnulllist) {
/* 87 */     if (a(creativemodetab)) {
/* 88 */       Iterator<PotionRegistry> iterator = IRegistry.POTION.iterator();
/*    */       
/* 90 */       while (iterator.hasNext()) {
/* 91 */         PotionRegistry potionregistry = iterator.next();
/*    */         
/* 93 */         if (potionregistry != Potions.EMPTY)
/* 94 */           nonnulllist.add(PotionUtil.a(new ItemStack(this), potionregistry)); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */