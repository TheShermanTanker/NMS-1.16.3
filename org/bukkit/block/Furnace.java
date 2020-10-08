package org.bukkit.block;

import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public interface Furnace extends Container {
  short getBurnTime();
  
  void setBurnTime(short paramShort);
  
  short getCookTime();
  
  void setCookTime(short paramShort);
  
  int getCookTimeTotal();
  
  void setCookTimeTotal(int paramInt);
  
  double getCookSpeedMultiplier();
  
  void setCookSpeedMultiplier(double paramDouble);
  
  @NotNull
  FurnaceInventory getInventory();
  
  @NotNull
  FurnaceInventory getSnapshotInventory();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\Furnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */