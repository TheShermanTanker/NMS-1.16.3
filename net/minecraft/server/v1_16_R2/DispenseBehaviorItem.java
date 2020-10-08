/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftVector;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.block.BlockDispenseEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class DispenseBehaviorItem
/*    */   implements IDispenseBehavior
/*    */ {
/*    */   private EnumDirection enumdirection;
/*    */   
/*    */   public final ItemStack dispense(ISourceBlock isourceblock, ItemStack itemstack) {
/* 16 */     this.enumdirection = (EnumDirection)isourceblock.getBlockData().get(BlockDispenser.FACING);
/* 17 */     ItemStack itemstack1 = a(isourceblock, itemstack);
/*    */     
/* 19 */     a(isourceblock);
/* 20 */     a(isourceblock, this.enumdirection);
/* 21 */     return itemstack1;
/*    */   }
/*    */ 
/*    */   
/*    */   protected ItemStack a(ISourceBlock isourceblock, ItemStack itemstack) {
/* 26 */     IPosition iposition = BlockDispenser.a(isourceblock);
/* 27 */     ItemStack itemstack1 = itemstack.cloneAndSubtract(1);
/*    */ 
/*    */     
/* 30 */     if (!a(isourceblock.getWorld(), itemstack1, 6, this.enumdirection, isourceblock)) {
/* 31 */       itemstack.add(1);
/*    */     }
/*    */     
/* 34 */     return itemstack;
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean a(World world, ItemStack itemstack, int i, EnumDirection enumdirection, ISourceBlock isourceblock) {
/* 39 */     if (itemstack.isEmpty()) return true; 
/* 40 */     IPosition iposition = BlockDispenser.a(isourceblock);
/*    */     
/* 42 */     double d0 = iposition.getX();
/* 43 */     double d1 = iposition.getY();
/* 44 */     double d2 = iposition.getZ();
/*    */     
/* 46 */     if (enumdirection.n() == EnumDirection.EnumAxis.Y) {
/* 47 */       d1 -= 0.125D;
/*    */     } else {
/* 49 */       d1 -= 0.15625D;
/*    */     } 
/*    */     
/* 52 */     EntityItem entityitem = new EntityItem(world, d0, d1, d2, itemstack);
/* 53 */     double d3 = world.random.nextDouble() * 0.1D + 0.2D;
/*    */     
/* 55 */     entityitem.setMot(world.random.nextGaussian() * 0.007499999832361937D * i + enumdirection.getAdjacentX() * d3, world.random.nextGaussian() * 0.007499999832361937D * i + 0.20000000298023224D, world.random.nextGaussian() * 0.007499999832361937D * i + enumdirection.getAdjacentZ() * d3);
/*    */ 
/*    */     
/* 58 */     Block block = world.getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
/* 59 */     CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack);
/*    */     
/* 61 */     BlockDispenseEvent event = new BlockDispenseEvent(block, (ItemStack)craftItem.clone(), CraftVector.toBukkit(entityitem.getMot()));
/* 62 */     if (!BlockDispenser.eventFired) {
/* 63 */       world.getServer().getPluginManager().callEvent((Event)event);
/*    */     }
/*    */     
/* 66 */     if (event.isCancelled()) {
/* 67 */       return false;
/*    */     }
/*    */     
/* 70 */     entityitem.setItemStack(CraftItemStack.asNMSCopy(event.getItem()));
/* 71 */     entityitem.setMot(CraftVector.toNMS(event.getVelocity()));
/*    */     
/* 73 */     if (!event.getItem().getType().equals(craftItem.getType())) {
/*    */       
/* 75 */       ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
/* 76 */       IDispenseBehavior idispensebehavior = BlockDispenser.REGISTRY.get(eventStack.getItem());
/* 77 */       if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior.getClass() != DispenseBehaviorItem.class) {
/* 78 */         idispensebehavior.dispense(isourceblock, eventStack);
/*    */       } else {
/* 80 */         world.addEntity(entityitem);
/*    */       } 
/* 82 */       return false;
/*    */     } 
/*    */     
/* 85 */     world.addEntity(entityitem);
/*    */     
/* 87 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(ISourceBlock isourceblock) {
/* 92 */     isourceblock.getWorld().triggerEffect(1000, isourceblock.getBlockPosition(), 0);
/*    */   }
/*    */   
/*    */   protected void a(ISourceBlock isourceblock, EnumDirection enumdirection) {
/* 96 */     isourceblock.getWorld().triggerEffect(2000, isourceblock.getBlockPosition(), enumdirection.c());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DispenseBehaviorItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */