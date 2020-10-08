/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.block.BlockDispenseEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ public class DispenseBehaviorShulkerBox
/*    */   extends DispenseBehaviorMaybe
/*    */ {
/*    */   protected ItemStack a(ISourceBlock isourceblock, ItemStack itemstack) {
/* 14 */     a(false);
/* 15 */     Item item = itemstack.getItem();
/*    */     
/* 17 */     if (item instanceof ItemBlock) {
/* 18 */       EnumDirection enumdirection = (EnumDirection)isourceblock.getBlockData().get(BlockDispenser.FACING);
/* 19 */       BlockPosition blockposition = isourceblock.getBlockPosition().shift(enumdirection);
/* 20 */       EnumDirection enumdirection1 = isourceblock.getWorld().isEmpty(blockposition.down()) ? enumdirection : EnumDirection.UP;
/*    */ 
/*    */       
/* 23 */       Block bukkitBlock = isourceblock.getWorld().getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
/* 24 */       CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack);
/*    */       
/* 26 */       BlockDispenseEvent event = new BlockDispenseEvent(bukkitBlock, (ItemStack)craftItem.clone(), new Vector(blockposition.getX(), blockposition.getY(), blockposition.getZ()));
/* 27 */       if (!BlockDispenser.eventFired) {
/* 28 */         isourceblock.getWorld().getServer().getPluginManager().callEvent((Event)event);
/*    */       }
/*    */       
/* 31 */       if (event.isCancelled()) {
/* 32 */         return itemstack;
/*    */       }
/*    */       
/* 35 */       if (!event.getItem().equals(craftItem)) {
/*    */         
/* 37 */         ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
/* 38 */         IDispenseBehavior idispensebehavior = BlockDispenser.REGISTRY.get(eventStack.getItem());
/* 39 */         if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior != this) {
/* 40 */           idispensebehavior.dispense(isourceblock, eventStack);
/* 41 */           return itemstack;
/*    */         } 
/*    */       } 
/*    */ 
/*    */       
/* 46 */       a(((ItemBlock)item).a(new BlockActionContextDirectional(isourceblock.getWorld(), blockposition, enumdirection, itemstack, enumdirection1)).a());
/*    */     } 
/*    */     
/* 49 */     return itemstack;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DispenseBehaviorShulkerBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */