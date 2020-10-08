/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlock;
/*    */ import org.bukkit.entity.Hanging;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.hanging.HangingPlaceEvent;
/*    */ 
/*    */ public class ItemHanging extends Item {
/*    */   public ItemHanging(EntityTypes<? extends EntityHanging> entitytypes, Item.Info item_info) {
/* 13 */     super(item_info);
/* 14 */     this.a = entitytypes;
/*    */   }
/*    */   private final EntityTypes<? extends EntityHanging> a;
/*    */   public EnumInteractionResult a(ItemActionContext itemactioncontext) {
/*    */     Object object;
/* 19 */     BlockPosition blockposition = itemactioncontext.getClickPosition();
/* 20 */     EnumDirection enumdirection = itemactioncontext.getClickedFace();
/* 21 */     BlockPosition blockposition1 = blockposition.shift(enumdirection);
/* 22 */     EntityHuman entityhuman = itemactioncontext.getEntity();
/* 23 */     ItemStack itemstack = itemactioncontext.getItemStack();
/*    */     
/* 25 */     if (entityhuman != null && !a(entityhuman, enumdirection, itemstack, blockposition1)) {
/* 26 */       return EnumInteractionResult.FAIL;
/*    */     }
/* 28 */     World world = itemactioncontext.getWorld();
/*    */ 
/*    */     
/* 31 */     if (this.a == EntityTypes.PAINTING) {
/* 32 */       object = new EntityPainting(world, blockposition1, enumdirection);
/*    */     } else {
/* 34 */       if (this.a != EntityTypes.ITEM_FRAME) {
/* 35 */         return EnumInteractionResult.a(world.isClientSide);
/*    */       }
/*    */       
/* 38 */       object = new EntityItemFrame(world, blockposition1, enumdirection);
/*    */     } 
/*    */     
/* 41 */     NBTTagCompound nbttagcompound = itemstack.getTag();
/*    */     
/* 43 */     if (nbttagcompound != null) {
/* 44 */       EntityTypes.a(world, entityhuman, (Entity)object, nbttagcompound);
/*    */     }
/*    */     
/* 47 */     if (((EntityHanging)object).survives()) {
/* 48 */       if (!world.isClientSide) {
/*    */         
/* 50 */         Player who = (itemactioncontext.getEntity() == null) ? null : (Player)itemactioncontext.getEntity().getBukkitEntity();
/* 51 */         Block blockClicked = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/* 52 */         BlockFace blockFace = CraftBlock.notchToBlockFace(enumdirection);
/*    */         
/* 54 */         HangingPlaceEvent event = new HangingPlaceEvent((Hanging)((EntityHanging)object).getBukkitEntity(), who, blockClicked, blockFace);
/* 55 */         world.getServer().getPluginManager().callEvent((Event)event);
/*    */         
/* 57 */         if (event.isCancelled()) {
/* 58 */           return EnumInteractionResult.FAIL;
/*    */         }
/*    */         
/* 61 */         ((EntityHanging)object).playPlaceSound();
/* 62 */         world.addEntity((Entity)object);
/*    */       } 
/*    */       
/* 65 */       itemstack.subtract(1);
/* 66 */       return EnumInteractionResult.a(world.isClientSide);
/*    */     } 
/* 68 */     return EnumInteractionResult.CONSUME;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean a(EntityHuman entityhuman, EnumDirection enumdirection, ItemStack itemstack, BlockPosition blockposition) {
/* 74 */     return (!enumdirection.n().c() && entityhuman.a(blockposition, enumdirection, itemstack));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemHanging.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */