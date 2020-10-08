package org.bukkit.inventory;

import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface EntityEquipment {
  void setItem(@NotNull EquipmentSlot paramEquipmentSlot, @Nullable ItemStack paramItemStack);
  
  @NotNull
  ItemStack getItem(@NotNull EquipmentSlot paramEquipmentSlot);
  
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
  
  @Nullable
  ItemStack getHelmet();
  
  void setHelmet(@Nullable ItemStack paramItemStack);
  
  @Nullable
  ItemStack getChestplate();
  
  void setChestplate(@Nullable ItemStack paramItemStack);
  
  @Nullable
  ItemStack getLeggings();
  
  void setLeggings(@Nullable ItemStack paramItemStack);
  
  @Nullable
  ItemStack getBoots();
  
  void setBoots(@Nullable ItemStack paramItemStack);
  
  @NotNull
  ItemStack[] getArmorContents();
  
  void setArmorContents(@NotNull ItemStack[] paramArrayOfItemStack);
  
  void clear();
  
  @Deprecated
  float getItemInHandDropChance();
  
  @Deprecated
  void setItemInHandDropChance(float paramFloat);
  
  float getItemInMainHandDropChance();
  
  void setItemInMainHandDropChance(float paramFloat);
  
  float getItemInOffHandDropChance();
  
  void setItemInOffHandDropChance(float paramFloat);
  
  float getHelmetDropChance();
  
  void setHelmetDropChance(float paramFloat);
  
  float getChestplateDropChance();
  
  void setChestplateDropChance(float paramFloat);
  
  float getLeggingsDropChance();
  
  void setLeggingsDropChance(float paramFloat);
  
  float getBootsDropChance();
  
  void setBootsDropChance(float paramFloat);
  
  @Nullable
  Entity getHolder();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\EntityEquipment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */