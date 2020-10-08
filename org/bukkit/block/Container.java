package org.bukkit.block;

import org.bukkit.Nameable;
import org.bukkit.inventory.BlockInventoryHolder;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public interface Container extends TileState, BlockInventoryHolder, Lockable, Nameable {
  @NotNull
  Inventory getInventory();
  
  @NotNull
  Inventory getSnapshotInventory();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\Container.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */