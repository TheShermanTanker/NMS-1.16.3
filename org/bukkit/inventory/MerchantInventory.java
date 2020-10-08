package org.bukkit.inventory;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface MerchantInventory extends Inventory {
  int getSelectedRecipeIndex();
  
  @Nullable
  MerchantRecipe getSelectedRecipe();
  
  @NotNull
  Merchant getMerchant();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\MerchantInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */