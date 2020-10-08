/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*    */ 
/*    */ public class ItemEndCrystal extends Item {
/*    */   public ItemEndCrystal(Item.Info item_info) {
/*  8 */     super(item_info);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult a(ItemActionContext itemactioncontext) {
/* 13 */     World world = itemactioncontext.getWorld();
/* 14 */     BlockPosition blockposition = itemactioncontext.getClickPosition();
/* 15 */     IBlockData iblockdata = world.getType(blockposition);
/*    */     
/* 17 */     if (!iblockdata.a(Blocks.OBSIDIAN) && !iblockdata.a(Blocks.BEDROCK)) {
/* 18 */       return EnumInteractionResult.FAIL;
/*    */     }
/* 20 */     BlockPosition blockposition1 = blockposition.up();
/*    */     
/* 22 */     if (!world.isEmpty(blockposition1)) {
/* 23 */       return EnumInteractionResult.FAIL;
/*    */     }
/* 25 */     double d0 = blockposition1.getX();
/* 26 */     double d1 = blockposition1.getY();
/* 27 */     double d2 = blockposition1.getZ();
/* 28 */     List<Entity> list = world.getEntities((Entity)null, new AxisAlignedBB(d0, d1, d2, d0 + 1.0D, d1 + 2.0D, d2 + 1.0D));
/*    */     
/* 30 */     if (!list.isEmpty()) {
/* 31 */       return EnumInteractionResult.FAIL;
/*    */     }
/* 33 */     if (world instanceof WorldServer) {
/* 34 */       EntityEnderCrystal entityendercrystal = new EntityEnderCrystal(world, d0 + 0.5D, d1, d2 + 0.5D);
/*    */       
/* 36 */       entityendercrystal.setShowingBottom(false);
/*    */       
/* 38 */       if (CraftEventFactory.callEntityPlaceEvent(itemactioncontext, entityendercrystal).isCancelled()) {
/* 39 */         return EnumInteractionResult.FAIL;
/*    */       }
/*    */       
/* 42 */       world.addEntity(entityendercrystal);
/* 43 */       EnderDragonBattle enderdragonbattle = ((WorldServer)world).getDragonBattle();
/*    */       
/* 45 */       if (enderdragonbattle != null) {
/* 46 */         enderdragonbattle.initiateRespawn();
/*    */       }
/*    */     } 
/*    */     
/* 50 */     itemactioncontext.getItemStack().subtract(1);
/* 51 */     return EnumInteractionResult.a(world.isClientSide);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean e(ItemStack itemstack) {
/* 59 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemEndCrystal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */