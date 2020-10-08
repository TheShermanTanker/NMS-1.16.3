package org.bukkit.inventory;

import org.jetbrains.annotations.Nullable;

public interface LlamaInventory extends SaddledHorseInventory {
  @Nullable
  ItemStack getDecor();
  
  void setDecor(@Nullable ItemStack paramItemStack);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\LlamaInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */