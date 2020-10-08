package org.bukkit.entity;

import org.bukkit.Art;
import org.jetbrains.annotations.NotNull;

public interface Painting extends Hanging {
  @NotNull
  Art getArt();
  
  boolean setArt(@NotNull Art paramArt);
  
  boolean setArt(@NotNull Art paramArt, boolean paramBoolean);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Painting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */