package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Openable;
import org.bukkit.block.data.Powerable;

public interface Gate extends Directional, Openable, Powerable {
  boolean isInWall();
  
  void setInWall(boolean paramBoolean);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\type\Gate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */