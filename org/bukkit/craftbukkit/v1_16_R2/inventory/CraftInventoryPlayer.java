/*     */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*     */ import com.google.common.base.Preconditions;
/*     */ import java.util.List;
/*     */ import net.minecraft.server.v1_16_R2.EntityPlayer;
/*     */ import net.minecraft.server.v1_16_R2.IInventory;
/*     */ import net.minecraft.server.v1_16_R2.ItemStack;
/*     */ import net.minecraft.server.v1_16_R2.Packet;
/*     */ import net.minecraft.server.v1_16_R2.PlayerInventory;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.inventory.EquipmentSlot;
/*     */ import org.bukkit.inventory.InventoryHolder;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class CraftInventoryPlayer extends CraftInventory implements PlayerInventory, EntityEquipment {
/*     */   public CraftInventoryPlayer(PlayerInventory inventory) {
/*  17 */     super((IInventory)inventory);
/*     */   }
/*     */ 
/*     */   
/*     */   public PlayerInventory getInventory() {
/*  22 */     return (PlayerInventory)this.inventory;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack[] getStorageContents() {
/*  27 */     return asCraftMirror((List<ItemStack>)(getInventory()).items);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getItemInMainHand() {
/*  32 */     return CraftItemStack.asCraftMirror(getInventory().getItemInHand());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemInMainHand(ItemStack item) {
/*  37 */     setItem(getHeldItemSlot(), item);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getItemInOffHand() {
/*  42 */     return CraftItemStack.asCraftMirror((ItemStack)(getInventory()).extraSlots.get(0));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemInOffHand(ItemStack item) {
/*  47 */     ItemStack[] extra = getExtraContents();
/*  48 */     extra[0] = item;
/*  49 */     setExtraContents(extra);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getItemInHand() {
/*  54 */     return getItemInMainHand();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemInHand(ItemStack stack) {
/*  59 */     setItemInMainHand(stack);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItem(int index, ItemStack item) {
/*  64 */     super.setItem(index, item);
/*  65 */     if (getHolder() == null)
/*  66 */       return;  EntityPlayer player = ((CraftPlayer)getHolder()).getHandle();
/*  67 */     if (player.playerConnection == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  96 */     if (index < PlayerInventory.getHotbarSize()) {
/*  97 */       index += 36;
/*  98 */     } else if (index > 39) {
/*  99 */       index += 5;
/* 100 */     } else if (index > 35) {
/* 101 */       index = 8 - index - 36;
/*     */     } 
/* 103 */     player.playerConnection.sendPacket((Packet)new PacketPlayOutSetSlot(player.defaultContainer.windowId, index, CraftItemStack.asNMSCopy(item)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItem(EquipmentSlot slot, ItemStack item) {
/* 108 */     Preconditions.checkArgument((slot != null), "slot must not be null");
/*     */     
/* 110 */     switch (slot) {
/*     */       case HAND:
/* 112 */         setItemInMainHand(item);
/*     */         return;
/*     */       case OFF_HAND:
/* 115 */         setItemInOffHand(item);
/*     */         return;
/*     */       case FEET:
/* 118 */         setBoots(item);
/*     */         return;
/*     */       case LEGS:
/* 121 */         setLeggings(item);
/*     */         return;
/*     */       case CHEST:
/* 124 */         setChestplate(item);
/*     */         return;
/*     */       case HEAD:
/* 127 */         setHelmet(item);
/*     */         return;
/*     */     } 
/* 130 */     throw new IllegalArgumentException("Not implemented. This is a bug");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getItem(EquipmentSlot slot) {
/* 136 */     Preconditions.checkArgument((slot != null), "slot must not be null");
/*     */     
/* 138 */     switch (slot) {
/*     */       case HAND:
/* 140 */         return getItemInMainHand();
/*     */       case OFF_HAND:
/* 142 */         return getItemInOffHand();
/*     */       case FEET:
/* 144 */         return getBoots();
/*     */       case LEGS:
/* 146 */         return getLeggings();
/*     */       case CHEST:
/* 148 */         return getChestplate();
/*     */       case HEAD:
/* 150 */         return getHelmet();
/*     */     } 
/* 152 */     throw new IllegalArgumentException("Not implemented. This is a bug");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHeldItemSlot() {
/* 158 */     return (getInventory()).itemInHandIndex;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeldItemSlot(int slot) {
/* 163 */     Validate.isTrue((slot >= 0 && slot < PlayerInventory.getHotbarSize()), "Slot is not between 0 and 8 inclusive");
/* 164 */     (getInventory()).itemInHandIndex = slot;
/* 165 */     (((CraftPlayer)getHolder()).getHandle()).playerConnection.sendPacket((Packet)new PacketPlayOutHeldItemSlot(slot));
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getHelmet() {
/* 170 */     return getItem(getSize() - 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getChestplate() {
/* 175 */     return getItem(getSize() - 3);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getLeggings() {
/* 180 */     return getItem(getSize() - 4);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getBoots() {
/* 185 */     return getItem(getSize() - 5);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHelmet(ItemStack helmet) {
/* 190 */     setItem(getSize() - 2, helmet);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setChestplate(ItemStack chestplate) {
/* 195 */     setItem(getSize() - 3, chestplate);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLeggings(ItemStack leggings) {
/* 200 */     setItem(getSize() - 4, leggings);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBoots(ItemStack boots) {
/* 205 */     setItem(getSize() - 5, boots);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack[] getArmorContents() {
/* 210 */     return asCraftMirror((List<ItemStack>)(getInventory()).armor);
/*     */   }
/*     */   
/*     */   private void setSlots(ItemStack[] items, int baseSlot, int length) {
/* 214 */     if (items == null) {
/* 215 */       items = new ItemStack[length];
/*     */     }
/* 217 */     Preconditions.checkArgument((items.length <= length), "items.length must be < %s", length);
/*     */     
/* 219 */     for (int i = 0; i < length; i++) {
/* 220 */       if (i >= items.length) {
/* 221 */         setItem(baseSlot + i, (ItemStack)null);
/*     */       } else {
/* 223 */         setItem(baseSlot + i, items[i]);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStorageContents(ItemStack[] items) throws IllegalArgumentException {
/* 230 */     setSlots(items, 0, (getInventory()).items.size());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setArmorContents(ItemStack[] items) {
/* 235 */     setSlots(items, (getInventory()).items.size(), (getInventory()).armor.size());
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack[] getExtraContents() {
/* 240 */     return asCraftMirror((List<ItemStack>)(getInventory()).extraSlots);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExtraContents(ItemStack[] items) {
/* 245 */     setSlots(items, (getInventory()).items.size() + (getInventory()).armor.size(), (getInventory()).extraSlots.size());
/*     */   }
/*     */ 
/*     */   
/*     */   public HumanEntity getHolder() {
/* 250 */     return (HumanEntity)this.inventory.getOwner();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getItemInHandDropChance() {
/* 255 */     return getItemInMainHandDropChance();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemInHandDropChance(float chance) {
/* 260 */     setItemInMainHandDropChance(chance);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getItemInMainHandDropChance() {
/* 265 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemInMainHandDropChance(float chance) {
/* 270 */     throw new UnsupportedOperationException("Cannot set drop chance for PlayerInventory");
/*     */   }
/*     */ 
/*     */   
/*     */   public float getItemInOffHandDropChance() {
/* 275 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItemInOffHandDropChance(float chance) {
/* 280 */     throw new UnsupportedOperationException("Cannot set drop chance for PlayerInventory");
/*     */   }
/*     */ 
/*     */   
/*     */   public float getHelmetDropChance() {
/* 285 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHelmetDropChance(float chance) {
/* 290 */     throw new UnsupportedOperationException("Cannot set drop chance for PlayerInventory");
/*     */   }
/*     */ 
/*     */   
/*     */   public float getChestplateDropChance() {
/* 295 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setChestplateDropChance(float chance) {
/* 300 */     throw new UnsupportedOperationException("Cannot set drop chance for PlayerInventory");
/*     */   }
/*     */ 
/*     */   
/*     */   public float getLeggingsDropChance() {
/* 305 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLeggingsDropChance(float chance) {
/* 310 */     throw new UnsupportedOperationException("Cannot set drop chance for PlayerInventory");
/*     */   }
/*     */ 
/*     */   
/*     */   public float getBootsDropChance() {
/* 315 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBootsDropChance(float chance) {
/* 320 */     throw new UnsupportedOperationException("Cannot set drop chance for PlayerInventory");
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftInventoryPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */