package org.bukkit.inventory;

import org.bukkit.entity.HumanEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface PlayerInventory extends Inventory {
  @NotNull
  ItemStack[] getArmorContents();
  
  @NotNull
  ItemStack[] getExtraContents();
  
  @Nullable
  ItemStack getHelmet();
  
  @Nullable
  ItemStack getChestplate();
  
  @Nullable
  ItemStack getLeggings();
  
  @Nullable
  ItemStack getBoots();
  
  void setItem(int paramInt, @Nullable ItemStack paramItemStack);
  
  void setItem(@NotNull EquipmentSlot paramEquipmentSlot, @Nullable ItemStack paramItemStack);
  
  @NotNull
  ItemStack getItem(@NotNull EquipmentSlot paramEquipmentSlot);
  
  void setArmorContents(@Nullable ItemStack[] paramArrayOfItemStack);
  
  void setExtraContents(@Nullable ItemStack[] paramArrayOfItemStack);
  
  void setHelmet(@Nullable ItemStack paramItemStack);
  
  void setChestplate(@Nullable ItemStack paramItemStack);
  
  void setLeggings(@Nullable ItemStack paramItemStack);
  
  void setBoots(@Nullable ItemStack paramItemStack);
  
  @NotNull
  ItemStack getItemInMainHand();
  
  void setItemInMainHand(@Nullable ItemStack paramItemStack);
  
  @NotNull
  ItemStack getItemInOffHand();
  
  void setItemInOffHand(@Nullable ItemStack paramItemStack);
  
  @Deprecated
  @NotNull
  ItemStack getItemInHand();
  
  @Deprecated
  void setItemInHand(@Nullable ItemStack paramItemStack);
  
  int getHeldItemSlot();
  
  void setHeldItemSlot(int paramInt);
  
  @Nullable
  HumanEntity getHolder();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\PlayerInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */