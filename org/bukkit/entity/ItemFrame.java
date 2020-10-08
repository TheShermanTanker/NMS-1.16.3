package org.bukkit.entity;

import org.bukkit.Rotation;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ItemFrame extends Hanging {
  @NotNull
  ItemStack getItem();
  
  void setItem(@Nullable ItemStack paramItemStack);
  
  void setItem(@Nullable ItemStack paramItemStack, boolean paramBoolean);
  
  @NotNull
  Rotation getRotation();
  
  void setRotation(@NotNull Rotation paramRotation) throws IllegalArgumentException;
  
  boolean isVisible();
  
  void setVisible(boolean paramBoolean);
  
  boolean isFixed();
  
  void setFixed(boolean paramBoolean);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\ItemFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */