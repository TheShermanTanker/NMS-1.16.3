package org.bukkit.inventory;

import org.bukkit.block.DoubleChest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface DoubleChestInventory extends Inventory {
  @NotNull
  Inventory getLeftSide();
  
  @NotNull
  Inventory getRightSide();
  
  @Nullable
  DoubleChest getHolder();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\DoubleChestInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */