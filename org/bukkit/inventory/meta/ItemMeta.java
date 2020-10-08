package org.bukkit.inventory.meta;

import com.destroystokyo.paper.Namespaced;
import com.google.common.collect.Multimap;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.tags.CustomItemTagContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ItemMeta extends Cloneable, ConfigurationSerializable, PersistentDataHolder {
  boolean hasDisplayName();
  
  @NotNull
  String getDisplayName();
  
  @NotNull
  BaseComponent[] getDisplayNameComponent();
  
  void setDisplayName(@Nullable String paramString);
  
  void setDisplayNameComponent(@Nullable BaseComponent[] paramArrayOfBaseComponent);
  
  boolean hasLocalizedName();
  
  @NotNull
  String getLocalizedName();
  
  void setLocalizedName(@Nullable String paramString);
  
  boolean hasLore();
  
  @Nullable
  List<String> getLore();
  
  @Nullable
  List<BaseComponent[]> getLoreComponents();
  
  void setLore(@Nullable List<String> paramList);
  
  void setLoreComponents(@Nullable List<BaseComponent[]> paramList);
  
  boolean hasCustomModelData();
  
  int getCustomModelData();
  
  void setCustomModelData(@Nullable Integer paramInteger);
  
  boolean hasEnchants();
  
  boolean hasEnchant(@NotNull Enchantment paramEnchantment);
  
  int getEnchantLevel(@NotNull Enchantment paramEnchantment);
  
  @NotNull
  Map<Enchantment, Integer> getEnchants();
  
  boolean addEnchant(@NotNull Enchantment paramEnchantment, int paramInt, boolean paramBoolean);
  
  boolean removeEnchant(@NotNull Enchantment paramEnchantment);
  
  boolean hasConflictingEnchant(@NotNull Enchantment paramEnchantment);
  
  void addItemFlags(@NotNull ItemFlag... paramVarArgs);
  
  void removeItemFlags(@NotNull ItemFlag... paramVarArgs);
  
  @NotNull
  Set<ItemFlag> getItemFlags();
  
  boolean hasItemFlag(@NotNull ItemFlag paramItemFlag);
  
  boolean isUnbreakable();
  
  void setUnbreakable(boolean paramBoolean);
  
  boolean hasAttributeModifiers();
  
  @Nullable
  Multimap<Attribute, AttributeModifier> getAttributeModifiers();
  
  @NotNull
  Multimap<Attribute, AttributeModifier> getAttributeModifiers(@NotNull EquipmentSlot paramEquipmentSlot);
  
  @Nullable
  Collection<AttributeModifier> getAttributeModifiers(@NotNull Attribute paramAttribute);
  
  boolean addAttributeModifier(@NotNull Attribute paramAttribute, @NotNull AttributeModifier paramAttributeModifier);
  
  void setAttributeModifiers(@Nullable Multimap<Attribute, AttributeModifier> paramMultimap);
  
  boolean removeAttributeModifier(@NotNull Attribute paramAttribute);
  
  boolean removeAttributeModifier(@NotNull EquipmentSlot paramEquipmentSlot);
  
  boolean removeAttributeModifier(@NotNull Attribute paramAttribute, @NotNull AttributeModifier paramAttributeModifier);
  
  @Deprecated
  @NotNull
  CustomItemTagContainer getCustomTagContainer();
  
  @Deprecated
  void setVersion(int paramInt);
  
  @NotNull
  ItemMeta clone();
  
  @Deprecated
  Set<Material> getCanDestroy();
  
  @Deprecated
  void setCanDestroy(Set<Material> paramSet);
  
  @Deprecated
  Set<Material> getCanPlaceOn();
  
  @Deprecated
  void setCanPlaceOn(Set<Material> paramSet);
  
  @NotNull
  Set<Namespaced> getDestroyableKeys();
  
  void setDestroyableKeys(@NotNull Collection<Namespaced> paramCollection);
  
  @NotNull
  Set<Namespaced> getPlaceableKeys();
  
  @NotNull
  void setPlaceableKeys(@NotNull Collection<Namespaced> paramCollection);
  
  boolean hasPlaceableKeys();
  
  boolean hasDestroyableKeys();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\meta\ItemMeta.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */