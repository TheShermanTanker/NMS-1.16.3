/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.stream.Collectors;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryGrindstone;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryView;
/*     */ import org.bukkit.entity.ExperienceOrb;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ 
/*     */ public class ContainerGrindstone extends Container {
/*  17 */   private CraftInventoryView bukkitEntity = null;
/*     */   private Player player;
/*     */   private final IInventory resultInventory;
/*     */   
/*     */   public CraftInventoryView getBukkitView() {
/*  22 */     if (this.bukkitEntity != null) {
/*  23 */       return this.bukkitEntity;
/*     */     }
/*     */     
/*  26 */     CraftInventoryGrindstone inventory = new CraftInventoryGrindstone(this.craftInventory, this.resultInventory);
/*  27 */     this.bukkitEntity = new CraftInventoryView((HumanEntity)this.player, (Inventory)inventory, this);
/*  28 */     return this.bukkitEntity;
/*     */   }
/*     */ 
/*     */   
/*     */   private final IInventory craftInventory;
/*     */   private final ContainerAccess containerAccess;
/*     */   
/*     */   public ContainerGrindstone(int i, PlayerInventory playerinventory) {
/*  36 */     this(i, playerinventory, ContainerAccess.a);
/*     */   }
/*     */   
/*     */   public ContainerGrindstone(int i, PlayerInventory playerinventory, final ContainerAccess containeraccess) {
/*  40 */     super(Containers.GRINDSTONE, i);
/*  41 */     this.resultInventory = new InventoryCraftResult();
/*  42 */     this.craftInventory = new InventorySubcontainer(2)
/*     */       {
/*     */         public void update() {
/*  45 */           super.update();
/*  46 */           ContainerGrindstone.this.a(this);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public Location getLocation() {
/*  52 */           return containeraccess.getLocation();
/*     */         }
/*     */       };
/*     */     
/*  56 */     this.containerAccess = containeraccess;
/*  57 */     a(new Slot(this.craftInventory, 0, 49, 19)
/*     */         {
/*     */           public boolean isAllowed(ItemStack itemstack) {
/*  60 */             return (itemstack.e() || itemstack.getItem() == Items.ENCHANTED_BOOK || itemstack.hasEnchantments());
/*     */           }
/*     */         });
/*  63 */     a(new Slot(this.craftInventory, 1, 49, 40)
/*     */         {
/*     */           public boolean isAllowed(ItemStack itemstack) {
/*  66 */             return (itemstack.e() || itemstack.getItem() == Items.ENCHANTED_BOOK || itemstack.hasEnchantments());
/*     */           }
/*     */         });
/*  69 */     a(new Slot(this.resultInventory, 2, 129, 34)
/*     */         {
/*     */           public boolean isAllowed(ItemStack itemstack) {
/*  72 */             return false;
/*     */           }
/*     */ 
/*     */           
/*     */           public ItemStack a(EntityHuman entityhuman, ItemStack itemstack) {
/*  77 */             containeraccess.a((world, blockposition) -> {
/*     */                   int j = a(world);
/*     */                   
/*     */                   while (j > 0) {
/*     */                     int k = EntityExperienceOrb.getOrbValue(j);
/*     */                     
/*     */                     j -= k;
/*     */                     
/*     */                     world.addEntity(new EntityExperienceOrb(world, blockposition.getX(), blockposition.getY() + 0.5D, blockposition.getZ() + 0.5D, k, ExperienceOrb.SpawnReason.GRINDSTONE, entityhuman));
/*     */                   } 
/*     */                   world.triggerEffect(1042, blockposition, 0);
/*     */                 });
/*  89 */             ContainerGrindstone.this.craftInventory.setItem(0, ItemStack.b);
/*  90 */             ContainerGrindstone.this.craftInventory.setItem(1, ItemStack.b);
/*  91 */             return itemstack;
/*     */           }
/*     */           
/*     */           private int a(World world) {
/*  95 */             byte b0 = 0;
/*  96 */             int j = b0 + e(ContainerGrindstone.this.craftInventory.getItem(0));
/*     */             
/*  98 */             j += e(ContainerGrindstone.this.craftInventory.getItem(1));
/*  99 */             if (j > 0) {
/* 100 */               int k = (int)Math.ceil(j / 2.0D);
/*     */               
/* 102 */               return k + world.random.nextInt(k);
/*     */             } 
/* 104 */             return 0;
/*     */           }
/*     */ 
/*     */           
/*     */           private int e(ItemStack itemstack) {
/* 109 */             int j = 0;
/* 110 */             Map<Enchantment, Integer> map = EnchantmentManager.a(itemstack);
/* 111 */             Iterator<Map.Entry<Enchantment, Integer>> iterator = map.entrySet().iterator();
/*     */             
/* 113 */             while (iterator.hasNext()) {
/* 114 */               Map.Entry<Enchantment, Integer> entry = iterator.next();
/* 115 */               Enchantment enchantment = entry.getKey();
/* 116 */               Integer integer = entry.getValue();
/*     */               
/* 118 */               if (!enchantment.c()) {
/* 119 */                 j += enchantment.a(integer.intValue());
/*     */               }
/*     */             } 
/*     */             
/* 123 */             return j;
/*     */           }
/*     */         });
/*     */     
/*     */     int j;
/*     */     
/* 129 */     for (j = 0; j < 3; j++) {
/* 130 */       for (int k = 0; k < 9; k++) {
/* 131 */         a(new Slot(playerinventory, k + j * 9 + 9, 8 + k * 18, 84 + j * 18));
/*     */       }
/*     */     } 
/*     */     
/* 135 */     for (j = 0; j < 9; j++) {
/* 136 */       a(new Slot(playerinventory, j, 8 + j * 18, 142));
/*     */     }
/*     */     
/* 139 */     this.player = (Player)playerinventory.player.getBukkitEntity();
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IInventory iinventory) {
/* 144 */     super.a(iinventory);
/* 145 */     if (iinventory == this.craftInventory) {
/* 146 */       e();
/* 147 */       CraftEventFactory.callPrepareResultEvent(this, 2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void e() {
/* 153 */     ItemStack itemstack = this.craftInventory.getItem(0);
/* 154 */     ItemStack itemstack1 = this.craftInventory.getItem(1);
/* 155 */     boolean flag = (!itemstack.isEmpty() || !itemstack1.isEmpty());
/* 156 */     boolean flag1 = (!itemstack.isEmpty() && !itemstack1.isEmpty());
/*     */     
/* 158 */     if (flag) {
/* 159 */       int i; ItemStack itemstack2; boolean flag2 = ((!itemstack.isEmpty() && itemstack.getItem() != Items.ENCHANTED_BOOK && !itemstack.hasEnchantments()) || (!itemstack1.isEmpty() && itemstack1.getItem() != Items.ENCHANTED_BOOK && !itemstack1.hasEnchantments()));
/*     */       
/* 161 */       if (itemstack.getCount() > 1 || itemstack1.getCount() > 1 || (!flag1 && flag2)) {
/* 162 */         this.resultInventory.setItem(0, ItemStack.b);
/* 163 */         c();
/*     */         
/*     */         return;
/*     */       } 
/* 167 */       byte b0 = 1;
/*     */ 
/*     */ 
/*     */       
/* 171 */       if (flag1) {
/* 172 */         if (itemstack.getItem() != itemstack1.getItem()) {
/* 173 */           this.resultInventory.setItem(0, ItemStack.b);
/* 174 */           c();
/*     */           
/*     */           return;
/*     */         } 
/* 178 */         Item item = itemstack.getItem();
/* 179 */         int j = item.getMaxDurability() - itemstack.getDamage();
/* 180 */         int k = item.getMaxDurability() - itemstack1.getDamage();
/* 181 */         int l = j + k + item.getMaxDurability() * 5 / 100;
/*     */         
/* 183 */         i = Math.max(item.getMaxDurability() - l, 0);
/* 184 */         itemstack2 = b(itemstack, itemstack1);
/* 185 */         if (!itemstack2.e()) {
/* 186 */           if (!ItemStack.matches(itemstack, itemstack1)) {
/* 187 */             this.resultInventory.setItem(0, ItemStack.b);
/* 188 */             c();
/*     */             
/*     */             return;
/*     */           } 
/* 192 */           b0 = 2;
/*     */         } 
/*     */       } else {
/* 195 */         boolean flag3 = !itemstack.isEmpty();
/*     */         
/* 197 */         i = flag3 ? itemstack.getDamage() : itemstack1.getDamage();
/* 198 */         itemstack2 = flag3 ? itemstack : itemstack1;
/*     */       } 
/*     */       
/* 201 */       this.resultInventory.setItem(0, a(itemstack2, i, b0));
/*     */     } else {
/* 203 */       this.resultInventory.setItem(0, ItemStack.b);
/*     */     } 
/*     */     
/* 206 */     c();
/*     */   }
/*     */   
/*     */   private ItemStack b(ItemStack itemstack, ItemStack itemstack1) {
/* 210 */     ItemStack itemstack2 = itemstack.cloneItemStack();
/* 211 */     Map<Enchantment, Integer> map = EnchantmentManager.a(itemstack1);
/* 212 */     Iterator<Map.Entry<Enchantment, Integer>> iterator = map.entrySet().iterator();
/*     */     
/* 214 */     while (iterator.hasNext()) {
/* 215 */       Map.Entry<Enchantment, Integer> entry = iterator.next();
/* 216 */       Enchantment enchantment = entry.getKey();
/*     */       
/* 218 */       if (!enchantment.c() || EnchantmentManager.getEnchantmentLevel(enchantment, itemstack2) == 0) {
/* 219 */         itemstack2.addEnchantment(enchantment, ((Integer)entry.getValue()).intValue());
/*     */       }
/*     */     } 
/*     */     
/* 223 */     return itemstack2;
/*     */   }
/*     */   
/*     */   private ItemStack a(ItemStack itemstack, int i, int j) {
/* 227 */     ItemStack itemstack1 = itemstack.cloneItemStack();
/*     */     
/* 229 */     itemstack1.removeTag("Enchantments");
/* 230 */     itemstack1.removeTag("StoredEnchantments");
/* 231 */     if (i > 0) {
/* 232 */       itemstack1.setDamage(i);
/*     */     } else {
/* 234 */       itemstack1.removeTag("Damage");
/*     */     } 
/*     */     
/* 237 */     itemstack1.setCount(j);
/*     */ 
/*     */     
/* 240 */     Map<Enchantment, Integer> map = (Map<Enchantment, Integer>)EnchantmentManager.a(itemstack).entrySet().stream().filter(entry -> ((Enchantment)entry.getKey()).c()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
/*     */     
/* 242 */     EnchantmentManager.a(map, itemstack1);
/* 243 */     itemstack1.setRepairCost(0);
/* 244 */     if (itemstack1.getItem() == Items.ENCHANTED_BOOK && map.size() == 0) {
/* 245 */       itemstack1 = new ItemStack(Items.BOOK);
/* 246 */       if (itemstack.hasName()) {
/* 247 */         itemstack1.a(itemstack.getName());
/*     */       }
/*     */     } 
/*     */     
/* 251 */     for (int k = 0; k < map.size(); k++) {
/* 252 */       itemstack1.setRepairCost(ContainerAnvil.d(itemstack1.getRepairCost()));
/*     */     }
/*     */     
/* 255 */     return itemstack1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(EntityHuman entityhuman) {
/* 260 */     super.b(entityhuman);
/* 261 */     this.containerAccess.a((world, blockposition) -> a(entityhuman, world, this.craftInventory));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canUse(EntityHuman entityhuman) {
/* 268 */     if (!this.checkReachable) return true; 
/* 269 */     return a(this.containerAccess, entityhuman, Blocks.GRINDSTONE);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack shiftClick(EntityHuman entityhuman, int i) {
/* 274 */     ItemStack itemstack = ItemStack.b;
/* 275 */     Slot slot = this.slots.get(i);
/*     */     
/* 277 */     if (slot != null && slot.hasItem()) {
/* 278 */       ItemStack itemstack1 = slot.getItem();
/*     */       
/* 280 */       itemstack = itemstack1.cloneItemStack();
/* 281 */       ItemStack itemstack2 = this.craftInventory.getItem(0);
/* 282 */       ItemStack itemstack3 = this.craftInventory.getItem(1);
/*     */       
/* 284 */       if (i == 2) {
/* 285 */         if (!a(itemstack1, 3, 39, true)) {
/* 286 */           return ItemStack.b;
/*     */         }
/*     */         
/* 289 */         slot.a(itemstack1, itemstack);
/* 290 */       } else if (i != 0 && i != 1) {
/* 291 */         if (!itemstack2.isEmpty() && !itemstack3.isEmpty()) {
/* 292 */           if (i >= 3 && i < 30) {
/* 293 */             if (!a(itemstack1, 30, 39, false)) {
/* 294 */               return ItemStack.b;
/*     */             }
/* 296 */           } else if (i >= 30 && i < 39 && !a(itemstack1, 3, 30, false)) {
/* 297 */             return ItemStack.b;
/*     */           } 
/* 299 */         } else if (!a(itemstack1, 0, 2, false)) {
/* 300 */           return ItemStack.b;
/*     */         } 
/* 302 */       } else if (!a(itemstack1, 3, 39, false)) {
/* 303 */         return ItemStack.b;
/*     */       } 
/*     */       
/* 306 */       if (itemstack1.isEmpty()) {
/* 307 */         slot.set(ItemStack.b);
/*     */       } else {
/* 309 */         slot.d();
/*     */       } 
/*     */       
/* 312 */       if (itemstack1.getCount() == itemstack.getCount()) {
/* 313 */         return ItemStack.b;
/*     */       }
/*     */       
/* 316 */       slot.a(entityhuman, itemstack1);
/*     */     } 
/*     */     
/* 319 */     return itemstack;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ContainerGrindstone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */