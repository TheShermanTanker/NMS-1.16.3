/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.projectiles.CraftBlockProjectileSource;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.block.BlockDispenseEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.projectiles.ProjectileSource;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ public abstract class DispenseBehaviorProjectile extends DispenseBehaviorItem {
/*    */   public ItemStack a(ISourceBlock isourceblock, ItemStack itemstack) {
/* 14 */     WorldServer worldserver = isourceblock.getWorld();
/* 15 */     IPosition iposition = BlockDispenser.a(isourceblock);
/* 16 */     EnumDirection enumdirection = (EnumDirection)isourceblock.getBlockData().get(BlockDispenser.FACING);
/* 17 */     IProjectile iprojectile = a(worldserver, iposition, itemstack);
/*    */ 
/*    */ 
/*    */     
/* 21 */     ItemStack itemstack1 = itemstack.cloneAndSubtract(1);
/* 22 */     Block block = worldserver.getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
/* 23 */     CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack1);
/*    */     
/* 25 */     BlockDispenseEvent event = new BlockDispenseEvent(block, (ItemStack)craftItem.clone(), new Vector(enumdirection.getAdjacentX(), (enumdirection.getAdjacentY() + 0.1F), enumdirection.getAdjacentZ()));
/* 26 */     if (!BlockDispenser.eventFired) {
/* 27 */       worldserver.getServer().getPluginManager().callEvent((Event)event);
/*    */     }
/*    */     
/* 30 */     if (event.isCancelled()) {
/* 31 */       itemstack.add(1);
/* 32 */       return itemstack;
/*    */     } 
/*    */     
/* 35 */     if (!event.getItem().equals(craftItem)) {
/* 36 */       itemstack.add(1);
/*    */       
/* 38 */       ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
/* 39 */       IDispenseBehavior idispensebehavior = BlockDispenser.REGISTRY.get(eventStack.getItem());
/* 40 */       if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior != this) {
/* 41 */         idispensebehavior.dispense(isourceblock, eventStack);
/* 42 */         return itemstack;
/*    */       } 
/*    */     } 
/*    */     
/* 46 */     iprojectile.shoot(event.getVelocity().getX(), event.getVelocity().getY(), event.getVelocity().getZ(), getPower(), a());
/* 47 */     iprojectile.projectileSource = (ProjectileSource)new CraftBlockProjectileSource(isourceblock.<TileEntityDispenser>getTileEntity());
/*    */     
/* 49 */     worldserver.addEntity(iprojectile);
/*    */     
/* 51 */     return itemstack;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(ISourceBlock isourceblock) {
/* 56 */     isourceblock.getWorld().triggerEffect(1002, isourceblock.getBlockPosition(), 0);
/*    */   }
/*    */   
/*    */   protected abstract IProjectile a(World paramWorld, IPosition paramIPosition, ItemStack paramItemStack);
/*    */   
/*    */   protected float a() {
/* 62 */     return 6.0F;
/*    */   }
/*    */   
/*    */   protected float getPower() {
/* 66 */     return 1.1F;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DispenseBehaviorProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */