package org.bukkit.block.data;

import java.util.Set;
import org.bukkit.Axis;
import org.jetbrains.annotations.NotNull;

public interface Orientable extends BlockData {
  @NotNull
  Axis getAxis();
  
  void setAxis(@NotNull Axis paramAxis);
  
  @NotNull
  Set<Axis> getAxes();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\Orientable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */