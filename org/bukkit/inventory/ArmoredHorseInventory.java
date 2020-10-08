package org.bukkit.inventory;

import org.jetbrains.annotations.Nullable;

public interface ArmoredHorseInventory extends AbstractHorseInventory {
  @Nullable
  ItemStack getArmor();
  
  void setArmor(@Nullable ItemStack paramItemStack);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\ArmoredHorseInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */