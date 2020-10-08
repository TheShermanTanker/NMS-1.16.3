/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.block.BlockDispenseEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ public class DispenseBehaviorShears
/*    */   extends DispenseBehaviorMaybe
/*    */ {
/*    */   protected ItemStack a(ISourceBlock isourceblock, ItemStack itemstack) {
/* 17 */     WorldServer worldserver = isourceblock.getWorld();
/*    */     
/* 19 */     Block bukkitBlock = worldserver.getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
/* 20 */     CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack);
/*    */     
/* 22 */     BlockDispenseEvent event = new BlockDispenseEvent(bukkitBlock, (ItemStack)craftItem.clone(), new Vector(0, 0, 0));
/* 23 */     if (!BlockDispenser.eventFired) {
/* 24 */       worldserver.getServer().getPluginManager().callEvent((Event)event);
/*    */     }
/*    */     
/* 27 */     if (event.isCancelled()) {
/* 28 */       return itemstack;
/*    */     }
/*    */     
/* 31 */     if (!event.getItem().equals(craftItem)) {
/*    */       
/* 33 */       ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
/* 34 */       IDispenseBehavior idispensebehavior = BlockDispenser.REGISTRY.get(eventStack.getItem());
/* 35 */       if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior != this) {
/* 36 */         idispensebehavior.dispense(isourceblock, eventStack);
/* 37 */         return itemstack;
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 42 */     if (!worldserver.s_()) {
/* 43 */       BlockPosition blockposition = isourceblock.getBlockPosition().shift((EnumDirection)isourceblock.getBlockData().get(BlockDispenser.FACING));
/*    */       
/* 45 */       a((a(worldserver, blockposition) || b(worldserver, blockposition, bukkitBlock, craftItem)));
/* 46 */       if (a() && itemstack.isDamaged(1, worldserver.getRandom(), (EntityPlayer)null)) {
/* 47 */         itemstack.setCount(0);
/*    */       }
/*    */     } 
/*    */     
/* 51 */     return itemstack;
/*    */   }
/*    */   
/*    */   private static boolean a(WorldServer worldserver, BlockPosition blockposition) {
/* 55 */     IBlockData iblockdata = worldserver.getType(blockposition);
/*    */     
/* 57 */     if (iblockdata.a(TagsBlock.BEEHIVES)) {
/* 58 */       int i = ((Integer)iblockdata.get(BlockBeehive.b)).intValue();
/*    */       
/* 60 */       if (i >= 5) {
/* 61 */         worldserver.playSound((EntityHuman)null, blockposition, SoundEffects.BLOCK_BEEHIVE_SHEAR, SoundCategory.BLOCKS, 1.0F, 1.0F);
/* 62 */         BlockBeehive.a(worldserver, blockposition);
/* 63 */         ((BlockBeehive)iblockdata.getBlock()).a(worldserver, iblockdata, blockposition, (EntityHuman)null, TileEntityBeehive.ReleaseStatus.BEE_RELEASED);
/* 64 */         return true;
/*    */       } 
/*    */     } 
/*    */     
/* 68 */     return false;
/*    */   }
/*    */   
/*    */   private static boolean b(WorldServer worldserver, BlockPosition blockposition, Block bukkitBlock, CraftItemStack craftItem) {
/* 72 */     List<EntityLiving> list = (List)worldserver.a((Class)EntityLiving.class, new AxisAlignedBB(blockposition), IEntitySelector.g);
/* 73 */     Iterator<EntityLiving> iterator = list.iterator();
/*    */     
/* 75 */     while (iterator.hasNext()) {
/* 76 */       EntityLiving entityliving = iterator.next();
/*    */       
/* 78 */       if (entityliving instanceof IShearable) {
/* 79 */         IShearable ishearable = (IShearable)entityliving;
/*    */         
/* 81 */         if (ishearable.canShear()) {
/*    */           
/* 83 */           if (CraftEventFactory.callBlockShearEntityEvent(entityliving, bukkitBlock, craftItem).isCancelled()) {
/*    */             continue;
/*    */           }
/*    */           
/* 87 */           ishearable.shear(SoundCategory.BLOCKS);
/* 88 */           return true;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 93 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DispenseBehaviorShears.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */