package org.bukkit.inventory;

import org.jetbrains.annotations.Nullable;

public interface EnchantingInventory extends Inventory {
  void setItem(@Nullable ItemStack paramItemStack);
  
  @Nullable
  ItemStack getItem();
  
  void setSecondary(@Nullable ItemStack paramItemStack);
  
  @Nullable
  ItemStack getSecondary();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\EnchantingInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */