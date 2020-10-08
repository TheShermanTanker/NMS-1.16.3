/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ 
/*    */ public abstract class TileEntityContainer extends TileEntity implements IInventory, ITileInventory, INamableTileEntity {
/*    */   public ChestLock chestLock;
/*    */   
/*    */   protected TileEntityContainer(TileEntityTypes<?> tileentitytypes) {
/* 11 */     super(tileentitytypes);
/* 12 */     this.chestLock = ChestLock.a;
/*    */   }
/*    */   public IChatBaseComponent customName;
/*    */   
/*    */   public void load(IBlockData iblockdata, NBTTagCompound nbttagcompound) {
/* 17 */     super.load(iblockdata, nbttagcompound);
/* 18 */     this.chestLock = ChestLock.b(nbttagcompound);
/* 19 */     if (nbttagcompound.hasKeyOfType("CustomName", 8)) {
/* 20 */       this.customName = MCUtil.getBaseComponentFromNbt("CustomName", nbttagcompound);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public NBTTagCompound save(NBTTagCompound nbttagcompound) {
/* 27 */     super.save(nbttagcompound);
/* 28 */     this.chestLock.a(nbttagcompound);
/* 29 */     if (this.customName != null) {
/* 30 */       nbttagcompound.setString("CustomName", IChatBaseComponent.ChatSerializer.a(this.customName));
/*    */     }
/*    */     
/* 33 */     return nbttagcompound;
/*    */   }
/*    */   
/*    */   public void setCustomName(IChatBaseComponent ichatbasecomponent) {
/* 37 */     this.customName = ichatbasecomponent;
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatBaseComponent getDisplayName() {
/* 42 */     return (this.customName != null) ? this.customName : getContainerName();
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatBaseComponent getScoreboardDisplayName() {
/* 47 */     return getDisplayName();
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public IChatBaseComponent getCustomName() {
/* 53 */     return this.customName;
/*    */   }
/*    */   
/*    */   protected abstract IChatBaseComponent getContainerName();
/*    */   
/*    */   public boolean e(EntityHuman entityhuman) {
/* 59 */     return a(entityhuman, this.chestLock, getScoreboardDisplayName());
/*    */   }
/*    */   
/*    */   public static boolean a(EntityHuman entityhuman, ChestLock chestlock, IChatBaseComponent ichatbasecomponent) {
/* 63 */     if (!entityhuman.isSpectator() && !chestlock.a(entityhuman.getItemInMainHand())) {
/* 64 */       entityhuman.a(new ChatMessage("container.isLocked", new Object[] { ichatbasecomponent }), true);
/* 65 */       entityhuman.a(SoundEffects.BLOCK_CHEST_LOCKED, SoundCategory.BLOCKS, 1.0F, 1.0F);
/* 66 */       return false;
/*    */     } 
/* 68 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Container createMenu(int i, PlayerInventory playerinventory, EntityHuman entityhuman) {
/* 75 */     return e(entityhuman) ? createContainer(i, playerinventory) : null;
/*    */   }
/*    */ 
/*    */   
/*    */   protected abstract Container createContainer(int paramInt, PlayerInventory paramPlayerInventory);
/*    */ 
/*    */   
/*    */   public Location getLocation() {
/* 83 */     if (this.world == null) return null; 
/* 84 */     return new Location((World)this.world.getWorld(), this.position.getX(), this.position.getY(), this.position.getZ());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntityContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */