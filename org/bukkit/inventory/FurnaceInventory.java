package org.bukkit.inventory;

import org.bukkit.block.Furnace;
import org.jetbrains.annotations.Nullable;

public interface FurnaceInventory extends Inventory {
  @Nullable
  ItemStack getResult();
  
  @Nullable
  ItemStack getFuel();
  
  @Nullable
  ItemStack getSmelting();
  
  void setFuel(@Nullable ItemStack paramItemStack);
  
  void setResult(@Nullable ItemStack paramItemStack);
  
  void setSmelting(@Nullable ItemStack paramItemStack);
  
  @Nullable
  Furnace getHolder();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\FurnaceInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */