/*     */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*     */ 
/*     */ import net.minecraft.server.v1_16_R2.ChatComponentText;
/*     */ import net.minecraft.server.v1_16_R2.Container;
/*     */ import net.minecraft.server.v1_16_R2.ContainerAnvil;
/*     */ import net.minecraft.server.v1_16_R2.ContainerBeacon;
/*     */ import net.minecraft.server.v1_16_R2.ContainerBlastFurnace;
/*     */ import net.minecraft.server.v1_16_R2.ContainerBrewingStand;
/*     */ import net.minecraft.server.v1_16_R2.ContainerCartography;
/*     */ import net.minecraft.server.v1_16_R2.ContainerChest;
/*     */ import net.minecraft.server.v1_16_R2.ContainerDispenser;
/*     */ import net.minecraft.server.v1_16_R2.ContainerEnchantTable;
/*     */ import net.minecraft.server.v1_16_R2.ContainerFurnaceFurnace;
/*     */ import net.minecraft.server.v1_16_R2.ContainerGrindstone;
/*     */ import net.minecraft.server.v1_16_R2.ContainerHopper;
/*     */ import net.minecraft.server.v1_16_R2.ContainerLectern;
/*     */ import net.minecraft.server.v1_16_R2.ContainerLoom;
/*     */ import net.minecraft.server.v1_16_R2.ContainerMerchant;
/*     */ import net.minecraft.server.v1_16_R2.ContainerProperties;
/*     */ import net.minecraft.server.v1_16_R2.ContainerShulkerBox;
/*     */ import net.minecraft.server.v1_16_R2.ContainerSmithing;
/*     */ import net.minecraft.server.v1_16_R2.ContainerSmoker;
/*     */ import net.minecraft.server.v1_16_R2.ContainerStonecutter;
/*     */ import net.minecraft.server.v1_16_R2.ContainerWorkbench;
/*     */ import net.minecraft.server.v1_16_R2.Containers;
/*     */ import net.minecraft.server.v1_16_R2.EntityHuman;
/*     */ import net.minecraft.server.v1_16_R2.IChatBaseComponent;
/*     */ import net.minecraft.server.v1_16_R2.IContainerProperties;
/*     */ import net.minecraft.server.v1_16_R2.IInventory;
/*     */ import net.minecraft.server.v1_16_R2.ItemStack;
/*     */ import net.minecraft.server.v1_16_R2.Packet;
/*     */ import net.minecraft.server.v1_16_R2.PacketPlayOutOpenWindow;
/*     */ import net.minecraft.server.v1_16_R2.PlayerInventory;
/*     */ import net.minecraft.server.v1_16_R2.Slot;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.event.inventory.InventoryType;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ 
/*     */ public class CraftContainer
/*     */   extends Container {
/*     */   private final InventoryView view;
/*     */   private InventoryType cachedType;
/*     */   
/*     */   public CraftContainer(InventoryView view, EntityHuman player, int id) {
/*  47 */     super(getNotchInventoryType(view.getTopInventory()), id);
/*  48 */     this.view = view;
/*     */     
/*  50 */     IInventory top = ((CraftInventory)view.getTopInventory()).getInventory();
/*  51 */     PlayerInventory bottom = (PlayerInventory)((CraftInventory)view.getBottomInventory()).getInventory();
/*  52 */     this.cachedType = view.getType();
/*  53 */     this.cachedTitle = view.getTitle();
/*  54 */     this.cachedSize = getSize();
/*  55 */     setupSlots(top, bottom, player);
/*     */   }
/*     */   private String cachedTitle; private Container delegate; private final int cachedSize;
/*     */   public CraftContainer(Inventory inventory, EntityHuman player, int id) {
/*  59 */     this(new InventoryView(inventory, player)
/*     */         {
/*     */           public Inventory getTopInventory() {
/*  62 */             return inventory;
/*     */           }
/*     */ 
/*     */           
/*     */           public Inventory getBottomInventory() {
/*  67 */             return (Inventory)getPlayer().getInventory();
/*     */           }
/*     */ 
/*     */           
/*     */           public HumanEntity getPlayer() {
/*  72 */             return (HumanEntity)player.getBukkitEntity();
/*     */           }
/*     */ 
/*     */           
/*     */           public InventoryType getType() {
/*  77 */             return inventory.getType();
/*     */           }
/*     */ 
/*     */           
/*     */           public String getTitle() {
/*  82 */             return (inventory instanceof CraftInventoryCustom) ? ((CraftInventoryCustom.MinecraftInventory)((CraftInventory)inventory).getInventory()).getTitle() : inventory.getType().getDefaultTitle();
/*     */           }
/*     */         }player, id);
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryView getBukkitView() {
/*  89 */     return this.view;
/*     */   }
/*     */   
/*     */   private int getSize() {
/*  93 */     return this.view.getTopInventory().getSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(EntityHuman entityhuman) {
/*  98 */     if (this.cachedType == this.view.getType() && this.cachedSize == getSize() && this.cachedTitle.equals(this.view.getTitle())) {
/*  99 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 104 */     boolean typeChanged = (this.cachedType != this.view.getType());
/* 105 */     this.cachedType = this.view.getType();
/* 106 */     this.cachedTitle = this.view.getTitle();
/* 107 */     if (this.view.getPlayer() instanceof CraftPlayer) {
/* 108 */       CraftPlayer player = (CraftPlayer)this.view.getPlayer();
/* 109 */       Containers<?> type = getNotchInventoryType(this.view.getTopInventory());
/* 110 */       IInventory top = ((CraftInventory)this.view.getTopInventory()).getInventory();
/* 111 */       PlayerInventory bottom = (PlayerInventory)((CraftInventory)this.view.getBottomInventory()).getInventory();
/* 112 */       this.items.clear();
/* 113 */       this.slots.clear();
/* 114 */       if (typeChanged) {
/* 115 */         setupSlots(top, bottom, (EntityHuman)player.getHandle());
/*     */       }
/* 117 */       int size = getSize();
/* 118 */       (player.getHandle()).playerConnection.sendPacket((Packet)new PacketPlayOutOpenWindow(this.windowId, type, (IChatBaseComponent)new ChatComponentText(this.cachedTitle)));
/* 119 */       player.updateInventory();
/*     */     } 
/* 121 */     return true;
/*     */   }
/*     */   
/*     */   public static Containers getNotchInventoryType(Inventory inventory) {
/* 125 */     switch (inventory.getType()) {
/*     */       case PLAYER:
/*     */       case CHEST:
/*     */       case ENDER_CHEST:
/*     */       case BARREL:
/* 130 */         switch (inventory.getSize()) {
/*     */           case 9:
/* 132 */             return Containers.GENERIC_9X1;
/*     */           case 18:
/* 134 */             return Containers.GENERIC_9X2;
/*     */           case 27:
/* 136 */             return Containers.GENERIC_9X3;
/*     */           case 36:
/*     */           case 41:
/* 139 */             return Containers.GENERIC_9X4;
/*     */           case 45:
/* 141 */             return Containers.GENERIC_9X5;
/*     */           case 54:
/* 143 */             return Containers.GENERIC_9X6;
/*     */         } 
/* 145 */         throw new IllegalArgumentException("Unsupported custom inventory size " + inventory.getSize());
/*     */       
/*     */       case WORKBENCH:
/* 148 */         return Containers.CRAFTING;
/*     */       case FURNACE:
/* 150 */         return Containers.FURNACE;
/*     */       case DISPENSER:
/* 152 */         return Containers.GENERIC_3X3;
/*     */       case ENCHANTING:
/* 154 */         return Containers.ENCHANTMENT;
/*     */       case BREWING:
/* 156 */         return Containers.BREWING_STAND;
/*     */       case BEACON:
/* 158 */         return Containers.BEACON;
/*     */       case ANVIL:
/* 160 */         return Containers.ANVIL;
/*     */       case SMITHING:
/* 162 */         return Containers.SMITHING;
/*     */       case HOPPER:
/* 164 */         return Containers.HOPPER;
/*     */       case DROPPER:
/* 166 */         return Containers.GENERIC_3X3;
/*     */       case SHULKER_BOX:
/* 168 */         return Containers.SHULKER_BOX;
/*     */       case BLAST_FURNACE:
/* 170 */         return Containers.BLAST_FURNACE;
/*     */       case LECTERN:
/* 172 */         return Containers.LECTERN;
/*     */       case SMOKER:
/* 174 */         return Containers.SMOKER;
/*     */       case LOOM:
/* 176 */         return Containers.LOOM;
/*     */       case CARTOGRAPHY:
/* 178 */         return Containers.CARTOGRAPHY_TABLE;
/*     */       case GRINDSTONE:
/* 180 */         return Containers.GRINDSTONE;
/*     */       case STONECUTTER:
/* 182 */         return Containers.STONECUTTER;
/*     */       case CREATIVE:
/*     */       case CRAFTING:
/*     */       case MERCHANT:
/* 186 */         throw new IllegalArgumentException("Can't open a " + inventory.getType() + " inventory!");
/*     */     } 
/*     */     
/* 189 */     return Containers.GENERIC_9X3;
/*     */   }
/*     */ 
/*     */   
/*     */   private void setupSlots(IInventory top, PlayerInventory bottom, EntityHuman entityhuman) {
/* 194 */     int windowId = -1;
/* 195 */     switch (this.cachedType) {
/*     */ 
/*     */       
/*     */       case PLAYER:
/*     */       case CHEST:
/*     */       case ENDER_CHEST:
/*     */       case BARREL:
/* 202 */         this.delegate = (Container)new ContainerChest(Containers.GENERIC_9X3, windowId, bottom, top, top.getSize() / 9);
/*     */         break;
/*     */       case DISPENSER:
/*     */       case DROPPER:
/* 206 */         this.delegate = (Container)new ContainerDispenser(windowId, bottom, top);
/*     */         break;
/*     */       case FURNACE:
/* 209 */         this.delegate = (Container)new ContainerFurnaceFurnace(windowId, bottom, top, (IContainerProperties)new ContainerProperties(4));
/*     */         break;
/*     */       case WORKBENCH:
/*     */       case CRAFTING:
/* 213 */         setupWorkbench(top, (IInventory)bottom);
/*     */         break;
/*     */       case ENCHANTING:
/* 216 */         this.delegate = (Container)new ContainerEnchantTable(windowId, bottom);
/*     */         break;
/*     */       case BREWING:
/* 219 */         this.delegate = (Container)new ContainerBrewingStand(windowId, bottom, top, (IContainerProperties)new ContainerProperties(2));
/*     */         break;
/*     */       case HOPPER:
/* 222 */         this.delegate = (Container)new ContainerHopper(windowId, bottom, top);
/*     */         break;
/*     */       case ANVIL:
/* 225 */         this.delegate = (Container)new ContainerAnvil(windowId, bottom);
/*     */         break;
/*     */       case SMITHING:
/* 228 */         this.delegate = (Container)new ContainerSmithing(windowId, bottom);
/*     */         break;
/*     */       case BEACON:
/* 231 */         this.delegate = (Container)new ContainerBeacon(windowId, (IInventory)bottom);
/*     */         break;
/*     */       case SHULKER_BOX:
/* 234 */         this.delegate = (Container)new ContainerShulkerBox(windowId, bottom, top);
/*     */         break;
/*     */       case BLAST_FURNACE:
/* 237 */         this.delegate = (Container)new ContainerBlastFurnace(windowId, bottom, top, (IContainerProperties)new ContainerProperties(4));
/*     */         break;
/*     */       case LECTERN:
/* 240 */         this.delegate = (Container)new ContainerLectern(windowId, top, (IContainerProperties)new ContainerProperties(1), bottom);
/*     */         break;
/*     */       case SMOKER:
/* 243 */         this.delegate = (Container)new ContainerSmoker(windowId, bottom, top, (IContainerProperties)new ContainerProperties(4));
/*     */         break;
/*     */       case LOOM:
/* 246 */         this.delegate = (Container)new ContainerLoom(windowId, bottom);
/*     */         break;
/*     */       case CARTOGRAPHY:
/* 249 */         this.delegate = (Container)new ContainerCartography(windowId, bottom);
/*     */         break;
/*     */       case GRINDSTONE:
/* 252 */         this.delegate = (Container)new ContainerGrindstone(windowId, bottom);
/*     */         break;
/*     */       case STONECUTTER:
/* 255 */         this.delegate = (Container)new ContainerStonecutter(windowId, bottom);
/*     */         break;
/*     */       case MERCHANT:
/* 258 */         this.delegate = (Container)new ContainerMerchant(windowId, bottom);
/*     */         break;
/*     */     } 
/*     */     
/* 262 */     if (this.delegate != null) {
/* 263 */       this.items = this.delegate.items;
/* 264 */       this.slots = this.delegate.slots;
/*     */     } 
/*     */ 
/*     */     
/* 268 */     if (this.cachedType == InventoryType.WORKBENCH) {
/* 269 */       this.delegate = (Container)new ContainerWorkbench(windowId, bottom);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void setupWorkbench(IInventory top, IInventory bottom) {
/* 275 */     a(new Slot(top, 0, 124, 35));
/*     */ 
/*     */     
/*     */     int row;
/*     */     
/* 280 */     for (row = 0; row < 3; row++) {
/* 281 */       for (int i = 0; i < 3; i++) {
/* 282 */         a(new Slot(top, 1 + i + row * 3, 30 + i * 18, 17 + row * 18));
/*     */       }
/*     */     } 
/*     */     
/* 286 */     for (row = 0; row < 3; row++) {
/* 287 */       for (int i = 0; i < 9; i++) {
/* 288 */         a(new Slot(bottom, i + row * 9 + 9, 8 + i * 18, 84 + row * 18));
/*     */       }
/*     */     } 
/*     */     
/* 292 */     for (int col = 0; col < 9; col++) {
/* 293 */       a(new Slot(bottom, col, 8 + col * 18, 142));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack shiftClick(EntityHuman entityhuman, int i) {
/* 300 */     return (this.delegate != null) ? this.delegate.shiftClick(entityhuman, i) : super.shiftClick(entityhuman, i);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canUse(EntityHuman entity) {
/* 305 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Containers<?> getType() {
/* 310 */     return getNotchInventoryType(this.view.getTopInventory());
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */