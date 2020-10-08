/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*    */ 
/*    */ public class ItemFireball extends Item {
/*    */   public ItemFireball(Item.Info item_info) {
/*  6 */     super(item_info);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult a(ItemActionContext itemactioncontext) {
/* 11 */     World world = itemactioncontext.getWorld();
/* 12 */     BlockPosition blockposition = itemactioncontext.getClickPosition();
/* 13 */     IBlockData iblockdata = world.getType(blockposition);
/* 14 */     boolean flag = false;
/*    */     
/* 16 */     if (BlockCampfire.h(iblockdata)) {
/*    */       
/* 18 */       if (CraftEventFactory.callBlockIgniteEvent(world, blockposition, BlockIgniteEvent.IgniteCause.FIREBALL, itemactioncontext.getEntity()).isCancelled()) {
/* 19 */         if (!(itemactioncontext.getEntity()).abilities.canInstantlyBuild) {
/* 20 */           itemactioncontext.getItemStack().subtract(1);
/*    */         }
/* 22 */         return EnumInteractionResult.PASS;
/*    */       } 
/*    */       
/* 25 */       a(world, blockposition);
/* 26 */       world.setTypeUpdate(blockposition, iblockdata.set(BlockCampfire.b, Boolean.valueOf(true)));
/* 27 */       flag = true;
/*    */     } else {
/* 29 */       blockposition = blockposition.shift(itemactioncontext.getClickedFace());
/* 30 */       if (BlockFireAbstract.a(world, blockposition, itemactioncontext.f())) {
/*    */         
/* 32 */         if (CraftEventFactory.callBlockIgniteEvent(world, blockposition, BlockIgniteEvent.IgniteCause.FIREBALL, itemactioncontext.getEntity()).isCancelled()) {
/* 33 */           if (!(itemactioncontext.getEntity()).abilities.canInstantlyBuild) {
/* 34 */             itemactioncontext.getItemStack().subtract(1);
/*    */           }
/* 36 */           return EnumInteractionResult.PASS;
/*    */         } 
/*    */         
/* 39 */         a(world, blockposition);
/* 40 */         world.setTypeUpdate(blockposition, BlockFireAbstract.a(world, blockposition));
/* 41 */         flag = true;
/*    */       } 
/*    */     } 
/*    */     
/* 45 */     if (flag) {
/* 46 */       itemactioncontext.getItemStack().subtract(1);
/* 47 */       return EnumInteractionResult.a(world.isClientSide);
/*    */     } 
/* 49 */     return EnumInteractionResult.FAIL;
/*    */   }
/*    */ 
/*    */   
/*    */   private void a(World world, BlockPosition blockposition) {
/* 54 */     world.playSound((EntityHuman)null, blockposition, SoundEffects.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS, 1.0F, (RANDOM.nextFloat() - RANDOM.nextFloat()) * 0.2F + 1.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemFireball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */