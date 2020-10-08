/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryLoom;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryView;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ 
/*     */ public class ContainerLoom extends Container {
/*  13 */   private CraftInventoryView bukkitEntity = null;
/*     */   private Player player;
/*     */   private final ContainerAccess containerAccess;
/*     */   
/*     */   public CraftInventoryView getBukkitView() {
/*  18 */     if (this.bukkitEntity != null) {
/*  19 */       return this.bukkitEntity;
/*     */     }
/*     */     
/*  22 */     CraftInventoryLoom inventory = new CraftInventoryLoom(this.craftInventory, this.resultInventory);
/*  23 */     this.bukkitEntity = new CraftInventoryView((HumanEntity)this.player, (Inventory)inventory, this);
/*  24 */     return this.bukkitEntity;
/*     */   }
/*     */ 
/*     */   
/*     */   private final ContainerProperty d;
/*     */   private Runnable e;
/*     */   private final Slot f;
/*     */   private final Slot g;
/*     */   private final Slot h;
/*     */   private final Slot i;
/*     */   private long j;
/*     */   private final IInventory craftInventory;
/*     */   private final IInventory resultInventory;
/*     */   
/*     */   public ContainerLoom(int i, PlayerInventory playerinventory) {
/*  39 */     this(i, playerinventory, ContainerAccess.a);
/*     */   }
/*     */   
/*     */   public ContainerLoom(int i, PlayerInventory playerinventory, final ContainerAccess containeraccess) {
/*  43 */     super(Containers.LOOM, i);
/*  44 */     this.d = ContainerProperty.a();
/*  45 */     this.e = (() -> {
/*     */       
/*  47 */       }); this.craftInventory = new InventorySubcontainer(3)
/*     */       {
/*     */         public void update() {
/*  50 */           super.update();
/*  51 */           ContainerLoom.this.a(this);
/*  52 */           ContainerLoom.this.e.run();
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public Location getLocation() {
/*  58 */           return containeraccess.getLocation();
/*     */         }
/*     */       };
/*     */     
/*  62 */     this.resultInventory = new InventorySubcontainer(1)
/*     */       {
/*     */         public void update() {
/*  65 */           super.update();
/*  66 */           ContainerLoom.this.e.run();
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public Location getLocation() {
/*  72 */           return containeraccess.getLocation();
/*     */         }
/*     */       };
/*     */     
/*  76 */     this.containerAccess = containeraccess;
/*  77 */     this.f = a(new Slot(this.craftInventory, 0, 13, 26)
/*     */         {
/*     */           public boolean isAllowed(ItemStack itemstack) {
/*  80 */             return itemstack.getItem() instanceof ItemBanner;
/*     */           }
/*     */         });
/*  83 */     this.g = a(new Slot(this.craftInventory, 1, 33, 26)
/*     */         {
/*     */           public boolean isAllowed(ItemStack itemstack) {
/*  86 */             return itemstack.getItem() instanceof ItemDye;
/*     */           }
/*     */         });
/*  89 */     this.h = a(new Slot(this.craftInventory, 2, 23, 45)
/*     */         {
/*     */           public boolean isAllowed(ItemStack itemstack) {
/*  92 */             return itemstack.getItem() instanceof ItemBannerPattern;
/*     */           }
/*     */         });
/*  95 */     this.i = a(new Slot(this.resultInventory, 0, 143, 58)
/*     */         {
/*     */           public boolean isAllowed(ItemStack itemstack) {
/*  98 */             return false;
/*     */           }
/*     */ 
/*     */           
/*     */           public ItemStack a(EntityHuman entityhuman, ItemStack itemstack) {
/* 103 */             ContainerLoom.this.f.a(1);
/* 104 */             ContainerLoom.this.g.a(1);
/* 105 */             if (!ContainerLoom.this.f.hasItem() || !ContainerLoom.this.g.hasItem()) {
/* 106 */               ContainerLoom.this.d.set(0);
/*     */             }
/*     */             
/* 109 */             containeraccess.a((world, blockposition) -> {
/*     */                   long j = world.getTime();
/*     */                   
/*     */                   if (ContainerLoom.this.j != j) {
/*     */                     world.playSound((EntityHuman)null, blockposition, SoundEffects.UI_LOOM_TAKE_RESULT, SoundCategory.BLOCKS, 1.0F, 1.0F);
/*     */                     
/*     */                     ContainerLoom.this.j = j;
/*     */                   } 
/*     */                 });
/* 118 */             return super.a(entityhuman, itemstack);
/*     */           }
/*     */         });
/*     */     
/*     */     int j;
/*     */     
/* 124 */     for (j = 0; j < 3; j++) {
/* 125 */       for (int k = 0; k < 9; k++) {
/* 126 */         a(new Slot(playerinventory, k + j * 9 + 9, 8 + k * 18, 84 + j * 18));
/*     */       }
/*     */     } 
/*     */     
/* 130 */     for (j = 0; j < 9; j++) {
/* 131 */       a(new Slot(playerinventory, j, 8 + j * 18, 142));
/*     */     }
/*     */     
/* 134 */     a(this.d);
/* 135 */     this.player = (Player)playerinventory.player.getBukkitEntity();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canUse(EntityHuman entityhuman) {
/* 140 */     if (!this.checkReachable) return true; 
/* 141 */     return a(this.containerAccess, entityhuman, Blocks.LOOM);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman entityhuman, int i) {
/* 146 */     if (i > 0 && i <= EnumBannerPatternType.R) {
/* 147 */       this.d.set(i);
/* 148 */       j();
/* 149 */       return true;
/*     */     } 
/* 151 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(IInventory iinventory) {
/* 157 */     ItemStack itemstack = this.f.getItem();
/* 158 */     ItemStack itemstack1 = this.g.getItem();
/* 159 */     ItemStack itemstack2 = this.h.getItem();
/* 160 */     ItemStack itemstack3 = this.i.getItem();
/*     */     
/* 162 */     if (!itemstack3.isEmpty() && (itemstack.isEmpty() || itemstack1.isEmpty() || this.d.get() <= 0 || (this.d.get() >= EnumBannerPatternType.P - EnumBannerPatternType.Q && itemstack2.isEmpty()))) {
/* 163 */       this.i.set(ItemStack.b);
/* 164 */       this.d.set(0);
/* 165 */     } else if (!itemstack2.isEmpty() && itemstack2.getItem() instanceof ItemBannerPattern) {
/* 166 */       NBTTagCompound nbttagcompound = itemstack.a("BlockEntityTag");
/* 167 */       boolean flag = (nbttagcompound.hasKeyOfType("Patterns", 9) && !itemstack.isEmpty() && nbttagcompound.getList("Patterns", 10).size() >= 6);
/*     */       
/* 169 */       if (flag) {
/* 170 */         this.d.set(0);
/*     */       } else {
/* 172 */         this.d.set(((ItemBannerPattern)itemstack2.getItem()).b().ordinal());
/*     */       } 
/*     */     } 
/*     */     
/* 176 */     j();
/*     */     
/* 178 */     CraftEventFactory.callPrepareResultEvent(this, 3);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack shiftClick(EntityHuman entityhuman, int i) {
/* 183 */     ItemStack itemstack = ItemStack.b;
/* 184 */     Slot slot = this.slots.get(i);
/*     */     
/* 186 */     if (slot != null && slot.hasItem()) {
/* 187 */       ItemStack itemstack1 = slot.getItem();
/*     */       
/* 189 */       itemstack = itemstack1.cloneItemStack();
/* 190 */       if (i == this.i.rawSlotIndex) {
/* 191 */         if (!a(itemstack1, 4, 40, true)) {
/* 192 */           return ItemStack.b;
/*     */         }
/*     */         
/* 195 */         slot.a(itemstack1, itemstack);
/* 196 */       } else if (i != this.g.rawSlotIndex && i != this.f.rawSlotIndex && i != this.h.rawSlotIndex) {
/* 197 */         if (itemstack1.getItem() instanceof ItemBanner) {
/* 198 */           if (!a(itemstack1, this.f.rawSlotIndex, this.f.rawSlotIndex + 1, false)) {
/* 199 */             return ItemStack.b;
/*     */           }
/* 201 */         } else if (itemstack1.getItem() instanceof ItemDye) {
/* 202 */           if (!a(itemstack1, this.g.rawSlotIndex, this.g.rawSlotIndex + 1, false)) {
/* 203 */             return ItemStack.b;
/*     */           }
/* 205 */         } else if (itemstack1.getItem() instanceof ItemBannerPattern) {
/* 206 */           if (!a(itemstack1, this.h.rawSlotIndex, this.h.rawSlotIndex + 1, false)) {
/* 207 */             return ItemStack.b;
/*     */           }
/* 209 */         } else if (i >= 4 && i < 31) {
/* 210 */           if (!a(itemstack1, 31, 40, false)) {
/* 211 */             return ItemStack.b;
/*     */           }
/* 213 */         } else if (i >= 31 && i < 40 && !a(itemstack1, 4, 31, false)) {
/* 214 */           return ItemStack.b;
/*     */         } 
/* 216 */       } else if (!a(itemstack1, 4, 40, false)) {
/* 217 */         return ItemStack.b;
/*     */       } 
/*     */       
/* 220 */       if (itemstack1.isEmpty()) {
/* 221 */         slot.set(ItemStack.b);
/*     */       } else {
/* 223 */         slot.d();
/*     */       } 
/*     */       
/* 226 */       if (itemstack1.getCount() == itemstack.getCount()) {
/* 227 */         return ItemStack.b;
/*     */       }
/*     */       
/* 230 */       slot.a(entityhuman, itemstack1);
/*     */     } 
/*     */     
/* 233 */     return itemstack;
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(EntityHuman entityhuman) {
/* 238 */     super.b(entityhuman);
/* 239 */     this.containerAccess.a((world, blockposition) -> a(entityhuman, entityhuman.world, this.craftInventory));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void j() {
/* 245 */     if (this.d.get() > 0) {
/* 246 */       ItemStack itemstack = this.f.getItem();
/* 247 */       ItemStack itemstack1 = this.g.getItem();
/* 248 */       ItemStack itemstack2 = ItemStack.b;
/*     */       
/* 250 */       if (!itemstack.isEmpty() && !itemstack1.isEmpty()) {
/* 251 */         NBTTagList nbttaglist; itemstack2 = itemstack.cloneItemStack();
/* 252 */         itemstack2.setCount(1);
/* 253 */         EnumBannerPatternType enumbannerpatterntype = EnumBannerPatternType.values()[this.d.get()];
/* 254 */         EnumColor enumcolor = ((ItemDye)itemstack1.getItem()).d();
/* 255 */         NBTTagCompound nbttagcompound = itemstack2.a("BlockEntityTag");
/*     */ 
/*     */         
/* 258 */         if (nbttagcompound.hasKeyOfType("Patterns", 9)) {
/* 259 */           nbttaglist = nbttagcompound.getList("Patterns", 10);
/*     */           
/* 261 */           while (nbttaglist.size() > 20) {
/* 262 */             nbttaglist.remove(20);
/*     */           }
/*     */         } else {
/*     */           
/* 266 */           nbttaglist = new NBTTagList();
/* 267 */           nbttagcompound.set("Patterns", nbttaglist);
/*     */         } 
/*     */         
/* 270 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*     */         
/* 272 */         nbttagcompound1.setString("Pattern", enumbannerpatterntype.b());
/* 273 */         nbttagcompound1.setInt("Color", enumcolor.getColorIndex());
/* 274 */         nbttaglist.add(nbttagcompound1);
/*     */       } 
/*     */       
/* 277 */       if (!ItemStack.matches(itemstack2, this.i.getItem()))
/* 278 */         this.i.set(itemstack2); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ContainerLoom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */