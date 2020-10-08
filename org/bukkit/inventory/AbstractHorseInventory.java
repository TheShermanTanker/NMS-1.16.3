package org.bukkit.inventory;

import org.jetbrains.annotations.Nullable;

public interface AbstractHorseInventory extends Inventory {
  @Nullable
  ItemStack getSaddle();
  
  void setSaddle(@Nullable ItemStack paramItemStack);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\AbstractHorseInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */