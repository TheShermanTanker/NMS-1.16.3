/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftHumanEntity;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.inventory.InventoryHolder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InventoryLargeChest
/*     */   implements IInventory
/*     */ {
/*     */   public final IInventory left;
/*     */   public final IInventory right;
/*  18 */   public List<HumanEntity> transaction = new ArrayList<>();
/*     */   
/*     */   public List<ItemStack> getContents() {
/*  21 */     List<ItemStack> result = new ArrayList<>(getSize());
/*  22 */     for (int i = 0; i < getSize(); i++) {
/*  23 */       result.add(getItem(i));
/*     */     }
/*  25 */     return result;
/*     */   }
/*     */   
/*     */   public void onOpen(CraftHumanEntity who) {
/*  29 */     this.left.onOpen(who);
/*  30 */     this.right.onOpen(who);
/*  31 */     this.transaction.add(who);
/*     */   }
/*     */   
/*     */   public void onClose(CraftHumanEntity who) {
/*  35 */     this.left.onClose(who);
/*  36 */     this.right.onClose(who);
/*  37 */     this.transaction.remove(who);
/*     */   }
/*     */   
/*     */   public List<HumanEntity> getViewers() {
/*  41 */     return this.transaction;
/*     */   }
/*     */   
/*     */   public InventoryHolder getOwner() {
/*  45 */     return null;
/*     */   }
/*     */   
/*     */   public void setMaxStackSize(int size) {
/*  49 */     this.left.setMaxStackSize(size);
/*  50 */     this.right.setMaxStackSize(size);
/*     */   }
/*     */ 
/*     */   
/*     */   public Location getLocation() {
/*  55 */     return this.left.getLocation();
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryLargeChest(IInventory iinventory, IInventory iinventory1) {
/*  60 */     if (iinventory == null) {
/*  61 */       iinventory = iinventory1;
/*     */     }
/*     */     
/*  64 */     if (iinventory1 == null) {
/*  65 */       iinventory1 = iinventory;
/*     */     }
/*     */     
/*  68 */     this.left = iinventory;
/*  69 */     this.right = iinventory1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSize() {
/*  74 */     return this.left.getSize() + this.right.getSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/*  79 */     return (this.left.isEmpty() && this.right.isEmpty());
/*     */   }
/*     */   
/*     */   public boolean a(IInventory iinventory) {
/*  83 */     return (this.left == iinventory || this.right == iinventory);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getItem(int i) {
/*  88 */     return (i >= this.left.getSize()) ? this.right.getItem(i - this.left.getSize()) : this.left.getItem(i);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack splitStack(int i, int j) {
/*  93 */     return (i >= this.left.getSize()) ? this.right.splitStack(i - this.left.getSize(), j) : this.left.splitStack(i, j);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack splitWithoutUpdate(int i) {
/*  98 */     return (i >= this.left.getSize()) ? this.right.splitWithoutUpdate(i - this.left.getSize()) : this.left.splitWithoutUpdate(i);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItem(int i, ItemStack itemstack) {
/* 103 */     if (i >= this.left.getSize()) {
/* 104 */       this.right.setItem(i - this.left.getSize(), itemstack);
/*     */     } else {
/* 106 */       this.left.setItem(i, itemstack);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxStackSize() {
/* 113 */     return Math.min(this.left.getMaxStackSize(), this.right.getMaxStackSize());
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/* 118 */     this.left.update();
/* 119 */     this.right.update();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman entityhuman) {
/* 124 */     return (this.left.a(entityhuman) && this.right.a(entityhuman));
/*     */   }
/*     */ 
/*     */   
/*     */   public void startOpen(EntityHuman entityhuman) {
/* 129 */     this.left.startOpen(entityhuman);
/* 130 */     this.right.startOpen(entityhuman);
/*     */   }
/*     */ 
/*     */   
/*     */   public void closeContainer(EntityHuman entityhuman) {
/* 135 */     this.left.closeContainer(entityhuman);
/* 136 */     this.right.closeContainer(entityhuman);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(int i, ItemStack itemstack) {
/* 141 */     return (i >= this.left.getSize()) ? this.right.b(i - this.left.getSize(), itemstack) : this.left.b(i, itemstack);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 146 */     this.left.clear();
/* 147 */     this.right.clear();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\InventoryLargeChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */