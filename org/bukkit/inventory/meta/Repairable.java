package org.bukkit.inventory.meta;

import org.jetbrains.annotations.NotNull;

public interface Repairable {
  boolean hasRepairCost();
  
  int getRepairCost();
  
  void setRepairCost(int paramInt);
  
  @NotNull
  Repairable clone();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\meta\Repairable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */