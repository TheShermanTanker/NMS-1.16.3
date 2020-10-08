/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import org.bukkit.inventory.InventoryHolder;
/*    */ 
/*    */ public class InventoryEnderChest extends InventorySubcontainer {
/*    */   private TileEntityEnderChest a;
/*    */   
/*    */   public TileEntityEnderChest getTileEntity() {
/*  8 */     return this.a;
/*    */   }
/*    */   private final EntityHuman owner;
/*    */   
/*    */   public InventoryHolder getBukkitOwner() {
/* 13 */     return (InventoryHolder)this.owner.getBukkitEntity();
/*    */   }
/*    */ 
/*    */   
/*    */   public Location getLocation() {
/* 18 */     if (getTileEntity() == null) return null; 
/* 19 */     return new Location((World)this.a.getWorld().getWorld(), this.a.getPosition().getX(), this.a.getPosition().getY(), this.a.getPosition().getZ());
/*    */   }
/*    */   
/*    */   public InventoryEnderChest(EntityHuman owner) {
/* 23 */     super(27);
/* 24 */     this.owner = owner;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(TileEntityEnderChest tileentityenderchest) {
/* 29 */     this.a = tileentityenderchest;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(NBTTagList nbttaglist) {
/*    */     int i;
/* 36 */     for (i = 0; i < getSize(); i++) {
/* 37 */       setItem(i, ItemStack.b);
/*    */     }
/*    */     
/* 40 */     for (i = 0; i < nbttaglist.size(); i++) {
/* 41 */       NBTTagCompound nbttagcompound = nbttaglist.getCompound(i);
/* 42 */       int j = nbttagcompound.getByte("Slot") & 0xFF;
/*    */       
/* 44 */       if (j >= 0 && j < getSize()) {
/* 45 */         setItem(j, ItemStack.a(nbttagcompound));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public NBTTagList g() {
/* 53 */     NBTTagList nbttaglist = new NBTTagList();
/*    */     
/* 55 */     for (int i = 0; i < getSize(); i++) {
/* 56 */       ItemStack itemstack = getItem(i);
/*    */       
/* 58 */       if (!itemstack.isEmpty()) {
/* 59 */         NBTTagCompound nbttagcompound = new NBTTagCompound();
/*    */         
/* 61 */         nbttagcompound.setByte("Slot", (byte)i);
/* 62 */         itemstack.save(nbttagcompound);
/* 63 */         nbttaglist.add(nbttagcompound);
/*    */       } 
/*    */     } 
/*    */     
/* 67 */     return nbttaglist;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(EntityHuman entityhuman) {
/* 72 */     return (this.a != null && !this.a.a(entityhuman)) ? false : super.a(entityhuman);
/*    */   }
/*    */ 
/*    */   
/*    */   public void startOpen(EntityHuman entityhuman) {
/* 77 */     if (this.a != null) {
/* 78 */       this.a.d();
/*    */     }
/*    */     
/* 81 */     super.startOpen(entityhuman);
/*    */   }
/*    */ 
/*    */   
/*    */   public void closeContainer(EntityHuman entityhuman) {
/* 86 */     if (this.a != null) {
/* 87 */       this.a.f();
/*    */     }
/*    */     
/* 90 */     super.closeContainer(entityhuman);
/* 91 */     this.a = null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\InventoryEnderChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */