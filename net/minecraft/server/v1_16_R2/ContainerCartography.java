/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryCartography;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryView;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ 
/*     */ public class ContainerCartography extends Container {
/*  13 */   private CraftInventoryView bukkitEntity = null;
/*     */   private Player player;
/*     */   private final ContainerAccess containerAccess;
/*     */   
/*     */   public CraftInventoryView getBukkitView() {
/*  18 */     if (this.bukkitEntity != null) {
/*  19 */       return this.bukkitEntity;
/*     */     }
/*     */     
/*  22 */     CraftInventoryCartography inventory = new CraftInventoryCartography(this.inventory, this.resultInventory);
/*  23 */     this.bukkitEntity = new CraftInventoryView((HumanEntity)this.player, (Inventory)inventory, this);
/*  24 */     return this.bukkitEntity;
/*     */   }
/*     */ 
/*     */   
/*     */   private long e;
/*     */   public final IInventory inventory;
/*     */   private final InventoryCraftResult resultInventory;
/*     */   
/*     */   public ContainerCartography(int i, PlayerInventory playerinventory) {
/*  33 */     this(i, playerinventory, ContainerAccess.a);
/*     */   }
/*     */   
/*     */   public ContainerCartography(int i, PlayerInventory playerinventory, final ContainerAccess containeraccess) {
/*  37 */     super(Containers.CARTOGRAPHY_TABLE, i);
/*  38 */     this.inventory = new InventorySubcontainer(2)
/*     */       {
/*     */         public void update() {
/*  41 */           ContainerCartography.this.a(this);
/*  42 */           super.update();
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public Location getLocation() {
/*  48 */           return containeraccess.getLocation();
/*     */         }
/*     */       };
/*     */     
/*  52 */     this.resultInventory = new InventoryCraftResult()
/*     */       {
/*     */         public void update() {
/*  55 */           ContainerCartography.this.a(this);
/*  56 */           super.update();
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public Location getLocation() {
/*  62 */           return containeraccess.getLocation();
/*     */         }
/*     */       };
/*     */     
/*  66 */     this.containerAccess = containeraccess;
/*  67 */     a(new Slot(this.inventory, 0, 15, 15)
/*     */         {
/*     */           public boolean isAllowed(ItemStack itemstack) {
/*  70 */             return (itemstack.getItem() == Items.FILLED_MAP);
/*     */           }
/*     */         });
/*  73 */     a(new Slot(this.inventory, 1, 15, 52)
/*     */         {
/*     */           public boolean isAllowed(ItemStack itemstack) {
/*  76 */             Item item = itemstack.getItem();
/*     */             
/*  78 */             return (item == Items.PAPER || item == Items.MAP || item == Items.dP);
/*     */           }
/*     */         });
/*  81 */     a(new Slot(this.resultInventory, 2, 145, 39)
/*     */         {
/*     */           public boolean isAllowed(ItemStack itemstack) {
/*  84 */             return false;
/*     */           }
/*     */ 
/*     */           
/*     */           public ItemStack a(EntityHuman entityhuman, ItemStack itemstack) {
/*  89 */             ((Slot)ContainerCartography.this.slots.get(0)).a(1);
/*  90 */             ((Slot)ContainerCartography.this.slots.get(1)).a(1);
/*  91 */             itemstack.getItem().b(itemstack, entityhuman.world, entityhuman);
/*  92 */             containeraccess.a((world, blockposition) -> {
/*     */                   long j = world.getTime();
/*     */                   
/*     */                   if (ContainerCartography.this.e != j) {
/*     */                     world.playSound((EntityHuman)null, blockposition, SoundEffects.UI_CARTOGRAPHY_TABLE_TAKE_RESULT, SoundCategory.BLOCKS, 1.0F, 1.0F);
/*     */                     
/*     */                     ContainerCartography.this.e = j;
/*     */                   } 
/*     */                 });
/* 101 */             return super.a(entityhuman, itemstack);
/*     */           }
/*     */         });
/*     */     
/*     */     int j;
/*     */     
/* 107 */     for (j = 0; j < 3; j++) {
/* 108 */       for (int k = 0; k < 9; k++) {
/* 109 */         a(new Slot(playerinventory, k + j * 9 + 9, 8 + k * 18, 84 + j * 18));
/*     */       }
/*     */     } 
/*     */     
/* 113 */     for (j = 0; j < 9; j++) {
/* 114 */       a(new Slot(playerinventory, j, 8 + j * 18, 142));
/*     */     }
/*     */     
/* 117 */     this.player = (Player)playerinventory.player.getBukkitEntity();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canUse(EntityHuman entityhuman) {
/* 122 */     if (!this.checkReachable) return true; 
/* 123 */     return a(this.containerAccess, entityhuman, Blocks.CARTOGRAPHY_TABLE);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IInventory iinventory) {
/* 128 */     ItemStack itemstack = this.inventory.getItem(0);
/* 129 */     ItemStack itemstack1 = this.inventory.getItem(1);
/* 130 */     ItemStack itemstack2 = this.resultInventory.getItem(2);
/*     */     
/* 132 */     if (!itemstack2.isEmpty() && (itemstack.isEmpty() || itemstack1.isEmpty())) {
/* 133 */       this.resultInventory.splitWithoutUpdate(2);
/* 134 */     } else if (!itemstack.isEmpty() && !itemstack1.isEmpty()) {
/* 135 */       a(itemstack, itemstack1, itemstack2);
/*     */     } 
/*     */     
/* 138 */     CraftEventFactory.callPrepareResultEvent(this, 2);
/*     */   }
/*     */   
/*     */   private void a(ItemStack itemstack, ItemStack itemstack1, ItemStack itemstack2) {
/* 142 */     this.containerAccess.a((world, blockposition) -> {
/*     */           Item item = itemstack1.getItem();
/*     */           WorldMap worldmap = ItemWorldMap.a(itemstack, world);
/*     */           if (worldmap != null) {
/*     */             ItemStack itemstack3;
/*     */             if (item == Items.PAPER && !worldmap.locked && worldmap.scale < 4) {
/*     */               itemstack3 = itemstack.cloneItemStack();
/*     */               itemstack3.setCount(1);
/*     */               itemstack3.getOrCreateTag().setInt("map_scale_direction", 1);
/*     */               c();
/*     */             } else if (item == Items.dP && !worldmap.locked) {
/*     */               itemstack3 = itemstack.cloneItemStack();
/*     */               itemstack3.setCount(1);
/*     */               itemstack3.getOrCreateTag().setBoolean("map_to_lock", true);
/*     */               c();
/*     */             } else {
/*     */               if (item != Items.MAP) {
/*     */                 this.resultInventory.splitWithoutUpdate(2);
/*     */                 c();
/*     */                 return;
/*     */               } 
/*     */               itemstack3 = itemstack.cloneItemStack();
/*     */               itemstack3.setCount(2);
/*     */               c();
/*     */             } 
/*     */             if (!ItemStack.matches(itemstack3, itemstack2)) {
/*     */               this.resultInventory.setItem(2, itemstack3);
/*     */               c();
/*     */             } 
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(ItemStack itemstack, Slot slot) {
/* 182 */     return (slot.inventory != this.resultInventory && super.a(itemstack, slot));
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack shiftClick(EntityHuman entityhuman, int i) {
/* 187 */     ItemStack itemstack = ItemStack.b;
/* 188 */     Slot slot = this.slots.get(i);
/*     */     
/* 190 */     if (slot != null && slot.hasItem()) {
/* 191 */       ItemStack itemstack1 = slot.getItem();
/* 192 */       Item item = itemstack1.getItem();
/*     */       
/* 194 */       itemstack = itemstack1.cloneItemStack();
/* 195 */       if (i == 2) {
/* 196 */         item.b(itemstack1, entityhuman.world, entityhuman);
/* 197 */         if (!a(itemstack1, 3, 39, true)) {
/* 198 */           return ItemStack.b;
/*     */         }
/*     */         
/* 201 */         slot.a(itemstack1, itemstack);
/* 202 */       } else if (i != 1 && i != 0) {
/* 203 */         if (item == Items.FILLED_MAP) {
/* 204 */           if (!a(itemstack1, 0, 1, false)) {
/* 205 */             return ItemStack.b;
/*     */           }
/* 207 */         } else if (item != Items.PAPER && item != Items.MAP && item != Items.dP) {
/* 208 */           if (i >= 3 && i < 30) {
/* 209 */             if (!a(itemstack1, 30, 39, false)) {
/* 210 */               return ItemStack.b;
/*     */             }
/* 212 */           } else if (i >= 30 && i < 39 && !a(itemstack1, 3, 30, false)) {
/* 213 */             return ItemStack.b;
/*     */           } 
/* 215 */         } else if (!a(itemstack1, 1, 2, false)) {
/* 216 */           return ItemStack.b;
/*     */         } 
/* 218 */       } else if (!a(itemstack1, 3, 39, false)) {
/* 219 */         return ItemStack.b;
/*     */       } 
/*     */       
/* 222 */       if (itemstack1.isEmpty()) {
/* 223 */         slot.set(ItemStack.b);
/*     */       }
/*     */       
/* 226 */       slot.d();
/* 227 */       if (itemstack1.getCount() == itemstack.getCount()) {
/* 228 */         return ItemStack.b;
/*     */       }
/*     */       
/* 231 */       slot.a(entityhuman, itemstack1);
/* 232 */       c();
/*     */     } 
/*     */     
/* 235 */     return itemstack;
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(EntityHuman entityhuman) {
/* 240 */     super.b(entityhuman);
/* 241 */     this.resultInventory.splitWithoutUpdate(2);
/* 242 */     this.containerAccess.a((world, blockposition) -> a(entityhuman, entityhuman.world, this.inventory));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ContainerCartography.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */