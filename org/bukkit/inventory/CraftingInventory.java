package org.bukkit.inventory;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface CraftingInventory extends Inventory {
  @Nullable
  ItemStack getResult();
  
  @NotNull
  ItemStack[] getMatrix();
  
  void setResult(@Nullable ItemStack paramItemStack);
  
  void setMatrix(@NotNull ItemStack[] paramArrayOfItemStack);
  
  @Nullable
  Recipe getRecipe();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\CraftingInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */