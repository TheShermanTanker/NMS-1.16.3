package org.bukkit.entity;

import org.bukkit.DyeColor;
import org.jetbrains.annotations.NotNull;

public interface Wolf extends Tameable, Sittable {
  boolean isAngry();
  
  void setAngry(boolean paramBoolean);
  
  @NotNull
  DyeColor getCollarColor();
  
  void setCollarColor(@NotNull DyeColor paramDyeColor);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Wolf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */