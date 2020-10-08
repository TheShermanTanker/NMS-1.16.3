/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftHumanEntity;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.inventory.InventoryHolder;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InventoryMerchant
/*     */   implements IInventory
/*     */ {
/*     */   private final IMerchant merchant;
/*     */   private final NonNullList<ItemStack> itemsInSlots;
/*     */   @Nullable
/*     */   private MerchantRecipe recipe;
/*     */   public int selectedIndex;
/*     */   private int e;
/*  23 */   public List<HumanEntity> transaction = new ArrayList<>();
/*  24 */   private int maxStack = 64;
/*     */   
/*     */   public List<ItemStack> getContents() {
/*  27 */     return this.itemsInSlots;
/*     */   }
/*     */   
/*     */   public void onOpen(CraftHumanEntity who) {
/*  31 */     this.transaction.add(who);
/*     */   }
/*     */   
/*     */   public void onClose(CraftHumanEntity who) {
/*  35 */     this.transaction.remove(who);
/*  36 */     this.merchant.setTradingPlayer((EntityHuman)null);
/*     */   }
/*     */   
/*     */   public List<HumanEntity> getViewers() {
/*  40 */     return this.transaction;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxStackSize() {
/*  45 */     return this.maxStack;
/*     */   }
/*     */   
/*     */   public void setMaxStackSize(int i) {
/*  49 */     this.maxStack = i;
/*     */   }
/*     */   
/*     */   public InventoryHolder getOwner() {
/*  53 */     return (this.merchant instanceof EntityVillagerAbstract) ? (InventoryHolder)((EntityVillagerAbstract)this.merchant).getBukkitEntity() : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Location getLocation() {
/*  58 */     return (this.merchant instanceof EntityVillager) ? ((EntityVillager)this.merchant).getBukkitEntity().getLocation() : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryMerchant(IMerchant imerchant) {
/*  63 */     this.itemsInSlots = NonNullList.a(3, ItemStack.b);
/*  64 */     this.merchant = imerchant;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSize() {
/*  69 */     return this.itemsInSlots.size();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*     */     ItemStack itemstack;
/*  74 */     Iterator<ItemStack> iterator = this.itemsInSlots.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/*  79 */       if (!iterator.hasNext()) {
/*  80 */         return true;
/*     */       }
/*     */       
/*  83 */       itemstack = iterator.next();
/*  84 */     } while (itemstack.isEmpty());
/*     */     
/*  86 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getItem(int i) {
/*  91 */     return this.itemsInSlots.get(i);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack splitStack(int i, int j) {
/*  96 */     ItemStack itemstack = this.itemsInSlots.get(i);
/*     */     
/*  98 */     if (i == 2 && !itemstack.isEmpty()) {
/*  99 */       return ContainerUtil.a(this.itemsInSlots, i, itemstack.getCount());
/*     */     }
/* 101 */     ItemStack itemstack1 = ContainerUtil.a(this.itemsInSlots, i, j);
/*     */     
/* 103 */     if (!itemstack1.isEmpty() && d(i)) {
/* 104 */       f();
/*     */     }
/*     */     
/* 107 */     return itemstack1;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean d(int i) {
/* 112 */     return (i == 0 || i == 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack splitWithoutUpdate(int i) {
/* 117 */     return ContainerUtil.a(this.itemsInSlots, i);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItem(int i, ItemStack itemstack) {
/* 122 */     this.itemsInSlots.set(i, itemstack);
/* 123 */     if (!itemstack.isEmpty() && itemstack.getCount() > getMaxStackSize()) {
/* 124 */       itemstack.setCount(getMaxStackSize());
/*     */     }
/*     */     
/* 127 */     if (d(i)) {
/* 128 */       f();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman entityhuman) {
/* 135 */     return (this.merchant.getTrader() == entityhuman);
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/* 140 */     f();
/*     */   }
/*     */   public void f() {
/*     */     ItemStack itemstack, itemstack1;
/* 144 */     this.recipe = null;
/*     */ 
/*     */ 
/*     */     
/* 148 */     if (((ItemStack)this.itemsInSlots.get(0)).isEmpty()) {
/* 149 */       itemstack = this.itemsInSlots.get(1);
/* 150 */       itemstack1 = ItemStack.b;
/*     */     } else {
/* 152 */       itemstack = this.itemsInSlots.get(0);
/* 153 */       itemstack1 = this.itemsInSlots.get(1);
/*     */     } 
/*     */     
/* 156 */     if (itemstack.isEmpty()) {
/* 157 */       setItem(2, ItemStack.b);
/* 158 */       this.e = 0;
/*     */     } else {
/* 160 */       MerchantRecipeList merchantrecipelist = this.merchant.getOffers();
/*     */       
/* 162 */       if (!merchantrecipelist.isEmpty()) {
/* 163 */         MerchantRecipe merchantrecipe = merchantrecipelist.a(itemstack, itemstack1, this.selectedIndex);
/*     */         
/* 165 */         if (merchantrecipe == null || merchantrecipe.isFullyUsed()) {
/* 166 */           this.recipe = merchantrecipe;
/* 167 */           merchantrecipe = merchantrecipelist.a(itemstack1, itemstack, this.selectedIndex);
/*     */         } 
/*     */         
/* 170 */         if (merchantrecipe != null && !merchantrecipe.isFullyUsed()) {
/* 171 */           this.recipe = merchantrecipe;
/* 172 */           setItem(2, merchantrecipe.f());
/* 173 */           this.e = merchantrecipe.getXp();
/*     */         } else {
/* 175 */           setItem(2, ItemStack.b);
/* 176 */           this.e = 0;
/*     */         } 
/*     */       } 
/*     */       
/* 180 */       this.merchant.k(getItem(2));
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public MerchantRecipe getRecipe() {
/* 186 */     return this.recipe;
/*     */   }
/*     */   
/*     */   public void c(int i) {
/* 190 */     this.selectedIndex = i;
/* 191 */     f();
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 196 */     this.itemsInSlots.clear();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\InventoryMerchant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */