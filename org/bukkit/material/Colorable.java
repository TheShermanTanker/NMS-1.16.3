package org.bukkit.material;

import org.bukkit.DyeColor;
import org.bukkit.UndefinedNullability;
import org.jetbrains.annotations.Nullable;

public interface Colorable {
  @Nullable
  DyeColor getColor();
  
  void setColor(@UndefinedNullability("defined by subclass") DyeColor paramDyeColor);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Colorable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */