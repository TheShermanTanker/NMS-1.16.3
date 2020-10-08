/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftHumanEntity;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.event.inventory.InventoryType;
/*     */ import org.bukkit.inventory.InventoryHolder;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InventoryCrafting
/*     */   implements IInventory, AutoRecipeOutput
/*     */ {
/*     */   private final NonNullList<ItemStack> items;
/*     */   private final int b;
/*     */   private final int c;
/*     */   public final Container container;
/*  21 */   public List<HumanEntity> transaction = new ArrayList<>();
/*     */   private IRecipe currentRecipe;
/*     */   public IInventory resultInventory;
/*     */   private EntityHuman owner;
/*  25 */   private int maxStack = 64;
/*     */   
/*     */   public List<ItemStack> getContents() {
/*  28 */     return this.items;
/*     */   }
/*     */   
/*     */   public void onOpen(CraftHumanEntity who) {
/*  32 */     this.transaction.add(who);
/*     */   }
/*     */   
/*     */   public InventoryType getInvType() {
/*  36 */     return (this.items.size() == 4) ? InventoryType.CRAFTING : InventoryType.WORKBENCH;
/*     */   }
/*     */   
/*     */   public void onClose(CraftHumanEntity who) {
/*  40 */     this.transaction.remove(who);
/*     */   }
/*     */   
/*     */   public List<HumanEntity> getViewers() {
/*  44 */     return this.transaction;
/*     */   }
/*     */   
/*     */   public InventoryHolder getOwner() {
/*  48 */     return (this.owner == null) ? null : (InventoryHolder)this.owner.getBukkitEntity();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxStackSize() {
/*  53 */     return this.maxStack;
/*     */   }
/*     */   
/*     */   public void setMaxStackSize(int size) {
/*  57 */     this.maxStack = size;
/*  58 */     this.resultInventory.setMaxStackSize(size);
/*     */   }
/*     */ 
/*     */   
/*     */   public Location getLocation() {
/*  63 */     return (this.container instanceof ContainerWorkbench) ? ((ContainerWorkbench)this.container).containerAccess.getLocation() : this.owner.getBukkitEntity().getLocation();
/*     */   }
/*     */ 
/*     */   
/*     */   public IRecipe getCurrentRecipe() {
/*  68 */     return this.currentRecipe;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrentRecipe(IRecipe currentRecipe) {
/*  73 */     this.currentRecipe = currentRecipe;
/*     */   }
/*     */   
/*     */   public InventoryCrafting(Container container, int i, int j, EntityHuman player) {
/*  77 */     this(container, i, j);
/*  78 */     this.owner = player;
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryCrafting(Container container, int i, int j) {
/*  83 */     this.items = NonNullList.a(i * j, ItemStack.b);
/*  84 */     this.container = container;
/*  85 */     this.b = i;
/*  86 */     this.c = j;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSize() {
/*  91 */     return this.items.size();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*     */     ItemStack itemstack;
/*  96 */     Iterator<ItemStack> iterator = this.items.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 101 */       if (!iterator.hasNext()) {
/* 102 */         return true;
/*     */       }
/*     */       
/* 105 */       itemstack = iterator.next();
/* 106 */     } while (itemstack.isEmpty());
/*     */     
/* 108 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getItem(int i) {
/* 113 */     return (i >= getSize()) ? ItemStack.b : this.items.get(i);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack splitWithoutUpdate(int i) {
/* 118 */     return ContainerUtil.a(this.items, i);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack splitStack(int i, int j) {
/* 123 */     ItemStack itemstack = ContainerUtil.a(this.items, i, j);
/*     */     
/* 125 */     if (!itemstack.isEmpty()) {
/* 126 */       this.container.a(this);
/*     */     }
/*     */     
/* 129 */     return itemstack;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItem(int i, ItemStack itemstack) {
/* 134 */     this.items.set(i, itemstack);
/* 135 */     this.container.a(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {}
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman entityhuman) {
/* 143 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 148 */     this.items.clear();
/*     */   }
/*     */   
/*     */   public int f() {
/* 152 */     return this.c;
/*     */   }
/*     */   
/*     */   public int g() {
/* 156 */     return this.b;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(AutoRecipeStackManager autorecipestackmanager) {
/* 161 */     Iterator<ItemStack> iterator = this.items.iterator();
/*     */     
/* 163 */     while (iterator.hasNext()) {
/* 164 */       ItemStack itemstack = iterator.next();
/*     */       
/* 166 */       autorecipestackmanager.a(itemstack);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\InventoryCrafting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */