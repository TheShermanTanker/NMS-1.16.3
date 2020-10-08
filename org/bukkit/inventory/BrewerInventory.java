package org.bukkit.inventory;

import org.bukkit.block.BrewingStand;
import org.jetbrains.annotations.Nullable;

public interface BrewerInventory extends Inventory {
  @Nullable
  ItemStack getIngredient();
  
  void setIngredient(@Nullable ItemStack paramItemStack);
  
  @Nullable
  ItemStack getFuel();
  
  void setFuel(@Nullable ItemStack paramItemStack);
  
  @Nullable
  BrewingStand getHolder();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\BrewerInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */