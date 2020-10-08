/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.destroystokyo.paper.event.block.AnvilDamagedEvent;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.bukkit.block.data.BlockData;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.StringUtils;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryAnvil;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryView;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ 
/*     */ public class ContainerAnvil extends ContainerAnvilAbstract {
/*  15 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   private int h;
/*     */   public String renameText;
/*     */   public final ContainerProperty levelCost;
/*  20 */   public int maximumRepairCost = 40;
/*     */   
/*     */   private CraftInventoryView bukkitEntity;
/*     */   
/*     */   public ContainerAnvil(int i, PlayerInventory playerinventory) {
/*  25 */     this(i, playerinventory, ContainerAccess.a);
/*     */   }
/*     */   
/*     */   public ContainerAnvil(int i, PlayerInventory playerinventory, ContainerAccess containeraccess) {
/*  29 */     super(Containers.ANVIL, i, playerinventory, containeraccess);
/*  30 */     this.levelCost = ContainerProperty.a();
/*  31 */     a(this.levelCost);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean a(IBlockData iblockdata) {
/*  36 */     return iblockdata.a(TagsBlock.ANVIL);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean b(EntityHuman entityhuman, boolean flag) {
/*  41 */     return ((entityhuman.abilities.canInstantlyBuild || entityhuman.expLevel >= this.levelCost.get()) && this.levelCost.get() > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected ItemStack a(EntityHuman entityhuman, ItemStack itemstack) {
/*  46 */     if (!entityhuman.abilities.canInstantlyBuild) {
/*  47 */       entityhuman.levelDown(-this.levelCost.get());
/*     */     }
/*     */     
/*  50 */     this.repairInventory.setItem(0, ItemStack.b);
/*  51 */     if (this.h > 0) {
/*  52 */       ItemStack itemstack1 = this.repairInventory.getItem(1);
/*     */       
/*  54 */       if (!itemstack1.isEmpty() && itemstack1.getCount() > this.h) {
/*  55 */         itemstack1.subtract(this.h);
/*  56 */         this.repairInventory.setItem(1, itemstack1);
/*     */       } else {
/*  58 */         this.repairInventory.setItem(1, ItemStack.b);
/*     */       } 
/*     */     } else {
/*  61 */       this.repairInventory.setItem(1, ItemStack.b);
/*     */     } 
/*     */     
/*  64 */     this.levelCost.set(0);
/*  65 */     this.containerAccess.a((world, blockposition) -> {
/*     */           IBlockData iblockdata = world.getType(blockposition);
/*     */           
/*     */           if (!entityhuman.abilities.canInstantlyBuild && iblockdata.a(TagsBlock.ANVIL) && entityhuman.getRandom().nextFloat() < 0.12F) {
/*     */             IBlockData iblockdata1 = BlockAnvil.c(iblockdata);
/*     */             
/*     */             AnvilDamagedEvent event = new AnvilDamagedEvent((InventoryView)getBukkitView(), (iblockdata1 != null) ? (BlockData)CraftBlockData.fromData(iblockdata1) : null);
/*     */             
/*     */             if (!event.callEvent()) {
/*     */               return;
/*     */             }
/*     */             if (event.getDamageState() == AnvilDamagedEvent.DamageState.BROKEN) {
/*     */               iblockdata1 = null;
/*     */             } else {
/*     */               iblockdata1 = ((CraftBlockData)event.getDamageState().getMaterial().createBlockData()).getState().set(BlockAnvil.FACING, iblockdata.get(BlockAnvil.FACING));
/*     */             } 
/*     */             if (iblockdata1 == null) {
/*     */               world.a(blockposition, false);
/*     */               world.triggerEffect(1029, blockposition, 0);
/*     */             } else {
/*     */               world.setTypeAndData(blockposition, iblockdata1, 2);
/*     */               world.triggerEffect(1030, blockposition, 0);
/*     */             } 
/*     */           } else {
/*     */             world.triggerEffect(1030, blockposition, 0);
/*     */           } 
/*     */         });
/*  92 */     return itemstack;
/*     */   }
/*     */ 
/*     */   
/*     */   public void e() {
/*  97 */     ItemStack itemstack = this.repairInventory.getItem(0);
/*     */     
/*  99 */     this.levelCost.set(1);
/* 100 */     int i = 0;
/* 101 */     byte b0 = 0;
/* 102 */     byte b1 = 0;
/*     */     
/* 104 */     if (itemstack.isEmpty()) {
/* 105 */       CraftEventFactory.callPrepareAnvilEvent((InventoryView)getBukkitView(), ItemStack.b);
/* 106 */       this.levelCost.set(0);
/*     */     } else {
/* 108 */       ItemStack itemstack1 = itemstack.cloneItemStack();
/* 109 */       ItemStack itemstack2 = this.repairInventory.getItem(1);
/* 110 */       Map<Enchantment, Integer> map = EnchantmentManager.a(itemstack1);
/* 111 */       int j = b0 + itemstack.getRepairCost() + (itemstack2.isEmpty() ? 0 : itemstack2.getRepairCost());
/*     */       
/* 113 */       this.h = 0;
/* 114 */       if (!itemstack2.isEmpty()) {
/* 115 */         boolean flag = (itemstack2.getItem() == Items.ENCHANTED_BOOK && !ItemEnchantedBook.d(itemstack2).isEmpty());
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 120 */         if (itemstack1.e() && itemstack1.getItem().a(itemstack, itemstack2)) {
/* 121 */           int k = Math.min(itemstack1.getDamage(), itemstack1.h() / 4);
/* 122 */           if (k <= 0) {
/* 123 */             CraftEventFactory.callPrepareAnvilEvent((InventoryView)getBukkitView(), ItemStack.b);
/* 124 */             this.levelCost.set(0);
/*     */             return;
/*     */           } 
/*     */           int i1;
/* 128 */           for (i1 = 0; k > 0 && i1 < itemstack2.getCount(); i1++) {
/* 129 */             int l = itemstack1.getDamage() - k;
/* 130 */             itemstack1.setDamage(l);
/* 131 */             i++;
/* 132 */             k = Math.min(itemstack1.getDamage(), itemstack1.h() / 4);
/*     */           } 
/*     */           
/* 135 */           this.h = i1;
/*     */         } else {
/* 137 */           if (!flag && (itemstack1.getItem() != itemstack2.getItem() || !itemstack1.e())) {
/* 138 */             CraftEventFactory.callPrepareAnvilEvent((InventoryView)getBukkitView(), ItemStack.b);
/* 139 */             this.levelCost.set(0);
/*     */             
/*     */             return;
/*     */           } 
/* 143 */           if (itemstack1.e() && !flag) {
/* 144 */             int k = itemstack.h() - itemstack.getDamage();
/* 145 */             int i1 = itemstack2.h() - itemstack2.getDamage();
/* 146 */             int l = i1 + itemstack1.h() * 12 / 100;
/* 147 */             int j1 = k + l;
/* 148 */             int k1 = itemstack1.h() - j1;
/*     */             
/* 150 */             if (k1 < 0) {
/* 151 */               k1 = 0;
/*     */             }
/*     */             
/* 154 */             if (k1 < itemstack1.getDamage()) {
/* 155 */               itemstack1.setDamage(k1);
/* 156 */               i += 2;
/*     */             } 
/*     */           } 
/*     */           
/* 160 */           Map<Enchantment, Integer> map1 = EnchantmentManager.a(itemstack2);
/* 161 */           boolean flag1 = false;
/* 162 */           boolean flag2 = false;
/* 163 */           Iterator<Enchantment> iterator = map1.keySet().iterator();
/*     */           
/* 165 */           while (iterator.hasNext()) {
/* 166 */             Enchantment enchantment = iterator.next();
/*     */             
/* 168 */             if (enchantment != null) {
/* 169 */               int l1 = ((Integer)map.getOrDefault(enchantment, Integer.valueOf(0))).intValue();
/* 170 */               int i2 = ((Integer)map1.get(enchantment)).intValue();
/*     */               
/* 172 */               i2 = (l1 == i2) ? (i2 + 1) : Math.max(i2, l1);
/* 173 */               boolean flag3 = enchantment.canEnchant(itemstack);
/*     */               
/* 175 */               if (this.player.abilities.canInstantlyBuild || itemstack.getItem() == Items.ENCHANTED_BOOK) {
/* 176 */                 flag3 = true;
/*     */               }
/*     */               
/* 179 */               Iterator<Enchantment> iterator1 = map.keySet().iterator();
/*     */               
/* 181 */               while (iterator1.hasNext()) {
/* 182 */                 Enchantment enchantment1 = iterator1.next();
/*     */                 
/* 184 */                 if (enchantment1 != enchantment && !enchantment.isCompatible(enchantment1)) {
/* 185 */                   flag3 = false;
/* 186 */                   i++;
/*     */                 } 
/*     */               } 
/*     */               
/* 190 */               if (!flag3) {
/* 191 */                 flag2 = true; continue;
/*     */               } 
/* 193 */               flag1 = true;
/* 194 */               if (i2 > enchantment.getMaxLevel()) {
/* 195 */                 i2 = enchantment.getMaxLevel();
/*     */               }
/*     */               
/* 198 */               map.put(enchantment, Integer.valueOf(i2));
/* 199 */               int j2 = 0;
/*     */               
/* 201 */               switch (enchantment.d()) {
/*     */                 case COMMON:
/* 203 */                   j2 = 1;
/*     */                   break;
/*     */                 case UNCOMMON:
/* 206 */                   j2 = 2;
/*     */                   break;
/*     */                 case RARE:
/* 209 */                   j2 = 4;
/*     */                   break;
/*     */                 case VERY_RARE:
/* 212 */                   j2 = 8;
/*     */                   break;
/*     */               } 
/* 215 */               if (flag) {
/* 216 */                 j2 = Math.max(1, j2 / 2);
/*     */               }
/*     */               
/* 219 */               i += j2 * i2;
/* 220 */               if (itemstack.getCount() > 1) {
/* 221 */                 i = 40;
/*     */               }
/*     */             } 
/*     */           } 
/*     */ 
/*     */           
/* 227 */           if (flag2 && !flag1) {
/* 228 */             CraftEventFactory.callPrepareAnvilEvent((InventoryView)getBukkitView(), ItemStack.b);
/* 229 */             this.levelCost.set(0);
/*     */             
/*     */             return;
/*     */           } 
/*     */         } 
/*     */       } 
/* 235 */       if (StringUtils.isBlank(this.renameText)) {
/* 236 */         if (itemstack.hasName()) {
/* 237 */           b1 = 1;
/* 238 */           i += b1;
/* 239 */           itemstack1.s();
/*     */         } 
/* 241 */       } else if (!this.renameText.equals(itemstack.getName().getString())) {
/* 242 */         b1 = 1;
/* 243 */         i += b1;
/* 244 */         itemstack1.a(new ChatComponentText(this.renameText));
/*     */       } 
/*     */       
/* 247 */       this.levelCost.set(j + i);
/* 248 */       if (i <= 0) {
/* 249 */         itemstack1 = ItemStack.b;
/*     */       }
/*     */       
/* 252 */       if (b1 == i && b1 > 0 && this.levelCost.get() >= this.maximumRepairCost) {
/* 253 */         this.levelCost.set(this.maximumRepairCost - 1);
/*     */       }
/*     */       
/* 256 */       if (this.levelCost.get() >= this.maximumRepairCost && !this.player.abilities.canInstantlyBuild) {
/* 257 */         itemstack1 = ItemStack.b;
/*     */       }
/*     */       
/* 260 */       if (!itemstack1.isEmpty()) {
/* 261 */         int k2 = itemstack1.getRepairCost();
/*     */         
/* 263 */         if (!itemstack2.isEmpty() && k2 < itemstack2.getRepairCost()) {
/* 264 */           k2 = itemstack2.getRepairCost();
/*     */         }
/*     */         
/* 267 */         if (b1 != i || b1 == 0) {
/* 268 */           k2 = d(k2);
/*     */         }
/*     */         
/* 271 */         itemstack1.setRepairCost(k2);
/* 272 */         EnchantmentManager.a(map, itemstack1);
/*     */       } 
/*     */       
/* 275 */       CraftEventFactory.callPrepareAnvilEvent((InventoryView)getBukkitView(), itemstack1);
/* 276 */       c();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static int d(int i) {
/* 281 */     return i * 2 + 1;
/*     */   }
/*     */   
/*     */   public void a(String s) {
/* 285 */     this.renameText = s;
/* 286 */     if (getSlot(2).hasItem()) {
/* 287 */       ItemStack itemstack = getSlot(2).getItem();
/*     */       
/* 289 */       if (StringUtils.isBlank(s)) {
/* 290 */         itemstack.s();
/*     */       } else {
/* 292 */         itemstack.a(new ChatComponentText(this.renameText));
/*     */       } 
/*     */     } 
/*     */     
/* 296 */     e();
/* 297 */     CraftEventFactory.callPrepareResultEvent(this, 2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CraftInventoryView getBukkitView() {
/* 303 */     if (this.bukkitEntity != null) {
/* 304 */       return this.bukkitEntity;
/*     */     }
/*     */ 
/*     */     
/* 308 */     CraftInventoryAnvil craftInventoryAnvil = new CraftInventoryAnvil(this.containerAccess.getLocation(), this.repairInventory, this.resultInventory, this);
/* 309 */     this.bukkitEntity = new CraftInventoryView((HumanEntity)this.player.getBukkitEntity(), (Inventory)craftInventoryAnvil, this);
/* 310 */     return this.bukkitEntity;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ContainerAnvil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */