/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftMerchantRecipe;
/*     */ 
/*     */ 
/*     */ public class MerchantRecipe
/*     */ {
/*     */   public ItemStack buyingItem1;
/*     */   public ItemStack buyingItem2;
/*     */   public final ItemStack sellingItem;
/*     */   public int uses;
/*     */   public int maxUses;
/*     */   public boolean rewardExp;
/*     */   private int specialPrice;
/*     */   private int demand;
/*     */   public float priceMultiplier;
/*     */   public int xp;
/*     */   private CraftMerchantRecipe bukkitHandle;
/*     */   
/*     */   public CraftMerchantRecipe asBukkit() {
/*  21 */     return (this.bukkitHandle == null) ? (this.bukkitHandle = new CraftMerchantRecipe(this)) : this.bukkitHandle;
/*     */   }
/*     */   
/*     */   public MerchantRecipe(ItemStack itemstack, ItemStack itemstack1, ItemStack itemstack2, int uses, int maxUses, int experience, float priceMultiplier, CraftMerchantRecipe bukkit) {
/*  25 */     this(itemstack, itemstack1, itemstack2, uses, maxUses, experience, priceMultiplier);
/*  26 */     this.bukkitHandle = bukkit;
/*     */   }
/*     */ 
/*     */   
/*     */   public MerchantRecipe(NBTTagCompound nbttagcompound) {
/*  31 */     this.rewardExp = true;
/*  32 */     this.xp = 1;
/*  33 */     this.buyingItem1 = ItemStack.a(nbttagcompound.getCompound("buy"));
/*  34 */     this.buyingItem2 = ItemStack.a(nbttagcompound.getCompound("buyB"));
/*  35 */     this.sellingItem = ItemStack.a(nbttagcompound.getCompound("sell"));
/*  36 */     this.uses = nbttagcompound.getInt("uses");
/*  37 */     if (nbttagcompound.hasKeyOfType("maxUses", 99)) {
/*  38 */       this.maxUses = nbttagcompound.getInt("maxUses");
/*     */     } else {
/*  40 */       this.maxUses = 4;
/*     */     } 
/*     */     
/*  43 */     if (nbttagcompound.hasKeyOfType("rewardExp", 1)) {
/*  44 */       this.rewardExp = nbttagcompound.getBoolean("rewardExp");
/*     */     }
/*     */     
/*  47 */     if (nbttagcompound.hasKeyOfType("xp", 3)) {
/*  48 */       this.xp = nbttagcompound.getInt("xp");
/*     */     }
/*     */     
/*  51 */     if (nbttagcompound.hasKeyOfType("priceMultiplier", 5)) {
/*  52 */       this.priceMultiplier = nbttagcompound.getFloat("priceMultiplier");
/*     */     }
/*     */     
/*  55 */     this.specialPrice = nbttagcompound.getInt("specialPrice");
/*  56 */     this.demand = nbttagcompound.getInt("demand");
/*     */   }
/*     */   
/*     */   public MerchantRecipe(ItemStack itemstack, ItemStack itemstack1, int i, int j, float f) {
/*  60 */     this(itemstack, ItemStack.b, itemstack1, i, j, f);
/*     */   }
/*     */   
/*     */   public MerchantRecipe(ItemStack itemstack, ItemStack itemstack1, ItemStack itemstack2, int i, int j, float f) {
/*  64 */     this(itemstack, itemstack1, itemstack2, 0, i, j, f);
/*     */   }
/*     */   
/*     */   public MerchantRecipe(ItemStack itemstack, ItemStack itemstack1, ItemStack itemstack2, int i, int j, int k, float f) {
/*  68 */     this(itemstack, itemstack1, itemstack2, i, j, k, f, 0);
/*     */   }
/*     */   
/*     */   public MerchantRecipe(ItemStack itemstack, ItemStack itemstack1, ItemStack itemstack2, int i, int j, int k, float f, int l) {
/*  72 */     this.rewardExp = true;
/*  73 */     this.xp = 1;
/*  74 */     this.buyingItem1 = itemstack;
/*  75 */     this.buyingItem2 = itemstack1;
/*  76 */     this.sellingItem = itemstack2;
/*  77 */     this.uses = i;
/*  78 */     this.maxUses = j;
/*  79 */     this.xp = k;
/*  80 */     this.priceMultiplier = f;
/*  81 */     this.demand = l;
/*     */   }
/*     */   
/*     */   public ItemStack a() {
/*  85 */     return this.buyingItem1;
/*     */   }
/*     */   
/*     */   public ItemStack getBuyItem1() {
/*  89 */     int i = this.buyingItem1.getCount();
/*  90 */     if (i <= 0) return ItemStack.b; 
/*  91 */     ItemStack itemstack = this.buyingItem1.cloneItemStack();
/*  92 */     int j = Math.max(0, MathHelper.d((i * this.demand) * this.priceMultiplier));
/*     */     
/*  94 */     itemstack.setCount(MathHelper.clamp(i + j + this.specialPrice, 1, this.buyingItem1.getItem().getMaxStackSize()));
/*  95 */     return itemstack;
/*     */   }
/*     */   
/*     */   public ItemStack getBuyItem2() {
/*  99 */     return this.buyingItem2;
/*     */   }
/*     */   
/*     */   public ItemStack getSellingItem() {
/* 103 */     return this.sellingItem;
/*     */   }
/*     */   
/*     */   public void e() {
/* 107 */     this.demand = Math.max(0, this.demand + this.uses - this.maxUses - this.uses);
/*     */   }
/*     */   
/*     */   public ItemStack f() {
/* 111 */     return this.sellingItem.cloneItemStack();
/*     */   }
/*     */   
/*     */   public int getUses() {
/* 115 */     return this.uses;
/*     */   }
/*     */   
/*     */   public void resetUses() {
/* 119 */     this.uses = 0;
/*     */   }
/*     */   
/*     */   public int getMaxUses() {
/* 123 */     return this.maxUses;
/*     */   }
/*     */   
/*     */   public void increaseUses() {
/* 127 */     this.uses++;
/*     */   }
/*     */   
/*     */   public int getDemand() {
/* 131 */     return this.demand;
/*     */   }
/*     */   
/*     */   public void increaseSpecialPrice(int i) {
/* 135 */     this.specialPrice += i;
/*     */   }
/*     */   
/*     */   public void setSpecialPrice() {
/* 139 */     this.specialPrice = 0;
/*     */   }
/*     */   
/*     */   public int getSpecialPrice() {
/* 143 */     return this.specialPrice;
/*     */   }
/*     */   
/*     */   public void setSpecialPrice(int i) {
/* 147 */     this.specialPrice = i;
/*     */   }
/*     */   
/*     */   public float getPriceMultiplier() {
/* 151 */     return this.priceMultiplier;
/*     */   }
/*     */   
/*     */   public int getXp() {
/* 155 */     return this.xp;
/*     */   }
/*     */   
/*     */   public boolean isFullyUsed() {
/* 159 */     return (this.uses >= this.maxUses);
/*     */   }
/*     */   
/*     */   public void q() {
/* 163 */     this.uses = this.maxUses;
/*     */   }
/*     */   
/*     */   public boolean r() {
/* 167 */     return (this.uses > 0);
/*     */   }
/*     */   
/*     */   public boolean isRewardExp() {
/* 171 */     return this.rewardExp;
/*     */   }
/*     */   
/*     */   public NBTTagCompound t() {
/* 175 */     NBTTagCompound nbttagcompound = new NBTTagCompound();
/*     */     
/* 177 */     nbttagcompound.set("buy", this.buyingItem1.save(new NBTTagCompound()));
/* 178 */     nbttagcompound.set("sell", this.sellingItem.save(new NBTTagCompound()));
/* 179 */     nbttagcompound.set("buyB", this.buyingItem2.save(new NBTTagCompound()));
/* 180 */     nbttagcompound.setInt("uses", this.uses);
/* 181 */     nbttagcompound.setInt("maxUses", this.maxUses);
/* 182 */     nbttagcompound.setBoolean("rewardExp", this.rewardExp);
/* 183 */     nbttagcompound.setInt("xp", this.xp);
/* 184 */     nbttagcompound.setFloat("priceMultiplier", this.priceMultiplier);
/* 185 */     nbttagcompound.setInt("specialPrice", this.specialPrice);
/* 186 */     nbttagcompound.setInt("demand", this.demand);
/* 187 */     return nbttagcompound;
/*     */   }
/*     */   
/*     */   public boolean a(ItemStack itemstack, ItemStack itemstack1) {
/* 191 */     return (c(itemstack, getBuyItem1()) && itemstack.getCount() >= getBuyItem1().getCount() && c(itemstack1, this.buyingItem2) && itemstack1.getCount() >= this.buyingItem2.getCount());
/*     */   }
/*     */   
/*     */   private boolean c(ItemStack itemstack, ItemStack itemstack1) {
/* 195 */     if (itemstack1.isEmpty() && itemstack.isEmpty()) {
/* 196 */       return true;
/*     */     }
/* 198 */     ItemStack itemstack2 = itemstack.cloneItemStack();
/*     */     
/* 200 */     if (itemstack2.getItem().usesDurability()) {
/* 201 */       itemstack2.setDamage(itemstack2.getDamage());
/*     */     }
/*     */     
/* 204 */     return (ItemStack.c(itemstack2, itemstack1) && (!itemstack1.hasTag() || (itemstack2.hasTag() && GameProfileSerializer.a(itemstack1.getTag(), itemstack2.getTag(), false))));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(ItemStack itemstack, ItemStack itemstack1) {
/* 209 */     if (!a(itemstack, itemstack1)) {
/* 210 */       return false;
/*     */     }
/* 212 */     itemstack.subtract(getBuyItem1().getCount());
/* 213 */     if (!getBuyItem2().isEmpty()) {
/* 214 */       itemstack1.subtract(getBuyItem2().getCount());
/*     */     }
/*     */     
/* 217 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MerchantRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */