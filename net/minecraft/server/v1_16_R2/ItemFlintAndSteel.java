/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*    */ 
/*    */ public class ItemFlintAndSteel extends Item {
/*    */   public ItemFlintAndSteel(Item.Info item_info) {
/*  6 */     super(item_info);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult a(ItemActionContext itemactioncontext) {
/* 11 */     EntityHuman entityhuman = itemactioncontext.getEntity();
/* 12 */     World world = itemactioncontext.getWorld();
/* 13 */     BlockPosition blockposition = itemactioncontext.getClickPosition();
/* 14 */     IBlockData iblockdata = world.getType(blockposition);
/*    */     
/* 16 */     if (BlockCampfire.h(iblockdata) && !CraftEventFactory.callBlockIgniteEvent(world, blockposition, entityhuman).isCancelled()) {
/* 17 */       world.playSound(entityhuman, blockposition, SoundEffects.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, RANDOM.nextFloat() * 0.4F + 0.8F);
/* 18 */       world.setTypeAndData(blockposition, iblockdata.set(BlockProperties.r, Boolean.valueOf(true)), 11);
/* 19 */       if (entityhuman != null) {
/* 20 */         itemactioncontext.getItemStack().damage(1, entityhuman, entityhuman1 -> entityhuman1.broadcastItemBreak(itemactioncontext.getHand()));
/*    */       }
/*    */ 
/*    */ 
/*    */       
/* 25 */       return EnumInteractionResult.a(world.s_());
/*    */     } 
/* 27 */     BlockPosition blockposition1 = blockposition.shift(itemactioncontext.getClickedFace());
/*    */     
/* 29 */     if (BlockFireAbstract.a(world, blockposition1, itemactioncontext.f())) {
/*    */       
/* 31 */       if (CraftEventFactory.callBlockIgniteEvent(world, blockposition1, BlockIgniteEvent.IgniteCause.FLINT_AND_STEEL, entityhuman).isCancelled()) {
/* 32 */         itemactioncontext.getItemStack().damage(1, entityhuman, entityhuman1 -> entityhuman1.broadcastItemBreak(itemactioncontext.getHand()));
/*    */ 
/*    */         
/* 35 */         return EnumInteractionResult.PASS;
/*    */       } 
/*    */       
/* 38 */       world.playSound(entityhuman, blockposition1, SoundEffects.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, RANDOM.nextFloat() * 0.4F + 0.8F);
/* 39 */       IBlockData iblockdata1 = BlockFireAbstract.a(world, blockposition1);
/*    */       
/* 41 */       world.setTypeAndData(blockposition1, iblockdata1, 11);
/* 42 */       ItemStack itemstack = itemactioncontext.getItemStack();
/*    */       
/* 44 */       if (entityhuman instanceof EntityPlayer) {
/* 45 */         CriterionTriggers.y.a((EntityPlayer)entityhuman, blockposition1, itemstack);
/* 46 */         itemstack.damage(1, entityhuman, entityhuman1 -> entityhuman1.broadcastItemBreak(itemactioncontext.getHand()));
/*    */       } 
/*    */ 
/*    */ 
/*    */       
/* 51 */       return EnumInteractionResult.a(world.s_());
/*    */     } 
/* 53 */     return EnumInteractionResult.FAIL;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemFlintAndSteel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */