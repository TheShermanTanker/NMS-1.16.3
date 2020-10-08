package org.bukkit.block;

import org.bukkit.inventory.BlockInventoryHolder;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public interface Lectern extends TileState, BlockInventoryHolder {
  int getPage();
  
  void setPage(int paramInt);
  
  @NotNull
  Inventory getInventory();
  
  @NotNull
  Inventory getSnapshotInventory();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\Lectern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */