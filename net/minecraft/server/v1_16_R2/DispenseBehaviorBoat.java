/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.block.BlockDispenseEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ public class DispenseBehaviorBoat extends DispenseBehaviorItem {
/* 10 */   private final DispenseBehaviorItem b = new DispenseBehaviorItem();
/*    */   private final EntityBoat.EnumBoatType c;
/*    */   
/*    */   public DispenseBehaviorBoat(EntityBoat.EnumBoatType entityboat_enumboattype) {
/* 14 */     this.c = entityboat_enumboattype;
/*    */   }
/*    */   
/*    */   public ItemStack a(ISourceBlock isourceblock, ItemStack itemstack) {
/*    */     double d3;
/* 19 */     EnumDirection enumdirection = (EnumDirection)isourceblock.getBlockData().get(BlockDispenser.FACING);
/* 20 */     WorldServer worldserver = isourceblock.getWorld();
/* 21 */     double d0 = isourceblock.getX() + (enumdirection.getAdjacentX() * 1.125F);
/* 22 */     double d1 = isourceblock.getY() + (enumdirection.getAdjacentY() * 1.125F);
/* 23 */     double d2 = isourceblock.getZ() + (enumdirection.getAdjacentZ() * 1.125F);
/* 24 */     BlockPosition blockposition = isourceblock.getBlockPosition().shift(enumdirection);
/*    */ 
/*    */     
/* 27 */     if (worldserver.getFluid(blockposition).a(TagsFluid.WATER)) {
/* 28 */       d3 = 1.0D;
/*    */     } else {
/* 30 */       if (!worldserver.getType(blockposition).isAir() || !worldserver.getFluid(blockposition.down()).a(TagsFluid.WATER)) {
/* 31 */         return this.b.dispense(isourceblock, itemstack);
/*    */       }
/*    */       
/* 34 */       d3 = 0.0D;
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 39 */     ItemStack itemstack1 = itemstack.cloneAndSubtract(1);
/* 40 */     Block block = worldserver.getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
/* 41 */     CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack1);
/*    */     
/* 43 */     BlockDispenseEvent event = new BlockDispenseEvent(block, (ItemStack)craftItem.clone(), new Vector(d0, d1 + d3, d2));
/* 44 */     if (!BlockDispenser.eventFired) {
/* 45 */       worldserver.getServer().getPluginManager().callEvent((Event)event);
/*    */     }
/*    */     
/* 48 */     if (event.isCancelled()) {
/* 49 */       itemstack.add(1);
/* 50 */       return itemstack;
/*    */     } 
/*    */     
/* 53 */     if (!event.getItem().equals(craftItem)) {
/* 54 */       itemstack.add(1);
/*    */       
/* 56 */       ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
/* 57 */       IDispenseBehavior idispensebehavior = BlockDispenser.REGISTRY.get(eventStack.getItem());
/* 58 */       if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior != this) {
/* 59 */         idispensebehavior.dispense(isourceblock, eventStack);
/* 60 */         return itemstack;
/*    */       } 
/*    */     } 
/*    */     
/* 64 */     EntityBoat entityboat = new EntityBoat(worldserver, event.getVelocity().getX(), event.getVelocity().getY(), event.getVelocity().getZ());
/*    */ 
/*    */     
/* 67 */     entityboat.setType(this.c);
/* 68 */     entityboat.yaw = enumdirection.o();
/* 69 */     if (!worldserver.addEntity(entityboat)) itemstack.add(1);
/*    */     
/* 71 */     return itemstack;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(ISourceBlock isourceblock) {
/* 76 */     isourceblock.getWorld().triggerEffect(1000, isourceblock.getBlockPosition(), 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DispenseBehaviorBoat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */