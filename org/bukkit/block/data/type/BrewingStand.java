package org.bukkit.block.data.type;

import java.util.Set;
import org.bukkit.block.data.BlockData;
import org.jetbrains.annotations.NotNull;

public interface BrewingStand extends BlockData {
  boolean hasBottle(int paramInt);
  
  void setBottle(int paramInt, boolean paramBoolean);
  
  @NotNull
  Set<Integer> getBottles();
  
  int getMaximumBottles();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\type\BrewingStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */