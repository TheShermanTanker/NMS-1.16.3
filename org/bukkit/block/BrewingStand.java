package org.bukkit.block;

import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public interface BrewingStand extends Container {
  int getBrewingTime();
  
  void setBrewingTime(int paramInt);
  
  int getFuelLevel();
  
  void setFuelLevel(int paramInt);
  
  @NotNull
  BrewerInventory getInventory();
  
  @NotNull
  BrewerInventory getSnapshotInventory();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\BrewingStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */