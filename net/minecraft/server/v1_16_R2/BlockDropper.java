/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryDoubleChest;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.inventory.InventoryMoveItemEvent;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class BlockDropper extends BlockDispenser {
/* 10 */   private static final IDispenseBehavior c = new DispenseBehaviorItem();
/*    */   
/*    */   public BlockDropper(BlockBase.Info blockbase_info) {
/* 13 */     super(blockbase_info);
/*    */   }
/*    */ 
/*    */   
/*    */   protected IDispenseBehavior a(ItemStack itemstack) {
/* 18 */     return c;
/*    */   }
/*    */ 
/*    */   
/*    */   public TileEntity createTile(IBlockAccess iblockaccess) {
/* 23 */     return new TileEntityDropper();
/*    */   }
/*    */ 
/*    */   
/*    */   public void dispense(WorldServer worldserver, BlockPosition blockposition) {
/* 28 */     SourceBlock sourceblock = new SourceBlock(worldserver, blockposition);
/* 29 */     TileEntityDispenser tileentitydispenser = sourceblock.<TileEntityDispenser>getTileEntity();
/* 30 */     int i = tileentitydispenser.h();
/*    */     
/* 32 */     if (i < 0) {
/* 33 */       worldserver.triggerEffect(1001, blockposition, 0);
/*    */     } else {
/* 35 */       ItemStack itemstack = tileentitydispenser.getItem(i);
/*    */       
/* 37 */       if (!itemstack.isEmpty()) {
/* 38 */         ItemStack itemstack1; EnumDirection enumdirection = (EnumDirection)worldserver.getType(blockposition).get(FACING);
/* 39 */         IInventory iinventory = TileEntityHopper.b(worldserver, blockposition.shift(enumdirection));
/*    */ 
/*    */         
/* 42 */         if (iinventory == null) {
/* 43 */           itemstack1 = c.dispense(sourceblock, itemstack);
/*    */         } else {
/*    */           Inventory destinationInventory;
/* 46 */           CraftItemStack oitemstack = CraftItemStack.asCraftMirror(itemstack.cloneItemStack().cloneAndSubtract(1));
/*    */ 
/*    */ 
/*    */           
/* 50 */           if (iinventory instanceof InventoryLargeChest) {
/* 51 */             CraftInventoryDoubleChest craftInventoryDoubleChest = new CraftInventoryDoubleChest((InventoryLargeChest)iinventory);
/*    */           } else {
/* 53 */             destinationInventory = iinventory.getOwner().getInventory();
/*    */           } 
/*    */           
/* 56 */           InventoryMoveItemEvent event = new InventoryMoveItemEvent(tileentitydispenser.getOwner().getInventory(), (ItemStack)oitemstack.clone(), destinationInventory, true);
/* 57 */           worldserver.getServer().getPluginManager().callEvent((Event)event);
/* 58 */           if (event.isCancelled()) {
/*    */             return;
/*    */           }
/* 61 */           itemstack1 = TileEntityHopper.addItem(tileentitydispenser, iinventory, CraftItemStack.asNMSCopy(event.getItem()), enumdirection.opposite());
/* 62 */           if (event.getItem().equals(oitemstack) && itemstack1.isEmpty()) {
/*    */             
/* 64 */             itemstack1 = itemstack.cloneItemStack();
/* 65 */             itemstack1.subtract(1);
/*    */           } else {
/* 67 */             itemstack1 = itemstack.cloneItemStack();
/*    */           } 
/*    */         } 
/*    */         
/* 71 */         tileentitydispenser.setItem(i, itemstack1);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockDropper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */