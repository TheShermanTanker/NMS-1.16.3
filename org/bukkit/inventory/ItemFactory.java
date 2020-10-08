package org.bukkit.inventory;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.hover.content.Content;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.UndefinedNullability;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ItemFactory {
  @UndefinedNullability
  ItemMeta getItemMeta(@NotNull Material paramMaterial);
  
  boolean isApplicable(@Nullable ItemMeta paramItemMeta, @Nullable ItemStack paramItemStack) throws IllegalArgumentException;
  
  boolean isApplicable(@Nullable ItemMeta paramItemMeta, @Nullable Material paramMaterial) throws IllegalArgumentException;
  
  boolean equals(@Nullable ItemMeta paramItemMeta1, @Nullable ItemMeta paramItemMeta2) throws IllegalArgumentException;
  
  @Nullable
  ItemMeta asMetaFor(@NotNull ItemMeta paramItemMeta, @NotNull ItemStack paramItemStack) throws IllegalArgumentException;
  
  @Nullable
  ItemMeta asMetaFor(@NotNull ItemMeta paramItemMeta, @NotNull Material paramMaterial) throws IllegalArgumentException;
  
  @NotNull
  Color getDefaultLeatherColor();
  
  @Deprecated
  @NotNull
  Material updateMaterial(@NotNull ItemMeta paramItemMeta, @NotNull Material paramMaterial) throws IllegalArgumentException;
  
  @NotNull
  ItemStack ensureServerConversions(@NotNull ItemStack paramItemStack);
  
  @Nullable
  String getI18NDisplayName(@Nullable ItemStack paramItemStack);
  
  @NotNull
  Content hoverContentOf(@NotNull ItemStack paramItemStack);
  
  @NotNull
  Content hoverContentOf(@NotNull Entity paramEntity);
  
  @NotNull
  Content hoverContentOf(@NotNull Entity paramEntity, @Nullable String paramString);
  
  @NotNull
  Content hoverContentOf(@NotNull Entity paramEntity, @Nullable BaseComponent paramBaseComponent);
  
  @NotNull
  Content hoverContentOf(@NotNull Entity paramEntity, @NotNull BaseComponent[] paramArrayOfBaseComponent);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\ItemFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */