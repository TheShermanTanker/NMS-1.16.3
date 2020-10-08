/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockDispenseEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ public class ItemMinecart extends Item {
/*  10 */   private static final IDispenseBehavior a = new DispenseBehaviorItem() {
/*  11 */       private final DispenseBehaviorItem b = new DispenseBehaviorItem();
/*     */       
/*     */       public ItemStack a(ISourceBlock isourceblock, ItemStack itemstack) {
/*     */         double d3;
/*  15 */         EnumDirection enumdirection = (EnumDirection)isourceblock.getBlockData().get(BlockDispenser.FACING);
/*  16 */         WorldServer worldserver = isourceblock.getWorld();
/*  17 */         double d0 = isourceblock.getX() + enumdirection.getAdjacentX() * 1.125D;
/*  18 */         double d1 = Math.floor(isourceblock.getY()) + enumdirection.getAdjacentY();
/*  19 */         double d2 = isourceblock.getZ() + enumdirection.getAdjacentZ() * 1.125D;
/*  20 */         BlockPosition blockposition = isourceblock.getBlockPosition().shift(enumdirection);
/*  21 */         IBlockData iblockdata = worldserver.getType(blockposition);
/*  22 */         BlockPropertyTrackPosition blockpropertytrackposition = (iblockdata.getBlock() instanceof BlockMinecartTrackAbstract) ? (BlockPropertyTrackPosition)iblockdata.get(((BlockMinecartTrackAbstract)iblockdata.getBlock()).d()) : BlockPropertyTrackPosition.NORTH_SOUTH;
/*     */ 
/*     */         
/*  25 */         if (iblockdata.a(TagsBlock.RAILS)) {
/*  26 */           if (blockpropertytrackposition.c()) {
/*  27 */             d3 = 0.6D;
/*     */           } else {
/*  29 */             d3 = 0.1D;
/*     */           } 
/*     */         } else {
/*  32 */           if (!iblockdata.isAir() || !worldserver.getType(blockposition.down()).a(TagsBlock.RAILS)) {
/*  33 */             return this.b.dispense(isourceblock, itemstack);
/*     */           }
/*     */           
/*  36 */           IBlockData iblockdata1 = worldserver.getType(blockposition.down());
/*  37 */           BlockPropertyTrackPosition blockpropertytrackposition1 = (iblockdata1.getBlock() instanceof BlockMinecartTrackAbstract) ? (BlockPropertyTrackPosition)iblockdata1.get(((BlockMinecartTrackAbstract)iblockdata1.getBlock()).d()) : BlockPropertyTrackPosition.NORTH_SOUTH;
/*     */           
/*  39 */           if (enumdirection != EnumDirection.DOWN && blockpropertytrackposition1.c()) {
/*  40 */             d3 = -0.4D;
/*     */           } else {
/*  42 */             d3 = -0.9D;
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/*  48 */         ItemStack itemstack1 = itemstack.cloneAndSubtract(1);
/*  49 */         Block block2 = worldserver.getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
/*  50 */         CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack1);
/*     */         
/*  52 */         BlockDispenseEvent event = new BlockDispenseEvent(block2, (ItemStack)craftItem.clone(), new Vector(d0, d1 + d3, d2));
/*  53 */         if (!BlockDispenser.eventFired) {
/*  54 */           worldserver.getServer().getPluginManager().callEvent((Event)event);
/*     */         }
/*     */         
/*  57 */         if (event.isCancelled()) {
/*  58 */           itemstack.add(1);
/*  59 */           return itemstack;
/*     */         } 
/*     */         
/*  62 */         if (!event.getItem().equals(craftItem)) {
/*  63 */           itemstack.add(1);
/*     */           
/*  65 */           ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
/*  66 */           IDispenseBehavior idispensebehavior = BlockDispenser.REGISTRY.get(eventStack.getItem());
/*  67 */           if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior != this) {
/*  68 */             idispensebehavior.dispense(isourceblock, eventStack);
/*  69 */             return itemstack;
/*     */           } 
/*     */         } 
/*     */         
/*  73 */         itemstack1 = CraftItemStack.asNMSCopy(event.getItem());
/*  74 */         EntityMinecartAbstract entityminecartabstract = EntityMinecartAbstract.a(worldserver, event.getVelocity().getX(), event.getVelocity().getY(), event.getVelocity().getZ(), ((ItemMinecart)itemstack1.getItem()).b);
/*     */         
/*  76 */         if (itemstack.hasName()) {
/*  77 */           entityminecartabstract.setCustomName(itemstack.getName());
/*     */         }
/*     */         
/*  80 */         if (!worldserver.addEntity(entityminecartabstract)) itemstack.add(1);
/*     */ 
/*     */         
/*  83 */         return itemstack;
/*     */       }
/*     */ 
/*     */       
/*     */       protected void a(ISourceBlock isourceblock) {
/*  88 */         isourceblock.getWorld().triggerEffect(1000, isourceblock.getBlockPosition(), 0);
/*     */       }
/*     */     };
/*     */   private final EntityMinecartAbstract.EnumMinecartType b;
/*     */   
/*     */   public ItemMinecart(EntityMinecartAbstract.EnumMinecartType entityminecartabstract_enumminecarttype, Item.Info item_info) {
/*  94 */     super(item_info);
/*  95 */     this.b = entityminecartabstract_enumminecarttype;
/*  96 */     BlockDispenser.a(this, a);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult a(ItemActionContext itemactioncontext) {
/* 101 */     World world = itemactioncontext.getWorld();
/* 102 */     BlockPosition blockposition = itemactioncontext.getClickPosition();
/* 103 */     IBlockData iblockdata = world.getType(blockposition);
/*     */     
/* 105 */     if (!iblockdata.a(TagsBlock.RAILS)) {
/* 106 */       return EnumInteractionResult.FAIL;
/*     */     }
/* 108 */     ItemStack itemstack = itemactioncontext.getItemStack();
/*     */     
/* 110 */     if (!world.isClientSide) {
/* 111 */       BlockPropertyTrackPosition blockpropertytrackposition = (iblockdata.getBlock() instanceof BlockMinecartTrackAbstract) ? (BlockPropertyTrackPosition)iblockdata.get(((BlockMinecartTrackAbstract)iblockdata.getBlock()).d()) : BlockPropertyTrackPosition.NORTH_SOUTH;
/* 112 */       double d0 = 0.0D;
/*     */       
/* 114 */       if (blockpropertytrackposition.c()) {
/* 115 */         d0 = 0.5D;
/*     */       }
/*     */       
/* 118 */       EntityMinecartAbstract entityminecartabstract = EntityMinecartAbstract.a(world, blockposition.getX() + 0.5D, blockposition.getY() + 0.0625D + d0, blockposition.getZ() + 0.5D, this.b);
/*     */       
/* 120 */       if (itemstack.hasName()) {
/* 121 */         entityminecartabstract.setCustomName(itemstack.getName());
/*     */       }
/*     */ 
/*     */       
/* 125 */       if (CraftEventFactory.callEntityPlaceEvent(itemactioncontext, entityminecartabstract).isCancelled()) {
/* 126 */         return EnumInteractionResult.FAIL;
/*     */       }
/*     */       
/* 129 */       if (!world.addEntity(entityminecartabstract)) return EnumInteractionResult.PASS;
/*     */     
/*     */     } 
/* 132 */     itemstack.subtract(1);
/* 133 */     return EnumInteractionResult.a(world.isClientSide);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemMinecart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */