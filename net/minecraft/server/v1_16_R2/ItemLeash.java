/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.entity.Hanging;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.hanging.HangingPlaceEvent;
/*    */ 
/*    */ public class ItemLeash extends Item {
/*    */   public ItemLeash(Item.Info item_info) {
/* 11 */     super(item_info);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult a(ItemActionContext itemactioncontext) {
/* 16 */     World world = itemactioncontext.getWorld();
/* 17 */     BlockPosition blockposition = itemactioncontext.getClickPosition();
/* 18 */     Block block = world.getType(blockposition).getBlock();
/*    */     
/* 20 */     if (block.a(TagsBlock.FENCES)) {
/* 21 */       EntityHuman entityhuman = itemactioncontext.getEntity();
/*    */       
/* 23 */       if (!world.isClientSide && entityhuman != null) {
/* 24 */         a(entityhuman, world, blockposition);
/*    */       }
/*    */       
/* 27 */       return EnumInteractionResult.a(world.isClientSide);
/*    */     } 
/* 29 */     return EnumInteractionResult.PASS;
/*    */   }
/*    */ 
/*    */   
/*    */   public static EnumInteractionResult a(EntityHuman entityhuman, World world, BlockPosition blockposition) {
/* 34 */     EntityLeash entityleash = null;
/* 35 */     boolean flag = false;
/* 36 */     double d0 = 7.0D;
/* 37 */     int i = blockposition.getX();
/* 38 */     int j = blockposition.getY();
/* 39 */     int k = blockposition.getZ();
/* 40 */     List<EntityInsentient> list = world.a(EntityInsentient.class, new AxisAlignedBB(i - 7.0D, j - 7.0D, k - 7.0D, i + 7.0D, j + 7.0D, k + 7.0D));
/* 41 */     Iterator<EntityInsentient> iterator = list.iterator();
/*    */     
/* 43 */     while (iterator.hasNext()) {
/* 44 */       EntityInsentient entityinsentient = iterator.next();
/*    */       
/* 46 */       if (entityinsentient.getLeashHolder() == entityhuman) {
/* 47 */         if (entityleash == null) {
/* 48 */           entityleash = EntityLeash.a(world, blockposition);
/*    */ 
/*    */           
/* 51 */           HangingPlaceEvent event = new HangingPlaceEvent((Hanging)entityleash.getBukkitEntity(), (entityhuman != null) ? (Player)entityhuman.getBukkitEntity() : null, world.getWorld().getBlockAt(i, j, k), BlockFace.SELF);
/* 52 */           world.getServer().getPluginManager().callEvent((Event)event);
/*    */           
/* 54 */           if (event.isCancelled()) {
/* 55 */             entityleash.die();
/* 56 */             return EnumInteractionResult.PASS;
/*    */           } 
/*    */         } 
/*    */ 
/*    */ 
/*    */         
/* 62 */         if (CraftEventFactory.callPlayerLeashEntityEvent(entityinsentient, entityleash, entityhuman).isCancelled()) {
/*    */           continue;
/*    */         }
/*    */ 
/*    */         
/* 67 */         entityinsentient.setLeashHolder(entityleash, true);
/* 68 */         flag = true;
/*    */       } 
/*    */     } 
/*    */     
/* 72 */     return flag ? EnumInteractionResult.SUCCESS : EnumInteractionResult.PASS;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemLeash.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */