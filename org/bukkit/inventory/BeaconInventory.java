package org.bukkit.inventory;

import org.jetbrains.annotations.Nullable;

public interface BeaconInventory extends Inventory {
  void setItem(@Nullable ItemStack paramItemStack);
  
  @Nullable
  ItemStack getItem();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\BeaconInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */